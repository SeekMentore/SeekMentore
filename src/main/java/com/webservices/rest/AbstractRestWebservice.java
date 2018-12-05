package com.webservices.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.constants.ValidationConstants;
import com.utils.DateUtils;
import com.utils.ExceptionUtils;
import com.utils.JSONUtils;
import com.utils.LoggerUtils;
import com.utils.ValidationUtils;

public abstract class AbstractRestWebservice extends AbstractWebservice {
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String, Object> securityFailureResponse;
	protected String methodName;
	protected boolean securityPassed = false;
	protected List<String> changedAttributes = new ArrayList<String>();
	
	protected abstract void doSecurity(final HttpServletRequest request) throws Exception;
	
	protected <T extends Object> T getValueForPropertyFromCompleteUpdatedJSONObject(final JsonObject jsonObject, final String propertyName, final Class<T> type) {
		LoggerUtils.logOnConsole("Update record JSON Object >> " + jsonObject);
		if (ValidationUtils.checkObjectAvailability(JSONUtils.getValueFromJSONObject(jsonObject, propertyName, String.class))) {
			LoggerUtils.logOnConsole("JSON Property >> " + propertyName);
			T typeValue = null;
			if (!ValidationConstants.NULLIFIED.equalsIgnoreCase(JSONUtils.getValueFromJSONObject(jsonObject, propertyName, String.class))) {
				final String value = JSONUtils.getValueFromJSONObject(jsonObject, propertyName, String.class);
				LoggerUtils.logOnConsole("JSON Property STRING value is >> " + value);
				LoggerUtils.logOnConsole("(type) class >> " + type.toString());
				if (Integer.class.equals(type)) {
					try {
						typeValue = type.cast(Integer.parseInt(value));
					} catch (Exception e) {
						LoggerUtils.logOnConsole("Cannot cast value (" + value + ") to Integer");
						LoggerUtils.logOnConsole(ExceptionUtils.generateErrorLog(e));
						LoggerUtils.logError(e);
					}
				} else if (Float.class.equals(type)) {
					try {
						typeValue = type.cast(Float.parseFloat(value));
					} catch (Exception e) {
						LoggerUtils.logOnConsole("Cannot cast value (" + value + ") to Float");
						LoggerUtils.logOnConsole(ExceptionUtils.generateErrorLog(e));
						LoggerUtils.logError(e);
					}
				} else if (Double.class.equals(type)) {
					try {
						typeValue = type.cast(Double.parseDouble(value));
					} catch (Exception e) {
						LoggerUtils.logOnConsole("Cannot cast value (" + value + ") to Double");
						LoggerUtils.logOnConsole(ExceptionUtils.generateErrorLog(e));
						LoggerUtils.logError(e);
					}
				} else if (Long.class.equals(type)) {
					try {
						typeValue = type.cast(Long.parseLong(value));
					} catch (Exception e) {
						LoggerUtils.logOnConsole("Cannot cast value (" + value + ") to Long");
						LoggerUtils.logOnConsole(ExceptionUtils.generateErrorLog(e));
						LoggerUtils.logError(e);
					}
				} else if (Date.class.equals(type)) {
					try {
						typeValue = type.cast(DateUtils.parseYYYYMMDD(value));
					} catch (Exception e) {
						LoggerUtils.logOnConsole("Cannot cast value (" + value + ") to Date");
						LoggerUtils.logOnConsole(ExceptionUtils.generateErrorLog(e));
						LoggerUtils.logError(e);
					}
				} else {
					try {
						typeValue = type.cast(value);
					} catch (Exception e) {
						LoggerUtils.logOnConsole("Cannot cast value (" + value + ") to " + type.toString());
						LoggerUtils.logOnConsole(ExceptionUtils.generateErrorLog(e));
						LoggerUtils.logError(e);
					}
				}
			} else {
				LoggerUtils.logOnConsole("JSON Property value is >> " + ValidationConstants.NULLIFIED);
			}
			this.changedAttributes.add(propertyName);
			return typeValue;
		} else {
			LoggerUtils.logOnConsole("JSON Object 'property' is NULL >> " + propertyName);
		}
		return null;
	}
}
