package com.webservices.rest.components;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.poi.util.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.FileConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.CommonsConstants;
import com.model.SelectModel;
import com.model.User;
import com.model.bouipath.UIMenu;
import com.model.bouipath.UISubMenu;
import com.model.mail.MailAttachment;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.MenuService;
import com.service.components.CommonsService;
import com.utils.ApplicationUtils;
import com.utils.JSONUtils;
import com.utils.MailUtils;
import com.utils.SecurityUtil;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_COMMONS) 
public class CommonsRestService extends AbstractRestWebservice implements RestMethodConstants, CommonsConstants {
	
	@Path(REST_METHOD_NAME_TO_GET_SERVER_INFO)
	@POST
	public String getServerInfo (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_GET_SERVER_INFO;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restResponse = new HashMap<String, Object>();
			restResponse.put("serverName", getJNDIandControlConfigurationLoadService().getServerName());
			restResponse.put("dbName", ApplicationUtils.getDBInformation());
			restResponse.put("fileSystemLinked", ApplicationUtils.getLinkedFileSystem());
			restResponse.put("supportEmail", SecurityUtil.decrypt(getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getEncryptedUsername()));
			restResponse.put("isEmailSendingActive", getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().isSendOutActualEmails());
			restResponse.put("divertedEmailId", getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().getDivertedRecipeintEmailId());
			restResponse.put("deployedVersion", "1");
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} 
		return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_TO_GET_ERROR_DETAILS)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String getErrorDetails (
			@FormParam("errorCode") final String errorCode,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		final Map<String, Object> restResponse = new HashMap<String, Object>();
		if("101".equals(errorCode)) {
			restResponse.put("errorImageSrc", "/images/error/101.png");
			restResponse.put("errorText", "You are not allowed to access this page.");
		} else if("102".equals(errorCode)) {
			restResponse.put("errorImageSrc", "/images/error/102.jpg");
			restResponse.put("errorText", "Page under maintnance.");
		} else if("103".equals(errorCode)) {
			restResponse.put("errorImageSrc", "/images/error/103.png");
			restResponse.put("errorText", "Your user login has been blocked. Please contact administrator.");
		} else if("104".equals(errorCode)) {
			restResponse.put("errorImageSrc", "/images/error/104.jpg");
			restResponse.put("errorText", "Site is under maintnance.");
		} else if("105".equals(errorCode)) {
			restResponse.put("errorImageSrc", "/images/error/105.jpg");
			restResponse.put("errorText", "Server error occurred while processing your request.");
		} else {
			restResponse.put("errorImageSrc", "/images/error/106.gif");
			restResponse.put("errorText", "Something went wrong!!!");
		}
		restResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
		restResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "");
		return JSONUtils.convertObjToJSONString(restResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_TO_GET_LOGIN_BASIC_INFO)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String getLoginBasicInfo (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_GET_LOGIN_BASIC_INFO;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restResponse = new HashMap<String, Object>();
			final User activeUser = getActiveUser(request);
			final List<UIMenu> uiMenuList = computeBOUIMenuForUser(activeUser); 
			restResponse.put("menu", uiMenuList);
			restResponse.put("username", activeUser.getName());
			restResponse.put("accessoptions", activeUser.getAccessOptions());
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
		return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	private List<UIMenu> computeBOUIMenuForUser(final User user) {
		final List<UIMenu> uiMenuList = new LinkedList<UIMenu>();
		for (final UIMenu uiMenu : getMenuService().getBOUIMenuArray()) {
			if (getMenuService().hasAccessToBOUIMenuItem(user.getPageAccessTypes(), uiMenu)) {
				final UIMenu uiMenuUICopy = uiMenu.getACopyForSendingToBOUI();
				Boolean hasSubMenu = false;
				for (final UISubMenu uiSubMenu : uiMenu.getSubMenuArray()) {
					if (getMenuService().hasAccessToBOUISubMenuItem(user.getPageAccessTypes(), uiSubMenu)) {
						uiMenuUICopy.addUISubMenu(uiSubMenu.getACopyForSendingToBOUI());
						hasSubMenu = true;
					}
				}
				uiMenuUICopy.setSubmenu(hasSubMenu);
				if (!hasSubMenu) {
					uiMenuUICopy.setSubMenuItems(null);
				}
				uiMenuList.add(uiMenuUICopy);
			}
		}
		return uiMenuList;
	}
	
	@Path(REST_METHOD_NAME_TO_GET_EMAIL_TEMPLATES)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String getEmailTemplates (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		List<SelectModel> emailTemplates = new LinkedList<SelectModel>(); 
		emailTemplates.add(new SelectModel("Rejection Template", "01"));
		emailTemplates.add(new SelectModel("Selection Template", "02"));
		restresponse.put("emailTemplates", emailTemplates);
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_TO_LOAD_EMAIL_TEMPLATE)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String loadEmailTemplate (
			@FormParam("templateId") final String templateId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restresponse = new HashMap<String, Object>();
		if("01".equals(templateId)) {
			restresponse.put("to", "rejectionlist@seekmentore.com");
			restresponse.put("cc", "rejectionadmin@seekmentore.com");
			restresponse.put("bcc", "founder@seekmentore.com");
			restresponse.put("subject", "This is a Rejection Email");
			restresponse.put("body", "Rejection email body<br/>This is second line.<br/><b>this line is in bold letters</b>");
		} else if("02".equals(templateId)) {
			restresponse.put("to", "selectionlist@seekmentore.com");
			restresponse.put("cc", "selectionadmin@seekmentore.com");
			restresponse.put("bcc", "founder@seekmentore.com");
			restresponse.put("subject", "This is a Selection Email");
			restresponse.put("body", "Selection email body<br/>This is second line.<br/><b>this line is in bold letters</b>");
		} else {
			restresponse.put("to", "");
			restresponse.put("cc", "");
			restresponse.put("bcc", "");
			restresponse.put("subject", "");
			restresponse.put("body", "");
		}
		restresponse.put("success", true);
		restresponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restresponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_SEND_EMAIL)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String sendEmail (
			@FormDataParam("to") final String to,
			@FormDataParam("cc") final String cc,
			@FormDataParam("bcc") final String bcc,
			@FormDataParam("subject") final String subject,
			@FormDataParam("body") final String body,
			@FormDataParam("inputFile1") final InputStream uploadedInputStreamFile1,
			@FormDataParam("inputFile1") final FormDataContentDisposition uploadedFileDetailFile1,
			@FormDataParam("inputFile2") final InputStream uploadedInputStreamFile2,
			@FormDataParam("inputFile2") final FormDataContentDisposition uploadedFileDetailFile2,
			@FormDataParam("inputFile3") final InputStream uploadedInputStreamFile3,
			@FormDataParam("inputFile3") final FormDataContentDisposition uploadedFileDetailFile3,
			@FormDataParam("inputFile4") final InputStream uploadedInputStreamFile4,
			@FormDataParam("inputFile4") final FormDataContentDisposition uploadedFileDetailFile4,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		Map<String, Object> restResponse = new HashMap<String, Object>();
		final List<MailAttachment> attachments = new ArrayList<MailAttachment>();
		if (null != uploadedInputStreamFile1) {
			byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFile1);
			if (fileBytes.length > 0) {
				attachments.add(new MailAttachment(uploadedFileDetailFile1.getFileName(), fileBytes, FileConstants.APPLICATION_TYPE_OCTET_STEAM));
			}
		}
		if (null != uploadedInputStreamFile2) {
			byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFile2);
			if (fileBytes.length > 0) {
				attachments.add(new MailAttachment(uploadedFileDetailFile2.getFileName(), fileBytes, FileConstants.APPLICATION_TYPE_OCTET_STEAM));
			}
		}
		if (null != uploadedInputStreamFile3) {
			byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFile3);
			if (fileBytes.length > 0) {
				attachments.add(new MailAttachment(uploadedFileDetailFile3.getFileName(), fileBytes, FileConstants.APPLICATION_TYPE_OCTET_STEAM));
			}
		}
		if (null != uploadedInputStreamFile4) {
			byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFile4);
			if (fileBytes.length > 0) {
				attachments.add(new MailAttachment(uploadedFileDetailFile4.getFileName(), fileBytes, FileConstants.APPLICATION_TYPE_OCTET_STEAM));
			}
		}
		MailUtils.sendMimeMessageEmail( 
				to, 
				cc,
				bcc,
				subject, 
				body,
				attachments.isEmpty() ? null : attachments);
		restResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
		restResponse.put("message", "Sent Email Successfully");
		return JSONUtils.convertObjToJSONString(restResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	public JNDIandControlConfigurationLoadService getJNDIandControlConfigurationLoadService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
	}
	
	public MenuService getMenuService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_MENU_SERVICE, MenuService.class);
	}
	
	@Override
	public void doSecurity(final HttpServletRequest request) {
		this.request = request;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_TO_GET_SERVER_INFO :
			case REST_METHOD_NAME_TO_GET_ERROR_DETAILS : 
			case REST_METHOD_NAME_TO_GET_LOGIN_BASIC_INFO : {
				this.securityPassed = true;
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
}
