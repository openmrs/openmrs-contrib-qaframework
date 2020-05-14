package org.openmrs.contrib.qaframework.automation;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openmrs.contrib.qaframework.page.ConditionsPage;
import org.openmrs.reference.page.HomePage;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConditionsSteps extends Steps {
	private ConditionsPage conditionsPage;
	private String patientDashboardId;
	private By addNew = By.id("conditionui-addNewCondition");

	@Given("User logs in, searches John and visits first patient dashboard")
	public void visitFirstJohnsDashboard() {
		goToLoginPage().login(testProperties.getUsername(),
				testProperties.getPassword(), testProperties.getLocation());
		homePage = new HomePage(loginPage);
		findPatientPage = homePage.goToFindPatientRecord();
		findPatientPage.enterPatient("John");
		dashboardPage = findPatientPage.clickOnFirstPatient();
		patientDashboardId = getElement(patientHeaderId).getText();
	}

	@And("User clicks on Conditions")
	public void launchManageConditions() {
		elementClickOn(ConditionsPage.MANAGE_CONDITIONS_ENDPOINT);
		conditionsPage = new ConditionsPage(dashboardPage);
		assertEquals(patientDashboardId, getElement(patientHeaderId).getText());
	}

	@Then("System loads Manage Conditions")
	public void systemLoadsManageConditions() {
		assertNotNull(getElement(addNew));
	}

	@And("User clicks on Return")
	public void clickReturn() {
		conditionsPage.clickReturn();
	}

	@Then("System returns to patient dashboard")
	public void returnToDashboard() {
		assertEquals(patientDashboardId, getElement(patientHeaderId).getText());
	}
}
