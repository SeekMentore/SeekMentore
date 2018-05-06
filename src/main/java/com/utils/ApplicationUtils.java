package com.utils;

import java.io.File;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.constants.ApplicationConstants;
import com.exception.ApplicationException;
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
	
	public static Character[] generateRandomPassword (
			final Character[] restrictedCharacters,
			final Integer minLength,
			final Integer maxLength,
			final Boolean needCapitalLetters,
			final Boolean needSmallLetters,
			final Boolean needSpecialCharacters,
			final Boolean needNumbers,
			final Boolean canStartWithNumbers,
			final Boolean canEndInNumbers,
			final Boolean canStartWithSpecialCharacters,
			final Boolean canEndWithSpecialCharacters,
			final Boolean isFixedLength
	) {
		if (minLength < 0) 
			throw new ApplicationException("Minimum Length cannot be less than Zero.");
		if (null == maxLength || maxLength < 0) 
			throw new ApplicationException("Invalid maximum length.");
		if (minLength > maxLength) 
			throw new ApplicationException("Minimum length cannot be greater than maximum length.");
		if (!needCapitalLetters && !needSmallLetters && !needSpecialCharacters && !needNumbers) 
			throw new ApplicationException("All characters on keyboard are omitted. Cannot create password without keys.");
		if (!needCapitalLetters && !needSmallLetters) {
			if (!needSpecialCharacters) {
				if (!canStartWithNumbers || !canEndInNumbers) {
					throw new ApplicationException("Only numbers are allowed hence password has to start and end in Numbers.");
				}
			}
			if (!needNumbers) {
				if (!canStartWithSpecialCharacters || !canEndWithSpecialCharacters) {
					throw new ApplicationException("Only Special Characters are allowed hence password has to start and end in Special Characters.");
				}
			}
		}
        final String capitalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String smallChars = "abcdefghijklmnopqrstuvwxyz";
        final String specialCharacters = "!@#$%^&*_=+-/.?<>)";
        final String numbers = "0123456789";
 
        String completePasswordString = EMPTY_STRING;
        if (needCapitalLetters) {
        	completePasswordString += capitalChars;
        }
        if (needSmallLetters) {
        	completePasswordString += smallChars;
        }
        if (needSpecialCharacters) {
        	completePasswordString += specialCharacters;
        }
        if (needNumbers) {
        	completePasswordString += numbers;
        }
        // Using random method
        final Random randomFigureGenerator = new Random();
        int breakLength = randomFigureGenerator.nextInt(completePasswordString.length());
        final List<Character> password = new LinkedList<Character>();
        int i = 0;
        int passwordLength = 0;
        passwordLength = maxLength;
        if (!isFixedLength) {
        	if (minLength + breakLength < maxLength) {
        		passwordLength = minLength + breakLength;
        	}
    	}
        do {
        	Boolean addThisCharacter = true;
        	int randomLocationIdentifier = randomFigureGenerator.nextInt(completePasswordString.length());
        	if (i == 0) {
        		if (numbers.indexOf(completePasswordString.charAt(randomLocationIdentifier)) != -1) {
        			if (!canStartWithNumbers) {
        				addThisCharacter = false;
        			}
        		}
        		if (specialCharacters.indexOf(completePasswordString.charAt(randomLocationIdentifier)) != -1) {
        			if (!canStartWithSpecialCharacters) {
        				addThisCharacter = false;
        			}
        		}
        	}
        	if (i == passwordLength - 1) {
        		if (numbers.indexOf(completePasswordString.charAt(randomLocationIdentifier)) != -1) {
        			if (!canEndInNumbers) {
        				addThisCharacter = false;
        			}
        		}
        		if (specialCharacters.indexOf(completePasswordString.charAt(randomLocationIdentifier)) != -1) {
        			if (!canEndWithSpecialCharacters) {
        				addThisCharacter = false;
        			}
        		}
        	}
        	if (addThisCharacter) {
        		if (null != restrictedCharacters && restrictedCharacters.length > 0) {
        			for (Character restrictedCharacter : restrictedCharacters) {
        				if (restrictedCharacter.equals(completePasswordString.charAt(randomLocationIdentifier))) {
        					addThisCharacter = false;
        					break;
        				}
        			}
        		}
        		if (addThisCharacter) {
        			password.add(completePasswordString.charAt(randomLocationIdentifier));
        			i++;
        		}
        	}
        	if (password.size() == passwordLength) {
        		break;
        	}
        } while(true);
        return password.toArray(new Character[] {});
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