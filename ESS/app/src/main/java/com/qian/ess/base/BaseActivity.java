package com.qian.ess.base;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.qian.ess.R;
import com.qian.ess.application.ESSApplication;
import com.qian.ess.base.callback.IFragmentBackHandled;
import com.qian.ess.base.callback.UnZipListener;
import com.qian.ess.bean.config.ConfigIcon;
import com.qian.ess.bean.config.FirstBaseInfo;
import com.qian.ess.bean.config.tc.TCConfig;
import com.qian.ess.bean.config.ts.TSConfig;
import com.qian.ess.bean.config.ui.UINameConfig;
import com.qian.ess.bean.config.user.UserConfig;
import com.qian.ess.bean.tc.PCJobObject;
import com.qian.ess.bean.ts.TSObject;
import com.qian.ess.common.Constants;
import com.qian.ess.common.ESSFilePath;
import com.qian.ess.db.action.ConfigIconAction;
import com.qian.ess.db.action.FirstBaseInfoAction;
import com.qian.ess.db.action.PCObjectAction;
import com.qian.ess.db.action.TSObjectAction;
import com.qian.ess.db.action.UINameAction;
import com.qian.ess.db.action.tc.TCConfigAction;
import com.qian.ess.db.action.ts.TSConfigAction;
import com.qian.ess.db.action.user.UserConfigAction;
import com.qian.ess.mvp.ui.activity.FirstInputActivity;
import com.qian.ess.mvp.ui.activity.MainActivity;
import com.qian.ess.utils.DeleteFileUtil;
import com.qian.ess.utils.DialogUtils;
import com.qian.ess.utils.Logger;
import com.qian.ess.utils.SPUtils;
import com.qian.ess.utils.ToastUtils;
import com.qian.ess.utils.ZipUtils;
import com.qian.ess.utils.thread.ThreadPoolProxyFactory;
import com.qian.ess.widget.loadding.CustomDialog;

import java.util.Locale;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/10 0010.
 */

public abstract class BaseActivity extends AppCompatActivity implements IFragmentBackHandled {

    protected String TAG;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    protected Context mContext;
    private CustomDialog dialog;//进度条
    private ProgressDialog mLoadingDialog;

    protected ESSApplication mApp;

