package com.qian.ess.bean.ts;

import android.text.TextUtils;

import com.qian.ess.db.DBConstants;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/2 0002.
 * <p>
 * 排故方法类
 */

public class TSSolution {

    private String fileName; //接收到的文件名

    //标签名
    private String tsSolutionIDLable;//排故ID
    private String tsIDLable;//故障ID
    private String tsShortNameLable;//故障简称
    private String tsSolutionNumberLable;//编号
    private String tsSolutionNameLable;//排故方法名
    private String possibleReasonLable;//可能原因
    private String isUsedLable;//是否使用
    private String isWorkLable;//是否起作用
    private List<String> pictureListLable = new ArrayList<>();//照片列表
    private List<String> videoListLable = new ArrayList<>();//视频列表
    private List<String> audioListLable = new ArrayList<>();//音频列表
    //备用参数标签名
    private String reserveTssParameter1Lable;
    private String reserveTssParameter2Lable;
    private String reserveTssParameter3Lable;
    private String reserveTssParameter4Lable;
    private String reserveTssParameter5Lable;
    private String reserveTssParameter6Lable;
    private String reserveTssParameter7Lable;
    private String reserveTssParameter8Lable;
    //值
    private String tsSolutionID;//ID
    private String tsID;//故障ID
    private String tsShortName;//故障简称
    private int tsSolutionNumber;//编号
    private String tsSolutionName;//排故方法名
    private String possibleReason;//可能原因
    private String tsDescription; //故障描述
    private String tsSolution; //解决方法
    private String tsRemark; //备注
    private boolean isUsed = false;//是否使用
    private boolean isWork = false;//是否起作用
    private List<String> pictureList = new ArrayList<>();//照片列表
    private List<String> videoList = new ArrayList<>();//视频列表
    private List<String> audioList = new ArrayList<>();//音频列表
    //备用参数
    private String reserveTssParameter1;
    private String reserveTssParameter2;
    private String reserveTssParameter3;
    private String reserveTssParameter4;
    private String reserveTssParameter5;
    private String reserveTssParameter6;
    private String reserveTssParameter7;
    private String reserveTssParameter8;

    @Override
    public String toString() {
        return "TSSolution{" +
                "fileName='" + fileName + '\'' +
                ", tsSolutionIDLable='" + tsSolutionIDLable + '\'' +
                ", tsIDLable='" + tsIDLable + '\'' +
                ", tsShortNameLable='" + tsShortNameLable + '\'' +
                ", tsSolutionNumberLable='" + tsSolutionNumberLable + '\'' +
                ", tsSolutionNameLable='" + tsSolutionNameLable + '\'' +
                ", possibleReasonLable='" + possibleReasonLable + '\'' +
                ", isUsedLable='" + isUsedLable + '\'' +
                ", isWorkLable='" + isWorkLable + '\'' +
                ", pictureListLable=" + pictureListLable +
                ", videoListLable=" + videoListLable +
                ", audioListLable=" + audioListLable +
                ", reserveTssParameter1Lable='" + reserveTssParameter1Lable + '\'' +
                ", reserveTssParameter2Lable='" + reserveTssParameter2Lable + '\'' +
                ", reserveTssParameter3Lable='" + reserveTssParameter3Lable + '\'' +
                ", reserveTssParameter4Lable='" + reserveTssParameter4Lable + '\'' +
                ", reserveTssParameter5Lable='" + reserveTssParameter5Lable + '\'' +
                ", reserveTssParameter6Lable='" + reserveTssParameter6Lable + '\'' +
                ", reserveTssParameter7Lable='" + reserveTssParameter7Lable + '\'' +
                ", reserveTssParameter8Lable='" + reserveTssParameter8Lable + '\'' +
                ", tsSolutionID='" + tsSolutionID + '\'' +
                ", tsID='" + tsID + '\'' +
                ", tsShortName='" + tsShortName + '\'' +
                ", tsSolutionNumber=" + tsSolutionNumber +
                ", tsSolutionName='" + tsSolutionName + '\'' +
                ", possibleReason='" + possibleReason + '\'' +
                ", tsDescription='" + tsDescription + '\'' +
                ", tsSolution='" + tsSolution + '\'' +
                ", tsRemark='" + tsRemark + '\'' +
                ", isUsed=" + isUsed +
                ", isWork=" + isWork +
                ", pictureList=" + pictureList +
                ", videoList=" + videoList +
                ", audioList=" + audioList +
                ", reserveTssParameter1='" + reserveTssParameter1 + '\'' +
                ", reserveTssParameter2='" + reserveTssParameter2 + '\'' +
                ", reserveTssParameter3='" + reserveTssParameter3 + '\'' +
                ", reserveTssParameter4='" + reserveTssParameter4 + '\'' +
                ", reserveTssParameter5='" + reserveTssParameter5 + '\'' +
                ", reserveTssParameter6='" + reserveTssParameter6 + '\'' +
                ", reserveTssParameter7='" + reserveTssParameter7 + '\'' +
                ", reserveTssParameter8='" + reserveTssParameter8 + '\'' +
                '}';
    }

