package org.openmrs.contrib.qaframework;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
// defining ordered by name features folder
features = {"src/test/resources/features"},
// defining the definition steps package
glue = "org.openmrs.contrib.qaframework.automation", plugin = {
		"html:target/index.html", "message:target/cucumber.ndjson"}, monochrome = true,
// only scenarios tagged here are executed
tags = "@selenium")
public class RunTest {
}
