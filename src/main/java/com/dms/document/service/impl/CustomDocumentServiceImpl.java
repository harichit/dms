package com.dms.document.service.impl;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.dms.document.service.CustomDocumentService;
import com.dms.document.vo.FileInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomDocumentServiceImpl implements CustomDocumentService{

	private static final Logger logger = LoggerFactory.getLogger(CustomDocumentServiceImpl.class);
	
	
	@Override
	public void createMetadataXML(FileInfo fileInfo,File fileObj) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(FileInfo.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(fileInfo, fileObj);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public FileInfo getMetadataXML(File fileObj) {
		JAXBContext jaxbContext;
		FileInfo fileInfo =null;
		try {
			jaxbContext = JAXBContext.newInstance(FileInfo.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	  		fileInfo = (FileInfo) jaxbUnmarshaller.unmarshal(fileObj);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

  		
  		return fileInfo;
		
	}
	
	
}
