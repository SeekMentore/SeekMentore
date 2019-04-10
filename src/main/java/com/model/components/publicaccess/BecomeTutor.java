package com.model.components.publicaccess;

import java.io.Serializable;
import java.util.Date;

import com.constants.components.AdminConstants;
import com.constants.components.SelectLookupConstants;
import com.constants.components.publicaccess.BecomeTutorConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.ApplicationUtils;
import com.utils.DateUtils;
import com.utils.PrintFormatterUtils;
import com.utils.ValidationUtils;

public class BecomeTutor extends PublicApplication implements Serializable, Cloneable, BecomeTutorConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = 7314098186505190523L;

	private String becomeTutorSerialId;
	private String applicationStatus;
	private Date dateOfBirth;
	private String contactNumber;
	private String emailId;
	private String firstName;
	private String lastName;
	private String gender;
	private String qualification;
	private String primaryProfession;
	private String transportMode;
	private Integer teachingExp;
	private String studentGrade;
	private String subjects;
	private String locations;
	private String preferredTimeToCall;
	private String additionalDetails;
	private String isContacted;
	private String whoContacted;
	private String contactedRemarks;
	private String isAuthenticationVerified;
	private String whoVerified;
	private String verificationRemarks;
	private String isToBeRecontacted;
	private String whoSuggestedForRecontact;
	private String suggestionRemarks;
	private String whoRecontacted;
	private String recontactedRemarks;
	private String isSelected;
	private String whoSelected;
	private String selectionRemarks;
	private String isRejected;
	private String whoRejected;
	private String rejectionRemarks;
	private Integer rejectionCount;
	private String reference;
	private String preferredTeachingType;
	private String reApplied;
	private String addressDetails;
	private Long applicationDateMillis;
	private Long contactedDateMillis;
	private Long verificationDateMillis;
	private Long suggestionDateMillis;
	private Long recontactedDateMillis;
	private Long selectionDateMillis;
	private Long rejectionDateMillis;
	private Long previousApplicationDateMillis;
	private Long recordLastUpdatedMillis;
	private String updatedBy;
	private String whoContactedName;
	private String whoVerifiedName;
	private String whoSuggestedForRecontactName;
	private String whoRecontactedName;
	private String whoSelectedName;
	private String whoRejectedName;
	private String updatedByName;
	private String isBlacklisted;
	private String blacklistedRemarks;
	private Long blacklistedDateMillis;
	private String whoBlacklisted;
	private String whoBlacklistedName;
	private String unblacklistedRemarks;
	private Long unblacklistedDateMillis;
	private String whoUnBlacklisted;
	private String whoUnBlacklistedName;
	
	public BecomeTutor() {}
	
	public String getBecomeTutorSerialId() {
		return becomeTutorSerialId;
	}

	public void setBecomeTutorSerialId(String becomeTutorSerialId) {
		this.becomeTutorSerialId = becomeTutorSerialId;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Integer getTeachingExp() {
		return teachingExp;
	}

	public void setTeachingExp(Integer teachingExp) {
		this.teachingExp = teachingExp;
	}

	public String getLocations() {
		return locations;
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}

	public String getPreferredTimeToCall() {
		return preferredTimeToCall;
	}

	public void setPreferredTimeToCall(String preferredTimeToCall) {
		this.preferredTimeToCall = preferredTimeToCall;
	}

	public String getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	public String getIsContacted() {
		return isContacted;
	}

	public void setIsContacted(String isContacted) {
		this.isContacted = isContacted;
	}

	public String getIsAuthenticationVerified() {
		return isAuthenticationVerified;
	}

	public void setIsAuthenticationVerified(String isAuthenticationVerified) {
		this.isAuthenticationVerified = isAuthenticationVerified;
	}

	public String getIsToBeRecontacted() {
		return isToBeRecontacted;
	}

	public void setIsToBeRecontacted(String isToBeRecontacted) {
		this.isToBeRecontacted = isToBeRecontacted;
	}

	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	
	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	public String getStudentGrade() {
		return studentGrade;
	}

	public void setStudentGrade(String studentGrade) {
		this.studentGrade = studentGrade;
	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public String getWhoContacted() {
		return whoContacted;
	}

	public void setWhoContacted(String whoContacted) {
		this.whoContacted = whoContacted;
	}

	public String getContactedRemarks() {
		return contactedRemarks;
	}

	public void setContactedRemarks(String contactedRemarks) {
		this.contactedRemarks = contactedRemarks;
	}

	public String getWhoVerified() {
		return whoVerified;
	}

	public void setWhoVerified(String whoVerified) {
		this.whoVerified = whoVerified;
	}

	public String getVerificationRemarks() {
		return verificationRemarks;
	}

	public void setVerificationRemarks(String verificationRemarks) {
		this.verificationRemarks = verificationRemarks;
	}

	public String getWhoSuggestedForRecontact() {
		return whoSuggestedForRecontact;
	}

	public void setWhoSuggestedForRecontact(String whoSuggestedForRecontact) {
		this.whoSuggestedForRecontact = whoSuggestedForRecontact;
	}

	public String getSuggestionRemarks() {
		return suggestionRemarks;
	}

	public void setSuggestionRemarks(String suggestionRemarks) {
		this.suggestionRemarks = suggestionRemarks;
	}

	public String getWhoRecontacted() {
		return whoRecontacted;
	}

	public void setWhoRecontacted(String whoRecontacted) {
		this.whoRecontacted = whoRecontacted;
	}

	public String getRecontactedRemarks() {
		return recontactedRemarks;
	}

	public void setRecontactedRemarks(String recontactedRemarks) {
		this.recontactedRemarks = recontactedRemarks;
	}

	public String getWhoSelected() {
		return whoSelected;
	}

	public void setWhoSelected(String whoSelected) {
		this.whoSelected = whoSelected;
	}

	public String getSelectionRemarks() {
		return selectionRemarks;
	}

	public void setSelectionRemarks(String selectionRemarks) {
		this.selectionRemarks = selectionRemarks;
	}

	public String getIsRejected() {
		return isRejected;
	}

	public void setIsRejected(String isRejected) {
		this.isRejected = isRejected;
	}

	public String getWhoRejected() {
		return whoRejected;
	}

	public void setWhoRejected(String whoRejected) {
		this.whoRejected = whoRejected;
	}

	public String getRejectionRemarks() {
		return rejectionRemarks;
	}

	public void setRejectionRemarks(String rejectionRemarks) {
		this.rejectionRemarks = rejectionRemarks;
	}

	public Integer getRejectionCount() {
		return rejectionCount;
	}

	public void setRejectionCount(Integer rejectionCount) {
		this.rejectionCount = rejectionCount;
	}

	public String getReApplied() {
		return reApplied;
	}

	public void setReApplied(String reApplied) {
		this.reApplied = reApplied;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getPreferredTeachingType() {
		return preferredTeachingType;
	}

	public void setPreferredTeachingType(String preferredTeachingType) {
		this.preferredTeachingType = preferredTeachingType;
	}
	
	public Long getApplicationDateMillis() {
		return applicationDateMillis;
	}

	public void setApplicationDateMillis(Long applicationDateMillis) {
		this.applicationDateMillis = applicationDateMillis;
	}

	public Long getContactedDateMillis() {
		return contactedDateMillis;
	}

	public void setContactedDateMillis(Long contactedDateMillis) {
		this.contactedDateMillis = contactedDateMillis;
	}

	public Long getVerificationDateMillis() {
		return verificationDateMillis;
	}

	public void setVerificationDateMillis(Long verificationDateMillis) {
		this.verificationDateMillis = verificationDateMillis;
	}

	public Long getSuggestionDateMillis() {
		return suggestionDateMillis;
	}

	public void setSuggestionDateMillis(Long suggestionDateMillis) {
		this.suggestionDateMillis = suggestionDateMillis;
	}

	public Long getRecontactedDateMillis() {
		return recontactedDateMillis;
	}

	public void setRecontactedDateMillis(Long recontactedDateMillis) {
		this.recontactedDateMillis = recontactedDateMillis;
	}

	public Long getSelectionDateMillis() {
		return selectionDateMillis;
	}

	public void setSelectionDateMillis(Long selectionDateMillis) {
		this.selectionDateMillis = selectionDateMillis;
	}

	public Long getRejectionDateMillis() {
		return rejectionDateMillis;
	}

	public void setRejectionDateMillis(Long rejectionDateMillis) {
		this.rejectionDateMillis = rejectionDateMillis;
	}

	public Long getPreviousApplicationDateMillis() {
		return previousApplicationDateMillis;
	}

	public void setPreviousApplicationDateMillis(Long previousApplicationDateMillis) {
		this.previousApplicationDateMillis = previousApplicationDateMillis;
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

	public String getWhoContactedName() {
		return whoContactedName;
	}

	public void setWhoContactedName(String whoContactedName) {
		this.whoContactedName = whoContactedName;
	}

	public String getWhoVerifiedName() {
		return whoVerifiedName;
	}

	public void setWhoVerifiedName(String whoVerifiedName) {
		this.whoVerifiedName = whoVerifiedName;
	}

	public String getWhoSuggestedForRecontactName() {
		return whoSuggestedForRecontactName;
	}

	public void setWhoSuggestedForRecontactName(String whoSuggestedForRecontactName) {
		this.whoSuggestedForRecontactName = whoSuggestedForRecontactName;
	}

	public String getWhoRecontactedName() {
		return whoRecontactedName;
	}

	public void setWhoRecontactedName(String whoRecontactedName) {
		this.whoRecontactedName = whoRecontactedName;
	}

	public String getWhoSelectedName() {
		return whoSelectedName;
	}

	public void setWhoSelectedName(String whoSelectedName) {
		this.whoSelectedName = whoSelectedName;
	}

	public String getWhoRejectedName() {
		return whoRejectedName;
	}

	public void setWhoRejectedName(String whoRejectedName) {
		this.whoRejectedName = whoRejectedName;
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

	@Override
	public Object[] getReportHeaders(final String reportSwitch) {
		switch (reportSwitch) {
			case AdminConstants.SUPPORT_TEAM_REPORT : {
				return new Object[] {
						COLUMN_NAME_BECOME_TUTOR_SERIAL_ID,
						COLUMN_NAME_APPLICATION_DATE,
						COLUMN_NAME_APPLICATION_STATUS,
						COLUMN_NAME_DATE_OF_BIRTH,
						COLUMN_NAME_CONTACT_NUMBER,
						COLUMN_NAME_EMAIL_ID,
						COLUMN_NAME_FIRST_NAME,
						COLUMN_NAME_LAST_NAME,
						COLUMN_NAME_GENDER,
						COLUMN_NAME_QUALIFICATION,
						COLUMN_NAME_PRIMARY_PROFESSION,
						COLUMN_NAME_TRANSPORT_MODE,
						COLUMN_NAME_TEACHING_EXPERIENCE,
						COLUMN_NAME_STUDENT_GRADE,
						COLUMN_NAME_SUBJECTS,
						COLUMN_NAME_LOCATIONS,
						COLUMN_NAME_PREFERRED_TIME_TO_CALL,
						COLUMN_NAME_REFERENCE,
						COLUMN_NAME_PREFERRED_TEACHING_TYPE,
						COLUMN_NAME_ADDITIONAL_DETAILS,
						"ADDRESS_DETAILS",
						COLUMN_NAME_IS_CONTACTED,
						COLUMN_NAME_WHO_CONTACTED,
						COLUMN_NAME_CONTACTED_DATE,
						COLUMN_NAME_CONTACTED_REMARKS,
						COLUMN_NAME_IS_AUTHENTICATION_VERIFIED,
						COLUMN_NAME_WHO_VERIFIED,
						COLUMN_NAME_VERIFICATION_DATE,
						COLUMN_NAME_VERIFICATION_REMARKS,
						COLUMN_NAME_IS_TO_BE_RECONTACTED,
						COLUMN_NAME_WHO_SUGGESTED_FOR_RECONTACT,
						COLUMN_NAME_SUGGESTION_DATE,
						COLUMN_NAME_SUGGESTION_REMARKS,
						COLUMN_NAME_WHO_RECONTACTED,
						COLUMN_NAME_RECONTACTED_DATE,
						COLUMN_NAME_RECONTACTED_REMARKS,
						COLUMN_NAME_IS_SELECTED,
						COLUMN_NAME_WHO_SELECTED,
						COLUMN_NAME_SELECTION_DATE,
						COLUMN_NAME_SELECTION_REMARKS,
						COLUMN_NAME_IS_REJECTED,
						COLUMN_NAME_WHO_REJECTED,
						COLUMN_NAME_REJECTION_DATE,
						COLUMN_NAME_REJECTION_REMARKS,
						COLUMN_NAME_REJECTION_COUNT,
						COLUMN_NAME_RE_APPLIED,
						COLUMN_NAME_PREVIOUS_APPLICATION_DATE,
						"IS_BLACKLISTED",
						"BLACKLISTED_REMARKS",
						"BLACKLISTED_DATE_MILLIS",
						"WHO_BLACKLISTED",
						"UN_BLACKLISTED_DATE_MILLIS",
						"WHO_UN_BLACKLISTED_NAME",
						COLUMN_NAME_RECORD_LAST_UPDATED,
						"UPDATED_BY"
					};
			}
		}
		return new Object[] {};
	}

	@Override
	public Object[] getReportRecords(String reportSwitch) {
		switch (reportSwitch) {
			case AdminConstants.SUPPORT_TEAM_REPORT : {
				return new Object[] {
						this.becomeTutorSerialId,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.applicationDateMillis),
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_PUBLIC_APPLICATION_STATUS_LOOKUP, this.applicationStatus),
						DateUtils.parseDateInIndianDTFormatWithoutTime(this.dateOfBirth),
						this.contactNumber,
						this.emailId,
						this.firstName,
						this.lastName,
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_GENDER_LOOKUP, this.gender),
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP, this.qualification),
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP, this.primaryProfession),
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP, this.transportMode),
						this.teachingExp,
						ApplicationUtils.getSelectLookupItemListConcatenatedLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, this.studentGrade, null, null),
						ApplicationUtils.getSelectLookupItemListConcatenatedLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, this.subjects, null, null),
						ApplicationUtils.getSelectLookupItemListConcatenatedLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP, this.locations, null, null),
						ApplicationUtils.getSelectLookupItemListConcatenatedLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP, this.preferredTimeToCall, null, null),
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP, this.reference),
						ApplicationUtils.getSelectLookupItemListConcatenatedLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP, this.preferredTeachingType, null, null),
						this.additionalDetails,
						this.addressDetails,
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP, this.isContacted),
						this.whoContactedName,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.contactedDateMillis),
						this.contactedRemarks,
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP, this.isAuthenticationVerified),
						this.whoVerifiedName,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.verificationDateMillis),
						this.verificationRemarks,
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP, this.isToBeRecontacted),
						this.whoSuggestedForRecontactName,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.suggestionDateMillis),
						this.suggestionRemarks,
						this.whoRecontactedName,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.recontactedDateMillis),
						this.recontactedRemarks,
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP, this.isSelected),
						this.whoSelectedName,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.selectionDateMillis),
						this.selectionRemarks,
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP, this.isRejected),
						this.whoRejectedName,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.rejectionDateMillis),
						this.rejectionRemarks,
						this.rejectionCount,
						this.reApplied,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.previousApplicationDateMillis),
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP, this.isBlacklisted),
						this.blacklistedRemarks,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.blacklistedDateMillis),
						this.whoBlacklistedName,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.unblacklistedDateMillis),
						this.whoUnBlacklistedName,
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
			case "becomeTutorSerialId" : return "BECOME_TUTOR_SERIAL_ID";
			case "applicationDateMillis" : return "APPLICATION_DATE_MILLIS";
			case "applicationStatus" : return "APPLICATION_STATUS";
			case "dateOfBirth" : return "DATE_OF_BIRTH";
			case "contactNumber" : return "CONTACT_NUMBER";
			case "emailId" : return "EMAIL_ID";
			case "firstName" : return "FIRST_NAME";
			case "lastName" : return "LAST_NAME";
			case "gender" : return "GENDER";
			case "qualification" : return "QUALIFICATION";
			case "primaryProfession" : return "PRIMARY_PROFESSION";
			case "transportMode" : return "TRANSPORT_MODE";
			case "teachingExp" : return "TEACHING_EXP";
			case "studentGrade" : return "STUDENT_GRADE";
			case "subjects" : return "SUBJECTS";
			case "locations" : return "LOCATIONS";
			case "preferredTimeToCall" : return "PREFERRED_TIME_TO_CALL";
			case "additionalDetails" : return "ADDITIONAL_DETAILS";
			case "isContacted" : return "IS_CONTACTED";
			case "whoContacted" : return "WHO_CONTACTED";			
			case "contactedDateMillis" : return "CONTACTED_DATE_MILLIS";
			case "contactedRemarks" : return "CONTACTED_REMARKS";
			case "isAuthenticationVerified" : return "IS_AUTHENTICATION_VERIFIED";
			case "whoVerified" : return "WHO_VERIFIED";
			case "verificationDateMillis" : return "VERIFICATION_DATE_MILLIS";
			case "verificationRemarks" : return "VERIFICATION_REMARKS";
			case "isToBeRecontacted" : return "IS_TO_BE_RECONTACTED";
			case "whoSuggestedForRecontact" : return "WHO_SUGGESTED_FOR_RECONTACT";
			case "suggestionDateMillis" : return "SUGGESTION_DATE_MILLIS";
			case "suggestionRemarks" : return "SUGGESTION_REMARKS";
			case "whoRecontacted" : return "WHO_RECONTACTED";
			case "recontactedDateMillis" : return "RECONTACTED_DATE_MILLIS";
			case "recontactedRemarks" : return "RECONTACTED_REMARKS";
			case "isSelected" : return "IS_SELECTED";
			case "whoSelected" : return "WHO_SELECTED";
			case "selectionDateMillis" : return "SELECTION_DATE_MILLIS";
			case "selectionRemarks" : return "SELECTION_REMARKS";
			case "isRejected" : return "IS_REJECTED";
			case "whoRejected" : return "WHO_REJECTED";
			case "rejectionDateMillis" : return "REJECTION_DATE_MILLIS";			
			case "rejectionRemarks" : return "REJECTION_REMARKS";
			case "rejectionCount" : return "REJECTION_COUNT";
			case "reApplied" : return "RE_APPLIED";
			case "reference" : return "REFERENCE";
			case "preferredTeachingType" : return "PREFERRED_TEACHING_TYPE";
			case "previousApplicationDateMillis" : return "PREVIOUS_APPLICATION_DATE_MILLIS";
			case "recordLastUpdatedMillis" : return "RECORD_LAST_UPDATED_MILLIS";
			case "updatedBy" : return "UPDATED_BY";
			case "whoContactedName" : return "WHO_CONTACTED_NAME";
			case "whoVerifiedName" : return "WHO_VERIFIED_NAME";
			case "whoSuggestedForRecontactName" : return "WHO_SUGGESTED_FOR_RECONTACT_NAME";
			case "whoRecontactedName" : return "WHO_RECONTACTED_NAME";
			case "whoSelectedName" : return "WHO_SELECTED_NAME";
			case "whoRejectedName" : return "WHO_REJECTED_NAME";
			case "updatedByName" : return "UPDATED_BY_NAME";
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
	public String getFormattedApplicationForPrinting() {
		final StringBuilder becomeTutorApplication = new StringBuilder(EMPTY_STRING);
		becomeTutorApplication.append(PrintFormatterUtils.startATable());
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_BECOME_TUTOR_SERIAL_ID, this.becomeTutorSerialId));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_APPLICATION_STATUS, this.applicationStatus));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_DATE_OF_BIRTH, DateUtils.parseDateInIndianDTFormatWithoutTime(this.dateOfBirth)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACT_NUMBER, this.contactNumber));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_EMAIL_ID, this.emailId));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_FIRST_NAME, this.firstName));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_LAST_NAME, this.lastName));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_GENDER, ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_GENDER_LOOKUP, this.gender)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUALIFICATION, ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP, this.qualification)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_PRIMARY_PROFESSION, ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP, this.primaryProfession)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_TRANSPORT_MODE, ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP, this.transportMode)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_TEACHING_EXPERIENCE, this.teachingExp));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_STUDENT_GRADE, ApplicationUtils.getSelectLookupItemListConcatenatedLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, this.studentGrade, null, null)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUBJECTS, ApplicationUtils.getSelectLookupItemListConcatenatedLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, this.subjects, null, null)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_LOCATIONS, ApplicationUtils.getSelectLookupItemListConcatenatedLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP, this.locations, null, null)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_PREFERRED_TIME_TO_CALL, ApplicationUtils.getSelectLookupItemListConcatenatedLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP, this.preferredTimeToCall, null, null)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REFERENCE, ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP, this.reference)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_PREFERRED_TEACHING_TYPE, ApplicationUtils.getSelectLookupItemListConcatenatedLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP, this.preferredTeachingType, null, null)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_ADDITIONAL_DETAILS, this.additionalDetails));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_ADDRESS_DETAILS, this.addressDetails));
		becomeTutorApplication.append(PrintFormatterUtils.endATable());
		return becomeTutorApplication.toString();
	}
	
	@Override
	public BecomeTutor clone() throws CloneNotSupportedException {  
		return (BecomeTutor)super.clone();
	}
}
