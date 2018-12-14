package com.classnet.util.upload;

import java.io.IOException;

public interface UploadFile {

	public abstract int getFileSize();

	public abstract void save() throws IOException, UploadException;

	public abstract void save(String fileName) throws IOException, UploadException;

	public abstract String getFilename();
	
	public abstract String getUploadFileName();
	
	public abstract String getFullpath();
}
