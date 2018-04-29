package com.model.mail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

public class MailAttachment implements Serializable {

	private static final long serialVersionUID = -6980182144471502902L;
	
	private long attachmentId; 
	private long mailId;
	
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

	public long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(long attachmentId) {
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

	public long getMailId() {
		return mailId;
	}

	public void setMailId(long mailId) {
		this.mailId = mailId;
	}
}
