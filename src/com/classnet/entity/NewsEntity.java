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
@Table(name="news",catalog="classnet")
public class NewsEntity implements Serializable{

	private Integer id;
	private String title;  //标题
	private String author;  //作者
	private String source;   //来源
	private Date pubtime=new Date();  //发布时间
	private String content;  //内容
	private Integer viewNum=0;  //浏览数目
	private String img; //新闻封面图片
	private Integer status=1; //新闻状态 1普通，2推荐， 3栏目头条, 4首页flash 图片新闻
	private NewsMenuEntity newsMenu; //目录
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="title",length=50,nullable=false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="author",length=10,nullable=true)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(name="source",length=20,nullable=true)
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Column(name="pubtime")
	public Date getPubtime() {
		return pubtime;
	}
	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}
	@Column(name="content",length=65535,nullable=false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="viewnum")
	public Integer getViewNum() {
		return viewNum;
	}
	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="menuId")
	public NewsMenuEntity getNewsMenu() {
		return newsMenu;
	}
	public void setNewsMenu(NewsMenuEntity newsMenu) {
		this.newsMenu = newsMenu;
	}
	@Column(name="img",length=50,nullable=true)
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
