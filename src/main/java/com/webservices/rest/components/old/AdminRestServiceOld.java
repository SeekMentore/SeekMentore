package com.webservices.rest.components.old;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.AdminConstants;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_ADMIN+"old") 
public class AdminRestServiceOld extends AbstractRestWebservice implements RestMethodConstants, AdminConstants {

	@Override
	protected void doSecurity(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
	}/*
	
	private String gridName;
	private String button;
	private String uniqueId;
	private String remarks;
	private String recepientEmailId;
	private String emailSalutationName;
	private String emailSubject;
	private String emailText;
	
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_REGISTRATION_PROFILE_PDF)
	@Produces({MediaType.APPLICATION_JSON})  
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadAdminTutorRegistrationProfilePdf (
    		@FormParam("tentativeTutorId") final String tentativeTutorId,
    		@FormParam("name") final String name,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_REGISTRATION_PROFILE_PDF;
		doSecurity(request);
		if (this.securityPassed) {
			FileUtils.writeFileToResponse(response, "Admin_Tutor_Registration_PDF_For_" + name + PERIOD + FileConstants.EXTENSION_PDF, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getAdminService().downloadAdminTutorRegistrationProfilePdf(tentativeTutorId));
		}
    }
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_ENQUIRY_PROFILE_PDF)
	@Produces({MediaType.APPLICATION_JSON})  
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadAdminTutorEnquiryProfilePdf (
    		@FormParam("enquiryId") final String enquiryId,
    		@FormParam("name") final String name,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_ENQUIRY_PROFILE_PDF;
		doSecurity(request);
		if (this.securityPassed) {
			FileUtils.writeFileToResponse(response, "Admin_Tutor_Enquiry_PDF_For_" + name + PERIOD + FileConstants.EXTENSION_PDF, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getAdminService().downloadAdminTutorEnquiryProfilePdf(enquiryId));
		}
    }
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_INDIVIDUAL_SUBSCRIPTION_PROFILE_PDF)
	@Produces({MediaType.APPLICATION_JSON})  
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadAdminIndividualSubscriptionProfilePdf (
    		@FormParam("tentativeSubscriptionId") final String tentativeSubscriptionId,
    		@FormParam("name") final String name,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_INDIVIDUAL_SUBSCRIPTION_PROFILE_PDF;
		doSecurity(request);
		if (this.securityPassed) {
			FileUtils.writeFileToResponse(response, "Admin_Individual_Subscription_PDF_For_" + name + PERIOD + FileConstants.EXTENSION_PDF, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getAdminService().downloadAdminIndividualSubscriptionProfilePdf(tentativeSubscriptionId));
		}
    }
	
	public AdminService getAdminService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ADMIN_SERVICE, AdminService.class);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	public JNDIandControlConfigurationLoadService getJNDIandControlConfigurationLoadService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
	}
	
	@Override
	public void doSecurity(final HttpServletRequest request) throws Exception {
		this.request = request;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_TUTOR_REGISTRATIONS :
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_REGISTRATION_PROFILE_PDF :
			case REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_REGISTRATIONS :
			case REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_REGISTRATIONS : 
			case REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_REGISTRATIONS : 
			case REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_REGISTRATIONS : 
			case REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_REGISTRATIONS : 
			case REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_REGISTRATIONS : 
			case REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_REGISTRATIONS : 
			case REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS_FROM_TUTOR_REGISTRATIONS :
				
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_TUTOR_ENQUIRIES :
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_ENQUIRY_PROFILE_PDF :
			case REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_ENQUIRIES :
			case REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_ENQUIRIES : 
			case REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_ENQUIRIES : 
			case REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_ENQUIRIES : 
			case REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_ENQUIRIES : 
			case REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_ENQUIRIES : 
			case REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_ENQUIRIES :
				
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_INDIVIDUAL_SUBSCRIPTION_PROFILE_PDF :
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_SUBSCRIPTIONS :
			case REST_METHOD_NAME_DISPLAY_NON_CONTACTED_SUBSCRIPTIONS :
			case REST_METHOD_NAME_DISPLAY_NON_VERIFIED_SUBSCRIPTIONS : 
			case REST_METHOD_NAME_DISPLAY_VERIFIED_SUBSCRIPTIONS : 
			case REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_SUBSCRIPTIONS : 
			case REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_SUBSCRIPTIONS : 
			case REST_METHOD_NAME_DISPLAY_SELECTED_SUBSCRIPTIONS : 
			case REST_METHOD_NAME_DISPLAY_REJECTED_SUBSCRIPTIONS : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_TAKE_ACTION_ON_SUBSCRIPTIONS :
			case REST_METHOD_NAME_TAKE_ACTION_ON_TUTOR_ENQUIRY :
			case REST_METHOD_NAME_TAKE_ACTION_ON_TUTOR_REGISTRATION : {
				handleTakeActionSecurity();
				break;
			}
			case REST_METHOD_NAME_SEND_EMAIL : {
				handleSendEmailSecurity();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
	
	private void handleTakeActionSecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.gridName)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_GRID_REFERENCE_ACCESS,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.button)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_BUTTON_ACTION,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		} else {
			switch(button) {
				case BUTTON_ACTION_CONTACTED : 
				case BUTTON_ACTION_RECONTACT : 
				case BUTTON_ACTION_REJECT : 
				case BUTTON_ACTION_VERIFY:
				case BUTTON_ACTION_REVERIFY : 
				case BUTTON_ACTION_FAIL_VERIFY : 
				case BUTTON_ACTION_SELECT : 
				case BUTTON_ACTION_RECONTACTED : {
					break;
				}
				default : {
					ApplicationUtils.appendMessageInMapAttribute(
							this.securityFailureResponse, 
							AdminConstants.VALIDATION_MESSAGE_INVALID_BUTTON_ACTION,
							RESPONSE_MAP_ATTRIBUTE_MESSAGE);
					this.securityPassed = false;
					break;
				}
			}
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.uniqueId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_UNIQUE_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		switch(button) {
			case BUTTON_ACTION_CONTACTED : 
			case BUTTON_ACTION_VERIFY:
			case BUTTON_ACTION_REVERIFY : 
			case BUTTON_ACTION_RECONTACTED : 
			case BUTTON_ACTION_SELECT : {
				break;
			}
			case BUTTON_ACTION_RECONTACT : 
			case BUTTON_ACTION_FAIL_VERIFY : 
			case BUTTON_ACTION_REJECT : {
				if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.remarks)) {
					ApplicationUtils.appendMessageInMapAttribute(
							this.securityFailureResponse, 
							AdminConstants.VALIDATION_MESSAGE_PLEASE_ENTER_REMARKS,
							RESPONSE_MAP_ATTRIBUTE_MESSAGE);
					this.securityPassed = false;
				}
				break;
			}
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket( 
					this.methodName + LINE_BREAK + getActiveUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE) + LINE_BREAK + this.gridName + LINE_BREAK + this.button + LINE_BREAK + this.uniqueId + LINE_BREAK + this.remarks);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleSendEmailSecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.validateEmailAddress(this.recepientEmailId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_RECEPIENT_ADDRESS,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.emailSalutationName)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_SALUTATION,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.emailSubject)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_BUTTON_ACTION,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		} 
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.emailText)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_UNIQUE_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(
					this.methodName + LINE_BREAK + getActiveUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE) + LINE_BREAK + this.recepientEmailId + LINE_BREAK + this.emailSalutationName + LINE_BREAK + this.emailSubject + LINE_BREAK + this.emailText);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
*/}
