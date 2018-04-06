package com.webservices.rest.components.publicaccess;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.SelectLookupConstants;
import com.constants.components.publicaccess.BecomeTutorConstants;
import com.constants.components.publicaccess.PublicAccessConstants;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.components.publicaccess.PublicApplication;
import com.model.components.publicaccess.SubmitQuery;
import com.service.components.publicaccess.PublicAccessService;
import com.utils.ApplicationUtils;
import com.utils.ValidationUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_PUBLIC_ACCESS) 
public class PublicAccessRestService extends AbstractRestWebservice implements RestMethodConstants, PublicAccessConstants {
	
	// Since the Class is Prototype scope hence introducing a class level variable 
	// Do not do this in Service classes as they are singleton
	private PublicApplication application;
	
	@Path(REST_METHOD_NAME_TO_BECOME_TUTOR)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String becomeTutor (
			final BecomeTutor application,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_BECOME_TUTOR;
		this.application = application;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getPublicAccessService().submitApplication(application), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_FIND_TUTOR)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String findTutor (
			final FindTutor application,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_FIND_TUTOR;
		this.application = application;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getPublicAccessService().submitApplication(application), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_SUBMIT_QUERY)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String submitQuery (
			final SubmitQuery application,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_SUBMIT_QUERY;
		this.application = application;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getPublicAccessService().submitApplication(application), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_SUBSCRIBE)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String subscribe (
			final FindTutor application,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_SUBSCRIBE;
		this.application = application;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getPublicAccessService().submitApplication(application), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	public PublicAccessService getPublicAccessService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_PUBLIC_ACCESS_SERVICE, PublicAccessService.class);
	}
	
	@Override
	public void doSecurity (final HttpServletRequest request) {
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
		final String captchaResponse = this.application.getCaptchaResponse();
		if (verifyCaptcha(captchaResponse)) {
			switch(this.methodName) {
				case REST_METHOD_NAME_TO_BECOME_TUTOR : {
					handleBecomeTutorSecurity();
					break;
				}
				case REST_METHOD_NAME_TO_FIND_TUTOR : {
					// Method level security
					this.securityPassed = true;
					break;
				}
				case REST_METHOD_NAME_TO_SUBMIT_QUERY : {
					// Method level security
					this.securityPassed = true;
					break;
				}
				case REST_METHOD_NAME_TO_SUBSCRIBE : {
					// Method level security
					this.securityPassed = true;
					break;
				}
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, !this.securityPassed);
	}
	
	private void handleBecomeTutorSecurity() {
		final BecomeTutor becomeTutorApplication = (BecomeTutor) this.application;
		this.securityPassed = true;
		if (!ValidationUtils.validatePhoneNumber(becomeTutorApplication.getContactNumber(), 10)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_CONTACT_NUMBER_MOBILE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateEmailAddress(becomeTutorApplication.getEmailId())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_EMAIL_ID,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateNameString(becomeTutorApplication.getFirstName(), false)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_FIRST_NAME,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateNameString(becomeTutorApplication.getLastName(), false)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_LAST_NAME,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getGender(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_GENDER_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_GENDER,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getQualification(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_QUALIFICATION,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getPrimaryProfession(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_PRIMARY_PROFESSION,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getTransportMode(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_TRANSPORT_MODE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_SUBJECTS,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getLocations(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_LOCATIONS,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateNumber(becomeTutorApplication.getTeachingExp(), true, 99, false, 0)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_TEACHING_EXPERIENCE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getPreferredTimeToCall(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_PREFERRED_TIME_TO_CALL,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	private boolean verifyCaptcha(final String captchaResponse) {
		// Verify captcha response and set values in securityFailureResponse
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(captchaResponse)) {
			return true;
		}
		return false;
	}
}
