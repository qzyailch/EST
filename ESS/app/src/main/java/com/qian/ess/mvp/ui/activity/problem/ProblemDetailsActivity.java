package com.qian.ess.mvp.ui.activity.problem;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.bean.config.ts.TSInfo;
import com.qian.ess.bean.config.ts.TSLabel;
import com.qian.ess.bean.config.ui.TSUIName;
import com.qian.ess.bean.config.user.UserMember;
import com.qian.ess.bean.ts.TSObject;
import com.qian.ess.bean.ts.TSSolution;
import com.qian.ess.common.Constants;
import com.qian.ess.common.ESSFilePath;
import com.qian.ess.db.action.TSObjectAction;
import com.qian.ess.db.action.UINameAction;
import com.qian.ess.db.action.ts.TSConfigAction;
import com.qian.ess.db.action.user.UserMemberAction;
import com.qian.ess.mvp.ui.adapter.problem.ProblemStepListAdapter;
import com.qian.ess.utils.DateUtils;
import com.qian.ess.utils.InitFileUtils;
import com.qian.ess.utils.LanguageUtils;
import com.qian.ess.utils.ToastUtils;
import com.qian.ess.utils.ZipUtils;
import com.qian.ess.utils.thread.ThreadPoolProxyFactory;
import com.qian.ess.widget.NoScrollListView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/7 0007.
 * <p>
 * 首页--问题--查看问题详情
 */

public class ProblemDetailsActivity extends BaseActivity {

    //自定义排故方法名
    private static final int ADD_CUSTOM_FUN = 520;

    @Bind(R.id.list_problem)
    NoScrollListView listProblem;

    //地点
    @Bind(R.id.label_address)
    TextView labelAddress;
    @Bind(R.id.txt_address)
    TextView txtAddress;

    //排故编号
    @Bind(R.id.label_ts_number)
    TextView labelTsNumber;
    @Bind(R.id.txt_ts_number)
    TextView txtTsNumber;

    //对象型号
    @Bind(R.id.label_object_model)
    TextView labelObjectModel;
    @Bind(R.id.txt_object_model)
    TextView txtObjectModel;

    //对象编号
    @Bind(R.id.label_object_number)
    TextView labelObjectNumber;
    @Bind(R.id.txt_object_number)
    TextView txtObjectNumber;

    //故障位置
    @Bind(R.id.label_ts_position)
    TextView labelTsPosition;
    @Bind(R.id.txt_ts_position)
    TextView txtTsPosition;

    //简称
    @Bind(R.id.txt_ts_short_name)
    TextView txtTsShortName;
    //全称
    @Bind(R.id.txt_ts_full_name)
    TextView txtTsFullName;

    //主管
    @Bind(R.id.label_ts_person_in_charge)
    TextView labelTsPersonInCharge;
    @Bind(R.id.txt_ts_person_in_charge)
    TextView txtTsPersonInCharge;

    //交付日期
    @Bind(R.id.label_ts_deliver_date_time)
    TextView labelTsDeliverDateTime;
    @Bind(R.id.txt_ts_deliver_date)
    TextView txtTsDeliverDate;
    @Bind(R.id.txt_ts_deliver_time)
    TextView txtTsDeliverTime;

    //开始日期
    @Bind(R.id.label_ts_begin_date_dime)
    TextView labelTsBeginDateDime;
    @Bind(R.id.txt_ts_begin_date)
    TextView txtTsBeginDate;
    @Bind(R.id.txt_ts_begin_time)
    TextView txtTsBeginTime;

    //部件位置
    @Bind(R.id.label_part_position)
    TextView labelPartPosition;
    @Bind(R.id.txt_part_position)
    TextView txtPartPosition;

    //部件编号
    @Bind(R.id.label_part_number)
    TextView labelPartNumber;
    @Bind(R.id.txt_part_number)
    TextView txtPartNumber;

    //操作者
    @Bind(R.id.label_ts_worker_name)
    TextView labelTsWorkerName;
    @Bind(R.id.txt_ts_worker_name)
    TextView txtTsWorkerName;

    //审核者
    @Bind(R.id.label_ts_auditor)
    TextView labelTsAuditor;
    @Bind(R.id.txt_ts_auditor)
    TextView txtTsAuditor;

