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

import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.EncounterFormPage;
import org.openmrs.contrib.qaframework.page.ManageEncountersPage;
import org.openmrs.contrib.qaframework.page.SystemAdministrationPage;

public class PatientEncounterSteps extends Steps {

	private AdministrationPage administrationPage;
	private EncounterFormPage encounterFormPage;
	private ManageEncountersPage manageEncountersPage;
	private SystemAdministrationPage systemAdministrationPage;
	private TestData.PatientInfo testPatient;
	private static final String ENCOUNTER_DATE_TIME = "24/02/2022 00:00";
	private static final String ROLE = "Clinician";
	private static final String PROVIDER_NAME = "Super User";
	private static final String REASON_FOR_DELETION = "Testing purpose";

	@Before(RunTest.HOOK.SELENIUM_PATIENT_ENCOUNTER )
	public void visitHomePage() {
		initiateWithLogin();
		testPatient = createTestPatient();
	}

	@After(RunTest.HOOK.SELENIUM_PATIENT_ENCOUNTER )
	public void destroy() {
		deletePatient(testPatient);
		quit();
	}

	@Given("a user clicks on System Administration app from the home page")
	public void loadSystemAdministrationPage() {
		systemAdministrationPage = homePage.goToSystemAdministrationPage();
	}

	@When("a user clicks on Advanced Administration app from the System Administration page")
	public void loadAdministrationPage() {
		administrationPage = systemAdministrationPage.goToAdvancedAdministration();
	}

	@Then("a user clicks on Manage Encounters link from the Administration page")
	public void loadManageEncountersPage() {
		manageEncountersPage = administrationPage.clickOnManageEncounters();
	}

	@When("a user clicks on Add Encounter link from the encounters page")
	public void loadEncounterFormPage() {
		encounterFormPage = manageEncountersPage.clickOnAddEncounter();
	}

	@And("a user fills the ecounter form")
	public void fillEncounterForm() {
		encounterFormPage.enterPatientIdentifier(testPatient.identifier);
		encounterFormPage.enterEncounterDateTime(ENCOUNTER_DATE_TIME);
		encounterFormPage.pressReturnKey();
		encounterFormPage.selectRole(ROLE);
		encounterFormPage.enterProviderName(PROVIDER_NAME);
	}

	@And("a user clicks Save Encounter button")
	public void clickOnSaveEncounterButton() {
		encounterFormPage.clickOnSaveEncounter();
	}

	@Then("the system saves encounter in the encounters table")
	public void systemSavesEncounter() {
		assertTrue(textExists("Encounter saved"));
	}

	@Given("a user checks delete checkbox")
	public void checkDelete() {
		encounterFormPage.checkDelete();
	}

	@When("a user provides a reason for deletion")
	public void reasonForDeletion() {
		encounterFormPage.enterVoidReason(REASON_FOR_DELETION);
	}
}
