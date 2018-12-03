package com.qian.ess.bean.config.user;

/**
 * Created by QianMoLi on 2018/11/19.
 * <p>
 * UserLevel配置
 */

public class UserLevel {

    private int levelId;
    private String levelNameEng;
    private String levelNameChn;

    @Override
    public String toString() {
        return "UserLevel{" +
                "levelId=" + levelId +
                ", levelNameEng='" + levelNameEng + '\'' +
                ", levelNameChn='" + levelNameChn + '\'' +
                '}';
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getLevelNameEng() {
        return levelNameEng;
    }

    public void setLevelNameEng(String levelNameEng) {
        this.levelNameEng = levelNameEng;
    }

    public String getLevelNameChn() {
        return levelNameChn;
    }

    public void setLevelNameChn(String levelNameChn) {
        this.levelNameChn = levelNameChn;
    }
}
