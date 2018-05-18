package com.webservices.rest.components;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
import com.model.ErrorPacket;
import com.model.mail.MailAttachment;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.components.AdminService;
import com.service.components.CommonsService;
import com.utils.ApplicationUtils;
import com.utils.FileUtils;
import com.utils.MailUtils;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_ADMIN) 
public class AdminRestService extends AbstractRestWebservice implements RestMethodConstants, AdminConstants {
	
	private String gridName;
	private String button;
	private String uniqueId;
	private String remarks;
	private String recepientEmailId;
	private String emailSalutationName;
	private String emailSubject;
	private String emailText;
	
	/*
	 * Tutor Registration Admin
	 */
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_TUTOR_REGISTRATIONS)
	@Produces({MediaType.APPLICATION_JSON})  
	@POST
    public void downloadAdminReportTutorRegistrations (
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			FileUtils.writeFileToResponse(response, "Admin_Tutor_Registration_Report" + PERIOD + FileConstants.EXTENSION_XLSX, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getAdminService().downloadAdminReportTutorRegistrations());
		}
    }
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_REGISTRATION_PROFILE_PDF)
	@Produces({MediaType.APPLICATION_JSON})  
	@Consumes("application/x-www-form-urlencoded")
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
	
	@Path(REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_REGISTRATIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayNonContactedTutorRegistrations (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_REGISTRATIONS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_REGISTRATIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayNonVerifiedTutorRegistrations (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_REGISTRATIONS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_REGISTRATIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayVerifiedTutorRegistrations (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_REGISTRATIONS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_REGISTRATIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayVerificationFailedTutorRegistrations (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_REGISTRATIONS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_REGISTRATIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayToBeRecontactedTutorRegistrations (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_REGISTRATIONS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_REGISTRATIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displaySelectedTutorRegistrations (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_REGISTRATIONS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_REGISTRATIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayRejectedTutorRegistrations (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_REGISTRATIONS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS_FROM_TUTOR_REGISTRATIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayRegisteredTutorsFromTutorRegistrations (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS_FROM_TUTOR_REGISTRATIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorRegistrations(REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS_FROM_TUTOR_REGISTRATIONS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TAKE_ACTION_ON_TUTOR_REGISTRATION)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String takeActionOnTutorRegistration (
			@FormParam("gridName") final String gridName,
			@FormParam("button") final String button,
			@FormParam("tentativeTutorId") final String tentativeTutorId,
			@FormParam("remarks") final String remarks,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TAKE_ACTION_ON_TUTOR_REGISTRATION;
		this.gridName = gridName;
		this.button = button;
		this.uniqueId = tentativeTutorId;
		this.remarks = remarks;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().takeActionOnTutorRegistration(gridName, button, tentativeTutorId, remarks, getLoggedInUser(request)), REST_MESSAGE_JSON_RESPONSE_NAME);
		} 
		return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
	}
	/*
	 * Tutor Registration Admin
	 */
	/*
	 * Tutor Enquiry Admin
	 */
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_TUTOR_ENQUIRIES)
	@Produces({MediaType.APPLICATION_JSON})  
	@POST
    public void downloadAdminReportTutorEnquiries (
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_TUTOR_ENQUIRIES;
		doSecurity(request);
		if (this.securityPassed) {
			FileUtils.writeFileToResponse(response, "Admin_Tutor_Enquiry_Report" + PERIOD + FileConstants.EXTENSION_XLSX, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getAdminService().downloadAdminReportTutorEnquiry());
		}
    }
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_ENQUIRY_PROFILE_PDF)
	@Produces({MediaType.APPLICATION_JSON})  
	@Consumes("application/x-www-form-urlencoded")
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
	
	@Path(REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_ENQUIRIES)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayNonContactedTutorEnquiries (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_ENQUIRIES;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorEnquiries(REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_ENQUIRIES, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_ENQUIRIES)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayNonVerifiedTutorEnquiries (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_ENQUIRIES;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorEnquiries(REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_ENQUIRIES, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_ENQUIRIES)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayVerifiedTutorEnquiries (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_ENQUIRIES;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorEnquiries(REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_ENQUIRIES, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_ENQUIRIES)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayVerificationFailedTutorEnquiries (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_ENQUIRIES;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorEnquiries(REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_ENQUIRIES, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_ENQUIRIES)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayToBeRecontactedTutorEnquiries (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_ENQUIRIES;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorEnquiries(REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_ENQUIRIES, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_ENQUIRIES)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displaySelectedTutorEnquiries (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_ENQUIRIES;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorEnquiries(REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_ENQUIRIES, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_ENQUIRIES)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayRejectedTutorEnquiries (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_ENQUIRIES;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displayTutorEnquiries(REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_ENQUIRIES, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TAKE_ACTION_ON_TUTOR_ENQUIRY)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String takeActionOnTutorEnquiry (
			@FormParam("gridName") final String gridName,
			@FormParam("button") final String button,
			@FormParam("enquiryId") final String enquiryId,
			@FormParam("remarks") final String remarks,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TAKE_ACTION_ON_TUTOR_ENQUIRY;
		this.gridName = gridName;
		this.button = button;
		this.uniqueId = enquiryId;
		this.remarks = remarks;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().takeActionOnTutorEnquiry(gridName, button, enquiryId, remarks, getLoggedInUser(request)), REST_MESSAGE_JSON_RESPONSE_NAME);
		} 
		return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
	}
	/*
	 * Tutor Enquiry Admin
	 */
	
	/*
	 * Subscription Admin
	 */
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_SUBSCRIPTIONS)
	@Produces({MediaType.APPLICATION_JSON})  
	@POST
    public void downloadAdminReportSubscriptions (
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_SUBSCRIPTIONS;
		doSecurity(request);
		if (this.securityPassed) {
			FileUtils.writeFileToResponse(response, "Admin_Subscriptions_Report" + PERIOD + FileConstants.EXTENSION_XLSX, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getAdminService().downloadAdminReportSubscriptions());
		}
    }
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_INDIVIDUAL_SUBSCRIPTION_PROFILE_PDF)
	@Produces({MediaType.APPLICATION_JSON})  
	@Consumes("application/x-www-form-urlencoded")
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
	
	@Path(REST_METHOD_NAME_DISPLAY_NON_CONTACTED_SUBSCRIPTIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayNonContactedSubscriptions (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_NON_CONTACTED_SUBSCRIPTIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displaySubscriptions(REST_METHOD_NAME_DISPLAY_NON_CONTACTED_SUBSCRIPTIONS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_NON_VERIFIED_SUBSCRIPTIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayNonVerifiedSubscriptions (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_NON_VERIFIED_SUBSCRIPTIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displaySubscriptions(REST_METHOD_NAME_DISPLAY_NON_VERIFIED_SUBSCRIPTIONS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_VERIFIED_SUBSCRIPTIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayVerifiedSubscriptions (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_VERIFIED_SUBSCRIPTIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displaySubscriptions(REST_METHOD_NAME_DISPLAY_VERIFIED_SUBSCRIPTIONS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_SUBSCRIPTIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayVerificationFailedSubscriptions (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_SUBSCRIPTIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displaySubscriptions(REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_SUBSCRIPTIONS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_SUBSCRIPTIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayToBeRecontactedSubscriptions (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_SUBSCRIPTIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displaySubscriptions(REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_SUBSCRIPTIONS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_SELECTED_SUBSCRIPTIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displaySelectedSubscriptions (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_SELECTED_SUBSCRIPTIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displaySubscriptions(REST_METHOD_NAME_DISPLAY_SELECTED_SUBSCRIPTIONS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_REJECTED_SUBSCRIPTIONS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayRejectedSubscriptions (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_REJECTED_SUBSCRIPTIONS;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().displaySubscriptions(REST_METHOD_NAME_DISPLAY_REJECTED_SUBSCRIPTIONS, LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TAKE_ACTION_ON_SUBSCRIPTIONS)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String takeActionOnSubscriptions (
			@FormParam("gridName") final String gridName,
			@FormParam("button") final String button,
			@FormParam("tentativeSubscriptionId") final String tentativeSubscriptionId,
			@FormParam("remarks") final String remarks,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TAKE_ACTION_ON_SUBSCRIPTIONS;
		this.gridName = gridName;
		this.button = button;
		this.uniqueId = tentativeSubscriptionId;
		this.remarks = remarks;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getAdminService().takeActionOnSubscriptions(gridName, button, tentativeSubscriptionId, remarks, getLoggedInUser(request)), REST_MESSAGE_JSON_RESPONSE_NAME);
		} 
		return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
	}	
	
	/*
	 * Subscription Admin
	 */
	
	@Path(REST_METHOD_NAME_SEND_EMAIL)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public void sendEmail (
			@FormDataParam("recepientEmailId") final String recepientEmailId,
			@FormDataParam("email-salutation-name") final String emailSalutationName,
			@FormDataParam("email-subject") final String emailSubject,
			@FormDataParam("emailText") final String emailText,
			@FormDataParam("inputFile_1") final InputStream uploadedInputStreamFile1,
			@FormDataParam("inputFile_1") final FormDataContentDisposition uploadedFileDetailFile1,
			@FormDataParam("inputFile_2") final InputStream uploadedInputStreamFile2,
			@FormDataParam("inputFile_2") final FormDataContentDisposition uploadedFileDetailFile2,
			@FormDataParam("inputFile_3") final InputStream uploadedInputStreamFile3,
			@FormDataParam("inputFile_3") final FormDataContentDisposition uploadedFileDetailFile3,
			@FormDataParam("inputFile_4") final InputStream uploadedInputStreamFile4,
			@FormDataParam("inputFile_4") final FormDataContentDisposition uploadedFileDetailFile4,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_SEND_EMAIL;
		this.recepientEmailId = recepientEmailId;
		this.emailSalutationName = emailSalutationName;
		this.emailSubject = emailSubject;
		this.emailText = emailText;
		doSecurity(request);
		if (this.securityPassed) {
			final List<MailAttachment> attachments = new ArrayList<MailAttachment>();
			if (null != uploadedInputStreamFile1) {
				byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFile1);
				if (fileBytes.length > 0) {
					attachments.add(new MailAttachment(uploadedFileDetailFile1.getFileName(), fileBytes, FileConstants.APPLICATION_TYPE_OCTET_STEAM));
				}
			}
			if (null != uploadedInputStreamFile2) {
				byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFile2);
				if (fileBytes.length > 0) {
					attachments.add(new MailAttachment(uploadedFileDetailFile2.getFileName(), fileBytes, FileConstants.APPLICATION_TYPE_OCTET_STEAM));
				}
			}
			if (null != uploadedInputStreamFile3) {
				byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFile3);
				if (fileBytes.length > 0) {
					attachments.add(new MailAttachment(uploadedFileDetailFile3.getFileName(), fileBytes, FileConstants.APPLICATION_TYPE_OCTET_STEAM));
				}
			}
			if (null != uploadedInputStreamFile4) {
				byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFile4);
				if (fileBytes.length > 0) {
					attachments.add(new MailAttachment(uploadedFileDetailFile4.getFileName(), fileBytes, FileConstants.APPLICATION_TYPE_OCTET_STEAM));
				}
			}
			final Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("emailSalutationName", emailSalutationName);
			attributes.put("emailText", emailText.replaceAll(NEW_LINE, LINE_BREAK));
			attributes.put("user", getLoggedInUser(request));
			attributes.put("companyContactInfo", getJNDIandControlConfigurationLoadService().getControlConfiguration().getCompanyContactDetails().getCompanyAdminContactDetails().getContactDetailsInEmbeddedFormat());
			MailUtils.sendMimeMessageEmail( 
					recepientEmailId, 
					null,
					null,
					emailSubject, 
					VelocityUtils.parseTemplate(ADMIN_EMAIL_VELOCITY_TEMPLATE_PATH, attributes),
					attachments.isEmpty() ? null : attachments);
		}
	}
	
	public AdminService getAdminService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ADMIN_SERVICE, AdminService.class);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	public static JNDIandControlConfigurationLoadService getJNDIandControlConfigurationLoadService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
	}
	
	@Override
	public void doSecurity(final HttpServletRequest request) {
		this.request = request;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
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
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, !this.securityPassed);
	}
	
	private void handleTakeActionSecurity() {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.gridName)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_GRID_REFERENCE_ACCESS,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.button)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_BUTTON_ACTION,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		} else {
			switch(button) {
				case BUTTON_ACTION_CONTACTED : 
				case BUTTON_ACTION_RECONTACT : 
				case BUTTON_ACTION_REJECT : 
				case BUTTON_ACTION_VERIFY:
				case BUTTON_ACTION_REVERIFY : 
				case BUTTON_ACTION_FAILVERIFY : 
				case BUTTON_ACTION_SELECT : 
				case BUTTON_ACTION_RECONTACTED : {
					break;
				}
				default : {
					ApplicationUtils.appendMessageInMapAttribute(
							this.securityFailureResponse, 
							AdminConstants.VALIDATION_MESSAGE_INVALID_BUTTON_ACTION,
							RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
					this.securityPassed = false;
					break;
				}
			}
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.uniqueId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_UNIQUE_ID,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
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
			case BUTTON_ACTION_FAILVERIFY : 
			case BUTTON_ACTION_REJECT : {
				if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.remarks)) {
					ApplicationUtils.appendMessageInMapAttribute(
							this.securityFailureResponse, 
							AdminConstants.VALIDATION_MESSAGE_PLEASE_ENTER_REMARKS,
							RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
					this.securityPassed = false;
				}
				break;
			}
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getLoggedInUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE) + LINE_BREAK + this.gridName + LINE_BREAK + this.button + LINE_BREAK + this.uniqueId + LINE_BREAK + this.remarks);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleSendEmailSecurity() {
		this.securityPassed = true;
		if (!ValidationUtils.validateEmailAddress(this.recepientEmailId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_RECEPIENT_ADDRESS,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.emailSalutationName)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_SALUTATION,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.emailSubject)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_BUTTON_ACTION,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		} 
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.emailText)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					AdminConstants.VALIDATION_MESSAGE_INVALID_UNIQUE_ID,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getLoggedInUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE) + LINE_BREAK + this.recepientEmailId + LINE_BREAK + this.emailSalutationName + LINE_BREAK + this.emailSubject + LINE_BREAK + this.emailText);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
}
