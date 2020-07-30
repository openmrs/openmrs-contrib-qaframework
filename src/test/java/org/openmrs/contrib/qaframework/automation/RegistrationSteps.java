package org.openmrs.contrib.qaframework.automation;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openmrs.contrib.qaframework.CucumberProperties;
import org.openmrs.reference.helper.PatientGenerator;
import org.openmrs.reference.helper.TestPatient;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.RegistrationPage;
import org.openmrs.uitestframework.page.LoginPage;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RegistrationSteps extends Steps {

	LoginPage loginPage;
	RegistrationPage registrationPage;
	TestPatient patient;
	ClinicianFacingPatientDashboardPage dashboardPage;

	@When("Registration user rightly logs in")
	public void registrationLogin() throws Exception {
		goToLoginPage();
		loginPage = getLoginPage();
		loginPage.login("clerk", "Clerk123", "Registration Desk");
	}

	@And("User clicks on Registration App")
	public void visitRegistrationPage() throws InterruptedException {
		homePage = new HomePage(loginPage);
		registrationPage = homePage.goToRegisterPatientApp();
	}

	@And("User enters " + CucumberProperties.REGEX_UNDER_DOUBLE_QUOTES_STRING
			+ " patient details")
	public void userEntersPatient(String validity) throws InterruptedException {
		patient = PatientGenerator.generateTestPatient();
		if ("wrong".equals(validity)) {
			patient.phone = "fake";
		} else if ("incomplete".equals(validity)) {
			patient.givenName = null;
		}
		if ("incomplete".equals(validity)) {
			assertNotNull(By.className("required"));
		} else {
			registrationPage.enterPatient(patient);
			if ("wrong".equals(validity)) {
				assertNotNull(By.className("phone"));
			} else if ("right".equals(validity)) {
				assertNotNull(By.id("submit"));
			}
		}
	}

	@Then("User's patient registration is "
			+ CucumberProperties.REGEX_UNDER_DOUBLE_QUOTES_STRING)
	public void registering(String status) throws InterruptedException {
		if ("successful".equals(status)) {
			dashboardPage = registrationPage.confirmPatient();
			patient.uuid = dashboardPage.getPatientUuidFromUrl();
			assertEquals(dashboardPage.getPatientGivenName(), patient.givenName);
			assertEquals(dashboardPage.getPatientFamilyName(),
					patient.familyName);
			assertNotNull(getElement(By.className("demographics")));
		} else {
			assertNotNull(RegistrationPage.FIELD_ERROR);
		}
		quitBrowser();
	}
}
