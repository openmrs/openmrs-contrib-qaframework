package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.ActiveVisitsPage;
import org.openmrs.reference.page.AddOrEditAllergyPage;
import org.openmrs.reference.page.AllergyPage;
import org.openmrs.reference.page.AttachmentsPage;
import org.openmrs.reference.page.ConditionPage;
import org.openmrs.reference.page.ConditionsPage;
import org.openmrs.reference.page.PatientVisitsDashboardPage;
import org.openmrs.reference.page.RequestAppointmentPage;
import org.openmrs.reference.page.VisitNotePage;
import org.openmrs.uitestframework.test.TestData;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
		new TestData.TestVisit(testPatient.uuid, TestData.getAVisitType(),
				getLocationUuid(homePage)).create();
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
		dashboardPage = activeVisitsPage
				.goToPatientDashboardOfLastActiveVisit();
	}

	@Then("the system loads Patient dashboard page")
	public void launchPatientDashboardPage() {
		assertNotNull(dashboardPage.getActiveVisitList());
	}

	// User story: Complete visit note
	@When("a user clicks visit note link from the patient dashboard")
	public void loadVisitNotePage() {
		visitsDashboardPage = dashboardPage.goToRecentVisits();
		visitNotePage = (VisitNotePage) dashboardPage.goToVisitNote()
				.waitForPage();
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
		visitNotePage.save();
	}

	@Then("the system adds the note into visit note table")
	public void systemAddsVisitNote() {
		assertEquals(DIAGNOSIS_PRIMARY, visitNotePage.primaryDiagnosis());
		assertEquals(DIAGNOSIS_SECONDARY, visitNotePage.secondaryDiagnosis());
		visitsDashboardPage.waitForPageToLoad();
		dashboardPage = visitsDashboardPage.goToPatientDashboard();
	}

	// User story: Add known allergies
	@When("a user clicks on Allergies link from Patient dashboard page")
	public void loadAllergiesPage() {
		allergyPage = (AllergyPage) dashboardPage.clickOnAllergiesWidgetLink()
				.waitForPage();
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

	// User story: Add known condition
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
		conditionPage = (ConditionPage) conditionsPage.clickOnAddNewCondition()
				.waitForPage();
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

	// User story: Attach supporting document
	@When("a user clicks on Attachments link from patient visits dashboard")
	public void loadAttachmentsPage() {
		visitsDashboardPage = (PatientVisitsDashboardPage) dashboardPage
				.goToRecentVisits();
		attachmentsPage = (AttachmentsPage) dashboardPage.goToAttachmentsPage()
				.waitForPage();
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

	// User story: Book an appointment
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

	// User story: End patient visit
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

	// User story: Adding New Allergy
	@When("user clicks on Allergies link from Patient dashboard")
	public void launchManageAllergies() {
		allergyPage = (AllergyPage) dashboardPage.clickOnAllergiesWidgetLink()
				.waitForPage();
	}

	@Then("system loads Allergies table page")
	public void systemLoadsManageAllergies() {
				getElement(patientHeaderId).getText();
		assertTrue(textExists("Allergies"));
	}

	@When("a user clicks Add New Allergy button")
	public void addNewAllergy() {
		addOrEditAllergyPage = allergyPage.clickOnAddNewAllergy();
		addOrEditAllergyPage.enterDrug(DRUG_NAME);
		addOrEditAllergyPage.drugId();
	}

	@And("the user clicks on the Save button")
	public void saveAllergy() {
		addOrEditAllergyPage.clickOnSaveAllergy();
	}

	@Then("the system adds a new Allergy")
	public void systemAddsAllergy() {
		assertTrue(textExists("Codeine"));
	}

	// User story: Add New Condition
	@When("a user clicks on Conditions on Patient dashboard")
	public void loadManageConditions() {
		conditionsPage = (ConditionsPage) dashboardPage
				.clickOnConditionsWidgetLink().waitForPage();
	}

	@Then("system loads Conditions Page")
	public void loadsManageConditionsPage() {
		assertTrue(conditionsPage.containsText("Conditions"));
	}

	@When("user clicks on Add new condition button")
	public void clicksAddNewCondition() {
		conditionPage = (ConditionPage) conditionsPage.clickOnAddNewCondition()
				.waitForPage();
	}

	@And("user enters patient condition")
	public void userEntersExistingCondition() {
		conditionPage.typeInCondition(CONDITION);
	}

	@And("user clicks on save button")
	public void clicksSaveCondition() {
		conditionPage.clickSave();
	}

	@Then("system adds New Condition in Conditions table")
	public void systemAddsNewCondition() {
		assertNotNull(conditionsPage.getConditionsList());
		dashboardPage = conditionsPage.clickReturn();
	}

	// User story: Adding and deleting New Allergy2
	@When("a user clicks on Allergy2 link from Patient dashboard page2")
	public void loadManageAllergy2() {
		allergyPage = (AllergyPage) dashboardPage.clickOnAllergiesWidgetLink()
				.waitForPage();
	}

	@Then("the system loads Allergy2 board page")
	public void loadsManageAllergy2() {
		assertEquals(getElement(patientHeaderId).getText(),
				getElement(patientHeaderId).getText());
		assertTrue(textExists("Allergies"));
	}

	@When("a user clicks Add New Allergy2 button")
	public void addNewAllergy2() {
		addOrEditAllergyPage = allergyPage.clickOnAddNewAllergy();
		addOrEditAllergyPage.enterDrug(DRUG_NAME);
		addOrEditAllergyPage.drugId();
	}

	@And("the user clicks on the Save button2")
	public void saveAllergy2() {
		addOrEditAllergyPage.clickOnSaveAllergy();
	}

	@Then("the system adds a new Allergy2")
	public void systemAddsAllergy2() {
		assertTrue(textExists("Penicillins"));
	}

	@When("the user clicks on the delete button from the patient dashboard")
	public void deleteAllergy2() {
		allergyPage.clickOnDeleteAllergy();
	}

	@And("the system loads Remove Allergy dashboard")
	public void confirmDeleteAllergy2() {
		allergyPage.clickOnConfirmDeleteAllergy();
	}

	@Then("system displays no allergy in the Allergies table")
	public void systemRemovesAllergy2() {
		assertTrue(textExists("Unknown"));
	}
	
	// User story: Adding and deleting New Condition2
	@When("a user clicks on Condition2 link from Patient dashboard page")
	public void loadManageCondition2() {
		conditionsPage = (ConditionsPage) dashboardPage
				.clickOnConditionsWidgetLink().waitForPage();
	}

	@Then("the system loads Condition2 board page")
	public void loadsManageConditionsPage2() {
		assertTrue(conditionsPage.containsText("Conditions"));
	}

	@When("a user clicks Add New Condition2 button")
	public void clicksAddNewCondition2() {
		conditionPage = (ConditionPage) conditionsPage.clickOnAddNewCondition()
				.waitForPage();
	}
	@And("the system loads Add New Condition2 dashboard")
	public void userEntersExistingCondition2() {
		conditionPage.typeInCondition(CONDITION);
	}

	@And("user clicks on the Save button2")
	public void clicksSaveCondition2() {
		conditionPage.clickSave();
	}

	@Then("the system adds a new Condition2")
	public void systemAddsNewCondition2() {
		assertNotNull(conditionsPage.getConditionsList());
		dashboardPage = conditionsPage.clickReturn();
	}

	@When("the user clicks on the delete button from dashboard")
	public void deleteCondition2() {
		conditionsPage.clickOn(By.className("delete-action"));
	}

	@Then("user clicks on the yes button to confirm")
	public void deleteCondition2Confirmation() {
		conditionsPage.confirmDeleteCondition();
	}

}
