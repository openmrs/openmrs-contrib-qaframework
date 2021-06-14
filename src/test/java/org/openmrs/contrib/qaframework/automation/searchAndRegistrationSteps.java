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
	private String familyName = "Faiza";
	private String givenName = "Andria";
	private String phoneNumber = "+21134567890";
	private TestData.PatientInfo testPatient;

	@Before(RunTest.HOOK.SELENIUM_SEARCH_REGISTRATION)
	public void setUp() throws Exception {
		testPatient = createTestPatient();
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_SEARCH_REGISTRATION)
	public void destroy() {
		quit();
	}

	@Given("User clicks on Find Patient Record App")
	public void clickOnFindPatientRecord() {
		findPatientPage = (FindPatientPage) homePage.goToFindPatientRecord()
				.waitForPage();
	}

	@And("User searches Andria Faiza")
	public void searchPatientAsAndriaFaiza() {
		findPatientPage.enterPatient("Andria Faiza");
	}

	@Then("The System returns no patient")
	public void noReturnPatient() {
		assertNotNull(getElement(By.className("dataTables_empty")));
	}

	@And("The system loads homePage")
	public void clickOnHomePage() {
		homePage.go();
	}

	@When("User clicks on registration app")
	public void clickOnRegistrationApp() throws InterruptedException {
		registrationPage = (RegistrationPage) homePage.goToRegisterPatientApp()
				.waitForPage();
	}

	@And("User enters patient details")
	public void enterPatientDetails() throws InterruptedException {
		registrationPage.enterPatientFamilyName("familyName ");
		registrationPage.enterPatientGivenName("givenName");
		registrationPage.clickOnGenderLink();
		registrationPage.selectPatientGender(familyName);
		registrationPage.clickOnBirthdateLabel();
		registrationPage.clickOnBirthDateLink();
		registrationPage.enterPatientBirthDate(null);
		registrationPage.enterPatientAddress(null);
		registrationPage.clickOnPhoneNumber();
		registrationPage.enterPhoneNumber("phoneNumber");
	}

	@Then("User clicks on comfirm button")
	public void clickOnComFormButton() throws InterruptedException {
		dashboardPage = registrationPage.confirmPatient();
		dashboardPage.waitForPage();

	}

}
