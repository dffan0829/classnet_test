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

import com.classnet.dao.ClazzDao;
import com.classnet.dao.ClazzMenuDao;
import com.classnet.dao.FileTypeDao;
import com.classnet.entity.ClazzEntity;
import com.classnet.entity.ClazzMenuEntity;
import com.classnet.entity.FileTypeEntity;
import com.classnet.form.ClazzForm;
import com.classnet.util.DateUtil;
import com.classnet.util.WebUtils;
import com.classnet.util.page.IPagination;
import com.classnet.util.page.SimplePagination;
import com.classnet.util.page.WebUtil;
import com.classnet.util.upload.UploadFileImpl;

public class ClazzAction extends DispatchAction{

	private String imgType;
	private String path;
	private String type;
	private String flashType;
	private Integer filesize=1024*1024*5;
	private ClazzDao clazzDao;
	private ClazzMenuDao clazzMenuDao;
	private FileTypeDao fileTypeDao;
	public void setFileTypeDao(FileTypeDao fileTypeDao) {
		this.fileTypeDao = fileTypeDao;
	}
	public void setClazzMenuDao(ClazzMenuDao clazzMenuDao) {
		this.clazzMenuDao = clazzMenuDao;
	}
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int pmid = WebUtil.getInteger(request, "pmId");
		int mid = WebUtil.getInteger(request, "mId");
		int page = WebUtil.getPage(request);
		int fileTypeId = WebUtil.getInteger(request, "ftId");
		String key = request.getParameter("key");
		int page_size = 10;
		DetachedCriteria dc = DetachedCriteria.forClass(ClazzEntity.class);
		if(mid!=0){
			dc.add(Restrictions.eq("clazzMenu.id", mid));
		}
		else if(pmid!=0){
			dc.createAlias("clazzMenu", "m").add(Restrictions.eq("m.parentMenuEntity.id", pmid));
		}
		if(pmid!=0){
			DetachedCriteria menudc = DetachedCriteria.forClass(ClazzMenuEntity.class);
			menudc.add(Restrictions.eq("parentMenuEntity.id", pmid));
			List<ClazzMenuEntity> childMenuList = clazzMenuDao.findByExample(menudc);
			request.setAttribute("childMenuList", childMenuList);
		}
		if(!WebUtils.isEmpty(key)){
			key = new String(key.getBytes("ISO8859-1"),"utf-8");
			dc.add(Restrictions.like("name", key, MatchMode.ANYWHERE));
			request.setAttribute("key", key);
		}
		if(fileTypeId!=0){
			dc.add(Restrictions.eq("fileType.id", fileTypeId));
		}
		IPagination pp = new SimplePagination(dc,Order.desc("pubtime"),true,page,page_size);
		pp.save(request);
		List<ClazzEntity> clazzList = pp.getPage();

		DetachedCriteria menudc = DetachedCriteria.forClass(ClazzMenuEntity.class);
		menudc.add(Restrictions.isNull("parentMenuEntity"));
		List<ClazzMenuEntity> menuList = clazzMenuDao.findByExample(menudc);
		
		DetachedCriteria filetypedc = DetachedCriteria.forClass(FileTypeEntity.class);
		List<FileTypeEntity> fileTypeList = fileTypeDao.findByExample(filetypedc);
		
