package com.classnet.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.classnet.dao.ClazzMenuDao;
import com.classnet.entity.ClazzMenuEntity;
import com.classnet.util.WebUtils;

public class ClazzMenuAction extends DispatchAction{

	private ClazzMenuDao clazzMenuDao;
	public void setClazzMenuDao(ClazzMenuDao clazzMenuDao) {
		this.clazzMenuDao = clazzMenuDao;
	}
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int parentId = WebUtils.StringToInt(request.getParameter("pid")); 
		DetachedCriteria topdc = DetachedCriteria.forClass(ClazzMenuEntity.class);
		topdc.add(Restrictions.isNull("parentMenuEntity"));
		List<ClazzMenuEntity> topMenuList = clazzMenuDao.findByExample(topdc);
		List<ClazzMenuEntity> menuList = null;
		if(parentId==0){
			menuList = topMenuList;
		}
		else{
			DetachedCriteria dc = DetachedCriteria.forClass(ClazzMenuEntity.class);
			dc.add(Restrictions.eq("parentMenuEntity.id", parentId));
			menuList = clazzMenuDao.findByExample(dc);
		}
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("menuList", menuList);
		return mapping.findForward("list");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String name = request.getParameter("name");
		int pid = WebUtils.StringToInt(request.getParameter("pid"));
		ClazzMenuEntity menu = new ClazzMenuEntity();
		menu.setName(name);
		String redirectUrl = request.getContextPath()+"/admin/clazz/clazzMenu.do?m=list";
		if(pid!=0){
			ClazzMenuEntity parent = clazzMenuDao.selectById(ClazzMenuEntity.class, pid);
			if(parent!=null){
				menu.setParentMenuEntity(parent);
			}
			redirectUrl+="&pid="+pid;
		}
		clazzMenuDao.save_(menu);
		response.sendRedirect(redirectUrl);
		return null;
	}
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		
		int id = WebUtils.StringToInt(request.getParameter("id"));
		String name = request.getParameter("name");
		if(id!=0){
			ClazzMenuEntity entity = clazzMenuDao.selectById(ClazzMenuEntity.class, id);
			if(entity!=null){
				entity.setName(name);
				clazzMenuDao.update_(entity);
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
			clazzMenuDao.delete(id);
		}
		else if(!WebUtils.isEmpty(ids)){
			String [] array = ids.split(",");
			for(String idstr : array){
				int mid = WebUtils.StringToInt(idstr);
				if(mid!=0){
					clazzMenuDao.delete(mid);
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/admin/clazz/clazzMenu.do?m=list");
		return null;
	}
}
