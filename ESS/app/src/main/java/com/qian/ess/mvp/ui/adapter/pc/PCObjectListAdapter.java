package com.qian.ess.mvp.ui.adapter.pc;

import android.content.Context;

import com.qian.ess.R;
import com.qian.ess.bean.tc.PCJobObject;
import com.qian.ess.mvp.ui.adapter.listview.CommonAdapter;
import com.qian.ess.mvp.ui.adapter.listview.ViewHolder;
import com.qian.ess.utils.DateUtils;

/**
 * Created by Administrator on 2018/8/7 0007.
 * <p>
 * 首页任务列表 PC
 */

public class PCObjectListAdapter extends CommonAdapter<PCJobObject> {

    public PCObjectListAdapter(Context context) {
        super(context, R.layout.item_home_task_list);
    }

    @Override
    protected void convert(ViewHolder holder, PCJobObject pcJobObject, int position) {
        holder.setText(R.id.txt_title, pcJobObject.getProcessCard().getName())
                .setText(R.id.txt_content, pcJobObject.getPcReceiveFileName())
                .setText(R.id.txt_date_time, DateUtils.dateToString(DateUtils.stringToDate(pcJobObject.getBeginDateTime(), DateUtils.FORMAT_LONG_WITHOUT), DateUtils.FORMAT_LONG_NOT_SECOND));
    }
}
