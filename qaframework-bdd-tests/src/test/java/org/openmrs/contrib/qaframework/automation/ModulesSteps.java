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

import static org.junit.Assert.assertTrue;

import java.util.List;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.ModulesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ModulesSteps extends Steps {

    private AdministrationPage administrationPage;
    private ModulesPage modulesPage;

    @Before(RunTest.HOOK.SELENIUM_MODULES)
    public void visitHomePage() {
    	initiateWithLogin();
    }

    @After(RunTest.HOOK.SELENIUM_MODULES)
    public void tearDown() {
    	quit();
    }

    @Given("a user clicks on administration app")
    public void loadSystemAdministrationPage() {
    	administrationPage = homePage.goToAdministration();
    }

    @Then ("the system loads administration page")
    public void systemLoadsAdministrationPage() {
    	assertTrue(textExists("Administration"));
    }
 
    @When ("a user clicks on the modules link")
    public void launchModulesPage() {
    	modulesPage = administrationPage.goToManageModulesPage();
    }

    @Then ("the system loads modules page")
    public void systemLoadsModulesPage() {
    	assertTrue(textExists("Modules"));
    }

    @And ("the user checks the list of started modules")
    public void checkListOfStartedModules() {
    	WebElement moduleListing = modulesPage.findElementById("moduleListing");
    	List<WebElement> firstColumn = moduleListing.findElements(By.cssSelector("#moduleListing table tbody td"));
    	for (WebElement eachModule : firstColumn) {
            Assert.assertFalse(eachModule.getText().contains("moduleNotStarted"));
        }	 
    }
}
