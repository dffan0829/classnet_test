package com.classnet.dao;

import com.classnet.entity.NewsEntity;

public interface NewsDao extends IHibernateSupportDao<NewsEntity>{

	public int findCount();
}
