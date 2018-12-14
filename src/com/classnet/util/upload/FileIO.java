package com.classnet.util.upload;

import java.io.File;
import java.io.IOException;

public class FileIO {

	public static void move(File source,File target){
		move(source,target,true);
	}
	
	public static void move(File source,File target,boolean exist){
		if(!exist){
			if(target.exists()){
				throw new IllegalArgumentException(target.getAbsolutePath()+" exist.");
			}
		}
		source.renameTo(target);
	}
	
	public static void delete(File f)throws IOException{
		if(f.exists())
			throw new IOException("file not found");
		f.delete();
	}
	
	public static void delete(String f)throws IOException{
		delete(new File(f));
	}
}
