package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.FindPatientPage;
import org.openmrs.reference.page.PatientCaptureVitalsPage;
import org.openmrs.uitestframework.test.TestData;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class VitalsAndTriagingSteps extends Steps {

	private PatientCaptureVitalsPage patientCaptureVitalsPage;
	private TestData.PatientInfo testPatient;

	@Before(RunTest.HOOK.SELENIUM_VITALS)
	public void visitPatientDashboard() {
	  testPatient = createTestPatient();
	  initiateWithLogin();	  
	  findPatientPage = (FindPatientPage) homePage.goToFindPatientRecord().waitForPage();
	  findPatientPage.enterPatient(testPatient.identifier);
	  findPatientPage.waitForPageToLoad();
	  dashboardPage = findPatientPage.clickOnFirstPatient();
	  dashboardPage.startVisit().waitForPage();
	}

	@After(RunTest.HOOK.SELENIUM_VITALS)
	public void destroy() {
	  deletePatient(testPatient);
	  quit();
	}

	@Given("a user clicks on Capture Vitals link from Patient dashboard")
	public void launchVitalsPage() {
	  patientCaptureVitalsPage = (PatientCaptureVitalsPage) dashboardPage.goToPatientCaptureVitalsPage().waitForPage();
	}

	@Then("the system loads Vitals page")
	public void systemloadsVitalsPage() {
	  assertTrue(textExists("Vitals"));
	}

	@And("a user enters patient vitals")
	public void enterPatientVitals() {
	  patientCaptureVitalsPage.setHeightField("154");
	  patientCaptureVitalsPage.setWeightField("65");
	  patientCaptureVitalsPage.setTemperatureField("34");
	  patientCaptureVitalsPage.setPulseField("80");
	  patientCaptureVitalsPage.setRespiratoryField("60");
	  patientCaptureVitalsPage.setBloodPressureFields("120", "110");
	  patientCaptureVitalsPage.setBloodOxygenSaturationField("94");
	}

	@And("a user clicks on save button")
	public void savePatientVitals() {
	  patientCaptureVitalsPage.confirm();
	  patientCaptureVitalsPage.save();
	}

	@Then("the system adds patient vitals into the vitals table")
	public void systemAddsPatientVitals() {	  
	  assertTrue(patientCaptureVitalsPage.containsText("Vitals"));	
	}
}
