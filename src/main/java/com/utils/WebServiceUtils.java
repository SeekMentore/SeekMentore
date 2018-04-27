package com.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.constants.BeanConstants;
import com.constants.ConnectionConstants;
import com.constants.JNDIandControlConfigurationConstants;
import com.constants.WebServiceConstants;
import com.constants.components.publicaccess.PublicAccessConstants;
import com.service.JNDIandControlConfigurationLoadService;
import com.utils.context.AppContext;

public class WebServiceUtils implements WebServiceConstants {
	
	public static String getRemoteIPAddress(final HttpServletRequest request) {
		String remoteIP = request.getRemoteAddr();
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(remoteIP)) {
			remoteIP = request.getHeader("True-Client-IP");
		}
		return remoteIP;
	}
	
	public static String getUserAgent(final HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(userAgent)) {
			userAgent = request.getHeader("user-agent");
		}
		return userAgent;
	}
	
	public static boolean verifyCaptcha (
		final String captchaResponse,
		final HttpServletRequest request
	) throws Exception {
		// Not matching captcha if the server is local
		if (getJNDIandControlConfigurationLoadService().getServerName().equals(JNDIandControlConfigurationConstants.SERVER_NAME_LOCAL))
			return true;
		// Verify captcha response and set values in securityFailureResponse
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(captchaResponse)) {
			final Map<String, String> postParams = new HashMap<String, String>();
			postParams.put(PublicAccessConstants.CAPTCHA_PROPERTY_SECRET, SecurityUtil.decrypt(getJNDIandControlConfigurationLoadService().getControlConfiguration().getCaptchaParams().getEncryptedApiSecret()));
			postParams.put(PublicAccessConstants.CAPTCHA_PROPERTY_RESPONSE, captchaResponse);
			postParams.put(PublicAccessConstants.CAPTCHA_PROPERTY_REMOTEIP, getRemoteIPAddress(request));
			
			final Map<String, String> requestProperties = new HashMap<String, String>();
			requestProperties.put(USER_AGENT, getUserAgent(request));
			requestProperties.put(ACCEPT_LANGUAGE, getJNDIandControlConfigurationLoadService().getControlConfiguration().getRemoteConnectionAcceptedLanguage());
			
			return JSONUtils.getJSONObjectFromString(ConnectionUtils.connectToUnsecuredURL(
																			getJNDIandControlConfigurationLoadService().getControlConfiguration().getCaptchaParams().getApiVerifyURL(), 
																			ConnectionConstants.METHOD_NAME_POST, 
																			postParams, 
																			requestProperties)).getBoolean(PublicAccessConstants.CAPTCHA_RESPONSE_SUCCESS);
		}
		return false;
	}
	
	public static void redirectToPage (
			final String pageURL, 
			final HttpServletRequest request,
			final HttpServletResponse response
	) throws IOException {
		response.sendRedirect(request.getContextPath() + pageURL);
	}
	
	public static JNDIandControlConfigurationLoadService getJNDIandControlConfigurationLoadService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
	}
}
