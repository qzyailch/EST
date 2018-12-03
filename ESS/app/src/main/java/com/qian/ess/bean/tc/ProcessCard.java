package com.qian.ess.bean.tc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QianMoLi on 2018/11/24.
 * <p>
 * ProcessCard 操作卡
 */

public class ProcessCard {

    private String pcReceiveFileName; //接收到的文件名
    private String number; //编号
    private String name; //名字
    private List<TechninalCard> techninalCards = new ArrayList<>();

    @Override
    public String toString() {
        return "ProcessCard{" +
                "pcReceiveFileName='" + pcReceiveFileName + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", techninalCards=" + techninalCards +
                '}';
    }

    public String getPcReceiveFileName() {
        return pcReceiveFileName;
    }

    public void setPcReceiveFileName(String pcReceiveFileName) {
        this.pcReceiveFileName = pcReceiveFileName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TechninalCard> getTechninalCards() {
        return techninalCards;
    }

    public void setTechninalCards(List<TechninalCard> techninalCards) {
        this.techninalCards = techninalCards;
    }
}
