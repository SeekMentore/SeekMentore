package com.utils;

import javax.servlet.http.HttpServletRequest;

import com.constants.WebServiceConstants;

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
}
