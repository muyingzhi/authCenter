package com.tianjian.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author muyz
 *         Created on 2018/5/16
 */
public class DateFlag {
    public final static int year = 1;
    public final static int month = 2;
    public final static int week = 3;
    public final static int day = 4;

    public static String getFormat(int dateFlag){
        String dateFormat = "%Y";
        switch (dateFlag){
            case DateFlag.year:
                dateFormat = "%Y";
                break;
            case DateFlag.month:
                dateFormat = "%Y-%m";
                break;
            case DateFlag.week:
                dateFormat = "%x%v";
                break;
            case DateFlag.day:
                dateFormat = "%Y-%m-%d";
                break;
        };
        return  dateFormat;
    }
    public static List<String> initDates(int dateFlag, boolean haveYear, Date startDate, Date endDate){
        List<String> datas = new ArrayList();

        Calendar start = Calendar.getInstance();
        start.setTime(startDate);

        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        switch (dateFlag)  {
            case DateFlag.year:
                while(true) {
                    // 测试此end日期是否在start日期之后
                    if (end.compareTo(start)==1) {
                        DateFormat format = new SimpleDateFormat("yyyy");
                        datas.add(format.format(start.getTime()));
                        // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                        start.add(Calendar.YEAR, 1);
                    } else {
                        break;
                    }
                }
                break;
            case DateFlag.month:
                while (true) {
                    // 测试此end日期是否在start日期之后
                    if (end.compareTo(start)==1) {
                        DateFormat format = new SimpleDateFormat("yyyy-MM");
                        datas.add(format.format(start.getTime()));
                        // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                        start.add(Calendar.MONTH, 1);
                    } else {
                        break;
                    }
                }
                break;
            case DateFlag.week:
                String lastWeek = "";
                while (true) {
                    // 测试此end日期是否在start日期之后
                    if (end.compareTo(start)==1){
                        String strWeek = DateFlag.getWeekOfYear(start.getTime(),haveYear);
                        if (!lastWeek.equals(strWeek.toString())) {
                            datas.add(strWeek.toString());
                            lastWeek = strWeek.toString();
                        }
                        // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                        start.add(Calendar.DAY_OF_MONTH, 1);
                    } else {
                        break;
                    }
                }
                break;
            case DateFlag.day:
                while (true) {
                    // 测试此end日期是否在start日期之后
                    if (end.compareTo(start)==1) {
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        datas.add(format.format(start.getTime()));
                        // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                        start.add(Calendar.DAY_OF_MONTH, 1);
                    } else {
                        break;
                    }
                }
                break;
        }
        return datas;
    }

