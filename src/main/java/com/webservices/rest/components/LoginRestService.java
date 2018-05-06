package com.webservices.rest.components;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

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
import com.service.components.CommonsService;
import com.utils.ApplicationUtils;
import com.utils.LoginUtils;
import com.utils.ValidationUtils;
import com.utils.WebServiceUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_LOGIN) 
public class LoginRestService extends AbstractRestWebservice implements RestMethodConstants, LoginConstants {
	
	private Credential credential;
	
	@Path(REST_METHOD_NAME_TO_VALIDATE_CREDENTIAL)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public void validateCredential (
			@FormParam("user-id") final String userId,
			@FormParam("password") final String password,
			@FormParam("user-type") final String userType,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_VALIDATE_CREDENTIAL;
		this.credential = new Credential(userId, userType, password);
		doSecurity(request);
		if (this.securityPassed) {
			final User user = getLoginService().validateCredential(credential);
			if (null != user) {
				LoginUtils.createNewSession(request, user);
				redirectToHomePageAsPerUserType(user.getUserType(), request, response);
			} else {
				WebServiceUtils.redirectToPage("/login.html?message=INVALID_USER", request, response);
			}
		} else {
			WebServiceUtils.redirectToPage("/login.html?message=INVALID_DATA", request, response);
		}
	}
	
	private void redirectToHomePageAsPerUserType(final String userType, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		switch(userType) {
			case "Admin" : {
				WebServiceUtils.redirectToPage("/admin.html", request, response);
				break;
			}
			case "Tutor" : {
				WebServiceUtils.redirectToPage("/tutor.html", request, response);
				break;
			}
			default	: {
				WebServiceUtils.redirectToPage("/error.html", request, response);
				break;
			}
		}
	}
	
	@Path(REST_METHOD_NAME_TO_LOGOUT)
	@POST
	public void logout (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_LOGOUT;
		doSecurity(request);
		if (this.securityPassed) {
			LoginUtils.logoutUserSession(request);
			WebServiceUtils.redirectToPage("/login.html", request, response);
		}
	}
	
	public LoginService getLoginService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_LOGIN_SERVICE, LoginService.class);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	@Override
	public void doSecurity(final HttpServletRequest request) {
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_TO_LOGOUT : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_TO_VALIDATE_CREDENTIAL : {
				handleCredential();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, !this.securityPassed);
	}
	
	private void handleCredential() {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.credential.getUserId())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					LoginConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_USER_ID,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.credential.getClientSideEncypytedPassword())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					LoginConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_PASSWORD,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.credential.getUserType())) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					LoginConstants.VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_USER_TYPE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), REST_METHOD_NAME_TO_VALIDATE_CREDENTIAL, this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE) + LINE_BREAK + this.credential.toString());
			getCommonsService().feedErrorRecord(errorPacket);
		}
	} 
}
