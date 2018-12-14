package com.classnet.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.criterion.DetachedCriteria;

import com.classnet.dao.NewsMenuDao;
import com.classnet.entity.NewsMenuEntity;
import com.classnet.util.WebUtils;

public class NewsMenuAction extends DispatchAction{
	
	private NewsMenuDao newsMenuDao;
	public void setNewsMenuDao(NewsMenuDao newsMenuDao) {
		this.newsMenuDao = newsMenuDao;
	}
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//DetachedCriteria dc = DetachedCriteria.forClass(NewsMenuEntity.class);
		//List<NewsMenuEntity> menuList = newsMenuDao.findByExample(dc);
		List<NewsMenuEntity> menuList = newsMenuDao.findByExample("from NewsMenuEntity");
		request.setAttribute("menuList", menuList);
		return mapping.findForward("list");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String name = request.getParameter("name");
		NewsMenuEntity menu = new NewsMenuEntity();
		menu.setName(name);
		newsMenuDao.save_(menu);
		response.sendRedirect(request.getContextPath()+"/admin/news/newsMenu.do?m=list");
		return null;
	}
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		
		int id = WebUtils.StringToInt(request.getParameter("id"));
		String name = request.getParameter("name");
		if(id!=0){
			NewsMenuEntity entity = newsMenuDao.selectById(NewsMenuEntity.class, id);
			if(entity!=null){
				entity.setName(name);
				newsMenuDao.update_(entity);
				response.getWriter().write("1");
			}
		}
		return null;
	}
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtils.StringToInt(request.getParameter("id"));
		String ids = request.getParameter("ids");
		if(id!=0){
			NewsMenuEntity entity = newsMenuDao.selectById(NewsMenuEntity.class, id);
			if(entity!=null)
				newsMenuDao.delete_(entity);
		}
		else if(!WebUtils.isEmpty(ids)){
			String [] array = ids.split(",");
			for(String idstr : array){
				int mid = WebUtils.StringToInt(idstr);
				if(mid!=0){
					NewsMenuEntity entity = newsMenuDao.selectById(NewsMenuEntity.class, mid);
					if(entity!=null)
						newsMenuDao.delete_(entity);
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/admin/news/newsMenu.do?m=list");
		return null;
	}
}
