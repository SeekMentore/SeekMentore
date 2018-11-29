package com.model.components;

import java.io.Serializable;

import com.model.Notification;
import com.utils.ValidationUtils;

public class Workflow extends Notification implements Serializable {
	
	private static final long serialVersionUID = -2375203428701697320L;
	
	private Long workflowId;
	
	public Workflow() {}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "workflowId" : return "WORKFLOW_ID";
		}
		return EMPTY_STRING;
	}
}
