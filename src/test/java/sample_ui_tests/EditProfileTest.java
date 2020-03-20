package sample_ui_tests;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.Keywords;

public class EditProfileTest {

	public static void main(String[] args) {
		
		String location_before = null;
		String location_after = null;
		String locationToChangeTo = "Tysons Corner, VA";
		
		try {
			Keywords.startUItest();
			Keywords.navigate("https://lit-mesa-27064.herokuapp.com/");
			Keywords.click("HomePage.LoginLink");
			Keywords.enter("LoginPage.EmailField", "murad@test.com");
			Keywords.enter("LoginPage.PasswordField", "murad001");
			Keywords.click("LoginPage.LoginButton");
			Keywords.click("HomePage.BoraCommunityLink");
			List<WebElement> allProfiles = Keywords.getElements("CommunityPage.AllProfiles");
			for (int i = 1; i < allProfiles.size(); i++) {
				String parentXpath = Keywords.getLocatorValue("CommunityPage.AllProfiles");
				String nameXpath = parentXpath + "[" + i + "]" +Keywords.getLocatorValue("CommunityPage.IndividualProfile.Name");
				String locationXpath = parentXpath + "[" + i + "]" +Keywords.getLocatorValue("CommunityPage.IndividualProfile.Location");
				String name = Keywords.getDriver().findElement(By.xpath(nameXpath)).getText();
				if (name.equalsIgnoreCase("Murad Test")) {
					location_before = Keywords.getDriver().findElement(By.xpath(locationXpath)).getText();
					break;
				}
			}
			Keywords.click("HomePage.DashboardLink");
			Keywords.click("DashboardPage.EditProfileLink");
			Keywords.clear("EditProfilePage.LocationField");
			Keywords.enter("EditProfilePage.LocationField", locationToChangeTo);
			Keywords.click("EditProfilePage.SubmitButton");
			Keywords.click("HomePage.BoraCommunityLink");
			allProfiles = Keywords.getElements("CommunityPage.AllProfiles");
			for (int i = 1; i < allProfiles.size(); i++) {
				String parentXpath = Keywords.getLocatorValue("CommunityPage.AllProfiles");
				String nameXpath = parentXpath + "[" + i + "]" +Keywords.getLocatorValue("CommunityPage.IndividualProfile.Name");
				String locationXpath = parentXpath + "[" + i + "]" +Keywords.getLocatorValue("CommunityPage.IndividualProfile.Location");
				String name = Keywords.getDriver().findElement(By.xpath(nameXpath)).getText();
				if (name.equalsIgnoreCase("Murad Test")) {
					location_after = Keywords.getDriver().findElement(By.xpath(locationXpath)).getText();
					break;
				}
			}
			
			Assert.assertNotEquals("Location Edit is not successful", location_before, location_after);
			Assert.assertEquals("Location Updated is not what's entered", location_after, locationToChangeTo);
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Keywords.endUITest();
		}

	}

}
