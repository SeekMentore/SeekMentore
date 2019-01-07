package com.model.components;

import java.io.Serializable;

import com.model.ApplicationWorkbookObject;
import com.model.Notification;
import com.utils.ValidationUtils;

public class AlertReminder extends Notification implements Serializable, Cloneable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -2375203428701697320L;
	
	private Long alertReminderId;
	
	public AlertReminder() {}
	
	public Long getAlertReminderId() {
		return alertReminderId;
	}

	public void setAlertReminderId(Long alertReminderId) {
		this.alertReminderId = alertReminderId;
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
			case "alertReminderId" : return "ALERT_REMINDER_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public AlertReminder clone() throws CloneNotSupportedException {  
		return (AlertReminder)super.clone();
	}
}
