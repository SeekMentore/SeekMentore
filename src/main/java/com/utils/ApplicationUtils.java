package com.utils;

import java.io.File;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.constants.ApplicationConstants;
import com.model.User;

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
		Double size = (double) bytes;
		String sizeExt = "Bytes";
		if (size > 0) {
			if (size > 1024) {
				size = (double) (size/1024);
				sizeExt = "KB";
			}
			if (size > 1024) {
				size = (double) (size/1024);
				sizeExt = "MB";
			}
			if (size > 1024) {
				size = (double) (size/1024);
				sizeExt = "GB";
			}
		}
		sizeResponse.put("size", size);
		sizeResponse.put("sizeExt", sizeExt);
		return sizeResponse;
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
}