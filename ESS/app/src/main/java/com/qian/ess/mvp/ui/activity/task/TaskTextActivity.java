package com.qian.ess.mvp.ui.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by QianMoLi on 2018/11/3.
 * <p>
 * 任务编辑文本
 */

public class TaskTextActivity extends BaseActivity {

    //输入框
    @Bind(R.id.et_text)
    EditText etText;

    @Override
    public int getLayoutId() {
        return R.layout.activity_task_text;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {

    }

    @Override
    public void initViews() {

    }

    @OnClick({R.id.txt_btn_cancel, R.id.txt_btn_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_btn_cancel:
                backward();
                break;
            case R.id.txt_btn_finish:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("text", etText.getText().toString());
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                backward();
                break;
            default:
                break;
        }
    }
}
