package com.qian.ess.utils;

import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

import com.qian.ess.application.ESSApplication;

public class ToastUtils {

    public static void show(CharSequence text) {
        if (text.length() < 10) {
            Toast toast= Toast.makeText(ESSApplication.getInstance(), text, Toast.LENGTH_SHORT);
            //toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            Toast toast= Toast.makeText(ESSApplication.getInstance(), text, Toast.LENGTH_LONG);
            //toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public static void show(@StringRes int resId) {
        show(ESSApplication.getInstance().getString(resId));
    }

    public static void showCenter(CharSequence text) {
        if (text.length() < 10) {
            Toast toast= Toast.makeText(ESSApplication.getInstance(), text, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            Toast toast= Toast.makeText(ESSApplication.getInstance(), text, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public static void showCenter(@StringRes int resId) {
        showCenter(ESSApplication.getInstance().getString(resId));
    }

}