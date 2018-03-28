package com.utils;

import java.util.Map;

import com.constants.ApplicationConstants;
import com.constants.BeanConstants;
import com.service.AdditionalAccessFunctionService;
import com.utils.context.AppContext;

public class AdditionalAccessFunctionUtils implements ApplicationConstants {
	
	public static boolean saveAccessForForm11(final String empId, final Map<String, Object> extraParams) {
		return true;
	}
	
	public static boolean saveAccessForForm2(final String empId, final Map<String, Object> extraParams) {
		return true;
	}
	
	public static boolean saveAccessForFormF(final String empId, final Map<String, Object> extraParams) {
		return true;
	}
	
	public static AdditionalAccessFunctionService getAdditionalAccessFunctionService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ADDITIONAL_ACCESS_FUNCTION_SERVICE, AdditionalAccessFunctionService.class);
	}
}