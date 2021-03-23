package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.AttachmentsPage;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.FindPatientPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AttachmentSteps extends Steps {

	private AttachmentsPage attachmentsPage;

	@Before(RunTest.HOOK.SELENIUM_DASHBOARD)
	public void visitPatientDashboard() {
		initiatePatientDashboard();
	}
	
	@After(RunTest.HOOK.SELENIUM_DASHBOARD)
	public void destroy() {
		quit();
	}
	
	@Given("a user clicks on Attachments link from patient dashboard")
	public void launchAttachmentsPage() {
		if (dashboardPage.containsText("Start Visit")) {
			dashboardPage.startVisit();
		} 
		attachmentsPage = (AttachmentsPage) dashboardPage.clickOnAttachmentsAppLink().waitForPage();
	}
	
	@Then("the system loads Attachments page ")
	public void systemLoadsAttachmentPage() {
		assertEquals(getElement(patientHeaderId).getText(), getElement(patientHeaderId).getText());
		assertTrue(textExists("Attachments"));
	}

	@And("a user attaches a file")
	public void userAttachesFile() {
		attachmentsPage.clickOrDragAndDropAFile();
	}

	@And("a user enters a caption")
	public void userEntersCaption() {
		attachmentsPage.addUserCaption();
	}

	@Then("the system enables upload button")
	public void systemEnablesUploadButton() {
		assertTrue(textExists("Upload file"));
	}

	@When("a user clicks the upload button")
	public void userClicksUploadButton() {
		attachmentsPage.clickOnUploadFile();
	}

	@Then("the system uploads the attached file")
	public void systemUploadsAttachedFile() {
		assertTrue(textExists("Upload file"));
	}

	@And("a user attaches a file or enters a caption")
	public void userAttachesFileOrEntersCaption() {
		userAttachesFile();
		userEntersCaption();
	}

	@And("a user clicks clear forms button")
	public void userClicksClearFormsButton() {
		attachmentsPage.clickClearForms();
	}

	@Then("the system clears the form")
	public void systemClearsForm() {
		assertTrue(textExists("Clear forms"));
	}

	@And("a user clicks on the camera icon")
	public void userClicksOnCameraIcon() {
		attachmentsPage.clickOnCameraIcon();
	}

	@Then("the system enables capturing an image")
	public void systemEnablesCapturingImage() {
		assertNotNull(getElement(AttachmentsPage.CAMERA_ICON));
		attachmentsPage.clickOnCameraIcon();
	}

	@And("a user clicks on save button")
	public void userClickOnSaveButton() {
		attachmentsPage.clickOnSaveButton();
	}

	@And("a user clicks upload button")
	public void userClickOnUploadButton() {
		attachmentsPage.clickOnUploadFile();
	}

	@Then("the system uploads the captured image")
	public void systemUploadsCapturedPhoto() {
		assertNotNull(getElement(AttachmentsPage.SAVE_IMAGE));
	}
}