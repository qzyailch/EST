package com.qian.ess.db.action.tc;

import android.content.ContentValues;
import android.text.TextUtils;

import com.qian.ess.bean.config.tc.TCConfig;
import com.qian.ess.bean.config.tc.TCDoc;
import com.qian.ess.bean.config.tc.TcInfo;
import com.qian.ess.db.DBConstants;
import com.qian.ess.db.DBHelper;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20 0020.
 * <p>
 * TCConfig
 */

public class TCConfigAction {

    /************TCDoc**************/
    //添加或更新TCDoc配置信息
    public static void addOrUpdateTCDoc(TCDoc tcDoc) {
        long time = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TS_CONFIG_TC_LINKS_Doc.COLUMN_TCDoc, tcDoc.getTcDoc());

        contentValues.put(DBConstants.T_TS_CONFIG_TC_LINKS_Doc.COLUMN_UPDATE_TIME, time);
        contentValues.put(DBConstants.T_TS_CONFIG_TC_LINKS_Doc.COLUMN_CREATE_TIME, time);
        DBHelper.getInstance().insert(DBConstants.T_TS_CONFIG_TC_LINKS_Doc.T_NAME, contentValues);
    }

    /************TCInfo**************/
    //添加或更新TSInfo配置信息
    public static void addOrUpdateTCInfo(TcInfo tcInfo) {
        long time = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TS_CONFIG_TC_LINKS_TCInfo.COLUMN_TC_ID, tcInfo.getTcId());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_LINKS_TCInfo.COLUMN_TC_PAGE_ENG, tcInfo.getTcPageEng());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_LINKS_TCInfo.COLUMN_TC_PAGE_CHN, tcInfo.getTcPageChn());

        contentValues.put(DBConstants.T_TS_CONFIG_TC_LINKS_TCInfo.COLUMN_UPDATE_TIME, time);
        contentValues.put(DBConstants.T_TS_CONFIG_TC_LINKS_TCInfo.COLUMN_CREATE_TIME, time);
        DBHelper.getInstance().insert(DBConstants.T_TS_CONFIG_TC_LINKS_TCInfo.T_NAME, contentValues);
    }


    /************TCConfig**************/
    //添加或更新TSConfig所有配置信息
    public static void addOrUpdateTCConfig(TCConfig tcConfig) {
        TCDoc tcDoc = tcConfig.getTcDoc();
        List<TcInfo> tcInfos = tcConfig.getTcInfos();

        if (null != tcDoc && !TextUtils.isEmpty(tcDoc.getTcDoc())) {
            addOrUpdateTCDoc(tcDoc);
        }

        if (null != tcInfos && tcInfos.size() > 0) {
            for (int i = 0, len = tcInfos.size(); i < len; i++) {
                addOrUpdateTCInfo(tcInfos.get(i));
            }
        }
    }

    //清除UI配置数据
    public static void clear() {
        DBHelper.getInstance().delete(DBConstants.T_TS_CONFIG_TC_LINKS_Doc.T_NAME, null, null);
        DBHelper.getInstance().delete(DBConstants.T_TS_CONFIG_TC_LINKS_TCInfo.T_NAME, null, null);
    }
}
