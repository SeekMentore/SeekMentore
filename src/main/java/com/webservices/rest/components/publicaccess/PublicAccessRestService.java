package com.webservices.rest.components.publicaccess;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.SelectLookupConstants;
import com.constants.components.publicaccess.BecomeTutorConstants;
import com.constants.components.publicaccess.FindTutorConstants;
import com.constants.components.publicaccess.PublicAccessConstants;
import com.constants.components.publicaccess.SubmitQueryConstants;
import com.constants.components.publicaccess.SubscribeWithUsConstants;
import com.model.ErrorPacket;
import com.model.components.RegisteredTutor;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.components.publicaccess.PublicApplication;
import com.model.components.publicaccess.SubmitQuery;
import com.model.components.publicaccess.SubscribeWithUs;
import com.service.components.AdminService;
import com.service.components.CommonsService;
import com.service.components.TutorService;
import com.service.components.publicaccess.PublicAccessService;
import com.utils.ApplicationUtils;
import com.utils.JSONUtils;
import com.utils.ValidationUtils;
import com.utils.WebServiceUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_PUBLIC_ACCESS) 
public class PublicAccessRestService extends AbstractRestWebservice implements RestMethodConstants, PublicAccessConstants {
	
	private PublicApplication application;
	private boolean isSubmitApplicationRequest = false;
	private boolean isReceiveDisplayDataRequest = false;
	
	@Path(REST_METHOD_NAME_TO_BECOME_TUTOR)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String becomeTutor (
			final BecomeTutor application,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_BECOME_TUTOR;
		this.isSubmitApplicationRequest = true;
		this.application = application;
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(submitPublicApplication(application, PAGE_REFERENCE_TUTOR_REGISTRATION), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_FIND_TUTOR)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String findTutor (
			final FindTutor application,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_FIND_TUTOR;
		this.isSubmitApplicationRequest = true;
		this.application = application;
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(submitPublicApplication(application, PAGE_REFERENCE_TUTOR_ENQUIRY), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_SUBSCRIBE)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String subscribe (
			final SubscribeWithUs application,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_SUBSCRIBE;
		this.isSubmitApplicationRequest = true;
		this.application = application;
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(submitPublicApplication(application, PAGE_REFERENCE_SUBSCRIBE_WITH_US), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_SUBMIT_QUERY)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String submitQuery (
			final SubmitQuery application,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_SUBMIT_QUERY;
		this.isSubmitApplicationRequest = true;
		this.application = application;
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(submitPublicApplication(application, PAGE_REFERENCE_SUBMIT_QUERY), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	private Map<String, Object> submitPublicApplication(final PublicApplication application, final String pageReference) throws Exception {
		final Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put(RESPONSE_MAP_ATTRIBUTE_PAGE_REFERNCE, pageReference);
		getPublicAccessService().submitApplication(application);
		restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
		restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, PUBLIC_APPLICATION_RECEIVE_SUCCESS_MESSAGE);
		return restresponse;
	}
	
	@Path(REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_BECOME_TUTOR)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String getDropdownListDataBecomeTutor (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_BECOME_TUTOR;
		this.isReceiveDisplayDataRequest = true;
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getDropdownListData(PAGE_REFERENCE_TUTOR_REGISTRATION), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_FIND_TUTOR)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String getDropdownListDataFindTutor (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_FIND_TUTOR;
		this.isReceiveDisplayDataRequest = true;
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getDropdownListData(PAGE_REFERENCE_TUTOR_ENQUIRY), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_SUBSCRIBE_WITH_US)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String getDropdownListDataSubscribeWithUs (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_SUBSCRIBE_WITH_US;
		this.isReceiveDisplayDataRequest = true;
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getDropdownListData(PAGE_REFERENCE_SUBSCRIBE_WITH_US), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	private Map<String, Object> getDropdownListData(final String page) {
		final Map<String, Object> restresponse = new HashMap<String, Object>();
		restresponse.put(RESPONSE_MAP_DROPDOWN_LIST, getPublicAccessService().getDropdownListData(page));
		restresponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
		restresponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, PUBLIC_APPLICATION_RECEIVE_SUCCESS_MESSAGE);
		return restresponse;
	}
	
	public PublicAccessService getPublicAccessService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_PUBLIC_ACCESS_SERVICE, PublicAccessService.class);
	}
	
	public AdminService getAdminService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ADMIN_SERVICE, AdminService.class);
	}
	
