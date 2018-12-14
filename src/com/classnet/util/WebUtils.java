package com.classnet.util;

public class WebUtils {

	public static boolean isEmpty(String str){
		return str==null||"".equals(str);
	}
	
	public static Integer StringToInt(String str){
		try{
			return Integer.parseInt(str);
		}catch(Exception e){
			return 0;
		}
	}
}
