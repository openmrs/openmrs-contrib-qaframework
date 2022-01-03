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

import java.util.logging.Logger;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.InitialSetupPage;

public class SimpleInstallSteps extends InitialSetupSteps {
	Logger log = Logger.getLogger(SimpleInstallSteps.class.getName());

	@Before(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and " + RunTest.HOOK.SIMPLE_INSTALL)
	public void init() {
		initialSetup();
	}

	@After(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and " + RunTest.HOOK.SIMPLE_INSTALL)
	public void finish() {
		complete();
	}

	@Given("User selects Language on step one of Simple Installation")
	public void selectLanguage() {
		log.info(".... simple installation.......");
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
