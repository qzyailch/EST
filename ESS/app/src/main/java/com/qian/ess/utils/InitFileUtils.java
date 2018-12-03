package com.qian.ess.utils;

import android.content.Context;

import com.qian.ess.bean.config.ConfigIcon;
import com.qian.ess.bean.config.FirstBaseInfo;
import com.qian.ess.bean.config.ts.TSConfig;
import com.qian.ess.bean.config.ui.UINameConfig;
import com.qian.ess.bean.config.user.UserConfig;
import com.qian.ess.bean.tc.PCJobObject;
import com.qian.ess.bean.ts.TSObject;
import com.qian.ess.common.ESSFilePath;
import com.qian.ess.db.action.ConfigIconAction;
import com.qian.ess.db.action.FirstBaseInfoAction;
import com.qian.ess.db.action.PCObjectAction;
import com.qian.ess.db.action.TSObjectAction;
import com.qian.ess.db.action.UINameAction;
import com.qian.ess.db.action.ts.TSConfigAction;
import com.qian.ess.db.action.user.UserConfigAction;

import java.io.File;

/**
 * Created by Administrator on 2018/11/29 0029.
 * <p>
 * 初始化文件夹系统
 */

public class InitFileUtils {

    /**************************初始化文件系统*************************/
    public static void init() {
        initBufferFile();
        initSystemFiles();
        initTransferFile();
        initConfigFile();
        initReceiveFile();
    }

    //初始化Buffer
    public static void initBufferFile() {
        //创建Buffer文件夹
        String buffer = ESSFilePath.BUFFER_FILE;
        File bufferFile = new File(buffer);
        if (!bufferFile.getParentFile().exists()) {
            bufferFile.getParentFile().mkdirs();
        }
        if (!bufferFile.exists()) {
            bufferFile.mkdirs();
        }
    }

    //初始化SystemFiles文件夹
    public static void initSystemFiles() {
        String sys = ESSFilePath.SYSTEM_FILE;
        File sysFile = new File(sys);
        if (!sysFile.getParentFile().exists()) {
            sysFile.getParentFile().mkdirs();
        }
        if (!sysFile.exists()) {
            sysFile.mkdirs();
        }

        //创建sys-PC
        String configPC = ESSFilePath.CONFIG_PC;
        File configPCFile = new File(configPC);
        if (!configPCFile.exists()) {
            configPCFile.mkdirs();
        }
        //创建sys-TS
        String configTS = ESSFilePath.CONFIG_TS;
        File configTSFile = new File(configTS);
        if (!configTSFile.exists()) {
            configTSFile.mkdirs();
        }
        //创建sys-TS
        String configED = ESSFilePath.CONFIG_ED;
        File configEDFile = new File(configED);
        if (!configEDFile.exists()) {
            configEDFile.mkdirs();
        }
        //创建sys-EC
        String configEC = ESSFilePath.CONFIG_EC;
        File configECFile = new File(configEC);
        if (!configECFile.exists()) {
            configECFile.mkdirs();
        }
    }

    //初始化中转站文件夹
    public static void initTransferFile() {
        String transfer = ESSFilePath.TRANSFERSTATION_FILE;
        File transferFile = new File(transfer);
        if (!transferFile.getParentFile().exists()) {
            transferFile.getParentFile().mkdirs();
        }
        if (!transferFile.exists()) {
            transferFile.mkdirs();
        }
    }

    //初始化config文件夹
    public static void initConfigFile() {
        String config = ESSFilePath.CONFIG_FILE;
        File configFile = new File(config);
        if (!configFile.getParentFile().getParentFile().exists()) {
            configFile.getParentFile().getParentFile().mkdirs();
        }
        if (!configFile.getParentFile().exists()) {
            configFile.getParentFile().mkdirs();
        }
        if (!configFile.exists()) {
            configFile.mkdirs();
        }

        //创建icon文件夹
        String icon = ESSFilePath.ICON_FILE;
        File iconFile = new File(icon);
        if (!iconFile.exists()) {
            iconFile.mkdirs();
        }
    }

    //创建文件接收地址
    public static void initReceiveFile() {
        String receive = ESSFilePath.RECEIVE_FILE;
        File receiveFile = new File(receive);
        if (!receiveFile.getParentFile().exists()) {
            receiveFile.getParentFile().mkdirs();
        }
        if (!receiveFile.exists()) {
            receiveFile.mkdirs();
        }
    }

