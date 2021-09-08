package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertNotNull;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.FindPatientPage;
import org.openmrs.uitestframework.test.TestData;
import org.openqa.selenium.By;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class FindPatientSteps extends Steps {

	private TestData.PatientInfo testPatient;

	@Before(RunTest.HOOK.SELENIUM_FINDPATIENT)
	public void systemLogin() {
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_FINDPATIENT)
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
		findPatientPage.enterPatient("Missing patient");
		findPatientPage.waitForPage();
	}

	@And("User enters John Smith")
	public void enterJohnSmith() {
		findPatientPage.enterPatient("John Smith");
		findPatientPage.waitForPage();
	}

	@Then("Search Page returns patients")
	public void returnResults() {
		firstPatientIdentifier = findPatientPage.getFirstPatientIdentifier();
		findPatientPage.waitForPage();
		assertNotNull(firstPatientIdentifier);
	}

	@And("User clicks on first patient")
	public void clickFirstPatient() {
		dashboardPage = findPatientPage.clickOnFirstPatient();

	}

	@Then("System loads patient dashboard")
	public void loadPatientDashboard() {
		matchPatientIds(firstPatientIdentifier);
		dashboardPage.waitForPageToLoad();
	}
}
