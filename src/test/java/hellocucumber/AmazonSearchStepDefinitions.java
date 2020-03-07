package hellocucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static hellocucumber.Hooks.driver;

import java.util.List;
import java.util.Map;

public class AmazonSearchStepDefinitions {

	@Given("I'm on the amamzon.com homepage")
	public void i_m_on_the_amamzon_com_homepage() {
		driver.get("https://www.amazon.com/");
	}

	@When("I search for a item")
	public void i_search_for_a_item(DataTable dataTable) {
		Map<String, Integer> data = dataTable.asMap(String.class, Integer.class);
		for (String key : data.keySet()) {
			System.out.println("Item name: " + key + " - Max price: " + data.get(key));
		}

	}

	@Then("I should see some result")
	public void i_should_see_some_result() {

	}

	@Given("I'm logged in to my account")
	public void i_m_logged_in_to_my_account(io.cucumber.datatable.DataTable dataTable) {
		List<LogInData> data = dataTable.asList(LogInData.class);
		for (LogInData logInData : data) {
			System.out.println("Username: " + logInData.username + " Password: " + logInData.password);
		}
	}

}
