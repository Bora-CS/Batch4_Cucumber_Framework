package utilities;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectRepository {

	private Properties prop;

	public ObjectRepository() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(Constants.OBJECT_REPOSITORY_PATH);
			prop.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public By getLocator(String key) throws Exception {
		String locator = prop.getProperty(key);
		String locatorType = locator.split(":")[0];
		String locatorValue = locator.split(":")[1];

		switch (locatorType) {
		case "id":
			return By.id(locatorValue);
		case "name":
			return By.name(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		case "linkText":
		case "link":
			return By.linkText(locatorValue);
		case "partialLinkText":
			return By.partialLinkText(locatorValue);
		case "className":
		case "class":
			return By.className(locatorValue);
		case "cssSelector":
		case "css":
			return By.cssSelector(locatorValue);
		case "tagName":
		case "tag":
			return By.tagName(locatorValue);
		default:
			throw new Exception("Unknown locator type <" + locatorType + ">");
		}
	}

	public String getLocatorValue(String key) {
		String locator = prop.getProperty(key);
		String locatorValue = locator.split(":")[1];
		return locatorValue;
	}

}
