package com.qian.ess.mvp.ui.adapter.home;

import android.content.Context;

import com.qian.ess.R;
import com.qian.ess.bean.home.TaskItem;
import com.qian.ess.mvp.ui.adapter.listview.CommonAdapter;
import com.qian.ess.mvp.ui.adapter.listview.ViewHolder;

/**
 * Created by Administrator on 2018/8/7 0007.
 * <p>
 * 首页任务列表
 */

public class TaskListAdapter extends CommonAdapter<TaskItem> {

    public TaskListAdapter(Context context) {
        super(context, R.layout.item_home_task_list);
    }

    @Override
    protected void convert(ViewHolder holder, TaskItem taskItem, int position) {
        holder.setText(R.id.txt_title, taskItem.getTitle())
                .setText(R.id.txt_content, taskItem.getContent())
                .setText(R.id.txt_date_time, taskItem.getDatetime());
    }
}
