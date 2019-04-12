
package com.tianjian.cache;

import java.util.Map;

/**
 * 实现缓存系统参数的封装
 * 
 */
public class AppParametersCache {
	/** 
	 * 统计年份
	 */
	public static String STATISTICS_YEAR;
	/** 
	 * 是否审核
	 */
	public static String IS_VERIFY;
	/** 
	 * 审核级别
	 */
	public static String TPCHECKLEVELS;
	/** 
	 * 县id
	 */
	public static String COUNTY_ID;	
	/**
	 * 扶贫办每年开始时间
	 */
	public static String BEGIN_DATE;
	/**
	 * 扶贫办每年结束时间
	 */
	public static String END_DATE;
	/**
	 * 系统参数Map
	 */
	public static Map appParametersCacheMap;
}