    public static String getWeekOfYear(Date date){
        return DateFlag.getWeekOfYear(date, true);
    }
    public static String getWeekOfYear(Date date, boolean haveYear) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);

        calendar.add(Calendar.DAY_OF_MONTH, -7);
        int week_7 = calendar.get(Calendar.WEEK_OF_YEAR);
        int year_7 = calendar.get(Calendar.YEAR);
        if (week > week_7 && year > year_7){
//            // ---跨年了
            week = week;
            year = year_7;
        }

        String tmp = week<10?"0"+String.valueOf(week): String.valueOf(week);
        if(haveYear){
            tmp = String.valueOf(year)+tmp;
        }else{

        }
        return tmp;
    }
    public static Date setEndDate(Date endDate){
        //-------
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DAY_OF_MONTH,1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sdfEnd   = sdf.format(calendar.getTime());

        try {
            endDate   = sdf.parse(sdfEnd);
            calendar.setTime(endDate);
            calendar.add(Calendar.SECOND, -1);
            endDate = calendar.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;

    }
    public static void main(String[] args){
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String demo = "";
        calendar.set(2015,0,1);
        demo = sdf.format(calendar.getTime());
        System.out.println(demo);
        System.out.println(DateFlag.getWeekOfYear(calendar.getTime()));
        calendar.set(2015,0,8);
        demo = sdf.format(calendar.getTime());
        System.out.println(demo);
        System.out.println(DateFlag.getWeekOfYear(calendar.getTime(),true));
        calendar.set(2015,11,28);
        demo = sdf.format(calendar.getTime());
        System.out.println(demo);
        System.out.println(DateFlag.getWeekOfYear(calendar.getTime(),true));
        calendar.set(2016,0,1);
        demo = sdf.format(calendar.getTime());
        System.out.println(demo);
        System.out.println(DateFlag.getWeekOfYear(calendar.getTime(),true));
        calendar.set(2016,0,8);
        demo = sdf.format(calendar.getTime());
        System.out.println(demo);
        System.out.println(DateFlag.getWeekOfYear(calendar.getTime(),true));
        calendar.set(2017,0,1);
        demo = sdf.format(calendar.getTime());
        System.out.println(demo);
        System.out.println(DateFlag.getWeekOfYear(calendar.getTime(),true));
        calendar.set(2017,11,31);
        demo = sdf.format(calendar.getTime());
        System.out.println(demo);
        System.out.println(DateFlag.getWeekOfYear(calendar.getTime(),true));
        calendar.set(2018,0,1);
        demo = sdf.format(calendar.getTime());
        System.out.println(demo);
        System.out.println(DateFlag.getWeekOfYear(calendar.getTime(),true));
        calendar.set(2017  ,0,1 );
        Date d1 = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR,443);
        Date d2 = calendar.getTime();
        int days = DateFlag.getDays("201752", d1, d2);

        Date d3 = DateFlag.getWeekLastDate("201801");

        Date d4 = DateFlag.getWeekLastDate("201802");
        Date d5 = DateFlag.getWeekFirstDate("201801");

        Date d6 = DateFlag.getWeekLastDate("201601");
        Date d7 = DateFlag.getWeekLastDate("201553");
        System.out.println("over");
    }
    public static int getDays(String dateString,Date startDate, Date endDate){
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            startDate = df.parse(df.format(startDate));
            endDate = df.parse(df.format(endDate));
        } catch (Exception ex) {

        }
        int days = 1;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            if (dateString.length() == 4) {
                java.util.Date dt = sdf.parse(dateString+"-01-01");
                start.setTime(dt);
                end.setTime(dt);
                end.add(Calendar.YEAR, 1);
            } else if (dateString.length() == 7) {
                java.util.Date dt = sdf.parse(dateString+"-01");
                start.setTime(dt);
                end.setTime(dt);
                end.add(Calendar.MONTH, 1);
            } else if (dateString.length() == 6) {
                start.setFirstDayOfWeek(Calendar.MONDAY);
                start.setMinimalDaysInFirstWeek(4);
                int year = Integer.valueOf(dateString.substring(0,4));
                int weeks = Integer.valueOf(dateString.substring(4));
                start.setWeekDate(year, weeks, Calendar.MONDAY );
                end.setTime(start.getTime());
                end.add(Calendar.DAY_OF_YEAR, 7 );
            } else {
                return 1;
            }
            if (startDate.getTime() > start.getTime().getTime()){
                start.setTime(startDate);
            }
            if (endDate.getTime() < end.getTime().getTime()){
                end.setTime(endDate);
                end.add(Calendar.DAY_OF_YEAR,1);
            }
            days = (int) ((end.getTimeInMillis()- start.getTimeInMillis()+1)/(1000*3600*24));

        }catch (Exception ex){

        }
        return days;
    }
    public static Date getWeekFirstDate(String yearweek){

        Calendar start = Calendar.getInstance();
        start.setFirstDayOfWeek(Calendar.MONDAY);
        start.setMinimalDaysInFirstWeek(4);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            java.util.Date dt = sdf.parse(yearweek.substring(0,4)+"-01-01");
            start.setTime(dt);
            int year = Integer.valueOf(yearweek.substring(0,4));
            int ydweek = start.get(Calendar.DAY_OF_WEEK);
            int weeks = Integer.valueOf(yearweek.substring(4));
            ydweek = ydweek==1?8:ydweek;
            if (ydweek>5){
                start.add(Calendar.DAY_OF_YEAR, 2- ydweek);
                start.add(Calendar.DAY_OF_YEAR, weeks * 7);
            } else {
                start.add(Calendar.DAY_OF_YEAR, 2 - ydweek);
                start.add(Calendar.DAY_OF_YEAR, (weeks - 1)* 7);
            }
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return start.getTime();
    }
    public static Date getWeekLastDate(String yearweek) {
        Calendar start = Calendar.getInstance();
        Date dtStart = DateFlag.getWeekFirstDate(yearweek);
        start.setTime(dtStart);
        start.add(Calendar.DAY_OF_YEAR, 7);
        return start.getTime();
    }
}
