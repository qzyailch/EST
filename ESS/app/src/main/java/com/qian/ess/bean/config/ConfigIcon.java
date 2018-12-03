package com.qian.ess.bean.config;

import android.text.TextUtils;
import android.util.Xml;

import com.qian.ess.utils.Logger;
import com.qian.ess.utils.ToastUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by QianMoLi on 2018/11/19.
 * <p>
 * icon名字配置
 */

public class ConfigIcon {

    private String ss = "SS.png";
    private String ts = "TS.png";
    private String ed = "ED.png";
    private String ec = "EC.png";
    private String iconObject = "Object.png";
    private String iconMember = "Member.png";
    private String iconTable = "Table.png";
    private String iconGrafic = "Grafic.png";
    private String pc = "PC.png";
    private String tr = "TR.png";

    //从Xml文件中解析出Bean
    public static ConfigIcon fromXml(String xmlPath) {
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

        ConfigIcon configIcon = new ConfigIcon();
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
                            case "SS":
                                configIcon.setSs(parser.nextText());
                                break;
                            case "TS":
                                configIcon.setTs(parser.nextText());
                                break;
                            case "ED":
                                configIcon.setEd(parser.nextText());
                                break;
                            case "EC":
                                configIcon.setEc(parser.nextText());
                                break;
                            case "Object":
                                configIcon.setIconObject(parser.nextText());
                                break;
                            case "Member":
                                configIcon.setIconMember(parser.nextText());
                                break;
                            case "Table":
                                configIcon.setIconTable(parser.nextText());
                                break;
                            case "Grafic":
                                configIcon.setIconGrafic(parser.nextText());
                                break;
                            case "PC":
                                configIcon.setPc(parser.nextText());
                                break;
                            case "TR":
                                configIcon.setTr(parser.nextText());
                                break;
                        }
                        break;
                    case XmlPullParser.END_TAG:

                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
            is.close();
            return configIcon;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //写成xml文件
    public static void writeToXml(ConfigIcon configIcon, String xmlPath) {
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

            xs.startTag(null, "Icons");

            createXmlTag(xs, "SS", configIcon.getSs());
            createXmlTag(xs, "TS", configIcon.getTs());
            createXmlTag(xs, "ED", configIcon.getEd());
            createXmlTag(xs, "EC", configIcon.getEc());
            createXmlTag(xs, "Object", configIcon.getIconObject());
            createXmlTag(xs, "Member", configIcon.getIconMember());
            createXmlTag(xs, "Table", configIcon.getIconTable());
            createXmlTag(xs, "Grafic", configIcon.getIconGrafic());
            createXmlTag(xs, "PC", configIcon.getPc());
            createXmlTag(xs, "TR", configIcon.getTr());

            xs.endTag(null, "Icons");

            xs.endDocument();
            xs.flush();

            ToastUtils.show("Success");
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.show("Fail");
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

    @Override
    public String toString() {
        return "ConfigIcon{" +
                "ss='" + ss + '\'' +
                ", ts='" + ts + '\'' +
                ", ed='" + ed + '\'' +
                ", ec='" + ec + '\'' +
                ", iconObject='" + iconObject + '\'' +
                ", iconMember='" + iconMember + '\'' +
                ", iconTable='" + iconTable + '\'' +
                ", iconGrafic='" + iconGrafic + '\'' +
                ", pc='" + pc + '\'' +
                ", tr='" + tr + '\'' +
                '}';
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public String getEc() {
        return ec;
    }

    public void setEc(String ec) {
        this.ec = ec;
    }

    public String getIconObject() {
        return iconObject;
    }

    public void setIconObject(String iconObject) {
        this.iconObject = iconObject;
    }

    public String getIconMember() {
        return iconMember;
    }

    public void setIconMember(String iconMember) {
        this.iconMember = iconMember;
    }

    public String getIconTable() {
        return iconTable;
    }

    public void setIconTable(String iconTable) {
        this.iconTable = iconTable;
    }

    public String getIconGrafic() {
        return iconGrafic;
    }

    public void setIconGrafic(String iconGrafic) {
        this.iconGrafic = iconGrafic;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getTr() {
        return tr;
    }

    public void setTr(String tr) {
        this.tr = tr;
    }
}
