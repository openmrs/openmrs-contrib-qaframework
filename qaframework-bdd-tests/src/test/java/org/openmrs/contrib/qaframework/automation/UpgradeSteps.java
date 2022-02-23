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

import org.junit.Assert;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.InitialSetupPage;
import org.openqa.selenium.By;

public class UpgradeSteps extends InitialSetupSteps {

	private static final String HEADER_TEXT = "server is currently in maintenance mode";
	private static final String SUPER_USER = "System Developer";
	
	@Before(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and " + RunTest.HOOK.UPGRADE)
	public void init() {
		initialSetupPage = new InitialSetupPage(driver);
		initialSetupPage.go();
		initialSetupPage.waitForPage();
	}

	@After(RunTest.HOOK.SELENIUM_INITIAL_SETUP + " and " + RunTest.HOOK.UPGRADE)
	public void finish() {
		complete();
	}

	@Given("User is on login page")
	public void visitLoginPage() {	
		Assert.assertTrue(textExists(HEADER_TEXT));
		Assert.assertTrue(textExists(SUPER_USER));
		Assert.assertNotNull(getElement(By.cssSelector("div.bar")));
		Assert.assertTrue(SETUP_PAGE_URL, driver.getCurrentUrl().contains(SETUP_PAGE_URL));
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
