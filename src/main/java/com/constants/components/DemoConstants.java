package com.constants.components;

public interface DemoConstants extends SalesConstants {
	
	String DEMO_STATUS_SCHEDULED = "01";
	String DEMO_STATUS_SUCCESS = "02";
	String DEMO_STATUS_FAILED = "03";
	String DEMO_STATUS_CANCELED = "04";
	
	String VALIDATION_MESSAGE_INVALID_DEMO_TRACKER_ID = "Invalid Demo Tracker Id.";
	String VALIDATION_MESSAGE_INVALID_TAKE_ACTION_REMARKS_ID = "Please enter remarks for Failed / Canceled Demo.";
	String VALIDATION_MESSAGE_INVALID_RESCHEDULE_REMARKS_ID = "Please enter remarks for Re-Scheduling Demo.";
	String VALIDATION_MESSAGE_INVALID_RESCHEDULE_DATE_AND_TIME = "Invalid Re-schedule date & time.";
	
	String VELOCITY_EMAIL_TEMPLATES_DEMO_PATH = VELOCITY_EMAIL_TEMPLATES_SALES_FOLDER_PATH + "/demo";
	String VELOCITY_TEMPLATES_DEMO_SUCCESS_TUTOR_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_DEMO_PATH + "/demo-success-tutor-email.vm";
	String VELOCITY_TEMPLATES_DEMO_SUCCESS_CLIENT_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_DEMO_PATH + "/demo-success-client-email.vm";
	String VELOCITY_TEMPLATES_DEMO_FAILED_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_DEMO_PATH + "/demo-failed-email.vm";
	String VELOCITY_TEMPLATES_DEMO_CANCEL_TUTOR_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_DEMO_PATH + "/demo-cancel-tutor-email.vm";
	String VELOCITY_TEMPLATES_DEMO_CANCEL_CLIENT_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_DEMO_PATH + "/demo-cancel-client-email.vm";
	String VELOCITY_TEMPLATES_DEMO_SCHEDULED_TUTOR_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_DEMO_PATH + "/demo-scheduled-tutor-email.vm";
	String VELOCITY_TEMPLATES_DEMO_SCHEDULED_CLIENT_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_DEMO_PATH + "/demo-scheduled-client-email.vm";
	String VELOCITY_TEMPLATES_DEMO_RESCHEDULED_TUTOR_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_DEMO_PATH + "/demo-rescheduled-tutor-email.vm";
	String VELOCITY_TEMPLATES_DEMO_RESCHEDULED_CLIENT_EMAIL_PATH = VELOCITY_EMAIL_TEMPLATES_DEMO_PATH + "/demo-rescheduled-client-email.vm";
	
	// Message Constants
	String INVALID_IS_DEMO_OCCURRED = "DEMO_INVALID_IS_DEMO_OCCURRED";
	String INVALID_Is_CLIENT_SATISFIED_FROM_TUTOR = "DEMO_INVALID_Is_CLIENT_SATISFIED_FROM_TUTOR";
	String INVALID_CLIENT_REMARKS = "DEMO_INVALID_CLIENT_REMARKS";
	String INVALID_IS_TUTOR_SATISFIED_WITH_CLIENT = "DEMO_INVALID_IS_TUTOR_SATISFIED_WITH_CLIENT";
	String INVALID_TUTOR_REMARKS = "DEMO_INVALID_TUTOR_REMARKS";
	String INVALID_IS_ADMIN_SATISFIED_FROM_TUTOR = "DEMO_INVALID_IS_ADMIN_SATISFIED_FROM_TUTOR";
	String INVALID_IS_ADMIN_SATISFIED_WITH_CLIENT = "DEMO_INVALID_IS_ADMIN_SATISFIED_WITH_CLIENT";
	String INVALID_NEED_PRICE_NEGOTIATION_WITH_CLIENT = "DEMO_INVALID_NEED_PRICE_NEGOTIATION_WITH_CLIENT";
	String INVALID_CLIENT_NEGOTIATION_REMARKS = "DEMO_INVALID_CLIENT_NEGOTIATION_REMARKS";
	String INVALID_NEGOTIATED_OVERRIDE_RATE_WITH_CLIENT = "DEMO_INVALID_NEGOTIATED_OVERRIDE_RATE_WITH_CLIENT";
	String INVALID_NEED_PRICE_NEGOTIATION_WITH_TUTOR = "DEMO_INVALID_NEED_PRICE_NEGOTIATION_WITH_TUTOR";
	String INVALID_TUTOR_NEGOTIATION_REMARKS = "DEMO_INVALID_TUTOR_NEGOTIATION_REMARKS";
	String INVALID_NEGOTIATED_OVERRIDE_RATE_WITH_TUTOR = "DEMO_INVALID_NEGOTIATED_OVERRIDE_RATE_WITH_TUTOR";
	String INVALID_ADMIN_REMARKS = "DEMO_INVALID_ADMIN_REMARKS";
	String INVALID_RESCHEDULE_NEW_DATE_AND_TIME = "DEMO_INVALID_RESCHEDULE_NEW_DATE_AND_TIME";
	String INVALID_RESCHEDULE_REMARKS = "DEMO_INVALID_RESCHEDULE_REMARKS";
	String INVALID_RESCHEDULE_DEMO_SERIAL_ID_INVALID = "DEMO_INVALID_RESCHEDULE_DEMO_SERIAL_ID_INVALID";
}
