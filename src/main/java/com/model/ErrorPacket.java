package com.model;

import java.io.Serializable;
import java.util.Date;

import com.utils.ValidationUtils;

public class ErrorPacket extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -6349692224199736678L;
	
	private String errorSerialId;
	private Long occuredAtMillis;
	private String requestURI;
	private String errorTrace;
	private Boolean isUserGenerated;
	private User user;
	
	public ErrorPacket() {}
	
	public ErrorPacket (
		Long occuredAtMillis,
		String requestURI,
		String errorTrace,
		Boolean isUserGenerated,
		User user
	) {
		this.occuredAtMillis = occuredAtMillis;
		this.requestURI = requestURI;
		this.errorTrace = errorTrace;
		this.isUserGenerated = isUserGenerated;
		this.user = user;
	}
	
	public ErrorPacket (
			String requestURI,
			String errorTrace,
			Boolean isUserGenerated,
			User user
	) {
		this.occuredAtMillis = new Date().getTime();
		this.requestURI = requestURI;
		this.errorTrace = errorTrace;
		this.isUserGenerated = isUserGenerated;
		this.user = user;
	}
	
	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getErrorTrace() {
		return errorTrace;
	}

	public void setErrorTrace(String errorTrace) {
		this.errorTrace = errorTrace;
	}

	public String getErrorSerialId() {
		return errorSerialId;
	}

	public void setErrorSerialId(String errorSerialId) {
		this.errorSerialId = errorSerialId;
	}

	public Long getOccuredAtMillis() {
		return occuredAtMillis;
	}

	public void setOccuredAtMillis(Long occuredAtMillis) {
		this.occuredAtMillis = occuredAtMillis;
	}
	
	public Boolean getIsUserGenerated() {
		return isUserGenerated;
	}

	public void setIsUserGenerated(Boolean isUserGenerated) {
		this.isUserGenerated = isUserGenerated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
			case "errorSerialId" : return "ERROR_SERIAL_ID";
			case "occuredAtMillis" : return "OCCURED_AT_MILLIS";
			case "requestURI" : return "REQUEST_URI";
			case "errorTrace" : return "ERROR_TRACE";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public ErrorPacket clone() throws CloneNotSupportedException {  
		return (ErrorPacket)super.clone();
	}
}
