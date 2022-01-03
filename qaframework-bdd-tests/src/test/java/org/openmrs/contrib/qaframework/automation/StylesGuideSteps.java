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

import org.junit.Assert;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.StylesGuidePage;
import org.openmrs.contrib.qaframework.page.SystemAdministrationPage;
import org.openqa.selenium.By;

public class StylesGuideSteps extends Steps {
	Logger log = Logger.getLogger(StylesGuidePage.class.getName());

	private SystemAdministrationPage systemAdministrationPage;
	private StylesGuidePage stylesGuidePage;

	@Before(RunTest.HOOK.SELENIUM_LOGIN)
	public void systemLogin() {
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_LOGIN)
	public void destroy() {
		quit();
	}

	@Given("user navigates to the systems admin page")
	public void navigateToSystemsPage() {
		System.out.println(".... styles Guide.......");
		systemAdministrationPage = homePage.goToSystemAdministrationPage();
	}

	@And("user presses the styles guide link")
	public void loadStylesGuide() {
		stylesGuidePage = (StylesGuidePage) systemAdministrationPage.clickOnStylesGuideAppLink().waitForPage();
	}

	@Then("system should load the styles guide page")
	public void validatePage() {
		Assert.assertNotNull(getElement(StylesGuidePage.STYLES_GUIDE_HEADER));
	}

	@When("user clicks back")
	public void back() {
		stylesGuidePage.pressBack();
	}

	@Then("system should return to the previous page")
	public void validateReturn() {
		Assert.assertNotNull(By.className("icon-magic"));
	}
}
