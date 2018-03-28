package com.model.components.formF;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import com.constants.DatabaseConstants;
import com.constants.components.FormFConstants;

@Entity
@Table( name = "FORMF_EMP_GRTY_NOM_DTLS", 
		catalog = DatabaseConstants.DATABASE_CATALOG_NAME
)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeGratuityNomineeDetails implements Serializable, FormFConstants {
	
	private static final long serialVersionUID = 4720495004554545356L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_GRTY_NOM_ID", unique = true, nullable = false)
	private long id;
	
	@Column(name = "NAME_AND_ADDRESS", nullable = false)
	private String nameAndAddress;
	
	@Column(name = "RELATIONSHIP", nullable = false)
	private String relationship;
	
	@Column(name = "AGE", nullable = false)
	private int age;
	
	@Column(name = "PERCENTAGE", nullable = false)
	private int percentage;
	
	@ManyToOne
	@JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
	@XmlElement
    @XmlInverseReference(mappedBy="employeeGratuityNomineeDetailsList")
	private FormF form;
	
	@Transient
	private boolean deleteRecord;
	
	public EmployeeGratuityNomineeDetails() {}
	
	public EmployeeGratuityNomineeDetails(
			String nameAndAddress,
			String relationship,
			int age,
			int percentage,
			FormF form
	) {
		this.nameAndAddress = nameAndAddress;
		this.relationship = relationship;
		this.age = age;
		this.percentage = percentage;
		this.form = form;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
	public FormF getForm() {
		return form;
	}

	public void setForm(FormF form) {
		this.form = form;
	}
	
	public boolean isDeleteRecord() {
		return deleteRecord;
	}

	public void setDeleteRecord(boolean deleteRecord) {
		this.deleteRecord = deleteRecord;
	}
}
