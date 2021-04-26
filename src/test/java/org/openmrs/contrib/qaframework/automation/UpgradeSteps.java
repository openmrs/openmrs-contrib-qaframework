package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openmrs.contrib.qaframework.RunTest;

public class UpgradeSteps extends InitialSetupSteps {
	@Before(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and "
			+ RunTest.HOOK.UPGRADE)
	public void init() {
		initialSetup();
	}

	@After(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and " + RunTest.HOOK.UPGRADE)
	public void finish() {
		complete();
	}

	@Given("User enters credentials")
	public void enterCredentials() {
		initialSetupPage.enterUsernameAndPassword();
	}

	@When("User proceeds with Upgrade")
	public void submitUpgrade() {
		initialSetupPage.upgrade();
	}

	@Then("System should run upgrade")
	public void waitToFinish() {
		waitForSetupCompletion();
	}

}
