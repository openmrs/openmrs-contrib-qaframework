package org.openmrs.contrib.qaframework.automation;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.FindPatientPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class EditPatientRelationshipSteps extends Steps {

	private FindPatientPage findpatientPage;

	private ClinicianFacingPatientDashboardPage clinicianFacingPatientDashboardPage;

	@Before(RunTest.HOOK.SELENIUM_LOGIN)
	public void systemLogin() {
		initiateWithLogin();
	}

	@Then("System loads clicianFacingPatientDashboardPage")
	public void loadClinicianFacingPatientDashboardPage() {
		matchPatientIds(firstPatientIdentifier);

	}

	@After(RunTest.HOOK.SELENIUM_DASHBOARD)
	public void destroy() {
		quit();
	}

	@Then("User clicks on editRegistration")
	public void clickEditPatientRegistration() {

	}
	@Then("User clicks on EditRelationship")
	public void clickEditRelationship() {

	}

	@And("User click on find Relationshiptype")
	public void findRelationshipType() {

	}

}
