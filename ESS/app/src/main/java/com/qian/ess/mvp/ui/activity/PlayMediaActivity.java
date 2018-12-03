package com.qian.ess.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.utils.InitFileUtils;

import butterknife.Bind;
import cn.jzvd.JZDataSource;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * Created by QianMoLi on 2018/9/24.
 * <p>
 * 视频播放
 */

public class PlayMediaActivity extends BaseActivity {

    @Bind(R.id.videoplayer)
    JzvdStd jzvdStd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_play_media;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {

    }

    @Override
    public void initViews() {
        jzvdStd.batteryLevel.setVisibility(View.GONE);
        jzvdStd.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backward();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            String path = bundle.getString("path");
            String fileName = bundle.getString("fileName");
            //对象编号
            String objectNumber = bundle.getString("objectNumber");
            //部件编号
            String partNumber = bundle.getString("partNumber");
            jzvdStd.setUp(new JZDataSource(InitFileUtils.getTSFilePath(objectNumber, partNumber) + "/" + fileName + "/" + path), JzvdStd.SCREEN_WINDOW_FULLSCREEN);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (Jzvd.backPress()) {
                return true;
            }
            return super.onKeyDown(keyCode, event);
        } else
            return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public boolean isFullScreen() {
        return true;
    }
}
