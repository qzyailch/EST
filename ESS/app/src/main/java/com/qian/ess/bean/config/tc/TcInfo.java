package com.qian.ess.bean.config.tc;

/**
 * Created by QianMoLi on 2018/11/19.
 * <p>
 * 配置信息类，TcInfo
 */

public class TcInfo {

    private int tcId;
    private int tcPageEng;
    private int tcPageChn;

    public int getTcId() {
        return tcId;
    }

    public void setTcId(int tcId) {
        this.tcId = tcId;
    }

    public int getTcPageEng() {
        return tcPageEng;
    }

    public void setTcPageEng(int tcPageEng) {
        this.tcPageEng = tcPageEng;
    }

    public int getTcPageChn() {
        return tcPageChn;
    }

    public void setTcPageChn(int tcPageChn) {
        this.tcPageChn = tcPageChn;
    }
}
