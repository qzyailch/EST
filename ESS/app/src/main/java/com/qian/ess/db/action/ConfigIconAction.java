package com.qian.ess.db.action;

import android.content.ContentValues;

import com.qian.ess.bean.config.ConfigIcon;
import com.qian.ess.db.DBConstants;
import com.qian.ess.db.DBHelper;
import com.qian.ess.utils.GsonUtils;

import org.json.JSONArray;

/**
 * Created by Administrator on 2018/11/20 0020.
 * <p>
 * ConfigIcon
 */

public class ConfigIconAction {

    //添加或更新Icon配置
    public static void addOrUpdateConfigIcon(ConfigIcon configIcon) {
        clear();
        long time = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TS_CONFIG_ICON.COLUMN_ICON_SS, configIcon.getSs());
        contentValues.put(DBConstants.T_TS_CONFIG_ICON.COLUMN_ICON_TS, configIcon.getTs());
        contentValues.put(DBConstants.T_TS_CONFIG_ICON.COLUMN_ICON_ED, configIcon.getEd());
        contentValues.put(DBConstants.T_TS_CONFIG_ICON.COLUMN_ICON_EC, configIcon.getEc());
        contentValues.put(DBConstants.T_TS_CONFIG_ICON.COLUMN_ICON_Object, configIcon.getIconObject());
        contentValues.put(DBConstants.T_TS_CONFIG_ICON.COLUMN_ICON_Member, configIcon.getIconMember());
        contentValues.put(DBConstants.T_TS_CONFIG_ICON.COLUMN_ICON_Table, configIcon.getIconTable());
        contentValues.put(DBConstants.T_TS_CONFIG_ICON.COLUMN_ICON_Grafic, configIcon.getIconGrafic());
        contentValues.put(DBConstants.T_TS_CONFIG_ICON.COLUMN_ICON_PC, configIcon.getPc());
        contentValues.put(DBConstants.T_TS_CONFIG_ICON.COLUMN_ICON_TR, configIcon.getTr());

        contentValues.put(DBConstants.T_TS_CONFIG_ICON.COLUMN_UPDATE_TIME, time);
        contentValues.put(DBConstants.T_TS_CONFIG_ICON.COLUMN_CREATE_TIME, time);
        DBHelper.getInstance().insert(DBConstants.T_TS_CONFIG_ICON.T_NAME, contentValues);
    }

    //获取当前Icon显示信息
    public static ConfigIcon getConfigIcon() {
        String sql = "select * from " + DBConstants.T_TS_CONFIG_ICON.T_NAME;
        sql += " order by " + DBConstants.T_TS_CONFIG_ICON.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        if (null != array && array.length() > 0) {
            return GsonUtils.fromJson(array.optJSONObject(0).toString(), ConfigIcon.class);
        }
        return null;
    }

    //清除Icon配置数据
    public static void clear() {
        DBHelper.getInstance().delete(DBConstants.T_TS_CONFIG_ICON.T_NAME, null, null);
    }
}