    private BaseFragment mBaseFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFullScreen()) {
            initWindows();
        }
        changeAppLanguage();
        TAG = this.getClass().getSimpleName();
        mApp = ESSApplication.getInstance();
        mApp.addActivity(this);
        mContext = this;

        if (isSetContentView()) {
            setContentView(getLayoutId());
            ButterKnife.bind(this);

            Toolbar commonToolbar = ButterKnife.findById(this, R.id.common_toolbar);
            if (commonToolbar != null) {
                setSupportActionBar(commonToolbar);

                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayShowTitleEnabled(false); //隐藏自带的title
                }

                if (hasBackIcon()) {
                    commonToolbar.setNavigationIcon(getBackIcon());
                    commonToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackIconListener();
                        }
                    });
                }

                commonToolbar.setTitleTextColor(getResources().getColor(R.color.text_white));
                initToolBar(commonToolbar);
            }
        }
        //地图需要初始化
        onChildCreate(savedInstanceState);
        initViews();
    }

    public void changeAppLanguage() {
        String sta = SPUtils.getString("language");//这是SharedPreferences工具类，用于保存设置，代码很简单，自己实现吧
        // 本地语言设置
        Locale myLocale = Locale.getDefault();
        if (!TextUtils.isEmpty(sta)) {
            switch (sta) {
                case Constants.LANGUAGE.ENGLISH:
                    myLocale = Locale.US;
                    break;
                case Constants.LANGUAGE.CHINESE:
                    myLocale = Locale.SIMPLIFIED_CHINESE;
                    break;
                default:
                    break;
            }
        }
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
    }

    @Override
    protected void onPause() {
        unregisterReceiver();
        super.onPause();
    }

    /**
     * 注册网络状态监听的广播
     */
    private void registerReceiver() {
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(Constants.ESSBroadcast.TOAST_ACTION);
        mFilter.addAction(Constants.ESSBroadcast.RECEIVE_FILE_PROCESS_ACTION);
        mFilter.addAction(Constants.ESSBroadcast.DISMISS_DIALOG_ACTION);
        registerReceiver(myNetReceiver, mFilter);
    }

    /**
     * 取消监听网络状态监听的广播
     */
    private void unregisterReceiver() {
        if (myNetReceiver != null) {
            unregisterReceiver(myNetReceiver);
        }
    }

    private BroadcastReceiver myNetReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Logger.i("result", "LoadingDialogActivity BroadcastReceiver : " + action);
            if (action.equals(Constants.ESSBroadcast.RECEIVE_FILE_PROCESS_ACTION)) {
                //0：接收文件 1：发送文件
                int type = intent.getIntExtra("type", 0);
                String message = type == 0 ? getResources().getString(R.string.receive_file_ing) : getResources().getString(R.string.send_file_ing);
                showSysLoadingDialog(message + intent.getStringExtra("process"));
            } else if (action.equals(Constants.ESSBroadcast.DISMISS_DIALOG_ACTION)) {
                dismissDialog();
                String filePath = intent.getStringExtra("filePath");
                String message = intent.getStringExtra("message");
                if (!TextUtils.isEmpty(filePath)) {
                    if (filePath.endsWith(".zip")) {
                        check(filePath);
                    }
                }
            } else if (action.equals(Constants.ESSBroadcast.TOAST_ACTION)) {
                ToastUtils.show(intent.getStringExtra("toast"));
            }
        }
    };

    /**
     * 检查文件是否重复
     *
     * @param filePath 接收到的文件保存路径，包含文件名
     *                 JTR-BXS_11-20181102-1600-L-NoM1-NoO
     *                 JTR-BXS_11-20181102-1600-R-NoM2-NoO
     *                 JTR-故障简称-日期-时间-位置-部件编号-对象编号
     */
    private void check(final String filePath) {
        try {
            //获取文件名，包含后缀名
            String fileName = filePath.split("/")[filePath.split("/").length - 1];
            if (fileName.length() > 4) {
                //文件名，不含后缀名
                final String fileNameWithOut = fileName.substring(0, fileName.length() - 4);

                FirstBaseInfo firstBaseInfo = FirstBaseInfoAction.getFirstBaseInfo();
                if (null == firstBaseInfo) {
                    ToastUtils.show("基本信息不存在，请重新输入");
                    forward(FirstInputActivity.class);
                    return;
                }

                String[] baseInfo = fileNameWithOut.split("-");
                if (baseInfo.length < 7) {
                    ToastUtils.show("文件名不合规则");
                    return;
                }

                //对象编号
                String NoO = baseInfo[6];
                //部件编号
                String memberNo = baseInfo[5];
                if (!NoO.equals(firstBaseInfo.getObjectNum())) {
                    ToastUtils.show("对象编号不正确");
                    return;
                }
                if (!memberNo.equals(firstBaseInfo.getComponentNum1()) && !memberNo.equals(firstBaseInfo.getComponentNum2())) {
                    ToastUtils.show("部件编号不正确");
                    return;
                }

                if (fileName.startsWith("JTR")) { //TS
                    //获取到解压后的路径，压缩文件同名路径下
                    final String tsOutputPath = ESSFilePath.ObjectFile.getMemberNoMForTS(NoO, memberNo) + "/" + fileNameWithOut;
                    TSObject tsObject = TSObjectAction.getTSObjectByFileName(fileNameWithOut);
                    if (null != tsObject) {
                        isReplace(new DialogUtils.IDialogCallBack() {
                            @Override
                            public void onConfirm(String data) {
                                unZip(filePath, tsOutputPath, true, new UnZipListener() {
                                    @Override
                                    public void onUnziped(String outputPath) {
                                        TSObjectAction.clearOldObject(fileNameWithOut);
                                        TSObjectAction.addOrUpdateTSObject(TSObject.fromXml(outputPath + "/" + fileNameWithOut + ".xml"), fileNameWithOut);
                                    }
                                });
                            }

                            @Override
                            public void onCancel() {

                            }
                        });
                    } else {
                        unZip(filePath, tsOutputPath, true, new UnZipListener() {
                            @Override
                            public void onUnziped(String outputPath) {
                                TSObjectAction.addOrUpdateTSObject(TSObject.fromXml(outputPath + "/" + fileNameWithOut + ".xml"), fileNameWithOut);
                            }
                        });
                    }
                } else if (fileName.startsWith("JPC")) { //PC
                    //获取到解压后的路径，压缩文件同名路径下
                    final String pcOutputPath = ESSFilePath.ObjectFile.getMemberNoMForPC(NoO, memberNo) + "/" + fileNameWithOut;
                    PCJobObject pcJobObject = PCObjectAction.getPCJobObjectByFileName(fileNameWithOut);
                    if (null != pcJobObject) {
                        //是否替换
                        isReplace(new DialogUtils.IDialogCallBack() {
                            @Override
                            public void onConfirm(String data) {
                                unZip(filePath, pcOutputPath, true, new UnZipListener() {
                                    @Override
                                    public void onUnziped(String outputPath) {
                                        PCObjectAction.clearOldPCObject(fileNameWithOut);
                                        PCObjectAction.addOrUpdatePCJobObject(PCJobObject.fromXml(outputPath + "/" + fileNameWithOut + ".xml"), fileNameWithOut);
                                    }
                                });
                            }

                            @Override
                            public void onCancel() {

                            }
                        });
                    } else {
                        //解压
                        unZip(filePath, pcOutputPath, true, new UnZipListener() {
                            @Override
                            public void onUnziped(String outputPath) {
                                PCObjectAction.addOrUpdatePCJobObject(PCJobObject.fromXml(outputPath + "/" + fileNameWithOut + ".xml"), fileNameWithOut);
                            }
                        });
                    }
                } else if (fileName.startsWith("Config")) { //config
                    unZip(filePath, ESSFilePath.CONFIG_FILE, false, new UnZipListener() {
                        @Override
                        public void onUnziped(String outputPath) {
                            initConfig(outputPath);
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解压文件
     *
     * @param srcFilePath 源文件路径
     * @param outputPath  解压后文件路径
     * @param isDeleteOld 是否删除旧的文件夹
     */
    private void unZip(final String srcFilePath, final String outputPath, final boolean isDeleteOld, final UnZipListener unZipListener) {
        showSysLoadingDialog(R.string.ess_unzip_file);
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (isDeleteOld) {
                        DeleteFileUtil.delete(outputPath);
                    }
                    ZipUtils.UnZipFolder(srcFilePath, outputPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissDialog();
                        ToastUtils.show(R.string.ess_unzip_success);
                        if (null != unZipListener) {
                            unZipListener.onUnziped(outputPath);
                        }
                    }
                });
            }
        });
    }

    //是否替换
    private void isReplace(DialogUtils.IDialogCallBack callBack) {
        DialogUtils.showConfirmDialog(getResources().getString(R.string.change_file), BaseActivity.this, callBack);
    }

    //初始化config文件
    private void initConfig(String path) {
        //添加TSConfig
        TSConfig tsConfig = TSConfig.fromXml(path + "/Config-TS-Links.config");
        Logger.i("result", "===============" + tsConfig.toString());
        TSConfigAction.addOrUpdateTSConfig(tsConfig);

        //添加PCConfig
        TCConfig tcConfig = TCConfig.fromXml(path + "/Config-TC-Links.config");
        Logger.i("result", "===============" + tcConfig.toString());
        TCConfigAction.addOrUpdateTCConfig(tcConfig);

        //添加UIName
        UINameConfig uiNameConfig = UINameConfig.fromXml(path + "/Config-UI-Names.config");
        Logger.i("result", "===============" + uiNameConfig.toString());
        UINameAction.addOrUpdateUINameConfig(uiNameConfig);

        //添加User
        UserConfig userConfig = UserConfig.fromXml(path + "/Config-User-Names.config");
        Logger.i("result", "===============" + userConfig.toString());
        UserConfigAction.addOrUpdateTSConfig(userConfig);

        //添加icon
        ConfigIcon configIconInit = ConfigIcon.fromXml(ESSFilePath.CONFIG_FILE + "/Config-Icons.config");
        Logger.i("result", "===============" + configIconInit.toString());
        ConfigIconAction.addOrUpdateConfigIcon(configIconInit);
    }

    protected void initWindows() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        //全透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 关闭软键盘状态
     */
    public void closeInput() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mBaseFragment != null && mBaseFragment.onBackPressed()) {
                return true;
            } else {
                backward();
            }
            return true;
        } else
            return super.onKeyDown(keyCode, event);
    }

    /**
     * 是否是全屏（默认为false），可在具体的Activity中重写
     */
    public boolean isFullScreen() {
        return false;
    }

    /**
     * 返回按钮，默认为箭头
     */
    public int getBackIcon() {
        return R.mipmap.com_back;
    }

    /**
     * 返回按钮点击事件，默认为backward事件
     */
    public void onBackIconListener() {
        backward();
    }

    /**
     * 是否setContentView（默认为true），可在具体的Activity中重写
     */
    public boolean isSetContentView() {
        return true;
    }

    /**
     * 是否有返回按钮（默认为true），可在具体的Activity中重写
     */
    public boolean hasBackIcon() {
        return true;
    }

    @LayoutRes
    public abstract int getLayoutId();

    public abstract void initToolBar(Toolbar commonToolbar);

    //竖屏回调
    public void verticalScreen() {

    }

    //横屏回调
    public void horizontalScreen() {

    }

    public int getScreenStatus() {
        /**
         *系统中定义： int ORIENTATION_PORTRAIT = 1;  竖屏
         *系统中定义： int ORIENTATION_LANDSCAPE = 2; 横屏
         */
        //获取屏幕的方向,数值1表示竖屏，数值2表示横屏
        return getResources().getConfiguration().orientation;
    }

    //屏幕方向发生改变的回调方法
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //当前屏幕为横屏
            horizontalScreen();
        } else {
            //当前屏幕为竖屏
            verticalScreen();
        }
        super.onConfigurationChanged(newConfig);
        //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);  //设置横屏
    }

    /**
     * onCreate
     */
    public void onChildCreate(Bundle savedInstanceState) {

    }

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    public abstract void initViews();

    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    public CustomDialog getDialog(CharSequence title) {
        if (dialog == null) {
            dialog = CustomDialog.instance(this);
            dialog.setCancelable(true);
        }
        dialog.setTitle(title);
        return dialog;
    }

    public void hideDialog() {
        if (dialog != null)
            dialog.hide();
    }

    public void showSysLoadingDialog(CharSequence message) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new ProgressDialog(this);
            mLoadingDialog.setCancelable(false);
        }
        mLoadingDialog.setMessage(message);
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    public void showSysLoadingDialog(@StringRes int resId) {
        showSysLoadingDialog(getResources().getString(resId));
    }

    public void showLoadingDialog(CharSequence title) {
        getDialog(title).show();
    }

    public void showLoadingDialog(@StringRes int titleRes) {
        getDialog(getResources().getString(titleRes)).show();
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }

        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    /**
     * 跳转到主界面
     * ----由于MainActivity是singleTask启动模式，所以封装出来单独处理
     *
     * @param pageNum 到主界面后显示第几个Page
     */
    public void startMain(int pageNum) {
        startMain(false, pageNum);
    }

    /**
     * 跳转到主界面
     * ----由于MainActivity是singleTask启动模式，所以封装出来单独处理
     *
     * @param isLogin 是否来自登录界面并且已经登录成功
     */
    public void startMain(boolean isLogin) {
        startMain(isLogin, PAGE_ONE);
    }

    /**
     * 跳转到主界面
     * ----由于MainActivity是singleTask启动模式，所以封装出来单独处理
     *
     * @param isLogin 是否来自登录界面并且已经登录成功
     * @param pageNum 到主界面后显示第几个Page
     */
    public void startMain(boolean isLogin, int pageNum) {
        if (pageNum < PAGE_ONE || pageNum > PAGE_FOUR) {
            pageNum = PAGE_ONE;
        }

        Bundle bundle = new Bundle();
        bundle.putBoolean("isLogin", isLogin);
        bundle.putInt("pageNum", pageNum);
        forward(MainActivity.class, bundle);
    }

    /**
     * 跳转到下一个Activity，并且销毁当前Activity
     *
     * @param cls 下一个Activity的Class
     */
    public void forwardAndFinish(Class<?> cls) {
        forwardAndFinish(cls, null, Constants.ActivityAnimType.DEFAULT);
    }

    public void forwardAndFinish(Class<?> cls, Bundle bundle) {
        forwardAndFinish(cls, bundle, Constants.ActivityAnimType.DEFAULT);
    }

    public void forwardAndFinish(Class<?> cls, Bundle bundle, Constants.ActivityAnimType animType) {
        Intent intent = new Intent(this, cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        executeForwardAnim(animType);
        finish();
    }

    /**
     * 跳转到下一个Activity，不销毁当前Activity
     *
     * @param cls 下一个Activity的Class
     */
    public void forward(Class<?> cls) {
        forward(cls, null, Constants.ActivityAnimType.DEFAULT);
    }

    public void forward(Class<?> cls, Bundle bundle) {
        forward(cls, bundle, Constants.ActivityAnimType.DEFAULT);
    }

    public void forward(Class<?> cls, Bundle bundle, Constants.ActivityAnimType animType) {
        Intent intent = new Intent(this, cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        executeForwardAnim(animType);
    }

    /**
     * 跳转到下一个Activity，并获取返回值
     *
     * @param cls 下一个Activity的Class
     */
    public void forwardForResult(Class<?> cls, int requestCode) {
        forwardForResult(cls, null, requestCode, Constants.ActivityAnimType.DEFAULT);
    }

    public void forwardForResult(Class<?> cls, Bundle bundle, int requestCode) {
        forwardForResult(cls, bundle, requestCode, Constants.ActivityAnimType.DEFAULT);
    }

    public void forwardForResult(Class<?> cls, Bundle bundle, int requestCode, Constants.ActivityAnimType animType) {
        Intent intent = new Intent(this, cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        executeForwardAnim(animType);
    }

    /**
     * 执行跳转到下一个Activity的动画
     */
    public void executeForwardAnim(Constants.ActivityAnimType animType) {
        switch (animType) {
            case DEFAULT:
                overridePendingTransition(R.anim.activity_push_forward_enter, R.anim.activity_push_forward_exit);
                break;
            case DIALOG:
                overridePendingTransition(R.anim.activity_forward_enter, R.anim.activity_forward_exit);
                break;
            case LOGIN:
                overridePendingTransition(R.anim.activity_pop_forward_enter, R.anim.activity_pop_forward_exit);
                break;
            default:
                break;
        }
    }

    /**
     * 执行回到到上一个Activity的动画
     */
    public void executeBackwardAnim(Constants.ActivityAnimType animType) {
        switch (animType) {
            case DEFAULT:
                overridePendingTransition(R.anim.activity_push_backward_enter, R.anim.activity_push_backward_exit);
                break;
            case DIALOG:
                overridePendingTransition(R.anim.activity_backward_enter, R.anim.activity_backward_exit);
                break;
            case LOGIN:
                overridePendingTransition(R.anim.activity_pop_backward_enter, R.anim.activity_pop_backward_exit);
                break;
            default:
                break;
        }
    }

    /**
     * 回到上一个Activity，并销毁当前Activity
     */
    public void backward() {
        finish();
        executeBackwardAnim(Constants.ActivityAnimType.DEFAULT);
    }

    /**
     * 回到上一个Activity，并销毁当前Activity
     */
    public void backward(Constants.ActivityAnimType animType) {
        finish();
        executeBackwardAnim(animType);
    }

    @Override
    protected void onDestroy() {
        mApp.removeActivity(this);
        super.onDestroy();
    }

    @Override
    public void setSelectedFragment(BaseFragment selectedFragment) {
        this.mBaseFragment = selectedFragment;
    }
}
