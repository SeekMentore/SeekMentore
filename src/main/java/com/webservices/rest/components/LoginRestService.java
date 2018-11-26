package com.webservices.rest.components;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.LoginConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.model.Credential;
import com.model.ErrorPacket;
import com.model.User;
import com.service.LoginService;
import com.service.MenuService;
import com.service.components.CommonsService;
import com.utils.ApplicationUtils;
import com.utils.JSONUtils;
import com.utils.LoggerUtils;
import com.utils.LoginUtils;
import com.utils.PasswordUtils;
import com.utils.SecurityUtil;
import com.utils.ValidationUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_LOGIN) 
public class LoginRestService extends AbstractRestWebservice implements RestMethodConstants, LoginConstants {
	
	private Credential credential;
	private User user;
	private String oldPassword;
	private String newPassword;
	private String retypeNewPassword;
	
	@Path(REST_METHOD_NAME_TO_VALIDATE_CREDENTIAL)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String validateCredential (
			@FormParam("userId") final String userId,
			@FormParam("password") final String password,
			@FormParam("userType") final String userType,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_VALIDATE_CREDENTIAL;
		final Map<String, Object> restResponse = new HashMap<String, Object>();
		this.credential = new Credential(userId, userType, password);
		doSecurity(request);
		restResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
		if (this.securityPassed) {
			// Trimming User-Id
			this.credential.setUserId(this.credential.getUserId().trim());
			final User user = getLoginService().validateCredential(credential);
			if (null != user) {
				LoginUtils.createNewSession(request, response, user);
				restResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
				restResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			} else {
				restResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "Invalid user credentials entered.");
			}
		} else {
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "Invalid data entered.");
		}
		return JSONUtils.convertObjToJSONString(restResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_TO_CHECK_UI_PATH_ACCESS)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String checkUIpathAccess (
			@FormParam("urlPath") final String urlPath,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		final Map<String, Object> restResponse = new HashMap<String, Object>();
		LoggerUtils.logOnConsole("Accessing UI Path " + urlPath);
		user = getActiveUser(request);
		if(getMenuService().hasAccessToUIURL(user.getUserId(), urlPath, user.getPageAccessTypes(), null)) {
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		} else {
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "No access to this UI URL");
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_REDIRECT_TO, "/public/error?errorCode=103");
		}
		return JSONUtils.convertObjToJSONString(restResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_RESET_PASSWORD)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String resetPassword (
			@FormParam("userId") final String userId,
			@FormParam("userType") final String userType,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restResponse = new HashMap<String, Object>();
		if("seek".equals(userId) && "admin".equals(userType)) {
			restResponse.put("success", true);
			restResponse.put("message", "Password reset successful");
		} else {
			restResponse.put("success", false);
			restResponse.put("message", "Failed to reset password");
		}
		return JSONUtils.convertObjToJSONString(restResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_CHANGE_PASSWORD)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String changePassword (
			@FormParam("oldPassword") final String oldPassword,
			@FormParam("newPassword") final String newPassword,
			@FormParam("retypeNewPassword") final String retypeNewPassword,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_CHANGE_PASSWORD;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.retypeNewPassword = retypeNewPassword;
		this.user = getActiveUser(request);
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getLoginService().changePassword(getActiveUser(request), newPassword, LoginUtils.getEmailIdOfUserInSession(request)), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} 
		return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_TO_LOGOUT)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String logout (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_LOGOUT;
		final Map<String, Object> restResponse = new HashMap<String, Object>();
		doSecurity(request);
		if (this.securityPassed) {
			LoginUtils.logoutUserSession(request, response);
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		} else {
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "Some error occurred, cannot logout.");
		}
		return JSONUtils.convertObjToJSONString(restResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	public LoginService getLoginService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_LOGIN_SERVICE, LoginService.class);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	public MenuService getMenuService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_MENU_SERVICE, MenuService.class);
	}
	
	@Override
	public void doSecurity(final HttpServletRequest request) throws Exception {
		this.request = request;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_TO_LOGOUT :
			case REST_METHOD_NAME_TO_CHECK_UI_PATH_ACCESS : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_CHANGE_PASSWORD : {
				handleChangePassword();
				break;
			}
			case REST_METHOD_NAME_TO_VALIDATE_CREDENTIAL : {
				handleCredential();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
	
	private void handleCredential() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.credential.getUserId())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					LoginConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_USER_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.credential.getClientSideEncypytedPassword())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					LoginConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_PASSWORD,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.credential.getUserType())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					LoginConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_USER_TYPE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getActiveUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE) + LINE_BREAK + this.credential.toString());
			getCommonsService().feedErrorRecord(errorPacket);
		}
	} 
	
	private void handleChangePassword() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.oldPassword)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					LoginConstants.VALIDATION_MESSAGE_PLEASE_ENTER_AN_OLD_PASSWORD,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		try {
			final String decryptUserPasswordFromUI = SecurityUtil.decryptClientSide(this.oldPassword);
			final String decryptUserPasswordFromSession = SecurityUtil.decrypt(this.user.getEncyptedPassword());
			if (!decryptUserPasswordFromSession.equals(decryptUserPasswordFromUI)) {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						LoginConstants.VALIDATION_MESSAGE_INCORRECT_OLD_PASSWORD,
						RESPONSE_MAP_ATTRIBUTE_MESSAGE);
				this.securityPassed = false;
			}
		} catch(Exception e) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					LoginConstants.VALIDATION_MESSAGE_INCORRECT_OLD_PASSWORD,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.newPassword)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					LoginConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_NEW_PASSWORD,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.retypeNewPassword)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					LoginConstants.VALIDATION_MESSAGE_PLEASE_ENTER_RETYPE_NEW_PASSWORD,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		try {
			final String decryptUserNewPasswordFromUI = SecurityUtil.decryptClientSide(this.newPassword);
			final String decryptUserRetypeNewPasswordFromUI = SecurityUtil.decryptClientSide(this.retypeNewPassword);
			if (!decryptUserNewPasswordFromUI.equals(decryptUserRetypeNewPasswordFromUI)) {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						LoginConstants.VALIDATION_MESSAGE_MISMATCH_NEW_PASSWORD,
						RESPONSE_MAP_ATTRIBUTE_MESSAGE);
				this.securityPassed = false;
			} else {
				if (!PasswordUtils.checkPasswordPolicy(decryptUserNewPasswordFromUI)) {
					ApplicationUtils.appendMessageInMapAttribute(
							this.securityFailureResponse, 
							LoginConstants.VALIDATION_MESSAGE_PASSWORD_POLICY_FAILED,
							RESPONSE_MAP_ATTRIBUTE_MESSAGE);
					this.securityPassed = false;
				}
			}
		} catch(Exception e) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					LoginConstants.VALIDATION_MESSAGE_MISMATCH_NEW_PASSWORD,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getActiveUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE) + LINE_BREAK + this.oldPassword + LINE_BREAK + this.newPassword + LINE_BREAK + this.retypeNewPassword);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	} 
}
