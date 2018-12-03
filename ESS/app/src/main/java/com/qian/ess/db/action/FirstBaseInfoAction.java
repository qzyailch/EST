package com.qian.ess.db.action;

import android.content.ContentValues;

import com.qian.ess.bean.config.FirstBaseInfo;
import com.qian.ess.db.DBConstants;
import com.qian.ess.db.DBHelper;

import org.json.JSONArray;

/**
 * Created by Administrator on 2018/11/20 0020.
 * <p>
 * FirstBaseInfo
 */

public class FirstBaseInfoAction {

    //添加或更新FirstBaseInfo第一次进入程序填写的信息
    public static void addOrUpdateFirstBaseInfo(FirstBaseInfo firstBaseInfo) {
        clear();
        long time = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_FIRST_BASE_INFO.COLUMN_BASE_ADDRESS, firstBaseInfo.getAddress());
        contentValues.put(DBConstants.T_FIRST_BASE_INFO.COLUMN_O_Name, firstBaseInfo.getObjectName());
        contentValues.put(DBConstants.T_FIRST_BASE_INFO.COLUMN_NoO, firstBaseInfo.getObjectNum());
        contentValues.put(DBConstants.T_FIRST_BASE_INFO.COLUMN_M_Name, firstBaseInfo.getComponentName());
        contentValues.put(DBConstants.T_FIRST_BASE_INFO.COLUMN_NoM1, firstBaseInfo.getComponentNum1());
        contentValues.put(DBConstants.T_FIRST_BASE_INFO.COLUMN_NoM2, firstBaseInfo.getComponentNum2());

        contentValues.put(DBConstants.T_FIRST_BASE_INFO.COLUMN_UPDATE_TIME, time);
        contentValues.put(DBConstants.T_FIRST_BASE_INFO.COLUMN_CREATE_TIME, time);
        DBHelper.getInstance().insert(DBConstants.T_FIRST_BASE_INFO.T_NAME, contentValues);
    }

    //获取当前FirstBaseInfo信息
    public static FirstBaseInfo getFirstBaseInfo() {
        String sql = "select * from " + DBConstants.T_FIRST_BASE_INFO.T_NAME;
        sql += " order by " + DBConstants.T_FIRST_BASE_INFO.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        if (null != array && array.length() > 0) {
            return FirstBaseInfo.fromJson(array.optJSONObject(0));
        }
        return null;
    }

    //清除FirstBaseInfo配置数据
    public static void clear() {
        DBHelper.getInstance().delete(DBConstants.T_FIRST_BASE_INFO.T_NAME, null, null);
    }
}
