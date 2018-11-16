package com.utils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.NewCookie;

import com.constants.BeanConstants;
import com.constants.LoginConstants;
import com.exception.ApplicationException;
import com.model.Employee;
import com.model.LogonTracker;
import com.model.User;
import com.model.UserAccessOptions;
import com.model.components.RegisteredTutor;
import com.model.components.SubscribedCustomer;
import com.service.LoginService;
import com.service.components.CommonsService;
import com.utils.context.AppContext;

public class LoginUtils implements LoginConstants {
	
	public static void createNewSession(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse, final User user) throws Exception {
		final LogonTracker logonTracker = new LogonTracker();
		logonTracker.setUserId(user.getUserId());
		logonTracker.setUserType(user.getUserType());
		logonTracker.setLoginTime(new Timestamp(new Date().getTime()));
		logonTracker.setLoginFrom(WebServiceUtils.getUserAgent(httpRequest));
		logonTracker.setMachineIp(WebServiceUtils.getRemoteIPAddress(httpRequest));
		getLoginService().feedLogonTracker(logonTracker);
		createTokensForNewSession(httpRequest, httpResponse, user);
	}
	
	private static void createTokensForNewSession(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse, final User user) throws Exception {
		final String userObjectJSONString = JSONUtils.convertObjToJSONString(ApplicationUtils.returnUserObjWithoutPasswordInformationFromSessionUserObjectBeforeSendingOnUI(user), USER_OBJECT);
		final String userAuthToken = new String(ApplicationUtils.generateBase64EncodedData(SecurityUtil.encrypt(userObjectJSONString).getBytes()));
		final String userTypeToken = new String(ApplicationUtils.generateBase64EncodedData(SecurityUtil.encrypt(user.getUserType()).getBytes()));
		final NewCookie userAuthCookie = new NewCookie("userAuthToken", userAuthToken, "/", "." + HttpRequestUtil.getDomain(httpRequest), "", -1, false);
		final NewCookie userTypeCookie = new NewCookie("userTypeToken", userTypeToken, "/", "." + HttpRequestUtil.getDomain(httpRequest), "", -1, false);
		httpResponse.addHeader("Set-Cookie", userAuthCookie.toString() + "; HTTPOnly");
		httpResponse.addHeader("Set-Cookie", userTypeCookie.toString() + "; HTTPOnly");
		httpResponse.addHeader("USER-AUTH", userAuthToken);
		httpResponse.addHeader("USER-TYPE", userTypeToken);
		httpRequest.setAttribute("userAuthToken", userAuthToken);
		httpRequest.setAttribute("userTypeToken", userTypeToken);
	}
	
	public static String getUserAuthToken(final HttpServletRequest httpRequest) {
		String userAuthToken = httpRequest.getParameter("user-auth");
		if (userAuthToken == null) {
			userAuthToken = (String)httpRequest.getHeader("USER-AUTH");
			if (userAuthToken == null) {
				final Cookie userAuthCookie = HttpRequestUtil.getCookie(httpRequest, "userAuthToken");
				if (userAuthCookie != null) {
					userAuthToken = userAuthCookie.getValue().toString();
				} else {
					userAuthToken = (String) httpRequest.getAttribute("userAuthToken");
				}
			}
		}
		return userAuthToken;
	}
	
	public static String getUserTypeToken(final HttpServletRequest httpRequest) {
		String userTypeToken = httpRequest.getParameter("user-type");
		if (userTypeToken == null) {
			userTypeToken = (String)httpRequest.getHeader("USER-TYPE");
			if (userTypeToken == null) {
				final Cookie userTypeCookie = HttpRequestUtil.getCookie(httpRequest, "userTypeToken");
				if (userTypeCookie != null) {
					userTypeToken = userTypeCookie.getValue().toString();
				} else {
					userTypeToken = (String) httpRequest.getAttribute("userTypeToken");
				}
			}
		}
		return userTypeToken;
	}
	
