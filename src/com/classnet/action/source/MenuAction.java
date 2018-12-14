package com.classnet.action.source;

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
import com.classnet.dao.SourceMenuDao;
import com.classnet.entity.ClazzEntity;
import com.classnet.entity.NewsEntity;
import com.classnet.entity.SourceEntity;
import com.classnet.entity.SourceMenuEntity;
import com.classnet.util.page.IPagination;
import com.classnet.util.page.SimplePagination;
import com.classnet.util.page.WebUtil;

public class MenuAction extends Action{

	private SourceMenuDao sourceMenuDao;
	private NewsDao newsDao;
	private ClazzDao clazzDao;
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}
	public void setSourceMenuDao(SourceMenuDao sourceMenuDao) {
		this.sourceMenuDao = sourceMenuDao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int page = WebUtil.getPage(request);
		int page_size = 20;
		int id = WebUtil.getInteger(request, "id");
		SourceMenuEntity sourceMenu = sourceMenuDao.selectById(SourceMenuEntity.class, id);
		if(sourceMenu==null){
			response.sendRedirect(request.getContextPath());
			return null;
		}
		DetachedCriteria dc = DetachedCriteria.forClass(SourceEntity.class);
		dc.add(Restrictions.eq("sourceMenu.id", id));
		dc.addOrder(Order.desc("pubtime"));
		IPagination pp = new SimplePagination(dc,Order.desc("pubtime"),true,page,page_size);
		pp.save(request);
		List<SourceEntity> sourceList = pp.getPage();
		
		DetachedCriteria menudc = DetachedCriteria.forClass(SourceMenuEntity.class);
		List<SourceMenuEntity> menuList = sourceMenuDao.findByExample(menudc);
		
		DetachedCriteria hotnewsdc = DetachedCriteria.forClass(NewsEntity.class);
		hotnewsdc.addOrder(Order.desc("viewNum"));
		List<NewsEntity> newsList = newsDao.findByExample(hotnewsdc, 10);
		
		DetachedCriteria clazzdc = DetachedCriteria.forClass(ClazzEntity.class);
		clazzdc.add(Restrictions.eq("status", 2));
		clazzdc.addOrder(Order.desc("viewCount"));
		List<ClazzEntity> clazzList = clazzDao.findByExample(clazzdc, 10);
		
		request.setAttribute("sourceMenu", sourceMenu);
		request.setAttribute("sourceList", sourceList);
		request.setAttribute("menuList", menuList);
		request.setAttribute("newsList", newsList);
		request.setAttribute("clazzList", clazzList);
		return mapping.findForward("succ");
	}
}
