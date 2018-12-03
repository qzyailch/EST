package com.qian.ess.db.action;

import android.content.ContentValues;
import android.text.TextUtils;

import com.qian.ess.bean.tc.PCJobObject;
import com.qian.ess.bean.tc.ProcessCard;
import com.qian.ess.bean.tc.TechninalCard;
import com.qian.ess.db.DBConstants;
import com.qian.ess.db.DBHelper;
import com.qian.ess.utils.GsonUtils;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/30 0030.
 * <p>
 * PC对象
 */

public class PCObjectAction {

    //添加或更新PCJobObject
    public static void addOrUpdatePCJobObject(PCJobObject pcJobObject, String fileName) {
        long time = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_PC_JOB.COLUMN_AIRCRAFT_NUMBER, pcJobObject.getAircraftNumber());
        contentValues.put(DBConstants.T_PC_JOB.COLUMN_ENGINE_NUMBER, pcJobObject.getEngineNumber());
        contentValues.put(DBConstants.T_PC_JOB.COLUMN_ENGINE_POSITION, pcJobObject.getEnginePosition());
        contentValues.put(DBConstants.T_PC_JOB.COLUMN_SENT_DATE_TIME, pcJobObject.getSentDateTime());
        contentValues.put(DBConstants.T_PC_JOB.COLUMN_BEGIN_DATE_TIME, pcJobObject.getBeginDateTime());
        contentValues.put(DBConstants.T_PC_JOB.COLUMN_FINISH_DATE_TIME, pcJobObject.getFinishDateTime());
        contentValues.put(DBConstants.T_PC_JOB.COLUMN_RECEIVED_DATE_TIME, pcJobObject.getReceivedDateTime());
        contentValues.put(DBConstants.T_PC_JOB.COLUMN_JOB_REPORT_PATH, pcJobObject.getJobReportPath());
        contentValues.put(DBConstants.T_PC_JOB.COLUMN_JOB_REPORT_DATE_TIME, pcJobObject.getJobReportDateTime());
        contentValues.put(DBConstants.T_PC_JOB.COLUMN_MAJOR, pcJobObject.getMajor());
        contentValues.put(DBConstants.T_PC_JOB.COLUMN_QCNAME, pcJobObject.getQcName());
        contentValues.put(DBConstants.T_PC_JOB.COLUMN_DIRECTOR_NAME, pcJobObject.getDirectorName());
        contentValues.put(DBConstants.T_PC_JOB.COLUMN_BASE_LOCATION, pcJobObject.getBaseLocation());
        contentValues.put(DBConstants.T_PC_JOB.COLUMN_CONCLUSION, pcJobObject.getConclusion());

        contentValues.put(DBConstants.T_PC_JOB.COLUMN_UPDATE_TIME, time);

        if (!TextUtils.isEmpty(pcJobObject.getPcReceiveFileName())) {
            DBHelper.getInstance().update(
                    DBConstants.T_PC_JOB.T_NAME,
                    contentValues,
                    DBConstants.T_PC_JOB.COLUMN_PC_RECEIVE_FILE_NAME + " = ?",
                    new String[]{pcJobObject.getPcReceiveFileName()}
            );
        } else {
            contentValues.put(DBConstants.T_TSOBJECT.COLUMN_CREATE_TIME, time);
            contentValues.put(DBConstants.T_PC_JOB.COLUMN_PC_RECEIVE_FILE_NAME, fileName);
            DBHelper.getInstance().insert(DBConstants.T_PC_JOB.T_NAME, contentValues);
        }

