package com.qian.ess.camera.listener;

import android.graphics.Bitmap;

/**
 * =====================================
 * 作    者: 钱真勇
 * 版    本：1.1.4
 * 创建日期：2017/4/26
 * 描    述：
 * =====================================
 */
public interface JCameraListener {

    void captureSuccess(Bitmap bitmap, int btnType);

    void recordSuccess(String url, Bitmap firstFrame, int btnType);

}
