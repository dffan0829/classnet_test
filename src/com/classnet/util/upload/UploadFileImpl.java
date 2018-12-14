package com.classnet.util.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;

/**
 * 
 * 此类包含了关于上传文件的信息
 * 
 */
public class UploadFileImpl implements UploadFile, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String path = "D:/upload/";// 上传的路径;

	private int size;// 上传文件的大小
	private int filesize;//当前文件的大小
	private String fileType;// 文件类型

	private FormFile file;

	static final Log log = LogFactory.getLog(UploadFile.class);

	public void setFile(FormFile file) {
		this.file = file;
	}


	/**
	 * 判断类型是否可以上传
	 * 
	 * @param type
	 * @param array
	 * @return flag
	 */
	public boolean isFileType(String type, String array) {
		if (array.indexOf(type) == -1)
			return false;
		else
			return true;
	}

	/**
	 * 得到文件类型。
	 */
	public String getType(String str) {
		return str.substring(str.lastIndexOf(".") + 1, str.length());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shsct.eq.util.upload.IUploadFile#getType()
	 */
	public String getType() {
		return getType(file.getFileName());
	}

	/**
	 * 去掉文件名后辍
	 * 
	 * @param fileName
	 * @return
	 */
	public String removeType(String fileName) {
		if (fileName.lastIndexOf(".") != -1) {
			int le = fileName.lastIndexOf(".");
			fileName = fileName.substring(0, le);
		}
		return fileName;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shsct.eq.util.upload.IUploadFile#save()
	 */
	public void save() throws IOException, UploadException {
		if (this.file == null)
			throw new UploadException("文件不存在");
		save(removeType(file.getFileName()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shsct.eq.util.upload.IUploadFile#save(java.lang.String)
	 */
	public void save(String fileName) throws UploadException,IOException{
		if(isFileSizeMax())
			throw new UploadException("文件超过了指定的容量,文件只支持"+sizeByStr());
		filename = file.getFileName();
		BufferedInputStream in = null;
		BufferedOutputStream stream = null;
		try {

			if (file != null) {
				String type = getType(filename).toLowerCase(); // 上传的文件,并且转为小写
				if (!StringUtils.isEmpty(fileType)) {
					if (!isFileType(type, fileType.toLowerCase())) {
						throw new Exception("文件格式不正确");
					}
				}
				uploadFileName=FileHelper.joinFile(fileName, type);

				in = new BufferedInputStream(file.getInputStream());

				if (!StringUtils.isEmpty(fileName)) {
					fullpath=FileHelper.joinPath(path, uploadFileName);
				} else {
					fullpath=FileHelper.joinPath(path, filename);
				}
				stream = new BufferedOutputStream(
						new FileOutputStream(fullpath));
				int buf = 1024 * 6;
				byte[] bufByte = new byte[buf];
				while (in.read(bufByte) != -1) {
					stream.write(bufByte);
				}
				stream.flush();
				stream.close();
				stream = null;
				in.close();
				in = null;
			} else {
				throw new IOException("上传的文件不存在");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			throw new UploadException(e);
		} finally {
			if (stream != null) {
				stream.close();
				stream = null;
			}
			if (in != null) {
				in.close();
				in = null;
			}
		}
		System.gc();
	}

	
	/**
	 * 用户只有设size大于0时才做判断
	 * @return
	 */
	boolean isFileSizeMax() {
		if (size > 0) {
			// log.info(file.getFileSize());
			if (file.getFileSize() > size) {
				return true;
			}
		}
		return false;
	}

	public String sizeByStr() {

		if (size < 1024 * 1024 && size > 0) {
			return ((int) ((int) (size / 1024 * 1000)) / 1000) + "k";
		} else if (size > 1024 * 1024 && size < 1024 * 1024 * 1024) {
			return ((int) ((int) (size / (1024 * 1024) * 1000)) / 1000) + "M";
		} else {
			return "0k";
		}
	}

	public String sizeByStr1() {// 重载sizeByStr方法

		if (size > 0 && size < 1024 * 1024 * 1024) {
			return "true";
		} else {
			return "false";
		}
	}

	public UploadFileImpl(String path, int size,String filetype, FormFile file) {
		super();
		/**
		 * "jpg,gif"
		 */
		// TODO Auto-generated constructor stub
		if(file==null||file.getFileSize()<1)
			throw new IllegalArgumentException("上传的文件不存在");
		this.path = path;
		this.size = size;
		this.fileType=filetype;
		this.file = file;
		
		MkDir md = new MkDir();
		md.mkDir(path);
		filesize=file.getFileSize();
	}

	public String getFilename() {
		// TODO Auto-generated method stub
		return filename;
	}

	public String getFullpath() {
		// TODO Auto-generated method stub
		return fullpath;
	}

	public String getUploadFileName() {
		// TODO Auto-generated method stub
		return uploadFileName;
	}
	private String uploadFileName;
	private String fullpath;
	private String filename;

	public int getFileSize() {
		// TODO Auto-generated method stub
		return filesize;
	}
}
