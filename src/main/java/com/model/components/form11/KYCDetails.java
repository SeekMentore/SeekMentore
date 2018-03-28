package com.model.components.form11;

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
import com.constants.components.Form11Constants;

@Entity
@Table(name = "FORM11_KYC_DTLS", catalog = DatabaseConstants.DATABASE_CATALOG_NAME)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class KYCDetails implements Serializable, Form11Constants {
	
	private static final long serialVersionUID = -7883553723675063485L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "KYC_DTLS_ID", unique = true, nullable = false)
	private long id;
	
	@Column(name = "DOCUMENT_TYPE", nullable = false)
	private String documentType;
	
	@Column(name = "REMARKS", nullable = false)
	private String remarks;
	
	@Column(name = "DOCUMENT_NAME", nullable = false)
	private String documentName;
	
	@Column(name = "DOCUMENT_NUMBER", nullable = false)
	private String documentNumber;
	
	@ManyToOne
	@JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
	@XmlElement
    @XmlInverseReference(mappedBy="kycDetailsList")
	private Form11 form;
	
	@Transient
	private boolean deleteRecord;
	
	public KYCDetails() {}
	
	public KYCDetails(
			String documentType,
			String documentName,
			String documentNumber,
			String remarks,
			Form11 form
	) {
		this.documentType = documentType;
		this.documentName = documentName;
		this.documentNumber = documentNumber;
		this.remarks = remarks;
		this.form = form;
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Form11 getForm() {
		return form;
	}

	public void setForm(Form11 form) {
		this.form = form;
	}

	public boolean isDeleteRecord() {
		return deleteRecord;
	}

	public void setDeleteRecord(boolean deleteRecord) {
		this.deleteRecord = deleteRecord;
	}
}