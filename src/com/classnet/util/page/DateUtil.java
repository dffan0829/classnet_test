package com.classnet.util.page;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtil {

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_FILENAME="yyyyMMddHHmmssSSS";

	public static String dateToStr(Date date, String f) {
		if (date == null)
			return null;

		SimpleDateFormat sf = new SimpleDateFormat(f);
		return sf.format(date);
	}
	
	public static String dateToStr(){
		return dateToStr(new Date(),DATE_FORMAT_FILENAME);
	}

	public static String dateToStr(Date date) {
		return dateToStr(date, DATE_FORMAT);
	}

	public static Date strToDate(String str, String f) {
		if(StringUtils.isEmpty(str))
			return null;
		
		try {
			SimpleDateFormat sf = new SimpleDateFormat(f);
			return sf.parse(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Date getFirstDayByMonth(Date date){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return strToDate(dateToStr(c.getTime()),"yyyy-MM-dd 00:00:00");
	}
	
	public static Date getLastDayByMonth(Date date){
		Date c=getDate(date, 1, "yyyy-MM-1");
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(c);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	public static Date strToDate(String str){
		return strToDate(str,DATE_FORMAT);
	}
	
	public static String spacingNow(Date lastDate){
		Date date=new Date();
		if(date.after(lastDate)){
			return null;
		}
		
		long spacing=(lastDate.getTime()-date.getTime())/1000;
		StringBuffer sb=new StringBuffer();
		if(spacing>day_){
			long day=spacing/day_;
			sb.append(day);
			sb.append("��");
			spacing-=day_*day;
		}
		if(spacing>hours_){
			long hours=spacing/hours_;
			sb.append(hours);
			sb.append("ʱ");
			spacing-=hours*hours_;
		}
		if(spacing>60){
			sb.append(spacing/60);
			sb.append("��");
		}
		return sb.toString();
	}
	
	public static Date getDate(Date d,int month,String f){
		Calendar c=Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)+month);
		Date newday=c.getTime();
		String strDate=dateToStr(newday,f);
		return strToDate(strDate,f);
	}
	
	private static final long day_=60*60*24;
	private static final long hours_=60*60;
	
	public static void main(String[] args){
		System.out.println(getDate(new Date(),5,"yyyy-MM-dd"));
	}
	public static Date getDate(int year, int month, int date){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DATE, date);
		return c.getTime();
	}
}
