package com.classnet.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.criterion.DetachedCriteria;

import com.classnet.dao.SourceMenuDao;
import com.classnet.entity.SourceMenuEntity;
import com.classnet.util.WebUtils;

public class SourceMenuAction extends DispatchAction{

	private SourceMenuDao sourceMenuDao;
	public void setSourceMenuDao(SourceMenuDao sourceMenuDao) {
		this.sourceMenuDao = sourceMenuDao;
	}
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DetachedCriteria dc = DetachedCriteria.forClass(SourceMenuEntity.class);
		List<SourceMenuEntity> menuList = sourceMenuDao.findByExample(dc);
		request.setAttribute("menuList", menuList);
		return mapping.findForward("list");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String name = request.getParameter("name");
		SourceMenuEntity menu = new SourceMenuEntity();
		menu.setName(name);
		sourceMenuDao.save_(menu);
		response.sendRedirect(request.getContextPath()+"/admin/source/menu.do?m=list");
		return null;
	}
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		
		int id = WebUtils.StringToInt(request.getParameter("id"));
		String name = request.getParameter("name");
		if(id!=0){
			SourceMenuEntity entity = sourceMenuDao.selectById(SourceMenuEntity.class, id);
			if(entity!=null){
				entity.setName(name);
				sourceMenuDao.update_(entity);
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
			SourceMenuEntity entity = sourceMenuDao.selectById(SourceMenuEntity.class, id);
			if(entity!=null)
				sourceMenuDao.delete_(entity);
		}
		else if(!WebUtils.isEmpty(ids)){
			String [] array = ids.split(",");
			for(String idstr : array){
				int mid = WebUtils.StringToInt(idstr);
				if(mid!=0){
					SourceMenuEntity entity = sourceMenuDao.selectById(SourceMenuEntity.class, mid);
					if(entity!=null)
						sourceMenuDao.delete_(entity);
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/admin/source/menu.do?m=list");
		return null;
	}
}
