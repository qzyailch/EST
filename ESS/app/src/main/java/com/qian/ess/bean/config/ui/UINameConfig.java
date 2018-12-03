package com.qian.ess.bean.config.ui;

import android.util.Xml;

import com.qian.ess.common.Constants;
import com.qian.ess.utils.Logger;
import com.qian.ess.utils.ToastUtils;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QianMoLi on 2018/11/19.
 * <p>
 * UINameConfig，从配置文件中获取到的对象
 */

public class UINameConfig {

    private List<UIName> uiNames = new ArrayList<>();
    private List<TSUIName> tsuiNames = new ArrayList<>();
    private List<TCUIName> tcuiNames = new ArrayList<>();

    //从Xml文件中解析出Bean
    public static UINameConfig fromXml(String xmlPath) {
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

        //UINameConfig
        UINameConfig uiNameConfig = new UINameConfig();
        //UIName
        UIName uiName = null;
        //TSUIName
        TSUIName tsuiName = null;
        //TCUIName
        TCUIName tcuiName = null;

        //当前正在解析的语言
        String language = null;
        //当前正在解析的对象，TS TC
        String nowObject = null;

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
                            /********UIName*********/
                            case "English":
                                uiName = new UIName();
                                uiName.setLanguage(Constants.LANGUAGE.ENGLISH);
                                language = Constants.LANGUAGE.ENGLISH;
                                break;
                            case "Chinese":
                                uiName = new UIName();
                                uiName.setLanguage(Constants.LANGUAGE.CHINESE);
                                language = Constants.LANGUAGE.CHINESE;
                                break;
                            case "Object":
                                if (null != uiName) {
                                    uiName.setObject(parser.nextText());
                                }
                                break;
                            case "Member":
                                if (null != uiName) {
                                    uiName.setMember(parser.nextText());
                                }
                                break;
                            case "O_Name":
                                if (null != uiName) {
                                    uiName.setOName(parser.nextText());
                                }
                                break;
                            case "M_Name":
                                if (null != uiName) {
                                    uiName.setMName(parser.nextText());
                                }
                                break;
                            case "Base":
                                if (null != uiName) {
                                    uiName.setBase(parser.nextText());
                                }
                                break;
                            case "NoO":
                                if (null != uiName) {
                                    uiName.setNoO(parser.nextText());
                                }
                                break;
                            case "NoM1":
                                if (null != uiName) {
                                    uiName.setNoM1(parser.nextText());
                                }
                                break;
                            case "NoM2":
                                if (null != uiName) {
                                    uiName.setNoM2(parser.nextText());
                                }
                                break;
                            case "SS":
                                if (null != uiName) {
                                    uiName.setSs(parser.nextText());
                                }
                                break;
                            case "TR":
                                if (null != uiName) {
                                    uiName.setTr(parser.nextText());
                                }
                                break;
                            case "ED":
                                if (null != uiName) {
                                    uiName.setEd(parser.nextText());
                                }
                                break;
                            case "EC":
                                if (null != uiName) {
                                    uiName.setEc(parser.nextText());
                                }
                                break;
                            case "EM":
                                if (null != uiName) {
                                    uiName.setEm(parser.nextText());
                                }
                                break;
                            case "AEM":
                                if (null != uiName) {
                                    uiName.setAem(parser.nextText());
                                }
                                break;
                            case "TSEM":
                                if (null != uiName) {
                                    uiName.setTsem(parser.nextText());
                                }
                                break;
                            case "ESTM":
                                if (null != uiName) {
                                    uiName.setEstm(parser.nextText());
                                }
                                break;
                            /********TSUIName*********/
                            case "TS":
                                tsuiName = new TSUIName();
                                tsuiName.setLanguage(language);
                                nowObject = "TS";
                                break;
                            case "workLocationLabel":
                                if ("TS".equals(nowObject)) {
                                    tsuiName.setWorkLocationLabel(parser.nextText());
                                } else if ("PC".equals(nowObject)) {
                                    tcuiName.setWorkLocationLabel(parser.nextText());
                                }
                                break;
                            case "tsNumberLabel":
                                tsuiName.setTsNumberLabel(parser.nextText());
                                break;
                            case "tsShortNameLabel":
                                tsuiName.setTsShortNameLabel(parser.nextText());
                                break;
                            case "tsFullNameLabel":
                                tsuiName.setTsFullNameLabel(parser.nextText());
                                break;
                            case "tsPositionLabel":
                                tsuiName.setTsPositionLabel(parser.nextText());
                                break;
                            case "objectModelLabel":
                                if ("TS".equals(nowObject)) {
                                    tsuiName.setObjectModelLabel(parser.nextText());
                                } else if ("PC".equals(nowObject)) {
                                    tcuiName.setObjectModelLabel(parser.nextText());
                                }
                                break;
                            case "objectNumberLabel":
                                if ("TS".equals(nowObject)) {
                                    tsuiName.setObjectNumberLabel(parser.nextText());
                                } else if ("PC".equals(nowObject)) {
                                    tcuiName.setObjectNumberLabel(parser.nextText());
                                }
                                break;
                            case "partNumberLabel":
                                if ("TS".equals(nowObject)) {
                                    tsuiName.setPartNumberLabel(parser.nextText());
                                } else if ("PC".equals(nowObject)) {
                                    tcuiName.setPartNumberLabel(parser.nextText());
                                }
                                break;
                            case "partPositionLabel":
                                if ("TS".equals(nowObject)) {
                                    tsuiName.setPartPositionLabel(parser.nextText());
                                } else if ("PC".equals(nowObject)) {
                                    tcuiName.setPartPositionLabel(parser.nextText());
                                }
                                break;
                            case "tsPersonInChargeLabel":
                                tsuiName.setTsPersonInChargeLabel(parser.nextText());
                                break;
                            case "tsWorkerNameLabel":
                                tsuiName.setTsWorkerNameLabel(parser.nextText());
                                break;
                            case "tsAuditorLabel":
                                tsuiName.setTsAuditorLabel(parser.nextText());
                                break;
                            case "tsDeliverDateTimeLabel":
                                tsuiName.setTsDeliverDateTimeLabel(parser.nextText());
                                break;
                            case "tsBeginDateTimeLabel":
                                tsuiName.setTsBeginDateTimeLabel(parser.nextText());
                                break;
                            case "tsFinishDateTimeLabel":
                                tsuiName.setTsFinishDateTimeLabel(parser.nextText());
                                break;
                            case "tsSolutionNumberLabel":
                                tsuiName.setTsSolutionNumberLabel(parser.nextText());
                                break;
                            case "tsSolutionsLabel":
                                tsuiName.setTsSolutionsLabel(parser.nextText());
                                break;
                            case "tsPossibleReasonLabel":
                                tsuiName.setTsPossibleReasonLabel(parser.nextText());
                                break;
                            case "tsIsUsedLabel":
                                tsuiName.setTsIsUsedLabel(parser.nextText());
                                break;
                            case "tsIsWorkLabel":
                                tsuiName.setTsIsWorkLabel(parser.nextText());
                                break;
                            case "realReasonLabel":
                                tsuiName.setRealReasonLabel(parser.nextText());
                                break;
                            case "remarkLabel":
                                if ("TS".equals(nowObject)) {
                                    tsuiName.setRemarkLabel(parser.nextText());
                                } else if ("PC".equals(nowObject)) {
                                    tcuiName.setRemarkLabel(parser.nextText());
                                }
                                break;
                            case "pictureLabel":
                                if ("TS".equals(nowObject)) {
                                    tsuiName.setPictureLabel(parser.nextText());
                                } else if ("PC".equals(nowObject)) {
                                    tcuiName.setPictureLabel(parser.nextText());
                                }
                                break;
                            case "videoLabel":
                                if ("TS".equals(nowObject)) {
                                    tsuiName.setVideoLabel(parser.nextText());
                                } else if ("PC".equals(nowObject)) {
                                    tcuiName.setVideoLabel(parser.nextText());
                                }
                                break;
                            case "audioLabel":
                                if ("TS".equals(nowObject)) {
                                    tsuiName.setAudioLabel(parser.nextText());
                                } else if ("PC".equals(nowObject)) {
                                    tcuiName.setAudioLabel(parser.nextText());
                                }
                                break;
                            case "usedLabel":
                                tsuiName.setUsedLabel(parser.nextText());
                                break;
                            case "workLabel":
                                tsuiName.setWorkLabel(parser.nextText());
                                break;
                            case "reasonLabel":
                                tsuiName.setReasonLabel(parser.nextText());
                                break;
                            case "descriptionLabel":
                                tsuiName.setDescriptionLabel(parser.nextText());
                                break;
                            case "solutionLabel":
                                tsuiName.setSolutionLabel(parser.nextText());
                                break;
                            /********TCUIName*********/
                            case "PC":
                                tcuiName = new TCUIName();
                                tcuiName.setLanguage(language);
                                nowObject = "PC";
                                break;
                            case "pcNumberLabel":
                                tcuiName.setPcNumberLabel(parser.nextText());
                                break;
                            case "pcPositionLabel":
                                tcuiName.setPcPositionLabel(parser.nextText());
                                break;
                            case "pcQCLabel":
                                tcuiName.setPcQCLabel(parser.nextText());
                                break;
                            case "pcPersonInChargeLabel":
                                tcuiName.setPcPersonInChargeLabel(parser.nextText());
                                break;
                            case "pcWorkerNameLabel":
                                tcuiName.setPcWorkerNameLabel(parser.nextText());
                                break;
                            case "pcAuditorLabel":
                                tcuiName.setPcAuditorLabel(parser.nextText());
                                break;
                            case "pcDeliverDateTimeLabel":
                                tcuiName.setPcDeliverDateTimeLabel(parser.nextText());
                                break;
                            case "pcBeginDateTimeLabel":
                                tcuiName.setPcBeginDateTimeLabel(parser.nextText());
                                break;
                            case "pcFinishDateTimeLabel":
                                tcuiName.setPcFinishDateTimeLabel(parser.nextText());
                                break;
                            case "pcTCNumberLabel":
                                tcuiName.setPcTCNumberLabel(parser.nextText());
                                break;
                            case "pcTCsLabel":
                                tcuiName.setPcTCsLabel(parser.nextText());
                                break;
                            case "pcCompleteLabel":
                                tcuiName.setPcCompleteLabel(parser.nextText());
                                break;
                            case "pcQuantityLabel":
                                tcuiName.setPcQuantityLabel(parser.nextText());
                                break;
                            default:
                                break;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endName = parser.getName();
                        switch (endName) {
                            case "English":
                            case "Chinese":
                                uiNameConfig.getUiNames().add(uiName);
                                uiName = null;
                                break;
                            case "TS":
                                uiNameConfig.getTsuiNames().add(tsuiName);
                                tsuiName = null;
                                break;
                            case "PC":
                                uiNameConfig.getTcuiNames().add(tcuiName);
                                tcuiName = null;
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
            is.close();
            return uiNameConfig;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "UINameConfig{" +
                "uiNames=" + uiNames +
                ", tsuiNames=" + tsuiNames +
                ", tcuiNames=" + tcuiNames +
                '}';
    }

    public List<UIName> getUiNames() {
        return uiNames;
    }

    public void setUiNames(List<UIName> uiNames) {
        this.uiNames = uiNames;
    }

    public List<TSUIName> getTsuiNames() {
        return tsuiNames;
    }

    public void setTsuiNames(List<TSUIName> tsuiNames) {
        this.tsuiNames = tsuiNames;
    }

    public List<TCUIName> getTcuiNames() {
        return tcuiNames;
    }

    public void setTcuiNames(List<TCUIName> tcuiNames) {
        this.tcuiNames = tcuiNames;
    }
}
