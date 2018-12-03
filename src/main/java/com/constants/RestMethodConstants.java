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
	
	/************************************************************************************************************/
	String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
	// Login REST methods
	String REST_METHOD_NAME_TO_VALIDATE_CREDENTIAL = "/validateCredential";
	String REST_METHOD_NAME_TO_CHECK_UI_PATH_ACCESS = "/checkUIpathAccess";
	String REST_METHOD_NAME_RESET_PASSWORD = "/resetPassword";
	String REST_METHOD_NAME_CHANGE_PASSWORD = "/changePassword";
	String REST_METHOD_NAME_TO_LOGOUT = "/logout";
	// Commons REST methods
	String REST_METHOD_NAME_TO_GET_USER = "/getUser";
	String REST_METHOD_NAME_TO_GET_SERVER_INFO = "/getServerInfo";
	String REST_METHOD_NAME_TO_GET_ERROR_DETAILS = "/getErrorDetails";
	String REST_METHOD_NAME_TO_GET_LOGIN_BASIC_INFO = "/getLoginBasicInfo";
	String REST_METHOD_NAME_TO_GET_EMAIL_TEMPLATES = "/getEmailTemplates";
	String REST_METHOD_NAME_TO_LOAD_EMAIL_TEMPLATE = "/loadEmailTemplate";
	String REST_METHOD_NAME_SEND_EMAIL = "/sendEmail";
	// Employee REST methods
	String REST_METHOD_NAME_ALERT_REMINDER_LIST = "/alertReminderList";
	String REST_METHOD_NAME_TASK_LIST = "/taskList";
	String REST_METHOD_NAME_WORKFLOW_LIST = "/workflowList";
	// Admin REST methods
	String REST_METHOD_NAME_REGISTERED_TUTORS_LIST = "/registeredTutorsList";
	String REST_METHOD_NAME_BLACKLIST_REGISTERED_TUTORS = "/blacklistRegisteredTutors";
	String REST_METHOD_NAME_UN_BLACKLIST_REGISTERED_TUTORS = "/unBlacklistRegisteredTutors";
	String REST_METHOD_NAME_SUBSCRIBED_CUSTOMERS_LIST = "/subscribedCustomersList";
	String REST_METHOD_NAME_BLACKLIST_SUBSCRIBED_CUSTOMERS = "/blacklistSubscribedCustomers";
	String REST_METHOD_NAME_UN_BLACKLIST_SUBSCRIBED_CUSTOMERS = "/unBlacklistSubscribedCustomers";
	// Admin - RegisteredTutor REST Methods
	String REST_METHOD_NAME_UPLOADED_DOCUMENTS = "/uploadedDocuments";
	String REST_METHOD_NAME_APPROVE_TUTOR_DOCUMENT_LIST = "/approveTutorDocumentList";
	String REST_METHOD_NAME_SEND_REMINDER_TUTOR_DOCUMENT_LIST = "/sendReminderTutorDocumentList";
	String REST_METHOD_NAME_REJECT_TUTOR_DOCUMENT_LIST = "/rejectTutorDocumentList";
	String REST_METHOD_NAME_BANK_DETAILS = "/bankDetails";
	String REST_METHOD_NAME_APPROVE_BANK_ACCOUNT_LIST = "/approveBankAccountList";
	String REST_METHOD_NAME_MAKE_DEFAULT_BANK_ACCOUNT = "/makeDefaultBankAccount";
	String REST_METHOD_NAME_REJECT_BANK_ACCOUNT_LIST = "/rejectBankAccountList";
	String REST_METHOD_NAME_CURRENT_PACKAGES = "/currentPackages";
	String REST_METHOD_NAME_HISTORY_PACKAGES = "/historyPackages";
	// Sales REST methods
	String REST_METHOD_NAME_PENDING_ENQUIRIES_LIST = "/pendingEnquiriesList";
	String REST_METHOD_NAME_COMPLETED_ENQUIRIES_LIST = "/completedEnquiriesList";
	String REST_METHOD_NAME_ABORTED_ENQUIRIES_LIST = "/abortedEnquiriesList";
	String REST_METHOD_NAME_CURRENT_CUSTOMER_ALL_PENDING_ENQUIRIES_LIST = "/currentCustomerAllPendingEnquiriesList";
	String REST_METHOD_NAME_TO_BE_MAPPED_ENQUIRIES_GRID_LIST = "/toBeMappedEnquiriesGridList";
	String REST_METHOD_NAME_ALL_MAPPING_ELIGIBLE_TUTORS_LIST = "/allMappingEligibleTutorsList";
	String REST_METHOD_NAME_CURRENT_ENQUIRY_ALL_MAPPED_TUTORS_LIST = "/currentEnquiryAllMappedTutorsList";
	String REST_METHOD_NAME_ALL_PENDING_MAPPED_TUTORS_LIST = "/allPendingMappedTutorsList";
	String REST_METHOD_NAME_ALL_DEMO_READY_MAPPED_TUTORS_LIST = "/allDemoReadyMappedTutorsList";
	String REST_METHOD_NAME_ALL_DEMO_SCHEDULED_MAPPED_TUTORS_LIST = "/allDemoScheduledMappedTutorsList";
	String REST_METHOD_NAME_CURRENT_TUTOR_ALL_MAPPING_LIST = "/currentTutorAllMappingList";
	String REST_METHOD_NAME_CURRENT_TUTOR_ALL_SCHEDULED_DEMO_LIST = "/currentTutorAllScheduledDemoList";
	String REST_METHOD_NAME_SCHEDULED_DEMO_LIST = "/scheduledDemoList";
	String REST_METHOD_NAME_RESCHEDULED_DEMO_LIST = "/reScheduledDemoList";
	String REST_METHOD_NAME_SUCCESSFUL_DEMO_LIST = "/successfulDemoList";
	String REST_METHOD_NAME_FAILED_DEMO_LIST = "/failedDemoList";
	String REST_METHOD_NAME_CANCELED_DEMO_LIST = "/canceledDemoList";
	// Support REST methods
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_BECOME_TUTOR_LIST = "/downloadAdminReportBecomeTutorList";
	String REST_METHOD_NAME_NON_CONTACTED_BECOME_TUTORS_LIST = "/nonContactedBecomeTutorsList";
	String REST_METHOD_NAME_NON_VERIFIED_BECOME_TUTORS_LIST = "/nonVerifiedBecomeTutorsList";
	String REST_METHOD_NAME_VERIFIED_BECOME_TUTORS_LIST = "/verifiedBecomeTutorsList";
	String REST_METHOD_NAME_VERIFICATION_FAILED_BECOME_TUTORS_LIST = "/verificationFailedBecomeTutorsList";
	String REST_METHOD_NAME_TO_BE_RECONTACTED_BECOME_TUTORS_LIST = "/toBeReContactedBecomeTutorsList";
	String REST_METHOD_NAME_SELECTED_BECOME_TUTORS_LIST = "/selectedBecomeTutorsList";
	String REST_METHOD_NAME_REJECTED_BECOME_TUTORS_LIST = "/rejectedBecomeTutorsList";
	String REST_METHOD_NAME_REGISTERED_BECOME_TUTORS_LIST = "/registeredBecomeTutorsList";
	String REST_METHOD_NAME_BLACKLIST_BECOME_TUTOR_LIST = "/blacklistBecomeTutorList";
	String REST_METHOD_NAME_UN_BLACKLIST_BECOME_TUTOR_LIST = "/unBlacklistBecomeTutorList";
	String REST_METHOD_NAME_TAKE_ACTION_ON_BECOME_TUTOR = "/takeActionOnBecomeTutor";
	String REST_METHOD_NAME_UPDATE_BECOME_TUTOR_RECORD = "/updateBecomeTutorRecord";
	String REST_METHOD_NAME_NON_CONTACTED_ENQUIRIES_LIST = "/nonContactedEnquiriesList";
	String REST_METHOD_NAME_NON_VERIFIED_ENQUIRIES_LIST = "/nonVerifiedEnquiriesList";
	String REST_METHOD_NAME_VERIFIED_ENQUIRIES_LIST = "/verifiedEnquiriesList";
	String REST_METHOD_NAME_VERIFICATION_FAILED_ENQUIRIES_LIST = "/verificationFailedEnquiriesList";
	String REST_METHOD_NAME_TO_BE_RECONTACTED_ENQUIRIES_LIST = "/toBeReContactedEnquiriesList";
	String REST_METHOD_NAME_SELECTED_ENQUIRIES_LIST = "/selectedEnquiriesList";
	String REST_METHOD_NAME_REJECTED_ENQUIRIES_LIST = "/rejectedEnquiriesList";	
	String REST_METHOD_NAME_BLACKLIST_ENQUIRY_REQUEST_LIST = "/blacklistEnquiryRequestList";
	String REST_METHOD_NAME_UN_BLACKLIST_ENQUIRY_REQUEST_LIST = "/unBlacklistEnquiryRequestList";
	String REST_METHOD_NAME_TAKE_ACTION_ON_FIND_TUTOR = "/takeActionOnFindTutor";
	String REST_METHOD_NAME_NON_CONTACTED_SUBSCRIPTIONS_LIST = "/nonContactedSubscriptionsList";
	String REST_METHOD_NAME_NON_VERIFIED_SUBSCRIPTIONS_LIST = "/nonVerifiedSubscriptionsList";
	String REST_METHOD_NAME_VERIFIED_SUBSCRIPTIONS_LIST = "/verifiedSubscriptionsList";
	String REST_METHOD_NAME_VERIFICATION_FAILED_SUBSCRIPTIONS_LIST = "/verificationFailedSubscriptionsList";
	String REST_METHOD_NAME_TO_BE_RECONTACTED_SUBSCRIPTIONS_LIST = "/toBeReContactedSubscriptionsList";
	String REST_METHOD_NAME_SELECTED_SUBSCRIPTIONS_LIST = "/selectedSubscriptionsList";
	String REST_METHOD_NAME_REJECTED_SUBSCRIPTIONS_LIST = "/rejectedSubscriptionsList";
	String REST_METHOD_NAME_BLACKLIST_SUBSCRIPTION_REQUEST_LIST = "/blacklistSubscriptionRequestList";
	String REST_METHOD_NAME_UN_BLACKLIST_SUBSCRIPTION_REQUEST_LIST = "/unBlacklistSubscriptionRequestList";
	String REST_METHOD_NAME_TAKE_ACTION_ON_SUBSCRIPTION = "/takeActionOnSubscription";
	String REST_METHOD_NAME_NON_CONTACTED_QUERY_LIST = "/nonContactedQueryList";
	String REST_METHOD_NAME_NON_ANSWERED_QUERY_LIST = "/nonAnsweredQueryList";
	String REST_METHOD_NAME_ANSWERED_QUERY_LIST = "/answeredQueryList";
	String REST_METHOD_CUSTOMER_COMPLAINT_LIST = "/customerComplaintList";
	String REST_METHOD_TUTOR_COMPLAINT_LIST = "/tutorComplaintList";
	String REST_METHOD_EMPLOYEE_COMPLAINT_LIST = "/employeeComplaintList";
	String REST_METHOD_RESOLVED_COMPLAINT_LIST = "/resolvedComplaintList";
}
