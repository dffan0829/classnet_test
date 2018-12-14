package com.classnet.util.upload;

import java.io.File;

public class MkDir {

    public MkDir() {

    }
    public void mkDir(String path){    
    	try{
	      File file = new File(path);     
	      if(!file.exists()||file==null){           
	         file.mkdirs();  
	      }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    public boolean Exist(String director,String fileName){
    	return Exist(director+"\\"+fileName);
    }
    
    public boolean Exist(String fileName){
        boolean flg=false;
    	try{
    		File file=new File(fileName);         		
    		if(file.exists()){
    			flg=true;
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return flg;
    }
}
