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
import org.openmrs.contrib.qaframework.helper.InitialSetupPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PostgresInstallSteps extends InitialSetupSteps {
	
	@Before(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and " + RunTest.HOOK.POSTGRES_INSTALL)
	public void init() {
		initialSetup();
	}

	@After(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and " + RunTest.HOOK.POSTGRES_INSTALL)
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
