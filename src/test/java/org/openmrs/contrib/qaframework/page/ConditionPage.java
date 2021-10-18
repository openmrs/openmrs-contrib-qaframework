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

public class ConditionPage extends Page {

	public static final By SAVE = By.id("addConditionBtn");
	private static final By CANCEL = By.className("cancel");
	private static final By CONDITION = By.id("conceptId-input");
	private static final By ICON_CALENDAR = By.cssSelector(".date .icon-calendar");
	private static final By ICON_CALENDAR_TODATE = By.cssSelector("td.day.active");
	private static final By ACTIVE = By.id("status-1");
	private static final By INACTIVE = By.id("status-2");
	private static final By FIRST_CONDITION = By.cssSelector(".dropdown-menu:first-of-type li a:first-of-type");

	public ConditionPage(ConditionsPage conditionsPage) {
		super(conditionsPage);
	}

	@Override
	public String getPageUrl() {
		return "/coreapps/conditionlist/addCondition.page";
	}

	public ConditionsPage clickCancel() {
		clickOn(CANCEL);
		return new ConditionsPage(this);
	}

	public void typeInCondition(String conditionText) {
		clickOn(CONDITION);
		findElement(CONDITION).sendKeys(conditionText);
		clickOn(FIRST_CONDITION);
	}

	public void clickSave() {
		clickOn(SAVE);
	}

	public void clickOnActive() {
		clickOn(ACTIVE);
	}

	public void clickOnInActive() {
		clickOn(INACTIVE);
		List<WebElement> elements = findElements(ICON_CALENDAR);
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).click();
			findElements(ICON_CALENDAR_TODATE).get(i).click();
		}
	}
}
