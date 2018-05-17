package com.model.components.publicaccess;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.constants.DatabaseConstants;
import com.constants.components.publicaccess.SubscribeWithUsConstants;
import com.utils.PrintFormatterUtils;
import com.model.ApplicationWorkbookObject;

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
	
	@Temporal(TemporalType.DATE)
	@Column(name = COLUMN_NAME_APPLICATION_DATE, length = 10, nullable = false)
	private Date applicationDate;
	
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
	
	@Temporal(TemporalType.DATE)
	@Column(name = COLUMN_NAME_CONTACTED_DATE, length = 10)
	private Date contactedDate;
	
	@Column(name = COLUMN_NAME_CONTACTED_REMARKS)
	private String contactedRemarks;
	
	@Column(name = COLUMN_NAME_IS_AUTHENTICATION_VERIFIED)
	private String isAuthenticationVerified;
	
	@Column(name = COLUMN_NAME_WHO_VERIFIED)
	private String whoVerified;
	
	@Temporal(TemporalType.DATE)
	@Column(name = COLUMN_NAME_VERIFICATION_DATE, length = 10)
	private Date verificationDate;
	
	@Column(name = COLUMN_NAME_VERIFICATION_REMARKS)
	private String verificationRemarks;
	
	@Column(name = COLUMN_NAME_IS_TO_BE_RECONTACTED)
	private String isToBeRecontacted;
	
	@Column(name = COLUMN_NAME_WHO_SUGGESTED_FOR_RECONTACT)
	private String whoSuggestedForRecontact;
	
	@Temporal(TemporalType.DATE)
	@Column(name = COLUMN_NAME_SUGGESTION_DATE, length = 10)
	private Date suggestionDate;
	
	@Column(name = COLUMN_NAME_SUGGESTION_REMARKS)
	private String suggestionRemarks;
	
	@Column(name = COLUMN_NAME_WHO_RECONTACTED)
	private String whoRecontacted;
	
	@Temporal(TemporalType.DATE)
	@Column(name = COLUMN_NAME_RECONTACTED_DATE, length = 10)
	private Date recontactedDate;
	
	@Column(name = COLUMN_NAME_RECONTACTED_REMARKS)
	private String recontactedRemarks;
	
	@Column(name = COLUMN_NAME_IS_SELECTED)
	private String isSelected;
	
	@Column(name = COLUMN_NAME_WHO_SELECTED)
	private String whoSelected;
	
	@Temporal(TemporalType.DATE)
	@Column(name = COLUMN_NAME_SELECTION_DATE, length = 10)
	private Date selectionDate;
	
	@Column(name = COLUMN_NAME_SELECTION_REMARKS)
	private String selectionRemarks;
	
	@Column(name = COLUMN_NAME_IS_REJECTED)
	private String isRejected;
	
	@Column(name = COLUMN_NAME_WHO_REJECTED)
	private String whoRejected;
	
	@Temporal(TemporalType.DATE)
	@Column(name = COLUMN_NAME_REJECTION_DATE, length = 10)
	private Date rejectionDate;
	
	@Column(name = COLUMN_NAME_REJECTION_REMARKS)
	private String rejectionRemarks;
	
	@Column(name = COLUMN_NAME_LOCATION)
	private String location;
	
	@Column(name = COLUMN_NAME_REFERENCE)
	private String reference;
	
	@Column(name = COLUMN_NAME_ADDRESS_DETAILS)
	private String addressDetails;
	
	@Temporal(TemporalType.DATE)
	@Column(name = COLUMN_NAME_RECORD_LAST_UPDATED, length = 10, nullable = false)
	private Date recordLastUpdated;
	
	public SubscribeWithUs() {}
	
	public SubscribeWithUs(
			Date applicationDate,
			String applicationStatus,
			String firstName,
			String lastName,
			String contactNumber,
			String emailId,
			String studentGrade,
			String subjects,
			String preferredTimeToCall,
			String additionalDetails,
			String isContacted,
			String whoContacted,
			Date contactedDate,
			String contactedRemarks,
			String isAuthenticationVerified,
			String whoVerified,
			Date verificationDate,
			String verificationRemarks,
			String isToBeRecontacted,
			String whoSuggestedForRecontact,
			Date suggestionDate,
			String suggestionRemarks,
			String whoRecontacted,
			Date recontactedDate,
			String recontactedRemarks,
			String isSelected,
			String whoSelected,
			Date selectionDate,
			String selectionRemarks,
			String isRejected,
			String whoRejected,
			Date rejectionDate,
			String rejectionRemarks,
			String location,
			String reference,
			String addressDetails,
			Date recordLastUpdated
			
	) {
		this.applicationDate = applicationDate;
		this.applicationStatus = applicationStatus;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.studentGrade = studentGrade;
		this.subjects = subjects;
		this.preferredTimeToCall = preferredTimeToCall;
		this.additionalDetails = additionalDetails;
		this.isContacted = isContacted;
		this.whoContacted = whoContacted;
		this.contactedDate = contactedDate;
		this.contactedRemarks = contactedRemarks;
		this.isAuthenticationVerified = isAuthenticationVerified;
		this.whoVerified = whoVerified;
		this.verificationDate = verificationDate;
		this.verificationRemarks = verificationRemarks;
		this.isToBeRecontacted = isToBeRecontacted;
		this.whoSuggestedForRecontact = whoSuggestedForRecontact;
		this.suggestionDate = suggestionDate;
		this.suggestionRemarks = suggestionRemarks;
		this.whoRecontacted = whoRecontacted;
		this.recontactedDate = recontactedDate;
		this.recontactedRemarks = recontactedRemarks;
		this.isSelected = isSelected;
		this.whoSelected = whoSelected;
		this.selectionDate = selectionDate;
		this.selectionRemarks = selectionRemarks;
		this.isRejected = isRejected;
		this.whoRejected = whoRejected;
		this.rejectionDate = rejectionDate;
		this.rejectionRemarks = rejectionRemarks;
		this.location = location;
		this.reference = reference;
		this.addressDetails = addressDetails;
		this.recordLastUpdated = recordLastUpdated;
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

	public Date getContactedDate() {
		return contactedDate;
	}

	public void setContactedDate(Date contactedDate) {
		this.contactedDate = contactedDate;
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

	public Date getVerificationDate() {
		return verificationDate;
	}

	public void setVerificationDate(Date verificationDate) {
		this.verificationDate = verificationDate;
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

	public Date getSuggestionDate() {
		return suggestionDate;
	}

	public void setSuggestionDate(Date suggestionDate) {
		this.suggestionDate = suggestionDate;
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

	public Date getRecontactedDate() {
		return recontactedDate;
	}

	public void setRecontactedDate(Date recontactedDate) {
		this.recontactedDate = recontactedDate;
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

	public Date getSelectionDate() {
		return selectionDate;
	}

	public void setSelectionDate(Date selectionDate) {
		this.selectionDate = selectionDate;
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

	public Date getRejectionDate() {
		return rejectionDate;
	}

	public void setRejectionDate(Date rejectionDate) {
		this.rejectionDate = rejectionDate;
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
	
	public Date getRecordLastUpdated() {
		return recordLastUpdated;
	}

	public void setRecordLastUpdated(Date recordLastUpdated) {
		this.recordLastUpdated = recordLastUpdated;
	}
	
	public Long getTentativeSubscriptionId() {
		return tentativeSubscriptionId;
	}

	public void setTentativeSubscriptionId(Long tentativeSubscriptionId) {
		this.tentativeSubscriptionId = tentativeSubscriptionId;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
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

	@Override
	public String toString() {
		final StringBuilder subscribeWithUsApplication = new StringBuilder(EMPTY_STRING);
		subscribeWithUsApplication.append(PrintFormatterUtils.startATable());
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_TENTATIVE_SUBSCRIPTION_ID, tentativeSubscriptionId));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_APPLICATION_DATE, applicationDate));
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
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACTED_DATE, contactedDate));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACTED_REMARKS, contactedRemarks));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_AUTHENTICATION_VERIFIED, isAuthenticationVerified));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_VERIFIED, whoVerified));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_VERIFICATION_DATE, verificationDate));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_VERIFICATION_REMARKS, verificationRemarks));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_TO_BE_RECONTACTED, isToBeRecontacted));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_SUGGESTED_FOR_RECONTACT, whoSuggestedForRecontact));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUGGESTION_DATE, suggestionDate));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUGGESTION_REMARKS, suggestionRemarks));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_RECONTACTED, whoRecontacted));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECONTACTED_DATE, recontactedDate));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECONTACTED_REMARKS, recontactedRemarks));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_SELECTED, isSelected));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_SELECTED, whoSelected));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SELECTION_DATE, selectionDate));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SELECTION_REMARKS, selectionRemarks));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_REJECTED, isRejected));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_REJECTED, whoRejected));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REJECTION_DATE, rejectionDate));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REJECTION_REMARKS, rejectionRemarks));
		subscribeWithUsApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECORD_LAST_UPDATED, recordLastUpdated));
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
					COLUMN_NAME_RECORD_LAST_UPDATED					
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
						this.applicationDate,
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
						this.whoContacted,
						this.contactedDate,
						this.contactedRemarks,
						this.isAuthenticationVerified,
						this.whoVerified,
						this.verificationDate,
						this.verificationRemarks,
						this.isToBeRecontacted,
						this.whoSuggestedForRecontact,
						this.suggestionDate,
						this.suggestionRemarks,
						this.whoRecontacted,
						this.recontactedDate,
						this.recontactedRemarks,
						this.isSelected,
						this.whoSelected,
						this.selectionDate,
						this.selectionRemarks,
						this.isRejected,
						this.whoRejected,
						this.rejectionDate,
						this.rejectionRemarks,
						this.recordLastUpdated
				};
			}
		}
		return new Object[] {};
	}
}
