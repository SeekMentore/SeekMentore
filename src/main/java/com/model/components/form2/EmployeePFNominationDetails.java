package com.model.components.form2;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import com.constants.DatabaseConstants;
import com.constants.components.Form2Constants;

@Entity
@Table(	name = "FORM2_EMP_PF_NOM_DTLS", 
		catalog = DatabaseConstants.DATABASE_CATALOG_NAME
)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeePFNominationDetails implements Serializable, Form2Constants {
	
	private static final long serialVersionUID = -8276285053174555672L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_PF_NOM_ID")
	private long id;
	
	@Column(name = "NAME_ADDRESS", nullable = false, length=100)
	private String nameAndAddress;
	
	@Column(name = "Relationship", nullable = false, length=100)
	private String relationship;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH", nullable = false, length = LENGTH_LIMIT_FOR_DATE_OF_BIRTH)
	private Date dateOfBirth;
	
	@Column(name = "PERCENTAGE", nullable = false, length=15)
	private double percentage;
	
	@Column(name = "GAURDIAN", nullable = false, length=15)
	private String guardian;
	
	@ManyToOne
	@JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
	@XmlElement
    @XmlInverseReference(mappedBy="employeePFNominationDetailsList")
	private Form2 form;
	
	@Transient
	private boolean deleteRecord;
	
	public EmployeePFNominationDetails () {}
	
	public EmployeePFNominationDetails (
			long id,
			String nameAndAddress,
			String relationship,
			Date dateOfBirth,
			double percentage,
			String guardian
	) {
		this.id = id;
		this.nameAndAddress = nameAndAddress;
		this.relationship = relationship;
		this.dateOfBirth = dateOfBirth;
		this.percentage = percentage;
		this.guardian = guardian;
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getNameAndAddress() {
		return nameAndAddress;
	}

	public void setNameAndAddress(String nameAndAddress) {
		this.nameAndAddress = nameAndAddress;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public String getGuardian() {
		return guardian;
	}

	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}
	
	public Form2 getForm() {
		return form;
	}

	public void setForm(Form2 form) {
		this.form = form;
	}
	
	public boolean isDeleteRecord() {
		return deleteRecord;
	}

	public void setDeleteRecord(boolean deleteRecord) {
		this.deleteRecord = deleteRecord;
	}
}
