package com.utils;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
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
import com.constants.JNDIandControlConfigurationConstants;
import com.constants.MessageConstants;
import com.model.MailSignature;
import com.model.components.commons.SelectLookup;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.components.ApplicationLookupDataService;
import com.utils.context.AppContext;
import com.utils.localization.Message;

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
    
    public static String getServerURL() throws Exception {
    	final String serverName = getJNDIandControlConfigurationLoadService().getServerName();
    	switch(serverName) {
	    	case JNDIandControlConfigurationConstants.SERVER_NAME_LOCAL : return Message.getMessage(MessageConstants.SERVER_URL_LOCAL);
	    	case JNDIandControlConfigurationConstants.SERVER_NAME_DEV : return Message.getMessage(MessageConstants.SERVER_URL_DEV);
	    	case JNDIandControlConfigurationConstants.SERVER_NAME_PROD : return Message.getMessage(MessageConstants.SERVER_URL_PROD);
	    	default: return Message.getMessage(MessageConstants.SERVER_URL_DEV);
    	}
    }
    
    public static String getBackofficeURL() throws Exception {
    	final String serverName = getJNDIandControlConfigurationLoadService().getServerName();
    	switch(serverName) {
	    	case JNDIandControlConfigurationConstants.SERVER_NAME_LOCAL : return Message.getMessage(MessageConstants.BACKOFFICE_URL_LOCAL);
	    	case JNDIandControlConfigurationConstants.SERVER_NAME_DEV : return Message.getMessage(MessageConstants.BACKOFFICE_URL_DEV);
	    	case JNDIandControlConfigurationConstants.SERVER_NAME_PROD : return Message.getMessage(MessageConstants.BACKOFFICE_URL_PROD);
	    	default: return Message.getMessage(MessageConstants.BACKOFFICE_URL_DEV);
    	}
    }
    
    public static SelectLookup getSelectLookupItem(final String selectLookUpTable, final String value) {
    	return getApplicationLookupDataService().getSelectLookupItem(selectLookUpTable, value);
    }
    
    public static String getBlankForNull(final Object object) {
    	if(ValidationUtils.checkObjectAvailability(object)) {
    		return String.valueOf(object);
    	}
    	return EMPTY_STRING;
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
    
    public static String getFormattedMailSignatureForEmails(final MailSignature mailSignature) {
    	final StringBuilder mailSignatureString = new StringBuilder(EMPTY_STRING);
    	mailSignatureString.append(mailSignature.getFrom());
		if (ValidationUtils.checkNonEmptyList(mailSignature.getMobiles())) {
			for (final String mobile : mailSignature.getMobiles()) {
				mailSignatureString.append(LINE_BREAK);
				mailSignatureString.append(mobile);
			}
		}
		if (ValidationUtils.checkNonEmptyList(mailSignature.getEmails())) {
			for (final String email : mailSignature.getEmails()) {
				mailSignatureString.append(LINE_BREAK);
				mailSignatureString.append(email);
			}
		}
		if (ValidationUtils.checkNonEmptyList(mailSignature.getWebsites())) {
			for (final String website : mailSignature.getWebsites()) {
				mailSignatureString.append(LINE_BREAK);
				mailSignatureString.append(website);
			}
		}
		return mailSignatureString.toString();
    }
    
    public static String formatRemarksAndComments(final String remarksAndComments) {
    	if (ValidationUtils.checkStringAvailability(remarksAndComments)) {
    		return remarksAndComments.trim().replaceAll(NEW_LINE, WHITESPACE + LINE_BREAK);
    	}
    	return EMPTY_STRING;
    }
    
    public static Boolean verifySameObjectWithNullCheck(final Object firstObject, final Object secondObject) {
    	final Boolean firstObjectNotNull = ValidationUtils.checkObjectAvailability(firstObject);
    	final Boolean secondObjectNotNull = ValidationUtils.checkObjectAvailability(secondObject);
    	if (XNOR(firstObjectNotNull, secondObjectNotNull)) {
    		if (!firstObjectNotNull) {
    			// Both objects NULL
    			return true;
    		}
    		final Class<?> firstObjectClass = firstObject.getClass();
    		final Class<?> secondObjectClass = secondObject.getClass();
    		if (firstObjectClass.equals(secondObjectClass)) {
    			if (firstObject.equals(secondObject)) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    public static String appendRemarksAndComments(final String currentRemarksAndComments, final String newRemarksAndComments) {
    	return formatRemarksAndComments((ValidationUtils.checkStringAvailability(currentRemarksAndComments) ? (currentRemarksAndComments.trim() + LINE_BREAK + NEW_LINE) : EMPTY_STRING) + formatRemarksAndComments(newRemarksAndComments.trim()));
    }
    
    public static Boolean XOR(final Boolean inputA, final Boolean inputB) {
    	return ((inputA && !inputB) || (!inputA && inputB));
    }
    
    public static Boolean XNOR(final Boolean inputA, final Boolean inputB) {
    	return ((!inputA && !inputB) || (inputA && inputB));
    }
    
    public static Map<String, Object> calculateRemainingHoursMinutesSecondsFromTotalAndCompletedHoursMinutesSeconds (
	    Integer totalHours,
	    Integer totalMinutes,
	    Integer totalSeconds,
	    Integer completedHours,
	    Integer completedMinutes,
	    Integer completedSeconds
	) {
	    if (!ValidationUtils.checkObjectAvailability(totalHours) || totalHours < 1) {
	      totalHours = 0;
	    }
	    if (!ValidationUtils.checkObjectAvailability(totalMinutes) || totalMinutes < 1) {
	      totalMinutes = 0;
	    }
	    if (!ValidationUtils.checkObjectAvailability(totalSeconds) || totalSeconds < 1) {
	      totalSeconds = 0;
	    }
	    if (!ValidationUtils.checkObjectAvailability(completedHours) || completedHours < 1) {
	      completedHours = 0;
	    }
	    if (!ValidationUtils.checkObjectAvailability(completedMinutes) || completedMinutes < 1) {
	      completedMinutes = 0;
	    }
	    if (!ValidationUtils.checkObjectAvailability(completedSeconds) || completedSeconds < 1) {
	      completedSeconds = 0;
	    }
	    Integer calculateTotalDurationInSeconds = (totalHours * 60 * 60) + (totalMinutes * 60) + totalSeconds;
	    Integer calculateCompletedDurationInSeconds = (completedHours * 60 * 60) + (completedMinutes * 60) + completedSeconds;
	    return calculateGapInTime(calculateTotalDurationInSeconds, calculateCompletedDurationInSeconds);
    }
    
    public static Map<String, Object> calculateIntervalHoursMinutesSecondsFromStartMillisecondsAndEndMilliseconds(
	    Long startMillis,
	    Long endMillis
	) {
	    if (!ValidationUtils.checkObjectAvailability(startMillis) || startMillis < 1) {
	      startMillis = 0L;
	    }
	    if (!ValidationUtils.checkObjectAvailability(endMillis) || endMillis < 1) {
	      endMillis = 0L;
	    }
	    Integer calculateTotalDurationInSeconds = (int) Math.ceil(endMillis / 1000);
	    Integer calculateCompletedDurationInSeconds = (int) Math.ceil(startMillis / 1000);
	    return calculateGapInTime(calculateTotalDurationInSeconds, calculateCompletedDurationInSeconds);
    }
    
    private static Map<String, Object> calculateGapInTime(Integer totalDurationInSeconds, Integer completedDurationInSeconds) {
    	final Map<String, Object> remainingTime = new HashMap<String, Object>();
    	remainingTime.put("remainingHours", 0);
    	remainingTime.put("remainingMinutes", 0);
    	remainingTime.put("remainingSeconds", 0);
    	remainingTime.put("overdueHours", 0);
    	remainingTime.put("overdueMinutes", 0);
    	remainingTime.put("overdueSeconds", 0);
    	remainingTime.put("isOverdue", (completedDurationInSeconds > totalDurationInSeconds));
    	Integer calculateGapDurationInSeconds = (Boolean)remainingTime.get("isOverdue") ? (completedDurationInSeconds - totalDurationInSeconds) : (totalDurationInSeconds - completedDurationInSeconds);
    	Integer gapInHours = (int) Math.floor(calculateGapDurationInSeconds / 3600);
    	Integer gapInMinutes = (int) Math.floor((calculateGapDurationInSeconds - (gapInHours * 3600)) / 60);
    	Integer gapInSeconds = calculateGapDurationInSeconds - ((gapInHours * 3600) + (gapInMinutes * 60));
        if ((Boolean)remainingTime.get("isOverdue")) {
	    	remainingTime.put("overdueHours", gapInHours);
	    	remainingTime.put("overdueMinutes", gapInMinutes);
	    	remainingTime.put("overdueSeconds", gapInSeconds);
        } else {
        	remainingTime.put("remainingHours", gapInHours);
        	remainingTime.put("remainingMinutes", gapInMinutes);
        	remainingTime.put("remainingSeconds", gapInSeconds);
        }
        return remainingTime;
    }
    
    public static String convertClobToString(Clob clob) throws SQLException, IOException {
		final StringBuilder stringBuilder = new StringBuilder();
		final Reader reader = clob.getCharacterStream();
		int character = -1;
		while((character = reader.read()) != -1) {
			stringBuilder.append(((char)character));
		}
		return stringBuilder.toString();
	}
    
    public static void copyAllPropertiesOfOneMapIntoAnother(final Map<String, ?> tobeCopiedOverMap, final Map<String, Object> resultantMap) {
    	if (ValidationUtils.checkObjectAvailability(resultantMap) && ValidationUtils.checkObjectAvailability(tobeCopiedOverMap)) {
    		for (Map.Entry<String, ?> entry : tobeCopiedOverMap.entrySet()) {
    			LoggerUtils.logOnConsole("KEY-VALUE Pair = [" + entry.getKey() + " - " + entry.getValue() + "]");
    			resultantMap.put(entry.getKey(), entry.getValue());
    		}
    	}
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