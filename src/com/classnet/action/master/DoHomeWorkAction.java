package com.classnet.action.master;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.classnet.dao.HomeWorkTitleDao;
import com.classnet.dao.UserDao;
import com.classnet.dao.UserHomeWorkDao;
import com.classnet.entity.HomeWorkTitleEntity;
import com.classnet.entity.UserEntity;
import com.classnet.entity.UserHomeWorkEntity;
import com.classnet.form.UserHomeWorkForm;
import com.classnet.util.page.IPagination;
import com.classnet.util.page.SimplePagination;
import com.classnet.util.page.WebUtil;
import com.classnet.util.upload.UploadFileImpl;

public class DoHomeWorkAction extends DispatchAction{

	private String path;
	private int filesize = 1024*1024*100;
	private String type = "rar";
	private HomeWorkTitleDao homeWorkTitleDao;
	private UserHomeWorkDao userHomeWorkDao;
	private UserDao userDao;
	public void setUserHomeWorkDao(UserHomeWorkDao userHomeWorkDao) {
		this.userHomeWorkDao = userHomeWorkDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setHomeWorkTitleDao(HomeWorkTitleDao homeWorkTitleDao) {
		this.homeWorkTitleDao = homeWorkTitleDao;
	}
	@SuppressWarnings("unchecked")
	public ActionForward hw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String loginUser = WebUtil.getLoginUser();
		UserEntity userEntity = userDao.getUser(loginUser);
		int page = WebUtil.getPage(request);
		int pageSize = 10;
		DetachedCriteria dc = DetachedCriteria.forClass(HomeWorkTitleEntity.class);
		IPagination pp = new SimplePagination(dc,Order.desc("id"),true,page,pageSize);
		pp.save(request);
		List<HomeWorkTitleEntity> homeworktitleList = pp.getPage();
		if(homeworktitleList!=null&&!homeworktitleList.isEmpty()){
			for(HomeWorkTitleEntity entity : homeworktitleList){
				boolean bln = userHomeWorkDao.findByUserIdAndTitleId(userEntity.getId(), entity.getId());
				if(bln){
					entity.setUsersubmit(1);
				}
			}
		}
		request.setAttribute("homeworktitleList", homeworktitleList);
		return mapping.findForward("hw");
	}
	public ActionForward titleDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtil.getInteger(request, "id");
		HomeWorkTitleEntity entity = homeWorkTitleDao.selectById(HomeWorkTitleEntity.class, id);
		if(entity==null){
			response.sendRedirect(request.getContextPath()+"/master/homework.do?m=hw");
			return null;
		}
		request.setAttribute("entity", entity);
		return mapping.findForward("homeworktitledetial");
	}
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		UserHomeWorkForm userHomeWorkForm = (UserHomeWorkForm)form;
		HomeWorkTitleEntity titleEntity = homeWorkTitleDao.selectById(HomeWorkTitleEntity.class, userHomeWorkForm.getTitleId());
		FormFile file = userHomeWorkForm.getWorkfile();
		if(titleEntity!=null&&file!=null&&file.getFileSize()>0){
			try{
				UserHomeWorkEntity entity = new UserHomeWorkEntity();
				UploadFileImpl uploadFile = new UploadFileImpl(path+"/homework/"+titleEntity.getId(),filesize,type,file);
				uploadFile.save();
				entity.setName(uploadFile.getFilename());
				entity.setAddtime(new Date());
				entity.setTitle(titleEntity);
				UserEntity user = userDao.getUser(WebUtil.getLoginUser());
				entity.setUserEntity(user);
				userHomeWorkDao.save_(entity);
			}catch(Exception e){
				//e.printStackTrace();
				request.getSession().setAttribute("message", e.getMessage());
				response.sendRedirect(request.getContextPath()+"/master/homework.do?m=titleDetail&id="+titleEntity.getId());
				return null;
			}
		}
		response.sendRedirect(request.getContextPath()+"/master/homework.do?m=hw");
		return null;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
