package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.AdmitToInpatientPage;
import org.openmrs.reference.page.FindPatientPage;
import org.openmrs.reference.page.TransferToWardServicePage;
import org.openmrs.uitestframework.test.TestData;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InternalPatientTransferSteps extends Steps {

	private AdmitToInpatientPage admitToInpatientPage;
	private TransferToWardServicePage transferToWardServicePage;
	private TestData.PatientInfo testPatient;

	@Before(RunTest.HOOK.SELENIUM_INTERNAL_TRANSFER)
	public void visitPatientDashboard() {
		testPatient = createTestPatient();
		initiateWithLogin();
		findPatientPage = (FindPatientPage) homePage.goToFindPatientRecord()
				.waitForPage();
		findPatientPage.enterPatient(testPatient.identifier);
		findPatientPage.waitForPageToLoad();
		dashboardPage = findPatientPage.clickOnFirstPatient();
		dashboardPage.startVisit().waitForPage();
	}

	@After(RunTest.HOOK.SELENIUM_INTERNAL_TRANSFER)
	public void destroy() {
		deletePatient(testPatient);
		quit();
	}

	@Given("a user clicks on Admit to Inpatient link from Patient dashboard")
	public void loadAdmissionPage() {
		admitToInpatientPage = (AdmitToInpatientPage) dashboardPage
				.goToAdmitToInpatient().waitForPage();
	}

	@Then("the system loads Admission page")
	public void systemloadsAdmissionPage() {
		assertTrue(textExists("Admission (Simple)"));
	}

	@When("a user clicks on save button after selecting location")
	public void savePatientDataWithLocation() {
		admitToInpatientPage.confirm("Inpatient Ward");
		admitToInpatientPage.waitForPage();
	}

	@Then("the system adds the patient data to admission table")
	public void systemAddsPatientData() {
		assertTrue(textExists("Encounters"));
	}

	@And("a user clicks on Transfer To Ward Service link from Patient dashboard")
	public void loadTransferToWordOrServicePage() {
		transferToWardServicePage = (TransferToWardServicePage) dashboardPage
				.clickOnTranfer().waitForPage();
	}

	@Then("the system loads Transfer to Ward Service Page")
	public void systemloadsWardServicePage() {
		assertTrue(textExists("Transfer Within Hospital (Simple)"));
	}

	@When("a user clicks on save button after choosing location")
	public void savePatientTransferData() {
		transferToWardServicePage.confirm("Isolation Ward");
		transferToWardServicePage.waitForPage();
	}

	@Then("the system saves the patient transfer data")
	public void systemSavesTransferData() {
		assertTrue(textExists("Encounters"));
	}
}
