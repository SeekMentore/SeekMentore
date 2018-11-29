package com.webservices.rest.components;

import java.io.InputStream;
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
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.TutorConstants;
import com.model.components.BankDetail;
import com.model.components.SubscriptionPackage;
import com.model.components.TutorDocument;
import com.model.gridcomponent.GridComponent;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.components.CommonsService;
import com.service.components.SubscriptionPackageService;
import com.service.components.TutorService;
import com.utils.ApplicationUtils;
import com.utils.GridComponentUtils;
import com.utils.JSONUtils;
import com.utils.ValidationUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_REGISTERED_TUTOR_ADMIN) 
public class RegisteredTutorRestService extends AbstractRestWebservice implements RestMethodConstants, TutorConstants {
	
	private Long tutorId;
	
	@Path(REST_METHOD_NAME_UPLOADED_DOCUMENTS)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String uploadedDocuments (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UPLOADED_DOCUMENTS;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, TutorDocument.class);
		tutorId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "tutorId", Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<TutorDocument> tutorDocumentsList = getTutorService().getTutorDocuments(tutorId, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, tutorDocumentsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(tutorDocumentsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_BANK_DETAILS)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String bankDetails (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_BANK_DETAILS;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, BankDetail.class);
		tutorId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "tutorId", Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<BankDetail> bankDetailsList = getTutorService().getBankDetails(tutorId, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, bankDetailsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(bankDetailsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_CURRENT_PACKAGES)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String currentPackages (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_CURRENT_PACKAGES;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscriptionPackage.class);
		tutorId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "tutorId", Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscriptionPackage> subscriptionPackagesList = getSubscriptionPackageService().getSubscriptionPackageListForTutor(REST_METHOD_NAME_CURRENT_PACKAGES, tutorId, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionPackagesList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionPackagesList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_HISTORY_PACKAGES)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String historyPackages (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_HISTORY_PACKAGES;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscriptionPackage.class);
		tutorId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "tutorId", Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscriptionPackage> subscriptionPackagesList = getSubscriptionPackageService().getSubscriptionPackageListForTutor(REST_METHOD_NAME_HISTORY_PACKAGES, tutorId, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionPackagesList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionPackagesList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
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
	
	public TutorService getTutorService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_TUTOR_SERVICE, TutorService.class);
	}
	
	public SubscriptionPackageService getSubscriptionPackageService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_SUBSCRIPTION_PACKAGE_SERVICE, SubscriptionPackageService.class);
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
			case REST_METHOD_NAME_UPLOADED_DOCUMENTS : 
			case REST_METHOD_NAME_BANK_DETAILS :
			case REST_METHOD_NAME_CURRENT_PACKAGES : 
			case REST_METHOD_NAME_HISTORY_PACKAGES :{
				handleSelectedTutorDataGridView();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
	
	private void handleSelectedTutorDataGridView() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.tutorId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_TUTOR_ID_ABSENT,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	} 
}
