package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static hellocucumber.Hooks.driver;

public class StepDefinitions {
	
	@Given("I'm on BoraTech Community homepage")
	public void i_m_on_BoraTech_Community_homepage() {
		driver.get("https://lit-mesa-27064.herokuapp.com/");
	}

	@When("I click on LogIn button")
	public void i_click_on_LogIn_button() {
		driver.findElement(By.xpath("//a[text()='Login']")).click();
	}

	@When("I enter valid username and valid password")
	public void i_enter_valid_username_and_valid_password() {
		driver.findElement(By.name("email")).sendKeys("murad@test.com");
		driver.findElement(By.name("password")).sendKeys("murad002");
	}

	@When("submit")
	public void submit() {
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

	@Then("I should be logged in")
	public void i_should_be_logged_in() {
		try {
			WebElement logoutButton = driver.findElement(By.xpath("//span[text()='Logout']"));
			assertTrue("User is not logged in.", logoutButton.isDisplayed());
		} catch (NoSuchElementException e) {
			assertTrue("User is not logged in.", false);
		}
	}

	@Then("See the Dashboard page")
	public void see_the_Dashboard_page() {
		try {
			WebElement h1Text = driver.findElement(By.xpath("//h1[@class='large text-primary']"));
			assertTrue("Dashboard is not shown.", h1Text.getText().equals("Dashboard"));
		} catch (NoSuchElementException e) {
			assertTrue("Dashboard is not shown.", false);
		}
		assertTrue("Dashboard is not shown.", driver.getCurrentUrl().endsWith("dashboard"));
	}

}
