package com.model.components.publicaccess;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.constants.DatabaseConstants;
import com.constants.components.publicaccess.SubscribeWithUsConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.PrintFormatterUtils;
import com.utils.ValidationUtils;

@Entity
@Table( name = SubscribeWithUsConstants.TABLE_NAME, 
		catalog = DatabaseConstants.DATABASE_CATALOG_NAME
)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SubscribeWithUs extends PublicApplication implements Serializable, SubscribeWithUsConstants, ApplicationWorkbookObject {

	private static final long serialVersionUID = 7314098186505190523L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = COLUMN_NAME_TENTATIVE_SUBSCRIPTION_ID, unique = true, nullable = false)
	private Long tentativeSubscriptionId;
	
	@Column(name = COLUMN_NAME_APPLICATION_STATUS, unique = true, nullable = false)
	private String applicationStatus;
	
	@Column(name = COLUMN_NAME_FIRST_NAME, nullable = false)
	private String firstName;
	
	@Column(name = COLUMN_NAME_LAST_NAME, nullable = false)
	private String lastName;
	
	@Column(name = COLUMN_NAME_CONTACT_NUMBER, unique = true, nullable = false)
	private String contactNumber;
	
	@Column(name = COLUMN_NAME_EMAIL_ID, unique = true, nullable = false)
	private String emailId;
	
	@Column(name = COLUMN_NAME_STUDENT_GRADE, nullable = false)
	private String studentGrade;
	
	@Column(name = COLUMN_NAME_SUBJECTS, nullable = false)
	private String subjects;
	
	@Column(name = COLUMN_NAME_PREFERRED_TIME_TO_CALL, nullable = false)
	private String preferredTimeToCall;
	
	@Column(name = COLUMN_NAME_ADDITIONAL_DETAILS)
	private String additionalDetails;
	
	@Column(name = COLUMN_NAME_SUBSCRIBED_CUSTOMER, nullable = false)
	private String subscribedCustomer;
	
	@Column(name = COLUMN_NAME_IS_CONTACTED, nullable = false)
	private String isContacted;
	
	@Column(name = COLUMN_NAME_WHO_CONTACTED)
	private String whoContacted;
	
	@Column(name = COLUMN_NAME_CONTACTED_REMARKS)
	private String contactedRemarks;
	
	@Column(name = COLUMN_NAME_IS_AUTHENTICATION_VERIFIED)
	private String isAuthenticationVerified;
	
	@Column(name = COLUMN_NAME_WHO_VERIFIED)
	private String whoVerified;
	
	@Column(name = COLUMN_NAME_VERIFICATION_REMARKS)
	private String verificationRemarks;
	
	@Column(name = COLUMN_NAME_IS_TO_BE_RECONTACTED)
	private String isToBeRecontacted;
	
	@Column(name = COLUMN_NAME_WHO_SUGGESTED_FOR_RECONTACT)
	private String whoSuggestedForRecontact;
	
	@Column(name = COLUMN_NAME_SUGGESTION_REMARKS)
	private String suggestionRemarks;
	
	@Column(name = COLUMN_NAME_WHO_RECONTACTED)
	private String whoRecontacted;
	
	@Column(name = COLUMN_NAME_RECONTACTED_REMARKS)
	private String recontactedRemarks;
	
	@Column(name = COLUMN_NAME_IS_SELECTED)
	private String isSelected;
	
	@Column(name = COLUMN_NAME_WHO_SELECTED)
	private String whoSelected;
	
	@Column(name = COLUMN_NAME_SELECTION_REMARKS)
	private String selectionRemarks;
	
	@Column(name = COLUMN_NAME_IS_REJECTED)
	private String isRejected;
	
	@Column(name = COLUMN_NAME_WHO_REJECTED)
	private String whoRejected;
	
	@Column(name = COLUMN_NAME_REJECTION_REMARKS)
	private String rejectionRemarks;
	
	@Column(name = COLUMN_NAME_LOCATION)
	private String location;
	
	@Column(name = COLUMN_NAME_REFERENCE)
	private String reference;
	
	@Column(name = COLUMN_NAME_ADDRESS_DETAILS)
	private String addressDetails;
	
	private Long applicationDateMillis;
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
	
	public SubscribeWithUs() {}
	
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
	
	public Long getTentativeSubscriptionId() {
		return tentativeSubscriptionId;
	}

	public void setTentativeSubscriptionId(Long tentativeSubscriptionId) {
		this.tentativeSubscriptionId = tentativeSubscriptionId;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
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
		final StringBuilder subscribeWithUsApplication = new StringBuilder(EMPTY_STRING);
		subscribeWithUsApplication.append(PrintFormatterUtils.startATable());
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_TENTATIVE_SUBSCRIPTION_ID, tentativeSubscriptionId));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_APPLICATION_DATE, new Date(applicationDateMillis)));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_APPLICATION_STATUS, applicationStatus));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_FIRST_NAME, firstName));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_LAST_NAME, lastName));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACT_NUMBER, contactNumber));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_EMAIL_ID, emailId));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_STUDENT_GRADE, studentGrade));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUBJECTS, subjects));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_PREFERRED_TIME_TO_CALL, preferredTimeToCall));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_LOCATION, location));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REFERENCE, reference));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_ADDRESS_DETAILS, addressDetails));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_ADDITIONAL_DETAILS, additionalDetails));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUBSCRIBED_CUSTOMER, subscribedCustomer));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_CONTACTED, isContacted));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_CONTACTED, whoContacted));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACTED_DATE, new Date(contactedDateMillis)));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACTED_REMARKS, contactedRemarks));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_AUTHENTICATION_VERIFIED, isAuthenticationVerified));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_VERIFIED, whoVerified));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_VERIFICATION_DATE, new Date(verificationDateMillis)));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_VERIFICATION_REMARKS, verificationRemarks));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_TO_BE_RECONTACTED, isToBeRecontacted));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_SUGGESTED_FOR_RECONTACT, whoSuggestedForRecontact));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUGGESTION_DATE, new Date(suggestionDateMillis)));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUGGESTION_REMARKS, suggestionRemarks));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_RECONTACTED, whoRecontacted));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECONTACTED_DATE, new Date(recontactedDateMillis)));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECONTACTED_REMARKS, recontactedRemarks));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_SELECTED, isSelected));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_SELECTED, whoSelected));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SELECTION_DATE, new Date(selectionDateMillis)));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SELECTION_REMARKS, selectionRemarks));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_REJECTED, isRejected));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_REJECTED, whoRejected));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REJECTION_DATE, new Date(rejectionDateMillis)));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REJECTION_REMARKS, rejectionRemarks));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECORD_LAST_UPDATED, new Date(recordLastUpdatedMillis)));
		subscribeWithUsApplication.append(PrintFormatterUtils.endATable());
		return subscribeWithUsApplication.toString();
	}
	
	@Override
	public Object[] getReportHeaders(String reportSwitch) {
		switch (reportSwitch) {
		case "Admin_Report" : {
			return new Object[] {
					COLUMN_NAME_APPLICATION_DATE,
					COLUMN_NAME_APPLICATION_STATUS,
					COLUMN_NAME_FIRST_NAME,
					COLUMN_NAME_LAST_NAME,
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
						new Date(this.applicationDateMillis),
						this.applicationStatus,
						this.firstName,
						this.lastName,
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getVerificationDateMillis() {
		return verificationDateMillis;
	}

	public void setVerificationDateMillis(Long verificationDateMillis) {
		this.verificationDateMillis = verificationDateMillis;
	}
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "tentativeSubscriptionId" : return "TENTATIVE_SUBSCRIPTION_ID";
			case "applicationDateMillis" : return "APPLICATION_DATE_MILLIS";
			case "applicationStatus" : return "APPLICATION_STATUS";
			case "firstName" : return "FIRST_NAME";
			case "lastName" : return "LAST_NAME";
			case "contactNumber" : return "CONTACT_NUMBER";
			case "emailId" : return "EMAIL_ID";
			case "studentGrade" : return "STUDENT_GRADE";
			case "subjects" : return "SUBJECTS";
			case "preferredTimeToCall" : return "PREFERRED_TIME_TO_CALL";
			case "location" : return "LOCATION";
			case "reference" : return "REFERENCE";
			case "addressDetails" : return "ADDRESS_DETAILS";
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
