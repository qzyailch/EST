package com.qian.ess.mvp.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.bean.config.FirstBaseInfo;
import com.qian.ess.bean.config.ts.TSConfig;
import com.qian.ess.bean.config.ui.UINameConfig;
import com.qian.ess.bean.config.user.UserConfig;
import com.qian.ess.bean.config.user.UserMember;
import com.qian.ess.bean.ts.TSObject;
import com.qian.ess.common.Constants;
import com.qian.ess.common.ESSFilePath;
import com.qian.ess.db.DBHelper;
import com.qian.ess.db.action.FirstBaseInfoAction;
import com.qian.ess.db.action.TSObjectAction;
import com.qian.ess.db.action.UINameAction;
import com.qian.ess.db.action.ts.TSConfigAction;
import com.qian.ess.db.action.user.UserConfigAction;
import com.qian.ess.db.action.user.UserMemberAction;
import com.qian.ess.utils.InitFileUtils;
import com.qian.ess.utils.Logger;
import com.qian.ess.utils.SPUtils;
import com.qian.ess.utils.ToastUtils;
import com.qian.ess.utils.thread.ThreadPoolProxyFactory;

import java.util.List;

/**
 * Created by Administrator on 2018/8/6 0006.
 * <p>
 * 启动页
 */

public class SplashActivity extends BaseActivity {

    private final int SPLASH_DISPLAY_LENGHT = 2000; //延迟两秒

    private final int MESSAGE_SAVE_SUCCESS = 1;
    private final int MESSAGE_SAVE_FAIL = 2;

    private Handler mHandler = new Handler();

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {

    }

    @Override
    public void initViews() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFirstUse()) {
                    if (initDB()) {
                        SPUtils.putBoolean(Constants.ACTION_IS_FIRST_USE, false);
                        delayLoad();
                    } else {
                        ToastUtils.show("初始化数据库失败");
                        backward();
                    }
                } else {
                    checkUpdateDB();
                    delayLoad();
                }
            }
        }, SPLASH_DISPLAY_LENGHT);
    }

    /**
     * 判断X5加载完成没有，或者延迟记载
     */
    private void delayLoad() {
        boolean isLoadedX5 = SPUtils.getBoolean("isLoadX5", false);
        if (isLoadedX5) { //已加载X5，直接进首页
            init();
        } else {
            mHandler.postDelayed(mRunnable, 5000); //延迟5秒进首页
        }
    }

    /**
     * 初始化
     */
    private void init() {
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().execute(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                try {
                    //初始化所有基础文件系统
                    InitFileUtils.init();
                    //初始化config文件
                    InitFileUtils.initConfigData(SplashActivity.this);
                    //初始化第一次的文件
                    InitFileUtils.initFirstConfig();
                    //Object，初始化Object的历史文件，当程序卸载或被清除数据，再次进来，则读取文件夹系统里面的历史文件数据
                    InitFileUtils.initObjectFile();
                    //初始化TS相关历史数据
                    InitFileUtils.initTSHistoryData();
                    //初始化PC相关历史数据
                    InitFileUtils.initPCHistoryData();

                    message.what = MESSAGE_SAVE_SUCCESS;
                } catch (Exception e) {
                    e.printStackTrace();
                    message.what = MESSAGE_SAVE_FAIL;
                }
                mInitHandler.sendMessage(message);
            }
        });
    }

    //下一页
    private void next() {
        FirstBaseInfo firstBaseInfo = FirstBaseInfoAction.getFirstBaseInfo();
        if (null == firstBaseInfo) {
            forwardAndFinish(FirstInputActivity.class);
        } else {
            forwardAndFinish(MainActivity.class);
        }
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            init();
        }
    };

    /**
     * do some action
     */
    private Handler mInitHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int msgId = msg.what;
            switch (msgId) {
                case MESSAGE_SAVE_SUCCESS:
                    next();
                    break;
                case MESSAGE_SAVE_FAIL:
                    ToastUtils.show("初始化数据错误");
                    backward();
                    break;
                default:
                    break;
            }
        }
    };

    //初始化数据库
    public boolean initDB() {
        //如果是第一次打开App则初始化数据库
        return DBHelper.getInstance().initDB();
    }

    public void checkUpdateDB() {
        if (DBHelper.getInstance().checkUpdateDB()) {
            DBHelper.getInstance().updateTables();
        }
    }

    @Override
    public boolean isFullScreen() {
        return true;
    }

    //是否第一次打开
    private boolean isFirstUse() {
        return SPUtils.getBoolean(Constants.ACTION_IS_FIRST_USE, true);
    }
}
