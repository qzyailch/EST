package com.qian.ess.mvp.ui.activity.dialog;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.common.Constants;
import com.qian.ess.service.SocketService;
import com.qian.ess.utils.NetworkUtils;
import com.qian.ess.utils.SPUtils;
import com.qian.ess.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/11/12 0012.
 * <p>
 * 设置，填写ip和port
 */

public class SettingDialogActivity extends BaseActivity {

    @Bind(R.id.et_ip)
    EditText etIp;
    @Bind(R.id.et_port)
    EditText etPort;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting_dialog;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {

    }

    @Override
    public void initViews() {
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        String ip = SPUtils.getString("ip");
        String port = SPUtils.getString("port");
        if (!TextUtils.isEmpty(ip)) {
            etIp.setText(ip);
        }
        if (!TextUtils.isEmpty(port)) {
            etPort.setText(port);
        }
    }

    @OnClick({
            R.id.txt_btn_cancel,
            R.id.txt_btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_btn_sure:
                if (saveIp()) {
//                    if (!etIp.getText().toString().equals(SPUtils.getString("ip"))
//                            || !etPort.getText().toString().equals(SPUtils.getString("port"))) {
//
//                        SPUtils.putString("ip", etIp.getText().toString());
//                        SPUtils.putString("port", etPort.getText().toString());
//
//                        if (NetworkUtils.isConnected(this)) {
//                            Intent intent = new Intent(this, SocketService.class);
//                            startService(intent);
//                        }
//                    }
                    SPUtils.putString("ip", etIp.getText().toString());
                    SPUtils.putString("port", etPort.getText().toString());

                    if (NetworkUtils.isConnected(this)) {
                        Intent intent = new Intent(this, SocketService.class);
                        startService(intent);
                    }
                    backward();
                }
                break;
            case R.id.txt_btn_cancel:
                backward();
                break;
            default:
                break;
        }
    }

    private boolean saveIp() {
        String ip = etIp.getText().toString();
        String port = etPort.getText().toString();
        if (TextUtils.isEmpty(ip)) {
            ToastUtils.show(R.string.please_input_ip);
            return false;
        }

        if (TextUtils.isEmpty(port)) {
            ToastUtils.show(R.string.please_input_port);
            return false;
        }
        return true;
    }

    @Override
    public void backward() {
        super.backward(Constants.ActivityAnimType.DIALOG);
    }
}
