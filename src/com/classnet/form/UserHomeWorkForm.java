package com.classnet.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

@SuppressWarnings("serial")
public class UserHomeWorkForm extends ActionForm{

	private int titleId;
	private FormFile workfile;
	public int getTitleId() {
		return titleId;
	}
	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}
	public FormFile getWorkfile() {
		return workfile;
	}
	public void setWorkfile(FormFile workfile) {
		this.workfile = workfile;
	}
	
}
