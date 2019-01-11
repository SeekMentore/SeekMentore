package com.constants.components;

import com.constants.VelocityConstants;

public interface SalesConstants extends ResponseMapConstants {
	
	String BUTTON_ACTION_TO_BE_MAPPED = "toBeMapped";
	String BUTTON_ACTION_ABORTED = "aborted";
	String BUTTON_ACTION_PENDING = "pending";
	String BUTTON_ACTION_DEMO_READY = "demoReady";
	String BUTTON_ACTION_CANCEL = "cancel";
	
	String VELOCITY_EMAIL_TEMPLATES_SALES_PATH = VelocityConstants.VELOCITY_EMAIL_TEMPLATES_FOLDER_PATH + "/sales";
}
