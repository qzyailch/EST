package com.qian.ess.bean.ts;

import android.text.TextUtils;
import android.util.Xml;

import com.qian.ess.db.DBConstants;
import com.qian.ess.db.action.TSObjectAction;
import com.qian.ess.utils.Logger;
import com.qian.ess.utils.ToastUtils;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/2 0002.
 * <p>
 * TS
 */

public class TSObject implements Serializable {

    private String fileName; //接收到的文件名

    private boolean isAddCustomFun = false; //是否已经添加排故方法

    //标签名
    private String tsIDLable;//ID
    private String workLocationLable;//地点
    private String tsNumberLable;//排故编号
    private String tsShortNameLable;//故障简称
    private String tsFullNameLable;//故障全称
    private String tsPositionLable;//故障位置
    private String objectModelLable;//对象型号
    private String objectNumberLable;//对象编号
    private String partNumberLable;//部件编号
    private String partPositionLable;//部件位置
    private String tsPersonInChargeLable;//主管
    private String tsWorkerNameLable;//操作者
    private String tsAuditorLable;//审核者
    private String tsDeliverDateTimeLable;//交付时间
    private String tsBeginDateTimeLable;//开始时间
    private String tsFinishDateTimeLable;//完成时间
    private String tsSolutionNumberLable;//排故编号标签
    private String tsSolutionsLable;//排故方法标签
    private String tsPossibleReasonLable;//可能原因标签
    private String tsIsUsedLable;//使用的方法 （+/-)标签
    private String tsIsWorkLable;//结果（+/-）标签
    private String realReasonLable;//真实原因
    private String remarkLable;//备注
    private String pictureLable;//照片标签
    private String videoLable;//视频标签
    private String audioLable;//音频标签
    private List<String> pictureListLable = new ArrayList<>();//照片列表
    private List<String> videoListLable = new ArrayList<>();//视频列表
    private List<String> audioListLable = new ArrayList<>();//音频列表
    //备用参数标签名
    private String reserveTsParameter1Lable;
    private String reserveTsParameter2Lable;
    private String reserveTsParameter3Lable;
    private String reserveTsParameter4Lable;
    private String reserveTsParameter5Lable;
    private String reserveTsParameter6Lable;
    private String reserveTsParameter7Lable;
    private String reserveTsParameter8Lable;

    //值
    private String tsID;//ID
    private String workLocation;//地点
    private String tsNumber;//排故编号
    private String tsShortName;//故障简称
    private String tsFullName;//故障全称
    private String tsPosition;//故障位置
    private String objectModel;//对象型号
    private String objectNumber;//对象编号
    private String partNumber;//部件编号
    private String partPosition;//部件位置
    private String tsPersonInCharge;//主管
    private String tsWorkerName;//操作者
    private String tsAuditor;//审核者
    private String tsDeliverDateTime;//交付时间 yyyyMMddHHmmss
    private String tsBeginDateTime;//开始时间 yyyyMMddHHmmss
    private String tsFinishDateTime;//完成时间 yyyyMMddHHmmss
    private List<TSSolution> tsSolutions = new ArrayList<>();//排故方法列表
    private String realReason;//真实原因
    private String remark;//备注
    private List<String> pictureList = new ArrayList<>();//照片列表
    private List<String> videoList = new ArrayList<>();//视频列表
    private List<String> audioList = new ArrayList<>();//音频列表
    //备用参数
    private String reserveTsParameter1;
    private String reserveTsParameter2;
    private String reserveTsParameter3;
    private String reserveTsParameter4;
    private String reserveTsParameter5;
    private String reserveTsParameter6;
    private String reserveTsParameter7;
    private String reserveTsParameter8;

