package com.webservices.rest.components;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
import com.constants.components.AdminConstants;
import com.service.components.AdminService;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_ADMIN) 
public class AdminRestService extends AbstractRestWebservice implements RestMethodConstants, AdminConstants {
	
	@Path(REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_REGISTRATIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayNonContactedTutorRegistrations (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_REGISTRATIONS), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_REGISTRATIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayNonVerifiedTutorRegistrations (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_REGISTRATIONS), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_REGISTRATIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayVerifiedTutorRegistrations (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_REGISTRATIONS), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_REGISTRATIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayVerificationFailedTutorRegistrations (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_REGISTRATIONS), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_REGISTRATIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayToBeRecontactedTutorRegistrations (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_REGISTRATIONS), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_REGISTRATIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displaySelectedTutorRegistrations (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_REGISTRATIONS), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_REGISTRATIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayRejectedTutorRegistrations (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_REGISTRATIONS), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TAKE_ACTION_ON_REGISTERED_TUTORS)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String takeActionOnRegisteredTutors (
			@FormParam("gridName") final String gridName,
			@FormParam("button") final String button,
			@FormParam("tentativeTutorId") final String tentativeTutorId,
			@FormParam("remarks") final String remarks,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TAKE_ACTION_ON_REGISTERED_TUTORS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().takeActionOnRegisteredTutors(gridName, button, tentativeTutorId, remarks, getLoggedInUser(request)), REST_MESSAGE_JSON_RESPONSE_NAME);
		} 
		return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
	}
	
	public AdminService getAdminService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ADMIN_SERVICE, AdminService.class);
	}
	
	@Override
	public void doSecurity(final HttpServletRequest request) {
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_REGISTRATIONS : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_REGISTRATIONS : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_REGISTRATIONS : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_REGISTRATIONS : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_REGISTRATIONS : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_REGISTRATIONS : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_REGISTRATIONS : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_TAKE_ACTION_ON_REGISTERED_TUTORS : {
				this.securityPassed = true;
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, !this.securityPassed);
	}
}
