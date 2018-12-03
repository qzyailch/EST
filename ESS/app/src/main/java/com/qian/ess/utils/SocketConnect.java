package com.qian.ess.utils;

import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import com.qian.ess.bean.socket.FileInfo;
import com.qian.ess.common.Constants;
import com.qian.ess.common.ESSFilePath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2018/8/13 0013.
 */

public class SocketConnect {

    private String ip;
    private int port;
    private Callback callback;

    private Socket mSocket;
    private InputStream is = null;
    private InputStreamReader isr = null;
    private BufferedReader br = null;
    private OutputStream os = null;

    public Boolean isConnected = false;

    //正在发送的文件
    private FileInfo sendFileInfo;
    //正在发送的文件信息，当收到成功的反馈后，则取下一段数据进行发送，否则再次发送当前数据
    private byte[] sendBuffer;

    //正在发送文件的输入流信息，从里面读取数据，发送出去
    private FileInputStream sendInputStream;

    private String TAG = "SocketConnect";

    private Thread mReceiveThread;
    private Thread mHeartThread = null;

    private Boolean isAutoConnect = true;

    private long maxcs = 5000;   //接收服务器返回心跳数据的最大超时时间，如果不需要，可设置为一个比较大的数字
    private long lasttime = 0;
    private Boolean waitAnswer = false;

    //标志正在接收文件，不需要进行类型判断
    private boolean mIsReceiveFile = false;
    //当前正在接收文件的标志
    private long mReceiveIdentifier = 0;

    //正在接收的文件
    Map<String, FileInfo> mFileMap = new HashMap<>();

    public interface Callback {
        void onConnected();

        void onDisconnected();

        void onReconnected();

        void onSend();

        //进度 0：接收文件 1：发送文件
        void onProgress(int type, long progress, long size);

        //type 0：字符串 1：文件
        void onReceived(int type, byte[] msg);

        void onError(String msg);
    }

