package com.qian.ess.oksocket.impl.abilities;

import com.qian.ess.oksocket.sdk.ConnectionInfo;
import com.qian.ess.oksocket.sdk.connection.IConnectionManager;

/**
 * Created by xuhao on 2017/6/30.
 */

public interface IConnectionSwitchListener {
    void onSwitchConnectionInfo(IConnectionManager manager, ConnectionInfo oldInfo, ConnectionInfo newInfo);
}
