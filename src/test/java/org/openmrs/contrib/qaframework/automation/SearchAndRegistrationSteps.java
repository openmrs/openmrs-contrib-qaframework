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


public class SearchAndRegistrationSteps extends Steps {
	private HomePage homePage;
	private RegistrationPage registrationPage;
	private ClinicianFacingPatientDashboardPage dashboardPage;
	private FindPatientPage findPatientPage;
	private String familyName = "Origin";
	private String givenName = "ashaba";
	private String gender = "Female";
	private String phoneNumber = "+21134567810";
	private TestData.PatientInfo testPatient;
	private static final String REASON = "patient nolonger needed";

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

	@And("a user searches origin ashaba")
	public void searchPatientAsOriginAshaba() {
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

	@Then("User enters patient details for Origin Ashaba")
	public void UserEnterPatientDetails() throws InterruptedException {
		enterPatientDetails();
	}

	@And("system loads clinicianFacingPatientDashboard")
	public void loadClinicianFacingPatientDashbaord() {
		if (dashboardPage != null) {
			dashboardPage.waitForPage();
			dashboardPage.deletePatient(REASON);
		}
	}

	@And("user click on delete patient")
	public void deletePatient() {
		if (dashboardPage != null) {
			dashboardPage.deletePatient(REASON);
		}
	}
}
