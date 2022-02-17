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

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.ActiveVisitsPage;
import org.openmrs.contrib.qaframework.page.RegistrationEditSectionPage;
public class XssOnPhoneNumberFieldSteps  extends Steps {
    private TestData.PatientInfo patient;
    private ActiveVisitsPage activeVisitsPage;
    private RegistrationEditSectionPage registrationEditSectionPage;

    @Before(RunTest.HOOK.SELENIUM_XSS_VULNERABILITY)
    public void setUp() throws Exception {
        initiateWithLogin();
        patient = createTestPatient();
        new TestData.TestVisit(patient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
    }

    @After(RunTest.HOOK.SELENIUM_XSS_VULNERABILITY)
    public void tearDown() throws Exception {
        deletePatient(patient);
        quit();
    }  
    
    @Given("user goes to active visit search page")
    public void userGoesToActiveVisitSearch() {
        activeVisitsPage = homePage.goToActiveVisitsSearch();
    }

    @When("user searches for patient using patient identifier")
    public void searchForPatientUsingPatientIdentifier() {
        activeVisitsPage.search(patient.identifier);
    }

    @Then("system loads  patient dashboard page of last active visit")
    public void systemGoesToPatientDashboardOfLastActiveVisit() {
        dashboardPage = activeVisitsPage.goToPatientDashboardOfLastActiveVisit();
    }
    
    @And("user clicks on show contact link")
    public void clickOnShowContact() {
        dashboardPage.clickOnShowContact();
    }

    @And("user clicks on edit contact info")
    public void clickOnEditContact() {
        registrationEditSectionPage = dashboardPage.clickOnEditContact();
    }

    @And("user clicks on phone number edit")
    public void userClicksOnPhoneNumberEdit(){
        registrationEditSectionPage.clickOnPhoneNumberEdit();
    }

    @And("user clears phone number")
    public void userClearsPhoneNumber() {
        registrationEditSectionPage.clearPhoneNumber();
    }

    @And("user enters new phone number with xss check script")
    public void userEntersPhoneNumber() {
        registrationEditSectionPage.enterPhoneNumber("<script>alert(0)</script>");
    }

    @And("user clicks on confirm edit button")
    public void userClicksOnComfirmEditButton() {
        registrationEditSectionPage.clickOnConfirmEdit();
    }

    @Then("system returns a message validator")
    public void checkXssVulnerabilityValidator() {
        assertTrue(registrationEditSectionPage.getInvalidPhoneNumberNotification().contains(("Must be a valid phone number (with +, -, numbers or parentheses)")));
    }

    @And("user clears the phone number input area")
    public void userClearPhoneNumber() {
        registrationEditSectionPage.clearPhoneNumber();
    }

    @And("user enters the collect phone number")
    public void userReEntersCorrectPhonrNumber() {
        registrationEditSectionPage.enterPhoneNumber("111111111");
    }

    @And("user clicks again on confirm edit button")
    public void userReClicksOnConfirmsEditButton() {
        registrationEditSectionPage.clickOnConfirmEdit();
    }

    @And("user clicks on confirm patient")
    public void userClicksOnConfirmPatient() throws Exception {
        dashboardPage = registrationEditSectionPage.confirmPatient();
    }

    @And("user clicks on show contact")
    public void userClicksOnShowContact() {
        dashboardPage.clickOnShowContact();
    }

    @Then("user validate the collect number to be entered into the input area")
    public void systemChecksTheCollectPhoneNumber() {
        assertTrue(dashboardPage.getTelephoneNumber().contains("111111111"));
    }
}
