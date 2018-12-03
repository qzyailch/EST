package com.qian.ess.mvp.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.bean.config.ConfigIcon;
import com.qian.ess.common.Constants;
import com.qian.ess.common.ESSFilePath;
import com.qian.ess.db.action.ConfigIconAction;
import com.qian.ess.mvp.ui.activity.dialog.SettingDialogActivity;
import com.qian.ess.mvp.ui.fragment.home.FourFragment;
import com.qian.ess.mvp.ui.fragment.home.HomeFragment;
import com.qian.ess.mvp.ui.fragment.home.SecondFragment;
import com.qian.ess.mvp.ui.fragment.home.ThirdFragment;
import com.qian.ess.service.SocketService;
import com.qian.ess.utils.CompatUtils;
import com.qian.ess.utils.NetworkUtils;
import com.qian.ess.utils.SPUtils;
import com.qian.ess.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 首页
 */
public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.vp_main)
    ViewPager vpMian;

    //底部按钮
    @Bind(R.id.img_home)
    ImageView imgHome;
    @Bind(R.id.txt_home)
    TextView txtHome;

    @Bind(R.id.img_second)
    ImageView imgSecond;
    @Bind(R.id.txt_second)
    TextView txtSecond;

    @Bind(R.id.img_third)
    ImageView imgThird;
    @Bind(R.id.txt_third)
    TextView txtThird;

    @Bind(R.id.img_four)
    ImageView imgFour;
    @Bind(R.id.txt_four)
    TextView txtFour;

    //未读消息
    @Bind(R.id.txt_message_home)
    TextView txtMessageHome;
    @Bind(R.id.txt_message_second)
    TextView txtMessageSecond;

    private HomeFragment homeFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private FourFragment fourFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {
        commonToolbar.setTitle(R.string.ess_app_name);
    }

    @Override
    public void initViews() {
        initPager();

        connect();
    }

    //连接
    private void connect() {
        if (!TextUtils.isEmpty(SPUtils.getString("ip")) && !TextUtils.isEmpty(SPUtils.getString("port"))) {
            if (NetworkUtils.isConnected(this)) {
                Intent intent = new Intent(this, SocketService.class);
                startService(intent);
            }
        }
    }

    /**
     * 初始化ViewPager
     */
    private void initPager() {
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        vpMian.setOffscreenPageLimit(3);
        vpMian.setAdapter(mainAdapter);
        vpMian.addOnPageChangeListener(this);
    }

    @Override
    public boolean hasBackIcon() {
        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //menu.findItem(R.id.menu_add).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) { //得到被点击的item的itemId
            case R.id.menu_search:  //对应的ID就是在add方法中所设定的Id
                ToastUtils.show("搜素");
                break;
            case R.id.action_user:

                break;
            case R.id.action_logout:

                break;
            case R.id.action_language_chinese:
                if (!Constants.LANGUAGE.CHINESE.equals(SPUtils.getString("language"))) {
                    SPUtils.putString("language", Constants.LANGUAGE.CHINESE);
                    recreate();//刷新界面
                }
                break;
            case R.id.action_language_english:
                if (!Constants.LANGUAGE.ENGLISH.equals(SPUtils.getString("language"))) {
                    SPUtils.putString("language", Constants.LANGUAGE.ENGLISH);
                    recreate();//刷新界面
                }
                break;
            case R.id.action_letter:
                //文字
                setShowIconOrText(1);
                break;
            case R.id.action_icon:
                //图标
                setShowIconOrText(0);
                break;
            case R.id.action_setting:
                forward(SettingDialogActivity.class, null, Constants.ActivityAnimType.DIALOG);
                break;
            default:
                break;
        }
        return true;
    }

    //显示文字或者图标
    private void setShowIconOrText(int type) {
        if (type == 1) {
            //文字
            txtHome.setVisibility(View.VISIBLE);
            txtSecond.setVisibility(View.VISIBLE);
            txtThird.setVisibility(View.VISIBLE);
            txtFour.setVisibility(View.VISIBLE);

            //计算图片宽度，将宽度和高度设置为一样
            ViewGroup.LayoutParams lp = imgHome.getLayoutParams();
            lp.width = CompatUtils.dp2px(mContext, 20f);
            lp.height = CompatUtils.dp2px(mContext, 20f);
            imgHome.setLayoutParams(lp);
            imgSecond.setLayoutParams(lp);
            imgThird.setLayoutParams(lp);
            imgFour.setLayoutParams(lp);

            margin(txtMessageHome, CompatUtils.dp2px(mContext, 10f), CompatUtils.dp2px(mContext, 2f), 0, 0);
            margin(txtMessageSecond, CompatUtils.dp2px(mContext, 10f), CompatUtils.dp2px(mContext, 2f), 0, 0);
        } else {
            //图标
            txtHome.setVisibility(View.GONE);
            txtSecond.setVisibility(View.GONE);
            txtThird.setVisibility(View.GONE);
            txtFour.setVisibility(View.GONE);

            //计算图片宽度，将宽度和高度设置为一样
            ViewGroup.LayoutParams lp = imgHome.getLayoutParams();
            lp.width = CompatUtils.dp2px(mContext, 40.5f);
            lp.height = CompatUtils.dp2px(mContext, 40.5f);
            imgHome.setLayoutParams(lp);
            imgSecond.setLayoutParams(lp);
            imgThird.setLayoutParams(lp);
            imgFour.setLayoutParams(lp);

            margin(txtMessageHome, CompatUtils.dp2px(mContext, 20f), CompatUtils.dp2px(mContext, 2f), 0, 0);
            margin(txtMessageSecond, CompatUtils.dp2px(mContext, 20f), CompatUtils.dp2px(mContext, 2f), 0, 0);
        }
    }

    private void margin(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /**
     * 切换Fragment
     *
     * @param pageNum 页面下标
     */
    public void switchFragmentPre(int pageNum) {
        if (pageNum < PAGE_ONE || pageNum > PAGE_FOUR) {
            pageNum = PAGE_ONE;
        }
        vpMian.setCurrentItem(pageNum, true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            mApp.exitWithDoubleClick();
            return true;
        } else
            return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setBottomIconChecked(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick({
            R.id.ll_home,
            R.id.ll_second,
            R.id.ll_third,
            R.id.ll_four
    })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                switchFragmentPre(PAGE_ONE);
                break;
            case R.id.ll_second:
                switchFragmentPre(PAGE_TWO);
                break;
            case R.id.ll_third:
                switchFragmentPre(PAGE_THREE);
                break;
            case R.id.ll_four:
                switchFragmentPre(PAGE_FOUR);
                break;
            default:
                break;
        }
    }

    //设置底部按钮选中状态
    private void setBottomIconChecked(int position) {
        ConfigIcon configIcon = ConfigIconAction.getConfigIcon();
        if (null == configIcon) {
            configIcon = new ConfigIcon();
        }
        String outputPath = ESSFilePath.ICON_FILE;

        //初始化
        ImageLoader.getInstance().displayImage("file://" + outputPath + "/" + configIcon.getSs(), imgHome);
        ImageLoader.getInstance().displayImage("file://" + outputPath + "/" + configIcon.getTs(), imgSecond);
        ImageLoader.getInstance().displayImage("file://" + outputPath + "/" + configIcon.getEd(), imgThird);
        ImageLoader.getInstance().displayImage("file://" + outputPath + "/" + configIcon.getEc(), imgFour);

        txtHome.setTextColor(getResources().getColor(R.color.text_subtitle_color));
        txtSecond.setTextColor(getResources().getColor(R.color.text_subtitle_color));
        txtThird.setTextColor(getResources().getColor(R.color.text_subtitle_color));
        txtFour.setTextColor(getResources().getColor(R.color.text_subtitle_color));

        switch (position) {
            case PAGE_ONE:
                ImageLoader.getInstance().displayImage("file://" + outputPath + "/" + configIcon.getSs(), imgHome);
                txtHome.setTextColor(getResources().getColor(R.color.app_theme_color_green));
                break;
            case PAGE_TWO:
                ImageLoader.getInstance().displayImage("file://" + outputPath + "/" + configIcon.getTs(), imgSecond);
                txtSecond.setTextColor(getResources().getColor(R.color.app_theme_color_green));
                break;
            case PAGE_THREE:
                ImageLoader.getInstance().displayImage("file://" + outputPath + "/" + configIcon.getEd(), imgThird);
                txtThird.setTextColor(getResources().getColor(R.color.app_theme_color_green));
                break;
            case PAGE_FOUR:
                ImageLoader.getInstance().displayImage("file://" + outputPath + "/" + configIcon.getEc(), imgFour);
                txtFour.setTextColor(getResources().getColor(R.color.app_theme_color_green));
                break;
            default:
                break;
        }
    }

    class MainAdapter extends FragmentPagerAdapter {

        public MainAdapter(FragmentManager fm) {
            super(fm);
            homeFragment = new HomeFragment();
            secondFragment = new SecondFragment();
            thirdFragment = new ThirdFragment();
            fourFragment = new FourFragment();
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return homeFragment;
                case 1:
                    return secondFragment;
                case 2:
                    return thirdFragment;
                case 3:
                    return fourFragment;
                default:
                    break;
            }

            return homeFragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
