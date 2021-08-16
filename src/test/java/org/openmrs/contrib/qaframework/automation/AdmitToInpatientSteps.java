package org.openmrs.contrib.qaframework.automation;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.FindPatientPage;
import org.openmrs.uitestframework.test.TestData.PatientInfo;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdmitToInpatientSteps extends Steps {

	@Before(RunTest.HOOK.SELENIUM)
	public void visitPatientDashboard() {
		createTestPatient();
	}

	@After(RunTest.HOOK.SELENIUM)
	public void destroy() {
		quit();
	}

	@Given("a user clicks on find patient record from home page")
	public void findPatientRecord() {
		findPatientPage = (FindPatientPage) homePage.goToFindPatientRecord()
				.waitForPage();
	}

	@When("a user selects a patient from the patient list")
	public void selectPatientFromPatientList() {
		dashboardPage = findPatientPage.clickOnFirstPatient();
	}

	@Then("the system loads Patient visit dashboard page")
	public void loadPatientVisitDashboardPage() {
		dashboardPage.waitForPageToLoad();
	}

	@When("a user clicks on start visit")
	public void clickOnStartVisit() {
		visitsDashboardPage = dashboardPage.startVisit();
	}

	@And("a user clicks on Admit to Inpatient")
	public void clickOnAdmitToInpatient() {
		admitToInpatientPage = visitsDashboardPage.goToAdmitToInpatient();
	}

	@Then("a user saves the patient to Inpatient ward")
	public void savePatientToInpatientWard() {
		admitToInpatientPage.confirm("Inpatient Ward");
	}
}
