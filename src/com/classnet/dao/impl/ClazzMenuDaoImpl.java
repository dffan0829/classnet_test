package com.classnet.dao.impl;

import org.hibernate.SQLQuery;

import com.classnet.dao.ClazzMenuDao;
import com.classnet.entity.ClazzMenuEntity;

public class ClazzMenuDaoImpl extends HibernateSupportDao<ClazzMenuEntity> implements ClazzMenuDao{

	public void delete(int id){
		try{
			String sql = "delete from clazz_menu where parentId=:parentId";
			SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
			q.setParameter("parentId", id);
			q.executeUpdate();
			
			String sql1 = "delete from clazz_menu where id=:id";
			SQLQuery q1 = sessionFactory.getCurrentSession().createSQLQuery(sql1);
			q1.setParameter("id", id);
			q1.executeUpdate();
			
		}catch(Exception e){
			throw new RuntimeException();
		}
	}
}
