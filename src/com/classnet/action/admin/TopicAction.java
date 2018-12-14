package com.classnet.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.classnet.dao.AnswerDao;
import com.classnet.dao.TopicDao;
import com.classnet.dao.TopicMenuDao;
import com.classnet.entity.TopicEntity;
import com.classnet.entity.TopicMenuEntity;
import com.classnet.util.WebUtils;
import com.classnet.util.page.IPagination;
import com.classnet.util.page.SimplePagination;
import com.classnet.util.page.WebUtil;

public class TopicAction extends DispatchAction{

	private TopicMenuDao topicMenuDao;
	private AnswerDao answerDao;
	private TopicDao topicDao;
	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}
	public void setTopicMenuDao(TopicMenuDao topicMenuDao) {
		this.topicMenuDao = topicMenuDao;
	}
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int page = WebUtil.getPage(request);
		int pageSize=10;
		String key = request.getParameter("key");
		int menuId = WebUtil.getInteger(request, "menuId");
		DetachedCriteria dc = DetachedCriteria.forClass(TopicEntity.class);
		if(menuId!=0){
			dc.add(Restrictions.eq("menuEntity.id", menuId));
			request.setAttribute("menuId", menuId);
		}
		if(!WebUtils.isEmpty(key)){
			key = new String(key.getBytes("ISO8859-1"),"UTF-8");
			dc.add(Restrictions.like("title", key, MatchMode.ANYWHERE));
			request.setAttribute("key", key);
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtil.getInteger(request, "id");
		String ids = request.getParameter("ids");
		if(id!=0){
			TopicEntity entity = topicDao.selectById(TopicEntity.class, id);
			if(entity!=null){
				topicDao.delete_(entity);
				answerDao.delByTopic(id);
			}
		}
		else if(!WebUtils.isEmpty(ids)){
			String [] array = ids.split(",");
			for(String idstr : array){
				int mid = WebUtils.StringToInt(idstr);
				if(mid!=0){
					TopicEntity entity = topicDao.selectById(TopicEntity.class, mid);
					if(entity!=null){
						topicDao.delete_(entity);
						answerDao.delByTopic(mid);
					}
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/admin/topic/topic.do?m=list");
		return null;
	}
	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}
}
