package com.qian.ess.utils;

import android.text.TextUtils;

import com.qian.ess.common.Constants;

/**
 * Created by QianMoLi on 2018/11/22.
 * <p>
 * 语言
 */

public class LanguageUtils {

    //获取当前的语言
    public static String getlanguage() {
        String language = SPUtils.getString("language");
        if (TextUtils.isEmpty(language)) {
            language = Constants.LANGUAGE.CHINESE;
        }
        return language;
    }
}
