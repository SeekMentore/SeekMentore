package com.model.components;

import java.io.Serializable;
import java.util.Date;

public class TutorDocument implements Serializable {
	
	private static final long serialVersionUID = -1763649873039566289L;
	private Long documentId;
	private Long tutorId;
	private String fsKey;
	private String filename;
	private String isApproved;
	private String whoActed;
	private String remarks;
	private Date actionDate;
	private Long actionDateMillis;
	private byte[] content;
	
	public TutorDocument() {}
	
	public TutorDocument(Long documentId) {
		this.documentId = documentId;
		tutorId = 1L;
		fsKey = "random key";
		filename = "Fake file";
		isApproved = documentId%2 == 0 ? "Y" : "N";
		whoActed = "abcf";
		remarks = "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test";
		actionDate = new Date();
		actionDateMillis = actionDate.getTime();			
	}

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

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public String getWhoActed() {
		return whoActed;
	}

	public void setWhoActed(String whoActed) {
		this.whoActed = whoActed;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getActionDateMillis() {
		return actionDateMillis;
	}

	public void setActionDateMillis(Long actionDateMillis) {
		this.actionDateMillis = actionDateMillis;
	}
}
