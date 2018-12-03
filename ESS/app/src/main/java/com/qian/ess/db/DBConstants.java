package com.qian.ess.db;

/**
 * Created by Administrator on 2018/11/2 0002.
 * <p>
 * 数据库配置信息
 */

public class DBConstants {

    //TS
    public class T_TSOBJECT {
        //表名
        public static final String T_NAME = "T_TSObject";

        //字段名
        public static final String COLUMN_IS_ADD_CUSTOM_FUN = "isAddCustomFun"; //是否已经添加排故方法
        public static final String COLUMN_TS_RECEIVE_FILE_NAME = "tsReceiveFileName"; //所接受的文件名
        public static final String COLUMN_TS_LANGUAGE = "tsLanguage";
        public static final String COLUMN_TS_ID_LABLE = "tsIDLable";
        public static final String COLUMN_WORK_LOCATION_LABLE = "workLocationLable";
        public static final String COLUMN_TS_NUMBER_LABLE = "tsNumberLable";
        public static final String COLUMN_TS_SHORT_NAME_LABLE = "tsShortNameLable";
        public static final String COLUMN_TS_FULL_NAME_LABLE = "tsFullNameLable";
        public static final String COLUMN_TS_POSITION_LABLE = "tsPositionLable";
        public static final String COLUMN_OBJECT_MODEL_LABLE = "objectModelLable";
        public static final String COLUMN_OBJECT_NUMBER_LABLE = "objectNumberLable";
        public static final String COLUMN_PART_NUMBER_LABLE = "partNumberLable";
        public static final String COLUMN_PART_POSITION_LABLE = "partPositionLable";
        public static final String COLUMN_TS_PERSON_IN_CHARGE_LABLE = "tsPersonInChargeLable";
        public static final String COLUMN_TS_WORKER_NAME_LABLE = "tsWorkerNameLable";
        public static final String COLUMN_TS_AUDITOR_LABLE = "tsAuditorLable";
        public static final String COLUMN_TS_DELIVER_DATETIME_LABLE = "tsDeliverDateTimeLable";
        public static final String COLUMN_TS_BEGIN_DATETIME_LABLE = "tsBeginDateTimeLable";
        public static final String COLUMN_TS_FINISH_DATETIME_LABLE = "tsFinishDateTimeLable";
        public static final String COLUMN_TS_SOLUTION_NUMBER_LABLE = "tsSolutionNumberLable";
        public static final String COLUMN_TS_SOLUTIONS_LABLE = "tsSolutionsLable";
        public static final String COLUMN_TS_POSSIBLE_REASON_LABLE = "tsPossibleReasonLable";
        public static final String COLUMN_TS_IS_USED_LABLE = "tsIsUsedLable";
        public static final String COLUMN_TS_IS_WORK_LABLE = "tsIsWorkLable";
        public static final String COLUMN_REAL_REASON_LABLE = "realReasonLable";
        public static final String COLUMN_REMARK_LABLE = "remarkLable";
        public static final String COLUMN_PICTURE_LABLE = "pictureLable";
        public static final String COLUMN_VIDEO_LABLE = "videoLable";
        public static final String COLUMN_AUDIO_LABLE = "audioLable";
        public static final String COLUMN_PICTURE_LIST_LABLE = "pictureListLable";
        public static final String COLUMN_VIDEO_LIST_LABLE = "videoListLable";
        public static final String COLUMN_AUDIO_LIST_LABLE = "audioListLable";
        public static final String COLUMN_RESERVETSPARAMETER1LABLE = "reserveTsParameter1Lable";
        public static final String COLUMN_RESERVETSPARAMETER2LABLE = "reserveTsParameter2Lable";
        public static final String COLUMN_RESERVETSPARAMETER3LABLE = "reserveTsParameter3Lable";
        public static final String COLUMN_RESERVETSPARAMETER4LABLE = "reserveTsParameter4Lable";
        public static final String COLUMN_RESERVETSPARAMETER5LABLE = "reserveTsParameter5Lable";
        public static final String COLUMN_RESERVETSPARAMETER6LABLE = "reserveTsParameter6Lable";
        public static final String COLUMN_RESERVETSPARAMETER7LABLE = "reserveTsParameter7Lable";
        public static final String COLUMN_RESERVETSPARAMETER8LABLE = "reserveTsParameter8Lable";

