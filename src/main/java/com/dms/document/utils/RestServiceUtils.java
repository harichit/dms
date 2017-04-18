package com.dms.document.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class RestServiceUtils{

	private static final Logger logger = LoggerFactory.getLogger(RestServiceUtils.class);  
	
	public static String getFileNameWithoutExtn(String fileName){
		String fileNameVal = null;
		
		String[] fileNameStr = fileName.split("\\.(?=[^\\.]+$)");
		
		fileNameVal = fileNameStr[0];
		
		return fileNameVal;
	}
	
}

