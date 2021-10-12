package org.openmrs.contrib.qaframework.automation;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.InitialSetupPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SimpleInstallSteps extends InitialSetupSteps {
	@Before(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and "
			+ RunTest.HOOK.SIMPLE_INSTALL)
	public void init() {
		initialSetup();
	}

	@After(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and "
			+ RunTest.HOOK.SIMPLE_INSTALL)
	public void finish() {
		complete();
	}

	@Given("User selects Language on step one of Simple Installation")
	public void selectLanguage() {
		initialSetupPage.installationSelectLanguage();
	}

	@And("User selects Simple Installation Type")
	public void simpleInstallationType() {
		initialSetupPage.selectInstallationType(InitialSetupPage.Type.SIMPLE);
	}

	@When("User enters all Simple Installation steps")
	public void simpleInstall() {
		initialSetupPage.install(InitialSetupPage.Type.SIMPLE);
	}

	@Then("System should run Simple installation")
	public void waitToFinish() {
		waitForSetupCompletion();
	}
}