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
import com.utils.ValidationUtils;

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
	
	@Column(name = COLUMN_NAME_REJECTION_COUNT)
	private Integer rejectionCount;
	
	@Column(name = COLUMN_NAME_REFERENCE)
	private String reference;
	
	@Column(name = COLUMN_NAME_PREFERRED_TEACHING_TYPE)
	private String preferredTeachingType;
	
	@Column(name = COLUMN_NAME_RE_APPLIED)
	private String reApplied;
	
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
	
	public BecomeTutor() {}
	
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

	@Override
	public String toString() {
		final StringBuilder becomeTutorApplication = new StringBuilder(EMPTY_STRING);
		becomeTutorApplication.append(PrintFormatterUtils.startATable());
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_TENTATIVE_TUTOR_ID, this.tentativeTutorId));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_APPLICATION_DATE, new Date(this.applicationDateMillis)));
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
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACTED_DATE, new Date(this.contactedDateMillis)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACTED_REMARKS, this.contactedRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_AUTHENTICATION_VERIFIED, this.isAuthenticationVerified));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_VERIFIED, this.whoVerified));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_VERIFICATION_DATE, new Date(this.verificationDateMillis)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_VERIFICATION_REMARKS, this.verificationRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_TO_BE_RECONTACTED, this.isToBeRecontacted));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_SUGGESTED_FOR_RECONTACT, this.whoSuggestedForRecontact));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUGGESTION_DATE, new Date(this.suggestionDateMillis)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUGGESTION_REMARKS, this.suggestionRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_RECONTACTED, this.whoRecontacted));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECONTACTED_DATE, new Date(this.recontactedDateMillis)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECONTACTED_REMARKS, this.recontactedRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_SELECTED, this.isSelected));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_SELECTED, this.whoSelected));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SELECTION_DATE, new Date(this.selectionDateMillis)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SELECTION_REMARKS, this.selectionRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_REJECTED, this.isRejected));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_REJECTED, this.whoRejected));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REJECTION_DATE, new Date(this.rejectionDateMillis)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REJECTION_REMARKS, this.rejectionRemarks));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REJECTION_COUNT, this.rejectionCount));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RE_APPLIED, this.reApplied));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_PREVIOUS_APPLICATION_DATE, new Date(this.previousApplicationDateMillis)));
		becomeTutorApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECORD_LAST_UPDATED, new Date(this.recordLastUpdatedMillis)));
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
						new Date(this.contactedDateMillis),
						this.contactedRemarks,
						this.isAuthenticationVerified,
						this.whoVerified,
						new Date(this.verificationDateMillis),
						this.verificationRemarks,
						this.isToBeRecontacted,
						this.whoSuggestedForRecontact,
						new Date(this.suggestionDateMillis),
						this.suggestionRemarks,
						this.whoRecontacted,
						new Date(this.recontactedDateMillis),
						this.recontactedRemarks,
						this.isSelected,
						this.whoSelected,
						new Date(this.selectionDateMillis),
						this.selectionRemarks,
						this.isRejected,
						this.whoRejected,
						new Date(this.rejectionDateMillis),
						this.rejectionRemarks,
						this.rejectionCount,
						this.reApplied,
						new Date(this.previousApplicationDateMillis),
						new Date(this.recordLastUpdatedMillis),
						this.updatedBy
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
			case "tentativeTutorId" : return "TENTATIVE_TUTOR_ID";
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
		}
		return EMPTY_STRING;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}
