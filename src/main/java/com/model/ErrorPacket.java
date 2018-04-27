package com.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.constants.UserConstants;

public class ErrorPacket implements Serializable, UserConstants {
	
	private static final long serialVersionUID = -6349692224199736678L;
	
	private long errorId;
	private Timestamp occuredAt;
	private String requestURI;
	private String errorTrace;
	
	public ErrorPacket() {
	}
	
	public ErrorPacket (
			Timestamp occuredAt,
			String requestURI,
			String errorTrace
	) {
		this.occuredAt = occuredAt;
		this.requestURI = requestURI;
		this.errorTrace = errorTrace;
	}
	
	public Timestamp getOccuredAt() {
		return occuredAt;
	}

	public void setOccuredAt(Timestamp occuredAt) {
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

	public long getErrorId() {
		return errorId;
	}

	public void setErrorId(long errorId) {
		this.errorId = errorId;
	}
}
