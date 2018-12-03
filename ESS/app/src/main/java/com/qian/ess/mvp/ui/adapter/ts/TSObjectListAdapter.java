package com.qian.ess.mvp.ui.adapter.ts;

import android.content.Context;

import com.qian.ess.R;
import com.qian.ess.bean.ts.TSObject;
import com.qian.ess.mvp.ui.adapter.listview.CommonAdapter;
import com.qian.ess.mvp.ui.adapter.listview.ViewHolder;
import com.qian.ess.utils.DateUtils;

/**
 * Created by Administrator on 2018/8/7 0007.
 * <p>
 * 首页任务列表 TS
 */

public class TSObjectListAdapter extends CommonAdapter<TSObject> {

    public TSObjectListAdapter(Context context) {
        super(context, R.layout.item_home_task_list);
    }

    @Override
    protected void convert(ViewHolder holder, TSObject tsObject, int position) {
        holder.setText(R.id.txt_title, tsObject.getTsShortName() + "：" + tsObject.getTsFullName())
                .setText(R.id.txt_content, tsObject.getFileName())
                .setText(R.id.txt_date_time, DateUtils.dateToString(DateUtils.stringToDate(tsObject.getTsBeginDateTime(), DateUtils.FORMAT_LONG_WITHOUT), DateUtils.FORMAT_LONG_NOT_SECOND));
    }
}
