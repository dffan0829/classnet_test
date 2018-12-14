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
@Table(name="clazz_table",catalog="classnet")
public class ClazzEntity implements Serializable{

	private Integer id;
	private String name;
	private String author;
	private FileTypeEntity fileType;
	private ClazzMenuEntity clazzMenu;
	private Date pubtime = new Date();
	private String school;
	private String filename;
	private String flashFilename;
	private Integer viewCount = 0;
	private Integer status = 1; //1正常 2推荐
	private String img;//封面图片
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="name",length=100,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="author",length=10,nullable=true)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="filetype")
	public FileTypeEntity getFileType() {
		return fileType;
	}
	public void setFileType(FileTypeEntity fileType) {
		this.fileType = fileType;
	}
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="menuId")
	public ClazzMenuEntity getClazzMenu() {
		return clazzMenu;
	}
	public void setClazzMenu(ClazzMenuEntity clazzMenu) {
		this.clazzMenu = clazzMenu;
	}
	@Column(name="pubtime")
	public Date getPubtime() {
		return pubtime;
	}
	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}
	@Column(name="filename",length=40,nullable=false)
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Column(name="flashFilename",length=40,nullable=true)
	public String getFlashFilename() {
		return flashFilename;
	}
	public void setFlashFilename(String flashFilename) {
		this.flashFilename = flashFilename;
	}
	@Column(name="school",length=50,nullable=true)
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	@Column(name="viewcount")
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name="img",length=40,nullable=true)
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
