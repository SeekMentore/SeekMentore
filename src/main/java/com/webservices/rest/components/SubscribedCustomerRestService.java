package com.webservices.rest.components;

import java.util.Arrays;
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

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.AdminConstants;
import com.constants.components.CustomerConstants;
import com.constants.components.SelectLookupConstants;
import com.model.components.SubscribedCustomer;
import com.model.components.SubscriptionPackage;
import com.model.gridcomponent.GridComponent;
import com.service.components.CustomerService;
import com.service.components.SubscriptionPackageService;
import com.utils.ApplicationUtils;
import com.utils.GridComponentUtils;
import com.utils.JSONUtils;
import com.utils.ValidationUtils;
import com.utils.context.AppContext;
import com.utils.localization.Message;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_SUBSCRIBED_CUSTOMER_ADMIN) 
public class SubscribedCustomerRestService extends AbstractRestWebservice implements RestMethodConstants, CustomerConstants {
	
	private String customerSerialId;
	private SubscribedCustomer subscribedCustomerObject;
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_SUBSCRIBED_CUSTOMER_PROFILE_PDF)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadAdminSubscribedCustomerProfilePdf (
    		@FormParam(REST_PARAM_CUSTOMER_SERIAL_ID) final String customerSerialId,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_SUBSCRIBED_CUSTOMER_PROFILE_PDF;
		this.customerSerialId = customerSerialId;
		doSecurity(request, response);
		if (this.securityPassed) {
			downloadFile(getCustomerService().downloadSubscribedCustomerProfilePdf(this.customerSerialId, true), response);
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
		this.customerSerialId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), REST_PARAM_CUSTOMER_SERIAL_ID, String.class);
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscriptionPackage> subscriptionPackagesList = getSubscriptionPackageService().getSubscriptionPackageListForCustomer(REST_METHOD_NAME_CURRENT_SUBSCRIPTION_PACKAGE_LIST, this.customerSerialId, gridComponent);
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
		this.customerSerialId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), REST_PARAM_CUSTOMER_SERIAL_ID, String.class);
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscriptionPackage> subscriptionPackagesList = getSubscriptionPackageService().getSubscriptionPackageListForCustomer(REST_METHOD_NAME_HISTORY_SUBSCRIPTION_PACKAGE_LIST, this.customerSerialId, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionPackagesList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionPackagesList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_GET_SUBSCRIBED_CUSTOMER_RECORD)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String getSubscribedCustomerRecord (
			@FormParam(REQUEST_PARAM_PARENT_SERIAL_ID) final String parentSerialId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_GET_SUBSCRIBED_CUSTOMER_RECORD;
		this.parentSerialId = parentSerialId;
		this.customerSerialId = parentSerialId;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final SubscribedCustomer subscribedCustomer = getCustomerService().getSubscribedCustomer(this.customerSerialId);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_RECORD_OBJECT, subscribedCustomer);
			ApplicationUtils.copyAllPropertiesOfOneMapIntoAnother(getCustomerService().getRecordFormUpdateStatus(subscribedCustomer), restresponse);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_UPDATE_CUSTOMER_RECORD)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateCustomerRecord (
			@FormDataParam(REQUEST_PARAM_COMPLETE_UPDATED_RECORD) final String completeUpdatedRecord,
			@FormDataParam(REQUEST_PARAM_PARENT_SERIAL_ID) final String parentSerialId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UPDATE_CUSTOMER_RECORD;
		createSubscribedCustomerObjectFromCompleteUpdatedRecordJSONObject(JSONUtils.getJSONObjectFromString(completeUpdatedRecord));
		this.parentSerialId = parentSerialId;
		this.customerSerialId = parentSerialId;
		doSecurity(request, response);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			this.subscribedCustomerObject.setCustomerSerialId(this.customerSerialId);
			getCustomerService().updateCustomerRecord(this.subscribedCustomerObject, this.changedAttributes, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, MESSAGE_UPDATED_RECORD);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	public CustomerService getCustomerService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_CUSTOMER_SERVICE, CustomerService.class);
	}
	
	public SubscriptionPackageService getSubscriptionPackageService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_SUBSCRIPTION_PACKAGE_SERVICE, SubscriptionPackageService.class);
	}

	@Override
	protected void doSecurity(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		this.request = request; this.response = response;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_CURRENT_SUBSCRIPTION_PACKAGE_LIST : 
			case REST_METHOD_NAME_HISTORY_SUBSCRIPTION_PACKAGE_LIST : 
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_SUBSCRIBED_CUSTOMER_PROFILE_PDF : {
				handleSelectedCustomerDataGridView();
				break;
			}
			case REST_METHOD_NAME_UPDATE_CUSTOMER_RECORD : {
				handleParentSerialId();
				handleSubscribedCustomerSecurity();
				break;
			}
			case REST_METHOD_NAME_GET_SUBSCRIBED_CUSTOMER_RECORD : {
				handleParentSerialId();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
	
	private void handleSelectedCustomerDataGridView() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkStringAvailability(this.customerSerialId)) {
			appendError(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, INVALID_CUSTOMER_SERIAL_ID));
		}
	} 
	
	private void createSubscribedCustomerObjectFromCompleteUpdatedRecordJSONObject(final JsonObject jsonObject) {
		if (ValidationUtils.checkObjectAvailability(jsonObject)) {
			this.subscribedCustomerObject = new SubscribedCustomer();
			this.subscribedCustomerObject.setName(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "name", String.class));
			this.subscribedCustomerObject.setContactNumber(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "contactNumber", String.class));
			this.subscribedCustomerObject.setEmailId(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "emailId", String.class));
			this.subscribedCustomerObject.setStudentGrades(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "studentGrades", String.class));
			this.subscribedCustomerObject.setInterestedSubjects(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "interestedSubjects", String.class));
			this.subscribedCustomerObject.setLocation(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "location", String.class));
			this.subscribedCustomerObject.setAdditionalDetails(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "additionalDetails", String.class));
			this.subscribedCustomerObject.setAddressDetails(getValueForPropertyFromCompleteUpdatedJSONObject(jsonObject, "addressDetails", String.class));
			this.omittableAttributesList = Arrays.asList(new String[]{"additionalDetails"});
		}
	}
	
	private void handleSubscribedCustomerSecurity() throws Exception {
		this.securityPassed = true;
		if (ValidationUtils.checkNonEmptyList(this.changedAttributes)) {
			for (final String attributeName : this.changedAttributes) {
				switch(attributeName) {
					case "name" : {
						if (!ValidationUtils.validateNameString(this.subscribedCustomerObject.getName(), true)) {
							appendError(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, PROVIDE_A_VALID_NAME));
						}
						break;
					}
					case "contactNumber" : {
						if (!ValidationUtils.validatePhoneNumber(this.subscribedCustomerObject.getContactNumber(), 10)) {
							appendError(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, PROVIDE_A_VALID_CONTACT_NUMBER_MOBILE));
						} else {
							// Check contact number in system
							final SubscribedCustomer subscribedCustomerInDatabaseWithContactNumber = getCustomerService().getSubscribedCustomerInDatabaseWithContactNumber(this.subscribedCustomerObject.getContactNumber());
							if (null != subscribedCustomerInDatabaseWithContactNumber) {
								appendError(Message.getMessageFromFile(AdminConstants.MESG_PROPERTY_FILE_NAME, AdminConstants.FAILURE_MESSAGE_THIS_CONTACT_NUMBER_ALREADY_EXISTS_IN_THE_SYSTEM));
							}
						}
						break;
					}
					case "emailId" : {
						if (!ValidationUtils.validateEmailAddress(this.subscribedCustomerObject.getEmailId())) {
							appendError(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, PROVIDE_A_VALID_EMAIL_ID));
						} else {
							// Check email Id in system
							final SubscribedCustomer subscribedCustomerInDatabaseWithEmailId = getCustomerService().getSubscribedCustomerInDatabaseWithEmailId(this.subscribedCustomerObject.getEmailId());
							if (null != subscribedCustomerInDatabaseWithEmailId) {
								appendError(Message.getMessageFromFile(AdminConstants.MESG_PROPERTY_FILE_NAME, AdminConstants.FAILURE_MESSAGE_THIS_EMAIL_ID_ALREADY_EXISTS_IN_THE_SYSTEM));
							} 
						}
						break;
					}
					case "studentGrades" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.subscribedCustomerObject.getStudentGrades(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
							appendError(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, SELECT_A_STUDENT_GRADE));
						}
						break;
					}
					case "interestedSubjects" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.subscribedCustomerObject.getInterestedSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
							appendError(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, SELECT_VALID_MULTIPLE_SUBJECTS));
						}
						break;
					}
					case "location" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.subscribedCustomerObject.getLocation(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
							appendError(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, SELECT_VALID_LOCATION));
						}
						break;
					}
					case "addressDetails" : {
						if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.subscribedCustomerObject.getAddressDetails())) {
							appendError(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, PROVIDE_ADDRESS_DETAILS));
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
