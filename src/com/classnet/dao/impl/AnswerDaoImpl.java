package com.classnet.dao.impl;

import org.hibernate.SQLQuery;

import com.classnet.dao.AnswerDao;
import com.classnet.entity.AnswerEntity;

public class AnswerDaoImpl extends HibernateSupportDao<AnswerEntity> implements AnswerDao{

	public void delByTopic(int topicId){
		String sql = "delete from answer_table where topicId=:topicId";
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		q.setParameter("topicId", topicId);
		q.executeUpdate();
	}
}
