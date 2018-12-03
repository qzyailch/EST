package com.qian.ess.bean.tc;

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
import java.util.List;

/**
 * Created by QianMoLi on 2018/11/24.
 * <p>
 * TC
 */

public class PCJobObject {

    private String pcReceiveFileName; //接收到的文件名
    private ProcessCard processCard; //操作卡
    private String aircraftNumber; //对象编号
    private String engineNumber; //部件编号
    private String enginePosition; //部件位置
    private String sentDateTime; //交付时间
    private String beginDateTime; //开始时间
    private String finishDateTime; //完成时间
    private String receivedDateTime; //发送时间
    private String jobReportPath; //报告路径
    private String jobReportDateTime; //报告时间
    private String major; //专业
    private String qcName; //质控
    private String directorName; //主管
    private String baseLocation; //地点
    private String conclusion; //结论

    //从Xml文件中解析出Bean
    public static PCJobObject fromXml(String xmlPath) {
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

        //Job
        PCJobObject pcJobObject = new PCJobObject();

        //操作卡
        ProcessCard processCard = null;
        //技术卡
        TechninalCard techninalCard = null;

        //由于每一个List的子项都是string，所以为了知道解析string标签时，是解析的哪一个List数据，临时记录一个保存当前解析List的标签名
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
                            //操作卡
                            case "ProcessCard":
                                processCard = new ProcessCard();
                                break;
                            //技术卡
                            case "TechninalCard":
                                techninalCard = new TechninalCard();
                                break;
                            /*************Job************/
                            case "AircraftNumber":
                                pcJobObject.setAircraftNumber(parser.nextText());
                                break;
                            case "EngineNumber":
                                pcJobObject.setEngineNumber(parser.nextText());
                                break;
                            case "EnginePosition":
                                pcJobObject.setEnginePosition(parser.nextText());
                                break;
                            case "SentDateTime":
                                pcJobObject.setSentDateTime(parser.nextText());
                                break;
                            case "BeginDateTime":
                                if (null != techninalCard) {
                                    techninalCard.setBeginDateTime(parser.nextText());
                                } else {
                                    pcJobObject.setBeginDateTime(parser.nextText());
                                }
                                break;
                            case "FinishDateTime":
                                if (null != techninalCard) {
                                    techninalCard.setFinishDateTime(parser.nextText());
                                } else {
                                    pcJobObject.setFinishDateTime(parser.nextText());
                                }
                                break;
                            case "ReceivedDateTime":
                                pcJobObject.setReceivedDateTime(parser.nextText());
                                break;
                            case "JobReportPath":
                                pcJobObject.setJobReportPath(parser.nextText());
                                break;
                            case "JobReportDateTime":
                                pcJobObject.setJobReportDateTime(parser.nextText());
                                break;
                            case "Major":
                                pcJobObject.setMajor(parser.nextText());
                                break;
                            case "QcName":
                                pcJobObject.setQcName(parser.nextText());
                                break;
                            case "DirectorName":
                                pcJobObject.setDirectorName(parser.nextText());
                                break;
                            case "BaseLocation":
                                pcJobObject.setBaseLocation(parser.nextText());
                                break;
                            case "Conclusion":
                                pcJobObject.setConclusion(parser.nextText());
                                break;
                            /*******************操作卡********************/
                            case "Number":
                                if (null != techninalCard) {
                                    techninalCard.setNumber(parser.nextText());
                                } else {
                                    if (null != processCard) {
                                        processCard.setNumber(parser.nextText());
                                    }
                                }
                                break;
                            case "Name":
                                if (null != techninalCard) {
                                    techninalCard.setName(parser.nextText());
                                } else {
                                    if (null != processCard) {
                                        processCard.setName(parser.nextText());
                                    }
                                }
                                break;
                            /*********************技术卡********************/
                            case "Hours":
                                if (null != techninalCard) {
                                    techninalCard.setHours(parser.nextText());
                                }
                                break;
                            case "Operations":
                                startListTag = "Operations";
                                break;
                            case "Actions":
                                startListTag = "Actions";
                                break;
                            case "Equipment":
                                startListTag = "Equipment";
                                break;
                            case "Tools":
                                startListTag = "Tools";
                                break;
                            case "Materials":
                                startListTag = "Materials";
                                break;
                            case "PhotoPathList":
                                startListTag = "PhotoPathList";
                                break;
                            case "VideoPathList":
                                startListTag = "VideoPathList";
                                break;
                            case "AudioPathList":
                                startListTag = "AudioPathList";
                                break;
                            case "string":
                                if (null != techninalCard) {
                                    if ("Operations".equals(startListTag)) {
                                        techninalCard.getOperations().add(parser.nextText());
                                    } else if ("Actions".equals(startListTag)) {
                                        techninalCard.getActions().add(parser.nextText());
                                    } else if ("Equipment".equals(startListTag)) {
                                        techninalCard.getEquipment().add(parser.nextText());
                                    } else if ("Tools".equals(startListTag)) {
                                        techninalCard.getTools().add(parser.nextText());
                                    } else if ("Materials".equals(startListTag)) {
                                        techninalCard.getMaterials().add(parser.nextText());
                                    } else if ("PhotoPathList".equals(startListTag)) {
                                        techninalCard.getPhotoPathList().add(parser.nextText());
                                    } else if ("VideoPathList".equals(startListTag)) {
                                        techninalCard.getVideoPathList().add(parser.nextText());
                                    } else if ("AudioPathList".equals(startListTag)) {
                                        techninalCard.getAudioPathList().add(parser.nextText());
                                    }
                                }
                                break;
                            case "WorkerName":
                                if (null != techninalCard) {
                                    techninalCard.setWorkerName(parser.nextText());
                                }
                                break;
                            case "ReviewerName":
                                if (null != techninalCard) {
                                    techninalCard.setReviewerName(parser.nextText());
                                }
                                break;
                            case "Remark":
                                if (null != techninalCard) {
                                    techninalCard.setRemark(parser.nextText());
                                }
                                break;
                            case "Page":
                                if (null != techninalCard) {
                                    techninalCard.setPage(Integer.parseInt(parser.nextText()));
                                }
                                break;
                            case "ResultValue":
                                if (null != techninalCard) {
                                    techninalCard.setResultValue(parser.nextText());
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endName = parser.getName();
                        switch (endName) {
                            //操作卡结束
                            case "ProcessCard":
                                pcJobObject.setProcessCard(processCard);
                                processCard = null;
                                break;
                            //技术卡结束
                            case "TechninalCard":
                                if (null != processCard) {
                                    processCard.getTechninalCards().add(techninalCard);
                                    techninalCard = null;
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
            return pcJobObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //将PCJobObject对象写成xml文件
    public static void writeToXml(PCJobObject pcJobObject, String xmlPath) {
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

            xs.startTag(null, "Job");
            xs.startTag(null, "ProcessCard");

            createXmlTag(xs, "Number", pcJobObject.getProcessCard().getNumber());
            createXmlTag(xs, "Name", pcJobObject.getProcessCard().getName());

            List<TechninalCard> techninalCards = pcJobObject.getProcessCard().getTechninalCards();//技术卡片列表
            if (null != techninalCards && techninalCards.size() > 0) {
                xs.startTag(null, "TechninalCards");
                for (int i = 0, len = techninalCards.size(); i < len; i++) {
                    xs.startTag(null, "TechninalCard");
                    TechninalCard techninalCard = techninalCards.get(i);

                    createXmlTag(xs, "Number", techninalCard.getNumber());
                    createXmlTag(xs, "Name", techninalCard.getName());
                    createXmlTag(xs, "Page", techninalCard.getPage() + "");
                    createXmlTag(xs, "ResultValue", techninalCard.getResultValue());
                    createXmlTag(xs, "WorkerName", techninalCard.getWorkerName());
                    createXmlTag(xs, "ReviewerName", techninalCard.getReviewerName());
                    createXmlTag(xs, "Remark", techninalCard.getRemark());

                    //自定义卡片
                    if (techninalCard.getPage() > 0) {
                        createXmlTag(xs, "Hours", techninalCard.getHours());

                        //自定义的一些属性
                        List<String> thisOperations = techninalCard.getPhotoPathList();
                        List<String> thisActions = techninalCard.getVideoPathList();
                        List<String> thisEquipment = techninalCard.getAudioPathList();
                        List<String> thisTools = techninalCard.getAudioPathList();
                        List<String> thisMaterials = techninalCard.getAudioPathList();

                        xs.startTag(null, "Operations");
                        if (null != thisOperations && thisOperations.size() > 0) {
                            for (int j = 0, length = thisOperations.size(); j < length; j++) {
                                createXmlTag(xs, "string", thisOperations.get(j));
                            }
                        }
                        xs.endTag(null, "Operations");

                        xs.startTag(null, "Actions");
                        if (null != thisActions && thisActions.size() > 0) {
                            for (int j = 0, length = thisActions.size(); j < length; j++) {
                                createXmlTag(xs, "string", thisActions.get(j));
                            }
                        }
                        xs.endTag(null, "Actions");

                        xs.startTag(null, "Equipment");
                        if (null != thisEquipment && thisEquipment.size() > 0) {
                            for (int j = 0, length = thisEquipment.size(); j < length; j++) {
                                createXmlTag(xs, "string", thisEquipment.get(j));
                            }
                        }
                        xs.endTag(null, "Equipment");

                        xs.startTag(null, "Tools");
                        if (null != thisTools && thisTools.size() > 0) {
                            for (int j = 0, length = thisTools.size(); j < length; j++) {
                                createXmlTag(xs, "string", thisTools.get(j));
                            }
                        }
                        xs.endTag(null, "Tools");

                        xs.startTag(null, "Materials");
                        if (null != thisMaterials && thisMaterials.size() > 0) {
                            for (int j = 0, length = thisMaterials.size(); j < length; j++) {
                                createXmlTag(xs, "string", thisMaterials.get(j));
                            }
                        }
                        xs.endTag(null, "Materials");
                    }

                    List<String> thisPhotoPathList = techninalCard.getPhotoPathList();//照片列表
                    List<String> thisVideoPathList = techninalCard.getVideoPathList();//视频列表
                    List<String> thisAudioPathList = techninalCard.getAudioPathList();//音频列表

                    xs.startTag(null, "PhotoPathList");
                    if (null != thisPhotoPathList && thisPhotoPathList.size() > 0) {
                        for (int j = 0, length = thisPhotoPathList.size(); j < length; j++) {
                            createXmlTag(xs, "string", thisPhotoPathList.get(j));
                        }
                    }
                    xs.endTag(null, "PhotoPathList");

                    xs.startTag(null, "VideoPathList");
                    if (null != thisVideoPathList && thisVideoPathList.size() > 0) {
                        for (int j = 0, length = thisVideoPathList.size(); j < length; j++) {
                            createXmlTag(xs, "string", thisVideoPathList.get(j));
                        }
                    }
                    xs.endTag(null, "VideoPathList");

                    xs.startTag(null, "AudioPathList");
                    if (null != thisAudioPathList && thisAudioPathList.size() > 0) {
                        for (int j = 0, length = thisAudioPathList.size(); j < length; j++) {
                            createXmlTag(xs, "string", thisAudioPathList.get(j));
                        }
                    }
                    xs.endTag(null, "AudioPathList");

                    createXmlTag(xs, "BeginDateTime", techninalCard.getBeginDateTime());
                    createXmlTag(xs, "FinishDateTime", techninalCard.getFinishDateTime());

                    xs.endTag(null, "TechninalCard");
                }
                xs.endTag(null, "TechninalCards");
            }

            xs.endTag(null, "ProcessCard");

            createXmlTag(xs, "AircraftNumber", pcJobObject.getAircraftNumber());
            createXmlTag(xs, "EngineNumber", pcJobObject.getEngineNumber());
            createXmlTag(xs, "EnginePosition", pcJobObject.getEnginePosition());
            createXmlTag(xs, "SentDateTime", pcJobObject.getSentDateTime());
            createXmlTag(xs, "BeginDateTime", pcJobObject.getBeginDateTime());
            createXmlTag(xs, "FinishDateTime", pcJobObject.getFinishDateTime());
            createXmlTag(xs, "ReceivedDateTime", pcJobObject.getReceivedDateTime());
            createXmlTag(xs, "JobReportPath", pcJobObject.getJobReportPath());
            createXmlTag(xs, "JobReportDateTime", pcJobObject.getJobReportDateTime());
            createXmlTag(xs, "Major", pcJobObject.getMajor());
            createXmlTag(xs, "QcName", pcJobObject.getQcName());
            createXmlTag(xs, "DirectorName", pcJobObject.getDirectorName());
            createXmlTag(xs, "BaseLocation", pcJobObject.getBaseLocation());
            createXmlTag(xs, "Conclusion", pcJobObject.getConclusion());

            xs.endTag(null, "Job");
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

    @Override
    public String toString() {
        return "PCJobObject{" +
                "pcReceiveFileName='" + pcReceiveFileName + '\'' +
                ", processCard=" + processCard +
                ", aircraftNumber='" + aircraftNumber + '\'' +
                ", engineNumber='" + engineNumber + '\'' +
                ", enginePosition='" + enginePosition + '\'' +
                ", sentDateTime='" + sentDateTime + '\'' +
                ", beginDateTime='" + beginDateTime + '\'' +
                ", finishDateTime='" + finishDateTime + '\'' +
                ", receivedDateTime='" + receivedDateTime + '\'' +
                ", jobReportPath='" + jobReportPath + '\'' +
                ", jobReportDateTime='" + jobReportDateTime + '\'' +
                ", major='" + major + '\'' +
                ", qcName='" + qcName + '\'' +
                ", directorName='" + directorName + '\'' +
                ", baseLocation='" + baseLocation + '\'' +
                ", conclusion='" + conclusion + '\'' +
                '}';
    }

    public String getPcReceiveFileName() {
        return pcReceiveFileName;
    }

    public void setPcReceiveFileName(String pcReceiveFileName) {
        this.pcReceiveFileName = pcReceiveFileName;
    }

    public ProcessCard getProcessCard() {
        return processCard;
    }

    public void setProcessCard(ProcessCard processCard) {
        this.processCard = processCard;
    }

    public String getAircraftNumber() {
        return aircraftNumber;
    }

    public void setAircraftNumber(String aircraftNumber) {
        this.aircraftNumber = aircraftNumber;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getEnginePosition() {
        return enginePosition;
    }

    public void setEnginePosition(String enginePosition) {
        this.enginePosition = enginePosition;
    }

    public String getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(String sentDateTime) {
        this.sentDateTime = sentDateTime;
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

    public String getReceivedDateTime() {
        return receivedDateTime;
    }

    public void setReceivedDateTime(String receivedDateTime) {
        this.receivedDateTime = receivedDateTime;
    }

    public String getJobReportPath() {
        return jobReportPath;
    }

    public void setJobReportPath(String jobReportPath) {
        this.jobReportPath = jobReportPath;
    }

    public String getJobReportDateTime() {
        return jobReportDateTime;
    }

    public void setJobReportDateTime(String jobReportDateTime) {
        this.jobReportDateTime = jobReportDateTime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getQcName() {
        return qcName;
    }

    public void setQcName(String qcName) {
        this.qcName = qcName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getBaseLocation() {
        return baseLocation;
    }

    public void setBaseLocation(String baseLocation) {
        this.baseLocation = baseLocation;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
}