    //初始化Object的业务文件，根据第一次填写的信息创建
    public static void initObjectFile() {
        FirstBaseInfo firstBaseInfo = FirstBaseInfoAction.getFirstBaseInfo();
        if (null != firstBaseInfo) {
            //对象编号文件夹
            String objectNoOPath = ESSFilePath.ObjectFile.getObjectNoO(firstBaseInfo.getObjectNum());
            File objectNoOFile = new File(objectNoOPath);
            if (!objectNoOFile.exists()) {
                objectNoOFile.mkdirs();
            }

            //部件编号1文件夹
            String memberNo1Path = ESSFilePath.ObjectFile.getMemberNoM(firstBaseInfo.getObjectNum(), firstBaseInfo.getComponentNum1());
            File memberNo1File = new File(memberNo1Path);
            if (!memberNo1File.exists()) {
                memberNo1File.mkdirs();
            }

            //部件编号2文件夹
            String memberNo2Path = ESSFilePath.ObjectFile.getMemberNoM(firstBaseInfo.getObjectNum(), firstBaseInfo.getComponentNum2());
            File memberNo2File = new File(memberNo2Path);
            if (!memberNo2File.exists()) {
                memberNo2File.mkdirs();
            }

            //创建部件编号1下面的所有文件夹
            String memberNo1PC = ESSFilePath.ObjectFile.getMemberNoMForPC(firstBaseInfo.getObjectNum(), firstBaseInfo.getComponentNum1());
            String memberNo1TS = ESSFilePath.ObjectFile.getMemberNoMForTS(firstBaseInfo.getObjectNum(), firstBaseInfo.getComponentNum1());
            String memberNo1ED = ESSFilePath.ObjectFile.getMemberNoMForED(firstBaseInfo.getObjectNum(), firstBaseInfo.getComponentNum1());
            String memberNo1EC = ESSFilePath.ObjectFile.getMemberNoMForEC(firstBaseInfo.getObjectNum(), firstBaseInfo.getComponentNum1());

            File memberNo1PCFile = new File(memberNo1PC);
            File memberNo1TSFile = new File(memberNo1TS);
            File memberNo1EDFile = new File(memberNo1ED);
            File memberNo1ECFile = new File(memberNo1EC);
            if (!memberNo1PCFile.exists()) {
                memberNo1PCFile.mkdirs();
            }
            if (!memberNo1TSFile.exists()) {
                memberNo1TSFile.mkdirs();
            }
            if (!memberNo1EDFile.exists()) {
                memberNo1EDFile.mkdirs();
            }
            if (!memberNo1ECFile.exists()) {
                memberNo1ECFile.mkdirs();
            }

            //创建部件编号2下面的所有文件夹
            String memberNo2PC = ESSFilePath.ObjectFile.getMemberNoMForPC(firstBaseInfo.getObjectNum(), firstBaseInfo.getComponentNum2());
            String memberNo2TS = ESSFilePath.ObjectFile.getMemberNoMForTS(firstBaseInfo.getObjectNum(), firstBaseInfo.getComponentNum2());
            String memberNo2ED = ESSFilePath.ObjectFile.getMemberNoMForED(firstBaseInfo.getObjectNum(), firstBaseInfo.getComponentNum2());
            String memberNo2EC = ESSFilePath.ObjectFile.getMemberNoMForEC(firstBaseInfo.getObjectNum(), firstBaseInfo.getComponentNum2());

            File memberNo2PCFile = new File(memberNo2PC);
            File memberNo2TSFile = new File(memberNo2TS);
            File memberNo2EDFile = new File(memberNo2ED);
            File memberNo2ECFile = new File(memberNo2EC);
            if (!memberNo2PCFile.exists()) {
                memberNo2PCFile.mkdirs();
            }
            if (!memberNo2TSFile.exists()) {
                memberNo2TSFile.mkdirs();
            }
            if (!memberNo2EDFile.exists()) {
                memberNo2EDFile.mkdirs();
            }
            if (!memberNo2ECFile.exists()) {
                memberNo2ECFile.mkdirs();
            }
        }
    }

