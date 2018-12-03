package com.qian.ess.mvp.ui.activity.task;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.bean.es.TaskHistory;
import com.qian.ess.mvp.ui.adapter.task.TaskHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by QianMoLi on 2018/11/3.
 * <p>
 * 任务历史纪录
 */

public class TaskHistoryActivity extends BaseActivity {

    //历史列表
    @Bind(R.id.list_history)
    ListView listHistory;

    private TaskHistoryAdapter historyAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_task_history;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            commonToolbar.setTitle(bundle.getString("title", ""));
        }
    }

    @Override
    public void initViews() {
        List<TaskHistory> list = new ArrayList<>();
        list.add(new TaskHistory());
        list.add(new TaskHistory());
        list.add(new TaskHistory());
        list.add(new TaskHistory());
        list.add(new TaskHistory());

        historyAdapter = new TaskHistoryAdapter(this);
        historyAdapter.setAllArray(list);
        listHistory.setAdapter(historyAdapter);
    }
}
