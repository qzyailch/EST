package com.qian.ess.mvp.ui.activity.problem;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.camera.JCameraView;
import com.qian.ess.common.ESSFilePath;
import com.qian.ess.db.action.TSObjectAction;
import com.qian.ess.mvp.ui.activity.camera.CameraActivity;
import com.qian.ess.mvp.ui.activity.camera.EditImageActivity;
import com.qian.ess.mvp.ui.fragment.dialog.RecordAudioDialogFragment;
import com.qian.ess.utils.DateUtils;
import com.qian.ess.utils.InitFileUtils;
import com.qian.ess.utils.Logger;
import com.qian.ess.utils.ToastUtils;

import java.io.File;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by QianMoLi on 2018/11/4.
 * <p>
 * 故障描述
 */

public class ProblemDescActivity extends BaseActivity {

    //编辑文本
    private static final int TEXT_CODE = 520;
    //编辑图片
    private static final int PIC_CODE = 522;
    private static final int EDIT_IMAGE_CODE = 526;
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

    @Bind(R.id.et_text)
    EditText editText;

    //TSObjectId
    private String tsId;
    private String tsSolutionID;
    //文件名
    private String fileName;
    //故障描述
    private String tsDescription;
    //编号
    private int tsSolutionNumber;

    //对象编号
    private String objectNumber;
    //部件编号
    private String partNumber;

    @Override
    public int getLayoutId() {
        return R.layout.activity_problem_desc;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            commonToolbar.setTitle(bundle.getString("title", ""));

            tsId = bundle.getString("tsId");
            tsSolutionID = bundle.getString("tsSolutionID");
            fileName = bundle.getString("fileName");
            tsDescription = bundle.getString("tsDescription");
            objectNumber = bundle.getString("objectNumber");
            partNumber = bundle.getString("partNumber");
            tsSolutionNumber = bundle.getInt("tsSolutionNumber");
        }
    }

    @Override
    public void initViews() {
        setBtn(txtBtn5, imgBtn5, getResources().getString(R.string.ess_result), 0);

        editText.setText(TextUtils.isEmpty(tsDescription) ? "" : tsDescription);
        editText.setSelection(editText.getText().toString().length());
    }

    @OnClick({
            R.id.txt_btn_cancel,
            R.id.txt_btn_finish,
            R.id.ll_btn_1,
            R.id.ll_btn_2,
            R.id.ll_btn_3,
            R.id.ll_btn_4,
            R.id.ll_btn_5
    })
    public void onViewClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.txt_btn_cancel:
                backward();
                break;
            case R.id.txt_btn_finish:
                Intent intent = new Intent();
                bundle.putString("text", editText.getText().toString());
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                backward();
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
                case TEXT_CODE:
                    if (null != bundle) {
                        String text = bundle.getString("text");
                        ToastUtils.show(text);
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

    //更新数据库的图片信息
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
