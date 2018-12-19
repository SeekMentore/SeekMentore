package com.webservices.rest.components;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.AdminConstants;
import com.constants.components.CustomerConstants;
import com.constants.components.EnquiryConstants;
import com.constants.components.SalesConstants;
import com.constants.components.SelectLookupConstants;
import com.constants.components.TutorConstants;
import com.model.components.DemoTracker;
import com.model.components.Enquiry;
import com.model.components.RegisteredTutor;
import com.model.components.TutorMapper;
import com.model.gridcomponent.GridComponent;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.components.AdminService;
import com.service.components.CommonsService;
import com.service.components.DemoService;
import com.service.components.EnquiryService;
import com.service.components.TutorService;
import com.utils.ApplicationUtils;
import com.utils.GridComponentUtils;
import com.utils.JSONUtils;
import com.utils.ValidationUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_SALES) 
public class SalesRestService extends AbstractRestWebservice implements SalesConstants, RestMethodConstants {
	
	private Long customerId;
	private Long enquiryId;
	private Long tutorId;
	private Enquiry enquiryObject;
	private TutorMapper tutorMapperObject;
	private DemoTracker demoTrackerObject;
	
	@Path(REST_METHOD_NAME_PENDING_ENQUIRIES_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String pendingEnquiriesList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_PENDING_ENQUIRIES_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, Enquiry.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<Enquiry> enquiryList = getEnquiryService().getEnquiryList(REST_METHOD_NAME_PENDING_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, enquiryList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(enquiryList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_COMPLETED_ENQUIRIES_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String completedEnquiriesList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_COMPLETED_ENQUIRIES_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, Enquiry.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<Enquiry> enquiryList = getEnquiryService().getEnquiryList(REST_METHOD_NAME_COMPLETED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, enquiryList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(enquiryList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_ABORTED_ENQUIRIES_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String abortedEnquiriesList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_ABORTED_ENQUIRIES_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, Enquiry.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<Enquiry> enquiryList = getEnquiryService().getEnquiryList(REST_METHOD_NAME_ABORTED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, enquiryList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(enquiryList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TAKE_ACTION_ON_ENQUIRY)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String takeActionOnEnquiry (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_BUTTON) final String button,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TAKE_ACTION_ON_ENQUIRY;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.button = button;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getEnquiryService().takeActionOnEnquiry(button, Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/pendingEnquiryCheckDataAccess")
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String pendingEnquiryCheckDataAccess (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("allEnquiriesDataModificationAccess", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_UPDATE_ENQUIRY_RECORD)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateEnquiryRecord (
			@FormDataParam(REQUEST_PARAM_COMPLETE_UPDATED_RECORD) final String completeUpdatedRecord,
			@FormDataParam(REQUEST_PARAM_PARENT_ID) final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UPDATE_ENQUIRY_RECORD;
		createEnquiryObjectFromCompleteUpdatedRecordJSONObject(JSONUtils.getJSONObjectFromString(completeUpdatedRecord));
		try {
			this.parentId = Long.parseLong(parentId);
		} catch(NumberFormatException e) {}
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			this.enquiryObject.setEnquiryId(Long.parseLong(parentId));
			getEnquiryService().updateEnquiryRecord(this.enquiryObject, this.changedAttributes, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, MESSAGE_UPDATED_RECORD);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_CURRENT_CUSTOMER_ALL_PENDING_ENQUIRIES_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String currentCustomerAllPendingEnquiriesList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_CURRENT_CUSTOMER_ALL_PENDING_ENQUIRIES_LIST;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, Enquiry.class);
		customerId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "customerId", Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<Enquiry> enquiryList = getEnquiryService().getEnquiryList(REST_METHOD_NAME_CURRENT_CUSTOMER_ALL_PENDING_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, enquiryList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(enquiryList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_BE_MAPPED_ENQUIRIES_GRID_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String toBeMappedEnquiriesGridList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_BE_MAPPED_ENQUIRIES_GRID_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, Enquiry.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<Enquiry> enquiryList = getEnquiryService().getEnquiryList(REST_METHOD_NAME_TO_BE_MAPPED_ENQUIRIES_GRID_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, enquiryList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(enquiryList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/mapTutorToEnquiryCheckDataAccess")
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String mapTutorToEnquiryCheckDataAccess (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("enquiryMappingAccess", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_ALL_MAPPING_ELIGIBLE_TUTORS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String allMappingEligibleTutorsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_ALL_MAPPING_ELIGIBLE_TUTORS_LIST;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, RegisteredTutor.class);
		this.enquiryId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "enquiryId", Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			gridComponent.setAdditionalFilterQueryString("WHERE TUTOR_ID NOT IN (SELECT T.TUTOR_ID FROM TUTOR_MAPPER T WHERE T.ENQUIRY_ID = "+this.enquiryId+")");
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
	
	@Path(REST_METHOD_NAME_MAP_REGISTERED_TUTORS)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String mapRegisteredTutors (
			@FormParam(REQUEST_PARAM_PARENT_ID) final String parentId,
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_MAP_REGISTERED_TUTORS;
		this.allIdsList = allIdsList;
		try {
			this.parentId = Long.parseLong(parentId);
		} catch(NumberFormatException e) {}
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getEnquiryService().mapRegisteredTutors(Long.parseLong(parentId), Arrays.asList(allIdsList.split(SEMICOLON)), getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	
	@Path(REST_METHOD_NAME_CURRENT_ENQUIRY_ALL_MAPPED_TUTORS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String allMappedTutorsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_CURRENT_ENQUIRY_ALL_MAPPED_TUTORS_LIST;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, TutorMapper.class);
		enquiryId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "enquiryId", Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<TutorMapper> allMappedTutorsList = getEnquiryService().getAllMappedTutorsList(REST_METHOD_NAME_CURRENT_ENQUIRY_ALL_MAPPED_TUTORS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, allMappedTutorsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(allMappedTutorsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_UN_MAP_REGISTERED_TUTORS)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String unmapRegisteredTutors (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UN_MAP_REGISTERED_TUTORS;
		this.allIdsList = allIdsList;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getEnquiryService().unmapRegisteredTutors(Arrays.asList(allIdsList.split(SEMICOLON)), getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/mappedTutorCheckDataAccess")
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String mappedTutorCheckDataAccess (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("mappedEnquiryFormAccess", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_UPDATE_TUTOR_MAPPER_RECORD)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateTutorMapperRecord (
			@FormDataParam(REQUEST_PARAM_COMPLETE_UPDATED_RECORD) final String completeUpdatedRecord,
			@FormDataParam(REQUEST_PARAM_PARENT_ID) final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UPDATE_TUTOR_MAPPER_RECORD;
		createTutorMapperObjectFromCompleteUpdatedRecordJSONObject(JSONUtils.getJSONObjectFromString(completeUpdatedRecord));
		try {
			this.parentId = Long.parseLong(parentId);
		} catch(NumberFormatException e) {}
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			this.tutorMapperObject.setTutorMapperId(Long.parseLong(parentId));
			getEnquiryService().updateTutorMapperRecord(this.tutorMapperObject, this.changedAttributes, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, MESSAGE_UPDATED_RECORD);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_PENDING_MAPPED_TUTORS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String pendingMappedTutorsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_PENDING_MAPPED_TUTORS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, TutorMapper.class);
			final List<TutorMapper> allMappedTutorsList = getEnquiryService().getAllMappedTutorsList(REST_METHOD_NAME_PENDING_MAPPED_TUTORS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, allMappedTutorsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(allMappedTutorsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DEMO_READY_MAPPED_TUTORS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String demoReadyMappedTutorsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DEMO_READY_MAPPED_TUTORS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, TutorMapper.class);
			final List<TutorMapper> allMappedTutorsList = getEnquiryService().getAllMappedTutorsList(REST_METHOD_NAME_DEMO_READY_MAPPED_TUTORS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, allMappedTutorsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(allMappedTutorsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DEMO_SCHEDULED_MAPPED_TUTORS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String demoScheduledMappedTutorsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DEMO_SCHEDULED_MAPPED_TUTORS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, TutorMapper.class);
			final List<TutorMapper> allMappedTutorsList = getEnquiryService().getAllMappedTutorsList(REST_METHOD_NAME_DEMO_SCHEDULED_MAPPED_TUTORS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, allMappedTutorsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(allMappedTutorsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TAKE_ACTION_ON_MAPPED_TUTOR)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String takeActionOnMappedTutor (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_BUTTON) final String button,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TAKE_ACTION_ON_MAPPED_TUTOR;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.button = button;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getEnquiryService().takeActionOnTutorMapper(button, Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/mappedTutorCheckScheduleDemoAccess")
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String mappedTutorCheckScheduleDemoAccess (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("scheduleDemoFormAccess", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_CURRENT_TUTOR_MAPPING_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String currentTutorMappingList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_CURRENT_TUTOR_MAPPING_LIST;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, TutorMapper.class);
		this.tutorId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "tutorId", Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<TutorMapper> currentTutorAllMappingList = getEnquiryService().getAllMappedTutorsList(REST_METHOD_NAME_CURRENT_TUTOR_MAPPING_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, currentTutorAllMappingList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(currentTutorAllMappingList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_CURRENT_CUSTOMER_MAPPING_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String currentCustomerMappingList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_CURRENT_CUSTOMER_MAPPING_LIST;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, TutorMapper.class);
		this.customerId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "customerId", Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<TutorMapper> currentTutorAllMappingList = getEnquiryService().getAllMappedTutorsList(REST_METHOD_NAME_CURRENT_CUSTOMER_MAPPING_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, currentTutorAllMappingList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(currentTutorAllMappingList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_CURRENT_TUTOR_SCHEDULED_DEMO_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String currentTutorScheduledDemoList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_CURRENT_TUTOR_SCHEDULED_DEMO_LIST;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, DemoTracker.class);
		this.tutorId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "tutorId", Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<DemoTracker> currentTutorAllScheduledDemoList = getDemoService().getDemoList(REST_METHOD_NAME_CURRENT_TUTOR_SCHEDULED_DEMO_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, currentTutorAllScheduledDemoList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(currentTutorAllScheduledDemoList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_CURRENT_CUSTOMER_SCHEDULED_DEMO_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String currentCustomerScheduledDemoList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_CURRENT_CUSTOMER_SCHEDULED_DEMO_LIST;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, DemoTracker.class);
		this.customerId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "customerId", Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<DemoTracker> currentTutorAllScheduledDemoList = getDemoService().getDemoList(REST_METHOD_NAME_CURRENT_CUSTOMER_SCHEDULED_DEMO_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, currentTutorAllScheduledDemoList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(currentTutorAllScheduledDemoList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_SCHEDULE_DEMO)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String scheduleDemo (
			@FormDataParam(REQUEST_PARAM_COMPLETE_UPDATED_RECORD) final String completeUpdatedRecord,
			@FormDataParam(REQUEST_PARAM_PARENT_ID) final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_SCHEDULE_DEMO;
		createTutorMapperSchdeuleDemoObjectFromCompleteUpdatedRecordJSONObject(JSONUtils.getJSONObjectFromString(completeUpdatedRecord));
		try {
			this.parentId = Long.parseLong(parentId);
		} catch(NumberFormatException e) {}
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			this.tutorMapperObject.setTutorMapperId(Long.parseLong(parentId));
			getEnquiryService().scheduleDemo(this.tutorMapperObject, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "Scheduled Demo Successfully");
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_SCHEDULED_DEMO_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String scheduledDemoList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_SCHEDULED_DEMO_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, DemoTracker.class);
			final List<DemoTracker> demoList = getDemoService().getDemoList(REST_METHOD_NAME_SCHEDULED_DEMO_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, demoList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(demoList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_RESCHEDULED_DEMO_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String reScheduledDemoList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_RESCHEDULED_DEMO_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, DemoTracker.class);
			final List<DemoTracker> demoList = getDemoService().getDemoList(REST_METHOD_NAME_RESCHEDULED_DEMO_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, demoList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(demoList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_SUCCESSFUL_DEMO_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String successfulDemoList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_SUCCESSFUL_DEMO_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, DemoTracker.class);
			final List<DemoTracker> demoList = getDemoService().getDemoList(REST_METHOD_NAME_SUCCESSFUL_DEMO_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, demoList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(demoList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_FAILED_DEMO_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String failedDemoList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_FAILED_DEMO_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, DemoTracker.class);
			final List<DemoTracker> demoList = getDemoService().getDemoList(REST_METHOD_NAME_FAILED_DEMO_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, demoList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(demoList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_CANCELED_DEMO_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String cancelledDemoGridList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_CANCELED_DEMO_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, DemoTracker.class);
			final List<DemoTracker> demoList = getDemoService().getDemoList(REST_METHOD_NAME_CANCELED_DEMO_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, demoList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(demoList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TAKE_ACTION_ON_DEMO)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String takeActionOnDemo (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_BUTTON) final String button,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TAKE_ACTION_ON_DEMO;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.button = button;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getDemoService().takeActionOnDemo(button, Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request), true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/demoTrackerModifyCheckDataAccess")
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String demoTrackerModifyCheckDataAccess (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("demoTrackerFormAccess", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_RE_SCHEDULE_DEMO)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String reScheduleDemo (
			@FormDataParam(REQUEST_PARAM_COMPLETE_UPDATED_RECORD) final String completeUpdatedRecord,
			@FormDataParam(REQUEST_PARAM_PARENT_ID) final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_RE_SCHEDULE_DEMO;
		createDemoReSchdeuleDemoObjectFromCompleteUpdatedRecordJSONObject(JSONUtils.getJSONObjectFromString(completeUpdatedRecord));
		try {
			this.parentId = Long.parseLong(parentId);
		} catch(NumberFormatException e) {}
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			this.demoTrackerObject.setDemoTrackerId(Long.parseLong(parentId));
			getDemoService().reScheduleDemo(this.demoTrackerObject, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "Re-Scheduled Demo Successfully");
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_UPDATE_DEMO_RECORD)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateDemoRecord (
			@FormDataParam(REQUEST_PARAM_COMPLETE_UPDATED_RECORD) final String completeUpdatedRecord,
			@FormDataParam(REQUEST_PARAM_PARENT_ID) final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UPDATE_DEMO_RECORD;
		createDemoObjectFromCompleteUpdatedRecordJSONObject(JSONUtils.getJSONObjectFromString(completeUpdatedRecord));
		try {
			this.parentId = Long.parseLong(parentId);
		} catch(NumberFormatException e) {}
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			this.demoTrackerObject.setDemoTrackerId(Long.parseLong(parentId));
			getDemoService().updateDemoRecord(this.demoTrackerObject, this.changedAttributes, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, MESSAGE_UPDATED_RECORD);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	

	public AdminService getAdminService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ADMIN_SERVICE, AdminService.class);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	public EnquiryService getEnquiryService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ENQUIRY_SERVICE, EnquiryService.class);
	}
	
	public DemoService getDemoService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_DEMO_SERVICE, DemoService.class);
	}
	
	public TutorService getTutorService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_TUTOR_SERVICE, TutorService.class);
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
			case REST_METHOD_NAME_PENDING_ENQUIRIES_LIST :
			case REST_METHOD_NAME_COMPLETED_ENQUIRIES_LIST :
			case REST_METHOD_NAME_ABORTED_ENQUIRIES_LIST :
			case REST_METHOD_NAME_TO_BE_MAPPED_ENQUIRIES_GRID_LIST :
			case REST_METHOD_NAME_PENDING_MAPPED_TUTORS_LIST : 
			case REST_METHOD_NAME_DEMO_READY_MAPPED_TUTORS_LIST : 
			case REST_METHOD_NAME_DEMO_SCHEDULED_MAPPED_TUTORS_LIST : 
			case REST_METHOD_NAME_SCHEDULED_DEMO_LIST : 
			case REST_METHOD_NAME_RESCHEDULED_DEMO_LIST : 
			case REST_METHOD_NAME_SUCCESSFUL_DEMO_LIST : 
			case REST_METHOD_NAME_FAILED_DEMO_LIST : 
			case REST_METHOD_NAME_CANCELED_DEMO_LIST : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_CURRENT_CUSTOMER_ALL_PENDING_ENQUIRIES_LIST :{
				handleSelectedCustomerDataGridView();
				break;
			}
			case REST_METHOD_NAME_ALL_MAPPING_ELIGIBLE_TUTORS_LIST : 
			case REST_METHOD_NAME_CURRENT_ENQUIRY_ALL_MAPPED_TUTORS_LIST : {
				handleSelectedEnquiryDataGridView();
				break;
			}
			case REST_METHOD_NAME_CURRENT_TUTOR_MAPPING_LIST : 
			case REST_METHOD_NAME_CURRENT_TUTOR_SCHEDULED_DEMO_LIST : {
				handleSelectedTutorDataGridView();
				break;
			}
			case REST_METHOD_NAME_CURRENT_CUSTOMER_MAPPING_LIST : 
			case REST_METHOD_NAME_CURRENT_CUSTOMER_SCHEDULED_DEMO_LIST : {
				handleSelectedCustomerDataGridView();
				break;
			}
			case REST_METHOD_NAME_UPDATE_ENQUIRY_RECORD : {
				handleParentId();
				handleEnquirySecurity();
				break;
			}
			case REST_METHOD_NAME_UPDATE_TUTOR_MAPPER_RECORD : {
				handleParentId();
				handleTutorMapperSecurity();
				break;
			}
			case REST_METHOD_NAME_TAKE_ACTION_ON_ENQUIRY : 
			case REST_METHOD_NAME_TAKE_ACTION_ON_MAPPED_TUTOR : 
			case REST_METHOD_NAME_TAKE_ACTION_ON_DEMO : {
				handleTakeAction();
				break;
			}
			case REST_METHOD_NAME_MAP_REGISTERED_TUTORS : {
				handleParentId();
				handleAllIds();
				break;
			}
			case REST_METHOD_NAME_UN_MAP_REGISTERED_TUTORS : {
				handleAllIds();
				break;
			}
			case REST_METHOD_NAME_SCHEDULE_DEMO : {
				handleParentId();
				handleScheduleDemoSecurity();
				break;
			}
			case REST_METHOD_NAME_RE_SCHEDULE_DEMO : {
				handleParentId();
				handleReScheduleDemoSecurity();
				break;
			}
			case REST_METHOD_NAME_UPDATE_DEMO_RECORD : {
				handleParentId();
				handleDemoSecurity();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
	
	public void handleTakeAction() throws Exception {
		this.securityPassed = true;
		super.handleTakeAction();
		if (this.securityPassed) {
			switch(button) {
				case BUTTON_ACTION_TO_BE_MAPPED : 
				case BUTTON_ACTION_DEMO_READY : {
					break;
				}
				case BUTTON_ACTION_ABORTED : 
				case BUTTON_ACTION_PENDING : 
				case BUTTON_ACTION_CANCEL : {
					handleComments();
					break;
				}
				default : {
					ApplicationUtils.appendMessageInMapAttribute(
							this.securityFailureResponse, 
							AdminConstants.VALIDATION_MESSAGE_BUTTON_UNKNOWN,
							RESPONSE_MAP_ATTRIBUTE_MESSAGE);
					this.securityPassed = false;
					break;
				}
			}
		}
	}
	
	private void createEnquiryObjectFromCompleteUpdatedRecordJSONObject(final JsonObject jsonObject) {
		if (ValidationUtils.checkObjectAvailability(jsonObject)) {
			this.enquiryObject = new Enquiry();
			this.enquiryObject.setSubject(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "subject", String.class));
			this.enquiryObject.setGrade(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "grade", String.class));
			this.enquiryObject.setQuotedClientRate(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "quotedClientRate", Integer.class));
			this.enquiryObject.setNegotiatedRateWithClient(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "negotiatedRateWithClient", Integer.class));
			this.enquiryObject.setClientNegotiationRemarks(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "clientNegotiationRemarks", String.class));
			this.enquiryObject.setAdditionalDetails(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "additionalDetails", String.class));
			this.enquiryObject.setLocationDetails(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "locationDetails", String.class));
			this.enquiryObject.setAddressDetails(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "addressDetails", String.class));
			this.enquiryObject.setPreferredTeachingType(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "preferredTeachingType", String.class));
		}
	}
	
	private void handleEnquirySecurity() throws Exception {
		this.securityPassed = true;
		if (ValidationUtils.checkNonEmptyList(this.changedAttributes)) {
			for (final String attributeName : this.changedAttributes) {
				switch(attributeName) {
					case "subject" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.enquiryObject.getSubject(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please select valid 'Subject'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "grade" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.enquiryObject.getGrade(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please select a valid 'Grade'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "quotedClientRate" : {
						if (!ValidationUtils.validateNumber(this.enquiryObject.getQuotedClientRate(), false, 0, false, 0)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please enter a valid 'Quoted Client Rate'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "negotiatedRateWithClient" : {
						if (!ValidationUtils.validateNumber(this.enquiryObject.getNegotiatedRateWithClient(), false, 0, false, 0)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please enter a valid 'Negotiated Rate With Client'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else if (ValidationUtils.checkObjectAvailability(this.enquiryObject.getNegotiatedRateWithClient())) {
							if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.enquiryObject.getClientNegotiationRemarks())) {
								ApplicationUtils.appendMessageInMapAttribute(
										this.securityFailureResponse, 
										"Please provide 'Client Negotiation Remarks'",
										RESPONSE_MAP_ATTRIBUTE_MESSAGE);
								this.securityPassed = false;
							}
						}
						break;
					}
					case "clientNegotiationRemarks" : {
						break;
					}
					case "additionalDetails" : {
						break;
					}
					case "locationDetails" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.enquiryObject.getLocationDetails(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please select valid 'Location'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "addressDetails" : {
						if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.enquiryObject.getAddressDetails())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please provide 'Address Details'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "preferredTeachingType" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.enquiryObject.getPreferredTeachingType(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please select valid multiple 'Preferred Tutoring Type'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					default : {
						ApplicationUtils.appendMessageInMapAttribute(
								this.securityFailureResponse, 
								AdminConstants.VALIDATION_MESSAGE_UNKONWN_PROPERTY,
								RESPONSE_MAP_ATTRIBUTE_MESSAGE);
						this.securityPassed = false;
						break;
					}
				}
			}
		} else {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_NO_ATTRIBUTES_CHANGED,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	private void createTutorMapperObjectFromCompleteUpdatedRecordJSONObject(final JsonObject jsonObject) {
		if (ValidationUtils.checkObjectAvailability(jsonObject)) {
			this.tutorMapperObject = new TutorMapper();
			this.tutorMapperObject.setQuotedTutorRate(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "quotedTutorRate", Integer.class));
			this.tutorMapperObject.setNegotiatedRateWithTutor(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "negotiatedRateWithTutor", Integer.class));
			this.tutorMapperObject.setTutorNegotiationRemarks(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "tutorNegotiationRemarks", String.class));
			this.tutorMapperObject.setIsTutorAgreed(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "isTutorAgreed", String.class));
			this.tutorMapperObject.setIsTutorRejectionValid(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "isTutorRejectionValid", String.class));
			this.tutorMapperObject.setTutorResponse(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "tutorResponse", String.class));
			this.tutorMapperObject.setAdminTutorRejectionValidityResponse(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "adminTutorRejectionValidityResponse", String.class));
			this.tutorMapperObject.setAdminRemarksForTutor(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "adminRemarksForTutor", String.class));
			this.tutorMapperObject.setIsClientAgreed(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "isClientAgreed", String.class));
			this.tutorMapperObject.setIsClientRejectionValid(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "isClientRejectionValid", String.class));
			this.tutorMapperObject.setClientResponse(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "clientResponse", String.class));
			this.tutorMapperObject.setAdminClientRejectionValidityResponse(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "adminClientRejectionValidityResponse", String.class));
			this.tutorMapperObject.setAdminRemarksForClient(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "adminRemarksForClient", String.class));
			this.tutorMapperObject.setAdminActionRemarks(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "adminActionRemarks", String.class));
		}
	}
	
	private void handleTutorMapperSecurity() throws Exception {
		this.securityPassed = true;
		if (ValidationUtils.checkNonEmptyList(this.changedAttributes)) {
			for (final String attributeName : this.changedAttributes) {
				switch(attributeName) {
					case "quotedTutorRate" : {
						if (!ValidationUtils.validateNumber(this.tutorMapperObject.getQuotedTutorRate(), false, 0, false, 0)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please enter a valid 'Quoted Tutor Rate'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "negotiatedRateWithTutor" : {
						if (!ValidationUtils.validateNumber(this.tutorMapperObject.getNegotiatedRateWithTutor(), false, 0, false, 0)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please enter a valid 'Negotiated Rate With Tutor'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else if (ValidationUtils.checkObjectAvailability(this.tutorMapperObject.getNegotiatedRateWithTutor())) {
							if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.tutorMapperObject.getTutorNegotiationRemarks())) {
								ApplicationUtils.appendMessageInMapAttribute(
										this.securityFailureResponse, 
										"Please provide 'Tutor Negotiation Remarks'",
										RESPONSE_MAP_ATTRIBUTE_MESSAGE);
								this.securityPassed = false;
							}
						}
						break;
					}
					case "tutorNegotiationRemarks" : {
						break;
					}
					case "isTutorAgreed" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.tutorMapperObject.getIsTutorAgreed(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please select a valid 'Is Tutor Agreed'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else {
							if (NO.equals((this.tutorMapperObject.getIsTutorAgreed()))) {
								if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.tutorMapperObject.getTutorResponse())) {
									ApplicationUtils.appendMessageInMapAttribute(
											this.securityFailureResponse, 
											"Please provide 'Tutor Response'",
											RESPONSE_MAP_ATTRIBUTE_MESSAGE);
									this.securityPassed = false;
								}
								if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.tutorMapperObject.getAdminRemarksForTutor())) {
									ApplicationUtils.appendMessageInMapAttribute(
											this.securityFailureResponse, 
											"Please provide 'Admin Remarks For Tutor'",
											RESPONSE_MAP_ATTRIBUTE_MESSAGE);
									this.securityPassed = false;
								}
								if (!ValidationUtils.validateAgainstSelectLookupValues(this.tutorMapperObject.getIsTutorRejectionValid(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP)) {
									ApplicationUtils.appendMessageInMapAttribute(
											this.securityFailureResponse, 
											"Please select a valid 'Is Tutor Rejection Valid'",
											RESPONSE_MAP_ATTRIBUTE_MESSAGE);
									this.securityPassed = false;
								} else {
									if (NO.equals((this.tutorMapperObject.getIsTutorRejectionValid()))) {
										if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.tutorMapperObject.getAdminTutorRejectionValidityResponse())) {
											ApplicationUtils.appendMessageInMapAttribute(
													this.securityFailureResponse, 
													"Please provide 'Admin Tutor Rejection Validity Response'",
													RESPONSE_MAP_ATTRIBUTE_MESSAGE);
											this.securityPassed = false;
										}
									}
								}
							}
						}
						break;
					}
					case "tutorResponse" : {
						break;
					}
					case "adminRemarksForTutor" : {
						break;
					}
					case "isTutorRejectionValid" : {
						break;
					}
					case "adminTutorRejectionValidityResponse" : {
						break;
					}
					case "isClientAgreed" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.tutorMapperObject.getIsClientAgreed(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please select a valid 'Is Client Agreed'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else {
							if (NO.equals((this.tutorMapperObject.getIsClientAgreed()))) {
								if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.tutorMapperObject.getClientResponse())) {
									ApplicationUtils.appendMessageInMapAttribute(
											this.securityFailureResponse, 
											"Please provide 'Client Response'",
											RESPONSE_MAP_ATTRIBUTE_MESSAGE);
									this.securityPassed = false;
								}
								if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.tutorMapperObject.getAdminRemarksForClient())) {
									ApplicationUtils.appendMessageInMapAttribute(
											this.securityFailureResponse, 
											"Please provide 'Admin Remarks For Client'",
											RESPONSE_MAP_ATTRIBUTE_MESSAGE);
									this.securityPassed = false;
								}
								if (!ValidationUtils.validateAgainstSelectLookupValues(this.tutorMapperObject.getIsClientRejectionValid(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP)) {
									ApplicationUtils.appendMessageInMapAttribute(
											this.securityFailureResponse, 
											"Please select a valid 'Is Client Rejection Valid'",
											RESPONSE_MAP_ATTRIBUTE_MESSAGE);
									this.securityPassed = false;
								} else {
									if (NO.equals((this.tutorMapperObject.getIsTutorRejectionValid()))) {
										if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.tutorMapperObject.getAdminClientRejectionValidityResponse())) {
											ApplicationUtils.appendMessageInMapAttribute(
													this.securityFailureResponse, 
													"Please provide 'Admin Client Rejection Validity Response'",
													RESPONSE_MAP_ATTRIBUTE_MESSAGE);
											this.securityPassed = false;
										}
									}
								}
							}
						}
						break;
					}
					case "clientResponse" : {
						break;
					}
					case "adminRemarksForClient" : {
						break;
					}
					case "isClientRejectionValid" : {
						break;
					}
					case "adminClientRejectionValidityResponse" : {
						break;
					}
					case "adminActionRemarks" : {
						if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.tutorMapperObject.getAdminActionRemarks())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please provide 'Admin Action Remarks'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					default : {
						ApplicationUtils.appendMessageInMapAttribute(
								this.securityFailureResponse, 
								AdminConstants.VALIDATION_MESSAGE_UNKONWN_PROPERTY,
								RESPONSE_MAP_ATTRIBUTE_MESSAGE);
						this.securityPassed = false;
						break;
					}
				}
			}
		} else {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_NO_ATTRIBUTES_CHANGED,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	private void createTutorMapperSchdeuleDemoObjectFromCompleteUpdatedRecordJSONObject(final JsonObject jsonObject) {
		if (ValidationUtils.checkObjectAvailability(jsonObject)) {
			this.tutorMapperObject = new TutorMapper();
			final Long demoDateMillis = getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "demoDateMillis", Long.class);
			final Long demoTimeMillis = getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "demoTimeMillis", Long.class);
			final Long localTimezoneOffsetInMilliseconds = getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "localTimezoneOffsetInMilliseconds", Long.class);
			if (ValidationUtils.checkObjectAvailability(demoDateMillis) && ValidationUtils.checkObjectAvailability(demoTimeMillis) && ValidationUtils.checkObjectAvailability(localTimezoneOffsetInMilliseconds)) {
				this.tutorMapperObject.setDemoDateAndTimeMillis(demoDateMillis + demoTimeMillis + localTimezoneOffsetInMilliseconds);
			}
		}
	}
	
	private void handleScheduleDemoSecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.tutorMapperObject.getDemoDateAndTimeMillis())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					"Please select a valid 'Demo Date' & 'Demo Time'",
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	private void createDemoReSchdeuleDemoObjectFromCompleteUpdatedRecordJSONObject(final JsonObject jsonObject) {
		if (ValidationUtils.checkObjectAvailability(jsonObject)) {
			this.demoTrackerObject = new DemoTracker();
			final Long demoDateMillis = getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "demoDateMillis", Long.class);
			final Long demoTimeMillis = getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "demoTimeMillis", Long.class);
			final Long localTimezoneOffsetInMilliseconds = getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "localTimezoneOffsetInMilliseconds", Long.class);
			if (ValidationUtils.checkObjectAvailability(demoDateMillis) && ValidationUtils.checkObjectAvailability(demoTimeMillis) && ValidationUtils.checkObjectAvailability(localTimezoneOffsetInMilliseconds)) {
				this.demoTrackerObject.setDemoDateAndTimeMillis(demoDateMillis + demoTimeMillis + localTimezoneOffsetInMilliseconds);
			}
			this.demoTrackerObject.setTutorMapperId(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "tutorMapperId", Long.class));
			this.demoTrackerObject.setReschedulingRemarks(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "reschedulingRemarks", String.class));
			this.demoTrackerObject.setReScheduleCount(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "reScheduleCount", Integer.class));
		}
	}
	
	private void handleReScheduleDemoSecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.demoTrackerObject.getDemoDateAndTimeMillis())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					"Please select a valid 'New Demo Date' & 'New Demo Time'",
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.checkObjectAvailability(this.demoTrackerObject.getTutorMapperId())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					"Request does not contains a 'TutorMapperId' hence cannot perform action",
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.demoTrackerObject.getReschedulingRemarks())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					"Please provide 'Rescheduling Remarks'",
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.checkObjectAvailability(this.demoTrackerObject.getReScheduleCount())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					"Request does not contains a 'ReScheduleCount' hence cannot perform action",
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	private void createDemoObjectFromCompleteUpdatedRecordJSONObject(final JsonObject jsonObject) {
		if (ValidationUtils.checkObjectAvailability(jsonObject)) {
			this.demoTrackerObject = new DemoTracker();
			this.demoTrackerObject.setDemoOccurred(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "demoOccurred", String.class));
			this.demoTrackerObject.setClientSatisfiedFromTutor(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "clientSatisfiedFromTutor", String.class));
			this.demoTrackerObject.setClientRemarks(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "clientRemarks", String.class));
			this.demoTrackerObject.setTutorSatisfiedWithClient(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "tutorSatisfiedWithClient", String.class));
			this.demoTrackerObject.setTutorRemarks(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "tutorRemarks", String.class));
			this.demoTrackerObject.setAdminSatisfiedFromTutor(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "adminSatisfiedFromTutor", String.class));
			this.demoTrackerObject.setAdminSatisfiedWithClient(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "adminSatisfiedWithClient", String.class));
			this.demoTrackerObject.setIsDemoSuccess(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "isDemoSuccess", String.class));
			this.demoTrackerObject.setNeedPriceNegotiationWithClient(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "needPriceNegotiationWithClient", String.class));
			this.demoTrackerObject.setNegotiatedOverrideRateWithClient(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "negotiatedOverrideRateWithClient", Integer.class));
			this.demoTrackerObject.setClientNegotiationRemarks(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "clientNegotiationRemarks", String.class));
			this.demoTrackerObject.setNeedPriceNegotiationWithTutor(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "needPriceNegotiationWithTutor", String.class));
			this.demoTrackerObject.setNegotiatedOverrideRateWithTutor(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "negotiatedOverrideRateWithTutor", Integer.class));
			this.demoTrackerObject.setTutorNegotiationRemarks(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "tutorNegotiationRemarks", String.class));
			this.demoTrackerObject.setAdminRemarks(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "adminRemarks", String.class));
			this.demoTrackerObject.setAdminFinalizingRemarks(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "adminFinalizingRemarks", String.class));
		}
	}
	
	private void handleDemoSecurity() throws Exception {
		this.securityPassed = true;
		if (ValidationUtils.checkNonEmptyList(this.changedAttributes)) {
			for (final String attributeName : this.changedAttributes) {
				switch(attributeName) {
					case "demoOccurred" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.demoTrackerObject.getDemoOccurred(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please select a valid 'Is Demo Occurred'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "clientSatisfiedFromTutor" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.demoTrackerObject.getClientSatisfiedFromTutor(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please select a valid 'Is Client Satisfied From Tutor'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else {
							if (NO.equals((this.demoTrackerObject.getClientSatisfiedFromTutor()))) {
								if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.demoTrackerObject.getClientRemarks())) {
									ApplicationUtils.appendMessageInMapAttribute(
											this.securityFailureResponse, 
											"Please provide 'Client Remarks'",
											RESPONSE_MAP_ATTRIBUTE_MESSAGE);
									this.securityPassed = false;
								}
							}
						}
						break;
					}
					case "clientRemarks" : {
						break;
					}
					case "tutorSatisfiedWithClient" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.demoTrackerObject.getTutorSatisfiedWithClient(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please select a valid 'Is Tutor Satisfied With Client'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else {
							if (NO.equals((this.demoTrackerObject.getClientSatisfiedFromTutor()))) {
								if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.demoTrackerObject.getTutorRemarks())) {
									ApplicationUtils.appendMessageInMapAttribute(
											this.securityFailureResponse, 
											"Please provide 'Tutor Remarks'",
											RESPONSE_MAP_ATTRIBUTE_MESSAGE);
									this.securityPassed = false;
								}
							}
						}
						break;
					}
					case "tutorRemarks" : {
						break;
					}
					case "adminSatisfiedFromTutor" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.demoTrackerObject.getAdminSatisfiedFromTutor(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please select a valid 'Is Admin Satisfied From Tutor'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else {
							if (NO.equals((this.demoTrackerObject.getClientSatisfiedFromTutor()))) {
								if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.demoTrackerObject.getAdminRemarks())) {
									ApplicationUtils.appendMessageInMapAttribute(
											this.securityFailureResponse, 
											"Please provide 'Admin Remarks'",
											RESPONSE_MAP_ATTRIBUTE_MESSAGE);
									this.securityPassed = false;
								}
							}
						}
						break;
					}
					case "adminSatisfiedWithClient" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.demoTrackerObject.getAdminSatisfiedWithClient(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please select a valid 'Is Admin Satisfied With Client'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else {
							if (NO.equals((this.demoTrackerObject.getClientSatisfiedFromTutor()))) {
								if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.demoTrackerObject.getAdminRemarks())) {
									ApplicationUtils.appendMessageInMapAttribute(
											this.securityFailureResponse, 
											"Please provide 'Admin Remarks'",
											RESPONSE_MAP_ATTRIBUTE_MESSAGE);
									this.securityPassed = false;
								}
							}
						}
						break;
					}
					case "isDemoSuccess" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.demoTrackerObject.getIsDemoSuccess(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please select a valid 'Is Admin Satisfied With Client'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "needPriceNegotiationWithClient" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.demoTrackerObject.getNeedPriceNegotiationWithClient(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please select a valid 'Need Price Negotiation With Client'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else {
							if (YES.equals((this.demoTrackerObject.getNeedPriceNegotiationWithClient()))) {
								if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.demoTrackerObject.getClientNegotiationRemarks())) {
									ApplicationUtils.appendMessageInMapAttribute(
											this.securityFailureResponse, 
											"Please provide 'Client Negotiation Remarks'",
											RESPONSE_MAP_ATTRIBUTE_MESSAGE);
									this.securityPassed = false;
								}
							}
						}
						break;
					}
					case "negotiatedOverrideRateWithClient" : {
						if (!ValidationUtils.validateNumber(this.demoTrackerObject.getNegotiatedOverrideRateWithClient(), false, 0, false, 0)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please enter a valid 'Negotiated Override Rate With Client'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else {
							if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.demoTrackerObject.getClientNegotiationRemarks())) {
								ApplicationUtils.appendMessageInMapAttribute(
										this.securityFailureResponse, 
										"Please provide 'Client Negotiation Remarks'",
										RESPONSE_MAP_ATTRIBUTE_MESSAGE);
								this.securityPassed = false;
							}
						}
						break;
					}
					case "clientNegotiationRemarks" : {
						break;
					}
					case "needPriceNegotiationWithTutor" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.demoTrackerObject.getNeedPriceNegotiationWithTutor(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please select a valid 'Need Price Negotiation With Tutor'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else {
							if (YES.equals((this.demoTrackerObject.getNeedPriceNegotiationWithClient()))) {
								if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.demoTrackerObject.getTutorNegotiationRemarks())) {
									ApplicationUtils.appendMessageInMapAttribute(
											this.securityFailureResponse, 
											"Please provide 'Tutor Negotiation Remarks'",
											RESPONSE_MAP_ATTRIBUTE_MESSAGE);
									this.securityPassed = false;
								}
							}
						}
						break;
					}
					case "negotiatedOverrideRateWithTutor" : {
						if (!ValidationUtils.validateNumber(this.demoTrackerObject.getNegotiatedOverrideRateWithTutor(), false, 0, false, 0)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please enter a valid 'Negotiated Override Rate With Tutor'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else {
							if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.demoTrackerObject.getTutorNegotiationRemarks())) {
								ApplicationUtils.appendMessageInMapAttribute(
										this.securityFailureResponse, 
										"Please provide 'Tutor Negotiation Remarks'",
										RESPONSE_MAP_ATTRIBUTE_MESSAGE);
								this.securityPassed = false;
							}
						}
						break;
					}
					case "tutorNegotiationRemarks" : {
						break;
					}
					case "adminRemarks" : {
						if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.demoTrackerObject.getAdminRemarks())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please provide 'Admin Remarks'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "adminFinalizingRemarks" : {
						if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.demoTrackerObject.getAdminFinalizingRemarks())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									"Please provide 'Admin Finalizing Remarks'",
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					default : {
						ApplicationUtils.appendMessageInMapAttribute(
								this.securityFailureResponse, 
								AdminConstants.VALIDATION_MESSAGE_UNKONWN_PROPERTY,
								RESPONSE_MAP_ATTRIBUTE_MESSAGE);
						this.securityPassed = false;
						break;
					}
				}
			}
		} else {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_NO_ATTRIBUTES_CHANGED,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	private void handleSelectedCustomerDataGridView() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.customerId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					CustomerConstants.VALIDATION_MESSAGE_CUSTOMER_ID_ABSENT,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	} 
	
	private void handleSelectedEnquiryDataGridView() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.enquiryId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					EnquiryConstants.VALIDATION_MESSAGE_ENQUIRY_ID_ABSENT,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	} 
	
	private void handleSelectedTutorDataGridView() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.tutorId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					TutorConstants.VALIDATION_MESSAGE_TUTOR_ID_ABSENT,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	} 
}
