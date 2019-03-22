package com.constants.components;

public interface SubscriptionPackageConstants extends SalesConstants {
	
	String BUTTON_ACTIVATE_SUBSCRIPTION = "activateSubscription";
	String BUTTON_END_SUBSCRIPTION = "endSubscription";
	String BUTTON_CREATE_ASSIGNMENT_SUBSCRIPTION = "createAssignment";
	
	String SUBSCRIPTION_ALREADY_ACTIVE = "SUBSCRIPTION_ALREADY_ACTIVE";
	String SUBSCRIPTION_ALREADY_TERMINATED = "SUBSCRIPTION_ALREADY_TERMINATED";
	String SUBSCRIPTION_NOT_ACTIVE = "SUBSCRIPTION_NOT_ACTIVE";
	String SUBSCRIPTION_HAS_RUNNING_ASSIGNMENT = "SUBSCRIPTION_HAS_RUNNING_ASSIGNMENT";
	String INVALID_PACKAGE_ASSIGNMENT_SERIAL_ID = "INVALID_PACKAGE_ASSIGNMENT_SERIAL_ID";
	String EXTRA_HOURS_TAUGHT = "EXTRA_HOURS_TAUGHT";
	String ZERO_HOURS_TAUGHT = "ZERO_HOURS_TAUGHT";
	String NULL_TOPICS_TAUGHT = "NULL_TOPICS_TAUGHT";
	String SUBSCRIPTION_PACKAGE_ID_MISSING = "SUBSCRIPTION_PACKAGE_ID_MISSING";
	String INVALID_PACKAGE_BILLING_TYPE = "INVALID_PACKAGE_BILLING_TYPE";
	String INVALID_FINALIZED_RATE_CLIENT = "INVALID_FINALIZED_RATE_CLIENT";
	String INVALID_FINALIZED_RATE_TUTOR = "INVALID_FINALIZED_RATE_TUTOR";
	String INVALID_IS_CUSTOMER_GRIEVED = "INVALID_IS_CUSTOMER_GRIEVED";
	String INVALID_CUSTOMER_HAPPINESS_INDEX = "INVALID_CUSTOMER_HAPPINESS_INDEX";
	String INVALID_CUSTOMER_REMARKS = "INVALID_CUSTOMER_REMARKS";
	String INVALID_IS_TUTOR_GRIEVED = "INVALID_IS_TUTOR_GRIEVED";
	String INVALID_TUTOR_HAPPINESS_INDEX = "INVALID_TUTOR_HAPPINESS_INDEX";
	String INVALID_TUTOR_REMARKS = "INVALID_TUTOR_REMARKS";
	String INVALID_ADMIN_REMARKS = "INVALID_ADMIN_REMARKS";
	String INVALID_ADDITIONAL_DETAILS_CLIENT = "INVALID_ADDITIONAL_DETAILS_CLIENT";
	String INVALID_ADDITIONAL_DETAILS_TUTOR = "INVALID_ADDITIONAL_DETAILS_TUTOR";
	String INVALID_ACTIVATING_REMARKS = "INVALID_ACTIVATING_REMARKS";
	String INVALID_TERMINATING_REMARKS = "INVALID_TERMINATING_REMARKS";
	String INVALID_TOTAL_HOURS = "INVALID_TOTAL_HOURS";
	String PACKAGE_ASSIGNMENT_ID_MISSING = "PACKAGE_ASSIGNMENT_ID_MISSING";
	String ASSIGNMENT_ATTENDANCE_ID_MISSING = "ASSIGNMENT_ATTENDANCE_ID_MISSING";
	String DOCUMENT_TYPE_MISSING = "DOCUMENT_TYPE_MISSING";
	String INVALID_CLASSWORK_PROVIDED = "INVALID_CLASSWORK_PROVIDED";
	String INVALID_HOMEWORK_PROVIDED = "INVALID_HOMEWORK_PROVIDED";
	String INVALID_TEST_PROVIDED = "INVALID_TEST_PROVIDED";
	String BLANK_TUTOR_REMARKS = "BLANK_TUTOR_REMARKS";
	String INVALID_PUNCTUALITY_INDEX = "INVALID_PUNCTUALITY_INDEX";
	String BLANK_PUNCTUALITY_REMARKS = "BLANK_PUNCTUALITY_REMARKS";
	String INVALID_EXPERTISE_INDEX = "INVALID_EXPERTISE_INDEX";
	String BLANK_EXPERTISE_REMARKS = "BLANK_EXPERTISE_REMARKS";
	String INVALID_KNOWLEDGE_INDEX = "INVALID_KNOWLEDGE_INDEX";
	String BLANK_KNOWLEDGE_REMARKS = "BLANK_KNOWLEDGE_REMARKS";
	String BLANK_STUDENT_REMARKS = "BLANK_STUDENT_REMARKS";
	
