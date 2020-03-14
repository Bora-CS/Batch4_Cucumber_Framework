package api_StepDefinitions;

import static org.junit.Assert.*;

import api_Implementations.BoraAPIServices;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class AddExperienceStepDefinitions {

	public static String token;
	public static Response response;

	@Given("User is logged in with username: {string} & password: {string} and have a valid token")
	public void user_is_logged_in_with_username_password_and_have_a_valid_token(String username, String password) {
		token = BoraAPIServices.login(username, password);
		assertTrue(!token.isEmpty());
	}
	
//	@Given("^User is logged in with username: \"(.*)\" & password: \"(.*)\" and have a valid token$")
//	public void user_is_logged_in_with_username_password_and_have_a_valid_token(String username, String password) {
//		token = BoraAPIServices.login(username, password);
//		assertTrue(!token.isEmpty());
//	}
	
//	@Given("^User is logged in with username: -(.*)- & password: -(.*)- and have a valid token$")
//	public void user_is_logged_in_with_username_password_and_have_a_valid_token(String username, String password) {
//		token = BoraAPIServices.login(username, password);
//		assertTrue(!token.isEmpty());
//	}
	
//	@Given("^User is logged in with username: <(.*)> & password: <(.*)> and have a valid token$")
//	public void user_is_logged_in_with_username_password_and_have_a_valid_token(String username, String password) {
//		token = BoraAPIServices.login(username, password);
//		assertTrue(!token.isEmpty());
//	}

	@When("User send a request to add experience with data: {string}, {string}, {string}, {string}, {string}, {string}, {string}")
	public void user_send_a_request_to_add_experience_with_data(String title, String company, String location,
			String from, String to, String currentString, String description) {
		boolean current = Boolean.valueOf(currentString);
		response = BoraAPIServices.addExperience(title, company, location, from, to, current, description, token);
	}

	@Then("User should receive a response status code: {string}")
	public void user_should_receive_a_response_status_code(String statusCodeString) {
		int expectedStatusCode = Integer.valueOf(statusCodeString);
		int actualStatusCode = response.getStatusCode();
		assertEquals(expectedStatusCode, actualStatusCode);
	}

}
