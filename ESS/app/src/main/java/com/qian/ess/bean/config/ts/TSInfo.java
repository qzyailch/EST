package com.qian.ess.bean.config.ts;

import java.io.Serializable;

/**
 * Created by QianMoLi on 2018/11/19.
 * <p>
 * 配置信息类，TcInfo
 */

public class TSInfo implements Serializable {

    private String language;
    private String tsShortName;
    private String tsColor;
    private String tsGroup;

    private int emDescriptionPage;
    private int aemDescriptionPage;
    private int tsemDescriptionPage;

    private int emReasonPage;
    private int aemReasonPage;
    private int tsemReasonPage;

    private int emConditionPage;
    private int aemConditionPage;
    private int tsemConditionPage;

    private int emSolutionPage;
    private int aemSolutionPage;
    private int tsemSolutionPage;

    @Override
    public String toString() {
        return "TSInfo{" +
                "language='" + language + '\'' +
                ", tsShortName='" + tsShortName + '\'' +
                ", tsColor='" + tsColor + '\'' +
                ", tsGroup='" + tsGroup + '\'' +
                ", emDescriptionPage=" + emDescriptionPage +
                ", aemDescriptionPage=" + aemDescriptionPage +
                ", tsemDescriptionPage=" + tsemDescriptionPage +
                ", emReasonPage=" + emReasonPage +
                ", aemReasonPage=" + aemReasonPage +
                ", tsemReasonPage=" + tsemReasonPage +
                ", emConditionPage=" + emConditionPage +
                ", aemConditionPage=" + aemConditionPage +
                ", tsemConditionPage=" + tsemConditionPage +
                ", emSolutionPage=" + emSolutionPage +
                ", aemSolutionPage=" + aemSolutionPage +
                ", tsemSolutionPage=" + tsemSolutionPage +
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

    public int getEmDescriptionPage() {
        return emDescriptionPage;
    }

    public void setEmDescriptionPage(int emDescriptionPage) {
        this.emDescriptionPage = emDescriptionPage;
    }

    public int getAemDescriptionPage() {
        return aemDescriptionPage;
    }

    public void setAemDescriptionPage(int aemDescriptionPage) {
        this.aemDescriptionPage = aemDescriptionPage;
    }

    public int getTsemDescriptionPage() {
        return tsemDescriptionPage;
    }

    public void setTsemDescriptionPage(int tsemDescriptionPage) {
        this.tsemDescriptionPage = tsemDescriptionPage;
    }

    public int getEmReasonPage() {
        return emReasonPage;
    }

    public void setEmReasonPage(int emReasonPage) {
        this.emReasonPage = emReasonPage;
    }

    public int getAemReasonPage() {
        return aemReasonPage;
    }

    public void setAemReasonPage(int aemReasonPage) {
        this.aemReasonPage = aemReasonPage;
    }

    public int getTsemReasonPage() {
        return tsemReasonPage;
    }

    public void setTsemReasonPage(int tsemReasonPage) {
        this.tsemReasonPage = tsemReasonPage;
    }

    public int getEmConditionPage() {
        return emConditionPage;
    }

    public void setEmConditionPage(int emConditionPage) {
        this.emConditionPage = emConditionPage;
    }

    public int getAemConditionPage() {
        return aemConditionPage;
    }

    public void setAemConditionPage(int aemConditionPage) {
        this.aemConditionPage = aemConditionPage;
    }

    public int getTsemConditionPage() {
        return tsemConditionPage;
    }

    public void setTsemConditionPage(int tsemConditionPage) {
        this.tsemConditionPage = tsemConditionPage;
    }

    public int getEmSolutionPage() {
        return emSolutionPage;
    }

    public void setEmSolutionPage(int emSolutionPage) {
        this.emSolutionPage = emSolutionPage;
    }

    public int getAemSolutionPage() {
        return aemSolutionPage;
    }

    public void setAemSolutionPage(int aemSolutionPage) {
        this.aemSolutionPage = aemSolutionPage;
    }

    public int getTsemSolutionPage() {
        return tsemSolutionPage;
    }

    public void setTsemSolutionPage(int tsemSolutionPage) {
        this.tsemSolutionPage = tsemSolutionPage;
    }
}
