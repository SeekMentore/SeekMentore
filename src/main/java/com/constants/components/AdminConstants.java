package com.constants.components;

public interface AdminConstants extends ResponseMapConstants {
	
	String VALIDATION_MESSAGE_INVALID_GRID_REFERENCE_ACCESS = "Invalid Grid Reference Access.";
	String VALIDATION_MESSAGE_INVALID_BUTTON_ACTION = "Invalid Button Action.";
	String VALIDATION_MESSAGE_INVALID_UNIQUE_ID = "Invalid Unique {Tutor Id / Enquiry Id / Subscription Id} Id.";
	String VALIDATION_MESSAGE_PLEASE_ENTER_REMARKS = "Please provide remarks for this action.";
	String VALIDATION_MESSAGE_INVALID_RECEPIENT_ADDRESS = "Invalid Recepient Email Address.";
	String VALIDATION_MESSAGE_INVALID_SALUTATION = "Invalid Salutation Name.";
	
	String VALIDATION_MESSAGE_ID_ABSENT = "No 'ID' found in request, hence cannot perform action.";
	String VALIDATION_MESSAGE_COMMENTS_ABSENT = "No 'comments' found in request which needs mandatory comments, hence cannot perform action.";
	String VALIDATION_MESSAGE_GRID_ABSENT = "No 'grid' found in request, hence cannot perform action.";
	String VALIDATION_MESSAGE_BUTTON_ABSENT = "No 'button' found in request, hence cannot perform action.";
	String VALIDATION_MESSAGE_TENTATIVE_TUTOR_ID_ABSENT = "No 'tentativeTutorId' found in request, hence cannot perform action.";
	String VALIDATION_MESSAGE_BUTTON_UNKNOWN = "Unknown 'button' found in request, hence cannot perform action.";
	String VALIDATION_MESSAGE_PARENT_ID_ABSENT = "No 'parentId' found in request, hence cannot perform action.";
	
	String VALIDATION_MESSAGE_UNKONWN_PROPERTY = "Unknown change attribute found in the JSON request";
	String VALIDATION_MESSAGE_NO_ATTRIBUTES_CHANGED = "Please change some properties to update.";
	String ACTION_SUCCESSFUL = "Action successful";
	
	String SUPPORT_TEAM_REPORT = "SUPPORT_TEAM_REPORT";
	String ADMIN_REPORT = "ADMIN_REPORT";
	
	String USER_CUSTOMER = "03";
	String USER_TUTOR = "02";
	String USER_EMPLOYEE = "01";
	
	String STATUS_RESOLVED = "02";
}
