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
	
	public static Boolean checkIfResponseIsStringYes(final String yesNoResponse) {
		if (checkStringAvailability(yesNoResponse)) {
			if (YES.equals(yesNoResponse)) {
				return true;
			}
		}
		return false;
	}
	
	public static Boolean checkObjectAvailability(final Object object) {
	    if (null != object) {
	      return true;
	    }
	    return false;
	}
	
	public static Boolean checkNonNegativeNumberAvailability(final Integer object) {
		if (checkObjectAvailability(object)) {
			if (object >= 0) {
				return true;
			}
		}
	    return false;
	}
	
	public static Boolean checkNonNegativeNumberAvailability(final Long object) {
		if (checkObjectAvailability(object)) {
			if (object >= 0) {
				return true;
			}
		}
	    return false;
	}
	
	public static Boolean checkNonNegativeNonZeroNumberAvailability(final Integer object) {
		if (checkObjectAvailability(object)) {
			if (object > 0) {
				return true;
			}
		}
	    return false;
	}
	
	public static Boolean checkNonNegativeNonZeroNumberAvailability(final Long object) {
		if (checkObjectAvailability(object)) {
			if (object > 0) {
				return true;
			}
		}
	    return false;
	}
	
	public static Boolean checkStringAvailability(final String stringObject) {
	    if (checkObjectAvailability(stringObject)) {
	      if (!EMPTY_STRING.equals(stringObject.trim())) {
	        return true;
	      }
	    }
	    return false;
	}
	
	public static Boolean checkNonEmptyList(final List<?> dataList) {
		if (checkObjectAvailability(dataList)) {
			return !dataList.isEmpty();
		}
		return false;
	}
	
	public static Boolean checkNonEmptyArray(final Object[] objectArray) {
		if (checkObjectAvailability(objectArray)) {
			return objectArray.length > 0;
		}
		return false;
	}
	
	public static Boolean validatePlainNotNullAndEmptyTextString(final Object text) {
		if (null != text && !EMPTY_STRING.equals(text.toString()))
			return true;
		return false;
	}
	
	public static Boolean validateNameString(final String name, Boolean spaceAllowed) {
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
	
	public static Boolean validateDate(final Date date) {
		if (null != date) {
			return true;
		}
		return false;
	}
	
	public static Boolean validatePhoneNumber(final String contactNumber, final int length) {
		if (validatePlainNotNullAndEmptyTextString(contactNumber)) {
			return Pattern.compile(REGEX_FOR_NUMBERS, Pattern.CASE_INSENSITIVE).matcher(contactNumber).find()
					? (contactNumber.length() == length)
					: false;
		}
		return false;
	}
	
	public static Boolean validateNumber (
			final Integer number, 
			final Boolean hasMaxCount,
			final int maxCount,
			final Boolean hasMinCount,
			final int minCount
	) {
		if (null == number)
			return false;
		final Boolean patternMatched = Pattern.compile(REGEX_FOR_NUMBERS, Pattern.CASE_INSENSITIVE).matcher(String.valueOf(number)).find();
		final Boolean isUnderMaxCountLimit = hasMaxCount ? number <= maxCount : true;
		final Boolean isUnderMinCountLimit = hasMinCount ? number >= minCount : true;
		return patternMatched && isUnderMaxCountLimit && isUnderMinCountLimit;
	}
	
	public static Boolean validateAgainstSelectLookupValues (
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
	
	public static Boolean validateEmailAddress(final String email) {
		if (validatePlainNotNullAndEmptyTextString(email)) {
			try {
				new InternetAddress(email).validate();
				return true;
			} catch (AddressException ex) {}
		}
		return false;
	}
	
	public static Boolean validateFileExtension(final String[] extensions, final String filename) {
		if (validatePlainNotNullAndEmptyTextString(filename)) {
			final String fileExtension = filename.substring(filename.lastIndexOf(DOT) + 1);
			if (checkStringAvailability(fileExtension)) {
				for (final String extension : extensions) {
					if (extension.trim().equalsIgnoreCase(fileExtension.trim())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static Boolean validateFileSizeInMB(final byte[] fileBytes, final Double maxSizeInMB) {
		return ApplicationUtils.computeFileSizeInMB((long)fileBytes.length) <= maxSizeInMB;
	}
	
	public static CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
}