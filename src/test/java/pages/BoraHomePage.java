package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BoraHomePage {
	
	private WebDriver driver;
	public By loginLink = By.xpath("//a[text()='Login']");
		
	public BoraHomePage (WebDriver driver) {
		this.driver = driver;
	}
	
	public void goToLogin () {
		driver.findElement(loginLink).click();
	}

}
