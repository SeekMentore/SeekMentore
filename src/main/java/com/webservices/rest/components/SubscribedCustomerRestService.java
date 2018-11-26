package com.webservices.rest.components;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.RestMethodConstants;
import com.constants.ScopeConstants;
import com.model.components.SubscriptionPackage;
import com.model.gridcomponent.GridComponent;
import com.utils.JSONUtils;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path("/subscribedCustomer") 
public class SubscribedCustomerRestService extends AbstractRestWebservice implements RestMethodConstants {
	
	
	@Path("/currentPackages")
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String currentPackages (
			final GridComponent gridComponent,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<SubscriptionPackage> data = new LinkedList<SubscriptionPackage>();
		data.add(new SubscriptionPackage(1L));
		data.add(new SubscriptionPackage(2L));
		data.add(new SubscriptionPackage(3L));
		data.add(new SubscriptionPackage(4L));
		data.add(new SubscriptionPackage(5L));
		data.add(new SubscriptionPackage(6L));
		data.add(new SubscriptionPackage(7L));
		data.add(new SubscriptionPackage(8L));
		data.add(new SubscriptionPackage(9L));
		data.add(new SubscriptionPackage(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/historyPackages")
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String historyPackages (
			final GridComponent gridComponent,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<SubscriptionPackage> data = new LinkedList<SubscriptionPackage>();
		data.add(new SubscriptionPackage(1L));
		data.add(new SubscriptionPackage(2L));
		data.add(new SubscriptionPackage(3L));
		data.add(new SubscriptionPackage(4L));
		data.add(new SubscriptionPackage(5L));
		data.add(new SubscriptionPackage(6L));
		data.add(new SubscriptionPackage(7L));
		data.add(new SubscriptionPackage(8L));
		data.add(new SubscriptionPackage(9L));
		data.add(new SubscriptionPackage(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/updateCustomerRecord")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateCustomerRecord (
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
