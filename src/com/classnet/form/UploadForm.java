package com.classnet.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

@SuppressWarnings("serial")
public class UploadForm extends ActionForm{

	private FormFile newFile;

	public FormFile getNewFile() {
		return newFile;
	}

	public void setNewFile(FormFile newFile) {
		this.newFile = newFile;
	}
	
}
