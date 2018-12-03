package com.qian.ess.mvp.ui.activity.dialog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.LinearLayout;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.common.Constants;
import com.qian.ess.utils.Logger;

/**
 * Created by Administrator on 2018/11/12 0012.
 * <p>
 * 通过service显示dialog
 */

public class LoadingDialogActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_loading_dialog;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {

    }

    @Override
    public void initViews() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        registerReceiver();
    }

    /**
     * 注册网络状态监听的广播
     */
    private void registerReceiver() {
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
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
                showSysLoadingDialog("正在接收文件：" + intent.getStringExtra("process"));
            } else if (action.equals(Constants.ESSBroadcast.DISMISS_DIALOG_ACTION)) {
                backward();
            }
        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver();
        super.onDestroy();
    }
}