	public TutorService getTutorService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_TUTOR_SERVICE, TutorService.class);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	@Override
	public void doSecurity (final HttpServletRequest request) throws Exception {
		this.request = request;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		if (this.isSubmitApplicationRequest) {
			final String captchaResponse = this.application.getCaptchaResponse();
			if (WebServiceUtils.verifyCaptcha(captchaResponse, request)) {
				switch(this.methodName) {
					case REST_METHOD_NAME_TO_BECOME_TUTOR : 
					case REST_METHOD_NAME_TO_FIND_TUTOR : 
					case REST_METHOD_NAME_TO_SUBSCRIBE : 
					case REST_METHOD_NAME_TO_SUBMIT_QUERY : {
						handleApplicationSecurity();
						break;
					}
				}
			} else {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						VALIDATION_MESSAGE_CAPTCHA_INVALIDATED_PLEASE_SELECT_AGAIN,
						RESPONSE_MAP_ATTRIBUTE_MESSAGE);
				this.securityPassed = false;
			}
		} else if (this.isReceiveDisplayDataRequest) {
			switch(this.methodName) {
				case REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_BECOME_TUTOR : 
				case REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_FIND_TUTOR : 
				case REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_SUBSCRIBE_WITH_US : {
					this.securityPassed = true;
					break;
				}
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
	
	public void handleApplicationSecurity() throws Exception {
		this.securityPassed = true;
		if (this.application instanceof BecomeTutor) {
			if (REST_METHOD_NAME_TO_BECOME_TUTOR.equals(this.methodName)) {
				handleBecomeTutorSecurity();
			} else {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						RESPONSE_MAP_ATTRIBUTE_MISMATCH_PUBLIC_PAGE_REFERENCE,
						RESPONSE_MAP_ATTRIBUTE_MESSAGE);
				this.securityPassed = false;
			}
		} else if (this.application instanceof FindTutor) {
			if (REST_METHOD_NAME_TO_FIND_TUTOR.equals(this.methodName)) {
				handleFindTutorSecurity();
			} else {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						RESPONSE_MAP_ATTRIBUTE_MISMATCH_PUBLIC_PAGE_REFERENCE,
						RESPONSE_MAP_ATTRIBUTE_MESSAGE);
				this.securityPassed = false;
			}
		} else if (this.application instanceof SubscribeWithUs) {
			if (REST_METHOD_NAME_TO_SUBSCRIBE.equals(this.methodName)) {
				handleSubscribeWithUsSecurity();
			} else {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						RESPONSE_MAP_ATTRIBUTE_MISMATCH_PUBLIC_PAGE_REFERENCE,
						RESPONSE_MAP_ATTRIBUTE_MESSAGE);
				this.securityPassed = false;
			}
		} else if (this.application instanceof SubmitQuery) {
			if (REST_METHOD_NAME_TO_SUBMIT_QUERY.equals(this.methodName)) {
				handleSubmitQuerySecurity();
			} else {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						RESPONSE_MAP_ATTRIBUTE_MISMATCH_PUBLIC_PAGE_REFERENCE,
						RESPONSE_MAP_ATTRIBUTE_MESSAGE);
				this.securityPassed = false;
			}
		} else {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					RESPONSE_MAP_ATTRIBUTE_UNKNOWN_PUBLIC_PAGE_REFERENCE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket( 
					this.methodName + LINE_BREAK + getActiveUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE) + LINE_BREAK + this.application.getFormattedApplicationForPrinting());
			getCommonsService().feedErrorRecord(errorPacket);
			// Append contact information if Failure occurred
			ApplicationUtils.appendMessageInMapAttribute(
							this.securityFailureResponse, 
							FAILURE_CONTACT_INFO,
							RESPONSE_MAP_ATTRIBUTE_MESSAGE);
		}
	}
	
	private void handleBecomeTutorSecurity() throws Exception {
		final BecomeTutor becomeTutorApplication = (BecomeTutor) this.application;
		this.securityPassed = true;
		if (!ValidationUtils.validatePhoneNumber(becomeTutorApplication.getContactNumber(), 10)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_CONTACT_NUMBER_MOBILE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		} else {
			// Check contact number in system
			final BecomeTutor becomeTutorApplicationInDatabaseWithContactNumber = getAdminService().getBecomeTutorApplicationInDatabaseWithContactNumber(becomeTutorApplication.getContactNumber());
			final RegisteredTutor registeredTutorInDatabaseWithContactNumber = getTutorService().getRegisteredTutorInDatabaseWithContactNumber(becomeTutorApplication.getContactNumber());
			if (null != becomeTutorApplicationInDatabaseWithContactNumber || null != registeredTutorInDatabaseWithContactNumber) {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						FAILURE_MESSAGE_THIS_CONTACT_NUMBER_ALREADY_EXISTS_IN_THE_SYSTEM,
						RESPONSE_MAP_ATTRIBUTE_MESSAGE);
				this.securityPassed = false;
			}
		}
		if (!ValidationUtils.validateEmailAddress(becomeTutorApplication.getEmailId())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_EMAIL_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		} else {
			// Check email Id in system
			final BecomeTutor becomeTutorApplicationInDatabaseWithEmailId = getAdminService().getBecomeTutorApplicationInDatabaseWithEmailId(becomeTutorApplication.getEmailId());
			final RegisteredTutor registeredTutorInDatabaseWithEmailId = getTutorService().getRegisteredTutorInDatabaseWithEmailId(becomeTutorApplication.getEmailId());
			if (null != becomeTutorApplicationInDatabaseWithEmailId || null != registeredTutorInDatabaseWithEmailId) {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						FAILURE_MESSAGE_THIS_EMAIL_ID_ALREADY_EXISTS_IN_THE_SYSTEM,
						RESPONSE_MAP_ATTRIBUTE_MESSAGE);
				this.securityPassed = false;
			}
		}
		if (!ValidationUtils.validateNameString(becomeTutorApplication.getFirstName(), true)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_FIRST_NAME,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateNameString(becomeTutorApplication.getLastName(), true)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_LAST_NAME,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateDate(becomeTutorApplication.getDateOfBirth())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_DATE_OF_BIRTH,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getGender(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_GENDER_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_GENDER,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getQualification(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_QUALIFICATION,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getPrimaryProfession(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_PRIMARY_PROFESSION,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getTransportMode(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_TRANSPORT_MODE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getStudentGrade(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_STUDENT_GRADE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_SUBJECTS,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getLocations(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_LOCATIONS,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getReference(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_REFERENCE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getPreferredTeachingType(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_TUTORING_TYPE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateNumber(becomeTutorApplication.getTeachingExp(), true, 99, false, 0)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_TEACHING_EXPERIENCE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getPreferredTimeToCall(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_PREFERRED_TIME_TO_CALL,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	private void handleFindTutorSecurity() throws Exception {
		final FindTutor findTutorApplication = (FindTutor) this.application;
		this.securityPassed = true;
		if (!ValidationUtils.validatePhoneNumber(findTutorApplication.getContactNumber(), 10)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_CONTACT_NUMBER_MOBILE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateEmailAddress(findTutorApplication.getEmailId())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_EMAIL_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateNameString(findTutorApplication.getName(), true)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_NAME,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(findTutorApplication.getStudentGrade(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_STUDENT_GRADE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(findTutorApplication.getSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_SUBJECTS,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(findTutorApplication.getPreferredTimeToCall(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_PREFERRED_TIME_TO_CALL,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(findTutorApplication.getLocation(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_LOCATION,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(findTutorApplication.getReference(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_REFERENCE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(findTutorApplication.getAddressDetails())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_ADDRESS_DETAILS,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	private void handleSubscribeWithUsSecurity() throws Exception {
		final SubscribeWithUs subscribeWithUsApplication = (SubscribeWithUs) this.application;
		this.securityPassed = true;
		if (!ValidationUtils.validatePhoneNumber(subscribeWithUsApplication.getContactNumber(), 10)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_CONTACT_NUMBER_MOBILE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateEmailAddress(subscribeWithUsApplication.getEmailId())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_EMAIL_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateNameString(subscribeWithUsApplication.getFirstName(), true)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_FIRST_NAME,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateNameString(subscribeWithUsApplication.getLastName(), true)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_LAST_NAME,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(subscribeWithUsApplication.getStudentGrade(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_STUDENT_GRADE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(subscribeWithUsApplication.getSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_SUBJECTS,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(subscribeWithUsApplication.getPreferredTimeToCall(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_PREFERRED_TIME_TO_CALL,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(subscribeWithUsApplication.getLocation(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_LOCATION,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(subscribeWithUsApplication.getReference(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_REFERENCE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(subscribeWithUsApplication.getAddressDetails())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_ENTER_ADDRESS_DETAILS,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
	
	private void handleSubmitQuerySecurity() throws Exception {
		final SubmitQuery submitQueryApplication = (SubmitQuery) this.application;
		this.securityPassed = true;
		if (!ValidationUtils.validateEmailAddress(submitQueryApplication.getEmailId())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubmitQueryConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_EMAIL_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(submitQueryApplication.getQueryDetails())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubmitQueryConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_QUERY,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
	}
}
