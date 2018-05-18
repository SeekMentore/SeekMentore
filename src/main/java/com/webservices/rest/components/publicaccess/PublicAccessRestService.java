package com.webservices.rest.components.publicaccess;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

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
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.components.publicaccess.PublicApplication;
import com.model.components.publicaccess.SubmitQuery;
import com.model.components.publicaccess.SubscribeWithUs;
import com.service.components.CommonsService;
import com.service.components.publicaccess.PublicAccessService;
import com.utils.ApplicationUtils;
import com.utils.ValidationUtils;
import com.utils.WebServiceUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_PUBLIC_ACCESS) 
public class PublicAccessRestService extends AbstractRestWebservice implements RestMethodConstants, PublicAccessConstants {
	
	// Since the Class is Prototype scope hence introducing a class level variable 
	// Do not do this in Service classes as they are singleton
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
			return convertObjToJSONString(getPublicAccessService().submitApplication(application), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
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
			return convertObjToJSONString(getPublicAccessService().submitApplication(application), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
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
			return convertObjToJSONString(getPublicAccessService().submitApplication(application), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
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
			return convertObjToJSONString(getPublicAccessService().submitApplication(application), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	public PublicAccessService getPublicAccessService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_PUBLIC_ACCESS_SERVICE, PublicAccessService.class);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
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
			return convertObjToJSONString(getPublicAccessService().getDropdownListData(PAGE_REFERENCE_TUTOR_REGISTRATION), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
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
			return convertObjToJSONString(getPublicAccessService().getDropdownListData(PAGE_REFERENCE_TUTOR_ENQUIRY), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
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
			return convertObjToJSONString(getPublicAccessService().getDropdownListData(PAGE_REFERENCE_SUBSCRIBE_WITH_US), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Override
	public void doSecurity (final HttpServletRequest request) throws Exception {
		this.request = request;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
		// Check Security/Validations for Methods which Submit Data
		if (this.isSubmitApplicationRequest) {
			final String captchaResponse = this.application.getCaptchaResponse();
			if (WebServiceUtils.verifyCaptcha(captchaResponse, request)) {
				switch(this.methodName) {
					case REST_METHOD_NAME_TO_BECOME_TUTOR : {
						handleBecomeTutorSecurity();
						break;
					}
					case REST_METHOD_NAME_TO_FIND_TUTOR : {
						handleFindTutorSecurity();
						break;
					}
					case REST_METHOD_NAME_TO_SUBMIT_QUERY : {
						handleSubmitQuerySecurity();
						break;
					}
					case REST_METHOD_NAME_TO_SUBSCRIBE : {
						handleSubscribeWithUsSecurity();
						break;
					}
				}
			} else {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						VALIDATION_MESSAGE_CAPTCHA_INVALIDATED_PLEASE_SELECT_AGAIN,
						RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
				this.securityPassed = false;
			}
		// Check Security/Validations for Methods which Receive Display Data
		} else if (this.isReceiveDisplayDataRequest) {
			switch(this.methodName) {
				case REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_BECOME_TUTOR : {
					// TODO Method level security
					this.securityPassed = true;
					break;
				}
				case REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_FIND_TUTOR : {
					// TODO Method level security
					this.securityPassed = true;
					break;
				}
				case REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_SUBSCRIBE_WITH_US : {
					// TODO Method level security
					this.securityPassed = true;
					break;
				}
			}
		}
		
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, !this.securityPassed);
	}
	
	private void handleBecomeTutorSecurity() {
		final BecomeTutor becomeTutorApplication = (BecomeTutor) this.application;
		this.securityPassed = true;
		if (!ValidationUtils.validatePhoneNumber(becomeTutorApplication.getContactNumber(), 10)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_CONTACT_NUMBER_MOBILE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateEmailAddress(becomeTutorApplication.getEmailId())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_EMAIL_ID,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateNameString(becomeTutorApplication.getFirstName(), true)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_FIRST_NAME,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateNameString(becomeTutorApplication.getLastName(), true)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_LAST_NAME,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateDate(becomeTutorApplication.getDateOfBirth())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_DATE_OF_BIRTH,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getGender(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_GENDER_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_GENDER,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getQualification(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_QUALIFICATION,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getPrimaryProfession(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_PRIMARY_PROFESSION,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getTransportMode(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_VALID_TRANSPORT_MODE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getStudentGrade(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_STUDENT_GRADE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_SUBJECTS,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getLocations(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_LOCATIONS,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getReference(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_REFERENCE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getPreferredTeachingType(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_TUTORING_TYPE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateNumber(becomeTutorApplication.getTeachingExp(), true, 99, false, 0)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_TEACHING_EXPERIENCE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(becomeTutorApplication.getPreferredTimeToCall(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					BecomeTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_PREFERRED_TIME_TO_CALL,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getLoggedInUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE) + LINE_BREAK + becomeTutorApplication.toString());
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleFindTutorSecurity() {
		final FindTutor findTutorApplication = (FindTutor) this.application;
		this.securityPassed = true;
		if (!ValidationUtils.validatePhoneNumber(findTutorApplication.getContactNumber(), 10)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_CONTACT_NUMBER_MOBILE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateEmailAddress(findTutorApplication.getEmailId())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_EMAIL_ID,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateNameString(findTutorApplication.getName(), true)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_NAME,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(findTutorApplication.getStudentGrade(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_STUDENT_GRADE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(findTutorApplication.getSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_SUBJECTS,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(findTutorApplication.getPreferredTimeToCall(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_PREFERRED_TIME_TO_CALL,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(findTutorApplication.getLocation(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_LOCATION,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(findTutorApplication.getReference(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_REFERENCE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(findTutorApplication.getAddressDetails())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					FindTutorConstants.VALIDATION_MESSAGE_PLEASE_ENTER_ADDRESS_DETAILS,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getLoggedInUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE) + LINE_BREAK + findTutorApplication.toString());
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleSubscribeWithUsSecurity() {
		final SubscribeWithUs subscribeWithUsApplication = (SubscribeWithUs) this.application;
		this.securityPassed = true;
		if (!ValidationUtils.validatePhoneNumber(subscribeWithUsApplication.getContactNumber(), 10)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_CONTACT_NUMBER_MOBILE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateEmailAddress(subscribeWithUsApplication.getEmailId())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_EMAIL_ID,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateNameString(subscribeWithUsApplication.getFirstName(), true)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_FIRST_NAME,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateNameString(subscribeWithUsApplication.getLastName(), true)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_LAST_NAME,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(subscribeWithUsApplication.getStudentGrade(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_SELECT_A_STUDENT_GRADE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(subscribeWithUsApplication.getSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_SUBJECTS,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(subscribeWithUsApplication.getPreferredTimeToCall(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_PREFERRED_TIME_TO_CALL,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(subscribeWithUsApplication.getLocation(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_LOCATION,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateAgainstSelectLookupValues(subscribeWithUsApplication.getReference(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_SELECT_VALID_REFERENCE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(subscribeWithUsApplication.getAddressDetails())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubscribeWithUsConstants.VALIDATION_MESSAGE_PLEASE_ENTER_ADDRESS_DETAILS,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getLoggedInUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE) + LINE_BREAK + subscribeWithUsApplication.toString());
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleSubmitQuerySecurity() {
		final SubmitQuery submitQueryApplication = (SubmitQuery) this.application;
		this.securityPassed = true;
		if (!ValidationUtils.validateEmailAddress(submitQueryApplication.getEmailId())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubmitQueryConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_EMAIL_ID,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(submitQueryApplication.getQueryDetails())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					SubmitQueryConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_QUERY,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getLoggedInUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE) + LINE_BREAK + submitQueryApplication.toString());
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
}
