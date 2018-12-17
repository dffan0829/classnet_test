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
import com.classnet.util.upload.UploadFileImpl;
//2018-12-14 17:42:46  
public class AddClazzAction extends DispatchAction{

	private String imgType;
	private String path;
	private String type;
	private String flashType;
	private Integer filesize=1024*1024*5;
	private ClazzMenuDao clazzMenuDao;
	private FileTypeDao fileTypeDao;
	private ClazzDao clazzDao;
	public void setClazzMenuDao(ClazzMenuDao clazzMenuDao) {
		this.clazzMenuDao = clazzMenuDao;
	}
	public void setFileTypeDao(FileTypeDao fileTypeDao) {
		this.fileTypeDao = fileTypeDao;
	}
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}
	@SuppressWarnings("unchecked")
	public ActionForward to(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DetachedCriteria menudc = DetachedCriteria.forClass(ClazzMenuEntity.class);
		menudc.add(Restrictions.isNull("parentMenuEntity"));
		List<ClazzMenuEntity> menuList = clazzMenuDao.findByExample(menudc);
		
		DetachedCriteria filetypedc = DetachedCriteria.forClass(FileTypeEntity.class);
		List<FileTypeEntity> fileTypeList = fileTypeDao.findByExample(filetypedc);
		
		request.setAttribute("menuList", menuList);
		request.setAttribute("fileTypeList", fileTypeList);
		return mapping.findForward("succ");
	}
	public ActionForward doadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ClazzForm clazzForm = (ClazzForm)form;
		ClazzEntity entity = new ClazzEntity();
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
		clazzDao.save_(entity);
		response.sendRedirect(request.getContextPath()+"/admin/clazz/clazz.do?m=list");
		return null;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setFlashType(String flashType) {
		this.flashType = flashType;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
}