	String VELOCITY_EMAIL_TEMPLATES_SUBSCRIPTION_PATH = VELOCITY_EMAIL_TEMPLATES_SALES_FOLDER_PATH + "/subscriptionpackage";
	String VELOCITY_TEMPLATES_SUBSCRIPTION_ACTIVATED_CLIENT_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_SUBSCRIPTION_PATH + "/subscription-package-activated-client-email.vm";
	String VELOCITY_TEMPLATES_SUBSCRIPTION_ACTIVATED_TUTOR_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_SUBSCRIPTION_PATH + "/subscription-package-activated-tutor-email.vm";
	String VELOCITY_TEMPLATES_SUBSCRIPTION_TERMINATED_CLIENT_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_SUBSCRIPTION_PATH + "/subscription-package-terminated-client-email.vm";
	String VELOCITY_TEMPLATES_SUBSCRIPTION_TERMINATED_TUTOR_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_SUBSCRIPTION_PATH + "/subscription-package-terminated-tutor-email.vm";
	String VELOCITY_TEMPLATES_SUBSCRIPTION_TERMINATED_SALES_TEAM_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_SUBSCRIPTION_PATH + "/subscription-package-terminated-sales-team-email.vm";
	String VELOCITY_TEMPLATES_ASSIGNMENT_STARTED_CLIENT_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_SUBSCRIPTION_PATH + "/package-asignment-started-client-email.vm";
	String VELOCITY_TEMPLATES_ASSIGNMENT_STARTED_TUTOR_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_SUBSCRIPTION_PATH + "/package-assignment-started-tutor-email.vm";
	String VELOCITY_TEMPLATES_ASSIGNMENT_END_SALES_TEAM_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_SUBSCRIPTION_PATH + "/package-asignment-end-sales-team-email.vm";
	String VELOCITY_TEMPLATES_ASSIGNMENT_HOURS_COMPLETED_CLIENT_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_SUBSCRIPTION_PATH + "/package-asignment-hours-completed-client-email.vm";
	String VELOCITY_TEMPLATES_ASSIGNMENT_HOURS_COMPLETED_TUTOR_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_SUBSCRIPTION_PATH + "/package-assignment-hours-completed-tutor-email.vm";
	String VELOCITY_TEMPLATES_ASSIGNMENT_HOURS_COMPLETED_SALES_TEAM_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_SUBSCRIPTION_PATH + "/package-asignment-hours-completed-sales-team-email.vm";
	String VELOCITY_TEMPLATES_ASSIGNMENT_RENEWAL_NOTIFICATION_SALES_TEAM_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_SUBSCRIPTION_PATH + "/package-asignment-renewal-notification-sales-team-email.vm";
	
	// Contract
	String VELOCITY_PDF_TEMPLATES_SUBSCRIPTION_PACKAGE_PATH = VELOCITY_PDF_TEMPLATES_SALES_FOLDER_PATH + "/subscriptionpackage";
	String SUBSCRIPTION_PACKAGE_CONTRACT_PDF_PATH = VELOCITY_PDF_TEMPLATES_SUBSCRIPTION_PACKAGE_PATH + "/subscription-package-contract.vm";
	
	String CUSTOMER_SUBSCRIPTION_PACKAGE_CONTRACT = "01";
	
	String BUTTON_START_ASSIGNMENT = "startAssignment";
	String BUTTON_END_ASSIGNMENT = "endAssignment";
	
	String PACKAGE_ASSIGNMENT_ALREADY_RUNNING = "PACKAGE_ASSIGNMENT_ALREADY_RUNNING";
	String PACKAGE_ASSIGNMENT_ALREADY_COMPLETED = "PACKAGE_ASSIGNMENT_ALREADY_COMPLETED";
	
	String ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_CLASSWORK = "01";
	String ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_HOMEWORK = "02";
	String ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_TEST = "03";
	String ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_OTHER = "04";
}
