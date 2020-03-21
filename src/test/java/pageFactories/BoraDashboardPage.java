package pageFactories;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Keywords;

public class BoraDashboardPage {

	private WebDriver driver;

	@FindBy(xpath = "//h1[@class='large text-primary']")
	public WebElement pageTitle;
	
	@FindBy(xpath = "//table[1]/tbody/tr")
	public List<WebElement> experienceTableRows;

	public BoraDashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getPageTitle() {
		Keywords.waitFor(1);
		return pageTitle.getText();
	}
	
	public List<WebElement> getExperienceTableRows () {
		return experienceTableRows;
	}

}
