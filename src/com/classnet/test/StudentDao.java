package com.classnet.test;

import java.util.List;

import com.classnet.dao.IHibernateSupportDao;
import com.classnet.entity.StudentInfoEntities;

public interface StudentDao extends IHibernateSupportDao<StudentInfoEntities>{
  public boolean exportStuInfo(List<StudentInfoEntities> list);
}
