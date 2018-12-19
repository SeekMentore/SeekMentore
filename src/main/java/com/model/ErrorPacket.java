package com.model;

import java.io.Serializable;
import java.util.Date;

import com.constants.UserConstants;

public class ErrorPacket implements Serializable, UserConstants {
	
	private static final long serialVersionUID = -6349692224199736678L;
	
	private Long errorId;
	private Date occuredAt;
	private Long occuredAtMillis;
	private String requestURI;
	private String errorTrace;
	
	public ErrorPacket() {
	}
	
	public ErrorPacket (
			Date occuredAt,
			String requestURI,
			String errorTrace
	) {
		this.occuredAt = occuredAt;
		this.occuredAtMillis = occuredAt.getTime();
		this.requestURI = requestURI;
		this.errorTrace = errorTrace;
	}
	
	public ErrorPacket (
			Long occuredAtMillis,
			String requestURI,
			String errorTrace
	) {
		this.occuredAtMillis = occuredAtMillis;
		this.occuredAt = new Date(occuredAtMillis);
		this.requestURI = requestURI;
		this.errorTrace = errorTrace;
	}
	
	public Date getOccuredAt() {
		return occuredAt;
	}

	public void setOccuredAt(Date occuredAt) {
		this.occuredAt = occuredAt;
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
}
