package com.classnet.dao.impl;

import org.hibernate.SQLQuery;

import com.classnet.dao.UserHomeWorkDao;
import com.classnet.entity.UserHomeWorkEntity;

public class UserHomeWorkDaoImpl extends HibernateSupportDao<UserHomeWorkEntity> implements UserHomeWorkDao{

	public boolean findByUserIdAndTitleId(int userId, int titleId){
		String sql = "select t.id from userhoumework_table t where t.userId=:userId and t.titleId=:titleId";
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		q.setParameter("userId", userId);
		q.setParameter("titleId", titleId);
		Object o = q.setMaxResults(1).uniqueResult();
		if(o==null){
			return false;
		}
		return true;
	}
}
