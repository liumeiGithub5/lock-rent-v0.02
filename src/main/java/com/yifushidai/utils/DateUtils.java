package com.yifushidai.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liumei on 2017/8/14 17:35.
 * desc:日期处理 时间转换工具
 */
public class DateUtils {
    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static Date getCurrent() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN);
        Date date = new Date();
        String dateStr =  df.format(date);
        return  df.parse(dateStr);
    }
    public static String  getCurrent2() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN);
        Date date = new Date();
        String dateStr =  df.format(date);
        return  dateStr;
    }

    public final static SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
    public final static SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_PATTERN);
    /*转日期 */
    public static Date toDate(String str) throws ParseException {
        return sdf.parse(str);
    }
    public static Date toDate2(String str) throws ParseException {
        return sdf2.parse(str);
    }
    /*转字符串*/
    public  static String toStr(Date date){
        return sdf.format(date);
    }
    public  static String toStr2(Date date){
        return sdf2.format(date);
    }
    /*加减时间*/
    public static Date subTime(Long l, Date date) throws ParseException {
        Date newTime = new Date(date.getTime()-l);
        String str = toStr(newTime);
        return toDate(str);
    }
    public static Date addTime(Long l, Date date) throws ParseException {
        Date newTime = new Date(date.getTime()+l);
        String str = toStr(newTime);
        return toDate(str);
    }
}
