package com.model;

import java.io.Serializable;

import com.constants.ApplicationConstants;

public class ApplicationFile implements Serializable, Cloneable, ApplicationConstants {

	private static final long serialVersionUID = -6980182144471502902L;
	
	private String filename;
	private byte[] content;
	
	public ApplicationFile() {}
	
	public ApplicationFile(final String filename, final byte[] content) {
		this.filename = filename.replaceAll(WHITESPACE, UNDERSCORE);
		this.content = content;
	}
	
	public String getFilename() {
		return filename;
	}

	public byte[] getContent() {
		return content;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@Override
	public ApplicationFile clone() throws CloneNotSupportedException {  
		return (ApplicationFile)super.clone();
	}
}
