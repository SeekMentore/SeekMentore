package com.constants;

public interface PasswordConstants extends ApplicationConstants {

	String CAPITAL_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String SMALL_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    String SPECIAL_CHARACTERS = "!@#^*_?";
    String NUMBERS = "0123456789";
    
    Integer MIN_PASSWORD_LENGTH = 4;
	Integer MAX_PASSWORD_LENGTH = 16;
	Boolean CAPITAL_CHARACTERS_ALLOWED = true;
	Boolean SMALL_CHARACTERS_ALLOWED = true;
	Boolean SPECIAL_CHARACTERS_ALLOWED = true;
	Boolean NUMBERS_ALLOWED = true;
	Boolean CAN_START_WITH_NUMBERS = true;
	Boolean CAN_END_WITH_NUMBERS = true;
	Boolean CAN_START_WITH_SPECIAL_CHARACTERS = true;
	Boolean CAN_END_WITH_SPECIAL_CHARACTERS = true;
	Boolean SHOULD_CONTAIN_CAPITAL_CHARACTERS = false;
	Boolean SHOULD_CONTAIN_SMALL_CHARACTERS = false;
	Boolean SHOULD_CONTAIN_SPECIAL_CHARACTERS = false;
	Boolean SHOULD_CONTAIN_NUMBERS = false;
}
