package com.qian.ess.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    //一年的毫秒数
    public static long oneYearMSEL = 1508104800000L;

    //一天的秒数
    public static long oneDaySECOND = 24 * 60 * 60;

    //一小时的秒数
    public static long oneHourSECOND = 60 * 60;

    //一分钟的秒数
    public static long oneMinSECOND = 60;

    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";


    /**
     * 英文简写（默认）如：23:15:06
     */
    public static String FORMAT_SHORT_HMS = "HH:mm:ss";

    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    public static String FORMAT_LONG_NOT_SECOND = "yyyy-MM-dd HH:mm";

    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG_WITHOUT = "yyyyMMddHHmmss";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 打点的日期格式
     */
    public static String FORMAT_POINT = "yyyy.MM.dd";

    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_SHORT_YM_CN = "yyyy年MM月";

    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd日";

    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";
    public static String FORMAT_FULL_EN = "yyyyMMddHHmmssSSS";

    /**
     * 获取SimpleDateFormat
     *
     * @param parttern 日期格式
     * @return SimpleDateFormat对象
     */
    private static SimpleDateFormat getDateFormat(String parttern) {
        return new SimpleDateFormat(parttern);
    }

    /**
     * 将字符串转化成日期
     *
     * @param format 日期格式 ("yyyy-MM-dd hh:mm:ss")
     * @return String
     */
    public static Date stringToDate(String dateStr, String format) {
        try {
            return getDateFormat(format).parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     * 将日期转化成字符串
     *
     * @param format 日期格式 ("yyyy-MM-dd hh:mm:ss")
     * @return String
     */
    public static String dateToString(Date date, String format) {
        return getDateFormat(format).format(date);
    }

    /**
     * 格式化日期
     *
     * @param format 日期格式 ("yyyy-MM-dd hh:mm:ss")
     * @return String
     */
    public static String dateToString(String date, String format) {
        return getDateFormat(format).format(date);
    }

    /**
     * 将时间戳格式化为指定格式
     *
     * @param date      时间戳
     * @param formatStr 日期格式 ("yyyy-MM-dd hh:mm:ss")
     * @return String
     */
    public static String timeToString(long date, String formatStr) {
        //时间戳转化为Sting或Date
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }

    /**
     * 将指定格式格式化为时间戳
     *
     * @param date      时间
     * @param formatStr 日期格式 ("yyyy-MM-dd hh:mm:ss")
     * @return String
     */
    public static long timeToString(String date, String formatStr) {
        return stringToDate(date, formatStr).getTime();
    }

    /**
     * 将秒转换成描述时长的字符串
     *
     * @param duration 秒
     */
    public static String secondToString(int duration) {
        StringBuilder stringBuilder = new StringBuilder();

        int hour = duration / 3600; //小时数
        int hour_remainder = duration % 3600; //小时数的余数

        int minutes = hour_remainder / 60; //分钟数
        int minutes_remainder = hour_remainder % 60; //分钟数的余数，即秒数

        if (hour > 0) {
            stringBuilder.append(hour).append("小时");
        }

        if (minutes > 0) {
            stringBuilder.append(minutes).append("分");
        }

        if (minutes_remainder >= 0) {
            stringBuilder.append(minutes_remainder).append("秒");
        }

        return stringBuilder.toString();
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param time   时间格式
     * @param format 格式
     * @return 具体时间
     */
    public static String convertTimeToFormat(String time, String format) {
        return convertTimeToFormat(timeToString(time, format));
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp 时间戳
     * @return 具体时间
     */
    public static String convertTimeToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis();
        long time = (curTime - timeStamp) / 1000;

        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
        } else {
            return "刚刚";
        }
    }

    /**
     * 判断是否为昨天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException 异常
     */
    public static boolean IsYesterday(String day, String format) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat(format).parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param day    选择的时间
     * @param format 时间格式化
     * @return true 是今天之前   false是今天之后或今天
     */
    public static boolean isBeforeToday(String day, String format) throws ParseException {
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat(format).parse(day);
        cal.setTime(date);

        return !IsToday(day, format) && cal.before(pre);

    }

    /**
     * @param day1 第一个日期
     * @param day2 第二个日期
     * @return true 是之前   false是今天之后或今天
     */
    public static boolean isBeforeTime(String day1, String day2) throws ParseException {
        Calendar pre = Calendar.getInstance();
        Date date1 = getDateFormat(FORMAT_SHORT).parse(day2);
        pre.setTime(date1);

        Calendar cal = Calendar.getInstance();
        Date date2 = getDateFormat(FORMAT_SHORT).parse(day1);
        cal.setTime(date2);

        return !isEqualDate(day1, day2) && cal.before(pre);

    }

    /**
     * 判断两个日期是否是同一天
     *
     * @param day1 第一个日期
     * @param day2 第二个日期
     * @return true相等 false不相等
     * @throws ParseException 异常
     */
    public static boolean isEqualDate(String day1, String day2) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date date1 = getDateFormat(FORMAT_SHORT).parse(day1);
        pre.setTime(date1);

        Calendar cal = Calendar.getInstance();
        Date date2 = getDateFormat(FORMAT_SHORT).parse(day2);
        cal.setTime(date2);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 计算两个时间之差
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 返回相差的天数
     * @throws ParseException 抛出异常
     */
    public static long getCompareDate(String startDate, String endDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = formatter.parse(startDate);
        Date date2 = formatter.parse(endDate);
        long l = date2.getTime() - date1.getTime();
        return l / (24 * 60 * 60 * 1000);
    }

    /**
     * 判断是否为今天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException 异常
     */
    public static boolean IsToday(String day, String format) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat(format).parse(day);
        cal.setTime(date);
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将一个时间转换成提示性时间字符串，如今天10:00 昨天12:00
     *
     * @param date 传入的 时间  "2016-06-28 10:10:30"
     * @return true今天 false不是
     * @throws ParseException 异常
     */
    public static String convertDateStrToFormat(String date) {
        String result = "";
        try {
            if (IsToday(date, "yyyy-MM-dd hh:mm:ss")) {
                result = "今天 " + date.split(" ")[1];
            } else if (IsYesterday(date, "yyyy-MM-dd hh:mm:ss")) {
                result = "昨天 " + date.split(" ")[1];
            } else {
                result = date;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 计算两个时间相差的年月日
     *
     * @param fromDate 开始的日期 比如2017-07-11，传20160711
     * @param toDate   结束的日期 比如2017-07-11，传20170711
     * @return 返回一个6年以上的状态 -1：报错 0：6年以下 1：6年以上
     */
    public static int getSixYearState(String fromDate, String toDate) {
        int isSixYear = -1;
        if (TextUtils.isEmpty(fromDate) || fromDate.length() < 8) {
            isSixYear = -1;
        } else {
            try {
                int start = Integer.parseInt(fromDate);
                int end = Integer.parseInt(toDate);
                if (start > end) {
                    isSixYear = -1;
                    ToastUtils.show("注册时间不能大于当前时间");
                } else {
                    isSixYear = (start + 60000) > end ? 0 : 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
                isSixYear = -1;
                ToastUtils.show("时间格式转换异常");
            }
        }

        return isSixYear;
    }

    /**
     * 计算两个时间相差的年月日
     *
     * @param fromDate 开始的日期 比如2017-07-11，传20160711
     * @param toDate   结束的日期 比如2017-07-11，传20170711
     * @return 返回一个数组，分别表示年月日
     */
    public static int[] getDateInterval(String fromDate, String toDate) {
        Calendar c1 = getCal(fromDate);
        Calendar c2 = getCal(toDate);
        int[] p1 = {c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH)};
        int[] p2 = {c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2.get(Calendar.DAY_OF_MONTH)};
        return new int[]{p2[0] - p1[0], p2[0] * 12 + p2[1] - p1[0] * 12 - p1[1], (int) ((c2.getTimeInMillis() - c1.getTimeInMillis()) / (24 * 3600 * 1000))};
    }

    /**
     * 格式化字符串日期
     *
     * @param date 要被格式化的日期
     * @return 被格式化的日期
     */
    private static Calendar getCal(String date) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)) - 1, Integer.parseInt(date.substring(6, 8)));
        return cal;
    }

    /**
     * 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 获取时间戳
     */
    public static long getTimeStamp(String format, String dateStr) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);
            Date date = df.parse(dateStr);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 获取日期年份
     *
     * @param date 日期
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 功能描述：返回月份
     *
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日份
     *
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回当前年月日
     *
     * @param date Date 日期
     * @return 返回当前年月日
     */
    public static int[] getYearMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new int[]{calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)};
    }

    /**
     * 功能描述：返回小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分钟
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫
     *
     * @param date 日期
     * @return 返回毫
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

}
