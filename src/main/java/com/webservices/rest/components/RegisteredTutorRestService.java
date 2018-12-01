package com.webservices.rest.components;

import java.io.InputStream;
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
import com.constants.components.AdminConstants;
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
	private String allIdsList;
	private String comments;
	private Long bankAccountId;
	
	@Path(REST_METHOD_NAME_UPLOADED_DOCUMENTS)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
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
		tutorId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), REQUEST_PARAM_TUTOR_ID, Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<TutorDocument> tutorDocumentsList = getTutorService().getTutorDocumentList(tutorId, gridComponent);
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
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
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
		tutorId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), REQUEST_PARAM_TUTOR_ID, Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<BankDetail> bankDetailsList = getTutorService().getBankDetailList(tutorId, gridComponent);
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
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
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
		tutorId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), REQUEST_PARAM_TUTOR_ID, Long.class);
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
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
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
		tutorId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), REQUEST_PARAM_TUTOR_ID, Long.class);
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
	
	@Path(REST_METHOD_NAME_APPROVE_TUTOR_DOCUMENT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String approveTutorDocumentList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_TUTOR_ID) final String tutorId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_APPROVE_TUTOR_DOCUMENT_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		try {
			this.tutorId = Long.parseLong(tutorId);
		} catch(NumberFormatException e) {}
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getTutorService().aprroveTutorDocumentList(Arrays.asList(allIdsList.split(SEMICOLON)), Long.parseLong(tutorId), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_SEND_REMINDER_TUTOR_DOCUMENT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String sendReminderTutorDocumentList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_TUTOR_ID) final String tutorId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_SEND_REMINDER_TUTOR_DOCUMENT_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		try {
			this.tutorId = Long.parseLong(tutorId);
		} catch(NumberFormatException e) {}
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getTutorService().sendTutorDocumentListReminderEmails(Arrays.asList(allIdsList.split(SEMICOLON)), Long.parseLong(tutorId), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "Sent Reminder for document/s successfully.");
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_REJECT_TUTOR_DOCUMENT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String rejectTutorDocumentList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_TUTOR_ID) final String tutorId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_REJECT_TUTOR_DOCUMENT_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		try {
			this.tutorId = Long.parseLong(tutorId);
		} catch(NumberFormatException e) {}
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getTutorService().rejectTutorDocumentList(Arrays.asList(allIdsList.split(SEMICOLON)), Long.parseLong(tutorId), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_APPROVE_BANK_ACCOUNT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String approveBankAccountList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_TUTOR_ID) final String tutorId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_APPROVE_BANK_ACCOUNT_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		try {
			this.tutorId = Long.parseLong(tutorId);
		} catch(NumberFormatException e) {}
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getTutorService().aprroveBankAccountList(Arrays.asList(allIdsList.split(SEMICOLON)), Long.parseLong(tutorId), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_MAKE_DEFAULT_BANK_ACCOUNT)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String makeDefaultBankAccount (
			@FormParam("bankAccountId") final String bankAccountId,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_TUTOR_ID) final String tutorId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_MAKE_DEFAULT_BANK_ACCOUNT;
		this.comments = comments;
		try {
			this.tutorId = Long.parseLong(tutorId);
		} catch(NumberFormatException e) {}
		try {
			this.bankAccountId = Long.parseLong(bankAccountId);
		} catch(NumberFormatException e) {}
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getTutorService().makeDefaultBankAccount(Long.parseLong(bankAccountId), Long.parseLong(tutorId), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_REJECT_BANK_ACCOUNT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String rejectBankAccountList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_TUTOR_ID) final String tutorId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_REJECT_BANK_ACCOUNT_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		try {
			this.tutorId = Long.parseLong(tutorId);
		} catch(NumberFormatException e) {}
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getTutorService().rejectBankAccountList(Arrays.asList(allIdsList.split(SEMICOLON)), Long.parseLong(tutorId), comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
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
			case REST_METHOD_NAME_HISTORY_PACKAGES : {
				handleSelectedTutorDataView();
				break;
			}
			case REST_METHOD_NAME_APPROVE_TUTOR_DOCUMENT_LIST :
			case REST_METHOD_NAME_SEND_REMINDER_TUTOR_DOCUMENT_LIST :
			case REST_METHOD_NAME_APPROVE_BANK_ACCOUNT_LIST : {
				handleSelectedTutorDataView();
				handleAllIds();
				break;
			}
			case REST_METHOD_NAME_MAKE_DEFAULT_BANK_ACCOUNT : {
				handleSelectedTutorDataView();
				handleBankDetailModification();
				break;
			}
			case REST_METHOD_NAME_REJECT_TUTOR_DOCUMENT_LIST : 
			case REST_METHOD_NAME_REJECT_BANK_ACCOUNT_LIST : {
				handleSelectedTutorDataView();
				handleAllIds();
				handleComments();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
	
	private void handleSelectedTutorDataView() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.tutorId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_TUTOR_ID_ABSENT,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	private void handleBankDetailModification() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.bankAccountId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_TUTOR_ID_ABSENT,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
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
}