    //从Json中解析出Bean
    public static TSSolution fromJson(JSONObject json) {
        TSSolution tsSolution = new TSSolution();
        tsSolution.setFileName(json.optString(DBConstants.T_TSSOLUTION.COLUMN_TS_RECEIVE_FILE_NAME));
        tsSolution.setTsSolutionIDLable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_ID_LABLE));
        tsSolution.setTsIDLable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_TS_ID_LABLE));
        tsSolution.setTsShortNameLable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_TS_SHORT_NAME_LABLE));
        tsSolution.setTsSolutionNumberLable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_NUMBER_LABLE));
        tsSolution.setTsSolutionNameLable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_NAME_LABLE));
        tsSolution.setPossibleReasonLable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_POSSIBLE_REASON_LABLE));
        tsSolution.setIsUsedLable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_IS_USED_LABLE));
        tsSolution.setIsWorkLable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_IS_WORK_LABLE));
        tsSolution.setPictureListLable(strToList(json.optString(DBConstants.T_TSSOLUTION.COLUMN_PICTURE_LIST_LABLE)));
        tsSolution.setVideoListLable(strToList(json.optString(DBConstants.T_TSSOLUTION.COLUMN_VIDEO_LIST_LABLE)));
        tsSolution.setAudioListLable(strToList(json.optString(DBConstants.T_TSSOLUTION.COLUMN_AUDIO_LIST_LABLE)));
        tsSolution.setReserveTssParameter1Lable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER1LABLE));
        tsSolution.setReserveTssParameter2Lable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER2LABLE));
        tsSolution.setReserveTssParameter3Lable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER3LABLE));
        tsSolution.setReserveTssParameter4Lable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER4LABLE));
        tsSolution.setReserveTssParameter5Lable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER5LABLE));
        tsSolution.setReserveTssParameter6Lable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER6LABLE));
        tsSolution.setReserveTssParameter7Lable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER7LABLE));
        tsSolution.setReserveTssParameter8Lable(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER8LABLE));

        tsSolution.setTsSolutionID(json.optString(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_ID));
        tsSolution.setTsID(json.optString(DBConstants.T_TSSOLUTION.COLUMN_TS_ID));
        tsSolution.setTsShortName(json.optString(DBConstants.T_TSSOLUTION.COLUMN_TS_SHORT_NAME));
        tsSolution.setTsSolutionNumber(json.optInt(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_NUMBER));
        tsSolution.setTsSolutionName(json.optString(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION_NAME));
        tsSolution.setPossibleReason(json.optString(DBConstants.T_TSSOLUTION.COLUMN_POSSIBLE_REASON));
        tsSolution.setTsSolution(json.optString(DBConstants.T_TSSOLUTION.COLUMN_TS_SOLUTION));
        tsSolution.setTsRemark(json.optString(DBConstants.T_TSSOLUTION.COLUMN_TS_REMARK));
        tsSolution.setTsDescription(json.optString(DBConstants.T_TSSOLUTION.COLUMN_TS_DESCRIPTION));
        tsSolution.setIsUsed(json.optInt(DBConstants.T_TSSOLUTION.COLUMN_IS_USED) == 1);
        tsSolution.setIsWork(json.optInt(DBConstants.T_TSSOLUTION.COLUMN_IS_WORK) == 1);
        tsSolution.setPictureList(strToList(json.optString(DBConstants.T_TSSOLUTION.COLUMN_PICTURE_LIST)));
        tsSolution.setVideoList(strToList(json.optString(DBConstants.T_TSSOLUTION.COLUMN_VIDEO_LIST)));
        tsSolution.setAudioList(strToList(json.optString(DBConstants.T_TSSOLUTION.COLUMN_AUDIO_LIST)));
        tsSolution.setReserveTssParameter1(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER1));
        tsSolution.setReserveTssParameter2(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER2));
        tsSolution.setReserveTssParameter3(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER3));
        tsSolution.setReserveTssParameter4(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER4));
        tsSolution.setReserveTssParameter5(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER5));
        tsSolution.setReserveTssParameter6(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER6));
        tsSolution.setReserveTssParameter7(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER7));
        tsSolution.setReserveTssParameter8(json.optString(DBConstants.T_TSSOLUTION.COLUMN_RESERVETSSPARAMETER8));

        return tsSolution;
    }

    //数据库存储的字符串，转成list数据
    private static List<String> strToList(String str) {
        List<String> list = new ArrayList<>();
        if (!TextUtils.isEmpty(str)) {
            String[] array = str.split(",");
            for (int i = 0, len = array.length; i < len; i++) {
                list.add(array[i]);
            }
        }
        return list;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTsSolutionIDLable() {
        return tsSolutionIDLable;
    }

    public void setTsSolutionIDLable(String tsSolutionIDLable) {
        this.tsSolutionIDLable = tsSolutionIDLable;
    }

    public String getTsIDLable() {
        return tsIDLable;
    }

    public void setTsIDLable(String tsIDLable) {
        this.tsIDLable = tsIDLable;
    }

    public String getTsShortNameLable() {
        return tsShortNameLable;
    }

    public void setTsShortNameLable(String tsShortNameLable) {
        this.tsShortNameLable = tsShortNameLable;
    }

    public String getTsSolutionNumberLable() {
        return tsSolutionNumberLable;
    }

    public void setTsSolutionNumberLable(String tsSolutionNumberLable) {
        this.tsSolutionNumberLable = tsSolutionNumberLable;
    }

    public String getTsSolutionNameLable() {
        return tsSolutionNameLable;
    }

    public void setTsSolutionNameLable(String tsSolutionNameLable) {
        this.tsSolutionNameLable = tsSolutionNameLable;
    }

    public String getPossibleReasonLable() {
        return possibleReasonLable;
    }

    public void setPossibleReasonLable(String possibleReasonLable) {
        this.possibleReasonLable = possibleReasonLable;
    }

    public String getIsUsedLable() {
        return isUsedLable;
    }

    public void setIsUsedLable(String isUsedLable) {
        this.isUsedLable = isUsedLable;
    }

    public String getIsWorkLable() {
        return isWorkLable;
    }

    public void setIsWorkLable(String isWorkLable) {
        this.isWorkLable = isWorkLable;
    }

    public List<String> getPictureListLable() {
        return pictureListLable;
    }

    public void setPictureListLable(List<String> pictureListLable) {
        this.pictureListLable = pictureListLable;
    }

    public List<String> getVideoListLable() {
        return videoListLable;
    }

    public void setVideoListLable(List<String> videoListLable) {
        this.videoListLable = videoListLable;
    }

    public List<String> getAudioListLable() {
        return audioListLable;
    }

    public void setAudioListLable(List<String> audioListLable) {
        this.audioListLable = audioListLable;
    }

    public String getReserveTssParameter1Lable() {
        return reserveTssParameter1Lable;
    }

    public void setReserveTssParameter1Lable(String reserveTssParameter1Lable) {
        this.reserveTssParameter1Lable = reserveTssParameter1Lable;
    }

    public String getReserveTssParameter2Lable() {
        return reserveTssParameter2Lable;
    }

    public void setReserveTssParameter2Lable(String reserveTssParameter2Lable) {
        this.reserveTssParameter2Lable = reserveTssParameter2Lable;
    }

    public String getReserveTssParameter3Lable() {
        return reserveTssParameter3Lable;
    }

    public void setReserveTssParameter3Lable(String reserveTssParameter3Lable) {
        this.reserveTssParameter3Lable = reserveTssParameter3Lable;
    }

    public String getReserveTssParameter4Lable() {
        return reserveTssParameter4Lable;
    }

    public void setReserveTssParameter4Lable(String reserveTssParameter4Lable) {
        this.reserveTssParameter4Lable = reserveTssParameter4Lable;
    }

    public String getReserveTssParameter5Lable() {
        return reserveTssParameter5Lable;
    }

    public void setReserveTssParameter5Lable(String reserveTssParameter5Lable) {
        this.reserveTssParameter5Lable = reserveTssParameter5Lable;
    }

    public String getReserveTssParameter6Lable() {
        return reserveTssParameter6Lable;
    }

    public void setReserveTssParameter6Lable(String reserveTssParameter6Lable) {
        this.reserveTssParameter6Lable = reserveTssParameter6Lable;
    }

    public String getReserveTssParameter7Lable() {
        return reserveTssParameter7Lable;
    }

    public void setReserveTssParameter7Lable(String reserveTssParameter7Lable) {
        this.reserveTssParameter7Lable = reserveTssParameter7Lable;
    }

    public String getReserveTssParameter8Lable() {
        return reserveTssParameter8Lable;
    }

    public void setReserveTssParameter8Lable(String reserveTssParameter8Lable) {
        this.reserveTssParameter8Lable = reserveTssParameter8Lable;
    }

    public String getTsSolutionID() {
        return tsSolutionID;
    }

    public void setTsSolutionID(String tsSolutionID) {
        this.tsSolutionID = tsSolutionID;
    }

    public String getTsID() {
        return tsID;
    }

    public void setTsID(String tsID) {
        this.tsID = tsID;
    }

    public String getTsShortName() {
        return tsShortName;
    }

    public void setTsShortName(String tsShortName) {
        this.tsShortName = tsShortName;
    }

    public int getTsSolutionNumber() {
        return tsSolutionNumber;
    }

    public void setTsSolutionNumber(int tsSolutionNumber) {
        this.tsSolutionNumber = tsSolutionNumber;
    }

    public String getTsSolutionName() {
        return tsSolutionName;
    }

    public void setTsSolutionName(String tsSolutionName) {
        this.tsSolutionName = tsSolutionName;
    }

    public String getPossibleReason() {
        return possibleReason;
    }

    public void setPossibleReason(String possibleReason) {
        this.possibleReason = possibleReason;
    }

    public String getTsDescription() {
        return tsDescription;
    }

    public void setTsDescription(String tsDescription) {
        this.tsDescription = tsDescription;
    }

    public String getTsSolution() {
        return tsSolution;
    }

    public void setTsSolution(String tsSolution) {
        this.tsSolution = tsSolution;
    }

    public String getTsRemark() {
        return TextUtils.isEmpty(tsRemark) ? "" : tsRemark;
    }

    public void setTsRemark(String tsRemark) {
        this.tsRemark = tsRemark;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean used) {
        isUsed = used;
    }

    public boolean getIsWork() {
        return isWork;
    }

    public void setIsWork(boolean work) {
        isWork = work;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public List<String> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<String> videoList) {
        this.videoList = videoList;
    }

    public List<String> getAudioList() {
        return audioList;
    }

    public void setAudioList(List<String> audioList) {
        this.audioList = audioList;
    }

    public String getReserveTssParameter1() {
        return reserveTssParameter1;
    }

    public void setReserveTssParameter1(String reserveTssParameter1) {
        this.reserveTssParameter1 = reserveTssParameter1;
    }

    public String getReserveTssParameter2() {
        return reserveTssParameter2;
    }

    public void setReserveTssParameter2(String reserveTssParameter2) {
        this.reserveTssParameter2 = reserveTssParameter2;
    }

    public String getReserveTssParameter3() {
        return reserveTssParameter3;
    }

    public void setReserveTssParameter3(String reserveTssParameter3) {
        this.reserveTssParameter3 = reserveTssParameter3;
    }

    public String getReserveTssParameter4() {
        return reserveTssParameter4;
    }

    public void setReserveTssParameter4(String reserveTssParameter4) {
        this.reserveTssParameter4 = reserveTssParameter4;
    }

    public String getReserveTssParameter5() {
        return reserveTssParameter5;
    }

    public void setReserveTssParameter5(String reserveTssParameter5) {
        this.reserveTssParameter5 = reserveTssParameter5;
    }

    public String getReserveTssParameter6() {
        return reserveTssParameter6;
    }

    public void setReserveTssParameter6(String reserveTssParameter6) {
        this.reserveTssParameter6 = reserveTssParameter6;
    }

    public String getReserveTssParameter7() {
        return reserveTssParameter7;
    }

    public void setReserveTssParameter7(String reserveTssParameter7) {
        this.reserveTssParameter7 = reserveTssParameter7;
    }

    public String getReserveTssParameter8() {
        return reserveTssParameter8;
    }

    public void setReserveTssParameter8(String reserveTssParameter8) {
        this.reserveTssParameter8 = reserveTssParameter8;
    }
}
