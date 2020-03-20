package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Keywords {

	public static ObjectRepository objRepo = new ObjectRepository();
	public static WebDriver driver;

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
			js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;')",
					element);
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
	
	public static void startUItest() {
		driver = DriverFactory.getInstance();
	}
	
	public static void endUITest() {
		DriverFactory.cleanUp();
	}
	
	public static void navigate(String url) {
		driver.get(url);
	}

	public static WebElement getElement(WebDriver driver, String locator) throws Exception {
		return driver.findElement(objRepo.getLocator(locator));
	}

	public static void click(WebDriver driver, String locator) throws Exception {
		WebElement element = getElement(driver, locator);
		highlightElement(driver, getElement(driver, locator));
		element.click();
	}
	
	public static void click(String locator) throws Exception {
		WebElement element = getElement(driver, locator);
		highlightElement(driver, getElement(driver, locator));
		element.click();
	}

	public static void enter(WebDriver driver, String locator, String input) throws Exception {
		WebElement element = getElement(driver, locator);
		highlightElement(driver, element);
		element.sendKeys(input);
	}
	
	public static void enter(String locator, String input) throws Exception {
		WebElement element = getElement(driver, locator);
		highlightElement(driver, element);
		element.sendKeys(input);
	}

	public static void selectFromDropdownByValue(WebDriver driver, String locator, String value) throws Exception {
		WebElement element = getElement(driver, locator);
		highlightElement(driver, element);
		Select select = new Select(element);
		select.selectByValue(value);
	}
	
	public static void selectFromDropdownByValue(String locator, String value) throws Exception {
		WebElement element = getElement(driver, locator);
		highlightElement(driver, element);
		Select select = new Select(element);
		select.selectByValue(value);
	}
	
	public static void selectFromDropdownByVisibleText(WebDriver driver, String locator, String text) throws Exception {
		WebElement element = getElement(driver, locator);
		highlightElement(driver, element);
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	public static void selectFromDropdownByVisibleText(String locator, String text) throws Exception {
		WebElement element = getElement(driver, locator);
		highlightElement(driver, element);
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	public static List<WebElement> getElements(String locator) throws Exception {
		return driver.findElements(objRepo.getLocator(locator));
	}
	
	public static String getLocatorValue (String locator) {
		return objRepo.getLocatorValue(locator);
	}
	
	public static By getLocator (String locator) throws Exception {
		return objRepo.getLocator(locator);
	}

	public static void clear(String locator) throws Exception {
		WebElement element = getElement(driver, locator);
		highlightElement(driver, element);
		element.clear();
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
}
