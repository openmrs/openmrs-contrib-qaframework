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
		"html:target/index.html", "message:target/cucumber.ndjson"}, monochrome = true)
public class RunTest {

	public class HOOK {
		public static final String SELENIUM = "@selenium";
		public static final String LOGIN = "@login";
		public static final String DASHBOARD = "@dashboard";
		public static final String INITIAL_SETUP = "@initialSetup";
		public static final String SIMPLE_INSTALL = "@simpleInstall";
		public static final String ADVANCED_INSTALL = "@advancedInstall";
		public static final String TESTING_INSTALL = "@testingInstall";
		public static final String EDITPATIENTRELATIONSHIP = "@editPatientRelationship";
		public static final String UPGRADE = "@upgrade";
		public static final String SELENIUM_EDITPATIENTRELATIONSHIP = SELENIUM
				+ " and " + EDITPATIENTRELATIONSHIP;
		public static final String SELENIUM_LOGIN = SELENIUM + " and " + LOGIN;
		public static final String SELENIUM_DASHBOARD = SELENIUM + " and "
				+ DASHBOARD;
		public static final String SELENIUM_INITIAL_SETUP = SELENIUM + " and "
				+ INITIAL_SETUP;
	}
}
