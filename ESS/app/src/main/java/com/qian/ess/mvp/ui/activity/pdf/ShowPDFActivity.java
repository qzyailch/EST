package com.qian.ess.mvp.ui.activity.pdf;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.tencent.smtt.sdk.TbsReaderView;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/8/8 0008.
 * <p>
 * 打开pdf文档
 */

public class ShowPDFActivity extends BaseActivity implements TbsReaderView.ReaderCallback {

    @Bind(R.id.rl_root)
    RelativeLayout rlRoot;

    private TbsReaderView mTbsReaderView;

    private String title;
    private String path;

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_pdf;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            title = bundle.getString("title");
            path = bundle.getString("path");
        }

        if (!TextUtils.isEmpty(title)) {
            commonToolbar.setTitle(title);
        }
    }

    @Override
    public void initViews() {
        mTbsReaderView = new TbsReaderView(this, this);
        rlRoot.addView(mTbsReaderView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        displayFile();
    }

    private void displayFile() {
        Bundle bundle = new Bundle();
        bundle.putString("filePath", path);
        bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
        boolean result = mTbsReaderView.preOpen(parseFormat(title), false);
        if (result) {
            mTbsReaderView.openFile(bundle);
        }
    }

    private String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTbsReaderView.onStop();
    }
}
