package com.qian.ess.db.action;

import android.content.ContentValues;
import android.text.TextUtils;

import com.qian.ess.bean.config.ui.TSUIName;
import com.qian.ess.bean.ts.TSObject;
import com.qian.ess.bean.ts.TSSolution;
import com.qian.ess.db.DBConstants;
import com.qian.ess.db.DBHelper;
import com.qian.ess.utils.LanguageUtils;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/11/13 0013.
 * <p>
 * TSObject
 */

public class TSObjectAction {

    //添加或更新TSObject
    public static void addOrUpdateTSObject(TSObject tsObject, String fileName) {
        long time = System.currentTimeMillis();
        String randomId = UUID.randomUUID().toString().replace("-", "");

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_ID_LABLE, tsObject.getTsIDLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_WORK_LOCATION_LABLE, tsObject.getWorkLocationLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_NUMBER_LABLE, tsObject.getTsNumberLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_SHORT_NAME_LABLE, tsObject.getTsShortNameLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_FULL_NAME_LABLE, tsObject.getTsFullNameLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_POSITION_LABLE, tsObject.getTsPositionLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_OBJECT_MODEL_LABLE, tsObject.getObjectModelLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_OBJECT_NUMBER_LABLE, tsObject.getObjectNumberLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_PART_NUMBER_LABLE, tsObject.getPartNumberLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_PART_POSITION_LABLE, tsObject.getPartPositionLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_PERSON_IN_CHARGE_LABLE, tsObject.getTsPersonInChargeLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_WORKER_NAME_LABLE, tsObject.getTsWorkerNameLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_AUDITOR_LABLE, tsObject.getTsAuditorLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_DELIVER_DATETIME_LABLE, tsObject.getTsDeliverDateTimeLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_BEGIN_DATETIME_LABLE, tsObject.getTsBeginDateTimeLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_FINISH_DATETIME_LABLE, tsObject.getTsFinishDateTimeLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_SOLUTION_NUMBER_LABLE, tsObject.getTsSolutionNumberLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_SOLUTIONS_LABLE, tsObject.getTsSolutionsLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_POSSIBLE_REASON_LABLE, tsObject.getTsPossibleReasonLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_IS_USED_LABLE, tsObject.getTsIsUsedLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_IS_WORK_LABLE, tsObject.getTsIsWorkLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_REAL_REASON_LABLE, tsObject.getRealReasonLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_REMARK_LABLE, tsObject.getRemarkLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_PICTURE_LABLE, tsObject.getPictureLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_VIDEO_LABLE, tsObject.getVideoLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_AUDIO_LABLE, tsObject.getAudioLable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_PICTURE_LIST_LABLE, listToString(tsObject.getPictureListLable()));
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_VIDEO_LIST_LABLE, listToString(tsObject.getVideoListLable()));
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_AUDIO_LIST_LABLE, listToString(tsObject.getAudioListLable()));
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER1LABLE, tsObject.getReserveTsParameter1Lable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER2LABLE, tsObject.getReserveTsParameter2Lable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER3LABLE, tsObject.getReserveTsParameter3Lable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER4LABLE, tsObject.getReserveTsParameter4Lable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER5LABLE, tsObject.getReserveTsParameter5Lable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER6LABLE, tsObject.getReserveTsParameter6Lable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER7LABLE, tsObject.getReserveTsParameter7Lable());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER8LABLE, tsObject.getReserveTsParameter8Lable());

        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_IS_ADD_CUSTOM_FUN, tsObject.getIsAddCustomFun());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_ID, TextUtils.isEmpty(tsObject.getTsID()) ? randomId : tsObject.getTsID());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_WORK_LOCATION, tsObject.getWorkLocation());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_NUMBER, tsObject.getTsNumber());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_SHORT_NAME, tsObject.getTsShortName());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_FULL_NAME, tsObject.getTsFullName());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_POSITION, tsObject.getTsPosition());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_OBJECT_MODEL, tsObject.getObjectModel());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_OBJECT_NUMBER, tsObject.getObjectNumber());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_PART_NUMBER, tsObject.getPartNumber());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_PART_POSITION, tsObject.getPartPosition());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_PERSON_IN_CHARGE, tsObject.getTsPersonInCharge());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_WORKER_NAME, tsObject.getTsWorkerName());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_AUDITOR, tsObject.getTsAuditor());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_DELIVER_DATETIME, tsObject.getTsDeliverDateTime());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_BEGIN_DATETIME, tsObject.getTsBeginDateTime());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_FINISH_DATETIME, tsObject.getTsFinishDateTime());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_REAL_REASON, tsObject.getRealReason());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_REMARK, tsObject.getRemark());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_PICTURE_LIST, listToString(tsObject.getPictureList()));
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_VIDEO_LIST, listToString(tsObject.getVideoList()));
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_AUDIO_LIST, listToString(tsObject.getAudioList()));
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER1, tsObject.getReserveTsParameter1());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER2, tsObject.getReserveTsParameter2());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER3, tsObject.getReserveTsParameter3());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER4, tsObject.getReserveTsParameter4());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER5, tsObject.getReserveTsParameter5());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER6, tsObject.getReserveTsParameter6());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER7, tsObject.getReserveTsParameter7());
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER8, tsObject.getReserveTsParameter8());

        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_UPDATE_TIME, time);

        if (!TextUtils.isEmpty(tsObject.getFileName())) {
            DBHelper.getInstance().update(
                    DBConstants.T_TSOBJECT.T_NAME,
                    contentValues,
                    DBConstants.T_TSOBJECT.COLUMN_TS_RECEIVE_FILE_NAME + " = ?",
                    new String[]{tsObject.getFileName()}
            );
        } else {
            contentValues.put(DBConstants.T_TSOBJECT.COLUMN_CREATE_TIME, time);
            contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_RECEIVE_FILE_NAME, fileName);
            DBHelper.getInstance().insert(DBConstants.T_TSOBJECT.T_NAME, contentValues);
        }

        List<TSSolution> solutions = tsObject.getTsSolutions();
        if (null != solutions && solutions.size() > 0) {
            for (TSSolution solution : solutions) {
                addOrUpdateTSSolution(solution, TextUtils.isEmpty(tsObject.getTsID()) ? randomId : tsObject.getTsID(), fileName);
            }
        }
    }

    //添加或更新TSSolution
    public static void addOrUpdateTSSolution(TSSolution tsSolution, String tsId, String fileName) {
        long time = System.currentTimeMillis();
        String randomId = UUID.randomUUID().toString().replace("-", "");

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_ID_LABLE, tsSolution.getTsSolutionIDLable());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_ID_LABLE, tsSolution.getTsIDLable());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_SHORT_NAME_LABLE, tsSolution.getTsShortNameLable());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_NUMBER_LABLE, tsSolution.getTsSolutionNumberLable());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_NAME_LABLE, tsSolution.getTsSolutionNameLable());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_POSSIBLE_REASON_LABLE, tsSolution.getPossibleReasonLable());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_IS_USED_LABLE, tsSolution.getIsUsedLable());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_IS_WORK_LABLE, tsSolution.getIsWorkLable());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_PICTURE_LIST_LABLE, listToString(tsSolution.getPictureListLable()));
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_VIDEO_LIST_LABLE, listToString(tsSolution.getVideoListLable()));
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_AUDIO_LIST_LABLE, listToString(tsSolution.getAudioListLable()));
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER1LABLE, tsSolution.getReserveTssParameter1Lable());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER2LABLE, tsSolution.getReserveTssParameter2Lable());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER3LABLE, tsSolution.getReserveTssParameter3Lable());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER4LABLE, tsSolution.getReserveTssParameter4Lable());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER5LABLE, tsSolution.getReserveTssParameter5Lable());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER6LABLE, tsSolution.getReserveTssParameter6Lable());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER7LABLE, tsSolution.getReserveTssParameter7Lable());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER8LABLE, tsSolution.getReserveTssParameter8Lable());

        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_ID, tsId);
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_SHORT_NAME, tsSolution.getTsShortName());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_NUMBER, tsSolution.getTsSolutionNumber());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_NAME, tsSolution.getTsSolutionName());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_POSSIBLE_REASON, tsSolution.getPossibleReason());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_DESCRIPTION, tsSolution.getTsDescription());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION, tsSolution.getTsSolution());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_REMARK, tsSolution.getTsRemark());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_IS_USED, tsSolution.getIsUsed());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_IS_WORK, tsSolution.getIsWork());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_PICTURE_LIST, listToString(tsSolution.getPictureList()));
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_VIDEO_LIST, listToString(tsSolution.getVideoList()));
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_AUDIO_LIST, listToString(tsSolution.getAudioList()));
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER1, tsSolution.getReserveTssParameter1());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER2, tsSolution.getReserveTssParameter2());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER3, tsSolution.getReserveTssParameter3());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER4, tsSolution.getReserveTssParameter4());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER5, tsSolution.getReserveTssParameter5());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER6, tsSolution.getReserveTssParameter6());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER7, tsSolution.getReserveTssParameter7());
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER8, tsSolution.getReserveTssParameter8());

        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_UPDATE_TIME, time);
        if (!TextUtils.isEmpty(tsSolution.getTsSolutionID()) && !TextUtils.isEmpty(tsSolution.getFileName())) {
//            if (!TextUtils.isEmpty(fileName)) {
//                contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_RECEIVE_FILE_NAME, fileName);
//            }
            DBHelper.getInstance().update(
                    DBConstants.T_TSSOLUTION.T_NAME,
                    contentValues,
                    DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_ID + " = ?",
                    new String[]{tsSolution.getTsSolutionID()}
            );
        } else {
            contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_CREATE_TIME, time);
            contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_ID, TextUtils.isEmpty(tsSolution.getTsSolutionID()) ? randomId : tsSolution.getTsSolutionID());
            contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_RECEIVE_FILE_NAME, fileName);
            DBHelper.getInstance().insert(DBConstants.T_TSSOLUTION.T_NAME, contentValues);
        }
    }

    public static void clearOldObject(String fileName) {
        DBHelper.getInstance().delete(DBConstants.T_TSOBJECT.T_NAME, DBConstants.T_TSOBJECT.COLUMN_TS_RECEIVE_FILE_NAME + " = ?", new String[]{fileName});
        DBHelper.getInstance().delete(DBConstants.T_TSSOLUTION.T_NAME, DBConstants.T_TSSOLUTION.COLUMN_TS_RECEIVE_FILE_NAME + " = ?", new String[]{fileName});
    }

    //获取真实显示在表格上的备注
    public static String getShowRemark(String tsId, List<TSSolution> list) {
        if (null == list || list.size() == 0) {
            list = getAllTSSolutionByTsId(tsId);
        }
        //备注单独处理，当某一个TSSolution的IsWord为true时，
        //判断是否为编号1,2,3,是则显示TSObject（这里先处理成显示TSSolution的备注）的备注，
        //否则显示TSSolution的“故障描述:xxx；解决方案：xxx；备注：xxx”，否则
        String showRemark = "";
        if (list.size() > 0) {
            for (int i = 0, len = list.size(); i < len; i++) {
                TSSolution tsSolution = list.get(i);
                if (tsSolution.getIsWork()) {
                    if (tsSolution.getTsSolutionNumber() > 3) {
                        TSUIName tsuiName = UINameAction.getTSUINameByLanguage(LanguageUtils.getlanguage());
                        if (null != tsuiName) {
                            String tsDescription = tsSolution.getTsDescription();
                            String tsSolutionStr = tsSolution.getTsSolution();
                            String tsRemark = tsSolution.getTsRemark();
                            if (!TextUtils.isEmpty(tsDescription)) {
                                showRemark = tsuiName.getDescriptionLabel() + ":" + tsDescription;
                            }
                            if (!TextUtils.isEmpty(tsSolutionStr)) {
                                if (!TextUtils.isEmpty(showRemark)) {
                                    showRemark += ";\n";
                                }
                                showRemark += tsuiName.getSolutionLabel() + ":" + tsSolutionStr;
                            }
                            if (!TextUtils.isEmpty(tsRemark)) {
                                if (!TextUtils.isEmpty(showRemark)) {
                                    showRemark += ";\n";
                                }
                                showRemark += tsRemark;
                            }
                        }
                    } else {
                        showRemark = tsSolution.getTsRemark();
                    }

                    break;
                }
            }
        }

        return showRemark;
    }

    //获取真实需要修改的备注
    public static String getShowEditRemark(String tsId, List<TSSolution> list) {
        if (null == list || list.size() == 0) {
            list = getAllTSSolutionByTsId(tsId);
        }
        //备注单独处理，当某一个TSSolution的IsWord为true时，
        //判断是否为编号1,2,3,是则显示TSObject（这里先处理成显示TSSolution的备注）的备注，
        //否则显示TSSolution的“故障描述:xxx；解决方案：xxx；备注：xxx”，否则
        String showRemark = "";
        if (list.size() > 0) {
            for (int i = 0, len = list.size(); i < len; i++) {
                TSSolution tsSolution = list.get(i);
                if (tsSolution.getIsWork()) {
                    showRemark = tsSolution.getTsRemark();
                    break;
                }
            }
        }

        return showRemark;
    }

    //更新TSObject的主管
    public static void updateTSObjectPersonInCharge(String tsId, String tsPersonInCharge) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_PERSON_IN_CHARGE, tsPersonInCharge);
        DBHelper.getInstance().update(
                DBConstants.T_TSOBJECT.T_NAME,
                contentValues,
                DBConstants.T_TSOBJECT.COLUMN_TS_ID + " = ?",
                new String[]{tsId}
        );
    }

    //更新TSObject的审核者
    public static void updateTSObjectAuditor(String tsId, String tsAuditor) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_AUDITOR, tsAuditor);
        DBHelper.getInstance().update(
                DBConstants.T_TSOBJECT.T_NAME,
                contentValues,
                DBConstants.T_TSOBJECT.COLUMN_TS_ID + " = ?",
                new String[]{tsId}
        );
    }

    //更新TSObject的操作者
    public static void updateTSObjectWorker(String tsId, String tsWorker) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_WORKER_NAME, tsWorker);
        DBHelper.getInstance().update(
                DBConstants.T_TSOBJECT.T_NAME,
                contentValues,
                DBConstants.T_TSOBJECT.COLUMN_TS_ID + " = ?",
                new String[]{tsId}
        );
    }

    //更新TSObject的备注
    public static void updateTSObjectRemark(String tsId, String remark) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_REMARK, remark);
        DBHelper.getInstance().update(
                DBConstants.T_TSOBJECT.T_NAME,
                contentValues,
                DBConstants.T_TSOBJECT.COLUMN_TS_ID + " = ?",
                new String[]{tsId}
        );
    }

    //更新TSObject的真实原因
    public static void updateTSObjectRealReason(String tsId, String realReason) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_REAL_REASON, realReason);
        DBHelper.getInstance().update(
                DBConstants.T_TSOBJECT.T_NAME,
                contentValues,
                DBConstants.T_TSOBJECT.COLUMN_TS_ID + " = ?",
                new String[]{tsId}
        );
    }

    //更新TSObject的故障位置
    public static void updateTSObjectTsPosition(String tsId, String tsPosition) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TSOBJECT.COLUMN_TS_POSITION, tsPosition);
        DBHelper.getInstance().update(
                DBConstants.T_TSOBJECT.T_NAME,
                contentValues,
                DBConstants.T_TSOBJECT.COLUMN_TS_ID + " = ?",
                new String[]{tsId}
        );
    }

    //更新TSObject的图片
    public static void updateTSObjectPic(String tsId, String pic) {
        TSObject tsObject = getTSObjectByTsId(tsId);
        if (null != tsObject) {
            List<String> picList = tsObject.getPictureList();
            picList.add("/Picture/" + pic);

            ContentValues contentValues = new ContentValues();
            contentValues.put(DBConstants.T_TSOBJECT.COLUMN_PICTURE_LIST, listToString(picList));
            DBHelper.getInstance().update(
                    DBConstants.T_TSOBJECT.T_NAME,
                    contentValues,
                    DBConstants.T_TSOBJECT.COLUMN_TS_ID + " = ?",
                    new String[]{tsId}
            );
        }
    }

    //更新TSObject的图片
    public static void updateTSObjectPic(String tsId, List<String> pics) {
        TSObject tsObject = getTSObjectByTsId(tsId);
        if (null != tsObject) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBConstants.T_TSOBJECT.COLUMN_PICTURE_LIST, listToString(pics));
            DBHelper.getInstance().update(
                    DBConstants.T_TSOBJECT.T_NAME,
                    contentValues,
                    DBConstants.T_TSOBJECT.COLUMN_TS_ID + " = ?",
                    new String[]{tsId}
            );
        }
    }

    //更新TSObject的视频
    public static void updateTSObjectVideo(String tsId, String video) {
        TSObject tsObject = getTSObjectByTsId(tsId);
        if (null != tsObject) {
            List<String> videoList = tsObject.getVideoList();
            videoList.add("/Video/" + video);

            ContentValues contentValues = new ContentValues();
            contentValues.put(DBConstants.T_TSOBJECT.COLUMN_VIDEO_LIST, listToString(videoList));
            DBHelper.getInstance().update(
                    DBConstants.T_TSOBJECT.T_NAME,
                    contentValues,
                    DBConstants.T_TSOBJECT.COLUMN_TS_ID + " = ?",
                    new String[]{tsId}
            );
        }
    }

    //更新TSObject的视频
    public static void updateTSObjectVideo(String tsId, List<String> videos) {
        TSObject tsObject = getTSObjectByTsId(tsId);
        if (null != tsObject) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBConstants.T_TSOBJECT.COLUMN_VIDEO_LIST, listToString(videos));
            DBHelper.getInstance().update(
                    DBConstants.T_TSOBJECT.T_NAME,
                    contentValues,
                    DBConstants.T_TSOBJECT.COLUMN_TS_ID + " = ?",
                    new String[]{tsId}
            );
        }
    }

    //更新TSObject的音频
    public static void updateTSObjectAudio(String tsId, String audio) {
        TSObject tsObject = getTSObjectByTsId(tsId);
        if (null != tsObject) {
            List<String> audioList = tsObject.getAudioList();
            audioList.add("/Audio/" + audio);

            ContentValues contentValues = new ContentValues();
            contentValues.put(DBConstants.T_TSOBJECT.COLUMN_AUDIO_LIST, listToString(audioList));
            DBHelper.getInstance().update(
                    DBConstants.T_TSOBJECT.T_NAME,
                    contentValues,
                    DBConstants.T_TSOBJECT.COLUMN_TS_ID + " = ?",
                    new String[]{tsId}
            );
        }
    }

    //更新TSObject的音频
    public static void updateTSObjectAudio(String tsId, List<String> audio) {
        TSObject tsObject = getTSObjectByTsId(tsId);
        if (null != tsObject) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBConstants.T_TSOBJECT.COLUMN_AUDIO_LIST, listToString(audio));
            DBHelper.getInstance().update(
                    DBConstants.T_TSOBJECT.T_NAME,
                    contentValues,
                    DBConstants.T_TSOBJECT.COLUMN_TS_ID + " = ?",
                    new String[]{tsId}
            );
        }
    }

    //获取TSObject
    public static TSObject getTSObjectByTsId(String tsId) {
        String sql = "select * from " + DBConstants.T_TSOBJECT.T_NAME;
        if (!TextUtils.isEmpty(tsId)) {
            sql += " where " + DBConstants.T_TSOBJECT.COLUMN_TS_ID + " = '" + tsId + "'";
        }
        sql += " order by " + DBConstants.T_TSOBJECT.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        if (null != array && array.length() > 0) {
            return TSObject.fromJson(array.optJSONObject(0));
        }
        return null;
    }

    //获取TSObject
    public static TSObject getTSObjectByFileName(String fileName) {
        String sql = "select * from " + DBConstants.T_TSOBJECT.T_NAME;
        if (!TextUtils.isEmpty(fileName)) {
            sql += " where " + DBConstants.T_TSOBJECT.COLUMN_TS_RECEIVE_FILE_NAME + " = '" + fileName + "'";
        }
        sql += " order by " + DBConstants.T_TSOBJECT.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        if (null != array && array.length() > 0) {
            return TSObject.fromJson(array.optJSONObject(0));
        }
        return null;
    }

    //获取所有的TSObject
    public static List<TSObject> getAllTSObject() {
        String sql = "select * from " + DBConstants.T_TSOBJECT.T_NAME;
        sql += " order by " + DBConstants.T_TSOBJECT.COLUMN_UPDATE_TIME + " desc";
        JSONArray array = DBHelper.getInstance().query(sql);
        List<TSObject> list = new ArrayList<>();
        if (null != array && array.length() > 0) {
            for (int i = 0, len = array.length(); i < len; i++) {
                list.add(TSObject.fromJson(array.optJSONObject(i)));
            }
        }
        return list;
    }

    //根据tsId获取下面的TSSolution
    public static List<TSSolution> getAllTSSolutionByTsId(String tsId) {
        String sql = "select * from " + DBConstants.T_TSSOLUTION.T_NAME;
        if (!TextUtils.isEmpty(tsId)) {
            sql += " where " + DBConstants.T_TSSOLUTION.COLUMN_TS_ID + " = '" + tsId + "'";
        }
        sql += " order by " + DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_NUMBER + " asc";
        JSONArray array = DBHelper.getInstance().query(sql);
        List<TSSolution> list = new ArrayList<>();
        if (null != array && array.length() > 0) {
            for (int i = 0, len = array.length(); i < len; i++) {
                list.add(TSSolution.fromJson(array.optJSONObject(i)));
            }
        }
        return list;
    }

    //获取TSSolution
    public static TSSolution getTSSolutionByTsIdAndTsSolutionId(String tsId, String tsSolutionId) {
        String sql = "select * from " + DBConstants.T_TSSOLUTION.T_NAME;
        if (!TextUtils.isEmpty(tsId)) {
            sql += " where " + DBConstants.T_TSSOLUTION.COLUMN_TS_ID + " = '" + tsId + "'";
        }
        if (!TextUtils.isEmpty(tsSolutionId)) {
            if (sql.contains("where")) {
                sql += " and ";
            } else {
                sql += " where ";
            }
            sql += DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_ID + " = '" + tsSolutionId + "'";
        }
        sql += " order by " + DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_NUMBER + " asc";
        JSONArray array = DBHelper.getInstance().query(sql);
        if (null != array && array.length() > 0) {
            return TSSolution.fromJson(array.optJSONObject(0));
        }
        return null;
    }

    //更新TSSolution的备注
    public static void updateTSSolutionRemark(String tsId, String tsSolutionID, String remark) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_REMARK, remark);
        DBHelper.getInstance().update(
                DBConstants.T_TSSOLUTION.T_NAME,
                contentValues,
                DBConstants.T_TSSOLUTION.COLUMN_TS_ID + " = ? and "
                        + DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_ID + " = ?",
                new String[]{tsId, tsSolutionID}
        );
    }

    //更新TSSolution的解决办法
    public static void updateTSSolutionSolution(String tsId, String tsSolutionID, String tsSolution) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION, tsSolution);
        DBHelper.getInstance().update(
                DBConstants.T_TSSOLUTION.T_NAME,
                contentValues,
                DBConstants.T_TSSOLUTION.COLUMN_TS_ID + " = ? and "
                        + DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_ID + " = ?",
                new String[]{tsId, tsSolutionID}
        );
    }

    //更新TSSolution的故障描述
    public static void updateTSSolutionDesc(String tsId, String tsSolutionID, String tsDescription) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_DESCRIPTION, tsDescription);
        DBHelper.getInstance().update(
                DBConstants.T_TSSOLUTION.T_NAME,
                contentValues,
                DBConstants.T_TSSOLUTION.COLUMN_TS_ID + " = ? and "
                        + DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_ID + " = ?",
                new String[]{tsId, tsSolutionID}
        );
    }

    //更新所有的TSSolution是否使用
    public static void updateTSSolutionIsUse(String tsId, String tsSolutionID, boolean isUse) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_IS_USED, isUse);
        DBHelper.getInstance().update(
                DBConstants.T_TSSOLUTION.T_NAME,
                contentValues,
                DBConstants.T_TSSOLUTION.COLUMN_TS_ID + " = ? and "
                        + DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_ID + " = ?",
                new String[]{tsId, tsSolutionID}
        );
    }

    //更新所有的TSSolution是否起作用
    public static void updateTSSolutionIsWork(String tsId, String tsSolutionID, boolean isWork) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_IS_WORK, false);

        //首先更新所有为false
        DBHelper.getInstance().update(
                DBConstants.T_TSSOLUTION.T_NAME,
                contentValues,
                DBConstants.T_TSSOLUTION.COLUMN_TS_ID + " = ?",
                new String[]{tsId}
        );

        //是否更新为true
        if (isWork) {
            contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_IS_WORK, true);
            DBHelper.getInstance().update(
                    DBConstants.T_TSSOLUTION.T_NAME,
                    contentValues,
                    DBConstants.T_TSSOLUTION.COLUMN_TS_ID + " = ? and "
                            + DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_ID + " = ?",
                    new String[]{tsId, tsSolutionID}
            );
        }
    }

    //新增自定义排故方法
    public static boolean addCustomSolution(String tsId, String fileName, String solutionName, String position,
                                            String reason, String desc, String solution, String remark) {

        long time = System.currentTimeMillis();
        String randomId = UUID.randomUUID().toString().replace("-", "");

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_IS_WORK, false);

        //首先更新所有为false
        DBHelper.getInstance().update(
                DBConstants.T_TSSOLUTION.T_NAME,
                contentValues,
                DBConstants.T_TSSOLUTION.COLUMN_TS_ID + " = ?",
                new String[]{tsId}
        );

        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_IS_USED, true);
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_IS_WORK, true);
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_ID, tsId);
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_RECEIVE_FILE_NAME, fileName);
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_NUMBER, DBHelper.getInstance().queryCaseNum(DBConstants.T_TSSOLUTION.T_NAME, " where " + DBConstants.T_TSOBJECT.COLUMN_TS_ID + " = '" + tsId + "'") + 1);
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_POSSIBLE_REASON, reason);
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_NAME, solutionName);
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_DESCRIPTION, desc);
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION, solution);
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_REMARK, remark);
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_ID, randomId);

        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_CREATE_TIME, time);
        contentValues.put(DBConstants.T_TSSOLUTION.COLUMN_UPDATE_TIME, time);
        boolean isAdd = DBHelper.getInstance().insert(DBConstants.T_TSSOLUTION.T_NAME, contentValues);

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(DBConstants.T_TSOBJECT.COLUMN_IS_ADD_CUSTOM_FUN, true);
        contentValues2.put(DBConstants.T_TSOBJECT.COLUMN_TS_POSITION, position);
        contentValues2.put(DBConstants.T_TSOBJECT.COLUMN_REAL_REASON, reason);
        contentValues2.put(DBConstants.T_TSOBJECT.COLUMN_UPDATE_TIME, time);

        int updateRows = DBHelper.getInstance().update(
                DBConstants.T_TSOBJECT.T_NAME,
                contentValues2,
                DBConstants.T_TSOBJECT.COLUMN_TS_ID + " = ?",
                new String[]{tsId}
        );

        return isAdd && updateRows > 0;
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