    //完成时间
    @Bind(R.id.label_ts_finish_date_time)
    TextView labelTsFinishDateTime;
    @Bind(R.id.txt_ts_finish_date)
    TextView txtTsFinishDate;
    @Bind(R.id.txt_ts_finish_time)
    TextView txtTsFinishTime;

    //编号
    @Bind(R.id.label_ts_solution_number)
    TextView labelTsSolutionNumber;
    //排故方法
    @Bind(R.id.label_ts_solutions)
    TextView labelTsSolutions;
    //可能的原因
    @Bind(R.id.label_ts_possible_reason)
    TextView labelTsPossibleReason;
    //真实的原因
    @Bind(R.id.label_real_reason)
    TextView labelRealReason;
    //使用的方法（+/-)
    @Bind(R.id.label_ts_is_used)
    TextView labelTsIsUsed;
    //结果（+/-）
    @Bind(R.id.label_ts_is_work)
    TextView labelTsIsWork;
    //备注
    @Bind(R.id.label_remark)
    TextView labelRemark;
    //照片
    @Bind(R.id.label_picture)
    TextView labelPicture;
    //视频
    @Bind(R.id.label_video)
    TextView labelVideo;
    //音频
    @Bind(R.id.label_audio)
    TextView labelAudio;

    //真实原因
    @Bind(R.id.txt_true_reason)
    TextView txtTrueReason;
    //备注
    @Bind(R.id.txt_remark)
    TextView txtRemark;
    //图片个数
    @Bind(R.id.txt_picture)
    TextView txtPictureSize;
    //视频个数
    @Bind(R.id.txt_video)
    TextView txtVideoSize;
    //音频个数
    @Bind(R.id.txt_audio)
    TextView txtAudioSize;

    //添加自定义排故方法
    @Bind(R.id.txt_btn_add_custom_fun)
    TextView txtAddCustomFun;

    //TsObjectId
    private String tsId;
    //文件名
    private String fileName;
    //TsInfo，说明书页码相关
    private TSInfo tsInfo;

    //对象编号
    private String objectNumber;
    //部件编号
    private String partNumber;

