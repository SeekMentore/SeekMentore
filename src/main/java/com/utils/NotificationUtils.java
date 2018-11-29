package com.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.constants.GridComponentConstants;
import com.model.Notification;

public class NotificationUtils implements GridComponentConstants {
	
	public static void mapNotificationColumnsForRecords(final Notification notification, final ResultSet row, final int rowNum) throws SQLException {
		notification.setInitiatedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, notification.resolveColumnNameForMapping("initiatedDateMillis"), Long.class));
		notification.setActionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, notification.resolveColumnNameForMapping("actionDateMillis"), Long.class));
		notification.setInitiatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, notification.resolveColumnNameForMapping("initiatedBy"), String.class));
		notification.setActionBy(ExceptionUtils.exceptionHandlerForRowMapper(row, notification.resolveColumnNameForMapping("actionBy"), String.class));
		notification.setDueDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, notification.resolveColumnNameForMapping("dueDateMillis"), Long.class));
		notification.setSubject(ExceptionUtils.exceptionHandlerForRowMapper(row, notification.resolveColumnNameForMapping("subject"), String.class));
		notification.setRecipientUserId(ExceptionUtils.exceptionHandlerForRowMapper(row, notification.resolveColumnNameForMapping("recipientUserId"), String.class));
	}
}
