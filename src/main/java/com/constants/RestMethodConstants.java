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
	String REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS_FROM_TUTOR_REGISTRATIONS = "/displayRegisteredTutorsFromTutorRegistrations";
	String REST_METHOD_NAME_TAKE_ACTION_ON_TUTOR_REGISTRATION = "/takeActionOnTutorRegistration";
	// Registered Tutors
	String REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS_LIST = "/registeredTutorsList";
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_DOCUMENT = "/downloadDocumentFromAdmin";
	String REST_METHOD_NAME_APRROVE_DOCUMENT_FROM_ADMIN = "/aprroveDocumentFromAdmin";
	String REST_METHOD_NAME_REJECT_DOCUMENT_FROM_ADMIN = "/rejectDocumentFromAdmin";
	String REST_METHOD_NAME_DOCUMENT_REMINDER_FROM_ADMIN = "/sendDocumentReminderEmail";
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_INDIVIDUAL_REGISTERED_TUTOR_PROFILE_PDF = "/downloadAdminIndividualRegisteredTutorProfilePdf";
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_REGISTERED_TUTORS = "/downloadAdminReportRegisteredTutors";
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
	String REST_METHOD_NAME_TAKE_ACTION_ON_TUTOR_ENQUIRY = "/takeActionOnTutorEnquiry";
	// Subscription Enquiry
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_INDIVIDUAL_SUBSCRIPTION_PROFILE_PDF = "/downloadAdminIndividualSubscriptionProfilePdf";
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_SUBSCRIPTIONS = "/downloadAdminReportSubscriptions";
	String REST_METHOD_NAME_DISPLAY_NON_CONTACTED_SUBSCRIPTIONS = "/displayNonContactedSubscriptions";
	String REST_METHOD_NAME_DISPLAY_NON_VERIFIED_SUBSCRIPTIONS = "/displayNonVerifiedSubscriptions";
	String REST_METHOD_NAME_DISPLAY_VERIFIED_SUBSCRIPTIONS = "/displayVerifiedSubscriptions";
	String REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_SUBSCRIPTIONS = "/displayVerificationFailedSubscriptions";
	String REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_SUBSCRIPTIONS = "/displayToBeRecontactedSubscriptions";
	String REST_METHOD_NAME_DISPLAY_SELECTED_SUBSCRIPTIONS = "/displaySelectedSubscriptions";
	String REST_METHOD_NAME_DISPLAY_REJECTED_SUBSCRIPTIONS = "/displayRejectedSubscriptions";
	String REST_METHOD_NAME_TAKE_ACTION_ON_SUBSCRIPTIONS = "/takeActionOnSubscriptions";
	// Enquiry
	String REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_PENDING_ENQUIRIES = "/displayCustomerWithPendingEnquiries";
	String REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_MAPPED_ENQUIRIES = "/displayCustomerWithMappedEnquiries";
	String REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_ABANDONED_ENQUIRIES = "/displayCustomerWithAbandonedEnquiries";
	String REST_METHOD_NAME_DISPLAY_ALL_ENQUIRIES_FOR_PARTICULAR_CUSTOMER = "/displayAllEnquiriesForParticularCustomer";
	String REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_FOR_ENQUIRY_DETAILS = "/getDropdownListDataForEnquiryDetails";
	String REST_METHOD_NAME_TO_UPDATE_ENQUIRY_DETAILS = "/updateEnquiryDetails";
	String REST_METHOD_NAME_DISPLAY_ALL_ELIGIBLE_TUTORS = "/displayAllEligibleTutors";
	String REST_METHOD_NAME_DISPLAY_ALL_MAPPED_DEMO_PENDING_TUTORS = "/displayAllMappedDemoPendingTutors";
	String REST_METHOD_NAME_DISPLAY_ALL_MAPPED_DEMO_SCHEDULED_TUTORS = "/displayAllMappedDemoScheduledTutors";
	String REST_METHOD_NAME_MAP_TUTORS = "/mapTutors";
	String REST_METHOD_NAME_UNMAP_TUTORS = "/unmapTutors";
	String REST_METHOD_NAME_TO_UPDATE_TUTOR_MAPPER_DETAILS = "/updateTutorMapperDetails";
	String REST_METHOD_NAME_SCHEDULE_DEMO = "/scheduleDemo";
	// Demo
	String REST_METHOD_NAME_DISPLAY_SCHEDULED_DEMOS = "/displayScheduledDemos";
	String REST_METHOD_NAME_DISPLAY_RESCHEDULED_DEMOS = "/displayRescheduledDemos";
	String REST_METHOD_NAME_DISPLAY_SUCCESSFULL_DEMOS = "/displaySuccessfullDemos";
	String REST_METHOD_NAME_DISPLAY_FAILED_DEMOS = "/displayFailedDemos";
	String REST_METHOD_NAME_DISPLAY_CANCELED_DEMOS = "/displayCanceledDemos";
	String REST_METHOD_NAME_DISPLAY_DEMO_DETAILS = "/displayDemoDetails";
	String REST_METHOD_NAME_TO_UPDATE_DEMO_TRACKER_DETAILS = "/updateDemoTrackerDetails";
	String REST_METHOD_NAME_DEMO_SUCCESS = "/demoSuccess";
	String REST_METHOD_NAME_DEMO_FAILURE = "/demoFailure";
	String REST_METHOD_NAME_CANCEL_DEMO = "/cancelDemo";
	String REST_METHOD_NAME_RESCHEDULE_DEMO = "/rescheduleDemo";
	// Send Email
	String REST_METHOD_NAME_SEND_EMAIL = "/sendEmail";
	// Login REST methods
	String REST_METHOD_NAME_TO_VALIDATE_CREDENTIAL = "/validateCredential";
	String REST_METHOD_NAME_CHANGE_PASSWORD = "/changePassword";
	String REST_METHOD_NAME_TO_LOGOUT = "/logout";
	// Commons REST methods
	String REST_METHOD_NAME_TO_GET_USER = "/getUser";
	String REST_METHOD_NAME_TO_GET_SERVER_INFO = "/getServerInfo";
	// Tutor REST methods
	String REST_METHOD_NAME_UPLOAD_DOCUMENTS = "/uploadDocuments";
	String REST_METHOD_NAME_LOAD_TUTOR_RECORD = "/loadTutorRecord";
	String REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_REGISTERED_TUTOR = "/getDropdownListDataRegisteredTutor";
	String REST_METHOD_NAME_TO_UPDATE_DETAILS = "/updateDetails";
	String REST_METHOD_NAME_DOWNLOAD_TUTOR_DOCUMENT = "/downloadDocument";
	//Customer Rest methods
	String REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_SUBSCRIBED_CUSTOMER = "/getDropdownListDataSubscribedCustomer";
	String REST_METHOD_NAME_LOAD_SUBSCRIBED_CUSTOMER_RECORD = "/loadSubscribedCustomerRecord";
	String REST_METHOD_NAME_TO_UPDATE_SUBSCRIBED_CUSTOMER_DETAILS = "/updateSubscribedCustomerDetails";
	String REST_METHOD_NAME_DISPLAY_SUBSCRIBED_CUSTOMERS_LIST = "/subscribedCustomersList";

}
