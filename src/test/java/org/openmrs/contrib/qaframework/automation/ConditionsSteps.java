package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.ConditionPage;
import org.openmrs.reference.page.ConditionsPage;
import org.openqa.selenium.By;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ConditionsSteps extends Steps {
	private ConditionsPage conditionsPage;
	private String patientDashboardId;
	private By addNewCondition = By.id("conditionui-addNewCondition");
	private ConditionPage conditionPage;

	@Before(RunTest.HOOK.SELENIUM_CONDITIONS)
	public void visitDashboard() {
		initiatePatientDashboard();
	}

	@After(RunTest.HOOK.SELENIUM_CONDITIONS)
	public void destroy() {
		quit();
	}

	@Given("User clicks on Conditions from Patient dashboard")
	public void launchManageConditions() {
		patientDashboardId = getElement(patientHeaderId).getText();
		conditionsPage = (ConditionsPage) dashboardPage
				.clickOnConditionsWidgetLink().waitForPage();
		matchPatientIds(patientDashboardId);
	}

	@Then("System loads Manage Conditions Page")
	public void systemLoadsManageConditions() {
		assertNotNull(getElement(addNewCondition));
	}

	@And("User clicks on Return")
	public void clickReturn() {
		conditionsPage.clickReturn();
	}

	@Then("System returns to patient dashboard")
	public void returnToDashboard() {
		matchPatientIds(patientDashboardId);
	}

	@And("User clicks on Add new condition")
	public void userClicksAddNewCondition() {
		conditionPage = (ConditionPage) conditionsPage.clickOnAddNewCondition()
				.waitForPage();
	}

	@Then("System on Add New Condition Page")
	public void launchAddNewCondition() {
		assertNotNull(getElement(ConditionPage.SAVE));
	}

	@And("User clicks on cancel")
	public void cancelAddNewCondition() {
		conditionPage.clickCancel();
	}

	@And("User clicks save")
	public void saveCondition() {
		conditionPage.clickSave();
		conditionsPage.waitForPage();
	}

	@And("User enters {string} condition")
	public void enterExistingCondition(String activity) {
		if ("active".equals(activity)) {
			conditionPage.typeInCondition("Diarrhea");
			conditionPage.clickOnActive();
		} else if ("inactive".equals(activity)) {
			conditionPage.typeInCondition("Diabetes mellitus");
			conditionPage.clickOnInActive();
		}
	}

	@Then("Then System on {string} Page")
	public void persist(String page) {
		if ("parent".equals(page)) {
			assertNotNull(getElement(addNewCondition));
		} else if ("current".equals(page)) {
			assertNotNull(getElement(ConditionPage.SAVE));
		}
	}

	@And("User clicks on set inactive button")
	public void setInActive() {
		if (StringUtils.isNotBlank(conditionsPage.getFirstConditionName())) {
			conditionsPage.clickActiveTab();
			conditionsPage.setFirstInActive();
		}
	}

	@And("User clicks on set active button")
	public void setActive() {
		conditionsPage.clickInActiveTab();
		if (StringUtils.isNotBlank(conditionsPage.getFirstConditionName())) {
			conditionsPage.setFirstActive();
		}
	}

	@Then("System should move condition to inactive section")
	public void moveInActive() {
		conditionsPage.clickInActiveTab();
		assertNotNull(conditionsPage.getFirstConditionName());
		assertNotNull(getElement(ConditionsPage.SET_ACTIVE));
	}

	@Then("System should move condition to active section")
	public void moveActive() {
		conditionsPage.clickActiveTab();
		if (StringUtils.isNotBlank(conditionsPage.getFirstConditionName())) {
			assertNotNull(getElement(ConditionsPage.SET_INACTIVE));
		}
	}

	@And("User edits active")
	public void editActive() {
		if (StringUtils.isNotBlank(conditionsPage.getFirstConditionName())) {
			conditionPage = (ConditionPage) conditionsPage.editFirstActive()
					.waitForPage();
			conditionPage.clickOnInActive();
			conditionPage.clickSave();
			conditionsPage.waitForPage();
		}
	}

	@And("User edits inactive")
	public void editInactive() {
		if (StringUtils.isNotBlank(conditionsPage.getFirstConditionName())) {
			conditionPage = (ConditionPage) conditionsPage.editFirstInActive()
					.waitForPage();
			conditionPage.clickOnActive();
			conditionPage.clickSave();
			conditionsPage.waitForPage();
		}
	}

	@Then("System should edit all active adjustable fields")
	public void successiveActiveEdition() {
		if (StringUtils.isNotBlank(conditionsPage.getFirstConditionName())) {
			assertNotNull(getElement(ConditionsPage.SET_INACTIVE));
			assertNotNull(conditionsPage.getFirstConditionName());
		}
	}

	@Then("System should edit all inactive adjustable fields")
	public void successiveInactiveEdition() {
		if (StringUtils.isNotBlank(conditionsPage.getFirstConditionName())) {
			assertNotNull(conditionsPage.getFirstConditionName());
			assertNotNull(getElement(ConditionsPage.SET_ACTIVE));
		}
	}

	@And("User clicks delete condition")
	public void delete() {
		String name = conditionsPage.getFirstConditionName();
		if (StringUtils.isNotBlank(name)) {
			conditionsPage.deleteFirstActive();
		}
		driver.findElement(By.cssSelector(".confirm")).click();

		conditionsPage.clickInActiveTab();
		name = conditionsPage.getFirstConditionName();
		if (StringUtils.isNotBlank(name)) {
			conditionsPage.deleteFirstInActive();
		}
		driver.findElement(By.cssSelector(".confirm")).click();
	}

	@Then("System should trash first condition")
	public void SuccessfulDeletion() {
		String name = conditionsPage.getFirstConditionName();
		if (StringUtils.isNotBlank(name)) {
			assertNull(driver.findElement(By.linkText(name)));
		}

		conditionsPage.clickInActiveTab();
		name = conditionsPage.getFirstConditionName();
		if (StringUtils.isNotBlank(name)) {
			assertNull(driver.findElement(By.linkText(name)));
		}
	}
}
