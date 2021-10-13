package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class InpatientSteps extends Steps {

	private TestData.PatientInfo testPatient;

	@Before(RunTest.HOOK.SELENIUM_ENCOUNTER)
	public void visitHomePage() {
		testPatient = createTestPatient();
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_ENCOUNTER)
	public void destroy() {
		deletePatient(testPatient);
		quit();
	}

	@Given("a user clicks on Find Patient app from Home page")
	public void goToFindPatientRecordPage() {
		findPatientPage = homePage.goToFindPatientRecord();
	}

	@Then("the system loads find patient record page")
	public void systemLoadsFindPatientRecordPage() {
		assertTrue(textExists("Find Patient Record"));
	}

	@When("a user searches an existing patient from find patient record page")
	public void searchExistingPatient() {
		findPatientPage.enterPatient(testPatient.identifier);
		findPatientPage.waitForPageToLoad();
		dashboardPage = findPatientPage.clickOnFirstPatient();
	}

	@And("a user starts visit from the patient dashboard page")
	public void clickOnStartVisit() {
		visitsDashboardPage = dashboardPage.startVisit();
	}

	@Then("the system loads patient visit dashboard page")
	public void systemLoadsPatientVisitDashboardPage() {
		assertTrue(visitsDashboardPage.containsText("Visits"));
	}

	@When("a user clicks on Admit to Inpatient button and selects location")
	public void goToAdmitToInpatientPage() {
		visitsDashboardPage.goToAdmitToInpatient().confirm("Inpatient Ward");
	}

	@And("the system confirms admission encounter is made")
	public void confirmAdmissionEncounter() {
		assertTrue(visitsDashboardPage.containsText("Admission"));
		assertThat(visitsDashboardPage.getEncountersCount(), is(1));
	}

	@When("a user clicks on Trasfer to Ward service button and selects location")
	public void goToTransferToWardServicePage() {
		visitsDashboardPage.goToTransferToWardServicePage().confirm(
				"Isolation Ward");
	}

	@Then("the system confirms trasfer within hospital encounter is made")
	public void confirmTrasferEncounter() {
		assertTrue(visitsDashboardPage.containsText("Transfer Within Hospital"));
		assertThat(visitsDashboardPage.getEncountersCount(), is(2));
	}

	@When("a user clicks on Exit from Inpatient button and selects location")
	public void goToExitFromInpatientPage() {
		visitsDashboardPage.goToExitFromInpatient().confirm("Isolation Ward");
	}

	@Then("the system confirms discharge encounter is made")
	public void confirmDischargeEncounter() throws InterruptedException {
		assertTrue(visitsDashboardPage.containsText("Discharge"));
		assertThat(visitsDashboardPage.getEncountersCount(), is(3));
		visitsDashboardPage.waitForPageToLoad();
		goToPatientDashboard();
	}

	public void goToPatientDashboard() throws InterruptedException {
		dashboardPage = visitsDashboardPage.goToPatientDashboard();
		dashboardPage.waitForPage();
	}
}
