package com.classnet.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

@SuppressWarnings("serial")
public class ClazzForm extends ActionForm{

	private String name;
	private String author;
	private int filetypeId;
	private int menuId;
	private String school;
	private FormFile imgFile;
	private FormFile file;
	private FormFile flashFile;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getFiletypeId() {
		return filetypeId;
	}
	public void setFiletypeId(int filetypeId) {
		this.filetypeId = filetypeId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public FormFile getFlashFile() {
		return flashFile;
	}
	public void setFlashFile(FormFile flashFile) {
		this.flashFile = flashFile;
	}
	public FormFile getImgFile() {
		return imgFile;
	}
	public void setImgFile(FormFile imgFile) {
		this.imgFile = imgFile;
	}
}
