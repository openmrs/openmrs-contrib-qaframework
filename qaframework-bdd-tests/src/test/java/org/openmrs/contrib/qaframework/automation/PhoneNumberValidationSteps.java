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
import org.openmrs.contrib.qaframework.page.RegistrationEditSectionPage;

public class PhoneNumberValidationSteps  extends Steps {
    
    private TestData.PatientInfo patient;
    private RegistrationEditSectionPage registrationEditSectionPage;

    @Before(RunTest.HOOK.SELENIUM_PHONE_NUMBER_VALIDATION)
    public void setUp() throws Exception {
        initiateWithLogin();
        patient = createTestPatient();
        new TestData.TestVisit(patient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
    }

    @After(RunTest.HOOK.SELENIUM_PHONE_NUMBER_VALIDATION)
    public void tearDown() throws Exception {
        deletePatient(patient);
        quit();
    }  
    
    @Given("user goes to find patient record app")
    public void userGoesToFindPatientApp() {
        findPatientPage = homePage.goToFindPatientRecord();
    }

    @When("user searches for patient using patient identifier")
    public void searchForPatientUsingPatientIdentifier() {
        findPatientPage.enterPatient(patient.identifier);
    	findPatientPage.waitForPageToLoad();
    }

    @And("user clicks on the first patient")
    public void userClicksOnFirstPatient() {
        dashboardPage = findPatientPage.clickOnFirstPatient();
    }

    @Then("system loads patient dashboard page")
    public void loadDashboardPage() {
        assertPage(dashboardPage.waitForPage());
    }
    
    @And("user clicks on show contact link")
    public void userClicksOnShowContact() {
        dashboardPage.clickOnShowContact();
    }

    @And("user clicks on edit contact info link")
    public void userClickOnEditContact() {
        registrationEditSectionPage = dashboardPage.clickOnEditContact();
    }

    @And("user clicks on phone number tab")
    public void userClicksOnPhoneNumber() {
        registrationEditSectionPage.clickOnPhoneNumberEdit();
    }

    @And("user clears phone number")
    public void userClearsPhoneNumber() {
        registrationEditSectionPage.clearPhoneNumber();
    }

    @And("user enters an invalid phone number")
    public void userEntersInvalidPhoneNumber() {
        registrationEditSectionPage.enterPhoneNumber("abcdefg1");
    }

    @And("user clicks on confirm edit button")
    public void userClicksOnConfirmEditButton() {
        registrationEditSectionPage.clickOnConfirmEdit();
    }

    @Then("system returns a message validator")
    public void validatePhoneNumber() {
        assertTrue(registrationEditSectionPage.getInvalidPhoneNumberNotification().contains(("Must be a valid phone number (with +, -, numbers or parentheses)")));
    }

    @And("user clears the phone number input area")
    public void userClearsThePhoneNumber() {
        registrationEditSectionPage.clearPhoneNumber();
    }

    @And("user enters the correct phone number")
    public void userEntersCorrectPhoneNumber() {
        registrationEditSectionPage.enterPhoneNumber("111111111");
    }

    @And("user clicks again on confirm edit button")
    public void clicksOnConfirmEditButton() {
        registrationEditSectionPage.clickOnConfirmEdit();
    }

    @And("user clicks on confirm patient button")
    public void userClicksOnConfirmPatient() throws Exception {
        dashboardPage = registrationEditSectionPage.confirmPatient();
    }

    @And("user clicks again on show contact link")
    public void userClicksAgainOnShowContact() {
        dashboardPage.clickOnShowContact();
    }

    @Then("system validates the correct phone number to be entered into the input area")
    public void systemChecksTheCorrectPhoneNumber() {
        assertTrue(dashboardPage.getTelephoneNumber().contains("111111111"));
    }
}
