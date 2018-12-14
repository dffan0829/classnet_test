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

import com.classnet.dao.TopicMenuDao;
import com.classnet.entity.TopicEntity;
import com.classnet.entity.TopicMenuEntity;
import com.classnet.util.page.IPagination;
import com.classnet.util.page.SimplePagination;
import com.classnet.util.page.WebUtil;

public class MenuAction extends Action{

	private TopicMenuDao topicMenuDao;
	public void setTopicMenuDao(TopicMenuDao topicMenuDao) {
		this.topicMenuDao = topicMenuDao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		int page = WebUtil.getPage(request);
		int pageSize = 50;
		int id = WebUtil.getInteger(request, "id");
		TopicMenuEntity menuEntity = topicMenuDao.selectById(TopicMenuEntity.class, id);
		
		DetachedCriteria dc = DetachedCriteria.forClass(TopicEntity.class);
		dc.add(Restrictions.eq("menuEntity.id", id));
		IPagination pp = new SimplePagination(dc,Order.desc("id"),true,page,pageSize);
		pp.save(request);
		List<TopicEntity> topicList = pp.getPage();
		
		DetachedCriteria menudc = DetachedCriteria.forClass(TopicMenuEntity.class);
		List<TopicMenuEntity> menuList = topicMenuDao.findByExample(menudc);
		
		request.setAttribute("menuList", menuList);
		request.setAttribute("topicList", topicList);
		request.setAttribute("menuEntity", menuEntity);
		return mapping.findForward("topicmenu");
	}
}
