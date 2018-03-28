package com.model;

import java.io.Serializable;
import java.util.Date;

import com.constants.ApplicationConstants;

public class ApplicationError implements Serializable {

	private static final long serialVersionUID = 1850665202812309025L;
	private String errorMessage;
	private String stackTrace;
	private String url;
	private String serverName;
	private String userId;
	private boolean traceable;
	private Throwable cause;
	private String clientIPAddress;
	private String userName;
	private String clientHost;
	private String jvmName;
	private String browserName;
	private String operatingSystem;
	private String requestParams;
	private Date auditStamp;
	private String userAgent;

	/**
	 * @return Returns the errorMessage.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage The errorMessage to set.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return Returns the stackTrace.
	 */
	public String getStackTrace() {
		return stackTrace;
	}

	/**
	 * @param stackTrace The stackTrace to set.
	 */
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	/**
	 * @return Returns the traceable.
	 */
	public boolean isTraceable() {
		return traceable;
	}

	/**
	 * @param traceable The traceable to set.
	 */
	public void setTraceable(boolean traceable) {
		this.traceable = traceable;
	}

	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return Returns the serverName.
	 */
	public String getServerName() {
		if (serverName.indexOf(ApplicationConstants.DOT) != -1) {
			serverName = serverName.substring(0, serverName.indexOf(ApplicationConstants.DOT)).toUpperCase();
		}
		return serverName;
	}

	/**
	 * @param serverName The serverName to set.
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the cause
	 */
	public Throwable getCause() {
		return cause;
	}

	/**
	 * @param cause the cause to set
	 */
	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public String getClientIPAddress() {
		return clientIPAddress;
	}

	public void setClientIPAddress(final String clientIPAddress) {
		this.clientIPAddress = clientIPAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getClientHost() {
		return clientHost;
	}

	public void setClientHost(final String clientHost) {
		this.clientHost = clientHost;
	}

	public String getJvmName() {
		return jvmName;
	}

	public void setJvmName(final String jvmName) {
		this.jvmName = jvmName;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(final String browserName) {
		this.browserName = browserName;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(final String requestParams) {
		this.requestParams = requestParams;
	}

	public Date getAuditStamp() {
		return auditStamp;
	}

	public void setAuditStamp(final Date auditStamp) {
		this.auditStamp = auditStamp;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}
