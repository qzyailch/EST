package com.qian.ess.mvp.ui.fragment.home;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qian.ess.R;
import com.qian.ess.base.BaseFragment;
import com.qian.ess.bean.config.ts.TSInfo;
import com.qian.ess.bean.ts.TSObject;
import com.qian.ess.db.action.TSObjectAction;
import com.qian.ess.db.action.ts.TSConfigAction;
import com.qian.ess.mvp.ui.activity.problem.ProblemDetailsActivity;
import com.qian.ess.mvp.ui.adapter.ts.TSObjectListAdapter;
import com.qian.ess.utils.LanguageUtils;
import com.qian.ess.utils.ToastUtils;
import com.qian.ess.widget.CustomEmptyView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/8/6 0006.
 * <p>
 * 第二页
 */

public class SecondFragment extends BaseFragment {

    //无数据
    @Bind(R.id.icon_no_data)
    CustomEmptyView mIconNoData;
    //列表
    @Bind(R.id.list_problem)
    ListView listTask;

    private TSObjectListAdapter tsObjectListAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_second;
    }

    @Override
    public void initViews() {
        tsObjectListAdapter = new TSObjectListAdapter(getContext());

        listTask.setAdapter(tsObjectListAdapter);
        listTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TSObject tsObject = tsObjectListAdapter.getArray().get(position);

                TSInfo tsInfo = TSConfigAction.getTSInfoByLanguage(LanguageUtils.getlanguage(), tsObject.getTsShortName());
                if (null == tsInfo) {
                    ToastUtils.show(R.string.not_found);
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putString("tsId", tsObject.getTsID());
                bundle.putString("title", tsObject.getTsShortName());
                bundle.putSerializable("tsInfo", tsInfo); //TSInfo
                forward(ProblemDetailsActivity.class, bundle);
            }
        });
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        List<TSObject> list = TSObjectAction.getAllTSObject();
        tsObjectListAdapter.setAllArray(list);
        tsObjectListAdapter.notifyDataSetChanged();
        if (list.size() > 0) {
            mIconNoData.setVisibility(View.GONE);
        } else {
            mIconNoData.setVisibility(View.VISIBLE);
        }
    }
}
