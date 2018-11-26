package com.webservices.rest.components;

import java.io.InputStream;
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

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.RestMethodConstants;
import com.constants.ScopeConstants;
import com.model.components.BankAccount;
import com.model.components.SubscriptionPackage;
import com.model.components.TutorDocument;
import com.model.gridcomponent.GridComponent;
import com.utils.JSONUtils;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path("/registeredTutor") 
public class RegisteredTutorRestService extends AbstractRestWebservice implements RestMethodConstants {
	
	
	@Path("/uploadedDocuments")
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String uploadedDocuments (
			final GridComponent gridComponent,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<TutorDocument> data = new LinkedList<TutorDocument>();
		data.add(new TutorDocument(1L));
		data.add(new TutorDocument(2L));
		data.add(new TutorDocument(3L));
		data.add(new TutorDocument(4L));
		data.add(new TutorDocument(5L));
		data.add(new TutorDocument(6L));
		data.add(new TutorDocument(7L));
		data.add(new TutorDocument(8L));
		data.add(new TutorDocument(9L));
		data.add(new TutorDocument(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/bankDetails")
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String bankDetails (
			final GridComponent gridComponent,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<BankAccount> data = new LinkedList<BankAccount>();
		data.add(new BankAccount(1L));
		data.add(new BankAccount(2L));
		data.add(new BankAccount(3L));
		data.add(new BankAccount(4L));
		data.add(new BankAccount(5L));
		data.add(new BankAccount(6L));
		data.add(new BankAccount(7L));
		data.add(new BankAccount(8L));
		data.add(new BankAccount(9L));
		data.add(new BankAccount(10L));		
		restresponse.put("data", data);
		restresponse.put("totalRecords", data.size());
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
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
	
	@Path("/approveTutorDocument")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String approveTutorDocument (
			@FormParam("selectedId") final String selectedId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "Document Approved");		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/approveMultipleTutorDocument")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String approveMultipleTutorDocument (
			@FormParam("selectedIdsList") final String selectedIdsList,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "All Documents Approved");		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/sendReminderTutorDocument")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String sendReminderTutorDocument (
			@FormParam("selectedId") final String selectedId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "Document Sent Reminder");		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/sendReminderMultipleTutorDocument")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String sendReminderMultipleTutorDocument (
			@FormParam("selectedIdsList") final String selectedIdsList,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "All Documents Sent Reminder");		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/rejectTutorDocument")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String rejectTutorDocument (
			@FormParam("selectedId") final String selectedId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "Document Rejected");		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/rejectMultipleTutorDocument")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String rejectMultipleTutorDocument (
			@FormParam("selectedIdsList") final String selectedIdsList,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "All Documents Rejected");		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/approveBankAccount")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String approveBankAccount (
			@FormParam("selectedId") final String selectedId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "Bank Account Approved");		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/approveMultipleBankAccount")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String approveMultipleBankAccount (
			@FormParam("selectedIdsList") final String selectedIdsList,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "All Bank Accounts Approved");		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/makeDefaultBankAccount")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String makeDefaultBankAccount (
			@FormParam("selectedId") final String selectedId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "Bank Account Made Default");		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/rejectBankAccount")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String rejectBankAccount (
			@FormParam("selectedId") final String selectedId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "Bank Account Rejected");		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/rejectMultipleBankAccount")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String rejectMultipleBankAccount (
			@FormParam("selectedIdsList") final String selectedIdsList,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put("success", true);
		restresponse.put("message", "All Bank Accounts Rejected");		
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/updateTutorRecord")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateTutorRecord (
			@FormDataParam("completeUpdatedRecord") final String completeUpdatedRecord,
			@FormDataParam("parentId") final String parentId,
			@FormDataParam("inputFilePANCard") final InputStream uploadedInputStreamFilePANCard,
			@FormDataParam("inputFilePANCard") final FormDataContentDisposition uploadedFileDetailFilePANCard,
			@FormDataParam("inputFileAadhaarCard") final InputStream uploadedInputStreamFileAadhaarCard,
			@FormDataParam("inputFileAadhaarCard") final FormDataContentDisposition uploadedFileDetailFileAadhaarCard,
			@FormDataParam("inputFilePhoto") final InputStream uploadedInputStreamFilePhoto,
			@FormDataParam("inputFilePhoto") final FormDataContentDisposition uploadedFileDetailFilePhoto,
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
	}
	
}
