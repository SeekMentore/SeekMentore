package com.constants;

public interface RestMethodConstants extends RestParamsConstants {
	
	
	// Public Access REST methods
	// REST methods to submit data
	String REST_METHOD_NAME_TO_BECOME_TUTOR = "/becomeTutor";
	String REST_METHOD_NAME_TO_FIND_TUTOR = "/findTutor";
	String REST_METHOD_NAME_TO_SUBMIT_QUERY = "/submitQuery";
	String REST_METHOD_NAME_TO_SUBSCRIBE = "/subscribe";
	// REST methods to receive data
	String REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_BECOME_TUTOR = "/getDropdownListDataBecomeTutor";
	String REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_FIND_TUTOR = "/getDropdownListDataFindTutor";
	String REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_SUBSCRIBE_WITH_US = "/getDropdownListDataSubscribeWithUs";
	
	// Admin REST methods
	// Tutor Registrations
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_REGISTRATION_PROFILE_PDF = "/downloadAdminTutorRegistrationProfilePdf";
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_TUTOR_REGISTRATIONS = "/downloadAdminReportTutorRegistrations";
	String REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_REGISTRATIONS = "/displayNonContactedTutorRegistrations";
	String REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_REGISTRATIONS = "/displayNonVerifiedTutorRegistrations";
	String REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_REGISTRATIONS = "/displayVerifiedTutorRegistrations";
	String REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_REGISTRATIONS = "/displayVerificationFailedTutorRegistrations";
	String REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_REGISTRATIONS = "/displayToBeRecontactedTutorRegistrations";
	String REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_REGISTRATIONS = "/displaySelectedTutorRegistrations";
	String REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_REGISTRATIONS = "/displayRejectedTutorRegistrations";
	String REST_METHOD_NAME_TAKE_ACTION_ON_REGISTERED_TUTORS = "/takeActionOnRegisteredTutors";
	// Tutor Enquiry
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_ENQUIRY_PROFILE_PDF = "/downloadAdminTutorEnquiryProfilePdf";
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_TUTOR_ENQUIRIES = "/downloadAdminReportTutorEnquiries";
	String REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_ENQUIRIES = "/displayNonContactedTutorEnquiries";
	String REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_ENQUIRIES = "/displayNonVerifiedTutorEnquiries";
	String REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_ENQUIRIES = "/displayVerifiedTutorEnquiries";
	String REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_ENQUIRIES = "/displayVerificationFailedTutorEnquiries";
	String REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_ENQUIRIES = "/displayToBeRecontactedTutorEnquiries";
	String REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_ENQUIRIES = "/displaySelectedTutorEnquiries";
	String REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_ENQUIRIES = "/displayRejectedTutorEnquiries";
	String REST_METHOD_NAME_TAKE_ACTION_ON_ENQUIRED_TUTORS = "/takeActionOnEnquiredTutors";
	// Login REST methods
	String REST_METHOD_NAME_TO_VALIDATE_CREDENTIAL = "/validateCredential";
	String REST_METHOD_NAME_TO_LOGOUT = "/logout";
	// Commons REST methods
	String REST_METHOD_NAME_TO_GET_USER = "/getUser";
	String REST_METHOD_NAME_TO_GET_SERVER_INFO = "/getServerInfo";
}