        ProcessCard processCard = pcJobObject.getProcessCard();
        if (null != processCard) {
            addOrUpdateProcessCard(processCard, fileName);
        }
    }

    //添加或更新ProcessCard
    public static void addOrUpdateProcessCard(ProcessCard processCard, String fileName) {
        long time = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_PC_PROCESSCARD.COLUMN_NUMBER, processCard.getNumber());
        contentValues.put(DBConstants.T_PC_PROCESSCARD.COLUMN_NAME, processCard.getName());

        contentValues.put(DBConstants.T_PC_PROCESSCARD.COLUMN_UPDATE_TIME, time);

        if (!TextUtils.isEmpty(processCard.getPcReceiveFileName())) {
            DBHelper.getInstance().update(
                    DBConstants.T_PC_PROCESSCARD.T_NAME,
                    contentValues,
                    DBConstants.T_PC_PROCESSCARD.COLUMN_PC_RECEIVE_FILE_NAME + " = ?",
                    new String[]{processCard.getPcReceiveFileName()}
            );
        } else {
            contentValues.put(DBConstants.T_PC_PROCESSCARD.COLUMN_CREATE_TIME, time);
            contentValues.put(DBConstants.T_PC_PROCESSCARD.COLUMN_PC_RECEIVE_FILE_NAME, fileName);
            DBHelper.getInstance().insert(DBConstants.T_PC_PROCESSCARD.T_NAME, contentValues);
        }

        List<TechninalCard> techninalCards = processCard.getTechninalCards();
        if (null != techninalCards && techninalCards.size() > 0) {
            for (TechninalCard techninalCard : techninalCards) {
                addOrUpdateTechninalCard(techninalCard, fileName);
            }
        }
    }

    //添加或更新TechninalCard
    public static void addOrUpdateTechninalCard(TechninalCard techninalCard, String fileName) {
        long time = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_NUMBER, techninalCard.getNumber());
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_NAME, techninalCard.getName());
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_PAGE, techninalCard.getPage());
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_RESULT_VALUE, techninalCard.getResultValue());
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_WORKER_NAME, techninalCard.getWorkerName());
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_REVIEWER_NAME, techninalCard.getReviewerName());
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_REMARK, techninalCard.getRemark());
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_HOURS, techninalCard.getHours());
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_OPERATIONS, listToString(techninalCard.getOperations()));
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_ACTIONS, listToString(techninalCard.getActions()));
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_EQUIPMENT, listToString(techninalCard.getEquipment()));
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_TOOLS, listToString(techninalCard.getTools()));
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_MATERIALS, listToString(techninalCard.getMaterials()));
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_PHOTO_PATH_LIST, listToString(techninalCard.getPhotoPathList()));
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_VIDEO_PATH_LIST, listToString(techninalCard.getVideoPathList()));
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_AUDIO_PATH_LIST, listToString(techninalCard.getAudioPathList()));
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_BEGIN_DATE_TIME, techninalCard.getBeginDateTime());
        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_FINISH_DATE_TIME, techninalCard.getFinishDateTime());

        contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_UPDATE_TIME, time);

        if (!TextUtils.isEmpty(techninalCard.getPcReceiveFileName())) {
            DBHelper.getInstance().update(
                    DBConstants.T_PC_TECHNINALCARD.T_NAME,
                    contentValues,
                    DBConstants.T_PC_TECHNINALCARD.COLUMN_PC_RECEIVE_FILE_NAME + " = ?",
                    new String[]{techninalCard.getPcReceiveFileName()}
            );
        } else {
            contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_CREATE_TIME, time);
            contentValues.put(DBConstants.T_PC_TECHNINALCARD.COLUMN_PC_RECEIVE_FILE_NAME, fileName);
            DBHelper.getInstance().insert(DBConstants.T_PC_TECHNINALCARD.T_NAME, contentValues);
        }
    }

    public static void clearOldPCObject(String fileName) {
        DBHelper.getInstance().delete(DBConstants.T_PC_JOB.T_NAME, DBConstants.T_PC_JOB.COLUMN_PC_RECEIVE_FILE_NAME + " = ?", new String[]{fileName});
        DBHelper.getInstance().delete(DBConstants.T_PC_PROCESSCARD.T_NAME, DBConstants.T_PC_PROCESSCARD.COLUMN_PC_RECEIVE_FILE_NAME + " = ?", new String[]{fileName});
        DBHelper.getInstance().delete(DBConstants.T_PC_TECHNINALCARD.T_NAME, DBConstants.T_PC_TECHNINALCARD.COLUMN_PC_RECEIVE_FILE_NAME + " = ?", new String[]{fileName});
    }

    //获取PCJobObject
    public static PCJobObject getPCJobObjectByFileName(String fileName) {
        String sql = "select * from " + DBConstants.T_PC_JOB.T_NAME;
        if (!TextUtils.isEmpty(fileName)) {
            sql += " where " + DBConstants.T_PC_JOB.COLUMN_PC_RECEIVE_FILE_NAME + " = '" + fileName + "'";
        }
        sql += " order by " + DBConstants.T_PC_JOB.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        if (null != array && array.length() > 0) {
            PCJobObject pcJobObject = GsonUtils.fromJson(array.optJSONObject(0).toString(), PCJobObject.class);
            JSONArray pcArray = DBHelper.getInstance().query("select * from " + DBConstants.T_PC_PROCESSCARD.T_NAME + " order by " + DBConstants.T_PC_JOB.COLUMN_UPDATE_TIME + " desc");
            if (null != pcArray && pcArray.length() > 0) {
                ProcessCard processCard = GsonUtils.fromJson(pcArray.optJSONObject(0).toString(), ProcessCard.class);
                pcJobObject.setProcessCard(processCard);
            }
            return pcJobObject;
        }
        return null;
    }

    //获取所有的PCJobObject
    public static List<PCJobObject> getAllPCJobObject() {
        String sql = "select * from " + DBConstants.T_PC_JOB.T_NAME;
        sql += " order by " + DBConstants.T_PC_JOB.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        List<PCJobObject> list = new ArrayList<>();
        if (null != array && array.length() > 0) {
            for (int i = 0, len = array.length(); i < len; i++) {
                PCJobObject pcJobObject = GsonUtils.fromJson(array.optJSONObject(i).toString(), PCJobObject.class);
                JSONArray pcArray = DBHelper.getInstance().query("select * from " + DBConstants.T_PC_PROCESSCARD.T_NAME + " order by " + DBConstants.T_PC_JOB.COLUMN_UPDATE_TIME + " desc");
                if (null != pcArray && pcArray.length() > 0) {
                    ProcessCard processCard = GsonUtils.fromJson(pcArray.optJSONObject(0).toString(), ProcessCard.class);
                    pcJobObject.setProcessCard(processCard);
                }
                list.add(pcJobObject);
            }
        }
        return list;
    }

    //根据fileName获取下面的TC，技术卡片
    public static List<TechninalCard> getAllTechninalCardByFileName(String fileName) {
        String sql = "select * from " + DBConstants.T_PC_TECHNINALCARD.T_NAME;
        if (!TextUtils.isEmpty(fileName)) {
            sql += " where " + DBConstants.T_PC_TECHNINALCARD.COLUMN_PC_RECEIVE_FILE_NAME + " = '" + fileName + "'";
        }
        sql += " order by " + DBConstants.T_PC_TECHNINALCARD.COLUMN_NUMBER + " asc";
        JSONArray array = DBHelper.getInstance().query(sql);
        List<TechninalCard> list = new ArrayList<>();
        if (null != array && array.length() > 0) {
            for (int i = 0, len = array.length(); i < len; i++) {
                list.add(TechninalCard.fromJson(array.optJSONObject(i)));
            }
        }
        return list;
    }

    //list数据转成逗号隔开的字符串，方便数据库存储
    private static String listToString(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        if (null != list && list.size() > 0) {
            for (int i = 0, len = list.size(); i < len; i++) {
                stringBuffer.append(",").append(list.get(i));
            }
        }
        if (!TextUtils.isEmpty(stringBuffer.toString())) {
            return stringBuffer.toString().substring(1);
        }

        return "";
    }
}
