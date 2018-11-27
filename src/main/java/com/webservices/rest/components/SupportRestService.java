package com.webservices.rest.components;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

import com.constants.RestMethodConstants;
import com.constants.ScopeConstants;
import com.model.components.Complaint;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.components.publicaccess.SubmitQuery;
import com.model.components.publicaccess.SubscribeWithUs;
import com.utils.JSONUtils;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path("/support") 
public class SupportRestService extends AbstractRestWebservice implements RestMethodConstants {
	
	@Path("/nonContactedBecomeTutorsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<BecomeTutor> data = new LinkedList<BecomeTutor>();
		data.add(new BecomeTutor(1L));
		data.add(new BecomeTutor(2L));
		data.add(new BecomeTutor(3L));
		data.add(new BecomeTutor(4L));
		data.add(new BecomeTutor(5L));
		data.add(new BecomeTutor(6L));
		data.add(new BecomeTutor(7L));
		data.add(new BecomeTutor(8L));
		data.add(new BecomeTutor(9L));
		data.add(new BecomeTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/nonVerifiedBecomeTutorsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<BecomeTutor> data = new LinkedList<BecomeTutor>();
		data.add(new BecomeTutor(1L));
		data.add(new BecomeTutor(2L));
		data.add(new BecomeTutor(3L));
		data.add(new BecomeTutor(4L));
		data.add(new BecomeTutor(5L));
		data.add(new BecomeTutor(6L));
		data.add(new BecomeTutor(7L));
		data.add(new BecomeTutor(8L));
		data.add(new BecomeTutor(9L));
		data.add(new BecomeTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/verifiedBecomeTutorsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<BecomeTutor> data = new LinkedList<BecomeTutor>();
		data.add(new BecomeTutor(1L));
		data.add(new BecomeTutor(2L));
		data.add(new BecomeTutor(3L));
		data.add(new BecomeTutor(4L));
		data.add(new BecomeTutor(5L));
		data.add(new BecomeTutor(6L));
		data.add(new BecomeTutor(7L));
		data.add(new BecomeTutor(8L));
		data.add(new BecomeTutor(9L));
		data.add(new BecomeTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/verificationFailedBecomeTutorsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<BecomeTutor> data = new LinkedList<BecomeTutor>();
		data.add(new BecomeTutor(1L));
		data.add(new BecomeTutor(2L));
		data.add(new BecomeTutor(3L));
		data.add(new BecomeTutor(4L));
		data.add(new BecomeTutor(5L));
		data.add(new BecomeTutor(6L));
		data.add(new BecomeTutor(7L));
		data.add(new BecomeTutor(8L));
		data.add(new BecomeTutor(9L));
		data.add(new BecomeTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/toBeReContactedBecomeTutorsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<BecomeTutor> data = new LinkedList<BecomeTutor>();
		data.add(new BecomeTutor(1L));
		data.add(new BecomeTutor(2L));
		data.add(new BecomeTutor(3L));
		data.add(new BecomeTutor(4L));
		data.add(new BecomeTutor(5L));
		data.add(new BecomeTutor(6L));
		data.add(new BecomeTutor(7L));
		data.add(new BecomeTutor(8L));
		data.add(new BecomeTutor(9L));
		data.add(new BecomeTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/selectedBecomeTutorsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<BecomeTutor> data = new LinkedList<BecomeTutor>();
		data.add(new BecomeTutor(1L));
		data.add(new BecomeTutor(2L));
		data.add(new BecomeTutor(3L));
		data.add(new BecomeTutor(4L));
		data.add(new BecomeTutor(5L));
		data.add(new BecomeTutor(6L));
		data.add(new BecomeTutor(7L));
		data.add(new BecomeTutor(8L));
		data.add(new BecomeTutor(9L));
		data.add(new BecomeTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/rejectedBecomeTutorsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<BecomeTutor> data = new LinkedList<BecomeTutor>();
		data.add(new BecomeTutor(1L));
		data.add(new BecomeTutor(2L));
		data.add(new BecomeTutor(3L));
		data.add(new BecomeTutor(4L));
		data.add(new BecomeTutor(5L));
		data.add(new BecomeTutor(6L));
		data.add(new BecomeTutor(7L));
		data.add(new BecomeTutor(8L));
		data.add(new BecomeTutor(9L));
		data.add(new BecomeTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/registeredBecomeTutorsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<BecomeTutor> data = new LinkedList<BecomeTutor>();
		data.add(new BecomeTutor(1L));
		data.add(new BecomeTutor(2L));
		data.add(new BecomeTutor(3L));
		data.add(new BecomeTutor(4L));
		data.add(new BecomeTutor(5L));
		data.add(new BecomeTutor(6L));
		data.add(new BecomeTutor(7L));
		data.add(new BecomeTutor(8L));
		data.add(new BecomeTutor(9L));
		data.add(new BecomeTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/becomeTutorCheckDataAccess")
	@Consumes("application/x-www-form-urlencoded")
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
	
	@Path("/blacklistBecomeTutors")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String blacklistBecomeTutors (
			@FormParam("allIdsList") final String allIdsList,
			@FormParam("comments") final String comments,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "All Ids blacklisted "+allIdsList+" "+comments);		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
	
	@Path("/nonContactedEnquirysList")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String nonContactedEnquirysList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<FindTutor> data = new LinkedList<FindTutor>();
		data.add(new FindTutor(1L));
		data.add(new FindTutor(2L));
		data.add(new FindTutor(3L));
		data.add(new FindTutor(4L));
		data.add(new FindTutor(5L));
		data.add(new FindTutor(6L));
		data.add(new FindTutor(7L));
		data.add(new FindTutor(8L));
		data.add(new FindTutor(9L));
		data.add(new FindTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/nonVerifiedEnquirysList")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String nonVerifiedEnquirysList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<FindTutor> data = new LinkedList<FindTutor>();
		data.add(new FindTutor(1L));
		data.add(new FindTutor(2L));
		data.add(new FindTutor(3L));
		data.add(new FindTutor(4L));
		data.add(new FindTutor(5L));
		data.add(new FindTutor(6L));
		data.add(new FindTutor(7L));
		data.add(new FindTutor(8L));
		data.add(new FindTutor(9L));
		data.add(new FindTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/verifiedEnquirysList")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String verifiedEnquirysList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<FindTutor> data = new LinkedList<FindTutor>();
		data.add(new FindTutor(1L));
		data.add(new FindTutor(2L));
		data.add(new FindTutor(3L));
		data.add(new FindTutor(4L));
		data.add(new FindTutor(5L));
		data.add(new FindTutor(6L));
		data.add(new FindTutor(7L));
		data.add(new FindTutor(8L));
		data.add(new FindTutor(9L));
		data.add(new FindTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/verificationFailedEnquirysList")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String verificationFailedEnquirysList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<FindTutor> data = new LinkedList<FindTutor>();
		data.add(new FindTutor(1L));
		data.add(new FindTutor(2L));
		data.add(new FindTutor(3L));
		data.add(new FindTutor(4L));
		data.add(new FindTutor(5L));
		data.add(new FindTutor(6L));
		data.add(new FindTutor(7L));
		data.add(new FindTutor(8L));
		data.add(new FindTutor(9L));
		data.add(new FindTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/toBeReContactedEnquirysList")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String toBeReContactedEnquirysList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<FindTutor> data = new LinkedList<FindTutor>();
		data.add(new FindTutor(1L));
		data.add(new FindTutor(2L));
		data.add(new FindTutor(3L));
		data.add(new FindTutor(4L));
		data.add(new FindTutor(5L));
		data.add(new FindTutor(6L));
		data.add(new FindTutor(7L));
		data.add(new FindTutor(8L));
		data.add(new FindTutor(9L));
		data.add(new FindTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/selectedEnquirysList")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String selectedEnquirysList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<FindTutor> data = new LinkedList<FindTutor>();
		data.add(new FindTutor(1L));
		data.add(new FindTutor(2L));
		data.add(new FindTutor(3L));
		data.add(new FindTutor(4L));
		data.add(new FindTutor(5L));
		data.add(new FindTutor(6L));
		data.add(new FindTutor(7L));
		data.add(new FindTutor(8L));
		data.add(new FindTutor(9L));
		data.add(new FindTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/rejectedEnquirysList")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String rejectedEnquirysList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<FindTutor> data = new LinkedList<FindTutor>();
		data.add(new FindTutor(1L));
		data.add(new FindTutor(2L));
		data.add(new FindTutor(3L));
		data.add(new FindTutor(4L));
		data.add(new FindTutor(5L));
		data.add(new FindTutor(6L));
		data.add(new FindTutor(7L));
		data.add(new FindTutor(8L));
		data.add(new FindTutor(9L));
		data.add(new FindTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/enquiryRequestCheckDataAccess")
	@Consumes("application/x-www-form-urlencoded")
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
	
	@Path("/blacklistEnquiryRequests")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String blacklistenquiryRequests (
			@FormParam("allIdsList") final String allIdsList,
			@FormParam("comments") final String comments,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "All Ids blacklisted "+allIdsList+" "+comments);		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
	
	@Path("/nonContactedSubscriptionsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<SubscribeWithUs> data = new LinkedList<SubscribeWithUs>();
		data.add(new SubscribeWithUs(1L));
		data.add(new SubscribeWithUs(2L));
		data.add(new SubscribeWithUs(3L));
		data.add(new SubscribeWithUs(4L));
		data.add(new SubscribeWithUs(5L));
		data.add(new SubscribeWithUs(6L));
		data.add(new SubscribeWithUs(7L));
		data.add(new SubscribeWithUs(8L));
		data.add(new SubscribeWithUs(9L));
		data.add(new SubscribeWithUs(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}	
	
	@Path("/nonVerifiedSubscriptionsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<SubscribeWithUs> data = new LinkedList<SubscribeWithUs>();
		data.add(new SubscribeWithUs(1L));
		data.add(new SubscribeWithUs(2L));
		data.add(new SubscribeWithUs(3L));
		data.add(new SubscribeWithUs(4L));
		data.add(new SubscribeWithUs(5L));
		data.add(new SubscribeWithUs(6L));
		data.add(new SubscribeWithUs(7L));
		data.add(new SubscribeWithUs(8L));
		data.add(new SubscribeWithUs(9L));
		data.add(new SubscribeWithUs(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}	
	
	@Path("/verifiedSubscriptionsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<SubscribeWithUs> data = new LinkedList<SubscribeWithUs>();
		data.add(new SubscribeWithUs(1L));
		data.add(new SubscribeWithUs(2L));
		data.add(new SubscribeWithUs(3L));
		data.add(new SubscribeWithUs(4L));
		data.add(new SubscribeWithUs(5L));
		data.add(new SubscribeWithUs(6L));
		data.add(new SubscribeWithUs(7L));
		data.add(new SubscribeWithUs(8L));
		data.add(new SubscribeWithUs(9L));
		data.add(new SubscribeWithUs(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}	
	
	@Path("/verificationFailedSubscriptionsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<SubscribeWithUs> data = new LinkedList<SubscribeWithUs>();
		data.add(new SubscribeWithUs(1L));
		data.add(new SubscribeWithUs(2L));
		data.add(new SubscribeWithUs(3L));
		data.add(new SubscribeWithUs(4L));
		data.add(new SubscribeWithUs(5L));
		data.add(new SubscribeWithUs(6L));
		data.add(new SubscribeWithUs(7L));
		data.add(new SubscribeWithUs(8L));
		data.add(new SubscribeWithUs(9L));
		data.add(new SubscribeWithUs(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}	
	
	@Path("/toBeReContactedSubscriptionsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<SubscribeWithUs> data = new LinkedList<SubscribeWithUs>();
		data.add(new SubscribeWithUs(1L));
		data.add(new SubscribeWithUs(2L));
		data.add(new SubscribeWithUs(3L));
		data.add(new SubscribeWithUs(4L));
		data.add(new SubscribeWithUs(5L));
		data.add(new SubscribeWithUs(6L));
		data.add(new SubscribeWithUs(7L));
		data.add(new SubscribeWithUs(8L));
		data.add(new SubscribeWithUs(9L));
		data.add(new SubscribeWithUs(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}	
	
	@Path("/selectedSubscriptionsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<SubscribeWithUs> data = new LinkedList<SubscribeWithUs>();
		data.add(new SubscribeWithUs(1L));
		data.add(new SubscribeWithUs(2L));
		data.add(new SubscribeWithUs(3L));
		data.add(new SubscribeWithUs(4L));
		data.add(new SubscribeWithUs(5L));
		data.add(new SubscribeWithUs(6L));
		data.add(new SubscribeWithUs(7L));
		data.add(new SubscribeWithUs(8L));
		data.add(new SubscribeWithUs(9L));
		data.add(new SubscribeWithUs(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}	
	
	@Path("/rejectedSubscriptionsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<SubscribeWithUs> data = new LinkedList<SubscribeWithUs>();
		data.add(new SubscribeWithUs(1L));
		data.add(new SubscribeWithUs(2L));
		data.add(new SubscribeWithUs(3L));
		data.add(new SubscribeWithUs(4L));
		data.add(new SubscribeWithUs(5L));
		data.add(new SubscribeWithUs(6L));
		data.add(new SubscribeWithUs(7L));
		data.add(new SubscribeWithUs(8L));
		data.add(new SubscribeWithUs(9L));
		data.add(new SubscribeWithUs(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/subscriptionRequestCheckDataAccess")
	@Consumes("application/x-www-form-urlencoded")
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
	
	@Path("/blacklistSubscriptionRequests")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String blacklistSubscriptionRequests (
			@FormParam("allIdsList") final String allIdsList,
			@FormParam("comments") final String comments,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "All Ids blacklisted "+allIdsList+" "+comments);		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
	
	@Path("/nonContactedQueryList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<SubmitQuery> data = new LinkedList<SubmitQuery>();
		data.add(new SubmitQuery(1L));
		data.add(new SubmitQuery(2L));
		data.add(new SubmitQuery(3L));
		data.add(new SubmitQuery(4L));
		data.add(new SubmitQuery(5L));
		data.add(new SubmitQuery(6L));
		data.add(new SubmitQuery(7L));
		data.add(new SubmitQuery(8L));
		data.add(new SubmitQuery(9L));
		data.add(new SubmitQuery(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/nonAnsweredQueryList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<SubmitQuery> data = new LinkedList<SubmitQuery>();
		data.add(new SubmitQuery(1L));
		data.add(new SubmitQuery(2L));
		data.add(new SubmitQuery(3L));
		data.add(new SubmitQuery(4L));
		data.add(new SubmitQuery(5L));
		data.add(new SubmitQuery(6L));
		data.add(new SubmitQuery(7L));
		data.add(new SubmitQuery(8L));
		data.add(new SubmitQuery(9L));
		data.add(new SubmitQuery(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/answeredQueryList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<SubmitQuery> data = new LinkedList<SubmitQuery>();
		data.add(new SubmitQuery(1L));
		data.add(new SubmitQuery(2L));
		data.add(new SubmitQuery(3L));
		data.add(new SubmitQuery(4L));
		data.add(new SubmitQuery(5L));
		data.add(new SubmitQuery(6L));
		data.add(new SubmitQuery(7L));
		data.add(new SubmitQuery(8L));
		data.add(new SubmitQuery(9L));
		data.add(new SubmitQuery(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/submittedQueryCheckDataAccess")
	@Consumes("application/x-www-form-urlencoded")
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
	
	@Path("/customerComplaintList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<Complaint> data = new LinkedList<Complaint>();
		data.add(new Complaint(1L));
		data.add(new Complaint(2L));
		data.add(new Complaint(3L));
		data.add(new Complaint(4L));
		data.add(new Complaint(5L));
		data.add(new Complaint(6L));
		data.add(new Complaint(7L));
		data.add(new Complaint(8L));
		data.add(new Complaint(9L));
		data.add(new Complaint(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/tutorComplaintList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<Complaint> data = new LinkedList<Complaint>();
		data.add(new Complaint(1L));
		data.add(new Complaint(2L));
		data.add(new Complaint(3L));
		data.add(new Complaint(4L));
		data.add(new Complaint(5L));
		data.add(new Complaint(6L));
		data.add(new Complaint(7L));
		data.add(new Complaint(8L));
		data.add(new Complaint(9L));
		data.add(new Complaint(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/employeeComplaintList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<Complaint> data = new LinkedList<Complaint>();
		data.add(new Complaint(1L));
		data.add(new Complaint(2L));
		data.add(new Complaint(3L));
		data.add(new Complaint(4L));
		data.add(new Complaint(5L));
		data.add(new Complaint(6L));
		data.add(new Complaint(7L));
		data.add(new Complaint(8L));
		data.add(new Complaint(9L));
		data.add(new Complaint(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/resolvedComplaintList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<Complaint> data = new LinkedList<Complaint>();
		data.add(new Complaint(1L));
		data.add(new Complaint(2L));
		data.add(new Complaint(3L));
		data.add(new Complaint(4L));
		data.add(new Complaint(5L));
		data.add(new Complaint(6L));
		data.add(new Complaint(7L));
		data.add(new Complaint(8L));
		data.add(new Complaint(9L));
		data.add(new Complaint(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/complaintCheckDataAccess")
	@Consumes("application/x-www-form-urlencoded")
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
	
	@Override
	protected void doSecurity(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
