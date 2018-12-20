package com.model.mail;

import java.io.Serializable;

import com.constants.MailConstants;
import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class EmailTemplate extends GridComponentObject implements Serializable, MailConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -1763649873039566289L;
	
	private Long emailTemplateId;
	private String emailTemplateLookupValue;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String body;
	private String addedBy;
	private String addedByName;
	private Long addedMillis;
	private String lastUpdatedBy;
	private String lastUpdatedByName;
	private Long lastUpdatedMillis;
	
	public EmailTemplate() {}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public Long getEmailTemplateId() {
		return emailTemplateId;
	}

	public void setEmailTemplateId(Long emailTemplateId) {
		this.emailTemplateId = emailTemplateId;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Long getAddedMillis() {
		return addedMillis;
	}

	public void setAddedMillis(Long addedMillis) {
		this.addedMillis = addedMillis;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Long getLastUpdatedMillis() {
		return lastUpdatedMillis;
	}

	public void setLastUpdatedMillis(Long lastUpdatedMillis) {
		this.lastUpdatedMillis = lastUpdatedMillis;
	}

	public String getEmailTemplateLookupValue() {
		return emailTemplateLookupValue;
	}

	public void setEmailTemplateLookupValue(String emailTemplateLookupValue) {
		this.emailTemplateLookupValue = emailTemplateLookupValue;
	}

	public String getAddedByName() {
		return addedByName;
	}

	public void setAddedByName(String addedByName) {
		this.addedByName = addedByName;
	}

	public String getLastUpdatedByName() {
		return lastUpdatedByName;
	}

	public void setLastUpdatedByName(String lastUpdatedByName) {
		this.lastUpdatedByName = lastUpdatedByName;
	}

	public static EmailTemplate getBlankEmailTemplate() {
		final EmailTemplate emailTemplate = new EmailTemplate();
		emailTemplate.to = EMPTY_STRING;
		emailTemplate.cc = EMPTY_STRING;
		emailTemplate.bcc = EMPTY_STRING;
		emailTemplate.subject = EMPTY_STRING;
		emailTemplate.body = EMPTY_STRING;
		return emailTemplate;
	}

	@Override
	public Object[] getReportHeaders(String reportSwitch) {
		return null;
	}

	@Override
	public Object[] getReportRecords(String reportSwitch) {
		return null;
	}
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "emailTemplateId" : return "EMAIL_TEMPLATE_ID";
			case "emailTemplateLookupValue" : return "EMAIL_TEMPLATE_LOOKUP_VALUE";
			case "to" : return "TO_ADDRESS";
			case "cc" : return "CC_ADDRESS";
			case "bcc" : return "BCC_ADDRESS";
			case "subject" : return "SUBJECT_CONTENT";
			case "body" : return "MESSAGE_CONTENT";
			case "addedBy" : return "ADDED_BY";
			case "addedByName" : return "ADDED_BY_NAME";
			case "addedMillis" : return "ADDED_MILLIS";
			case "lastUpdatedBy" : return "LAST_UPDATED_BY";
			case "lastUpdatedByName" : return "LAST_UPDATED_BY_NAME";
			case "lastUpdatedMillis" : return "LAST_UPDATED_MILLIS";
		}
		return EMPTY_STRING;
	}
}
