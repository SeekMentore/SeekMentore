package com.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.constants.ApplicationConstants;

public class ApplicationUtils implements ApplicationConstants {
	
	public static <T extends Object> T parseXML(final String filePath, final Class<T> type) throws JAXBException {
		return type.cast(JAXBContext.newInstance(type).createUnmarshaller().unmarshal(new File(String.valueOf(type.getClassLoader().getResource(filePath).getPath()))));
	}
}