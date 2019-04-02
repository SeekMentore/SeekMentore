package com.utils;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import com.constants.ApplicationConstants;

public class UUIDGeneratorUtils implements ApplicationConstants {
	
	public static UUID generateRandomUUID() {
		return UUID.randomUUID();
	}
	
	public static String generateRandomGUID() {
		return String.valueOf(generateRandomUUID());
	}
	
	public static String generateSerialGUID() {
		final StringBuilder serialId = new StringBuilder(EMPTY_STRING);
		final String uid = String.valueOf(generateRandomUUID()).toUpperCase();
		final String[] uidSections = uid.split(DASH);
		for (final String uidSection : uidSections) {
			serialId.append(String.valueOf(uidSection.charAt(ThreadLocalRandom.current().nextInt(0, uidSection.length()))));
			if (uidSection.length() > 4) {
				serialId.append(String.valueOf(uidSection.charAt(ThreadLocalRandom.current().nextInt(0, uidSection.length()))));
			}
		}
		return String.valueOf(serialId);
	}
}