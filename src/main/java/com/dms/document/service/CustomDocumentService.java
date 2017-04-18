package com.dms.document.service;
import java.io.File;
import com.dms.document.vo.FileInfo;

public interface CustomDocumentService {
	public void createMetadataXML(FileInfo fileInfo,File fileObj);
	public FileInfo getMetadataXML(File fileObj);
}
