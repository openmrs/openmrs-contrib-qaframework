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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.commons.lang3.RandomStringUtils;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.ActiveVisitsPage;
import org.openmrs.contrib.qaframework.page.RegistrationEditSectionPage;

public class EditDemographicsAndContactInfoSteps extends Steps {

	private String prefix;
	private TestData.PatientInfo testPatient;
	private ActiveVisitsPage activeVisitsPage;
	private RegistrationEditSectionPage registrationEditSectionPage;

	@Before(RunTest.HOOK.SELENIUM_PATIENT_DEMOGRAPHICS)
	public void visitPatientDashboard() {
		initiateWithLogin();
		prefix = RandomStringUtils.randomAlphanumeric(6);
		testPatient = createTestPatient();
		new TestData.TestVisit(testPatient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
	}

	@After(RunTest.HOOK.SELENIUM_PATIENT_DEMOGRAPHICS)
	public void destroy() {
		try {
			deletePatient(testPatient);
		} catch (IllegalStateException ex) {}

		quit();
	}
	
	@Given("a user clicks on the find patient app on the home page")
	public void clickOnFindPatientRecordApp() {
		findPatientPage = homePage.goToFindPatientRecord();
	}
	
	@Given("a user clicks on the active visits app on the home page")
	public void clickOnActiveVisitsApp() {
		activeVisitsPage = homePage.goToActiveVisitsSearch();
	}
	
	@Then("the system loads the active visits page")
	public void systemLoadsActiveVisitsPage() {
		assertTrue(textExists("Active Visits"));
	}
	
	@Then("the system loads the find patient page")
	public void systemLoadsFindPatientPage() {
		assertTrue(textExists("Find Patient Record"));
	}
	
	@And("a user searches for the patient to edit")
	public void searchForPatientToEdit() {
		findPatientPage.enterPatient(testPatient.identifier);
	}
	
	@And("a user searches for the active visit patient to edit")
	public void searchForActiveVisitPatientToEdit() {
		activeVisitsPage.search(testPatient.identifier);
	}
	
	@And("a user clicks on returned patient in the patient table")
	public void clickOnTheReturnedPatient() {
		dashboardPage = findPatientPage.clickOnFirstPatient();
	}
	
	@And("a user clicks on the active visit patient to edit")
	public void clickOnActivePatientToEdit() {
		dashboardPage = activeVisitsPage.goToPatientDashboardOfLastActiveVisit();
	}

	@And("a user clicks on Edit Registration Information link from Patient dashboard")
	public void loadRegistrationSummaryPage() {
		registrationEditSectionPage = dashboardPage.clickOnEditPatient();
	}

	@Then("the system loads Registration Summary Page")
	public void systemLoadsRegistrationSummaryPage() {
		assertTrue(textExists("Demographics"));
	}

	@When("a user edits demographics")
	public void editDemographics() {
		testPatient.gender = "Male";
		testPatient.birthDay = "23";
		testPatient.birthMonth = "May";
		testPatient.birthYear = "1992";
		registrationEditSectionPage.enterGivenName(testPatient.givenName + prefix);
		registrationEditSectionPage.enterMiddleName(testPatient.middleName + prefix);
		registrationEditSectionPage.enterFamilyName(testPatient.familyName + prefix);
		registrationEditSectionPage.selectPatientGender(testPatient.gender);
		registrationEditSectionPage.clickOnBirthdateLabel();
		registrationEditSectionPage.enterBirthDay(testPatient.birthDay);
		registrationEditSectionPage.selectBirthMonth(testPatient.birthMonth);
		registrationEditSectionPage.enterBirthYear(testPatient.birthYear);
	}
	
	@And ("a user clicks on the confirm button to save the changes")
	public void clickOnTheConfirmButton() throws InterruptedException {
		registrationEditSectionPage.clickOnConfirmEdit();
		dashboardPage = registrationEditSectionPage.confirmPatient();
	}

	@Then("the system saves the updated patient demographics")
	public void systemSavesUpdatedDemographics() {
		assertThat(dashboardPage.getPatientGivenName(), is(testPatient.givenName + prefix));
	}
	
	@And("a user clicks on the show contact downdrop")
	public void clickOnShowContactDropDown() {
		dashboardPage.clickOnShowContact();
	}

	@And("a user clicks on Edit link under contact info section from Registration Summary Page")
	public void loadContactInfoEditSection() {
		registrationEditSectionPage = dashboardPage.clickOnEditContact();
	}

	@Then("the system loads the edit contact information section")
	public void systemLoadsEditContactInfoSection() {
		assertTrue(textExists("Contact Info"));
	}

	@And("a user edits contact information")
	public void editContactInfo() {
		testPatient.city = "Adidas Abbeba";
		testPatient.state = "Adidas Abbeba";
		testPatient.country = "Ethiopia";
		testPatient.postalCode = "3822";
		testPatient.phone = "aaaaaaaaa";
		registrationEditSectionPage.clearVillage();
		registrationEditSectionPage.enterVillage(testPatient.city );
		registrationEditSectionPage.clearState();
		registrationEditSectionPage.enterState(testPatient.state);
		registrationEditSectionPage.clearCountry();
		registrationEditSectionPage.enterCountry(testPatient.country);
		registrationEditSectionPage.clearPostalCode();
		registrationEditSectionPage.enterPostalCode(testPatient.postalCode);
		registrationEditSectionPage.clickOnPhoneNumberEdit();
		registrationEditSectionPage.enterPhoneNumber(testPatient.phone);
		assertTrue(registrationEditSectionPage.getInvalidPhoneNumberNotification().contains("Must be a valid phone number (with +, -, numbers or parentheses)"));
		registrationEditSectionPage.clearPhoneNumber();
		registrationEditSectionPage.enterPhoneNumber("111111111");
		registrationEditSectionPage.clickOnConfirmEdit();
	}

	@Then("a user clicks on the confirm button")
	public void saveUpdatedPatientDetails() throws InterruptedException {
		registrationEditSectionPage.confirmPatient();
	}
}
