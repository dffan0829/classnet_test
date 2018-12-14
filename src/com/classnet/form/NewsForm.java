package com.classnet.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

@SuppressWarnings("serial")
public class NewsForm extends ActionForm{

	private String title;  //标题
	private String author;  //作者
	private String source;   //来源
	private String content;  //内容
	private FormFile imgFile; //新闻封面图片
	private int menuId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public FormFile getImgFile() {
		return imgFile;
	}
	public void setImgFile(FormFile imgFile) {
		this.imgFile = imgFile;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
}
