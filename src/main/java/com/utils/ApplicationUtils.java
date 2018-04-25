package com.utils;

import java.io.File;
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
}