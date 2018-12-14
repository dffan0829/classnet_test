package com.classnet.dao.impl;

import com.classnet.dao.SourceDao;
import com.classnet.entity.SourceEntity;

public class SourceDaoImpl extends HibernateSupportDao<SourceEntity> implements SourceDao{

	public int findCount(){
		String sql = "select count(t.id) from SourceEntity t";
		Object o = sessionFactory.getCurrentSession().createQuery(sql).setMaxResults(1).uniqueResult();
		return Integer.parseInt(o.toString());
	}
}
