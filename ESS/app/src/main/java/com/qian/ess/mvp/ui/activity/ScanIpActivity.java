package com.qian.ess.mvp.ui.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;

import java.lang.ref.WeakReference;
import java.net.InetAddress;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/8/8 0008.
 * <p>
 * 扫描局域网内IP地址
 */

public class ScanIpActivity extends BaseActivity {

    @Bind(R.id.tvPorts)
    TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_ip;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {
        commonToolbar.setTitle("扫描ip地址");
    }

    @Override
    public void initViews() {
        Thread thread = new Thread(new ScanPorts(1, 256, this));
        thread.start();
        Toast.makeText(ScanIpActivity.this, "开始扫描", Toast.LENGTH_LONG).show();
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            //将已经打开的端口号显示在TextView控件上
            textView.append(String.valueOf(msg.what) + ":ok\n");
            Log.i(TAG, String.valueOf(msg.what));
            super.handleMessage(msg);
        }

    };

    class ScanPorts extends Thread {
        private WeakReference<Context> mContextRef;
        private int minPort, maxPort;

        public ScanPorts(int minPort, int maxPort, Context context) {
            this.minPort = minPort;
            this.maxPort = maxPort;
            mContextRef = new WeakReference<>(context);
        }

        @Override
        public void run() {
            try {
                Context context = mContextRef.get();

                if (context != null) {
                    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                    WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                    WifiInfo connectionInfo = wm.getConnectionInfo();
                    int ipAddress = connectionInfo.getIpAddress();
                    String ipString = Formatter.formatIpAddress(ipAddress);

                    Log.i("result", "activeNetwork: " + String.valueOf(activeNetwork));
                    Log.i("result", "ipString: " + String.valueOf(ipString));

                    String prefix = ipString.substring(0, ipString.lastIndexOf(".") + 1);
                    Log.i("result", "prefix: " + prefix);

                    for (int i = 0; i < 255; i++) {
                        String testIp = prefix + String.valueOf(i);

                        InetAddress address = InetAddress.getByName(testIp);
                        boolean reachable = address.isReachable(1000);
                        String hostName = address.getCanonicalHostName();

                        if (reachable)
                            Log.i("result", "Host: " + String.valueOf(hostName) + "(" + String.valueOf(testIp) + ") is reachable!");
                    }
                }
            } catch (Throwable t) {
                Log.i("result", "Well that's not good.", t);
            }
            handler.post(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(ScanIpActivity.this, "扫描完成", Toast.LENGTH_LONG).show();
                }
            });
            super.run();
        }

    }

}
