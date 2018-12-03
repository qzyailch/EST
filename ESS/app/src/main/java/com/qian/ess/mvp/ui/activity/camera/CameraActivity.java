package com.qian.ess.mvp.ui.activity.camera;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.camera.JCameraView;
import com.qian.ess.camera.listener.ClickListener;
import com.qian.ess.camera.listener.ErrorListener;
import com.qian.ess.camera.listener.JCameraListener;
import com.qian.ess.common.ESSFilePath;
import com.qian.ess.utils.DateUtils;
import com.qian.ess.utils.FileUtils;
import com.qian.ess.utils.ToastUtils;
import com.qian.ess.utils.thread.ThreadPoolProxyFactory;

import java.io.File;
import java.util.Date;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/8/6 0006.
 * <p>
 * 拍小视频界面
 */

public class CameraActivity extends BaseActivity {

    private final int MESSAGE_SAVE_SUCCESS = 1;
    private final int MESSAGE_SAVE_FAIL = 2;

    @Bind(R.id.jcameraview)
    JCameraView jCameraView;

    //编辑还是保存
    private int type;
    private String outputPath;

    @Override
    public int getLayoutId() {
        return R.layout.activity_camera;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {

    }

    @Override
    public void initViews() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            outputPath = bundle.getString("outputPath");
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置视频保存路径
        jCameraView.setSaveVideoPath(outputPath);
        //设置只能录像或只能拍照或两种都可以（默认两种都可以）
        jCameraView.setFeatures(JCameraView.BUTTON_STATE_ONLY_CAPTURE);
        //设置视频质量
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);

        //JCameraView监听
        jCameraView.setErrorLisenter(new ErrorListener() {
            @Override
            public void onError() {
                //打开Camera失败回调
                Log.i("CJT", "open camera error");
            }

            @Override
            public void AudioPermissionError() {
                //没有录取权限回调
                Log.i("CJT", "AudioPermissionError");
            }
        });

        jCameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap, int btnType) {
                //获取图片bitmap
                Log.i("JCameraView", "bitmap = " + bitmap.getWidth() + "，btnType = " + btnType);
                type = btnType;
                savePic(bitmap);
            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame, int btnType) {
                //获取视频路径
                Log.i("CJT", "url = " + url);
            }
            //@Override
            //public void quit() {
            //    (1.1.9+后用左边按钮的点击事件替换)
            //}
        });
        //左边按钮点击事件
        jCameraView.setLeftClickListener(new ClickListener() {
            @Override
            public void onClick() {
                CameraActivity.this.finish();
            }
        });
        //右边按钮点击事件
        jCameraView.setRightClickListener(new ClickListener() {

            @Override
            public void onClick() {
                ToastUtils.show("Right");
            }
        });
    }

    /**
     * 保存图片
     */
    private void savePic(final Bitmap bitmap) {
        showSysLoadingDialog(R.string.loading);
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().execute(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                try {
                    String imgPath = outputPath + File.separator + "pic" + DateUtils.dateToString(new Date(), DateUtils.FORMAT_LONG_WITHOUT) + ".png";
                    if (FileUtils.saveImage(bitmap, imgPath)) {
                        message.obj = imgPath;
                        message.what = MESSAGE_SAVE_SUCCESS;
                    } else {
                        message.what = MESSAGE_SAVE_FAIL;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    message.what = MESSAGE_SAVE_FAIL;
                }

                handler.sendMessage(message);
            }
        });
    }

    /**
     * do some action
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            dismissDialog();
            super.handleMessage(msg);
            int msgId = msg.what;
            switch (msgId) {
                case MESSAGE_SAVE_SUCCESS:
                    ToastUtils.show("保存成功");
                    String imgPath = (String) msg.obj;
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("imgPath", imgPath);
                    bundle.putInt("type", type);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    backward();
                    break;
                case MESSAGE_SAVE_FAIL:
                    ToastUtils.show("保存失败");
                    backward();
                    break;
                default:
                    break;
            }
        }
    };
}
