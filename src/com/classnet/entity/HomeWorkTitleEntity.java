package com.classnet.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name="homework_title",catalog="classnet")
public class HomeWorkTitleEntity implements Serializable{

	private Integer id;
	private String title;
	private Date time;
	private String description;
	private List<UserHomeWorkEntity> homeworkList;
	
	private Integer usersubmit=0; //不入数据库
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="title",nullable=false,length=500)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="pubtime")
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@OneToMany(mappedBy="title")
	public List<UserHomeWorkEntity> getHomeworkList() {
		return homeworkList;
	}
	public void setHomeworkList(List<UserHomeWorkEntity> homeworkList) {
		this.homeworkList = homeworkList;
	}
	@Column(name="description",nullable=true,length=65535)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Transient
	public Integer getUsersubmit() {
		return usersubmit;
	}
	public void setUsersubmit(Integer usersubmit) {
		this.usersubmit = usersubmit;
	}
}
