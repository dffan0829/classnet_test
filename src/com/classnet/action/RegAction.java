package com.classnet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.classnet.dao.UserDao;
import com.classnet.entity.UserEntity;

public class RegAction extends DispatchAction{

	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public ActionForward toreg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("succ");
	}
	public ActionForward doreg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(username);
		userEntity.setPassword(password);
		userEntity.setEmail(email);
		userEntity.setAuthorite("ROLE_USER");
		userEntity.setEnable(true);
		userDao.save_(userEntity);
		return mapping.findForward("regok");
	} 
	public ActionForward checkuser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String username = request.getParameter("username");
		DetachedCriteria dc = DetachedCriteria.forClass(UserEntity.class);
		dc.add(Restrictions.eq("username", username));
		UserEntity user = userDao.uniqueResult(dc);
		response.setCharacterEncoding("utf-8");
		if(user==null){
			response.getWriter().write("1");
		}
		else{
			response.getWriter().write("2");
		}
		return null;
	}
}
