package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertNotNull;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.FindPatientPage;
import org.openqa.selenium.By;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class FindPatientSteps extends Steps {

	@Before(RunTest.HOOK.SELENIUM_LOGIN)
	public void systemLogin() {
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_LOGIN)
	public void destroy() {
		quit();
	}

	@Given("User clicks on Find Patient App")
	public void visitFindPatientPage() {
		findPatientPage = (FindPatientPage) homePage.goToFindPatientRecord()
				.waitForPage();
	}

	@And("User enters missing patient")
	public void enterMissingPatient() {
		findPatientPage.enterPatient("MissingPatient");
	}

	@Then("Search Page returns no patients")
	public void noPatients() {
		assertNotNull(getElement(By.className("dataTables_empty")));
	}

	@And("User enters {string} patientName")
	public void enterPatientName(String patientName) {
		findPatientPage.enterPatient(patientName);
	}

	@Then("Search Page returns patients")
	public void returnResults() {
		firstPatientIdentifier = findPatientPage.getFirstPatientIdentifier();
		assertNotNull(firstPatientIdentifier);
	}

	@And("User clicks on first patient")
	public void clickFirstPatient() {
		dashboardPage = findPatientPage.clickOnFirstPatient();
	}

	@Then("System loads patient dashboard")
	public void loadPatientDashboard() {
		matchPatientIds(firstPatientIdentifier);
	}
}
