/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.automation;

import org.openmrs.contrib.qaframework.RunTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
