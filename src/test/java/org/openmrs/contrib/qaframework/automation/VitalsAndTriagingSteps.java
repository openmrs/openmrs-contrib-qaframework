package org.openmrs.contrib.qaframework.automation;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.FindPatientPage;
import org.openmrs.contrib.qaframework.page.PatientCaptureVitalsPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VitalsAndTriagingSteps extends Steps {

	private PatientCaptureVitalsPage patientCaptureVitalsPage;
	private TestData.PatientInfo testPatient;

	@Before(RunTest.HOOK.SELENIUM_VITALS)
	public void visitPatientDashboard() throws InterruptedException {
		testPatient = createTestPatient();
		initiateWithLogin();
		findPatientPage = (FindPatientPage) homePage.goToFindPatientRecord()
				.waitForPage();
		findPatientPage.enterPatient(testPatient.identifier);
		Thread.sleep(50000);
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
	public void loadVitalsPage() {
		patientCaptureVitalsPage = (PatientCaptureVitalsPage) dashboardPage
				.goToPatientCaptureVitalsPage().waitForPage();
	}

	@Then("the system loads Vitals page")
	public void systemloadsVitalsPage() {
		assertTrue(textExists("Vitals"));
	}

	@When("a user enters normal patient vitals")
	public void enterNormalPatientVitals() {
		patientCaptureVitalsPage.setHeightField("154");
		patientCaptureVitalsPage.setWeightField("65");
		patientCaptureVitalsPage.setTemperatureField("34");
		patientCaptureVitalsPage.setPulseField("80");
		patientCaptureVitalsPage.setRespiratoryField("60");
		patientCaptureVitalsPage.setBloodPressureFields("120", "110");
		patientCaptureVitalsPage.setBloodOxygenSaturationField("94");
	}

	@When("a user enters a vital below minimum value and the system alerts until valid")
	public void crossCheckMinimumVitalsValidity() {
		patientCaptureVitalsPage.setHeightField("5");
		assertThat(patientCaptureVitalsPage.getValidationErrors(),
				hasItem("Minimum: 10"));
		patientCaptureVitalsPage.clearPatientHeight();
		patientCaptureVitalsPage.setHeightField("15");
		patientCaptureVitalsPage.setWeightField("-10");
		assertThat(patientCaptureVitalsPage.getValidationErrors(),
				hasItem("Minimum: 0"));
		patientCaptureVitalsPage.clearPatientWeight();
		patientCaptureVitalsPage.setWeightField("7");
		patientCaptureVitalsPage.setTemperatureField("20");
		assertThat(patientCaptureVitalsPage.getValidationErrors(),
				hasItem("Minimum: 25"));
		patientCaptureVitalsPage.clearPatientTemperature();
		patientCaptureVitalsPage.setTemperatureField("28");
		patientCaptureVitalsPage.setPulseField("-5");
		assertThat(patientCaptureVitalsPage.getValidationErrors(),
				hasItem("Minimum: 0"));
		patientCaptureVitalsPage.clearPatientPulse();
		patientCaptureVitalsPage.setPulseField("5");
		patientCaptureVitalsPage.setRespiratoryField("-10");
		assertThat(patientCaptureVitalsPage.getValidationErrors(),
				hasItem("Minimum: 0"));
		patientCaptureVitalsPage.clearPatientRespiratoryRate();
		patientCaptureVitalsPage.setRespiratoryField("4");
		patientCaptureVitalsPage.setBloodPressureFields("60", "25");
		assertThat(patientCaptureVitalsPage.getValidationErrors(),
				hasItem("Minimum: 30"));
		patientCaptureVitalsPage.clearPatientBloodPressure2();
		patientCaptureVitalsPage.setBloodPressureFields("60", "40");
		patientCaptureVitalsPage.setBloodOxygenSaturationField("5");
	}

	@When("a user enters a vital above maximum value and the system alerts until valid")
	public void crossCheckMaximumVitalsValidity() {
		patientCaptureVitalsPage.setHeightField("300");
		assertThat(patientCaptureVitalsPage.getValidationErrors(),
				hasItem("Maximum: 272"));
		patientCaptureVitalsPage.clearPatientHeight();
		patientCaptureVitalsPage.setHeightField("170");
		patientCaptureVitalsPage.setWeightField("280");
		assertThat(patientCaptureVitalsPage.getValidationErrors(),
				hasItem("Maximum: 250"));
		patientCaptureVitalsPage.clearPatientWeight();
		patientCaptureVitalsPage.setWeightField("75");
		patientCaptureVitalsPage.setTemperatureField("45");
		assertThat(patientCaptureVitalsPage.getValidationErrors(),
				hasItem("Maximum: 43"));
		patientCaptureVitalsPage.clearPatientTemperature();
		patientCaptureVitalsPage.setTemperatureField("36.5");
		patientCaptureVitalsPage.setPulseField("245");
		assertThat(patientCaptureVitalsPage.getValidationErrors(),
				hasItem("Maximum: 230"));
		patientCaptureVitalsPage.clearPatientPulse();
		patientCaptureVitalsPage.setPulseField("78");
		patientCaptureVitalsPage.setRespiratoryField("1004");
		assertThat(patientCaptureVitalsPage.getValidationErrors(),
				hasItem("Maximum: 99"));
		patientCaptureVitalsPage.clearPatientRespiratoryRate();
		patientCaptureVitalsPage.setRespiratoryField("14");
		patientCaptureVitalsPage.setBloodPressureFields("120", "180");
		assertThat(patientCaptureVitalsPage.getValidationErrors(),
				hasItem("Maximum: 150"));
		patientCaptureVitalsPage.clearPatientBloodPressure2();
		patientCaptureVitalsPage.setBloodPressureFields("120", "80");
		patientCaptureVitalsPage.setBloodOxygenSaturationField("60");
	}

	@And("a user clicks on save button")
	public void savePatientVitals() {
		patientCaptureVitalsPage.confirm();
		patientCaptureVitalsPage.save();
		patientCaptureVitalsPage.waitForPage();
	}

	@Then("the system adds patient vitals into the vitals table")
	public void systemAddsPatientVitals() {
		assertTrue(patientCaptureVitalsPage.containsText("Vitals"));
	}
}
