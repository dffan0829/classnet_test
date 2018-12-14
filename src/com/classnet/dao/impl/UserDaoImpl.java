package com.classnet.dao.impl;

import org.hibernate.Query;

import com.classnet.dao.UserDao;
import com.classnet.entity.UserEntity;

public class UserDaoImpl extends HibernateSupportDao<UserEntity> implements UserDao{

	public UserEntity getUser(String username){
		try{
			String sql = "from UserEntity where username=:username";
			Query q = getSessionFactory().getCurrentSession().createQuery(sql);
			q.setParameter("username", username);
			return (UserEntity)q.setMaxResults(1).uniqueResult();
		}catch(RuntimeException e){
			throw e;
		}
	}
}
