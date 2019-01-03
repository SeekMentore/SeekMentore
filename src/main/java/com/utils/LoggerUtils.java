package com.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.constants.BeanConstants;
import com.constants.JNDIandControlConfigurationConstants;
import com.constants.LoggerConstants;
import com.model.ErrorPacket;
import com.service.JNDIandControlConfigurationLoadService;
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
		Boolean isServerLocal = false;
		try {
			isServerLocal = getJNDIandControlConfigurationLoadService().getServerName().equals(JNDIandControlConfigurationConstants.SERVER_NAME_LOCAL);
		} catch(Exception e) {}
		if (isServerLocal) {
			// Only print on console if Server is Local
			System.out.println(message);
		} else {
			logDebugSteps(message);
		}
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
		logOnConsole(message);
		if (isErrorEnabled)
			LOGGER.error(message);
	}
	
	public static void logError(final Throwable exception) {
		final String errorMessage = ExceptionUtils.generateErrorLog(exception);
		logError(errorMessage);
		if (isTraceEnabled)
			LOGGER.trace(errorMessage, exception);
	}
	
	public static void logFatal(final String message) {
		logOnConsole(message);
		if (isFatalEnabled)
			LOGGER.fatal(message);
	}
	
	public static void logFatal(final Throwable exception) {
		final String errorMessage = ExceptionUtils.generateErrorLog(exception);
		logFatal(errorMessage);
		if (isFatalEnabled)
			LOGGER.fatal(errorMessage, exception);
	}
	
	public static void logErrorFromApplicationExceptionConstructor(final Throwable exception) {
		final ErrorPacket errorPacket = new ErrorPacket("RUNTIME_EXCEPTION_CANNOT_CAPTURE_URI", ExceptionUtils.generateErrorLog(exception));
		try {
			getCommonsService().feedErrorRecord(errorPacket);
		} catch (Exception e) {}
		logOnConsole(errorPacket.getErrorTrace());
		if (isErrorEnabled)
			LOGGER.error(errorPacket.getErrorTrace());
		if (isTraceEnabled)
			LOGGER.trace(errorPacket.getErrorTrace(), exception);
	}
	
	public static CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	public static JNDIandControlConfigurationLoadService getJNDIandControlConfigurationLoadService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
	}
}
