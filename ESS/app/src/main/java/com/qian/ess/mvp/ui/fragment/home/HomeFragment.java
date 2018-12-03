package com.qian.ess.mvp.ui.fragment.home;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qian.ess.R;
import com.qian.ess.base.BaseFragment;
import com.qian.ess.bean.tc.PCJobObject;
import com.qian.ess.db.action.PCObjectAction;
import com.qian.ess.mvp.ui.activity.task.TaskDetailsActivity;
import com.qian.ess.mvp.ui.adapter.pc.PCObjectListAdapter;
import com.qian.ess.widget.CustomEmptyView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/8/6 0006.
 * <p>
 * 首页
 */

public class HomeFragment extends BaseFragment {

    //无数据
    @Bind(R.id.icon_no_data)
    CustomEmptyView mIconNoData;
    //列表
    @Bind(R.id.list_task)
    ListView listTask;

    private PCObjectListAdapter mPCObjectListAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews() {
        mPCObjectListAdapter = new PCObjectListAdapter(getContext());

        listTask.setAdapter(mPCObjectListAdapter);
        listTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PCJobObject pcJobObject = mPCObjectListAdapter.getArray().get(position);

                Bundle bundle = new Bundle();
                bundle.putString("title", pcJobObject.getProcessCard().getName());
                bundle.putString("fileName",pcJobObject.getPcReceiveFileName());
                forward(TaskDetailsActivity.class, bundle);
            }
        });
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        List<PCJobObject> list = PCObjectAction.getAllPCJobObject();
        mPCObjectListAdapter.setAllArray(list);
        mPCObjectListAdapter.notifyDataSetChanged();
        if (list.size() > 0) {
            mIconNoData.setVisibility(View.GONE);
        } else {
            mIconNoData.setVisibility(View.VISIBLE);
        }
    }
}
