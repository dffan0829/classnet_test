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
import com.classnet.dao.SourceDao;
import com.classnet.dao.SourceMenuDao;
import com.classnet.entity.ClazzEntity;
import com.classnet.entity.NewsEntity;
import com.classnet.entity.SourceEntity;
import com.classnet.entity.SourceMenuEntity;

public class SourceIndexAction extends Action{

	private SourceDao sourceDao;
	private SourceMenuDao sourceMenuDao;
	private NewsDao newsDao;
	private ClazzDao clazzDao;
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}
	public void setSourceDao(SourceDao sourceDao) {
		this.sourceDao = sourceDao;
	}
	public void setSourceMenuDao(SourceMenuDao sourceMenuDao) {
		this.sourceMenuDao = sourceMenuDao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DetachedCriteria menudc = DetachedCriteria.forClass(SourceMenuEntity.class);
		List<SourceMenuEntity> menuList = sourceMenuDao.findByExample(menudc);
		if(menuList!=null&&!menuList.isEmpty()){
			for(SourceMenuEntity menu : menuList){
				DetachedCriteria sourcedc = DetachedCriteria.forClass(SourceEntity.class);
				sourcedc.add(Restrictions.eq("sourceMenu.id", menu.getId()));
				sourcedc.addOrder(Order.desc("pubtime"));
				List<SourceEntity> sourceList = sourceDao.findByExample(sourcedc, 10);
				menu.setSourceList(sourceList);
			}
		}
		
		DetachedCriteria hotnewsdc = DetachedCriteria.forClass(NewsEntity.class);
		hotnewsdc.addOrder(Order.desc("viewNum"));
		List<NewsEntity> newsList = newsDao.findByExample(hotnewsdc, 10);
		
		DetachedCriteria clazzdc = DetachedCriteria.forClass(ClazzEntity.class);
		clazzdc.add(Restrictions.eq("status", 2));
		clazzdc.addOrder(Order.desc("viewCount"));
		List<ClazzEntity> clazzList = clazzDao.findByExample(clazzdc, 10);
		
		request.setAttribute("menuList", menuList);
		request.setAttribute("newsList", newsList);
		request.setAttribute("clazzList", clazzList);
		return mapping.findForward("succ");
	}
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
}
