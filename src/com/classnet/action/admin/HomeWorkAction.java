package com.classnet.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.classnet.dao.HomeWorkTitleDao;
import com.classnet.dao.UserHomeWorkDao;
import com.classnet.entity.HomeWorkTitleEntity;
import com.classnet.entity.UserHomeWorkEntity;
import com.classnet.util.WebUtils;
import com.classnet.util.page.IPagination;
import com.classnet.util.page.SimplePagination;
import com.classnet.util.page.WebUtil;

public class HomeWorkAction extends DispatchAction{

	private String path;
	private HomeWorkTitleDao homeWorkTitleDao;
	private UserHomeWorkDao userHomeWorkDao;
	public void setUserHomeWorkDao(UserHomeWorkDao userHomeWorkDao) {
		this.userHomeWorkDao = userHomeWorkDao;
	}
	public void setHomeWorkTitleDao(HomeWorkTitleDao homeWorkTitleDao) {
		this.homeWorkTitleDao = homeWorkTitleDao;
	}
	@SuppressWarnings("unchecked")
	public ActionForward titleList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int page = WebUtil.getPage(request);
		String key = request.getParameter("key");
		int pageSize = 10;
		DetachedCriteria dc = DetachedCriteria.forClass(HomeWorkTitleEntity.class);
		if(!WebUtils.isEmpty(key)){
			key = new String(key.getBytes("ISO8859-1"),"utf-8");
			dc.add(Restrictions.like("title", key, MatchMode.ANYWHERE));
			request.setAttribute("key", key);
		}
		IPagination pp = new SimplePagination(dc,Order.desc("id"),true,page,pageSize);
		pp.save(request);
		List<HomeWorkTitleEntity> homeworktitleList = pp.getPage();
		request.setAttribute("homeworktitleList", homeworktitleList);
		return mapping.findForward("titleList");
	}
	public ActionForward addTitle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("addTitle");
	}
	public ActionForward doAddTitle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		HomeWorkTitleEntity entity = new HomeWorkTitleEntity();
		entity.setTitle(title);
		entity.setTime(new Date());
		entity.setDescription(description);
		homeWorkTitleDao.save_(entity);
		response.sendRedirect(request.getContextPath()+"/admin/homework.do?m=titleList");
		return null;
	}
	public ActionForward editTitle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int id = WebUtil.getInteger(request, "id");
		HomeWorkTitleEntity entity = homeWorkTitleDao.selectById(HomeWorkTitleEntity.class, id);
		if(entity==null){
			response.sendRedirect(request.getContextPath()+"/admin/homework.do?m=titleList");
			return null;
		}
		request.setAttribute("homeWorkTitleEntity", entity);
		return mapping.findForward("addTitle");
	}
	public ActionForward doEditTitle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtil.getInteger(request, "id");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		HomeWorkTitleEntity entity = homeWorkTitleDao.selectById(HomeWorkTitleEntity.class, id);
		if(entity!=null){
			entity.setTitle(title);
			entity.setDescription(description);
			homeWorkTitleDao.update_(entity);
		}
		response.sendRedirect(request.getContextPath()+"/admin/homework.do?m=titleList");
		return null;
	}
	public ActionForward delTitle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtil.getInteger(request, "id");
		String ids = request.getParameter("ids");
		if(id!=0){
			HomeWorkTitleEntity entity = homeWorkTitleDao.selectById(HomeWorkTitleEntity.class, id);
			if(entity!=null){
				homeWorkTitleDao.delete_(entity);
			}
		}
		else if(!WebUtils.isEmpty(ids)){
			String [] array = ids.split(",");
			for(String idstr : array){
				int mid = WebUtils.StringToInt(idstr);
				if(mid!=0){
					HomeWorkTitleEntity entity = homeWorkTitleDao.selectById(HomeWorkTitleEntity.class, mid);
					if(entity!=null)
						homeWorkTitleDao.delete_(entity);
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/admin/homework.do?m=titleList");
		return null;
	}
	public ActionForward titleDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtil.getInteger(request, "id");
		HomeWorkTitleEntity entity = homeWorkTitleDao.selectById(HomeWorkTitleEntity.class, id);
		request.setAttribute("homeWorkTitleEntity", entity);
		return mapping.findForward("homeworktitledetail");
	}
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int page = WebUtil.getPage(request);
		int pageSize = 10;
		int titleId = WebUtil.getInteger(request, "titleId");
		String key = request.getParameter("key");
		String username = request.getParameter("username");
		DetachedCriteria dc = DetachedCriteria.forClass(UserHomeWorkEntity.class);
		dc.add(Restrictions.eq("title.id", titleId));
		if(!WebUtils.isEmpty(key)){
			key = new String(key.getBytes("ISO8859-1"),"utf-8");
			dc.add(Restrictions.like("name", key, MatchMode.ANYWHERE));
			request.setAttribute("key", key);
		}
		if(!WebUtils.isEmpty(username)){
			dc.createAlias("userEntity", "user").add(Restrictions.like("user.username", username, MatchMode.ANYWHERE));
			request.setAttribute("username", username);
		}
		IPagination pp = new SimplePagination(dc,Order.desc("id"),true,page,pageSize);
		pp.save(request);
		List<UserHomeWorkEntity> homeworkList = pp.getPage();
		request.setAttribute("homeworkList", homeworkList);
		HomeWorkTitleEntity titleEntity = homeWorkTitleDao.selectById(HomeWorkTitleEntity.class, titleId);
		request.setAttribute("titleEntity", titleEntity);
		return mapping.findForward("homeworklist");
	}
	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = WebUtil.getInteger(request, "id");
		UserHomeWorkEntity entity = userHomeWorkDao.selectById(UserHomeWorkEntity.class, id);
		if(entity!=null){
			HomeWorkTitleEntity titleEntity = entity.getTitle();
			File file = new File(path+"/homework/"+titleEntity.getId()+"/"+entity.getName());
			if(!file.exists()){
				response.setCharacterEncoding("utf-8");
				response.getWriter().write("该文件不存在");
				return null;
			}
			response.reset();
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition","attachment;filename=" + new String(entity.getName().getBytes(),"ISO8859-1")); 
			OutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(file);
			byte[] b = new byte[1024];
			int i = 0;
			while((i=in.read(b))>0){
				out.write(b,0,i);
			}
			out.flush();
			in.close();
			out.close();
		}
		return null;
	}
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int titleId = WebUtil.getInteger(request, "titleId");
		int id = WebUtil.getInteger(request, "id");
		String ids = request.getParameter("ids");
		if(id!=0){
			UserHomeWorkEntity entity = userHomeWorkDao.selectById(UserHomeWorkEntity.class, id);
			if(entity!=null){
				HomeWorkTitleEntity titleEntity = entity.getTitle();
				File file = new File(path+"/homework/"+titleEntity.getId()+"/"+entity.getName());
				userHomeWorkDao.delete_(entity);
				if(file.exists()){
					file.delete();
				}
			}
		}
		else if(!WebUtils.isEmpty(ids)){
			String [] array = ids.split(",");
			for(String idstr : array){
				int mid = WebUtils.StringToInt(idstr);
				if(mid!=0){
					UserHomeWorkEntity entity = userHomeWorkDao.selectById(UserHomeWorkEntity.class, mid);
					if(entity!=null){
						HomeWorkTitleEntity titleEntity = entity.getTitle();
						File file = new File(path+"/homework/"+titleEntity.getId()+"/"+entity.getName());
						userHomeWorkDao.delete_(entity);
						if(file.exists()){
							file.delete();
						}
					}
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/admin/homework.do?m=list&titleId="+titleId);
		return null;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
