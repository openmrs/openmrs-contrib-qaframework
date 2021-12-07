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

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.InitialSetupPage;

public class AdvancedInstallSteps extends InitialSetupSteps {

	@Before(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and " + RunTest.HOOK.ADVANCED_INSTALL)
	public void init() {
		initialSetup();
	}

	@After(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and " + RunTest.HOOK.ADVANCED_INSTALL)
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
