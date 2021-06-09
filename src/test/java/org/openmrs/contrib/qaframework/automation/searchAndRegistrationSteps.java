package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertNotNull;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.helper.PatientGenerator;
import org.openmrs.reference.helper.TestPatient;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.FindPatientPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.RegistrationPage;
import org.openmrs.uitestframework.page.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class searchAndRegistrationSteps extends Steps {
	private HomePage homePage;
	private RegistrationPage registrationPage;
	private LoginPage loginPage;
	private ClinicianFacingPatientDashboardPage dashboardPage;	
	private FindPatientPage findPatientPage;
	private TestPatient patient;

	@Before(RunTest.HOOK.SEARCH_REGISTRATION)
	public void setUp() throws Exception {
		initiateWithLogin();
		findPatientPage = new FindPatientPage(page);
		findPatientPage = homePage.goToFindPatientRecord();
		patient = new TestPatient();

	}

	@After(RunTest.HOOK.SEARCH_REGISTRATION)
	public void destroy() {
		quit();
	}

	@Given("User logs in as Admin")
	public void adminlogIn(String locationName) throws Exception {
		loginPage.loginAsAdmin();

	}
	@And("User clicks on Find Patient Record App")
	public void clickOnFindPatientRecord() {
		homePage = new HomePage(loginPage);
		findPatientPage = (FindPatientPage) homePage.goToFindPatientRecord();

	}
	@Then("User searches john smith")
	public void searchPatientAsJohnsmith() {
		findPatientPage.enterPatient("John Smith");
	}

	@Then("system returns searched patient results")
	public void returnSearchResults() {
		firstPatientIdentifier = findPatientPage.getFirstPatientIdentifier();
		assertNotNull(firstPatientIdentifier);

	}

	@And("User click on first Patient")
	public void clickOnFirstPatient() {
		dashboardPage = findPatientPage.clickOnFirstPatient();
	}

	@Then("Load patient dashboard")
	public void loadPatientDashboard() {
		matchPatientIds(firstPatientIdentifier);

	}
	@Given("System loads back to homePage")
	public void goBackToHomePage() {
		homePage.go();
	}

	@And("User clicks on registration app")
	public void clickOnRegistrationApp() throws InterruptedException {
		homePage = new HomePage(loginPage);
		registrationPage = (RegistrationPage) homePage.goToRegisterPatientApp()
				.waitForPage();
	}

	@And("User enters patient details")
	public void enterPatientDetails() throws InterruptedException {
	        patient = PatientGenerator.generateTestPatient();
		registrationPage.enterPatient(patient);

	}
	@Then("User clicks on comfirm button")
	public void clickOnComFormButton() throws InterruptedException {
	     dashboardPage = registrationPage.confirmPatient();
	     dashboardPage.waitForPage(); 

	}

}
