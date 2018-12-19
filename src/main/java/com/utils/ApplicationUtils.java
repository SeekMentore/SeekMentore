package com.utils;

import java.io.File;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.dbcp2.BasicDataSource;

import com.constants.ApplicationConstants;
import com.constants.BeanConstants;
import com.model.User;
import com.service.JNDIandControlConfigurationLoadService;
import com.utils.context.AppContext;

public class ApplicationUtils implements ApplicationConstants {
	
	public static <T extends Object> T parseXML(final String filePath, final Class<T> type) throws JAXBException {
		return type.cast(JAXBContext.newInstance(type).createUnmarshaller().unmarshal(new File(String.valueOf(type.getClassLoader().getResource(filePath).getPath()))));
	}
	
	public static String convertStringArrayInSemiColonSeparatedString(final String[] array) {
		final StringBuilder semiColonSeparatedString =  new StringBuilder(EMPTY_STRING);
		for (final String arrayElement : array) {
			semiColonSeparatedString.append(arrayElement).append(SEMICOLON);
		}
		return semiColonSeparatedString.toString();
	}
	
	public static void appendMessageInMapAttribute(final Map<String, Object> response, final String message, final String attributeName) {
		response.put(attributeName, (String)response.get(attributeName) + LINE_BREAK + message);
	}
	
	public static User returnUserObjWithoutSensitiveInformationFromSessionUserObjectBeforeSendingOnUI(final User user) {
		final User newuserobj = user.getACopy();
		newuserobj.setEncyptedPassword(null);
		newuserobj.setPageAccessTypes(null);
		newuserobj.setUserType(null);
		return newuserobj;
	}
	
	public static User returnUserObjWithoutPasswordInformationFromSessionUserObjectBeforeSendingOnUI(final User user) {
		final User newuserobj = user.getACopy();
		newuserobj.setEncyptedPassword(null);
		return newuserobj;
	}
	
	public static String returnBlankIfStringNull(final String obj) {
		if (obj == null)
			return EMPTY_STRING;
		return obj.toString();
	}
	
	public static String setYesOrNoFromYN(final String value) {
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(value)) {
			switch (value) {
				case YES : return "Yes";
				case NO : return "No";
				default : return value;
			}
		}
		return EMPTY_STRING;
	}
	
	public static Map<String, Object> computeFileSizeInIterativeManner(final Long bytes) {
		final Map<String, Object> sizeResponse = new HashMap<String, Object>();
		sizeResponse.put("size", 0);
		sizeResponse.put("sizeExt", "Bytes");
		if (ValidationUtils.checkObjectAvailability(bytes)) {
			if (bytes > 0) {
				Double size = (double) bytes;
				String sizeExt = "Bytes";
				if (size > 0) {
					if (size > 1024) {
						size = computeFileSizeInKB(bytes);
						sizeExt = "KB";
					}
					if (size > 1024) {
						size = computeFileSizeInMB(bytes);
						sizeExt = "MB";
					}
					if (size > 1024) {
						size = computeFileSizeInGB(bytes);
						sizeExt = "GB";
					}
				}
				sizeResponse.put("size", size);
				sizeResponse.put("sizeExt", sizeExt);
			}
		}
		return sizeResponse;
	}
	
	public static Double computeFileSizeInKB(final Long bytes) {
		if (ValidationUtils.checkObjectAvailability(bytes)) {
			if (bytes > 0) {
				Double size = (double) bytes;
				size = (double) (size/1024);
				return size;
			}
		}
		return 0D;
	}
	
	public static Double computeFileSizeInMB(final Long bytes) {
		if (ValidationUtils.checkObjectAvailability(bytes)) {
			if (bytes > 0) {
				Double size = computeFileSizeInKB(bytes);
				size = (double) (size/1024);
				return size;
			}
		}
		return 0D;
	}
	
	public static Double computeFileSizeInGB(final Long bytes) {
		if (ValidationUtils.checkObjectAvailability(bytes)) {
			if (bytes > 0) {
				Double size = computeFileSizeInMB(bytes);
				size = (double) (size/1024);
				return size;
			}
		}
		return 0D;
	}
	
	public static String getStringFromCharacterArray(final Character[] array) {
		String value = null;
		if (null != array && array.length > 0) {
			value = EMPTY_STRING;
			for (Character character : array) {
				value += Character.toString((char)character);
			}
		}
		return value;
	}
	
	/**
     * System Encoder and Decoder for sending complex binary data 
     */
    public static byte[] generateBase64EncodedData(final byte[] bytes) {
    	return Base64.getEncoder().encode(bytes);
    }
    
    public static byte[] generateBase64DecodedData(final byte[] bytes) {
    	return Base64.getDecoder().decode(bytes);
    }
    
    public static String getDBInformation() {
    	switch(getBasicDataSource().getUrl()) {
	    	case "jdbc:mysql://localhost:3306/SEEK_MENTORE" : return "Local Database";    
	    	case "jdbc:mysql://seekmentore-india-mumbai-instances-dev-rds.c6n0ykmmjbpp.ap-south-1.rds.amazonaws.com:3306/SEEK_MENTORE" : return "Dev Database";  
	    	case "jdbc:mysql://seekmentore-india-mumbai-instances-prod-rds.c6n0ykmmjbpp.ap-south-1.rds.amazonaws.com:3306/SEEK_MENTORE" : return "Prod Database"; 
    	}
    	return "Unknown Database";
    }
    
    public static String getLinkedFileSystem() throws Exception {
    	switch(SecurityUtil.decrypt(getJNDIandControlConfigurationLoadService().getControlConfiguration().getAwsParams().getS3ClientParams().getBucketNameEncypted())) {
	    	case "seekmentore-dev" : return "Dev File System";    
	    	case "seekmentore-prod" : return "Prod File System";  
    	}
    	return "Unknown File System";
    }
    
    private static BasicDataSource getBasicDataSource() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_DATA_SOURCE, BasicDataSource.class);
	}
    
    private static JNDIandControlConfigurationLoadService getJNDIandControlConfigurationLoadService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
	}
}