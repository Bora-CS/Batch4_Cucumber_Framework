package standalone_ui_tests;

import org.testng.annotations.Test;

import pageFactories.BoraDashboardPage;
import pageFactories.BoraHomePage;
import pageFactories.BoraLoginPage;
import utilities.DriverFactory;
import utilities.Keywords;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BoraLoginTest {
	
	private WebDriver driver;
	
	@Test
	public void happyPath() {
		BoraHomePage homePage = new BoraHomePage(driver);
		BoraLoginPage loginPage = homePage.goToLogin();
		BoraDashboardPage dashboardPage = loginPage.login("murad@test.com", "murad001");
		String actualPageTitle = dashboardPage.getPageTitle();
		String expectedTitle = "Dashboard";
		Assert.assertEquals(actualPageTitle, expectedTitle);
//		Assert.assertTrue(!loginPage.isAlertExist()); // Same as below
		Assert.assertFalse(loginPage.isAlertExist()); // Same as above
	}
	
	@Test
	public void nagativePath_invalidPassword() {
		BoraHomePage homePage = new BoraHomePage(driver);
		BoraLoginPage loginPage = homePage.goToLogin();
		loginPage.loginWithInvalidData("murad@test.com", "murad001");
		String actualPageTitle = loginPage.getPageTitle();
		String expectedTitle = "Sign In";
		Assert.assertEquals(actualPageTitle, expectedTitle);
		Assert.assertTrue(loginPage.isAlertExist());
	}
	
	@Test
	public void nagativePath_invalidUsername() {
		BoraHomePage homePage = new BoraHomePage(driver);
		BoraLoginPage loginPage = homePage.goToLogin();
		loginPage.loginWithInvalidData("murad_invalid@test.com", "murad001");
		String actualPageTitle = loginPage.getPageTitle();
		String expectedTitle = "Sign In";
		Assert.assertEquals(actualPageTitle, expectedTitle);
		Assert.assertTrue(loginPage.isAlertExist());
	}

	@BeforeMethod
	public void beforeTest() {
		driver = DriverFactory.getInstance();
		driver.get("https://lit-mesa-27064.herokuapp.com/");
	}

	@AfterMethod
	public void afterTest(ITestResult result) {
	   if (result.getStatus() == ITestResult.FAILURE) {
	      Keywords.takeScreenShot(driver, result.getName());
	   }  
	   DriverFactory.cleanUp();
	}
}
