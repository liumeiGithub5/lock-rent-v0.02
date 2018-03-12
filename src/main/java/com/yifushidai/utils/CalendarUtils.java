package com.yifushidai.utils;

import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by liumei on 2017/11/15 11:12.
 * desc:calendar 日期加减
 */
public class CalendarUtils {

    /*获取两个间隔时间 相差几天*/
    public static int getDayDiff(String sd, String ed) throws ParseException {
        //获取毫秒值，计算 "yyyy-MM-dd"
        Long stime = DateUtils.toDate2(sd).getTime();
        Long etime = DateUtils.toDate2(ed).getTime();
       /* //获取毫秒值，计算 "yyyy-MM-dd HH:mm:ss"
        Long stime = DateUtils.toDate(sd).getTime();
        Long etime = DateUtils.toDate(ed).getTime();*/
        int range = (int)((etime-stime) / (1000 * 60 * 60 * 24));
        return range;
    }
    public static int getDayDiff2(String sd, String ed) throws ParseException {
        //获取毫秒值，计算 "yyyy-MM-dd HH:mm:ss"
        Long stime = DateUtils.toDate(sd).getTime();
        Long etime = DateUtils.toDate(ed).getTime();
        int range = (int)((etime-stime) / (1000 * 60 * 60 * 24));
        return range;
    }

    /*特定时间段，按照特定间隔进行天数、周数、月数、年数的  加减, 返回结果日期*/
    public static String  dayaddOrSub(String sd,Integer num) throws ParseException {
        Calendar specialDate = Calendar.getInstance();
        specialDate.setTime(DateUtils.toDate2(sd));
        specialDate.add(Calendar.DATE,+num);//按照num加 或者减  num= +5 ；num= -7
        return DateUtils.toStr2(specialDate.getTime()); //2017-11-16
    }
    public static String  weekaddOrSub(Date sd,Integer num){
        Calendar specialDate = Calendar.getInstance();
        specialDate.setTime(sd);
        specialDate.add(Calendar.WEDNESDAY,+num);
        return DateUtils.toStr(specialDate.getTime());
    }
    public static String  monthaddOrSub(Date sd,Integer num){
        Calendar specialDate = Calendar.getInstance();
        specialDate.setTime(sd);
        specialDate.add(Calendar.MONTH,+num);
        return DateUtils.toStr(specialDate.getTime());
    }
    public static String  yearaddOrSub(Date sd,Integer num){
        Calendar specialDate = Calendar.getInstance();
        specialDate.setTime(sd);
        specialDate.add(Calendar.YEAR,+num);
        return DateUtils.toStr(specialDate.getTime());
    }
   /* @Test
    public void dayadd1() throws ParseException {
        Calendar specialDate = Calendar.getInstance();
        specialDate.setTime(DateUtils.getCurrent());
        specialDate.add(Calendar.DATE,+1);
        System.out.println(DateUtils.getCurrent());
        System.out.println(DateUtils.toStr(specialDate.getTime()));
       // return specialDate;
    }
    @Test
    public void getDayDiff1() throws ParseException {
        //Calendar specialDate = Calendar.getInstance();
        //获取毫秒值，计算
        // specialDate.setTime(sd);//起始日期

        Long stime = DateUtils.toDate("2017-08-07 12:00:00").getTime();
        Long etime = DateUtils.toDate("2017-09-07 16:30:26").getTime();
        Long range = (etime-stime) / (1000 * 60 * 60 * 24);
        System.out.println(etime-stime);
        System.out.println(range);
    }*/
}
