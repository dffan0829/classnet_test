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
import org.springframework.beans.BeanUtils;

import com.classnet.dao.NewsDao;
import com.classnet.dao.NewsMenuDao;
import com.classnet.entity.NewsEntity;
import com.classnet.entity.NewsMenuEntity;
import com.classnet.form.NewsForm;
import com.classnet.util.DateUtil;
import com.classnet.util.upload.UploadFileImpl;

public class PubNewsAction extends DispatchAction{

	private String path;
	private String type;
	private Integer filesize=1024*1024;
	private NewsMenuDao newsMenuDao;
	private NewsDao newsDao;
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	public void setNewsMenuDao(NewsMenuDao newsMenuDao) {
		this.newsMenuDao = newsMenuDao;
	}
	@SuppressWarnings("unchecked")
	public ActionForward to(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// DetachedCriteria dc = DetachedCriteria.forClass(NewsMenuEntity.class);
		List<NewsMenuEntity> menuList = newsMenuDao.findByExample("from NewsMenuEntity");
		request.setAttribute("menuList", menuList);
		return mapping.findForward("to");
	}
	public ActionForward dopub(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		NewsForm newsForm = (NewsForm)form;
		NewsEntity entity = new NewsEntity();
		BeanUtils.copyProperties(newsForm, entity);
		FormFile file = newsForm.getImgFile();
		if(file!=null&&file.getFileSize()>0){
			UploadFileImpl uploadFile = new UploadFileImpl(path+"/images",filesize,type,file);
			uploadFile.save(DateUtil.getDateString());
			entity.setImg(uploadFile.getUploadFileName());
		}
		NewsMenuEntity newsMenu = newsMenuDao.selectById(NewsMenuEntity.class, newsForm.getMenuId());
		if(newsMenu!=null){
			entity.setNewsMenu(newsMenu);
			newsDao.save_(entity);
		}
		response.sendRedirect(request.getContextPath()+"/admin/news/news.do?m=list");
		return null;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
