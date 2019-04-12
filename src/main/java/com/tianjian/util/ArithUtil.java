package com.tianjian.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
* 目标：封装加减乘除操作的自定义工具类
* 原因：float ,double 只能用来做科学计算或者工程计算，但在商业计算中要用java.math.BigDecimal

* @version 1.0
* @since NC5.7
*/
public class ArithUtil {
	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;
	
	private static final int TWO_DIV_SCALE = 2;

	// 不能实例化
	private ArithUtil() {
	}

	/**
	 * 说明：
	 * 提供精确的加法运算
	 * 创建日期: 2013-9-28
	 * 
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));// 建议写string类型的参数，下同
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 说明：
	 * 提供精确的加法运算
	 * 创建日期: 2013-9-28
	 * 
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */
	public static double add(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);// 建议写string类型的参数，下同
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).doubleValue();
	}

	/**
	 * 说明：
	 * 提供精确的减法运算
	 * 创建日期: 2013-9-28
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * 说明：
	 * 提供精确的减法运算
	 * 创建日期: 2013-9-28
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double sub(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);// 建议写string类型的参数，下同
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 说明：
	 * 提供精确的乘法运算 
	 * 创建日期: 2013-9-28
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	
	/**
	 * 说明：
	 * 提供精确的乘法运算 
	 * 创建日期: 2013-9-28
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double mul(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);// 建议写string类型的参数，下同
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 说明：
	 * 提供相对精确的除法运算，当发生除不尽的情况，精确到.后10位
	 * 创建人: 李林君 邮箱 
	 * 创建日期: 2013-9-28
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double divTen(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}
	
	/**
	 * 说明：
	 * 提供相对精确的除法运算，当发生除不尽的情况，精确到.后10位
	 * 创建人: 李林君 邮箱 
	 * 创建日期: 2013-9-28
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double divTwo(String v1, String v2) {
		return div(v1, v2, TWO_DIV_SCALE);
	}

	/**
	 * 说明：
	 * 创建日期: 2013-9-28
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 * @return
	 */
	private static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(" the scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();// scale 后的四舍五入
	}
	
	/**
	 * 说明：
	 * 创建日期: 2013-9-28
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 * @return
	 */
	private static double div(String v1, String v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(" the scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();// scale 后的四舍五入
	}
	
	//方法一：用JAVA自带的函数
	public static boolean isNumeric(String str){
	    for (int i = str.length();--i>=0;){  
	        if (!Character.isDigit(str.charAt(i))){
	            return false;
	        }
	    }
	    return true;
	}

	/*方法二：推荐，速度最快
	  * 判断是否为整数 
	  * @param str 传入的字符串 
	  * @return 是整数返回true,否则返回false 
	*/
	public static boolean isInteger(String str) {  
	        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	        return pattern.matcher(str).matches();  
	}

}

