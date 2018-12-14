package com.classnet.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getDateString(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sf.format(new Date())+(int)(Math.random()*10);
	}
	public static void main(String[] args) {
		System.out.println(getDateString());
	}
}
