package com.webservices.rest;

import javax.servlet.http.HttpServletRequest;

import com.model.User;
import com.utils.LoginUtils;

public abstract class AbstractWebservice {
	
	public String getLoggedInUserId(final HttpServletRequest request) throws Exception {
		return LoginUtils.getUserFromSession(request).getUserId();
	}
	
	public String getLoggedInUserIdAndTypeForPrinting(final HttpServletRequest request) throws Exception {
		return LoginUtils.getLoggedInUserIdAndTypeForPrinting(request);
	}
	
	public User getLoggedInUser(final HttpServletRequest request) throws Exception {
		return LoginUtils.getUserFromSession(request);
	}
	
	public String getLoggedInUserType(final HttpServletRequest request) {
		return LoginUtils.getUserTypeFromSession(request);
	}
	
	public String getActiveUserId(final HttpServletRequest request) throws Exception {
		return getLoggedInUserId(request);
	}
	
	public String getActiveUserIdAndTypeForPrinting(final HttpServletRequest request) throws Exception {
		return LoginUtils.getActiveUserIdAndTypeForPrintingWithExceptionHandled(request);
	}
	
	public User getActiveUser(final HttpServletRequest request) throws Exception {
		return getLoggedInUser(request);
	}
	
	public String getActiveUserType(final HttpServletRequest request) {
		return getLoggedInUserType(request);
	}
	
	public String getServerURL(final HttpServletRequest request) {
		return String.valueOf(request.getRequestURL()).substring(0, String.valueOf(request.getRequestURL()).indexOf(request.getRequestURI()));
	}
	
	public String getServerURLWithContextPath(final HttpServletRequest request) {
		return String.valueOf(request.getRequestURL()).substring(0, String.valueOf(request.getRequestURL()).indexOf(request.getRequestURI())) + request.getContextPath();
	}
}
