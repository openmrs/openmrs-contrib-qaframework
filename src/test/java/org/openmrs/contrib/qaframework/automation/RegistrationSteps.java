package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.helper.PatientGenerator;
import org.openmrs.reference.helper.TestPatient;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.RegistrationPage;
import org.openqa.selenium.By;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationSteps extends Steps {

	RegistrationPage registrationPage;
	TestPatient patient;
	ClinicianFacingPatientDashboardPage dashboardPage;

	@After(RunTest.HOOK.SELENIUM)
	public void destroy() {
		quit();
	}

	@When("Registration user rightly logs in")
	public void registrationLogin() throws Exception {
		goToLoginPage();
		loginPage = getLoginPage();
		loginPage.login("clerk", "Clerk123", "Registration Desk");
	}

	@And("User clicks on Registration App")
	public void visitRegistrationPage() throws InterruptedException {
		goToRegistrationApp();
	}

	@And("User enters {string} details for John Smith")
	public void userEntersPatient(String validity) throws InterruptedException {
		patient = PatientGenerator.generateTestPatient();
		patient.givenName = "John";
		patient.familyName = "Smith";
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

	@Then("User's patient registration is {string}")
	public void registering(String status) throws InterruptedException {
		if ("successful".equals(status)) {
			dashboardPage = (ClinicianFacingPatientDashboardPage) registrationPage
					.confirmPatient().waitForPage();
			patient.uuid = dashboardPage.getPatientUuidFromUrl();
			assertEquals(dashboardPage.getPatientGivenName(), patient.givenName);
			assertEquals(dashboardPage.getPatientFamilyName(),
					patient.familyName);
			assertNotNull(getElement(By.className("demographics")));
		} else {
			assertNotNull(RegistrationPage.FIELD_ERROR);
		}
	}
}
