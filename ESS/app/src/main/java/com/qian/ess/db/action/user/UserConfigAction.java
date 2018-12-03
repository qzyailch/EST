package com.qian.ess.db.action.user;

import com.qian.ess.bean.config.user.UserConfig;
import com.qian.ess.bean.config.user.UserLevel;
import com.qian.ess.bean.config.user.UserMember;
import com.qian.ess.db.DBConstants;
import com.qian.ess.db.DBHelper;

import java.util.List;

/**
 * Created by QianMoLi on 2018/11/22.
 * <p>
 * User配置信息操作
 */

public class UserConfigAction {

    //添加或更新TSConfig所有配置信息
    public static void addOrUpdateTSConfig(UserConfig userConfig) {
        clear();
        List<UserLevel> userLevels = userConfig.getUserLevels();
        List<UserMember> userMembers = userConfig.getUserMembers();

        if (null != userLevels && userLevels.size() > 0) {
            for (int i = 0, len = userLevels.size(); i < len; i++) {
                UserLevelAction.addOrUpdateUserLevel(userLevels.get(i));
            }
        }

        if (null != userMembers && userMembers.size() > 0) {
            for (int i = 0, len = userMembers.size(); i < len; i++) {
                UserMemberAction.addOrUpdateUserMember(userMembers.get(i));
            }
        }
    }

    //清除UI配置数据
    public static void clear() {
        DBHelper.getInstance().delete(DBConstants.T_TS_CONFIG_User_Level.T_NAME, null, null);
        DBHelper.getInstance().delete(DBConstants.T_TS_CONFIG_User_Member.T_NAME, null, null);
    }

}
