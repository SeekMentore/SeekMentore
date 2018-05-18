package com.utils;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.constants.BeanConstants;
import com.constants.LoginConstants;
import com.exception.ApplicationException;
import com.model.Employee;
import com.model.LogonTracker;
import com.model.User;
import com.model.components.RegisteredTutor;
import com.model.components.SubscribedCustomer;
import com.service.LoginService;
import com.service.components.CommonsService;
import com.utils.context.AppContext;

public class LoginUtils implements LoginConstants {
	
	public static void createNewSession(final HttpServletRequest httpRequest, final User user) throws DataAccessException, InstantiationException, IllegalAccessException {
		final HttpSession session = httpRequest.getSession();
		session.setAttribute(USER_OBJECT, user);
		session.setAttribute(USER_TYPE,  user.getUserType());
		final LogonTracker logonTracker = new LogonTracker();
		logonTracker.setUserId(user.getUserId());
		logonTracker.setUserType(user.getUserType());
		logonTracker.setLoginTime(new Timestamp(new Date().getTime()));
		logonTracker.setLoginFrom(WebServiceUtils.getUserAgent(httpRequest));
		logonTracker.setMachineIp(WebServiceUtils.getRemoteIPAddress(httpRequest));
		getLoginService().feedLogonTracker(logonTracker);
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
			case "Customer" : {
				return type.cast(getCommonsService().getSubscribedCustomerFromDbUsingUserId(user.getUserId()));
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
	
	public static String getEmailIdOfUserInSession(final HttpServletRequest httpRequest) throws DataAccessException, InstantiationException, IllegalAccessException {
		final String userType = getUserTypeFromSession(httpRequest);
		switch(userType) {
			case "Admin" : {
				final Employee employee = Employee.class.cast(getUserTypeObject(httpRequest, Employee.class));
				return employee.getUserId() + "@" + employee.getEmailDomain();
			}
			case "Tutor" : {
				final RegisteredTutor registeredTutor = RegisteredTutor.class.cast(getUserTypeObject(httpRequest, RegisteredTutor.class));
				return registeredTutor.getEmailId();
			}
			case "Customer" : {
				final SubscribedCustomer subscribedCustomer = SubscribedCustomer.class.cast(getUserTypeObject(httpRequest, SubscribedCustomer.class));
				return subscribedCustomer.getEmailId();
			}
		}
		throw new ApplicationException("No Email Id in Session");
	}
	
	public static String getLoggedInUserIdAndTypeForPrinting(final HttpServletRequest request) {
		final User user = getUserFromSession(request);
		return null != user ? (user.getUserId() + "<" + user.getUserType() + ">") : "NO_USER_IN_SESSION";
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
