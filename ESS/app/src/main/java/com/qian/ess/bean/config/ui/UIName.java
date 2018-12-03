package com.qian.ess.bean.config.ui;

/**
 * Created by QianMoLi on 2018/11/19.
 * <p>
 * UI名字配置
 */

public class UIName {

    private String language; //语言
    private String object; //对象
    private String member; //部件
    private String oName; //对象名
    private String mName; //部件名
    private String base; //位置
    private String noO; //对象编号
    private String noM1; //部件1编号
    private String noM2; //部件2编号
    private String ss; //服务
    private String tr; //排故
    private String ed; //文件
    private String ec; //状态
    private String em;
    private String aem;
    private String tsem;
    private String estm;

    @Override
    public String toString() {
        return "UIName{" +
                "language='" + language + '\'' +
                ", object='" + object + '\'' +
                ", member='" + member + '\'' +
                ", oName='" + oName + '\'' +
                ", mName='" + mName + '\'' +
                ", base='" + base + '\'' +
                ", noO='" + noO + '\'' +
                ", noM1='" + noM1 + '\'' +
                ", noM2='" + noM2 + '\'' +
                ", ss='" + ss + '\'' +
                ", tr='" + tr + '\'' +
                ", ed='" + ed + '\'' +
                ", ec='" + ec + '\'' +
                ", em='" + em + '\'' +
                ", aem='" + aem + '\'' +
                ", tsem='" + tsem + '\'' +
                ", estm='" + estm + '\'' +
                '}';
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getOName() {
        return oName;
    }

    public void setOName(String oName) {
        this.oName = oName;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getNoO() {
        return noO;
    }

    public void setNoO(String noO) {
        this.noO = noO;
    }

    public String getNoM1() {
        return noM1;
    }

    public void setNoM1(String noM1) {
        this.noM1 = noM1;
    }

    public String getNoM2() {
        return noM2;
    }

    public void setNoM2(String noM2) {
        this.noM2 = noM2;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public String getTr() {
        return tr;
    }

    public void setTr(String tr) {
        this.tr = tr;
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

    public String getEm() {
        return em;
    }

    public void setEm(String em) {
        this.em = em;
    }

    public String getAem() {
        return aem;
    }

    public void setAem(String aem) {
        this.aem = aem;
    }

    public String getTsem() {
        return tsem;
    }

    public void setTsem(String tsem) {
        this.tsem = tsem;
    }

    public String getEstm() {
        return estm;
    }

    public void setEstm(String estm) {
        this.estm = estm;
    }
}
