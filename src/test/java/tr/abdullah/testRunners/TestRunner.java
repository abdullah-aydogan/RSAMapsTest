package tr.abdullah.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/tr/abdullah/features",
        plugin = "json:target/jsonReports/cucumber-report.json",
        glue = {"tr/abdullah/stepDefinitions"}
)
public class TestRunner {


}