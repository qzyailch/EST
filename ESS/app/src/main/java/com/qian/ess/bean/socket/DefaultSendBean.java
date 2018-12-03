package com.qian.ess.bean.socket;

/**
 * Created by didi on 2018/6/4.
 */

public class DefaultSendBean {

    /**
     * byte[] 分5个部分
     * <p>
     * [0]是类型，区别是字串还是文件
     * [1]-[4] 是文件名长度
     * [5]-[9] 是文件长度
     * [9]-[n] 是文件名
     * 从[1024]开始是文件
     */
    protected byte[] bytes;

    public DefaultSendBean(byte[] bytes) {
        this.bytes = bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
