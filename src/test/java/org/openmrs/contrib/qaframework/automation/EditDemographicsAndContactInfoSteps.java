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

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.FindPatientPage;
import org.openmrs.contrib.qaframework.page.RegistrationEditSectionPage;
import org.openmrs.contrib.qaframework.page.RegistrationSummaryPage;

public class EditDemographicsAndContactInfoSteps extends Steps {

	private TestData.PatientInfo testPatient;
	private RegistrationEditSectionPage registrationEditSectionPage;
	private RegistrationSummaryPage registrationSummaryPage;

	@Before(RunTest.HOOK.SELENIUM_PATIENT_DEMOGRAPHICS)
	public void visitPatientDashboard() {
		testPatient = createTestPatient();
		initiateWithLogin();
		findPatientPage = (FindPatientPage) homePage.goToFindPatientRecord().waitForPage();
		findPatientPage.enterPatient(testPatient.identifier);
		findPatientPage.waitForPageToLoad();
		dashboardPage = findPatientPage.clickOnFirstPatient();
	}

	@After(RunTest.HOOK.SELENIUM_PATIENT_DEMOGRAPHICS)
	public void destroy() {
		deletePatient(testPatient);
		quit();
	}

	@Given("a user clicks on Edit Registration Information link from Patient dashboard")
	public void loadRegistrationSummaryPage() {
		registrationSummaryPage = (RegistrationSummaryPage) dashboardPage.goToRegistrationSummary().waitForPage();
	}

	@Then("the system loads Registration Summary Page")
	public void systemLoadsRegistrationSummaryPage() {
		assertPage(registrationSummaryPage.waitForPage());
	}

	@When("a user clicks on Edit link from Registration Summary Page")
	public void loadEditDemographicsSection() {
		registrationEditSectionPage = (RegistrationEditSectionPage) registrationSummaryPage.clickOnEditDemographics().waitForPage();
	}

	@Then("the system loads edit demographics section")
	public void systemLoadsEditDemographicsSection() {
		assertPage(registrationEditSectionPage.waitForPage());
	}

	@And("a user edits demographics")
	public void editDemographics() {
		testPatient.givenName = "Yuan";
		testPatient.middleName = "Youn";
		testPatient.familyName = "Ching";
		testPatient.gender = "Male";
		testPatient.birthDay = "23";
		testPatient.birthMonth = "May";
		testPatient.birthYear = "1990";
		registrationEditSectionPage.enterGivenName(testPatient.givenName);
		registrationEditSectionPage.enterMiddleName(testPatient.middleName);
		registrationEditSectionPage.enterFamilyName(testPatient.familyName);
		registrationEditSectionPage.selectPatientGender(testPatient.gender);
		registrationEditSectionPage.clickOnBirthdateLabel();
		registrationEditSectionPage.enterBirthDay(testPatient.birthDay);
		registrationEditSectionPage.selectBirthMonth(testPatient.birthMonth);
		registrationEditSectionPage.enterBirthYear(testPatient.birthYear);
	}

	@Then("the system saves the updated patient demographics")
	public void systemSavesUpdatedDemographics() {
		assertEquals(registrationSummaryPage.getPatientGivenName(), testPatient.givenName);
		assertEquals(registrationSummaryPage.getPatientFamilyName(), testPatient.familyName);
	}

	@When("a user clicks on Edit link under contact info section from Registration Summary Page")
	public void loadContactInfoEditSection() {
		registrationSummaryPage.clickOnShowContact();
		registrationEditSectionPage = (RegistrationEditSectionPage) registrationSummaryPage.clickOnEditContact().waitForPage();
	}

	@Then("the system loads the edit contact information section")
	public void systemLoadsEditContactInfoSection() {
		assertPage(registrationEditSectionPage.waitForPage());
	}

	@And("a user edits contact information")
	public void editContactInfo() {
		testPatient.city = "Kyambogo";
		testPatient.state = "Central";
		testPatient.country = "Uganda";
		testPatient.postalCode = "256";
		testPatient.phone = "0785456654";
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
	}

	@And("a user clicks on the confirm button")
	public void saveUpdatedPatientDetails() throws InterruptedException {
		registrationEditSectionPage.clickOnConfirmEdit();
		registrationSummaryPage = registrationEditSectionPage.confirmPatientEdit();
	}

	@Then("the system saves the updated patient contact information")
	public void systemSavesUpdatedContactInfo() {
		registrationSummaryPage.clickOnShowContact();
		assertEquals(registrationSummaryPage.getTelephoneNumber(), testPatient.phone);
	}
}
