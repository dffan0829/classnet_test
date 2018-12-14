package com.classnet.action;

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
import com.classnet.dao.ClazzMenuDao;
import com.classnet.dao.NewsDao;
import com.classnet.dao.SourceDao;
import com.classnet.dao.SourceMenuDao;
import com.classnet.dao.TopicDao;
import com.classnet.dao.TopicMenuDao;
import com.classnet.entity.ClazzEntity;
import com.classnet.entity.ClazzMenuEntity;
import com.classnet.entity.NewsEntity;
import com.classnet.entity.SourceEntity;
import com.classnet.entity.SourceMenuEntity;
import com.classnet.entity.TopicEntity;
import com.classnet.entity.TopicMenuEntity;

public class IndexAction extends Action{

	private NewsDao newsDao;
	private ClazzDao clazzDao;
	private SourceDao sourceDao;
	private ClazzMenuDao clazzMenuDao;
	private SourceMenuDao sourceMenuDao;
	private TopicDao topicDao;
	private TopicMenuDao topicMenuDao;
	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}
	public void setTopicMenuDao(TopicMenuDao topicMenuDao) {
		this.topicMenuDao = topicMenuDao;
	}
	public void setSourceMenuDao(SourceMenuDao sourceMenuDao) {
		this.sourceMenuDao = sourceMenuDao;
	}
	public void setClazzMenuDao(ClazzMenuDao clazzMenuDao) {
		this.clazzMenuDao = clazzMenuDao;
	}
	public void setSourceDao(SourceDao sourceDao) {
		this.sourceDao = sourceDao;
	}
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		DetachedCriteria newsTJdc = DetachedCriteria.forClass(NewsEntity.class);
		newsTJdc.add(Restrictions.eq("status", 2));
		List<NewsEntity> newsTJList = newsDao.findByExample(newsTJdc, 10);
		
		DetachedCriteria newsBigImgdc = DetachedCriteria.forClass(NewsEntity.class);
		newsBigImgdc.add(Restrictions.eq("status", 4));
		List<NewsEntity> newsBigImgList = newsDao.findByExample(newsBigImgdc);
		
		DetachedCriteria newclazzdc = DetachedCriteria.forClass(ClazzEntity.class);
		newclazzdc.addOrder(Order.desc("pubtime"));
		List<ClazzEntity> newClazzList = clazzDao.findByExample(newclazzdc,13);
		
		DetachedCriteria clazzmenudc = DetachedCriteria.forClass(ClazzMenuEntity.class);
		clazzmenudc.add(Restrictions.isNull("parentMenuEntity"));
		List<ClazzMenuEntity> clazzMenuList = clazzMenuDao.findByExample(clazzmenudc);
		
		DetachedCriteria clazztjdc = DetachedCriteria.forClass(ClazzEntity.class);
		clazztjdc.add(Restrictions.eq("status", 2));
		List<ClazzEntity> tjClazzList = clazzDao.findByExample(clazztjdc,4);
		
		DetachedCriteria sourcedc = DetachedCriteria.forClass(SourceEntity.class);
		sourcedc.addOrder(Order.desc("pubtime"));
		List<SourceEntity> sourceList = sourceDao.findByExample(sourcedc, 16);
		
		DetachedCriteria sourcemenudc = DetachedCriteria.forClass(SourceMenuEntity.class);
		List<SourceMenuEntity> sourceMenuList = sourceMenuDao.findByExample(sourcemenudc);
		
		DetachedCriteria topicdc = DetachedCriteria.forClass(TopicEntity.class);
		topicdc.addOrder(Order.desc("id"));
		List<TopicEntity> topicList	= topicDao.findByExample(topicdc, 16);
		
		DetachedCriteria topicmenudc = DetachedCriteria.forClass(TopicMenuEntity.class);
		List<TopicMenuEntity> topicMenuList = topicMenuDao.findByExample(topicmenudc);
		
		request.setAttribute("newsTJList", newsTJList);
		request.setAttribute("newClazzList", newClazzList);
		request.setAttribute("clazzMenuList", clazzMenuList);
		request.setAttribute("tjClazzList", tjClazzList);
		request.setAttribute("sourceList", sourceList);
		request.setAttribute("sourceMenuList", sourceMenuList);
		request.setAttribute("newsBigImgList", newsBigImgList);
		request.setAttribute("topicList", topicList);
		request.setAttribute("topicMenuList", topicMenuList);
		return mapping.findForward("succ");
	}
}
