package com.qian.ess.bean.es;

/**
 * Created by QianMoLi on 2018/11/3.
 * <p>
 * 任务历史纪录
 */

public class TaskHistory {

    private String details;

    private boolean isOpen;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean open) {
        isOpen = open;
    }
}
