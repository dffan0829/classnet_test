package com.classnet.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="userhoumework_table",catalog="classnet")
public class UserHomeWorkEntity implements Serializable{

	private Integer id;
	private UserEntity userEntity;
	private HomeWorkTitleEntity title;
	private String name;
	private Date addtime;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="name",nullable=false,length=200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="addtime")
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="titleId")
	public HomeWorkTitleEntity getTitle() {
		return title;
	}
	public void setTitle(HomeWorkTitleEntity title) {
		this.title = title;
	}
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="userId")
	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
}
