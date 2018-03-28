package com.constants.components;

import com.constants.ApplicationConstants;

public interface FormConstants extends ApplicationConstants {
	
	String PARAMETER_EMPID = "empId";
	
	String FORM_OBJECT = "form";
	
	String MESSAGE_SUCCESS = "success";
	
	String VELOCITY_TEMPLATES_FOLDER_PATH = "/velocity/templates/form";
	
	String EXCEPTION_NO_EMP_ID_IN_REQUEST = "No empId in request.";
	String EXCEPTION_NO_FORM_PRESENT_FOR_THE_EMP_ID_IN_REQUEST = "No form present for the empId in request.";
}
