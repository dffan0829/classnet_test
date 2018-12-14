package com.classnet.action.clazz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.classnet.dao.ClazzMenuDao;
import com.classnet.dao.FileTypeDao;
import com.classnet.entity.ClazzEntity;
import com.classnet.entity.ClazzMenuEntity;
import com.classnet.entity.FileTypeEntity;
import com.classnet.util.WebUtils;
import com.classnet.util.page.IPagination;
import com.classnet.util.page.SimplePagination;
import com.classnet.util.page.WebUtil;

public class SearchAction extends Action{

	private ClazzMenuDao clazzMenuDao;
	private FileTypeDao fileTypeDao;
	public void setClazzMenuDao(ClazzMenuDao clazzMenuDao) {
		this.clazzMenuDao = clazzMenuDao;
	}
	public void setFileTypeDao(FileTypeDao fileTypeDao) {
		this.fileTypeDao = fileTypeDao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int page = WebUtil.getPage(request);
		int page_size = 20;
		int pmId = WebUtil.getInteger(request, "pmId");
		int mId = WebUtil.getInteger(request, "mId");
		int ftId = WebUtil.getInteger(request, "ftId");
		String key = request.getParameter("key");
		DetachedCriteria dc = DetachedCriteria.forClass(ClazzEntity.class);
		if(mId!=0){
			dc.add(Restrictions.eq("clazzMenu.id", mId));
		}
		else if(pmId!=0){
			dc.createAlias("clazzMenu", "m").add(Restrictions.eq("m.parentMenuEntity.id", pmId));
		}
		if(pmId!=0){
			DetachedCriteria menudc = DetachedCriteria.forClass(ClazzMenuEntity.class);
			menudc.add(Restrictions.eq("parentMenuEntity.id", pmId));
			List<ClazzMenuEntity> childMenuList = clazzMenuDao.findByExample(menudc);
			request.setAttribute("childMenuList", childMenuList);
		}
		if(ftId!=0){
			dc.add(Restrictions.eq("fileType.id", ftId));
		}
		if(!WebUtils.isEmpty(key)){
			key = new String(key.getBytes("ISO8859-1"),"utf-8");
			dc.add(Restrictions.like("name", key, MatchMode.ANYWHERE));
			request.setAttribute("key", key);
		}
		IPagination pp = new SimplePagination(dc,Order.desc("pubtime"),true,page,page_size);
		pp.save(request);
		List<ClazzEntity> clazzList = pp.getPage();
		
		DetachedCriteria filetypedc = DetachedCriteria.forClass(FileTypeEntity.class);
		List<FileTypeEntity> fileTypeList = fileTypeDao.findByExample(filetypedc);
		
		DetachedCriteria menudc = DetachedCriteria.forClass(ClazzMenuEntity.class);
		menudc.add(Restrictions.isNull("parentMenuEntity"));
		List<ClazzMenuEntity> menuList = clazzMenuDao.findByExample(menudc);
		
		request.setAttribute("clazzList", clazzList);
		request.setAttribute("fileTypeList", fileTypeList);
		request.setAttribute("menuList", menuList);
		return mapping.findForward("succ");
	}
}
