package com.classnet.util.page;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

public class WebUtil {

	public static int getPage(HttpServletRequest req) {
		try {
			int page = Integer.parseInt(getParameter(req, "page"));
			if (page < 1)
				page = 1;
			return page;
		} catch (Exception e) {
			return 1;
		}
	}

	public static String getLoginUser() {
		try {
			UserDetails ud = (UserDetails) getLogin();
			if (ud != null){
				return ud.getUsername();
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getLoginCheckUser(){
		String user=WebUtil.getLoginUser();
		if(StringUtils.isEmpty(user))
			Assert.notNull(user,"用户名为空");
		
		return user;
	}

	public static UserDetails getLogin() {
		try {
			UserDetails ud = (UserDetails) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			return ud;
		} catch (Exception e) {
			return null;
		}
	}

	public static String getParameter(HttpServletRequest req, String param) {
		return req.getParameter(param);
	}

	public static String getParameter(HttpServletRequest req, String param,
			String defaultV) {
		String s = getParameter(req, param);
		if (StringUtils.isEmpty(s)) {
			s = defaultV;
		}
		return s;
	}
	
	public static Integer getInteger(HttpServletRequest req,String param){
		try{
			return Integer.parseInt(getParameter(req, param));
		}catch(Exception e){
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public static String getUrl(HttpServletRequest req, boolean page) {
		StringBuffer s = req.getRequestURL();
		Map map = req.getParameterMap();
		StringBuffer sb = new StringBuffer();
		if (map != null && !map.isEmpty()) {
			for (Iterator<Map.Entry<String, Object>> it = map.entrySet()
					.iterator(); it.hasNext();) {
				Map.Entry<String, Object> entry = it.next();
				if (!entry.getKey().equals("page")) {
					sb.append(entry.getKey());
					sb.append("=");
					String param = WebUtil.getParameter(req, entry.getKey());
					try {
						sb.append(new String(param.getBytes("ISO8859-1"),"utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					sb.append("&");
				}
			}
		}
		if (page) {
			sb.append("page=");
		}

		if (sb.length() != 0) {
			s.append("?");
			s.append(sb);
		}
		return s.toString();
	}
	
	public static String getUrl(HttpServletRequest req){
		return req.getRequestURI();
	}
	
	public static String getDomain(HttpServletRequest req){
		return getParameter(req, "title");
	}
	
	public static boolean isAdmin(){
		UserDetails ud=getLogin();
		if(ud!=null){
			boolean is=false;
			for(GrantedAuthority ga:ud.getAuthorities()){
				if(ga.getAuthority().equals("ROLE_SUPERVISOR")){
					is=true;
					break;
				}
			}
			return is;
		}
		return false;
	}
}
