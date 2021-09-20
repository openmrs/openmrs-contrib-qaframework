package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.ConditionPage;
import org.openmrs.reference.page.ConditionsPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class XSSCheckSteps extends Steps {
	private static final String ALERT_1_SCRIPT = "<script>alert(\"XSS is executed\");</script>";
	private ConditionPage conditionPage;
	private ConditionsPage conditionsPage;
	private String patientDashboardId;

	@Before(RunTest.HOOK.SELENIUM_DASHBOARD)
	public void visitDashboard() {
		initiatePatientDashboard();
	}

	@After(RunTest.HOOK.SELENIUM_DASHBOARD)
	public void destroy() {
		quit();
	}

	@Given("user clicks on Add Conditions Icon from Patient dashboard")
	public void launchManageConditions() {
		patientDashboardId = getElement(patientHeaderId).getText();
		conditionsPage = (ConditionsPage) dashboardPage
				.clickOnConditionsWidgetLink().waitForPage();
		matchPatientIds(patientDashboardId);
	}

	@When("user clicks on Add new condition")
	public void AddMaliciousCondition() {
		conditionPage = (ConditionPage) conditionsPage.clickOnAddNewCondition()
				.waitForPage();
	}

	@And("user enters a malicious code")
	public void enterMaliciousCCondition() {
		conditionPage.typeInCondition(ALERT_1_SCRIPT);
	}

	@Then("System does not execute the XSS")
	public void systemThrowsXSS() {
		assertThat(isAlertDisplayed(), is(false));
	}

	public boolean isAlertDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, 10 /* timeout in seconds */);
		boolean foundAlert = false;
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		} catch (TimeoutException e) {
			System.out.println("alert is present");
		}
		return foundAlert;
	}
}