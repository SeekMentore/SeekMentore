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
import com.constants.components.DemoTrackerConstants;
import com.model.ErrorPacket;
import com.model.components.DemoTracker;
import com.service.components.CommonsService;
import com.service.components.DemoService;
import com.utils.ApplicationUtils;
import com.utils.ValidationUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_DEMO) 
public class DemoRestService extends AbstractRestWebservice implements RestMethodConstants, DemoTrackerConstants {
	
	private Long demoTrackerId;
	private Date rescheduledDemoDateAndTime;
	private Long rescheduledDemoDateAndTimeinMillis;
	private String finalizingRemarks;
	
	@Path(REST_METHOD_NAME_DISPLAY_SCHEDULED_DEMOS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayPendingDemos (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_SCHEDULED_DEMOS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getDemoService().displayDemos(REST_METHOD_NAME_DISPLAY_SCHEDULED_DEMOS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_RESCHEDULED_DEMOS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayRescheduledDemos (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_RESCHEDULED_DEMOS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getDemoService().displayDemos(REST_METHOD_NAME_DISPLAY_RESCHEDULED_DEMOS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_SUCCESSFULL_DEMOS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displaySuccessfullDemos (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_SUCCESSFULL_DEMOS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getDemoService().displayDemos(REST_METHOD_NAME_DISPLAY_SUCCESSFULL_DEMOS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_FAILED_DEMOS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayFailedDemos (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_FAILED_DEMOS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getDemoService().displayDemos(REST_METHOD_NAME_DISPLAY_FAILED_DEMOS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_CANCELED_DEMOS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayCanceledDemos (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_CANCELED_DEMOS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getDemoService().displayDemos(REST_METHOD_NAME_DISPLAY_CANCELED_DEMOS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_DEMO_DETAILS)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String displayDemoDetails (
			@FormParam("demoTrackerId") final Long demoTrackerId,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_DEMO_DETAILS;
		this.demoTrackerId = demoTrackerId;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getDemoService().displayDemoDetails(demoTrackerId), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_UPDATE_DEMO_TRACKER_DETAILS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String updateDemoTrackerDetails (
			final DemoTracker demoTrackerObject,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_UPDATE_DEMO_TRACKER_DETAILS;
		this.demoTrackerId = demoTrackerObject.getDemoTrackerId();
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getDemoService().updateDemoTrackerDetails(demoTrackerObject, getLoggedInUser(request)), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DEMO_SUCCESS)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String demoSuccess (
			@FormParam("demoTrackerId") final Long demoTrackerId,
			@FormParam("finalizingRemarks") final String finalizingRemarks,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DEMO_SUCCESS;
		this.demoTrackerId = demoTrackerId;
		this.finalizingRemarks = finalizingRemarks;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getDemoService().takeActionOnDemo(REST_METHOD_NAME_DEMO_SUCCESS, demoTrackerId, finalizingRemarks, getLoggedInUser(request)), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DEMO_FAILURE)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String demoFailure (
			@FormParam("demoTrackerId") final Long demoTrackerId,
			@FormParam("finalizingRemarks") final String finalizingRemarks,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DEMO_FAILURE;
		this.demoTrackerId = demoTrackerId;
		this.finalizingRemarks = finalizingRemarks;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getDemoService().takeActionOnDemo(REST_METHOD_NAME_DEMO_FAILURE, demoTrackerId, finalizingRemarks, getLoggedInUser(request)), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_CANCEL_DEMO)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String cancelDemo (
			@FormParam("demoTrackerId") final Long demoTrackerId,
			@FormParam("finalizingRemarks") final String finalizingRemarks,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_CANCEL_DEMO;
		this.demoTrackerId = demoTrackerId;
		this.finalizingRemarks = finalizingRemarks;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getDemoService().takeActionOnDemo(REST_METHOD_NAME_CANCEL_DEMO, demoTrackerId, finalizingRemarks, getLoggedInUser(request)), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_RESCHEDULE_DEMO)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String rescheduleDemo (
			@FormParam("demoTrackerId") final Long demoTrackerId,
			@FormParam("demoTimeInMillis") final Long demoTimeInMillis,
			@FormParam("finalizingRemarks") final String finalizingRemarks,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_RESCHEDULE_DEMO;
		this.demoTrackerId = demoTrackerId;
		this.rescheduledDemoDateAndTimeinMillis = demoTimeInMillis;
		this.finalizingRemarks = finalizingRemarks;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getDemoService().rescheduleDemo(demoTrackerId, rescheduledDemoDateAndTime, finalizingRemarks, getLoggedInUser(request)), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	public DemoService getDemoService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_DEMO_SERVICE, DemoService.class);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	@Override
	public void doSecurity(final HttpServletRequest request) {
		this.request = request;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_DISPLAY_SCHEDULED_DEMOS :
			case REST_METHOD_NAME_DISPLAY_RESCHEDULED_DEMOS :
			case REST_METHOD_NAME_DISPLAY_SUCCESSFULL_DEMOS :
			case REST_METHOD_NAME_DISPLAY_FAILED_DEMOS :
			case REST_METHOD_NAME_DISPLAY_CANCELED_DEMOS : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_DISPLAY_DEMO_DETAILS :
			case REST_METHOD_NAME_TO_UPDATE_DEMO_TRACKER_DETAILS :{
				handleParticularDemoSecurity();
				break;
			}
			case REST_METHOD_NAME_DEMO_SUCCESS : 
			case REST_METHOD_NAME_DEMO_FAILURE : 
			case REST_METHOD_NAME_CANCEL_DEMO : {
				handleTakeActionDemoSecurity();
				break;
			}
			case REST_METHOD_NAME_RESCHEDULE_DEMO : {
				handleRescheduledDemoSecurity();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, !this.securityPassed);
	}
	
	private void handleParticularDemoSecurity() {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.demoTrackerId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_DEMO_TRACKER_ID,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getLoggedInUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE) + LINE_BREAK + this.demoTrackerId);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleTakeActionDemoSecurity() {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.demoTrackerId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_DEMO_TRACKER_ID,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		switch(this.methodName) {
			case REST_METHOD_NAME_DEMO_SUCCESS : {
				break;
			}
			case REST_METHOD_NAME_CANCEL_DEMO :
			case REST_METHOD_NAME_DEMO_FAILURE : {
				if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.finalizingRemarks)) {
					ApplicationUtils.appendMessageInMapAttribute(
							this.securityFailureResponse, 
							VALIDATION_MESSAGE_INVALID_TAKE_ACTION_REMARKS_ID,
							RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
					this.securityPassed = false;
				}
				break;
			}
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getLoggedInUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE) + LINE_BREAK + this.demoTrackerId);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleRescheduledDemoSecurity() {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.demoTrackerId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_DEMO_TRACKER_ID,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.rescheduledDemoDateAndTimeinMillis)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_RESCHEDULE_DATE_AND_TIME,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		} else {
			this.rescheduledDemoDateAndTime = new Date(this.rescheduledDemoDateAndTimeinMillis);
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.finalizingRemarks)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_RESCHEDULE_REMARKS_ID,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getLoggedInUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE) + LINE_BREAK + this.demoTrackerId + LINE_BREAK + this.rescheduledDemoDateAndTimeinMillis);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
}
