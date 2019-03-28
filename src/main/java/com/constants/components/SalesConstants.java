package com.constants.components;

import com.constants.VelocityConstants;

public interface SalesConstants extends ResponseMapConstants {
	
	String MESG_PROPERTY_FILE_NAME = "spring.components.sales";

	String BUTTON_ACTION_TO_BE_MAPPED = "toBeMapped";
	String BUTTON_ACTION_ABORTED = "aborted";
	String BUTTON_ACTION_PENDING = "pending";
	String BUTTON_ACTION_DEMO_READY = "demoReady";
	String BUTTON_ACTION_CANCEL = "cancel";
	String BUTTON_ACTION_DEMO_SUCCESS = "demoSuccess";
	String BUTTON_ACTION_DEMO_FAILED = "demoFailed";
	
	String VELOCITY_EMAIL_TEMPLATES_SALES_FOLDER_PATH = VelocityConstants.VELOCITY_EMAIL_TEMPLATES_FOLDER_PATH + "/sales";
	String VELOCITY_PDF_TEMPLATES_SALES_FOLDER_PATH = VelocityConstants.VELOCITY_PDF_TEMPLATES_FOLDER_PATH + "/sales";
}
