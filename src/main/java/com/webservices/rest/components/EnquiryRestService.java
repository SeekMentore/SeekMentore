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
import com.constants.components.EnquiryConstants;
import com.service.components.CommonsService;
import com.service.components.EnquiryService;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_ENQUIRY) 
public class EnquiryRestService extends AbstractRestWebservice implements RestMethodConstants, EnquiryConstants {
	
	@Path(REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_PENDING_ENQUIRIES)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayCustomerWithPendingEnquiries (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_PENDING_ENQUIRIES;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getEnquiryService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_PENDING_ENQUIRIES, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_MAPPED_ENQUIRIES)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayCustomerWithMappedEnquiries (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_MAPPED_ENQUIRIES;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getEnquiryService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_MAPPED_ENQUIRIES, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_ABANDONED_ENQUIRIES)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayCustomerWithAbandonedEnquiries (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_ABANDONED_ENQUIRIES;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getEnquiryService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_ABANDONED_ENQUIRIES, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	public EnquiryService getEnquiryService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ENQUIRY_SERVICE, EnquiryService.class);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	@Override
	public void doSecurity(final HttpServletRequest request) {
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_PENDING_ENQUIRIES : 
			case REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_MAPPED_ENQUIRIES : 
			case REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_ABANDONED_ENQUIRIES : {
				this.securityPassed = true;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, !this.securityPassed);
	}
}
