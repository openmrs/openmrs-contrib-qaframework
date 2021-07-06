package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertNotNull;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.FindPatientPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.RegistrationPage;
import org.openmrs.uitestframework.test.TestData;
import org.openqa.selenium.By;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class searchAndRegistrationSteps extends Steps {
	private HomePage homePage;
	private RegistrationPage registrationPage;
	private ClinicianFacingPatientDashboardPage dashboardPage;
	private FindPatientPage findPatientPage;
	private String familyName = "Olora";
	private String givenName = "Job";
	private String gender = "Male";
	private String phoneNumber = "+21134567890";
	private TestData.PatientInfo testPatient;

	@Before(RunTest.HOOK.SELENIUM_SEARCH_REGISTRATION)
	public void setUp() throws Exception {
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_SEARCH_REGISTRATION)
	public void destroy() {
		quit();
	}

	@Given("a user clicks on Find Patient Record App")
	public void clickOnFindPatientRecord() throws InterruptedException {
		goToFindPatientRecordApp();
	}

	@And("a user searches Olora Job")
	public void searchPatientAsAndriaFaith() {
		searchForNewPatient();
	}

	@Then("The System returns no patient")
	public void noReturnPatient() {
		assertNotNull(getElement(By.className("dataTables_empty")));
	}

	@And("The system loads homePage")
	public void clickOnHomePage() throws InterruptedException {
		goToHomePage();
	}

	@And("System clicks on registration app")
	public void clickOnRegistrationDefaultApp() throws InterruptedException {
		goToRegistrationApp();
	}

	@Then("User enters patient details for Olora Job")
	public void UserEnterPatientDetails() throws InterruptedException {
		enterPatientDetails();
	}

	@And("system loads clinicianFacingPatientDashboard")
	public void loadClinicianFacingPatientDashbaord() {
		if (dashboardPage != null) {
			dashboardPage.waitForPage();
		}
	}

}
