package org.openmrs.contrib.qaframework.automation;

import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.FindPatientPage;
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
	private FindPatientPage findpatientPage;
	private FindPatientSteps findPatientSteps;

	private String patientDashboardId;
	private ClinicianFacingPatientDashboardPage clinicianFacingPatientDashboardPage;

	@Before(RunTest.HOOK.SELENIUM_LOGIN)
	public void systemLogin() {
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_DASHBOARD)
	public void destroy() {
		quit();
	}

	@Given("System loads clinicianFacingPatientDashboardPage")
	public void loadClinicianFacingPatientDashboardPage() throws Exception {
		matchPatientIds(patientDashboardId);
	}

	@When("User clicks on editRegistrationInformation")
	public void clickEditRegistrationInformation() {
		// dashboardPage.goToRegistrationSummary().waitForPage();
	}

	@And("User clicks on RegistrationSummary")
	public void clickOnRegistrationSummary() {
		// RegistrationSummaryPage summary = new RegistrationSummaryPage(page);
		// summary.goToEditPatientRelationship();

	}

	@And("User clicks   on  SelectRelationshipType")
	public void findRelationshipType() throws InterruptedException {
		// editPatientRelationshipPage.clickOnSelectRelationshipType();

	}

	@Then("system loads back to patientDashboard")
	public void loadPatientDashboard() {
		matchPatientIds(firstPatientIdentifier);
	}

}
