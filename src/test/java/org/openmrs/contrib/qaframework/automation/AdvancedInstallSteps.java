package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.uitestframework.page.InitialSetupPage;

public class AdvancedInstallSteps extends InitialSetupSteps {
	@Before(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and "
			+ RunTest.HOOK.ADVANCED_INSTALL)
	public void init() {
		initialSetup();
	}

	@After(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and "
			+ RunTest.HOOK.ADVANCED_INSTALL)
	public void finish() {
		complete();
	}

	@Given("User selects Language on step one of Advanced Installation")
	public void selectLanguage() {
		initialSetupPage.installationSelectLanguage();
	}

	@And("User selects Advanced Installation Type")
	public void advancedInstallationType() {
		initialSetupPage.selectInstallationType(InitialSetupPage.Type.ADVANCED);
	}

	@When("User enters all Advanced Installation steps")
	public void advancedInstall() {
		initialSetupPage.install(InitialSetupPage.Type.ADVANCED);
	}

	@Then("System should run Advanced installation")
	public void waitToFinish() {
		waitForSetupCompletion();
	}
}
