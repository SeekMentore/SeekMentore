package com.model.control;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class MailingDuringDevelopmentAndTestingFeatures implements Serializable {
	
	private static final long serialVersionUID = 6693618464803123134L;
	
	private boolean sendOutActualEmails;
	private boolean showOnConsoleWhatEmailWillBeSent;
	private boolean sendOutEmailsButDivertThemToSomeOtherRecipient;
	private String divertedRecipeintEmailId;
	
	public boolean isSendOutActualEmails() {
		return sendOutActualEmails;
	}
	
	@XmlElement
	public void setSendOutActualEmails(boolean sendOutActualEmails) {
		this.sendOutActualEmails = sendOutActualEmails;
	}
	
	public boolean isShowOnConsoleWhatEmailWillBeSent() {
		return showOnConsoleWhatEmailWillBeSent;
	}
	
	@XmlElement
	public void setShowOnConsoleWhatEmailWillBeSent(boolean showOnConsoleWhatEmailWillBeSent) {
		this.showOnConsoleWhatEmailWillBeSent = showOnConsoleWhatEmailWillBeSent;
	}
	
	public boolean isSendOutEmailsButDivertThemToSomeOtherRecipient() {
		return sendOutEmailsButDivertThemToSomeOtherRecipient;
	}
	
	@XmlElement
	public void setSendOutEmailsButDivertThemToSomeOtherRecipient(boolean sendOutEmailsButDivertThemToSomeOtherRecipient) {
		this.sendOutEmailsButDivertThemToSomeOtherRecipient = sendOutEmailsButDivertThemToSomeOtherRecipient;
	}
	
	public String getDivertedRecipeintEmailId() {
		return divertedRecipeintEmailId;
	}
	
	@XmlElement
	public void setDivertedRecipeintEmailId(String divertedRecipeintEmailId) {
		this.divertedRecipeintEmailId = divertedRecipeintEmailId;
	}
	
}
