package com.qian.ess.mvp.ui.activity.task;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.bean.config.FirstBaseInfo;
import com.qian.ess.bean.config.ui.TCUIName;
import com.qian.ess.bean.tc.PCJobObject;
import com.qian.ess.bean.tc.TechninalCard;
import com.qian.ess.db.action.FirstBaseInfoAction;
import com.qian.ess.db.action.PCObjectAction;
import com.qian.ess.db.action.UINameAction;
import com.qian.ess.mvp.ui.adapter.task.TaskStepListAdapter;
import com.qian.ess.utils.DateUtils;
import com.qian.ess.utils.LanguageUtils;
import com.qian.ess.widget.NoScrollListView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/7 0007.
 * <p>
 * 首页--任务--查看任务详情
 */

public class TaskDetailsActivity extends BaseActivity {

    //任务列表
    @Bind(R.id.list_task)
    NoScrollListView listTask;

    //单位
    @Bind(R.id.label_location)
    TextView labelLocation;
    @Bind(R.id.txt_location)
    TextView txtLocation;

    //操作卡片编号
    @Bind(R.id.label_pc_number)
    TextView labelPcNumber;
    @Bind(R.id.txt_pc_number)
    TextView txtPcNumber;

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

    //专业
    @Bind(R.id.label_major)
    TextView labelMajor;
    @Bind(R.id.txt_major)
    TextView txtMajor;

    //操作卡名字
    @Bind(R.id.txt_pc_name)
    TextView txtPcName;

    //主管
    @Bind(R.id.label_director_name)
    TextView labelDirectorName;
    @Bind(R.id.txt_director_name)
    TextView txtDirectorName;

    //发送时间
    @Bind(R.id.label_send_date_time)
    TextView labelSendDateTime;
    @Bind(R.id.txt_send_date)
    TextView txtSendDate;
    @Bind(R.id.txt_send_time)
    TextView txtSendTime;

    //开始时间
    @Bind(R.id.label_begin_date_time)
    TextView labelBeginDateTime;
    @Bind(R.id.txt_begin_date)
    TextView txtBeginDate;
    @Bind(R.id.txt_begin_time)
    TextView txtBeginTime;

    //结束时间
    @Bind(R.id.label_finish_date_time)
    TextView labelFinishDateTime;
    @Bind(R.id.txt_finish_date)
    TextView txtFinishDate;
    @Bind(R.id.txt_finish_time)
    TextView txtFinishTime;

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

    //质控
    @Bind(R.id.label_qc_name)
    TextView labelQcName;
    @Bind(R.id.txt_qc_name)
    TextView txtQcName;

    //编号
    @Bind(R.id.label_number)
    TextView labelNumber;
    //工作内容
    @Bind(R.id.label_work_content)
    TextView labelWorkContent;
    //完成情况
    @Bind(R.id.label_completion)
    TextView labelCompletion;
    //工人
    @Bind(R.id.label_worker)
    TextView labelWorker;
    //审核者
    @Bind(R.id.label_auditor)
    TextView labelAuditor;
    //结果数量
    @Bind(R.id.label_number_of_results)
    TextView labelNumberOfResults;
    //备注
    @Bind(R.id.label_remark)
    TextView labelRemark;
    //图片
    @Bind(R.id.label_picture)
    TextView labelPicture;
    //视频
    @Bind(R.id.label_video)
    TextView labelVideo;
    //音频
    @Bind(R.id.label_audio)
    TextView labelAudio;

    private String fileName;

    private TaskStepListAdapter taskStepListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_task_details;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            commonToolbar.setTitle(bundle.getString("title", ""));

