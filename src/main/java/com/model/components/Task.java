package com.model.components;

import java.io.Serializable;

import com.model.ApplicationWorkbookObject;
import com.model.Notification;
import com.utils.ValidationUtils;

public class Task extends Notification implements Serializable, Cloneable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -2375203428701697320L;
	
	private String taskSerialId;
	
	public Task() {}

	public String getTaskId() {
		return taskSerialId;
	}

	public void setTaskSerialId(String taskSerialId) {
		this.taskSerialId = taskSerialId;
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
			case "taskSerialId" : return "TASK_SERIAL_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public Task clone() throws CloneNotSupportedException {  
		return (Task)super.clone();
	}
}
