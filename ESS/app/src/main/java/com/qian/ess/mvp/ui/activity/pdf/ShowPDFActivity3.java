package com.qian.ess.mvp.ui.activity.pdf;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.github.barteksc.pdfviewer.PDFView;
import com.qian.ess.R;
import com.qian.ess.base.BaseActivity;

import java.io.File;

import butterknife.Bind;

/**
 * Created by QianMoLi on 2018/8/20.
 */

public class ShowPDFActivity3 extends BaseActivity {

    private String title;
    private String path;

    @Bind(R.id.pdfView)
    PDFView pdfView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_pdf3;
    }

    @Override
    public void initToolBar(Toolbar commonToolbar) {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            title = bundle.getString("title");
            path = bundle.getString("path");
        }

        if (!TextUtils.isEmpty(title)) {
            commonToolbar.setTitle(title);
        }
    }

    @Override
    public void initViews() {
        pdfView.fromFile(new File(path))
                //.pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .load();
    }
}
