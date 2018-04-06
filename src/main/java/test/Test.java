package test;

import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.constants.ApplicationConstants;

public class Test {
	private static final String VALID_NAME_REGEX_WITH_SPACES = "^[\\p{L} .'-]+$";
	private static final String REGEX_FOR_NUMBERS = "\\d{1}";
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   System.out.print(email + " >> ");
		   return result;
		}
	
	public static boolean validatePlainNotNullAndEmptyTextString(final String text) {
		if (null != text && !ApplicationConstants.EMPTY_STRING.equals(text))
			return true;
		return false;
	}
	
	public static boolean validateNameString(final String name, boolean spaceAllowed) {
		if (validatePlainNotNullAndEmptyTextString(name)) {
		    return Pattern.compile(VALID_NAME_REGEX_WITH_SPACES, Pattern.CASE_INSENSITIVE).matcher(name).find() 
	    		? 
				(spaceAllowed 
						? true 
						: 
						(name.indexOf(ApplicationConstants.WHITESPACE) == -1)
				) 
				: 
				false;
		}
		return false;
	}
	public static boolean validateMobilePhoneNumbers(final String contactNumber) {
		return Pattern.compile(REGEX_FOR_NUMBERS, Pattern.CASE_INSENSITIVE).matcher(contactNumber).find();
	}
	public static void main(String args[]) {
		/*System.out.println(Test.isValidEmailAddress("mukherjeeshantanu797@gmail.com"));
		System.out.println(Test.isValidEmailAddress("gunjack.mukherjee@gmail.com"));
		System.out.println(Test.isValidEmailAddress("support@seekmentore.com"));
		System.out.println(Test.isValidEmailAddress("prm@seekmentore.com"));
		System.out.println(Test.isValidEmailAddress("prm@seekmentore"));
		System.out.println(Test.isValidEmailAddress("prmseekmentore"));
		System.out.println(Test.isValidEmailAddress("prmseekmentore@"));
		System.out.println(Test.isValidEmailAddress("prmseekmentore@dfsfdgads"));
		System.out.println(Test.isValidEmailAddress("prmseek.m.entore@...dfsfdgads"));
		System.out.println(Test.isValidEmailAddress("prmseek.m.entore@dfsfdgads"));
		System.out.println(Test.isValidEmailAddress("prmseek.m#entore@dfsfdgads"));*/
		
		/*System.out.println(Test.validateNameString("prmseekmen tore", true));*/
		System.out.println(Test.validateMobilePhoneNumbers("0"));
	}

}
