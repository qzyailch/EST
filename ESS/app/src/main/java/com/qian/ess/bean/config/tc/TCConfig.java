package com.qian.ess.bean.config.tc;

import android.util.Xml;

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
 * TCConfig，从配置文件中获取到的对象
 */

public class TCConfig {

    private TCDoc tcDoc = new TCDoc();
    private List<TcInfo> tcInfos = new ArrayList<>();

    //从Xml文件中解析出Bean
    public static TCConfig fromXml(String xmlPath) {
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

        TCConfig tcConfig = new TCConfig();
        TcInfo tcInfo = null;
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
                            case "TCDoc":
                                tcConfig.getTcDoc().setTcDoc(parser.nextText());
                                break;
                            /**************TCInfo***************/
                            case "TCInfo":
                                tcInfo = new TcInfo();
                                break;
                            case "TCID":
                                if (null != tcInfo) {
                                    tcInfo.setTcId(Integer.parseInt(parser.nextText()));
                                }
                                break;
                            case "TCPageEng":
                                if (null != tcInfo) {
                                    tcInfo.setTcPageEng(Integer.parseInt(parser.nextText()));
                                }
                                break;
                            case "TCPageChn":
                                if (null != tcInfo) {
                                    tcInfo.setTcPageChn(Integer.parseInt(parser.nextText()));
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endName = parser.getName();
                        switch (endName) {
                            case "TCInfo":
                                tcConfig.getTcInfos().add(tcInfo);
                                tcInfo = null;
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
            return tcConfig;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TCDoc getTcDoc() {
        return tcDoc;
    }

    public void setTcDoc(TCDoc tcDoc) {
        this.tcDoc = tcDoc;
    }

    public List<TcInfo> getTcInfos() {
        return tcInfos;
    }

    public void setTcInfos(List<TcInfo> tcInfos) {
        this.tcInfos = tcInfos;
    }
}
