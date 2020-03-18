package cucumberRunners;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "html:target/htmlReports" }, 
		tags = { "@Login" }, 
		features = { "src/test/resources/featureFiles" }, 
		glue = {
				"ui_StepDefinitions", 
				"api_StepDefinitions",
				"cucumberHooks"
				},
		monochrome = true,
		dryRun = false
		)
public class DummyRunner {
}