    public SocketConnect(String ip, int port, Callback callback) {
        super();
        this.ip = ip;
        this.port = port;
        this.callback = callback;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * 设置等待接收心跳包的最大超时时间
     *
     * @param t 毫秒
     */
    public void setMaxOvertime(long t) {
        maxcs = t;
    }


    /**
     * 开始连接
     */
    public void connect() {
        _disconnect();

        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    _connect();
                }
            }).start();
        } else {
            _connect();
        }

        //连接了socket之后，才创建监听进程。
        //openWatchThread();
    }


    /**
     * 断开连接
     */
    public void disconnect() {
        _disconnect();

        closeWatchThread();
    }

    private void _connect() {
        try {
            Logger.i("result", "开始连接");
            mSocket = new Socket(ip, port);
            Boolean isConnect = mSocket.isConnected();
            if (isConnect) {
                Logger.i("result", "连接成功");
                is = mSocket.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);

                os = mSocket.getOutputStream();

                isConnected = true;
                callback.onConnected();
                Log.e(TAG, "onConnected");
                mFileMap = new HashMap<>();
                //创建监听线程
                openThread();
            }
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError(e.getMessage());
            Log.e(TAG, "onError");
        }

    }


    private void _disconnect() {
        try {
            if (mSocket != null) {
                isConnected = false;
                closeThread();

                if (!mSocket.isClosed()) {
                    if (!mSocket.isInputShutdown()) {
                        mSocket.shutdownInput();
                    }
                    if (!mSocket.isOutputShutdown()) {
                        mSocket.shutdownOutput();
                    }
                    //此处先关socket再关流。
                    if (br != null) {
                        br.close();
                        br = null;
                    }
                    if (isr != null) {
                        isr.close();
                        isr = null;
                    }
                    if (is != null) {
                        is.close();
                        is = null;
                    }
                    if (os != null) {
                        os.close();
                        os = null;
                    }

                    mSocket.close();
                }
                mSocket = null;

                //调用回调
                callback.onDisconnected();
                Log.e(TAG, "onDisconnected");
            }


        } catch (Exception e) {
            e.printStackTrace();
            callback.onError("断开连接异常");
            Log.e(TAG, "onError");
        }

    }


    private void closeThread() {
        if (mReceiveThread != null) {
            isConnected = false;
            mReceiveThread.interrupt();
            mReceiveThread = null;
            Log.e(TAG, "close thread");
        }
    }

    private void closeWatchThread() {
        if (mHeartThread != null) {
            isAutoConnect = false;
            mHeartThread.interrupt();
            mHeartThread = null;
            Log.e(TAG, "close mHeartThread");
        }
    }

    private void openThread() {
        closeThread();
        mReceiveThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (isConnected) {
                    try {
//                        int readLen = 0;
//                        readLen = is.read(buffer);
//                        if (readLen > 0) {
//                            byte[] data = new byte[readLen];
//                            System.arraycopy(buffer, 0, data, 0, readLen);
//
//                            callback.onReceived(data);
//                            lasttime = System.currentTimeMillis();
//                        }

                        //这儿的判断，正在接收包，直接写文件
                        //大于0才开始写文件
                        if (mIsReceiveFile) {
                            if (is.available() > 0) {
                                //获取文件包体
                                //从mReceiveIdentifier里面回去FileInfo对象
                                receiveFile(mFileMap.get(mReceiveIdentifier + ""));
                            }
                        } else {
                            //判断数据类型
                            byte[] typeByte = new byte[1];
                            int type = is.read(typeByte);
                            Logger.i("onReceived", "是否读到消息类型：" + type);
                            if (type > 0) {
                                Logger.i("onReceived", "消息类型：" + typeByte[0]);
                                if (Constants.FILE_CONSTANTS.SEND_TYPE_STRING == typeByte[0]) {
                                    //接收字符串
                                    receiveString();
                                } else if (Constants.FILE_CONSTANTS.SEND_TYPE_FILE == typeByte[0]) {
                                    //获取文件数据header，并开始接收第一段文件数据
                                    receiveFileHeader();
                                } else if (Constants.FILE_CONSTANTS.SEND_TYPE_FILE_CONTENT == typeByte[0]) {
                                    //获取文件包体
                                    receiveFileContent();
                                } else if (Constants.FILE_CONSTANTS.SEND_TYPE_FILE_FEEDBACK == typeByte[0]) {
                                    //接收回调
                                    receiveFeedBack();
                                } else {
                                    callback.onError("数据格式错误，只接收字符串和文件");
                                }
                            } else {
                                disconnect();
                            }
                        }

//                        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//                        byte[] b = new byte[1024];
//                        int n;
//                        while ((n = is.read(b)) != -1) {
//                            outStream.write(b, 0, n);
//                        }
//                        callback.onReceived(outStream.toByteArray());
//                        outStream.close();
//                        lasttime = System.currentTimeMillis();

                    } catch (Exception e) {
                        e.printStackTrace();
                        callback.onError("读取数据异常");
                        Log.e(TAG, "onError");
                        disconnect();
                    }

                }
            }
        });
        mReceiveThread.start();
    }

    /**
     * 接收字符串
     */
    private void receiveString() {
        try {
            Logger.i("onReceived", "开始接收字符串");
            int available = is.available();
            byte[] all_byte = new byte[available];
            int readLen = is.read(all_byte);
            if (readLen > 0) {
                callback.onReceived(0, all_byte);
                lasttime = System.currentTimeMillis();
            }
        } catch (Exception e) {
            callback.onError("接收字符串异常");
            e.printStackTrace();
        }
    }

    /**
     * 接收文件Header
     */
    private void receiveFileHeader() {
        try {
            Logger.i("onReceived", "开始接收文件Header");

            //这是header
            //文件名长度
            int fileNameLength = 0;
            //分包个数
            int subPackagesTotal = 0;
            //识别码 yyyyMMddHHmmssSSS
            long identifier = 0;
            //文件大小
            long fileSize = 0;
            //当前包长度
            int subPackageslength = 0;
            //文件名
            String fileName = "";

            //文件名长度
            byte[] fileNameLengthByte = new byte[4];
            if (is.read(fileNameLengthByte) > 0) {
                fileNameLength = BytesUtils.bytesToInt(fileNameLengthByte, 0);
                Logger.i("onReceived", "文件名长度：" + fileNameLength);
            }

            //分包个数
            byte[] subPackagesTotalByte = new byte[4];
            if (is.read(subPackagesTotalByte) > 0) {
                subPackagesTotal = BytesUtils.bytesToInt(subPackagesTotalByte, 0);
                Logger.i("onReceived", "分包个数：" + subPackagesTotal);
            }

            //识别码
            byte[] identifierByte = new byte[8];
            if (is.read(identifierByte) > 0) {
                identifier = BytesUtils.bytesToLong(identifierByte, 0, true);
                Logger.i("onReceived", "识别码：" + Arrays.toString(identifierByte) + "，" + identifier);
            }

            //文件大小
            byte[] fileSizeByte = new byte[8];
            if (is.read(fileSizeByte) > 0) {
                fileSize = BytesUtils.bytesToLong(fileSizeByte, 0, true);
                Logger.i("onReceived", "文件大小：" + fileSize);
            }

            //包长度
            byte[] subPackageslengthByte = new byte[4];
            if (is.read(subPackageslengthByte) > 0) {
                subPackageslength = BytesUtils.bytesToInt(subPackageslengthByte, 0);
                Logger.i("onReceived", "包长度：" + subPackageslength);
            }

            //文件名
            byte[] fileNameByte = new byte[fileNameLength];
            if (is.read(fileNameByte) > 0) {
                fileName = new String(fileNameByte, Charset.defaultCharset());
                Logger.i("onReceived", "文件名：" + fileName);
            }

            long skip = is.skip(1024 - (29 + fileNameLength));
            Logger.i("onReceived", "跳过：" + skip);

            String receiveFilePath = ESSFilePath.RECEIVE_FILE + File.separator + fileName;
            File file = new File(receiveFilePath);
            if (!file.getParentFile().getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            //创建文件输出流
            FileOutputStream saveFileOutput = new FileOutputStream(receiveFilePath);

            //新建一个正在接收的文件
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(fileName);
            fileInfo.setFilePath(receiveFilePath);
            fileInfo.setIdentifier(identifier);
            fileInfo.setPackagesNo(0);
            fileInfo.setSaveFileOutputStream(saveFileOutput);
            fileInfo.setSubPackagesTotal(subPackagesTotal);
            fileInfo.setReceiveSubPackagesLength(0);
            fileInfo.setSubPackagesLength(subPackageslength); //包长度
            fileInfo.setProgress(0);
            fileInfo.setSize(fileSize);
            mFileMap.put(identifier + "", fileInfo);

            //获取文件数据
            receiveFile(fileInfo);
        } catch (Exception e) {
            callback.onError("接收文件Header异常");
            e.printStackTrace();
        }
    }

    /**
     * 接收文件包体
     */
    private void receiveFileContent() {
        try {
            Logger.i("onReceived", "开始接收文件包体");

            //这是包体
            //识别码 yyyyMMddHHmmssSSS
            long identifier = 0;
            //包序号
            int packagesNo = 0;
            //包长度
            int subPackageslength = 0;

            //识别码
            byte[] identifierByte = new byte[8];
            if (is.read(identifierByte) > 0) {
                identifier = BytesUtils.bytesToLong(identifierByte, 0, true);
                Logger.i("onReceived", "识别码：" + identifier);
            }

            //包序号
            byte[] packagesNoByte = new byte[4];
            if (is.read(packagesNoByte) > 0) {
                packagesNo = BytesUtils.bytesToInt(packagesNoByte, 0);
                Logger.i("onReceived", "包序号：" + packagesNo);
            }

            //包长度
            byte[] subPackageslengthByte = new byte[4];
            if (is.read(subPackageslengthByte) > 0) {
                subPackageslength = BytesUtils.bytesToInt(subPackageslengthByte, 0);
                Logger.i("onReceived", "包长度：" + subPackageslength);
            }

            if (!mFileMap.containsKey("" + identifier)) {
                //不包含识别码，则清空当前数据包
                clear();
                return;
            }

            FileInfo fileInfo = mFileMap.get(identifier + "");
            //packagesNo这是当前你发过来的包序号
            //跟这儿是匹配的
            //判断到我已经成功接收到的包序号，和你发过来的包序号-1相等，则开始接收你发过来这个包
            if (fileInfo.getPackagesNo() == packagesNo - 1) {
                //初始化当前接受到的包大小为0
                //首先初始化这个包接收到的数据为0
                fileInfo.setReceiveSubPackagesLength(0);
                //设置包长度
                fileInfo.setSubPackagesLength(subPackageslength);
                receiveFile(fileInfo);
            } else {
                //包序号不正确
                clear();
                //反馈
                feedBack(FileInfo.FLAG_FAILURE, fileInfo.getIdentifier(), packagesNo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收文件
     *
     * @param fileInfo 正在接收的文件信息
     */
    private void receiveFile(FileInfo fileInfo) {
        try {
            Logger.i("onReceived", "开始接收文件，识别码：" + fileInfo.getIdentifier());

            //然后继续从is里面接收文件信息
            //保存当前数据包的长度
            int receiveTotal = 0;
            //创建一个文件,指定保存路径和刚才传输过来的文件名
            byte[] buf = new byte[1024];
            while (is.available() > 0 && fileInfo.getProgress() < fileInfo.getSize()) {
                int len = is.read(buf);
                if (len != -1) {
                    receiveTotal += len;
                    fileInfo.getSaveFileOutputStream().write(buf, 0, len);//这一句
                    fileInfo.setProgress(fileInfo.getProgress() + len);

                    callback.onProgress(0, fileInfo.getProgress(), fileInfo.getSize());
                }
            }
            //上面这一整段就是写文件，receiveTotal表示从is也就是InputStream中接受到的所有数据，当前的

            //这儿的log意思
            //receiveTotal：就是上面这一争端接收到的数据长度，也就是一次从InputStream中拿到的数据，不一定就是1024*1024那么多，也许会被分包，后面说
            //(fileInfo.getReceiveSubPackagesLength() + receiveTotal)刚开始接收这个包的时候，初始化为0了，当接受一段数据后，加起来，这儿只是log
            //fileInfo.getSubPackagesLength()应该接收到的长度
            //getPackagesNo 序号 ，这儿+1只是为了看log，表示我正在接收getPackagesNo+1这个序号的包，并没有实际操作
            //getSize文件总长度
            Logger.i("onReceived",
                    "当前输入流接收到的长度：" + receiveTotal
                            + "\n当前数据包接收到长度：" + (fileInfo.getReceiveSubPackagesLength() + receiveTotal)
                            + "\n当前数据包本身应该接收到的长度：" + (fileInfo.getSubPackagesLength())
                            + "\n包序号：" + (fileInfo.getPackagesNo() + 1)
                            + "\n已经接收文件长度：" + fileInfo.getProgress()
                            + "\n文件总长度：" + fileInfo.getSize());

            //当前接收到的包长度
            //设置进去  操作在这儿
            fileInfo.setReceiveSubPackagesLength(fileInfo.getReceiveSubPackagesLength() + receiveTotal);
            //当前接收到的包长度与包本身长度相等时，则表名当前包接收完毕，反馈成功的消息，等待下一次接收
            if (fileInfo.getReceiveSubPackagesLength() == fileInfo.getSubPackagesLength()) {
                mIsReceiveFile = false;
                //当前包接收完毕后，包序号+1
                //fileInfo.getPackagesNo()这个表示我已经接受成功了的包个数，也就是包序号
                //当这一次的inputStream接收完毕后，获取到fileInfo.getReceiveSubPackagesLength() == fileInfo.getSubPackagesLength()
                //也就是本来应该接受的长度，和实际接收到的长度相等
                //那就说明这一次的数据是接受成功的
                //那包序号+1，表示已经成功接受包序号+1个包
                //比如当header这个包接收完毕。包序号为1
                //第二个包体接收完毕，包序号+1
                //就这个意思
                //实际操作在这儿
                fileInfo.setPackagesNo(fileInfo.getPackagesNo() + 1);
                //反馈
                //反馈一样的，因为上面已经+1了，那下面这儿直接获取到序号，表名这个序号接受完毕，反馈回去
                feedBack(FileInfo.FLAG_SUCCESS, fileInfo.getIdentifier(), fileInfo.getPackagesNo());
            } else {
                //当不相等时，表示被http分包了，则下一次的InputStream不需要判断类型，直接用来写入文件流
                //getReceiveSubPackagesLength和getSubPackagesLength不相等，也就是这个包我已经接收到的长度和需要接收的长度不相等
                //mReceiveIdentifier临时全局存一下标志位，也就是yyyyMM...这个东西
                //并把mIsReceiveFile设为true，表示下一段流数据也是这个包的文件数据，直接用来写文件
                mReceiveIdentifier = fileInfo.getIdentifier();
                mIsReceiveFile = true;
            }

            //进度与总Size相同时，表示接收完毕
            if (fileInfo.getProgress() == fileInfo.getSize()) {
                mIsReceiveFile = false;
                fileInfo.getSaveFileOutputStream().flush();
                fileInfo.getSaveFileOutputStream().close();
                callback.onReceived(1, fileInfo.getFilePath().getBytes());
                lasttime = System.currentTimeMillis();

                mFileMap.remove(fileInfo.getIdentifier() + "");
            }
        } catch (Exception e) {
            callback.onError("接收文件异常");
            e.printStackTrace();

            clear();
            //反馈
            feedBack(FileInfo.FLAG_FAILURE, fileInfo.getIdentifier(), fileInfo.getPackagesNo());
        }
    }

    /**
     * 接收文件后，反馈消息
     *
     * @param flag       1成功，其他失败
     * @param identifier 识别码
     * @param packagesNo 包序号
     */
    private void feedBack(byte flag, long identifier, int packagesNo) {
        try {
            //界面上弹消息
            Logger.i("onSend", "标志：" + identifier + "，包序号：" + packagesNo + "，" + (flag == FileInfo.FLAG_SUCCESS ? "接收成功" : "接受失败"));
            //标记消息类型为3，反馈消息
            byte[] type = new byte[]{Constants.FILE_CONSTANTS.SEND_TYPE_FILE_FEEDBACK};
            //识别码
            byte[] identifierByte = BytesUtils.longToBytes(identifier, 0, true);
            Logger.i("onSend", "识别码：" + Arrays.toString(identifierByte) + "，" + identifier);
            //包序号
            byte[] packagesNoByte = BytesUtils.intToBytes(packagesNo);
            //标记反馈结果 1成功 其他失败
            byte[] flagByte = new byte[]{flag};
            byte[] sendData = BytesUtils.byteMergerAll(type, identifierByte, packagesNoByte, flagByte);
            os.write(sendData);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //清空当前流中的错误数据
    private void clear() {
        try {
            byte[] buf = new byte[1024];
            while (is.available() > 0) {
                int len = is.read(buf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openWatchThread() {
        //closeWatchThread();
        if (mHeartThread != null) {
            return;
        }
        mHeartThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (isAutoConnect) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        //加入超时判断
                        if (waitAnswer) {
                            if ((System.currentTimeMillis() - lasttime) > maxcs) {
                                isConnected = false;
                            }
                        }

                        sendHeart(0xff);

                        if (isConnected) {

                        } else {
                            //未连接的情况下，重新连接服务器
                            Log.e(TAG, "onReconnect");
                            callback.onReconnected();
                            _disconnect();
                            _connect();
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        callback.onError("读取数据异常");
                        Log.e(TAG, "onError");
                    }

                }
            }
        });
        mHeartThread.start();
    }

    //接收文件发送完毕后的回调
    private void receiveFeedBack() {
        try {
            //识别码 yyyyMMddHHmmssSSS
            long identifier = 0;
            //包序号
            int packagesNo = 0;

            //识别码
            byte[] identifierByte = new byte[8];
            if (is.read(identifierByte) > 0) {
                identifier = BytesUtils.bytesToLong(identifierByte, 0, true);
                Logger.i("onReceived", "识别码：" + identifier);
            }

            //包序号
            byte[] packagesNoByte = new byte[4];
            if (is.read(packagesNoByte) > 0) {
                packagesNo = BytesUtils.bytesToInt(packagesNoByte, 0);
                Logger.i("onReceived", "包序号：" + packagesNo);
            }

            //标记反馈结果 1成功 其他失败
            byte[] flagByte = new byte[4];
            //反馈结果
            int flag = is.read(flagByte);
            if (flag == FileInfo.FLAG_SUCCESS && identifier == sendFileInfo.getIdentifier()) {
                //更新进度 已经发送成功的包
                sendFileInfo.setPackagesNo(packagesNo);
                callback.onProgress(1, sendFileInfo.getPackagesNo(), sendFileInfo.getSubPackagesTotal());

                //判断是否总分包个数大于1或者已经发送的包小于总个数
                if (sendFileInfo.getSubPackagesTotal() > 1 || sendFileInfo.getPackagesNo() < sendFileInfo.getSubPackagesTotal()) {
                    //当倒数第二个包发送完毕，此时应该发送最后一个包，则计算最后一个包的大小
                    if (sendFileInfo.getPackagesNo() == sendFileInfo.getSubPackagesTotal() - 1) {
                        sendBuffer = new byte[(int) (sendFileInfo.getSize() - sendFileInfo.getPackagesNo() * FileInfo.SUB_PACKAGES_LENGTH)];
                    } else {
                        sendBuffer = new byte[FileInfo.SUB_PACKAGES_LENGTH];
                    }

                    //发下一次的包
                    int len = sendInputStream.read(sendBuffer);
                    if (len != -1) {
                        sendFileInfo.setPackagesNo(packagesNo + 1);
                        sendFileContent();
                    } else {
                        callback.onSend();
                    }
                } else {
                    callback.onSend();
                }
            } else {
                //重发当前的包
                sendFileContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送文件
     *
     * @param fileInfo 文件信息
     */
    public void sendFile(final FileInfo fileInfo) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    setSendFileInfo(fileInfo);
                }
            }).start();
        } else {
            setSendFileInfo(fileInfo);
        }
    }

    /**
     * 初始化发送文件的信息
     *
     * @param fileInfo 文件
     */
    private void setSendFileInfo(FileInfo fileInfo) {
        try {
            sendFileInfo = fileInfo;
            File file = new File(sendFileInfo.getFilePath());
            //发送文件内容
            sendInputStream = new FileInputStream(file);

            if (fileInfo.getSubPackagesTotal() > 1) {
                sendBuffer = new byte[FileInfo.SUB_PACKAGES_LENGTH];
            } else {
                sendBuffer = new byte[(int) fileInfo.getSize()];
            }

            //判断是否读到文件末尾
            int len = sendInputStream.read(sendBuffer);
            if (len != -1) {
                //开始
                callback.onProgress(1, 0, sendFileInfo.getSize());
                sendHeader();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送文件Header
     */
    private void sendHeader() {
        try {
            String fileName = sendFileInfo.getFileName();
            //标记文件
            byte[] type = new byte[]{Constants.FILE_CONSTANTS.SEND_TYPE_FILE};
            //文件名长度
            byte[] fileNameLength = BytesUtils.intToBytes(fileName.length());
            //分包个数
            byte[] subPackagesTotal = BytesUtils.intToBytes(sendFileInfo.getSubPackagesTotal());
            //识别码 yyyyMMddHHmmssSSS
            byte[] identifier = BytesUtils.longToBytes(sendFileInfo.getIdentifier(), 0, true);
            //文件大小
            byte[] fileSize = BytesUtils.longToBytes(sendFileInfo.getSize(), 0, true);
            //包长度
            byte[] subPackageslength = BytesUtils.intToBytes(sendBuffer.length);
            //文件名
            byte[] fileNameBytes = fileName.getBytes(Charset.defaultCharset());

            Logger.i("onSend", "发送头文件，包长度：" + sendBuffer.length);

            //header，1024，不足则以0x00补足
            byte[] headerData = BytesUtils.byteMergerAll(type, fileNameLength, subPackagesTotal, identifier, fileSize, subPackageslength, fileNameBytes);
            byte[] header = new byte[1024];
            if (headerData.length <= 1024) {
                for (int i = 0; i < 1024; i++) {
                    if (i < headerData.length) {
                        header[i] = headerData[i];
                    } else {
                        header[i] = (byte) 0x00;
                    }
                }
            }

            byte[] sendData = BytesUtils.byteMergerAll(header, sendBuffer);
            os.write(sendData);
            Logger.i("onReceived", "发送header完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送文件包体
     */
    private void sendFileContent() {
        try {
            //标记包体
            byte[] type = new byte[]{Constants.FILE_CONSTANTS.SEND_TYPE_FILE_CONTENT};
            //识别码 yyyyMMddHHmmssSSS
            byte[] identifier = BytesUtils.longToBytes(sendFileInfo.getIdentifier(), 0, true);
            //包序号
            byte[] packagesNo = BytesUtils.intToBytes(sendFileInfo.getPackagesNo());
            //包长度
            byte[] subPackageslength = BytesUtils.intToBytes(sendBuffer.length);

            Logger.i("onSend", "发送文件包，包长度：" + sendBuffer.length);

            byte[] sendData = BytesUtils.byteMergerAll(type, identifier, packagesNo, subPackageslength, sendBuffer);
            os.write(sendData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 发送文件
//     *
//     * @param fileInfo 文件
//     */
//    private void sendFileData(FileInfo fileInfo) {
//        try {
//            File file = new File(fileInfo.getFilePath());
//            //发送文件内容
//            FileInputStream inputStream = new FileInputStream(file);
//            byte[] buf = new byte[FileInfo.SUB_PACKAGES_LENGTH];
//            //是否是第一段数据
//            boolean isFirst = true;
//            //判断是否读到文件末尾
//            int len;
//            //开始
//            callback.onProgress(1, 0, fileInfo.getSize());
//            while ((len = inputStream.read(buf)) != -1) {
//                if (isFirst) {
//                    isFirst = false;
//                    //发送header
//                    fileInfo.setPackagesNo(1);
//                    sendHeader(fileInfo, buf);
//                } else {
//                    fileInfo.setPackagesNo(fileInfo.getPackagesNo() + 1);
//                    sendFileContent(fileInfo, buf);
//                }
//
//                fileInfo.setProgress(fileInfo.getProgress() + len);
//                callback.onProgress(1, fileInfo.getProgress(), fileInfo.getSize());
//                //os.write(buf, 0, len);//将文件循环写入输出流
//            }
//            os.flush();
//            callback.onSend();
//        } catch (Exception e) {
//            callback.onError("发送文件失败");
//            e.printStackTrace();
//        }
//    }

    /**
     * 发送命令（字符串）
     *
     * @param msg 信息
     */
    public void sendString(final String msg) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    sendString(msg.getBytes());
                }
            }).start();
        } else {
            sendString(msg.getBytes());
        }
    }

    /**
     * 发送命令
     *
     * @param msg 信息
     */
    private void sendString(byte[] msg) {
        try {
            byte[] type = new byte[]{Constants.FILE_CONSTANTS.SEND_TYPE_STRING};
            os.write(BytesUtils.byteMergerAll(type, msg));
            os.flush();

            callback.onSend();
            Log.e(TAG, "onSend");

            if (!waitAnswer) {
                waitAnswer = true;
                lasttime = System.currentTimeMillis();
            }
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError("发送字符串失败");
            Log.e(TAG, "onError");
        }
    }

    /**
     * 发送byte数据
     *
     * @param msg 信息
     */
    public void sendData(final byte[] msg) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    sendDataByte(msg);
                }
            }).start();
        } else {
            sendDataByte(msg);
        }
    }

    /**
     * 发送命令
     *
     * @param msg 信息
     */
    private void sendDataByte(byte[] msg) {
        try {
            os.write(msg);
            os.flush();

            callback.onSend();
            Log.e(TAG, "onSend");

            if (!waitAnswer) {
                waitAnswer = true;
                lasttime = System.currentTimeMillis();
            }
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError("发送字符串失败");
            Log.e(TAG, "onError");
        }
    }

    /**
     * 发送心跳包
     *
     * @param i
     */
    private void sendHeart(int i) {
        try {
            os.write(i);
            os.flush();

            if (mSocket.isInputShutdown() || mSocket.isOutputShutdown()) {
                isConnected = false;
            }
        } catch (Exception e) {
            Log.e(TAG, "sendHeart fail");
            isConnected = false;
        }
    }
}
