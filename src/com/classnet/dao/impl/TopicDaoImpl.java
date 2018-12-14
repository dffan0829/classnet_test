package com.classnet.dao.impl;

import com.classnet.dao.TopicDao;
import com.classnet.entity.TopicEntity;

public class TopicDaoImpl extends HibernateSupportDao<TopicEntity> implements TopicDao{

	public int findCount(){
		String sql = "select count(t.id) from TopicEntity t";
		Object o = sessionFactory.getCurrentSession().createQuery(sql).setMaxResults(1).uniqueResult();
		return Integer.parseInt(o.toString());
	}
}
