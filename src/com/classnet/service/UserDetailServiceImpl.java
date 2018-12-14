package com.classnet.service;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import com.classnet.dao.UserDao;
import com.classnet.entity.UserEntity;

public class UserDetailServiceImpl implements UserDetailsService {

	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDetailServiceImpl() {
	}

	@SuppressWarnings("deprecation")
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		UserEntity entity = userDao.getUser(username);
		if (entity == null)
			throw new UsernameNotFoundException("user don't exist");
		GrantedAuthority authorities[] = new GrantedAuthority[]{new GrantedAuthorityImpl(entity.getAuthorite())};
		return new User(entity.getUsername(),entity.getPassword(),entity.isEnable(),authorities);
	}

	public static void main(String args[]) throws Exception {
		ApplicationContext acx = new ClassPathXmlApplicationContext(
				new String[] { "classpath:applicationContext.xml",
						"classpath:applicationContext-acegi1.xml" });
		UserDetailsService detail = (UserDetailsService) acx
				.getBean("userDetailServiceImpl");
		UserDetails ud = detail.loadUserByUsername("feng");
		System.out
				.println((new StringBuilder(String.valueOf(ud.getUsername())))
						.append("     ").append(ud.getPassword()).toString());
	}


}