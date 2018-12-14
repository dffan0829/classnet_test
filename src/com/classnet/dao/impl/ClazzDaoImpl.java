package com.classnet.dao.impl;

import com.classnet.dao.ClazzDao;
import com.classnet.entity.ClazzEntity;

public class ClazzDaoImpl extends HibernateSupportDao<ClazzEntity> implements ClazzDao{

	public int findCount(){
		String sql = "select count(t.id) from ClazzEntity t";
		Object o = sessionFactory.getCurrentSession().createQuery(sql).setMaxResults(1).uniqueResult();
		return Integer.parseInt(o.toString());
	}
}
