package com.utils;

import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.json.JSONException;
import org.json.JSONObject;

import com.constants.ApplicationConstants;
import com.constants.JSONConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class JSONUtils implements JSONConstants {
	
	public static String convertObjToJSONString (
			final Object obj,
			final String ObjName
	) throws JSONException {
		return new JSONObject().put(ObjName, new Gson().toJson((obj != null) ? obj : ApplicationConstants.EMPTY_STRING)).toString();
	}
	
	public static JsonObject getJSONObjectFromString(final String jsonResponse) {
		final JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse.toString()));
		final JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		return jsonObject;
	}
	
	public static JsonArray getJSONArrayFromString(final String jsonResponse) {
		final JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse.toString()));
		final JsonArray jsonArray = jsonReader.readArray();
		jsonReader.close();
		return jsonArray;
	}
	
	public static <T extends Object> T getValueFromJSONObject(final JsonObject jsonObject, final Object property, Class<T> type) {
		try {
			final JsonValue value = jsonObject.get(property);
			final JsonValue.ValueType valueType = value.getValueType();
			if (valueType.equals(JsonValue.ValueType.STRING)) {
				return type.cast(jsonObject.getString((String)property));
			} else if (valueType.equals(JsonValue.ValueType.NUMBER)) {
				final JsonNumber numberValue = jsonObject.getJsonNumber((String)property);
				if (type.equals(Integer.class)) {
					return type.cast(numberValue.intValue());
				} else if (type.equals(Long.class)) {
					return type.cast(numberValue.longValue());
				} else if (type.equals(Float.class) || type.equals(Double.class)) {
					return type.cast(numberValue.doubleValue());
				} 
				return type.cast(0);
			} else if (valueType.equals(JsonValue.ValueType.ARRAY)) {
				return type.cast(jsonObject.getJsonArray((String)property));
			} else if (valueType.equals(JsonValue.ValueType.TRUE) || valueType.equals(JsonValue.ValueType.FALSE)) {
				return type.cast(jsonObject.getBoolean((String)property));
			} else if (valueType.equals(JsonValue.ValueType.OBJECT)) {
				return type.cast(value);
			}
			return null;
		} catch (Exception e) {
			if (type == Long.class || type == Integer.class || type == Double.class || type == Float.class) {
				return type.cast(0);
			}
			return null;
		}
	}
	
	public static <T extends Object> T convertToObjectFromJSONString(final String jsonString, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		return type.cast(new ObjectMapper().readValue(jsonString, new TypeReference<T>(){}));
	}
}
