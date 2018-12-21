package com.model.components;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.constants.components.AdminConstants;
import com.constants.components.SelectLookupConstants;
import com.constants.components.publicaccess.RegisteredTutorConstants;
import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ApplicationUtils;
import com.utils.DateUtils;
import com.utils.ValidationUtils;

public class RegisteredTutor extends GridComponentObject implements Serializable, RegisteredTutorConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -1763649873039566289L;
	
	private Long tutorId;
	private String name;
	private String contactNumber;
	private String emailId;
	private Long tentativeTutorId;
	private Date dateOfBirth;
	private String gender;
	private String qualification;
	private String primaryProfession;
	private String transportMode;
	private Integer teachingExp;
	private String interestedStudentGrades;
	private String interestedSubjects;
	private String comfortableLocations;
	private String additionalDetails;
	private String encryptedPassword;
	private String userId;
	private Long recordLastUpdatedMillis;
	private String updatedBy;
	private String updatedByName;
	private String preferredTeachingType;
	private String addressDetails;
	private List<TutorDocument> documents;
	
	public RegisteredTutor() {}
	
	public RegisteredTutor getACopy() {
		final RegisteredTutor newInstance = new RegisteredTutor();
		newInstance.tutorId = tutorId;
		newInstance.name = name;
		newInstance.contactNumber = contactNumber;
		newInstance.emailId = emailId;
		newInstance.tentativeTutorId = tentativeTutorId;
		newInstance.dateOfBirth = dateOfBirth;
		newInstance.gender = gender;
		newInstance.qualification = qualification;
		newInstance.primaryProfession = primaryProfession;
		newInstance.transportMode = transportMode;
		newInstance.teachingExp = teachingExp;
		newInstance.interestedStudentGrades = interestedStudentGrades;
		newInstance.interestedSubjects = interestedSubjects;
		newInstance.comfortableLocations = comfortableLocations;
		newInstance.additionalDetails = additionalDetails;
		newInstance.encryptedPassword = encryptedPassword;
		newInstance.userId = userId;
		newInstance.recordLastUpdatedMillis = recordLastUpdatedMillis;
		newInstance.updatedBy = updatedBy;
		newInstance.preferredTeachingType = preferredTeachingType;
		newInstance.addressDetails = addressDetails;
		return newInstance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getPrimaryProfession() {
		return primaryProfession;
	}

	public void setPrimaryProfession(String primaryProfession) {
		this.primaryProfession = primaryProfession;
	}

	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	public String getInterestedStudentGrades() {
		return interestedStudentGrades;
	}

	public void setInterestedStudentGrades(String interestedStudentGrades) {
		this.interestedStudentGrades = interestedStudentGrades;
	}

	public String getInterestedSubjects() {
		return interestedSubjects;
	}

	public void setInterestedSubjects(String interestedSubjects) {
		this.interestedSubjects = interestedSubjects;
	}

	public String getComfortableLocations() {
		return comfortableLocations;
	}

	public void setComfortableLocations(String comfortableLocations) {
		this.comfortableLocations = comfortableLocations;
	}

	public String getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getTutorId() {
		return tutorId;
	}

	public void setTutorId(Long tutorId) {
		this.tutorId = tutorId;
	}

	public Long getTentativeTutorId() {
		return tentativeTutorId;
	}

	public void setTentativeTutorId(Long tentativeTutorId) {
		this.tentativeTutorId = tentativeTutorId;
	}

	public Integer getTeachingExp() {
		return teachingExp;
	}

	public void setTeachingExp(Integer teachingExp) {
		this.teachingExp = teachingExp;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getPreferredTeachingType() {
		return preferredTeachingType;
	}

	public void setPreferredTeachingType(String preferredTeachingType) {
		this.preferredTeachingType = preferredTeachingType;
	}

	public List<TutorDocument> getDocuments() {
		return documents;
	}

	public void setDocuments(List<TutorDocument> documents) {
		this.documents = documents;
	}

	public Long getRecordLastUpdatedMillis() {
		return recordLastUpdatedMillis;
	}

	public void setRecordLastUpdatedMillis(Long recordLastUpdatedMillis) {
		this.recordLastUpdatedMillis = recordLastUpdatedMillis;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}
	
	@Override
	public Object[] getReportHeaders(String reportSwitch) {
		switch (reportSwitch) {
			case AdminConstants.ADMIN_REPORT : {
				return new Object[] {
						"TUTOR_ID",
						"BECOME_TUTOR_ID",
						"USER_ID",
						"NAME",
						"CONTACT_NUMBER",
						"EMAIL_ID",
						"DATE_OF_BIRTH",
						"GENDER",
						"QUALIFICATION",
						"PRIMARY_PROFESSION",
						"TRANSPORT_MODE",
						"TEACHING_EXP",
						"INTERESTED_STUDENT_GRADES",
						"INTERESTED_SUBJECTS",
						"COMFORTABLE_LOCATIONS",
						"ADDITIONAL_DETAILS",
						"RECORD_LAST_UPDATED",
						"UPDATED_BY"
					};
			}
		}
		return new Object[] {};
	}

	@Override
	public Object[] getReportRecords(String reportSwitch) {
		switch (reportSwitch) {
			case AdminConstants.ADMIN_REPORT : {
				return new Object[] {
						this.tutorId,
						this.tentativeTutorId,
						this.userId,
						this.name,
						this.contactNumber,
						this.emailId,
						DateUtils.parseDateInIndianDTFormatWithoutTime(this.dateOfBirth),
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_GENDER_LOOKUP, this.gender),
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP, this.qualification),
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP, this.primaryProfession),
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP, this.transportMode),
						this.teachingExp,
						ApplicationUtils.getSelectLookupItemListConcatenatedLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, this.interestedStudentGrades, null, null),
						ApplicationUtils.getSelectLookupItemListConcatenatedLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, this.interestedSubjects, null, null),
						ApplicationUtils.getSelectLookupItemListConcatenatedLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP, this.comfortableLocations, null, null),
						this.additionalDetails,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.recordLastUpdatedMillis),
						this.updatedByName
					};
			}
		}
		return new Object[] {};
	}
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "tutorId" : return "TUTOR_ID";
			case "name" : return "NAME";
			case "contactNumber" : return "CONTACT_NUMBER";
			case "emailId" : return "EMAIL_ID";
			case "tentativeTutorId" : return "TENTATIVE_TUTOR_ID";
			case "dateOfBirth" : return "DATE_OF_BIRTH";
			case "gender" : return "GENDER";
			case "qualification" : return "QUALIFICATION";
			case "primaryProfession" : return "PRIMARY_PROFESSION";
			case "transportMode" : return "TRANSPORT_MODE";
			case "teachingExp" : return "TEACHING_EXP";
			case "interestedStudentGrades" : return "INTERESTED_STUDENT_GRADES";
			case "interestedSubjects" : return "INTERESTED_SUBJECTS";
			case "comfortableLocations" : return "COMFORTABLE_LOCATIONS";
			case "preferredTeachingType" : return "PREFERRED_TEACHING_TYPE";
			case "additionalDetails" : return "ADDITIONAL_DETAILS";
			case "encryptedPassword" : return "ENCRYPTED_PASSWORD";
			case "userId" : return "USER_ID";
			case "recordLastUpdatedMillis" : return "RECORD_LAST_UPDATED_MILLIS";
			case "updatedBy" : return "UPDATED_BY";
			case "updatedByName" : return "UPDATED_BY_NAME";
			case "addressDetails" : return "ADDRESS_DETAILS";
		}
		return EMPTY_STRING;
	}
}
