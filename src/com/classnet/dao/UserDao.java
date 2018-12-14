package com.classnet.dao;

import com.classnet.entity.UserEntity;

public interface UserDao extends IHibernateSupportDao<UserEntity>{

	public UserEntity getUser(String username);
}
