package hellocucumber;

import static hellocucumber.Hooks.driver;

import org.openqa.selenium.By;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class RandomClass {
	
	@Given("I'm on BoraTech Community homepage")
	public void i_m_on_BoraTech_Community_homepage() {
		driver.get("https://lit-mesa-27064.herokuapp.com/");
	}

	@When("I click on LogIn button")
	public void i_click_on_LogIn_button() {
		driver.findElement(By.xpath("//a[text()='Login']")).click();
	}
}
