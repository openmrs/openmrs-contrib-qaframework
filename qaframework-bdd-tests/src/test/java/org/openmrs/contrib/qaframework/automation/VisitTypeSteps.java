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

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.commons.lang3.RandomStringUtils;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.RestClient;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.VisitTypeListPage;
import org.openmrs.contrib.qaframework.page.VisitTypePage;

public class VisitTypeSteps extends Steps {

    private AdministrationPage administrationPage;
    private VisitTypePage visitTypePage;
    private VisitTypeListPage visitTypeListPage;
    private String visitTypeUuid;
    private final String VISIT_TYPE_NAME = RandomStringUtils.randomAlphabetic(5);
    private static final String VISIT_TYPE_DESCRIPTION = "Description for Visit Type test workflow";

    @Before(RunTest.HOOK.SELENIUM_VISIT_TYPE)
    public void setUp() {
        initiateWithLogin();
    }

    @After(RunTest.HOOK.SELENIUM_VISIT_TYPE)
    public void tearDown() {
        RestClient.delete("visittype/" + visitTypeUuid, true);
        quit();
    }

    @Given("a user clicks on the system administration link from the home page")
    public void launchSystemAdministrationPage() {
        administrationPage = homePage.goToAdministration();
    }

    @Then("the administration page gets loaded")
    public void systemLoadsAdministrationPage() {
        assertTrue(textExists("Administration"));
    }

    @And("a user clicks on the manage Visit Types link")
    public void launchManageVisitTypePage() {
        visitTypeListPage = administrationPage.goToVisitTypePage();
    }

    @Then("the manage visit types page is loaded")
    public void systemLoadsManageVisitTypePage() {
        assertTrue(textExists("Visit Type Management"));
    }

    @When("a user clicks on the add visit type link on the manage visit type page")
    public void clickOnAddVisitTypeLink() {
        visitTypePage = visitTypeListPage.addVisitType();
    }

    @Then("the system loads the visit type form page")
    public void systemLoadsVisitTypeFormPage() {
        assertTrue(textExists("Visit Type"));
    }

    @And("a user fills the add visit type form")
    public void fillAddVisitTypeForm() {
        visitTypePage.setName(VISIT_TYPE_NAME);
        visitTypePage.setDescription(VISIT_TYPE_DESCRIPTION);
    }

    @And ("a user saves visit type")
    public void saveVisitType() {
        visitTypePage.save();
    }

    @Then("a visit type is saved successfully")
    public void systemAddsVisitType() {
        assertTrue(textExists("Visit Type saved"));
    }

    @When("a user clicks on the already saved visit type link")
    public void clickAlreadySavedVisitType() {
        visitTypePage = visitTypeListPage.clickAlreadySavedVisitType();
    }

    @And("a user edits the visit type name")
    public void editVisitType() {
        visitTypePage.setName("edited Visit Type name");
        visitTypePage.save();
    }

    @And ("a user fills in the reason for retiring visit type")
    public void setRetireReason() {
        visitTypePage.setRetireReason("retire reason");
    }

    @And("a user clicks on retire visit type button")
    public void retireVisitType() {
        visitTypePage.retire();
    }

    @And("a user clicks on delete visit type button")
    public void deleteVisitType() {
        visitTypePage.delete();
    }

    @Then("a visit type is deleted successfully")
    public void systemDeletesAVisitType() {
        assertTrue(textExists("Visit Type deleted forever successfully"));
    }
}
