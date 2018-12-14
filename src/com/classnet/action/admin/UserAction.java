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

import com.classnet.dao.UserDao;
import com.classnet.entity.UserEntity;
import com.classnet.util.WebUtils;
import com.classnet.util.page.IPagination;
import com.classnet.util.page.SimplePagination;
import com.classnet.util.page.WebUtil;

public class UserAction extends DispatchAction{

	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String key = request.getParameter("key");
		int page = WebUtil.getPage(request);
		int page_size=10;
		DetachedCriteria dc = DetachedCriteria.forClass(UserEntity.class);
		dc.add(Restrictions.eq("authorite", "ROLE_USER"));
		if(!WebUtils.isEmpty(key)){
			key = new String(key.getBytes("ISO8859-1"),"utf-8");
			dc.add(Restrictions.like("username", key, MatchMode.ANYWHERE));
			request.setAttribute("key", key);
		}
		IPagination pp = new SimplePagination(dc,Order.desc("id"),true,page,page_size);
		pp.save(request);
		List<UserEntity> userList = pp.getPage();
		request.setAttribute("userList", userList);
		return mapping.findForward("list");
	}
	public ActionForward changestatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtil.getInteger(request, "id");
		int status = WebUtil.getInteger(request, "status");
		UserEntity userEntity = userDao.selectById(UserEntity.class, id);
		if(userEntity!=null){
			if(status==1){
				userEntity.setEnable(true);
			}
			else{
				userEntity.setEnable(false);
			}
			userDao.update_(userEntity);
		}
		response.sendRedirect(request.getContextPath()+"/admin/user.do?m=list");
		return null;
	}
}
