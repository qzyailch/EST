package com.qian.ess.service;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.qian.ess.bean.config.ConfigIcon;
import com.qian.ess.common.ESSFilePath;
import com.qian.ess.db.action.ConfigIconAction;
import com.qian.ess.utils.Logger;
import com.qian.ess.utils.ZipUtils;
import com.qian.ess.utils.thread.ThreadPoolProxyFactory;

import java.io.File;

/**
 * Created by Administrator on 2018/11/20 0020.
 * <p>
 * 第一次进入，初始化icon等信息，把初始icon解压到文件夹中
 */

public class InitService extends Service {

    //根目录
    private String dirName = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init();
        return super.onStartCommand(intent, flags, startId);
    }

    //判断是否数据库有值
    private void init() {
        ConfigIcon configIcon = ConfigIconAction.getConfigIcon();
        if (null == configIcon) {
            unIconZip();
        }
    }

    /**
     * 解压Icon文件
     */
    private void unIconZip() {
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String outputPath = dirName + ESSFilePath.ICON_FILE;
                    File file = new File(outputPath);
                    if (!file.getParentFile().getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    ZipUtils.UnZipFolder(InitService.this, "icon.zip", outputPath);
                    Logger.i("result", "解压icon文件成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
