package com.model.control;

import java.io.Serializable;

public class MailingDuringDevelopmentAndTestingFeatures implements Serializable {
	
	private static final long serialVersionUID = 6693618464803123134L;
	
	private boolean sendOutActualEmails;
	private boolean showOnConsoleWhatEmailWillBeSent;
	private boolean sendOutActualEmailsButDivertThemToSomeOtherRecipient;
	private String divertedRecipeintEmailId;
	
	public boolean isSendOutActualEmails() {
		return sendOutActualEmails;
	}
	
	public void setSendOutActualEmails(boolean sendOutActualEmails) {
		this.sendOutActualEmails = sendOutActualEmails;
	}
	
	public boolean isShowOnConsoleWhatEmailWillBeSent() {
		return showOnConsoleWhatEmailWillBeSent;
	}
	
	public void setShowOnConsoleWhatEmailWillBeSent(boolean showOnConsoleWhatEmailWillBeSent) {
		this.showOnConsoleWhatEmailWillBeSent = showOnConsoleWhatEmailWillBeSent;
	}
	
	public String getDivertedRecipeintEmailId() {
		return divertedRecipeintEmailId;
	}
	
	public void setDivertedRecipeintEmailId(String divertedRecipeintEmailId) {
		this.divertedRecipeintEmailId = divertedRecipeintEmailId;
	}

	public boolean isSendOutActualEmailsButDivertThemToSomeOtherRecipient() {
		return sendOutActualEmailsButDivertThemToSomeOtherRecipient;
	}

	public void setSendOutActualEmailsButDivertThemToSomeOtherRecipient(
			boolean sendOutActualEmailsButDivertThemToSomeOtherRecipient) {
		this.sendOutActualEmailsButDivertThemToSomeOtherRecipient = sendOutActualEmailsButDivertThemToSomeOtherRecipient;
	}
	
}
