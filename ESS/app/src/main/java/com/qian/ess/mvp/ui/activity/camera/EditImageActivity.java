package com.qian.ess.mvp.ui.activity.camera;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;

import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;
import com.qian.ess.utils.ToastUtils;

import java.io.File;

/**
 * Created by QianMoLi on 2018/11/4.
 * <p>
 * 编辑图片
 */

public class EditImageActivity extends BaseActivity {

    public static final int ACTION_REQUEST_EDITIMAGE = 9;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_image;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {

    }

    @Override
    public void initViews() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            String imagePath = bundle.getString("imgPath");
            editImageClick(imagePath);
        }
    }

    /**
     * 编辑选择的图片
     *
     * @author panyi
     */
    private void editImageClick(String imagePath) {
        if (TextUtils.isEmpty(imagePath)) {
            ToastUtils.show("路径不能为空");
            return;
        }
        File file = new File(imagePath);
        if (!file.exists()) {
            ToastUtils.show("图片不存在");
            return;
        }
        com.yjing.imageeditlibrary.editimage.EditImageActivity.start(this, imagePath, imagePath, ACTION_REQUEST_EDITIMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ACTION_REQUEST_EDITIMAGE://
                    handleEditorImage(data);
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleEditorImage(Intent data) {
        String newFilePath = data.getStringExtra(com.yjing.imageeditlibrary.editimage.EditImageActivity.SAVE_FILE_PATH);
        boolean isImageEdit = data.getBooleanExtra(com.yjing.imageeditlibrary.editimage.EditImageActivity.IMAGE_IS_EDIT, false);
        Log.d("image is edit", isImageEdit + "");

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("imgPath", newFilePath);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        backward();
    }

}
