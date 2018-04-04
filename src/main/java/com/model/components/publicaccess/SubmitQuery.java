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
import com.constants.components.publicaccess.SubmitQueryConstants;
import com.utils.PrintFormatterUtils;

@Entity
@Table( name = SubmitQueryConstants.TABLE_NAME, 
		catalog = DatabaseConstants.DATABASE_CATALOG_NAME
)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SubmitQuery extends PublicApplication implements Serializable, SubmitQueryConstants {

	private static final long serialVersionUID = 7314098186505190523L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = COLUMN_NAME_QUERY_ID, unique = true, nullable = false)
	private long queryId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = COLUMN_NAME_QUERY_REQUESTED_DATE, length = 10, nullable = false)
	private Date queryRequestedDate;
	
	@Column(name = COLUMN_NAME_QUERY_STATUS, unique = true, nullable = false)
	private String queryStatus;
	
	@Column(name = COLUMN_NAME_EMAIL_ID, unique = true, nullable = false)
	private String emailId;
	
	@Column(name = COLUMN_NAME_QUERY_DETAILS, nullable = false)
	private String queryDetails;
	
	@Column(name = COLUMN_NAME_IS_CONTACTED, nullable = false)
	private String isContacted;
	
	@Column(name = COLUMN_NAME_WHO_CONTACTED)
	private String whoContacted;
	
	@Temporal(TemporalType.DATE)
	@Column(name = COLUMN_NAME_CONTACTED_DATE, length = 10)
	private Date contactedDate;
	
	@Column(name = COLUMN_NAME_QUERY_RESPONSE)
	private String queryResponse;
	
	@Column(name = COLUMN_NAME_NOT_ANSWERED)
	private String notAnswered;
	
	@Column(name = COLUMN_NAME_NOT_ANSWERED_REASON)
	private String notAnsweredReason;
	
	@Column(name = COLUMN_NAME_WHO_NOT_ANSWERED)
	private String whoNotAnswered;
	
	public SubmitQuery() {}
	
	public SubmitQuery(
			Date queryRequestedDate,
			String queryStatus,
			String emailId,
			String queryDetails,
			String isContacted,
			String whoContacted,
			Date contactedDate,
			String queryResponse,
			String notAnswered,
			String notAnsweredReason,
			String whoNotAnswered
	) {
		this.queryRequestedDate = queryRequestedDate;
		this.queryStatus = queryStatus;
		this.emailId = emailId;
		this.queryDetails = queryDetails;
		this.isContacted = isContacted;
		this.whoContacted = whoContacted;
		this.contactedDate = contactedDate;
		this.queryResponse = queryResponse;
		this.notAnswered = notAnswered;
		this.notAnsweredReason = notAnsweredReason;
		this.whoNotAnswered = whoNotAnswered;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getIsContacted() {
		return isContacted;
	}

	public void setIsContacted(String isContacted) {
		this.isContacted = isContacted;
	}

	public String getWhoContacted() {
		return whoContacted;
	}

	public void setWhoContacted(String whoContacted) {
		this.whoContacted = whoContacted;
	}

	public Date getContactedDate() {
		return contactedDate;
	}

	public void setContactedDate(Date contactedDate) {
		this.contactedDate = contactedDate;
	}

	@Override
	public String toString() {
		final StringBuilder submitQueryApplication = new StringBuilder(EMPTY_STRING);
		submitQueryApplication.append(PrintFormatterUtils.startATable());
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUERY_ID, queryId));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUERY_REQUESTED_DATE, queryRequestedDate));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUERY_STATUS, queryStatus));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_EMAIL_ID, emailId));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUERY_DETAILS, queryDetails));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_CONTACTED, isContacted));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_CONTACTED, whoContacted));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACTED_DATE, contactedDate));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUERY_RESPONSE, queryResponse));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_NOT_ANSWERED, notAnswered));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_NOT_ANSWERED_REASON, notAnsweredReason));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_NOT_ANSWERED, whoNotAnswered));
		submitQueryApplication.append(PrintFormatterUtils.endATable());
		return submitQueryApplication.toString();
	}
}
