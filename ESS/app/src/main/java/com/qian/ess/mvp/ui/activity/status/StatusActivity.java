package com.qian.ess.mvp.ui.activity.status;

import android.support.v7.widget.Toolbar;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;

/**
 * Created by Administrator on 2018/8/13 0013.
 */

public class StatusActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_status;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {
        commonToolbar.setTitle(R.string.ec);
    }

    @Override
    public void initViews() {

    }
}
