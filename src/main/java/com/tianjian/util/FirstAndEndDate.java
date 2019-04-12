package com.tianjian.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具
 * @Author:wub
 * @Date:Created in 2018/4/12
 */
public class FirstAndEndDate {
    private static Calendar cale = Calendar.getInstance();

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static String firstday;
    private static String endday;
    public static String  getFirstDateForCurrentMonth(){
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        return firstday;
    }
    public static String  getEndtDateForCurrentMonth(){
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        endday = format.format(cale.getTime());
        return endday;
    }
    public static String  getFirstDateForCurrentYear(){
    	String str=format.format(new Date());
    	firstday=str.split("-")[0]+"-01-01";
        return firstday;
    }
    public static String  getEndtDateForCurrentYear(){
        //cale.add(Calendar.MONTH, 1);
    	//cale.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE);
        endday = format.format(new Date());
        return endday;
    }

    //判断开始日期跟结束日期之间的年份
    public static List<String> getYears(Date dBegin, Date dEnd) {
        List<String> dateList = new ArrayList<String>();

            Calendar calBegin = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calBegin.setTime(dBegin);
            int year1 = calBegin.get(Calendar.YEAR);
            Calendar calEnd = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calEnd.setTime(dEnd);
            int year2 = calEnd.get(Calendar.YEAR);
            // 测试此日期是否在指定日期之后
            while (year2 >= year1) {
                dateList.add(ToolDateTime.format(calBegin.getTime(), "yyyy"));
                calBegin.add(Calendar.YEAR, 1);
                year1 = calBegin.get(Calendar.YEAR);
            }
        return dateList;
    }
    //判断开始日期跟结束日期之间的日期数
    public static List<String> getDates(Date beginDate, Date endDate) throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<String> lDate = new ArrayList<String>();
        lDate.add(format.format(beginDate));// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(format.format(cal.getTime()));
            } else {
                break;
            }
        }
        lDate.add(format.format(endDate));// 把结束时间加入集合
        return lDate;
    }
    //判断当前开始日期跟结束日期之间的月份
    public static List<String> getMonths(Date dBegin, Date dEnd) {
        List<String> dateList = new ArrayList<String>();

            Calendar calBegin = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calBegin.setTime(dBegin);
            int yearMonth1 = (calBegin.get(Calendar.YEAR)) * 100 + calBegin.get(Calendar.MONTH);
            Calendar calEnd = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calEnd.setTime(dEnd);
            int yearMonth2 = (calEnd.get(Calendar.YEAR)) * 100 + calEnd.get(Calendar.MONTH);
            // 测试此日期是否在指定日期之后
            while (yearMonth2 >= yearMonth1) {
                dateList.add(ToolDateTime.format(calBegin.getTime(), ToolDateTime.pattern_ym));
                calBegin.add(Calendar.MONTH, 1);
                yearMonth1 = (calBegin.get(Calendar.YEAR)) * 100 + calBegin.get(Calendar.MONTH);
            }
        return dateList;
    }
    
}
