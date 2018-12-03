package com.qian.ess.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qian.ess.R;

/**
 * Created by Administrator on 2017/3/22.
 * 自定义EmptyView
 */
public class CustomEmptyView extends FrameLayout {

    private View mRootView;
    private Context mContext;

    private ImageView mEmptyImg;
    private TextView mEmptyText;

    public CustomEmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public CustomEmptyView(Context context) {
        this(context, null);
    }

    public CustomEmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public void init() {
        mRootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_empty, this);
        mEmptyImg = (ImageView) mRootView.findViewById(R.id.custom_empty_img);
        mEmptyText = (TextView) mRootView.findViewById(R.id.custom_empty_text);
    }

    /**
     * 设置无数据图片
     */
    public void setEmptyImage(int imgRes) {
        mEmptyImg.setImageResource(imgRes);
    }

    /**
     * 设置无数据文字
     */
    public void setEmptyText(String text) {
        mEmptyText.setText(text);
    }

    /**
     * 设置无数据文字颜色
     */
    public void setmEmptyTextColor(int color) {
        mEmptyText.setTextColor(mContext.getResources().getColor(color));
    }
}
