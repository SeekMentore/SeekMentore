package com.model.mail;

import java.io.Serializable;
import java.util.List;

import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class ApplicationMail extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {

	private static final long serialVersionUID = -8603850515164057242L;
	
	private Long mailId;
	private String mailType;
	private Long entryDateMillis;
	private String fromAddress;
	private String toAddress;
	private String ccAddress;
	private String bccAddress;
	private String subjectContent;
	private String messageContent;
	private String mailSent;
	private Long sendDateMillis;
	private String errorOccuredWhileSending;
	private Long errorDateMillis;
	private String errorTrace;
	private List<MailAttachment> attachments;
	
	public ApplicationMail() {}
	
	public ApplicationMail(
		String mailType,
		Long entryDateMillis,
		String fromAddress,
		String toAddress,
		String ccAddress,
		String bccAddress,
		String subjectContent,
		String messageContent,
		List<MailAttachment> attachments
	) {
		this.mailType = mailType;
		this.entryDateMillis = entryDateMillis;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.ccAddress = ccAddress;
		this.bccAddress = bccAddress;
		this.subjectContent = subjectContent;
		this.messageContent = messageContent;
		this.attachments = attachments;
	}

	public Long getMailId() {
		return mailId;
	}

	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getCcAddress() {
		return ccAddress;
	}

	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}

	public String getBccAddress() {
		return bccAddress;
	}

	public void setBccAddress(String bccAddress) {
		this.bccAddress = bccAddress;
	}

	public String getSubjectContent() {
		return subjectContent;
	}

	public void setSubjectContent(String subjectContent) {
		this.subjectContent = subjectContent;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public List<MailAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<MailAttachment> attachments) {
		this.attachments = attachments;
	}
	
	public String getMailType() {
		return mailType;
	}

	public void setMailType(String mailType) {
		this.mailType = mailType;
	}

	public String getMailSent() {
		return mailSent;
	}

	public void setMailSent(String mailSent) {
		this.mailSent = mailSent;
	}
	
	public String getErrorOccuredWhileSending() {
		return errorOccuredWhileSending;
	}

	public void setErrorOccuredWhileSending(String errorOccuredWhileSending) {
		this.errorOccuredWhileSending = errorOccuredWhileSending;
	}

	public String getErrorTrace() {
		return errorTrace;
	}

	public void setErrorTrace(String errorTrace) {
		this.errorTrace = errorTrace;
	}

	public Long getEntryDateMillis() {
		return entryDateMillis;
	}

	public void setEntryDateMillis(Long entryDateMillis) {
		this.entryDateMillis = entryDateMillis;
	}

	public Long getSendDateMillis() {
		return sendDateMillis;
	}

	public void setSendDateMillis(Long sendDateMillis) {
		this.sendDateMillis = sendDateMillis;
	}

	public Long getErrorDateMillis() {
		return errorDateMillis;
	}

	public void setErrorDateMillis(Long errorDateMillis) {
		this.errorDateMillis = errorDateMillis;
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
			case "mailId" : return "MAIL_ID";
			case "mailType" : return "MAIL_TYPE";
			case "entryDateMillis" : return "ENTRY_DATE_MILLIS";
			case "fromAddress" : return "FROM_ADDRESS";
			case "toAddress" : return "TO_ADDRESS";
			case "ccAddress" : return "CC_ADDRESS";
			case "bccAddress" : return "BCC_ADDRESS";
			case "subjectContent" : return "SUBJECT_CONTENT";
			case "messageContent" : return "MESSAGE_CONTENT";
			case "mailSent" : return "MAIL_SENT";
			case "sendDateMillis" : return "SEND_DATE_MILLIS";
			case "errorOccuredWhileSending" : return "ERROR_OCCURED_WHILE_SENDING";
			case "errorDateMillis" : return "ERROR_DATE_MILLIS";
			case "errorTrace" : return "ERROR_TRACE";
		}
		return EMPTY_STRING;
	}

	@Override
	public ApplicationMail clone() throws CloneNotSupportedException {  
		return (ApplicationMail)super.clone();
	}
}
