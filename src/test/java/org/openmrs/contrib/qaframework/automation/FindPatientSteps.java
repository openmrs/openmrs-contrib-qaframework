package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openmrs.reference.page.HomePage;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FindPatientSteps extends Steps {

	@After("@selenium")
	public void destroy() {
		quit();
	}

	@When("Search user rightly logs in")
	public void patientSearchLogin() {
		goToLoginPage();
		loginPage.login(testProperties.getUsername(),
				testProperties.getPassword(), "Registration Desk");
	}

	@And("User clicks on Find Patient App")
	public void visitFindPatientPage() {
		homePage = new HomePage(loginPage);
		findPatientPage = homePage.goToFindPatientRecord();
	}

	@And("User enters missing patient")
	public void enterMissingPatient() {
		findPatientPage.enterPatient("MissingPatient");
	}

	@Then("Search Page returns no patients")
	public void noPatients() {
		assertNotNull(getElement(By.className("dataTables_empty")));
	}

	@And("User enters John")
	public void enterJohn() {
		findPatientPage.enterPatient("John");
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
		assertEquals(trimPatientId(firstPatientIdentifier),
				trimPatientId(getElement(patientHeaderId).getText()));
	}

	private String trimPatientId(String id) {
		id = id.replace("Recent", "");
		if (id.indexOf("[") > 0) {
			id = id.split("\\[")[0];
		}
		if (id.indexOf(" ") > 0) {
			id = id.split(" ")[0];
		}
		return id;
	}
}
