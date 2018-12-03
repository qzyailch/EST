package com.qian.ess.db.action.ts;

import android.content.ContentValues;
import android.text.TextUtils;

import com.qian.ess.bean.config.ts.TSConfig;
import com.qian.ess.bean.config.ts.TSDoc;
import com.qian.ess.bean.config.ts.TSInfo;
import com.qian.ess.bean.config.ts.TSLabel;
import com.qian.ess.db.DBConstants;
import com.qian.ess.db.DBHelper;
import com.qian.ess.utils.GsonUtils;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20 0020.
 * <p>
 * TSConfig
 */

public class TSConfigAction {

    /************TSDoc**************/
    //添加或更新TSDoc配置信息
    public static void addOrUpdateTSDoc(TSDoc tsDoc) {
        long time = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_Doc.COLUMN_TSDoc_Label, tsDoc.getLabel());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_Doc.COLUMN_TSDoc_Text, tsDoc.getText());

        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_Doc.COLUMN_UPDATE_TIME, time);
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_Doc.COLUMN_CREATE_TIME, time);
        DBHelper.getInstance().insert(DBConstants.T_TS_CONFIG_TS_LINKS_Doc.T_NAME, contentValues);
    }

    /************TSLabels**************/
    //添加或更新TSLabels配置信息
    public static void addOrUpdateTSLabels(TSLabel tsLabel) {
        long time = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_TS_LANGUAGE, tsLabel.getLanguage());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_TS_SHORTNAME, tsLabel.getTsShortName());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_TS_COLOR, tsLabel.getTsColor());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_TS_GROUP, tsLabel.getTsGroup());

        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_TROUBLE_SHOOTING_WAY_EM, tsLabel.getTroubleshootingWayEM());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_TROUBLE_SHOOTING_WAY_AEM, tsLabel.getTroubleshootingWayAEM());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_TROUBLE_SHOOTING_WAY_TSEM, tsLabel.getTroubleshootingWayTSEM());

        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_DESCRIPTION_EM, tsLabel.getDescriptionEM());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_DESCRIPTION_AEM, tsLabel.getDescriptionAEM());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_DESCRIPTION_TSEM, tsLabel.getDescriptionTSEM());

        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_REASON_EM, tsLabel.getReasonEM());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_REASON_AEM, tsLabel.getReasonAEM());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_REASON_TSEM, tsLabel.getReasonTSEM());

        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_CONDITION_EM, tsLabel.getConditionEM());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_CONDITION_AEM, tsLabel.getConditionAEM());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_CONDITION_TSEM, tsLabel.getConditionTSEM());

        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_SOLUTION_EM, tsLabel.getSolutionEM());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_SOLUTION_AEM, tsLabel.getSolutionAEM());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_SOLUTION_TSEM, tsLabel.getSolutionTSEM());

        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_UPDATE_TIME, time);
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_CREATE_TIME, time);
        DBHelper.getInstance().insert(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.T_NAME, contentValues);
    }

    //根据语言获取当前TSLabels显示信息
    public static TSLabel getTSLabelByLanguage(String language) {
        String sql = "select * from " + DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.T_NAME;
        if (!TextUtils.isEmpty(language)) {
            sql += " where " + DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_TS_LANGUAGE + " = '" + language + "'";
        }
        sql += " order by " + DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        if (null != array && array.length() > 0) {
            return GsonUtils.fromJson(array.optJSONObject(0).toString(), TSLabel.class);
        }
        return null;
    }

    /************TSInfo**************/
    //添加或更新TSInfo配置信息
    public static void addOrUpdateTSInfo(TSInfo tsInfo) {
        long time = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_TS_LANGUAGE, tsInfo.getLanguage());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_TS_SHORTNAME, tsInfo.getTsShortName());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_TS_COLOR, tsInfo.getTsColor());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_TS_GROUP, tsInfo.getTsGroup());

        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_EM_DESCRIPTION_PAGE, tsInfo.getEmDescriptionPage());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_AEM_DESCRIPTION_PAGE, tsInfo.getAemDescriptionPage());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_TSEM_DESCRIPTION_PAGE, tsInfo.getTsemDescriptionPage());

        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_EM_REASON_PAGE, tsInfo.getEmReasonPage());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_AEM_REASON_PAGE, tsInfo.getAemReasonPage());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_TSEM_REASON_PAGE, tsInfo.getTsemReasonPage());

        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_EM_CONDITION_PAGE, tsInfo.getEmConditionPage());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_AEM_CONDITION_PAGE, tsInfo.getAemConditionPage());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_TSEM_CONDITION_PAGE, tsInfo.getTsemConditionPage());

        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_EM_SOLUTION_PAGE, tsInfo.getEmSolutionPage());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_AEM_SOLUTION_PAGE, tsInfo.getAemSolutionPage());
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_TSEM_SOLUTION_PAGE, tsInfo.getTsemSolutionPage());

        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_UPDATE_TIME, time);
        contentValues.put(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_CREATE_TIME, time);
        DBHelper.getInstance().insert(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.T_NAME, contentValues);
    }

    //根据语言获取当前TSInfo信息
    public static TSInfo getTSInfoByLanguage(String language, String tsShortName) {
        String sql = "select * from " + DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.T_NAME;
        if (!TextUtils.isEmpty(language)) {
            sql += " where " + DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_TS_LANGUAGE + " = '" + language + "'";
        }
        if (!TextUtils.isEmpty(tsShortName)) {
            if (sql.contains("where")) {
                sql += " and ";
            } else {
                sql += " where ";
            }
            sql += DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_TS_SHORTNAME + " = '" + tsShortName + "'";
        }
        sql += " order by " + DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        if (null != array && array.length() > 0) {
            return GsonUtils.fromJson(array.optJSONObject(0).toString(), TSInfo.class);
        }
        return null;
    }

    /************TSConfig**************/
    //添加或更新TSConfig所有配置信息
    public static void addOrUpdateTSConfig(TSConfig tsConfig) {
        clear();
        List<TSDoc> tsDocs = tsConfig.getTsDocs();
        List<TSLabel> tsLabels = tsConfig.getTsLabels();
        List<TSInfo> tsInfos = tsConfig.getTsInfos();

        if (null != tsDocs && tsDocs.size() > 0) {
            for (int i = 0, len = tsDocs.size(); i < len; i++) {
                addOrUpdateTSDoc(tsDocs.get(i));
            }
        }

        if (null != tsLabels && tsLabels.size() > 0) {
            for (int i = 0, len = tsLabels.size(); i < len; i++) {
                addOrUpdateTSLabels(tsLabels.get(i));
            }
        }

        if (null != tsInfos && tsInfos.size() > 0) {
            for (int i = 0, len = tsInfos.size(); i < len; i++) {
                addOrUpdateTSInfo(tsInfos.get(i));
            }
        }
    }

    //清除UI配置数据
    public static void clear() {
        DBHelper.getInstance().delete(DBConstants.T_TS_CONFIG_TS_LINKS_Doc.T_NAME, null, null);
        DBHelper.getInstance().delete(DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.T_NAME, null, null);
        DBHelper.getInstance().delete(DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.T_NAME, null, null);
    }
}
