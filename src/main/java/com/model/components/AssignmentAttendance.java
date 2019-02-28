package com.model.components;

import java.io.Serializable;
import java.util.List;

import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class AssignmentAttendance extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {

	private static final long serialVersionUID = -171799632331459916L;
	
	private String assignmentAttendanceSerialId;
	private String packageAssignmentSerialId;
	private Long entryDateTimeMillis;
	private Long exitDateTimeMillis;
	private Integer durationHours;
	private Integer durationMinutes;
	private String topicsTaught;
	private String isClassworkProvided;
	private String isHomeworkProvided;
	private String isTestProvided;
	private String tutorRemarks;
	private String tutorPunctualityIndex;
	private String punctualityRemarks;
	private String tutorExpertiseIndex;
	private String expertiseRemarks;
	private String tutorKnowledgeIndex;
	private String knowledgeRemarks;
	private String studentRemarks;
	private Long recordLastUpdatedMillis;
	private String updatedBy;
	private String updatedByName;
	private List<AssignmentAttendanceDocument> documents;
	
	public AssignmentAttendance() {}
	
	public String getAssignmentAttendanceSerialId() {
		return assignmentAttendanceSerialId;
	}

	public void setAssignmentAttendanceSerialId(String assignmentAttendanceSerialId) {
		this.assignmentAttendanceSerialId = assignmentAttendanceSerialId;
	}

	public String getPackageAssignmentSerialId() {
		return packageAssignmentSerialId;
	}

	public void setPackageAssignmentSerialId(String packageAssignmentSerialId) {
		this.packageAssignmentSerialId = packageAssignmentSerialId;
	}

	public Long getEntryDateTimeMillis() {
		return entryDateTimeMillis;
	}

	public void setEntryDateTimeMillis(Long entryDateTimeMillis) {
		this.entryDateTimeMillis = entryDateTimeMillis;
	}

	public Long getExitDateTimeMillis() {
		return exitDateTimeMillis;
	}

	public void setExitDateTimeMillis(Long exitDateTimeMillis) {
		this.exitDateTimeMillis = exitDateTimeMillis;
	}

	public Integer getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(Integer durationHours) {
		this.durationHours = durationHours;
	}

	public Integer getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(Integer durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public String getTopicsTaught() {
		return topicsTaught;
	}

	public void setTopicsTaught(String topicsTaught) {
		this.topicsTaught = topicsTaught;
	}

	public String getIsClassworkProvided() {
		return isClassworkProvided;
	}

	public void setIsClassworkProvided(String isClassworkProvided) {
		this.isClassworkProvided = isClassworkProvided;
	}

	public String getIsHomeworkProvided() {
		return isHomeworkProvided;
	}

	public void setIsHomeworkProvided(String isHomeworkProvided) {
		this.isHomeworkProvided = isHomeworkProvided;
	}

	public String getIsTestProvided() {
		return isTestProvided;
	}

	public void setIsTestProvided(String isTestProvided) {
		this.isTestProvided = isTestProvided;
	}

	public String getTutorRemarks() {
		return tutorRemarks;
	}

	public void setTutorRemarks(String tutorRemarks) {
		this.tutorRemarks = tutorRemarks;
	}

	public String getTutorPunctualityIndex() {
		return tutorPunctualityIndex;
	}

	public void setTutorPunctualityIndex(String tutorPunctualityIndex) {
		this.tutorPunctualityIndex = tutorPunctualityIndex;
	}

	public String getPunctualityRemarks() {
		return punctualityRemarks;
	}

	public void setPunctualityRemarks(String punctualityRemarks) {
		this.punctualityRemarks = punctualityRemarks;
	}

	public String getTutorExpertiseIndex() {
		return tutorExpertiseIndex;
	}

	public void setTutorExpertiseIndex(String tutorExpertiseIndex) {
		this.tutorExpertiseIndex = tutorExpertiseIndex;
	}

	public String getExpertiseRemarks() {
		return expertiseRemarks;
	}

	public void setExpertiseRemarks(String expertiseRemarks) {
		this.expertiseRemarks = expertiseRemarks;
	}

	public String getTutorKnowledgeIndex() {
		return tutorKnowledgeIndex;
	}

	public void setTutorKnowledgeIndex(String tutorKnowledgeIndex) {
		this.tutorKnowledgeIndex = tutorKnowledgeIndex;
	}

	public String getKnowledgeRemarks() {
		return knowledgeRemarks;
	}

	public void setKnowledgeRemarks(String knowledgeRemarks) {
		this.knowledgeRemarks = knowledgeRemarks;
	}

	public String getStudentRemarks() {
		return studentRemarks;
	}

	public void setStudentRemarks(String studentRemarks) {
		this.studentRemarks = studentRemarks;
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
	
	public List<AssignmentAttendanceDocument> getDocuments() {
		return documents;
	}

	public void setDocuments(List<AssignmentAttendanceDocument> documents) {
		this.documents = documents;
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
			case "assignmentAttendanceSerialId" : return "ASSIGNMENT_ATTENDANCE_SERIAL_ID";
			case "packageAssignmentSerialId" : return "PACKAGE_ASSIGNMENT_SERIAL_ID";
			case "entryDateTimeMillis" : return "ENTRY_DATE_TIME_MILLIS";
			case "exitDateTimeMillis" : return "EXIT_DATE_TIME_MILLIS";
			case "durationHours" : return "DURATION_HOURS";
			case "durationMinutes" : return "DURATION_MINUTES";
			case "topicsTaught" : return "TOPICS_TAUGHT";
			case "isClassworkProvided" : return "CLASSWORK_PROVIDED";
			case "isHomeworkProvided" : return "HOMEWORK_PROVIDED";
			case "isTestProvided" : return "TEST_PROVIDED";
			case "tutorRemarks" : return "TUTOR_REMARKS";
			case "tutorPunctualityIndex" : return "TUTOR_PUNCTUALITY_INDEX";
			case "punctualityRemarks" : return "PUNCTUALITY_REMARKS";
			case "tutorExpertiseIndex" : return "TUTOR_EXPERTISE_INDEX";
			case "expertiseRemarks" : return "EXPERTISE_REMARKS";
			case "tutorKnowledgeIndex" : return "TUTOR_KNOWLEDGE_INDEX";
			case "knowledgeRemarks" : return "KNOWLEDGE_REMARKS";
			case "studentRemarks" : return "STUDENT_REMARKS";
			case "recordLastUpdatedMillis" : return "RECORD_LAST_UPDATED_MILLIS";
			case "updatedBy" : return "UPDATED_BY";
			case "updatedByName" : return "UPDATED_BY_NAME";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public AssignmentAttendance clone() throws CloneNotSupportedException {  
		return (AssignmentAttendance)super.clone();
	}
}
