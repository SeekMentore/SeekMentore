package com.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.constants.BeanConstants;
import com.constants.LoginConstants;
import com.model.User;
import com.service.LoginService;
import com.service.components.CommonsService;
import com.utils.context.AppContext;

public class LoginUtils implements LoginConstants {
	
	public static void createNewSession(final HttpServletRequest httpRequest, final User user) throws DataAccessException, InstantiationException, IllegalAccessException {
		final HttpSession session = httpRequest.getSession();
		session.setAttribute(USER_OBJECT, user);
		session.setAttribute(USER_TYPE,  user.getUserType());
	}
	
	private static <T extends Object> T getUserTypeObject(final HttpServletRequest httpRequest, final Class<T> type) throws DataAccessException, InstantiationException, IllegalAccessException {
		final User user = getUserFromSession(httpRequest);
		switch(user.getUserType()) {
			case "Admin" : {
				return type.cast(getCommonsService().getEmployeeFromDbUsingUserId(user.getUserId()));
			}
			case "Tutor" : {
				return type.cast(getCommonsService().getTutorFromDbUsingUserId(user.getUserId()));
			}
		}
		return null;
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
	
	public static String getUserTypeFromSession(final HttpServletRequest httpRequest) {
		final HttpSession session = httpRequest.getSession();
		return (String)session.getAttribute(USER_TYPE);
	}
	
	public static <T extends Object> T getUserTypeObjectFromSession(final HttpServletRequest httpRequest, Class<T> type) throws DataAccessException, InstantiationException, IllegalAccessException {
		return getUserTypeObject(httpRequest, type);
	}
	
	public static void logoutUserSession(final HttpServletRequest httpRequest) {
		final HttpSession session = httpRequest.getSession();
		session.invalidate();
	}

	public static LoginService getLoginService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_LOGIN_SERVICE, LoginService.class);
	}
	
	public static CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
}
