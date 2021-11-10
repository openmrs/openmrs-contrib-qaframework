package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.FindPatientPage;
import org.openmrs.contrib.qaframework.page.MarkPatientDeceasedPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class MarkPatientDeceasedSteps extends Steps {

	MarkPatientDeceasedPage markPatientDeceased = new MarkPatientDeceasedPage(
			page);

	private TestData.PatientInfo patient;

	@Before(RunTest.HOOK.MARK_PATIENT_DECEASED)
	public void setUp() throws Exception {
		patient = createTestPatient();
		createTestPatient();
	}

	@Given("user adds a new concept called cause of death")
	public void user_adds_a_new_concept_called_cause_of_death() {

		homePage.goToAdministration();
		markPatientDeceased.newConcept();
		throw new io.cucumber.java.PendingException();
	}

	@And("user adds the concept id to the global properties")
	public void user_adds_the_concept_id_to_the_global_properties() {
		markPatientDeceased.newConcept();
		throw new io.cucumber.java.PendingException();
	}

	@Then("the global property for is saved")
	public void the_global_property_for_is_saved() {
		String conceptID = markPatientDeceased.getConceptId();
		markPatientDeceased.enterConceptid(conceptID);
		throw new io.cucumber.java.PendingException();
	}

	@Given("a patient is selected")
	public void a_patient_is_selected() {
		FindPatientPage findPatientPage = homePage.goToFindPatientRecord();
		findPatientPage.enterPatient(patient.identifier);
		findPatientPage.waitForPageToLoad();
		findPatientPage.clickOnFirstPatient();
		throw new io.cucumber.java.PendingException();
	}

	@Given("User eneters the details of the deceased and saves")
	public void user_eneters_the_details_of_the_deceased_and_saves() {
		markPatientDeceased.clickOnMarkPatientDead();
		throw new io.cucumber.java.PendingException();
	}

	@Then("Patiet deceased confirmation message is displayed")
	public void patiet_deceased_confirmation_message_is_displayed() {
		assertTrue(markPatientDeceased.confirmDeadMessage().contains(
				"The patient is deceased"));
		throw new io.cucumber.java.PendingException();
	}

	@After(RunTest.HOOK.MARK_PATIENT_DECEASED)
	public void tearDown() throws Exception {
		homePage.go();
		homePage.goToAdministration();
		markPatientDeceased.deleteConcept();
		deletePatient(patient);
	}

}
