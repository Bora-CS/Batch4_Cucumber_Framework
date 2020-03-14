package ui_StepDefinitions;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IsItFridayYetStepDefinitions {
	private String today;
	private String actualAnswer;
	
	@Given("today is {string}")
	public void today_is(String day) {
		today = day;
	}

	@When("I ask if it's Friday yet")
	public void i_ask_if_it_s_Friday_yet() {
		actualAnswer = IsItFriday.isItFriday(today);
	}

	@Then("I should be told {string}")
	public void i_should_be_told(String answer) {
	    Assert.assertEquals(answer, actualAnswer);
	}
}

class IsItFriday {
	public static String isItFriday(String today) {
		if ("Friday".equalsIgnoreCase(today)) {
			return "Yes";
		} else {
			return "Nope";
		}
	}
}
