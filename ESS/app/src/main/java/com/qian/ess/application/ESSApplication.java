package com.qian.ess.application;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.view.WindowManager;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.utils.Logger;
import com.qian.ess.utils.SPUtils;
import com.qian.ess.utils.ToastUtils;
import com.tencent.smtt.sdk.QbSdk;

import java.util.LinkedList;

/**
 * Created by Administrator on 2016/8/26 0026.
 * <p>
 * application
 */
public class ESSApplication extends Application {

    private static final String TAG = ESSApplication.class.getSimpleName();
    private static Context mContext;
    private Handler mHandler;
    private static ESSApplication mInstance;
    private long mLastPressBackKeyTime;

    private LinkedList<BaseActivity> mActivities = new LinkedList<BaseActivity>();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = ESSApplication.this;
        mInstance = this;
        initImageLoader(this);
        ESSCrashHandler.getInstance().init(this);

        initTencentWebview();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static ESSApplication getInstance() {
        return mInstance;
    }

    /**
     * 初始化腾讯X5内核
     */
    private void initTencentWebview() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Logger.i("result", "腾讯X5内核onViewInitFinished is " + arg0);
                //X5内核已加载完成
                SPUtils.putBoolean("isLoadX5", true);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    /**
     * ImageLoader 图片组件初始化
     *
     * @param context
     */
    public void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
        ImageLoader.getInstance().init(config);
    }

    public void addActivity(BaseActivity activity) {
        mActivities.add(activity);
    }

    public void removeActivity(BaseActivity activity) {
        mActivities.remove(activity);
    }

    public LinkedList<BaseActivity> getActivities() {
        return mActivities;
    }

    /**
     * 双击后完全退出应用程序
     */
    public void exitWithDoubleClick() {
        if (System.currentTimeMillis() - mLastPressBackKeyTime <= 1500) {
            exit();
        } else {
            mLastPressBackKeyTime = System.currentTimeMillis();
            ToastUtils.show("再按一次退出");
        }
    }

    /**
     * 退出应用程序
     */
    public void exit() {
        BaseActivity activity;
        while (mActivities.size() != 0) {
            activity = mActivities.poll();
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.gc();
        System.exit(0);
    }

    /**
     * 获取当前版本名称
     *
     * @return
     */
    public String getCurrentVersionName() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取手机厂商+型号
     */
    public String getPhoneInfo() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取屏幕高度与宽度
     */
    public int[] getWindowHW() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return new int[]{width, height};
    }
}
