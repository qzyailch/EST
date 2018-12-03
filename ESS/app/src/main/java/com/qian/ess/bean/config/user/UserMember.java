package com.qian.ess.bean.config.user;

/**
 * Created by QianMoLi on 2018/11/19.
 * <p>
 * UserMember 配置信息
 */

public class UserMember {

    private int userLevelId;
    private int userId;
    private String userName;
    private String userPassword;

    @Override
    public String toString() {
        return "UserMember{" +
                "userLevelId=" + userLevelId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    public int getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(int userLevelId) {
        this.userLevelId = userLevelId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
