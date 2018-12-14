package com.classnet.action.topic;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.classnet.dao.AnswerDao;
import com.classnet.dao.TopicDao;
import com.classnet.dao.UserDao;
import com.classnet.entity.AnswerEntity;
import com.classnet.entity.TopicEntity;
import com.classnet.entity.UserEntity;
import com.classnet.util.page.WebUtil;

public class ReplyAction extends Action{

	private TopicDao topicDao;
	private AnswerDao answerDao;
	private UserDao userDao;
	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}
	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		int topicId = WebUtil.getInteger(request, "topicId");
		String content = request.getParameter("content");
		AnswerEntity entity = new AnswerEntity();
		entity.setContent(content);
		entity.setPubtime(new Date());
		TopicEntity topicEntity = topicDao.selectById(TopicEntity.class, topicId);
		entity.setTopicEntity(topicEntity);
		UserEntity userEntity = userDao.getUser(WebUtil.getLoginUser());
		entity.setUserEntity(userEntity);
		entity.setStatus(1);
		answerDao.save_(entity);
		int num = topicEntity.getReplyNum()==null?0:topicEntity.getReplyNum();
		topicEntity.setReplyNum(num+1);
		topicEntity.setEditTime(new Date());
		topicEntity.setEditUser(WebUtil.getLoginUser());
		topicDao.update_(topicEntity);
		response.sendRedirect(request.getContextPath()+"/topic/topic.do?id="+topicId);
		return null;
	}
}
