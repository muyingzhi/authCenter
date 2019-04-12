package com.tianjian.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolDateTime {
	public static final String pattern_ym = "yyyy-MM"; // pattern_ym
	 public static final String pattern_ymd = "yyyy-MM-dd"; // pattern_ymd


	 

	public static String format(Date date, String pattern) {
	         DateFormat format = new SimpleDateFormat(pattern);
	         return format.format(date);
	     }

	public static Date parse(String date, String pattern) {
	         SimpleDateFormat format = new SimpleDateFormat(pattern);
	         try {
	             return format.parse(date);
	         } catch (Exception e) {
	             System.out.println("ToolDateTime.parse异常：date值" + date + "，pattern值" + pattern);
	             return null;
	         }
	     }

}
