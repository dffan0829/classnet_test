package com.classnet.dao.impl;

import com.classnet.dao.NewsDao;
import com.classnet.entity.NewsEntity;

public class NewsDaoImpl extends HibernateSupportDao<NewsEntity> implements NewsDao{

	public int findCount(){
		String sql = "select count(t.id) from NewsEntity t";
		Object o = sessionFactory.getCurrentSession().createQuery(sql).setMaxResults(1).uniqueResult();
		return Integer.parseInt(o.toString());
	}
}
