package com.model.mail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class MailAttachment extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {

	private static final long serialVersionUID = -6980182144471502902L;
	
	private Long attachmentId; 
	private Long mailId;
	private String filename;
	private byte[] content;
	private String applicationType;
	private ByteArrayDataSource datasource;
	private ByteArrayInputStream inputStream;
	private DataHandler dataHandler;
	private MimeBodyPart attachment;
	
	public MailAttachment() {}
	
	public MailAttachment(String filename, byte[] content, String applicationType) throws IOException, MessagingException {
		this.filename = filename;
		this.content = content;
		this.applicationType = applicationType;
		this.inputStream = new ByteArrayInputStream(this.content);
		this.datasource = new ByteArrayDataSource(this.inputStream, this.applicationType); 
		this.dataHandler = new DataHandler(this.datasource);
		prepareMimeBodyPart();
	}
	
	private void prepareMimeBodyPart() throws MessagingException {
		this.attachment = new MimeBodyPart();
		attachment.setDataHandler(this.dataHandler);
	    attachment.setFileName(this.filename);
	}

	public MimeBodyPart getAttachment() {
		return attachment;
	}

	public String getFilename() {
		return filename;
	}

	public byte[] getContent() {
		return content;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public ByteArrayDataSource getDatasource() {
		return datasource;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public DataHandler getDataHandler() {
		return dataHandler;
	}

	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public void setAttachment(MimeBodyPart attachment) {
		this.attachment = attachment;
	}

	public Long getMailId() {
		return mailId;
	}

	public void setMailId(Long mailId) {
		this.mailId = mailId;
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
			case "attachmentId" : return "ATTACHMENT_ID";
			case "mailId" : return "MAIL_ID";
			case "content" : return "CONTENT";
			case "filename" : return "FILENAME";
			case "applicationType" : return "APPLICATION_TYPE";
		}
		return EMPTY_STRING;
	}

	@Override
	public MailAttachment clone() throws CloneNotSupportedException {  
		return (MailAttachment)super.clone();
	}
}
