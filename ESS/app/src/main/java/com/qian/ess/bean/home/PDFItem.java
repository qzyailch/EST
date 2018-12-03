package com.qian.ess.bean.home;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2018/8/7 0007.
 * <p>
 * 首页-- 文档 -- 列表item
 */

@XStreamAlias("PDFItem")
public class PDFItem {

    private String icon;
    private String title;
    private String path;

    public PDFItem() {

    }

    @Override
    public String toString() {
        return "PDFItem{" +
                "icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
