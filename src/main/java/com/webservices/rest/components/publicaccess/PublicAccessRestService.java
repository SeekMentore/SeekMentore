package com.webservices.rest.components.publicaccess;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.publicaccess.PublicAccessConstants;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.components.publicaccess.PublicApplication;
import com.model.components.publicaccess.SubmitQuery;
import com.service.components.publicaccess.PublicAccessService;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_PUBLIC_ACCESS) 
public class PublicAccessRestService extends AbstractRestWebservice implements RestMethodConstants, PublicAccessConstants {
	
	// Since the Class is Prototype scope hence introducing a class level variable 
	// Do not do this in Service classes as they are singleton
	private PublicApplication application;
	private Map<String, Object> securityFailureResponse;
	private String methodName;
	private boolean securityPassed = false;
	
	@Path(REST_METHOD_NAME_TO_BECOME_TUTOR)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String becomeTutor (
			final BecomeTutor application,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_BECOME_TUTOR;
		this.application = application;
		if (doSecurity(request)) {
			return convertObjToJSONString(getPublicAccessService().submitApplication(application), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_FIND_TUTOR)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String findTutor (
			final FindTutor application,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_FIND_TUTOR;
		this.application = application;
		if (doSecurity(request)) {
			return convertObjToJSONString(getPublicAccessService().submitApplication(application), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_SUBMIT_QUERY)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String submitQuery (
			final SubmitQuery application,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_SUBMIT_QUERY;
		this.application = application;
		if (doSecurity(request)) {
			return convertObjToJSONString(getPublicAccessService().submitApplication(application), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_SUBSCRIBE)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String subscribe (
			final FindTutor application,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_SUBSCRIBE;
		this.application = application;
		if (doSecurity(request)) {
			return convertObjToJSONString(getPublicAccessService().submitApplication(application), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	public PublicAccessService getPublicAccessService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_PUBLIC_ACCESS_SERVICE, PublicAccessService.class);
	}
	
	@Override
	public boolean doSecurity (final HttpServletRequest request) {
		this.securityFailureResponse = new HashMap<String, Object>();
		final String captchaResponse = this.application.getCaptchaResponse();
		if (verifyCaptcha(captchaResponse)) {
			switch(this.methodName) {
				case REST_METHOD_NAME_TO_BECOME_TUTOR : {
					// Method level security
					this.securityPassed = true;
					break;
				}
				case REST_METHOD_NAME_TO_FIND_TUTOR : {
					// Method level security
					this.securityPassed = true;
					break;
				}
				case REST_METHOD_NAME_TO_SUBMIT_QUERY : {
					// Method level security
					this.securityPassed = true;
					break;
				}
				case REST_METHOD_NAME_TO_SUBSCRIBE : {
					// Method level security
					this.securityPassed = true;
					break;
				}
			}
		}
		return this.securityPassed;
	}
	
	private boolean verifyCaptcha(final String captchaResponse) {
		// Verify captcha response and set values in securityFailureResponse
		return true;
	}
}
