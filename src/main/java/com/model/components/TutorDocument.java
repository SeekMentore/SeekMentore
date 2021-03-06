package com.model.components;

import java.io.Serializable;

import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class TutorDocument extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -1763649873039566289L;
	
	private String documentSerialId;
	private String tutorSerialId;
	private String documentType;
	private String fsKey;
	private String filename;
	private String isApproved;
	private String whoActed;
	private String whoActedName;
	private String remarks;
	private Long actionDateMillis;
	private byte[] content;
	// Action Button Security Properties
	private Boolean showApprove = false;
	private Boolean enableApprove = false;
	private Boolean showSendReminder = false;
	private Boolean enableSendReminder = false;
	private Boolean showReject = false;
	private Boolean enableReject = false;
	
	public TutorDocument() {}
	
	public TutorDocument(final String documentType) {
		this.documentType = documentType;
	}
	
	public TutorDocument(final String documentType, final String filename, final byte[] content) {
		this.documentType = documentType;
		this.filename = filename;
		this.content = content;
	}
	
	public String getDocumentSerialId() {
		return documentSerialId;
	}

	public void setDocumentSerialId(String documentSerialId) {
		this.documentSerialId = documentSerialId;
	}

	public String getTutorSerialId() {
		return tutorSerialId;
	}

	public void setTutorSerialId(String tutorSerialId) {
		this.tutorSerialId = tutorSerialId;
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
	
	public Boolean getShowApprove() {
		return showApprove;
	}

	public void setShowApprove(Boolean showApprove) {
		this.showApprove = showApprove;
	}

	public Boolean getEnableApprove() {
		return enableApprove;
	}

	public void setEnableApprove(Boolean enableApprove) {
		this.enableApprove = enableApprove;
	}

	public Boolean getShowSendReminder() {
		return showSendReminder;
	}

	public void setShowSendReminder(Boolean showSendReminder) {
		this.showSendReminder = showSendReminder;
	}

	public Boolean getEnableSendReminder() {
		return enableSendReminder;
	}

	public void setEnableSendReminder(Boolean enableSendReminder) {
		this.enableSendReminder = enableSendReminder;
	}

	public Boolean getShowReject() {
		return showReject;
	}

	public void setShowReject(Boolean showReject) {
		this.showReject = showReject;
	}

	public Boolean getEnableReject() {
		return enableReject;
	}

	public void setEnableReject(Boolean enableReject) {
		this.enableReject = enableReject;
	}

	@Override
	public Object[] getReportHeaders(String reportSwitch) {
		return null;
	}

	@Override
	public Object[] getReportRecords(String reportSwitch) {
		return null;
	}
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "documentSerialId" : return "DOCUMENT_SERIAL_ID";
			case "documentType" : return "DOCUMENT_TYPE";
			case "tutorSerialId" : return "TUTOR_SERIAL_ID";
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
	
	@Override
	public TutorDocument clone() throws CloneNotSupportedException {  
		return (TutorDocument)super.clone();
	}
}
