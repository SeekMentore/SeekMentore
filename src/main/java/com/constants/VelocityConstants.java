package com.constants;

public interface VelocityConstants extends ApplicationConstants {
	
	String VELOCITY_TOOL_DATE_TOOL = "DateTool";
	String VELOCITY_TOOL_MATH_TOOL = "MathTool";
	String VELOCITY_TOOL_NUMBER_TOOL = "NumberTool";
	String VELOCITY_LOCALE_US = "LocaleUS";
	
	
	String VELOCITY_PROPERTIES_FILE = "velocity/velocity.properties";
	String UTF_ENCODING = "ISO-8859-1";
	
	String VELOCITY_TEMPLATES_FOLDER_PATH = "/velocity/templates";
	
	String VELOCITY_EMAIL_TEMPLATES_FOLDER_PATH = VELOCITY_TEMPLATES_FOLDER_PATH + "/email";
	String VELOCITY_EMAIL_TEMPLATES_CORE_EMAIL_TEMPLATE_PATH = VELOCITY_EMAIL_TEMPLATES_FOLDER_PATH + "/core-mail-template.vm";
	
	String VELOCITY_PDF_TEMPLATES_FOLDER_PATH = VELOCITY_TEMPLATES_FOLDER_PATH + "/pdf";
	
}
