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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.FileConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.AdminConstants;
import com.model.components.Complaint;
import com.model.components.TutorDocument;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.components.publicaccess.SubmitQuery;
import com.model.components.publicaccess.SubscribeWithUs;
import com.model.gridcomponent.GridComponent;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.components.AdminService;
import com.service.components.CommonsService;
import com.utils.ApplicationUtils;
import com.utils.FileUtils;
import com.utils.GridComponentUtils;
import com.utils.JSONUtils;
import com.utils.ValidationUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_SUPPORT) 
public class SupportRestService extends AbstractRestWebservice implements RestMethodConstants {
	
	private String allIdsList;
	private String comments;
	private String grid;
	private String button;
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_BECOME_TUTOR_LIST)
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadAdminReportBecomeTutorList (
    		@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_BECOME_TUTOR_LIST;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, TutorDocument.class);
		grid = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "grid", String.class);
		doSecurity(request);
		if (this.securityPassed) {
			FileUtils.writeFileToResponse(response, "Admin_Tutor_Registration_Report" + PERIOD + FileConstants.EXTENSION_XLSX, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getAdminService().downloadAdminReportBecomeTutorList(grid, gridComponent));
		}
    }
	
	@Path(REST_METHOD_NAME_NON_CONTACTED_BECOME_TUTORS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String nonContactedBecomeTutorsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_NON_CONTACTED_BECOME_TUTORS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, BecomeTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<BecomeTutor> becomeTutorsList = getAdminService().getBecomeTutorList(REST_METHOD_NAME_NON_CONTACTED_BECOME_TUTORS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, becomeTutorsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(becomeTutorsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_NON_VERIFIED_BECOME_TUTORS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String nonVerifiedBecomeTutorsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_NON_VERIFIED_BECOME_TUTORS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, BecomeTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<BecomeTutor> becomeTutorsList = getAdminService().getBecomeTutorList(REST_METHOD_NAME_NON_VERIFIED_BECOME_TUTORS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, becomeTutorsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(becomeTutorsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_VERIFIED_BECOME_TUTORS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String verifiedBecomeTutorsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_VERIFIED_BECOME_TUTORS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, BecomeTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<BecomeTutor> becomeTutorsList = getAdminService().getBecomeTutorList(REST_METHOD_NAME_VERIFIED_BECOME_TUTORS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, becomeTutorsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(becomeTutorsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_VERIFICATION_FAILED_BECOME_TUTORS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String verificationFailedBecomeTutorsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_VERIFICATION_FAILED_BECOME_TUTORS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, BecomeTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<BecomeTutor> becomeTutorsList = getAdminService().getBecomeTutorList(REST_METHOD_NAME_VERIFICATION_FAILED_BECOME_TUTORS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, becomeTutorsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(becomeTutorsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_BE_RECONTACTED_BECOME_TUTORS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String toBeReContactedBecomeTutorsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_BE_RECONTACTED_BECOME_TUTORS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, BecomeTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<BecomeTutor> becomeTutorsList = getAdminService().getBecomeTutorList(REST_METHOD_NAME_TO_BE_RECONTACTED_BECOME_TUTORS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, becomeTutorsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(becomeTutorsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_SELECTED_BECOME_TUTORS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String selectedBecomeTutorsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_SELECTED_BECOME_TUTORS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, BecomeTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<BecomeTutor> becomeTutorsList = getAdminService().getBecomeTutorList(REST_METHOD_NAME_SELECTED_BECOME_TUTORS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, becomeTutorsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(becomeTutorsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_REJECTED_BECOME_TUTORS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String rejectedBecomeTutorsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_REJECTED_BECOME_TUTORS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, BecomeTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<BecomeTutor> becomeTutorsList = getAdminService().getBecomeTutorList(REST_METHOD_NAME_REJECTED_BECOME_TUTORS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, becomeTutorsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(becomeTutorsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_REGISTERED_BECOME_TUTORS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String registeredBecomeTutorsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_REGISTERED_BECOME_TUTORS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, BecomeTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<BecomeTutor> becomeTutorsList = getAdminService().getBecomeTutorList(REST_METHOD_NAME_REGISTERED_BECOME_TUTORS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, becomeTutorsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(becomeTutorsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/becomeTutorCheckDataAccess")
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String becomeTutorCheckDataAccess (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("formDataEditAccess", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_BLACKLIST_BECOME_TUTOR_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String blacklistBecomeTutorList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_BLACKLIST_BECOME_TUTOR_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().blacklistBecomeTutorList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_UN_BLACKLIST_BECOME_TUTOR_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String unBlacklistBecomeTutorList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UN_BLACKLIST_BECOME_TUTOR_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().unBlacklistBecomeTutorList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TAKE_ACTION_ON_BECOME_TUTOR)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String takeActionOnBecomeTutor (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam("button") final String button,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TAKE_ACTION_ON_BECOME_TUTOR;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.button = button;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().takeActionOnBecomeTutor(button, Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/updateBecomeTutorRecord")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateBecomeTutorRecord (
			@FormDataParam("completeUpdatedRecord") final String completeUpdatedRecord,
			@FormDataParam("parentId") final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "Record Updated "+completeUpdatedRecord);		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_NON_CONTACTED_ENQUIRIES_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String nonContactedEnquiriesList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_NON_CONTACTED_ENQUIRIES_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, FindTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<FindTutor> findTutorList = getAdminService().getEnquiryList(REST_METHOD_NAME_NON_CONTACTED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, findTutorList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(findTutorList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_NON_VERIFIED_ENQUIRIES_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String nonVerifiedEnquiriesList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_NON_VERIFIED_ENQUIRIES_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, FindTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<FindTutor> findTutorList = getAdminService().getEnquiryList(REST_METHOD_NAME_NON_VERIFIED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, findTutorList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(findTutorList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_VERIFIED_ENQUIRIES_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String verifiedEnquiriesList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_VERIFIED_ENQUIRIES_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, FindTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<FindTutor> findTutorList = getAdminService().getEnquiryList(REST_METHOD_NAME_VERIFIED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, findTutorList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(findTutorList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_VERIFICATION_FAILED_ENQUIRIES_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String verificationFailedEnquiriesList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_VERIFICATION_FAILED_ENQUIRIES_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, FindTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<FindTutor> findTutorList = getAdminService().getEnquiryList(REST_METHOD_NAME_VERIFICATION_FAILED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, findTutorList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(findTutorList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_BE_RECONTACTED_ENQUIRIES_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String toBeReContactedEnquiriesList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_BE_RECONTACTED_ENQUIRIES_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, FindTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<FindTutor> findTutorList = getAdminService().getEnquiryList(REST_METHOD_NAME_TO_BE_RECONTACTED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, findTutorList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(findTutorList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_SELECTED_ENQUIRIES_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String selectedEnquiriesList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_SELECTED_ENQUIRIES_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, FindTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<FindTutor> findTutorList = getAdminService().getEnquiryList(REST_METHOD_NAME_SELECTED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, findTutorList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(findTutorList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_REJECTED_ENQUIRIES_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String rejectedEnquiriesList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_REJECTED_ENQUIRIES_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, FindTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<FindTutor> findTutorList = getAdminService().getEnquiryList(REST_METHOD_NAME_REJECTED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, findTutorList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(findTutorList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/enquiryRequestCheckDataAccess")
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String enquiryRequestCheckDataAccess (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("formDataEditAccess", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_BLACKLIST_ENQUIRY_REQUEST_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String blacklistEnquiryRequestList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_BLACKLIST_ENQUIRY_REQUEST_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().blacklistFindTutorList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_UN_BLACKLIST_ENQUIRY_REQUEST_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String unBlacklistEnquiryRequestList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UN_BLACKLIST_ENQUIRY_REQUEST_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().unBlacklistFindTutorList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TAKE_ACTION_ON_FIND_TUTOR)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String takeActionOnFindTutor (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam("button") final String button,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TAKE_ACTION_ON_FIND_TUTOR;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.button = button;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().takeActionOnFindTutor(button, Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/updateEnquiryRequestRecord")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateenquiryRequestRecord (
			@FormDataParam("completeUpdatedRecord") final String completeUpdatedRecord,
			@FormDataParam("parentId") final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "Record Updated "+completeUpdatedRecord);		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_NON_CONTACTED_SUBSCRIPTIONS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String nonContactedSubscriptionsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_NON_CONTACTED_SUBSCRIPTIONS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribeWithUs.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscribeWithUs> subscriptionsList = getAdminService().getSubscriptionList(REST_METHOD_NAME_NON_CONTACTED_SUBSCRIPTIONS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}	
	
	@Path(REST_METHOD_NAME_NON_VERIFIED_SUBSCRIPTIONS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String nonVerifiedSubscriptionsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_NON_VERIFIED_SUBSCRIPTIONS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribeWithUs.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscribeWithUs> subscriptionsList = getAdminService().getSubscriptionList(REST_METHOD_NAME_NON_VERIFIED_SUBSCRIPTIONS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}	
	
	@Path(REST_METHOD_NAME_VERIFIED_SUBSCRIPTIONS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String verifiedSubscriptionsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_VERIFIED_SUBSCRIPTIONS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribeWithUs.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscribeWithUs> subscriptionsList = getAdminService().getSubscriptionList(REST_METHOD_NAME_VERIFIED_SUBSCRIPTIONS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}	
	
	@Path(REST_METHOD_NAME_VERIFICATION_FAILED_SUBSCRIPTIONS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String verificationFailedSubscriptionsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_VERIFICATION_FAILED_SUBSCRIPTIONS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribeWithUs.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscribeWithUs> subscriptionsList = getAdminService().getSubscriptionList(REST_METHOD_NAME_VERIFICATION_FAILED_SUBSCRIPTIONS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}	
	
	@Path(REST_METHOD_NAME_TO_BE_RECONTACTED_SUBSCRIPTIONS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String toBeReContactedSubscriptionsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_BE_RECONTACTED_SUBSCRIPTIONS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribeWithUs.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscribeWithUs> subscriptionsList = getAdminService().getSubscriptionList(REST_METHOD_NAME_TO_BE_RECONTACTED_SUBSCRIPTIONS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}	
	
	@Path(REST_METHOD_NAME_SELECTED_SUBSCRIPTIONS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String selectedSubscriptionsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_SELECTED_SUBSCRIPTIONS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribeWithUs.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscribeWithUs> subscriptionsList = getAdminService().getSubscriptionList(REST_METHOD_NAME_NON_CONTACTED_SUBSCRIPTIONS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}	
	
	@Path(REST_METHOD_NAME_REJECTED_SUBSCRIPTIONS_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String rejectedSubscriptionsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_REJECTED_SUBSCRIPTIONS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribeWithUs.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscribeWithUs> subscriptionsList = getAdminService().getSubscriptionList(REST_METHOD_NAME_REJECTED_SUBSCRIPTIONS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/subscriptionRequestCheckDataAccess")
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String subscriptionRequestCheckDataAccess (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("formDataEditAccess", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_BLACKLIST_SUBSCRIPTION_REQUEST_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String blacklistSubscriptionRequestList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_BLACKLIST_SUBSCRIPTION_REQUEST_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().blacklistSubscriptionList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_UN_BLACKLIST_SUBSCRIPTION_REQUEST_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String unBlacklistSubscriptionRequestList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UN_BLACKLIST_SUBSCRIPTION_REQUEST_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().unBlacklistSubscriptionList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TAKE_ACTION_ON_SUBSCRIPTION)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String takeActionOnSubscription (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam("button") final String button,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TAKE_ACTION_ON_SUBSCRIPTION;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.button = button;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().takeActionOnSubscription(button, Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/updateSubscriptionRequestRecord")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateSubscriptionRequestRecord (
			@FormDataParam("completeUpdatedRecord") final String completeUpdatedRecord,
			@FormDataParam("parentId") final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "Record Updated "+completeUpdatedRecord);		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_NON_CONTACTED_QUERY_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String nonContactedQueryList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_NON_CONTACTED_QUERY_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubmitQuery.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubmitQuery> queryList = getAdminService().getQueryList(REST_METHOD_NAME_NON_CONTACTED_QUERY_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, queryList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(queryList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_NON_ANSWERED_QUERY_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String nonAnsweredQueryList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_NON_ANSWERED_QUERY_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubmitQuery.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubmitQuery> queryList = getAdminService().getQueryList(REST_METHOD_NAME_NON_ANSWERED_QUERY_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, queryList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(queryList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_ANSWERED_QUERY_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String answeredQueryList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_ANSWERED_QUERY_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubmitQuery.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubmitQuery> queryList = getAdminService().getQueryList(REST_METHOD_NAME_ANSWERED_QUERY_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, queryList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(queryList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/submittedQueryCheckDataAccess")
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String submittedQueryCheckDataAccess (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("queryResponseCapableAccess", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/updateSubmittedQueryRecord")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateSubmittedQueryRecord (
			@FormDataParam("completeUpdatedRecord") final String completeUpdatedRecord,
			@FormDataParam("parentId") final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "Record Updated "+completeUpdatedRecord);		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_CUSTOMER_COMPLAINT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String customerComplaintList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_CUSTOMER_COMPLAINT_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, Complaint.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<Complaint> complaintList = getAdminService().getComplaintList(REST_METHOD_CUSTOMER_COMPLAINT_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, complaintList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(complaintList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_TUTOR_COMPLAINT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String tutorComplaintList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_TUTOR_COMPLAINT_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, Complaint.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<Complaint> complaintList = getAdminService().getComplaintList(REST_METHOD_TUTOR_COMPLAINT_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, complaintList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(complaintList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_EMPLOYEE_COMPLAINT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String employeeComplaintList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_EMPLOYEE_COMPLAINT_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, Complaint.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<Complaint> complaintList = getAdminService().getComplaintList(REST_METHOD_EMPLOYEE_COMPLAINT_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, complaintList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(complaintList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_RESOLVED_COMPLAINT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String resolvedComplaintList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_RESOLVED_COMPLAINT_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, Complaint.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<Complaint> complaintList = getAdminService().getComplaintList(REST_METHOD_RESOLVED_COMPLAINT_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, complaintList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(complaintList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/complaintCheckDataAccess")
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String complaintCheckDataAccess (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("complaintAddressCapableAccess", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/updateComplaintRecord")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateComplaintRecord (
			@FormDataParam("completeUpdatedRecord") final String completeUpdatedRecord,
			@FormDataParam("parentId") final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "Record Updated "+completeUpdatedRecord);		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	public AdminService getAdminService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ADMIN_SERVICE, AdminService.class);
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
			case REST_METHOD_NAME_NON_CONTACTED_BECOME_TUTORS_LIST : 
			case REST_METHOD_NAME_NON_VERIFIED_BECOME_TUTORS_LIST : 
			case REST_METHOD_NAME_VERIFIED_BECOME_TUTORS_LIST : 
			case REST_METHOD_NAME_VERIFICATION_FAILED_BECOME_TUTORS_LIST : 
			case REST_METHOD_NAME_TO_BE_RECONTACTED_BECOME_TUTORS_LIST : 
			case REST_METHOD_NAME_SELECTED_BECOME_TUTORS_LIST : 
			case REST_METHOD_NAME_REJECTED_BECOME_TUTORS_LIST : 
			case REST_METHOD_NAME_REGISTERED_BECOME_TUTORS_LIST : 
			case REST_METHOD_NAME_NON_CONTACTED_ENQUIRIES_LIST : 
			case REST_METHOD_NAME_NON_VERIFIED_ENQUIRIES_LIST : 
			case REST_METHOD_NAME_VERIFIED_ENQUIRIES_LIST : 
			case REST_METHOD_NAME_VERIFICATION_FAILED_ENQUIRIES_LIST : 
			case REST_METHOD_NAME_TO_BE_RECONTACTED_ENQUIRIES_LIST : 
			case REST_METHOD_NAME_SELECTED_ENQUIRIES_LIST : 
			case REST_METHOD_NAME_REJECTED_ENQUIRIES_LIST : 
			case REST_METHOD_NAME_NON_CONTACTED_SUBSCRIPTIONS_LIST : 
			case REST_METHOD_NAME_NON_VERIFIED_SUBSCRIPTIONS_LIST : 
			case REST_METHOD_NAME_VERIFIED_SUBSCRIPTIONS_LIST : 
			case REST_METHOD_NAME_VERIFICATION_FAILED_SUBSCRIPTIONS_LIST : 
			case REST_METHOD_NAME_TO_BE_RECONTACTED_SUBSCRIPTIONS_LIST : 
			case REST_METHOD_NAME_SELECTED_SUBSCRIPTIONS_LIST : 
			case REST_METHOD_NAME_REJECTED_SUBSCRIPTIONS_LIST :
			case REST_METHOD_NAME_NON_CONTACTED_QUERY_LIST : 
			case REST_METHOD_NAME_NON_ANSWERED_QUERY_LIST : 
			case REST_METHOD_NAME_ANSWERED_QUERY_LIST :
			case REST_METHOD_CUSTOMER_COMPLAINT_LIST :
			case REST_METHOD_TUTOR_COMPLAINT_LIST : 
			case REST_METHOD_EMPLOYEE_COMPLAINT_LIST : 
			case REST_METHOD_RESOLVED_COMPLAINT_LIST :{
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_BLACKLIST_BECOME_TUTOR_LIST : 
			case REST_METHOD_NAME_UN_BLACKLIST_BECOME_TUTOR_LIST :
			case REST_METHOD_NAME_BLACKLIST_ENQUIRY_REQUEST_LIST : 
			case REST_METHOD_NAME_UN_BLACKLIST_ENQUIRY_REQUEST_LIST : 
			case REST_METHOD_NAME_BLACKLIST_SUBSCRIPTION_REQUEST_LIST : 
			case REST_METHOD_NAME_UN_BLACKLIST_SUBSCRIPTION_REQUEST_LIST : {
				handleAllIds();
				handleComments();
				break;
			}
			case REST_METHOD_NAME_TAKE_ACTION_ON_BECOME_TUTOR : 
			case REST_METHOD_NAME_TAKE_ACTION_ON_FIND_TUTOR : 
			case REST_METHOD_NAME_TAKE_ACTION_ON_SUBSCRIPTION : {
				handleTakeAction();
				break;
			}
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_BECOME_TUTOR_LIST : {
				handleDownload();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
	
	private void handleTakeAction() throws Exception {
		this.securityPassed = true;
		handleAllIds();
		if (!ValidationUtils.checkStringAvailability(this.button)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_BUTTON_ABSENT,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		} else {
			switch(button) {
				case AdminConstants.BUTTON_ACTION_CONTACTED : 
				case AdminConstants.BUTTON_ACTION_VERIFY:
				case AdminConstants.BUTTON_ACTION_REVERIFY : 
				case AdminConstants.BUTTON_ACTION_SELECT : 
				case AdminConstants.BUTTON_ACTION_RECONTACTED : {
					break;
				}
				case AdminConstants.BUTTON_ACTION_RECONTACT : 
				case AdminConstants.BUTTON_ACTION_REJECT : 
				case AdminConstants.BUTTON_ACTION_FAIL_VERIFY : {
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
	
	private void handleAllIds() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkStringAvailability(this.allIdsList) || !ValidationUtils.checkNonEmptyList(Arrays.asList(this.allIdsList.split(SEMICOLON)))) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_ID_ABSENT,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	} 
	
	private void handleComments() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkStringAvailability(this.comments)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_COMMENTS_ABSENT,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	} 
	
	private void handleDownload() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkStringAvailability(this.grid)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_GRID_ABSENT,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
}
