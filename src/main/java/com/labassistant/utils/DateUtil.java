package com.labassistant.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.labassistant.exception.MyRuntimeException;

/**
 * 日期工具类
 * @author zql
 * @date 2015/09/10
 */
public final class DateUtil {

	private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";  //默认的日期格式
	private static final long d = 24 * 60 * 60 * 1000;  //一天的毫秒数
	//private static final long h = 60 * 60 * 1000; //一小时的毫秒数
	//private static final long m = 60 * 1000; //一分钟的毫秒数
	//private static final long s = 1000; //一秒的毫秒数 只是用于测试
	
	// 不支持实例化
	private DateUtil(){}

	/**
	 * 格式化日期
	 * @param format
	 * @param date
	 * @return
	 */
	public static String formatDate(String format, Date date){
		return new SimpleDateFormat(format).format(date);
	}
	
	public static String formatDate(Date date){
		return formatDate(dateFormat, date);
	}
	
	public static int getYear(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}
	
	public static int getMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}
	/**
	 * 将字符串转换成日期类型
	 * @param date
	 * @return
	 */
	public static Date str2Date(String format, String date){
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			throw new MyRuntimeException(date + " can not convert from string to date");
		}
	}
	
	public static Date str2Date(String date){
		return str2Date(dateFormat, date);
	}
	
	/**
	 * 计算日期相差值,仅需保留到天
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String diff(Date startTime, Date endTime){
		// 忽略终止日期小于起始日期的影响
		long diff = Math.abs(endTime.getTime() - startTime.getTime());
		long day = diff / d;
		return day + "天";
		
		//long hour = diff % d / h;
		//long min = diff % d % h / m;
		//long sec = diff % d % h % m / s;  // 只是用于测试
		//long mm = diff % d % h % m % s;   // 只是用于测试
		//return day + "天" + hour + "小时" + min + "分钟";
		//return day + "天" + hour + "小时" + min + "分钟" + sec + "秒" + mm + "毫秒";
	}
	
	// test
	public static void main(String[] args){
		Date start = new Date();
		System.out.println(formatDate(start));
		for(int i = -1000000000; i < 2000000000; i++);
		Date end = new Date();
		System.out.println(formatDate(end));
		System.out.println(diff(start, end));
		System.out.println(getYear(str2Date("yyyyMMdd","20140922")));
		
		System.out.println(new Date().getTime());
		String name = "1446704449263.pdf".replace(".pdf", "");
		System.out.println(name);
		System.out.println(formatDate("yyyy.MM.dd HH:mm:ss", new Date(Long.parseLong(name))));
	}
}
