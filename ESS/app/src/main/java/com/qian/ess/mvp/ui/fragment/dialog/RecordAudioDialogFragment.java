package com.qian.ess.mvp.ui.fragment.dialog;

import android.app.Dialog;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;

import com.qian.ess.R;
import com.qian.ess.db.action.TSObjectAction;
import com.qian.ess.utils.DateUtils;
import com.qian.ess.utils.InitFileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 开始录音的 DialogFragment
 * <p>
 * Created by developerHaoz on 2017/8/12.
 */

public class RecordAudioDialogFragment extends DialogFragment {

    private static final String TAG = "RecordAudioDialogFragme";

    private boolean mStartRecording = true;

    private FloatingActionButton mFabRecord;
    private Chronometer mChronometerTime;
    private ImageView mIvClose;

    private MediaRecorder mRecorder = null;

    //文件名，也就是文件路径
    private String tsId;
    private String fileName;
    //对象编号
    private String objectNumber;
    //部件编号
    private String partNumber;
    //输出文件及路径
    private String outputPath;

    private OnAudioCancelListener mListener;

    public static RecordAudioDialogFragment newInstance(String objectNumber, String partNumber, String fileName, String tsId) {
        RecordAudioDialogFragment dialogFragment = new RecordAudioDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("objectNumber", objectNumber);
        bundle.putString("partNumber", partNumber);
        bundle.putString("fileName", fileName);
        bundle.putString("tsId", tsId);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        Bundle bundle = getArguments();
        if (null != bundle) {
            tsId = bundle.getString("tsId");
            fileName = bundle.getString("fileName");
            objectNumber = bundle.getString("objectNumber");
            partNumber = bundle.getString("partNumber");
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_record_audio, null);
        initView(view);

        mFabRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecord(mStartRecording);
                mStartRecording = !mStartRecording;
            }
        });

        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCancel();
            }
        });

        builder.setCancelable(false);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onDestroyView() {
        if (mRecorder != null) {
            stopRecording();
        }
        super.onDestroyView();
    }

    private void initView(View view) {
        mChronometerTime = (Chronometer) view.findViewById(R.id.record_audio_chronometer_time);
        mFabRecord = (FloatingActionButton) view.findViewById(R.id.record_audio_fab_record);
        mIvClose = (ImageView) view.findViewById(R.id.record_audio_iv_close);
    }

    public void startRecording() {
        setFileNameAndPath();
        try {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mRecorder.setOutputFile(outputPath);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mRecorder.setAudioChannels(1);
            mRecorder.setAudioSamplingRate(44100);
            mRecorder.setAudioEncodingBitRate(192000);
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            Log.e("result", "prepare() failed");
        }
    }

    public void setFileNameAndPath() {
        String outputFileName = "audio" + DateUtils.dateToString(new Date(), DateUtils.FORMAT_LONG_WITHOUT);
        outputPath = InitFileUtils.getTSFilePath(objectNumber, partNumber) + "/" + fileName + "/Audio/" + outputFileName + ".mp3";

        File file = new File(outputPath);
        if (!file.getParentFile().getParentFile().exists()) {
            file.getParentFile().getParentFile().mkdirs();
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    private void onRecord(boolean start) {
        if (start) {
            // start recording
            mFabRecord.setImageResource(R.mipmap.ic_media_stop);

            //start Chronometer
            mChronometerTime.setBase(SystemClock.elapsedRealtime());
            mChronometerTime.start();

            startRecording();
        } else {
            mFabRecord.setImageResource(R.mipmap.ic_mic_white_36dp);
            mChronometerTime.stop();

            updateAudioInDb();

            stopRecording();
            dismiss();
        }
    }

    //更新数据库的音频信息
    private void updateAudioInDb() {
        //获取文件名，包含后缀名
        String fileName = outputPath.split("/")[outputPath.split("/").length - 1];
        TSObjectAction.updateTSObjectAudio(tsId, fileName);
    }

    public void setOnCancelListener(OnAudioCancelListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    onRecord(mStartRecording);
                }
                break;
        }
    }

    public interface OnAudioCancelListener {
        void onCancel();
    }
}
