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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.ActiveVisitsPage;
import org.openmrs.contrib.qaframework.page.AddOrEditAllergyPage;
import org.openmrs.contrib.qaframework.page.AllergyPage;
import org.openmrs.contrib.qaframework.page.AttachmentsPage;
import org.openmrs.contrib.qaframework.page.ConditionPage;
import org.openmrs.contrib.qaframework.page.ConditionsPage;
import org.openmrs.contrib.qaframework.page.PatientVisitsDashboardPage;
import org.openmrs.contrib.qaframework.page.RequestAppointmentPage;
import org.openmrs.contrib.qaframework.page.VisitNotePage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClinicalVisitSteps extends Steps {

	private static final String DIAGNOSIS_PRIMARY = "Cancer";
	private static final String DIAGNOSIS_SECONDARY = "Malaria";
	private static final String APPOINTMENT_TYPE = "Surgery";
	private static final String DRUG_NAME = "Penicillins";
	private static final String REACTION = "Headache";
	private static final String CONDITION = "Malaria";
	private ActiveVisitsPage activeVisitsPage;
	private VisitNotePage visitNotePage;
	private AllergyPage allergyPage;
	private AddOrEditAllergyPage addOrEditAllergyPage;
	private ConditionPage conditionPage;
	private ConditionsPage conditionsPage;
	private AttachmentsPage attachmentsPage;
	private RequestAppointmentPage requestAppointmentPage;
	private TestData.PatientInfo testPatient;

	@Before(RunTest.HOOK.SELENIUM_CLINICAL_VISIT)
	public void visitHomePage() {
		testPatient = createTestPatient();
		initiateWithLogin();
		new TestData.TestVisit(testPatient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
	}

	@After(RunTest.HOOK.SELENIUM_CLINICAL_VISIT)
	public void destroy() {
		deletePatient(testPatient);
		quit();
	}

	@Given("a user clicks on active visits link from home page")
	public void launchPatientDashboardActiveVisits() {
		activeVisitsPage = homePage.goToActiveVisitsSearch();
	}

	@When("a user selects a patient from active patient list")
	public void searchActivePatient() {
		activeVisitsPage.search(testPatient.identifier);
		dashboardPage = activeVisitsPage.goToPatientDashboardOfLastActiveVisit();
	}

	@Then("the system loads Patient dashboard page")
	public void launchPatientDashboardPage() {
		assertNotNull(dashboardPage.getActiveVisitList());
	}

	@When("a user clicks visit note link from the patient dashboard")
	public void loadVisitNotePage() {
		visitsDashboardPage = dashboardPage.goToRecentVisits();
		visitNotePage = (VisitNotePage) dashboardPage.goToVisitNote().waitForPage();
	}

	@Then("the system loads visit note page")
	public void systemLoadsVisitNotePage() {
		assertTrue(textExists("Visit Note"));
	}

	@When("a user fills the visit note")
	public void fillVisitNoteForm() {
		visitNotePage.selectProviderAndLocation();
		visitNotePage.addDiagnosis(DIAGNOSIS_PRIMARY);
		visitNotePage.addSecondaryDiagnosis(DIAGNOSIS_SECONDARY);
		visitNotePage.addNote("This is a visit note.");
	}

	@And("a user clicks on save visit note button")
	public void addVisitNote() {
		visitNotePage.save().waitForPage();		
	}

	@Then("the system adds the note into visit note table")
	public void systemAddsVisitNote() {
		assertEquals(DIAGNOSIS_PRIMARY, visitNotePage.primaryDiagnosis());
		assertEquals(DIAGNOSIS_SECONDARY, visitNotePage.secondaryDiagnosis());
		visitsDashboardPage.waitForPageToLoad();
		dashboardPage = visitsDashboardPage.goToPatientDashboard();
	}

	@When("a user clicks on Allergies link from Patient dashboard page")
	public void loadAllergiesPage() {
		allergyPage = (AllergyPage) dashboardPage.clickOnAllergiesWidgetLink().waitForPage();
	}

	@Then("the system loads Allergies board page")
	public void systemLoadsAllergiesPage() {
		assertTrue(textExists("Allergies"));
	}

	@When("a user clicks Add Known Allergy button")
	public void addKnownAllergy() {
		addOrEditAllergyPage = allergyPage.clickOnAddNewAllergy();
		addOrEditAllergyPage.enterDrug(DRUG_NAME);
		addOrEditAllergyPage.drugId();
		addOrEditAllergyPage.enterReaction(REACTION);
		addOrEditAllergyPage.reactionId();
		addOrEditAllergyPage.addAllergyNote("The effect is severe");
	}

	@And("a user clicks on save allergy button")
	public void saveKnownAllergy() {
		allergyPage = addOrEditAllergyPage.clickOnSaveAllergy();
	}

	@Then("the system adds known allergies into the allergies table")
	public void systemAddsKnownAllergy() {
		assertNotNull(addOrEditAllergyPage.getAllergiesList());
		dashboardPage = addOrEditAllergyPage.clickReturn();
	}

	@When("a user clicks on Conditions link from Patient dashboard")
	public void loadManageConditionsPage() {
		conditionsPage = (ConditionsPage) dashboardPage
				.clickOnConditionsWidgetLink().waitForPage();
	}

	@Then("the system loads Manage Conditions Page")
	public void systemLoadsManageConditionsPage() {
		assertTrue(conditionsPage.containsText("Conditions"));
	}

	@When("a user clicks on Add new condition")
	public void userClicksAddNewCondition() {
		conditionPage = (ConditionPage) conditionsPage.clickOnAddNewCondition().waitForPage();
	}

	@And("a user enters patient condition")
	public void enterExistingCondition() {
		conditionPage.typeInCondition(CONDITION);
	}

	@And("a user clicks on save condition button")
	public void saveCondition() {
		conditionPage.clickSave();
	}

	@Then("the system adds New Condition in Conditions table")
	public void systemAddsCondition() {
		assertNotNull(conditionsPage.getConditionsList());
		dashboardPage = conditionsPage.clickReturn();
	}

	@When("a user clicks on Attachments link from patient visits dashboard")
	public void loadAttachmentsPage() {
		visitsDashboardPage = (PatientVisitsDashboardPage) dashboardPage.goToRecentVisits();
		attachmentsPage = (AttachmentsPage) dashboardPage.goToAttachmentsPage().waitForPage();
	}

	@Then("the system loads Attachments page")
	public void systemloadsAttachmentsPage() {
		assertTrue(textExists("Attachments"));
	}

	@When("a user attaches patient supporting file")
	public void addSupportingFile() {
		attachmentsPage.setFileUrl("/home/opensource/Documents/Form.pdf");
		attachmentsPage.addAttachmentNote("Client medical history form");
	}

	@And("a user clicks the upload file button")
	public void clickOnUploadFile() {
		attachmentsPage.clickOnUploadFile();
	}

	@Then("the system uploads the file")
	public void systemAddsSupportingFile() {
		assertNotNull(attachmentsPage.getAttachmentsList());
		dashboardPage = attachmentsPage.goToPatientDashboardPage();
	}

	@When("a user clicks on Request appointment link from Patient dashboard")
	public void loadRequestAppointmentPage() {
		requestAppointmentPage = (RequestAppointmentPage) dashboardPage
				.clickOnRequest();
	}

	@Then("the system loads Request appointment page")
	public void systemloadsRequestAppointmentPage() {
		assertTrue(textExists("Request Appointment"));
	}

	@When("a user fills the Request appointment form")
	public void fillRequestAppointmentForm() {
		requestAppointmentPage.enterAppointmentType(APPOINTMENT_TYPE);
		requestAppointmentPage.enterMinimumValue("1");
		requestAppointmentPage.selectMinimumUnits("Day(s)");
		requestAppointmentPage.enterMaximumValue("2");
		requestAppointmentPage.selectMaximumUnits("Day(s)");
		requestAppointmentPage.addAppointmentRequestNote("Request note");
	}

	@And("a user clicks on save appointment button")
	public void saveAppointmentRequest() {
		dashboardPage = requestAppointmentPage.saveRequest();
	}

	@Then("the system adds Appointment request into the appointment table")
	public void systemAddsAppointmentRequest() {
		assertNotNull(requestAppointmentPage.getAppointmentRequestsList());
	}

	@When("a user clicks on recent visit link")
	public void loadRecentVisitPage() {
		visitsDashboardPage = dashboardPage.goToRecentVisits();
	}

	@Then("the system loads recent visit page")
	public void systemLoadsRecentVisitPage() {
		assertTrue(textExists("Visits"));
	}

	@When("a user clicks on end visit button")
	public void endPatientVisit() {
		visitsDashboardPage.endVisit();
		visitsDashboardPage.waitForPageToLoad();
	}

	@Then("the system ends the patient visit")
	public void systemEndsPatientVisit() {
		assertNull(visitsDashboardPage.getActiveVisit());
		dashboardPage = visitsDashboardPage.goToPatientDashboard();
	}
}
