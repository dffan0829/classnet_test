package com.classnet.util.page;

import javax.servlet.http.HttpServletRequest;

import org.springframework.orm.hibernate3.HibernateTemplate;

public abstract class AbstractPageination implements IPagination {

	HibernateTemplate ht;
	protected int page=1;
	protected int page_size=20;
	
	protected AbstractPageination(int page,int page_size) {
		super();
		this.page=page;
		this.page_size=page_size;
		// TODO Auto-generated constructor stub
		ht=ApplicationContextConfig.getBean(HibernateTemplate.class, "hibernateTemplate");
	}

	public void save(HttpServletRequest req, String url, String key) {
		PageInfo pi=new PageInfo(url,size(),page,page_size);
		req.setAttribute(key, pi);
	}

	public void save(HttpServletRequest req, String url) {
		save(req,url,PAGE_REQUEST_KEY);
	}

	public void save(HttpServletRequest req) {
		save(req,WebUtil.getUrl(req,true));
	}

	
	
}
