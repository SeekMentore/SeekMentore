package com.utils;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.constants.BeanConstants;
import com.constants.ValidationConstants;
import com.model.components.commons.SelectLookup;
import com.service.components.CommonsService;
import com.utils.context.AppContext;

public class ValidationUtils implements ValidationConstants {
	
	public static boolean validatePlainNotNullAndEmptyTextString(final Object text) {
		if (null != text && !EMPTY_STRING.equals(text.toString()))
			return true;
		return false;
	}
	
	public static boolean validateNameString(final String name, boolean spaceAllowed) {
		if (validatePlainNotNullAndEmptyTextString(name)) {
		    return Pattern.compile(REGEX_FOR_NAME_WITH_SPACES, Pattern.CASE_INSENSITIVE).matcher(name).find() 
	    		? 
				(spaceAllowed 
						? true 
						: 
						(name.indexOf(WHITESPACE) == -1)
				) 
				: 
				false;
		}
		return false;
	}
	
	public static boolean validateDate(final Date date) {
		if (null != date) {
			return true;
		}
		return false;
	}
	
	public static boolean validatePhoneNumber(final String contactNumber, final int length) {
		if (validatePlainNotNullAndEmptyTextString(contactNumber)) {
			return Pattern.compile(REGEX_FOR_NUMBERS, Pattern.CASE_INSENSITIVE).matcher(contactNumber).find()
					? (contactNumber.length() == length)
					: false;
		}
		return false;
	}
	
	public static boolean validateNumber (
			final Integer number, 
			final boolean hasMaxCount,
			final int maxCount,
			final boolean hasMinCount,
			final int minCount
	) {
		if (null == number)
			return false;
		final boolean patternMatched = Pattern.compile(REGEX_FOR_NUMBERS, Pattern.CASE_INSENSITIVE).matcher(String.valueOf(number)).find();
		final boolean isUnderMaxCountLimit = hasMaxCount ? number <= maxCount : true;
		final boolean isUnderMinCountLimit = hasMinCount ? number >= minCount : true;
		return patternMatched && isUnderMaxCountLimit && isUnderMinCountLimit;
	}
	
	public static boolean validateAgainstSelectLookupValues (
			final String delimitedValues,
			final String delimiter,
			final String selectLookUpTable
	) {
		if (validatePlainNotNullAndEmptyTextString(delimitedValues)) {
			final List<SelectLookup> selectLookupList = getCommonsService().getSelectLookupList(selectLookUpTable);
			for (final String value : delimitedValues.split(delimiter)) {
				if (!selectLookupList.contains(new SelectLookup(value))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public static boolean validateEmailAddress(final String email) {
		if (validatePlainNotNullAndEmptyTextString(email)) {
			try {
				new InternetAddress(email).validate();
				return true;
			} catch (AddressException ex) {}
		}
		return false;
	}
	
	public static boolean validateFileExtension(final String[] extensions, final String filename) {
		if (validatePlainNotNullAndEmptyTextString(filename)) {
			final String fileExtension = filename.substring(filename.lastIndexOf(DOT) + 1);
			if (filename.indexOf(DOT) == filename.lastIndexOf(DOT)) {
				for (final String extension : extensions) {
					if (extension.equalsIgnoreCase(fileExtension)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
}