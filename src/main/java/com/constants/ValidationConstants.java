package com.constants;

public interface ValidationConstants extends ApplicationConstants {
	
	String REGEX_FOR_NAME_WITH_SPACES = "^[\\p{L} .'-]+$";
	String REGEX_FOR_NUMBERS = "\\d{1}";
}
