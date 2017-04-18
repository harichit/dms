package com.dms.document.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dms.document.constants.CommonConstants;
import com.dms.document.service.CustomDocumentService;
import com.dms.document.service.impl.CustomDocumentServiceImpl;
import com.dms.document.utils.RestServiceUtils;
import com.dms.document.vo.FileInfo;
import com.dms.document.vo.FileInfoVo;
import com.google.gson.Gson;

@RestController
@EnableAutoConfiguration
@RequestMapping("/dms")
public class DocumentController {
	
	 @Autowired
	private CustomDocumentService customAuthenticationService;

	 private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);
@RequestMapping(value = "/upload", method = RequestMethod.POST)
    
    public String authenticate(@Valid FileInfoVo fileInfoVo,BindingResult bindingResult,@RequestParam(value = "file", required = false) MultipartFile file) {
	String response=null;
    	
         try {

             // Get the file and save it somewhere
             byte[] bytes = file.getBytes();
             Path path = Paths.get(CommonConstants.FILE_FOLDER_PATH + file.getOriginalFilename());
             Files.write(path, bytes);
             FileInfo fileInfo = new FileInfo();
             fileInfo.setFileName(file.getOriginalFilename());
             fileInfo.setCreator(fileInfoVo.getCreator());
             fileInfo.setModifier(fileInfoVo.getModifier());
             fileInfo.setSize(file.getSize());
             fileInfo.setCreated(new Date());
             File fileObj = new File(CommonConstants.FILE_FOLDER_PATH+RestServiceUtils.getFileNameWithoutExtn(fileInfo.getFileName())+".xml");
             this.customAuthenticationService.createMetadataXML(fileInfo, fileObj);
             response = "Document uploaded successfully";
             
         } catch (IOException e) {
        	 logger.error("error occured while uploading document");
        	 response = "Document failed to upload";
             return response;
         }
    	
    	return response;
    }
	
	@RequestMapping(value = "/fileInfo", method = RequestMethod.GET)
 
 public String getFileInfo(@RequestParam("fileName") String fileName) {
	String response=null;
 	  	
      try {
    	          
          File fileObj = new File(CommonConstants.FILE_FOLDER_PATH+RestServiceUtils.getFileNameWithoutExtn(fileName)+".xml");
                    
          FileInfo fileInfo = this.customAuthenticationService.getMetadataXML(fileObj);
  		
  		Gson gson = new Gson();
  		String jsonInString = gson.toJson(fileInfo);
  		response = jsonInString;

      } catch (Exception e) {
    	  logger.error("error occured while fectching metadata of document");
     	 response = "Requested document is not found";
          return response;
      }
 	
 	return response;
 }
 
 
}
