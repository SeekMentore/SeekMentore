package com.model.components.publicaccess;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.constants.DatabaseConstants;
import com.constants.components.publicaccess.SubmitQueryConstants;
import com.utils.PrintFormatterUtils;
import com.utils.ValidationUtils;

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
	private Long queryId;
	
	@Column(name = COLUMN_NAME_QUERY_STATUS, unique = true, nullable = false)
	private String queryStatus;
	
	@Column(name = COLUMN_NAME_EMAIL_ID, unique = true, nullable = false)
	private String emailId;
	
	@Column(name = COLUMN_NAME_QUERY_DETAILS, nullable = false)
	private String queryDetails;
	
	@Column(name = COLUMN_NAME_REGISTERED_TUTOR, nullable = false)
	private String registeredTutor;
	
	@Column(name = COLUMN_NAME_SUBSCRIBED_CUSTOMER, nullable = false)
	private String subscribedCustomer;
	
	@Column(name = COLUMN_NAME_IS_CONTACTED, nullable = false)
	private String isContacted;
	
	@Column(name = COLUMN_NAME_WHO_CONTACTED)
	private String whoContacted;
	
	@Column(name = COLUMN_NAME_QUERY_RESPONSE)
	private String queryResponse;
	
	@Column(name = COLUMN_NAME_NOT_ANSWERED)
	private String notAnswered;
	
	@Column(name = COLUMN_NAME_NOT_ANSWERED_REASON)
	private String notAnsweredReason;
	
	@Column(name = COLUMN_NAME_WHO_NOT_ANSWERED)
	private String whoNotAnswered;
	
	private Long queryRequestedDateMillis;
	private Long contactedDateMillis;
	private Long recordLastUpdatedMillis;
	private String updatedBy;
	
	public SubmitQuery() {}
	
	public SubmitQuery(Long queryId) {
		this.queryId = queryId;
		this.queryRequestedDateMillis = new Date().getTime();
		this.queryStatus = "FRESH";
		this.emailId = "abc@hg.com";
		this.registeredTutor = "Y";
		this.subscribedCustomer = "N";
		this.queryDetails = "Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test";
		this.isContacted = "N";
		this.whoContacted = "abc";
		this.contactedDateMillis = new Date().getTime();
		this.queryResponse = "Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test";
		this.notAnswered = "Y";
		this.notAnsweredReason = "Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test";
		this.whoNotAnswered = "abc";
		this.recordLastUpdatedMillis = new Date().getTime();
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

	public Long getQueryId() {
		return queryId;
	}

	public void setQueryId(Long queryId) {
		this.queryId = queryId;
	}

	public String getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}

	public String getQueryDetails() {
		return queryDetails;
	}

	public void setQueryDetails(String queryDetails) {
		this.queryDetails = queryDetails;
	}

	public String getQueryResponse() {
		return queryResponse;
	}

	public void setQueryResponse(String queryResponse) {
		this.queryResponse = queryResponse;
	}

	public String getNotAnswered() {
		return notAnswered;
	}

	public void setNotAnswered(String notAnswered) {
		this.notAnswered = notAnswered;
	}

	public String getNotAnsweredReason() {
		return notAnsweredReason;
	}

	public void setNotAnsweredReason(String notAnsweredReason) {
		this.notAnsweredReason = notAnsweredReason;
	}

	public String getWhoNotAnswered() {
		return whoNotAnswered;
	}

	public void setWhoNotAnswered(String whoNotAnswered) {
		this.whoNotAnswered = whoNotAnswered;
	}
	
	public String getRegisteredTutor() {
		return registeredTutor;
	}

	public void setRegisteredTutor(String registeredTutor) {
		this.registeredTutor = registeredTutor;
	}

	public String getSubscribedCustomer() {
		return subscribedCustomer;
	}

	public void setSubscribedCustomer(String subscribedCustomer) {
		this.subscribedCustomer = subscribedCustomer;
	}
	
	public Long getQueryRequestedDateMillis() {
		return queryRequestedDateMillis;
	}

	public void setQueryRequestedDateMillis(Long queryRequestedDateMillis) {
		this.queryRequestedDateMillis = queryRequestedDateMillis;
	}

	public Long getContactedDateMillis() {
		return contactedDateMillis;
	}

	public void setContactedDateMillis(Long contactedDateMillis) {
		this.contactedDateMillis = contactedDateMillis;
	}

	public Long getRecordLastUpdatedMillis() {
		return recordLastUpdatedMillis;
	}

	public void setRecordLastUpdatedMillis(Long recordLastUpdatedMillis) {
		this.recordLastUpdatedMillis = recordLastUpdatedMillis;
	}

	@Override
	public String toString() {
		final StringBuilder submitQueryApplication = new StringBuilder(EMPTY_STRING);
		submitQueryApplication.append(PrintFormatterUtils.startATable());
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUERY_ID, queryId));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUERY_REQUESTED_DATE, new Date(queryRequestedDateMillis)));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUERY_STATUS, queryStatus));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_EMAIL_ID, emailId));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUERY_DETAILS, queryDetails));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REGISTERED_TUTOR, registeredTutor));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUBSCRIBED_CUSTOMER, subscribedCustomer));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_IS_CONTACTED, isContacted));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_CONTACTED, whoContacted));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_CONTACTED_DATE, new Date(contactedDateMillis)));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUERY_RESPONSE, queryResponse));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_NOT_ANSWERED, notAnswered));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_NOT_ANSWERED_REASON, notAnsweredReason));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_WHO_NOT_ANSWERED, whoNotAnswered));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_RECORD_LAST_UPDATED, new Date(recordLastUpdatedMillis)));
		submitQueryApplication.append(PrintFormatterUtils.endATable());
		return submitQueryApplication.toString();
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "queryId" : return "QUERY_ID";
			case "queryRequestedDateMillis" : return "QUERY_REQUESTED_DATE_MILLIS";
			case "queryStatus" : return "QUERY_STATUS";
			case "registeredTutor" : return "REGISTERED_TUTOR";
			case "subscribedCustomer" : return "SUBSCRIBED_CUSTOMER";
			case "queryDetails" : return "QUERY_DETAILS";
			case "queryResponse" : return "QUERY_RESPONSE";
			case "notAnswered" : return "NOT_ANSWERED";
			case "notAnsweredReason" : return "NOT_ANSWERED_REASON";
			case "whoNotAnswered" : return "WHO_NOT_ANSWERED";
			case "emailId" : return "EMAIL_ID";
			case "isContacted" : return "IS_CONTACTED";
			case "whoContacted" : return "WHO_CONTACTED";
			case "contactedDateMillis" : return "CONTACTED_DATE_MILLIS";
			case "recordLastUpdatedMillis" : return "RECORD_LAST_UPDATED_MILLIS";
			case "updatedBy" : return "UPDATED_BY";
		}
		return EMPTY_STRING;
	}
}
