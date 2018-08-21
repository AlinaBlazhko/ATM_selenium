package ATM10_task;

import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by X240 on 8/21/2018.
 */
@RunWith(Cucumber.class)
//@CucumberOptions(format = {"pretty", "html:target/cucumber"})
@CucumberOptions(
        strict = true,
//        features="src\\test\\resources\\features\\email.feature",    // if runner does not see the features
        plugin = {
                "pretty", "json:target/Cucumber.json",
                "html:target/cucumber-html-report"
        }
)
public class TestRunner { }

