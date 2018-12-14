package com.classnet.dao;

import com.classnet.entity.AnswerEntity;

public interface AnswerDao extends IHibernateSupportDao<AnswerEntity>{

	public void delByTopic(int topicId);
}
