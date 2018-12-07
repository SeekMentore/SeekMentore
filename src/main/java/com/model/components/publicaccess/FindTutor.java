package com.model.components.publicaccess;

import java.io.Serializable;
import java.util.Date;

import com.constants.components.publicaccess.FindTutorConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.PrintFormatterUtils;
import com.utils.ValidationUtils;

public class FindTutor extends PublicApplication implements Serializable, FindTutorConstants, ApplicationWorkbookObject {

	private static final long serialVersionUID = 7314098186505190523L;
	
	private Long enquiryId;
	private String enquiryStatus;
	private String name;
	private String contactNumber;
	private String emailId;
	private String studentGrade;
	private String subjects;
	private String preferredTimeToCall;
	private String additionalDetails;
	private String subscribedCustomer;
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
	private String location;
	private String reference;
	private String addressDetails;
	private Long enquiryDateMillis;
	private Long contactedDateMillis;
	private Long verificationDateMillis;
	private Long suggestionDateMillis;
	private Long recontactedDateMillis;
	private Long selectionDateMillis;
	private Long rejectionDateMillis;
	private Long recordLastUpdatedMillis;
	private String updatedBy;
	private String whoContactedName;
	private String whoVerifiedName;
	private String whoSuggestedForRecontactName;
	private String whoRecontactedName;
	private String whoSelectedName;
	private String whoRejectedName;
	private String updatedByName;
	
	public FindTutor() {}
	
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
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

	public Long getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(Long enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getEnquiryStatus() {
		return enquiryStatus;
	}

	public void setEnquiryStatus(String enquiryStatus) {
		this.enquiryStatus = enquiryStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentGrade() {
		return studentGrade;
	}

	public void setStudentGrade(String studentGrade) {
		this.studentGrade = studentGrade;
	}
	
	public String getSubscribedCustomer() {
		return subscribedCustomer;
	}

	public void setSubscribedCustomer(String subscribedCustomer) {
		this.subscribedCustomer = subscribedCustomer;
	}
	
	public Long getEnquiryDateMillis() {
		return enquiryDateMillis;
	}

	public void setEnquiryDateMillis(Long enquiryDateMillis) {
		this.enquiryDateMillis = enquiryDateMillis;
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

	public Long getRecordLastUpdatedMillis() {
		return recordLastUpdatedMillis;
	}

	public void setRecordLastUpdatedMillis(Long recordLastUpdatedMillis) {
		this.recordLastUpdatedMillis = recordLastUpdatedMillis;
	}

	@Override
	public String toString() {
		final StringBuilder findTutorApplication = new StringBuilder(EMPTY_STRING);
		findTutorApplication.append(PrintFormatterUtils.startATable());
		findTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_ENQUIRY_ID, enquiryId));
		findTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_ENQUIRY_STATUS, enquiryStatus));
		findTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_NAME, name));
		findTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACT_NUMBER, contactNumber));
		findTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_EMAIL_ID, emailId));
		findTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_STUDENT_GRADE, studentGrade));
		findTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUBJECTS, subjects));
		findTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_PREFERRED_TIME_TO_CALL, preferredTimeToCall));
		findTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_LOCATION, location));
		findTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REFERENCE, reference));
		findTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_ADDRESS_DETAILS, addressDetails));
		findTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_ADDITIONAL_DETAILS, additionalDetails));
		findTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUBSCRIBED_CUSTOMER, subscribedCustomer));
		findTutorApplication.append(PrintFormatterUtils.endATable());
		return findTutorApplication.toString();
	}

	@Override
	public Object[] getReportHeaders(String reportSwitch) {
		switch (reportSwitch) {
		case "Admin_Report" : {
			return new Object[] {
					COLUMN_NAME_ENQUIRY_DATE,
					COLUMN_NAME_ENQUIRY_STATUS,
					COLUMN_NAME_NAME,
					COLUMN_NAME_CONTACT_NUMBER,
					COLUMN_NAME_EMAIL_ID,
					COLUMN_NAME_STUDENT_GRADE,
					COLUMN_NAME_SUBJECTS,
					COLUMN_NAME_PREFERRED_TIME_TO_CALL,
					COLUMN_NAME_LOCATION,
					COLUMN_NAME_REFERENCE,
					COLUMN_NAME_ADDRESS_DETAILS,
					COLUMN_NAME_ADDITIONAL_DETAILS,
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
			case "Admin_Report" : {
				return new Object[] {
						new Date(this.enquiryDateMillis),
						this.enquiryStatus,
						this.name,
						this.contactNumber,
						this.emailId,
						this.studentGrade,
						this.subjects,
						this.preferredTimeToCall,
						this.location,
						this.reference,
						this.addressDetails,
						this.additionalDetails,
						this.isContacted,
						this.whoContactedName,
						new Date(this.contactedDateMillis),
						this.contactedRemarks,
						this.isAuthenticationVerified,
						this.whoVerifiedName,
						new Date(this.verificationDateMillis),
						this.verificationRemarks,
						this.isToBeRecontacted,
						this.whoSuggestedForRecontactName,
						new Date(this.suggestionDateMillis),
						this.suggestionRemarks,
						this.whoRecontactedName,
						new Date(this.recontactedDateMillis),
						this.recontactedRemarks,
						this.isSelected,
						this.whoSelectedName,
						new Date(this.selectionDateMillis),
						this.selectionRemarks,
						this.isRejected,
						this.whoRejectedName,
						new Date(this.rejectionDateMillis),
						this.rejectionRemarks,
						new Date(this.recordLastUpdatedMillis),
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
			case "enquiryId" : return "ENQUIRY_ID";
			case "enquiryDateMillis" : return "ENQUIRY_DATE_MILLIS";
			case "enquiryStatus" : return "ENQUIRY_STATUS";
			case "name" : return "NAME";
			case "contactNumber" : return "CONTACT_NUMBER";
			case "emailId" : return "EMAIL_ID";
			case "studentGrade" : return "STUDENT_GRADE";
			case "subjects" : return "SUBJECTS";
			case "preferredTimeToCall" : return "PREFERRED_TIME_TO_CALL";
			case "additionalDetails" : return "ADDITIONAL_DETAILS";
			case "subscribedCustomer" : return "SUBSCRIBED_CUSTOMER";
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
			case "recordLastUpdatedMillis" : return "RECORD_LAST_UPDATED_MILLIS";
			case "reference" : return "REFERENCE";
			case "location" : return "LOCATION";
			case "addressDetails" : return "ADDRESS_DETAILS";
			case "updatedBy" : return "UPDATED_BY";
			case "whoContactedName" : return "WHO_CONTACTED_NAME";
			case "whoVerifiedName" : return "WHO_VERIFIED_NAME";
			case "whoSuggestedForRecontactName" : return "WHO_SUGGESTED_FOR_RECONTACT_NAME";
			case "whoRecontactedName" : return "WHO_RECONTACTED_NAME";
			case "whoSelectedName" : return "WHO_SELECTED_NAME";
			case "whoRejectedName" : return "WHO_REJECTED_NAME";
			case "updatedByName" : return "UPDATED_BY_NAME";
		}
		return EMPTY_STRING;
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
}
