package com.utils;

import java.util.UUID;

import com.constants.ApplicationConstants;

public class UUIDGeneratorUtils implements ApplicationConstants {
	
	public static UUID generateRandomUUID() {
		return UUID.randomUUID();
	}
	
	public static String generateRandomGUID() {
		return String.valueOf(UUID.randomUUID());
	}
}