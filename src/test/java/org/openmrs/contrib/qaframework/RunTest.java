package org.openmrs.contrib.qaframework;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = "org.openmrs.contrib.qaframework.automation",
        tags = {"@run"}
    )
public class RunTest {
}
