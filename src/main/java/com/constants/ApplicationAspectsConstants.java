package com.constants;

public interface ApplicationAspectsConstants extends ApplicationConstants {
	
	String POINTCUT_EXPRESSION_TO_INTERCEPT_ALL_COMPONENTS_SERVICE_FUNCTIONS = "execution(public * com.service.components.*.*(*))";
	String POINTCUT_EXPRESSION_TO_INTERCEPT_ALL_SCHEDULER_SERVICE_FUNCTIONS = "execution(public * com.service.SchedulerService.*(*))";
	
	String THROWING_ERROR = "error";
}
