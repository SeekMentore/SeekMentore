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
import com.utils.PrintFormatterUtils;

@Entity
@Table( name = BecomeTutorConstants.TABLE_NAME, 
		catalog = DatabaseConstants.DATABASE_CATALOG_NAME
)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BecomeTutor extends PublicApplication implements Serializable, BecomeTutorConstants {

	private static final long serialVersionUID = 7314098186505190523L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = COLUMN_NAME_TENTATIVE_TUTOR_ID, unique = true, nullable = false)
	private long tentativeTutorId;
	
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
	private int teachingExp;
	
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
	private int rejectionCount;
	
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
			int teachingExp,
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
			int rejectionCount,
			String reApplied,
			Date previousApplicationDate,
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
		this.previousApplicationDate = previousApplicationDate;
		this.recordLastUpdated = recordLastUpdated;
	}

	public long getTentativeTutorId() {
		return tentativeTutorId;
	}

	public void setTentativeTutorId(long tentativeTutorId) {
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

	public int getTeachingExp() {
		return teachingExp;
	}

	public void setTeachingExp(int teachingExp) {
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

	public int getRejectionCount() {
		return rejectionCount;
	}

	public void setRejectionCount(int rejectionCount) {
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
	
	@Override
	public String toString() {
		final StringBuilder becomeTutorApplication = new StringBuilder(EMPTY_STRING);
		becomeTutorApplication.append(PrintFormatterUtils.startATable());
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_TENTATIVE_TUTOR_ID, tentativeTutorId));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_APPLICATION_DATE, applicationDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_APPLICATION_STATUS, applicationStatus));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_DATE_OF_BIRTH, dateOfBirth));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACT_NUMBER, contactNumber));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_EMAIL_ID, emailId));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_FIRST_NAME, firstName));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_LAST_NAME, lastName));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_GENDER, gender));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUALIFICATION, qualification));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_PRIMARY_PROFESSION, primaryProfession));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_TRANSPORT_MODE, transportMode));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_TEACHING_EXPERIENCE, teachingExp));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_STUDENT_GRADE, studentGrade));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUBJECTS, subjects));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_LOCATIONS, locations));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_PREFERRED_TIME_TO_CALL, preferredTimeToCall));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_ADDITIONAL_DETAILS, additionalDetails));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_CONTACTED, isContacted));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_CONTACTED, whoContacted));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACTED_DATE, contactedDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACTED_REMARKS, contactedRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_AUTHENTICATION_VERIFIED, isAuthenticationVerified));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_VERIFIED, whoVerified));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_VERIFICATION_DATE, verificationDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_VERIFICATION_REMARKS, verificationRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_TO_BE_RECONTACTED, isToBeRecontacted));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_SUGGESTED_FOR_RECONTACT, whoSuggestedForRecontact));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUGGESTION_DATE, suggestionDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUGGESTION_REMARKS, suggestionRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_RECONTACTED, whoRecontacted));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECONTACTED_DATE, recontactedDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECONTACTED_REMARKS, recontactedRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_SELECTED, isSelected));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_SELECTED, whoSelected));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SELECTION_DATE, selectionDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SELECTION_REMARKS, selectionRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_REJECTED, isRejected));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_REJECTED, whoRejected));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REJECTION_DATE, rejectionDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REJECTION_REMARKS, rejectionRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REJECTION_COUNT, rejectionCount));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RE_APPLIED, reApplied));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_PREVIOUS_APPLICATION_DATE, previousApplicationDate));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECORD_LAST_UPDATED, recordLastUpdated));
		becomeTutorApplication.append(PrintFormatterUtils.endATable());
		return becomeTutorApplication.toString();
	}
}
