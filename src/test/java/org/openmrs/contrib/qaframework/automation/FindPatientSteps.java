package org.openmrs.contrib.qaframework.automation;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.FindPatientPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.uitestframework.page.LoginPage;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FindPatientSteps extends Steps {

	LoginPage loginPage;
	FindPatientPage findPatientPage;
	String firstPatientIdentifier;
	ClinicianFacingPatientDashboardPage dashboardPage;

	@When("Search user rightly logs in")
	public void patientSearchLogin() throws Exception {
		startWebDriver();
		goToLoginPage();
		loginPage = getLoginPage();
		loginPage.login(testProperties.getUsername(), testProperties.getPassword(), "Registration Desk");
	}

	@And("User clicks on Find Patient App")
	public void visitFindPatientPage() throws InterruptedException {
		homePage = new HomePage(loginPage);
		findPatientPage = homePage.goToFindPatientRecord();
	}


	@And("User enters missing patient")
	public void enterMissingPatient() throws InterruptedException {
		findPatientPage.enterPatient("MissingPatient");
	}


	@Then("Search Page returns no patients")
	public void noPatients() throws InterruptedException {
		assertNotNull(getElement(By.className("dataTables_empty")));
		quitBrowser();
	}

	@And("User enters John")
	public void enterJohn() throws InterruptedException {
		findPatientPage.enterPatient("John");
	}


	@Then("Search Page returns patients")
	public void returnResults() throws InterruptedException {
		firstPatientIdentifier = findPatientPage.getFirstPatientName();
		assertNotNull(firstPatientIdentifier);
	}

	@And("User clicks on first patient")
	public void clickFirstPatient() throws InterruptedException {
		dashboardPage = findPatientPage.clickOnFirstPatient();
	}

	@Then("System loads patient dashboard")
	public void loadPatientDashboard() throws InterruptedException {
		assertEquals(firstPatientIdentifier, By.cssSelector("div.identifiers > span"));
		quitBrowser();
	}
}
