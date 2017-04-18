package com.dms.document.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FileInfo {

	private String fileName;
	private String creator;
	private String modifier;
	private long size;
	private Date created;
	public String getFileName() {
		return fileName;
	}
	@XmlElement
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCreator() {
		return creator;
	}
	@XmlElement
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getModifier() {
		return modifier;
	}
	@XmlElement
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	public long getSize() {
		return size;
	}
	@XmlElement
	public void setSize(long size) {
		this.size = size;
	}
	public Date getCreated() {
		return created;
	}
	@XmlElement
	public void setCreated(Date created) {
		this.created = created;
	}
	

	
}

