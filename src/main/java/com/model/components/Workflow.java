package com.model.components;

import java.io.Serializable;

import com.model.ApplicationWorkbookObject;
import com.model.Notification;
import com.utils.ValidationUtils;

public class Workflow extends Notification implements Serializable, Cloneable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -2375203428701697320L;
	
	private String workflowSerialId;
	
	public Workflow() {}

	public String getWorkflowSerialId() {
		return workflowSerialId;
	}

	public void setWorkflowSerialId(String workflowSerialId) {
		this.workflowSerialId = workflowSerialId;
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
			case "workflowSerialId" : return "WORKFLOW_SERIAL_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public Workflow clone() throws CloneNotSupportedException {  
		return (Workflow)super.clone();
	}
}
