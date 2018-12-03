package com.qian.ess.oksocket.impl.abilities;

import com.qian.ess.oksocket.sdk.OkSocketOptions;
import com.qian.ess.oksocket.sdk.bean.ISendable;

/**
 * Created by xuhao on 2017/5/16.
 */

public interface IWriter {
    boolean write() throws RuntimeException;

    void setOption(OkSocketOptions option);

    void offer(ISendable sendable);

    void close();

}