		request.setAttribute("clazzList", clazzList);
		request.setAttribute("menuList", menuList);
		request.setAttribute("fileTypeList", fileTypeList);
		return mapping.findForward("list");
	}
	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		int id = WebUtils.StringToInt(request.getParameter("id"));
		ClazzEntity clazzEntity = clazzDao.selectById(ClazzEntity.class, id);
		if(clazzEntity==null){
			response.sendRedirect(request.getContextPath()+"/admin/clazz/clazz.do?m=list");
			return null;
		}
		
		DetachedCriteria menudc = DetachedCriteria.forClass(ClazzMenuEntity.class);
		menudc.add(Restrictions.isNull("parentMenuEntity"));
		List<ClazzMenuEntity> menuList = clazzMenuDao.findByExample(menudc);
		
		DetachedCriteria childmenudc = DetachedCriteria.forClass(ClazzMenuEntity.class);
		childmenudc.add(Restrictions.eq("parentMenuEntity.id", clazzEntity.getClazzMenu().getParentMenuEntity().getId()));
		List<ClazzMenuEntity> clildMenuList = clazzMenuDao.findByExample(childmenudc);
		
		DetachedCriteria filetypedc = DetachedCriteria.forClass(FileTypeEntity.class);
		List<FileTypeEntity> fileTypeList = fileTypeDao.findByExample(filetypedc);
		
		request.setAttribute("clazzEntity", clazzEntity);
		request.setAttribute("menuList", menuList);
		request.setAttribute("fileTypeList", fileTypeList);
		request.setAttribute("clildMenuList", clildMenuList);
		return mapping.findForward("edit");
	}	
	public ActionForward doedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtil.getInteger(request, "id");
		ClazzForm clazzForm = (ClazzForm)form;
		ClazzEntity entity = clazzDao.selectById(ClazzEntity.class, id);
		if(entity==null){
			response.sendRedirect(request.getContextPath()+"/admin/clazz/clazz.do?m=list");
			return null;
		}
		BeanUtils.copyProperties(clazzForm, entity);
		FormFile imgFile = clazzForm.getImgFile();
		if(imgFile!=null&&imgFile.getFileSize()>0){
			UploadFileImpl uploadFile = new UploadFileImpl(path+"/images",filesize,imgType,imgFile);
			uploadFile.save(DateUtil.getDateString());
			entity.setImg(uploadFile.getUploadFileName());
		}
		FormFile file = clazzForm.getFile();
		if(file!=null&&file.getFileSize()>0){
			UploadFileImpl uploadFile = new UploadFileImpl(path+"/files",filesize,type,file);
			uploadFile.save(DateUtil.getDateString());
			entity.setFilename(uploadFile.getUploadFileName());
		}
		FormFile flashFile = clazzForm.getFlashFile();
		if(flashFile!=null&&flashFile.getFileSize()>0){
			UploadFileImpl uploadFile = new UploadFileImpl(path+"/files",filesize,flashType,flashFile);
			uploadFile.save(DateUtil.getDateString());
			entity.setFlashFilename(uploadFile.getUploadFileName());
		}
		ClazzMenuEntity menu = clazzMenuDao.selectById(ClazzMenuEntity.class, clazzForm.getMenuId());
		if(menu!=null){
			entity.setClazzMenu(menu);
		}
		FileTypeEntity fileType = fileTypeDao.selectById(FileTypeEntity.class, clazzForm.getFiletypeId());
		if(fileType!=null){
			entity.setFileType(fileType);
		}
		clazzDao.update_(entity);
		response.sendRedirect(request.getContextPath()+"/admin/clazz/clazz.do?m=list");
		return null;
	}
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtils.StringToInt(request.getParameter("id"));
		String ids = request.getParameter("ids");
		if(id!=0){
			ClazzEntity entity = clazzDao.selectById(ClazzEntity.class, id);
			if(entity!=null){
				clazzDao.delete_(entity);
			}
		}
		else if(!WebUtils.isEmpty(ids)){
			String [] array = ids.split(",");
			for(String idstr : array){
				int mid = WebUtils.StringToInt(idstr);
				if(mid!=0){
					ClazzEntity entity = clazzDao.selectById(ClazzEntity.class, mid);
					if(entity!=null)
						clazzDao.delete_(entity);
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/admin/clazz/clazz.do?m=list");
		return null;
	}
	public ActionForward changestatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtil.getInteger(request, "id");
		int status = WebUtil.getInteger(request, "status");
		ClazzEntity entity = clazzDao.selectById(ClazzEntity.class, id);
		if(entity!=null){
			entity.setStatus(status);
			clazzDao.update_(entity);
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("1");
		}
		return null;
	}
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setFlashType(String flashType) {
		this.flashType = flashType;
	}
}
