package com.classnet.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.criterion.DetachedCriteria;

import com.classnet.dao.TopicMenuDao;
import com.classnet.entity.TopicMenuEntity;
import com.classnet.util.WebUtils;

public class TopicMenuAction extends DispatchAction{

	private TopicMenuDao topicMenuDao;
	public void setTopicMenuDao(TopicMenuDao topicMenuDao) {
		this.topicMenuDao = topicMenuDao;
	}
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DetachedCriteria dc = DetachedCriteria.forClass(TopicMenuEntity.class);
		List<TopicMenuEntity> menuList = topicMenuDao.findByExample(dc);
		request.setAttribute("menuList", menuList);
		return mapping.findForward("list");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String name = request.getParameter("name");
		TopicMenuEntity menu = new TopicMenuEntity();
		menu.setName(name);
		topicMenuDao.save_(menu);
		response.sendRedirect(request.getContextPath()+"/admin/topic/topicMenu.do?m=list");
		return null;
	}
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		
		int id = WebUtils.StringToInt(request.getParameter("id"));
		String name = request.getParameter("name");
		if(id!=0){
			TopicMenuEntity entity = topicMenuDao.selectById(TopicMenuEntity.class, id);
			if(entity!=null){
				entity.setName(name);
				topicMenuDao.update_(entity);
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
			TopicMenuEntity entity = topicMenuDao.selectById(TopicMenuEntity.class, id);
			if(entity!=null)
				topicMenuDao.delete_(entity);
		}
		else if(!WebUtils.isEmpty(ids)){
			String [] array = ids.split(",");
			for(String idstr : array){
				int mid = WebUtils.StringToInt(idstr);
				if(mid!=0){
					TopicMenuEntity entity = topicMenuDao.selectById(TopicMenuEntity.class, mid);
					if(entity!=null)
						topicMenuDao.delete_(entity);
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/admin/topic/topicMenu.do?m=list");
		return null;
	}
}
