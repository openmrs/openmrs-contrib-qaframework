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
import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.AddOrEditAllergyPage;
import org.openmrs.contrib.qaframework.page.AllergyPage;
import org.openmrs.contrib.qaframework.page.FindPatientPage;

public class AllergiesSteps extends Steps {
	
	private static final String DRUG_NAME = "Morphine";
	private static final String REACTION = "Itching";
	private static final String NEW_REACTION = "Rash";
	private static final String ALLERGY_NOTE = "The whole body itches";
	private static final String NEW_ALLERGY_NOTE = "There's a rash on the body";
	private AllergyPage allergyPage;
	private AddOrEditAllergyPage addOrEditAllergyPage;
	private TestData.PatientInfo testPatient;

	@Before(RunTest.HOOK.SELENIUM_ALLERGIES)
	public void visitDashboard() {
		initiateWithLogin();
		testPatient = createTestPatient();
		findPatientPage = (FindPatientPage) homePage.goToFindPatientRecord().waitForPage();
		findPatientPage.enterPatient(testPatient.identifier);
		findPatientPage.waitForPageToLoad();
		dashboardPage = findPatientPage.clickOnFirstPatient();	
	}

	@After(RunTest.HOOK.SELENIUM_ALLERGIES)
	public void destroy() {
		deletePatient(testPatient);
		quit();
	}

	@Given("a user clicks on Allergies link from Patient dashboard")
	public void loadAllergiesPage() {
		allergyPage = (AllergyPage) dashboardPage.clickOnAllergiesWidgetLink().waitForPage();
	}

	@Then("the system loads Allergies page")
	public void systemLoadsAllergiesPage() {
		assertEquals(getElement(patientHeaderId).getText(),getElement(patientHeaderId).getText());
		assertTrue(textExists("Allergies"));
	}

	@And("a user clicks No Known Allergy button")
	public void addNoKnownAllergy() {
		allergyPage.addNoKnownAllergy();
	}

	@Then("the system add no known allergies into the allergies table")
	public void systemAddsNoKnownAllergies() {
		assertTrue(textExists("No known allergies"));
	}

	@And("a user clicks Remove No Known Allergy icon")
	public void removeNoKnownAllergy() {
		allergyPage.removeNoKnownAllergy();
	}

	@Then("the system displays unknown in the allergies table")
	public void systemRemovesNoKnownAllergies() {
		assertTrue(textExists("Unknown"));
	}
	
	@When("a user adds a known allergy into the system")
	public void addKnownAllergy() {
		addOrEditAllergyPage = allergyPage.clickOnAddNewAllergy();
		addOrEditAllergyPage.enterDrug(DRUG_NAME);
		addOrEditAllergyPage.drugId();
		addOrEditAllergyPage.enterReaction(REACTION);
		addOrEditAllergyPage.reactionId();
		addOrEditAllergyPage.addAllergyNote(ALLERGY_NOTE);
		allergyPage = addOrEditAllergyPage.clickOnSaveAllergy();
		assertTrue(allergyPage.getAllergen().contains(DRUG_NAME));
	}
	
	@Then("a user edits a known allergy")
	public void EditKnownAllergy() {
		addOrEditAllergyPage = allergyPage.clickOnEditAllergy();
		addOrEditAllergyPage.enterReaction(NEW_REACTION);
		addOrEditAllergyPage.reactionId();
		addOrEditAllergyPage.addAllergyNote(NEW_ALLERGY_NOTE);
		allergyPage = addOrEditAllergyPage.clickOnSaveAllergy();
		assertTrue(allergyPage.getReaction().contains(NEW_REACTION));
	}
	
	@And("a user deletes a known allergy")
	public void DeleteKnownAllergy() {
		allergyPage.clickOnDeleteAllergy();
		allergyPage.clickOnConfirmDeleteAllergy();
		assertTrue(allergyPage.getAllergyStatus().contains("Unknown"));
	}
}
