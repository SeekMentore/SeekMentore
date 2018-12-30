package com.model;

import java.io.Serializable;
import java.util.Date;

import com.utils.ValidationUtils;

public class ErrorPacket extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -6349692224199736678L;
	
	private Long errorId;
	private Long occuredAtMillis;
	private String requestURI;
	private String errorTrace;
	
	public ErrorPacket() {
	}
	
	public ErrorPacket (
			Long occuredAtMillis,
			String requestURI,
			String errorTrace
	) {
		this.occuredAtMillis = occuredAtMillis;
		this.requestURI = requestURI;
		this.errorTrace = errorTrace;
	}
	
	public ErrorPacket (
			String requestURI,
			String errorTrace
	) {
		this.occuredAtMillis = new Date().getTime();
		this.requestURI = requestURI;
		this.errorTrace = errorTrace;
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

	public Long getErrorId() {
		return errorId;
	}

	public void setErrorId(Long errorId) {
		this.errorId = errorId;
	}

	public Long getOccuredAtMillis() {
		return occuredAtMillis;
	}

	public void setOccuredAtMillis(Long occuredAtMillis) {
		this.occuredAtMillis = occuredAtMillis;
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
			case "errorId" : return "ERROR_ID";
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