    private ProblemStepListAdapter problemStepListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_problem_details;
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
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            tsId = bundle.getString("tsId");
            tsInfo = (TSInfo) bundle.getSerializable("tsInfo");
        }

        String language = LanguageUtils.getlanguage();
        TSLabel tsLabel = TSConfigAction.getTSLabelByLanguage(language);
        if (null == tsLabel) {
            tsLabel = new TSLabel();
        }
        TSUIName tsuiName = UINameAction.getTSUINameByLanguage(language);

        initTableLabel(tsuiName);
        initTSSolutionList(tsLabel);

        txtAddCustomFun.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
    }

    //初始化内容
    private void init(List<TSSolution> list) {
        TSObject tsObject = TSObjectAction.getTSObjectByTsId(tsId);

        if (null != tsObject) {
            fileName = tsObject.getFileName(); //文件名
            String workLocation = tsObject.getWorkLocation(); //地址
            String tsShortName = tsObject.getTsShortName(); //简称
            String tsFullName = tsObject.getTsFullName(); //全称
            String objectModel = tsObject.getObjectModel(); //对象型号
            objectNumber = tsObject.getObjectNumber(); //对象编号
            String tsPosition = tsObject.getTsPosition(); //故障位置
            String partPosition = tsObject.getPartPosition(); //部件位置
            partNumber = tsObject.getPartNumber(); //部件编号
            String tsNumber = tsObject.getTsNumber(); //排故编号
            String tsDeliverDateTime = tsObject.getTsDeliverDateTime(); //交付时间
            String tsBeginDateTime = tsObject.getTsBeginDateTime(); //开始时间
            String tsFinishDateTime = tsObject.getTsFinishDateTime(); //结束时间

            String tsPersonInCharge = tsObject.getTsPersonInCharge(); //主管
            String tsWorkerName = tsObject.getTsWorkerName(); //操作者
            String tsAuditor = tsObject.getTsAuditor(); //审核者

            String realReason = tsObject.getRealReason(); //真实原因
            String remark = tsObject.getRemark(); //备注
            int pictureListSize = tsObject.getPictureList().size(); //图片个数
            int videoListSize = tsObject.getVideoList().size(); //视频个数
            int audioListSize = tsObject.getAudioList().size(); //音频个数

            txtAddCustomFun.setVisibility(tsObject.getIsAddCustomFun() ? View.GONE : View.VISIBLE);

            txtAddress.setText(TextUtils.isEmpty(workLocation) ? "" : workLocation);
            txtTsShortName.setText(TextUtils.isEmpty(tsShortName) ? "" : tsShortName);
            txtTsFullName.setText(TextUtils.isEmpty(tsFullName) ? "" : tsFullName);
            txtObjectModel.setText(TextUtils.isEmpty(objectModel) ? "" : objectModel);
            txtObjectNumber.setText(TextUtils.isEmpty(objectNumber) ? "" : objectNumber);
            txtTsPosition.setText(TextUtils.isEmpty(tsPosition) ? "" : tsPosition);
            txtPartPosition.setText(TextUtils.isEmpty(partPosition) ? "" : partPosition);
            txtPartNumber.setText(TextUtils.isEmpty(partNumber) ? "" : partNumber);
            txtTsNumber.setText(TextUtils.isEmpty(tsNumber) ? "" : tsNumber);

            txtTsPersonInCharge.setText(TextUtils.isEmpty(tsPersonInCharge) ? "" : tsPersonInCharge);
            txtTsWorkerName.setText(TextUtils.isEmpty(tsWorkerName) ? "" : tsWorkerName);
            txtTsAuditor.setText(TextUtils.isEmpty(tsAuditor) ? "" : tsAuditor);

            txtTrueReason.setText(TextUtils.isEmpty(realReason) ? "" : realReason);
            txtPictureSize.setText(pictureListSize == 0 ? "-" : pictureListSize + "");
            txtVideoSize.setText(videoListSize == 0 ? "-" : videoListSize + "");
            txtAudioSize.setText(audioListSize == 0 ? "-" : audioListSize + "");

            if (!TextUtils.isEmpty(tsDeliverDateTime)) {
                String formatDeliverDateTime = DateUtils.dateToString(DateUtils.stringToDate(tsDeliverDateTime, DateUtils.FORMAT_LONG_WITHOUT), DateUtils.FORMAT_LONG_NOT_SECOND);
                txtTsDeliverDate.setText(formatDeliverDateTime.split(" ")[0]);
                txtTsDeliverTime.setText(formatDeliverDateTime.split(" ")[1]);
            }

            if (!TextUtils.isEmpty(tsBeginDateTime)) {
                String formatBeginDateTime = DateUtils.dateToString(DateUtils.stringToDate(tsBeginDateTime, DateUtils.FORMAT_LONG_WITHOUT), DateUtils.FORMAT_LONG_NOT_SECOND);
                txtTsBeginDate.setText(formatBeginDateTime.split(" ")[0]);
                txtTsBeginTime.setText(formatBeginDateTime.split(" ")[1]);
            }

            if (!TextUtils.isEmpty(tsFinishDateTime)) {
                String formatFinishDateTime = DateUtils.dateToString(DateUtils.stringToDate(tsFinishDateTime, DateUtils.FORMAT_LONG_WITHOUT), DateUtils.FORMAT_LONG_NOT_SECOND);
                txtTsFinishDate.setText(formatFinishDateTime.split(" ")[0]);
                txtTsFinishTime.setText(formatFinishDateTime.split(" ")[1]);
            }

            String showRemark = TSObjectAction.getShowRemark(tsId, list);
            txtRemark.setText(TextUtils.isEmpty(showRemark) ? "-" : showRemark);
        }
    }

    //初始化表格
    private void initTableLabel(TSUIName tsuiName) {
        if (null != tsuiName) {
            labelAddress.setText(tsuiName.getWorkLocationLabel());
            labelTsNumber.setText(tsuiName.getTsNumberLabel());
            labelObjectModel.setText(tsuiName.getObjectModelLabel());
            labelObjectNumber.setText(tsuiName.getObjectNumberLabel());
            labelTsPosition.setText(tsuiName.getTsPositionLabel());
            labelTsPersonInCharge.setText(tsuiName.getTsPersonInChargeLabel());
            labelTsDeliverDateTime.setText(tsuiName.getTsDeliverDateTimeLabel());
            labelTsBeginDateDime.setText(tsuiName.getTsBeginDateTimeLabel());
            labelPartPosition.setText(tsuiName.getPartPositionLabel());
            labelPartNumber.setText(tsuiName.getPartNumberLabel());
            labelTsWorkerName.setText(tsuiName.getTsWorkerNameLabel());
            labelTsAuditor.setText(tsuiName.getTsAuditorLabel());
            labelTsFinishDateTime.setText(tsuiName.getTsFinishDateTimeLabel());
            labelTsSolutionNumber.setText(tsuiName.getTsSolutionNumberLabel());
            labelTsSolutions.setText(tsuiName.getTsSolutionsLabel());
            labelTsPossibleReason.setText(tsuiName.getTsPossibleReasonLabel());
            labelRealReason.setText(tsuiName.getRealReasonLabel());
            labelTsIsUsed.setText(tsuiName.getTsIsUsedLabel());
            labelTsIsWork.setText(tsuiName.getTsIsWorkLabel());
            labelRemark.setText(tsuiName.getRemarkLabel());
            labelPicture.setText(tsuiName.getPictureLabel());
            labelVideo.setText(tsuiName.getVideoLabel());
            labelAudio.setText(tsuiName.getAudioLabel());
        }
    }

    //初始化TSSolution列表
    private void initTSSolutionList(TSLabel tsLabel) {
        problemStepListAdapter = new ProblemStepListAdapter(this, tsLabel);
        problemStepListAdapter.setOnSolutionClickListener(new ProblemStepListAdapter.OnSolutionClickListener() {
            @Override
            public void onSolutionClick(TSSolution tsSolution) {
                Bundle bundle = getIntent().getExtras();

                if (null != bundle) {
                    TSLabel tsLabel = TSConfigAction.getTSLabelByLanguage(LanguageUtils.getlanguage());
                    if (null == tsLabel) {
                        tsLabel = new TSLabel();
                    }
                    String title = bundle.getString("title", "");
                    switch (tsSolution.getTsSolutionNumber()) {
                        case 1:
                            title += "/" + tsLabel.getTroubleshootingWayEM();
                            break;
                        case 2:
                            title += "/" + tsLabel.getTroubleshootingWayAEM();
                            break;
                        case 3:
                            title += "/" + tsLabel.getTroubleshootingWayTSEM();
                            break;
                        default:
                            title += "/" + tsSolution.getTsSolutionName();
                            break;
                    }
                    bundle.putString("title", title);
                    bundle.putString("tsId", tsId);
                    bundle.putString("fileName", fileName); //文件名
                    bundle.putBoolean("isUse", tsSolution.getIsUsed()); //是否使用
                    bundle.putBoolean("isWork", tsSolution.getIsWork()); //是否起作用
                    bundle.putString("tsSolutionID", tsSolution.getTsSolutionID()); //当前列表项的Id
                    bundle.putString("tsPosition", txtTsPosition.getText().toString()); //故障位置
                    bundle.putString("realReason", txtTrueReason.getText().toString()); //真实原因
                    bundle.putString("possibleReason", tsSolution.getPossibleReason()); //可能原因
                    bundle.putString("tsSolution", tsSolution.getTsSolution()); //解决办法
                    bundle.putString("tsDescription", tsSolution.getTsDescription()); //故障描述
                    bundle.putString("objectNumber", objectNumber); //对象编号
                    bundle.putString("partNumber", partNumber); //部件编号
                    bundle.putInt("tsSolutionNumber", tsSolution.getTsSolutionNumber()); //编号，根据编号来判断是哪一个说明书，约定1，2，3代表EM,AEM,TSEM
                    bundle.putSerializable("tsInfo", tsInfo); //TSInfo
                }
                forward(BeginProblemActivity.class, bundle);
            }
        });
        listProblem.setAdapter(problemStepListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<TSSolution> list = TSObjectAction.getAllTSSolutionByTsId(tsId);
        problemStepListAdapter.setAllArray(list);
        problemStepListAdapter.notifyDataSetChanged();
        init(list);
    }

    @OnClick({
            R.id.txt_ts_worker_name,
            R.id.txt_ts_auditor,
            R.id.txt_ts_person_in_charge,
            R.id.txt_btn_add_custom_fun,
            R.id.txt_btn_cancel,
            R.id.txt_btn_begin
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_ts_worker_name:
                //操作者
                selectUser(3);
                break;
            case R.id.txt_ts_auditor:
                //审核者
                selectUser(2);
                break;
            case R.id.txt_ts_person_in_charge:
                //主管
                selectUser(1);
                break;
            case R.id.txt_btn_add_custom_fun:
                //添加自定义排故方法
                addCustomFun();
                break;
            case R.id.txt_btn_cancel:
                backward();
                break;
            case R.id.txt_btn_begin:
                send();
                break;
            default:
                break;
        }
    }

    //发送
    private void send() {
        showLoadingDialog(R.string.sen_file_zip);
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    createTSXml();
                    String src = ZipUtils.ZipFolder(InitFileUtils.getTSFilePath(objectNumber, partNumber) + "/" + fileName
                            , ESSFilePath.RECEIVE_FILE + "/" + fileName + ".zip");

                    sendFile(src);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //发送文件
    private void sendFile(String filePath) {
        Intent intent = new Intent();
        intent.putExtra("filePath", filePath);
        intent.setAction(Constants.ESSBroadcast.SEND_MESSAGE_OR_FILE_ACTION);
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().execute(new Runnable() {
            @Override
            public void run() {
                createTSXml();
            }
        });
    }

    /**
     * 创建TS的xml文件
     */
    private void createTSXml() {
        try {
            TSObject tsObject = TSObjectAction.getTSObjectByTsId(tsId);
            List<TSSolution> list = TSObjectAction.getAllTSSolutionByTsId(tsId);
            tsObject.setTsSolutions(list);

            TSObject.writeToXml(tsObject, InitFileUtils.getTSFilePath(objectNumber, partNumber) + "/" + fileName + "/" + fileName + ".xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加自定义排故方法
    private void addCustomFun() {
        Bundle bundle = getIntent().getExtras();
        if (null == bundle) {
            bundle = new Bundle();
        }
        bundle.putString("title", bundle.getString("title", ""));
        bundle.putString("tsId", tsId);
        bundle.putString("fileName", fileName);
        bundle.putString("objectNumber", objectNumber);
        bundle.putString("partNumber", partNumber);
        bundle.putString("tsPosition", txtTsPosition.getText().toString());
        forwardForResult(AddCustomFunActivity.class, bundle, ADD_CUSTOM_FUN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_CUSTOM_FUN:
                    txtAddCustomFun.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 选择用户
     *
     * @param userLevel 1：主管 2：审核者 3：操作者 4：质控员
     */
    private void selectUser(int userLevel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final List<UserMember> userMembers = UserMemberAction.queryAllUserMember(userLevel);
        if (null == userMembers || userMembers.size() == 0) {
            ToastUtils.show("Not Found");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0, len = userMembers.size(); i < len; i++) {
            stringBuffer.append(",").append(userMembers.get(i).getUserName());
        }
        //设置一个下拉的列表选择项
        builder.setItems(stringBuffer.toString().substring(1).split(","), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserMember userMember = userMembers.get(which);
                inputAuditorPassword(userMember);
            }
        });
        builder.show();
    }

    //输入审核者密码
    private void inputAuditorPassword(final UserMember userMember) {
        final EditText editText = new EditText(this);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(this);
        inputDialog.setTitle(R.string.input_password).setView(editText);
        inputDialog.setPositiveButton(R.string.ess_btn_sure,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (editText.getText().toString().equals(userMember.getUserPassword())) {
                            switch (userMember.getUserLevelId()) {
                                case 1:
                                    txtTsPersonInCharge.setText(userMember.getUserName());
                                    TSObjectAction.updateTSObjectPersonInCharge(tsId, userMember.getUserName());
                                    break;
                                case 2:
                                    txtTsAuditor.setText(userMember.getUserName());
                                    TSObjectAction.updateTSObjectAuditor(tsId, userMember.getUserName());
                                    break;
                                case 3:
                                    txtTsWorkerName.setText(userMember.getUserName());
                                    TSObjectAction.updateTSObjectWorker(tsId, userMember.getUserName());
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            ToastUtils.show(R.string.password_fail);
                        }
                    }
                }).show();
    }
}
