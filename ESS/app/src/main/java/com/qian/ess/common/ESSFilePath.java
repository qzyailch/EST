package com.qian.ess.common;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2017/3/23 0023.
 * <p>
 * 文件系统
 */

public class ESSFilePath {

    //系统根目录
    public static String dirName = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

    public static final String ROOT_FILE_DRECTORY = dirName + "EST";

    /**
     * 配置文件保存地址，包含EC,ED,ES,TR
     */
    public static final String SYSTEM_FILE = ROOT_FILE_DRECTORY + "/SystemFiles";

    /**
     * Buffer文件地址，用于临时存储的文件夹
     */
    public static final String BUFFER_FILE = ROOT_FILE_DRECTORY + "/Buffer";

    /**
     * 中转站，用于电脑上通过USB传进来的文件，我读取出来后，用于下拉刷新
     */
    public static final String TRANSFERSTATION_FILE = ROOT_FILE_DRECTORY + "/TransferStation";


    /****************************配置文件*******************************/

    /**
     * 配置文件保存地址，里面包含config文件和icon文件夹
     */
    public static final String CONFIG_FILE = SYSTEM_FILE + "/Configuration/ConfigForAPP";

    /**
     * icon文件保存地址
     */
    public static final String ICON_FILE = CONFIG_FILE + "/icon";

    /**
     * 配置文件：PC
     */
    public static final String CONFIG_PC = SYSTEM_FILE + "/ES";

    /**
     * 配置文件：TS
     */
    public static final String CONFIG_TS = SYSTEM_FILE + "/TR";

    /**
     * 配置文件：ED
     */
    public static final String CONFIG_ED = SYSTEM_FILE + "/ED";

    /**
     * 配置文件：EC
     */
    public static final String CONFIG_EC = SYSTEM_FILE + "/EC";

    /****************************临时文件*******************************/

    /**
     * 文件接收地址
     */
    public static final String RECEIVE_FILE = BUFFER_FILE + "/receive_file";

    /**
     * 业务文件夹系统
     * <p>
     * JTR-BXS_11-20181102-1600-L-NoM1-NoO
     * JTR-BXS_11-20181102-1600-R-NoM2-NoO
     * JTR-故障简称-日期-时间-位置-部件编号-对象编号
     */
    public static class ObjectFile {

        /**
         * 对象编号地址，所有业务文件夹的入口
         *
         * @param NoO 对象编号
         */
        public static String getObjectNoO(String NoO) {
            return ESSFilePath.ROOT_FILE_DRECTORY + "/Object-" + NoO;
        }

        /**
         * 部件编号地址
         *
         * @param NoM 部件编号
         */
        public static String getMemberNoM(String NoO, String NoM) {
            return getObjectNoO(NoO) + "/Member-" + NoM;
        }

        /**
         * 部件编号地址下面的业务文件夹地址：PC文件
         *
         * @param NoM 部件编号
         */
        public static String getMemberNoMForPC(String NoO, String NoM) {
            return getMemberNoM(NoO, NoM) + "/ES";
        }

        /**
         * 部件编号地址下面的业务文件夹地址：TS文件
         *
         * @param NoM 部件编号
         */
        public static String getMemberNoMForTS(String NoO, String NoM) {
            return getMemberNoM(NoO, NoM) + "/TR";
        }

        /**
         * 部件编号地址下面的业务文件夹地址：ED文件
         *
         * @param NoM 部件编号
         */
        public static String getMemberNoMForED(String NoO, String NoM) {
            return getMemberNoM(NoO, NoM) + "/ED";
        }

        /**
         * 部件编号地址下面的业务文件夹地址：EC文件
         *
         * @param NoM 部件编号
         */
        public static String getMemberNoMForEC(String NoO, String NoM) {
            return getMemberNoM(NoO, NoM) + "/EC";
        }
    }
}
