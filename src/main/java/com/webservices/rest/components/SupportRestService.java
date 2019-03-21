package com.webservices.rest.components;

import java.util.Arrays;
import java.util.Date;
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
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.FileConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.AdminConstants;
import com.constants.components.CommonsConstants;
import com.constants.components.SelectLookupConstants;
import com.constants.components.SupportConstants;
import com.constants.components.publicaccess.BecomeTutorConstants;
import com.constants.components.publicaccess.FindTutorConstants;
import com.constants.components.publicaccess.PublicAccessConstants;
import com.constants.components.publicaccess.SubscribeWithUsConstants;
import com.model.components.Complaint;
import com.model.components.SubscribedCustomer;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.components.publicaccess.SubmitQuery;
import com.model.components.publicaccess.SubscribeWithUs;
import com.model.gridcomponent.GridComponent;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.components.AdminService;
import com.service.components.CommonsService;
import com.service.components.CustomerService;
import com.utils.ApplicationUtils;
import com.utils.FileUtils;
import com.utils.GridComponentUtils;
import com.utils.JSONUtils;
import com.utils.ValidationUtils;
import com.utils.context.AppContext;
import com.utils.localization.Message;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_SUPPORT) 
public class SupportRestService extends AbstractRestWebservice implements SupportConstants, RestMethodConstants {
	
