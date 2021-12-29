package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.AddOrEditAllergyPage;
import org.openmrs.contrib.qaframework.page.AllergyPage;
import org.openmrs.contrib.qaframework.page.FindPatientPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AllergySteps extends Steps {
	
	private static final String DRUG_NAME = "Morphine";
	private static final String REACTION = "Itching";
	private static final String NEW_REACTION = "Rash";
	private static final String ALLERGY_NOTE = "The whole body itches";
	private static final String NEW_ALLERGY_NOTE = "There's a rash on the body";
	private AllergyPage allergyPage;
	private AddOrEditAllergyPage addOrEditAllergyPage;
	private TestData.PatientInfo testPatient;
	
	@Before(RunTest.HOOK.SELENIUM_ALLERGY)
	public void patientVisitsDashboard() {
		initiateWithLogin();
		testPatient = createTestPatient();
		findPatientPage = (FindPatientPage) homePage.goToFindPatientRecord().waitForPage();
		findPatientPage.enterPatient(testPatient.identifier);
		findPatientPage.waitForPageToLoad();
		dashboardPage = findPatientPage.clickOnFirstPatient();
	}
	
	@After(RunTest.HOOK.SELENIUM_ALLERGY)
	public void tearDown() {
		deletePatient(testPatient);
		quit();
	}
	
	@Given("a user clicks on Allergies link from the Patient dashboard")
	public void loadAllergyPage() {
		allergyPage = (AllergyPage) dashboardPage.clickOnAllergiesWidgetLink().waitForPage();
	}

	@Then("the system loads the Allergy page")
	public void systemLoadsAllergyPage() {
		assertEquals(getElement(patientHeaderId).getText(),getElement(patientHeaderId).getText());
		assertTrue(textExists("Allergies"));
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
