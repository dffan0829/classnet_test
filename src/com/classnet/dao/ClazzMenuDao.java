package com.classnet.dao;

import com.classnet.entity.ClazzMenuEntity;

public interface ClazzMenuDao extends IHibernateSupportDao<ClazzMenuEntity>{

	public void delete(int id);
}
