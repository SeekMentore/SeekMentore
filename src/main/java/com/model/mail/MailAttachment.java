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
	
	private String attachmentSerialId; 
	private String mailSerialId;
	private String filename;
	private byte[] content;
	private String applicationType;
	private String isFSStored;
	private String fsKey;
	private ByteArrayDataSource datasource;
	private ByteArrayInputStream inputStream;
	private DataHandler dataHandler;
	private MimeBodyPart attachment;
	
	public MailAttachment() {}
	
	public MailAttachment(final String filename, final byte[] content, final String applicationType) {
		this.filename = filename;
		this.content = content;
		this.applicationType = applicationType;
	}
	
	public MailAttachment(final String filename, final String fsKey, final String applicationType) {
		this.filename = filename;
		this.fsKey = fsKey;
		this.isFSStored = YES;
		this.applicationType = applicationType;
	}
	
	public void createMimeAttachment() throws IOException, MessagingException {
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

	public String getAttachmentSerialId() {
		return attachmentSerialId;
	}

	public void setAttachmentSerialId(String attachmentSerialId) {
		this.attachmentSerialId = attachmentSerialId;
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

	public String getMailSerialId() {
		return mailSerialId;
	}

	public void setMailSerialId(String mailSerialId) {
		this.mailSerialId = mailSerialId;
	}

	public Boolean getIsFileStoredInFileSystem() {
		return ValidationUtils.checkStringAvailability(this.isFSStored) && YES.equals(this.isFSStored);
	}

	public String getIsFSStored() {
		return isFSStored;
	}

	public void setIsFSStored(String isFSStored) {
		this.isFSStored = isFSStored;
	}

	public String getFsKey() {
		return fsKey;
	}

	public void setFsKey(String fsKey) {
		this.fsKey = fsKey;
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
			case "attachmentSerialId" : return "ATTACHMENT_SERIAL_ID";
			case "mailSerialId" : return "MAIL_SERIAL_ID";
			case "content" : return "CONTENT";
			case "filename" : return "FILENAME";
			case "applicationType" : return "APPLICATION_TYPE";
			case "isFSStored" : return "IS_FS_STORED";
			case "fsKey" : return "FS_KEY";
		}
		return EMPTY_STRING;
	}

	@Override
	public MailAttachment clone() throws CloneNotSupportedException {  
		return (MailAttachment)super.clone();
	}
}
