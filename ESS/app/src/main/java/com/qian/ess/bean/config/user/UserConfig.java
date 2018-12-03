package com.qian.ess.bean.config.user;

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
 * UserConfig，从配置文件中获取到的对象
 */

public class UserConfig {

    private List<UserLevel> userLevels = new ArrayList<>();
    private List<UserMember> userMembers = new ArrayList<>();

    //从Xml文件中解析出Bean
    public static UserConfig fromXml(String xmlPath) {
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

        UserConfig userLevelConfig = new UserConfig();
        //UserLevelAction
        UserLevel userLevel = null;
        //UserMember
        UserMember userMember = null;
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
                            case "UserLevel":
                                userLevel = new UserLevel();
                                break;
                            case "UserMember":
                                userMember = new UserMember();
                                break;
                            /**************UserLevelAction***************/
                            case "LevelID":
                                if (null != userLevel) {
                                    userLevel.setLevelId(Integer.parseInt(parser.nextText()));
                                }
                                break;
                            case "LevelNameENG":
                                if (null != userLevel) {
                                    userLevel.setLevelNameEng(parser.nextText());
                                }
                                break;
                            case "LevelNameCHN":
                                if (null != userLevel) {
                                    userLevel.setLevelNameChn(parser.nextText());
                                }
                                break;
                            /**************UserLevelAction***************/
                            case "UserID":
                                if (null != userMember) {
                                    userMember.setUserId(Integer.parseInt(parser.nextText()));
                                }
                                break;
                            case "UserName":
                                if (null != userMember) {
                                    userMember.setUserName(parser.nextText());
                                }
                                break;
                            case "UserPassword":
                                if (null != userMember) {
                                    userMember.setUserPassword(parser.nextText());
                                }
                                break;
                            case "UserLevelID":
                                if (null != userMember) {
                                    userMember.setUserLevelId(Integer.parseInt(parser.nextText()));
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endName = parser.getName();
                        switch (endName) {
                            case "UserLevel":
                                userLevelConfig.getUserLevels().add(userLevel);
                                userLevel = null;
                                break;
                            case "UserMember":
                                userLevelConfig.getUserMembers().add(userMember);
                                userMember = null;
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
            return userLevelConfig;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "UserConfig{" +
                "userLevels=" + userLevels +
                ", userMembers=" + userMembers +
                '}';
    }

    public List<UserLevel> getUserLevels() {
        return userLevels;
    }

    public void setUserLevels(List<UserLevel> userLevels) {
        this.userLevels = userLevels;
    }

    public List<UserMember> getUserMembers() {
        return userMembers;
    }

    public void setUserMembers(List<UserMember> userMembers) {
        this.userMembers = userMembers;
    }
}
