package com.qian.ess.mvp.ui.activity.problem;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.noober.menu.FloatMenu;
import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.bean.ts.TSObject;
import com.qian.ess.db.action.TSObjectAction;
import com.qian.ess.mvp.ui.activity.PlayMediaActivity;
import com.qian.ess.mvp.ui.adapter.problem.ProblemImageGridAdapter;
import com.qian.ess.mvp.ui.adapter.ts.AudioAdapter;
import com.qian.ess.mvp.ui.adapter.ts.VideoAdapter;
import com.qian.ess.utils.DeleteFileUtil;
import com.qian.ess.utils.InitFileUtils;
import com.qian.ess.widget.NoScrollGridView;
import com.qian.ess.widget.NoScrollListView;

import java.io.IOException;
import java.io.Serializable;

import butterknife.Bind;

/**
 * Created by QianMoLi on 2018/11/4.
 * <p>
 * 结果
 */

public class ProblemResultActivity extends BaseActivity {

    //图片
    @Bind(R.id.gridView_img)
    NoScrollGridView gridView;
    //视频
    @Bind(R.id.list_media)
    NoScrollListView listVideo;
    //视频
    @Bind(R.id.list_audio)
    NoScrollListView listAudio;

    //备注
    @Bind(R.id.txt_remark)
    TextView txtRemark;

    //TsObjectId
    private String tsId;
    //排故方法的Id
    private String tsSolutionID;
    //文件名
    private String fileName;
    //编号
    private int tsSolutionNumber;

    //对象编号
    private String objectNumber;
    //部件编号
    private String partNumber;

    //图片适配器
    private ProblemImageGridAdapter problemImageGridAdapter;
    //视频列表适配器
    private VideoAdapter videoAdapter;
    //音频列表适配器
    private AudioAdapter audioAdapter;

    //屏幕touch时采集点，用于显示上下文菜单
    private Point point = new Point();

    private MediaPlayer mMediaPlayer = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_problem_result;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {
        commonToolbar.setTitle(R.string.ess_result);
    }

    @Override
    public void initViews() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            tsId = bundle.getString("tsId");
            tsSolutionID = bundle.getString("tsSolutionID");
            fileName = bundle.getString("fileName");
            objectNumber = bundle.getString("objectNumber");
            partNumber = bundle.getString("partNumber");
            tsSolutionNumber = bundle.getInt("tsSolutionNumber");
        }

        String showRemark = TSObjectAction.getShowEditRemark(tsId, null);
        txtRemark.setText(TextUtils.isEmpty(showRemark) ? "" : showRemark);

        TSObject tsObject = TSObjectAction.getTSObjectByTsId(tsId);
        initPicList(tsObject);
        initVideoList(tsObject);
        initAudioList(tsObject);
    }

    //初始化图片列表
    private void initPicList(TSObject tsObject) {
        if (null != tsObject) {
            problemImageGridAdapter = new ProblemImageGridAdapter(this, objectNumber, partNumber, fileName);
            problemImageGridAdapter.setAllArray(tsObject.getPictureList());
            gridView.setAdapter(problemImageGridAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("imgs", (Serializable) problemImageGridAdapter.getArray());
                    bundle.putString("fileName", fileName);
                    bundle.putString("objectNumber", objectNumber);
                    bundle.putString("partNumber", partNumber);
                    bundle.putInt("position", position);
                    forward(ImageBrowseActivity.class, bundle);
                }
            });

            gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int itemPosition, long id) {
                    FloatMenu floatMenu = new FloatMenu(ProblemResultActivity.this);
                    floatMenu.items(getResources().getString(R.string.ess_delete));
                    floatMenu.setOnItemClickListener(new FloatMenu.OnItemClickListener() {
                        @Override
                        public void onClick(View v, int position) {
                            switch (position) {
                                case 0:
                                    if (DeleteFileUtil.delete(InitFileUtils.getTSFilePath(objectNumber, partNumber) + "/" + fileName + problemImageGridAdapter.getArray().get(itemPosition))) {
                                        problemImageGridAdapter.getArray().remove(itemPosition);
                                        problemImageGridAdapter.notifyDataSetChanged();

                                        TSObjectAction.updateTSObjectPic(tsId, problemImageGridAdapter.getArray());
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                    floatMenu.show(point);
                    return true;
                }
            });
        }
    }

    //初始化视频列表
    private void initVideoList(TSObject tsObject) {
        if (null != tsObject) {
            videoAdapter = new VideoAdapter(this, objectNumber, partNumber, fileName);
            videoAdapter.setAllArray(tsObject.getVideoList());
            videoAdapter.setOnPicClickListener(new VideoAdapter.OnPicClickListener() {
                @Override
                public void onDelete(int position) {
                    if (DeleteFileUtil.delete(InitFileUtils.getTSFilePath(objectNumber, partNumber) + "/" + fileName + videoAdapter.getArray().get(position))) {
                        videoAdapter.getArray().remove(position);
                        videoAdapter.notifyDataSetChanged();

                        TSObjectAction.updateTSObjectVideo(tsId, videoAdapter.getArray());
                    }
                }
            });
            listVideo.setAdapter(videoAdapter);
            listVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String path = videoAdapter.getArray().get(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("path", path);
                    bundle.putString("objectNumber", objectNumber);
                    bundle.putString("partNumber", partNumber);
                    bundle.putString("fileName", fileName);
                    forward(PlayMediaActivity.class, bundle);
                }
            });
        }
    }

    //初始化音频列表
    private void initAudioList(TSObject tsObject) {
        if (null != tsObject) {
            audioAdapter = new AudioAdapter(this);
            audioAdapter.setAllArray(tsObject.getAudioList());
            listAudio.setAdapter(audioAdapter);
            listAudio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    audioAdapter.setPlayPosition(position);
                    audioAdapter.notifyDataSetChanged();
                    startPlaying(audioAdapter.getItem(position));
                }
            });

            listAudio.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(final AdapterView<?> parent, View view, final int itemPosition, long id) {
                    FloatMenu floatMenu = new FloatMenu(ProblemResultActivity.this);
                    floatMenu.items(getResources().getString(R.string.ess_delete));
                    floatMenu.setOnItemClickListener(new FloatMenu.OnItemClickListener() {
                        @Override
                        public void onClick(View v, int position) {
                            switch (position) {
                                case 0:
                                    if (DeleteFileUtil.delete(InitFileUtils.getTSFilePath(objectNumber, partNumber) + "/" + fileName + audioAdapter.getArray().get(position))) {
                                        audioAdapter.getArray().remove(itemPosition);
                                        audioAdapter.notifyDataSetChanged();

                                        TSObjectAction.updateTSObjectAudio(tsId, audioAdapter.getArray());
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                    floatMenu.show(point);
                    return true;
                }
            });
        }
    }

    private void startPlaying(String path) {
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(InitFileUtils.getTSFilePath(objectNumber, partNumber) + "/" + fileName + path);
            mMediaPlayer.prepare();

            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                }
            });
        } catch (IOException e) {
            Log.e("result", "prepare() failed");
        }

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlaying();
            }
        });
    }

    private void stopPlaying() {
        if (null != mMediaPlayer) {
            audioAdapter.setPlayPosition(-1);
            audioAdapter.notifyDataSetChanged();
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            point.x = (int) ev.getRawX();
            point.y = (int) ev.getRawY();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        stopPlaying();
        super.onDestroy();
    }
}
