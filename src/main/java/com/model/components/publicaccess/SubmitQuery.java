package com.model.components.publicaccess;

import java.io.Serializable;

import com.constants.components.AdminConstants;
import com.constants.components.SelectLookupConstants;
import com.constants.components.publicaccess.SubmitQueryConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.ApplicationUtils;
import com.utils.DateUtils;
import com.utils.PrintFormatterUtils;
import com.utils.ValidationUtils;

public class SubmitQuery extends PublicApplication implements Serializable, Cloneable, SubmitQueryConstants, ApplicationWorkbookObject {

	private static final long serialVersionUID = 7314098186505190523L;

	private String querySerialId;
	private String queryStatus;
	private String emailId;
	private String queryDetails;
	private String registeredTutor;
	private String subscribedCustomer;
	private String isContacted;
	private String whoContacted;
	private String queryResponse;
	private String notAnswered;
	private String notAnsweredReason;
	private String whoNotAnswered;
	private Long queryRequestedDateMillis;
	private Long contactedDateMillis;
	private Long recordLastUpdatedMillis;
	private String updatedBy;
	private String whoContactedName;
	private String whoNotAnsweredName;
	private String updatedByName;
	private String isMailSent;
	private Long mailSentMillis;
	
	public SubmitQuery() {}
	
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

	public String getQuerySerialId() {
		return querySerialId;
	}

	public void setQuerySerialId(String querySerialId) {
		this.querySerialId = querySerialId;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public String getWhoContactedName() {
		return whoContactedName;
	}

	public void setWhoContactedName(String whoContactedName) {
		this.whoContactedName = whoContactedName;
	}

	public String getWhoNotAnsweredName() {
		return whoNotAnsweredName;
	}

	public void setWhoNotAnsweredName(String whoNotAnsweredName) {
		this.whoNotAnsweredName = whoNotAnsweredName;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
	
	public String getIsMailSent() {
		return isMailSent;
	}

	public void setIsMailSent(String isMailSent) {
		this.isMailSent = isMailSent;
	}

	public Long getMailSentMillis() {
		return mailSentMillis;
	}

	public void setMailSentMillis(Long mailSentMillis) {
		this.mailSentMillis = mailSentMillis;
	}

	@Override
	public String getFormattedApplicationForPrinting() {
		final StringBuilder submitQueryApplication = new StringBuilder(EMPTY_STRING);
		submitQueryApplication.append(PrintFormatterUtils.startATable());
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUERY_SERIAL_ID, this.querySerialId));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUERY_STATUS, this.queryStatus));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_EMAIL_ID, this.emailId));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_QUERY_DETAILS, this.queryDetails));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_REGISTERED_TUTOR, ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP, this.registeredTutor)));
		submitQueryApplication.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns(COLUMN_NAME_SUBSCRIBED_CUSTOMER, ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP, this.subscribedCustomer)));
		submitQueryApplication.append(PrintFormatterUtils.endATable());
		return submitQueryApplication.toString();
	}

	@Override
	public Object[] getReportHeaders(String reportSwitch) {
		switch (reportSwitch) {
			case AdminConstants.SUPPORT_TEAM_REPORT : {
				return new Object[] {
						COLUMN_NAME_QUERY_SERIAL_ID,
						COLUMN_NAME_QUERY_REQUESTED_DATE,
						COLUMN_NAME_QUERY_STATUS,
						COLUMN_NAME_EMAIL_ID,
						COLUMN_NAME_QUERY_DETAILS,
						COLUMN_NAME_REGISTERED_TUTOR,
						COLUMN_NAME_SUBSCRIBED_CUSTOMER,
						COLUMN_NAME_QUERY_RESPONSE,
						COLUMN_NAME_NOT_ANSWERED,
						COLUMN_NAME_NOT_ANSWERED_REASON,
						COLUMN_NAME_WHO_NOT_ANSWERED,
						COLUMN_NAME_IS_CONTACTED,
						COLUMN_NAME_WHO_CONTACTED,
						COLUMN_NAME_CONTACTED_DATE,
						COLUMN_NAME_RECORD_LAST_UPDATED,
						"UPDATED_BY"
					};
			}
		}
		return new Object[] {};
	}

	@Override
	public Object[] getReportRecords(String reportSwitch) {
		switch (reportSwitch) {
			case AdminConstants.SUPPORT_TEAM_REPORT : {
				return new Object[] {
						this.querySerialId,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.queryRequestedDateMillis),
						this.queryStatus,
						this.emailId,
						this.queryDetails,
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP, this.registeredTutor),
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP, this.subscribedCustomer),
						this.queryResponse,
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP, this.notAnswered),
						this.notAnsweredReason,
						this.whoNotAnsweredName,
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP, this.isContacted),
						this.whoContactedName,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.contactedDateMillis),
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.recordLastUpdatedMillis),
						this.updatedByName
				};
			}
		}
		return new Object[] {};
	}
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "querySerialId" : return "QUERY_SERIAL_ID";
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
			case "whoContactedName" : return "WHO_CONTACTED_NAME";
			case "whoNotAnsweredName" : return "WHO_NOT_ANSWERED_NAME";
			case "updatedByName" : return "UPDATED_BY_NAME";
			case "isMailSent" : return "IS_MAIL_SENT";
			case "mailSentMillis" : return "MAIL_SENT_MILLIS";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public SubmitQuery clone() throws CloneNotSupportedException {  
		return (SubmitQuery)super.clone();
	}
}
