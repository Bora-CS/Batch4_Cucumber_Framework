package hellocucumber;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	public static WebDriver driver;
	public static String testType;
	private static final String UI_TEST = "UI_TEST";
	private static final String API_TEST = "API_TEST";
	private static final String UNDEFINED = "UNDEFINED";

	@Before
	public void setupTest(Scenario scenario) throws Exception {
		determineTestType(scenario);
		switch (testType) {
		case UI_TEST:
			System.setProperty("webdriver.chrome.driver",
					"/Users/Muradil/Batch4_Cucumber_Framework/src/test/resources/hellocucumber/chromedriver");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			break;
		case API_TEST:
			System.out.println("Do some setup that's needed for API tests!");
			break;
		case UNDEFINED:
			String errorMessage = "Please specific a test type in scenario name.\nFor example: \"Scenario: [UI] your scenario name here...\"";
			throw new Exception(errorMessage);
		}
	}

	private void determineTestType(Scenario scenario) {
		String scenarioName = scenario.getName();
		if (scenarioName.contains("[UI]")) {
			testType = UI_TEST;
		} else if (scenarioName.contains("[API]")) {
			testType = API_TEST;
		} else {
			testType = UNDEFINED;
		}
	}

	@After
	public void cleanupTest() throws Exception {
		switch (testType) {
		case UI_TEST:
			driver.close();
			driver.quit();
			break;
		case API_TEST:
			System.out.println("Cleaning up after API tests!");
			break;
		}
	}

}
