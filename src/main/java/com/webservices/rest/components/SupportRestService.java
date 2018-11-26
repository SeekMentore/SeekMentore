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
import com.model.gridcomponent.GridComponent;
import com.utils.JSONUtils;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path("/support") 
public class SupportRestService extends AbstractRestWebservice implements RestMethodConstants {
	
	@Path("/nonContactedBecomeTutorsList")
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String nonContactedBecomeTutorsList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String nonVerifiedBecomeTutorsList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String verifiedBecomeTutorsList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String verificationFailedBecomeTutorsList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String toBeReContactedBecomeTutorsList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String selectedBecomeTutorsList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String rejectedBecomeTutorsList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String registeredBecomeTutorsList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String nonContactedEnquirysList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String nonVerifiedEnquirysList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String verifiedEnquirysList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String verificationFailedEnquirysList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String toBeReContactedEnquirysList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String selectedEnquirysList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String rejectedEnquirysList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String nonContactedSubscriptionsList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String nonVerifiedSubscriptionsList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String verifiedSubscriptionsList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String verificationFailedSubscriptionsList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String toBeReContactedSubscriptionsList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String selectedSubscriptionsList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String rejectedSubscriptionsList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String nonContactedQueryList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String nonAnsweredQueryList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String answeredQueryList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String customerComplaintList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String tutorComplaintList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String employeeComplaintList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String resolvedComplaintList (
			final GridComponent gridComponent,
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
	@Consumes({MediaType.APPLICATION_JSON})
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
