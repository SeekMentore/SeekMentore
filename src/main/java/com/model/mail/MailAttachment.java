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
	
	private String filename;
	
	private byte[] content;
	
	private String applicationType;
	
	private ByteArrayDataSource datasource;
	
	private ByteArrayInputStream inputStream;
	
	private DataHandler dataHandler;
	
	private MimeBodyPart attachment;
	
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
}
