package cucumberRunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "html:target/htmlReports" }, 
		tags = { "@Smoke", "@UI" }, 
		features = { "src/test/resources/featureFiles" }, 
		glue = {
				"ui_StepDefinitions", 
				"api_StepDefinitions",
				"cucumberHooks"
				},
		monochrome = true
		)
public class UI_SmokeTestRunner {
}