            fileName = bundle.getString("fileName");
        }
    }

    @Override
    public void initViews() {
        TCUIName tcuiName = UINameAction.getTCUINameByLanguage(LanguageUtils.getlanguage());
        init();
        initTableLabel(tcuiName);
        initPCList();
    }

    //初始化表格
    private void initTableLabel(TCUIName tcuiName) {
        if (null != tcuiName) {
            labelLocation.setText(tcuiName.getWorkLocationLabel());
            labelPcNumber.setText(tcuiName.getPcNumberLabel());
            labelObjectModel.setText(tcuiName.getObjectModelLabel());
            labelObjectNumber.setText(tcuiName.getObjectNumberLabel());
            labelMajor.setText(tcuiName.getPcPositionLabel());
            labelDirectorName.setText(tcuiName.getPcPersonInChargeLabel());
            labelSendDateTime.setText(tcuiName.getPcDeliverDateTimeLabel());
            labelBeginDateTime.setText(tcuiName.getPcBeginDateTimeLabel());
            labelFinishDateTime.setText(tcuiName.getPcFinishDateTimeLabel());
            labelPartPosition.setText(tcuiName.getPartPositionLabel());
            labelPartNumber.setText(tcuiName.getPartNumberLabel());
            labelQcName.setText(tcuiName.getPcQCLabel());
            labelNumber.setText(tcuiName.getPcTCNumberLabel());
            labelWorkContent.setText(tcuiName.getPcTCsLabel());
            labelCompletion.setText(tcuiName.getPcCompleteLabel());
            labelWorker.setText(tcuiName.getPcWorkerNameLabel());
            labelAuditor.setText(tcuiName.getPcAuditorLabel());
            labelNumberOfResults.setText(tcuiName.getPcQuantityLabel());
            labelRemark.setText(tcuiName.getRemarkLabel());
            labelPicture.setText(tcuiName.getPictureLabel());
            labelVideo.setText(tcuiName.getVideoLabel());
            labelAudio.setText(tcuiName.getAudioLabel());
        }
    }

    //初始化表格的内容
    private void init() {
        PCJobObject pcJobObject = PCObjectAction.getPCJobObjectByFileName(fileName);
        if (null != pcJobObject) {
            txtLocation.setText(pcJobObject.getBaseLocation());
            txtPcNumber.setText(pcJobObject.getProcessCard().getNumber());
            txtObjectNumber.setText(pcJobObject.getAircraftNumber());
            txtMajor.setText(pcJobObject.getMajor());
            txtPcName.setText(pcJobObject.getProcessCard().getName());
            txtDirectorName.setText(pcJobObject.getDirectorName());

            String pcSentDateTime = pcJobObject.getSentDateTime(); //交付时间
            String pcBeginDateTime = pcJobObject.getBeginDateTime(); //开始时间
            String pcFinishDateTime = pcJobObject.getFinishDateTime(); //结束时间

            if (!TextUtils.isEmpty(pcSentDateTime)) {
                //TODO 发送时间
                String formatSentDateTime = DateUtils.dateToString(DateUtils.stringToDate(pcSentDateTime, DateUtils.FORMAT_LONG_WITHOUT), DateUtils.FORMAT_LONG_NOT_SECOND);
                txtSendDate.setText(formatSentDateTime.split(" ")[0]);
                txtSendTime.setText(formatSentDateTime.split(" ")[1]);
            }
            if (!TextUtils.isEmpty(pcBeginDateTime)) {
                //TODO 开始时间
                String formatBeginDateTime = DateUtils.dateToString(DateUtils.stringToDate(pcBeginDateTime, DateUtils.FORMAT_LONG_WITHOUT), DateUtils.FORMAT_LONG_NOT_SECOND);
                txtBeginDate.setText(formatBeginDateTime.split(" ")[0]);
                txtBeginTime.setText(formatBeginDateTime.split(" ")[1]);
            }
            if (!TextUtils.isEmpty(pcFinishDateTime)) {
                //TODO 结束时间
                String formatFinishDateTime = DateUtils.dateToString(DateUtils.stringToDate(pcFinishDateTime, DateUtils.FORMAT_LONG_WITHOUT), DateUtils.FORMAT_LONG_NOT_SECOND);
                txtFinishDate.setText(formatFinishDateTime.split(" ")[0]);
                txtFinishTime.setText(formatFinishDateTime.split(" ")[1]);
            }

            txtPartPosition.setText(pcJobObject.getEnginePosition());
            txtPartNumber.setText(pcJobObject.getEngineNumber());
            txtQcName.setText(pcJobObject.getQcName());
        }

        FirstBaseInfo firstBaseInfo = FirstBaseInfoAction.getFirstBaseInfo();
        if (null != firstBaseInfo) {
            //TODO 对象型号
            txtObjectModel.setText(firstBaseInfo.getObjectName());
        }
    }

    //初始化TC列表，技术卡片列表
    private void initPCList() {
        taskStepListAdapter = new TaskStepListAdapter(this);
        taskStepListAdapter.setOnItemClickListenet(new TaskStepListAdapter.OnItemClickListenet() {
            @Override
            public void onTCClick(TechninalCard techninalCard) {

            }

            @Override
            public void onWorkerNameClick(TechninalCard techninalCard) {

            }

            @Override
            public void onReviewerNameClick(TechninalCard techninalCard) {

            }
        });
        listTask.setAdapter(taskStepListAdapter);
        listTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TechninalCard tc = taskStepListAdapter.getArray().get(position);
                Bundle bundle = getIntent().getExtras();
                if (null != bundle) {
                    String title = bundle.getString("title", "") + "/" + tc.getName();
                    bundle.putString("title", title);
                }
                forward(BeginTaskActivity.class, bundle);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<TechninalCard> list = PCObjectAction.getAllTechninalCardByFileName(fileName);
        taskStepListAdapter.setAllArray(list);
        taskStepListAdapter.notifyDataSetChanged();
    }

    @OnClick({
            R.id.txt_btn_cancel,
            R.id.txt_btn_begin
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_btn_cancel:
                backward();
                break;
            case R.id.txt_btn_begin:
                backward();
                break;
            default:
                break;
        }
    }
}
