package com.qian.ess.db.action.user;

import android.content.ContentValues;

import com.qian.ess.bean.config.user.UserMember;
import com.qian.ess.db.DBConstants;
import com.qian.ess.db.DBHelper;
import com.qian.ess.utils.GsonUtils;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20 0020.
 * <p>
 * UserMember
 */

public class UserMemberAction {

    //添加或更新UserMember配置信息
    public static void addOrUpdateUserMember(UserMember userMember) {
        long time = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TS_CONFIG_User_Member.COLUMN_USER_LEVEL_ID, userMember.getUserLevelId());
        contentValues.put(DBConstants.T_TS_CONFIG_User_Member.COLUMN_USER_ID, userMember.getUserId());
        contentValues.put(DBConstants.T_TS_CONFIG_User_Member.COLUMN_USER_NAME, userMember.getUserName());
        contentValues.put(DBConstants.T_TS_CONFIG_User_Member.COLUMN_USER_PASSWORD, userMember.getUserPassword());

        contentValues.put(DBConstants.T_TS_CONFIG_User_Member.COLUMN_UPDATE_TIME, time);
        contentValues.put(DBConstants.T_TS_CONFIG_User_Member.COLUMN_CREATE_TIME, time);
        DBHelper.getInstance().insert(DBConstants.T_TS_CONFIG_User_Member.T_NAME, contentValues);
    }

    //查询全部UserMember信息
    public static List<UserMember> queryAllUserMember(int userLevelId) {
        String sql = "select * from " + DBConstants.T_TS_CONFIG_User_Member.T_NAME;
        if (userLevelId > 0) {
            sql += " where " + DBConstants.T_TS_CONFIG_User_Member.COLUMN_USER_LEVEL_ID + " = " + userLevelId;
        }
        sql += " order by " + DBConstants.T_TS_CONFIG_User_Member.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        return GsonUtils.toList(array.toString(), UserMember.class);
    }

    //根据UserId查询UserLevel信息
    public static UserMember queryUserMemberByUserId(int userId) {
        String sql = "select * from " + DBConstants.T_TS_CONFIG_User_Member.T_NAME;
        if (userId > 0) {
            sql += " where " + DBConstants.T_TS_CONFIG_User_Member.COLUMN_USER_ID + " = " + userId;
        }
        sql += " order by " + DBConstants.T_TS_CONFIG_User_Member.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        if (null != array && array.length() > 0) {
            return GsonUtils.fromJson(array.optJSONObject(0).toString(), UserMember.class);
        }
        return null;
    }
}
