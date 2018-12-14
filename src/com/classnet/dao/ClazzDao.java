package com.classnet.dao;

import com.classnet.entity.ClazzEntity;

public interface ClazzDao extends IHibernateSupportDao<ClazzEntity>{

	public int findCount();
}
