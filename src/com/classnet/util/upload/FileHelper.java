package com.classnet.util.upload;

public class FileHelper {

	
	/**
	 * ����ļ���
	 * @param filename
	 * @param type
	 * @return
	 */
	public static String joinFile(String filename, String type) {
		return new StringBuffer(filename).append(".").append(type).toString();
	}

	
	/**
	 * ����ļ�·��
	 * @param path
	 * @param file
	 * @return
	 */
	public static String joinPath(String path, String file) {
		return new StringBuffer(path).append("/").append(file).toString();
	}
}
