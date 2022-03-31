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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.ActiveVisitsPage;
import org.openmrs.contrib.qaframework.page.MergeVisitsPage;

public class VisitSteps extends Steps { 
	
    private TestData.PatientInfo patient;
    private ActiveVisitsPage activeVisitsPage;
    private MergeVisitsPage mergeVisitsPage;

    @After(RunTest.HOOK.SELENIUM_PATIENT_VISIT)
    public void tearDown() {
    	deletePatient(patient);
    	quit();
    }
    
    @Given("user logins into the system with no initiated patient visits")
    public void initiateLogin() {
    	initiateWithLogin();
    	patient = createTestPatient();
    }
    
    @Given("user initiates login with patient visits")
    public void setUpLoginWithVisits() {
    	initiateLogin();
    	new TestData.TestVisit(patient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
    	new TestData.TestVisit(patient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
    }
     
    @When("user clicks on the find patient record app")
    public void clickOnFindPatientRecordApp() {
    	findPatientPage = homePage.goToFindPatientRecord();
    }
    
    @And("user searches for the patient record")
    public void searchForPatientRecord() {
    	findPatientPage.enterPatient(patient.identifier);
        findPatientPage.waitForPageToLoad();
    }
    
    @And("user clicks on the patient returned in the patient table")
    public void clickOnTheReturnedPatient() {
    	dashboardPage = findPatientPage.clickOnFirstPatient();
    }

    @Then("the system loads the clinician facing patient dashboard")
    public void systemLoadsClinicianFacingPatientDashboard() {
    	assertPage(dashboardPage.waitForPage());
    }
 
    @And("user clicks on the start visit link")
    public void clickOnStartVisitLink() {
    	visitsDashboardPage = dashboardPage.startVisit();
    }
    
    @Then("the system starts the patient visit")
    public void systemStartsVisit() {
    	assertTrue(textExists("Visits"));
    }
    
    @And("user clicks on the add past visit link")
    public void clickOnAddPastVisitLink() {
    	visitsDashboardPage = dashboardPage.addPastVisit();
    }
    
    @Then("the system adds the patient past visit")
    public void systemAddsPastVisit() {
    	assertThat(visitsDashboardPage.getVisitList().get(0).getAttribute("class"), is(not("no-results")));
    }
    
    @And("user clicks on the recent visits link in the recent visits section")
    public void clickOnRecentVisitsLink() {
    	visitsDashboardPage = dashboardPage.goToRecentVisits();
    	visitsDashboardPage.waitForPage();
    }
    
    @And("user clicks on the actions button")
    public void clickOnTheActionsButton() {
    	visitsDashboardPage.clickOnActions();
    }
    
    @And("user clicks on the merge visits link")
    public void clickOnMergeVisitsLink() {
    	mergeVisitsPage = visitsDashboardPage.clickOnMergeVisits();
    }
    
    @And("user selects the available visits to be merged")
    public void selectVisitsToBeMerged() {
    	mergeVisitsPage.checkFirstVisit();
    	mergeVisitsPage.checkSecondVisit();
    }
    
    @And("user clicks on the merge selected visits button")
    public void clickOnMergeVisitsButton() {
    	mergeVisitsPage.clickOnMergeSelecetdVisits();
    }
    
    @Then("the system merges the visits successfully")
    public void systemMergesVisitsSuccessfully() {
    	assertThat(mergeVisitsPage.getAllVisit().size(), is(1));
    }
      
    @And("user clicks on the end visit button")
    public void clickOnTheEndVisitButton() {
    	visitsDashboardPage.endVisit();
    	visitsDashboardPage.waitForPageToLoad();
    }
    
    @Then("the system ends the visit")
    public void systemEndsVisit() {
    	assertTrue(textExists("No active visit"));
    }

    @When("user clicks on the Active Visits app from the home page")
    public void loadActiveVisitsPage() {
    	activeVisitsPage = homePage.goToActiveVisitsSearch();
    }

    @And("user searches for the patient with active visit")
    public void searchForPatientWithActiveVisit() {
    	activeVisitsPage.search(patient.identifier);
    	activeVisitsPage.waitForPageToLoad();
    }

    @And("user clicks on the patient returned with active visit")
    public void loadPatientDashboardPage() {
    	dashboardPage = activeVisitsPage.goToPatientDashboardOfLastActiveVisit();
    }
}
