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

	@When("I enter username {string} and password {string}")
	public void i_enter_username_and_password(String username, String password) {
		driver.findElement(By.name("email")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
	}

	@When("submit")
	public void submit() {
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	@Then("I should not be logged in")
	public void i_should_not_be_logged_in() {
		String error = "User is somehow logged in.";
		try {
			WebElement loginElement = driver.findElement(By.xpath("//*[text()='Login']"));
			assertTrue(error, loginElement.isDisplayed());
		} catch (NoSuchElementException e) {
			assertTrue(error, false);
		}
	}

	@Then("I will not see the Dashboard page")
	public void i_will_not_see_the_Dashboard_page() {
		String error = "Sign in page is gone.";
		try {
			WebElement h1Text = driver.findElement(By.xpath("//h1[@class='large text-primary']"));
			assertTrue(error, h1Text.getText().equals("Sign In"));
		} catch (NoSuchElementException e) {
			assertTrue(error, false);
		}
		assertTrue(error, driver.getCurrentUrl().endsWith("login"));
	}


}
