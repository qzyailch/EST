package com.qian.ess.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.bean.socket.DefaultSendBean;
import com.qian.ess.common.Constants;
import com.qian.ess.utils.BytesUtils;
import com.qian.ess.utils.FileUtils;
import com.qian.ess.utils.Logger;
import com.qian.ess.utils.SocketConnect;
import com.qian.ess.utils.ToastUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/9 0009.
 */

public class SocketClientActivity extends BaseActivity {

    private String dirName = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator;
    private String mImageUrl;
    private Uri mImageUri;

    private static final int MESSAGE_HANDLE_SUCCESS = 520;

    //照相机返回码
    private static final int TAKE_PHOTO_CODE = 1010;
    //选择图片返回码
    private static final int PICK_PHOTO_CODE = 1011;

    //选择文件
    private static final int SELECI_FILE_COSE = 1012;
    //从相册获取的文件夹
    private static final String IMAGE_TYPE = "image/*";

    @Bind(R.id.et_send_ip)
    EditText etSendIp;
    @Bind(R.id.et_send_port)
    EditText etSendPort;

    @Bind(R.id.et_send_content)
    EditText etSendContent;
    @Bind(R.id.txt_receive)
    TextView txtReceive;

    SocketConnect socketConnect = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_socket;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {

    }

    @Override
    public void initViews() {
        socketConnect = new SocketConnect(etSendIp.getText().toString(), Integer.parseInt(etSendPort.getText().toString()),
                new SocketConnect.Callback() {

                    @Override
                    public void onSend() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setReceive("发送完毕");
                            }
                        });
                    }

                    @Override
                    public void onProgress(int type, final long progress, final long size) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showSysLoadingDialog(progress + "/" + size);
                            }
                        });
                    }

                    @Override
                    public void onReconnected() {

                    }

                    @Override
                    public void onReceived(int type, final byte[] msg) {
                        Logger.i("onReceived", Arrays.toString(msg));

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dismissDialog();
                                setReceive(new String(msg));
                            }
                        });
                    }

                    @Override
                    public void onError(String msg) {
                        Logger.i("onReceived", msg);
                    }

                    @Override
                    public void onDisconnected() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dismissDialog();
                                setReceive("onDisconnected");
                            }
                        });
                    }

                    @Override
                    public void onConnected() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setReceive("连接成功");
                            }
                        });
                        String msg = "hello!";
                        socketConnect.sendString(msg);
                    }
                });

        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            connect();
        }
    }

    private void setReceive(String receive) {
        String str = txtReceive.getText().toString();
        if (!TextUtils.isEmpty(str)) {
            str += "\n";
        }
        str += receive;
        txtReceive.setText(str);
    }

    @OnClick({
            R.id.btn_send,
            R.id.btn_lianjie,
            R.id.btn_paizhao,
            R.id.btn_file,
            R.id.btn_xuanzezhopian
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_lianjie:
                connect();
                break;
            case R.id.btn_send:
                sendMessage();
                break;
            case R.id.btn_paizhao:
                takePhoto();
                break;
            case R.id.btn_xuanzezhopian:
                selectForPhoto();
                break;
            case R.id.btn_file:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, SELECI_FILE_COSE);
                break;
            default:
                break;
        }
    }

    private void connect() {
        if (null != socketConnect) {
            if (!socketConnect.isConnected) {
                socketConnect.setIp(etSendIp.getText().toString());
                socketConnect.setPort(Integer.parseInt(etSendPort.getText().toString()));
                socketConnect.connect();
            }
        }
    }

    /**
     * 发送消息
     */
    private void sendMessage() {
        if (null == socketConnect || !socketConnect.isConnected) {
            ToastUtils.show("请连接");
            return;
        }
        socketConnect.sendString(etSendContent.getText().toString());
    }

    /**
     * do some action
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dismissDialog();
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

    /**
     * 发送文件
     */
    private void sendFile(final String path) {
        showLoadingDialog(R.string.load_more);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String fileName = FileUtils.getFileName(path);
                //标记文件
                byte[] type = new byte[]{Constants.FILE_CONSTANTS.SEND_TYPE_FILE};
                //文件名长度
                byte[] fileNameLength = BytesUtils.intToBytes(fileName.length());
                //文件大小
                byte[] fileLength = BytesUtils.intToBytes(FileUtils.getFileSizeForInt(new File(path)));
                //文件名
                byte[] fileNameBytes = fileName.getBytes(Charset.defaultCharset());
                //文件
                byte[] fileData = FileUtils.File2byte(path);

                byte[] headerData = BytesUtils.byteMergerAll(type, fileNameLength, fileLength, fileNameBytes);
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
                byte[] sendData = BytesUtils.byteMergerAll(header, fileData);

                Message message = new Message();
                message.what = MESSAGE_HANDLE_SUCCESS;
                message.obj = new DefaultSendBean(sendData);
                mHandler.sendMessage(message);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        socketConnect.disconnect();
        super.onDestroy();
    }

    /**
     * 拍照
     */
    public void takePhoto() {
        try {
            if (null == socketConnect || !socketConnect.isConnected) {
                ToastUtils.show("请连接");
                return;
            }
            mImageUrl = dirName + "ess" + System.currentTimeMillis() + ".png";
            File file = new File(mImageUrl);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            mImageUri = Uri.fromFile(file);

            Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
            startActivityForResult(takePhotoIntent, TAKE_PHOTO_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从相册选择
     */
    private void selectForPhoto() {
        if (null == socketConnect || !socketConnect.isConnected) {
            ToastUtils.show("请连接");
            return;
        }
        Intent getAlbum = new Intent(Intent.ACTION_PICK);
        getAlbum.setType(IMAGE_TYPE);
        startActivityForResult(getAlbum, PICK_PHOTO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case TAKE_PHOTO_CODE:
                    try {
                        Logger.i("result", "mImageUrl = " + mImageUrl);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    sendFile(mImageUrl);
                    break;
                case PICK_PHOTO_CODE:
                    try {
                        String path;
                        Uri imgUri = data.getData();
                        if (!TextUtils.isEmpty(imgUri.getAuthority())) { //使用 getAuthority 做判断条件
                            String[] proj = {MediaStore.Images.Media.DATA};
                            Cursor cursor = managedQuery(imgUri, proj, null, null, null);
                            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            cursor.moveToFirst();
                            path = cursor.getString(column_index);
                        } else {
                            path = imgUri.getPath(); //小米选择照片返回 data="file:///..." uri.getAuthority()==""
                        }

                        Logger.i("result", "选择图片的路径：" + path);
                        sendFile(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case SELECI_FILE_COSE:
                    try {
                        String path2;
                        Uri uri = data.getData();
                        if (!TextUtils.isEmpty(uri.getAuthority())) { //使用 getAuthority 做判断条件
                            String[] proj = {MediaStore.Images.Media.DATA};
                            Cursor cursor = managedQuery(uri, proj, null, null, null);
                            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            cursor.moveToFirst();
                            path2 = cursor.getString(column_index);
                        } else {
                            path2 = uri.getPath(); //小米选择照片返回 data="file:///..." uri.getAuthority()==""
                        }

                        if (!path2.startsWith("/storage")) {
                            path2 = uri.getPath();
                        }
                        ToastUtils.show("选择文件的路径：" + path2);
                        Logger.i("result", "选择文件的路径：" + path2);
                        sendFile(path2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
