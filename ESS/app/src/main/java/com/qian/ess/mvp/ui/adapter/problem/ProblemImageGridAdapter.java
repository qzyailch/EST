package com.qian.ess.mvp.ui.adapter.problem;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qian.ess.R;
import com.qian.ess.application.ESSApplication;
import com.qian.ess.mvp.ui.adapter.listview.CommonAdapter;
import com.qian.ess.mvp.ui.adapter.listview.ViewHolder;
import com.qian.ess.utils.CompatUtils;
import com.qian.ess.utils.InitFileUtils;

/**
 * Created by Administrator on 2018/11/6 0006.
 * <p>
 * 排故--结果中的图片
 */

public class ProblemImageGridAdapter extends CommonAdapter<String> {

    private String fileName;
    private String objectNumber;
    private String partNumber;

    public ProblemImageGridAdapter(Context context, String objectNumber, String partNumber, String fileName) {
        super(context, R.layout.item_problem_img);
        this.fileName = fileName;
        this.objectNumber = objectNumber;
        this.partNumber = partNumber;
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        ImageLoader.getInstance().displayImage("file://" + InitFileUtils.getTSFilePath(objectNumber, partNumber) + "/" + fileName + item, (ImageView) viewHolder.getView(R.id.img));

        ImageView imageView = viewHolder.getView(R.id.img);
        //计算图片宽度，将宽度和高度设置为一样
        int imgWidth = ESSApplication.getInstance().getWindowHW()[0] / 3 - CompatUtils.dp2px(mContext, 20f);
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.width = imgWidth;
        lp.height = imgWidth;
        imageView.setLayoutParams(lp);
    }
}