	private BecomeTutor becomeTutorObject;
	private Long tentativeTutorId;
	private Long enquiryId;
	private FindTutor findTutorObject;
	private SubscribeWithUs subscriptionObject;
	private SubmitQuery submitQueryObject;
	private Complaint complaintObject;
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_BECOME_TUTOR_LIST)
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
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, BecomeTutor.class);
		this.grid = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), EXTRA_PARAM_SELECTED_GRID, String.class);
		doSecurity(request, response);
		if (this.securityPassed) {
			FileUtils.writeFileToResponse(response, "Become_Tutor_Report" + PERIOD + FileConstants.EXTENSION_XLSX, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getAdminService().downloadReportBecomeTutorList(this.grid, gridComponent));
		}
    }
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_BECOME_TUTOR_PROFILE_PDF)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadAdminBecomeTutorProfilePdf (
    		@FormParam("tentativeTutorId") final String tentativeTutorId,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_BECOME_TUTOR_PROFILE_PDF;
		try {
			this.tentativeTutorId = Long.valueOf(tentativeTutorId);
		} catch(NumberFormatException e) {}
		doSecurity(request, response);
		if (this.securityPassed) {
			FileUtils.writeFileToResponse(response, "Become_Tutor_Profile" + PERIOD + FileConstants.EXTENSION_PDF, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getAdminService().downloadBecomeTutorProfilePdf(Long.valueOf(tentativeTutorId)));
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
		doSecurity(request, response);
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
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
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
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
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
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
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
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
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
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
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
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
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
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
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
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().blacklistBecomeTutorList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().unBlacklistBecomeTutorList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TAKE_ACTION_ON_BECOME_TUTOR)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String takeActionOnBecomeTutor (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_BUTTON) final String button,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TAKE_ACTION_ON_BECOME_TUTOR;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.button = button;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().takeActionOnBecomeTutor(button, Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_UPDATE_BECOME_TUTOR_RECORD)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateBecomeTutorRecord (
			@FormDataParam(REQUEST_PARAM_COMPLETE_UPDATED_RECORD) final String completeUpdatedRecord,
			@FormDataParam(REQUEST_PARAM_PARENT_ID) final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UPDATE_BECOME_TUTOR_RECORD;
		createBecomeTutorObjectFromCompleteUpdatedRecordJSONObject(JSONUtils.getJSONObjectFromString(completeUpdatedRecord));
		try {
			this.parentId = Long.parseLong(parentId);
		} catch(NumberFormatException e) {}
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			this.becomeTutorObject.setTentativeTutorId(Long.parseLong(parentId));
			getAdminService().updateBecomeTutorRecord(this.becomeTutorObject, this.changedAttributes, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, MESSAGE_UPDATED_RECORD);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_FIND_TUTOR_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadAdminReportFindTutorList (
    		@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_FIND_TUTOR_LIST;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, FindTutor.class);
		this.grid = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), EXTRA_PARAM_SELECTED_GRID, String.class);
		doSecurity(request, response);
		if (this.securityPassed) {
			FileUtils.writeFileToResponse(response, "Enquiry_Registration_Report" + PERIOD + FileConstants.EXTENSION_XLSX, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getAdminService().downloadAdminReportFindTutorList(this.grid, gridComponent));
		}
    }
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_FIND_TUTOR_PROFILE_PDF)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadAdminFindTutorProfilePdf (
    		@FormParam("enquiryId") final String enquiryId,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_FIND_TUTOR_PROFILE_PDF;
		try {
			this.enquiryId = Long.valueOf(enquiryId);
		} catch(NumberFormatException e) {}
		doSecurity(request, response);
		if (this.securityPassed) {
			FileUtils.writeFileToResponse(response, "Find_Tutor_Profile" + PERIOD + FileConstants.EXTENSION_PDF, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getAdminService().downloadFindTutorProfilePdf(Long.valueOf(enquiryId)));
		}
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, FindTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<FindTutor> findTutorList = getAdminService().getFindTutorList(REST_METHOD_NAME_NON_CONTACTED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, findTutorList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(findTutorList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, FindTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<FindTutor> findTutorList = getAdminService().getFindTutorList(REST_METHOD_NAME_NON_VERIFIED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, findTutorList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(findTutorList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, FindTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<FindTutor> findTutorList = getAdminService().getFindTutorList(REST_METHOD_NAME_VERIFIED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, findTutorList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(findTutorList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, FindTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<FindTutor> findTutorList = getAdminService().getFindTutorList(REST_METHOD_NAME_VERIFICATION_FAILED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, findTutorList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(findTutorList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, FindTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<FindTutor> findTutorList = getAdminService().getFindTutorList(REST_METHOD_NAME_TO_BE_RECONTACTED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, findTutorList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(findTutorList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, FindTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<FindTutor> findTutorList = getAdminService().getFindTutorList(REST_METHOD_NAME_SELECTED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, findTutorList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(findTutorList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, FindTutor.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<FindTutor> findTutorList = getAdminService().getFindTutorList(REST_METHOD_NAME_REJECTED_ENQUIRIES_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, findTutorList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(findTutorList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().blacklistFindTutorList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().unBlacklistFindTutorList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TAKE_ACTION_ON_FIND_TUTOR)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String takeActionOnFindTutor (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_BUTTON) final String button,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TAKE_ACTION_ON_FIND_TUTOR;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.button = button;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().takeActionOnFindTutor(button, Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_UPDATE_FIND_TUTOR_RECORD)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateFindTutorRecord (
			@FormDataParam(REQUEST_PARAM_COMPLETE_UPDATED_RECORD) final String completeUpdatedRecord,
			@FormDataParam(REQUEST_PARAM_PARENT_ID) final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UPDATE_FIND_TUTOR_RECORD;
		createFindTutorObjectFromCompleteUpdatedRecordJSONObject(JSONUtils.getJSONObjectFromString(completeUpdatedRecord));
		try {
			this.parentId = Long.parseLong(parentId);
		} catch(NumberFormatException e) {}
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			this.findTutorObject.setEnquiryId(Long.parseLong(parentId));
			getAdminService().updateFindTutorRecord(this.findTutorObject, this.changedAttributes, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, MESSAGE_UPDATED_RECORD);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_SUBSCRIBE_WITH_US_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadAdminReportSubscribeWithUsList (
    		@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_SUBSCRIBE_WITH_US_LIST;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribeWithUs.class);
		this.grid = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), EXTRA_PARAM_SELECTED_GRID, String.class);
		doSecurity(request, response);
		if (this.securityPassed) {
			FileUtils.writeFileToResponse(response, "Subscription_Registration_Report" + PERIOD + FileConstants.EXTENSION_XLSX, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getAdminService().downloadAdminReportSubscribeWithUsList(this.grid, gridComponent));
		}
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribeWithUs.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscribeWithUs> subscriptionsList = getAdminService().getSubscribeWithUsList(REST_METHOD_NAME_NON_CONTACTED_SUBSCRIPTIONS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribeWithUs.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscribeWithUs> subscriptionsList = getAdminService().getSubscribeWithUsList(REST_METHOD_NAME_NON_VERIFIED_SUBSCRIPTIONS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribeWithUs.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscribeWithUs> subscriptionsList = getAdminService().getSubscribeWithUsList(REST_METHOD_NAME_VERIFIED_SUBSCRIPTIONS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribeWithUs.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscribeWithUs> subscriptionsList = getAdminService().getSubscribeWithUsList(REST_METHOD_NAME_VERIFICATION_FAILED_SUBSCRIPTIONS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribeWithUs.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscribeWithUs> subscriptionsList = getAdminService().getSubscribeWithUsList(REST_METHOD_NAME_TO_BE_RECONTACTED_SUBSCRIPTIONS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribeWithUs.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscribeWithUs> subscriptionsList = getAdminService().getSubscribeWithUsList(REST_METHOD_NAME_SELECTED_SUBSCRIPTIONS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscribeWithUs.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscribeWithUs> subscriptionsList = getAdminService().getSubscribeWithUsList(REST_METHOD_NAME_REJECTED_SUBSCRIPTIONS_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().blacklistSubscriptionList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().unBlacklistSubscriptionList(Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TAKE_ACTION_ON_SUBSCRIPTION)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String takeActionOnSubscription (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_BUTTON) final String button,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TAKE_ACTION_ON_SUBSCRIPTION;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.button = button;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().takeActionOnSubscription(button, Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_UPDATE_SUBSCRIPTION_RECORD)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateSubscriptionRecord (
			@FormDataParam(REQUEST_PARAM_COMPLETE_UPDATED_RECORD) final String completeUpdatedRecord,
			@FormDataParam(REQUEST_PARAM_PARENT_ID) final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UPDATE_SUBSCRIPTION_RECORD;
		createSubscriptionObjectFromCompleteUpdatedRecordJSONObject(JSONUtils.getJSONObjectFromString(completeUpdatedRecord));
		try {
			this.parentId = Long.parseLong(parentId);
		} catch(NumberFormatException e) {}
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			this.subscriptionObject.setTentativeSubscriptionId(Long.parseLong(parentId));
			getAdminService().updateSubscriptionRecord(this.subscriptionObject, this.changedAttributes, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, MESSAGE_UPDATED_RECORD);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_SUBMIT_QUERY_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadAdminReportSubmitQueryList (
    		@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_SUBMIT_QUERY_LIST;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubmitQuery.class);
		this.grid = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), EXTRA_PARAM_SELECTED_GRID, String.class);
		doSecurity(request, response);
		if (this.securityPassed) {
			FileUtils.writeFileToResponse(response, "Query_Registration_Report" + PERIOD + FileConstants.EXTENSION_XLSX, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getAdminService().downloadAdminReportSubmitQueryList(this.grid, gridComponent));
		}
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubmitQuery.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubmitQuery> queryList = getAdminService().getSubmitQueryList(REST_METHOD_NAME_NON_CONTACTED_QUERY_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, queryList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(queryList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubmitQuery.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubmitQuery> queryList = getAdminService().getSubmitQueryList(REST_METHOD_NAME_NON_ANSWERED_QUERY_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, queryList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(queryList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubmitQuery.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubmitQuery> queryList = getAdminService().getSubmitQueryList(REST_METHOD_NAME_ANSWERED_QUERY_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, queryList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(queryList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path("/submitQueryCheckDataAccess")
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String submitQueryCheckDataAccess (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("queryResponseCapableAccess", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_TAKE_ACTION_ON_SUBMIT_QUERY)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String takeActionOnSubmitQuery (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_BUTTON) final String button,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TAKE_ACTION_ON_SUBMIT_QUERY;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.button = button;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().takeActionOnSubmitQuery(button, Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_UPDATE_SUBMIT_QUERY_RECORD)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateSubmitQueryRecord (
			@FormDataParam(REQUEST_PARAM_COMPLETE_UPDATED_RECORD) final String completeUpdatedRecord,
			@FormDataParam(REQUEST_PARAM_PARENT_ID) final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UPDATE_SUBMIT_QUERY_RECORD;
		createSubmitQueryObjectFromCompleteUpdatedRecordJSONObject(JSONUtils.getJSONObjectFromString(completeUpdatedRecord));
		try {
			this.parentId = Long.parseLong(parentId);
		} catch(NumberFormatException e) {}
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			this.submitQueryObject.setQueryId(Long.parseLong(parentId));
			getAdminService().updateSubmitQueryRecord(this.submitQueryObject, this.changedAttributes, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, MESSAGE_UPDATED_RECORD);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_CUSTOMER_COMPLAINT_LIST)
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
		this.methodName = REST_METHOD_NAME_CUSTOMER_COMPLAINT_LIST;
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, Complaint.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<Complaint> complaintList = getAdminService().getComplaintList(REST_METHOD_NAME_CUSTOMER_COMPLAINT_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, complaintList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(complaintList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TUTOR_COMPLAINT_LIST)
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
		this.methodName = REST_METHOD_NAME_TUTOR_COMPLAINT_LIST;
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, Complaint.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<Complaint> complaintList = getAdminService().getComplaintList(REST_METHOD_NAME_TUTOR_COMPLAINT_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, complaintList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(complaintList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_EMPLOYEE_COMPLAINT_LIST)
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
		this.methodName = REST_METHOD_NAME_EMPLOYEE_COMPLAINT_LIST;
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, Complaint.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<Complaint> complaintList = getAdminService().getComplaintList(REST_METHOD_NAME_EMPLOYEE_COMPLAINT_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, complaintList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(complaintList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_RESOLVED_COMPLAINT_LIST)
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
		this.methodName = REST_METHOD_NAME_RESOLVED_COMPLAINT_LIST;
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, Complaint.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<Complaint> complaintList = getAdminService().getComplaintList(REST_METHOD_NAME_RESOLVED_COMPLAINT_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, complaintList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(complaintList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_NOT_RESOLVED_COMPLAINT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String holdComplaintList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_NOT_RESOLVED_COMPLAINT_LIST;
		doSecurity(request, response);
		if (this.securityPassed) {
			final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, Complaint.class);
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<Complaint> complaintList = getAdminService().getComplaintList(REST_METHOD_NAME_NOT_RESOLVED_COMPLAINT_LIST, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, complaintList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(complaintList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
	
	@Path(REST_METHOD_NAME_TAKE_ACTION_ON_COMPLAINT)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String takeActionOnComplaint (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_BUTTON) final String button,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TAKE_ACTION_ON_COMPLAINT;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.button = button;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getAdminService().takeActionOnSubscription(button, Arrays.asList(allIdsList.split(SEMICOLON)), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, AdminConstants.ACTION_SUCCESSFUL);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_UPDATE_COMPLAINT_RECORD)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateComplaintRecord (
			@FormDataParam(REQUEST_PARAM_COMPLETE_UPDATED_RECORD) final String completeUpdatedRecord,
			@FormDataParam(REQUEST_PARAM_PARENT_ID) final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UPDATE_COMPLAINT_RECORD;
		createComplaintObjectFromCompleteUpdatedRecordJSONObject(JSONUtils.getJSONObjectFromString(completeUpdatedRecord));
		try {
			this.parentId = Long.parseLong(parentId);
		} catch(NumberFormatException e) {}
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			this.complaintObject.setComplaintId(Long.parseLong(parentId));
			getAdminService().updateComplaintRecord(this.complaintObject, this.changedAttributes, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, MESSAGE_UPDATED_RECORD);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	public AdminService getAdminService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ADMIN_SERVICE, AdminService.class);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	public CustomerService getCustomerService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_CUSTOMER_SERVICE, CustomerService.class);
	}
	
	public JNDIandControlConfigurationLoadService getJNDIandControlConfigurationLoadService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
	}
	
	@Override
	protected void doSecurity(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		this.request = request; this.response = response;
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
			case REST_METHOD_NAME_CUSTOMER_COMPLAINT_LIST :
			case REST_METHOD_NAME_TUTOR_COMPLAINT_LIST : 
			case REST_METHOD_NAME_EMPLOYEE_COMPLAINT_LIST : 
			case REST_METHOD_NAME_RESOLVED_COMPLAINT_LIST :
			case REST_METHOD_NAME_NOT_RESOLVED_COMPLAINT_LIST : {
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
			case REST_METHOD_NAME_TAKE_ACTION_ON_SUBSCRIPTION : 
			case REST_METHOD_NAME_TAKE_ACTION_ON_SUBMIT_QUERY : 
			case REST_METHOD_NAME_TAKE_ACTION_ON_COMPLAINT : {
				handleTakeAction();
				break;
			}
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_BECOME_TUTOR_LIST : 
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_FIND_TUTOR_LIST : 
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_SUBSCRIBE_WITH_US_LIST : 
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_SUBMIT_QUERY_LIST : {
				handleGridDownload();
				break;
			}
			case REST_METHOD_NAME_UPDATE_BECOME_TUTOR_RECORD : {
				handleParentId();
				handleBecomeTutorSecurity();
				break;
			}
			case REST_METHOD_NAME_UPDATE_FIND_TUTOR_RECORD : {
				handleParentId();
				handleFindTutorSecurity();
				break;
			}
			case REST_METHOD_NAME_UPDATE_SUBSCRIPTION_RECORD : {
				handleParentId();
				handleSubscribeWithUsSecurity();
				break;
			}
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_BECOME_TUTOR_PROFILE_PDF : {
				handleTentativeTutorId();
				break;
			}
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_FIND_TUTOR_PROFILE_PDF : {
				handleEnquiryId();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
	
	public void handleTentativeTutorId() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.tentativeTutorId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_TENTATIVE_TUTOR_ID_ABSENT,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	public void handleEnquiryId() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.enquiryId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_ENQUIRY_ID_ABSENT,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	public void handleTakeAction() throws Exception {
		this.securityPassed = true;
		super.handleTakeAction();
		if (this.securityPassed) {
			switch(this.button) {
				case BUTTON_ACTION_CONTACTED : 
				case BUTTON_ACTION_VERIFY:
				case BUTTON_ACTION_REVERIFY : 
				case BUTTON_ACTION_SELECT : 
				case BUTTON_ACTION_RECONTACTED : {
					break;
				}
				case BUTTON_ACTION_RECONTACT : 
				case BUTTON_ACTION_REJECT : 
				case BUTTON_ACTION_FAIL_VERIFY : 
				case BUTTON_ACTION_RESPOND : 
				case BUTTON_ACTION_PUT_ON_HOLD : {
					handleComments();
					break;
				}
				default : {
					ApplicationUtils.appendMessageInMapAttribute(
							this.securityFailureResponse, 
							Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_BUTTON_UNKNOWN, new Object[] {this.button}),
							RESPONSE_MAP_ATTRIBUTE_MESSAGE);
					this.securityPassed = false;
					break;
				}
			}
		}
	}
	
	private void createBecomeTutorObjectFromCompleteUpdatedRecordJSONObject(final JsonObject jsonObject) {
		if (ValidationUtils.checkObjectAvailability(jsonObject)) {
			this.becomeTutorObject = new BecomeTutor();
			this.becomeTutorObject.setFirstName(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "firstName", String.class));
			this.becomeTutorObject.setLastName(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "lastName", String.class));
			this.becomeTutorObject.setDateOfBirth(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "dateOfBirth", Date.class));
			this.becomeTutorObject.setContactNumber(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "contactNumber", String.class));
			this.becomeTutorObject.setEmailId(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "emailId", String.class));
			this.becomeTutorObject.setGender(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "gender", String.class));
			this.becomeTutorObject.setQualification(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "qualification", String.class));
			this.becomeTutorObject.setPrimaryProfession(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "primaryProfession", String.class));
			this.becomeTutorObject.setTransportMode(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "transportMode", String.class));
			this.becomeTutorObject.setTeachingExp(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "teachingExp", Integer.class));
			this.becomeTutorObject.setStudentGrade(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "studentGrades", String.class));
			this.becomeTutorObject.setSubjects(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "subjects", String.class));
			this.becomeTutorObject.setLocations(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "locations", String.class));
			this.becomeTutorObject.setPreferredTimeToCall(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "preferredTimeToCall", String.class));
			this.becomeTutorObject.setAdditionalDetails(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "additionalDetails", String.class));
			this.becomeTutorObject.setReference(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "reference", String.class));
			this.becomeTutorObject.setPreferredTeachingType(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "preferredTeachingType", String.class));
			this.becomeTutorObject.setAddressDetails(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "addressDetails", String.class));
		}
	}
	
	private void handleBecomeTutorSecurity() throws Exception {
		this.securityPassed = true;
		if (ValidationUtils.checkNonEmptyList(this.changedAttributes)) {
			for (final String attributeName : this.changedAttributes) {
				switch(attributeName) {
					case "firstName" : {
						if (!ValidationUtils.validateNameString(this.becomeTutorObject.getFirstName(), true)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_FIRST_NAME,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "lastName" : {
						if (!ValidationUtils.validateNameString(this.becomeTutorObject.getLastName(), true)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_LAST_NAME,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "dateOfBirth" : {
						if (!ValidationUtils.validateDate(this.becomeTutorObject.getDateOfBirth())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_DATE_OF_BIRTH,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "contactNumber" : {
						if (!ValidationUtils.validatePhoneNumber(this.becomeTutorObject.getContactNumber(), 10)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_CONTACT_NUMBER_MOBILE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else {
							// Check contact number in system
							final BecomeTutor becomeTutorApplicationInDatabaseWithContactNumber = getAdminService().getBecomeTutorApplicationInDatabaseWithContactNumber(this.becomeTutorObject.getContactNumber());
							if (null != becomeTutorApplicationInDatabaseWithContactNumber) {
								ApplicationUtils.appendMessageInMapAttribute(
										this.securityFailureResponse, 
										PublicAccessConstants.FAILURE_MESSAGE_THIS_CONTACT_NUMBER_ALREADY_EXISTS_IN_THE_SYSTEM,
										RESPONSE_MAP_ATTRIBUTE_MESSAGE);
								this.securityPassed = false;
							}
						}
						break;
					}
					case "emailId" : {
						if (!ValidationUtils.validateEmailAddress(this.becomeTutorObject.getEmailId())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_EMAIL_ID,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else {
							// Check email Id in system
							final BecomeTutor becomeTutorApplicationInDatabaseWithEmailId = getAdminService().getBecomeTutorApplicationInDatabaseWithEmailId(this.becomeTutorObject.getEmailId());
							if (null != becomeTutorApplicationInDatabaseWithEmailId) {
								ApplicationUtils.appendMessageInMapAttribute(
										this.securityFailureResponse, 
										PublicAccessConstants.FAILURE_MESSAGE_THIS_EMAIL_ID_ALREADY_EXISTS_IN_THE_SYSTEM,
										RESPONSE_MAP_ATTRIBUTE_MESSAGE);
								this.securityPassed = false;
							} 
						}
						break;
					}
					case "gender" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.becomeTutorObject.getGender(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_GENDER_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_GENDER,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "qualification" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.becomeTutorObject.getQualification(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_QUALIFICATION,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "primaryProfession" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.becomeTutorObject.getPrimaryProfession(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_PRIMARY_PROFESSION,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "transportMode" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.becomeTutorObject.getTransportMode(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_TRANSPORT_MODE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "teachingExp" : {
						if (!ValidationUtils.validateNumber(this.becomeTutorObject.getTeachingExp(), true, 99, false, 0)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_TEACHING_EXPERIENCE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "studentGrades" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.becomeTutorObject.getStudentGrade(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_STUDENT_GRADE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "subjects" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.becomeTutorObject.getSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_SUBJECTS,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "locations" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.becomeTutorObject.getLocations(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_LOCATIONS,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "preferredTimeToCall" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.becomeTutorObject.getPreferredTimeToCall(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_PREFERRED_TIME_TO_CALL,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "additionalDetails" : {
						break;
					}
					case "reference" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.becomeTutorObject.getReference(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_REFERENCE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "preferredTeachingType" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.becomeTutorObject.getPreferredTeachingType(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_TUTORING_TYPE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "addressDetails" : {
						if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.becomeTutorObject.getAddressDetails())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_ADDRESS_DETAILS,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					default : {
						ApplicationUtils.appendMessageInMapAttribute(
								this.securityFailureResponse, 
								Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_UNKONWN_PROPERTY, new Object[] {attributeName}),
								RESPONSE_MAP_ATTRIBUTE_MESSAGE);
						this.securityPassed = false;
						break;
					}
				}
			}
		} else {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_NO_ATTRIBUTES_CHANGED),
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	private void createFindTutorObjectFromCompleteUpdatedRecordJSONObject(final JsonObject jsonObject) throws Exception {
		if (ValidationUtils.checkObjectAvailability(jsonObject)) {
			this.findTutorObject = new FindTutor();
			this.findTutorObject.setName(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "name", String.class));
			this.findTutorObject.setContactNumber(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "contactNumber", String.class));
			this.findTutorObject.setEmailId(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "emailId", String.class));
			this.findTutorObject.setStudentGrade(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "studentGrade", String.class));
			this.findTutorObject.setSubjects(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "subjects", String.class));
			this.findTutorObject.setLocation(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "location", String.class));
			this.findTutorObject.setPreferredTimeToCall(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "preferredTimeToCall", String.class));
			this.findTutorObject.setAdditionalDetails(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "additionalDetails", String.class));
			this.findTutorObject.setAddressDetails(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "addressDetails", String.class));
			this.findTutorObject.setReference(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "reference", String.class));
			if (this.changedAttributes.contains("emailId") || this.changedAttributes.contains("contactNumber")) {
				SubscribedCustomer subscribedCustomerInDatabaseWithEmailId = null;
				SubscribedCustomer subscribedCustomerInDatabaseWithContactNumber = null;
				if (this.changedAttributes.contains("emailId")) {
					// Check email Id in system for Subscribed Customer
					subscribedCustomerInDatabaseWithEmailId = getCustomerService().getSubscribedCustomerInDatabaseWithEmailId(this.findTutorObject.getEmailId());
				}
				if (this.changedAttributes.contains("contactNumber")) {
					// Check contact number in system for Subscribed Customer
					subscribedCustomerInDatabaseWithContactNumber = getCustomerService().getSubscribedCustomerInDatabaseWithContactNumber(this.findTutorObject.getContactNumber());
				}
				if (null != subscribedCustomerInDatabaseWithEmailId || null != subscribedCustomerInDatabaseWithContactNumber) {
					this.findTutorObject.setSubscribedCustomer(YES);
				} else {
					this.findTutorObject.setSubscribedCustomer(NO);
				}
				this.changedAttributes.add("subscribedCustomer");
			}
		}
	}
	
	private void handleFindTutorSecurity() throws Exception {
		this.securityPassed = true;
		if (ValidationUtils.checkNonEmptyList(this.changedAttributes)) {
			for (final String attributeName : this.changedAttributes) {
				switch(attributeName) {
					case "name" : {
						if (!ValidationUtils.validateNameString(this.findTutorObject.getName(), true)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_NAME,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "contactNumber" : {
						if (!ValidationUtils.validatePhoneNumber(this.findTutorObject.getContactNumber(), 10)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_CONTACT_NUMBER_MOBILE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "emailId" : {
						if (!ValidationUtils.validateEmailAddress(this.findTutorObject.getEmailId())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_EMAIL_ID,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "studentGrade" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.findTutorObject.getStudentGrade(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_STUDENT_GRADE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "subjects" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.findTutorObject.getSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_SUBJECTS,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "preferredTimeToCall" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.findTutorObject.getPreferredTimeToCall(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_PREFERRED_TIME_TO_CALL,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "location" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.findTutorObject.getLocation(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_LOCATION,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "reference" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.findTutorObject.getReference(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_REFERENCE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "additionalDetails" : {
						break;
					}
					case "subscribedCustomer" : {
						break;
					}
					case "addressDetails" : {
						if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.findTutorObject.getAddressDetails())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_ADDRESS_DETAILS,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					default : {
						ApplicationUtils.appendMessageInMapAttribute(
								this.securityFailureResponse, 
								Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_UNKONWN_PROPERTY, new Object[] {attributeName}),
								RESPONSE_MAP_ATTRIBUTE_MESSAGE);
						this.securityPassed = false;
						break;
					}
				}
			}
		} else {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_NO_ATTRIBUTES_CHANGED),
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	private void createSubscriptionObjectFromCompleteUpdatedRecordJSONObject(final JsonObject jsonObject) throws Exception {
		if (ValidationUtils.checkObjectAvailability(jsonObject)) {
			this.subscriptionObject = new SubscribeWithUs();
			this.subscriptionObject.setFirstName(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "firstName", String.class));
			this.subscriptionObject.setLastName(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "lastName", String.class));
			this.subscriptionObject.setContactNumber(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "contactNumber", String.class));
			this.subscriptionObject.setEmailId(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "emailId", String.class));
			this.subscriptionObject.setStudentGrade(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "studentGrades", String.class));
			this.subscriptionObject.setSubjects(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "subjects", String.class));
			this.subscriptionObject.setLocation(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "locations", String.class));
			this.subscriptionObject.setPreferredTimeToCall(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "preferredTimeToCall", String.class));
			this.subscriptionObject.setAdditionalDetails(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "additionalDetails", String.class));
			this.subscriptionObject.setAddressDetails(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "addressDetails", String.class));
			this.subscriptionObject.setReference(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "reference", String.class));
			if (this.changedAttributes.contains("emailId") || this.changedAttributes.contains("contactNumber")) {
				SubscribedCustomer subscribedCustomerInDatabaseWithEmailId = null;
				SubscribedCustomer subscribedCustomerInDatabaseWithContactNumber = null;
				if (this.changedAttributes.contains("emailId")) {
					// Check email Id in system for Subscribed Customer
					subscribedCustomerInDatabaseWithEmailId = getCustomerService().getSubscribedCustomerInDatabaseWithEmailId(this.subscriptionObject.getEmailId());
				}
				if (this.changedAttributes.contains("contactNumber")) {
					// Check contact number in system for Subscribed Customer
					subscribedCustomerInDatabaseWithContactNumber = getCustomerService().getSubscribedCustomerInDatabaseWithContactNumber(this.subscriptionObject.getContactNumber());
				}
				if (null != subscribedCustomerInDatabaseWithEmailId || null != subscribedCustomerInDatabaseWithContactNumber) {
					this.subscriptionObject.setSubscribedCustomer(YES);
				} else {
					this.subscriptionObject.setSubscribedCustomer(NO);
				}
				this.changedAttributes.add("subscribedCustomer");
			}
		}
	}
	
	private void handleSubscribeWithUsSecurity() throws Exception {
		this.securityPassed = true;
		if (ValidationUtils.checkNonEmptyList(this.changedAttributes)) {
			for (final String attributeName : this.changedAttributes) {
				switch(attributeName) {
					case "firstName" : {
						if (!ValidationUtils.validateNameString(this.subscriptionObject.getFirstName(), true)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_FIRST_NAME,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "lastName" : {
						if (!ValidationUtils.validateNameString(this.subscriptionObject.getLastName(), true)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_LAST_NAME,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "contactNumber" : {
						if (!ValidationUtils.validatePhoneNumber(this.subscriptionObject.getContactNumber(), 10)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_CONTACT_NUMBER_MOBILE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "emailId" : {
						if (!ValidationUtils.validateEmailAddress(this.subscriptionObject.getEmailId())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_EMAIL_ID,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "studentGrades" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.subscriptionObject.getStudentGrade(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_STUDENT_GRADE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "subjects" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.subscriptionObject.getSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_SUBJECTS,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "preferredTimeToCall" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.subscriptionObject.getPreferredTimeToCall(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_PREFERRED_TIME_TO_CALL,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "locations" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.subscriptionObject.getLocation(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_LOCATION,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "reference" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.subscriptionObject.getReference(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_REFERENCE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "addressDetails" : {
						if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.subscriptionObject.getAddressDetails())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_ENTER_ADDRESS_DETAILS,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "additionalDetails" : {
						break;
					}
					case "subscribedCustomer" : {
						break;
					}
					default : {
						ApplicationUtils.appendMessageInMapAttribute(
								this.securityFailureResponse, 
								Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_UNKONWN_PROPERTY, new Object[] {attributeName}),
								RESPONSE_MAP_ATTRIBUTE_MESSAGE);
						this.securityPassed = false;
						break;
					}
				}
			}
		} else {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.VALIDATION_MESSAGE_NO_ATTRIBUTES_CHANGED),
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	private void createSubmitQueryObjectFromCompleteUpdatedRecordJSONObject(final JsonObject jsonObject) throws DataAccessException, InstantiationException, IllegalAccessException {
		if (ValidationUtils.checkObjectAvailability(jsonObject)) {
			
		}
	}
	
	private void createComplaintObjectFromCompleteUpdatedRecordJSONObject(final JsonObject jsonObject) throws DataAccessException, InstantiationException, IllegalAccessException {
		if (ValidationUtils.checkObjectAvailability(jsonObject)) {
			
		}
	}
}
