package com.webservices.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractRestWebservice extends AbstractWebservice {
	
	// Since the Class is Prototype scope hence introducing a class level variable 
	// Do not do this in Service classes as they are singleton
	protected HttpServletRequest request;
	protected Map<String, Object> securityFailureResponse;
	protected String methodName;
	protected boolean securityPassed = false;
	
	protected abstract void doSecurity(final HttpServletRequest request) throws Exception;
}
