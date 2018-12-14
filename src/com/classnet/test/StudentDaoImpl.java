package com.classnet.test;

import java.util.List;
import java.util.UUID;

import com.classnet.dao.impl.HibernateSupportDao;
import com.classnet.entity.StudentInfoEntities;

public class StudentDaoImpl extends HibernateSupportDao<StudentInfoEntities> implements StudentDao{

	@Override
	public boolean exportStuInfo(List<StudentInfoEntities> list) {
		for (StudentInfoEntities studentInfoEntities : list) {
			studentInfoEntities.setClassno(1002);
			//studentInfoEntities.setRowguid(UUID.randomUUID().toString());
			sessionFactory.getCurrentSession().save(studentInfoEntities);
		}
		return false;
	}
 
}
