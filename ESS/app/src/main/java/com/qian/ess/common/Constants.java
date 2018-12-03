package com.qian.ess.common;

/**
 * Created by Administrator on 2018/1/25 0025.
 * <p>
 * 常量
 */

public class Constants {

    //activity跳转动画类型
    public enum ActivityAnimType {
        DEFAULT, DIALOG, LOGIN
    }

    /*****以下两个值保存在SP中，每次打包需要用户覆盖安装后再次进入引导页，则需要交换这两个变量的值*****/

    /**
     * 保存是否是第一次使用，是保存在SPUtils中的key值
     * --用于判断是否需要进入引导页
     */
    public static final String ACTION_IS_FIRST_USE = "isFirstUse";

    /**
     * 保存是否是第一次使用，是保存在SPUtils中的key值
     * --用于判断是否需要进入引导页
     */
    public static final String ACTION_IS_FIRST_USE_2 = "isFirstUse2";

    /*********************************分页常量值****************************************/
    public class Page {
        //分页加载每一页显示条数
        public static final int PAGE_ROWS = 20;
    }

    /*********************************app自定义广播常量值*********************************/
    public class ESSBroadcast {
        //接收文件进度
        public static final String RECEIVE_FILE_PROCESS_ACTION = "com.qian.ess.RECEIVE_FILE_PROCESS_ACTION";
        //接收文件完毕
        public static final String DISMISS_DIALOG_ACTION = "com.qian.ess.DISMISS_DIALOG_ACTION";
        //发送消息或者文件的广播
        public static final String SEND_MESSAGE_OR_FILE_ACTION = "com.qian.ess.SEND_MESSAGE_OR_FILE_ACTION";
        //toast
        public static final String TOAST_ACTION = "com.qian.ess.TOAST_ACTION";
    }

    /*********************************语言*********************************/
    public class LANGUAGE {
        //英文
        public static final String ENGLISH = "English";
        //中文
        public static final String CHINESE = "Chinese";
    }

    /*********************************文件操作相关*********************************/
    public class FILE_CONSTANTS {
        /*************************文件分类****************************/
        //文档类型
        public static final String FILE_TYPE_DEFAULT = "default";
        public static final String FILE_TYPE_VIDEO = "video";
        public static final String FILE_TYPE_DOCUMENT = "document";
        public static final String FILE_TYPE_PICTURE = "picture";
        public static final String FILE_TYPE_MUSIC = "music";
        public static final String FILE_TYPE_APK = "apk";
        public static final String FILE_TYPE_ZIP = "zip";

        //发送数据区分文件类型还是字符串类型
        public static final byte SEND_TYPE_STRING = (byte) 0x00; //字符串
        public static final byte SEND_TYPE_FILE = (byte) 0x01; //文件
        public static final byte SEND_TYPE_FILE_CONTENT = (byte) 0x02; //包体
        public static final byte SEND_TYPE_FILE_FEEDBACK = (byte) 0x03; //文件接收完毕，反馈消息
    }
}
