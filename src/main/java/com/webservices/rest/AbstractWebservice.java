package com.webservices.rest;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;

import com.constants.ApplicationConstants;
import com.google.gson.Gson;
import com.model.User;
import com.utils.LoginUtils;

public abstract class AbstractWebservice {
	
	public String getLoggedInUserId(final HttpServletRequest request) {
		return LoginUtils.getUserFromSession(request).getUserId();
	}
	
	public String getLoggedInUserIdAndTypeForPrinting(final HttpServletRequest request) {
		return LoginUtils.getLoggedInUserIdAndTypeForPrinting(request);
	}
	
	public User getLoggedInUser(final HttpServletRequest request) {
		return LoginUtils.getUserFromSession(request);
	}
	
	public String getLoggedInUserType(final HttpServletRequest request) {
		return LoginUtils.getUserTypeFromSession(request);
	}
	
	public <T extends Object> T getLoggedInUserTypeObject(final HttpServletRequest request, Class<T> type) throws DataAccessException, InstantiationException, IllegalAccessException {
		return LoginUtils.getUserTypeObjectFromSession(request, type);
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
