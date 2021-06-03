package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.PatientCaptureVitalsPage;
import org.openmrs.reference.page.PatientVisitsDashboardPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CaptureVitalsSteps extends Steps{
	
	private ClinicianFacingPatientDashboardPage patientDashboardPage;
	private PatientVisitsDashboardPage patientVisitsDashboardPage;
	private PatientCaptureVitalsPage patientCaptureVitalsPage;
	
	@Before(RunTest.HOOK.SELENIUM_DASHBOARD)
	public void visitDashboard() {
		initiatePatientDashboard();
	}

	@After(RunTest.HOOK.SELENIUM_DASHBOARD)
	public void destroy() {
		quit();
	}
	
	@Given("a user clicks on Capture Vitals link from Patient dashboard")
	public void loadVitalsPage() {
		patientVisitsDashboardPage = patientDashboardPage.startVisit();
		patientCaptureVitalsPage = patientDashboardPage.goToPatientCaptureVitalsPage();  
	}

	@Then("the system loads Vitals page")
	public void systemloadsVitalsPage() {
		assertEquals(getElement(patientHeaderId).getText(), getElement(patientHeaderId).getText());
		assertTrue(textExists("Vitals"));	    
	}

	@And("a user enters patient vitals")
	public void enterPatientVitals() {
	  patientCaptureVitalsPage.setHeightField("180");
        patientCaptureVitalsPage.setWeightField("77");
        patientCaptureVitalsPage.setTemperatureField("35.8");
        patientCaptureVitalsPage.setPulseField("124");
        patientCaptureVitalsPage.setRespiratoryField("115");
        patientCaptureVitalsPage.setBloodPressureFields("122", "75");
        patientCaptureVitalsPage.setBloodOxygenSaturationField("54");    
	}

	@And("a user clicks on save button")
	public void savePatientVitals() {
	  patientCaptureVitalsPage.confirm();
        patientCaptureVitalsPage.save();    
	}

	@Then("the system adds patient vitals into the vitals table")
	public void systemsAddsPatientVitals() {
		assertTrue(textExists("Last Vitals"));
	}
}
