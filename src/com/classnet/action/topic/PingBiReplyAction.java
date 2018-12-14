package com.classnet.action.topic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.classnet.dao.AnswerDao;
import com.classnet.entity.AnswerEntity;
import com.classnet.util.page.WebUtil;

public class PingBiReplyAction extends Action{

	private AnswerDao answerDao;
	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		int id = WebUtil.getInteger(request, "id");
		if(WebUtil.isAdmin()){
			AnswerEntity entity = answerDao.selectById(AnswerEntity.class, id);
			if(entity!=null){
				entity.setStatus(2);
				answerDao.update_(entity);
				response.getWriter().write("1");
			}
		}
		return null;
	}
}
