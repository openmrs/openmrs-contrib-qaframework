package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertNotNull;
// import static org.hamcrest.Matchers.not;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.ClinicianFacingPatientDashboardPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class VisitSteps extends Steps {
    
    private TestData.PatientInfo patient;
    private ClinicianFacingPatientDashboardPage dashboardPage;

    @Before(RunTest.HOOK.SELENIUM_VISIT)
    public void visitPatientDashboardPage() {
        patient = createTestPatient();
        initiateWithLogin();
        new TestData.TestVisit(patient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
        new TestData.TestVisit(patient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
    }

    @After
    public void tearDown() throws Exception {
        quit();
        // deletePatient(patient);
    }

   @Given("User click on first Patient")
   public void userClicksOnFirstPatient() {
       findPatientPage = homePage.goToFindPatientRecord();
       findPatientPage.enterPatient(patient.identifier);
       dashboardPage = findPatientPage.clickOnFirstPatient();   
   }
   
   @Then("User clicks on start visit")
   public void userClicksOnStartVisit(){
       visitsDashboardPage = dashboardPage.startVisit();
   }

   @And("the system loads patient visits dashboardPage")
   public void systemLoadsPatientVisitsDashboardPage() {
       assertNotNull(visitsDashboardPage.getActiveVisit());
   }

   @And("user goes back to the dashboardPage")
   public void systemGoesBackToHomePage() {
       dashboardPage.go();
   }

//    @Then("user clicks on past visit")
//    public void userClickOnAddPastVisit(){
//        visitsDashboardPage = dashboardPage.addPastVisit();
//    }

//    @And("system loads patient dashboard page")
//    public void userloadsPatientDashboardPage(){
//     //    assertThat(visitsDashboardPage.getVisitList().get(0).getAttribute("class"), is(not("no-results")));
//    }
   
}
