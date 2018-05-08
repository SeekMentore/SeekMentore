package com.model.components;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.constants.components.publicaccess.RegisteredTutorConstants;

public class RegisteredTutor implements Serializable, RegisteredTutorConstants {
	
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
	private String encyptedPassword;
	private String userId;
	private Date recordLastUpdated;
	private String updatedBy;
	private String preferredTeachingType;
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
		newInstance.encyptedPassword = encyptedPassword;
		newInstance.userId = userId;
		newInstance.recordLastUpdated = recordLastUpdated;
		newInstance.updatedBy = updatedBy;
		newInstance.preferredTeachingType = preferredTeachingType;
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

	public String getEncyptedPassword() {
		return encyptedPassword;
	}

	public void setEncyptedPassword(String encyptedPassword) {
		this.encyptedPassword = encyptedPassword;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getTeachingExp() {
		return teachingExp;
	}

	public void setTeachingExp(Integer teachingExp) {
		this.teachingExp = teachingExp;
	}

	public Date getRecordLastUpdated() {
		return recordLastUpdated;
	}

	public void setRecordLastUpdated(Date recordLastUpdated) {
		this.recordLastUpdated = recordLastUpdated;
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
}
