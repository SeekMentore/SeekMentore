package com.webservices.rest.components;

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
import com.constants.components.publicaccess.FindTutorConstants;
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
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_SUBSCRIBED_CUSTOMER_ADMIN) 
public class SubscribedCustomerRestService extends AbstractRestWebservice implements RestMethodConstants, CustomerConstants {
	
	private Long customerId;
	private SubscribedCustomer subscribedCustomerObject;
	private Long parentId;
	
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
		customerId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "customerId", Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscriptionPackage> subscriptionPackagesList = getSubscriptionPackageService().getSubscriptionPackageListForCustomer(REST_METHOD_NAME_CURRENT_PACKAGE_LIST, customerId, gridComponent);
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
		customerId = JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "customerId", Long.class);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			final List<SubscriptionPackage> subscriptionPackagesList = getSubscriptionPackageService().getSubscriptionPackageListForCustomer(REST_METHOD_NAME_HISTORY_PACKAGE_LIST, customerId, gridComponent);
			restresponse.put(GRID_COMPONENT_RECORD_DATA, subscriptionPackagesList);
			restresponse.put(GRID_COMPONENT_TOTAL_RECORDS, GridComponentUtils.getTotalRecords(subscriptionPackagesList, gridComponent));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_UPDATE_CUSTOMER_RECORD)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String updateCustomerRecord (
			@FormDataParam(REQUEST_PARAM_COMPLETE_UPDATED_RECORD) final String completeUpdatedRecord,
			@FormDataParam(REQUEST_PARAM_PARENT_ID) final String parentId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UPDATE_CUSTOMER_RECORD;
		createsubscribedCustomerObjectFromCompleteUpdatedRecordJSONObject(JSONUtils.getJSONObjectFromString(completeUpdatedRecord));
		try {
			this.parentId = Long.parseLong(parentId);
		} catch(NumberFormatException e) {}
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restresponse = new HashMap<String, Object>();
			this.subscribedCustomerObject.setCustomerId(Long.parseLong(parentId));
			getCustomerService().updateCustomerRecord(this.subscribedCustomerObject, this.changedAttributes, getActiveUser(request));
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "Updated record");
			return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	public CustomerService getCustomerService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_CUSTOMER_SERVICE, CustomerService.class);
	}
	
	public SubscriptionPackageService getSubscriptionPackageService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_SUBSCRIPTION_PACKAGE_SERVICE, SubscriptionPackageService.class);
	}

	@Override
	protected void doSecurity(HttpServletRequest request) throws Exception {
		this.request = request;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_CURRENT_PACKAGE_LIST : 
			case REST_METHOD_NAME_HISTORY_PACKAGE_LIST :{
				handleSelectedCustomerDataGridView();
				break;
			}
			case REST_METHOD_NAME_UPDATE_TUTOR_RECORD : {
				handleParentId();
				handleCustomerSecurity();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
	
	private void handleSelectedCustomerDataGridView() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.checkObjectAvailability(this.customerId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_CUSTOMER_ID_ABSENT,
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
	
	private void createsubscribedCustomerObjectFromCompleteUpdatedRecordJSONObject(final JsonObject jsonObject) {
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
		}
	}
	
	private void handleCustomerSecurity() throws Exception {
		this.securityPassed = true;
		if (ValidationUtils.checkNonEmptyList(this.changedAttributes)) {
			for (final String attributeName : this.changedAttributes) {
				switch(attributeName) {
					case "name" : {
						if (!ValidationUtils.validateNameString(this.subscribedCustomerObject.getName(), true)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_NAME,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "contactNumber" : {
						if (!ValidationUtils.validatePhoneNumber(this.subscribedCustomerObject.getContactNumber(), 10)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_CONTACT_NUMBER_MOBILE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else {
							// Check contact number in system
							final SubscribedCustomer subscribedCustomerInDatabaseWithContactNumber = getCustomerService().getSubscribedCustomerInDatabaseWithContactNumber(this.subscribedCustomerObject.getContactNumber());
							if (null != subscribedCustomerInDatabaseWithContactNumber) {
								ApplicationUtils.appendMessageInMapAttribute(
										this.securityFailureResponse, 
										FAILURE_MESSAGE_THIS_CONTACT_NUMBER_ALREADY_EXISTS_IN_THE_SYSTEM,
										RESPONSE_MAP_ATTRIBUTE_MESSAGE);
								this.securityPassed = false;
							}
						}
						break;
					}
					case "emailId" : {
						if (!ValidationUtils.validateEmailAddress(this.subscribedCustomerObject.getEmailId())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_EMAIL_ID,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						} else {
							// Check email Id in system
							final SubscribedCustomer subscribedCustomerInDatabaseWithEmailId = getCustomerService().getSubscribedCustomerInDatabaseWithEmailId(this.subscribedCustomerObject.getEmailId());
							if (null != subscribedCustomerInDatabaseWithEmailId) {
								ApplicationUtils.appendMessageInMapAttribute(
										this.securityFailureResponse, 
										FAILURE_MESSAGE_THIS_EMAIL_ID_ALREADY_EXISTS_IN_THE_SYSTEM,
										RESPONSE_MAP_ATTRIBUTE_MESSAGE);
								this.securityPassed = false;
							} 
						}
						break;
					}
					case "studentGrades" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.subscribedCustomerObject.getStudentGrades(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_STUDENT_GRADE,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "interestedSubjects" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.subscribedCustomerObject.getInterestedSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_SUBJECTS,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "location" : {
						if (!ValidationUtils.validateAgainstSelectLookupValues(this.subscribedCustomerObject.getLocation(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_LOCATION,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
						}
						break;
					}
					case "additionalDetails" : {
						break;
					}
					case "addressDetails" : {
						if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.subscribedCustomerObject.getAddressDetails())) {
							ApplicationUtils.appendMessageInMapAttribute(
									this.securityFailureResponse, 
									FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_ADDRESS_DETAILS,
									RESPONSE_MAP_ATTRIBUTE_MESSAGE);
							this.securityPassed = false;
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
