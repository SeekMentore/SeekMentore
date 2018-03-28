package com.model.components.form2;

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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.constants.DatabaseConstants;
import com.constants.components.Form2Constants;

@Entity
@Table(	name = Form2Constants.FORM2_TABLE_NAME,
		catalog = DatabaseConstants.DATABASE_CATALOG_NAME, 
		uniqueConstraints = {
			@UniqueConstraint(columnNames = Form2Constants.FORM2_COLUMN_NAME_FOR_EMP_ID)
		}
)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Form2 implements Serializable, Form2Constants {

	private static final long serialVersionUID = 7314098186505190523L;
	
	@Id
	@Column(name = FORM2_COLUMN_NAME_FOR_EMP_ID, unique = true, nullable = false, length = LENGTH_LIMIT_FOR_EMP_ID)
	private String empId;
	
	@Column(name = "FORM_STATUS", nullable = false)
	private String formStatus;
	
	@Column(name = FORM2_COLUMN_NAME_FOR_NAME, nullable = false, length = LENGTH_LIMIT_FOR_NAME)
	private String name;
	
	@Column(name = FORM2_COLUMN_NAME_FOR_FATHER_NAME, nullable = false, length = LENGTH_LIMIT_FOR_NAME)
	private String fathersName;
	
	@Temporal(TemporalType.DATE)
	@Column(name = FORM2_COLUMN_NAME_FOR_DATE_OF_BIRTH, nullable = false, length = LENGTH_LIMIT_FOR_DATE_OF_BIRTH)
	private Date dateOfBirth;
	
	@Column(name = FORM2_COLUMN_NAME_FOR_GENDER, nullable = false, length = LENGTH_LIMIT_FOR_GENDER)
	private String gender;
	
	@Column(name = FORM2_COLUMN_NAME_FOR_MARITAL_STATUS, nullable = false, length = LENGTH_LIMIT_FOR_GENDER)
	private String maritalStatus;
	
	@Column(name = FORM2_COLUMN_NAME_FOR_ACCOUNT_NUMBER, nullable = false, length = LENGTH_LIMIT_FOR_ACCOUNT_NUMBER)
	private String accountNumber;
	
	@Column(name = FORM2_COLUMN_NAME_FOR_PERMANENT_ADDRESS, nullable = false, length = LENGTH_LIMIT_FOR_PERMANENT_ADDRESS)
	private String permanentAddress;
	
	@Column(name = FORM2_COLUMN_NAME_FOR_TEMPORARY_ADDRESS_SAME_AS_PERMAMENT, nullable = false, length = LENGTH_LIMIT_FOR_GENDER)
	private String temporaryAddressSameAsPermament;
	
	@Column(name = FORM2_COLUMN_NAME_FOR_TEMPORARY_ADDRESS, length = LENGTH_LIMIT_FOR_PERMANENT_ADDRESS)
	private String temporaryAddress;
	
	@Temporal(TemporalType.DATE)
	@Column(name = FORM2_COLUMN_NAME_FOR_DATE_OF_JOINING_EPF, nullable = false, length = LENGTH_LIMIT_FOR_DATE_OF_BIRTH)
	private Date dateOfJoiningEPF;
	
	@Temporal(TemporalType.DATE)
	@Column(name = FORM2_COLUMN_NAME_FOR_DATE_OF_JOINING_EPS, nullable = false, length = LENGTH_LIMIT_FOR_DATE_OF_BIRTH)
	private Date dateOfJoiningEPS;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMP_ID")
	@XmlElement
    @XmlInverseReference(mappedBy="form")
	private Set<EmployeePFNominationDetails> employeePFNominationDetailsList;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMP_ID")
	@XmlElement
    @XmlInverseReference(mappedBy="form")
	private Set<WidowChildrenPensionNominee> widowChildrenPensionNomineeList;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMP_ID")
	@XmlElement
    @XmlInverseReference(mappedBy="form")
	private Set<OnlyWidowPensionNominee> onlyWidowPensionNomineeList;
	
	public Form2() {}

	public Form2 (
			String empId,
			String formStatus,
			String name,
			String fathersName,
			Date dateOfBirth,
			String gender,
			String maritalStatus,
			String accountNumber,
			String permanentAddress,
			String temporaryAddressSameAsPermament,
			String temporaryAddress,
			Date dateOfJoiningEPF,
			Date dateOfJoiningEPS
	) {
		this.empId = empId;
		this.formStatus = formStatus;
		this.name = name;
		this.fathersName = fathersName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.permanentAddress = permanentAddress;
		this.temporaryAddressSameAsPermament = temporaryAddressSameAsPermament;
		this.temporaryAddress = temporaryAddress;
		this.dateOfJoiningEPF = dateOfJoiningEPF;
		this.dateOfJoiningEPS = dateOfJoiningEPS;
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

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getTemporaryAddressSameAsPermament() {
		return temporaryAddressSameAsPermament;
	}

	public void setTemporaryAddressSameAsPermament(String temporaryAddressSameAsPermament) {
		this.temporaryAddressSameAsPermament = temporaryAddressSameAsPermament;
	}

	public String getTemporaryAddress() {
		return temporaryAddress;
	}

	public void setTemporaryAddress(String temporaryAddress) {
		this.temporaryAddress = temporaryAddress;
	}

	public Date getDateOfJoiningEPF() {
		return dateOfJoiningEPF;
	}

	public void setDateOfJoiningEPF(Date dateOfJoiningEPF) {
		this.dateOfJoiningEPF = dateOfJoiningEPF;
	}

	public Date getDateOfJoiningEPS() {
		return dateOfJoiningEPS;
	}

	public void setDateOfJoiningEPS(Date dateOfJoiningEPS) {
		this.dateOfJoiningEPS = dateOfJoiningEPS;
	}
	
	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}

	public Set<EmployeePFNominationDetails> getEmployeePFNominationDetailsList() {
		return employeePFNominationDetailsList;
	}

	public void setEmployeePFNominationDetailsList(Set<EmployeePFNominationDetails> employeePFNominationDetailsList) {
		this.employeePFNominationDetailsList = employeePFNominationDetailsList;
	}

	public Set<WidowChildrenPensionNominee> getWidowChildrenPensionNomineeList() {
		return widowChildrenPensionNomineeList;
	}

	public void setWidowChildrenPensionNomineeList(Set<WidowChildrenPensionNominee> widowChildrenPensionNomineeList) {
		this.widowChildrenPensionNomineeList = widowChildrenPensionNomineeList;
	}

	public Set<OnlyWidowPensionNominee> getOnlyWidowPensionNomineeList() {
		return onlyWidowPensionNomineeList;
	}

	public void setOnlyWidowPensionNomineeList(Set<OnlyWidowPensionNominee> onlyWidowPensionNomineeList) {
		this.onlyWidowPensionNomineeList = onlyWidowPensionNomineeList;
	}
}
