package com.model.components;

import java.io.Serializable;

import com.model.ApplicationWorkbookObject;
import com.model.Notification;
import com.utils.ValidationUtils;

public class AlertReminder extends Notification implements Serializable, Cloneable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -2375203428701697320L;
	
	private String alertReminderSerialId;
	
	public AlertReminder() {}
	
	public String getAlertReminderSerialId() {
		return alertReminderSerialId;
	}

	public void setAlertReminderSerialId(String alertReminderSerialId) {
		this.alertReminderSerialId = alertReminderSerialId;
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
			case "alertReminderSerialId" : return "ALERT_REMINDER_SERIAL_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public AlertReminder clone() throws CloneNotSupportedException {  
		return (AlertReminder)super.clone();
	}
}
