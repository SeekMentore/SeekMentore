package com.utils;

import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.MathTool;
import org.apache.velocity.tools.generic.NumberTool;

import com.constants.BeanConstants;
import com.constants.MessageConstants;
import com.constants.VelocityConstants;
import com.model.MailSignature;
import com.model.control.SupportContactDetails;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.VelocityEngineService;
import com.utils.context.AppContext;
import com.utils.localization.Message;

public class VelocityUtils implements VelocityConstants {
	
	private static void addVelocityContextProperties(final VelocityContext velocityContext) {
		velocityContext.put(VELOCITY_TOOL_DATE_TOOL, new DateTool());
		velocityContext.put(VELOCITY_TOOL_MATH_TOOL, new MathTool());
		velocityContext.put(VELOCITY_TOOL_NUMBER_TOOL, new NumberTool());
		velocityContext.put(VELOCITY_LOCALE_US, Locale.US);
		addVelocityContextForStaticUtilityClasses(velocityContext);
	}
	
	private static void addVelocityContextForStaticUtilityClasses(final VelocityContext velocityContext) {
		velocityContext.put("String", String.class);
		velocityContext.put("ApplicationUtils", ApplicationUtils.class);
		velocityContext.put("ValidationUtils", ValidationUtils.class);
		velocityContext.put("DateUtils", DateUtils.class);
	}
	
	private static void addAttributesToVelocityContext(final VelocityContext velocityContext, final Map<String,Object> attributes) throws Exception {
		attributes.put("serverURL", ApplicationUtils.getServerURL());
		attributes.put("imageServerURL", Message.getMessage(MessageConstants.IMAGE_SERVER_URL));
		if (attributes != null) {
			for (final Map.Entry<String,Object> entry : attributes.entrySet()) {
				final String key = entry.getKey();
				final Object value = entry.getValue();
				if (value == null){
					velocityContext.remove(key);
				} else{
					velocityContext.put(key, value);
				}
			}
		}
	}
	
	public static String parseTemplate(final String filePath, final Map<String, Object> attributes) throws Exception {
		final VelocityContext velocityContext = new VelocityContext();
		addVelocityContextProperties(velocityContext);
		addAttributesToVelocityContext(velocityContext, attributes);
		StringWriter writer = new StringWriter();
		getVelocityEngineService().getVelocityEngine().mergeTemplate(filePath, UTF_ENCODING, velocityContext, writer);
		final String result = writer.toString();
		writer = null;
    	return result;
	}
	
	public static String parseTemplateForEmail(final String filePath, final Map<String, Object> attributes) throws Exception {
		final SupportContactDetails supportContactDetails = getJNDIandControlConfigurationLoadService().getControlConfiguration().getCompanyContactDetails().getSupportContactDetails();
		final MailSignature mailSignature = new MailSignature(supportContactDetails.getFromText());
		mailSignature.addAllMobile(supportContactDetails.getMobile());
		mailSignature.addAllEmail(supportContactDetails.getEmail());
		mailSignature.addAllWebsite(supportContactDetails.getWebsite());
		return parseTemplateForEmail(filePath, attributes, mailSignature);
	}
	
	public static String parseTemplateForEmail(final String filePath, final Map<String, Object> attributes, final MailSignature mailSignature) throws Exception {
		attributes.put("mailSignature", ApplicationUtils.getFormattedMailSignatureForEmails(mailSignature));
		attributes.put("contentFilePath", filePath);
		return parseTemplate(VELOCITY_EMAIL_TEMPLATES_CORE_EMAIL_TEMPLATE_PATH, attributes);
	}
	
	public static VelocityEngineService getVelocityEngineService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_VELOCITY_ENGINE_SERVICE, VelocityEngineService.class);
	}
	
	public static JNDIandControlConfigurationLoadService getJNDIandControlConfigurationLoadService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
	}
}