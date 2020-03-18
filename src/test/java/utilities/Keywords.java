package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Keywords {

	public static String getDriverPath() throws Exception {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("mac")) {
			return Constants.CHROME_DRIVER_PATH_MAC;
		} else if (os.toLowerCase().startsWith("windows")) {
			return Constants.CHROME_DRIVER_PATH_WINDOWS;
		} else {
			throw new Exception("OS Name: " + os + ", No ChromeDriver availbale for this operating system.");
		}
	}

	public static String getCurrentTimeStamp() {
		Date date = new Date();
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
		return timeStamp;
	}

	public static void takeScreenShot(WebDriver driver) {
		TakesScreenshot screenShotTaker = (TakesScreenshot) driver;
		File screenShotFile = screenShotTaker.getScreenshotAs(OutputType.FILE);
		File targetScreenShotResultFile = new File("target/ScreenShots/SS" + getCurrentTimeStamp() + ".jpeg");
		try {
			FileUtils.copyFile(screenShotFile, targetScreenShotResultFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void highlightElement(WebDriver driver, WebElement element) {
		if (Constants.DEMO_MODE) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;')", element);
			waitFor(1);
		}
	}

	public static void waitFor(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void click(WebDriver driver, By locator) {
		WebElement element = driver.findElement(locator);
		highlightElement(driver, element);
		element.click();
	}
	
	public static void sendKeys(WebDriver driver, By locator, String input) {
		WebElement element = driver.findElement(locator);
		highlightElement(driver, element);
		element.sendKeys(input);
	}

	public static void selectFromDropdownByValue(WebDriver driver, By locator, String value) {
		WebElement element = driver.findElement(locator);
		highlightElement(driver, element);
		Select select = new Select(element);
		select.selectByValue(value);
	}
}
