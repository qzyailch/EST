package com.qian.ess.bean.config;

import android.text.TextUtils;
import android.util.Xml;

import com.qian.ess.utils.Logger;
import com.qian.ess.utils.ToastUtils;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/11/13 0013.
 * <p>
 * 第一次填写的基本信息
 */

public class FirstBaseInfo {

    //位置
    private String address;
    //对象名
    private String objectName;
    //对象编号
    private String objectNum;
    //部件名
    private String componentName;
    //部件1编号
    private String componentNum1;
    //部件2编号
    private String componentNum2;

    //从Xml文件中解析出Bean
    public static FirstBaseInfo fromXml(String xmlPath) {
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

        FirstBaseInfo firstBaseInfo = new FirstBaseInfo();
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
                            case "Base":
                                firstBaseInfo.setAddress(parser.nextText());
                                break;
                            case "O_Name":
                                firstBaseInfo.setObjectName(parser.nextText());
                                break;
                            case "NoO":
                                firstBaseInfo.setObjectNum(parser.nextText());
                                break;
                            case "M_Name":
                                firstBaseInfo.setComponentName(parser.nextText());
                                break;
                            case "NoM1":
                                firstBaseInfo.setComponentNum1(parser.nextText());
                                break;
                            case "NoM2":
                                firstBaseInfo.setComponentNum2(parser.nextText());
                                break;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endName = parser.getName();
                        switch (endName) {

                            default:
                                break;
                        }
                        break;
                }
                eventType = parser.next();
            }
            is.close();
            return firstBaseInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //写成xml文件
    public static void writeToXml(FirstBaseInfo firstBaseInfo, String xmlPath) {
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

            xs.startTag(null, "DefaultSettings");
            xs.startTag(null, "DefaultSetting");

            createXmlTag(xs, "Base", firstBaseInfo.getAddress());
            createXmlTag(xs, "O_Name", firstBaseInfo.getObjectName());
            createXmlTag(xs, "NoO", firstBaseInfo.getObjectNum());
            createXmlTag(xs, "M_Name", firstBaseInfo.getComponentName());
            createXmlTag(xs, "NoM1", firstBaseInfo.getComponentNum1());
            createXmlTag(xs, "NoM2", firstBaseInfo.getComponentNum2());

            xs.endTag(null, "DefaultSetting");
            xs.endTag(null, "DefaultSettings");

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
    public static FirstBaseInfo fromJson(JSONObject json) {
        FirstBaseInfo firstBaseInfo = new FirstBaseInfo();
        firstBaseInfo.setAddress(json.optString("Base"));
        firstBaseInfo.setObjectName(json.optString("O_Name"));
        firstBaseInfo.setObjectNum(json.optString("NoO"));
        firstBaseInfo.setComponentName(json.optString("M_Name"));
        firstBaseInfo.setComponentNum1(json.optString("NoM1"));
        firstBaseInfo.setComponentNum2(json.optString("NoM2"));

        return firstBaseInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectNum() {
        return objectNum;
    }

    public void setObjectNum(String objectNum) {
        this.objectNum = objectNum;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentNum1() {
        return componentNum1;
    }

    public void setComponentNum1(String componentNum1) {
        this.componentNum1 = componentNum1;
    }

    public String getComponentNum2() {
        return componentNum2;
    }

    public void setComponentNum2(String componentNum2) {
        this.componentNum2 = componentNum2;
    }
}
