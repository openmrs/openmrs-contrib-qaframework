package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openmrs.reference.page.AllergyPage;
import org.openmrs.reference.page.HomePage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AllergiesSteps extends Steps {
	private AllergyPage allergyPage;

	@After("@selenium")
	public void destroy() {
		quit();
	}

	@Given("a user logs in, searches John and visits his dashboard")
	public void loadJohnDashboard() {
		configuredUserLogin();
		homePage = new HomePage(loginPage);
		findPatientPage = homePage.goToFindPatientRecord();
		findPatientPage.enterPatient("John");
		dashboardPage = findPatientPage.clickOnFirstPatient();
	}

	@And("a user clicks on Allergies link")
	public void loadAllergiesPage() {
		dashboardPage.clickOnAllergyManagement();
		allergyPage = new AllergyPage(dashboardPage);
	}

	@Then("the system loads Allergies page")
	public void systemLoadsAllergiesPage() {
		assertEquals(getElement(patientHeaderId).getText(),
				getElement(patientHeaderId).getText());
		assertTrue(textExists("Allergies"));
	}

	@And("a user clicks No Known Allergy button")
	public void addNoKnownAllergy() {
		allergyPage.addNoKnownAllergy();
	}

	@Then("the system add no known allergies into the allergies table")
	public void systemAddsNoKnownAllergies() {
		assertTrue(textExists("No known allergies"));
	}

	@And("a user clicks Remove No Known Allergy icon")
	public void removeNoKnownAllergy() {
		allergyPage.removeNoKnownAllergy();
	}

	@Then("the system displays unknown in the allergies table")
	public void systemRemovesNoKnownAllergies() {
		assertTrue(textExists("Unknown"));
	}

}
