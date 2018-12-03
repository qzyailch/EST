package com.qian.ess.bean.config.ts;

/**
 * Created by QianMoLi on 2018/11/19.
 *
 * 配置信息类，TSDoc
 */

public class TSDoc {

    private String label;
    private String text;

    @Override
    public String toString() {
        return "TSDoc{" +
                "label='" + label + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
