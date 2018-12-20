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
import com.constants.ErrorConstants;
import com.constants.FileConstants;
import com.constants.ImageServerPathConstants;
import com.constants.MailConstants;
import com.constants.MessageConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.CommonsConstants;
import com.constants.components.SelectLookupConstants;
import com.model.User;
import com.model.bouipath.UIMenu;
import com.model.bouipath.UISubMenu;
import com.model.components.commons.SelectLookup;
import com.model.mail.EmailTemplate;
import com.model.mail.MailAttachment;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.MenuService;
import com.service.components.CommonsService;
import com.utils.ApplicationUtils;
import com.utils.JSONUtils;
import com.utils.MailUtils;
import com.utils.SecurityUtil;
import com.utils.ValidationUtils;
import com.utils.context.AppContext;
import com.utils.localization.Message;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_COMMONS) 
public class CommonsRestService extends AbstractRestWebservice implements RestMethodConstants, CommonsConstants {
	
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private List<MailAttachment> attachments;
	
	@Path(REST_METHOD_NAME_TO_GET_SERVER_INFO)
	@POST
	public String getServerInfo (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_GET_SERVER_INFO;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restResponse = new HashMap<String, Object>();
			restResponse.put(SERVER_NAME, getJNDIandControlConfigurationLoadService().getServerName());
			restResponse.put(DB_NAME, ApplicationUtils.getDBInformation());
			restResponse.put(FILE_SYSTEM_LINKED, ApplicationUtils.getLinkedFileSystem());
			restResponse.put(SUPPORT_EMAIL, SecurityUtil.decrypt(getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getEncryptedUsername()));
			restResponse.put(IS_EMAIL_SENDING_ACTIVE, getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().isSendOutActualEmails());
			restResponse.put(DIVERTED_EMAIL_ID, getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().getDivertedRecipeintEmailId());
			restResponse.put(LAST_DEPLOYED_VERSION_AND_DATE, Message.getMessage(MessageConstants.LAST_STABLE_PROD_VERSION_AND_DATE));
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
			@FormParam(PARAM_ERROR_CODE) final String errorCode,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_GET_ERROR_DETAILS;
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restResponse = new HashMap<String, Object>();
			switch(errorCode) {
				case ErrorConstants.ERROR_CODE_101 : {
					restResponse.put(ErrorConstants.ERROR_IMAGE_SRC, Message.getMessageFromFile(ImageServerPathConstants.MESG_PROPERTY_FILE_NAME, ImageServerPathConstants.ERROR_CODE_101));
					restResponse.put(ErrorConstants.ERROR_TEXT, Message.getMessageFromFile(ErrorConstants.MESG_PROPERTY_FILE_NAME, ErrorConstants.MESG_PROPERTY_ERROR_ACCESS_DENIED));
					break;
				}
				case ErrorConstants.ERROR_CODE_102 : {
					restResponse.put(ErrorConstants.ERROR_IMAGE_SRC, Message.getMessageFromFile(ImageServerPathConstants.MESG_PROPERTY_FILE_NAME, ImageServerPathConstants.ERROR_CODE_102));
					restResponse.put(ErrorConstants.ERROR_TEXT, Message.getMessageFromFile(ErrorConstants.MESG_PROPERTY_FILE_NAME, ErrorConstants.MESG_PROPERTY_ERROR_PAGE_UNDER_MAINTENANCE));
					break;
				}
				case ErrorConstants.ERROR_CODE_103 : {
					restResponse.put(ErrorConstants.ERROR_IMAGE_SRC, Message.getMessageFromFile(ImageServerPathConstants.MESG_PROPERTY_FILE_NAME, ImageServerPathConstants.ERROR_CODE_103));
					restResponse.put(ErrorConstants.ERROR_TEXT, Message.getMessageFromFile(ErrorConstants.MESG_PROPERTY_FILE_NAME, ErrorConstants.MESG_PROPERTY_ERROR_LOGIN_BLOCKED));
					break;
				}
				case ErrorConstants.ERROR_CODE_104 : {
					restResponse.put(ErrorConstants.ERROR_IMAGE_SRC, Message.getMessageFromFile(ImageServerPathConstants.MESG_PROPERTY_FILE_NAME, ImageServerPathConstants.ERROR_CODE_104));
					restResponse.put(ErrorConstants.ERROR_TEXT, Message.getMessageFromFile(ErrorConstants.MESG_PROPERTY_FILE_NAME, ErrorConstants.MESG_PROPERTY_ERROR_SITE_UNDER_MAINTENANCE));
					break;
				}
				case ErrorConstants.ERROR_CODE_105 : {
					restResponse.put(ErrorConstants.ERROR_IMAGE_SRC, Message.getMessageFromFile(ImageServerPathConstants.MESG_PROPERTY_FILE_NAME, ImageServerPathConstants.ERROR_CODE_105));
					restResponse.put(ErrorConstants.ERROR_TEXT, Message.getMessageFromFile(ErrorConstants.MESG_PROPERTY_FILE_NAME, ErrorConstants.MESG_PROPERTY_ERROR_SERVER_ERROR));
					break;
				}
				default : {
					restResponse.put(ErrorConstants.ERROR_IMAGE_SRC, Message.getMessageFromFile(ImageServerPathConstants.MESG_PROPERTY_FILE_NAME, ImageServerPathConstants.ERROR_UNKNOWN));
					restResponse.put(ErrorConstants.ERROR_TEXT, Message.getMessageFromFile(ErrorConstants.MESG_PROPERTY_FILE_NAME, ErrorConstants.MESG_PROPERTY_ERROR_UNKNOWN));
					break;
				}
			}
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
		return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_TO_GET_LOGIN_BASIC_INFO)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String getLoginBasicInfo (
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_GET_LOGIN_BASIC_INFO;
		this.activeUser = getActiveUser(request);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restResponse = new HashMap<String, Object>();
			final List<UIMenu> uiMenuList = computeBOUIMenuForUser(this.activeUser); 
			restResponse.put(MENU, uiMenuList);
			restResponse.put(USERNAME, activeUser.getName());
			restResponse.put(ACCESSOPTIONS, activeUser.getAccessOptions());
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
		this.methodName = REST_METHOD_NAME_TO_GET_EMAIL_TEMPLATES;
		this.activeUser = getActiveUser(request);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restResponse = new HashMap<String, Object>();
			restResponse.put(EMAIL_TEMPLATES, getCommonsService().getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_EMAIL_TEMPLATE_LOOKUP));
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
		return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_TO_LOAD_EMAIL_TEMPLATE)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String loadEmailTemplate (
			@FormParam(PARAM_TEMPLATE_ID) final String templateId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_LOAD_EMAIL_TEMPLATE;
		this.activeUser = getActiveUser(request);
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restResponse = new HashMap<String, Object>();
			restResponse.put(EMAIL_TEMPLATE, EmailTemplate.getBlankEmailTemplate());
			final SelectLookup emailTemplateSelectLookup = getCommonsService().getSelectLookupItem(SelectLookupConstants.SELECT_LOOKUP_TABLE_EMAIL_TEMPLATE_LOOKUP, templateId);
			if (ValidationUtils.checkObjectAvailability(emailTemplateSelectLookup)) {
				final EmailTemplate emailTemplate = getCommonsService().getEmailTemplateFromLookupValue(templateId);
				if (ValidationUtils.checkObjectAvailability(emailTemplate)) {
					restResponse.put(EMAIL_TEMPLATE, emailTemplate);
				}
			}
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
			return JSONUtils.convertObjToJSONString(restResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
		return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_SEND_EMAIL)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public String sendEmail (
			@FormDataParam(MailConstants.MAIL_PARAM_TO) final String to,
			@FormDataParam(MailConstants.MAIL_PARAM_CC) final String cc,
			@FormDataParam(MailConstants.MAIL_PARAM_BCC) final String bcc,
			@FormDataParam(MailConstants.MAIL_PARAM_SUBJECT) final String subject,
			@FormDataParam(MailConstants.MAIL_PARAM_MESSAGE) final String body,
			@FormDataParam(INPUT_FILE_1) final InputStream uploadedInputStreamFile1,
			@FormDataParam(INPUT_FILE_1) final FormDataContentDisposition uploadedFileDetailFile1,
			@FormDataParam(INPUT_FILE_2) final InputStream uploadedInputStreamFile2,
			@FormDataParam(INPUT_FILE_2) final FormDataContentDisposition uploadedFileDetailFile2,
			@FormDataParam(INPUT_FILE_3) final InputStream uploadedInputStreamFile3,
			@FormDataParam(INPUT_FILE_3) final FormDataContentDisposition uploadedFileDetailFile3,
			@FormDataParam(INPUT_FILE_4) final InputStream uploadedInputStreamFile4,
			@FormDataParam(INPUT_FILE_4) final FormDataContentDisposition uploadedFileDetailFile4,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_SEND_EMAIL;
		this.attachments = new ArrayList<MailAttachment>();
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		if (null != uploadedInputStreamFile1) {
			byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFile1);
			if (fileBytes.length > 0) {
				this.attachments.add(new MailAttachment(uploadedFileDetailFile1.getFileName(), fileBytes, FileConstants.APPLICATION_TYPE_OCTET_STEAM));
			}
		}
		if (null != uploadedInputStreamFile2) {
			byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFile2);
			if (fileBytes.length > 0) {
				this.attachments.add(new MailAttachment(uploadedFileDetailFile2.getFileName(), fileBytes, FileConstants.APPLICATION_TYPE_OCTET_STEAM));
			}
		}
		if (null != uploadedInputStreamFile3) {
			byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFile3);
			if (fileBytes.length > 0) {
				this.attachments.add(new MailAttachment(uploadedFileDetailFile3.getFileName(), fileBytes, FileConstants.APPLICATION_TYPE_OCTET_STEAM));
			}
		}
		if (null != uploadedInputStreamFile4) {
			byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFile4);
			if (fileBytes.length > 0) {
				this.attachments.add(new MailAttachment(uploadedFileDetailFile4.getFileName(), fileBytes, FileConstants.APPLICATION_TYPE_OCTET_STEAM));
			}
		}
		doSecurity(request);
		if (this.securityPassed) {
			final Map<String, Object> restResponse = new HashMap<String, Object>();
			MailUtils.sendMimeMessageEmail( 
					to, 
					cc,
					bcc,
					subject, 
					body,
					attachments.isEmpty() ? null : attachments);
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
			restResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.EMAIL_SEND_SUCCESS));
			return JSONUtils.convertObjToJSONString(restResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
		return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
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
	public void doSecurity(final HttpServletRequest request) throws Exception {
		this.request = request;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_TO_GET_SERVER_INFO :
			case REST_METHOD_NAME_TO_GET_ERROR_DETAILS : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_TO_GET_LOGIN_BASIC_INFO : 
			case REST_METHOD_NAME_TO_GET_EMAIL_TEMPLATES : 
			case REST_METHOD_NAME_TO_LOAD_EMAIL_TEMPLATE : {
				handleActiveUserSecurity();
				break;
			}
			case REST_METHOD_NAME_SEND_EMAIL : {
				handleSendEmailSecurity();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
	
	private void handleSendEmailSecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.validateEmailAddress(this.to)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.INVALID_TO_EMAIL_ADDRESS),
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		} 
		if (ValidationUtils.checkStringAvailability(this.cc)) {
			if (!ValidationUtils.validateEmailAddress(this.cc)) {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.INVALID_CC_EMAIL_ADDRESS),
						RESPONSE_MAP_ATTRIBUTE_MESSAGE);
				this.securityPassed = false;
			} 
		}
		if (ValidationUtils.checkStringAvailability(this.bcc)) {
			if (!ValidationUtils.validateEmailAddress(this.bcc)) {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.INVALID_BCC_EMAIL_ADDRESS),
						RESPONSE_MAP_ATTRIBUTE_MESSAGE);
				this.securityPassed = false;
			} 
		}
		if (!ValidationUtils.checkStringAvailability(this.subject)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.INVALID_SUBJECT),
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (ValidationUtils.checkNonEmptyList(this.attachments)) {
			for (final MailAttachment attachment : this.attachments) {
				if (!ValidationUtils.validateFileExtension(ACCEPTABLE_FILE_EXTENSIONS_ARRAY, attachment.getFilename())) {
					ApplicationUtils.appendMessageInMapAttribute(
							this.securityFailureResponse, 
							Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.INVALID_EXTENSION),
							RESPONSE_MAP_ATTRIBUTE_MESSAGE);
					this.securityPassed = false;
				}
				if (!ValidationUtils.validateFileSizeInMB(attachment.getContent(), MAXIMUM_FILE_SIZE_FOR_EMAIL_DOCUMENTS_IN_MB)) {
					ApplicationUtils.appendMessageInMapAttribute(
							this.securityFailureResponse, 
							Message.getMessageFromFile(CommonsConstants.MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, CommonsConstants.INVALID_SIZE),
							RESPONSE_MAP_ATTRIBUTE_MESSAGE);
					this.securityPassed = false;
				}
			}
		}
	}
}
