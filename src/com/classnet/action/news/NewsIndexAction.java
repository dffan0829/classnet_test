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

import com.classnet.dao.NewsDao;
import com.classnet.dao.NewsMenuDao;
import com.classnet.entity.NewsEntity;
import com.classnet.entity.NewsMenuEntity;

public class NewsIndexAction extends Action{
	
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

		// DetachedCriteria menudc = DetachedCriteria.forClass(NewsMenuEntity.class);
		List<NewsMenuEntity> menuList = newsMenuDao.findByExample("from NewsMenuEntity");
		if(menuList!=null&&!menuList.isEmpty()){
			for(NewsMenuEntity menu : menuList){
				DetachedCriteria toutiaodc = DetachedCriteria.forClass(NewsEntity.class);
				toutiaodc.add(Restrictions.eq("newsMenu.id", menu.getId()));
				toutiaodc.add(Restrictions.eq("status", 3));
				List<NewsEntity> toutiaoList = newsDao.findByExample(toutiaodc, 1);
				if(toutiaoList!=null&&!toutiaoList.isEmpty()){
					NewsEntity tt = toutiaoList.get(0);
					String content = tt.getContent();
					content = content.replaceAll("&nbsp;", "");
					content = content.replaceAll("<[^>]+>", "");
					content = content.replaceAll("\\s+", "");
					tt.setContent(content);
					menu.setNewsEntity(tt);
				}
				DetachedCriteria menunewsdc = DetachedCriteria.forClass(NewsEntity.class);
				menunewsdc.add(Restrictions.eq("newsMenu.id", menu.getId()));
				menunewsdc.add(Restrictions.eq("status", 1));
				List<NewsEntity> menunewsList = newsDao.findByExample(menunewsdc, 10);
				if(menunewsList!=null&&!menunewsList.isEmpty()){
					menu.setNewsList(menunewsList);
				}
			}
		}
		
		DetachedCriteria newsTJdc = DetachedCriteria.forClass(NewsEntity.class);
		newsTJdc.add(Restrictions.eq("status", 2));
		List<NewsEntity> newsTJList = newsDao.findByExample(newsTJdc, 10);
		
		DetachedCriteria newsBigImgdc = DetachedCriteria.forClass(NewsEntity.class);
		newsBigImgdc.add(Restrictions.eq("status", 4));
		List<NewsEntity> newsBigImgList = newsDao.findByExample(newsBigImgdc);
		
		DetachedCriteria newnewsdc = DetachedCriteria.forClass(NewsEntity.class);
		newnewsdc.addOrder(Order.desc("pubtime"));
		List<NewsEntity> newnewsList = newsDao.findByExample(newnewsdc, 10);
		
		request.setAttribute("menuList", menuList);
		request.setAttribute("newsTJList", newsTJList);
		request.setAttribute("newsBigImgList", newsBigImgList);
		request.setAttribute("newnewsList", newnewsList);
		return mapping.findForward("index");
	}
}