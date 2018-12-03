package com.qian.ess.base;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/10 0010.
 * <p>
 * 对请求结果返回对象的封装，包含{@link #returnCode}， {@link #message}
 * 和{@link #result}
 */

public class HttpResult<T> implements Serializable {
    private int returnCode;
    private Page page;
    private String state;
    private String message;
    private T result;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "returnCode=" + returnCode +
                ", page=" + page +
                ", state='" + state + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    //分页信息
    public static class Page {

        /**
         * pageNo : 1
         * pageSize : 20
         * recordNumber : 3
         * records : null
         * total : 3
         */

        private int pageNo;
        private int pageSize;
        private int recordNumber;
        private int total;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getRecordNumber() {
            return recordNumber;
        }

        public void setRecordNumber(int recordNumber) {
            this.recordNumber = recordNumber;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}
