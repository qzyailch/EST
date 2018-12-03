package com.qian.ess.mvp.ui.adapter.listview;

import android.content.Context;

import com.qian.ess.mvp.ui.adapter.listview.base.ItemViewDelegate;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {

    public CommonAdapter(Context context, int layoutId) {
        super(context, new ArrayList<T>());
        init(layoutId);
    }

    public CommonAdapter(Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        init(layoutId);
    }

    protected void init(final int layoutId) {
        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder viewHolder, T item, int position);

}
