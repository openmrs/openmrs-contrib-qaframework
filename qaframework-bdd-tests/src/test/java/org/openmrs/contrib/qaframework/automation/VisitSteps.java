package org.openmrs.contrib.qaframework.automation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.MergeVisitsPage;

public class VisitSteps extends Steps { 
	
	private TestData.PatientInfo patient;
	private MergeVisitsPage mergeVisitsPage;

    @After(RunTest.HOOK.SELENIUM_VISIT)
    public void tearDown() {
    	deletePatient(patient);
    	quit();
    }
    
    @Given("user logins into the system")
    public void initiateLogin() {
    	initiateWithLogin();
    	patient = createTestPatient();
    }
    
    @When("a patient is initiated with two visits")
    public void setUpLoginWithVisits() {
    	new TestData.TestVisit(patient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
    	new TestData.TestVisit(patient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
    }
    
    @When("a patient is initiated with one visit")
    public void loginWithPatientVisit() {
    	new TestData.TestVisit(patient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
    }
     
    @And("user clicks on the find patient record app")
    public void clickOnFindPatientRecord() {
    	findPatientPage = homePage.goToFindPatientRecord();
    }
    
    @And("user searches for the patient record")
    public void searchForPatientRecord() {
    	findPatientPage.enterPatient(patient.identifier);
    	findPatientPage.waitForPageToLoad();
    }
    
    @And("user clicks on the first patient record")
    public void clickTheFirstPatientRecord() {
    	dashboardPage = findPatientPage.clickOnFirstPatient();
    }
    
    @And("user clicks on the start visit link")
    public void clickOnStartVisitLink() {
    	visitsDashboardPage = dashboardPage.startVisit();
    }
    
    @Then("the system starts the patient visit")
    public void systemStartsVisit() {
    	assertNotNull(visitsDashboardPage.getActiveVisit());
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
    public void clickActionsButton() {
    	visitsDashboardPage.clickOnActions();
    }
    
    @And("user clicks on the merge visits link")
    public void clickOnMergeVisitsLink() {
    	mergeVisitsPage = visitsDashboardPage.clickOnMergeVisits();
    }
    
    @And("user selects the available visits to be merged")
    public void selectFirstVisit() {
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
    	assertNull(visitsDashboardPage.getActiveVisit());
    }   
}
