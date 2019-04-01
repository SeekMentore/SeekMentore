package com.constants.components.publicaccess;

public interface FindTutorConstants extends PublicAccessConstants {
	
	String TABLE_NAME = "FIND_TUTOR";
	
	String COLUMN_NAME_RECORD_LAST_UPDATED = "RECORD_LAST_UPDATED";
	String COLUMN_NAME_REJECTION_REMARKS = "REJECTION_REMARKS";
	String COLUMN_NAME_REJECTION_DATE = "REJECTION_DATE";
	String COLUMN_NAME_WHO_REJECTED = "WHO_REJECTED";
	String COLUMN_NAME_IS_REJECTED = "IS_REJECTED";
	String COLUMN_NAME_SELECTION_REMARKS = "SELECTION_REMARKS";
	String COLUMN_NAME_SELECTION_DATE = "SELECTION_DATE";
	String COLUMN_NAME_WHO_SELECTED = "WHO_SELECTED";
	String COLUMN_NAME_IS_SELECTED = "IS_SELECTED";
	String COLUMN_NAME_RECONTACTED_REMARKS = "RECONTACTED_REMARKS";
	String COLUMN_NAME_RECONTACTED_DATE = "RECONTACTED_DATE";
	String COLUMN_NAME_WHO_RECONTACTED = "WHO_RECONTACTED";
	String COLUMN_NAME_SUGGESTION_REMARKS = "SUGGESTION_REMARKS";
	String COLUMN_NAME_SUGGESTION_DATE = "SUGGESTION_DATE";
	String COLUMN_NAME_WHO_SUGGESTED_FOR_RECONTACT = "WHO_SUGGESTED_FOR_RECONTACT";
	String COLUMN_NAME_IS_TO_BE_RECONTACTED = "IS_TO_BE_RECONTACTED";
	String COLUMN_NAME_VERIFICATION_REMARKS = "VERIFICATION_REMARKS";
	String COLUMN_NAME_VERIFICATION_DATE = "VERIFICATION_DATE";
	String COLUMN_NAME_WHO_VERIFIED = "WHO_VERIFIED";
	String COLUMN_NAME_IS_AUTHENTICATION_VERIFIED = "IS_AUTHENTICATION_VERIFIED";
	String COLUMN_NAME_CONTACTED_REMARKS = "CONTACTED_REMARKS";
	String COLUMN_NAME_CONTACTED_DATE = "CONTACTED_DATE";
	String COLUMN_NAME_WHO_CONTACTED = "WHO_CONTACTED";
	String COLUMN_NAME_IS_CONTACTED = "IS_CONTACTED";
	String COLUMN_NAME_SUBSCRIBED_CUSTOMER = "SUBSCRIBED_CUSTOMER";
	String COLUMN_NAME_ADDITIONAL_DETAILS = "ADDITIONAL_DETAILS";
	String COLUMN_NAME_PREFERRED_TIME_TO_CALL = "PREFERRED_TIME_TO_CALL";
	String COLUMN_NAME_SUBJECTS = "SUBJECTS";
	String COLUMN_NAME_EMAIL_ID = "EMAIL_ID";
	String COLUMN_NAME_CONTACT_NUMBER = "CONTACT_NUMBER";
	String COLUMN_NAME_STUDENT_GRADE = "STUDENT_GRADE";
	String COLUMN_NAME_NAME = "NAME";
	String COLUMN_NAME_ENQUIRY_STATUS = "ENQUIRY_STATUS";
	String COLUMN_NAME_ENQUIRY_DATE = "ENQUIRY_DATE";
	String COLUMN_NAME_FIND_TUTOR_SERIAL_ID = "FIND_TUTOR_SERIAL_ID";
	String COLUMN_NAME_LOCATION ="LOCATION";
	String COLUMN_NAME_REFERENCE = "REFERENCE";
	String COLUMN_NAME_ADDRESS_DETAILS = "ADDRESS_DETAILS";
	String COLUMN_NAME_IS_DATA_MIGRATED = "IS_DATA_MIGRATED";
	String COLUMN_NAME_WHEN_MIGRATED = "WHEN_MIGRATED";
	
	String VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_CONTACT_NUMBER_MOBILE = "Please enter a valid 'Contact Number(Mobile)'";
	String VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_EMAIL_ID = "Please enter a valid 'Email Id'";
	String VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_NAME = "Please enter a valid 'Name'";
	String VALIDATION_MESSAGE_PLEASE_SELECT_A_STUDENT_GRADE = "Please select a valid 'Student Grade'";
	String VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_SUBJECTS = "Please select valid multiple 'Subjects'";
	String VALIDATION_MESSAGE_PLEASE_SELECT_VALID_MULTIPLE_PREFERRED_TIME_TO_CALL = "Please select valid multiple 'Preferred Time to Call'";
	String VALIDATION_MESSAGE_PLEASE_SELECT_VALID_LOCATION = "Please select valid 'Location'";
	String VALIDATION_MESSAGE_PLEASE_SELECT_VALID_REFERENCE = "Please select valid 'Reference'";
	String VALIDATION_MESSAGE_PLEASE_ENTER_ADDRESS_DETAILS = "Please provide 'Address Details'";
	String VALIDATION_MESSAGE_EMAIL_ID_CONTACT_NUMBER_MULTIPLE_CUSTOMERS = "'EmailId' & 'Contact Number' entered by you belong to two different Customers in our system. If you feel this message is an error and someone has stolen your identity please contact us from the form below.";
}
