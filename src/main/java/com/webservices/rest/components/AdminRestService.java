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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.AdminConstants;
import com.model.components.RegisteredTutor;
import com.model.components.SubscribedCustomer;
import com.model.gridcomponent.GridComponent;
import com.utils.JSONUtils;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_ADMIN) 
public class AdminRestService extends AbstractRestWebservice implements RestMethodConstants, AdminConstants {
	
	@Path("/registeredTutorsList")
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String registeredTutorsList (
			final GridComponent gridComponent,
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
	
	@Path("/registeredTutorCheckDataAccess")
	@Consumes({MediaType.APPLICATION_JSON})
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
	
	@Path("/blacklistRegisteredTutors")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String blacklistRegisteredTutors (
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
	
	@Path("/subscribedCustomersList")
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String subscribedCustomersList (
			final GridComponent gridComponent,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<SubscribedCustomer> data = new LinkedList<SubscribedCustomer>();
		data.add(new SubscribedCustomer(1L));
		data.add(new SubscribedCustomer(2L));
		data.add(new SubscribedCustomer(3L));
		data.add(new SubscribedCustomer(4L));
		data.add(new SubscribedCustomer(5L));
		data.add(new SubscribedCustomer(6L));
		data.add(new SubscribedCustomer(7L));
		data.add(new SubscribedCustomer(8L));
		data.add(new SubscribedCustomer(9L));
		data.add(new SubscribedCustomer(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/subscribedCustomerCheckDataAccess")
	@Consumes({MediaType.APPLICATION_JSON})
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
	
	@Path("/blacklistSubscribedCustomers")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String blacklistSubscribedCustomers (
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

	@Override
	protected void doSecurity(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
	}
	
}
