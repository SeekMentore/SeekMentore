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
import com.constants.components.publicaccess.BecomeTutorConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.PrintFormatterUtils;

@Entity
@Table( name = BecomeTutorConstants.TABLE_NAME,
		catalog = DatabaseConstants.DATABASE_CATALOG_NAME
)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BecomeTutor extends PublicApplication implements Serializable, BecomeTutorConstants, ApplicationWorkbookObject {

	private static final long serialVersionUID = 7314098186505190523L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = COLUMN_NAME_TENTATIVE_TUTOR_ID, unique = true, nullable = false)
	private Long tentativeTutorId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = COLUMN_NAME_APPLICATION_DATE, length = 10, nullable = false)
	private Date applicationDate;
	
	@Column(name = COLUMN_NAME_APPLICATION_STATUS, unique = true, nullable = false)
	private String applicationStatus;
	
	@Temporal(TemporalType.DATE)
	@Column(name = COLUMN_NAME_DATE_OF_BIRTH, length = 10, nullable = false)
	private Date dateOfBirth;
	
	@Column(name = COLUMN_NAME_CONTACT_NUMBER, unique = true, nullable = false)
	private String contactNumber;
	
	@Column(name = COLUMN_NAME_EMAIL_ID, unique = true, nullable = false)
	private String emailId;
	
	@Column(name = COLUMN_NAME_FIRST_NAME, nullable = false)
	private String firstName;
	
	@Column(name = COLUMN_NAME_LAST_NAME, nullable = false)
	private String lastName;
	
	@Column(name = COLUMN_NAME_GENDER, nullable = false)
	private String gender;
	
	@Column(name = COLUMN_NAME_QUALIFICATION, nullable = false)
	private String qualification;
	
	@Column(name = COLUMN_NAME_PRIMARY_PROFESSION, nullable = false)
	private String primaryProfession;
	
	@Column(name = COLUMN_NAME_TRANSPORT_MODE, nullable = false)
	private String transportMode;
	
	@Column(name = COLUMN_NAME_TEACHING_EXPERIENCE, nullable = false)
	private Integer teachingExp;
	
	@Column(name = COLUMN_NAME_STUDENT_GRADE, nullable = false)
	private String studentGrade;
	
	@Column(name = COLUMN_NAME_SUBJECTS, nullable = false)
	private String subjects;
	
	@Column(name = COLUMN_NAME_LOCATIONS, nullable = false)
	private String locations;
	
	@Column(name = COLUMN_NAME_PREFERRED_TIME_TO_CALL, nullable = false)
	private String preferredTimeToCall;
	
	@Column(name = COLUMN_NAME_ADDITIONAL_DETAILS)
	private String additionalDetails;
	
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
	
	@Column(name = COLUMN_NAME_REJECTION_COUNT)
	private Integer rejectionCount;
	
	@Column(name = COLUMN_NAME_REFERENCE)
	private String reference;
	
	@Column(name = COLUMN_NAME_PREFERRED_TEACHING_TYPE)
	private String preferredTeachingType;
	
	@Column(name = COLUMN_NAME_RE_APPLIED)
	private String reApplied;
	
	@Temporal(TemporalType.DATE)
	@Column(name = COLUMN_NAME_PREVIOUS_APPLICATION_DATE, length = 10)
	private Date previousApplicationDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = COLUMN_NAME_RECORD_LAST_UPDATED, length = 10, nullable = false)
	private Date recordLastUpdated;
	
	public BecomeTutor() {}
	
	public BecomeTutor(
			Date applicationDate,
			String applicationStatus,
			Date dateOfBirth,
			String contactNumber,
			String emailId,
			String firstName,
			String lastName,
			String gender,
			String qualification,
			String primaryProfession,
			String transportMode,
			Integer teachingExp,
			String studentGrade,
			String subjects,
			String locations,
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
			Integer rejectionCount,
			String reApplied,
			Date previousApplicationDate,
			String reference,
			String preferredTeachingType,
			Date recordLastUpdated
	) {
		this.applicationDate = applicationDate;
		this.applicationStatus = applicationStatus;
		this.dateOfBirth = dateOfBirth;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.qualification = qualification;
		this.primaryProfession = primaryProfession;
		this.transportMode = transportMode;
		this.teachingExp = teachingExp;
		this.studentGrade = studentGrade;
		this.subjects = subjects;
		this.locations = locations;
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
		this.rejectionCount = rejectionCount;
		this.reApplied = reApplied;
		this.reference = reference;
		this.preferredTeachingType = preferredTeachingType;
		this.previousApplicationDate = previousApplicationDate;
		this.recordLastUpdated = recordLastUpdated;
	}

	public Long getTentativeTutorId() {
		return tentativeTutorId;
	}

	public void setTentativeTutorId(Long tentativeTutorId) {
		this.tentativeTutorId = tentativeTutorId;
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

	public Date getPreviousApplicationDate() {
		return previousApplicationDate;
	}

	public void setPreviousApplicationDate(Date previousApplicationDate) {
		this.previousApplicationDate = previousApplicationDate;
	}
	
	public Date getRecordLastUpdated() {
		return recordLastUpdated;
	}

	public void setRecordLastUpdated(Date recordLastUpdated) {
		this.recordLastUpdated = recordLastUpdated;
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
	
	@Override
	public String toString() {
		final StringBuilder becomeTutorApplication = new StringBuilder(EMPTY_STRING);
		becomeTutorApplication.append(PrintFormatterUtils.startATable());
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_TENTATIVE_TUTOR_ID, this.tentativeTutorId));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_APPLICATION_DATE, this.applicationDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_APPLICATION_STATUS, this.applicationStatus));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_DATE_OF_BIRTH, this.dateOfBirth));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACT_NUMBER, this.contactNumber));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_EMAIL_ID, this.emailId));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_FIRST_NAME, this.firstName));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_LAST_NAME, this.lastName));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_GENDER, this.gender));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUALIFICATION, this.qualification));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_PRIMARY_PROFESSION, this.primaryProfession));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_TRANSPORT_MODE, this.transportMode));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_TEACHING_EXPERIENCE, this.teachingExp));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_STUDENT_GRADE, this.studentGrade));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUBJECTS, this.subjects));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_LOCATIONS, this.locations));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_PREFERRED_TIME_TO_CALL, this.preferredTimeToCall));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REFERENCE, this.reference));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_PREFERRED_TEACHING_TYPE, this.preferredTeachingType));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_ADDITIONAL_DETAILS, this.additionalDetails));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_CONTACTED, this.isContacted));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_CONTACTED, this.whoContacted));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACTED_DATE, this.contactedDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACTED_REMARKS, this.contactedRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_AUTHENTICATION_VERIFIED, this.isAuthenticationVerified));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_VERIFIED, this.whoVerified));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_VERIFICATION_DATE, this.verificationDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_VERIFICATION_REMARKS, this.verificationRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_TO_BE_RECONTACTED, this.isToBeRecontacted));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_SUGGESTED_FOR_RECONTACT, this.whoSuggestedForRecontact));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUGGESTION_DATE, this.suggestionDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUGGESTION_REMARKS, this.suggestionRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_RECONTACTED, this.whoRecontacted));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECONTACTED_DATE, this.recontactedDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECONTACTED_REMARKS, this.recontactedRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_SELECTED, this.isSelected));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_SELECTED, this.whoSelected));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SELECTION_DATE, this.selectionDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SELECTION_REMARKS, this.selectionRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_REJECTED, this.isRejected));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_REJECTED, this.whoRejected));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REJECTION_DATE, this.rejectionDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REJECTION_REMARKS, this.rejectionRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REJECTION_COUNT, this.rejectionCount));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RE_APPLIED, this.reApplied));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_PREVIOUS_APPLICATION_DATE, this.previousApplicationDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECORD_LAST_UPDATED, this.recordLastUpdated));
		becomeTutorApplication.append(PrintFormatterUtils.endATable());
		return becomeTutorApplication.toString();
	}

	@Override
	public Object[] getReportHeaders(final String reportSwitch) {
		switch (reportSwitch) {
			case "Admin_Report" : {
				return new Object[] {
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
						this.dateOfBirth,
						this.contactNumber,
						this.emailId,
						this.firstName,
						this.lastName,
						this.gender,
						this.qualification,
						this.primaryProfession,
						this.transportMode,
						this.teachingExp,
						this.studentGrade,
						this.subjects,
						this.locations,
						this.preferredTimeToCall,
						this.reference,
						this.preferredTeachingType,
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
						this.rejectionCount,
						this.reApplied,
						this.previousApplicationDate,
						this.recordLastUpdated
				};
			}
		}
		return new Object[] {};
	}
}
