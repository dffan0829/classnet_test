package com.classnet.dao;

import com.classnet.entity.UserHomeWorkEntity;

public interface UserHomeWorkDao extends IHibernateSupportDao<UserHomeWorkEntity>{

	public boolean findByUserIdAndTitleId(int userId, int titleId);
}
