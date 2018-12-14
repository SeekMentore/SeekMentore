package com.model.components;

import java.io.Serializable;

import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class TutorDocument extends GridComponentObject implements Serializable {
	
	private static final long serialVersionUID = -1763649873039566289L;
	
	private Long documentId;
	private Long tutorId;
	private String documentType;
	private String fsKey;
	private String filename;
	private String isApproved;
	private String whoActed;
	private String whoActedName;
	private String remarks;
	private Long actionDateMillis;
	private byte[] content;
	
	public TutorDocument() {}
	
	public TutorDocument(final String documentType, String filename, final byte[] content) {
		this.documentType = documentType;
		this.content = content;
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
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "documentId" : return "DOCUMENT_ID";
			case "documentType" : return "DOCUMENT_TYPE";
			case "tutorId" : return "TUTOR_ID";
			case "fsKey" : return "FS_KEY";
			case "filename" : return "FILENAME";
			case "isApproved" : return "IS_APPROVED";
			case "whoActed" : return "WHO_ACTED";
			case "whoActedName" : return "WHO_ACTED_NAME";
			case "actionDateMillis" : return "ACTION_DATE_MILLIS";
			case "remarks" : return "REMARKS";
		}
		return EMPTY_STRING;
	}

	public String getWhoActedName() {
		return whoActedName;
	}

	public void setWhoActedName(String whoActedName) {
		this.whoActedName = whoActedName;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
}
