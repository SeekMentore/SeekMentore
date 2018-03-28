package com.model.components.formF;

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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.constants.ApplicationConstants;
import com.constants.DatabaseConstants;
import com.constants.components.FormFConstants;

@Entity
@Table( name = FormFConstants.FORMF_TABLE_NAME, 
		catalog = DatabaseConstants.DATABASE_CATALOG_NAME
)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FormF implements Serializable, FormFConstants {

	private static final long serialVersionUID = 7314098186505190523L;

	@Id
	@Column(name = "EMP_ID", unique = true, nullable = false)
	private String empId;
	
	@Column(name = "FORM_STATUS", nullable = false)
	private String formStatus;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "NOTICE_DATE", unique = true, nullable = false, length = 10)
	private Date noticeDate;
	
	@Column(name = "FULL_NAME", nullable = false)
	private String fullName;
	
	@Column(name = "GENDER", nullable = false)
	private String gender;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DOA", unique = true, nullable = false, length = 10)
	private Date doa;
	
	@Column(name = "DEPARTMENT", nullable = false)
	private String department;
	
	@Column(name = "EMPLOYEE_NUMBER", nullable = false)
	private String employeeNumber;
	
	@Column(name = "FATHER_NAME", nullable = false)
	private String fathersName;
	
	@Column(name = "HUSBAND_NAME", nullable = false)
	private String husbandsName;
	
	@Column(name = "MARITAL_STATUS", nullable = false)
	private String maritalStatus;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH", unique = true, nullable = false, length = 10)
	private Date dateOfBirth;
	
	@Column(name = "PERMANENT_ADDRESS", nullable = false)
	private String permanentAddress;
	
	@Column(name = "STATE", nullable = false)
	private String state;
	
	@Column(name = "PINCODE", nullable = false)
	private String pincode;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "APPOINTMENT_DATE", unique = true, nullable = false, length = 10)
	private Date appointmentDate;
	
	@Column(name = "DESIGNATION", nullable = false)
	private String designation;
	
	@Column(name = "FAMILY_FLAG", nullable = false)
	private String familyFlagString;
	
	@Column(name = "SELF_DEPENDENTS", nullable = false)
	private String selfDependents;
	
	@Column(name = "HUSBAND_DEPENDENTS", nullable = false)
	private String husbandDependents;
	
	@Transient
	private boolean familyFlag;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMP_ID")
	@XmlElement
    @XmlInverseReference(mappedBy="form")
	private Set<EmployeeGratuityNomineeDetails> employeeGratuityNomineeDetailsList;
	
	public FormF() {}
	
	public FormF(
			String empId,
			String formStatus,
			String name,
			Date noticeDate,
			String fullName,
			String gender,
			Date doa,
			String department,
			String employeeNumber,
			String fathersName,
			String husbandsName,
			String maritalStatus,
			Date dateOfBirth,
			String permanentAddress,
			String state,
			String pincode,
			Date appointmentDate,
			String designation,
			String familyFlagString,
			String selfDependents,
			String husbandDependents
	) {
		this.empId = empId;
		this.formStatus = formStatus;
		this.name = name;
		this.noticeDate = noticeDate;
		this.fullName = fullName;
		this.gender = gender;
		this.doa = doa;
		this.department = department;
		this.employeeNumber = employeeNumber;
		this.fathersName = fathersName;
		this.husbandsName = husbandsName;
		this.maritalStatus = maritalStatus;
		this.dateOfBirth = dateOfBirth;
		this.permanentAddress = permanentAddress;
		this.state = state;
		this.pincode = pincode;
		this.appointmentDate = appointmentDate;
		this.designation = designation;
		this.familyFlagString = familyFlagString;
		this.selfDependents = selfDependents;
		this.husbandDependents = husbandDependents;
		setFamilyFlag(isFamilyFlag());
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

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDoa() {
		return doa;
	}

	public void setDoa(Date doa) {
		this.doa = doa;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getHusbandsName() {
		return husbandsName;
	}

	public void setHusbandsName(String husbandsName) {
		this.husbandsName = husbandsName;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}
	
	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getFamilyFlagString() {
		return familyFlagString;
	}

	public void setFamilyFlagString(String familyFlagString) {
		this.familyFlagString = familyFlagString;
		setFamilyFlag(isFamilyFlag());
	}

	public String getSelfDependents() {
		return selfDependents;
	}

	public void setSelfDependents(String selfDependents) {
		this.selfDependents = selfDependents;
	}

	public String getHusbandDependents() {
		return husbandDependents;
	}

	public void setHusbandDependents(String husbandDependents) {
		this.husbandDependents = husbandDependents;
	}

	public boolean isFamilyFlag() {
		return ApplicationConstants.YES.equals(getFamilyFlagString());
	}
	
	private void setFamilyFlag(boolean familyFlag) {
		this.familyFlag = familyFlag;
	}
	
	public Set<EmployeeGratuityNomineeDetails> getEmployeeGratuityNomineeDetailsList() {
		return employeeGratuityNomineeDetailsList;
	}

	public void setEmployeeGratuityNomineeDetailsList(
			Set<EmployeeGratuityNomineeDetails> employeeGratuityNomineeDetailsList) {
		this.employeeGratuityNomineeDetailsList = employeeGratuityNomineeDetailsList;
	}
}
