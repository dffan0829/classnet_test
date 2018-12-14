package com.classnet.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;

import com.classnet.dao.NewsDao;
import com.classnet.dao.NewsMenuDao;
import com.classnet.entity.NewsEntity;
import com.classnet.entity.NewsMenuEntity;
import com.classnet.form.NewsForm;
import com.classnet.util.DateUtil;
import com.classnet.util.WebUtils;
import com.classnet.util.page.IPagination;
import com.classnet.util.page.SimplePagination;
import com.classnet.util.page.WebUtil;
import com.classnet.util.upload.UploadFileImpl;

public class NewsAction extends DispatchAction{

	private String path;
	private String type;
	private Integer filesize=1024*1024;
	private NewsDao newsDao;
	private NewsMenuDao newsMenuDao;
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	public void setNewsMenuDao(NewsMenuDao newsMenuDao) {
		this.newsMenuDao = newsMenuDao;
	}
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String key = request.getParameter("key");
		int menuId = WebUtils.StringToInt(request.getParameter("menuId"));
		int page = WebUtil.getPage(request);
		int page_size = 10;
		DetachedCriteria dc = DetachedCriteria.forClass(NewsEntity.class);
		if(!WebUtils.isEmpty(key)){
			key = new String(key.getBytes("ISO8859-1"),"utf-8");
			dc.add(Restrictions.like("title", key, MatchMode.ANYWHERE));
			request.setAttribute("key", key);
		}
		if(menuId!=0){
			dc.add(Restrictions.eq("newsMenu.id", menuId));
			request.setAttribute("menuId", menuId);
		}
		IPagination pp = new SimplePagination(dc,Order.desc("pubtime"),true,page,page_size);
		pp.save(request);
		List<NewsEntity> newsList = pp.getPage();
		request.setAttribute("newsList", newsList);
		//DetachedCriteria menudc = DetachedCriteria.forClass(NewsMenuEntity.class);
		List<NewsMenuEntity> menuList = newsMenuDao.findByExample("from NewsMenuEntity");
		request.setAttribute("menuList", menuList);
		return mapping.findForward("list");
	}
	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		int id = WebUtils.StringToInt(request.getParameter("id"));
		NewsEntity newsEntity = newsDao.selectById(NewsEntity.class, id);
		// DetachedCriteria dc = DetachedCriteria.forClass(NewsMenuEntity.class);
		List<NewsMenuEntity> menuList = newsMenuDao.findByExample("from NewsMenuEntity");
		request.setAttribute("menuList", menuList);
		request.setAttribute("newsEntity", newsEntity);
		return mapping.findForward("edit");
	}
	public ActionForward doedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtils.StringToInt(request.getParameter("id"));
		NewsEntity entity = newsDao.selectById(NewsEntity.class, id);
		if(entity!=null){
			NewsForm newsForm = (NewsForm)form;
			BeanUtils.copyProperties(newsForm, entity);
			FormFile file = newsForm.getImgFile();
			if(file!=null&&file.getFileSize()>0){
				UploadFileImpl uploadFile = new UploadFileImpl(path+"/images",filesize,type,file);
				uploadFile.save(DateUtil.getDateString());
				entity.setImg(uploadFile.getUploadFileName());
			}
			NewsMenuEntity newsMenu = newsMenuDao.selectById(NewsMenuEntity.class, newsForm.getMenuId());
			if(newsMenu!=null){
				entity.setNewsMenu(newsMenu);
				newsDao.update_(entity);
			}
		}
		response.sendRedirect(request.getContextPath()+"/admin/news/news.do?m=list");
		return null;
	}
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtils.StringToInt(request.getParameter("id"));
		String ids = request.getParameter("ids");
		if(id!=0){
			NewsEntity entity = newsDao.selectById(NewsEntity.class, id);
			if(entity!=null){
				newsDao.delete_(entity);
			}
		}
		else if(!WebUtils.isEmpty(ids)){
			String [] array = ids.split(",");
			for(String idstr : array){
				int mid = WebUtils.StringToInt(idstr);
				if(mid!=0){
					NewsEntity entity = newsDao.selectById(NewsEntity.class, mid);
					if(entity!=null)
						newsDao.delete_(entity);
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/admin/news/news.do?m=list");
		return null;
	}
	public ActionForward changestatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtils.StringToInt(request.getParameter("id"));
		int status = WebUtils.StringToInt(request.getParameter("status"));
		NewsEntity news = newsDao.selectById(NewsEntity.class, id);
		if(news!=null){
			news.setStatus(status);
			newsDao.update_(news);
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("1");
		}
		return null;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setType(String type) {
		this.type = type;
	}
}
