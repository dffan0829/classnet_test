package com.classnet.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="clazz_menu",catalog="classnet")
public class ClazzMenuEntity implements Serializable{

	private Integer id;
	private String name;
	private ClazzMenuEntity parentMenuEntity;
	private List<ClazzMenuEntity> childMenuList = new ArrayList<ClazzMenuEntity>();
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
	@ManyToOne(fetch=FetchType.EAGER,optional=true)
	@JoinColumn(name="parentId")
	public ClazzMenuEntity getParentMenuEntity() {
		return parentMenuEntity;
	}
	public void setParentMenuEntity(ClazzMenuEntity parentMenuEntity) {
		this.parentMenuEntity = parentMenuEntity;
	}
	@OneToMany(mappedBy="parentMenuEntity")
	public List<ClazzMenuEntity> getChildMenuList() {
		return childMenuList;
	}
	public void setChildMenuList(List<ClazzMenuEntity> childMenuList) {
		this.childMenuList = childMenuList;
	}
	@OneToMany(mappedBy="clazzMenu")
	public List<ClazzEntity> getClazzList() {
		return clazzList;
	}
	public void setClazzList(List<ClazzEntity> clazzList) {
		this.clazzList = clazzList;
	}
	
}
