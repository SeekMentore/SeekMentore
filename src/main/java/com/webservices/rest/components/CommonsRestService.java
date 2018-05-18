package com.webservices.rest.components;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.CommonsConstants;
import com.model.User;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.components.CommonsService;
import com.utils.ApplicationUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_COMMONS) 
public class CommonsRestService extends AbstractRestWebservice implements RestMethodConstants, CommonsConstants {
	
	@Path(REST_METHOD_NAME_TO_GET_USER)
	@POST
	public String getUser (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_GET_USER;
		doSecurity(request);
		if (this.securityPassed) {
			final User user = ApplicationUtils.returnUserObjWithoutSensitiveInformationFromSessionUserObjectBeforeSendingOnUI(getLoggedInUser(request));
			return convertObjToJSONString(user, REST_MESSAGE_JSON_RESPONSE_NAME);
		} 
		return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_TO_GET_SERVER_INFO)
	@POST
	public String getServerInfo (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_GET_SERVER_INFO;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getJNDIandControlConfigurationLoadService().getServerName(), REST_MESSAGE_JSON_RESPONSE_NAME);
		} 
		return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	 public static JNDIandControlConfigurationLoadService getJNDIandControlConfigurationLoadService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
	}
	
	@Override
	public void doSecurity(final HttpServletRequest request) {
		this.request = request;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_TO_GET_USER : 
			case REST_METHOD_NAME_TO_GET_SERVER_INFO : {
				this.securityPassed = true;
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, !this.securityPassed);
	}
}
