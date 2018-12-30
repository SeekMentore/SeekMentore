package com.utils;

import java.io.File;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.dbcp2.BasicDataSource;

import com.constants.ApplicationConstants;
import com.constants.BeanConstants;
import com.model.components.commons.SelectLookup;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.components.ApplicationLookupDataService;
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
    	final String dbURL = getBasicDataSource().getUrl();
    	if (dbURL.indexOf("localhost") != -1) {
    		return "Local Database";
    	}
    	if (dbURL.indexOf("dev-rds") != -1) {
    		return "Dev Database";
    	}
    	if (dbURL.indexOf("prod-rds") != -1) {
    		return "Prod Database";
    	}
    	return "Unknown Database";
    }
    
    public static String getLinkedFileSystem() throws Exception {
    	final String bucketName = SecurityUtil.decrypt(getJNDIandControlConfigurationLoadService().getControlConfiguration().getAwsParams().getS3ClientParams().getBucketNameEncypted());
    	if (bucketName.indexOf("dev") != -1) {
    		return "Dev File System";
    	}
    	if (bucketName.indexOf("prod") != -1) {
    		return "Prod File System";
    	}
    	return "Unknown File System";
    }
    
    public static SelectLookup getSelectLookupItem(final String selectLookUpTable, final String value) {
    	return getApplicationLookupDataService().getSelectLookupItem(selectLookUpTable, value);
    }
    
    public static String getSelectLookupItemLabel(final String selectLookUpTable, final String value) {
    	final SelectLookup selectLookupItem = getSelectLookupItem(selectLookUpTable, value);
    	if (ValidationUtils.checkObjectAvailability(selectLookupItem)) {
    		return selectLookupItem.getLabel();
    	}
    	return null;
    }
    
    public static List<SelectLookup> getSelectLookupItemList(final String selectLookUpTable, final String multivalue, String delimiter) {
    	if (!ValidationUtils.checkStringAvailability(delimiter)) {
    		delimiter = SEMICOLON;
    	}
    	return getApplicationLookupDataService().getSelectLookupItemList(selectLookUpTable, multivalue, delimiter);
    }
    
    public static String getSelectLookupItemListConcatenatedLabelString(final String selectLookUpTable, final String multivalue, final String delimiter, String labelSeparator) {
    	final List<String> concatenatedLabelStringList = new LinkedList<String>();
    	final List<SelectLookup> selectLookupItemList = getSelectLookupItemList(selectLookUpTable, multivalue, delimiter);
    	if (ValidationUtils.checkNonEmptyList(selectLookupItemList)) {
    		for (final SelectLookup selectLookupItem : selectLookupItemList) {
    			concatenatedLabelStringList.add(selectLookupItem.getLabel());
    		}
    		if (ValidationUtils.checkNonEmptyList(concatenatedLabelStringList)) {
    			if (!ValidationUtils.checkStringAvailability(labelSeparator)) {
    				labelSeparator = SEMICOLON + WHITESPACE;
    			}
    			return String.join(labelSeparator, concatenatedLabelStringList);
    		}
    	}
    	return null;
    }
    
    private static BasicDataSource getBasicDataSource() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_DATA_SOURCE, BasicDataSource.class);
	}
    
    private static JNDIandControlConfigurationLoadService getJNDIandControlConfigurationLoadService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
	}
    
    private static ApplicationLookupDataService getApplicationLookupDataService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_APPLICATION_LOOKUP_DATA_SERVICE, ApplicationLookupDataService.class);
	}
}