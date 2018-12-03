package com.qian.ess.mvp.ui.adapter.task;

import android.content.Context;
import android.view.View;

import com.qian.ess.R;
import com.qian.ess.bean.es.TaskHistory;
import com.qian.ess.mvp.ui.adapter.listview.CommonAdapter;
import com.qian.ess.mvp.ui.adapter.listview.ViewHolder;

/**
 * Created by QianMoLi on 2018/11/3.
 * <p>
 * 任务历史纪录
 */

public class TaskHistoryAdapter extends CommonAdapter<TaskHistory> {

    public TaskHistoryAdapter(Context context) {
        super(context, R.layout.item_task_history_list);
    }

    @Override
    protected void convert(ViewHolder viewHolder, final TaskHistory item, int position) {
        viewHolder.setText(R.id.txt_name, "工作" + (getArray().size() - position) + "完成情况")
                .setText(R.id.txt_details, (getArray().size() - position) + "：呵呵呵呵")
                .setOnClickListener(R.id.ll_task, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.setIsOpen(!item.getIsOpen());
                        notifyDataSetChanged();
                    }
                });

        if (item.getIsOpen()) {
            viewHolder.setVisible(R.id.txt_details, true)
                    .setImageResource(R.id.img_down, R.mipmap.icon__details_open);
        } else {
            viewHolder.setVisible(R.id.txt_details, false)
                    .setImageResource(R.id.img_down, R.mipmap.icon__details_close);
        }
    }
}
