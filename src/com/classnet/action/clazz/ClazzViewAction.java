package com.classnet.action.clazz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.classnet.dao.ClazzDao;
import com.classnet.dao.ClazzMenuDao;
import com.classnet.entity.ClazzEntity;
import com.classnet.entity.ClazzMenuEntity;
import com.classnet.util.page.WebUtil;

public class ClazzViewAction extends Action{

	private ClazzDao clazzDao;
	private ClazzMenuDao clazzMenuDao;
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}
	public void setClazzMenuDao(ClazzMenuDao clazzMenuDao) {
		this.clazzMenuDao = clazzMenuDao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		int id = WebUtil.getInteger(request, "id");
		ClazzEntity clazzEnttiy = clazzDao.selectById(ClazzEntity.class, id);
		
		DetachedCriteria menudc = DetachedCriteria.forClass(ClazzMenuEntity.class);
		menudc.add(Restrictions.isNull("parentMenuEntity"));
		List<ClazzMenuEntity> menuList = clazzMenuDao.findByExample(menudc);
		
		request.setAttribute("clazzEntity", clazzEnttiy);
		request.setAttribute("menuList", menuList);
		return mapping.findForward("succ");
	}
}
