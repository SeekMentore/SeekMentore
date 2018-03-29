package com.webservices.rest.components;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.FileConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.CommonsConstants;
import com.model.mail.MailAttachment;
import com.service.components.CommonsService;
import com.service.components.Form11Service;
import com.service.components.FormFService;
import com.utils.MailUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_COMMONS) 
public class CommonsRestService extends AbstractRestWebservice implements RestMethodConstants, CommonsConstants {
	
	@Path(REST_METHOD_NAME_GET_COUNTRY_LIST)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public void getCountryList (
			@Context final HttpServletRequest request
	) throws IOException, JSONException {
		getCommonsService().testJDBCConnection();
	}
	
	@Path(REST_METHOD_NAME_GET_STATE_LIST)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
    public void getStateList (
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		final List<MailAttachment> attachments = new LinkedList<MailAttachment>();
		attachments.add(new MailAttachment(getLoggedInEmpId(request) + "Form 11" + PERIOD + FileConstants.EXTENSION_PDF, 
											AppContext.getBean(BeanConstants.BEAN_NAME_FORM11_SERVICE, Form11Service.class).downloadForm(getLoggedInEmpId(request)),
											"application/pdf"));
		attachments.add(new MailAttachment(getLoggedInEmpId(request) + "Form F" + PERIOD + FileConstants.EXTENSION_PDF, 
											AppContext.getBean(BeanConstants.BEAN_NAME_FORMF_SERVICE, FormFService.class).downloadForm(getLoggedInEmpId(request)),
											"application/pdf"));
		MailUtils.sendSimpleMailMessage(null, 
				"mukherjeeshantanu797@gmail.com;gunjack.mukherjee@gmail.com", 
				"prm.seekmentore@gmail.com",
				"partner.seekmentore@gmail.com",
				"Simple Email Subject", 
				"Simple Email Body");
		MailUtils.sendMimeMessageEmail(null, 
				"mukherjeeshantanu797@gmail.com;gunjack.mukherjee@gmail.com", 
				"prm.seekmentore@gmail.com",
				"partner.seekmentore@gmail.com",
				"Attachment Email Subject", 
				"<html><body><h1>this is html h1</h1><h3>this is html h3</h3></body></html>",
				attachments);
		final List<MailAttachment> attachments1 = new LinkedList<MailAttachment>();
		MailUtils.sendMimeMessageEmail(null, 
				"mukherjeeshantanu797@gmail.com;gunjack.mukherjee@gmail.com", 
				"prm.seekmentore@gmail.com",
				"partner.seekmentore@gmail.com",
				"Without Attachment Email Subject", 
				"<html><body><h1>Without Attachment this is html h1</h1><h3>Without Attachment this is html h3</h3></body></html>",
				attachments1);
    }
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	@Override
	public boolean doSecurity(final HttpServletRequest request) {
		// TODO Auto-generated method stub
		return true;
	}
}
