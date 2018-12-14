package com.classnet.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Table(name="topic_table",catalog="classnet")
public class TopicEntity implements Serializable{

	private Integer id;
	private String title;
	private String detail;
	private UserEntity userEntity;
	private TopicMenuEntity menuEntity;
	private Date pubtime;
	private Integer replyNum=0;//回复数
	private String editUser; //更新人
	private Date editTime;   //更新时间
	private List<AnswerEntity> answerList = new ArrayList<AnswerEntity>();
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="title",length=400,nullable=false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="detail",length=65535,nullable=false)
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="userId")
	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="menuId")
	public TopicMenuEntity getMenuEntity() {
		return menuEntity;
	}
	public void setMenuEntity(TopicMenuEntity menuEntity) {
		this.menuEntity = menuEntity;
	}
	@Column(name="pubtime")
	public Date getPubtime() {
		return pubtime;
	}
	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}
	@Column(name="replynum")
	public Integer getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}
	@Column(name="edituser",length=50,nullable=true)
	public String getEditUser() {
		return editUser;
	}
	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}
	@Column(name="edittime")
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	@OneToMany(mappedBy="topicEntity")
	public List<AnswerEntity> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<AnswerEntity> answerList) {
		this.answerList = answerList;
	}
}
