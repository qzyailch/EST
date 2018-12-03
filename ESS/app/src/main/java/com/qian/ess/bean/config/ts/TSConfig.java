package com.qian.ess.bean.config.ts;

import android.text.TextUtils;
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
 * TSConfig，从配置文件中获取到的对象
 */

public class TSConfig {

    private List<TSDoc> tsDocs = new ArrayList<>();
    private List<TSLabel> tsLabels = new ArrayList<>();
    private List<TSInfo> tsInfos = new ArrayList<>();

    //从Xml文件中解析出Bean
    public static TSConfig fromXml(String xmlPath) {
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

        //TSConfig
        TSConfig tsConfig = new TSConfig();
        //TSDoc
        TSDoc tsDoc = null;

        //由于tsLabels和tsInfos中都包含中英文标志，所以为了知道解析中英文标签时，是解析的哪一个List数据，临时记录一个保存当前解析List的标签名
        String startListTag = null;

        //TSLabel
        TSLabel tsLabel = null;
        //TSInfo
        TSInfo tsInfo = null;

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
                            case "TSLabels":
                                startListTag = "TSLabels";
                                break;
                            case "TSInfos":
                                startListTag = "TSInfos";
                                break;
                            /**************TSDoc***************/
                            case "EM":
                                tsDoc = new TSDoc();
                                tsDoc.setLabel("EM");
                                tsDoc.setText(parser.nextText());
                                tsConfig.getTsDocs().add(tsDoc);
                                break;
                            case "AEM":
                                tsDoc = new TSDoc();
                                tsDoc.setLabel("AEM");
                                tsDoc.setText(parser.nextText());
                                tsConfig.getTsDocs().add(tsDoc);
                                break;
                            case "TSEM":
                                tsDoc = new TSDoc();
                                tsDoc.setLabel("TSEM");
                                tsDoc.setText(parser.nextText());
                                tsConfig.getTsDocs().add(tsDoc);
                                break;
                            /**************TSLabel***************/
                            case "English":
                                if ("TSLabels".equals(startListTag)) {
                                    tsLabel = new TSLabel();
                                    tsLabel.setLanguage(Constants.LANGUAGE.ENGLISH);
                                } else if ("TSInfos".equals(startListTag)) {
                                    if (null != tsInfo) {
                                        tsInfo.setLanguage(Constants.LANGUAGE.ENGLISH);
                                    }
                                }
                                break;
                            case "Chinese":
                                if ("TSLabels".equals(startListTag)) {
                                    tsLabel = new TSLabel();
                                    tsLabel.setLanguage(Constants.LANGUAGE.CHINESE);
                                } else if ("TSInfos".equals(startListTag)) {
                                    if (null != tsInfo) {
                                        tsInfo.setLanguage(Constants.LANGUAGE.CHINESE);
                                    }
                                }
                                break;
                            case "TSShortName":
                                if ("TSLabels".equals(startListTag)) {
                                    if (null != tsLabel) {
                                        tsLabel.setTsShortName(parser.nextText());
                                    }
                                } else if ("TSInfos".equals(startListTag)) {
                                    if (null != tsInfo) {
                                        tsInfo.setTsShortName(parser.nextText());
                                    }
                                }
                                break;
                            case "TSColor":
                                if ("TSLabels".equals(startListTag)) {
                                    if (null != tsLabel) {
                                        tsLabel.setTsColor(parser.nextText());
                                    }
                                } else if ("TSInfos".equals(startListTag)) {
                                    if (null != tsInfo) {
                                        tsInfo.setTsColor(parser.nextText());
                                    }
                                }
                                break;
                            case "TSGroup":
                                if ("TSLabels".equals(startListTag)) {
                                    if (null != tsLabel) {
                                        tsLabel.setTsGroup(parser.nextText());
                                    }
                                } else if ("TSInfos".equals(startListTag)) {
                                    if (null != tsInfo) {
                                        tsInfo.setTsGroup(parser.nextText());
                                    }
                                }
                                break;
                            case "TroubleshootingWayEM":
                                if (null != tsLabel) {
                                    tsLabel.setTroubleshootingWayEM(parser.nextText());
                                }
                                break;
                            case "TroubleshootingWayAEM":
                                if (null != tsLabel) {
                                    tsLabel.setTroubleshootingWayAEM(parser.nextText());
                                }
                                break;
                            case "TroubleshootingWayTSEM":
                                if (null != tsLabel) {
                                    tsLabel.setTroubleshootingWayTSEM(parser.nextText());
                                }
                                break;
                            case "DescriptionEM":
                                if (null != tsLabel) {
                                    tsLabel.setDescriptionEM(parser.nextText());
                                }
                                break;
                            case "DescriptionAEM":
                                if (null != tsLabel) {
                                    tsLabel.setDescriptionAEM(parser.nextText());
                                }
                                break;
                            case "DescriptionTSEM":
                                if (null != tsLabel) {
                                    tsLabel.setDescriptionTSEM(parser.nextText());
                                }
                                break;
                            case "ReasonEM":
                                if (null != tsLabel) {
                                    tsLabel.setReasonEM(parser.nextText());
                                }
                                break;
                            case "ReasonAEM":
                                if (null != tsLabel) {
                                    tsLabel.setReasonAEM(parser.nextText());
                                }
                                break;
                            case "ReasonTSEM":
                                if (null != tsLabel) {
                                    tsLabel.setReasonTSEM(parser.nextText());
                                }
                                break;
                            case "ConditionEM":
                                if (null != tsLabel) {
                                    tsLabel.setConditionEM(parser.nextText());
                                }
                                break;
                            case "ConditionAEM":
                                if (null != tsLabel) {
                                    tsLabel.setConditionAEM(parser.nextText());
                                }
                                break;
                            case "ConditionTSEM":
                                if (null != tsLabel) {
                                    tsLabel.setConditionTSEM(parser.nextText());
                                }
                                break;
                            case "SolutionEM":
                                if (null != tsLabel) {
                                    tsLabel.setSolutionEM(parser.nextText());
                                }
                                break;
                            case "SolutionAEM":
                                if (null != tsLabel) {
                                    tsLabel.setSolutionAEM(parser.nextText());
                                }
                                break;
                            case "SolutionTSEM":
                                if (null != tsLabel) {
                                    tsLabel.setSolutionTSEM(parser.nextText());
                                }
                                break;
                            /**************TSInfo***************/
                            case "TSInfo":
                                tsInfo = new TSInfo();
                                break;
                            case "EMDescriptionPage":
                                if (null != tsInfo) {
                                    String page = parser.nextText();
                                    page = TextUtils.isEmpty(page) ? "0" : page;
                                    tsInfo.setEmDescriptionPage(Integer.parseInt(page));
                                }
                                break;
                            case "AEMDescriptionPage":
                                if (null != tsInfo) {
                                    String page = parser.nextText();
                                    page = TextUtils.isEmpty(page) ? "0" : page;
                                    tsInfo.setAemDescriptionPage(Integer.parseInt(page));
                                }
                                break;
                            case "TSEMDescriptionPage":
                                if (null != tsInfo) {
                                    String page = parser.nextText();
                                    page = TextUtils.isEmpty(page) ? "0" : page;
                                    tsInfo.setTsemDescriptionPage(Integer.parseInt(page));
                                }
                                break;
                            case "EMReasonPage":
                                if (null != tsInfo) {
                                    String page = parser.nextText();
                                    page = TextUtils.isEmpty(page) ? "0" : page;
                                    tsInfo.setEmReasonPage(Integer.parseInt(page));
                                }
                                break;
                            case "AEMReasonPage":
                                if (null != tsInfo) {
                                    String page = parser.nextText();
                                    page = TextUtils.isEmpty(page) ? "0" : page;
                                    tsInfo.setAemReasonPage(Integer.parseInt(page));
                                }
                                break;
                            case "TSEMReasonPage":
                                if (null != tsInfo) {
                                    String page = parser.nextText();
                                    page = TextUtils.isEmpty(page) ? "0" : page;
                                    tsInfo.setTsemReasonPage(Integer.parseInt(page));
                                }
                                break;
                            case "EMConditionPage":
                                if (null != tsInfo) {
                                    String page = parser.nextText();
                                    page = TextUtils.isEmpty(page) ? "0" : page;
                                    tsInfo.setEmConditionPage(Integer.parseInt(page));
                                }
                                break;
                            case "AEMConditionPage":
                                if (null != tsInfo) {
                                    String page = parser.nextText();
                                    page = TextUtils.isEmpty(page) ? "0" : page;
                                    tsInfo.setAemConditionPage(Integer.parseInt(page));
                                }
                                break;
                            case "TSEMConditionPage":
                                if (null != tsInfo) {
                                    String page = parser.nextText();
                                    page = TextUtils.isEmpty(page) ? "0" : page;
                                    tsInfo.setTsemConditionPage(Integer.parseInt(page));
                                }
                                break;
                            case "EMSolutionPage":
                                if (null != tsInfo) {
                                    String page = parser.nextText();
                                    page = TextUtils.isEmpty(page) ? "0" : page;
                                    tsInfo.setEmSolutionPage(Integer.parseInt(page));
                                }
                                break;
                            case "AEMSolutionPage":
                                if (null != tsInfo) {
                                    String page = parser.nextText();
                                    page = TextUtils.isEmpty(page) ? "0" : page;
                                    tsInfo.setAemSolutionPage(Integer.parseInt(page));
                                }
                                break;
                            case "TSEMSolutionPage":
                                if (null != tsInfo) {
                                    String page = parser.nextText();
                                    page = TextUtils.isEmpty(page) ? "0" : page;
                                    tsInfo.setTsemSolutionPage(Integer.parseInt(page));
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endName = parser.getName();
                        switch (endName) {
                            case "English":
                                if ("TSLabels".equals(startListTag)) {
                                    tsConfig.getTsLabels().add(tsLabel);
                                    tsLabel = null;
                                } else if ("TSInfos".equals(startListTag)) {
                                    tsConfig.getTsInfos().add(tsInfo);
                                    //当前的tsInfo并没有完成，复制一份用于Chinese
                                    if (null != tsInfo) {
                                        TSInfo thisTsInfo = new TSInfo();
                                        thisTsInfo.setTsShortName(tsInfo.getTsShortName());
                                        thisTsInfo.setTsColor(tsInfo.getTsColor());
                                        thisTsInfo.setTsGroup(tsInfo.getTsGroup());
                                        tsInfo = thisTsInfo;
                                    }
                                }
                                break;
                            case "Chinese":
                                if ("TSLabels".equals(startListTag)) {
                                    tsConfig.getTsLabels().add(tsLabel);
                                    tsLabel = null;
                                } else if ("TSInfos".equals(startListTag)) {
                                    tsConfig.getTsInfos().add(tsInfo);
                                    tsInfo = null;
                                }
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
            return tsConfig;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "TSConfig{" +
                "tsDocs=" + tsDocs +
                ", tsLabels=" + tsLabels +
                ", tsInfos=" + tsInfos +
                '}';
    }

    public List<TSDoc> getTsDocs() {
        return tsDocs;
    }

    public void setTsDocs(List<TSDoc> tsDocs) {
        this.tsDocs = tsDocs;
    }

    public List<TSLabel> getTsLabels() {
        return tsLabels;
    }

    public void setTsLabels(List<TSLabel> tsLabels) {
        this.tsLabels = tsLabels;
    }

    public List<TSInfo> getTsInfos() {
        return tsInfos;
    }

    public void setTsInfos(List<TSInfo> tsInfos) {
        this.tsInfos = tsInfos;
    }
}
