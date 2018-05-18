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
import com.constants.components.CustomerConstants;
import com.model.components.SubscribedCustomer;
import com.service.components.CommonsService;
import com.service.components.CustomerService;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_CUSTOMER) 
public class CustomerRestService extends AbstractRestWebservice implements RestMethodConstants, CustomerConstants {
	
	@Path(REST_METHOD_NAME_LOAD_SUBSCRIBED_CUSTOMER_RECORD)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String loadSubscribedCustomerRecord (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_LOAD_SUBSCRIBED_CUSTOMER_RECORD;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getCustomerService().getSubscribedCustomer(getLoggedInUserTypeObject(request, SubscribedCustomer.class).getACopy()), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_SUBSCRIBED_CUSTOMER)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String getDropdownListDataSubscribedCustomer (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_SUBSCRIBED_CUSTOMER;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getCustomerService().getDropdownListData(), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_UPDATE_SUBSCRIBED_CUSTOMER_DETAILS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String updateSubscribedCustomerDetails (
			final SubscribedCustomer subscribedCustomerObj,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_UPDATE_SUBSCRIBED_CUSTOMER_DETAILS;
		doSecurity(request);
		if (this.securityPassed) {
			subscribedCustomerObj.setCustomerId(getLoggedInUserTypeObject(request, SubscribedCustomer.class).getCustomerId());
			return convertObjToJSONString(getCustomerService().updateDetails(subscribedCustomerObj), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	public CustomerService getCustomerService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_CUSTOMER_SERVICE, CustomerService.class);
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_SUBSCRIBED_CUSTOMERS_LIST)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String subscribedCustomersList (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_SUBSCRIBED_CUSTOMERS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getCustomerService().subscribedCustomersList(LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	@Override
	public void doSecurity(final HttpServletRequest request) {
		this.request = request;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_LOAD_SUBSCRIBED_CUSTOMER_RECORD :
			case REST_METHOD_NAME_TO_UPDATE_SUBSCRIBED_CUSTOMER_DETAILS :
			case REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_SUBSCRIBED_CUSTOMER :
			case REST_METHOD_NAME_DISPLAY_SUBSCRIBED_CUSTOMERS_LIST : {
				this.securityPassed = true;
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, !this.securityPassed);
	}

}
