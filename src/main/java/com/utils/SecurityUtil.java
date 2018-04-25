package com.utils;

import com.constants.BeanConstants;
import com.constants.SecurityConstants;
import com.service.CipherService;
import com.service.JNDIandControlConfigurationLoadService;
import com.utils.context.AppContext;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecurityUtil implements SecurityConstants {
	
	/**
	 * encrypt and decrypt methods to be called for data coming or going to UI
	 */
    public static String encryptClientSide(final String value) throws Exception {
    	// @TODO
        return value;
    }
    
    public static String decryptClientSide(final String value) throws Exception {
    	// @TODO
        return value;
    }
	
	/**
	 * encrypt and decrypt methods to be called from all over the project except from @Configuration annotated class
	 */
    public static String encrypt(final String value) throws Exception {
        return new BASE64Encoder().encode(getCipherService().getCipherInEncyptionMode().doFinal(value.getBytes(getJNDIandControlConfigurationLoadService().getControlConfiguration().getAppEncodingStandard())));
    }
    
    public static String decrypt(final String value) throws Exception {
        return new String(getCipherService().getCipherInDecyptionMode().doFinal(new BASE64Decoder().decodeBuffer(value)), getJNDIandControlConfigurationLoadService().getControlConfiguration().getAppEncodingStandard());
    }
    
    /**
     * encrypt and decrypt method with CipherService, JNDIandControlConfigurationLoadService
     * as parameters since this utility could be called from @Configuration annotated class which gets instantiated 
     * before the ApplicationAwareListener is activated making the AppContext.applicationContext object as NULL
     */
    public static String encrypt (
    		final String value, 
    		final CipherService cipherService,
    		final JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService
	) throws Exception {
        return new BASE64Encoder().encode(cipherService.getCipherInEncyptionMode().doFinal(value.getBytes(jndiAndControlConfigurationLoadService.getControlConfiguration().getAppEncodingStandard())));
    }
    
    public static String decrypt (
    		final String value, 
    		final CipherService cipherService,
    		final JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService
	) throws Exception {
        return new String(cipherService.getCipherInDecyptionMode().doFinal(new BASE64Decoder().decodeBuffer(value)), jndiAndControlConfigurationLoadService.getControlConfiguration().getAppEncodingStandard());
    }
    
    public static CipherService getCipherService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_CIPHER_SERVICE, CipherService.class);
	}
    
    public static JNDIandControlConfigurationLoadService getJNDIandControlConfigurationLoadService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
	}
}
