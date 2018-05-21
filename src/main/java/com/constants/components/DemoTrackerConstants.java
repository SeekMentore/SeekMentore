package com.constants.components;

import com.constants.ApplicationConstants;

public interface DemoTrackerConstants extends ApplicationConstants {
	
	String DEMO_STATUS_FAILED = "FAILED";
	String DEMO_STATUS_SUCCESS = "SUCCESS";
	String DEMO_STATUS_PENDING = "PENDING";
	String DEMO_STATUS_RESCHEDULED = "RE-SCHEDULED";
	String DEMO_STATUS_CANCELED = "CANCELED";
	
	String VALIDATION_MESSAGE_INVALID_DEMO_TRACKER_ID = "Invalid Demo Tracker Id.";
	String VALIDATION_MESSAGE_INVALID_TAKE_ACTION_REMARKS_ID = "Please enter remarks for Failed / Canceled Demo.";
	String VALIDATION_MESSAGE_INVALID_RESCHEDULE_REMARKS_ID = "Please enter remarks for Re-Scheduling Demo.";
	String VALIDATION_MESSAGE_INVALID_RESCHEDULE_DATE_AND_TIME = "Invalid Re-schedule date & time.";
	
	String VELOCITY_TEMPLATES_ENQUIRIES_PATH = AdminConstants.VELOCITY_TEMPLATES_ADMIN_FOLDER_PATH + "/demo";
	String VELOCITY_TEMPLATES_DEMO_RESCHEDULED_TUTOR_EMAIL_PATH = VELOCITY_TEMPLATES_ENQUIRIES_PATH + "/demo-rescheduled-tutor-email.vm";
	String VELOCITY_TEMPLATES_DEMO_RESCHEDULED_CLIENT_EMAIL_PATH = VELOCITY_TEMPLATES_ENQUIRIES_PATH + "/demo-rescheduled-client-email.vm";
	String VELOCITY_TEMPLATES_DEMO_SUCCESS_TUTOR_EMAIL_PATH = VELOCITY_TEMPLATES_ENQUIRIES_PATH + "/demo-success-tutor-email.vm";
	String VELOCITY_TEMPLATES_DEMO_SUCCESS_CLIENT_EMAIL_PATH = VELOCITY_TEMPLATES_ENQUIRIES_PATH + "/demo-success-client-email.vm";
	String VELOCITY_TEMPLATES_DEMO_FAILED_EMAIL_PATH = VELOCITY_TEMPLATES_ENQUIRIES_PATH + "/demo-failed-email.vm";
	String VELOCITY_TEMPLATES_DEMO_CANCEL_TUTOR_EMAIL_PATH = VELOCITY_TEMPLATES_ENQUIRIES_PATH + "/demo-cancel-tutor-email.vm";
	String VELOCITY_TEMPLATES_DEMO_CANCEL_CLIENT_EMAIL_PATH = VELOCITY_TEMPLATES_ENQUIRIES_PATH + "/demo-cancel-client-email.vm";
	
	String RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE = "FAILURE_MESSAGE";
	String RESPONSE_MAP_ATTRIBUTE_FAILURE = "FAILURE";
}
