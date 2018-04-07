package com.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.constants.BeanConstants;
import com.constants.LoginConstants;
import com.exception.ApplicationException;
import com.model.User;
import com.service.LoginService;
import com.utils.context.AppContext;

public class LoginUtils implements LoginConstants {
	
	public static boolean isNewSession(final HttpServletRequest httpRequest) {
		final HttpSession session = httpRequest.getSession();
		return null == session.getAttribute(USER_OBJECT);
	}
	
	public static void createNewSession(final HttpServletRequest httpRequest) {
		final String empId = (String) httpRequest.getHeader("SEEK_MENTORE_HEADER");
		if (null == empId || EMPTY_STRING.equals(empId.trim()))
			throw new ApplicationException("Header not present.");
		final User user = getLoginService().getUser(empId);
		if (null == user)
			throw new ApplicationException(empId + " Is not a valid user.");
		final HttpSession session = httpRequest.getSession();
		session.setAttribute(USER_OBJECT, user);
	}
	
	public static void validateExistingSession(final HttpServletRequest httpRequest) {
		final HttpSession session = httpRequest.getSession();
		final User user = (User)session.getAttribute(USER_OBJECT);
		if (null == user.getEmpId() || user.getPageAccessTypes().isEmpty())
			throw new ApplicationException("Not a valid user in session.");
	}
	
	public static User getUserFromSession(final HttpServletRequest httpRequest) {
		final HttpSession session = httpRequest.getSession();
		return (User)session.getAttribute(USER_OBJECT);
	}
	
	public static LoginService getLoginService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_LOGIN_SERVICE, LoginService.class);
	}
}
