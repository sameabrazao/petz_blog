package petz;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "feature", glue = { "" }, monochrome = true, dryRun = false)
public class RunTest{

}
