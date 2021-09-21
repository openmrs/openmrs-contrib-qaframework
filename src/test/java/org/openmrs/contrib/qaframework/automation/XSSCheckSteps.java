package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.ConditionPage;
import org.openmrs.reference.page.ConditionsPage;
import org.openqa.selenium.UnhandledAlertException;

import static org.junit.Assert.assertTrue;

public class XSSCheckSteps extends Steps {
	private static final String ALERT_MESSAGE_SCRIPT = "<script>alert(\"XSS is executed\");</script>";
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

	@Then("user enters a malicious code")
	public void enterMaliciousCCondition() {
		try {
			conditionPage.typeInCondition(ALERT_MESSAGE_SCRIPT);
		} catch (UnhandledAlertException e) {
			assertTrue(e.getAlertText().equals(ALERT_MESSAGE_SCRIPT));
		}
	}
}
