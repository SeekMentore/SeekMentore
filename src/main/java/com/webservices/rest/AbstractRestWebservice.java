package com.webservices.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.constants.ValidationConstants;
import com.constants.components.CommonsConstants;
import com.constants.components.ResponseMapConstants;
import com.model.User;
import com.utils.ApplicationUtils;
import com.utils.DateUtils;
import com.utils.ExceptionUtils;
import com.utils.JSONUtils;
import com.utils.LoggerUtils;
import com.utils.ValidationUtils;
import com.utils.localization.Message;

public abstract class AbstractRestWebservice extends AbstractWebservice implements ResponseMapConstants {
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String, Object> securityFailureResponse;
	protected String methodName;
	protected Boolean securityPassed = false;
	protected User activeUser;
	protected List<String> changedAttributes = new ArrayList<String>();
	protected List<String> omittableAttributesList = new ArrayList<String>();
	protected String parentSerialId;
	protected String allIdsList;
	protected String comments;
	protected String button;
	protected String grid;
	
	protected abstract void doSecurity(final HttpServletRequest request, final HttpServletResponse response) throws Exception;
	
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
	
	protected void appendError(final String message) {
		ApplicationUtils.appendMessageInMapAttribute(
				this.securityFailureResponse, 
				message,
				RESPONSE_MAP_ATTRIBUTE_MESSAGE);
		this.securityPassed = false;
	}
	
	protected void handleActiveUserSecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.activeUser)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_ACTIVE_USER_ABSENT),
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	protected void handleParentSerialId() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkStringAvailability(this.parentSerialId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_PARENT_ID_ABSENT),
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	protected void handleTakeAction() throws Exception {
		this.securityPassed = true;
		handleAllIds();
		if (!ValidationUtils.checkStringAvailability(this.button)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_BUTTON_ABSENT),
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	protected void handleAllIds() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkStringAvailability(this.allIdsList) || !ValidationUtils.checkNonEmptyList(Arrays.asList(this.allIdsList.split(SEMICOLON)))) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_ID_ABSENT),
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	} 
	
	protected void handleComments() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkStringAvailability(this.comments)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_COMMENTS_ABSENT),
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	protected void handleGridDownload() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkStringAvailability(this.grid)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_GRID_ABSENT),
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	protected void handleUnkownAttributeError(final String attributeName) {
		if (!(ValidationUtils.checkNonEmptyList(this.omittableAttributesList) && this.omittableAttributesList.contains(attributeName))) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_UNKONWN_PROPERTY, new Object[] {attributeName}),
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	protected void handleNoAttributeChangedError() {
		ApplicationUtils.appendMessageInMapAttribute(
				this.securityFailureResponse, 
				Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_NO_ATTRIBUTES_CHANGED),
				RESPONSE_MAP_ATTRIBUTE_MESSAGE);
		this.securityPassed = false;
	}
	
	protected void handleUnknownButtonError() {
		ApplicationUtils.appendMessageInMapAttribute(
				this.securityFailureResponse, 
				Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_BUTTON_UNKNOWN, new Object[] {this.button}),
				RESPONSE_MAP_ATTRIBUTE_MESSAGE);
		this.securityPassed = false;
	}
	
	protected void handleUploadingFileSizeError() {
		ApplicationUtils.appendMessageInMapAttribute(
				this.securityFailureResponse, 
				Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.INVALID_SIZE, new Object[] {CommonsConstants.MAXIMUM_FILE_SIZE_FOR_EMAIL_DOCUMENTS_IN_MB.toString()}),
				RESPONSE_MAP_ATTRIBUTE_MESSAGE);
		this.securityPassed = false;
	}
	
	protected void handleUploadingFileSizeError(final String maximumFileSizeInMB) {
		ApplicationUtils.appendMessageInMapAttribute(
				this.securityFailureResponse, 
				Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.INVALID_SIZE, new Object[] {maximumFileSizeInMB}),
				RESPONSE_MAP_ATTRIBUTE_MESSAGE);
		this.securityPassed = false;
	}
	
	protected void handleUploadingFileExtensionError() {
		ApplicationUtils.appendMessageInMapAttribute(
				this.securityFailureResponse, 
				Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.INVALID_EXTENSION, new Object[] {CommonsConstants.ACCEPTABLE_FILE_EXTENSIONS}),
				RESPONSE_MAP_ATTRIBUTE_MESSAGE);
		this.securityPassed = false;
	}
	
	protected void handleUploadingFileExtensionError(final String permittedExtensionsString) {
		ApplicationUtils.appendMessageInMapAttribute(
				this.securityFailureResponse, 
				Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.INVALID_EXTENSION, new Object[] {permittedExtensionsString}),
				RESPONSE_MAP_ATTRIBUTE_MESSAGE);
		this.securityPassed = false;
	}
}
