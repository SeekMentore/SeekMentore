package com.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.constants.BeanConstants;
import com.constants.LoginConstants;
import com.model.User;
import com.service.LoginService;
import com.utils.context.AppContext;

public class LoginUtils implements LoginConstants {
	
	public static void createNewSession(final HttpServletRequest httpRequest, final User user) {
		final HttpSession session = httpRequest.getSession();
		session.setAttribute(USER_OBJECT, user);
	}
	
	public static boolean validateExistingSession(final HttpServletRequest httpRequest) {
		final HttpSession session = httpRequest.getSession();
		final User user = (User)session.getAttribute(USER_OBJECT);
		if (null != user && null != user.getUserId() && !EMPTY_STRING.equals(user.getUserId().trim()) && null != user.getPageAccessTypes() && !user.getPageAccessTypes().isEmpty()) {
			return true;
		}
		session.invalidate();
		return false;
	}
	
	public static User getUserFromSession(final HttpServletRequest httpRequest) {
		final HttpSession session = httpRequest.getSession();
		return (User)session.getAttribute(USER_OBJECT);
	}
	
	public static void logoutUserSession(final HttpServletRequest httpRequest) {
		final HttpSession session = httpRequest.getSession();
		session.invalidate();
	}

	public static LoginService getLoginService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_LOGIN_SERVICE, LoginService.class);
	}
}
