package com.qian.ess.mvp.ui.adapter.home;

import android.content.Context;

import com.qian.ess.R;
import com.qian.ess.bean.home.PDFItem;
import com.qian.ess.mvp.ui.adapter.listview.CommonAdapter;
import com.qian.ess.mvp.ui.adapter.listview.ViewHolder;

/**
 * Created by Administrator on 2018/8/7 0007.
 * <p>
 * 首页--文档--列表适配器
 */

public class PDFListAdapter extends CommonAdapter<PDFItem> {

    public PDFListAdapter(Context context) {
        super(context, R.layout.item_home_task_list);
    }

    @Override
    protected void convert(ViewHolder viewHolder, PDFItem item, int position) {
        viewHolder.setText(R.id.txt_title, item.getTitle())
                .setText(R.id.txt_content, item.getPath())
                .setVisible(R.id.txt_date_time, false);
    }
}
