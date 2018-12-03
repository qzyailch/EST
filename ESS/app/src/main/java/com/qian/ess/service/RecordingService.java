package com.qian.ess.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.qian.ess.utils.DateUtils;
import com.qian.ess.utils.InitFileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;

/**
 * 录音的 Service
 * <p>
 * Created by developerHaoz on 2017/8/12.
 */

public class RecordingService extends Service {

    private static final String LOG_TAG = "RecordingService";

    private MediaRecorder mRecorder = null;

    private TimerTask mIncrementTimerTask = null;

    //文件名，也就是文件路径
    private String fileName;
    //对象编号
    private String objectNumber;
    //部件编号
    private String partNumber;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        if (null != bundle) {
            fileName = bundle.getString("fileName");
            objectNumber = bundle.getString("objectNumber");
            partNumber = bundle.getString("partNumber");
        }
        startRecording();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (mRecorder != null) {
            stopRecording();
        }
        super.onDestroy();
    }

    public void startRecording() {
        try {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mRecorder.setOutputFile(getFileNameAndPath());
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mRecorder.setAudioChannels(1);
            mRecorder.setAudioSamplingRate(44100);
            mRecorder.setAudioEncodingBitRate(192000);
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public String getFileNameAndPath() {
        String outputFileName = "audio" + DateUtils.dateToString(new Date(), DateUtils.FORMAT_LONG_WITHOUT);
        String outputFilePath = InitFileUtils.getTSFilePath(objectNumber, partNumber) + "/" + fileName + "/Audio/" + outputFileName + ".mp3";

        File file = new File(outputFilePath);
        if (!file.getParentFile().getParentFile().exists()) {
            file.getParentFile().getParentFile().mkdirs();
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        return outputFilePath;
    }

    public void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        if (mIncrementTimerTask != null) {
            mIncrementTimerTask.cancel();
            mIncrementTimerTask = null;
        }

        mRecorder = null;
    }

}
