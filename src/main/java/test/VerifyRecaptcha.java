package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

public class VerifyRecaptcha {

	public static final String url = "https://www.google.com/recaptcha/api/siteverify";
	public static final String secret = "6LdFBlAUAAAAAOV_CBp_pYFWzwklM070ciEzcr8c";
	private final static String USER_AGENT = "Mozilla/5.0";

	public static boolean verify(String gRecaptchaResponse) throws IOException {
		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
			return false;
		}
		
		try{
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String postParams = "secret=" + secret + "&response="
				+ gRecaptchaResponse;

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(postParams);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + postParams);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());
		
		//parse JSON response and return 'success' value
		JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		
		return jsonObject.getBoolean("success");
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String args[]) throws IOException {
		verify("03AJIzXZ75KpmRuAYaB31nI0ksxko6oNMvuFEvW83jA7zJk6xbEHqhIWgkHD_9gZZhIxj33uDe8Y4sdIQNuDTx4cO1zxGCJC15rX2DfM4EPq-TZ8DRBtFy4lzPOaIWF6emPWp_zrzlUh6q-hT9nUnAXHeDrOjS8wZEAndXMYnKRQwa4mmFMYL3hVthLr9xNUtV4sLJTj0Hzk7V3cI4JRBr7jWQfAHJIb2XN8SFdA2Ehgipf9gwfnwhAqv_F5ahj6SgOJFnGzJo8Nce8NkuiQ2lkc_0oHxl7xMl-YbWxPvlQ3hRC6ddnbjGnqxMXEoQke26fC2nGl1jPS3N0DnrlJdCVXDQMCzU-Il8huwLNzzlhrF2x-q-eMr7XHk");
	}
}
