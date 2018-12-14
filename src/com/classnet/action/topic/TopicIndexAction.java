package com.classnet.action.topic;

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

import com.classnet.dao.TopicDao;
import com.classnet.dao.TopicMenuDao;
import com.classnet.entity.TopicEntity;
import com.classnet.entity.TopicMenuEntity;

public class TopicIndexAction extends Action{

	private TopicMenuDao topicMenuDao;
	private TopicDao topicDao;
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DetachedCriteria menudc = DetachedCriteria.forClass(TopicMenuEntity.class);
		List<TopicMenuEntity> menuList = topicMenuDao.findByExample(menudc);
		if(menuList!=null){
			for(TopicMenuEntity menu : menuList){
				DetachedCriteria dc = DetachedCriteria.forClass(TopicEntity.class);
				dc.add(Restrictions.eq("menuEntity.id", menu.getId()));
				dc.addOrder(Order.desc("id"));
				menu.setTopicList(topicDao.findByExample(dc, 10));
			}
		}
		request.setAttribute("menuList", menuList);
		return mapping.findForward("succ");
	}
	public void setTopicMenuDao(TopicMenuDao topicMenuDao) {
		this.topicMenuDao = topicMenuDao;
	}
	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}
}
