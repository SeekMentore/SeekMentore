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
@Table(	name = "FORM2_WDW_CHLDRN_NOM_DTLS", 
		catalog = DatabaseConstants.DATABASE_CATALOG_NAME
)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WidowChildrenPensionNominee implements Serializable, Form2Constants {
	
	private static final long serialVersionUID = -7432831969166977031L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WDW_CHLDRN_NOM_ID")
	private long id;
	
	@Column(name = "NAME", nullable = false, length=100)
	private String name;
	
	@Column(name = "ADDRESS", nullable = false, length=500)
	private String address;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH", nullable = false, length = LENGTH_LIMIT_FOR_DATE_OF_BIRTH)
	private Date dateOfBirth;
	
	@Column(name = "RELATIONSHIP", nullable = false, length=100)
	private String relationship;
	
	@ManyToOne
	@JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
	@XmlElement
    @XmlInverseReference(mappedBy="widowChildrenPensionNomineeList")
	private Form2 form;
	
	@Transient
	private boolean deleteRecord;
	
	public WidowChildrenPensionNominee () {}
	
	public WidowChildrenPensionNominee (
			long id,
			String name,
			String address,
			Date dateOfBirth,
			String relationship
	) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.relationship = relationship;
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
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
