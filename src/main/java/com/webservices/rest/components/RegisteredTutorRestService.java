package com.webservices.rest.components;

import java.io.InputStream;
import java.util.ArrayList;
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

import org.apache.poi.util.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.FileConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.AdminConstants;
import com.constants.components.SelectLookupConstants;
import com.constants.components.TutorConstants;
import com.constants.components.publicaccess.BecomeTutorConstants;
import com.model.components.BankDetail;
import com.model.components.RegisteredTutor;
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
	private RegisteredTutor registeredTutorObject;
	private Long parentId;
	
	@Path(REST_METHOD_NAME_UPLOADED_DOCUMENT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String uploadedDocumentList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UPLOADED_DOCUMENT_LIST;
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
	
	@Path(REST_METHOD_NAME_BANK_DETAIL_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String bankDetailList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_BANK_DETAIL_LIST;
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
	
	@Path(REST_METHOD_NAME_CURRENT_PACKAGE_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String currentPackageList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_CURRENT_PACKAGE_LIST;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscriptionPackage.class);
		tutorId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), REQUEST_PARAM_TUTOR_ID, Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscriptionPackage> subscriptionPackagesList = getSubscriptionPackageService().getSubscriptionPackageListForTutor(REST_METHOD_NAME_CURRENT_PACKAGE_LIST, tutorId, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionPackagesList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionPackagesList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_HISTORY_PACKAGE_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String historyPackageList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_HISTORY_PACKAGE_LIST;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscriptionPackage.class);
		tutorId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), REQUEST_PARAM_TUTOR_ID, Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscriptionPackage> subscriptionPackagesList = getSubscriptionPackageService().getSubscriptionPackageListForTutor(REST_METHOD_NAME_HISTORY_PACKAGE_LIST, tutorId, gridComponent);
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
	
	@Path(REST_METHOD_NAME_UPDATE_TUTOR_RECORD)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateTutorRecord (
			@FormDataParam(REQUEST_PARAM_COMPLETE_UPDATED_RECORD) final String completeUpdatedRecord,
			@FormDataParam(REQUEST_PARAM_PARENT_ID) final String parentId,
			@FormDataParam("inputFilePANCard") final InputStream uploadedInputStreamFilePANCard,
			@FormDataParam("inputFilePANCard") final FormDataContentDisposition uploadedFileDetailFilePANCard,
			@FormDataParam("inputFileAadhaarCard") final InputStream uploadedInputStreamFileAadhaarCard,
			@FormDataParam("inputFileAadhaarCard") final FormDataContentDisposition uploadedFileDetailFileAadhaarCard,
			@FormDataParam("inputFilePhoto") final InputStream uploadedInputStreamFilePhoto,
			@FormDataParam("inputFilePhoto") final FormDataContentDisposition uploadedFileDetailFilePhoto,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UPDATE_TUTOR_RECORD;
		createRegisteredTutorObjectFromCompleteUpdatedRecordJSONObject(JSONUtils.getJSONObjectFromString(completeUpdatedRecord));
		final List<TutorDocument> tutorDocuments = new ArrayList<TutorDocument>();
		if (null != uploadedInputStreamFilePANCard) {
			byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFilePANCard);
			if (fileBytes.length > 0) {
				tutorDocuments.add(new TutorDocument("PAN_CARD", uploadedFileDetailFilePANCard.getFileName(), fileBytes));
			}
		}
		if (null != uploadedInputStreamFileAadhaarCard) {
			byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFileAadhaarCard);
			if (fileBytes.length > 0) {
				tutorDocuments.add(new TutorDocument("AADHAAR_CARD", uploadedFileDetailFilePANCard.getFileName(), fileBytes));
			}
		}
		if (null != uploadedInputStreamFilePhoto) {
			byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFilePhoto);
			if (fileBytes.length > 0) {
				tutorDocuments.add(new TutorDocument("PROFILE_PHOTO", uploadedFileDetailFilePANCard.getFileName(), fileBytes));
			}
		}
		if (ValidationUtils.checkNonEmptyList(tutorDocuments)) {
			this.registeredTutorObject.setDocuments(tutorDocuments);
			this.changedAttributes.add("documents");
		}
		try {
			this.parentId = Long.parseLong(parentId);
		} catch(NumberFormatException e) {}
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			this.registeredTutorObject.setTutorId(Long.parseLong(parentId));
			getTutorService().updateTutorRecord(this.registeredTutorObject, this.changedAttributes, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "Updated record");
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
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
			case REST_METHOD_NAME_UPLOADED_DOCUMENT_LIST : 
			case REST_METHOD_NAME_BANK_DETAIL_LIST :
			case REST_METHOD_NAME_CURRENT_PACKAGE_LIST : 
			case REST_METHOD_NAME_HISTORY_PACKAGE_LIST : {
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
			case REST_METHOD_NAME_UPDATE_TUTOR_RECORD : {
				handleParentId();
				handleTutorSecurity();
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
	
	private void handleParentId() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.parentId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_PARENT_ID_ABSENT,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	private void createRegisteredTutorObjectFromCompleteUpdatedRecordJSONObject(final JsonObject jsonObject) {
		if (ValidationUtils.checkObjectAvailability(jsonObject)) {
			this.registeredTutorObject = new RegisteredTutor();
			this.registeredTutorObject.setName(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "name", String.class));
			this.registeredTutorObject.setDateOfBirth(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "dateOfBirth", Date.class));
			this.registeredTutorObject.setContactNumber(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "contactNumber", String.class));
			this.registeredTutorObject.setEmailId(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "emailId", String.class));
			this.registeredTutorObject.setGender(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "gender", String.class));
			this.registeredTutorObject.setQualification(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "qualification", String.class));
			this.registeredTutorObject.setPrimaryProfession(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "primaryProfession", String.class));
			this.registeredTutorObject.setTransportMode(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "transportMode", String.class));
			this.registeredTutorObject.setTeachingExp(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "teachingExp", Integer.class));
			this.registeredTutorObject.setInterestedStudentGrades(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "interestedStudentGrades", String.class));
			this.registeredTutorObject.setInterestedSubjects(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "interestedSubjects", String.class));
			this.registeredTutorObject.setComfortableLocations(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "comfortableLocations", String.class));
			this.registeredTutorObject.setAdditionalDetails(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "additionalDetails", String.class));
			this.registeredTutorObject.setPreferredTeachingType(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "preferredTeachingType", String.class));
		}
	}
	
	private void handleTutorSecurity() throws Exception {
		this.securityPassed = true;
		if (ValidationUtils.checkNonEmptyList(this.changedAttributes)) {
			for (final String attributeName : this.changedAttributes) {
				switch(attributeName) {
					case "name" : {
						if (!ValidationUtils.validateNameString(this.registeredTutorObject.getName(), true)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_FIRST_NAME,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "contactNumber" : {
						if (!ValidationUtils.validatePhoneNumber(this.registeredTutorObject.getContactNumber(), 10)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_CONTACT_NUMBER_MOBILE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "emailId" : {
						if (!ValidationUtils.validateEmailAddress(this.registeredTutorObject.getEmailId())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_EMAIL_ID,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "dateOfBirth" : {
						if (!ValidationUtils.validateDate(this.registeredTutorObject.getDateOfBirth())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_DATE_OF_BIRTH,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "gender" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.registeredTutorObject.getGender(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_GENDER_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_GENDER,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "qualification" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.registeredTutorObject.getQualification(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_QUALIFICATION,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "primaryProfession" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.registeredTutorObject.getPrimaryProfession(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_PRIMARY_PROFESSION,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "transportMode" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.registeredTutorObject.getTransportMode(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_TRANSPORT_MODE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "interestedStudentGrades" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.registeredTutorObject.getInterestedStudentGrades(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_STUDENT_GRADE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "interestedSubjects" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.registeredTutorObject.getInterestedSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_SUBJECTS,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "comfortableLocations" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.registeredTutorObject.getComfortableLocations(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_LOCATIONS,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "preferredTeachingType" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.registeredTutorObject.getPreferredTeachingType(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_TUTORING_TYPE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "teachingExp" : {
						if (!ValidationUtils.validateNumber(this.registeredTutorObject.getTeachingExp(), true, 99, false, 0)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_TEACHING_EXPERIENCE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "additionalDetails" : {
						break;
					}
					case "documents" : {
						if (ValidationUtils.checkNonEmptyList(this.registeredTutorObject.getDocuments())) {
							for (final TutorDocument document : this.registeredTutorObject.getDocuments()) {
								final String fileExtension = document.getFilename().substring(document.getFilename().lastIndexOf(PERIOD) + 1);
								if (!(FileConstants.EXTENSION_PDF.equalsIgnoreCase(fileExtension) 
										|| FileConstants.EXTENSION_JPEG.equalsIgnoreCase(fileExtension)
										|| FileConstants.EXTENSION_JPG.equalsIgnoreCase(fileExtension)
										|| FileConstants.EXTENSION_PNG.equalsIgnoreCase(fileExtension))) {
									ApplicationUtils.appendMessageInMapAttribute(
											this.securityFailureResponse, 
											VALIDATION_MESSAGE_INVALID_FILE_TYPE_FOR_DOCUMENTS,
											RESPONSE_MAP_ATTRIBUTE_MESSAGE);
									this.securityPassed = false;
									break;
								}
								if (ApplicationUtils.computeFileSizeInMB((long)document.getContent().length) > MAXIMUM_FILE_SIZE_FOR_DOCUMENTS_IN_MB) {
									ApplicationUtils.appendMessageInMapAttribute(
											this.securityFailureResponse, 
											VALIDATION_MESSAGE_INVALID_FILE_SIZE_FOR_DOCUMENTS,
											RESPONSE_MAP_ATTRIBUTE_MESSAGE);
									this.securityPassed = false;
									break;
								}
							}
						}
						break;
					}
					default : {
						ApplicationUtils.appendMessageInMapAttribute(
								this.securityFailureResponse, 
								AdminConstants.VALIDATION_MESSAGE_UNKONWN_PROPERTY,
								RESPONSE_MAP_ATTRIBUTE_MESSAGE);
						this.securityPassed = false;
						break;
					}
				}
			}
		} else {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_NO_ATTRIBUTES_CHANGED,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
}
