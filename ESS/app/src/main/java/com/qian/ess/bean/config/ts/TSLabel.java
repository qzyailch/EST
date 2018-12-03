package com.qian.ess.bean.config.ts;

/**
 * Created by QianMoLi on 2018/11/19.
 * <p>
 * 配置信息类，TSLabel 界面上的标签信息
 */

public class TSLabel {

    private String language;
    private String tsShortName; //故障简称
    private String tsColor; //故障颜色
    private String tsGroup; //群组
    private String troubleshootingWayEM = "EM里的排故方法"; //EM里的排故方法
    private String troubleshootingWayAEM = "AEM里的排故方法"; //AEM里的排故方法
    private String troubleshootingWayTSEM = "TSEM里的排故方法"; //TSEM里的排故方法

    private String descriptionEM = "EM里的描述"; //EM里的描述
    private String descriptionAEM = "AEM里的描述"; //AEM里的描述
    private String descriptionTSEM = ""; //TSEM里的描述

    private String reasonEM = "EM里的原因"; //EM里的原因
    private String reasonAEM = "AEM里的原因"; //AEM里的原因
    private String reasonTSEM = "TSEM里的原因"; //TSEM里的原因

    private String conditionEM = "EM里的条件"; //EM里的条件
    private String conditionAEM = "AEM里的条件"; //AEM里的条件
    private String conditionTSEM = "TSEM里的条件"; //TSEM里的条件

    private String solutionEM = "EM里的解决方案"; //EM里的解决方案
    private String solutionAEM = "AEM里的解决方案"; //AEM里的解决方案
    private String solutionTSEM = "TSEM里的解决方案"; //TSEM里的解决方案

    @Override
    public String toString() {
        return "TSLabel{" +
                "language='" + language + '\'' +
                ", tsShortName='" + tsShortName + '\'' +
                ", tsColor='" + tsColor + '\'' +
                ", tsGroup='" + tsGroup + '\'' +
                ", troubleshootingWayEM='" + troubleshootingWayEM + '\'' +
                ", troubleshootingWayAEM='" + troubleshootingWayAEM + '\'' +
                ", troubleshootingWayTSEM='" + troubleshootingWayTSEM + '\'' +
                ", descriptionEM='" + descriptionEM + '\'' +
                ", descriptionAEM='" + descriptionAEM + '\'' +
                ", descriptionTSEM='" + descriptionTSEM + '\'' +
                ", reasonEM='" + reasonEM + '\'' +
                ", reasonAEM='" + reasonAEM + '\'' +
                ", reasonTSEM='" + reasonTSEM + '\'' +
                ", conditionEM='" + conditionEM + '\'' +
                ", conditionAEM='" + conditionAEM + '\'' +
                ", conditionTSEM='" + conditionTSEM + '\'' +
                ", solutionEM='" + solutionEM + '\'' +
                ", solutionAEM='" + solutionAEM + '\'' +
                ", solutionTSEM='" + solutionTSEM + '\'' +
                '}';
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTsShortName() {
        return tsShortName;
    }

    public void setTsShortName(String tsShortName) {
        this.tsShortName = tsShortName;
    }

    public String getTsColor() {
        return tsColor;
    }

    public void setTsColor(String tsColor) {
        this.tsColor = tsColor;
    }

    public String getTsGroup() {
        return tsGroup;
    }

    public void setTsGroup(String tsGroup) {
        this.tsGroup = tsGroup;
    }

    public String getTroubleshootingWayEM() {
        return troubleshootingWayEM;
    }

    public void setTroubleshootingWayEM(String troubleshootingWayEM) {
        this.troubleshootingWayEM = troubleshootingWayEM;
    }

    public String getTroubleshootingWayAEM() {
        return troubleshootingWayAEM;
    }

    public void setTroubleshootingWayAEM(String troubleshootingWayAEM) {
        this.troubleshootingWayAEM = troubleshootingWayAEM;
    }

    public String getTroubleshootingWayTSEM() {
        return troubleshootingWayTSEM;
    }

    public void setTroubleshootingWayTSEM(String troubleshootingWayTSEM) {
        this.troubleshootingWayTSEM = troubleshootingWayTSEM;
    }

    public String getDescriptionEM() {
        return descriptionEM;
    }

    public void setDescriptionEM(String descriptionEM) {
        this.descriptionEM = descriptionEM;
    }

    public String getDescriptionAEM() {
        return descriptionAEM;
    }

    public void setDescriptionAEM(String descriptionAEM) {
        this.descriptionAEM = descriptionAEM;
    }

    public String getDescriptionTSEM() {
        return descriptionTSEM;
    }

    public void setDescriptionTSEM(String descriptionTSEM) {
        this.descriptionTSEM = descriptionTSEM;
    }

    public String getReasonEM() {
        return reasonEM;
    }

    public void setReasonEM(String reasonEM) {
        this.reasonEM = reasonEM;
    }

    public String getReasonAEM() {
        return reasonAEM;
    }

    public void setReasonAEM(String reasonAEM) {
        this.reasonAEM = reasonAEM;
    }

    public String getReasonTSEM() {
        return reasonTSEM;
    }

    public void setReasonTSEM(String reasonTSEM) {
        this.reasonTSEM = reasonTSEM;
    }

    public String getConditionEM() {
        return conditionEM;
    }

    public void setConditionEM(String conditionEM) {
        this.conditionEM = conditionEM;
    }

    public String getConditionAEM() {
        return conditionAEM;
    }

    public void setConditionAEM(String conditionAEM) {
        this.conditionAEM = conditionAEM;
    }

    public String getConditionTSEM() {
        return conditionTSEM;
    }

    public void setConditionTSEM(String conditionTSEM) {
        this.conditionTSEM = conditionTSEM;
    }

    public String getSolutionEM() {
        return solutionEM;
    }

    public void setSolutionEM(String solutionEM) {
        this.solutionEM = solutionEM;
    }

    public String getSolutionAEM() {
        return solutionAEM;
    }

    public void setSolutionAEM(String solutionAEM) {
        this.solutionAEM = solutionAEM;
    }

    public String getSolutionTSEM() {
        return solutionTSEM;
    }

    public void setSolutionTSEM(String solutionTSEM) {
        this.solutionTSEM = solutionTSEM;
    }
}
