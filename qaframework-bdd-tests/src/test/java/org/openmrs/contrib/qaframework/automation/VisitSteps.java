package org.openmrs.contrib.qaframework.automation;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.ClinicianFacingPatientDashboardPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
// import io.cucumber.java.en.Then;

public class VisitSteps extends Steps {
    
    private TestData.PatientInfo patient;
    private ClinicianFacingPatientDashboardPage dashboardPage;

    @Before(RunTest.HOOK.SELENIUM_VISIT)
    public void visitPatientDashboardPage() {
        patient = createTestPatient();
        initiateWithLogin();
        new TestData.TestVisit(patient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
        findPatientPage = homePage.goToFindPatientRecord();
        findPatientPage.enterPatient(patient.identifier);
        dashboardPage = findPatientPage.clickOnFirstPatient();  
    }

    @After
    public void tearDown() throws Exception {
        quit();
        deletePatient(patient);
    }

   @Given("User clicks on start visit")
   public void userClicksOnStartVisit(){
         dashboardPage.startVisit();
   }

   @And("the system loads patient visits dashboardPage")
   public void systemLoadsPatientVisitsDashboardPage() {
      dashboardPage.waitForPage();
   }

   @And("user click on recent visit")
   public void clickOnEndVisit(){
       visitsDashboardPage= dashboardPage.goToRecentVisits();
   }

   @And("user clicks on end visit")
   public void systemGoesBackToHomePage() {
       visitsDashboardPage.endVisit();
   }
}
