package com.qian.ess.mvp.ui.activity.problem;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.bean.config.ts.TSInfo;
import com.qian.ess.bean.config.ui.TSUIName;
import com.qian.ess.camera.JCameraView;
import com.qian.ess.db.action.TSObjectAction;
import com.qian.ess.db.action.UINameAction;
import com.qian.ess.mvp.ui.activity.camera.CameraActivity;
import com.qian.ess.mvp.ui.activity.camera.EditImageActivity;
import com.qian.ess.mvp.ui.fragment.dialog.RecordAudioDialogFragment;
import com.qian.ess.utils.DateUtils;
import com.qian.ess.utils.InitFileUtils;
import com.qian.ess.utils.LanguageUtils;
import com.qian.ess.utils.Logger;
import com.qian.ess.utils.ToastUtils;
import com.qian.ess.widget.ToggleButton;

import java.io.File;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/7 0007.
 * <p>
 * 开始解决问题
 */

public class BeginProblemActivity extends BaseActivity {

    //编辑文本
//    private static final int TEXT_CODE = 520;
    //编辑图片
    private static final int PIC_CODE = 522;
    private static final int EDIT_IMAGE_CODE = 526;
    //填写真实原因
    private static final int REASON_CODE = 528;
    //故障解决办法
    private static final int SOLUTION_CODE = 530;
    //故障描述
    private static final int DESC_CODE = 532;
    //故障位置
    private static final int POSITION_CODE = 536;
    private final int MEDIA_RESULT = 4; //拍视频返回码

    //底部操作按钮
    @Bind(R.id.ll_bottom)
    LinearLayout llBottom;

    @Bind(R.id.ll_btn_1)
    LinearLayout llBtn1;
    @Bind(R.id.img_btn_1)
    ImageView imgBtn1;
    @Bind(R.id.txt_btn_1)
    TextView txtBtn1;

    @Bind(R.id.ll_btn_2)
    LinearLayout llBtn2;
    @Bind(R.id.img_btn_2)
    ImageView imgBtn2;
    @Bind(R.id.txt_btn_2)
    TextView txtBtn2;

    @Bind(R.id.ll_btn_3)
    LinearLayout llBtn3;
    @Bind(R.id.img_btn_3)
    ImageView imgBtn3;
    @Bind(R.id.txt_btn_3)
    TextView txtBtn3;

    @Bind(R.id.ll_btn_4)
    LinearLayout llBtn4;
    @Bind(R.id.img_btn_4)
    ImageView imgBtn4;
    @Bind(R.id.txt_btn_4)
    TextView txtBtn4;

    @Bind(R.id.ll_btn_5)
    LinearLayout llBtn5;
    @Bind(R.id.img_btn_5)
    ImageView imgBtn5;
    @Bind(R.id.txt_btn_5)
    TextView txtBtn5;

    //故障位置
    @Bind(R.id.txt_position)
    TextView txtTsPosition;
    //原因
    @Bind(R.id.txt_reason)
    TextView txtReason;
    //故障描述
    @Bind(R.id.txt_desc)
    TextView txtDesc;
    //解决办法
    @Bind(R.id.txt_way)
    TextView txtWay;
    //是否使用该方案
    @Bind(R.id.txt_is_use_way)
    TextView txtIsUse;
    @Bind(R.id.tb_is_use)
    ToggleButton tbIsUse;
    //是否有效
    @Bind(R.id.txt_is_use_way_result)
    TextView txtIsWork;
    @Bind(R.id.tb_is_work)
    ToggleButton tbIsWork;

    //TsObjectId
    private String tsId;
    //文件名
    private String fileName;
    //排故方法的Id
    private String tsSolutionID;
    //故障位置
    private String tsPosition;
    //真实原因
    private String realReason;
    //可能原因
    private String possibleReason;
    //解决办法
    private String tsSolution;
    //故障描述
    private String tsDescription;
    //编号
    private int tsSolutionNumber;
    //TsInfo，说明书页码相关
    private TSInfo tsInfo;
    //标题
    private String title;

