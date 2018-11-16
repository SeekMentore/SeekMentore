package com.model.components;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
	
	private static final long serialVersionUID = -2375203428701697320L;
	
	private Long id;
	private Date initiatedDate;
	private Date actionDate;
	private Long initiatedDateMillis;
	private Long actionDateMillis;
	private String initiatedBy;
	private String actionBy;
	private Date dueDate;
	private Long dueDateMillis;
	private String subject;
	
	public Task() {}
	
	// to be removed
	public Task(final Long id) {
		this.id = id;
		initiatedDate = new Date();
		actionDate = new Date();
		dueDate = new Date();
		initiatedDateMillis = initiatedDate.getTime();
		actionDateMillis = actionDate.getTime();
		dueDateMillis = dueDate.getTime();
		initiatedBy = "Shantanu Mukherjee " + id;
		subject = "Dummy Alert and Reminder for : " + id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInitiatedDate() {
		return initiatedDate;
	}

	public void setInitiatedDate(Date initiatedDate) {
		this.initiatedDate = initiatedDate;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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
}
