package geneticAlgorithm.imporvised.v1;

import java.util.List;

import com.constants.ValidationConstants;

public class Validator implements ValidationConstants {
	
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
	
	public static Boolean checkStringContainsText(final String stringObject, final String textToSearch) {
	    if (checkStringAvailability(stringObject) && checkStringAvailability(textToSearch)) {
	    	if (stringObject.indexOf(textToSearch) != -1) {
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
}
