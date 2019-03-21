package com.constants.components;

public interface AdminConstants extends ResponseMapConstants {
	
	String VALIDATION_MESSAGE_TENTATIVE_TUTOR_ID_ABSENT = "No 'tentativeTutorId' found in request, hence cannot perform action.";
	String VALIDATION_MESSAGE_ENQUIRY_ID_ABSENT = "No 'enquiryId' found in request, hence cannot perform action.";
	
	String ACTION_SUCCESSFUL = "Action successful";
	
	String SUPPORT_TEAM_REPORT = "SUPPORT_TEAM_REPORT";
	String ADMIN_REPORT = "ADMIN_REPORT";
	
	String USER_EMPLOYEE = "01";
	String USER_TUTOR = "02";
	String USER_CUSTOMER = "03";
	
	String STATUS_RESOLVED = "02";
}
