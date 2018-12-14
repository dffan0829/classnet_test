package com.classnet.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;

import com.classnet.dao.SourceDao;
import com.classnet.dao.SourceMenuDao;
import com.classnet.entity.SourceEntity;
import com.classnet.entity.SourceMenuEntity;
import com.classnet.form.SourceForm;
import com.classnet.util.DateUtil;
import com.classnet.util.WebUtils;
import com.classnet.util.page.IPagination;
import com.classnet.util.page.SimplePagination;
import com.classnet.util.page.WebUtil;
import com.classnet.util.upload.UploadFileImpl;

public class SourceAction extends DispatchAction{

	private String path;
	private String type;
	private Integer filesize=1024*1024;
	private SourceDao sourceDao;
	private SourceMenuDao sourceMenuDao;
	public void setSourceDao(SourceDao sourceDao) {
		this.sourceDao = sourceDao;
	}
	public void setSourceMenuDao(SourceMenuDao sourceMenuDao) {
		this.sourceMenuDao = sourceMenuDao;
	}
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		String key = request.getParameter("key");
		int menuId = WebUtils.StringToInt(request.getParameter("menuId"));
		int page = WebUtil.getPage(request);
		int page_size = 10;
		DetachedCriteria dc = DetachedCriteria.forClass(SourceEntity.class);
		if(!WebUtils.isEmpty(key)){
			key = new String(key.getBytes("ISO8859-1"),"utf-8");
			dc.add(Restrictions.like("name", key, MatchMode.ANYWHERE));
			request.setAttribute("key", key);
		}
		if(menuId!=0){
			dc.add(Restrictions.eq("sourceMenu.id", menuId));
			request.setAttribute("menuId", menuId);
		}
		IPagination pp = new SimplePagination(dc,Order.desc("pubtime"),true,page,page_size);
		pp.save(request);
		List<SourceEntity> sourceList = pp.getPage();
		request.setAttribute("sourceList", sourceList);
		DetachedCriteria menudc = DetachedCriteria.forClass(SourceMenuEntity.class);
		List<SourceMenuEntity> menuList = sourceMenuDao.findByExample(menudc);
		request.setAttribute("menuList", menuList);
		return mapping.findForward("list");
	}
	@SuppressWarnings("unchecked")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		DetachedCriteria menudc = DetachedCriteria.forClass(SourceMenuEntity.class);
		List<SourceMenuEntity> menuList = sourceMenuDao.findByExample(menudc);
		request.setAttribute("menuList", menuList);
		return mapping.findForward("add");
	}
	public ActionForward doadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		SourceForm sourceForm = (SourceForm)form;
		SourceEntity entity = new SourceEntity();
		BeanUtils.copyProperties(sourceForm, entity);
		FormFile file = sourceForm.getFile();
		if(file!=null&&file.getFileSize()>0){
			UploadFileImpl uploadFile = new UploadFileImpl(path+"/files",filesize,type,file);
			uploadFile.save(DateUtil.getDateString());
			entity.setFilename(uploadFile.getUploadFileName());
		}
		SourceMenuEntity menu = sourceMenuDao.selectById(SourceMenuEntity.class, sourceForm.getMenuId());
		entity.setSourceMenu(menu);
		sourceDao.save_(entity);
		response.sendRedirect(request.getContextPath()+"/admin/source/source.do?m=list");
		return null;
	}
	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		int id = WebUtils.StringToInt(request.getParameter("id"));
		SourceEntity sourceEntity = sourceDao.selectById(SourceEntity.class, id);
		DetachedCriteria dc = DetachedCriteria.forClass(SourceMenuEntity.class);
		List<SourceMenuEntity> menuList = sourceMenuDao.findByExample(dc);
		request.setAttribute("menuList", menuList);
		request.setAttribute("sourceEntity", sourceEntity);
		return mapping.findForward("edit");
	}
	public ActionForward doedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtils.StringToInt(request.getParameter("id"));
		SourceEntity entity = sourceDao.selectById(SourceEntity.class, id);
		if(entity!=null){
			SourceForm sourceForm = (SourceForm)form;
			BeanUtils.copyProperties(sourceForm, entity);
			FormFile file = sourceForm.getFile();
			if(file!=null&&file.getFileSize()>0){
				UploadFileImpl uploadFile = new UploadFileImpl(path+"/files",filesize,type,file);
				uploadFile.save(DateUtil.getDateString());
				entity.setFilename(uploadFile.getUploadFileName());
			}
			SourceMenuEntity menu = sourceMenuDao.selectById(SourceMenuEntity.class, sourceForm.getMenuId());
			if(menu!=null){
				entity.setSourceMenu(menu);
				sourceDao.update_(entity);
			}
		}
		response.sendRedirect(request.getContextPath()+"/admin/source/source.do?m=list");
		return null;
	}
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtils.StringToInt(request.getParameter("id"));
		String ids = request.getParameter("ids");
		if(id!=0){
			SourceEntity entity = sourceDao.selectById(SourceEntity.class, id);
			if(entity!=null){
				sourceDao.delete_(entity);
			}
		}
		else if(!WebUtils.isEmpty(ids)){
			String [] array = ids.split(",");
			for(String idstr : array){
				int mid = WebUtils.StringToInt(idstr);
				if(mid!=0){
					SourceEntity entity = sourceDao.selectById(SourceEntity.class, mid);
					if(entity!=null)
						sourceDao.delete_(entity);
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/admin/source/source.do?m=list");
		return null;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setType(String type) {
		this.type = type;
	}
}
