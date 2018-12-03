package com.qian.ess.camera.listener;

/**
 * create by CJT2325
 * 1563354365@qq.com.
 */

public interface CaptureListener {
    void takePictures();

    void recordShort(long time);

    void recordStart();

    void recordEnd(long time);

    void recordZoom(float zoom);

    void recordError();
}
