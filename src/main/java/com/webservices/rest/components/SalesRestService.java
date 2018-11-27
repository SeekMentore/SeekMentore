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
import com.model.components.DemoTracker;
import com.model.components.Enquiries;
import com.model.components.RegisteredTutor;
import com.model.components.TutorMapper;
import com.utils.JSONUtils;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path("/sales") 
public class SalesRestService extends AbstractRestWebservice implements RestMethodConstants {
	
	@Path("/pendingEnquiriesList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<Enquiries> data = new LinkedList<Enquiries>();
		data.add(new Enquiries(1L));
		data.add(new Enquiries(2L));
		data.add(new Enquiries(3L));
		data.add(new Enquiries(4L));
		data.add(new Enquiries(5L));
		data.add(new Enquiries(6L));
		data.add(new Enquiries(7L));
		data.add(new Enquiries(8L));
		data.add(new Enquiries(9L));
		data.add(new Enquiries(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/completedEnquiriesList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<Enquiries> data = new LinkedList<Enquiries>();
		data.add(new Enquiries(1L));
		data.add(new Enquiries(2L));
		data.add(new Enquiries(3L));
		data.add(new Enquiries(4L));
		data.add(new Enquiries(5L));
		data.add(new Enquiries(6L));
		data.add(new Enquiries(7L));
		data.add(new Enquiries(8L));
		data.add(new Enquiries(9L));
		data.add(new Enquiries(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/abortedEnquiriesList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<Enquiries> data = new LinkedList<Enquiries>();
		data.add(new Enquiries(1L));
		data.add(new Enquiries(2L));
		data.add(new Enquiries(3L));
		data.add(new Enquiries(4L));
		data.add(new Enquiries(5L));
		data.add(new Enquiries(6L));
		data.add(new Enquiries(7L));
		data.add(new Enquiries(8L));
		data.add(new Enquiries(9L));
		data.add(new Enquiries(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/pendingEnquiryCheckDataAccess")
	@Consumes("application/x-www-form-urlencoded")
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
	
	@Path("/updateEnquiryRecord")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateEnquiryRecord (
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
	
	@Path("/currentCustomerAllPendingEnquiriesList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<Enquiries> data = new LinkedList<Enquiries>();
		data.add(new Enquiries(1L));
		data.add(new Enquiries(2L));
		data.add(new Enquiries(3L));
		data.add(new Enquiries(4L));
		data.add(new Enquiries(5L));
		data.add(new Enquiries(6L));
		data.add(new Enquiries(7L));
		data.add(new Enquiries(8L));
		data.add(new Enquiries(9L));
		data.add(new Enquiries(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/toBeMappedEnquiriesGridList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<Enquiries> data = new LinkedList<Enquiries>();
		data.add(new Enquiries(1L));
		data.add(new Enquiries(2L));
		data.add(new Enquiries(3L));
		data.add(new Enquiries(4L));
		data.add(new Enquiries(5L));
		data.add(new Enquiries(6L));
		data.add(new Enquiries(7L));
		data.add(new Enquiries(8L));
		data.add(new Enquiries(9L));
		data.add(new Enquiries(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/mapTutorToEnquiryCheckDataAccess")
	@Consumes("application/x-www-form-urlencoded")
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
	
	@Path("/allMappingEligibleTutorsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<RegisteredTutor> data = new LinkedList<RegisteredTutor>();
		data.add(new RegisteredTutor(1L));
		data.add(new RegisteredTutor(2L));
		data.add(new RegisteredTutor(3L));
		data.add(new RegisteredTutor(4L));
		data.add(new RegisteredTutor(5L));
		data.add(new RegisteredTutor(6L));
		data.add(new RegisteredTutor(7L));
		data.add(new RegisteredTutor(8L));
		data.add(new RegisteredTutor(9L));
		data.add(new RegisteredTutor(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/mapRegisteredTutors")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String mapRegisteredTutors (
			@FormParam("enquiryId") final String enquiryId,
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
	
	@Path("/mapRegisteredTutor")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String mapRegisteredTutor (
			@FormParam("enquiryId") final String enquiryId,
			@FormParam("tutorId") final String tutorId,
			@FormParam("comments") final String comments,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "All Ids mapped "+tutorId+" "+comments);		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/allMappedTutorsList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<TutorMapper> data = new LinkedList<TutorMapper>();
		data.add(new TutorMapper(1L));
		data.add(new TutorMapper(2L));
		data.add(new TutorMapper(3L));
		data.add(new TutorMapper(4L));
		data.add(new TutorMapper(5L));
		data.add(new TutorMapper(6L));
		data.add(new TutorMapper(7L));
		data.add(new TutorMapper(8L));
		data.add(new TutorMapper(9L));
		data.add(new TutorMapper(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/unmapRegisteredTutors")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String unmapRegisteredTutors (
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
	
	@Path("/unmapRegisteredTutor")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String unmapRegisteredTutor (
			@FormParam("tutorMapperId") final String tutorMapperId,
			@FormParam("comments") final String comments,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "All Ids mapped "+tutorMapperId+" "+comments);		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/mappedTutorCheckDataAccess")
	@Consumes("application/x-www-form-urlencoded")
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
	
	@Path("/updateTutorMapperRecord")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateTutorMapperRecord (
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
	
	@Path("/allDemoReadyMappedTutorsList")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String allDemoReadyMappedTutorsList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<TutorMapper> data = new LinkedList<TutorMapper>();
		data.add(new TutorMapper(1L));
		data.add(new TutorMapper(2L));
		data.add(new TutorMapper(3L));
		data.add(new TutorMapper(4L));
		data.add(new TutorMapper(5L));
		data.add(new TutorMapper(6L));
		data.add(new TutorMapper(7L));
		data.add(new TutorMapper(8L));
		data.add(new TutorMapper(9L));
		data.add(new TutorMapper(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/mappedTutorCheckScheduleDemoAccess")
	@Consumes("application/x-www-form-urlencoded")
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
	
	@Path("/currentTutorAllMappingList")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String currentTutorAllMappingList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<TutorMapper> data = new LinkedList<TutorMapper>();
		data.add(new TutorMapper(1L));
		data.add(new TutorMapper(2L));
		data.add(new TutorMapper(3L));
		data.add(new TutorMapper(4L));
		data.add(new TutorMapper(5L));
		data.add(new TutorMapper(6L));
		data.add(new TutorMapper(7L));
		data.add(new TutorMapper(8L));
		data.add(new TutorMapper(9L));
		data.add(new TutorMapper(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/currentTutorAllScheduledDemoList")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String currentTutorAllScheduledDemoList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<DemoTracker> data = new LinkedList<DemoTracker>();
		data.add(new DemoTracker(1L));
		data.add(new DemoTracker(2L));
		data.add(new DemoTracker(3L));
		data.add(new DemoTracker(4L));
		data.add(new DemoTracker(5L));
		data.add(new DemoTracker(6L));
		data.add(new DemoTracker(7L));
		data.add(new DemoTracker(8L));
		data.add(new DemoTracker(9L));
		data.add(new DemoTracker(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/updateScheduleDemoMappedTutorRecord")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateScheduleDemoMappedTutorRecord (
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
	
	@Path("/scheduledDemoList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<DemoTracker> data = new LinkedList<DemoTracker>();
		data.add(new DemoTracker(1L));
		data.add(new DemoTracker(2L));
		data.add(new DemoTracker(3L));
		data.add(new DemoTracker(4L));
		data.add(new DemoTracker(5L));
		data.add(new DemoTracker(6L));
		data.add(new DemoTracker(7L));
		data.add(new DemoTracker(8L));
		data.add(new DemoTracker(9L));
		data.add(new DemoTracker(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/reScheduledDemoList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<DemoTracker> data = new LinkedList<DemoTracker>();
		data.add(new DemoTracker(1L));
		data.add(new DemoTracker(2L));
		data.add(new DemoTracker(3L));
		data.add(new DemoTracker(4L));
		data.add(new DemoTracker(5L));
		data.add(new DemoTracker(6L));
		data.add(new DemoTracker(7L));
		data.add(new DemoTracker(8L));
		data.add(new DemoTracker(9L));
		data.add(new DemoTracker(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/successfulDemoList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<DemoTracker> data = new LinkedList<DemoTracker>();
		data.add(new DemoTracker(1L));
		data.add(new DemoTracker(2L));
		data.add(new DemoTracker(3L));
		data.add(new DemoTracker(4L));
		data.add(new DemoTracker(5L));
		data.add(new DemoTracker(6L));
		data.add(new DemoTracker(7L));
		data.add(new DemoTracker(8L));
		data.add(new DemoTracker(9L));
		data.add(new DemoTracker(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/failedDemoList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<DemoTracker> data = new LinkedList<DemoTracker>();
		data.add(new DemoTracker(1L));
		data.add(new DemoTracker(2L));
		data.add(new DemoTracker(3L));
		data.add(new DemoTracker(4L));
		data.add(new DemoTracker(5L));
		data.add(new DemoTracker(6L));
		data.add(new DemoTracker(7L));
		data.add(new DemoTracker(8L));
		data.add(new DemoTracker(9L));
		data.add(new DemoTracker(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/cancelledDemoGridList")
	@Consumes("application/x-www-form-urlencoded")
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
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<DemoTracker> data = new LinkedList<DemoTracker>();
		data.add(new DemoTracker(1L));
		data.add(new DemoTracker(2L));
		data.add(new DemoTracker(3L));
		data.add(new DemoTracker(4L));
		data.add(new DemoTracker(5L));
		data.add(new DemoTracker(6L));
		data.add(new DemoTracker(7L));
		data.add(new DemoTracker(8L));
		data.add(new DemoTracker(9L));
		data.add(new DemoTracker(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/demoTrackerModifyCheckDataAccess")
	@Consumes("application/x-www-form-urlencoded")
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
	
	@Path("/cancelDemos")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String cancelDemos (
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
	
	@Path("/updateDemoTrackerRecord")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateDemoTrackerRecord (
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
