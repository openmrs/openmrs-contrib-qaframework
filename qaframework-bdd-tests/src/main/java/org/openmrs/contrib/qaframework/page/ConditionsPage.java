/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.page;

import java.util.List;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConditionsPage extends Page {

	public static final By ADD_NEW_CONDITION = By.id("conditionui-addNewCondition");
	public static final By SET_ACTIVE = By.cssSelector("#INACTIVE tbody.ng-scope button");
	public static final By SET_INACTIVE = By.cssSelector("#ACTIVE tbody.ng-scope button");
	private static final By RETURN = By.cssSelector(".actions .cancel");
	private static final By TAB_ACTIVE = By.cssSelector("a[href='#ACTIVE']");
	private static final By TAB_INACTIVE = By.cssSelector("a[href='#INACTIVE']");
	private static final By FIRST_CONDITION_NAME = By.cssSelector("#ACTIVE tbody.ng-scope td:nth-child(1)");
	private static final By EDIT_ACTIVE_CONDITION = By.cssSelector("#ACTIVE tbody:nth-child(3) i.icon-pencil.edit-action.ng-scope");
	private static final By EDIT_INACTIVE_CONDITION = By.cssSelector("#INACTIVE td:nth-child(4) i.icon-pencil.edit-action.ng-scope");
	private static final By DELETE_FIRST_ACTIVE_CONDITION = By.cssSelector("#ACTIVE tbody:nth-child(3) i.icon-remove.delete-action.ng-scope");
	private static final By DELETE_FIRST_INACTIVE_CONDITION = By.cssSelector("#INACTIVE td:nth-child(4) i.icon-remove.delete-action.ng-scope");
	private static final By CONDITIONS_LIST = By.cssSelector("tr.clickable-tr");
	private static final By CONFIRM_DELETE_CONDITION_BUTTON = By.cssSelector("#remove-condition-dialog > div.dialog-content > button.confirm.right");

	public ConditionsPage(ClinicianFacingPatientDashboardPage clinicianFacingPatientDashboardPage) {
		super(clinicianFacingPatientDashboardPage);
	}

	public ConditionsPage(ConditionPage conditionPage) {
		super(conditionPage);
	}

	@Override
	public String getPageUrl() {
		return "/coreapps/conditionlist/manageConditions.page";
	}

	public ClinicianFacingPatientDashboardPage clickReturn() {
		clickOn(RETURN);
		return new ClinicianFacingPatientDashboardPage(this);
	}

	public String getFirstConditionName() {
		try {
			 waiter.until(ExpectedConditions.elementToBeClickable(FIRST_CONDITION_NAME));
			return driver.findElement(FIRST_CONDITION_NAME).getAttribute("innerText");
		} catch (Exception e) {
			return null;
		}
	}

	public void clickActiveTab() {
		clickOn(TAB_ACTIVE);
	}

	public void clickInActiveTab() {
		waiter.until(ExpectedConditions.elementToBeClickable(TAB_INACTIVE));
		clickOn(TAB_INACTIVE);
	}

	public void setFirstActive() {
		clickOn(SET_ACTIVE);
	}

	public void setFirstInActive() {
		clickOn(SET_INACTIVE);
	}

	public ConditionPage editFirstActive() {
		clickOn(EDIT_ACTIVE_CONDITION);
		return new ConditionPage(this);
	}

	public ConditionPage editFirstInActive() {
		waiter.until(ExpectedConditions.elementToBeClickable(EDIT_INACTIVE_CONDITION));
		clickOn(EDIT_INACTIVE_CONDITION);
		return new ConditionPage(this);
	}

	public void deleteFirstActive() {
		clickOn(DELETE_FIRST_ACTIVE_CONDITION);
	}

	public void deleteFirstInActive() {
		waiter.until(ExpectedConditions.elementToBeClickable(DELETE_FIRST_INACTIVE_CONDITION));
		clickOn(DELETE_FIRST_INACTIVE_CONDITION);
	}

	public ConditionPage clickOnAddNewCondition() {
		clickOn(ADD_NEW_CONDITION);
		return new ConditionPage(this);
	}

	public List<WebElement> getConditionsList() {
		return findElements(CONDITIONS_LIST);
	}

	public void confirmDeleteCondition() {
		clickOn(CONFIRM_DELETE_CONDITION_BUTTON);
	}
}
