package ui_StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactories.BoraHomePage;
import pageFactories.BoraLoginPage;
import ui_Implementations.BoraCommunityUiImplementations;
import utilities.DriverFactory;
import utilities.Keywords;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoraCommunityStepDefinitions {

	private WebDriver driver = DriverFactory.getInstance();
	private BoraHomePage homePage = new BoraHomePage(driver);
	private BoraLoginPage loginPage = new BoraLoginPage(driver);
	
	@When("User logs into BoraCommunity with username: {string} & password: {string}")
	public void user_logs_into_BoraCommunity_with_username_password(String username, String password) {
		homePage.goToLogin();
		loginPage.login(username, password);
	}

	@Then("User should see the expeirence with detail: {string}, {string}, {string}, {string} listed under Experience Credentials")
	public void user_should_see_the_expeirence_with_detail_listed_under_Experience_Credentials(String title, String company, String from, String to) {
		BoraCommunityUiImplementations.validateExperienceExistOnDashboard(title, company, from, to);
	}

	@Given("I'm on BoraTech Community homepage")
	public void i_m_on_BoraTech_Community_homepage() {
		driver.get("https://lit-mesa-27064.herokuapp.com/");
	}

	@When("I click on LogIn button")
	public void i_click_on_LogIn_button() {
		loginPage.clickLogin();
	}

	@When("I enter username {string} and password {string}")
	public void i_enter_username_and_password(String username, String password) {
		loginPage.setEmail(username);
		loginPage.setPassword(password);
	}

	@When("submit")
	public void submit() {
		loginPage.clickLogin();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Keywords.takeScreenShot(driver, null);
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