        public static final String COLUMN_TS_ID = "tsID";
        public static final String COLUMN_WORK_LOCATION = "workLocation";
        public static final String COLUMN_TS_NUMBER = "tsNumber";
        public static final String COLUMN_TS_SHORT_NAME = "tsShortName";
        public static final String COLUMN_TS_FULL_NAME = "tsFullName";
        public static final String COLUMN_TS_POSITION = "tsPosition";
        public static final String COLUMN_OBJECT_MODEL = "objectModel";
        public static final String COLUMN_OBJECT_NUMBER = "objectNumber";
        public static final String COLUMN_PART_NUMBER = "partNumber";
        public static final String COLUMN_PART_POSITION = "partPosition";
        public static final String COLUMN_TS_PERSON_IN_CHARGE = "tsPersonInCharge";
        public static final String COLUMN_TS_WORKER_NAME = "tsWorkerName";
        public static final String COLUMN_TS_AUDITOR = "tsAuditor";
        public static final String COLUMN_TS_DELIVER_DATETIME = "tsDeliverDateTime";
        public static final String COLUMN_TS_BEGIN_DATETIME = "tsBeginDateTime";
        public static final String COLUMN_TS_FINISH_DATETIME = "tsFinishDateTime";
        public static final String COLUMN_REAL_REASON = "realReason";
        public static final String COLUMN_REMARK = "remark";
        public static final String COLUMN_PICTURE_LIST = "pictureList";
        public static final String COLUMN_VIDEO_LIST = "videoList";
        public static final String COLUMN_AUDIO_LIST = "audioList";
        public static final String COLUMN_RESERVETSPARAMETER1 = "reserveTsParameter1";
        public static final String COLUMN_RESERVETSPARAMETER2 = "reserveTsParameter2";
        public static final String COLUMN_RESERVETSPARAMETER3 = "reserveTsParameter3";
        public static final String COLUMN_RESERVETSPARAMETER4 = "reserveTsParameter4";
        public static final String COLUMN_RESERVETSPARAMETER5 = "reserveTsParameter5";
        public static final String COLUMN_RESERVETSPARAMETER6 = "reserveTsParameter6";
        public static final String COLUMN_RESERVETSPARAMETER7 = "reserveTsParameter7";
        public static final String COLUMN_RESERVETSPARAMETER8 = "reserveTsParameter8";

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";

        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_IS_ADD_CUSTOM_FUN + "," + COLUMN_TS_RECEIVE_FILE_NAME + "," + COLUMN_TS_ID_LABLE
                + "," + COLUMN_WORK_LOCATION_LABLE + "," + COLUMN_TS_NUMBER_LABLE + "," + COLUMN_TS_LANGUAGE
                + "," + COLUMN_TS_SHORT_NAME_LABLE + "," + COLUMN_TS_FULL_NAME_LABLE + "," + COLUMN_TS_POSITION_LABLE
                + "," + COLUMN_OBJECT_MODEL_LABLE + "," + COLUMN_OBJECT_NUMBER_LABLE + "," + COLUMN_PART_NUMBER_LABLE
                + "," + COLUMN_PART_POSITION_LABLE + "," + COLUMN_TS_PERSON_IN_CHARGE_LABLE + "," + COLUMN_TS_WORKER_NAME_LABLE
                + "," + COLUMN_TS_AUDITOR_LABLE + "," + COLUMN_TS_DELIVER_DATETIME_LABLE + "," + COLUMN_TS_BEGIN_DATETIME_LABLE
                + "," + COLUMN_TS_FINISH_DATETIME_LABLE + "," + COLUMN_TS_SOLUTION_NUMBER_LABLE + "," + COLUMN_TS_SOLUTIONS_LABLE
                + "," + COLUMN_TS_POSSIBLE_REASON_LABLE + "," + COLUMN_TS_IS_USED_LABLE + "," + COLUMN_TS_IS_WORK_LABLE
                + "," + COLUMN_REAL_REASON_LABLE + "," + COLUMN_REMARK_LABLE + "," + COLUMN_PICTURE_LABLE
                + "," + COLUMN_VIDEO_LABLE + "," + COLUMN_AUDIO_LABLE + "," + COLUMN_PICTURE_LIST_LABLE
                + "," + COLUMN_VIDEO_LIST_LABLE + "," + COLUMN_AUDIO_LIST_LABLE + "," + COLUMN_RESERVETSPARAMETER1LABLE
                + "," + COLUMN_RESERVETSPARAMETER2LABLE + "," + COLUMN_RESERVETSPARAMETER3LABLE + "," + COLUMN_RESERVETSPARAMETER4LABLE
                + "," + COLUMN_RESERVETSPARAMETER5LABLE + "," + COLUMN_RESERVETSPARAMETER6LABLE + "," + COLUMN_RESERVETSPARAMETER7LABLE
                + "," + COLUMN_RESERVETSPARAMETER8LABLE + "," + COLUMN_TS_ID + "," + COLUMN_WORK_LOCATION + "," + COLUMN_TS_NUMBER
                + "," + COLUMN_TS_SHORT_NAME + "," + COLUMN_TS_FULL_NAME + "," + COLUMN_TS_POSITION + "," + COLUMN_OBJECT_MODEL
                + "," + COLUMN_OBJECT_NUMBER + "," + COLUMN_PART_NUMBER + "," + COLUMN_PART_POSITION + "," + COLUMN_TS_PERSON_IN_CHARGE
                + "," + COLUMN_TS_WORKER_NAME + "," + COLUMN_TS_AUDITOR + "," + COLUMN_TS_DELIVER_DATETIME + "," + COLUMN_TS_BEGIN_DATETIME
                + "," + COLUMN_TS_FINISH_DATETIME + "," + COLUMN_REAL_REASON + "," + COLUMN_REMARK + "," + COLUMN_PICTURE_LIST
                + "," + COLUMN_VIDEO_LIST + "," + COLUMN_AUDIO_LIST + "," + COLUMN_RESERVETSPARAMETER1 + "," + COLUMN_RESERVETSPARAMETER2
                + "," + COLUMN_RESERVETSPARAMETER3 + "," + COLUMN_RESERVETSPARAMETER4 + "," + COLUMN_RESERVETSPARAMETER5
                + "," + COLUMN_RESERVETSPARAMETER6 + "," + COLUMN_RESERVETSPARAMETER7 + "," + COLUMN_RESERVETSPARAMETER8
                + "," + COLUMN_CREATE_TIME + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    public class T_TSSOLUTION {
        //表名
        public static final String T_NAME = "T_TSSolution";

        //字段名
        public static final String COLUMN_TS_RECEIVE_FILE_NAME = "tsReceiveFileName"; //所接受的文件名
        public static final String COLUMN_TS_SOLUTION_ID_LABLE = "tsSolutionIDLable";
        public static final String COLUMN_TS_ID_LABLE = "tsIDLable";
        public static final String COLUMN_TS_SHORT_NAME_LABLE = "tsShortNameLable";
        public static final String COLUMN_TS_SOLUTION_NUMBER_LABLE = "tsSolutionNumberLable";
        public static final String COLUMN_TS_SOLUTION_NAME_LABLE = "tsSolutionNameLable";
        public static final String COLUMN_POSSIBLE_REASON_LABLE = "possibleReasonLable";
        public static final String COLUMN_IS_USED_LABLE = "isUsedLable";
        public static final String COLUMN_IS_WORK_LABLE = "isWorkLable";
        public static final String COLUMN_PICTURE_LIST_LABLE = "pictureListLable";
        public static final String COLUMN_VIDEO_LIST_LABLE = "videoListLable";
        public static final String COLUMN_AUDIO_LIST_LABLE = "audioListLable";
        public static final String COLUMN_RESERVETSSPARAMETER1LABLE = "reserveTssParameter1Lable";
        public static final String COLUMN_RESERVETSSPARAMETER2LABLE = "reserveTssParameter2Lable";
        public static final String COLUMN_RESERVETSSPARAMETER3LABLE = "reserveTssParameter3Lable";
        public static final String COLUMN_RESERVETSSPARAMETER4LABLE = "reserveTssParameter4Lable";
        public static final String COLUMN_RESERVETSSPARAMETER5LABLE = "reserveTssParameter5Lable";
        public static final String COLUMN_RESERVETSSPARAMETER6LABLE = "reserveTssParameter6Lable";
        public static final String COLUMN_RESERVETSSPARAMETER7LABLE = "reserveTssParameter7Lable";
        public static final String COLUMN_RESERVETSSPARAMETER8LABLE = "reserveTssParameter8Lable";

        public static final String COLUMN_TS_SOLUTION_ID = "tsSolutionID";
        public static final String COLUMN_TS_ID = "tsID";
        public static final String COLUMN_TS_SHORT_NAME = "tsShortName";
        public static final String COLUMN_TS_SOLUTION_NUMBER = "tsSolutionNumber";
        public static final String COLUMN_TS_SOLUTION_NAME = "tsSolutionName";
        public static final String COLUMN_POSSIBLE_REASON = "possibleReason";
        public static final String COLUMN_TS_DESCRIPTION = "tsDescription"; //故障描述
        public static final String COLUMN_TS_SOLUTION = "tsSolution"; //解决方法
        public static final String COLUMN_TS_REMARK = "tsRemark"; //备注
        public static final String COLUMN_IS_USED = "isUsed";
        public static final String COLUMN_IS_WORK = "isWork";
        public static final String COLUMN_PICTURE_LIST = "pictureList";
        public static final String COLUMN_VIDEO_LIST = "videoList";
        public static final String COLUMN_AUDIO_LIST = "audioList";
        public static final String COLUMN_RESERVETSSPARAMETER1 = "reserveTssParameter1";
        public static final String COLUMN_RESERVETSSPARAMETER2 = "reserveTssParameter2";
        public static final String COLUMN_RESERVETSSPARAMETER3 = "reserveTssParameter3";
        public static final String COLUMN_RESERVETSSPARAMETER4 = "reserveTssParameter4";
        public static final String COLUMN_RESERVETSSPARAMETER5 = "reserveTssParameter5";
        public static final String COLUMN_RESERVETSSPARAMETER6 = "reserveTssParameter6";
        public static final String COLUMN_RESERVETSSPARAMETER7 = "reserveTssParameter7";
        public static final String COLUMN_RESERVETSSPARAMETER8 = "reserveTssParameter8";

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";

        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_TS_RECEIVE_FILE_NAME + "," + COLUMN_TS_SOLUTION_ID_LABLE + "," + COLUMN_TS_ID_LABLE
                + "," + COLUMN_TS_SHORT_NAME_LABLE + "," + COLUMN_TS_SOLUTION_NUMBER_LABLE + "," + COLUMN_TS_SOLUTION_NAME_LABLE
                + "," + COLUMN_POSSIBLE_REASON_LABLE + "," + COLUMN_IS_USED_LABLE + "," + COLUMN_IS_WORK_LABLE
                + "," + COLUMN_PICTURE_LIST_LABLE + "," + COLUMN_VIDEO_LIST_LABLE + "," + COLUMN_AUDIO_LIST_LABLE
                + "," + COLUMN_RESERVETSSPARAMETER1LABLE + "," + COLUMN_RESERVETSSPARAMETER2LABLE + "," + COLUMN_RESERVETSSPARAMETER3LABLE
                + "," + COLUMN_RESERVETSSPARAMETER4LABLE + "," + COLUMN_RESERVETSSPARAMETER5LABLE + "," + COLUMN_RESERVETSSPARAMETER6LABLE
                + "," + COLUMN_RESERVETSSPARAMETER7LABLE + "," + COLUMN_RESERVETSSPARAMETER8LABLE + "," + COLUMN_TS_SOLUTION_ID
                + "," + COLUMN_TS_ID + "," + COLUMN_TS_SHORT_NAME + "," + COLUMN_TS_SOLUTION_NUMBER + "," + COLUMN_TS_SOLUTION_NAME
                + "," + COLUMN_TS_DESCRIPTION + "," + COLUMN_TS_SOLUTION + "," + COLUMN_TS_REMARK
                + "," + COLUMN_POSSIBLE_REASON + "," + COLUMN_IS_USED + "," + COLUMN_IS_WORK + "," + COLUMN_PICTURE_LIST
                + "," + COLUMN_VIDEO_LIST + "," + COLUMN_AUDIO_LIST + "," + COLUMN_RESERVETSSPARAMETER1
                + "," + COLUMN_RESERVETSSPARAMETER2 + "," + COLUMN_RESERVETSSPARAMETER3 + "," + COLUMN_RESERVETSSPARAMETER4
                + "," + COLUMN_RESERVETSSPARAMETER5 + "," + COLUMN_RESERVETSSPARAMETER6 + "," + COLUMN_RESERVETSSPARAMETER7
                + "," + COLUMN_RESERVETSSPARAMETER8 + "," + COLUMN_CREATE_TIME + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    //业务表，PC
    public class T_PC_JOB {
        //表名
        public static final String T_NAME = "T_PC_JOB";

        //字段
        public static final String COLUMN_PC_RECEIVE_FILE_NAME = "pcReceiveFileName"; //所接受的文件名
        public static final String COLUMN_AIRCRAFT_NUMBER = "aircraftNumber"; //对象编号
        public static final String COLUMN_ENGINE_NUMBER = "engineNumber"; //部件编号
        public static final String COLUMN_ENGINE_POSITION = "enginePosition"; //部件位置
        public static final String COLUMN_SENT_DATE_TIME = "sentDateTime"; //交付时间
        public static final String COLUMN_BEGIN_DATE_TIME = "beginDateTime"; //开始时间
        public static final String COLUMN_FINISH_DATE_TIME = "finishDateTime"; //完成时间
        public static final String COLUMN_RECEIVED_DATE_TIME = "receivedDateTime"; //发送时间
        public static final String COLUMN_JOB_REPORT_PATH = "jobReportPath"; //报告路径
        public static final String COLUMN_JOB_REPORT_DATE_TIME = "jobReportDateTime"; //报告时间
        public static final String COLUMN_MAJOR = "major"; //专业
        public static final String COLUMN_QCNAME = "qcName"; //质控
        public static final String COLUMN_DIRECTOR_NAME = "directorName"; //主管
        public static final String COLUMN_BASE_LOCATION = "baseLocation"; //地点
        public static final String COLUMN_CONCLUSION = "conclusion"; //结论

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";

        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_PC_RECEIVE_FILE_NAME + "," + COLUMN_AIRCRAFT_NUMBER
                + "," + COLUMN_ENGINE_NUMBER + "," + COLUMN_ENGINE_POSITION + "," + COLUMN_SENT_DATE_TIME
                + "," + COLUMN_BEGIN_DATE_TIME + "," + COLUMN_FINISH_DATE_TIME + "," + COLUMN_RECEIVED_DATE_TIME
                + "," + COLUMN_JOB_REPORT_PATH + "," + COLUMN_JOB_REPORT_DATE_TIME + "," + COLUMN_MAJOR
                + "," + COLUMN_QCNAME + "," + COLUMN_DIRECTOR_NAME + "," + COLUMN_BASE_LOCATION
                + "," + COLUMN_CONCLUSION + "," + COLUMN_CREATE_TIME + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    //操作卡
    public class T_PC_PROCESSCARD {

        //表名
        public static final String T_NAME = "T_PC_PROCESSCARD";

        //字段
        public static final String COLUMN_PC_RECEIVE_FILE_NAME = "pcReceiveFileName"; //所接受的文件名
        public static final String COLUMN_NUMBER = "number"; //编号
        public static final String COLUMN_NAME = "name"; //名字


        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";

        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_PC_RECEIVE_FILE_NAME + "," + COLUMN_NUMBER + "," + COLUMN_NAME
                + "," + COLUMN_CREATE_TIME + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    //技术卡
    public class T_PC_TECHNINALCARD {
        //表名
        public static final String T_NAME = "T_PC_TECHNINALCARD";

        //字段
        public static final String COLUMN_PC_RECEIVE_FILE_NAME = "pcReceiveFileName"; //所接受的文件名
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PAGE = "page";
        public static final String COLUMN_RESULT_VALUE = "resultValue";
        public static final String COLUMN_WORKER_NAME = "workerName";
        public static final String COLUMN_REVIEWER_NAME = "reviewerName";
        public static final String COLUMN_REMARK = "remark";
        public static final String COLUMN_HOURS = "hours";
        public static final String COLUMN_OPERATIONS = "operations";
        public static final String COLUMN_ACTIONS = "actions";
        public static final String COLUMN_EQUIPMENT = "equipment";
        public static final String COLUMN_TOOLS = "tools";
        public static final String COLUMN_MATERIALS = "materials";
        public static final String COLUMN_PHOTO_PATH_LIST = "photoPathList";
        public static final String COLUMN_VIDEO_PATH_LIST = "videoPathList";
        public static final String COLUMN_AUDIO_PATH_LIST = "audioPathList";
        public static final String COLUMN_BEGIN_DATE_TIME = "beginDateTime";
        public static final String COLUMN_FINISH_DATE_TIME = "finishDateTime";

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";

        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_PC_RECEIVE_FILE_NAME + "," + COLUMN_NUMBER + "," + COLUMN_NAME
                + "," + COLUMN_PAGE + "," + COLUMN_RESULT_VALUE + "," + COLUMN_WORKER_NAME + "," + COLUMN_REVIEWER_NAME
                + "," + COLUMN_REMARK + "," + COLUMN_HOURS + "," + COLUMN_OPERATIONS + "," + COLUMN_ACTIONS
                + "," + COLUMN_EQUIPMENT + "," + COLUMN_TOOLS + "," + COLUMN_MATERIALS + "," + COLUMN_PHOTO_PATH_LIST
                + "," + COLUMN_VIDEO_PATH_LIST + "," + COLUMN_AUDIO_PATH_LIST + "," + COLUMN_BEGIN_DATE_TIME
                + "," + COLUMN_FINISH_DATE_TIME + "," + COLUMN_CREATE_TIME + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    //配置表，第一次进入手动填写的内容
    public class T_FIRST_BASE_INFO {
        //表名
        public static final String T_NAME = "T_First_Base_Info";

        //字段名
        public static final String COLUMN_BASE_ADDRESS = "Base"; //位置
        public static final String COLUMN_O_Name = "O_Name"; //对象名
        public static final String COLUMN_NoO = "NoO"; //对象编号
        public static final String COLUMN_M_Name = "M_Name"; //部件名
        public static final String COLUMN_NoM1 = "NoM1"; //部件1编号
        public static final String COLUMN_NoM2 = "NoM2"; //部件2编号

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";

        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_BASE_ADDRESS + "," + COLUMN_O_Name + "," + COLUMN_NoO
                + "," + COLUMN_M_Name + "," + COLUMN_NoM1 + "," + COLUMN_NoM2 + "," + COLUMN_CREATE_TIME
                + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    //配置表，icon
    public class T_TS_CONFIG_ICON {
        //表名
        public static final String T_NAME = "T_Config_Icon";

        //字段名
        public static final String COLUMN_ICON_SS = "ss";
        public static final String COLUMN_ICON_TS = "ts";
        public static final String COLUMN_ICON_ED = "ed";
        public static final String COLUMN_ICON_EC = "ec";
        public static final String COLUMN_ICON_Object = "iconObject";
        public static final String COLUMN_ICON_Member = "iconMember";
        public static final String COLUMN_ICON_Table = "iconTable";
        public static final String COLUMN_ICON_Grafic = "iconGrafic";
        public static final String COLUMN_ICON_PC = "pc";
        public static final String COLUMN_ICON_TR = "tr";

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";

        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_ICON_SS + "," + COLUMN_ICON_TS + "," + COLUMN_ICON_ED
                + "," + COLUMN_ICON_EC + "," + COLUMN_ICON_Object + "," + COLUMN_ICON_Member + "," + COLUMN_ICON_Table
                + "," + COLUMN_ICON_Grafic + "," + COLUMN_ICON_PC + "," + COLUMN_ICON_TR + "," + COLUMN_CREATE_TIME
                + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    //配置表，TC-Links
    //TcDoc
    public class T_TS_CONFIG_TC_LINKS_Doc {
        //表名
        public static final String T_NAME = "T_Config_TC_LINKS_Doc";

        //字段名
        public static final String COLUMN_TCDoc = "tcDoc";

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";

        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_TCDoc + "," + COLUMN_CREATE_TIME + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    //TCInfo
    public class T_TS_CONFIG_TC_LINKS_TCInfo {
        //表名
        public static final String T_NAME = "T_Config_TC_LINKS_TCInfo";

        //字段名
        public static final String COLUMN_TC_ID = "tcId";
        public static final String COLUMN_TC_PAGE_ENG = "tcPageEng";
        public static final String COLUMN_TC_PAGE_CHN = "tcPageChn";

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";

        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_TC_ID + "," + COLUMN_TC_PAGE_ENG + "," + COLUMN_TC_PAGE_CHN + ","
                + COLUMN_CREATE_TIME + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    //配置表，TS-Links
    //TSDoc
    public class T_TS_CONFIG_TS_LINKS_Doc {
        //表名
        public static final String T_NAME = "T_Config_TS_LINKS_Doc";

        //字段名
        public static final String COLUMN_TSDoc_Label = "label";
        public static final String COLUMN_TSDoc_Text = "text";

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";

        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_TSDoc_Label + "," + COLUMN_TSDoc_Text
                + "," + COLUMN_CREATE_TIME + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    //TSLabels
    public class T_TS_CONFIG_TS_LINKS_TSLabels {
        //表名
        public static final String T_NAME = "T_Config_TS_LINKS_TSLabels";

        //字段名
        public static final String COLUMN_TS_LANGUAGE = "language";
        public static final String COLUMN_TS_SHORTNAME = "tsShortName";
        public static final String COLUMN_TS_COLOR = "tsColor";
        public static final String COLUMN_TS_GROUP = "tsGroup";
        public static final String COLUMN_TROUBLE_SHOOTING_WAY_EM = "troubleshootingWayEM";
        public static final String COLUMN_TROUBLE_SHOOTING_WAY_AEM = "troubleshootingWayAEM";
        public static final String COLUMN_TROUBLE_SHOOTING_WAY_TSEM = "troubleshootingWayTSEM";

        public static final String COLUMN_DESCRIPTION_EM = "descriptionEM";
        public static final String COLUMN_DESCRIPTION_AEM = "descriptionAEM";
        public static final String COLUMN_DESCRIPTION_TSEM = "descriptionTSEM";

        public static final String COLUMN_REASON_EM = "reasonEM";
        public static final String COLUMN_REASON_AEM = "reasonAEM";
        public static final String COLUMN_REASON_TSEM = "reasonTSEM";

        public static final String COLUMN_CONDITION_EM = "conditionEM";
        public static final String COLUMN_CONDITION_AEM = "conditionAEM";
        public static final String COLUMN_CONDITION_TSEM = "conditionTSEM";

        public static final String COLUMN_SOLUTION_EM = "solutionEM";
        public static final String COLUMN_SOLUTION_AEM = "solutionAEM";
        public static final String COLUMN_SOLUTION_TSEM = "solutionTSEM";

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";

        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_TS_LANGUAGE + "," + COLUMN_TS_SHORTNAME + "," + COLUMN_TS_COLOR
                + "," + COLUMN_TS_GROUP + "," + COLUMN_TROUBLE_SHOOTING_WAY_EM + "," + COLUMN_TROUBLE_SHOOTING_WAY_AEM
                + "," + COLUMN_TROUBLE_SHOOTING_WAY_TSEM + "," + COLUMN_DESCRIPTION_EM + "," + COLUMN_DESCRIPTION_AEM
                + "," + COLUMN_DESCRIPTION_TSEM + "," + COLUMN_REASON_EM + "," + COLUMN_REASON_AEM + "," + COLUMN_REASON_TSEM
                + "," + COLUMN_CONDITION_EM + "," + COLUMN_CONDITION_AEM + "," + COLUMN_CONDITION_TSEM + "," + COLUMN_SOLUTION_EM
                + "," + COLUMN_SOLUTION_AEM + "," + COLUMN_SOLUTION_TSEM + "," + COLUMN_CREATE_TIME + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    //TSInfo
    public class T_TS_CONFIG_TS_LINKS_TSInfo {
        //表名
        public static final String T_NAME = "T_Config_TS_LINKS_TSInfo";

        //字段名
        public static final String COLUMN_TS_LANGUAGE = "language";
        public static final String COLUMN_TS_SHORTNAME = "tsShortName";
        public static final String COLUMN_TS_COLOR = "tsColor";
        public static final String COLUMN_TS_GROUP = "tsGroup";
        public static final String COLUMN_EM_DESCRIPTION_PAGE = "emDescriptionPage";
        public static final String COLUMN_AEM_DESCRIPTION_PAGE = "aemDescriptionPage";
        public static final String COLUMN_TSEM_DESCRIPTION_PAGE = "tsemDescriptionPage";

        public static final String COLUMN_EM_REASON_PAGE = "emReasonPage";
        public static final String COLUMN_AEM_REASON_PAGE = "aemReasonPage";
        public static final String COLUMN_TSEM_REASON_PAGE = "tsemReasonPage";

        public static final String COLUMN_EM_CONDITION_PAGE = "emConditionPage";
        public static final String COLUMN_AEM_CONDITION_PAGE = "aemConditionPage";
        public static final String COLUMN_TSEM_CONDITION_PAGE = "tsemConditionPage";

        public static final String COLUMN_EM_SOLUTION_PAGE = "emSolutionPage";
        public static final String COLUMN_AEM_SOLUTION_PAGE = "aemSolutionPage";
        public static final String COLUMN_TSEM_SOLUTION_PAGE = "tsemSolutionPage";

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";

        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_TS_LANGUAGE + "," + COLUMN_TS_SHORTNAME + "," + COLUMN_TS_COLOR
                + "," + COLUMN_TS_GROUP + "," + COLUMN_EM_DESCRIPTION_PAGE + "," + COLUMN_AEM_DESCRIPTION_PAGE
                + "," + COLUMN_TSEM_DESCRIPTION_PAGE + "," + COLUMN_EM_REASON_PAGE + "," + COLUMN_AEM_REASON_PAGE
                + "," + COLUMN_TSEM_REASON_PAGE + "," + COLUMN_EM_CONDITION_PAGE + "," + COLUMN_AEM_CONDITION_PAGE
                + "," + COLUMN_TSEM_CONDITION_PAGE + "," + COLUMN_EM_SOLUTION_PAGE + "," + COLUMN_AEM_SOLUTION_PAGE
                + "," + COLUMN_TSEM_SOLUTION_PAGE + "," + COLUMN_CREATE_TIME + "," + COLUMN_UPDATE_TIME;
        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    //配置表，UI-Names
    public class T_TS_CONFIG_UI_Names {
        //表名
        public static final String T_NAME = "T_Config_UI_Names";

        //字段名
        public static final String COLUMN_LANGUAGE = "language"; //语言
        public static final String COLUMN_Object = "object"; //对象
        public static final String COLUMN_Member = "member"; //部件
        public static final String COLUMN_O_Name = "oName"; //对象名
        public static final String COLUMN_M_Name = "mName"; //部件名
        public static final String COLUMN_Base = "base"; //位置
        public static final String COLUMN_NoO = "noO"; //对象编号
        public static final String COLUMN_NoM1 = "noM1"; //部件1编号
        public static final String COLUMN_NoM2 = "noM2"; //部件2编号
        public static final String COLUMN_SS = "ss"; //服务
        public static final String COLUMN_TR = "tr"; //排故
        public static final String COLUMN_ED = "ed"; //文件
        public static final String COLUMN_EC = "ec"; //状态
        public static final String COLUMN_EM = "em";
        public static final String COLUMN_AEM = "aem";
        public static final String COLUMN_TSEM = "tsem";
        public static final String COLUMN_ESTM = "estm";

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";

        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_LANGUAGE + "," + COLUMN_Object + "," + COLUMN_Member
                + "," + COLUMN_O_Name + "," + COLUMN_M_Name + "," + COLUMN_Base + "," + COLUMN_NoO + "," + COLUMN_NoM1
                + "," + COLUMN_NoM2 + "," + COLUMN_SS + "," + COLUMN_TR + "," + COLUMN_ED + "," + COLUMN_EC
                + "," + COLUMN_EM + "," + COLUMN_AEM + "," + COLUMN_TSEM + "," + COLUMN_ESTM
                + "," + COLUMN_CREATE_TIME + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    //配置表，TS-UI-Names
    public class T_TS_CONFIG_TS_UI_Names {
        //表名
        public static final String T_NAME = "T_Config_TS_UI_Names";

        //字段名
        public static final String COLUMN_LANGUAGE = "language"; //语言
        public static final String COLUMN_WORK_LOCATION_LABEL = "workLocationLabel"; //地点
        public static final String COLUMN_TS_NUMBER_LABEL = "tsNumberLabel"; //排故编号
        public static final String COLUMN_TS_SHORT_NAME_LABEL = "tsShortNameLabel"; //故障名称(信号简称)
        public static final String COLUMN_TS_FULL_NAME_LABEL = "tsFullNameLabel"; //故障名称(信号全称)
        public static final String COLUMN_TS_POSITION_LABEL = "tsPositionLabel"; //故障位置
        public static final String COLUMN_OBJECT_MODEL_LABEL = "objectModelLabel"; //对象型号
        public static final String COLUMN_OBJECT_NUMBER_LABEL = "objectNumberLabel"; //对象编号
        public static final String COLUMN_PART_NUMBER_LABEL = "partNumberLabel"; //部件编号
        public static final String COLUMN_PART_POSITION_LABEL = "partPositionLabel"; //部件位置
        public static final String COLUMN_TS_PERSON_IN_CHARGE_LABEL = "tsPersonInChargeLabel"; //主管
        public static final String COLUMN_TS_WORKER_NAME_LABEL = "tsWorkerNameLabel"; //操作者
        public static final String COLUMN_TS_AUDITOR_LABEL = "tsAuditorLabel"; //审核者
        public static final String COLUMN_TS_DELIVER_DATE_TIME_LABEL = "tsDeliverDateTimeLabel"; //交付日期/时间
        public static final String COLUMN_TS_BEGIN_DATE_TIME_LABEL = "tsBeginDateTimeLabel"; //开始日期/时间
        public static final String COLUMN_TS_FINISH_DATE_TIME_LABEL = "tsFinishDateTimeLabel"; //完成日期/时间
        public static final String COLUMN_TS_SOLUTION_NUMBER_LABEL = "tsSolutionNumberLabel"; //编号
        public static final String COLUMN_TS_SOLUTIONS_LABEL = "tsSolutionsLabel"; //排故方法
        public static final String COLUMN_TS_POSSIBLE_REASON_LABEL = "tsPossibleReasonLabel"; //可能的原因
        public static final String COLUMN_TS_IS_USED_LABEL = "tsIsUsedLabel"; //使用的方法（+/-)
        public static final String COLUMN_TS_IS_WORK_LABEL = "tsIsWorkLabel"; //结果（+/-）
        public static final String COLUMN_REAL_REASON_LABEL = "realReasonLabel"; //真实的原因
        public static final String COLUMN_REMARK_LABEL = "remarkLabel"; //备注
        public static final String COLUMN_PICTURE_LABEL = "pictureLabel"; //照片
        public static final String COLUMN_VIDEO_LABEL = "videoLabel"; //视频
        public static final String COLUMN_AUDIO_LABEL = "audioLabel"; //音频
        public static final String COLUMN_USED_LABEL = "usedLabel"; //是否使用该方法
        public static final String COLUMN_WORK_LABEL = "workLabel"; //是否起作用
        public static final String COLUMN_REASON_LABEL = "reasonLabel"; //原因
        public static final String COLUMN_DESCRIPTION_LABEL = "descriptionLabel"; //故障描述
        public static final String COLUMN_SOLUTION_LABEL = "solutionLabel"; //解决方法

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";
        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_LANGUAGE + "," + COLUMN_WORK_LOCATION_LABEL + "," + COLUMN_TS_NUMBER_LABEL
                + "," + COLUMN_TS_SHORT_NAME_LABEL + "," + COLUMN_TS_FULL_NAME_LABEL + "," + COLUMN_TS_POSITION_LABEL
                + "," + COLUMN_OBJECT_MODEL_LABEL + "," + COLUMN_OBJECT_NUMBER_LABEL + "," + COLUMN_PART_NUMBER_LABEL
                + "," + COLUMN_PART_POSITION_LABEL + "," + COLUMN_TS_PERSON_IN_CHARGE_LABEL + "," + COLUMN_TS_WORKER_NAME_LABEL
                + "," + COLUMN_TS_AUDITOR_LABEL + "," + COLUMN_TS_DELIVER_DATE_TIME_LABEL + "," + COLUMN_TS_BEGIN_DATE_TIME_LABEL
                + "," + COLUMN_TS_FINISH_DATE_TIME_LABEL + "," + COLUMN_TS_SOLUTION_NUMBER_LABEL + "," + COLUMN_TS_SOLUTIONS_LABEL
                + "," + COLUMN_TS_POSSIBLE_REASON_LABEL + "," + COLUMN_TS_IS_USED_LABEL + "," + COLUMN_TS_IS_WORK_LABEL
                + "," + COLUMN_REAL_REASON_LABEL + "," + COLUMN_REMARK_LABEL + "," + COLUMN_PICTURE_LABEL + "," + COLUMN_SOLUTION_LABEL
                + "," + COLUMN_USED_LABEL + "," + COLUMN_WORK_LABEL + "," + COLUMN_REASON_LABEL + "," + COLUMN_DESCRIPTION_LABEL
                + "," + COLUMN_VIDEO_LABEL + "," + COLUMN_AUDIO_LABEL + "," + COLUMN_CREATE_TIME + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    //配置表，TC-UI-Names
    public class T_TS_CONFIG_TC_UI_Names {
        //表名
        public static final String T_NAME = "T_Config_TC_UI_Names";

        //字段名
        public static final String COLUMN_LANGUAGE = "language"; //语言
        public static final String COLUMN_WORK_LOCATION_LABEL = "workLocationLabel"; //单位
        public static final String COLUMN_PC_NUMBER_LABEL = "pcNumberLabel"; //操作卡片编号
        public static final String COLUMN_OBJECT_MODEL_LABEL = "objectModelLabel"; //对象型号
        public static final String COLUMN_OBJECT_NUMBER_LABEL = "objectNumberLabel"; //对象编号
        public static final String COLUMN_PC_POSITION_LABEL = "pcPositionLabel"; //专业
        public static final String COLUMN_PART_NUMBER_LABEL = "partNumberLabel"; //部件编号
        public static final String COLUMN_PART_POSITION_LABEL = "partPositionLabel"; //部件位置
        public static final String COLUMN_PC_QC_LABEL = "pcQCLabel"; //质控
        public static final String COLUMN_PC_PERSON_IN_CHARGE_LABEL = "pcPersonInChargeLabel"; //主管
        public static final String COLUMN_PC_WORKER_NAME_LABEL = "pcWorkerNameLabel"; //操作者
        public static final String COLUMN_PC_AUDITOR_LABEL = "pcAuditorLabel"; //审核者
        public static final String COLUMN_PC_DELIVER_DATE_TIME_LABEL = "pcDeliverDateTimeLabel"; //交付日期/时间
        public static final String COLUMN_PC_BEGIN_DATE_TIME_LABEL = "pcBeginDateTimeLabel"; //开始日期/时间
        public static final String COLUMN_PC_FINISH_DATE_TIME_LABEL = "pcFinishDateTimeLabel"; //完成日期/时间
        public static final String COLUMN_PC_TC_NUMBER_LABEL = "pcTCNumberLabel"; //编号
        public static final String COLUMN_PC_TC_S_LABEL = "pcTCsLabel"; //工作内容
        public static final String COLUMN_PC_COMPLETE_LABEL = "pcCompleteLabel"; //完成情况
        public static final String COLUMN_PC_QUANTITY_LABEL = "pcQuantityLabel"; //结果数量
        public static final String COLUMN_REMARK_LABEL = "remarkLabel"; //备注
        public static final String COLUMN_PICTURE_LABEL = "pictureLabel"; //照片
        public static final String COLUMN_VIDEO_LABEL = "videoLabel"; //视频
        public static final String COLUMN_AUDIO_LABEL = "audioLabel"; //音频

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";
        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_LANGUAGE + "," + COLUMN_WORK_LOCATION_LABEL + "," + COLUMN_PC_NUMBER_LABEL
                + "," + COLUMN_OBJECT_MODEL_LABEL + "," + COLUMN_OBJECT_NUMBER_LABEL + "," + COLUMN_PART_NUMBER_LABEL
                + "," + COLUMN_PART_POSITION_LABEL + "," + COLUMN_PC_POSITION_LABEL + "," + COLUMN_PC_QC_LABEL
                + "," + COLUMN_PC_PERSON_IN_CHARGE_LABEL + "," + COLUMN_PC_WORKER_NAME_LABEL + "," + COLUMN_PC_AUDITOR_LABEL
                + "," + COLUMN_PC_DELIVER_DATE_TIME_LABEL + "," + COLUMN_PC_BEGIN_DATE_TIME_LABEL + "," + COLUMN_PC_FINISH_DATE_TIME_LABEL
                + "," + COLUMN_PC_TC_NUMBER_LABEL + "," + COLUMN_PC_TC_S_LABEL + "," + COLUMN_PC_COMPLETE_LABEL
                + "," + COLUMN_PC_QUANTITY_LABEL + "," + COLUMN_REMARK_LABEL + "," + COLUMN_PICTURE_LABEL
                + "," + COLUMN_VIDEO_LABEL + "," + COLUMN_AUDIO_LABEL + "," + COLUMN_CREATE_TIME + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    //配置表，User_Level
    public class T_TS_CONFIG_User_Level {
        //表名
        public static final String T_NAME = "T_Config_User_Level";

        //字段名
        public static final String COLUMN_LEVEL_ID = "levelId";
        public static final String COLUMN_LEVEL_NAME_ENG = "levelNameEng";
        public static final String COLUMN_LEVEL_NAME_CHN = "levelNameChn";

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";

        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_LEVEL_ID + "," + COLUMN_LEVEL_NAME_ENG + "," + COLUMN_LEVEL_NAME_CHN
                + "," + COLUMN_CREATE_TIME + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }

    //配置表，User_Member
    public class T_TS_CONFIG_User_Member {
        //表名
        public static final String T_NAME = "T_Config_User_Member";

        //字段名
        public static final String COLUMN_USER_LEVEL_ID = "userLevelId";
        public static final String COLUMN_USER_ID = "userId";
        public static final String COLUMN_USER_NAME = "userName";
        public static final String COLUMN_USER_PASSWORD = "userPassword";

        //更新时间和创建时间
        public static final String COLUMN_CREATE_TIME = "createTime";
        public static final String COLUMN_UPDATE_TIME = "updateTime";

        //初始化信息
        public static final String T_COLUMNS_INIT = COLUMN_USER_LEVEL_ID + "," + COLUMN_USER_ID + "," + COLUMN_USER_NAME
                + "," + COLUMN_USER_PASSWORD + "," + COLUMN_CREATE_TIME + "," + COLUMN_UPDATE_TIME;

        //更新表信息
        public static final String T_COLUMNS_UPDATE = "";
    }
}
