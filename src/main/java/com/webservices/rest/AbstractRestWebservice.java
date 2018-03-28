package com.webservices.rest;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractRestWebservice extends AbstractWebservice {
	
	public abstract boolean doSecurity(final HttpServletRequest request);
}
