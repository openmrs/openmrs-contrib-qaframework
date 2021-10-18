package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.uitestframework.page.InitialSetupPage;

public class TestingInstallSteps extends InitialSetupSteps {
	@Before(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and "
			+ RunTest.HOOK.TESTING_INSTALL)
	public void init() {
		initialSetup();
	}

	@After(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and "
			+ RunTest.HOOK.TESTING_INSTALL)
	public void finish() {
		complete();
	}

	@Given("User selects Language on step one of Testing Installation")
	public void selectLanguage() {
		initialSetupPage.installationSelectLanguage();
	}

	@And("User selects Testing Installation Type")
	public void testingInstallationType() {
		initialSetupPage.selectInstallationType(InitialSetupPage.Type.TESTING);
	}

	@When("User enters all Testing Installation steps")
	public void testingInstall() {
		initialSetupPage.install(InitialSetupPage.Type.TESTING);
	}

	@Then("System should run Testing installation")
	public void waitToFinish() {
		waitForSetupCompletion();
	}
}