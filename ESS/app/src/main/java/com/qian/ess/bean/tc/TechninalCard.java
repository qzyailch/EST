package com.qian.ess.bean.tc;

import android.text.TextUtils;

import com.qian.ess.db.DBConstants;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QianMoLi on 2018/11/24.
 * <p>
 * TechninalCard 技术卡
 */

public class TechninalCard {

    private String pcReceiveFileName; //接收到的文件名
    private String number;
    private String name;
    private int page;
    private String resultValue;
    private String workerName;
    private String reviewerName;
    private String remark;
    private String hours;
    private List<String> operations = new ArrayList<>();
    private List<String> actions = new ArrayList<>();
    private List<String> equipment = new ArrayList<>();
    private List<String> tools = new ArrayList<>();
    private List<String> materials = new ArrayList<>();
    private List<String> photoPathList = new ArrayList<>();
    private List<String> videoPathList = new ArrayList<>();
    private List<String> audioPathList = new ArrayList<>();
    private String beginDateTime;
    private String finishDateTime;

    //从Json中解析出Bean
    public static TechninalCard fromJson(JSONObject json) {
        TechninalCard techninalCard = new TechninalCard();
        techninalCard.setPcReceiveFileName(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_PC_RECEIVE_FILE_NAME));
        techninalCard.setNumber(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_NUMBER));
        techninalCard.setName(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_NAME));
        techninalCard.setPage(json.optInt(DBConstants.T_PC_TECHNINALCARD.COLUMN_PAGE));
        techninalCard.setResultValue(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_RESULT_VALUE));
        techninalCard.setWorkerName(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_WORKER_NAME));
        techninalCard.setReviewerName(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_REVIEWER_NAME));
        techninalCard.setRemark(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_REMARK));
        techninalCard.setHours(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_HOURS));
        techninalCard.setOperations(strToList(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_OPERATIONS)));
        techninalCard.setActions(strToList(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_ACTIONS)));
        techninalCard.setEquipment(strToList(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_EQUIPMENT)));
        techninalCard.setTools(strToList(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_TOOLS)));
        techninalCard.setMaterials(strToList(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_MATERIALS)));
        techninalCard.setPhotoPathList(strToList(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_PHOTO_PATH_LIST)));
        techninalCard.setVideoPathList(strToList(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_VIDEO_PATH_LIST)));
        techninalCard.setAudioPathList(strToList(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_AUDIO_PATH_LIST)));
        techninalCard.setBeginDateTime(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_BEGIN_DATE_TIME));
        techninalCard.setFinishDateTime(json.optString(DBConstants.T_PC_TECHNINALCARD.COLUMN_FINISH_DATE_TIME));

        return techninalCard;
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

    @Override
    public String toString() {
        return "TechninalCard{" +
                "pcReceiveFileName='" + pcReceiveFileName + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", page=" + page +
                ", resultValue='" + resultValue + '\'' +
                ", workerName='" + workerName + '\'' +
                ", reviewerName='" + reviewerName + '\'' +
                ", remark='" + remark + '\'' +
                ", hours='" + hours + '\'' +
                ", operations=" + operations +
                ", actions=" + actions +
                ", equipment=" + equipment +
                ", tools=" + tools +
                ", materials=" + materials +
                ", photoPathList=" + photoPathList +
                ", videoPathList=" + videoPathList +
                ", audioPathList=" + audioPathList +
                ", beginDateTime='" + beginDateTime + '\'' +
                ", finishDateTime='" + finishDateTime + '\'' +
                '}';
    }

    public String getPcReceiveFileName() {
        return pcReceiveFileName;
    }

    public void setPcReceiveFileName(String pcReceiveFileName) {
        this.pcReceiveFileName = pcReceiveFileName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public List<String> getOperations() {
        return operations;
    }

    public void setOperations(List<String> operations) {
        this.operations = operations;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }

    public List<String> getTools() {
        return tools;
    }

    public void setTools(List<String> tools) {
        this.tools = tools;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    public List<String> getPhotoPathList() {
        return photoPathList;
    }

    public void setPhotoPathList(List<String> photoPathList) {
        this.photoPathList = photoPathList;
    }

    public List<String> getVideoPathList() {
        return videoPathList;
    }

    public void setVideoPathList(List<String> videoPathList) {
        this.videoPathList = videoPathList;
    }

    public List<String> getAudioPathList() {
        return audioPathList;
    }

    public void setAudioPathList(List<String> audioPathList) {
        this.audioPathList = audioPathList;
    }

    public String getBeginDateTime() {
        return beginDateTime;
    }

    public void setBeginDateTime(String beginDateTime) {
        this.beginDateTime = beginDateTime;
    }

    public String getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(String finishDateTime) {
        this.finishDateTime = finishDateTime;
    }
}
