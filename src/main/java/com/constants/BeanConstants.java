package com.constants;

public interface BeanConstants extends ApplicationConstants {
	
	// Core architecture services
	String BEAN_NAME_LOGIN_SERVICE = "loginService";
	String BEAN_NAME_MENU_SERVICE = "menuService";
	String BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE = "jndiAndControlConfigurationLoadService";
	String BEAN_NAME_QUERY_MAPPER_SERVICE = "queryMapperService";
	String BEAN_NAME_CIPHER_SERVICE = "cipherService";
	String BEAN_NAME_VELOCITY_ENGINE_SERVICE = "velocityEngineService";
	String BEAN_NAME_ADDITIONAL_ACCESS_FUNCTION_SERVICE = "additionalAccessFunctionService";
	String BEAN_NAME_SCHEDULER_SERVICE = "schedulerService";
	String BEAN_NAME_MAIl_SERVICE = "mailService";
	String BEAN_NAME_LOCK_SERVICE = "lockService";
	// Restricted functional services
	String BEAN_NAME_ADMIN_SERVICE = "adminService";
	String BEAN_NAME_COMMONS_SERVICE = "commonsService";
	// Public Access Service
	String BEAN_NAME_PUBLIC_ACCESS_SERVICE = "publicAccessService";
}
