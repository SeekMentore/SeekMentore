package com.utils;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.constants.JSONConstants;

public class JSONUtils implements JSONConstants {
	
	public static JsonObject getJSONObjectFromString(final String jsonResponse) {
		final JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse.toString()));
		final JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		return jsonObject;
	}
}
