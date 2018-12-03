package com.qian.ess.db.action.user;

import android.content.ContentValues;

import com.qian.ess.bean.config.user.UserLevel;
import com.qian.ess.db.DBConstants;
import com.qian.ess.db.DBHelper;
import com.qian.ess.utils.GsonUtils;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20 0020.
 * <p>
 * UserLevel
 */

public class UserLevelAction {

    //添加或更新UserLevel配置信息
    public static void addOrUpdateUserLevel(UserLevel userLevel) {
        long time = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TS_CONFIG_User_Level.COLUMN_LEVEL_ID, userLevel.getLevelId());
        contentValues.put(DBConstants.T_TS_CONFIG_User_Level.COLUMN_LEVEL_NAME_ENG, userLevel.getLevelNameEng());
        contentValues.put(DBConstants.T_TS_CONFIG_User_Level.COLUMN_LEVEL_NAME_CHN, userLevel.getLevelNameChn());

        contentValues.put(DBConstants.T_TS_CONFIG_User_Level.COLUMN_UPDATE_TIME, time);
        contentValues.put(DBConstants.T_TS_CONFIG_User_Level.COLUMN_CREATE_TIME, time);
        DBHelper.getInstance().insert(DBConstants.T_TS_CONFIG_User_Level.T_NAME, contentValues);
    }

    //查询全部UserLevel信息
    public static List<UserLevel> queryAllUserLevel() {
        String sql = "select * from " + DBConstants.T_TS_CONFIG_User_Level.T_NAME;
        sql += " order by " + DBConstants.T_TS_CONFIG_User_Level.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        return GsonUtils.toList(array.toString(), UserLevel.class);
    }

    //根据UserLevelId查询UserLevel信息
    public static UserLevel queryUserLevelById(int userLevelId) {
        String sql = "select * from " + DBConstants.T_TS_CONFIG_User_Level.T_NAME;
        if (userLevelId > 0) {
            sql += " where " + DBConstants.T_TS_CONFIG_User_Level.COLUMN_LEVEL_ID + " = " + userLevelId;
        }
        sql += " order by " + DBConstants.T_TS_CONFIG_User_Level.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        if (null != array && array.length() > 0) {
            return GsonUtils.fromJson(array.optJSONObject(0).toString(), UserLevel.class);
        }
        return null;
    }
}
