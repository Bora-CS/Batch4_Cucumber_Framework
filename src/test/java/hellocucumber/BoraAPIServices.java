package hellocucumber;

import org.json.simple.JSONObject;
import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BoraAPIServices {
	
	public static final String APPLICATION_URL = "http://ec2-3-86-91-230.compute-1.amazonaws.com:5000";
	public static final String APPLICATION_URI = APPLICATION_URL;
	
	public static String login(String email, String password) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = APPLICATION_URL;
		RequestSpecification request = RestAssured.given();

		request.header("Content-type", "application/json");

		JSONObject body = new JSONObject();
		body.put("email", email);
		body.put("password", password);
		request.body(body);

		Response response = request.post(endpoint);

		try {
			Assert.assertEquals("Log in failed.",response.getStatusCode(), 200);
			JsonPath jp = response.jsonPath();
			String token = jp.get("token");
			return token;
		} catch (Exception e) {
			return "";
		}
	}
	
	public static Response addExperience(String title, String company, String location, String from, String to, boolean current, String description, String token) {
		String endpoint = "/api/profile/experience";
		RestAssured.baseURI = APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-type", "Application/json");
		request.header("x-auth-token", token);

		JSONObject body = new JSONObject();

		body.put("title", title);
		body.put("company", company);
		body.put("location", location);
		body.put("from", from);
		body.put("to", to);
		body.put("current", current);
		body.put("description", description);

		request.body(body);
		Response response = request.put(endpoint);
		return response;
	}
	
}
