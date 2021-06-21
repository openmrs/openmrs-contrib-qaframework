package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.FindPatientPage;
import org.openmrs.reference.page.PatientCaptureVitalsPage;
import org.openmrs.uitestframework.test.TestData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertTrue;

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

	@And("a user enters normal patient vitals")
	public void enterNormalPatientVitals() {
	  patientCaptureVitalsPage.setHeightField("154");
	  patientCaptureVitalsPage.setWeightField("65");
	  patientCaptureVitalsPage.setTemperatureField("34");
	  patientCaptureVitalsPage.setPulseField("80");
	  patientCaptureVitalsPage.setRespiratoryField("60");
	  patientCaptureVitalsPage.setBloodPressureFields("120", "110");
	  patientCaptureVitalsPage.setBloodOxygenSaturationField("94");
	}

	@And("a user enters abnormal patient vitals")
	public void enterAbnormalPatientVitals() {
	  patientCaptureVitalsPage.setHeightField("300");
	  assertThat(patientCaptureVitalsPage.getValidationErrors(), hasItem("Maximum: 272"));
	  patientCaptureVitalsPage.clearPatientHeight();
	  patientCaptureVitalsPage.setHeightField("260");  
	  patientCaptureVitalsPage.setWeightField("280");
	  assertThat(patientCaptureVitalsPage.getValidationErrors(), hasItem("Maximum: 250")); 
	  patientCaptureVitalsPage.clearPatientWeight();
	  patientCaptureVitalsPage.setWeightField("230");  
	  patientCaptureVitalsPage.setTemperatureField("45");
	  assertThat(patientCaptureVitalsPage.getValidationErrors(), hasItem("Maximum: 43"));
	  patientCaptureVitalsPage.clearPatientTemperature();
	  patientCaptureVitalsPage.setTemperatureField("42");  
	  patientCaptureVitalsPage.setPulseField("120"); 
	  patientCaptureVitalsPage.setRespiratoryField("12");  
	  patientCaptureVitalsPage.setBloodPressureFields("120", "180");
	  assertThat(patientCaptureVitalsPage.getValidationErrors(), hasItem("Maximum: 150"));
	  patientCaptureVitalsPage.clearPatientBloodPressure2();
	  patientCaptureVitalsPage.setBloodPressureFields("120", "145"); 
	  patientCaptureVitalsPage.setBloodOxygenSaturationField("60");
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