    //从Xml文件中解析出Bean
    public static TSObject fromXml(String xmlPath) {
        FileInputStream is = null;
        try {
            File file = new File(xmlPath);
            if (!file.exists()) {
                ToastUtils.show("文件不存在");
                return null;
            }

            is = new FileInputStream(file);
        } catch (Exception e) {
            Logger.e("result", "Couldn't find xml file " + xmlPath);
            return null;
        }

        //TSObject
        TSObject tsObject = new TSObject();
        //TSSolution
        TSSolution tsSolution = null;

        //由于每一个List的子项都是Element，所以为了知道解析Element标签时，是解析的哪一个List数据，临时记录一个保存当前解析List的标签名
        String startListTag = null;

        try {
            XmlPullParser parser = Xml.newPullParser();
            // 设置文件输入流
            parser.setInput(is, "utf-8");
            // 得到当前事件类型
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        /**
                         * 通过getName判断读到哪个标签, 然后通过nextText获取文本节点值，
                         * 或者通过getAttributeValue(i)获取属性节点值
                         */
                        String name = parser.getName();
                        switch (name) {
                            /************标签***********/
                            case "tsIDLable":
                                if (null != tsSolution) {
                                    tsSolution.setTsIDLable(parser.nextText());
                                } else {
                                    tsObject.setTsIDLable(parser.nextText());
                                }
                                break;
                            case "workLocationLable":
                                tsObject.setWorkLocationLable(parser.nextText());
                                break;
                            case "tsNumberLable":
                                tsObject.setTsNumberLable(parser.nextText());
                                break;
                            case "tsShortNameLable":
                                if (null != tsSolution) {
                                    tsSolution.setTsShortNameLable(parser.nextText());
                                } else {
                                    tsObject.setTsShortNameLable(parser.nextText());
                                }
                                break;
                            case "tsFullNameLable":
                                tsObject.setTsFullNameLable(parser.nextText());
                                break;
                            case "tsPositionLable":
                                tsObject.setTsPositionLable(parser.nextText());
                                break;
                            case "objectModelLable":
                                tsObject.setObjectModelLable(parser.nextText());
                                break;
                            case "objectNumberLable":
                                tsObject.setObjectNumberLable(parser.nextText());
                                break;
                            case "partNumberLable":
                                tsObject.setPartNumberLable(parser.nextText());
                                break;
                            case "partPositionLable":
                                tsObject.setPartPositionLable(parser.nextText());
                                break;
                            case "tsPersonInChargeLable":
                                tsObject.setTsPersonInChargeLable(parser.nextText());
                                break;
                            case "tsWorkerNameLable":
                                tsObject.setTsWorkerNameLable(parser.nextText());
                                break;
                            case "tsAuditorLable":
                                tsObject.setTsAuditorLable(parser.nextText());
                                break;
                            case "tsDeliverDateTimeLable":
                                tsObject.setTsDeliverDateTimeLable(parser.nextText());
                                break;
                            case "tsBeginDateTimeLable":
                                tsObject.setTsBeginDateTimeLable(parser.nextText());
                                break;
                            case "tsFinishDateTimeLable":
                                tsObject.setTsFinishDateTimeLable(parser.nextText());
                                break;
                            case "tsSolutionNumberLable":
                                if (null != tsSolution) {
                                    tsSolution.setTsSolutionNumberLable(parser.nextText());
                                } else {
                                    tsObject.setTsSolutionNumberLable(parser.nextText());
                                }
                                break;
                            case "tsSolutionsLable":
                                tsObject.setTsSolutionsLable(parser.nextText());
                                break;
                            case "tsPossibleReasonLable":
                                tsObject.setTsPossibleReasonLable(parser.nextText());
                                break;
                            case "tsIsUsedLable":
                                tsObject.setTsIsUsedLable(parser.nextText());
                                break;
                            case "tsIsWorkLable":
                                tsObject.setTsIsWorkLable(parser.nextText());
                                break;
                            case "realReasonLable":
                                tsObject.setRealReasonLable(parser.nextText());
                                break;
                            case "remarkLable":
                                tsObject.setRemarkLable(parser.nextText());
                                break;
                            case "pictureLable":
                                tsObject.setPictureLable(parser.nextText());
                                break;
                            case "videoLable":
                                tsObject.setVideoLable(parser.nextText());
                                break;
                            case "audioLable":
                                tsObject.setAudioLable(parser.nextText());
                                break;
                            case "pictureListLable":
                                startListTag = "pictureListLable";
                                break;
                            case "videoListLable":
                                startListTag = "videoListLable";
                                break;
                            case "audioListLable":
                                startListTag = "audioListLable";
                                break;
                            case "reserveTsParameter1Lable":
                                tsObject.setReserveTsParameter1Lable(parser.nextText());
                                break;
                            case "reserveTsParameter2Lable":
                                tsObject.setReserveTsParameter2Lable(parser.nextText());
                                break;
                            case "reserveTsParameter3Lable":
                                tsObject.setReserveTsParameter3Lable(parser.nextText());
                                break;
                            case "reserveTsParameter4Lable":
                                tsObject.setReserveTsParameter4Lable(parser.nextText());
                                break;
                            case "reserveTsParameter5Lable":
                                tsObject.setReserveTsParameter5Lable(parser.nextText());
                                break;
                            case "reserveTsParameter6Lable":
                                tsObject.setReserveTsParameter6Lable(parser.nextText());
                                break;
                            case "reserveTsParameter7Lable":
                                tsObject.setReserveTsParameter7Lable(parser.nextText());
                                break;
                            case "reserveTsParameter8Lable":
                                tsObject.setReserveTsParameter8Lable(parser.nextText());
                                break;
                            /************具体值**************/
                            case "tsID":
                                if (null != tsSolution) {
                                    tsSolution.setTsID(parser.nextText());
                                } else {
                                    tsObject.setTsID(parser.nextText());
                                }
                                break;
                            case "workLocation":
                                tsObject.setWorkLocation(parser.nextText());
                                break;
                            case "tsNumber":
                                tsObject.setTsNumber(parser.nextText());
                                break;
                            case "tsShortName":
                                if (null != tsSolution) {
                                    tsSolution.setTsShortName(parser.nextText());
                                } else {
                                    tsObject.setTsShortName(parser.nextText());
                                }
                                break;
                            case "tsFullName":
                                tsObject.setTsFullName(parser.nextText());
                                break;
                            case "tsPosition":
                                tsObject.setTsPosition(parser.nextText());
                                break;
                            case "objectModel":
                                tsObject.setObjectModel(parser.nextText());
                                break;
                            case "objectNumber":
                                tsObject.setObjectNumber(parser.nextText());
                                break;
                            case "partNumber":
                                tsObject.setPartNumber(parser.nextText());
                                break;
                            case "partPosition":
                                tsObject.setPartPosition(parser.nextText());
                                break;
                            case "tsPersonInCharge":
                                tsObject.setTsPersonInCharge(parser.nextText());
                                break;
                            case "tsWorkerName":
                                tsObject.setTsWorkerName(parser.nextText());
                                break;
                            case "tsAuditor":
                                tsObject.setTsAuditor(parser.nextText());
                                break;
                            case "tsDeliverDateTime":
                                tsObject.setTsDeliverDateTime(parser.nextText());
                                break;
                            case "tsBeginDateTime":
                                tsObject.setTsBeginDateTime(parser.nextText());
                                break;
                            case "tsFinishDateTime":
                                tsObject.setTsFinishDateTime(parser.nextText());
                                break;
                            case "realReason":
                                tsObject.setRealReason(parser.nextText());
                                break;
                            case "remark":
                                tsObject.setRemark(parser.nextText());
                                break;
                            case "pictureList":
                                startListTag = "pictureList";
                                break;
                            case "videoList":
                                startListTag = "videoList";
                                break;
                            case "audioList":
                                startListTag = "audioList";
                                break;
                            case "reserveTsParameter1":
                                tsObject.setReserveTsParameter1(parser.nextText());
                                break;
                            case "reserveTsParameter2":
                                tsObject.setReserveTsParameter2(parser.nextText());
                                break;
                            case "reserveTsParameter3":
                                tsObject.setReserveTsParameter3(parser.nextText());
                                break;
                            case "reserveTsParameter4":
                                tsObject.setReserveTsParameter4(parser.nextText());
                                break;
                            case "reserveTsParameter5":
                                tsObject.setReserveTsParameter5(parser.nextText());
                                break;
                            case "reserveTsParameter6":
                                tsObject.setReserveTsParameter6(parser.nextText());
                                break;
                            case "reserveTsParameter7":
                                tsObject.setReserveTsParameter7(parser.nextText());
                                break;
                            case "reserveTsParameter8":
                                tsObject.setReserveTsParameter8(parser.nextText());
                                break;
                            case "Element":
                                if ("pictureListLable".equals(startListTag)) {
                                    if (null != tsSolution) {
                                        tsSolution.getPictureListLable().add(parser.nextText());
                                    } else {
                                        tsObject.getPictureListLable().add(parser.nextText());
                                    }
                                } else if ("videoListLable".equals(startListTag)) {
                                    if (null != tsSolution) {
                                        tsSolution.getVideoListLable().add(parser.nextText());
                                    } else {
                                        tsObject.getVideoListLable().add(parser.nextText());
                                    }
                                } else if ("audioListLable".equals(startListTag)) {
                                    if (null != tsSolution) {
                                        tsSolution.getAudioListLable().add(parser.nextText());
                                    } else {
                                        tsObject.getAudioListLable().add(parser.nextText());
                                    }
                                } else if ("pictureList".equals(startListTag)) {
                                    if (null != tsSolution) {
                                        tsSolution.getPictureList().add(parser.nextText());
                                    } else {
                                        tsObject.getPictureList().add(parser.nextText());
                                    }
                                } else if ("videoList".equals(startListTag)) {
                                    if (null != tsSolution) {
                                        tsSolution.getVideoList().add(parser.nextText());
                                    } else {
                                        tsObject.getVideoList().add(parser.nextText());
                                    }
                                } else if ("audioList".equals(startListTag)) {
                                    if (null != tsSolution) {
                                        tsSolution.getAudioList().add(parser.nextText());
                                    } else {
                                        tsObject.getAudioList().add(parser.nextText());
                                    }
                                }
                                break;
                            /**************TSSolution***************/
                            case "TSSolution":
                                tsSolution = new TSSolution();
                                break;
                            case "tsSolutionIDLable":
                                if (null != tsSolution) {
                                    tsSolution.setTsSolutionIDLable(parser.nextText());
                                }
                                break;
                            case "tsSolutionNameLable":
                                if (null != tsSolution) {
                                    tsSolution.setTsSolutionNameLable(parser.nextText());
                                }
                                break;
                            case "possibleReasonLable":
                                if (null != tsSolution) {
                                    tsSolution.setPossibleReasonLable(parser.nextText());
                                }
                                break;
                            case "isUsedLable":
                                if (null != tsSolution) {
                                    tsSolution.setIsUsedLable(parser.nextText());
                                }
                                break;
                            case "isWorkLable":
                                if (null != tsSolution) {
                                    tsSolution.setIsWorkLable(parser.nextText());
                                }
                                break;
                            case "reserveTssParameter1Lable":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter1Lable(parser.nextText());
                                }
                                break;
                            case "reserveTssParameter2Lable":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter2Lable(parser.nextText());
                                }
                                break;
                            case "reserveTssParameter3Lable":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter3Lable(parser.nextText());
                                }
                                break;
                            case "reserveTssParameter4Lable":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter4Lable(parser.nextText());
                                }
                                break;
                            case "reserveTssParameter5Lable":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter5Lable(parser.nextText());
                                }
                                break;
                            case "reserveTssParameter6Lable":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter6Lable(parser.nextText());
                                }
                                break;
                            case "reserveTssParameter7Lable":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter7Lable(parser.nextText());
                                }
                                break;
                            case "reserveTssParameter8Lable":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter8Lable(parser.nextText());
                                }
                                break;
                            case "tsSolutionID":
                                if (null != tsSolution) {
                                    tsSolution.setTsSolutionID(parser.nextText());
                                }
                                break;
                            case "tsSolutionNumber":
                                if (null != tsSolution) {
                                    tsSolution.setTsSolutionNumber(Integer.parseInt(parser.nextText()));
                                }
                                break;
                            case "tsSolutionName":
                                if (null != tsSolution) {
                                    tsSolution.setTsSolutionName(parser.nextText());
                                }
                                break;
                            case "possibleReason":
                                if (null != tsSolution) {
                                    tsSolution.setPossibleReason(parser.nextText());
                                }
                                break;
                            case "tsDescription":
                                if (null != tsSolution) {
                                    tsSolution.setTsDescription(parser.nextText());
                                }
                                break;
                            case "tsSolution":
                                if (null != tsSolution) {
                                    tsSolution.setTsSolution(parser.nextText());
                                }
                                break;
                            case "tsRemark":
                                if (null != tsSolution) {
                                    tsSolution.setTsRemark(parser.nextText());
                                }
                                break;
                            case "isUsed":
                                if (null != tsSolution) {
                                    String innerText = parser.nextText();
                                    tsSolution.setIsUsed("true".equals(innerText) || "True".equals(innerText));
                                }
                                break;
                            case "isWork":
                                if (null != tsSolution) {
                                    String innerText = parser.nextText();
                                    tsSolution.setIsWork("true".equals(innerText) || "True".equals(innerText));
                                }
                                break;
                            case "reserveTssParameter1":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter1(parser.nextText());
                                }
                                break;
                            case "reserveTssParameter2":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter2(parser.nextText());
                                }
                                break;
                            case "reserveTssParameter3":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter3(parser.nextText());
                                }
                                break;
                            case "reserveTssParameter4":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter4(parser.nextText());
                                }
                                break;
                            case "reserveTssParameter5":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter5(parser.nextText());
                                }
                                break;
                            case "reserveTssParameter6":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter6(parser.nextText());
                                }
                                break;
                            case "reserveTssParameter7":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter7(parser.nextText());
                                }
                                break;
                            case "reserveTssParameter8":
                                if (null != tsSolution) {
                                    tsSolution.setReserveTssParameter8(parser.nextText());
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endName = parser.getName();
                        switch (endName) {
                            case "TSSolution":
                                tsObject.getTsSolutions().add(tsSolution);
                                tsSolution = null;
                                break;
                            default:
                                break;
                        }
                        break;
                }
                eventType = parser.next();
            }
            is.close();
            return tsObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //将TSObject对象写成xml文件
    public static void writeToXml(TSObject tsObject, String xmlPath) {
        FileOutputStream fos = null;
        try {
            File file = new File(xmlPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            //打开要写入的xml文件
            fos = new FileOutputStream(xmlPath);
            // 拿到xml序列化器
            XmlSerializer xs = Xml.newSerializer();
            // 指定用utf-8编码生成文件
            xs.setOutput(fos, "utf-8");
            // 生成xml表头，两个参数表示表头属性
            xs.startDocument("utf-8", true);

            xs.startTag(null, "TS");
            xs.startTag(null, "Elements");

            createXmlTag(xs, "tsIDLable", tsObject.getTsIDLable());
            createXmlTag(xs, "workLocationLable", tsObject.getWorkLocationLable());
            createXmlTag(xs, "tsNumberLable", tsObject.getTsNumberLable());
            createXmlTag(xs, "tsShortNameLable", tsObject.getTsShortNameLable());
            createXmlTag(xs, "tsFullNameLable", tsObject.getTsFullNameLable());
            createXmlTag(xs, "tsPositionLable", tsObject.getTsPositionLable());
            createXmlTag(xs, "objectModelLable", tsObject.getObjectModelLable());
            createXmlTag(xs, "objectNumberLable", tsObject.getObjectNumberLable());
            createXmlTag(xs, "partNumberLable", tsObject.getPartNumberLable());
            createXmlTag(xs, "partPositionLable", tsObject.getPartPositionLable());
            createXmlTag(xs, "tsPersonInChargeLable", tsObject.getTsPersonInChargeLable());
            createXmlTag(xs, "tsWorkerNameLable", tsObject.getTsWorkerNameLable());
            createXmlTag(xs, "tsAuditorLable", tsObject.getTsAuditorLable());
            createXmlTag(xs, "tsDeliverDateTimeLable", tsObject.getTsDeliverDateTimeLable());
            createXmlTag(xs, "tsBeginDateTimeLable", tsObject.getTsBeginDateTimeLable());
            createXmlTag(xs, "tsFinishDateTimeLable", tsObject.getTsFinishDateTimeLable());
            createXmlTag(xs, "tsSolutionNumberLable", tsObject.getTsSolutionNumberLable());
            createXmlTag(xs, "tsSolutionsLable", tsObject.getTsSolutionsLable());
            createXmlTag(xs, "tsPossibleReasonLable", tsObject.getTsPossibleReasonLable());
            createXmlTag(xs, "tsIsUsedLable", tsObject.getTsIsUsedLable());
            createXmlTag(xs, "tsIsWorkLable", tsObject.getTsIsWorkLable());
            createXmlTag(xs, "realReasonLable", tsObject.getRealReasonLable());
            createXmlTag(xs, "remarkLable", tsObject.getRemarkLable());
            createXmlTag(xs, "pictureLable", tsObject.getPictureLable());
            createXmlTag(xs, "videoLable", tsObject.getVideoLable());
            createXmlTag(xs, "audioLable", tsObject.getAudioLable());

            List<String> thisPictureListLable = tsObject.getPictureListLable();//照片列表
            List<String> thisVideoListLable = tsObject.getVideoListLable();//视频列表
            List<String> thisAudioListLable = tsObject.getAudioListLable();//音频列表

            xs.startTag(null, "pictureListLable");
            if (null != thisPictureListLable && thisPictureListLable.size() > 0) {
                for (int i = 0, len = thisPictureListLable.size(); i < len; i++) {
                    createXmlTag(xs, "Element", thisPictureListLable.get(i));
                }
            }
            xs.endTag(null, "pictureListLable");

            xs.startTag(null, "videoListLable");
            if (null != thisVideoListLable && thisVideoListLable.size() > 0) {
                for (int i = 0, len = thisVideoListLable.size(); i < len; i++) {
                    createXmlTag(xs, "Element", thisVideoListLable.get(i));
                }
            }
            xs.endTag(null, "videoListLable");

            xs.startTag(null, "audioListLable");
            if (null != thisAudioListLable && thisAudioListLable.size() > 0) {
                for (int i = 0, len = thisAudioListLable.size(); i < len; i++) {
                    createXmlTag(xs, "Element", thisAudioListLable.get(i));
                }
            }
            xs.endTag(null, "audioListLable");

            createXmlTag(xs, "reserveTsParameter1Lable", tsObject.getReserveTsParameter1Lable());
            createXmlTag(xs, "reserveTsParameter2Lable", tsObject.getReserveTsParameter2Lable());
            createXmlTag(xs, "reserveTsParameter3Lable", tsObject.getReserveTsParameter3Lable());
            createXmlTag(xs, "reserveTsParameter4Lable", tsObject.getReserveTsParameter4Lable());
            createXmlTag(xs, "reserveTsParameter5Lable", tsObject.getReserveTsParameter5Lable());
            createXmlTag(xs, "reserveTsParameter6Lable", tsObject.getReserveTsParameter6Lable());
            createXmlTag(xs, "reserveTsParameter7Lable", tsObject.getReserveTsParameter7Lable());
            createXmlTag(xs, "reserveTsParameter8Lable", tsObject.getReserveTsParameter8Lable());

            createXmlTag(xs, "tsID", tsObject.getTsID());
            createXmlTag(xs, "workLocation", tsObject.getWorkLocation());
            createXmlTag(xs, "tsNumber", tsObject.getTsNumber());
            createXmlTag(xs, "tsShortName", tsObject.getTsShortName());
            createXmlTag(xs, "tsFullName", tsObject.getTsFullName());
            createXmlTag(xs, "tsPosition", tsObject.getTsPosition());
            createXmlTag(xs, "objectModel", tsObject.getObjectModel());
            createXmlTag(xs, "objectNumber", tsObject.getObjectNumber());
            createXmlTag(xs, "partNumber", tsObject.getPartNumber());
            createXmlTag(xs, "partPosition", tsObject.getPartPosition());
            createXmlTag(xs, "tsPersonInCharge", tsObject.getTsPersonInCharge());
            createXmlTag(xs, "tsWorkerName", tsObject.getTsWorkerName());
            createXmlTag(xs, "tsAuditor", tsObject.getTsAuditor());
            createXmlTag(xs, "tsDeliverDateTime", tsObject.getTsDeliverDateTime());
            createXmlTag(xs, "tsBeginDateTime", tsObject.getTsBeginDateTime());
            createXmlTag(xs, "tsFinishDateTime", tsObject.getTsFinishDateTime());

            List<TSSolution> thisTsSolutions = tsObject.getTsSolutions();//排故方法列表
            if (null != thisTsSolutions && thisTsSolutions.size() > 0) {
                for (int i = 0, len = thisTsSolutions.size(); i < len; i++) {
                    TSSolution tsSolution = thisTsSolutions.get(i);
                    xs.startTag(null, "TSSolution");
                    createXmlTag(xs, "tsSolutionIDLable", tsSolution.getTsSolutionIDLable());
                    createXmlTag(xs, "tsIDLable", tsSolution.getTsIDLable());
                    createXmlTag(xs, "tsShortNameLable", tsSolution.getTsShortNameLable());
                    createXmlTag(xs, "tsSolutionNumberLable", tsSolution.getTsSolutionNumberLable());
                    createXmlTag(xs, "tsSolutionNameLable", tsSolution.getTsSolutionNameLable());
                    createXmlTag(xs, "possibleReasonLable", tsSolution.getPossibleReasonLable());
                    createXmlTag(xs, "isUsedLable", tsSolution.getIsUsedLable());
                    createXmlTag(xs, "isWorkLable", tsSolution.getIsWorkLable());

                    List<String> thisSolutionPictureListLable = tsSolution.getPictureListLable();//照片列表
                    List<String> thisSolutionVideoListLable = tsSolution.getVideoListLable();//视频列表
                    List<String> thisSolutionAudioListLable = tsSolution.getAudioListLable();//音频列表

                    xs.startTag(null, "pictureListLable");
                    if (null != thisSolutionPictureListLable && thisSolutionPictureListLable.size() > 0) {
                        for (int j = 0, length = thisSolutionPictureListLable.size(); j < length; j++) {
                            createXmlTag(xs, "Element", thisSolutionPictureListLable.get(j));
                        }
                    }
                    xs.endTag(null, "pictureListLable");

                    xs.startTag(null, "videoListLable");
                    if (null != thisSolutionVideoListLable && thisSolutionVideoListLable.size() > 0) {
                        for (int j = 0, length = thisSolutionVideoListLable.size(); j < length; j++) {
                            createXmlTag(xs, "Element", thisSolutionVideoListLable.get(j));
                        }
                    }
                    xs.endTag(null, "videoListLable");

                    xs.startTag(null, "audioListLable");
                    if (null != thisSolutionAudioListLable && thisSolutionAudioListLable.size() > 0) {
                        for (int j = 0, length = thisSolutionAudioListLable.size(); j < length; j++) {
                            createXmlTag(xs, "Element", thisSolutionAudioListLable.get(j));
                        }
                    }
                    xs.endTag(null, "audioListLable");

                    createXmlTag(xs, "reserveTssParameter1Lable", tsSolution.getReserveTssParameter1Lable());
                    createXmlTag(xs, "reserveTssParameter2Lable", tsSolution.getReserveTssParameter2Lable());
                    createXmlTag(xs, "reserveTssParameter3Lable", tsSolution.getReserveTssParameter3Lable());
                    createXmlTag(xs, "reserveTssParameter4Lable", tsSolution.getReserveTssParameter4Lable());
                    createXmlTag(xs, "reserveTssParameter5Lable", tsSolution.getReserveTssParameter5Lable());
                    createXmlTag(xs, "reserveTssParameter6Lable", tsSolution.getReserveTssParameter6Lable());
                    createXmlTag(xs, "reserveTssParameter7Lable", tsSolution.getReserveTssParameter7Lable());
                    createXmlTag(xs, "reserveTssParameter8Lable", tsSolution.getReserveTssParameter8Lable());

                    createXmlTag(xs, "tsSolutionID", tsSolution.getTsSolutionID());
                    createXmlTag(xs, "tsID", tsSolution.getTsID());
                    createXmlTag(xs, "tsShortName", tsSolution.getTsShortName());
                    createXmlTag(xs, "tsSolutionNumber", tsSolution.getTsSolutionNumber() + "");
                    createXmlTag(xs, "tsSolutionName", tsSolution.getTsSolutionName());
                    createXmlTag(xs, "possibleReason", tsSolution.getPossibleReason());
                    createXmlTag(xs, "tsDescription", tsSolution.getTsDescription());
                    createXmlTag(xs, "tsSolution", tsSolution.getTsSolution());
                    createXmlTag(xs, "tsRemark", tsSolution.getTsRemark());
                    createXmlTag(xs, "isUsed", tsSolution.getIsUsed() + "");
                    createXmlTag(xs, "isWork", tsSolution.getIsWork() + "");

                    List<String> thisSolutionPictureList = tsSolution.getPictureList();//照片列表
                    List<String> thisSolutionVideoList = tsSolution.getVideoList();//视频列表
                    List<String> thisSolutionAudioList = tsSolution.getAudioList();//音频列表
                    xs.startTag(null, "pictureList");
                    if (null != thisSolutionPictureList && thisSolutionPictureList.size() > 0) {
                        for (int j = 0, length = thisSolutionPictureList.size(); j < length; j++) {
                            createXmlTag(xs, "Element", thisSolutionPictureList.get(j));
                        }
                    }
                    xs.endTag(null, "pictureList");

                    xs.startTag(null, "videoList");
                    if (null != thisSolutionVideoList && thisSolutionVideoList.size() > 0) {
                        for (int j = 0, length = thisSolutionVideoList.size(); j < length; j++) {
                            createXmlTag(xs, "Element", thisSolutionVideoList.get(j));
                        }
                    }
                    xs.endTag(null, "videoList");

                    xs.startTag(null, "audioList");
                    if (null != thisSolutionAudioList && thisSolutionAudioList.size() > 0) {
                        for (int j = 0, length = thisSolutionAudioList.size(); j < length; j++) {
                            createXmlTag(xs, "Element", thisSolutionAudioList.get(j));
                        }
                    }
                    xs.endTag(null, "audioList");

                    createXmlTag(xs, "reserveTssParameter1", tsSolution.getReserveTssParameter1());
                    createXmlTag(xs, "reserveTssParameter2", tsSolution.getReserveTssParameter2());
                    createXmlTag(xs, "reserveTssParameter3", tsSolution.getReserveTssParameter3());
                    createXmlTag(xs, "reserveTssParameter4", tsSolution.getReserveTssParameter4());
                    createXmlTag(xs, "reserveTssParameter5", tsSolution.getReserveTssParameter5());
                    createXmlTag(xs, "reserveTssParameter6", tsSolution.getReserveTssParameter6());
                    createXmlTag(xs, "reserveTssParameter7", tsSolution.getReserveTssParameter7());
                    createXmlTag(xs, "reserveTssParameter8", tsSolution.getReserveTssParameter8());

                    xs.endTag(null, "TSSolution");
                }
            }

            createXmlTag(xs, "realReason", tsObject.getRealReason());
            createXmlTag(xs, "remark", TSObjectAction.getShowRemark(tsObject.getTsID(), tsObject.getTsSolutions()));

            List<String> thisPictureList = tsObject.getPictureList();//照片列表
            List<String> thisVideoList = tsObject.getVideoList();//视频列表
            List<String> thisAudioList = tsObject.getAudioList();//音频列表
            xs.startTag(null, "pictureList");
            if (null != thisPictureList && thisPictureList.size() > 0) {
                for (int j = 0, length = thisPictureList.size(); j < length; j++) {
                    createXmlTag(xs, "Element", thisPictureList.get(j));
                }
            }
            xs.endTag(null, "pictureList");

            xs.startTag(null, "videoList");
            if (null != thisVideoList && thisVideoList.size() > 0) {
                for (int j = 0, length = thisVideoList.size(); j < length; j++) {
                    createXmlTag(xs, "Element", thisVideoList.get(j));
                }
            }
            xs.endTag(null, "videoList");

            xs.startTag(null, "audioList");
            if (null != thisAudioList && thisAudioList.size() > 0) {
                for (int j = 0, length = thisAudioList.size(); j < length; j++) {
                    createXmlTag(xs, "Element", thisAudioList.get(j));
                }
            }
            xs.endTag(null, "audioList");

            createXmlTag(xs, "reserveTsParameter1", tsObject.getReserveTsParameter1());
            createXmlTag(xs, "reserveTsParameter2", tsObject.getReserveTsParameter2());
            createXmlTag(xs, "reserveTsParameter3", tsObject.getReserveTsParameter3());
            createXmlTag(xs, "reserveTsParameter4", tsObject.getReserveTsParameter4());
            createXmlTag(xs, "reserveTsParameter5", tsObject.getReserveTsParameter5());
            createXmlTag(xs, "reserveTsParameter6", tsObject.getReserveTsParameter6());
            createXmlTag(xs, "reserveTsParameter7", tsObject.getReserveTsParameter7());
            createXmlTag(xs, "reserveTsParameter8", tsObject.getReserveTsParameter8());

            xs.endTag(null, "Elements");
            xs.endTag(null, "TS");

            xs.endDocument();
            xs.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //创建xml标签
    private static void createXmlTag(XmlSerializer xs, String tag, String text) throws IOException {
        xs.startTag(null, tag);
        if (!TextUtils.isEmpty(text)) {
            xs.text(text);
        }
        xs.endTag(null, tag);
    }

    //从Json中解析出Bean
    public static TSObject fromJson(JSONObject json) {
        TSObject tsObject = new TSObject();
        tsObject.setFileName(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_RECEIVE_FILE_NAME));
        tsObject.setTsIDLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_ID_LABLE));
        tsObject.setWorkLocationLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_WORK_LOCATION_LABLE));
        tsObject.setTsNumberLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_NUMBER_LABLE));
        tsObject.setTsShortNameLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_SHORT_NAME_LABLE));
        tsObject.setTsFullNameLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_FULL_NAME_LABLE));
        tsObject.setTsPositionLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_POSITION_LABLE));
        tsObject.setObjectModelLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_OBJECT_MODEL_LABLE));
        tsObject.setObjectNumberLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_OBJECT_NUMBER_LABLE));
        tsObject.setPartNumberLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_PART_NUMBER_LABLE));
        tsObject.setPartPositionLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_PART_POSITION_LABLE));
        tsObject.setTsPersonInChargeLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_PERSON_IN_CHARGE_LABLE));
        tsObject.setTsWorkerNameLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_WORKER_NAME_LABLE));
        tsObject.setTsAuditorLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_AUDITOR_LABLE));
        tsObject.setTsDeliverDateTimeLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_DELIVER_DATETIME_LABLE));
        tsObject.setTsBeginDateTimeLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_BEGIN_DATETIME_LABLE));
        tsObject.setTsFinishDateTimeLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_FINISH_DATETIME_LABLE));
        tsObject.setTsSolutionNumberLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_SOLUTION_NUMBER_LABLE));
        tsObject.setTsSolutionsLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_SOLUTIONS_LABLE));
        tsObject.setTsPossibleReasonLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_POSSIBLE_REASON_LABLE));
        tsObject.setTsIsUsedLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_IS_USED_LABLE));
        tsObject.setTsIsWorkLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_IS_WORK_LABLE));
        tsObject.setRealReasonLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_REAL_REASON_LABLE));
        tsObject.setRemarkLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_REMARK_LABLE));
        tsObject.setPictureLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_PICTURE_LABLE));
        tsObject.setVideoLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_VIDEO_LABLE));
        tsObject.setAudioLable(json.optString(DBConstants.T_TSOBJECT.COLUMN_AUDIO_LABLE));
        tsObject.setPictureListLable(strToList(json.optString(DBConstants.T_TSOBJECT.COLUMN_PICTURE_LIST_LABLE)));
        tsObject.setVideoListLable(strToList(json.optString(DBConstants.T_TSOBJECT.COLUMN_VIDEO_LIST_LABLE)));
        tsObject.setAudioListLable(strToList(json.optString(DBConstants.T_TSOBJECT.COLUMN_AUDIO_LIST_LABLE)));
        tsObject.setReserveTsParameter1Lable(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER1LABLE));
        tsObject.setReserveTsParameter2Lable(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER2LABLE));
        tsObject.setReserveTsParameter3Lable(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER3LABLE));
        tsObject.setReserveTsParameter4Lable(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER4LABLE));
        tsObject.setReserveTsParameter5Lable(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER5LABLE));
        tsObject.setReserveTsParameter6Lable(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER6LABLE));
        tsObject.setReserveTsParameter7Lable(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER7LABLE));
        tsObject.setReserveTsParameter8Lable(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER8LABLE));

        tsObject.setTsID(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_ID));
        tsObject.setIsAddCustomFun(json.optInt(DBConstants.T_TSOBJECT.COLUMN_IS_ADD_CUSTOM_FUN) == 1);
        tsObject.setWorkLocation(json.optString(DBConstants.T_TSOBJECT.COLUMN_WORK_LOCATION));
        tsObject.setTsNumber(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_NUMBER));
        tsObject.setTsShortName(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_SHORT_NAME));
        tsObject.setTsFullName(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_FULL_NAME));
        tsObject.setTsPosition(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_POSITION));
        tsObject.setObjectModel(json.optString(DBConstants.T_TSOBJECT.COLUMN_OBJECT_MODEL));
        tsObject.setObjectNumber(json.optString(DBConstants.T_TSOBJECT.COLUMN_OBJECT_NUMBER));
        tsObject.setPartNumber(json.optString(DBConstants.T_TSOBJECT.COLUMN_PART_NUMBER));
        tsObject.setPartPosition(json.optString(DBConstants.T_TSOBJECT.COLUMN_PART_POSITION));
        tsObject.setTsPersonInCharge(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_PERSON_IN_CHARGE));
        tsObject.setTsWorkerName(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_WORKER_NAME));
        tsObject.setTsAuditor(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_AUDITOR));
        tsObject.setTsDeliverDateTime(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_DELIVER_DATETIME));
        tsObject.setTsBeginDateTime(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_BEGIN_DATETIME));
        tsObject.setTsFinishDateTime(json.optString(DBConstants.T_TSOBJECT.COLUMN_TS_FINISH_DATETIME));
        tsObject.setRealReason(json.optString(DBConstants.T_TSOBJECT.COLUMN_REAL_REASON));
        tsObject.setRemark(json.optString(DBConstants.T_TSOBJECT.COLUMN_REMARK));
        tsObject.setPictureList(strToList(json.optString(DBConstants.T_TSOBJECT.COLUMN_PICTURE_LIST)));
        tsObject.setVideoList(strToList(json.optString(DBConstants.T_TSOBJECT.COLUMN_VIDEO_LIST)));
        tsObject.setAudioList(strToList(json.optString(DBConstants.T_TSOBJECT.COLUMN_AUDIO_LIST)));
        tsObject.setReserveTsParameter1(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER1));
        tsObject.setReserveTsParameter2(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER2));
        tsObject.setReserveTsParameter3(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER3));
        tsObject.setReserveTsParameter4(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER4));
        tsObject.setReserveTsParameter5(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER5));
        tsObject.setReserveTsParameter6(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER6));
        tsObject.setReserveTsParameter7(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER7));
        tsObject.setReserveTsParameter8(json.optString(DBConstants.T_TSOBJECT.COLUMN_RESERVETSPARAMETER8));

        return tsObject;
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
        return "TSObject{" +
                "fileName='" + fileName + '\'' +
                ", isAddCustomFun=" + isAddCustomFun +
                ", tsIDLable='" + tsIDLable + '\'' +
                ", workLocationLable='" + workLocationLable + '\'' +
                ", tsNumberLable='" + tsNumberLable + '\'' +
                ", tsShortNameLable='" + tsShortNameLable + '\'' +
                ", tsFullNameLable='" + tsFullNameLable + '\'' +
                ", tsPositionLable='" + tsPositionLable + '\'' +
                ", objectModelLable='" + objectModelLable + '\'' +
                ", objectNumberLable='" + objectNumberLable + '\'' +
                ", partNumberLable='" + partNumberLable + '\'' +
                ", partPositionLable='" + partPositionLable + '\'' +
                ", tsPersonInChargeLable='" + tsPersonInChargeLable + '\'' +
                ", tsWorkerNameLable='" + tsWorkerNameLable + '\'' +
                ", tsAuditorLable='" + tsAuditorLable + '\'' +
                ", tsDeliverDateTimeLable='" + tsDeliverDateTimeLable + '\'' +
                ", tsBeginDateTimeLable='" + tsBeginDateTimeLable + '\'' +
                ", tsFinishDateTimeLable='" + tsFinishDateTimeLable + '\'' +
                ", tsSolutionNumberLable='" + tsSolutionNumberLable + '\'' +
                ", tsSolutionsLable='" + tsSolutionsLable + '\'' +
                ", tsPossibleReasonLable='" + tsPossibleReasonLable + '\'' +
                ", tsIsUsedLable='" + tsIsUsedLable + '\'' +
                ", tsIsWorkLable='" + tsIsWorkLable + '\'' +
                ", realReasonLable='" + realReasonLable + '\'' +
                ", remarkLable='" + remarkLable + '\'' +
                ", pictureLable='" + pictureLable + '\'' +
                ", videoLable='" + videoLable + '\'' +
                ", audioLable='" + audioLable + '\'' +
                ", pictureListLable=" + pictureListLable +
                ", videoListLable=" + videoListLable +
                ", audioListLable=" + audioListLable +
                ", reserveTsParameter1Lable='" + reserveTsParameter1Lable + '\'' +
                ", reserveTsParameter2Lable='" + reserveTsParameter2Lable + '\'' +
                ", reserveTsParameter3Lable='" + reserveTsParameter3Lable + '\'' +
                ", reserveTsParameter4Lable='" + reserveTsParameter4Lable + '\'' +
                ", reserveTsParameter5Lable='" + reserveTsParameter5Lable + '\'' +
                ", reserveTsParameter6Lable='" + reserveTsParameter6Lable + '\'' +
                ", reserveTsParameter7Lable='" + reserveTsParameter7Lable + '\'' +
                ", reserveTsParameter8Lable='" + reserveTsParameter8Lable + '\'' +
                ", tsID='" + tsID + '\'' +
                ", workLocation='" + workLocation + '\'' +
                ", tsNumber='" + tsNumber + '\'' +
                ", tsShortName='" + tsShortName + '\'' +
                ", tsFullName='" + tsFullName + '\'' +
                ", tsPosition='" + tsPosition + '\'' +
                ", objectModel='" + objectModel + '\'' +
                ", objectNumber='" + objectNumber + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", partPosition='" + partPosition + '\'' +
                ", tsPersonInCharge='" + tsPersonInCharge + '\'' +
                ", tsWorkerName='" + tsWorkerName + '\'' +
                ", tsAuditor='" + tsAuditor + '\'' +
                ", tsDeliverDateTime='" + tsDeliverDateTime + '\'' +
                ", tsBeginDateTime='" + tsBeginDateTime + '\'' +
                ", tsFinishDateTime='" + tsFinishDateTime + '\'' +
                ", tsSolutions=" + tsSolutions +
                ", realReason='" + realReason + '\'' +
                ", remark='" + remark + '\'' +
                ", pictureList=" + pictureList +
                ", videoList=" + videoList +
                ", audioList=" + audioList +
                ", reserveTsParameter1='" + reserveTsParameter1 + '\'' +
                ", reserveTsParameter2='" + reserveTsParameter2 + '\'' +
                ", reserveTsParameter3='" + reserveTsParameter3 + '\'' +
                ", reserveTsParameter4='" + reserveTsParameter4 + '\'' +
                ", reserveTsParameter5='" + reserveTsParameter5 + '\'' +
                ", reserveTsParameter6='" + reserveTsParameter6 + '\'' +
                ", reserveTsParameter7='" + reserveTsParameter7 + '\'' +
                ", reserveTsParameter8='" + reserveTsParameter8 + '\'' +
                '}';
    }

    public boolean getIsAddCustomFun() {
        return isAddCustomFun;
    }

    public void setIsAddCustomFun(boolean addCustomFun) {
        isAddCustomFun = addCustomFun;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTsIDLable() {
        return tsIDLable;
    }

    public void setTsIDLable(String tsIDLable) {
        this.tsIDLable = tsIDLable;
    }

    public String getWorkLocationLable() {
        return workLocationLable;
    }

    public void setWorkLocationLable(String workLocationLable) {
        this.workLocationLable = workLocationLable;
    }

    public String getTsNumberLable() {
        return tsNumberLable;
    }

    public void setTsNumberLable(String tsNumberLable) {
        this.tsNumberLable = tsNumberLable;
    }

    public String getTsShortNameLable() {
        return tsShortNameLable;
    }

    public void setTsShortNameLable(String tsShortNameLable) {
        this.tsShortNameLable = tsShortNameLable;
    }

    public String getTsFullNameLable() {
        return tsFullNameLable;
    }

    public void setTsFullNameLable(String tsFullNameLable) {
        this.tsFullNameLable = tsFullNameLable;
    }

    public String getTsPositionLable() {
        return tsPositionLable;
    }

    public void setTsPositionLable(String tsPositionLable) {
        this.tsPositionLable = tsPositionLable;
    }

    public String getObjectModelLable() {
        return objectModelLable;
    }

    public void setObjectModelLable(String objectModelLable) {
        this.objectModelLable = objectModelLable;
    }

    public String getObjectNumberLable() {
        return objectNumberLable;
    }

    public void setObjectNumberLable(String objectNumberLable) {
        this.objectNumberLable = objectNumberLable;
    }

    public String getPartNumberLable() {
        return partNumberLable;
    }

    public void setPartNumberLable(String partNumberLable) {
        this.partNumberLable = partNumberLable;
    }

    public String getPartPositionLable() {
        return partPositionLable;
    }

    public void setPartPositionLable(String partPositionLable) {
        this.partPositionLable = partPositionLable;
    }

    public String getTsPersonInChargeLable() {
        return tsPersonInChargeLable;
    }

    public void setTsPersonInChargeLable(String tsPersonInChargeLable) {
        this.tsPersonInChargeLable = tsPersonInChargeLable;
    }

    public String getTsWorkerNameLable() {
        return tsWorkerNameLable;
    }

    public void setTsWorkerNameLable(String tsWorkerNameLable) {
        this.tsWorkerNameLable = tsWorkerNameLable;
    }

    public String getTsAuditorLable() {
        return tsAuditorLable;
    }

    public void setTsAuditorLable(String tsAuditorLable) {
        this.tsAuditorLable = tsAuditorLable;
    }

    public String getTsDeliverDateTimeLable() {
        return tsDeliverDateTimeLable;
    }

    public void setTsDeliverDateTimeLable(String tsDeliverDateTimeLable) {
        this.tsDeliverDateTimeLable = tsDeliverDateTimeLable;
    }

    public String getTsBeginDateTimeLable() {
        return tsBeginDateTimeLable;
    }

    public void setTsBeginDateTimeLable(String tsBeginDateTimeLable) {
        this.tsBeginDateTimeLable = tsBeginDateTimeLable;
    }

    public String getTsFinishDateTimeLable() {
        return tsFinishDateTimeLable;
    }

    public void setTsFinishDateTimeLable(String tsFinishDateTimeLable) {
        this.tsFinishDateTimeLable = tsFinishDateTimeLable;
    }

    public String getTsSolutionNumberLable() {
        return tsSolutionNumberLable;
    }

    public void setTsSolutionNumberLable(String tsSolutionNumberLable) {
        this.tsSolutionNumberLable = tsSolutionNumberLable;
    }

    public String getTsSolutionsLable() {
        return tsSolutionsLable;
    }

    public void setTsSolutionsLable(String tsSolutionsLable) {
        this.tsSolutionsLable = tsSolutionsLable;
    }

    public String getTsPossibleReasonLable() {
        return tsPossibleReasonLable;
    }

    public void setTsPossibleReasonLable(String tsPossibleReasonLable) {
        this.tsPossibleReasonLable = tsPossibleReasonLable;
    }

    public String getTsIsUsedLable() {
        return tsIsUsedLable;
    }

    public void setTsIsUsedLable(String tsIsUsedLable) {
        this.tsIsUsedLable = tsIsUsedLable;
    }

    public String getTsIsWorkLable() {
        return tsIsWorkLable;
    }

    public void setTsIsWorkLable(String tsIsWorkLable) {
        this.tsIsWorkLable = tsIsWorkLable;
    }

    public String getRealReasonLable() {
        return realReasonLable;
    }

    public void setRealReasonLable(String realReasonLable) {
        this.realReasonLable = realReasonLable;
    }

    public String getRemarkLable() {
        return remarkLable;
    }

    public void setRemarkLable(String remarkLable) {
        this.remarkLable = remarkLable;
    }

    public String getPictureLable() {
        return pictureLable;
    }

    public void setPictureLable(String pictureLable) {
        this.pictureLable = pictureLable;
    }

    public String getVideoLable() {
        return videoLable;
    }

    public void setVideoLable(String videoLable) {
        this.videoLable = videoLable;
    }

    public String getAudioLable() {
        return audioLable;
    }

    public void setAudioLable(String audioLable) {
        this.audioLable = audioLable;
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

    public String getReserveTsParameter1Lable() {
        return reserveTsParameter1Lable;
    }

    public void setReserveTsParameter1Lable(String reserveTsParameter1Lable) {
        this.reserveTsParameter1Lable = reserveTsParameter1Lable;
    }

    public String getReserveTsParameter2Lable() {
        return reserveTsParameter2Lable;
    }

    public void setReserveTsParameter2Lable(String reserveTsParameter2Lable) {
        this.reserveTsParameter2Lable = reserveTsParameter2Lable;
    }

    public String getReserveTsParameter3Lable() {
        return reserveTsParameter3Lable;
    }

    public void setReserveTsParameter3Lable(String reserveTsParameter3Lable) {
        this.reserveTsParameter3Lable = reserveTsParameter3Lable;
    }

    public String getReserveTsParameter4Lable() {
        return reserveTsParameter4Lable;
    }

    public void setReserveTsParameter4Lable(String reserveTsParameter4Lable) {
        this.reserveTsParameter4Lable = reserveTsParameter4Lable;
    }

    public String getReserveTsParameter5Lable() {
        return reserveTsParameter5Lable;
    }

    public void setReserveTsParameter5Lable(String reserveTsParameter5Lable) {
        this.reserveTsParameter5Lable = reserveTsParameter5Lable;
    }

    public String getReserveTsParameter6Lable() {
        return reserveTsParameter6Lable;
    }

    public void setReserveTsParameter6Lable(String reserveTsParameter6Lable) {
        this.reserveTsParameter6Lable = reserveTsParameter6Lable;
    }

    public String getReserveTsParameter7Lable() {
        return reserveTsParameter7Lable;
    }

    public void setReserveTsParameter7Lable(String reserveTsParameter7Lable) {
        this.reserveTsParameter7Lable = reserveTsParameter7Lable;
    }

    public String getReserveTsParameter8Lable() {
        return reserveTsParameter8Lable;
    }

    public void setReserveTsParameter8Lable(String reserveTsParameter8Lable) {
        this.reserveTsParameter8Lable = reserveTsParameter8Lable;
    }

    public String getTsID() {
        return tsID;
    }

    public void setTsID(String tsID) {
        this.tsID = tsID;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getTsNumber() {
        return tsNumber;
    }

    public void setTsNumber(String tsNumber) {
        this.tsNumber = tsNumber;
    }

    public String getTsShortName() {
        return tsShortName;
    }

    public void setTsShortName(String tsShortName) {
        this.tsShortName = tsShortName;
    }

    public String getTsFullName() {
        return tsFullName;
    }

    public void setTsFullName(String tsFullName) {
        this.tsFullName = tsFullName;
    }

    public String getTsPosition() {
        return tsPosition;
    }

    public void setTsPosition(String tsPosition) {
        this.tsPosition = tsPosition;
    }

    public String getObjectModel() {
        return objectModel;
    }

    public void setObjectModel(String objectModel) {
        this.objectModel = objectModel;
    }

    public String getObjectNumber() {
        return objectNumber;
    }

    public void setObjectNumber(String objectNumber) {
        this.objectNumber = objectNumber;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartPosition() {
        return partPosition;
    }

    public void setPartPosition(String partPosition) {
        this.partPosition = partPosition;
    }

    public String getTsPersonInCharge() {
        return tsPersonInCharge;
    }

    public void setTsPersonInCharge(String tsPersonInCharge) {
        this.tsPersonInCharge = tsPersonInCharge;
    }

    public String getTsWorkerName() {
        return tsWorkerName;
    }

    public void setTsWorkerName(String tsWorkerName) {
        this.tsWorkerName = tsWorkerName;
    }

    public String getTsAuditor() {
        return tsAuditor;
    }

    public void setTsAuditor(String tsAuditor) {
        this.tsAuditor = tsAuditor;
    }

    public String getTsDeliverDateTime() {
        return tsDeliverDateTime;
    }

    public void setTsDeliverDateTime(String tsDeliverDateTime) {
        this.tsDeliverDateTime = tsDeliverDateTime;
    }

    public String getTsBeginDateTime() {
        return tsBeginDateTime;
    }

    public void setTsBeginDateTime(String tsBeginDateTime) {
        this.tsBeginDateTime = tsBeginDateTime;
    }

    public String getTsFinishDateTime() {
        return tsFinishDateTime;
    }

    public void setTsFinishDateTime(String tsFinishDateTime) {
        this.tsFinishDateTime = tsFinishDateTime;
    }

    public List<TSSolution> getTsSolutions() {
        return tsSolutions;
    }

    public void setTsSolutions(List<TSSolution> tsSolutions) {
        this.tsSolutions = tsSolutions;
    }

    public String getRealReason() {
        return realReason;
    }

    public void setRealReason(String realReason) {
        this.realReason = realReason;
    }

    public String getRemark() {
        return TextUtils.isEmpty(remark) ? "" : remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getReserveTsParameter1() {
        return reserveTsParameter1;
    }

    public void setReserveTsParameter1(String reserveTsParameter1) {
        this.reserveTsParameter1 = reserveTsParameter1;
    }

    public String getReserveTsParameter2() {
        return reserveTsParameter2;
    }

    public void setReserveTsParameter2(String reserveTsParameter2) {
        this.reserveTsParameter2 = reserveTsParameter2;
    }

    public String getReserveTsParameter3() {
        return reserveTsParameter3;
    }

    public void setReserveTsParameter3(String reserveTsParameter3) {
        this.reserveTsParameter3 = reserveTsParameter3;
    }

    public String getReserveTsParameter4() {
        return reserveTsParameter4;
    }

    public void setReserveTsParameter4(String reserveTsParameter4) {
        this.reserveTsParameter4 = reserveTsParameter4;
    }

    public String getReserveTsParameter5() {
        return reserveTsParameter5;
    }

    public void setReserveTsParameter5(String reserveTsParameter5) {
        this.reserveTsParameter5 = reserveTsParameter5;
    }

    public String getReserveTsParameter6() {
        return reserveTsParameter6;
    }

    public void setReserveTsParameter6(String reserveTsParameter6) {
        this.reserveTsParameter6 = reserveTsParameter6;
    }

    public String getReserveTsParameter7() {
        return reserveTsParameter7;
    }

    public void setReserveTsParameter7(String reserveTsParameter7) {
        this.reserveTsParameter7 = reserveTsParameter7;
    }

    public String getReserveTsParameter8() {
        return reserveTsParameter8;
    }

    public void setReserveTsParameter8(String reserveTsParameter8) {
        this.reserveTsParameter8 = reserveTsParameter8;
    }
}
