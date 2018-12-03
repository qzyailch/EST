package com.qian.ess.mvp.ui.fragment.home;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.qian.ess.R;
import com.qian.ess.base.BaseFragment;
import com.qian.ess.bean.home.PDFItem;
import com.qian.ess.common.Constants;
import com.qian.ess.mvp.ui.activity.pdf.ShowPDFActivity;
import com.qian.ess.mvp.ui.activity.pdf.ShowPDFActivity3;
import com.qian.ess.mvp.ui.adapter.home.PDFListAdapter;
import com.qian.ess.utils.FileUtils;
import com.qian.ess.utils.ScanFileCountUtil;
import com.qian.ess.widget.CustomEmptyView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/8/6 0006.
 * <p>
 * 第三页
 */

public class ThirdFragment extends BaseFragment {

    //无数据
    @Bind(R.id.icon_no_data)
    CustomEmptyView mIconNoData;
    //列表
    @Bind(R.id.list_pdf)
    ListView listTask;

    //loading布局
    @Bind(R.id.ll_loading)
    LinearLayout llLoading;

    //扫描出来的数据
    private List<PDFItem> pdfItems = new ArrayList<>();

    private PDFListAdapter mPDFListAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_third;
    }

    @Override
    public void initViews() {
        mPDFListAdapter = new PDFListAdapter(getContext());
        listTask.setAdapter(mPDFListAdapter);
        listTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PDFItem pdfItem = mPDFListAdapter.getArray().get(position);

                String fileName = pdfItem.getTitle();
                Bundle bundle = new Bundle();
                bundle.putString("title", fileName);
                bundle.putString("path", pdfItem.getPath());
                if (fileName.endsWith(".pdf")) {
                    forward(ShowPDFActivity3.class, bundle);
                } else {
                    forward(ShowPDFActivity.class, bundle);
                }
            }
        });

        scanFile();
    }

    /**
     * 扫描某个文件夹下的文档.doc .pdf等
     */
    private void scanFile() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        final String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        //单一线程线程池
        ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
        singleExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                ScanFileCountUtil scanFileCountUtil = new ScanFileCountUtil
                        .Builder(mHandler)
                        .setFilePath(path)
                        .setCategorySuffix(FileUtils.getCategorySuffix(Arrays.asList(new String[]{Constants.FILE_CONSTANTS.FILE_TYPE_DOCUMENT})))
                        .create();
                scanFileCountUtil.scanCountFile();
            }
        });
    }

    //文件扫描结束的处理
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            pdfItems.clear();
            Map<String, Set<File>> countRes = (Map<String, Set<File>>) msg.obj;
            for (Map.Entry<String, Set<File>> entry : countRes.entrySet()) {
                if (Constants.FILE_CONSTANTS.FILE_TYPE_DOCUMENT.equals(entry.getKey())) {
                    Iterator<File> iterators = entry.getValue().iterator();
                    while (iterators.hasNext()) {
                        PDFItem pdfItem = new PDFItem();
                        File file = iterators.next();
                        pdfItem.setTitle(file.getName());
                        pdfItem.setPath(file.getAbsolutePath());

                        pdfItems.add(pdfItem);
                    }
                }
            }

            mPDFListAdapter.setAllArray(pdfItems);
            mPDFListAdapter.notifyDataSetChanged();

            llLoading.setVisibility(View.GONE);
            if (pdfItems.size() > 0) {
                mIconNoData.setVisibility(View.GONE);
            } else {
                mIconNoData.setVisibility(View.VISIBLE);
            }
        }
    };
}
