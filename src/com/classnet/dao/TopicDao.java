package com.classnet.dao;

import com.classnet.entity.TopicEntity;

public interface TopicDao extends IHibernateSupportDao<TopicEntity>{

	public int findCount();
}
