package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	private static WebDriver driver = null;

	private DriverFactory() {
	}

	public static WebDriver getInstance() {
		try {
			if (driver == null) {
				System.setProperty("webdriver.chrome.driver", Keywords.getDriverPath());
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			}
			return driver;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void cleanUp() {
		if (driver != null) {
			driver.close();
			driver.quit();
			driver = null;
		}
	}

}
