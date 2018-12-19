package com.model;

import com.utils.ValidationUtils;

public abstract class Notification extends GridComponentObject {
	
	private Long initiatedDateMillis;
	private Long actionDateMillis;
	private Long dueDateMillis;
	private String subject;
	private String initiatedBy;
	private String actionBy;
	private String recipientUserId;
	private String initiatedByName;
	private String actionByName;
	private String recipientUserName;
	
	public Long getInitiatedDateMillis() {
		return initiatedDateMillis;
	}

	public void setInitiatedDateMillis(Long initiatedDateMillis) {
		this.initiatedDateMillis = initiatedDateMillis;
	}

	public Long getActionDateMillis() {
		return actionDateMillis;
	}

	public void setActionDateMillis(Long actionDateMillis) {
		this.actionDateMillis = actionDateMillis;
	}

	public String getInitiatedBy() {
		return initiatedBy;
	}

	public void setInitiatedBy(String initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	public String getActionBy() {
		return actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}

	public Long getDueDateMillis() {
		return dueDateMillis;
	}

	public void setDueDateMillis(Long dueDateMillis) {
		this.dueDateMillis = dueDateMillis;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "initiatedDateMillis" : return "INITIATED_DATE_MILLIS";
			case "actionDateMillis" : return "ACTION_DATE_MILLIS";
			case "dueDateMillis" : return "DUE_DATE_MILLIS";
			case "subject" : return "SUBJECT";
			case "initiatedBy" : return "INITIATED_BY";
			case "actionBy" : return "ACTION_BY";
			case "recipientUserId" : return "RECIPIENT_USER_ID";
			case "initiatedByName" : return "INITIATED_BY_NAME";
			case "actionByName" : return "ACTION_BY_NAME";
			case "recipientUserName" : return "RECIPIENT_USER_NAME";
		}
		return EMPTY_STRING;
	}

	public String getRecipientUserId() {
		return recipientUserId;
	}

	public void setRecipientUserId(String recipientUserId) {
		this.recipientUserId = recipientUserId;
	}

	public String getInitiatedByName() {
		return initiatedByName;
	}

	public void setInitiatedByName(String initiatedByName) {
		this.initiatedByName = initiatedByName;
	}

	public String getActionByName() {
		return actionByName;
	}

	public void setActionByName(String actionByName) {
		this.actionByName = actionByName;
	}

	public String getRecipientUserName() {
		return recipientUserName;
	}

	public void setRecipientUserName(String recipientUserName) {
		this.recipientUserName = recipientUserName;
	}
}
