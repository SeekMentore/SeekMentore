package com.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.constants.ConnectionConstants;

public class ConnectionUtils implements ConnectionConstants {
	
	public static String connectToUnsecuredURL (
			final String url,
			final String method,
			final Map<String, String> postParams,
			final Map<String, String> requestProperties
	) throws IOException {
		final HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
		connection.setRequestMethod(method);
		for(Map.Entry<String, String> entry : requestProperties.entrySet()) {
			connection.setRequestProperty(entry.getKey(), entry.getValue());
		}
		final StringBuilder postParamsStringBuilder = new StringBuilder(EMPTY_STRING);
		for(Map.Entry<String, String> entry : postParams.entrySet()) {
			postParamsStringBuilder.append(entry.getKey()).append(ASSIGNMENT_OPERATOR).append(entry.getValue()).append(AMPERSAND_OPERATOR);
		}
		String postParamsString = EMPTY_STRING;
		if (postParamsStringBuilder.toString().length() > 0) {
			postParamsString = postParamsStringBuilder.toString().substring(0, postParamsStringBuilder.toString().length() - 1);
		}
		connection.setDoOutput(true);
		final DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
		dataOutputStream.writeBytes(postParamsString);
		dataOutputStream.flush();
		dataOutputStream.close();
		final int responseCode = connection.getResponseCode();
		LoggerUtils.logOnConsole("Connection URL : " + url);
		LoggerUtils.logOnConsole("Post parameters : " + postParamsString);
		LoggerUtils.logOnConsole("Response Code : " + responseCode);
		final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		final StringBuilder response = new StringBuilder(EMPTY_STRING);
		while ((inputLine = bufferedReader.readLine()) != null) {
			response.append(inputLine);
		}
		bufferedReader.close();
		LoggerUtils.logOnConsole(response.toString());
		return response.toString();
	}
}
