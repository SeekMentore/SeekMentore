package com.utils;

import com.constants.ApplicationConstants;
import com.constants.BeanConstants;
import com.service.AdditionalAccessFunctionService;
import com.utils.context.AppContext;

public class AdditionalAccessFunctionUtils implements ApplicationConstants {
	
	public static AdditionalAccessFunctionService getAdditionalAccessFunctionService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ADDITIONAL_ACCESS_FUNCTION_SERVICE, AdditionalAccessFunctionService.class);
	}
}