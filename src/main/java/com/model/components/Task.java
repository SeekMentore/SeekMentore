package com.model.components;

import java.io.Serializable;

import com.model.Notification;
import com.utils.ValidationUtils;

public class Task extends Notification implements Serializable {
	
	private static final long serialVersionUID = -2375203428701697320L;
	
	private Long taskId;
	
	public Task() {}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "taskId" : return "TASK_ID";
		}
		return EMPTY_STRING;
	}
}
