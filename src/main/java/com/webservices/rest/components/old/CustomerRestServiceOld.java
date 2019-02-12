package com.webservices.rest.components.old;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.CustomerConstants;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_CUSTOMER+"old") 
public class CustomerRestServiceOld extends AbstractRestWebservice implements RestMethodConstants, CustomerConstants {

	@Override
	protected void doSecurity(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}/*
	
	@Path(REST_METHOD_NAME_LOAD_SUBSCRIBED_CUSTOMER_RECORD)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String loadSubscribedCustomerRecord (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_LOAD_SUBSCRIBED_CUSTOMER_RECORD;
		doSecurity(request, response);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getCustomerService().getSubscribedCustomer(((SubscribedCustomer)getActiveUser(request)).clone()), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_SUBSCRIBED_CUSTOMER)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String getDropdownListDataSubscribedCustomer (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_SUBSCRIBED_CUSTOMER;
		doSecurity(request, response);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getCustomerService().getDropdownListData(), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			subscribedCustomerObj.setCustomerId(((SubscribedCustomer)getActiveUser(request)).getCustomerId());
			return JSONUtils.convertObjToJSONString(getCustomerService().updateDetails(subscribedCustomerObj), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getCustomerService().subscribedCustomersList(LINE_BREAK), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	@Override
	public void doSecurity(final HttpServletRequest request) {
		this.request = request; this.response = response;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_LOAD_SUBSCRIBED_CUSTOMER_RECORD :
			case REST_METHOD_NAME_TO_UPDATE_SUBSCRIBED_CUSTOMER_DETAILS :
			case REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_SUBSCRIBED_CUSTOMER :
			case REST_METHOD_NAME_DISPLAY_SUBSCRIBED_CUSTOMERS_LIST : {
				this.securityPassed = true;
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}

*/}
