package com.classnet.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name="news_menu",catalog="classnet")
public class NewsMenuEntity implements Serializable{

	private Integer id;
	private String name;
	private List<NewsEntity> newsList = new ArrayList<NewsEntity>();
	private NewsEntity newsEntity; //栏目头条新闻
	
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
	@OneToMany(fetch=FetchType.EAGER,mappedBy="newsMenu")
	public List<NewsEntity> getNewsList() {
		return newsList;
	}
	public void setNewsList(List<NewsEntity> newsList) {
		this.newsList = newsList;
	}
	@Transient
	public NewsEntity getNewsEntity() {
		return newsEntity;
	}
	public void setNewsEntity(NewsEntity newsEntity) {
		this.newsEntity = newsEntity;
	}
}
