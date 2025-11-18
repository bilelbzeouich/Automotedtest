package stepdefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "stepdefinitions", plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json"
}, monochrome = true, tags = "@webForm or @webFormSimple or @runValidLoginAsAdmin or @runInvalidLogin or @ValidCredentials or @InvalidCredentials or @TestScreenshotFailure")
public class CucumberRunner {
}