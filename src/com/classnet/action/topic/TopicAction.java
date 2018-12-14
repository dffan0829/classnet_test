package com.classnet.action.topic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.classnet.dao.AnswerDao;
import com.classnet.dao.TopicDao;
import com.classnet.entity.AnswerEntity;
import com.classnet.entity.TopicEntity;
import com.classnet.util.page.WebUtil;

public class TopicAction extends Action{

	private TopicDao topicDao;
	private AnswerDao answerDao;
	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}
	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		int id = WebUtil.getInteger(request, "id");
		TopicEntity entity = topicDao.selectById(TopicEntity.class, id);
		if(entity!=null){
			DetachedCriteria dc = DetachedCriteria.forClass(AnswerEntity.class);
			dc.add(Restrictions.eq("topicEntity.id", id));
			entity.setAnswerList(answerDao.findByExample(dc));
		}
		request.setAttribute("loginUser", WebUtil.getLoginUser());
		request.setAttribute("isAdmin", WebUtil.isAdmin());
		request.setAttribute("topicEntity", entity);
		return mapping.findForward("topic");
	}
}
