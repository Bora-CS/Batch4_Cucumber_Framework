package hellocucumber;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	public static WebDriver driver;
	
	@Before
	public void setupSelenium () {
		System.out.println("==> Run Selenium");
		System.setProperty("webdriver.chrome.driver", "/Users/Muradil/Batch4_Cucumber_Framework/src/test/resources/hellocucumber/chromedriver");	
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	@After
	public void cleanupSelenium () {
		System.out.println("==> Quit Selenium");
		driver.close();
		driver.quit();
	}
	
}
