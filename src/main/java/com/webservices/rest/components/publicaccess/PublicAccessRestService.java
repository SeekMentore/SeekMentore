package com.webservices.rest.components.publicaccess;

import javax.mail.MessagingException;
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
import com.service.components.publicaccess.PublicAccessService;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_PUBLIC_ACCESS) 
public class PublicAccessRestService extends AbstractRestWebservice implements RestMethodConstants, PublicAccessConstants {
	
	
	@Path(REST_METHOD_NAME_APPLY_TO_BECOME_TUTOR)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String saveOrUpdateForm (
			final BecomeTutor application,
			@Context final HttpServletRequest request
	) throws Exception {
		return convertObjToJSONString(getPublicAccessService().submitApplication(application), "response");
	}
	
	@Path("testEmail")
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public void testEmail (
			@Context final HttpServletRequest request
	) throws MessagingException, Exception {
		getPublicAccessService().testEmail();
	}
	
	public PublicAccessService getPublicAccessService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_PUBLIC_ACCESS_SERVICE, PublicAccessService.class);
	}
	
	@Override
	public boolean doSecurity(final HttpServletRequest request) {
		// TODO Auto-generated method stub
		return true;
	}
}
