package com.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.constants.UserConstants;
import com.model.User;
import com.model.components.OtherContactNumber;
import com.model.components.OtherEmail;

public class UserUtils implements UserConstants {
	
	public static void mapUserPseudoColumnsForRecords(final User user, final ResultSet row, final int rowNum) throws SQLException {
		user.setName(ExceptionUtils.exceptionHandlerForRowMapper(row, user.resolveColumnNameForMapping("name"), String.class));
		user.setContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, user.resolveColumnNameForMapping("contactNumber"), String.class));
		user.setEmailId(ExceptionUtils.exceptionHandlerForRowMapper(row, user.resolveColumnNameForMapping("emailId"), String.class));
		user.setEncryptedPassword(ExceptionUtils.exceptionHandlerForRowMapper(row, user.resolveColumnNameForMapping("encryptedPassword"), String.class));
		user.setUserId(ExceptionUtils.exceptionHandlerForRowMapper(row, user.resolveColumnNameForMapping("userId"), String.class));
		user.setUserType(ExceptionUtils.exceptionHandlerForRowMapper(row, user.resolveColumnNameForMapping("userType"), String.class));
		user.setRecordLastUpdatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, user.resolveColumnNameForMapping("recordLastUpdatedMillis"), Long.class));
		user.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, user.resolveColumnNameForMapping("updatedBy"), String.class));
		user.setUpdatedByName(ExceptionUtils.exceptionHandlerForRowMapper(row, user.resolveColumnNameForMapping("updatedByName"), String.class));
	}
	
	public static void mapUserPseudoColumnsForOtherEmails(final OtherEmail otherEmail, final ResultSet row, final int rowNum) throws SQLException {
		otherEmail.setEmailId(ExceptionUtils.exceptionHandlerForRowMapper(row, otherEmail.resolveColumnNameForMapping("emailId"), String.class));
		otherEmail.setIsPrimary(ExceptionUtils.exceptionHandlerForRowMapper(row, otherEmail.resolveColumnNameForMapping("isPrimary"), String.class));
	}
	
	public static void mapUserPseudoColumnsForOtherContactNumbers(final OtherContactNumber otherContactNumber, final ResultSet row, final int rowNum) throws SQLException {
		otherContactNumber.setContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, otherContactNumber.resolveColumnNameForMapping("contactNumber"), String.class));
		otherContactNumber.setIsPrimary(ExceptionUtils.exceptionHandlerForRowMapper(row, otherContactNumber.resolveColumnNameForMapping("isPrimary"), String.class));
	}
	
	public static User returnUserObjWithoutPasswordInformationFromSessionUserObjectBeforeSendingOnUI(final User user) throws CloneNotSupportedException {
		final User newuserobj = user.clone();
		newuserobj.setEncryptedPassword(null);
		return newuserobj;
	}
}
