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
@Table(name="topic_menu",catalog="classnet")
public class TopicMenuEntity implements Serializable{

	private Integer id;
	private String name;
	private List<TopicEntity> topicList = new ArrayList<TopicEntity>();
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="name",length=50,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(mappedBy="menuEntity")
	public List<TopicEntity> getTopicList() {
		return topicList;
	}
	public void setTopicList(List<TopicEntity> topicList) {
		this.topicList = topicList;
	}
}
