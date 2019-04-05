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
import com.constants.components.publicaccess.FindTutorConstants;
import com.model.components.BankDetail;
import com.model.components.RegisteredTutor;
import com.model.components.SubscriptionPackage;
import com.model.components.TutorDocument;
import com.model.components.publicaccess.BecomeTutor;
import com.model.gridcomponent.GridComponent;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.components.AdminService;
import com.service.components.CommonsService;
import com.service.components.SubscriptionPackageService;
import com.service.components.TutorService;
import com.utils.ApplicationUtils;
import com.utils.GridComponentUtils;
import com.utils.JSONUtils;
import com.utils.ValidationUtils;
import com.utils.context.AppContext;
import com.utils.localization.Message;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_REGISTERED_TUTOR_ADMIN) 
public class RegisteredTutorRestService extends AbstractRestWebservice implements RestMethodConstants, TutorConstants {
	
	private String tutorSerialId;
	private String documentSerialId;
	private String bankAccountSerialId;
	private String documentType;
	private RegisteredTutor registeredTutorObject;
	
	@Path(REST_METHOD_NAME_GET_REGISTERED_TUTOR_RECORD)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String getRegisteredTutorRecord (
			@FormParam(REQUEST_PARAM_PARENT_SERIAL_ID) final String parentSerialId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_GET_REGISTERED_TUTOR_RECORD;
		this.parentSerialId = parentSerialId;
		this.tutorSerialId = parentSerialId;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final RegisteredTutor registeredTutor = getTutorService().getRegisteredTutor(this.tutorSerialId);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_RECORD_OBJECT, registeredTutor);
			ApplicationUtils.copyAllPropertiesOfOneMapIntoAnother(getTutorService().getRecordFormUpdateStatus(registeredTutor), restresponse);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
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
		this.tutorSerialId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), REQUEST_PARAM_TUTOR_SERIAL_ID, String.class);
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<TutorDocument> tutorDocumentsList = getTutorService().getTutorDocumentList(this.tutorSerialId, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, tutorDocumentsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(tutorDocumentsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_REGISTERED_TUTOR_PROFILE_PDF)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadAdminRegisteredTutorProfilePdf (
    		@FormParam(REQUEST_PARAM_TUTOR_SERIAL_ID) final String tutorSerialId,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_REGISTERED_TUTOR_PROFILE_PDF;
		this.tutorSerialId = tutorSerialId;
		doSecurity(request, response);
		if (this.securityPassed) {
			downloadFile(getTutorService().downloadRegisteredTutorProfilePdf(this.tutorSerialId, true), response);
		}
    }
	
	@Path(REST_METHOD_NAME_GET_REGISTERED_TUTOR_DOCUMENT_COUNT_AND_EXISTENCE)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String getRegisteredTutorDocumentCountAndExistence (
			@FormParam(REQUEST_PARAM_TUTOR_SERIAL_ID) final String tutorSerialId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_GET_REGISTERED_TUTOR_DOCUMENT_COUNT_AND_EXISTENCE;
		this.tutorSerialId = tutorSerialId;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_RECORD_OBJECT, getTutorService().getRegisteredTutorDocumentCountAndExistence(this.tutorSerialId));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DOWNLOAD_TUTOR_DOCUMENT)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadTutorDocument (
    		@FormParam(REQUEST_PARAM_DOCUMENT_SERIAL_ID) final String documentSerialId,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_TUTOR_DOCUMENT;
		this.documentSerialId = documentSerialId;
		doSecurity(request, response);
		if (this.securityPassed) {
			downloadFile(getTutorService().downloadTutorDocument(this.documentSerialId), response);
		}
    }
	
	@Path(REST_METHOD_NAME_DOWNLOAD_REGISTERED_TUTOR_DOCUMENT_FILE)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadRegisteredTutorDocumentFile (
    		@FormParam(REQUEST_PARAM_TUTOR_SERIAL_ID) final String tutorSerialId,
    		@FormParam(REQUEST_PARAM_DOCUMENT_TYPE) final String documentType,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_REGISTERED_TUTOR_DOCUMENT_FILE;
		this.tutorSerialId = tutorSerialId;
		this.documentType = documentType;
		doSecurity(request, response);
		if (this.securityPassed) {
			downloadFile(getTutorService().downloadRegisteredTutorDocumentFile(this.tutorSerialId, this.documentType), response);
		}
    }
	
	@Path(REST_METHOD_NAME_DOWNLOAD_REGISTERED_TUTOR_ALL_DOCUMENTS)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadRegisteredTutorAllDocuments (
    		@FormParam(REQUEST_PARAM_TUTOR_SERIAL_ID) final String tutorSerialId,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_REGISTERED_TUTOR_ALL_DOCUMENTS;
		this.tutorSerialId = tutorSerialId;
		doSecurity(request, response);
		if (this.securityPassed) {
			downloadZipFile(getTutorService().downloadRegisteredTutorAllDocuments(this.tutorSerialId), this.tutorSerialId + "_DOCUMENTS" + PERIOD + FileConstants.EXTENSION_ZIP, response);
		}
    }
	
	@Path(REST_METHOD_NAME_REMOVE_REGISTERED_TUTOR_DOCUMENT_FILE)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String removeRegisteredTutorDocumentFile (
			@FormParam(REQUEST_PARAM_TUTOR_SERIAL_ID) final String tutorSerialId,
    		@FormParam(REQUEST_PARAM_DOCUMENT_TYPE) final String documentType,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_REMOVE_REGISTERED_TUTOR_DOCUMENT_FILE;
		this.tutorSerialId = tutorSerialId;
		this.documentType = documentType;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getTutorService().removeRegisteredTutorDocumentFile(this.tutorSerialId, this.documentType, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_REMOVED_DOCUMENT_TYPE, documentType);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
		this.tutorSerialId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), REQUEST_PARAM_TUTOR_SERIAL_ID, String.class);
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<BankDetail> bankDetailsList = getTutorService().getBankDetailList(this.tutorSerialId, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, bankDetailsList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(bankDetailsList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_CURRENT_SUBSCRIPTION_PACKAGE_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String currentSubscriptionPackageList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_CURRENT_SUBSCRIPTION_PACKAGE_LIST;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscriptionPackage.class);
		this.tutorSerialId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), REQUEST_PARAM_TUTOR_SERIAL_ID, String.class);
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscriptionPackage> subscriptionPackagesList = getSubscriptionPackageService().getSubscriptionPackageListForTutor(REST_METHOD_NAME_CURRENT_SUBSCRIPTION_PACKAGE_LIST, this.tutorSerialId, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionPackagesList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionPackagesList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_HISTORY_SUBSCRIPTION_PACKAGE_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String historySubscriptionPackageList (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_HISTORY_SUBSCRIPTION_PACKAGE_LIST;
		final GridComponent gridComponent =  new GridComponent(start, limit, otherParams, filters, sorters, SubscriptionPackage.class);
		this.tutorSerialId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), REQUEST_PARAM_TUTOR_SERIAL_ID, String.class);
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscriptionPackage> subscriptionPackagesList = getSubscriptionPackageService().getSubscriptionPackageListForTutor(REST_METHOD_NAME_HISTORY_SUBSCRIPTION_PACKAGE_LIST, this.tutorSerialId, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionPackagesList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionPackagesList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_APPROVE_TUTOR_DOCUMENT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String approveTutorDocumentList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_TUTOR_SERIAL_ID) final String tutorSerialId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_APPROVE_TUTOR_DOCUMENT_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.tutorSerialId = tutorSerialId;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getTutorService().aprroveTutorDocumentList(Arrays.asList(allIdsList.split(SEMICOLON)), this.tutorSerialId, comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_SEND_REMINDER_TUTOR_DOCUMENT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String sendReminderTutorDocumentList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_TUTOR_SERIAL_ID) final String tutorSerialId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_SEND_REMINDER_TUTOR_DOCUMENT_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.tutorSerialId = tutorSerialId;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			List<String> idList = null;
			List<String> documentTypeList = null;
			if (ValidationUtils.checkStringContainsText(allIdsList, COLON)) {
				final String[] bothIdListElements = allIdsList.split(COLON);
				if (ValidationUtils.checkStringAvailability(bothIdListElements[0])) {
					idList = Arrays.asList(bothIdListElements[0].split(SEMICOLON));
				}
				if (ValidationUtils.checkStringAvailability(bothIdListElements[1])) {
					documentTypeList = Arrays.asList(bothIdListElements[1].split(SEMICOLON));
				}
			} else {
				idList = Arrays.asList(allIdsList.split(SEMICOLON));
			}
			getTutorService().sendTutorDocumentListReminderEmails(idList, 
																	documentTypeList, 
																	this.tutorSerialId, 
																	comments, 
																	getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "Sent Reminder for document/s successfully.");
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_REJECT_TUTOR_DOCUMENT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String rejectTutorDocumentList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_TUTOR_SERIAL_ID) final String tutorSerialId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_REJECT_TUTOR_DOCUMENT_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.tutorSerialId = tutorSerialId;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getTutorService().rejectTutorDocumentList(Arrays.asList(allIdsList.split(SEMICOLON)), this.tutorSerialId, comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_APPROVE_BANK_ACCOUNT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String approveBankAccountList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_TUTOR_SERIAL_ID) final String tutorSerialId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_APPROVE_BANK_ACCOUNT_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.tutorSerialId = tutorSerialId;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getTutorService().aprroveBankAccountList(Arrays.asList(allIdsList.split(SEMICOLON)), this.tutorSerialId, comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_MAKE_DEFAULT_BANK_ACCOUNT)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String makeDefaultBankAccount (
			@FormParam("bankAccountSerialId") final String bankAccountSerialId,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_TUTOR_SERIAL_ID) final String tutorSerialId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_MAKE_DEFAULT_BANK_ACCOUNT;
		this.comments = comments;
		this.tutorSerialId = tutorSerialId;
		this.bankAccountSerialId = bankAccountSerialId;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getTutorService().makeDefaultBankAccount(this.bankAccountSerialId, this.tutorSerialId, comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_REJECT_BANK_ACCOUNT_LIST)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String rejectBankAccountList (
			@FormParam(REQUEST_PARAM_ALL_IDS_LIST) final String allIdsList,
			@FormParam(REQUEST_PARAM_COMMENTS) final String comments,
			@FormParam(REQUEST_PARAM_TUTOR_SERIAL_ID) final String tutorSerialId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_REJECT_BANK_ACCOUNT_LIST;
		this.allIdsList = allIdsList;
		this.comments = comments;
		this.tutorSerialId = tutorSerialId;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			getTutorService().rejectBankAccountList(Arrays.asList(allIdsList.split(SEMICOLON)), this.tutorSerialId, comments, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_UPDATE_TUTOR_RECORD)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateTutorRecord (
			@FormDataParam(REQUEST_PARAM_COMPLETE_UPDATED_RECORD) final String completeUpdatedRecord,
			@FormDataParam(REQUEST_PARAM_PARENT_SERIAL_ID) final String parentSerialId,
			@FormDataParam(REQUEST_PARAM_INPUT_FILE_PAN_CARD) final InputStream uploadedInputStreamFilePANCard,
			@FormDataParam(REQUEST_PARAM_INPUT_FILE_PAN_CARD) final FormDataContentDisposition uploadedFileDetailFilePANCard,
			@FormDataParam(REQUEST_PARAM_INPUT_FILE_AADHAAR_CARD) final InputStream uploadedInputStreamFileAadhaarCard,
			@FormDataParam(REQUEST_PARAM_INPUT_FILE_AADHAAR_CARD) final FormDataContentDisposition uploadedFileDetailFileAadhaarCard,
			@FormDataParam(REQUEST_PARAM_INPUT_FILE_PHOTO) final InputStream uploadedInputStreamFilePhoto,
			@FormDataParam(REQUEST_PARAM_INPUT_FILE_PHOTO) final FormDataContentDisposition uploadedFileDetailFilePhoto,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UPDATE_TUTOR_RECORD;
		createRegisteredTutorObjectFromCompleteUpdatedRecordJSONObject(JSONUtils.getJSONObjectFromString(completeUpdatedRecord));
		final List<TutorDocument> tutorDocuments = new ArrayList<TutorDocument>();
		if (null != uploadedInputStreamFilePANCard) {
			byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFilePANCard);
			if (fileBytes.length > 0) {
				tutorDocuments.add(new TutorDocument(DOCUMENT_TYPE_PAN_CARD, uploadedFileDetailFilePANCard.getFileName(), fileBytes));
			}
		}
		if (null != uploadedInputStreamFilePhoto) {
			byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFilePhoto);
			if (fileBytes.length > 0) {
				tutorDocuments.add(new TutorDocument(DOCUMENT_TYPE_PROFILE_PHOTO, uploadedFileDetailFilePhoto.getFileName(), fileBytes));
			}
		}
		if (null != uploadedInputStreamFileAadhaarCard) {
			byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFileAadhaarCard);
			if (fileBytes.length > 0) {
				tutorDocuments.add(new TutorDocument(DOCUMENT_TYPE_AADHAAR_CARD, uploadedFileDetailFileAadhaarCard.getFileName(), fileBytes));
			}
		}
		if (ValidationUtils.checkNonEmptyList(tutorDocuments)) {
			this.registeredTutorObject.setDocuments(tutorDocuments);
			this.changedAttributes.add("documents");
		}
		this.parentSerialId = parentSerialId;
		this.tutorSerialId = parentSerialId;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			this.registeredTutorObject.setTutorSerialId(this.tutorSerialId);
			getTutorService().updateTutorRecord(this.registeredTutorObject, this.changedAttributes, getActiveUser(request));
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
	protected void doSecurity(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		this.request = request; this.response = response;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_UPLOADED_DOCUMENT_LIST : 
			case REST_METHOD_NAME_BANK_DETAIL_LIST :
			case REST_METHOD_NAME_CURRENT_SUBSCRIPTION_PACKAGE_LIST : 
			case REST_METHOD_NAME_HISTORY_SUBSCRIPTION_PACKAGE_LIST : 
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_REGISTERED_TUTOR_PROFILE_PDF : {
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
				handleParentSerialId();
				handleTutorSecurity();
				break;
			}
			case REST_METHOD_NAME_DOWNLOAD_TUTOR_DOCUMENT : {
				handleDocumentSerialId();
				break;
			}
			case REST_METHOD_NAME_GET_REGISTERED_TUTOR_RECORD : {
				handleParentSerialId();
				break;
			}
			case REST_METHOD_NAME_GET_REGISTERED_TUTOR_DOCUMENT_COUNT_AND_EXISTENCE : {
				handleSelectedTutorDataView();
				break;
			}
			case REST_METHOD_NAME_DOWNLOAD_REGISTERED_TUTOR_DOCUMENT_FILE : {
				handleSelectedTutorDataView();
				handleSelectedDocumentTypeSecurity();
				break;
			}
			case REST_METHOD_NAME_DOWNLOAD_REGISTERED_TUTOR_ALL_DOCUMENTS : {
				handleSelectedTutorDataView();
				break;
			}
			case REST_METHOD_NAME_REMOVE_REGISTERED_TUTOR_DOCUMENT_FILE : {
				handleSelectedTutorDataView();
				handleSelectedDocumentTypeSecurity();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
	
	protected void handleDocumentSerialId() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.documentSerialId)) {
			appendError(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, INVALID_DOCUMENT_SERIAL_ID));
		}
	}
	
	private void handleSelectedTutorDataView() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.tutorSerialId)) {
			appendError(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, INVALID_TUTOR_SERIAL_ID));
		}
	}
	
	private void handleBankDetailModification() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.bankAccountSerialId)) {
			appendError(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, INVALID_BANK_DETAIL_SERIAL_ID));	
		}
	}
	
	private void handleSelectedDocumentTypeSecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkStringAvailability(this.documentType)) {
			appendError(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, DOCUMENT_TYPE_MISSING));
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
			this.registeredTutorObject.setAddressDetails(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "addressDetails", String.class));
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
						} else {
							// Check contact number in system
							final RegisteredTutor registeredTutorInDatabaseWithContactNumber = getTutorService().getRegisteredTutorInDatabaseWithContactNumber(this.registeredTutorObject.getContactNumber());
							final BecomeTutor becomeTutorApplicationInDatabaseWithContactNumber = getAdminService().getBecomeTutorApplicationInDatabaseWithContactNumber(this.registeredTutorObject.getContactNumber());
							if (null != registeredTutorInDatabaseWithContactNumber || null != becomeTutorApplicationInDatabaseWithContactNumber) {
								appendError(Message.getMessageFromFile(AdminConstants.MESG_PROPERTY_FILE_NAME, AdminConstants.FAILURE_MESSAGE_THIS_CONTACT_NUMBER_ALREADY_EXISTS_IN_THE_SYSTEM));
							}
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
						} else {
							// Check email Id in system
							final RegisteredTutor registeredTutorInDatabaseWithEmailId = getTutorService().getRegisteredTutorInDatabaseWithEmailId(this.registeredTutorObject.getEmailId());
							final BecomeTutor becomeTutorApplicationInDatabaseWithEmailId = getAdminService().getBecomeTutorApplicationInDatabaseWithEmailId(this.registeredTutorObject.getEmailId());
							if (null != registeredTutorInDatabaseWithEmailId || null != becomeTutorApplicationInDatabaseWithEmailId) {
								appendError(Message.getMessageFromFile(AdminConstants.MESG_PROPERTY_FILE_NAME, AdminConstants.FAILURE_MESSAGE_THIS_EMAIL_ID_ALREADY_EXISTS_IN_THE_SYSTEM));
							} 
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
					case "addressDetails" : {
						if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.registeredTutorObject.getAddressDetails())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_ADDRESS_DETAILS,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
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
									handleUploadingFileExtensionError(VALID_FILE_TYPE_FOR_DOCUMENTS);
								}
								if (ApplicationUtils.computeFileSizeInMB((long)document.getContent().length) > MAXIMUM_FILE_SIZE_FOR_DOCUMENTS_IN_MB) {
									handleUploadingFileSizeError(MAXIMUM_VALID_FILE_SIZE_FOR_DOCUMENTS_IN_MB.toString());
								}
							}
						}
						break;
					}
					default : {
						handleUnkownAttributeError(attributeName);
						break;
					}
				}
			}
		} else {
			handleNoAttributeChangedError();
		}
	}
}
