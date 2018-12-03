package com.qian.ess.mvp.ui.adapter.problem;

import android.content.Context;

import com.qian.ess.R;
import com.qian.ess.mvp.ui.adapter.listview.CommonAdapter;
import com.qian.ess.mvp.ui.adapter.listview.ViewHolder;

/**
 * Created by QianMoLi on 2018/11/3.
 * <p>
 * 每一个问题解决的步骤列表
 */

public class BeginProblemDetailsListAdapter extends CommonAdapter<String> {

    public BeginProblemDetailsListAdapter(Context context) {
        super(context, R.layout.item_begin_problem_details_adapter);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        viewHolder.setText(R.id.txt_step, item);
    }
}
