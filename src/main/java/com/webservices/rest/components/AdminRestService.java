package com.webservices.rest.components;

import java.sql.Timestamp;
import java.util.Date;
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
import com.model.ErrorPacket;
import com.service.components.AdminService;
import com.service.components.CommonsService;
import com.utils.ApplicationUtils;
import com.utils.ValidationUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_ADMIN) 
public class AdminRestService extends AbstractRestWebservice implements RestMethodConstants, AdminConstants {
	
	private String gridName;
	private String button;
	private String tentativeTutorId;
	private String remarks;
	
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
		this.gridName = gridName;
		this.button = button;
		this.tentativeTutorId = tentativeTutorId;
		this.remarks = remarks;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().takeActionOnRegisteredTutors(gridName, button, tentativeTutorId, remarks, getLoggedInUser(request)), REST_MESSAGE_JSON_RESPONSE_NAME);
		} 
		return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
	}
	
	public AdminService getAdminService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ADMIN_SERVICE, AdminService.class);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
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
				handleTakeActionSecurity();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, !this.securityPassed);
	}
	
	private void handleTakeActionSecurity() {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.gridName)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_GRID_REFERENCE_ACCESS,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.button)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_BUTTON_ACTION,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		} else {
			switch(button) {
				case BUTTON_ACTION_CONTACTED : 
				case BUTTON_ACTION_RECONTACT : 
				case BUTTON_ACTION_REJECT : 
				case BUTTON_ACTION_VERIFY:
				case BUTTON_ACTION_REVERIFY : 
				case BUTTON_ACTION_FAILVERIFY : 
				case BUTTON_ACTION_SELECT : 
				case BUTTON_ACTION_RECONTACTED : {
					break;
				}
				default : {
					ApplicationUtils.appendMessageInMapAttribute(
							this.securityFailureResponse, 
							AdminConstants.VALIDATION_MESSAGE_INVALID_BUTTON_ACTION,
							RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
					this.securityPassed = false;
					break;
				}
			}
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.tentativeTutorId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_TUTOR_ID,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		switch(button) {
			case BUTTON_ACTION_CONTACTED : 
			case BUTTON_ACTION_VERIFY:
			case BUTTON_ACTION_REVERIFY : 
			case BUTTON_ACTION_RECONTACTED : 
			case BUTTON_ACTION_SELECT : {
				break;
			}
			case BUTTON_ACTION_RECONTACT : 
			case BUTTON_ACTION_FAILVERIFY : 
			case BUTTON_ACTION_REJECT : {
				if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.remarks)) {
					ApplicationUtils.appendMessageInMapAttribute(
							this.securityFailureResponse, 
							AdminConstants.VALIDATION_MESSAGE_PLEASE_ENTER_REMARKS,
							RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
					this.securityPassed = false;
				}
				break;
			}
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), REST_METHOD_NAME_TAKE_ACTION_ON_REGISTERED_TUTORS, this.gridName + LINE_BREAK + this.button + LINE_BREAK + this.tentativeTutorId + LINE_BREAK + this.remarks);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
}
