package org.openmrs.contrib.qaframework.automation;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.EditPatientRelationshipPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.RegistrationSummaryPage;
import org.openqa.selenium.By;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class EditPatientRelationshipSteps extends Steps {
	private static final By NEXT_BUTTON = By.id("next-button");
	private HomePage homePage;
	private EditPatientRelationshipPage editPatientRelationshipPage;
	private ClinicianFacingPatientDashboardPage patientDashboardPage;
	private RegistrationSummaryPage registrationSummaryPage;

	private String patientDashboardId;
	private ClinicianFacingPatientDashboardPage clinicianFacingPatientDashboardPage;

	@Before(RunTest.HOOK.EDITPATIENTRELATIONSHIP)
	public void setUp() throws Exception {
		initiateWithLogin();

	}

	@After(RunTest.HOOK.EDITPATIENTRELATIONSHIP)
	public void destroy() {
		quit();
	}

	@Given("System clicks on registration app")
	public void clickOnRegistrationDefaultApp() throws InterruptedException {
		goToRegistrationApp();
	}

	@And("User enters patient details for Origin Ashaba")
	public void UserEnterPatientDetails() throws InterruptedException {
		enterPatientDetails();
	}

	//
	// @And("system returns a patients")
	// public void sytemReturnPatientResults() {
	// returnResults();
	// }
	//
	// @Then("User clicks on first patient searched")
	// public void clickOnFirstPatientSearched() {
	// dashboardPage = findPatientPage.clickOnFirstPatient();
	// }
	//
	// @And("System loads patient dashboardPage")
	// public void loadClinicianFacingPatientDashboardPage() throws Exception {
	// matchPatientIds(patientDashboardId);
	// }
	@Then("User clicks on RegistrationSummary")
	public void clickOnRegistrationSummary() {
		patientDashboardPage.goToRegistrationSummary().waitForPage();
	}

	@And("User click on editPatientRelationshipPage")
	public void clickeditPatientRelationshipPage() {
		registrationSummaryPage.goToEditPatientRelationship();
	}

	@And("User clicks   on  SelectRelationshipType")
	public void findRelationshipType() throws InterruptedException {
		editPatientRelationshipPage.clickOnSelectRelationshipType();
	}

	@Then("system loads back to patientDashboard")
	public void loadPatientDashboard() {
		homePage.go();
		matchPatientIds(firstPatientIdentifier);
	}
}
