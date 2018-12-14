package com.classnet.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.criterion.DetachedCriteria;

import com.classnet.dao.FileTypeDao;
import com.classnet.entity.FileTypeEntity;
import com.classnet.util.WebUtils;

public class FileTypeAction extends DispatchAction{

	private FileTypeDao fileTypeDao;
	public void setFileTypeDao(FileTypeDao fileTypeDao) {
		this.fileTypeDao = fileTypeDao;
	}
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DetachedCriteria dc = DetachedCriteria.forClass(FileTypeEntity.class);
		List<FileTypeEntity> fileTypeList = fileTypeDao.findByExample(dc);
		request.setAttribute("fileTypeList", fileTypeList);
		return mapping.findForward("list");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String name = request.getParameter("name");
		FileTypeEntity menu = new FileTypeEntity();
		menu.setName(name);
		fileTypeDao.save_(menu);
		response.sendRedirect(request.getContextPath()+"/admin/clazz/fileType.do?m=list");
		return null;
	}
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		
		int id = WebUtils.StringToInt(request.getParameter("id"));
		String name = request.getParameter("name");
		if(id!=0){
			FileTypeEntity entity = fileTypeDao.selectById(FileTypeEntity.class, id);
			if(entity!=null){
				entity.setName(name);
				fileTypeDao.update_(entity);
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
			FileTypeEntity entity = fileTypeDao.selectById(FileTypeEntity.class, id);
			if(entity!=null)
				fileTypeDao.delete_(entity);
		}
		else if(!WebUtils.isEmpty(ids)){
			String [] array = ids.split(",");
			for(String idstr : array){
				int mid = WebUtils.StringToInt(idstr);
				if(mid!=0){
					FileTypeEntity entity = fileTypeDao.selectById(FileTypeEntity.class, mid);
					if(entity!=null)
						fileTypeDao.delete_(entity);
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/admin/clazz/fileType.do?m=list");
		return null;
	}
}
