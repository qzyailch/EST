package com.qian.ess.mvp.ui.adapter.listview.loadmore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.qian.ess.R;

public class LoadMoreFooter implements View.OnClickListener {

    public enum State {
        disable, loading, nomore, endless, fail
    }

    public interface OnLoadMoreListener {

        void onLoadMore();

    }

    protected ProgressWheel mLoading;
    protected TextView mFooterText;
    protected ListView mListView;

    private State state = State.nomore;
    private final OnLoadMoreListener loadMoreListener;

    public LoadMoreFooter(@NonNull Context context, @NonNull ListView listView, @NonNull OnLoadMoreListener loadMoreListener) {
        this.mListView = listView;
        this.loadMoreListener = loadMoreListener;
        View footerView = LayoutInflater.from(context).inflate(R.layout.footer_load_more, listView, false);
        listView.addFooterView(footerView, null, false);
        mFooterText = (TextView) footerView.findViewById(R.id.tv_text);
        mLoading = (ProgressWheel) footerView.findViewById(R.id.icon_loading);
        //loadEndLayout = (LinearLayout) footerView.findViewById(R.id.layout_load_end);
        setState(State.disable);
        mFooterText.setOnClickListener(this);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (view.getLastVisiblePosition() == view.getCount() - 1) {
                    checkLoadMore();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }

        });
    }

    public void checkLoadMore() {
        if (getState() == State.endless || getState() == State.fail) {
            setState(State.loading);
            loadMoreListener.onLoadMore();
        }
    }

    @NonNull
    public State getState() {
        return state;
    }

    public void setState(@NonNull State state) {
        this.state = state;
        switch (state) {
            case disable:
                mLoading.setVisibility(View.GONE);
                mFooterText.setVisibility(View.GONE);
                mFooterText.setClickable(false);
                break;
            case loading:
                mLoading.setVisibility(View.VISIBLE);
                mFooterText.setVisibility(View.GONE);
                mFooterText.setClickable(false);
                break;
            case nomore:
                int rows = mListView.getCount() - mListView.getFooterViewsCount() - mListView.getHeaderViewsCount();
                mLoading.setVisibility(View.GONE);
                mFooterText.setVisibility(View.GONE);
                mFooterText.setText(Html.fromHtml("已全部加载完毕 共<font color='#d7002e'>" + rows + "</font>条"));
                mFooterText.setClickable(false);
                break;
            case endless:
                mLoading.setVisibility(View.GONE);
                mFooterText.setVisibility(View.VISIBLE);
                mFooterText.setText(R.string.load_more);
                mFooterText.setClickable(true);
                break;
            case fail:
                mLoading.setVisibility(View.GONE);
                mFooterText.setVisibility(View.VISIBLE);
                mFooterText.setText(R.string.load_more_fail);
                mFooterText.setClickable(true);
                break;
            default:
                throw new AssertionError("Unknow state.");
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.tv_text:
                checkLoadMore();
                break;
        }
    }
}
