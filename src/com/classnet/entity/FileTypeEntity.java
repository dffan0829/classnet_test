package com.classnet.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="file_type",catalog="classnet")
public class FileTypeEntity implements Serializable{

	private Integer id;
	private String name;
	private List<ClazzEntity> clazzList = new ArrayList<ClazzEntity>();
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="name",length=20,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(mappedBy="fileType")
	public List<ClazzEntity> getClazzList() {
		return clazzList;
	}
	public void setClazzList(List<ClazzEntity> clazzList) {
		this.clazzList = clazzList;
	}
}
