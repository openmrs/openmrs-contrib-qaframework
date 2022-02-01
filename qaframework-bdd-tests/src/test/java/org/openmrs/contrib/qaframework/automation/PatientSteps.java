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

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.PatientGenerator;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.helper.TestPatient;
import org.openmrs.contrib.qaframework.page.DataManagementPage;
import org.openmrs.contrib.qaframework.page.MergePatientsPage;
import org.openmrs.contrib.qaframework.page.PatientVisitsDashboardPage;
import org.openmrs.contrib.qaframework.page.RegistrationPage;

public class PatientSteps extends Steps {
	
	private DataManagementPage dataManagementPage;
	private MergePatientsPage mergePatientsPage;
	private PatientVisitsDashboardPage VisitsDashboardPage;
	private RegistrationPage registrationPage;
	private TestData.PatientInfo testPatient;
	private TestData.PatientInfo testPatient2;
	private TestPatient patient;

	@Before(RunTest.HOOK.SELENIUM_PATIENT)
	public void visitHomePage() {
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_PATIENT)
	public void destroy() {
		deletePatient(testPatient);
		deletePatient(testPatient2);
		quit();
	}
	
	@Given("a user creates a patient")
	public void createPatient() {
		testPatient = createTestPatient();
	}
	
	@When("a user clicks on Find Patient Record app from the home page")
	public void loadFindPatientPage() {
		findPatientPage = homePage.goToFindPatientRecord();
	}
	
	@And("the system loads find patient page")
	public void systemLoadsFindPatientPage() {
		assertTrue(textExists("Find Patient Record"));
	}
	
	@And("a user enters the patient's {string}")
	public void findPatient(String name) {
		if ("rightName".equals(name)) {
			findPatientPage.enterPatient(testPatient.givenName);
			findPatientPage.waitForPageToLoad();
		}else {
			findPatientPage.enterPatient("wrongName");
		}
	}
	
	@Then("the patient search is {string}")
	public void systemLoadsPatient(String status) {
		if ("successful".equals(status)) {
			assertThat(findPatientPage.getFirstPatientName(), containsString(testPatient.givenName));
			assertThat(findPatientPage.getFirstPatientName(), containsString(testPatient.middleName));
			assertThat(findPatientPage.getFirstPatientName(), containsString(testPatient.familyName));
		} else {
			assertTrue(textExists("No matching records found"));
		}
	}
	
	@And("a user searches for an existing patient by name")
	public void searchPatient() {
		findPatientPage.enterPatient(testPatient.givenName);
		firstPatientIdentifier = findPatientPage.getFirstPatientIdentifier();
		assertNotNull(firstPatientIdentifier);
	}
	
	@And("a user clicks on first patient")
	public void loadPatientDashboard() {
		dashboardPage = findPatientPage.clickOnFirstPatient();
	}
	
	@Then("the system loads the patient dashboard")
	public void systemLoadsPatientDashboard() {
		matchPatientIds(firstPatientIdentifier);
		dashboardPage.goToHomePage();
	}
	
	@Given("a user clicks on Register a patient App from the home page")
	public void loadRegisterPatientPage() throws InterruptedException {
  		registrationPage = homePage.goToRegisterPatientApp();
	}
	
	@When("the system loads Register a patient page")
	public void systemLoadsRegisterPatientPage() {
		assertTrue(textExists("Register a patient"));
	}
	
	@And("a user clicks on the Unidentified Patient checkbox")
	public void clickOnUndefinedPatientCheckbox() {
  		registrationPage.selectUnidentifiedPatient();
	}
	
	@And("a user selects a gender")
	public void selectGender() throws InterruptedException {
  		registrationPage.clickOnGenderLink();
  		registrationPage.selectPatientGender("Male");
  		registrationPage.clickOnConfirmSection();
	}
	
	@And("a user clicks Confirm button")
	public void clickOnConfirmButton() throws InterruptedException {
  		dashboardPage = registrationPage.confirmPatient();
	}
	
	@Then("the system adds patient record into the patients table")
	public void systemAddsPatientRecord() {
		assertThat(dashboardPage.getPatientGivenName(), equalTo("UNKNOWN"));
		assertThat(dashboardPage.getPatientFamilyName(), equalTo("UNKNOWN"));
		dashboardPage.goToHomePage();
	}
	
