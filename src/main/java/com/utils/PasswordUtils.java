package com.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.constants.PasswordConstants;
import com.exception.ApplicationException;

public class PasswordUtils implements PasswordConstants {
	
	public static boolean checkPasswordPolicy(final String password) {
		Boolean policyFollowed = false;
		final String allCharacters = CAPITAL_CHARACTERS + SMALL_CHARACTERS + SPECIAL_CHARACTERS + NUMBERS;
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(password)) {
			if (password.length() >= MIN_PASSWORD_LENGTH && password.length() <= MAX_PASSWORD_LENGTH) {
				int i = 0;
				int capsCount = 0;
				int smallCount = 0;
				int specialCount = 0;
				int numberCount = 0;
				Boolean broken = false;
				for (char character : password.toCharArray()) {
					if (allCharacters.indexOf(character) == -1) {
						broken = true;
						break;
					}
					if(!CAPITAL_CHARACTERS_ALLOWED) {
						if (CAPITAL_CHARACTERS.indexOf(character) != -1) {
							broken = true;
							break;
						}
					}
					if(!SMALL_CHARACTERS_ALLOWED) {
						if (SMALL_CHARACTERS.indexOf(character) != -1) {
							broken = true;
							break;
						}
					}
					if(!SPECIAL_CHARACTERS_ALLOWED) {
						if (SPECIAL_CHARACTERS.indexOf(character) != -1) {
							broken = true;
							break;
						}
					}
					if(!NUMBERS_ALLOWED) {
						if (NUMBERS.indexOf(character) != -1) {
							broken = true;
							break;
						}
					}
					if (i == 0) {
						if(!CAN_START_WITH_NUMBERS) {
							if (NUMBERS.indexOf(character) != -1) {
								broken = true;
								break;
							}
						}
						if(!CAN_START_WITH_SPECIAL_CHARACTERS) {
							if (SPECIAL_CHARACTERS.indexOf(character) != -1) {
								broken = true;
								break;
							}
						}
					}
					if (i == password.length() - 1) {
						if(!CAN_END_WITH_NUMBERS) {
							if (NUMBERS.indexOf(character) != -1) {
								broken = true;
								break;
							}
						}
						if(!CAN_END_WITH_SPECIAL_CHARACTERS) {
							if (SPECIAL_CHARACTERS.indexOf(character) != -1) {
								broken = true;
								break;
							}
						}
					}
					if (CAPITAL_CHARACTERS.indexOf(character) != -1) {
						capsCount++;
					}
					if (SMALL_CHARACTERS.indexOf(character) != -1) {
						smallCount++;
					}
					if (SPECIAL_CHARACTERS.indexOf(character) != -1) {
						specialCount++;
					}
					if (NUMBERS.indexOf(character) != -1) {
						numberCount++;
					}
					i++;
				}
				if (!broken) {
					policyFollowed = true;
					if (SHOULD_CONTAIN_CAPITAL_CHARACTERS && capsCount == 0) {
						policyFollowed = false;
					}
					if (SHOULD_CONTAIN_SMALL_CHARACTERS && smallCount == 0) {
						policyFollowed = false;
					}
					if (SHOULD_CONTAIN_SPECIAL_CHARACTERS && specialCount == 0) {
						policyFollowed = false;
					}
					if (SHOULD_CONTAIN_NUMBERS && numberCount == 0) {
						policyFollowed = false;
					}
				}
			}
		}
		return policyFollowed;
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
 
        String completePasswordString = EMPTY_STRING;
        if (needCapitalLetters) {
        	completePasswordString += CAPITAL_CHARACTERS;
        }
        if (needSmallLetters) {
        	completePasswordString += SMALL_CHARACTERS;
        }
        if (needSpecialCharacters) {
        	completePasswordString += SPECIAL_CHARACTERS;
        }
        if (needNumbers) {
        	completePasswordString += NUMBERS;
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
        		if (NUMBERS.indexOf(completePasswordString.charAt(randomLocationIdentifier)) != -1) {
        			if (!canStartWithNumbers) {
        				addThisCharacter = false;
        			}
        		}
        		if (SPECIAL_CHARACTERS.indexOf(completePasswordString.charAt(randomLocationIdentifier)) != -1) {
        			if (!canStartWithSpecialCharacters) {
        				addThisCharacter = false;
        			}
        		}
        	}
        	if (i == passwordLength - 1) {
        		if (NUMBERS.indexOf(completePasswordString.charAt(randomLocationIdentifier)) != -1) {
        			if (!canEndInNumbers) {
        				addThisCharacter = false;
        			}
        		}
        		if (SPECIAL_CHARACTERS.indexOf(completePasswordString.charAt(randomLocationIdentifier)) != -1) {
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
	
}