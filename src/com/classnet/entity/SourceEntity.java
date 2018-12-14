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
@Table(name="source_table",catalog="classnet")
public class SourceEntity implements Serializable{

	private Integer id;
	private String name;
	private Date pubtime = new Date();
	private String fileSize;
	private String filename;
	private SourceMenuEntity sourceMenu;
	
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
	@Column(name="pubtime")
	public Date getPubtime() {
		return pubtime;
	}
	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}
	@Column(name="fileSize",length=20,nullable=false)
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="menuId")
	public SourceMenuEntity getSourceMenu() {
		return sourceMenu;
	}
	public void setSourceMenu(SourceMenuEntity sourceMenu) {
		this.sourceMenu = sourceMenu;
	}
	@Column(name="filename",length=50,nullable=false)
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
