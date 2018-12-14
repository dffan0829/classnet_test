package com.classnet.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.springframework.beans.BeanUtils;

import com.classnet.entity.NewsEntity;
import com.classnet.entity.StudentInfoEntities;
import com.classnet.form.NewsForm;
import com.classnet.util.upload.UploadFileImpl;

public class StuInfoFileUploadAction extends DispatchAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String path;
	private String type;
	private Integer filesize=1024*1024;
	private StudentDao studentDao;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getFilesize() {
		return filesize;
	}

	public void setFilesize(Integer filesize) {
		this.filesize = filesize;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public ActionForward doExcelImport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			NewsForm newsForm = (NewsForm)form;
			NewsEntity entity = new NewsEntity();
			BeanUtils.copyProperties(newsForm, entity);
			FormFile file = newsForm.getImgFile();
			Workbook wb = new HSSFWorkbook(file.getInputStream());
			Sheet sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum() + 1;
			List<StudentInfoEntities> list = new ArrayList<StudentInfoEntities>();
			for (int i = 0; i < rowNum; i++) {
				Row row = sheet.getRow(i);
				int cellNum = row.getLastCellNum();
				StudentInfoEntities entities = new StudentInfoEntities();
				for (int j = 0; j < cellNum; j++) {
					Cell cell = row.getCell(j);
					switch (j) {// 通过列数来判断对应插如的字段
					case 0:
						entities.setStuName(cell.getStringCellValue());
						break;
					case 1:
						entities.setGender(cell.getStringCellValue());
						break;
					case 2:
						entities.setStuNo((int) cell.getNumericCellValue());
						break;
					case 3:
						entities.setAddress(cell.getStringCellValue());
						break;
					case 4:
						entities.setStuTel(String.valueOf((int) cell.getNumericCellValue()));
						break;
					case 5:
						entities.setEmail(cell.getStringCellValue());
						break;

					}
				}
				list.add(entities);
			}
			studentDao.exportStuInfo(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapping.findForward("stulst");
	}

}