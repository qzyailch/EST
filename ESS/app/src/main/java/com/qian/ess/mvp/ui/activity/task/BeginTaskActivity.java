package com.qian.ess.mvp.ui.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.camera.JCameraView;
import com.qian.ess.mvp.ui.activity.camera.CameraActivity;
import com.qian.ess.mvp.ui.activity.camera.EditImageActivity;
import com.qian.ess.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/7 0007.
 * <p>
 * 开始任务
 */

public class BeginTaskActivity extends BaseActivity {

    //编辑文本
    private static final int TEXT_CODE = 520;
    //编辑图片
    private static final int PIC_CODE = 522;
    private static final int EDIT_IMAGE_CODE = 526;

    //底部操作按钮
    @Bind(R.id.ll_bottom)
    LinearLayout llBottom;

    @Bind(R.id.ll_btn_1)
    LinearLayout llBtn1;
    @Bind(R.id.img_btn_1)
    ImageView imgBtn1;
    @Bind(R.id.txt_btn_1)
    TextView txtBtn1;

    @Bind(R.id.ll_btn_2)
    LinearLayout llBtn2;
    @Bind(R.id.img_btn_2)
    ImageView imgBtn2;
    @Bind(R.id.txt_btn_2)
    TextView txtBtn2;

    @Bind(R.id.ll_btn_3)
    LinearLayout llBtn3;
    @Bind(R.id.img_btn_3)
    ImageView imgBtn3;
    @Bind(R.id.txt_btn_3)
    TextView txtBtn3;

    @Bind(R.id.ll_btn_4)
    LinearLayout llBtn4;
    @Bind(R.id.img_btn_4)
    ImageView imgBtn4;
    @Bind(R.id.txt_btn_4)
    TextView txtBtn4;

    @Bind(R.id.ll_btn_5)
    LinearLayout llBtn5;
    @Bind(R.id.img_btn_5)
    ImageView imgBtn5;
    @Bind(R.id.txt_btn_5)
    TextView txtBtn5;

    @Bind(R.id.txt_btn_edit)
    TextView txtBtnEdit;
    @Bind(R.id.txt_btn_begin)
    TextView txtBtnBegin;

    /* 用于判断遮罩是否正处于动画状态 */
    private boolean isAnim = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_begin_task;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            commonToolbar.setTitle(bundle.getString("title", ""));
        }
    }

    @Override
    public void initViews() {
        setBtnEnable(txtBtn2, imgBtn2, llBtn2, false);
        setBtnEnable(txtBtn3, imgBtn3, llBtn3, false);
    }

    @OnClick({
            R.id.txt_btn_edit,
            R.id.txt_btn_begin,
            R.id.ll_btn_1,
            R.id.ll_btn_2,
            R.id.ll_btn_3,
            R.id.ll_btn_4,
            R.id.ll_btn_5
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_btn_edit:
            case R.id.txt_btn_begin:
                doAnimateOpen(llBottom);
                break;
            case R.id.ll_btn_1:
                //文本
                forwardForResult(TaskTextActivity.class, TEXT_CODE);
                break;
            case R.id.ll_btn_2:
                //音频
                break;
            case R.id.ll_btn_3:
                //视频
                break;
            case R.id.ll_btn_4:
                //图片
                forwardForResult(CameraActivity.class, PIC_CODE);
                break;
            case R.id.ll_btn_5:
                //历史记录
                forward(TaskHistoryActivity.class, getIntent().getExtras());
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle bundle = new Bundle();
            if (null != data) {
                bundle = data.getExtras();
            }
            switch (requestCode) {
                case TEXT_CODE:
                    if (null != bundle) {
                        String text = bundle.getString("text");
                        ToastUtils.show(text);
                    }
                    break;
                case PIC_CODE:
                    if (null != bundle) {
                        String imgPath = bundle.getString("imgPath");
                        int type = bundle.getInt("type");
                        ToastUtils.show(imgPath);
                        if (type == JCameraView.TYPE_BTN_MENU) {
                            //编辑
                            forwardForResult(EditImageActivity.class, bundle, EDIT_IMAGE_CODE);
                        } else if (type == JCameraView.TYPE_BTN_CONFIRM) {
                            //确认

                        }
                    }
                    break;
                case EDIT_IMAGE_CODE:
                    if (null != bundle) {
                        String imgPath = bundle.getString("imgPath");
                        ToastUtils.show(imgPath);
                    }
                    break;
                default:
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    //设置按钮文字和图标
    private void setBtn(TextView txtBtn, ImageView imgBtn, String text, int srcRes) {
        txtBtn.setText(text);
        if (null == imgBtn || 0 == srcRes) {
            return;
        }
        imgBtn.setImageResource(srcRes);
    }

    //设置按钮状态
    private void setBtnEnable(TextView txtBtn, ImageView imgBtn, LinearLayout llBtn, boolean isEnable) {
        if (isEnable) {
            txtBtn.setAlpha(1.0f);
            imgBtn.setAlpha(1.0f);
            llBtn.setEnabled(true);
        } else {
            txtBtn.setAlpha(0.5f);
            imgBtn.setAlpha(0.5f);
            llBtn.setEnabled(false);
        }
    }

    /**
     * 打开菜单的动画
     *
     * @param view 执行动画的view
     */
    private void doAnimateOpen(View view) {
        closeInput();
        if (view.getVisibility() == View.VISIBLE) {
            return;
        }
        view.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.actionsheet_dialog_in);
        //开始播放动画
        view.startAnimation(animation);
    }

    /**
     * 关闭菜单的动画
     *
     * @param view 执行动画的view
     */
    private void doAnimateClose(final View view) {
        if (view.getVisibility() == View.GONE) {
            return;
        }
        if (isAnim) {
            return;
        }
        isAnim = true;
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.actionsheet_dialog_out);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
                isAnim = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //开始播放动画
        view.startAnimation(animation);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (llBottom.getVisibility() == View.VISIBLE) {
                doAnimateClose(llBottom);
                return true;
            }
            return super.onKeyDown(keyCode, event);
        } else
            return super.onKeyDown(keyCode, event);
    }
}
