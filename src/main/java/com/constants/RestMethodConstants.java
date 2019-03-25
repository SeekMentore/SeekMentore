package com.constants;

public interface RestMethodConstants extends RestParamsConstants {
	String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
	// PublicAccess REST methods
	String REST_METHOD_NAME_TO_BECOME_TUTOR = "/becomeTutor";
	String REST_METHOD_NAME_TO_FIND_TUTOR = "/findTutor";
	String REST_METHOD_NAME_TO_SUBMIT_QUERY = "/submitQuery";
	String REST_METHOD_NAME_TO_SUBSCRIBE = "/subscribe";
	String REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_BECOME_TUTOR = "/getDropdownListDataBecomeTutor";
	String REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_FIND_TUTOR = "/getDropdownListDataFindTutor";
	String REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_SUBSCRIBE_WITH_US = "/getDropdownListDataSubscribeWithUs";
	// Login REST methods
	String REST_METHOD_NAME_TO_VALIDATE_CREDENTIAL = "/validateCredential";
	String REST_METHOD_NAME_TO_CHECK_UI_PATH_ACCESS = "/checkUIpathAccess";
	String REST_METHOD_NAME_RESET_PASSWORD = "/resetPassword";
	String REST_METHOD_NAME_TOKEN_RESET_PASSWORD = "/tokenResetPassword";
	String REST_METHOD_NAME_CHANGE_PASSWORD = "/changePassword";
	String REST_METHOD_NAME_TO_LOGOUT = "/logout";
	// Commons REST methods
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
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_REGISTERED_TUTOR_LIST = "/downloadAdminReportRegisteredTutorList";
	String REST_METHOD_NAME_REGISTERED_TUTORS_LIST = "/registeredTutorsList";
	String REST_METHOD_NAME_BLACKLIST_REGISTERED_TUTORS = "/blacklistRegisteredTutors";
	String REST_METHOD_NAME_UN_BLACKLIST_REGISTERED_TUTORS = "/unBlacklistRegisteredTutors";
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_SUBSCRIBED_CUSTOMER_LIST = "/downloadAdminReportSubscribedCustomerList";
	String REST_METHOD_NAME_SUBSCRIBED_CUSTOMERS_LIST = "/subscribedCustomersList";
	String REST_METHOD_NAME_BLACKLIST_SUBSCRIBED_CUSTOMERS = "/blacklistSubscribedCustomers";
	String REST_METHOD_NAME_UN_BLACKLIST_SUBSCRIBED_CUSTOMERS = "/unBlacklistSubscribedCustomers";
	// Admin - RegisteredTutor REST Methods
	String REST_METHOD_NAME_UPLOADED_DOCUMENT_LIST = "/uploadedDocumentList";
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_REGISTERED_TUTOR_PROFILE_PDF = "/downloadAdminRegisteredTutorProfilePdf";
	String REST_METHOD_NAME_DOWNLOAD_TUTOR_DOCUMENT = "/downloadTutorDocument";
	String REST_METHOD_NAME_APPROVE_TUTOR_DOCUMENT_LIST = "/approveTutorDocumentList";
	String REST_METHOD_NAME_SEND_REMINDER_TUTOR_DOCUMENT_LIST = "/sendReminderTutorDocumentList";
	String REST_METHOD_NAME_REJECT_TUTOR_DOCUMENT_LIST = "/rejectTutorDocumentList";
	String REST_METHOD_NAME_BANK_DETAIL_LIST = "/bankDetailList";
	String REST_METHOD_NAME_APPROVE_BANK_ACCOUNT_LIST = "/approveBankAccountList";
	String REST_METHOD_NAME_MAKE_DEFAULT_BANK_ACCOUNT = "/makeDefaultBankAccount";
	String REST_METHOD_NAME_REJECT_BANK_ACCOUNT_LIST = "/rejectBankAccountList";
	String REST_METHOD_NAME_CURRENT_SUBSCRIPTION_PACKAGE_LIST = "/currentSubscriptionPackageList";
	String REST_METHOD_NAME_HISTORY_SUBSCRIPTION_PACKAGE_LIST = "/historySubscriptionPackageList";
	String REST_METHOD_NAME_UPDATE_TUTOR_RECORD = "/updateTutorRecord";
	// Admin - SubscribedCustomer REST Methods
	String REST_METHOD_NAME_UPDATE_CUSTOMER_RECORD = "/updateCustomerRecord";
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_SUBSCRIBED_CUSTOMER_PROFILE_PDF = "/downloadAdminSubscribedCustomerProfilePdf";
	// Sales REST methods
	String REST_METHOD_NAME_PENDING_ENQUIRIES_LIST = "/pendingEnquiriesList";
	String REST_METHOD_NAME_COMPLETED_ENQUIRIES_LIST = "/completedEnquiriesList";
	String REST_METHOD_NAME_ABORTED_ENQUIRIES_LIST = "/abortedEnquiriesList";
	String REST_METHOD_NAME_UPDATE_ENQUIRY_RECORD = "/updateEnquiryRecord";
	String REST_METHOD_NAME_TAKE_ACTION_ON_ENQUIRY = "/takeActionOnEnquiry";
	String REST_METHOD_NAME_CURRENT_CUSTOMER_ALL_PENDING_ENQUIRIES_LIST = "/currentCustomerAllPendingEnquiriesList";
	String REST_METHOD_NAME_TO_BE_MAPPED_ENQUIRIES_GRID_LIST = "/toBeMappedEnquiriesGridList";
	String REST_METHOD_NAME_ALL_MAPPING_ELIGIBLE_TUTORS_LIST = "/allMappingEligibleTutorsList";
	String REST_METHOD_NAME_MAP_REGISTERED_TUTORS = "/mapRegisteredTutors";
	String REST_METHOD_NAME_CURRENT_ENQUIRY_ALL_MAPPED_TUTORS_LIST = "/currentEnquiryAllMappedTutorsList";
	String REST_METHOD_NAME_UN_MAP_REGISTERED_TUTORS = "/unmapRegisteredTutors";
	String REST_METHOD_NAME_UPDATE_TUTOR_MAPPER_RECORD = "/updateTutorMapperRecord";
	String REST_METHOD_NAME_PENDING_MAPPED_TUTORS_LIST = "/pendingMappedTutorsList";
	String REST_METHOD_NAME_DEMO_READY_MAPPED_TUTORS_LIST = "/demoReadyMappedTutorsList";
	String REST_METHOD_NAME_DEMO_SCHEDULED_MAPPED_TUTORS_LIST = "/demoScheduledMappedTutorsList";
	String REST_METHOD_NAME_ENQUIRY_CLOSED_MAPPED_TUTORS_LIST = "/enquiryClosedMappedTutorsList";
	String REST_METHOD_NAME_TAKE_ACTION_ON_MAPPED_TUTOR = "/takeActionOnMappedTutor";
	String REST_METHOD_NAME_CURRENT_TUTOR_MAPPING_LIST = "/currentTutorMappingList";
	String REST_METHOD_NAME_CURRENT_CUSTOMER_MAPPING_LIST = "/currentCustomerMappingList";
	String REST_METHOD_NAME_CURRENT_TUTOR_SCHEDULED_DEMO_LIST = "/currentTutorScheduledDemoList";
	String REST_METHOD_NAME_CURRENT_CUSTOMER_SCHEDULED_DEMO_LIST = "/currentCustomerScheduledDemoList";
	String REST_METHOD_NAME_SCHEDULE_DEMO = "/scheduleDemo";
	String REST_METHOD_NAME_SCHEDULED_DEMO_LIST = "/scheduledDemoList";
	String REST_METHOD_NAME_SUCCESSFUL_DEMO_LIST = "/successfulDemoList";
	String REST_METHOD_NAME_FAILED_DEMO_LIST = "/failedDemoList";
	String REST_METHOD_NAME_CANCELED_DEMO_LIST = "/canceledDemoList";
	String REST_METHOD_NAME_ENQUIRY_CLOSED_DEMO_LIST = "/enquiryClosedDemoList";
	String REST_METHOD_NAME_TAKE_ACTION_ON_DEMO = "/takeActionOnDemo";
	String REST_METHOD_NAME_RE_SCHEDULE_DEMO = "/reScheduleDemo";
	String REST_METHOD_NAME_UPDATE_DEMO_RECORD = "/updateDemoRecord";
	String REST_METHOD_NAME_SELECTED_SUBSCRIPTION_PACKAGE_CURRENT_ASSIGNMENT_LIST = "/selectedSubscriptionPackageCurrentAssignmentList";
	String REST_METHOD_NAME_SELECTED_SUBSCRIPTION_PACKAGE_HISTORY_ASSIGNMENT_LIST = "/selectedSubscriptionPackageHistoryAssignmentList";
	String REST_METHOD_NAME_UPDATE_SUBSCRIPTION_PACKAGE_RECORD = "/updateSubscriptionPackageRecord";
	String REST_METHOD_NAME_TAKE_ACTION_ON_SUBSCRIPTION_PACKAGE = "/takeActionOnSubscriptionPackage";
	String REST_METHOD_NAME_DOWNLOAD_SUBSCRIPTION_PACKAGE_CONTRACT_PDF = "/downloadSubscriptionPackageContractPdf";
	String REST_METHOD_NAME_UPDATE_SUBSCRIPTION_PACKAGE_ASSIGNMENT_RECORD = "/updateSubscriptionPackageAssignmentRecord";
	String REST_METHOD_NAME_TAKE_ACTION_ON_SUBSCRIPTION_PACKAGE_ASSIGNMENT = "/takeActionOnSubscriptionPackageAssignment";
	String REST_METHOD_NAME_NEW_ASSIGNMENT_LIST = "/newAssignmentList";
	String REST_METHOD_NAME_STARTED_ASSIGNMENT_LIST = "/startedAssignmentList";
	String REST_METHOD_NAME_HOURS_COMPLETED_ASSIGNMENT_LIST = "/hoursCompletedAssignmentList";
	String REST_METHOD_NAME_REVIEWED_ASSIGNMENT_LIST = "/reviewedAssignmentList";
	String REST_METHOD_NAME_GET_PACKAGE_ASSIGNMENT_RECORD = "/getPackageAssignmentRecord";
	String REST_METHOD_NAME_INSERT_ASSIGNMENT_ATTENDANCE = "/insertAssignmentAttendance";
	String REST_METHOD_NAME_ASSIGNMENT_ATTENDANCE_LIST = "/assignmentAttendanceList";
	String REST_METHOD_NAME_GET_ASSIGNMENT_ATTENDANCE_UPLOADED_DOCUMENT_COUNT_AND_EXISTENCE = "/getAssignmentAttendanceUploadedDocumentCountAndExistence";
	String REST_METHOD_NAME_UPDATE_ASSIGNMENT_ATTENDANCE = "/updateAssignmentAttendance";
	String REST_METHOD_NAME_DOWNLOAD_ATTENDANCE_TRACKER_SHEET = "/downloadAttendanceTrackerSheet";
	String REST_METHOD_NAME_DOWNLOAD_ASSIGNMENT_ATTENDANCE_DOCUMENT_FILE = "/downloadAssignmentAttendanceDocumentFile";
	String REST_METHOD_NAME_REMOVE_ASSIGNMENT_ATTENDANCE_DOCUMENT_FILE = "/removeAssignmentAttendanceDocumentFile";
	String REST_METHOD_NAME_DOWNLOAD_ASSIGNMENT_ATTENDANCE_ALL_DOCUMENTS = "/downloadAssignmentAttendanceAllDocuments";
	// Support REST methods
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_BECOME_TUTOR_LIST = "/downloadAdminReportBecomeTutorList";
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_BECOME_TUTOR_PROFILE_PDF = "/downloadAdminBecomeTutorProfilePdf";
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
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_FIND_TUTOR_LIST = "/downloadAdminReportFindTutorList";
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_FIND_TUTOR_PROFILE_PDF = "/downloadAdminFindTutorProfilePdf";
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
	String REST_METHOD_NAME_UPDATE_FIND_TUTOR_RECORD = "/updateFindTutorRecord";
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_SUBSCRIBE_WITH_US_LIST = "/downloadAdminReportSubscribeWithUsList";
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
	String REST_METHOD_NAME_UPDATE_SUBSCRIPTION_RECORD = "/updateSubscriptionRecord";
	String REST_METHOD_NAME_NON_CONTACTED_QUERY_LIST = "/nonContactedQueryList";
	String REST_METHOD_NAME_NON_ANSWERED_QUERY_LIST = "/nonAnsweredQueryList";
	String REST_METHOD_NAME_ANSWERED_QUERY_LIST = "/answeredQueryList";
	String REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_SUBMIT_QUERY_LIST = "/downloadAdminReportSubmitQueryList";
	String REST_METHOD_NAME_TAKE_ACTION_ON_SUBMIT_QUERY = "/takeActionOnSubmitQuery";
	String REST_METHOD_NAME_UPDATE_SUBMIT_QUERY_RECORD = "/updateSubmitQueryRecord";
	String REST_METHOD_NAME_CUSTOMER_COMPLAINT_LIST = "/customerComplaintList";
	String REST_METHOD_NAME_TUTOR_COMPLAINT_LIST = "/tutorComplaintList";
	String REST_METHOD_NAME_EMPLOYEE_COMPLAINT_LIST = "/employeeComplaintList";
	String REST_METHOD_NAME_RESOLVED_COMPLAINT_LIST = "/resolvedComplaintList";
	String REST_METHOD_NAME_NOT_RESOLVED_COMPLAINT_LIST = "/holdComplaintList";
	String REST_METHOD_NAME_TAKE_ACTION_ON_COMPLAINT = "/takeActionOnComplaint";
	String REST_METHOD_NAME_UPDATE_COMPLAINT_RECORD = "/updateComplaintRecord";
}
