package com.classnet.dao;

import com.classnet.entity.SourceEntity;

public interface SourceDao extends IHibernateSupportDao<SourceEntity>{

	public int findCount();
}
