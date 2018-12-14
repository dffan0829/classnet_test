package com.classnet.util.page;

import javax.servlet.http.HttpServletRequest;

public class PageUtil {

	private static String key="PageInfo";
	public static void save(HttpServletRequest req,int total,int page,int page_size,String url){
		PageInfo pi=new PageInfo(url,total,page,page_size);
		req.setAttribute(key, pi);
	}
}
