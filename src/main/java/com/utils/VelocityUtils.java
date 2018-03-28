package com.utils;

import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.MathTool;
import org.apache.velocity.tools.generic.NumberTool;

import com.constants.BeanConstants;
import com.constants.VelocityConstants;
import com.service.VelocityEngineService;
import com.utils.context.AppContext;

public class VelocityUtils implements VelocityConstants {
	
	private static void addVelocityContextProperties(final VelocityContext velocityContext) {
		velocityContext.put(VELOCITY_TOOL_DATE_TOOL, new DateTool());
		velocityContext.put(VELOCITY_TOOL_MATH_TOOL, new MathTool());
		velocityContext.put(VELOCITY_TOOL_NUMBER_TOOL, new NumberTool());
		velocityContext.put(VELOCITY_LOCALE_US, Locale.US);
	}

	private static void addAttributesToVelocityContext(final VelocityContext velocityContext, final Map<String,Object> attributes) {
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
	
	public static String parseTemplate(final String filePath, final Map<String, Object> attributes) throws JAXBException, URISyntaxException {
		final VelocityContext velocityContext = new VelocityContext();
		addVelocityContextProperties(velocityContext);
		addAttributesToVelocityContext(velocityContext, attributes);
		StringWriter writer = new StringWriter();
		getVelocityEngineService().getVelocityEngine().mergeTemplate(filePath, UTF_ENCODING, velocityContext, writer);
		final String result = writer.toString();
		writer = null;
    	return result;
	}
	
	public static VelocityEngineService getVelocityEngineService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_VELOCITY_ENGINE_SERVICE, VelocityEngineService.class);
	}
}