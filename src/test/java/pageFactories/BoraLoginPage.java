package pageFactories;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BoraLoginPage {

	private WebDriver driver;
	
	@FindBy(xpath = "//h1[@class='large text-primary']")
	public WebElement pageTitle;
	
	@FindBy(name = "email")
	public WebElement email;
	
	@FindBy(name = "password")
	public WebElement password;
	
	@FindBy(xpath = "//input[@type='submit']")
	public WebElement login;
	
	@FindBy(css = ".alert.alert-danger")
	public WebElement alert;
	
	public BoraLoginPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setEmail(String emailValue) {
		email.sendKeys(emailValue);
	}
	
	public void setPassword(String passwordValue) {
		password.sendKeys(passwordValue);
	}
	
	public void clickLogin () {
		login.click();
	}
	
	public String getPageTitle () {
		return pageTitle.getText();
	}
	
	public boolean isAlertExist () {
		try {
			return alert.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public BoraDashboardPage login (String emailValue, String passwordValue) {
		this.setEmail(emailValue);
		this.setPassword(passwordValue);
		this.clickLogin();
		return new BoraDashboardPage(driver);
	}
	
	public void loginWithInvalidData (String emailValue, String passwordValue) {
		this.setEmail(emailValue);
		this.setPassword(passwordValue);
		this.clickLogin();
	}
	
}
