package org.openmrs.contrib.qaframework.automation;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.FindPatientPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class EditPatientRelationshipSteps extends Steps {

	private FindPatientPage findpatientPage;

	private ClinicianFacingPatientDashboardPage clinicianFacingPatientDashboardPage;

	@Before(RunTest.HOOK.SELENIUM_LOGIN)
	public void systemLogin() {
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_DASHBOARD)
	public void destroy() {
		quit();
	}

	@Given("System loads clinicianFacingPatientDashboardPage")
	public void loadClinicianFacingPatientDashboardPage() throws Exception {

	}
	@When("User clicks on editRegistrationInformation")
	public void clickEditRegistrationInformation() {

	}
	@And("User clicks on EditRelationships")
	public void clickEditRelationships() {

	}

	@And("User clicks on   Relationshiptype")
	public void findRelationshipType() {

	}
	@Then("system loads back to patientDashboard")
	public void loadPatientDashboard() {

	}

}
