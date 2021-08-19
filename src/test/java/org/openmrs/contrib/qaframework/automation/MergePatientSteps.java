package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.DataManagementPage;
import org.openmrs.reference.page.MergePatientsPage;
import org.openmrs.reference.page.PatientVisitsDashboardPage;
import org.openmrs.uitestframework.test.TestData;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class MergePatientSteps extends Steps {
	private DataManagementPage dataManagementPage;
	private MergePatientsPage mergePatientsPage;
	private PatientVisitsDashboardPage dashboardPage;
	TestData.PatientInfo testPatient;
	TestData.PatientInfo testPatient1;

	@Before(RunTest.HOOK.SELENIUM_DATA_MANAGEMENT)
	public void setup() {
		initiateWithLogin();
		testPatient = createTestPatient();
		testPatient1 = createTestPatient();
	}

	@Given("User click data management from home Page")
	public void goToDataManagement() {
		dataManagementPage = homePage.goToDataManagement();
		mergePatientsPage = dataManagementPage.goToMergePatient();
	}

	@And("The system loads data management page")
	public void loadDataManagementPage() {
		assertTrue(textExists("Merge Patient Electronic Records"));
	}

	@And("User enter patient1 using patient identifier")
	public void enterPatient1() {
		mergePatientsPage.enterPatient1(testPatient.identifier);
	}

	@Then("User enters patient2 using patient identifier")
	public void enterPatient2() {
		mergePatientsPage.enterPatient2(testPatient1.identifier);
	}

	@And("User clicks on  continue")
	public void clickOnContinueButton() {
		mergePatientsPage.clickOnContinue();
	}

	@And("User clicks on mergePatients")
	public void clickOnMergePatient() {
		mergePatientsPage.clickOnMergePatient();
		mergePatientsPage.waitForPage();
	}

	@Then("User clicks on continue from dashbaord")
	public void clickOnContinue() {
		mergePatientsPage.clickOnContinue();
	}

	@After(RunTest.HOOK.SELENIUM_DATA_MANAGEMENT)
	public void destroy() {
		deletePatient(testPatient);
		quit();
	}
}
