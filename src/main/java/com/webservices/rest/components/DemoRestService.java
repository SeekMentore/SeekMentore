package com.webservices.rest.components;

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
import com.constants.components.DemoTrackerConstants;
import com.service.components.CommonsService;
import com.service.components.DemoService;
import com.service.components.EnquiryService;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_DEMO) 
public class DemoRestService extends AbstractRestWebservice implements RestMethodConstants, DemoTrackerConstants {
	
	@Path(REST_METHOD_NAME_DISPLAY_PENDING_DEMOS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayPendingDemos (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_PENDING_DEMOS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getDemoService().displayDemos(REST_METHOD_NAME_DISPLAY_PENDING_DEMOS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
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
	
	public DemoService getDemoService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_DEMO_SERVICE, DemoService.class);
	}
	
	public EnquiryService getEnquiryService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ENQUIRY_SERVICE, EnquiryService.class);
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
			case REST_METHOD_NAME_DISPLAY_PENDING_DEMOS :
			case REST_METHOD_NAME_DISPLAY_SUCCESSFULL_DEMOS :
			case REST_METHOD_NAME_DISPLAY_FAILED_DEMOS : {
				this.securityPassed = true;
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, !this.securityPassed);
	}
}
