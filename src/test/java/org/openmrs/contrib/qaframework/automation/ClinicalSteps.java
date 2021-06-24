package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.FindPatientPage;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.VisitNotePage;
import org.openmrs.uitestframework.test.TestData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClinicalSteps extends Steps {

	private VisitNotePage visitNotePage;
	private static final String DIAGNOSIS_PRIMARY = "Cancer";
	private static final String DIAGNOSIS_SECONDARY = "Malaria";
	private TestData.PatientInfo testPatient;

	@Before(RunTest.HOOK.SELENIUM_CLINICAL)
	public void visitHomePage() {
		testPatient = createTestPatient();
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_CLINICAL)
	public void destroy() {
		deletePatient(testPatient);
		quit();
	}

	@Given("a user clicks on search patient link from home page")
	public void launchPatientDashboardActiveVisists() {
		findPatientPage = homePage.goToFindPatientRecord();
	}

	@And("a user searches and starts the patient visit")
	public void searchActivePatient() {
		findPatientPage.enterPatient(testPatient.identifier);
		findPatientPage.waitForPageToLoad();
		dashboardPage = findPatientPage.clickOnFirstPatient();
		dashboardPage.startVisit().waitForPage();
	}

	@And("a user clicks visit note link from the patient dashboard")
	public void launchVisitNote() {
		visitNotePage = (VisitNotePage) dashboardPage.goToVisitNote();
	}

	@Then("the system loads visit note page")
	public void systemLoadsVisitNote() {
		assertTrue(textExists("Visit Note"));
	}

	@And("a user fills the visit note")
	public void fillVisitNote() {
		visitNotePage.selectProviderAndLocation();
		visitNotePage.addDiagnosis(DIAGNOSIS_PRIMARY);
		visitNotePage.addSecondaryDiagnosis(DIAGNOSIS_SECONDARY);
		visitNotePage.addNote("This is a note");
	}

	@And("a user clicks on save button")
	public void addVisitNote() {
		dashboardPage = visitNotePage.save();
	}

	@Then("the system adds the note into visit note table")
	public void systemAddsVisitNote() {
		assertEquals(DIAGNOSIS_PRIMARY, visitNotePage.primaryDiagnosis());
		assertEquals(DIAGNOSIS_SECONDARY, visitNotePage.secondaryDiagnosis());
	}
}
