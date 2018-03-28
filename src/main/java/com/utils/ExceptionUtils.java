package com.utils;

import com.constants.ExceptionConstants;

public class ExceptionUtils implements ExceptionConstants {
	
	public static Throwable getRootCause(final Throwable exception) {
		Throwable root = null;
		if (exception != null) {
			root = org.apache.commons.lang.exception.ExceptionUtils.getCause(exception) == null ? exception	: org.apache.commons.lang.exception.ExceptionUtils.getCause(exception);
		}
		return root;
	}
	
	public static String generateErrorLog(final Throwable exception) {
		return org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(exception).replaceAll("at ", "<br>at ").replaceAll("Caused by", "<br>Caused by");
	}
	
	public static String getLastCausedBy(final Throwable exception) {
		String lastCausedBy = EMPTY_STRING; 
		if (exception != null) {
			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(exception);
			if (stackTrace != null) {
				String[] causedByArray = stackTrace.split("Caused by:");
				if (causedByArray != null && causedByArray.length > 0) {
					String lastSection = causedByArray[causedByArray.length-1];
					String[] atArray = lastSection.split("\\sat\\s");
					if (atArray != null) {
						if (atArray.length >= 1) {
							lastCausedBy += atArray[0];
						}
						if (atArray.length >= 2) {
							lastCausedBy += " at " + atArray[1];
						}
						lastCausedBy = lastCausedBy.replace("\n", " ").replace("\r", " ").replace("\t", " ");
						int maxCausedByLength = 120;
						if (lastCausedBy.length() > maxCausedByLength) {
							lastCausedBy = lastCausedBy.substring(0, maxCausedByLength);
						}
					}
				}
			}
		}
		return lastCausedBy;
	}
}
