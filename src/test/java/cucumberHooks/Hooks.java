package cucumberHooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.DriverFactory;

public class Hooks {

	public static WebDriver driver;
	public static String testType;
	private static final String UI_TEST = "UI_TEST";
	private static final String API_TEST = "API_TEST";
	private static final String END_TO_END_TEST = "END_TO_END_TEST";
	private static final String UNDEFINED = "UNDEFINED";

	@Before
	public void setupTest(Scenario scenario) throws Exception {
		determineTestType(scenario);
		switch (testType) {
		case UI_TEST:
		case END_TO_END_TEST:
			driver = DriverFactory.getInstance();
			break;
		case API_TEST:
			break;
		case UNDEFINED:
			String errorMessage = "Please specific a test type in scenario name."
					+ "\nOptions: [UI], [API], [UI & API]"
					+ "\nFor example: \"Scenario: [UI] your scenario name here...\"";
			throw new Exception(errorMessage);
		}
	}

	private void determineTestType(Scenario scenario) {
		String scenarioName = scenario.getName();
		if (scenarioName.contains("[UI & API]")) {
			testType = END_TO_END_TEST;
		} else if (scenarioName.contains("[UI]")) {
			testType = UI_TEST;
		} else if (scenarioName.contains("[API]")) {
			testType = API_TEST;
		} else {
			testType = UNDEFINED;
		}
	}

	@After
	public void cleanupTest(Scenario scenario) throws Exception {
		switch (testType) {
		case UI_TEST:
		case END_TO_END_TEST:
			if (scenario.isFailed()) {
				TakesScreenshot tss = (TakesScreenshot) driver;
				byte[] screenshot = tss.getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png", "ScreenShotOnFailure");
			}
			DriverFactory.cleanUp();
			break;
		case API_TEST:
			break;
		}
	}

}
