package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BoraLoginPage {

	private WebDriver driver;
	public By email = By.name("email");
	public By password = By.name("password");
	public By login = By.xpath("//input[@type='submit']");
	
	public BoraLoginPage (WebDriver driver) {
		this.driver = driver;
	}
	
	public void setEmail(String emailValue) {
		driver.findElement(email).sendKeys(emailValue);
	}
	
	public void setPassword(String passwordValue) {
		driver.findElement(password).sendKeys(passwordValue);
	}
	
	public void clickLogin () {
		driver.findElement(login).click();
	}
	
	public void login (String emailValue, String passwordValue) {
		this.setEmail(emailValue);
		this.setPassword(passwordValue);
		this.clickLogin();
	}
	
}
