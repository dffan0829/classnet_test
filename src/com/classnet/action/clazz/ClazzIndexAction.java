package com.classnet.action.clazz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.classnet.dao.ClazzDao;
import com.classnet.dao.ClazzMenuDao;
import com.classnet.dao.FileTypeDao;
import com.classnet.entity.ClazzEntity;
import com.classnet.entity.ClazzMenuEntity;
import com.classnet.entity.FileTypeEntity;

public class ClazzIndexAction extends Action{

	private ClazzDao clazzDao;
	private ClazzMenuDao clazzMenuDao;
	private FileTypeDao fileTypeDao;
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}
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
	
		DetachedCriteria filetypedc = DetachedCriteria.forClass(FileTypeEntity.class);
		List<FileTypeEntity> fileTypeList = fileTypeDao.findByExample(filetypedc);
		if(fileTypeList!=null&&!fileTypeList.isEmpty()){
			for(FileTypeEntity ft : fileTypeList){
				DetachedCriteria clazzdc = DetachedCriteria.forClass(ClazzEntity.class);
				clazzdc.add(Restrictions.eq("fileType.id", ft.getId()));
				List<ClazzEntity> clazzList = clazzDao.findByExample(clazzdc,10);
				ft.setClazzList(clazzList);
			}
		}
		
		DetachedCriteria menudc = DetachedCriteria.forClass(ClazzMenuEntity.class);
		menudc.add(Restrictions.isNull("parentMenuEntity"));
		List<ClazzMenuEntity> menuList = clazzMenuDao.findByExample(menudc);
		
		DetachedCriteria newclazzdc = DetachedCriteria.forClass(ClazzEntity.class);
		newclazzdc.addOrder(Order.desc("pubtime"));
		List<ClazzEntity> newClazzList = clazzDao.findByExample(newclazzdc,8);
		
		request.setAttribute("fileTypeList", fileTypeList);
		request.setAttribute("menuList", menuList);
		request.setAttribute("newClazzList", newClazzList);
		return mapping.findForward("succ");
	}
}
