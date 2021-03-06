package com.model.components;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.constants.components.AdminConstants;
import com.constants.components.SelectLookupConstants;
import com.constants.components.publicaccess.RegisteredTutorConstants;
import com.model.ApplicationWorkbookObject;
import com.model.User;
import com.utils.ApplicationUtils;
import com.utils.DateUtils;
import com.utils.ValidationUtils;

public class RegisteredTutor extends User implements Serializable, Cloneable, RegisteredTutorConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -1763649873039566289L;
	
	private String tutorSerialId;
	private String becomeTutorSerialId;
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
	private String preferredTeachingType;
	private String addressDetails;
	private String isBlacklisted;
	private String blacklistedRemarks;
	private Long blacklistedDateMillis;
	private String whoBlacklisted;
	private String whoBlacklistedName;
	private String unblacklistedRemarks;
	private Long unblacklistedDateMillis;
	private String whoUnBlacklisted;
	private String whoUnBlacklistedName;
	private List<TutorDocument> documents;
	private List<BankDetail> bankDetails;
	private List<RegisteredTutorEmail> registeredTutorEmails;
	private List<RegisteredTutorContactNumber> registeredTutorContactNumbers;
	
	public RegisteredTutor() {}
	
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

	public String getTutorSerialId() {
		return tutorSerialId;
	}

	public void setTutorSerialId(String tutorSerialId) {
		this.tutorSerialId = tutorSerialId;
	}

	public String getBecomeTutorSerialId() {
		return becomeTutorSerialId;
	}

	public void setBecomeTutorSerialId(String becomeTutorSerialId) {
		this.becomeTutorSerialId = becomeTutorSerialId;
	}

	public Integer getTeachingExp() {
		return teachingExp;
	}

	public void setTeachingExp(Integer teachingExp) {
		this.teachingExp = teachingExp;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}
	
	public String getIsBlacklisted() {
		return isBlacklisted;
	}

	public void setIsBlacklisted(String isBlacklisted) {
		this.isBlacklisted = isBlacklisted;
	}

	public String getBlacklistedRemarks() {
		return blacklistedRemarks;
	}

	public void setBlacklistedRemarks(String blacklistedRemarks) {
		this.blacklistedRemarks = blacklistedRemarks;
	}

	public Long getBlacklistedDateMillis() {
		return blacklistedDateMillis;
	}

	public void setBlacklistedDateMillis(Long blacklistedDateMillis) {
		this.blacklistedDateMillis = blacklistedDateMillis;
	}

	public String getWhoBlacklisted() {
		return whoBlacklisted;
	}

	public void setWhoBlacklisted(String whoBlacklisted) {
		this.whoBlacklisted = whoBlacklisted;
	}

	public String getWhoBlacklistedName() {
		return whoBlacklistedName;
	}

	public void setWhoBlacklistedName(String whoBlacklistedName) {
		this.whoBlacklistedName = whoBlacklistedName;
	}
	
	public String getUnblacklistedRemarks() {
		return unblacklistedRemarks;
	}

	public void setUnblacklistedRemarks(String unblacklistedRemarks) {
		this.unblacklistedRemarks = unblacklistedRemarks;
	}

	public Long getUnblacklistedDateMillis() {
		return unblacklistedDateMillis;
	}

	public void setUnblacklistedDateMillis(Long unblacklistedDateMillis) {
		this.unblacklistedDateMillis = unblacklistedDateMillis;
	}

	public String getWhoUnBlacklisted() {
		return whoUnBlacklisted;
	}

	public void setWhoUnBlacklisted(String whoUnBlacklisted) {
		this.whoUnBlacklisted = whoUnBlacklisted;
	}

	public String getWhoUnBlacklistedName() {
		return whoUnBlacklistedName;
	}

	public void setWhoUnBlacklistedName(String whoUnBlacklistedName) {
		this.whoUnBlacklistedName = whoUnBlacklistedName;
	}
	
	public List<RegisteredTutorEmail> getRegisteredTutorEmails() {
		return registeredTutorEmails;
	}

	public void setRegisteredTutorEmails(List<RegisteredTutorEmail> registeredTutorEmails) {
		this.registeredTutorEmails = registeredTutorEmails;
	}
	
	public List<RegisteredTutorContactNumber> getRegisteredTutorContactNumbers() {
		return registeredTutorContactNumbers;
	}

	public void setRegisteredTutorContactNumbers(List<RegisteredTutorContactNumber> registeredTutorContactNumbers) {
		this.registeredTutorContactNumbers = registeredTutorContactNumbers;
	}
	
	public List<BankDetail> getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(List<BankDetail> bankDetails) {
		this.bankDetails = bankDetails;
	}

	@Override
	public Object[] getReportHeaders(String reportSwitch) {
		switch (reportSwitch) {
			case AdminConstants.ADMIN_REPORT : {
				return new Object[] {
						"TUTOR_SERIAL_ID",
						"BECOME_TUTOR_SERIAL_ID",
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
						"ADDRESS_DETAILS",
						"IS_BLACKLISTED",
						"BLACKLISTED_REMARKS",
						"BLACKLISTED_DATE_MILLIS",
						"WHO_BLACKLISTED",
						"UN_BLACKLISTED_DATE_MILLIS",
						"WHO_UN_BLACKLISTED_NAME",
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
						this.tutorSerialId,
						this.becomeTutorSerialId,
						this.getUserId(),
						this.getName(),
						this.getContactNumber(),
						this.getEmailId(),
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
						this.addressDetails,
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP, this.isBlacklisted),
						this.blacklistedRemarks,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.blacklistedDateMillis),
						this.whoBlacklistedName,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.unblacklistedDateMillis),
						this.whoUnBlacklistedName,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.getRecordLastUpdatedMillis()),
						this.getUpdatedByName()
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
			case "tutorSerialId" : return "TUTOR_SERIAL_ID";
			case "becomeTutorSerialId" : return "BECOME_TUTOR_SERIAL_ID";
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
			case "addressDetails" : return "ADDRESS_DETAILS";
			case "isBlacklisted" : return "IS_BLACKLISTED";
			case "blacklistedRemarks" : return "BLACKLISTED_REMARKS";
			case "blacklistedDateMillis" : return "BLACKLISTED_DATE_MILLIS";
			case "whoBlacklisted" : return "WHO_BLACKLISTED";
			case "whoBlacklistedName" : return "WHO_BLACKLISTED_NAME";
			case "unblacklistedRemarks" : return "UN_BLACKLISTED_REMARKS";
			case "unblacklistedDateMillis" : return "UN_BLACKLISTED_DATE_MILLIS";
			case "whoUnBlacklisted" : return "WHO_UN_BLACKLISTED";
			case "whoUnBlacklistedName" : return "WHO_UN_BLACKLISTED_NAME";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public RegisteredTutor clone() throws CloneNotSupportedException {  
		return (RegisteredTutor)super.clone();
	}
}
