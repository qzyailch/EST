package com.qian.ess.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class Logger {

    private final static boolean DEBUG = true;

    /**
     * 打印info信息
     * @param tag 标志
     * @param msg 打印信息
     * */
    public static void i(String tag, String msg){
        if(DEBUG){
            Log.i(tag,msg);
        }
    }

    /**
     * 打印warn信息
     * @param tag 标志
     * @param msg 打印信息
     * */
    public static void w(String tag, String msg){
        if(DEBUG){
            Log.w(tag, msg);
        }
    }

    /**
     * 打印error信息
     * @param tag 标志
     * @param msg 打印信息
     * */
    public static void e(String tag, String msg){
        if(DEBUG){
            Log.e(tag, msg);
        }
    }

    /**
     * 打印verbose信息
     * @param tag 标志
     * @param msg 打印信息
     * */
    public static void v(String tag, String msg){
        if(DEBUG){
            Log.v(tag, msg);
        }
    }

    /**
     * 打印debug信息
     * @param tag 标志
     * @param msg 打印信息
     * */
    public static void d(String tag, String msg){
        if(DEBUG){
            Log.d(tag, msg);
        }
    }
}
