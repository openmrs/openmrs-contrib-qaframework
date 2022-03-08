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
		findPatientPage = homePage.goToFindPatientRecord();
		findPatientPage.enterPatient(testPatient.identifier);
		findPatientPage.waitForPageToLoad();
		dashboardPage = findPatientPage.clickOnFirstPatient();	
	}

	@After(RunTest.HOOK.SELENIUM_ALLERGIES)
	public void destroy() {
		deletePatient(testPatient);
		quit();
	}

	@Given("a user clicks on Allergies link from the Patient dashboard")
	public void loadAllergiesPage() {
		allergyPage = (AllergyPage) dashboardPage.clickOnAllergiesWidgetLink().waitForPage();
	}

	@Then("the system loads the Allergies board page")
	public void systemLoadsAllergiesPage() {
		assertEquals(getElement(patientHeaderId).getText(),getElement(patientHeaderId).getText());
		assertTrue(textExists("Allergies"));
	}

	@When("a user clicks No Known Allergy button")
	public void addNoKnownAllergy() {
		allergyPage.addNoKnownAllergy();
	}

	@Then("the system adds no known allergies into the allergies table")
	public void systemAddsNoKnownAllergies() {
		assertTrue(textExists("No known allergies"));
	}

	@When("a user clicks Remove No Known Allergy icon")
	public void removeNoKnownAllergy() {
		allergyPage.removeNoKnownAllergy();
	}

	@Then("the system displays unknown in the allergies table")
	public void systemRemovesNoKnownAllergies() {
		assertTrue(textExists("Unknown"));
	}
	
	@When("a user clicks Add New Allergy button")
	public void loadAddAllergyPage() {
		addOrEditAllergyPage = allergyPage.clickOnAddNewAllergy();
	}
	
	@Then("the system loads add new allergy page")
	public void systemLoadsAddAllergyPage() {
		assertTrue(textExists("Add New Allergy"));
	}
	
	@And("a user selects an allergy")
	public void selectKnownAllergy() {
		addOrEditAllergyPage.enterDrug(DRUG_NAME);
		addOrEditAllergyPage.drugId();
		addOrEditAllergyPage.enterReaction(REACTION);
		addOrEditAllergyPage.reactionId();
		addOrEditAllergyPage.addAllergyNote(ALLERGY_NOTE);
	}
	
	@And("a user clicks on the save allergy button")
	public void saveAllergy() {
		addOrEditAllergyPage.clickOnSaveAllergy();
	}
	
	@Then("the system adds known allergy into allergies table")
	public void systemAddsKnownAllergy() {
		assertTrue(allergyPage.getAllergen().contains(DRUG_NAME));
	}
	
	@When("a user clicks on the edit Allergy icon")
	public void loadEditAllergyPage() {
		addOrEditAllergyPage = allergyPage.clickOnEditAllergy();
	}
	
	@Then("the system loads edit allergy page")
	public void systemLoadsEditAllergyPage() {
		assertTrue(textExists("Edit Allergy"));
	}
	
	@And("a user edits an allergy")
	public void editKnownAllergy() {
		addOrEditAllergyPage.enterReaction(NEW_REACTION);
		addOrEditAllergyPage.reactionId();
		addOrEditAllergyPage.addAllergyNote(NEW_ALLERGY_NOTE);
	}
	
	@Then("the system adds edited allergy into the allergies table")
	public void systemAddsEditedAllergy() {
		assertTrue(allergyPage.getReaction().contains(NEW_REACTION));
	}
		
	@When("a user clicks on the delete Allergy icon")
	public void clickOnDeleteAllergyIcon() {
		allergyPage.clickOnDeleteAllergy();
	}
	
	@Then("a user confirms the delete action")
	public void clickOnConfirmDeleteAllergy() {
		allergyPage.clickOnConfirmDeleteAllergy();
	}
	
	@And("the system deletes an allergy from the allergies table")
	public void systemDeletesAllergy() {
		assertTrue(allergyPage.getAllergyStatus().contains("Unknown"));
		dashboardPage = addOrEditAllergyPage.clickReturn();
	}
}
