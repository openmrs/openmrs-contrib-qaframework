/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.automation;
 
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.contrib.qaframework.page.ConditionPage;
import org.openmrs.contrib.qaframework.page.ConditionsPage;
import org.openmrs.contrib.qaframework.page.FindPatientPage;
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
    private TestData.PatientInfo testPatient;

    @Before(RunTest.HOOK.SELENIUM_CONDITION)
    public void visitDashboard() {
        testPatient = createTestPatient();
        initiateWithLogin();
        findPatientPage = (FindPatientPage) homePage.goToFindPatientRecord().waitForPage();
        findPatientPage.enterPatient(testPatient.identifier);
        dashboardPage = (ClinicianFacingPatientDashboardPage) findPatientPage.clickOnFirstPatient().waitForPage();
    }
 
    @After(RunTest.HOOK.SELENIUM_CONDITION)
    public void destroy() {
        deletePatient(testPatient);
        quit();
    }
    
	@Given("User clicks on Conditions from Patient dashboard")
	public void launchManageConditions() {
		conditionsPage = (ConditionsPage) dashboardPage.clickOnConditionsWidgetLink().waitForPage();
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
		conditionPage = (ConditionPage) conditionsPage.clickOnAddNewCondition().waitForPage();
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
	}
	
	@Then ("System adds New Condition in Conditions table")
	public void systemEntersExistingConditionToTable(){
		assertNotNull(conditionsPage.getConditionsList());
		dashboardPage = conditionsPage.clickReturn();
	}

	@And("User enters active condition")
	public void enterActiveCondition() {
			conditionPage.typeInCondition("Diarrhea");
			conditionPage.clickOnActive();
	}
	
	@And("User enters inactive condition")
	public void enterInactiveCondition(){
			conditionPage.typeInCondition("Diabetes mellitus");
			conditionPage.clickOnInActive();
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
		if (StringUtils.isNotBlank(conditionsPage.getFirstConditionName())) {
			assertNotNull(getElement(ConditionsPage.SET_ACTIVE));
		}
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
			conditionPage = conditionsPage.editFirstActive();
			conditionPage.clickOnInActive();
			conditionPage.clickSave();
		}
	}

	@And("User edits inactive")
	public void editInactive() {
		if (StringUtils.isNotBlank(conditionsPage.getFirstConditionName())) {
			conditionPage = conditionsPage.editFirstInActive();
			conditionPage.clickOnActive();
			conditionPage.clickSave();
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

	@And("User clicks delete first active condition")
	public void deleteFirstActiveCondition() {
		if (StringUtils.isNotBlank(conditionsPage.getFirstConditionName())) {
			conditionsPage.deleteFirstActive();
			conditionsPage.confirmDeleteCondition();
		}
	}

   @And("User clicks delete first inactive condition")
   public void deleteFirstInactiveCondition(){
		if (StringUtils.isNotBlank(conditionsPage.getFirstConditionName())) {
			conditionsPage.deleteFirstInActive();
			conditionsPage.confirmDeleteCondition();
		}
	}

	@Then("System should trash first active condition")
	public void SuccessfullyDeleteActiveCondition() {
		String name = conditionsPage.getFirstConditionName();
		if (StringUtils.isNotBlank(name)) {
			assertNull(driver.findElement(By.xpath(name)));
		}
	}
		
	@Then("System should trash first inactive condition")
		public void SuccessfullyDeleteInctiveCondition() {
		String name = conditionsPage.getFirstConditionName();
		if (StringUtils.isNotBlank(name)) {
			assertNull(driver.findElement(By.xpath(name)));
		}
	}
}
