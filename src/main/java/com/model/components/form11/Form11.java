package com.model.components.form11;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.constants.DatabaseConstants;
import com.constants.components.Form11Constants;

@Entity
@Table( name = Form11Constants.FORM11_TABLE_NAME, 
		catalog = DatabaseConstants.DATABASE_CATALOG_NAME
)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Form11 implements Serializable, Form11Constants {

	private static final long serialVersionUID = 7314098186505190523L;
	
	@Id
	@Column(name = "EMP_ID", unique = true, nullable = false)
	private String empId;
	
	@Column(name = "FORM_STATUS", nullable = false)
	private String formStatus;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH", unique = true, nullable = false, length = 10)
	private Date dateOfBirth;
	
	@Column(name = "FATHER_HUSBAND_NAME", nullable = false)
	private String fatherOrHusbandsName;
	
	@Column(name = "RELATION_WITH", nullable = false)
	private String relationWithAbove;
	
	@Column(name = "GENDER", nullable = false)
	private String gender;
	
	@Column(name = "MOBILE_NUMBER", nullable = false)
	private String mobileNumber;
	
	@Column(name = "EMAIL_ID", unique = true, nullable = false)
	private String emailId;
	
	@Column(name = "EARLIER_MEMBER_OF_PF_1952", nullable = false)
	private String earlierMemberOfPF1952;
	
	@Column(name = "EARLIER_MEMBER_OF_PF_1995", nullable = false)
	private String earlierMemberOfPF1995;
	
	@Column(name = "UAN", nullable = false)
	private String uan;
	
	@Column(name = "PF_MEMBER_ID", nullable = false)
	private String pfMemberId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_EXIT", unique = true, nullable = false, length = 10)
	private Date dateOfExit;
	
	@Column(name = "SCHEME_CERTIFICATE_NUMBER", nullable = false)
	private String schemeCertificateNumber;
	
	@Column(name = "PPO_NUMBER", nullable = false)
	private String ppoNumber;
	
	@Column(name = "INTERNATIONAL_WORKER", nullable = false)
	private String internationalWorker;
	
	@Column(name = "COUNTRY_OF_ORIGIN", nullable = false)
	private String countryOfOrigin;
	
	@Column(name = "PASSPORT_NUMBER", nullable = false)
	private String passportNumber;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PASSPORT_VALID_FROM", unique = true, nullable = false, length = 10)
	private Date passportValidFrom;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PASSPORT_VALID_TO", unique = true, nullable = false, length = 10)
	private Date passportValidTo;
	
	@Column(name = "EDUCATIONAL_QUALIFICATION", nullable = false)
	private String educationalQualification;
	
	@Column(name = "MARITAL_STATUS", nullable = false)
	private String maritalStatus;
	
	@Column(name = "SPECIALLY_ABLED", nullable = false)
	private String speciallyAbled;
	
	@Column(name = "CATEGORY", nullable = false)
	private String category;
	
	@Column(name = "NON_CONTRIBUTORY_DAYS", nullable = false)
	private int nonContributoryDays;
	
	@Column(name = "PF_ACC_NUMBER", nullable = true)
	private String pfAccNumber;
	
	@Column(name = "ESTABLISH_NAME_ADDR", nullable = true)
	private String establishNameWithAddr;
	
	@Column(name = "TRUST_NAME_ADDR", nullable = true)
	private String trustNameWithAddr;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_JOINING", unique = true, nullable = false, length = 10)
	private Date dateOfJoining;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMP_ID")
	@XmlElement
    @XmlInverseReference(mappedBy="form")
	private Set<KYCDetails> kycDetailsList;
	
	public Form11() {}
	
	public Form11(
			String empId,
			String formStatus,
			String name,
			Date dateOfBirth,
			String fatherOrHusbandsName,
			String relationWithAbove,
			String gender,
			String mobileNumber,
			String emailId,
			String earlierMemberOfPF1952,
			String earlierMemberOfPF1995,
			String uan,
			String pfMemberId,
			Date dateOfExit,
			String schemeCertificateNumber,
			String ppoNumber,
			String internationalWorker,
			String countryOfOrigin,
			String passportNumber,
			Date passportValidFrom,
			Date passportValidTo,
			String educationalQualification,
			String maritalStatus,
			String speciallyAbled,
			String category,
			int nonContributoryDays,
			String pfAccNumber,
			String establishNameWithAddr,
			String trustNameWithAddr,
			Date dateOfJoining
	) {
		this.empId = empId;
		this.formStatus = formStatus;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.fatherOrHusbandsName = fatherOrHusbandsName;
		this.relationWithAbove = relationWithAbove;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.earlierMemberOfPF1952 = earlierMemberOfPF1952;
		this.earlierMemberOfPF1995 = earlierMemberOfPF1995;
		this.uan = uan;
		this.pfMemberId = pfMemberId;
		this.dateOfExit = dateOfExit;
		this.schemeCertificateNumber = schemeCertificateNumber;
		this.ppoNumber = ppoNumber;
		this.internationalWorker = internationalWorker;
		this.countryOfOrigin = countryOfOrigin;
		this.passportNumber = passportNumber;
		this.passportValidFrom = passportValidFrom;
		this.passportValidTo = passportValidTo;
		this.educationalQualification = educationalQualification;
		this.maritalStatus = maritalStatus;
		this.speciallyAbled = speciallyAbled;
		this.category = category;
		this.nonContributoryDays = nonContributoryDays;
		this.pfAccNumber = pfAccNumber;
		this.establishNameWithAddr = establishNameWithAddr;
		this.trustNameWithAddr = trustNameWithAddr;
		this.dateOfJoining = dateOfJoining;
	}
	
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFatherOrHusbandsName() {
		return fatherOrHusbandsName;
	}

	public void setFatherOrHusbandsName(String fatherOrHusbandsName) {
		this.fatherOrHusbandsName = fatherOrHusbandsName;
	}

	public String getRelationWithAbove() {
		return relationWithAbove;
	}

	public void setRelationWithAbove(String relationWithAbove) {
		this.relationWithAbove = relationWithAbove;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEarlierMemberOfPF1952() {
		return earlierMemberOfPF1952;
	}

	public void setEarlierMemberOfPF1952(String earlierMemberOfPF1952) {
		this.earlierMemberOfPF1952 = earlierMemberOfPF1952;
	}

	public String getEarlierMemberOfPF1995() {
		return earlierMemberOfPF1995;
	}

	public void setEarlierMemberOfPF1995(String earlierMemberOfPF1995) {
		this.earlierMemberOfPF1995 = earlierMemberOfPF1995;
	}

	public String getUan() {
		return uan;
	}

	public void setUan(String uan) {
		this.uan = uan;
	}

	public String getPfMemberId() {
		return pfMemberId;
	}

	public void setPfMemberId(String pfMemberId) {
		this.pfMemberId = pfMemberId;
	}

	public Date getDateOfExit() {
		return dateOfExit;
	}

	public void setDateOfExit(Date dateOfExit) {
		this.dateOfExit = dateOfExit;
	}

	public String getSchemeCertificateNumber() {
		return schemeCertificateNumber;
	}

	public void setSchemeCertificateNumber(String schemeCertificateNumber) {
		this.schemeCertificateNumber = schemeCertificateNumber;
	}

	public String getPpoNumber() {
		return ppoNumber;
	}

	public void setPpoNumber(String ppoNumber) {
		this.ppoNumber = ppoNumber;
	}

	public String getInternationalWorker() {
		return internationalWorker;
	}

	public void setInternationalWorker(String internationalWorker) {
		this.internationalWorker = internationalWorker;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public Date getPassportValidFrom() {
		return passportValidFrom;
	}

	public void setPassportValidFrom(Date passportValidFrom) {
		this.passportValidFrom = passportValidFrom;
	}

	public Date getPassportValidTo() {
		return passportValidTo;
	}

	public void setPassportValidTo(Date passportValidTo) {
		this.passportValidTo = passportValidTo;
	}

	public String getEducationalQualification() {
		return educationalQualification;
	}

	public void setEducationalQualification(String educationalQualification) {
		this.educationalQualification = educationalQualification;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getSpeciallyAbled() {
		return speciallyAbled;
	}

	public void setSpeciallyAbled(String speciallyAbled) {
		this.speciallyAbled = speciallyAbled;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getNonContributoryDays() {
		return nonContributoryDays;
	}

	public void setNonContributoryDays(int nonContributoryDays) {
		this.nonContributoryDays = nonContributoryDays;
	}

	public String getPfAccNumber() {
		return pfAccNumber;
	}

	public void setPfAccNumber(String pfAccNumber) {
		this.pfAccNumber = pfAccNumber;
	}

	public String getEstablishNameWithAddr() {
		return establishNameWithAddr;
	}

	public void setEstablishNameWithAddr(String establishNameWithAddr) {
		this.establishNameWithAddr = establishNameWithAddr;
	}

	public String getTrustNameWithAddr() {
		return trustNameWithAddr;
	}

	public void setTrustNameWithAddr(String trustNameWithAddr) {
		this.trustNameWithAddr = trustNameWithAddr;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	
	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}
	
	public Set<KYCDetails> getKycDetailsList() {
		return kycDetailsList;
	}

	public void setKycDetailsList(Set<KYCDetails> kycDetailsList) {
		this.kycDetailsList = kycDetailsList;
	}
}
