/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.PatientGenerator;
import org.openmrs.contrib.qaframework.helper.TestPatient;
import org.openmrs.contrib.qaframework.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.contrib.qaframework.page.HomePage;
import org.openmrs.contrib.qaframework.page.RegistrationPage;
import org.openqa.selenium.By;

public class RegistrationSteps extends Steps {

	private RegistrationPage registrationPage;
	private TestPatient patient;

	@After(RunTest.HOOK.SELENIUM_REGISTRATION)
	public void destroy() {
		quit();
	}

	@Given("Registered user rightly logs in")
	public void registrationLogin() throws Exception {
		goToLoginPage();
		loginPage = getLoginPage();
		loginPage.login("clerk", "Clerk123", "Registration Desk");
	}

	@And("User clicks on Registration App")
	public void visitRegistrationPage() throws InterruptedException {
		homePage = new HomePage(loginPage);
		registrationPage = (RegistrationPage) homePage.goToRegisterPatientApp().waitForPage();
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
			dashboardPage = (ClinicianFacingPatientDashboardPage) registrationPage.confirmPatient().waitForPage();
			patient.uuid = dashboardPage.getPatientUuidFromUrl();
			assertEquals(dashboardPage.getPatientGivenName(), patient.givenName);
			assertEquals(dashboardPage.getPatientFamilyName(), patient.familyName);
			assertNotNull(getElement(By.className("demographics")));
		} else {
			assertNotNull(RegistrationPage.FIELD_ERROR);
		}
	}
}
