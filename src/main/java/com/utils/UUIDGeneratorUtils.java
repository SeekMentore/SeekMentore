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
		return String.valueOf(generateRandomUUID()).toUpperCase();
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
	
	/*public static void main(String args[]) {
		final String fsKey = "secured/contracts/customer/subscriptionpackage/8B4EB26D-9E94-417F-835D-267B97F98766/Contract.pdf";
		final String folderNameWithPathFromRootFolder = fsKey.substring(0, fsKey.lastIndexOf(FORWARD_SLASH));
		final String filename = fsKey.substring(fsKey.lastIndexOf(FORWARD_SLASH) + 1);
		System.out.println(folderNameWithPathFromRootFolder);
		System.out.println(filename);
	}*/
}