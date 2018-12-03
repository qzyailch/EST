package com.qian.ess.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.qian.ess.R;
import com.qian.ess.bean.socket.DefaultSendBean;
import com.qian.ess.bean.socket.FileInfo;
import com.qian.ess.common.Constants;
import com.qian.ess.utils.DateUtils;
import com.qian.ess.utils.FileUtils;
import com.qian.ess.utils.Logger;
import com.qian.ess.utils.NetworkUtils;
import com.qian.ess.utils.SPUtils;
import com.qian.ess.utils.SocketConnect;
import com.qian.ess.utils.ToastUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Administrator on 2018/11/12 0012.
 * <p>
 * Socket Service
 */

public class SocketService extends Service {

    private static final int MESSAGE_HANDLE_SUCCESS = 520;

    //连接状态 1；正在执行连接 0；连接失败 2：连接成功
    private int connect_status = -1;

    SocketConnect socketConnect = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerReceiver();
        return super.onStartCommand(intent, flags, startId);
    }

    private void init() {
        if (null == socketConnect || !socketConnect.isConnected) {
            socketConnect = new SocketConnect(SPUtils.getString("ip"), Integer.parseInt(SPUtils.getString("port")),
                    new SocketConnect.Callback() {

                        @Override
                        public void onSend() {
                            Logger.i("result", "发送完毕");
                            sendToast(getResources().getString(R.string.ess_send_finish));

                            Intent intent = new Intent();
                            intent.setAction(Constants.ESSBroadcast.DISMISS_DIALOG_ACTION);
                            sendBroadcast(intent);
                        }

                        @Override
                        public void onProgress(int type, final long progress, final long size) {
                            Logger.i("result", progress + "/" + size);

                            DecimalFormat df = new DecimalFormat("######0.00");
                            sendProcess(type, df.format(progress * 100.0 / size) + "%");
                        }

                        @Override
                        public void onReconnected() {

                        }

                        @Override
                        public void onReceived(int type, final byte[] msg) {
                            Logger.i("onReceived", Arrays.toString(msg));
                            Logger.i("onReceived", "" + new String(msg));
                            sendToast("" + new String(msg));

                            Intent intent = new Intent();
                            if (type == 1) { //文件路径
                                intent.putExtra("filePath", "" + new String(msg));
                            } else { //字符串
                                intent.putExtra("message", "" + new String(msg));
                            }
                            intent.setAction(Constants.ESSBroadcast.DISMISS_DIALOG_ACTION);
                            sendBroadcast(intent);
                        }

                        @Override
                        public void onError(String msg) {
                            Logger.i("onReceived", msg);
                            connect_status = -1;
                            sendToast(msg);
                        }

                        @Override
                        public void onDisconnected() {
                            Logger.i("result", "onDisconnected");
                            sendToast("onDisconnected");
                            connect_status = 0;
                        }

                        @Override
                        public void onConnected() {
                            Logger.i("result", "连接成功");
                            sendToast(getResources().getString(R.string.ess_connect_success));
                            connect_status = 2;
                        }
                    });
        } else {
            socketConnect.disconnect();
        }

        connect();
    }

    //发送toast消息
    private void sendToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("toast", msg);
        intent.setAction(Constants.ESSBroadcast.TOAST_ACTION);
        sendBroadcast(intent);
    }

    //发送接收文件进度消息
    private void sendProcess(int type, String msg) {
        Intent intent = new Intent();
        intent.putExtra("type", type);
        intent.putExtra("process", msg);
        intent.setAction(Constants.ESSBroadcast.RECEIVE_FILE_PROCESS_ACTION);
        sendBroadcast(intent);
    }

    //连接
    private void connect() {
        if (null != socketConnect && !socketConnect.isConnected) {
            if (connect_status == 1) {
                return;
            }
            connect_status = 1;
            socketConnect.setIp(SPUtils.getString("ip"));
            socketConnect.setPort(Integer.parseInt(SPUtils.getString("port")));
            socketConnect.connect();
        }
    }

    /**
     * 注册网络状态监听的广播
     */
    private void registerReceiver() {
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mFilter.addAction(Constants.ESSBroadcast.SEND_MESSAGE_OR_FILE_ACTION);
        registerReceiver(myNetReceiver, mFilter);
    }

    /**
     * 取消监听网络状态监听的广播
     */
    private void unregisterReceiver() {
        if (myNetReceiver != null) {
            unregisterReceiver(myNetReceiver);
        }
    }

    private BroadcastReceiver myNetReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Logger.i("result", "SocketService BroadcastReceiver : " + action);
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                if (!NetworkUtils.isConnected(SocketService.this)) {
                    socketConnect.disconnect();
                } else {
                    init();
                }
            } else if (action.equals(Constants.ESSBroadcast.SEND_MESSAGE_OR_FILE_ACTION)) {
                String message = intent.getStringExtra("message");
                String filePath = intent.getStringExtra("filePath");
                if (!TextUtils.isEmpty(message)) {
                    socketConnect.sendString(message);
                } else if (!TextUtils.isEmpty(filePath)) {
                    sendFile(filePath);
                }
            }
        }
    };

    /**
     * 发送文件
     */
    private void sendFile(final String path) {
        //文件名
        String fileName = FileUtils.getFileName(path);
        //文件大小
        long fileSize = FileUtils.getFileSizeForLong(new File(path));

        if (fileSize <= 0) {
            ToastUtils.show("文件大小为0");
            return;
        }

        FileInfo fileInfo = new FileInfo();
        fileInfo.setFilePath(path);
        fileInfo.setFileName(fileName);
        fileInfo.setPackagesNo(0);
        fileInfo.setSize(fileSize);
        fileInfo.setSubPackagesTotal(fileSize % FileInfo.SUB_PACKAGES_LENGTH == 0
                ? (int) (fileSize / FileInfo.SUB_PACKAGES_LENGTH)
                : ((int) (fileSize / FileInfo.SUB_PACKAGES_LENGTH) + 1));
        fileInfo.setIdentifier(Long.parseLong(DateUtils.dateToString(new Date(), DateUtils.FORMAT_FULL_EN)));
        socketConnect.sendFile(fileInfo);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String fileName = FileUtils.getFileName(path);
//                //标记文件
//                byte[] type = new byte[]{Constants.FILE_CONSTANTS.SEND_TYPE_FILE};
//                //文件名长度
//                byte[] fileNameLength = BytesUtils.intToBytes(fileName.length());
//                //文件大小
//                byte[] fileLength = BytesUtils.intToBytes(FileUtils.getFileSizeForInt(new File(path)));
//                //文件名
//                byte[] fileNameBytes = fileName.getBytes(Charset.defaultCharset());
//                //文件
//                byte[] fileData = FileUtils.File2byte(path);
//
//                byte[] headerData = BytesUtils.byteMergerAll(type, fileNameLength, fileLength, fileNameBytes);
//                byte[] header = new byte[1024];
//                if (headerData.length <= 1024) {
//                    for (int i = 0; i < 1024; i++) {
//                        if (i < headerData.length) {
//                            header[i] = headerData[i];
//                        } else {
//                            header[i] = (byte) 0x00;
//                        }
//                    }
//                }
//                byte[] sendData = BytesUtils.byteMergerAll(header, fileData);
//
//                Message message = new Message();
//                message.what = MESSAGE_HANDLE_SUCCESS;
//                message.obj = new DefaultSendBean(sendData);
//                mHandler.sendMessage(message);
//            }
//        }).start();
    }

    /**
     * do some action
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int msgId = msg.what;
            switch (msgId) {
                case MESSAGE_HANDLE_SUCCESS:
                    DefaultSendBean data = (DefaultSendBean) msg.obj;
                    socketConnect.sendData(data.getBytes());
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onDestroy() {
        Logger.i("result", "关闭SocketService");
        unregisterReceiver();
        socketConnect.disconnect();
        super.onDestroy();
    }
}
