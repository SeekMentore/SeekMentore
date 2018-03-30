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
	@Column(name = "TENT_TUTOR_ID", unique = true, nullable = false)
	private long tentativeTutorId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH", length = 10, nullable = false)
	private Date dateOfBirth;
	
	@Column(name = "CONTACT_NUMBER", unique = true, nullable = false)
	private String contactNumber;
	
	@Column(name = "EMAIL_ID", unique = true, nullable = false)
	private String emailId;
	
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;
	
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;
	
	@Column(name = "GENDER", nullable = false)
	private String gender;
	
	@Column(name = "QUALIFICATION", nullable = false)
	private String qualification;
	
	@Column(name = "PRIMARY_PROFESSION", nullable = false)
	private String primaryProfession;
	
	@Column(name = "TRANSPORT_MODE", nullable = false)
	private String transportMode;
	
	@Column(name = "TEACHING_EXP", nullable = false)
	private int teachingExp;
	
	@Column(name = "LOCATIONS", nullable = false)
	private String locations;
	
	@Column(name = "PRFRD_TIME_CALL", nullable = false)
	private String preferredTimeToCall;
	
	@Column(name = "ADDL_DETAILS", nullable = false)
	private String additionalDetails;
	
	@Column(name = "IS_CONTACTED", nullable = false)
	private String isContacted;
	
	@Column(name = "IS_AUTH_VERIFIED")
	private String isAuthenticationVerified;
	
	@Column(name = "IS_TO_BE_RECONTACTED")
	private String isToBeRecontacted;
	
	@Column(name = "IS_SELECTED")
	private String isSelected;
	
	public BecomeTutor() {}
	
	public BecomeTutor(
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
			String locations,
			String preferredTimeToCall,
			String additionalDetails,
			String isContacted,
			String isAuthenticationVerified,
			String isToBeRecontacted,
			String isSelected
	) {
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
		this.locations = locations;
		this.preferredTimeToCall = preferredTimeToCall;
		this.additionalDetails = additionalDetails;
		this.isContacted = isContacted;
		this.isAuthenticationVerified = isAuthenticationVerified;
		this.isToBeRecontacted = isToBeRecontacted;
		this.isSelected = isSelected;
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
}
