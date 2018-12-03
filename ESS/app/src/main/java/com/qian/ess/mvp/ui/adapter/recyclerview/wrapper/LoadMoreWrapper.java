package com.qian.ess.mvp.ui.adapter.recyclerview.wrapper;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.qian.ess.R;
import com.qian.ess.mvp.ui.adapter.recyclerview.base.BaseViewHolder;
import com.qian.ess.mvp.ui.adapter.recyclerview.utils.WrapperUtils;


/**
 * Created by zhy on 16/6/23.
 */
public class LoadMoreWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2;

    public enum State {
        disable, //没数据，不显示
        loading, //正在加载
        nomore, //已加载完毕
        endless, //加载更多
        fail //加载失败
    }

    private State state = State.disable;

    private RecyclerView.Adapter mInnerAdapter;
    private View mLoadMoreView;
    private int mLoadMoreLayoutId;

    public LoadMoreWrapper(RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
        mLoadMoreLayoutId = R.layout.footer_load_more;
    }

    private boolean hasLoadMore() {
        return mLoadMoreView != null || mLoadMoreLayoutId != 0;
    }


    private boolean isShowLoadMore(int position) {
        return hasLoadMore() && (position >= mInnerAdapter.getItemCount());
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowLoadMore(position)) {
            return ITEM_TYPE_LOAD_MORE;
        }
        return mInnerAdapter.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_LOAD_MORE) {
            BaseViewHolder holder;
            if (mLoadMoreView != null) {
                holder = BaseViewHolder.createViewHolder(parent.getContext(), mLoadMoreView);
            } else {
                holder = BaseViewHolder.createViewHolder(parent.getContext(), parent, mLoadMoreLayoutId);
            }
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isShowLoadMore(position)) {
            BaseViewHolder viewHolder = (BaseViewHolder) holder;
            setLoadMoreState(viewHolder);
            if (mOnLoadMoreListener != null && (getState() == State.endless || getState() == State.fail)) {
                setState(State.loading);
                setLoadMoreState(viewHolder);
                mOnLoadMoreListener.onLoadMore();
            }
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                if (isShowLoadMore(position)) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null) {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }


    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);

        if (isShowLoadMore(holder.getLayoutPosition())) {
            setFullSpan(holder);
        }
    }

    private void setFullSpan(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;

            p.setFullSpan(true);
        }
    }

    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount() + (hasLoadMore() ? 1 : 0);
    }


    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public LoadMoreWrapper setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener != null) {
            mOnLoadMoreListener = loadMoreListener;
        }
        return this;
    }

    public LoadMoreWrapper setLoadMoreView(View loadMoreView) {
        mLoadMoreView = loadMoreView;
        return this;
    }

    public LoadMoreWrapper setLoadMoreView(int layoutId) {
        mLoadMoreLayoutId = layoutId;
        return this;
    }

    private void setLoadMoreState(BaseViewHolder holder) {
        switch (getState()) {
            case disable:
                holder.setVisible(R.id.icon_loading, false)
                        .setVisible(R.id.tv_text, false)
                        .setClickAble(R.id.tv_text, false);
                break;
            case loading:
                holder.setVisible(R.id.icon_loading, true)
                        .setVisible(R.id.tv_text, false)
                        .setClickAble(R.id.tv_text, false);
                break;
            case nomore:
                holder.setVisible(R.id.icon_loading, false)
                        .setVisible(R.id.tv_text, false)
                        .setClickAble(R.id.tv_text, false);
                break;
            case endless:
                holder.setVisible(R.id.icon_loading, false)
                        .setVisible(R.id.tv_text, true)
                        .setText(R.id.tv_text, R.string.load_more)
                        .setClickAble(R.id.tv_text, true);
                break;
            case fail:
                holder.setVisible(R.id.icon_loading, false)
                        .setVisible(R.id.tv_text, true)
                        .setText(R.id.tv_text, R.string.load_more_fail)
                        .setClickAble(R.id.tv_text, true);
                break;
            default:
                throw new AssertionError("Unknow state.");
        }
    }

    @NonNull
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
