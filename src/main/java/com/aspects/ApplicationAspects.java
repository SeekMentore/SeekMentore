package com.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.constants.ApplicationAspectsConstants;
import com.utils.LoggerUtils;  

@Component
@Aspect  
public class ApplicationAspects implements ApplicationAspectsConstants {  
	
	/**
	 * START
	 * Functions to Intercept Service Calls
	 */
	
	/*
	 * All Components Service Methods
	 */
    @Pointcut(POINTCUT_EXPRESSION_TO_INTERCEPT_ALL_COMPONENTS_SERVICE_FUNCTIONS)  
    public void executingComponentsServicePublicFunctions(){} 
      
    @Around("executingComponentsServicePublicFunctions()") 
    public Object componentsServiceFunctionDebugLoggingAdvice(final ProceedingJoinPoint pjp) throws Throwable {  
		LoggerUtils.logDebugSteps("Executing < " + pjp.getSignature() + "> with arguments <" + getArgumentsAsStringArray(pjp.getArgs()) + ">");  
        final Object obj = pjp.proceed();  
    	LoggerUtils.logDebugSteps("Executed <" + pjp.getSignature() + ">");  
        return obj;    
    }  
    
    @AfterThrowing(pointcut = POINTCUT_EXPRESSION_TO_INTERCEPT_ALL_COMPONENTS_SERVICE_FUNCTIONS, throwing = THROWING_ERROR)  
	public void serviceComponentsExceptionLoggingAdvice(final JoinPoint jp, final Throwable error) {
    	LoggerUtils.logError("Exception occured while executing <" + jp.getSignature() + ">");
    	LoggerUtils.logError(error);
	}  
    
    private String getArgumentsAsStringArray(final Object[] args) {
    	final StringBuilder stringArrayReps = new StringBuilder(EMPTY_STRING);
    	stringArrayReps.append(LEFT_SQUARE_BRACKET);
    	for (int i = 0 ; i < args.length ; i++) {
    		final Object argument =  args[i];
    		stringArrayReps.append(null != argument ? argument.toString() : NULL_VALUE);
    		if (i < args.length - 1) {
    			stringArrayReps.append(COMMA);
    		}
    	}
    	stringArrayReps.append(RIGHT_SQUARE_BRACKET);
    	return stringArrayReps.toString();
    }
    
    /*
	 * All Scheduler Service Methods
	 */
    @Pointcut(POINTCUT_EXPRESSION_TO_INTERCEPT_ALL_SCHEDULER_SERVICE_FUNCTIONS)  
    public void executingSchedulerServicePublicFunctions(){} 
    
    @Around("executingSchedulerServicePublicFunctions()") 
    public Object schedulerServiceFunctionDebugLoggingAdvice(final ProceedingJoinPoint pjp) throws Throwable {  
		LoggerUtils.logDebugSteps("Executing < " + pjp.getSignature() + "> with arguments <" + getArgumentsAsStringArray(pjp.getArgs()) + ">");  
        final Object obj = pjp.proceed();  
    	LoggerUtils.logDebugSteps("Executed <" + pjp.getSignature() + ">");  
        return obj;    
    } 
    /**
     * END
	 * Functions to Intercept Service Calls
     */
}  
