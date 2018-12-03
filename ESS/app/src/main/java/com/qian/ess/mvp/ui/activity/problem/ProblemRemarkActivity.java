package com.qian.ess.mvp.ui.activity.problem;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.db.action.TSObjectAction;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by QianMoLi on 2018/11/3.
 * <p>
 * 编辑TS的备注
 */

public class ProblemRemarkActivity extends BaseActivity {

    //输入框
    @Bind(R.id.et_text)
    EditText etText;

    //TsObjectId
    private String tsId;
    //排故方法的Id
    private String tsSolutionID;
    //编号
    private int tsSolutionNumber;

    @Override
    public int getLayoutId() {
        return R.layout.activity_task_text;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {

    }

    @Override
    public void initViews() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            tsId = bundle.getString("tsId");
            tsSolutionID = bundle.getString("tsSolutionID");
            tsSolutionNumber = bundle.getInt("tsSolutionNumber");
        }

        String showRemark = TSObjectAction.getShowEditRemark(tsId, null);
        etText.setText(TextUtils.isEmpty(showRemark) ? "" : showRemark);
        etText.setSelection(etText.getText().toString().length());
    }

    @OnClick({R.id.txt_btn_cancel, R.id.txt_btn_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_btn_cancel:
                backward();
                break;
            case R.id.txt_btn_finish:
                TSObjectAction.updateTSSolutionRemark(tsId, tsSolutionID, etText.getText().toString());
                backward();
                break;
            default:
                break;
        }
    }
}
