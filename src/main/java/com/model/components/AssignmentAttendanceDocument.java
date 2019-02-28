package com.model.components;

import java.io.Serializable;

import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class AssignmentAttendanceDocument extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {

	private static final long serialVersionUID = -171799632331459916L;
	
	private String assignmentAttendanceDocumentSerialId;
	private String assignmentAttendanceSerialId;
	private String documentType;
	private String fsKey;
	private String filename;
	private Long actionDateMillis;
	private String whoActed;
	private String whoActedName;
	private Long recordLastUpdatedMillis;
	private String updatedBy;
	private String updatedByName;
	private byte[] content;
	
	public AssignmentAttendanceDocument() {}
	
	public AssignmentAttendanceDocument(final String documentType, final String filename, final byte[] content) {
		this.documentType = documentType;
		this.filename = filename;
		this.content = content;
	}
	
	public String getAssignmentAttendanceDocumentSerialId() {
		return assignmentAttendanceDocumentSerialId;
	}

	public void setAssignmentAttendanceDocumentSerialId(String assignmentAttendanceDocumentSerialId) {
		this.assignmentAttendanceDocumentSerialId = assignmentAttendanceDocumentSerialId;
	}

	public String getAssignmentAttendanceSerialId() {
		return assignmentAttendanceSerialId;
	}

	public void setAssignmentAttendanceSerialId(String assignmentAttendanceSerialId) {
		this.assignmentAttendanceSerialId = assignmentAttendanceSerialId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
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

	public Long getActionDateMillis() {
		return actionDateMillis;
	}

	public void setActionDateMillis(Long actionDateMillis) {
		this.actionDateMillis = actionDateMillis;
	}

	public String getWhoActed() {
		return whoActed;
	}

	public void setWhoActed(String whoActed) {
		this.whoActed = whoActed;
	}

	public String getWhoActedName() {
		return whoActedName;
	}

	public void setWhoActedName(String whoActedName) {
		this.whoActedName = whoActedName;
	}

	public Long getRecordLastUpdatedMillis() {
		return recordLastUpdatedMillis;
	}

	public void setRecordLastUpdatedMillis(Long recordLastUpdatedMillis) {
		this.recordLastUpdatedMillis = recordLastUpdatedMillis;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
	
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
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
			case "assignmentAttendanceDocumentSerialId" : return "ASSIGNMENT_ATTENDANCE_DOCUMENT_SERIAL_ID";
			case "assignmentAttendanceSerialId" : return "ASSIGNMENT_ATTENDANCE_SERIAL_ID";
			case "documentType" : return "DOCUMENT_TYPE";
			case "fsKey" : return "FS_KEY";
			case "filename" : return "FILENAME";
			case "actionDateMillis" : return "ACTION_DATE_MILLIS";
			case "whoActed" : return "WHO_ACTED";
			case "whoActedName" : return "WHO_ACTED_NAME";
			case "recordLastUpdatedMillis" : return "RECORD_LAST_UPDATED_MILLIS";
			case "updatedBy" : return "UPDATED_BY";
			case "updatedByName" : return "UPDATED_BY_NAME";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public AssignmentAttendanceDocument clone() throws CloneNotSupportedException {  
		return (AssignmentAttendanceDocument)super.clone();
	}
}
