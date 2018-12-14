package com.classnet.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.classnet.dao.ClazzDao;
import com.classnet.dao.NewsDao;
import com.classnet.dao.SourceDao;
import com.classnet.dao.TopicDao;

public class IndexAction extends Action{

	private NewsDao newsDao;
	private ClazzDao clazzDao;
	private SourceDao sourceDao;
	private TopicDao topicDao;
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}
	public void setSourceDao(SourceDao sourceDao) {
		this.sourceDao = sourceDao;
	}
	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String m = request.getParameter("m");
		if(StringUtils.isEmpty(m)){
			m = "index";
		}
		request.setAttribute("m", m);
		request.setAttribute("newsnum", newsDao.findCount());
		request.setAttribute("clazznum", clazzDao.findCount());
		request.setAttribute("sourcenum", sourceDao.findCount());
		request.setAttribute("topicnum", topicDao.findCount());
		return mapping.findForward("index");
	}
}
