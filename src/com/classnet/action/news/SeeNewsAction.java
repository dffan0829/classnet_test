package com.classnet.action.news;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.criterion.DetachedCriteria;

import com.classnet.dao.NewsDao;
import com.classnet.dao.NewsMenuDao;
import com.classnet.entity.NewsEntity;
import com.classnet.entity.NewsMenuEntity;
import com.classnet.util.WebUtils;

public class SeeNewsAction extends Action{

	private NewsDao newsDao;
	private NewsMenuDao newsMenuDao;
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	public void setNewsMenuDao(NewsMenuDao newsMenuDao) {
		this.newsMenuDao = newsMenuDao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtils.StringToInt(request.getParameter("id"));
		NewsEntity newsEntity = newsDao.selectById(NewsEntity.class, id);
		DetachedCriteria menudc = DetachedCriteria.forClass(NewsMenuEntity.class);
		List<NewsMenuEntity> menuList = newsMenuDao.findByExample("from NewsMenuEntity");
		request.setAttribute("newsEntity", newsEntity);
		request.setAttribute("menuList", menuList);
		return mapping.findForward("succ");
	}
}
