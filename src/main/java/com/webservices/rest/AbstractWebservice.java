package com.webservices.rest;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import com.constants.ApplicationConstants;
import com.google.gson.Gson;
import com.model.User;
import com.utils.LoginUtils;

public abstract class AbstractWebservice {
	
	public String getLoggedInEmpId(final HttpServletRequest request) {
		return LoginUtils.getUserFromSession(request).getEmpId();
	}
	
	public User getLoggedInUser(final HttpServletRequest request) {
		return LoginUtils.getUserFromSession(request);
	}
	
	public String convertObjToJSONString(
			final Object obj,
			final String ObjName
	) throws JSONException {
		return new JSONObject().put(ObjName, new Gson().toJson((obj != null) ? obj : ApplicationConstants.EMPTY_STRING)).toString();
	}
	
	public String getServerURL(final HttpServletRequest request) {
		return String.valueOf(request.getRequestURL()).substring(0, String.valueOf(request.getRequestURL()).indexOf(request.getRequestURI()));
	}
	
	public String getServerURLWithContextPath(final HttpServletRequest request) {
		return String.valueOf(request.getRequestURL()).substring(0, String.valueOf(request.getRequestURL()).indexOf(request.getRequestURI())) + request.getContextPath();
	}
}
