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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openqa.selenium.By;

public class UpgradeSteps extends InitialSetupSteps {

	private static final String HEADER_TEXT = "server is currently in maintenance mode";
	private static final String SUPER_USER = "System Developer";
	private static final String SETUP_PAGE_URL = "initialsetup";
	
	@Before(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and " + RunTest.HOOK.UPGRADE)
	public void init() {
		initialSetup();
	}

	@After(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and " + RunTest.HOOK.UPGRADE)
	public void finish() {
		complete();
	}

	@Given("User is on login page")
	public void visitLoginPage() {	
		assertTrue(textExists(HEADER_TEXT));
		assertTrue(textExists(SUPER_USER));
		assertNotNull(getElement(By.cssSelector("div.bar")));
		assertTrue(SETUP_PAGE_URL, driver.getCurrentUrl().contains(SETUP_PAGE_URL));
	}

	@And("User enters credentials")
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
