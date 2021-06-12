package org.openmrs.contrib.qaframework.automation;

import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.EditPatientRelationshipPage;
import org.openmrs.reference.page.FindPatientPage;
import org.openmrs.reference.page.HeaderPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.RegistrationSummaryPage;
import org.openqa.selenium.By;
import org.openmrs.contrib.qaframework.RunTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EditPatientRelationshipSteps extends Steps {
	private static final By NEXT_BUTTON = By.id("next-button");
	private HomePage homePage;
	private HeaderPage headerPage;
	private EditPatientRelationshipPage editPatientRelationshipPage;
	private ClinicianFacingPatientDashboardPage patientDashboardPage;
	private RegistrationSummaryPage registrationSummaryPage;

	private String patientDashboardId;
	private ClinicianFacingPatientDashboardPage clinicianFacingPatientDashboardPage;

	@Before(RunTest.HOOK.EDITPATIENTRELATIONSHIP)
	public void setUp() throws Exception {
		homePage = new HomePage(page);
		assertPage(homePage.waitForPage());
		headerPage = new HeaderPage(driver);
		// editPatientRelationshipPage = new EditPatientRelationshipPage(page);
		registrationSummaryPage = new RegistrationSummaryPage(page);
		patientDashboardPage = new ClinicianFacingPatientDashboardPage(page);
	}

	@After(RunTest.HOOK.EDITPATIENTRELATIONSHIP)
	public void destroy() {
		quit();
	}

	@Given("User clicks on find Patient record")
	public void clickOnFindPatientRecord() {
		homePage.goToFindPatientRecord().clickOnFirstPatient();
	}

	@And("System loads clinicianFacingPatientDashboardPage")
	public void loadClinicianFacingPatientDashboardPage() throws Exception {
		matchPatientIds(patientDashboardId);
	}
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
