package com.classnet.action.news;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.classnet.dao.ClazzDao;
import com.classnet.dao.NewsDao;
import com.classnet.dao.NewsMenuDao;
import com.classnet.entity.ClazzEntity;
import com.classnet.entity.NewsEntity;
import com.classnet.entity.NewsMenuEntity;
import com.classnet.util.WebUtils;
import com.classnet.util.page.IPagination;
import com.classnet.util.page.SimplePagination;
import com.classnet.util.page.WebUtil;

public class NewsMenuAction extends Action{

	private NewsDao newsDao;
	private NewsMenuDao newsMenuDao;
	private ClazzDao clazzDao;
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}
	public void setNewsMenuDao(NewsMenuDao newsMenuDao) {
		this.newsMenuDao = newsMenuDao;
	}
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		int id = WebUtils.StringToInt(request.getParameter("id"));
		int page = WebUtil.getPage(request);
		int page_size = 20;
		DetachedCriteria dc = DetachedCriteria.forClass(NewsEntity.class);
		dc.add(Restrictions.eq("newsMenu.id", id));
		IPagination pp = new SimplePagination(dc,Order.desc("pubtime"),true,page,page_size);
		pp.save(request);
		List<NewsEntity> newsList = pp.getPage();
		
		DetachedCriteria hotnewsdc = DetachedCriteria.forClass(NewsEntity.class);
		hotnewsdc.addOrder(Order.desc("viewNum"));
		List<NewsEntity> hotNewsList = newsDao.findByExample(hotnewsdc, 10);
		
		DetachedCriteria tjnews = DetachedCriteria.forClass(NewsEntity.class);
		tjnews.add(Restrictions.eq("status", 2));
		tjnews.addOrder(Order.desc("viewNum"));
		List<NewsEntity> tjnewsList = newsDao.findByExample(tjnews, 10);
		
		//DetachedCriteria menudc = DetachedCriteria.forClass(NewsMenuEntity.class);
		List<NewsMenuEntity> menuList = newsMenuDao.findByExample("from NewsMenuEntity");
		
		NewsMenuEntity menuEntity = newsMenuDao.selectById(NewsMenuEntity.class, id);
		request.setAttribute("menuEntity", menuEntity);
		request.setAttribute("newsList", newsList);
		request.setAttribute("hotNewsList", hotNewsList);
		request.setAttribute("tjnews", tjnewsList);
		request.setAttribute("menuList", menuList);
		return mapping.findForward("succ");
	}
}