    //对象编号
    private String objectNumber;
    //部件编号
    private String partNumber;


    @Override
    public int getLayoutId() {
        return R.layout.activity_begin_problem;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            title = bundle.getString("title", "");
            commonToolbar.setTitle(title);

            tsInfo = (TSInfo) bundle.getSerializable("tsInfo");
            tsId = bundle.getString("tsId");
            fileName = bundle.getString("fileName");
            tsSolutionID = bundle.getString("tsSolutionID");
            tsPosition = bundle.getString("tsPosition");
            realReason = bundle.getString("realReason");
            possibleReason = bundle.getString("possibleReason");
            tsSolution = bundle.getString("tsSolution");
            tsDescription = bundle.getString("tsDescription");
            objectNumber = bundle.getString("objectNumber");
            partNumber = bundle.getString("partNumber");
            tsSolutionNumber = bundle.getInt("tsSolutionNumber");

            boolean isUse = bundle.getBoolean("isUse");
            boolean isWork = bundle.getBoolean("isWork");
            if (isUse) {
                tbIsUse.setToggleOn();
            } else {
                tbIsUse.setToggleOff();
            }
            if (isWork) {
                tbIsWork.setToggleOn();
            } else {
                tbIsWork.setToggleOff();
            }
        }
    }

    @Override
    public void initViews() {
        setBtn(txtBtn5, imgBtn5, getResources().getString(R.string.ess_result), 0);

        TSUIName tsuiName = UINameAction.getTSUINameByLanguage(LanguageUtils.getlanguage());
        if (null != tsuiName) {
            txtTsPosition.setText(tsuiName.getTsPositionLabel());
            txtReason.setText(tsuiName.getReasonLabel());
            txtDesc.setText(tsuiName.getDescriptionLabel());
            txtWay.setText(tsuiName.getSolutionLabel());
            txtIsUse.setText(tsuiName.getUsedLabel());
            txtIsWork.setText(tsuiName.getWorkLabel());
        }

        tbIsUse.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                TSObjectAction.updateTSSolutionIsUse(tsId, tsSolutionID, on);
            }
        });

        tbIsWork.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on) {
                    //同步更新选中是否使用
                    tbIsUse.toggleOn();
                }
                TSObjectAction.updateTSSolutionIsWork(tsId, tsSolutionID, on);
            }
        });
    }

    @OnClick({
            R.id.txt_position,
            R.id.txt_reason,
            R.id.txt_desc,
            R.id.txt_way,
            R.id.ll_btn_1,
            R.id.ll_btn_2,
            R.id.ll_btn_3,
            R.id.ll_btn_4,
            R.id.ll_btn_5
    })
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        String putTitle = "";
        switch (view.getId()) {
            case R.id.txt_position:
                //故障位置
                putTitle = title + "/" + txtTsPosition.getText().toString();
                bundle.putString("title", putTitle);
                bundle.putString("tsId", tsId);
                bundle.putString("tsSolutionID", tsSolutionID);
                bundle.putString("fileName", fileName);
                bundle.putString("tsPosition", tsPosition);
                bundle.putString("objectNumber", objectNumber);
                bundle.putString("partNumber", partNumber);
                bundle.putInt("tsSolutionNumber", tsSolutionNumber); //编号，根据编号来判断是哪一个说明书，约定1，2，3代表EM,AEM,TSEM
                bundle.putSerializable("tsInfo", tsInfo); //TSInfo
                forwardForResult(ProblemPositionActivity.class, bundle, POSITION_CODE);
                break;
            case R.id.txt_reason:
                //原因
                putTitle = title + "/" + txtReason.getText().toString();
                bundle.putString("title", putTitle);
                bundle.putString("tsId", tsId);
                bundle.putString("tsSolutionID", tsSolutionID);
                bundle.putString("fileName", fileName);
                bundle.putString("realReason", realReason);
                bundle.putString("possibleReason", possibleReason);
                bundle.putString("objectNumber", objectNumber);
                bundle.putString("partNumber", partNumber);
                bundle.putInt("tsSolutionNumber", tsSolutionNumber); //编号，根据编号来判断是哪一个说明书，约定1，2，3代表EM,AEM,TSEM
                bundle.putSerializable("tsInfo", tsInfo); //TSInfo
                forwardForResult(ProblemReasonActivity.class, bundle, REASON_CODE);
                break;
            case R.id.txt_desc:
                //故障描述：说明书跳转pdf，自定义的跳转修改页面
                if (tsSolutionNumber > 3) {
                    putTitle = title + "/" + txtDesc.getText().toString();
                    bundle.putString("title", putTitle);
                    bundle.putString("tsId", tsId);
                    bundle.putString("tsSolutionID", tsSolutionID);
                    bundle.putString("fileName", fileName);
                    bundle.putString("tsDescription", tsDescription);
                    bundle.putString("objectNumber", objectNumber);
                    bundle.putString("partNumber", partNumber);
                    bundle.putInt("tsSolutionNumber", tsSolutionNumber); //编号，根据编号来判断是哪一个说明书，约定1，2，3代表EM,AEM,TSEM
                    bundle.putSerializable("tsInfo", tsInfo); //TSInfo
                    forwardForResult(ProblemDescActivity.class, bundle, DESC_CODE);
                } else {
                    //TODO 跳转PDF
                }
                break;
            case R.id.txt_way:
                //解决办法：说明书跳转pdf，自定义的跳转修改页面
                if (tsSolutionNumber > 3) {
                    putTitle = title + "/解决办法";
                    bundle.putString("title", putTitle);
                    bundle.putString("tsId", tsId);
                    bundle.putString("tsSolutionID", tsSolutionID);
                    bundle.putString("fileName", fileName);
                    bundle.putString("tsSolution", tsSolution);
                    bundle.putString("objectNumber", objectNumber);
                    bundle.putString("partNumber", partNumber);
                    bundle.putInt("tsSolutionNumber", tsSolutionNumber); //编号，根据编号来判断是哪一个说明书，约定1，2，3代表EM,AEM,TSEM
                    bundle.putSerializable("tsInfo", tsInfo); //TSInfo
                    forwardForResult(ProblemSolutionActivity.class, bundle, SOLUTION_CODE);
                } else {
                    //TODO 跳转PDF
                }
                break;
            case R.id.ll_btn_1:
                //备注
                bundle.putString("tsId", tsId);
                bundle.putString("tsSolutionID", tsSolutionID);
                forward(ProblemRemarkActivity.class, bundle);
                break;
            case R.id.ll_btn_2:
                //音频
                takeAudio();
                break;
            case R.id.ll_btn_3:
                //视频
                takeMedia();
                break;
            case R.id.ll_btn_4:
                //图片
                bundle.putString("outputPath", InitFileUtils.getTSFilePath(objectNumber, partNumber) + "/" + fileName + "/Picture");
                forwardForResult(CameraActivity.class, bundle, PIC_CODE);
                break;
            case R.id.ll_btn_5:
                //历史记录
                bundle.putString("tsId", tsId);
                bundle.putString("tsSolutionID", tsSolutionID);
                bundle.putString("fileName", fileName);
                bundle.putString("objectNumber", objectNumber);
                bundle.putString("partNumber", partNumber);
                bundle.putInt("tsSolutionNumber", tsSolutionNumber);
                forward(ProblemResultActivity.class, bundle);
                break;
            default:
                break;
        }
    }

    /**
     * 录制音频
     */
    private void takeAudio() {
        final RecordAudioDialogFragment fragment = RecordAudioDialogFragment.newInstance(objectNumber, partNumber, fileName, tsId);
        fragment.show(getSupportFragmentManager(), RecordAudioDialogFragment.class.getSimpleName());
        fragment.setOnCancelListener(new RecordAudioDialogFragment.OnAudioCancelListener() {
            @Override
            public void onCancel() {
                fragment.dismiss();
            }
        });
    }

    /**
     * 拍视频
     */
    private void takeMedia() {
        try {
            String outputFileName = "video" + DateUtils.dateToString(new Date(), DateUtils.FORMAT_LONG_WITHOUT);
            String mediaUrl = InitFileUtils.getTSFilePath(objectNumber, partNumber) + "/" + fileName + "/Video/" + outputFileName + ".mp4";
            File file = new File(mediaUrl);
            if (!file.getParentFile().getParentFile().exists()) {
                file.getParentFile().getParentFile().mkdirs();
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Uri uri = Uri.fromFile(file);

            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//            intent.setAction("android.media.action.VIDEO_CAPTURE");
//            intent.addCategory("android.intent.category.DEFAULT");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, MEDIA_RESULT);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.show(e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle bundle = new Bundle();
            if (null != data) {
                bundle = data.getExtras();
            }
            switch (requestCode) {
                case REASON_CODE:
                    //填写的真实原因
                    if (null != bundle) {
                        realReason = bundle.getString("text");
                        TSObjectAction.updateTSObjectRealReason(tsId, realReason);
                    }
                    break;
                case SOLUTION_CODE:
                    //解决办法
                    if (null != bundle) {
                        tsSolution = bundle.getString("text");
                        TSObjectAction.updateTSSolutionSolution(tsId, tsSolutionID, tsSolution);
                    }
                    break;
                case DESC_CODE:
                    //故障描述
                    if (null != bundle) {
                        tsDescription = bundle.getString("text");
                        TSObjectAction.updateTSSolutionDesc(tsId, tsSolutionID, tsDescription);
                    }
                    break;
                case POSITION_CODE:
                    //故障位置
                    if (null != bundle) {
                        tsPosition = bundle.getString("text");
                        TSObjectAction.updateTSObjectTsPosition(tsId, tsPosition);
                    }
                    break;
                case PIC_CODE:
                    if (null != bundle) {
                        String imgPath = bundle.getString("imgPath");
                        int type = bundle.getInt("type");
                        if (type == JCameraView.TYPE_BTN_MENU) {
                            //编辑
                            forwardForResult(EditImageActivity.class, bundle, EDIT_IMAGE_CODE);
                        } else if (type == JCameraView.TYPE_BTN_CONFIRM) {
                            //确认
                            updatePicInDb(imgPath);
                        }
                    }
                    break;
                case EDIT_IMAGE_CODE:
                    if (null != bundle) {
                        String imgPath = bundle.getString("imgPath");
                        updatePicInDb(imgPath);
                    }
                    break;
                case MEDIA_RESULT: //拍视频
                    try {
                        Uri imgUri = data.getData();
                        String path = getRealFilePath(imgUri);

                        Logger.i("tag", "选择视频的路径：" + path);

                        if (!new File(path).exists()) {
                            ToastUtils.show("您选择的视频不存在，请重试");
                            return;
                        }

                        updateVideoInDb(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getRealFilePath(final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    //更新数据库的图片信息
    private void updatePicInDb(String picPath) {
        //获取文件名，包含后缀名
        String fileName = picPath.split("/")[picPath.split("/").length - 1];
        TSObjectAction.updateTSObjectPic(tsId, fileName);
    }

    //更新数据库的视频信息
    private void updateVideoInDb(String videoPath) {
        //获取文件名，包含后缀名
        String fileName = videoPath.split("/")[videoPath.split("/").length - 1];
        TSObjectAction.updateTSObjectVideo(tsId, fileName);
    }

    //设置按钮文字和图标
    private void setBtn(TextView txtBtn, ImageView imgBtn, String text, int srcRes) {
        txtBtn.setText(text);
        if (null == imgBtn || 0 == srcRes) {
            return;
        }
        imgBtn.setImageResource(srcRes);
    }

    //设置按钮状态
    private void setBtnEnable(TextView txtBtn, ImageView imgBtn, LinearLayout llBtn, boolean isEnable) {
        if (isEnable) {
            txtBtn.setAlpha(1.0f);
            imgBtn.setAlpha(1.0f);
            llBtn.setEnabled(true);
        } else {
            txtBtn.setAlpha(0.5f);
            imgBtn.setAlpha(0.5f);
            llBtn.setEnabled(false);
        }
    }
}
