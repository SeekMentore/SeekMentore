package com.model.components;

import java.io.Serializable;

import com.constants.components.SelectLookupConstants;

public class TutorDocument implements Serializable, SelectLookupConstants {
	
	private static final long serialVersionUID = -1763649873039566289L;
	private Long documentId;
	private Long tutorId;
	private String fsKey;
	private String filename;
	private byte[] content;
	
	public TutorDocument() {}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Long getTutorId() {
		return tutorId;
	}

	public void setTutorId(Long tutorId) {
		this.tutorId = tutorId;
	}

	public String getFsKey() {
		return fsKey;
	}

	public void setFsKey(String fsKey) {
		this.fsKey = fsKey;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}