	@Then("the system loads unidentified patient keyboard")
	public void systemLoadsUnidentifiedPatientKeyboard() {
  		assertTrue(textExists("UNKNOWN UNKNOWN"));
		dashboardPage.goToHomePage();
	}
	
	@And("a user captures the patient details")
	public void capturePatientDetails() throws InterruptedException {
		patient = PatientGenerator.generateTestPatient();
		patient.givenName = "Jowella";
		patient.middleName = "Jane";
		patient.familyName = "Kate";
		patient.gender = "Female";
		patient.birthDay = "01";
		patient.birthMonth = "January";
		patient.birthYear = "2000";
		patient.address1 ="Nakwero";
		patient.address2 ="Canaan Estate";
		patient.city = "Kampala";
		patient.state ="Central";
		patient.country ="Uganda";
		patient.postalCode ="235";
		patient.phone ="0786987987";
		registrationPage.enterPatient(patient);
	}
	
	@And("the system alerts that similar patient found")
	public void systemAllertsSimilarPatient() {
		String birthDate = patient.birthDay + "." + patient.birthMonth.substring(0,3) + "." + patient.birthYear;
		String name = registrationPage.getSimilarPatientName();
		assertEquals(name, (patient.givenName + " " + patient.familyName));
		String info = registrationPage.getSimilarPatientInfo();
		assertEquals(info, (patient.gender + ", " + birthDate + ", " + patient.address1 + " " + patient.address2 + " " + patient.city + patient.state + patient.country + patient.postalCode ));
	}
	
	@Then("the system displays patient dashboard")
	public void systemDisplaysPatientDashboard() {
		assertThat(dashboardPage.getPatientGivenName(), equalTo(patient.givenName));
		assertThat(dashboardPage.getPatientFamilyName(), equalTo(patient.familyName));
		dashboardPage.goToHomePage();
	}
	
	@And("a user captures patient name with accented letter")
	public void namePatientAccentedLetter() throws InterruptedException {
		patient = PatientGenerator.generateTestPatient();
		patient.givenName = "Jowellał";
		patient.familyName = "Katè";
		patient.gender = "Female";
		registrationPage.enterPatient(patient);
	}
	
	@Given("a user creates two patients")
	public void createTwoPatients() {
  		testPatient = createTestPatient();
  		testPatient2 = createTestPatient();
	}
	
	@When("a user clicks on Data Management App from the home page")
	public void loadDataManagementPage() {
  		dataManagementPage = homePage.goToDataManagement();
	}
	
	@And("the system loads data management page")
	public void systemLoadsDataManagementPage() {
  		assertTrue(textExists("Data Management"));
	}
	
	@And("a user clicks on Merge Patient Electronic Records app")
	public void loadMergePatientsPage() {
  		mergePatientsPage = dataManagementPage.goToMergePatient();
	}
	
	@And("the system loads merge patients page")
	public void systemLoadsMergePatientsPage() {
  		assertTrue(textExists("Merge Patient Electronic Records"));
	}
	
	@And("a user captures IDs for patients to merge")
	public void capturePatientIds() {
		mergePatientsPage.enterPatient1(testPatient.identifier);
		mergePatientsPage.enterPatient2(testPatient2.identifier);
	}
	
	@And("a user clicks Continue button")
	public void clickOnContinueButton() {
		mergePatientsPage.clickOnContinue();
	}
	
	@And("a user selects the preferred record")
	public void selectPreferredRecord() {
		mergePatientsPage.clickOnMergePatient();
	}
	
	@And("a user clicks yes continue button")
	public void confirmMergePatientRecord() {
		VisitsDashboardPage = mergePatientsPage.clickOnContinue();
	}
	
	@Then("the system loads patient visits dashboard")
	public void systemReturnsPatientDashboard() {
		assertThat(VisitsDashboardPage.getPatientFamilyName(), equalTo(testPatient2.familyName));
	}
	
	@And("a user clicks No button")
	public void clickNoButton() {
		mergePatientsPage.clickOnNo();
	}
	
	@And("the system returns to capture patient ID section")
	public void systemReturnsCapturePatientIDSection() {
		assertTrue(textExists("Merge Patient Electronic Records"));
	}
	
	@Then("the system displays Continue button")
	public void systemDisplaysContinueButton() {
		assertNotNull(dataManagementPage.CONTINUE);
	}	
}
