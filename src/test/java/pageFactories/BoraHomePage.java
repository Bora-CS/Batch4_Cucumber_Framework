package pageFactories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BoraHomePage {
	
	private WebDriver driver;
	
	@FindBy(xpath = "//a[text()='Login']")
	private WebElement loginLink;
		
	public BoraHomePage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		driver.get("https://lit-mesa-27064.herokuapp.com/");
	}
	
	public BoraLoginPage goToLogin () {
		loginLink.click();
		return new BoraLoginPage(driver);
	}

}
