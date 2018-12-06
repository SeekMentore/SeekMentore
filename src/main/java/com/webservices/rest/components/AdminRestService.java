package com.webservices.rest.components;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.AdminConstants;
import com.model.components.RegisteredTutor;
import com.model.components.SubscribedCustomer;
import com.model.gridcomponent.GridComponent;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.components.AdminService;
import com.service.components.CommonsService;
import com.service.components.CustomerService;
import com.service.components.TutorService;
import com.utils.ApplicationUtils;
import com.utils.GridComponentUtils;
import com.utils.JSONUtils;
import com.utils.ValidationUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_ADMIN) 
public class AdminRestService extends AbstractRestWebservice implements RestMethodConstants, AdminConstants {
	
	private String allIdsList;
	private String comments;
	
	@Path(REST_METHOD_NAME_REGISTERED_TUTORS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String registeredTutorsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_REGISTERED_TUTORS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, RegisteredTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<RegisteredTutor> registeredTutorsList = getTutorService().getRegisteredTutorList(gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, registeredTutorsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(registeredTutorsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/registeredTutorCheckDataAccess")
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String registeredTutorCheckDataAccess (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("documentViewAccess", true);
		restresponse.put("documentHandleAccess", true);
		restresponse.put("bankViewAccess", true);
		restresponse.put("bankHandleAccess", true);
		restresponse.put("formDataEditAccess", true);
		restresponse.put("activePackageViewAccess", true);
		restresponse.put("historyPackagesViewAccess", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_BLACKLIST_REGISTERED_TUTORS)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String blacklistRegisteredTutors (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_BLACKLIST_REGISTERED_TUTORS;
		this.allIdsList = allIdsList;
		this.comments = comments;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getTutorService().blacklistRegisteredTutorList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_UN_BLACKLIST_REGISTERED_TUTORS)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String unBlacklistRegisteredTutors (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UN_BLACKLIST_REGISTERED_TUTORS;
		this.allIdsList = allIdsList;
		this.comments = comments;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getTutorService().unBlacklistRegisteredTutorList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_SUBSCRIBED_CUSTOMERS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String subscribedCustomersList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_SUBSCRIBED_CUSTOMERS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribedCustomer.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscribedCustomer> subscribedCustomersList = getCustomerService().getSubscribedCustomersList(gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscribedCustomersList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscribedCustomersList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/subscribedCustomerCheckDataAccess")
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String subscribedCustomerCheckDataAccess (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("formDataEditAccess", true);
		restresponse.put("activePackageViewAccess", true);
		restresponse.put("historyPackagesViewAccess", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_BLACKLIST_SUBSCRIBED_CUSTOMERS)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String blacklistSubscribedCustomers (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_BLACKLIST_SUBSCRIBED_CUSTOMERS;
		this.allIdsList = allIdsList;
		this.comments = comments;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getCustomerService().blacklistSubscribedCustomerList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_UN_BLACKLIST_SUBSCRIBED_CUSTOMERS)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String unBlacklistSubscribedCustomers (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UN_BLACKLIST_SUBSCRIBED_CUSTOMERS;
		this.allIdsList = allIdsList;
		this.comments = comments;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getCustomerService().unBlacklistSubscribedCustomerList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	public AdminService getAdminService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ADMIN_SERVICE, AdminService.class);
	}
	
	public TutorService getTutorService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_TUTOR_SERVICE, TutorService.class);
	}
	
	public CustomerService getCustomerService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_CUSTOMER_SERVICE, CustomerService.class);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	public JNDIandControlConfigurationLoadService getJNDIandControlConfigurationLoadService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
	}

	@Override
	protected void doSecurity(HttpServletRequest request) throws Exception {
		this.request = request;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_REGISTERED_TUTORS_LIST :
			case REST_METHOD_NAME_SUBSCRIBED_CUSTOMERS_LIST : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_BLACKLIST_REGISTERED_TUTORS : 
			case REST_METHOD_NAME_UN_BLACKLIST_REGISTERED_TUTORS :
			case REST_METHOD_NAME_BLACKLIST_SUBSCRIBED_CUSTOMERS : 
			case REST_METHOD_NAME_UN_BLACKLIST_SUBSCRIBED_CUSTOMERS : {
				handleAllIdsAndComments();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
	
	private void handleAllIdsAndComments() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkStringAvailability(this.allIdsList) || !ValidationUtils.checkNonEmptyList(Arrays.asList(this.allIdsList.split(SEMICOLON)))) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_ID_ABSENT,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.checkStringAvailability(this.comments)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_COMMENTS_ABSENT,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	} 
}
