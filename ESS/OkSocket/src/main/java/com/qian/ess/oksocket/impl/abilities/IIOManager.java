package com.qian.ess.oksocket.impl.abilities;

import com.qian.ess.oksocket.sdk.OkSocketOptions;
import com.qian.ess.oksocket.sdk.bean.ISendable;

/**
 * Created by xuhao on 2017/5/16.
 */

public interface IIOManager {
    void resolve();

    void setOkOptions(OkSocketOptions options);

    void send(ISendable sendable);

    void close();

    void close(Exception e);

}