	public static User getUserFromUserAuthToken(final String userAuthToken) throws Exception {
		User user = null;
		final JsonObject tokenAsJSONObject = JSONUtils.getJSONObjectFromString(SecurityUtil.decrypt(new String(ApplicationUtils.generateBase64DecodedData(userAuthToken.getBytes()))));
		final JsonObject userAsJSONObject = JSONUtils.getJSONObjectFromString(JSONUtils.getValueFromJSONObject(tokenAsJSONObject, USER_OBJECT, String.class));
		if (null != userAsJSONObject) {
			user = new User();
			user.setName(JSONUtils.getValueFromJSONObject(userAsJSONObject, "name", String.class));
			user.setUserId(JSONUtils.getValueFromJSONObject(userAsJSONObject, "userId", String.class));
			user.setUserType(JSONUtils.getValueFromJSONObject(userAsJSONObject, "userType", String.class));
			final JsonArray pageAccessTypesJsonArray = JSONUtils.getValueFromJSONObject(userAsJSONObject, "pageAccessTypes", JsonArray.class);
			final List<String> pageAccessTypes = new LinkedList<String>();
			for (final Object pageAccessType : pageAccessTypesJsonArray) {
				pageAccessTypes.add(pageAccessType.toString().replaceAll("\"", "").trim());
			}
			user.setPageAccessTypes(pageAccessTypes);
			final JsonObject accessOptionsAsJSONObject = JSONUtils.getValueFromJSONObject(userAsJSONObject, "accessOptions", JsonObject.class);
			final UserAccessOptions accessOptions = new UserAccessOptions();
			accessOptions.setEmailformaccess(JSONUtils.getValueFromJSONObject(accessOptionsAsJSONObject, "emailformaccess", Boolean.class));
			accessOptions.setImpersonationaccess(JSONUtils.getValueFromJSONObject(accessOptionsAsJSONObject, "impersonationaccess", Boolean.class));
			user.setAccessOptions(accessOptions);
		}
		return user;
	}
	
	private static <T extends Object> T getUserTypeObject(final HttpServletRequest httpRequest, final Class<T> type) throws Exception {
		final User user = getUserFromSession(httpRequest);
		switch(user.getUserType()) {
			case USER_TYPE_EMPLOYEE : {
				return type.cast(getCommonsService().getEmployeeFromDbUsingUserId(user.getUserId()));
			}
			case USER_TYPE_TUTOR : {
				return type.cast(getCommonsService().getTutorFromDbUsingUserId(user.getUserId()));
			}
			case USER_TYPE_CUSTOMER : {
				return type.cast(getCommonsService().getSubscribedCustomerFromDbUsingUserId(user.getUserId()));
			}
		}
		return null;
	}
	
	public static boolean validateExistingSession(final HttpServletRequest httpRequest) {
		final User user = getUserFromSession(httpRequest);
		if (null != user && null != user.getUserId() && !EMPTY_STRING.equals(user.getUserId().trim()) && null != user.getPageAccessTypes() && !user.getPageAccessTypes().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static User getUserFromSession(final HttpServletRequest httpRequest) {
		try {
			return getUserFromUserAuthToken(getUserAuthToken(httpRequest));
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getUserTypeFromSession(final HttpServletRequest httpRequest) {
		final HttpSession session = httpRequest.getSession();
		return (String)session.getAttribute(USER_TYPE);
	}
	
	public static <T extends Object> T getUserTypeObjectFromSession(final HttpServletRequest httpRequest, Class<T> type) throws Exception {
		return getUserTypeObject(httpRequest, type);
	}
	
	public static String getEmailIdOfUserInSession(final HttpServletRequest httpRequest) throws Exception {
		final String userType = getUserTypeFromSession(httpRequest);
		switch(userType) {
			case USER_TYPE_EMPLOYEE : {
				final Employee employee = Employee.class.cast(getUserTypeObject(httpRequest, Employee.class));
				return employee.getUserId() + "@" + employee.getEmailDomain();
			}
			case USER_TYPE_TUTOR : {
				final RegisteredTutor registeredTutor = RegisteredTutor.class.cast(getUserTypeObject(httpRequest, RegisteredTutor.class));
				return registeredTutor.getEmailId();
			}
			case USER_TYPE_CUSTOMER : {
				final SubscribedCustomer subscribedCustomer = SubscribedCustomer.class.cast(getUserTypeObject(httpRequest, SubscribedCustomer.class));
				return subscribedCustomer.getEmailId();
			}
		}
		throw new ApplicationException("No Email Id in Session");
	}
	
	public static String getLoggedInUserIdAndTypeForPrinting(final HttpServletRequest request) throws Exception {
		final User user = getUserFromSession(request);
		return null != user ? (user.getUserId() + "<" + user.getUserType() + ">") : "NO_USER_IN_SESSION";
	}
	
	public static String getActiveUserIdAndTypeForPrinting(final HttpServletRequest request) throws Exception {
		final User user = getUserFromSession(request);
		return null != user ? (user.getUserId() + "<" + user.getUserType() + ">") : "NO_USER_IN_SESSION";
	}
	
	public static String getActiveUserIdAndTypeForPrintingWithExceptionHandled(final HttpServletRequest request) {
		try {
			return getActiveUserIdAndTypeForPrinting(request);
		} catch (Exception e) {
			return "[No user in session]";
		}
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