    /******************************初始化数据系统******************************/
    //初始化config文件
    public static void initConfigData(Context context) throws Exception {
        ConfigIcon configIcon = ConfigIconAction.getConfigIcon();
        if (null == configIcon) {
            String uiConfigPath = ESSFilePath.CONFIG_FILE + "/Config-UI-Names.config";
            if (!new File(uiConfigPath).exists()) {
                ZipUtils.UnZipFolder(context, "ConfigForAPP.zip", ESSFilePath.CONFIG_FILE);
                ZipUtils.UnZipFolder(context, "icon.zip", ESSFilePath.ICON_FILE);
                Logger.i("result", "config文件初始化解压成功");
            }
            //添加TSConfig
            TSConfig tsConfig = TSConfig.fromXml(ESSFilePath.CONFIG_FILE + "/Config-TS-Links.config");
            Logger.i("result", "===============" + tsConfig.toString());
            TSConfigAction.addOrUpdateTSConfig(tsConfig);

            //添加UIName
            UINameConfig uiNameConfig = UINameConfig.fromXml(ESSFilePath.CONFIG_FILE + "/Config-UI-Names.config");
            Logger.i("result", "===============" + uiNameConfig.toString());
            UINameAction.addOrUpdateUINameConfig(uiNameConfig);

            //添加User
            UserConfig userConfig = UserConfig.fromXml(ESSFilePath.CONFIG_FILE + "/Config-User-Names.config");
            Logger.i("result", "===============" + userConfig.toString());
            UserConfigAction.addOrUpdateTSConfig(userConfig);

            //添加icon
            ConfigIcon configIconInit = ConfigIcon.fromXml(ESSFilePath.CONFIG_FILE + "/Config-User-Names.config");
            Logger.i("result", "===============" + configIconInit.toString());
            ConfigIconAction.addOrUpdateConfigIcon(configIconInit);
        }
    }

    //初始化第一次填写信息的config
    public static void initFirstConfig() {
        FirstBaseInfo firstBaseInfo = FirstBaseInfoAction.getFirstBaseInfo();
        if (null == firstBaseInfo) {
            String firstBaseInfoConfigPath = ESSFilePath.CONFIG_FILE + "/Config-Default-Settings.config";
            if (new File(firstBaseInfoConfigPath).exists()) {
                FirstBaseInfoAction.addOrUpdateFirstBaseInfo(FirstBaseInfo.fromXml(firstBaseInfoConfigPath));
            }
        }
    }

    //初始化PC相关历史信息
    public static void initPCHistoryData() {
        if (PCObjectAction.getAllPCJobObject().size() == 0) {
            FirstBaseInfo firstBaseInfo = FirstBaseInfoAction.getFirstBaseInfo();
            if (null != firstBaseInfo) {
                //部件编号1文件夹
                String pcMember1FilePath = ESSFilePath.ObjectFile.getMemberNoMForPC(firstBaseInfo.getObjectNum(), firstBaseInfo.getComponentNum1());
                initPCJobObject(pcMember1FilePath);
                //部件编号2文件夹
                String pcMember2FilePath = ESSFilePath.ObjectFile.getMemberNoMForPC(firstBaseInfo.getObjectNum(), firstBaseInfo.getComponentNum2());
                initPCJobObject(pcMember2FilePath);
            }
        }
    }

    //初始化PCJobObject
    private static void initPCJobObject(String pcFilePath) {
        File tsFile = new File(pcFilePath);
        //文件夹
        String file1List[] = tsFile.list();
        //子文件
        if (file1List.length > 0) {
            //子文件循环
            for (int i = 0; i < file1List.length; i++) {
                if (file1List[i].startsWith("JPC")) {
                    PCObjectAction.addOrUpdatePCJobObject(PCJobObject.fromXml(pcFilePath + "/" + file1List[i] + "/" + file1List[i] + ".xml"), file1List[i]);
                }
            }
        }
    }

    //初始化TS相关历史数据
    public static void initTSHistoryData() {
        if (TSObjectAction.getAllTSObject().size() == 0) {
            FirstBaseInfo firstBaseInfo = FirstBaseInfoAction.getFirstBaseInfo();
            if (null != firstBaseInfo) {
                //部件编号1文件夹
                String tsMember1FilePath = ESSFilePath.ObjectFile.getMemberNoMForTS(firstBaseInfo.getObjectNum(), firstBaseInfo.getComponentNum1());
                initTSObject(tsMember1FilePath);
                //部件编号2文件夹
                String tsMember2FilePath = ESSFilePath.ObjectFile.getMemberNoMForTS(firstBaseInfo.getObjectNum(), firstBaseInfo.getComponentNum2());
                initTSObject(tsMember2FilePath);
            }
        }
    }

    //初始化TSObject
    private static void initTSObject(String tsFilePath) {
        File tsFile = new File(tsFilePath);
        //文件夹
        String file1List[] = tsFile.list();
        //子文件
        if (file1List.length > 0) {
            //子文件循环
            for (int i = 0; i < file1List.length; i++) {
                if (file1List[i].startsWith("JTR")) {
                    TSObjectAction.addOrUpdateTSObject(TSObject.fromXml(tsFilePath + "/" + file1List[i] + "/" + file1List[i] + ".xml"), file1List[i]);
                }
            }
        }
    }

    /***************************获取TS所在路径***************************/
    public static String getTSFilePath(String NoO, String memberNo) {
        return ESSFilePath.ObjectFile.getMemberNoMForTS(NoO, memberNo);
    }
}
