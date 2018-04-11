package com.aus.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xy on 2017/5/16.
 */
public class DateUtil {

    private static SimpleDateFormat simpleDateFormat = null;
    private static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    private static final String YMD = "yyMMdd";

    /**
     * 时间转字符串 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static Date strToDate(String date) throws ParseException {
        if (date == null || date.length() == 0) return null;
        simpleDateFormat = new SimpleDateFormat(YMDHMS);
        return simpleDateFormat.parse(date);
    }
    /**
     * 时间转字符串 MM-dd HH:mm
     * @param date
     * @return
     */
    public static String dateToStrMDHM(Date date){
        if (date == null) return "";
        simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        return simpleDateFormat.format(date);
    }
    /**
     * 时间转字符串 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String dateToStr(Date date){
        if (date == null) return "";
        simpleDateFormat = new SimpleDateFormat(YMDHMS);
        return simpleDateFormat.format(date);
    }
    /**
     * 返回当前时间格式为yyyyMMdd
     * @return
     */
    public static String getYmd(){
        simpleDateFormat = new SimpleDateFormat(YMD);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 时间缀后几位
     * @return
     */
    public static String getCurrentTimeComposeSub(int num){
        String current = String.valueOf(System.currentTimeMillis());
        if (current.length() > num){
            return current.substring(current.length() - num);
        }
        return "";
    }
}
