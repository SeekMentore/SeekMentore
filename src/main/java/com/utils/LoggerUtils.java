package com.utils;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.constants.BeanConstants;
import com.constants.LoggerConstants;
import com.service.components.CommonsService;
import com.utils.context.AppContext;

public class LoggerUtils implements LoggerConstants {
	
	private static final transient Log LOGGER = LogFactory.getLog(LoggerUtils.class);
	
	private static final transient boolean isFatalEnabled = LOGGER.isFatalEnabled();
	private static final transient boolean isInfoEnabled = LOGGER.isInfoEnabled();
	private static final transient boolean isTraceEnabled = LOGGER.isTraceEnabled();
	private static final transient boolean isWarnEnabled = LOGGER.isWarnEnabled();
	private static final transient boolean isDebugEnabled = LOGGER.isDebugEnabled();
	private static final transient boolean isErrorEnabled = LOGGER.isErrorEnabled();
	
	public static void logOnConsole(final String message) {
		System.out.println(message);
	}
	
	public static void logInfoSteps(final String message) {
		if (isInfoEnabled)
			LOGGER.info(message);
	}
	
	public static void logWarnSteps(final String message) {
		if (isWarnEnabled)
			LOGGER.warn(message);
	}
	
	public static void logDebugSteps(final String message) {
		if (isDebugEnabled)
			LOGGER.debug(message);
	}
	
	public static void logError(final String message) {
		if (isErrorEnabled)
			LOGGER.error(message);
	}
	
	public static void logError(final Throwable exception) {
		logError(ExceptionUtils.generateErrorLog(exception));
		if (isTraceEnabled)
			LOGGER.trace(exception.getMessage(), exception);
	}
	
	public static void logFatal(final String message) {
		if (isFatalEnabled)
			LOGGER.fatal(message);
	}
	
	public static void logFatal(final Throwable exception) {
		logFatal(ExceptionUtils.generateErrorLog(exception));
		if (isFatalEnabled)
			LOGGER.fatal(exception.getMessage(), exception);
	}
	
	public static void logErrorFromApplicationExceptionConstructor(final Throwable exception) {
		final Timestamp occurredTimestamp = new Timestamp(new Date().getTime());
		final String message = ExceptionUtils.generateErrorLog(exception);
		getCommonsService().feedErrorRecord(occurredTimestamp, "RUNTIME_EXCEPTION_CANNOT_CAPTURE_URI", message);
		if (isErrorEnabled)
			LOGGER.error(message);
		if (isTraceEnabled)
			LOGGER.trace(exception.getMessage(), exception);
	}
	
	public static CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
}
