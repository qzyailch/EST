package com.qian.ess.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.qian.ess.utils.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DBHelper {

    private static final String TAG = "DBHelper";

    private static DBHelper dbHelper = null;
    private SQLiteDatabase db = null;
    @SuppressLint("SdCardPath")
    private static String DB_PATH = "/data/data/com.qian.ess/databases/";
    private static String DB_NAME = "ess.db";
    private static final int dbVersion = 1; //数据库版本

    //二维数组放初始化数据库信息
    String[][] initTables = new String[][]{
            {DBConstants.T_TSOBJECT.T_NAME, DBConstants.T_TSOBJECT.T_COLUMNS_INIT},
            {DBConstants.T_TSSOLUTION.T_NAME, DBConstants.T_TSSOLUTION.T_COLUMNS_INIT},
            {DBConstants.T_FIRST_BASE_INFO.T_NAME, DBConstants.T_FIRST_BASE_INFO.T_COLUMNS_INIT},
            {DBConstants.T_TS_CONFIG_ICON.T_NAME, DBConstants.T_TS_CONFIG_ICON.T_COLUMNS_INIT},
            {DBConstants.T_TS_CONFIG_TC_LINKS_Doc.T_NAME, DBConstants.T_TS_CONFIG_TC_LINKS_Doc.T_COLUMNS_INIT},
            {DBConstants.T_TS_CONFIG_TC_LINKS_TCInfo.T_NAME, DBConstants.T_TS_CONFIG_TC_LINKS_TCInfo.T_COLUMNS_INIT},
            {DBConstants.T_TS_CONFIG_TS_LINKS_Doc.T_NAME, DBConstants.T_TS_CONFIG_TS_LINKS_Doc.T_COLUMNS_INIT},
            {DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.T_NAME, DBConstants.T_TS_CONFIG_TS_LINKS_TSLabels.T_COLUMNS_INIT},
            {DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.T_NAME, DBConstants.T_TS_CONFIG_TS_LINKS_TSInfo.T_COLUMNS_INIT},
            {DBConstants.T_TS_CONFIG_UI_Names.T_NAME, DBConstants.T_TS_CONFIG_UI_Names.T_COLUMNS_INIT},
            {DBConstants.T_TS_CONFIG_TS_UI_Names.T_NAME, DBConstants.T_TS_CONFIG_TS_UI_Names.T_COLUMNS_INIT},
            {DBConstants.T_TS_CONFIG_TC_UI_Names.T_NAME, DBConstants.T_TS_CONFIG_TC_UI_Names.T_COLUMNS_INIT},
            {DBConstants.T_TS_CONFIG_User_Level.T_NAME, DBConstants.T_TS_CONFIG_User_Level.T_COLUMNS_INIT},
            {DBConstants.T_TS_CONFIG_User_Member.T_NAME, DBConstants.T_TS_CONFIG_User_Member.T_COLUMNS_INIT},
            {DBConstants.T_PC_JOB.T_NAME, DBConstants.T_PC_JOB.T_COLUMNS_INIT},
            {DBConstants.T_PC_PROCESSCARD.T_NAME, DBConstants.T_PC_PROCESSCARD.T_COLUMNS_INIT},
            {DBConstants.T_PC_TECHNINALCARD.T_NAME, DBConstants.T_PC_TECHNINALCARD.T_COLUMNS_INIT}
    };

    //二维数组放更新数据库信息
    String[][] updateTables = new String[][]{

    };

    private DBHelper() {

    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    public SQLiteDatabase openDatabase() {
        SQLiteDatabase database = null;
        try {
            String databaseFilename = DB_PATH + DB_NAME;
            File dir = new File(DB_PATH);

            if (!dir.exists()) {
                dir.mkdirs();
            }
            File f = new File(databaseFilename);
            if (!f.exists()) {
                f.createNewFile();
            }
            database = SQLiteDatabase.openOrCreateDatabase(databaseFilename,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return database;
    }

    public boolean initDB() {
        boolean flag = false;
        try {
            db = this.openDatabase();
            if (db == null) {
                return flag;
            }
            db.beginTransaction();
            for (int i = 0, len = initTables.length; i < len; i++) {
                String table = initTables[i][0];
                String columns = initTables[i][1];
                String sql = "CREATE TABLE IF NOT EXISTS " + table + "(" + columns + ")";
                db.execSQL(sql);
            }

            /* 此处判断是由于新需求，需要用户再次覆盖安装时重新进入引导页，则会引发该方法即initDB()，
               避免到时又需要此需求，又需要更新数据库，会出现问题，数据库版本号更新了，但是数据库字段没更新，
               所以，加此判断，当获取到的数据库版本低于需要更新的版本(说明数据库已经初始化initDB())，但是又进入了此方法，则需要判断是否更新数据库）*/
            Logger.i(TAG, "initDB db version:" + db.getVersion());
            if (db.getVersion() >= 1 && db.getVersion() < dbVersion && updateTables.length > 0) {
                updateTables(db);
            } else {
                db.setVersion(dbVersion);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            flag = true;
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            if (db != null)
                db.close();
        }

        return flag;

    }

    public boolean insert(String tableName, JSONObject v) {
        try {
            db = this.openDatabase();
            if (db == null) {
                return false;
            }
            String[] oldColumns = getColumnNames(db, tableName).split(",");
            List<String> oldColumnsList = Arrays.asList(oldColumns);

            ContentValues values = new ContentValues();
            Iterator<?> it = v.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                String value = v.optString(key);
                if (!oldColumnsList.contains(key)) {
                    continue;
                }
                values.put(key, value);
            }
            db.insert(tableName, null, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null)
                db.close();
        }
        return false;
    }

    public boolean insert(String tableName, ContentValues values) {
        try {
            db = this.openDatabase();
            if (db == null) {
                return false;
            }
            db.insert(tableName, null, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null)
                db.close();
        }
        return false;
    }

    public boolean insertForBatch(String tableName, JSONArray vs) {
        boolean flag = false;
        try {
            db = this.openDatabase();
            if (db == null) {
                return flag;
            }
            String[] oldColumns = getColumnNames(db, tableName).split(",");
            List<String> oldColumnsList = Arrays.asList(oldColumns);

            db.beginTransaction();
            for (int i = 0; i < vs.length(); i++) {
                JSONObject v = vs.getJSONObject(i);
                ContentValues values = new ContentValues();
                Iterator<?> it = v.keys();
                while (it.hasNext()) {
                    String key = (String) it.next();
                    String value = v.optString(key);
                    if (!oldColumnsList.contains(key)) {
                        continue;
                    }
                    values.put(key, value);
                }
                db.insert(tableName, null, values);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            flag = true;
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            if (db != null)
                db.close();
        }
        return flag;
    }

    public boolean insertForMap(String tableName, HashMap<?, ?> parameter) {
        boolean flag = false;
        String columns = "", values = "";
        Iterator<?> it = parameter.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            columns += key + ",";
            values += parameter.get(key) + ",";
        }
        if (columns.endsWith(","))
            columns = columns.substring(0, columns.lastIndexOf(","));
        if (values.endsWith(","))
            values = values.substring(0, values.lastIndexOf(","));

        String sql = "insert into " + tableName + " (" + columns + ") values ("
                + values + ")";

        try {
            db = this.openDatabase();
            if (db == null)
                return flag;
            db.execSQL(sql);
            flag = true;
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            if (db != null)
                db.close();
        }
        return flag;
    }

    public int update(String tableName, ContentValues values,
                      String whereClause, String[] whereArgs) {
        int count = -1;
        try {
            db = this.openDatabase();
            if (db == null)
                return count;
            count = db.update(tableName, values, whereClause, whereArgs);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null)
                db.close();
        }
        return count;
    }

    public int delete(String tableName, String whereClause, String[] whereArgs) {
        int count = -1;
        try {
            db = this.openDatabase();
            if (db == null)
                return count;
            count = db.delete(tableName, whereClause, whereArgs);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null)
                db.close();
        }
        return count;
    }

    public boolean doForSql(String sql) {
        boolean flag = false;
        try {
            db = this.openDatabase();
            if (db == null) {
                return flag;
            }

            db.execSQL(sql);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (db != null)
                db.close();
        }
        return flag;
    }

    public String queryForSql(String sql, String[] selectionArgs) {
        Cursor cursor = null;
        try {
            db = this.openDatabase();
            if (db == null) {
                return "";
            }
            cursor = db.rawQuery(sql, selectionArgs);
            return cursorToJsonString(cursor);
        } catch (Exception e) {
            e.printStackTrace();
            return "e:" + e.getMessage();
        } finally {
            if (db != null && db.isOpen())
                db.close();
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
    }

    /**
     * 查询数据库表中的总条数.
     */
    public long queryCaseNum(String table, String where) {
        Cursor cursor = null;
        try {
            db = this.openDatabase();
            if (db == null) {
                return 0;
            }
            String sql = "select count(*) from " + table;
            if (!TextUtils.isEmpty(where)) {
                sql += where;
            }
            cursor = db.rawQuery(sql, null);
            cursor.moveToFirst();
            long count = cursor.getLong(0);
            cursor.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (db != null && db.isOpen())
                db.close();
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }

    }

    public JSONArray query(String sql) {
        Cursor cursor = null;
        try {
            db = this.openDatabase();
            if (db == null) {
                return null;
            }
            cursor = db.rawQuery(sql, null);
            return cursorToJsonArray(cursor);
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONArray();
        } finally {
            if (db != null && db.isOpen())
                db.close();
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
    }

    public JSONArray query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        Cursor cursor = null;
        try {
            db = this.openDatabase();
            if (db == null) {
                return null;
            }
            cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
            return cursorToJsonArray(cursor);
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONArray();
        } finally {
            if (db != null && db.isOpen())
                db.close();
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
    }

    private JSONArray cursorToJsonArray(Cursor c) throws JSONException {
        JSONArray ja = new JSONArray();
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                JSONObject jo = new JSONObject();
                for (int i = 0; i < c.getColumnCount(); i++) {
                    jo.put(c.getColumnName(i),
                            (c.getString(i) == null ? "" : c.getString(i)));
                }
                ja.put(jo);
                c.moveToNext();
            } while (!c.isAfterLast());
        }
        c.close();
        return ja;
    }

    private String cursorToJsonString(Cursor c) throws JSONException {
        JSONArray ja = new JSONArray();
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                JSONObject jo = new JSONObject();
                for (int i = 0; i < c.getColumnCount(); i++) {
                    jo.put(c.getColumnName(i),
                            (c.getString(i) == null ? "" : c.getString(i)));
                }
                ja.put(jo);
                c.moveToNext();
            } while (!c.isAfterLast());
        }
        c.close();
        return ja.toString();
    }

    public boolean checkUpdateDB() {
        try {
            db = this.openDatabase();
            Logger.i(TAG, "current db version is : " + db.getVersion() + " , update version is : " + dbVersion);
            return db.getVersion() < dbVersion && updateTables.length > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新数据库
     */
    public boolean updateTables() {
        boolean flag = false;
        try {
            db = this.openDatabase();
            db.beginTransaction();
            for (int i = 0, len = updateTables.length; i < len; i++) {
                String table = updateTables[i][0];
                String columns = initTables[i][1];
                if (!isExistsTable(db, table)) {
                    String sql = "CREATE TABLE IF NOT EXISTS " + table + "(" + columns + ")";
                    db.execSQL(sql);
                    continue;
                }
                String[] newColumns = columns.split(",");
                String[] oldColumns = getColumnNames(db, table).split(",");
                String[] finalColumns = exist(oldColumns, newColumns);
                for (int j = 0; j < finalColumns.length; j++) {
                    db.execSQL("ALTER TABLE " + table + " ADD COLUMN " + finalColumns[j]);
                }
            }
            db.setVersion(dbVersion);
            db.setTransactionSuccessful();
            db.endTransaction();
            flag = true;
            Logger.i(TAG, "update db success");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(TAG, "update db error");
        }
        return flag;
    }

    /**
     * 更新数据库，用于在initDB()调用以后，新需求
     */
    public boolean updateTables(SQLiteDatabase db) {
        boolean flag = false;
        try {
            for (int i = 0, len = updateTables.length; i < len; i++) {
                String table = updateTables[i][0];
                String columns = initTables[i][1];
                if (!isExistsTable(db, table)) {
                    String sql = "CREATE TABLE IF NOT EXISTS " + table + "(" + columns + ")";
                    db.execSQL(sql);
                    continue;
                }
                String[] newColumns = columns.split(",");
                String[] oldColumns = getColumnNames(db, table).split(",");
                String[] finalColumns = exist(oldColumns, newColumns);
                for (int j = 0; j < finalColumns.length; j++) {
                    db.execSQL("ALTER TABLE " + table + " ADD COLUMN " + finalColumns[j]);
                }
            }
            db.setVersion(dbVersion);
            flag = true;
            Logger.i(TAG, "update db success");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(TAG, "update db error");
        }
        return flag;
    }

    private boolean isExistsTable(SQLiteDatabase db, String tableName) {
        String sql = "select count(*) as c from sqlite_master where type ='table' and name ='" + tableName + "';";
        Cursor cursor = db.rawQuery(sql, null);
        boolean result = false;
        if (cursor.moveToNext()) {
            int count = cursor.getInt(0);
            if (count > 0) {
                result = true;
            }
        }

        return result;
    }

    public boolean clearDataBase() {
        boolean success = false;
        try {
            JSONArray names = query("select name from sqlite_master");
            for (int i = 0; i < names.length(); i++) {
                String name = names.optJSONObject(i).optString("name");
                if (name.equals("message")) {
                    continue;
                }
                if (name.equals("uuAddress")) {
                    continue;
                }
                doForSql("DELETE FROM " + name);
            }
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    private String getColumnNames(SQLiteDatabase db, String tableName) {
        StringBuffer sb = null;
        Cursor c = null;
        try {

            c = db.rawQuery("PRAGMA table_info(" + tableName + ")", null);
            if (null != c) {
                int columnIndex = c.getColumnIndex("name");
                if (-1 == columnIndex) {
                    return null;
                }

                int index = 0;
                sb = new StringBuffer(c.getCount());
                for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                    sb.append(c.getString(columnIndex));
                    sb.append(",");
                    index++;
                }
                sb = sb.deleteCharAt(sb.lastIndexOf(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return sb.toString();
    }

    private String[] exist(String[] page, String[] datas) {
        List<String> sList1 = Arrays.asList(page);//数组转换为List
        Set<String> set1 = new HashSet<String>(sList1);//List转换为Set
        Set<String> set2 = new HashSet<String>();
        for (int i = 0; i < datas.length; i++) {
            if (set1.add(datas[i])) {
                set2.add(datas[i]);
                //将其中一个的数据往Set中添加，
                //如果存在返回false,则已经存在
            }
        }
        String[] results = new String[set2.size()];
        results = set2.toArray(results);
        return results;
    }
}
