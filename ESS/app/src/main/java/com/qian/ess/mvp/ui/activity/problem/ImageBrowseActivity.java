package com.qian.ess.mvp.ui.activity.problem;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.utils.InitFileUtils;
import com.qian.ess.widget.PinchImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/1/12 0012.
 * <p>
 * 浏览大图
 */

public class ImageBrowseActivity extends BaseActivity {

    //头部
    @Bind(R.id.common_toolbar_title)
    TextView txtTitle;
    //图片浏览器
    @Bind(R.id.imgs_viewpager)
    ViewPager imgsViewpager;

    private List<String> imgArray = new ArrayList<>();
    private ImageBrowseAdapter mAdapter;

    private String fileName;

    //对象编号
    private String objectNumber;
    //部件编号
    private String partNumber;

    @Override
    public int getLayoutId() {
        return R.layout.activity_image_browse;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {

    }

    @Override
    public void initViews() {
        try {
            Bundle bundle = getIntent().getExtras();
            if (null != bundle) {
                imgArray = (ArrayList<String>) getIntent().getSerializableExtra("imgs");
                fileName = bundle.getString("fileName");
                objectNumber = bundle.getString("objectNumber");
                partNumber = bundle.getString("partNumber");
                int position = bundle.getInt("position");
                setTopTitle(position);

                mAdapter = new ImageBrowseAdapter(this);
                imgsViewpager.setAdapter(mAdapter);
                imgsViewpager.setCurrentItem(position);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        initFunction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化方法
     */
    private void initFunction() {
        imgsViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTopTitle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 设置头部
     */
    private void setTopTitle(int position) {
        txtTitle.setText((position + 1) + "/" + imgArray.size());
    }

    /**
     * 浏览图片适配器
     */
    class ImageBrowseAdapter extends PagerAdapter {

        Context mContext;

        public ImageBrowseAdapter(Context context) {
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return imgArray.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_img_browse, null);
            PinchImageView img = (PinchImageView) view.findViewById(R.id.img_browse);

            String imgPath = imgArray.get(position);
            if (!TextUtils.isEmpty(imgPath)) {
                ImageLoader.getInstance().displayImage("file://" + InitFileUtils.getTSFilePath(objectNumber, partNumber) + "/" + fileName + "/" + imgPath, img);
            } else {
                img.setImageResource(R.mipmap.vehicle_cleaning_gift_certificate);
            }
            ((ViewPager) container).addView(view);
            return view;
        }

    }
}
