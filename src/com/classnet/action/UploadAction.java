package com.classnet.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.classnet.form.UploadForm;
import com.classnet.util.DateUtil;
import com.classnet.util.upload.UploadFileImpl;

public class UploadAction extends Action{

	private String type;
	private int filesize = 1024*1024;
	private String path;
	private String uploadHttpPath;
	public void setUploadHttpPath(String uploadHttpPath) {
		this.uploadHttpPath = uploadHttpPath;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		UploadForm uploadForm = (UploadForm)form;
		FormFile file = uploadForm.getNewFile();
		UploadFileImpl uploadFile = new UploadFileImpl(path+"/images",filesize,type,file);
		uploadFile.save(DateUtil.getDateString());
		out.write("<script type='text/javascript'>");
		out.write("(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;try{document.domain=d;}catch (e){break;}}})(); ");
		out.write("window.parent.OnUploadCompleted(0,'"+uploadHttpPath+"/images/"+uploadFile.getUploadFileName()+"','','') ; ");
		out.write("</script>'");
		return null;
	}
	public void setType(String type) {
		this.type = type;
	}
}
