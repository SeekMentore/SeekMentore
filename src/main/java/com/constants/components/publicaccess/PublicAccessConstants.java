package com.constants.components.publicaccess;

import com.constants.ApplicationConstants;
import com.constants.VelocityConstants;

public interface PublicAccessConstants extends ApplicationConstants {
	
	String VELOCITY_TEMPLATES_PUBLIC_ACCESS_FOLDER_PATH = VelocityConstants.VELOCITY_TEMPLATES_FOLDER_PATH + "/publicaccess";
	String VELOCITY_TEMPLATES_BECOME_TUTOR_PATH = VELOCITY_TEMPLATES_PUBLIC_ACCESS_FOLDER_PATH + "/becometutor";
	
	String BECOME_TUTOR_REGISTRATION_NOTIFICATION_VELOCITY_TEMPLATE_PATH = VELOCITY_TEMPLATES_BECOME_TUTOR_PATH + "/registration-notification.vm";
	String BECOME_TUTOR_REGISTRATION_CONFIRMATION_VELOCITY_TEMPLATE_PATH = VELOCITY_TEMPLATES_BECOME_TUTOR_PATH + "/registration-confirmation.vm";
	
	String SUBJECT_NEW_TUTOR_REGISTRATION_REQUEST = "New Tutor Registration request arrived";
	String BECOME_TUTOR_APPLICATION_VM_OBJECT = "becomeTutorApplication";
	String SUBJECT_NEW_TUTOR_REGISTRATION_CONFIRMATION = "Congratulations !! Your Registration is successfull with " + COMPANY_NAME_IN_QUOTES;

}
