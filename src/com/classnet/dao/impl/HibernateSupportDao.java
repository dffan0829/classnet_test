package com.classnet.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import com.classnet.dao.IHibernateSupportDao;

public class HibernateSupportDao<T> implements IHibernateSupportDao<T>{

	public SessionFactory sessionFactory;

	public T save_(T entity) {
		try {
			sessionFactory.getCurrentSession().save(entity);
			return entity;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public T save__(T entity){
		try {
			sessionFactory.getCurrentSession().save(entity);
			return entity;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void update_(T entity) {
		try {
			sessionFactory.getCurrentSession().update(entity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void delete_(T entity) {
		try {
			sessionFactory.getCurrentSession().delete(entity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public T selectById(Class<T> clz, Serializable id) {
		try {
			return (T) sessionFactory.getCurrentSession().get(clz, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List findByExample(DetachedCriteria dc) {
		return dc
				.getExecutableCriteria(getSessionFactory().getCurrentSession())
				.list();
	}

	@SuppressWarnings("unchecked")
	public List findByExample(DetachedCriteria dc, int rows) {
		if (rows < 1)
			return findByExample(dc);

		return dc
				.getExecutableCriteria(getSessionFactory().getCurrentSession())
				.setMaxResults(rows).list();
	}
	@SuppressWarnings("unchecked")
	public List findByExample(DetachedCriteria dc, int start, int rows) {
		if (rows < 1)
			return findByExample(dc);

		return dc
				.getExecutableCriteria(getSessionFactory().getCurrentSession())
				.setFirstResult(start)
				.setMaxResults(rows).list();
	}
	
	 @SuppressWarnings("unchecked")
	public  List findByExample(String sql){
		try{
			return sessionFactory.getCurrentSession().createQuery(sql).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * example:String sql="from User u WHERE u.username=:username AND u.password=:password"
	 * 
	 * Map<String,Object> map=new HashMap<String,Object>();
	 * map.put("username","lancey");
	 * map.put("password","111111");
	 * 
	 * List list=findByExample(sql,map);
	 * 
	 * @param sql
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List findByExample(String sql,Map<String,Object> map){
		try{
			Query query=sessionFactory.getCurrentSession().createQuery(sql);
			if(map!=null&&!map.isEmpty()){
				for(Iterator<Map.Entry<String, Object>> it=map.entrySet().iterator();it.hasNext();){
					Entry<String, Object> entry=it.next();
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected List findByNativeExample(String sql,Map<String,Object> map){
		try{
			SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery(sql);
			if(map!=null&&!map.isEmpty()){
				for(Iterator<Map.Entry<String, Object>> it=map.entrySet().iterator();it.hasNext();){
					Entry<String, Object> entry=it.next();
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * example:String sql="from User u WHERE u.username=? AND u.password=?"
	 * findByExample(sql,new Object[]{"aaaaa","111111"});
	 * @param sql
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List findByExample(String sql,Object[] obj){
		try{
			Query query=sessionFactory.getCurrentSession().createQuery(sql);
			if(!ArrayUtils.isEmpty(obj)){
				int pos=0;
				for(Object o:obj){
					query.setString(pos++, o.toString());
				}
			}
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public T uniqueResult(DetachedCriteria dc) {
		return (T) dc.getExecutableCriteria(
				getSessionFactory().getCurrentSession()).setMaxResults(1)
				.uniqueResult();
	}

	public int rows(DetachedCriteria dc) {
		try {
			return (Integer) dc.setProjection(Projections.rowCount())
					.getExecutableCriteria(
							getSessionFactory().getCurrentSession())
					.setMaxResults(1).uniqueResult();
		} catch (Exception e) {
			return 0;
		}
	}

}
