package ui_Implementations;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageFactories.BoraDashboardPage;
import utilities.DriverFactory;

public class BoraCommunityUiImplementations {

	private static WebDriver driver = DriverFactory.getInstance();

	public static void validateExperienceExistOnDashboard(String title, String company, String from, String to) {
		BoraDashboardPage dashboard = new BoraDashboardPage(driver);
		List<WebElement> experienceTableRows = dashboard.getExperienceTableRows();
		boolean found = false;
		for (WebElement row : experienceTableRows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String currentCompany = cells.get(0).getText();
			String currentTitle = cells.get(1).getText();
			String currentYears = cells.get(2).getText();
			String expectedYears = from + " - " + (to.isEmpty() ? "Now" : to);
			if (currentCompany.equalsIgnoreCase(company) && currentTitle.equalsIgnoreCase(title)
					&& currentYears.equalsIgnoreCase(expectedYears)) {
				found = true;
				break;
			}
		}
		Assert.assertTrue("The expected experience is not listed.", found);
	}

}
