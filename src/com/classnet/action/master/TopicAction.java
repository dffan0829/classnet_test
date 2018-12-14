package com.classnet.action.master;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.classnet.dao.AnswerDao;
import com.classnet.dao.TopicDao;
import com.classnet.dao.TopicMenuDao;
import com.classnet.dao.UserDao;
import com.classnet.entity.TopicEntity;
import com.classnet.entity.TopicMenuEntity;
import com.classnet.entity.UserEntity;
import com.classnet.util.page.IPagination;
import com.classnet.util.page.SimplePagination;
import com.classnet.util.page.WebUtil;

public class TopicAction extends DispatchAction{

	private TopicDao topicDao;
	private TopicMenuDao topicMenuDao;
	private UserDao userDao;
	private AnswerDao answerDao;
	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setTopicMenuDao(TopicMenuDao topicMenuDao) {
		this.topicMenuDao = topicMenuDao;
	}
	@SuppressWarnings("unchecked")
	public ActionForward addTopic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DetachedCriteria dc = DetachedCriteria.forClass(TopicMenuEntity.class);
		List<TopicMenuEntity> menuList = topicMenuDao.findByExample(dc);
		request.setAttribute("menuList", menuList);
		return mapping.findForward("addtopic");
	}
	public ActionForward doAddTopic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int menuId = WebUtil.getInteger(request, "menuId");
		String title = request.getParameter("title");
		String detail = request.getParameter("content");
		TopicEntity entity = new TopicEntity();
		entity.setDetail(detail);
		entity.setPubtime(new Date());
		entity.setTitle(title);
		UserEntity userEntity = userDao.getUser(WebUtil.getLoginUser());
		entity.setUserEntity(userEntity);
		TopicMenuEntity menuEntity = topicMenuDao.selectById(TopicMenuEntity.class, menuId);
		entity.setMenuEntity(menuEntity);
		topicDao.save_(entity);
		response.sendRedirect(request.getContextPath()+"/master/topic.do?m=list");
		return null;
	}
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int page = WebUtil.getPage(request);
		int pageSize = 10;
		int menuId = WebUtil.getInteger(request, "menuId");
		DetachedCriteria dc = DetachedCriteria.forClass(TopicEntity.class);
		dc.createAlias("userEntity", "user").add(Restrictions.eq("user.username", WebUtil.getLoginUser()));
		if(menuId!=0){
			dc.add(Restrictions.eq("menuEntity.id", menuId));
			request.setAttribute("menuId", menuId);
		}
		IPagination pp = new SimplePagination(dc,Order.desc("id"),true,page,pageSize);
		pp.save(request);
		List<TopicEntity> topicList = pp.getPage();
		request.setAttribute("topicList", topicList);
		
		DetachedCriteria menudc = DetachedCriteria.forClass(TopicMenuEntity.class);
		List<TopicMenuEntity> menuList = topicMenuDao.findByExample(menudc);
		request.setAttribute("menuList", menuList);
		return mapping.findForward("list");
	}
	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtil.getInteger(request, "id");
		TopicEntity entity = topicDao.selectById(TopicEntity.class, id);
		if(entity==null){
			response.sendRedirect(request.getContextPath()+"/master/topic.do?m=list");
			return null;
		}
		request.setAttribute("topicEntity", entity);
		
		DetachedCriteria dc = DetachedCriteria.forClass(TopicMenuEntity.class);
		List<TopicMenuEntity> menuList = topicMenuDao.findByExample(dc);
		request.setAttribute("menuList", menuList);
		return mapping.findForward("addtopic");
	}
	public ActionForward doEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtil.getInteger(request, "topicId");
		int menuId = WebUtil.getInteger(request, "menuId");
		String title = request.getParameter("title");
		String detail = request.getParameter("content");
		TopicEntity entity = topicDao.selectById(TopicEntity.class, id);
		entity.setDetail(detail);
		entity.setTitle(title);
		TopicMenuEntity menuEntity = topicMenuDao.selectById(TopicMenuEntity.class, menuId);
		entity.setMenuEntity(menuEntity);
		topicDao.update_(entity);
		response.sendRedirect(request.getContextPath()+"/master/topic.do?m=list");
		return null;
	}
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtil.getInteger(request, "id");
		TopicEntity entity = topicDao.selectById(TopicEntity.class, id);
		if(entity!=null){
			topicDao.delete_(entity);
			answerDao.delByTopic(id);
		}
		response.sendRedirect(request.getContextPath()+"/master/topic.do?m=list");
		return null;
	}
	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}
}
