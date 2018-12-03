package com.qian.ess.mvp.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.bean.config.FirstBaseInfo;
import com.qian.ess.bean.config.ui.UIName;
import com.qian.ess.common.ESSFilePath;
import com.qian.ess.db.action.FirstBaseInfoAction;
import com.qian.ess.db.action.UINameAction;
import com.qian.ess.utils.InitFileUtils;
import com.qian.ess.utils.LanguageUtils;
import com.qian.ess.utils.ToastUtils;
import com.qian.ess.utils.thread.ThreadPoolProxyFactory;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/11/13 0013.
 * <p>
 * 第一次进入需要填写一些东西
 */

public class FirstInputActivity extends BaseActivity {

    private final int MESSAGE_SAVE_SUCCESS = 1;
    private final int MESSAGE_SAVE_FAIL = 2;

    //位置
    @Bind(R.id.et_address)
    EditText etAddress;
    @Bind(R.id.txt_address)
    TextView txtAddress;

    //对象名
    @Bind(R.id.et_object_name)
    EditText etObjectName;
    @Bind(R.id.txt_object_name)
    TextView txtObjectName;

    //对象编号
    @Bind(R.id.et_object_num)
    EditText etObjectNum;
    @Bind(R.id.txt_object_num)
    TextView txtObjectNum;

    //部件名
    @Bind(R.id.et_component_name)
    EditText etComponentName;
    @Bind(R.id.txt_component_name)
    TextView txtComponentName;

    //部件1编号
    @Bind(R.id.et_component_num1)
    EditText etComponentNum1;
    @Bind(R.id.txt_component_num1)
    TextView txtComponentNum1;

    //部件2编号
    @Bind(R.id.et_component_num2)
    EditText etComponentNum2;
    @Bind(R.id.txt_component_num2)
    TextView txtComponentNum2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_first_input;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {
        commonToolbar.setTitle(R.string.base_info);
    }

    @Override
    public void initViews() {
        UIName uiName = UINameAction.getUINameByLanguage(LanguageUtils.getlanguage());
        if (null != uiName) {
            txtAddress.setText(uiName.getBase());
            txtObjectName.setText(uiName.getOName());
            txtObjectNum.setText(uiName.getNoO());
            txtComponentName.setText(uiName.getMName());
            txtComponentNum1.setText(uiName.getNoM1());
            txtComponentNum2.setText(uiName.getNoM2());
        }
    }

    @OnClick(R.id.txt_btn_sure)
    public void onViewClicked() {
        createXml();
    }

    //生成xml文件
    private void createXml() {
        String address = etAddress.getText().toString();
        String objectName = etObjectName.getText().toString();
        String objectNum = etObjectNum.getText().toString();
        String componentName = etComponentName.getText().toString();
        String componentNum1 = etComponentNum1.getText().toString();
        String componentNum2 = etComponentNum2.getText().toString();

        if (TextUtils.isEmpty(address)) {
            ToastUtils.show(R.string.please_input_geographical_position);
            return;
        }
        if (TextUtils.isEmpty(objectName)) {
            ToastUtils.show(R.string.please_input_object_name);
            return;
        }
        if (TextUtils.isEmpty(objectNum)) {
            ToastUtils.show(R.string.please_object_num);
            return;
        }
        if (TextUtils.isEmpty(componentName)) {
            ToastUtils.show(R.string.please_component_name);
            return;
        }
        if (TextUtils.isEmpty(componentNum1)) {
            ToastUtils.show(R.string.please_component1_num);
            return;
        }
        if (TextUtils.isEmpty(componentNum2)) {
            ToastUtils.show(R.string.please_component2_num);
            return;
        }

        FirstBaseInfo firstBaseInfo = new FirstBaseInfo();
        firstBaseInfo.setAddress(address);
        firstBaseInfo.setObjectName(objectName);
        firstBaseInfo.setObjectNum(objectNum);
        firstBaseInfo.setComponentName(componentName);
        firstBaseInfo.setComponentNum1(componentNum1);
        firstBaseInfo.setComponentNum2(componentNum2);
        FirstBaseInfo.writeToXml(firstBaseInfo, ESSFilePath.CONFIG_FILE + "/Config-Default-Settings.config");
        FirstBaseInfoAction.addOrUpdateFirstBaseInfo(firstBaseInfo);

        createObject();
    }

    private void createObject() {
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().execute(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                try {
                    //初始化Object的文件
                    InitFileUtils.initObjectFile();
                    message.what = MESSAGE_SAVE_SUCCESS;
                } catch (Exception e) {
                    e.printStackTrace();
                    message.what = MESSAGE_SAVE_FAIL;
                }
                mInitHandler.sendMessage(message);
            }
        });
    }

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
                    forwardAndFinish(MainActivity.class);
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
}
