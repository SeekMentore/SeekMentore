package com.constants.components;

import com.constants.ApplicationConstants;

public interface DemoTrackerConstants extends ApplicationConstants {
	
	String DEMO_STATUS_FAILED = "FAILED";
	String DEMO_STATUS_SUCCESS = "SUCCESS";
	String DEMO_STATUS_PENDING = "PENDING";
	String DEMO_STATUS_RESCHEDULED = "RE-SCHEDULED";
	String DEMO_STATUS_CANCELED = "CANCELED";
	
	String VALIDATION_MESSAGE_INVALID_DEMO_TRACKER_ID = "Invalid Demo Tracker Id.";
	String VALIDATION_MESSAGE_INVALID_RESCHEDULE_DATE_AND_TIME = "Invalid Re-schedule date & time.";
	
	String RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE = "FAILURE_MESSAGE";
	String RESPONSE_MAP_ATTRIBUTE_FAILURE = "FAILURE";
}
