package com.qian.ess.bean.home;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2018/8/7 0007.
 * <p>
 * 首页--任务--列表item
 */

@XStreamAlias("TaskItem")
public class TaskItem {

    private String icon;
    private String title;
    private String content;
    private String datetime;

    public TaskItem() {

    }

    public TaskItem(String icon, String title, String content, String datetime) {
        this.icon = icon;
        this.title = title;
        this.content = content;
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "TaskItem{" +
                "icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", datetime='" + datetime + '\'' +
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
