package com.qian.ess.db.action;

import android.content.ContentValues;
import android.text.TextUtils;

import com.qian.ess.bean.config.ui.TCUIName;
import com.qian.ess.bean.config.ui.TSUIName;
import com.qian.ess.bean.config.ui.UIName;
import com.qian.ess.bean.config.ui.UINameConfig;
import com.qian.ess.db.DBConstants;
import com.qian.ess.db.DBHelper;
import com.qian.ess.utils.GsonUtils;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20 0020.
 * <p>
 * UIName
 */

public class UINameAction {

    //添加或更新UI配置
    public static void addOrUpdateUIName(UIName uiName) {
        long time = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_LANGUAGE, uiName.getLanguage());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_Object, uiName.getObject());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_Member, uiName.getMember());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_O_Name, uiName.getOName());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_M_Name, uiName.getMName());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_Base, uiName.getBase());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_NoO, uiName.getNoO());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_NoM1, uiName.getNoM1());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_NoM2, uiName.getNoM2());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_SS, uiName.getSs());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_TR, uiName.getTr());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_ED, uiName.getEd());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_EC, uiName.getEc());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_EM, uiName.getEm());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_AEM, uiName.getAem());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_TSEM, uiName.getTsem());
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_ESTM, uiName.getEstm());

        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_UPDATE_TIME, time);
        contentValues.put(DBConstants.T_TS_CONFIG_UI_Names.COLUMN_CREATE_TIME, time);
        DBHelper.getInstance().insert(DBConstants.T_TS_CONFIG_UI_Names.T_NAME, contentValues);
    }

    //添加或更新TSUI配置
    public static void addOrUpdateTSUIName(TSUIName tsuiName) {
        long time = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_LANGUAGE, tsuiName.getLanguage());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_WORK_LOCATION_LABEL, tsuiName.getWorkLocationLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_TS_NUMBER_LABEL, tsuiName.getTsNumberLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_TS_SHORT_NAME_LABEL, tsuiName.getTsShortNameLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_TS_FULL_NAME_LABEL, tsuiName.getTsFullNameLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_TS_POSITION_LABEL, tsuiName.getTsPositionLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_OBJECT_MODEL_LABEL, tsuiName.getObjectModelLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_OBJECT_NUMBER_LABEL, tsuiName.getObjectNumberLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_PART_NUMBER_LABEL, tsuiName.getPartNumberLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_PART_POSITION_LABEL, tsuiName.getPartPositionLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_TS_PERSON_IN_CHARGE_LABEL, tsuiName.getTsPersonInChargeLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_TS_WORKER_NAME_LABEL, tsuiName.getTsWorkerNameLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_TS_AUDITOR_LABEL, tsuiName.getTsAuditorLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_TS_DELIVER_DATE_TIME_LABEL, tsuiName.getTsDeliverDateTimeLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_TS_BEGIN_DATE_TIME_LABEL, tsuiName.getTsBeginDateTimeLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_TS_FINISH_DATE_TIME_LABEL, tsuiName.getTsFinishDateTimeLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_TS_SOLUTION_NUMBER_LABEL, tsuiName.getTsSolutionNumberLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_TS_SOLUTIONS_LABEL, tsuiName.getTsSolutionsLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_TS_POSSIBLE_REASON_LABEL, tsuiName.getTsPossibleReasonLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_TS_IS_USED_LABEL, tsuiName.getTsIsUsedLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_TS_IS_WORK_LABEL, tsuiName.getTsIsWorkLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_REAL_REASON_LABEL, tsuiName.getRealReasonLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_REMARK_LABEL, tsuiName.getRemarkLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_PICTURE_LABEL, tsuiName.getPictureLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_VIDEO_LABEL, tsuiName.getVideoLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_AUDIO_LABEL, tsuiName.getAudioLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_USED_LABEL, tsuiName.getUsedLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_WORK_LABEL, tsuiName.getWorkLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_REASON_LABEL, tsuiName.getReasonLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_DESCRIPTION_LABEL, tsuiName.getDescriptionLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_SOLUTION_LABEL, tsuiName.getSolutionLabel());

        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_UPDATE_TIME, time);
        contentValues.put(DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_CREATE_TIME, time);
        DBHelper.getInstance().insert(DBConstants.T_TS_CONFIG_TS_UI_Names.T_NAME, contentValues);
    }

    //添加或更新TCUI配置
    public static void addOrUpdateTCUIName(TCUIName tcuiName) {
        long time = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_LANGUAGE, tcuiName.getLanguage());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_WORK_LOCATION_LABEL, tcuiName.getWorkLocationLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PC_NUMBER_LABEL, tcuiName.getPcNumberLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_OBJECT_MODEL_LABEL, tcuiName.getObjectModelLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_OBJECT_NUMBER_LABEL, tcuiName.getObjectNumberLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PC_POSITION_LABEL, tcuiName.getPcPositionLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PART_NUMBER_LABEL, tcuiName.getPartNumberLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PART_POSITION_LABEL, tcuiName.getPartPositionLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PC_QC_LABEL, tcuiName.getPcQCLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PC_PERSON_IN_CHARGE_LABEL, tcuiName.getPcPersonInChargeLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PC_WORKER_NAME_LABEL, tcuiName.getPcWorkerNameLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PC_AUDITOR_LABEL, tcuiName.getPcAuditorLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PC_DELIVER_DATE_TIME_LABEL, tcuiName.getPcDeliverDateTimeLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PC_BEGIN_DATE_TIME_LABEL, tcuiName.getPcBeginDateTimeLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PC_FINISH_DATE_TIME_LABEL, tcuiName.getPcFinishDateTimeLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PC_TC_NUMBER_LABEL, tcuiName.getPcTCNumberLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PC_TC_S_LABEL, tcuiName.getPcTCsLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PC_COMPLETE_LABEL, tcuiName.getPcCompleteLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PC_QUANTITY_LABEL, tcuiName.getPcQuantityLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_REMARK_LABEL, tcuiName.getRemarkLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_PICTURE_LABEL, tcuiName.getPictureLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_VIDEO_LABEL, tcuiName.getVideoLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_AUDIO_LABEL, tcuiName.getAudioLabel());

        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_UPDATE_TIME, time);
        contentValues.put(DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_CREATE_TIME, time);
        DBHelper.getInstance().insert(DBConstants.T_TS_CONFIG_TC_UI_Names.T_NAME, contentValues);
    }

    //根据语言获取当前UI显示信息
    public static UIName getUINameByLanguage(String language) {
        String sql = "select * from " + DBConstants.T_TS_CONFIG_UI_Names.T_NAME;
        if (!TextUtils.isEmpty(language)) {
            sql += " where " + DBConstants.T_TS_CONFIG_UI_Names.COLUMN_LANGUAGE + " = '" + language + "'";
        }
        sql += " order by " + DBConstants.T_TS_CONFIG_UI_Names.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        if (null != array && array.length() > 0) {
            return GsonUtils.fromJson(array.optJSONObject(0).toString(), UIName.class);
        }
        return null;
    }

    //根据语言获取当前TSUI显示信息
    public static TSUIName getTSUINameByLanguage(String language) {
        String sql = "select * from " + DBConstants.T_TS_CONFIG_TS_UI_Names.T_NAME;
        if (!TextUtils.isEmpty(language)) {
            sql += " where " + DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_LANGUAGE + " = '" + language + "'";
        }
        sql += " order by " + DBConstants.T_TS_CONFIG_TS_UI_Names.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        if (null != array && array.length() > 0) {
            return GsonUtils.fromJson(array.optJSONObject(0).toString(), TSUIName.class);
        }
        return null;
    }

    //根据语言获取当前TSUI显示信息
    public static TCUIName getTCUINameByLanguage(String language) {
        String sql = "select * from " + DBConstants.T_TS_CONFIG_TC_UI_Names.T_NAME;
        if (!TextUtils.isEmpty(language)) {
            sql += " where " + DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_LANGUAGE + " = '" + language + "'";
        }
        sql += " order by " + DBConstants.T_TS_CONFIG_TC_UI_Names.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        if (null != array && array.length() > 0) {
            return GsonUtils.fromJson(array.optJSONObject(0).toString(), TCUIName.class);
        }
        return null;
    }

    /************TSConfig**************/
    //添加或更新TSConfig所有配置信息
    public static void addOrUpdateUINameConfig(UINameConfig uiNameConfig) {
        clear();
        List<UIName> uiNames = uiNameConfig.getUiNames();
        List<TSUIName> tsuiNames = uiNameConfig.getTsuiNames();
        List<TCUIName> tcuiNames = uiNameConfig.getTcuiNames();

        if (null != uiNames && uiNames.size() > 0) {
            for (int i = 0, len = uiNames.size(); i < len; i++) {
                addOrUpdateUIName(uiNames.get(i));
            }
        }

        if (null != tsuiNames && tsuiNames.size() > 0) {
            for (int i = 0, len = tsuiNames.size(); i < len; i++) {
                addOrUpdateTSUIName(tsuiNames.get(i));
            }
        }

        if (null != tcuiNames && tcuiNames.size() > 0) {
            for (int i = 0, len = tcuiNames.size(); i < len; i++) {
                addOrUpdateTCUIName(tcuiNames.get(i));
            }
        }
    }

    //清除UI配置数据
    public static void clear() {
        DBHelper.getInstance().delete(DBConstants.T_TS_CONFIG_UI_Names.T_NAME, null, null);
        DBHelper.getInstance().delete(DBConstants.T_TS_CONFIG_TS_UI_Names.T_NAME, null, null);
        DBHelper.getInstance().delete(DBConstants.T_TS_CONFIG_TC_UI_Names.T_NAME, null, null);
    }
}
