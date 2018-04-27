package com.model.mail;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class ApplicationMail implements Serializable {

	private static final long serialVersionUID = -8603850515164057242L;
	
	private long mailId;
	private String mailType;
	private Timestamp entryDate;
	private String fromAddress;
	private String toAddress;
	private String ccAddress;
	private String bccAddress;
	private String subjectContent;
	private String messageContent;
	private String mailSent;
	private Timestamp sendDate;
	private String errorOccuredWhileSending;
	private Timestamp errorDate;
	private String errorTrace;
	private List<MailAttachment> attachments;
	
	public ApplicationMail() {}
	
	public ApplicationMail(
		String mailType,
		Timestamp entryDate,
		String fromAddress,
		String toAddress,
		String ccAddress,
		String bccAddress,
		String subjectContent,
		String messageContent,
		List<MailAttachment> attachments
	) {
		this.mailType = mailType;
		this.entryDate = entryDate;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.ccAddress = ccAddress;
		this.bccAddress = bccAddress;
		this.subjectContent = subjectContent;
		this.messageContent = messageContent;
		this.attachments = attachments;
	}

	public long getMailId() {
		return mailId;
	}

	public void setMailId(long mailId) {
		this.mailId = mailId;
	}

	public Timestamp getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Timestamp entryDate) {
		this.entryDate = entryDate;
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

	public Timestamp getSendDate() {
		return sendDate;
	}

	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
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

	public Timestamp getErrorDate() {
		return errorDate;
	}

	public void setErrorDate(Timestamp errorDate) {
		this.errorDate = errorDate;
	}

	public String getErrorTrace() {
		return errorTrace;
	}

	public void setErrorTrace(String errorTrace) {
		this.errorTrace = errorTrace;
	}

}
