package com.qian.ess.mvp.ui.fragment.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qian.ess.R;
import com.qian.ess.base.BaseFragment;
import com.qian.ess.common.Constants;
import com.qian.ess.mvp.ui.activity.SocketClientActivity;
import com.qian.ess.mvp.ui.activity.status.StatusActivity;
import com.qian.ess.utils.SPUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/6 0006.
 * <p>
 * 第四页
 */

public class FourFragment extends BaseFragment {

    @Bind(R.id.txt_ec_1_title)
    TextView txtEc1Title;
    @Bind(R.id.txt_ec_1_content)
    TextView txtEc1Content;
    @Bind(R.id.txt_ec_2_title)
    TextView txtEc2Title;
    @Bind(R.id.txt_ec_2_content)
    TextView txtEc2Content;
    @Bind(R.id.txt_ec_3_title)
    TextView txtEc3Title;
    @Bind(R.id.txt_ec_3_content)
    TextView txtEc3Content;
    @Bind(R.id.txt_ec_4_title)
    TextView txtEc4Title;
    @Bind(R.id.txt_ec_4_content)
    TextView txtEc4Content;
    @Bind(R.id.txt_ec_5_title)
    TextView txtEc5Title;
    @Bind(R.id.txt_ec_5_content)
    TextView txtEc5Content;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_four;
    }

    @Override
    public void initViews() {
        if (Constants.LANGUAGE.ENGLISH.equals(SPUtils.getString("language"))) {
            initEnglishData();
        } else {
            initChineseData();
        }
    }

    /**
     * 获取数据
     */
    private void initChineseData() {
        txtEc1Title.setText("状态1");
        txtEc1Content.setText("内容内容内容内容内容内容内容内容内容内容内容内容内容内容");

        txtEc2Title.setText("状态1");
        txtEc2Content.setText("内容内容内容内容内容内容内容内容内容内容内容内容内容内容");

        txtEc3Title.setText("状态1");
        txtEc3Content.setText("内容内容内容内容内容内容内容内容内容内容内容内容内容内容");

        txtEc4Title.setText("状态1");
        txtEc4Content.setText("内容内容内容内容内容内容内容内容内容内容内容内容内容内容");

        txtEc5Title.setText("状态1");
        txtEc5Content.setText("内容内容内容内容内容内容内容内容内容内容内容内容内容内容");
    }

    /**
     * 获取数据
     */
    private void initEnglishData() {
        txtEc1Title.setText("EC1");
        txtEc1Content.setText("this is content,this is content,this is content,this is content");

        txtEc2Title.setText("EC2");
        txtEc2Content.setText("this is content,this is content,this is content,this is content");

        txtEc3Title.setText("EC3");
        txtEc3Content.setText("this is content,this is content,this is content,this is content");

        txtEc4Title.setText("EC4");
        txtEc4Content.setText("this is content,this is content,this is content,this is content");

        txtEc5Title.setText("EC5");
        txtEc5Content.setText("this is content,this is content,this is content,this is content");
    }

    @OnClick({R.id.ll_top, R.id.ll_second, R.id.ll_third, R.id.ll_four, R.id.ll_five})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_top:
                //forward(ScanIpActivity.class);
                break;
            case R.id.ll_second:
                Bundle bundle = new Bundle();
//                forward(SocketClientActivity.class, bundle);
                break;
            case R.id.ll_third:
                //forward(SocketClientActivity.class);
                break;
            case R.id.ll_four:
                forward(StatusActivity.class);
                break;
            case R.id.ll_five:
                forward(StatusActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
