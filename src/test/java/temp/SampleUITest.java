package temp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.DriverFactory;
import utilities.Keywords;

public class SampleUITest {

	public static void main(String[] args) {
		
		WebDriver driver = DriverFactory.getInstance();
		driver.get("https://lit-mesa-27064.herokuapp.com/apply");
		
		Keywords.sendKeys(driver, By.name("firstname"), "Muradil");
		Keywords.sendKeys(driver, By.name("lastname"), "Erkin");		
		Keywords.click(driver, By.xpath("//input[@name='gender'][@value='male']"));
		Keywords.selectFromDropdownByValue(driver, By.name("course"), "scrummaster");
		
		DriverFactory.cleanUp();
		
	}

}
