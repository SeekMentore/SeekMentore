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
	
	public static String generateFilenameAppendedUID() {
		final String uid = String.valueOf(generateRandomUUID()).toUpperCase().replaceAll(DASH, EMPTY_STRING);
		final Integer min = 0;
		final Integer max = uid.length() - 1;
		final Integer minimumDistanceApart = 3;
		Integer startIndex = ThreadLocalRandom.current().nextInt(min, max - minimumDistanceApart - 1);
		Integer endIndex = ThreadLocalRandom.current().nextInt(startIndex + minimumDistanceApart, max + 1);
		return uid.substring(startIndex, endIndex);
	}
}