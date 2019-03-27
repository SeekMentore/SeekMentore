package com.constants.components;

import com.constants.VelocityConstants;

public interface CustomerConstants extends ResponseMapConstants {	
	String MESG_PROPERTY_FILE_NAME = "spring.components.customer";
	
	String VELOCITY_EMAIL_TEMPLATES_SUBSCRIBED_CUSTOMER_FOLDER_PATH = VelocityConstants.VELOCITY_EMAIL_TEMPLATES_FOLDER_PATH + "/subscribedcustomer";
	String PROFILE_CREATION_VELOCITY_TEMPLATE_PATH_SUBSCRIBED_CUSTOMER = VELOCITY_EMAIL_TEMPLATES_SUBSCRIBED_CUSTOMER_FOLDER_PATH + "/profile-created.vm";	
	String VELOCITY_PDF_TEMPLATES_SUBSCRIBED_CUSTOMER_FOLDER_PATH = VelocityConstants.VELOCITY_PDF_TEMPLATES_FOLDER_PATH + "/subscribedcustomer";
	String SUBSCRIBED_CUSTOMER_PROFILE_VELOCITY_TEMPLATE_PATH = VELOCITY_PDF_TEMPLATES_SUBSCRIBED_CUSTOMER_FOLDER_PATH + "/subscribed-customer-profile.vm";
	
	// Message Constants
	String INVALID_CUSTOMER_SERIAL_ID = "INVALID_CUSTOMER_SERIAL_ID";
}
