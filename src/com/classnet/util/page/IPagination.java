package com.classnet.util.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * Example:
 * <code>
 * 
 * DetachedCriteria dc=DetachedCriteria.forClass(ResArea.class,"r");
 * dc.add(Restrictions.eq("r.step",1)); 
 * IPagination pp=new SimplePagination(dc,Order.desc("r.id"),WebUtil.getPage(request),page_size);
 * List list=pp.getPage();
 * pp.save(request);aa.do?paramId=1&page=3
 * </code>	
 * 
 * 
 * <code>
 * String sql="select * from ResArea r WHERE r.step=:step ORDER BY r.id desc;
 * String totalsql="SELECT COUNT(*) FROM ResArea r WHERE r.step=:step;
 * Map<String,Object> map=new HashMap<String,Object>();
 * map.put("step",1);
 * IPagination pp=new QueryPagination(sql,map,totalsql,map,WebUtil.getPage(request),page_size);
 * List list=pp.getPage();
 * pp.save(request);
 * </code>
 * </pre>
 *
 */
public interface IPagination {
	
	public final String PAGE_REQUEST_KEY="PageInfo";

	@SuppressWarnings("unchecked")
	public List getPage();
	
	public int size();
	
	public void save(HttpServletRequest req,String url,String key);
	
	public void save(HttpServletRequest req,String url);
	
	public void save(HttpServletRequest req);
}
