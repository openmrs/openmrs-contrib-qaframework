package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.InitialSetupPage;

public class PostgresInstallSteps extends InitialSetupSteps {
	@Before(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and "
			+ RunTest.HOOK.POSTGRES_INSTALL)
	public void init() {
		initialSetup();
	}

	@After(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and "
			+ RunTest.HOOK.POSTGRES_INSTALL)
	public void finish() {
		complete();
	}

	@Given("User selects Language on step one of postgres Installation")
	public void selectLanguage() {
		initialSetupPage.installationSelectLanguage();
	}

	@And("User selects Advanced Installation Type on postgres")
	public void advancedInstallationType() {
		initialSetupPage.selectInstallationType(InitialSetupPage.Type.ADVANCED);
	}

	@When("User enters all postgres Installation steps")
	public void postgresInstall() {
		initialSetupPage.install(InitialSetupPage.Type.POSTGRES);
	}

	@Then("System should run postgres installation")
	public void waitToFinish() {
		waitForSetupCompletion();
	}
}
