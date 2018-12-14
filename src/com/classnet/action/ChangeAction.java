package com.classnet.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.classnet.dao.ClazzMenuDao;
import com.classnet.entity.ClazzMenuEntity;
import com.classnet.util.WebUtils;

public class ChangeAction extends Action{

	private ClazzMenuDao clazzMenuDao;
	public void setClazzMenuDao(ClazzMenuDao clazzMenuDao) {
		this.clazzMenuDao = clazzMenuDao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int pid = WebUtils.StringToInt(request.getParameter("pid"));
		DetachedCriteria dc = DetachedCriteria.forClass(ClazzMenuEntity.class);
		dc.add(Restrictions.eq("parentMenuEntity.id", pid));
		List<ClazzMenuEntity> menuList = clazzMenuDao.findByExample(dc);
		if(menuList!=null&&!menuList.isEmpty()){
			JSONArray ja = new JSONArray();
			for(ClazzMenuEntity m : menuList){
				JSONObject jo = new JSONObject();
				jo.put("id", m.getId());
				jo.put("name", m.getName());
				ja.add(jo);
			}
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(ja.toString());
		}
		return null;
	}
}
