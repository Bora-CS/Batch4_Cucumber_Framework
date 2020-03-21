package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.Keywords;

public class BoraDashboardPage {
	
	private WebDriver driver;
	public By pageTitle = By.xpath("//h1[@class='large text-primary']");
	
	public BoraDashboardPage (WebDriver driver) {
		this.driver = driver;
	}
	
	public String getPageTitle () {
		Keywords.waitFor(1);
		return driver.findElement(pageTitle).getText();
	}
	
}
