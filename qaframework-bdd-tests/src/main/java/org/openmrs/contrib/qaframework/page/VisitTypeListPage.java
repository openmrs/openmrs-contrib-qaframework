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

import java.util.ArrayList;
import java.util.List;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class VisitTypeListPage extends AdminManagementPage {

	private static final By ERROR = By.cssSelector("span.error");
	private static final By VISIT_TYPE_LIST = By.cssSelector("#content form table tbody tr td");
	private static final By RETIRED_VISIT_TYPE_LIST = By.cssSelector("#content form table tbody tr td del");
	private static final By ALREADY_SAVED_VISIT_TYPE_LINK = By.cssSelector("#content tr:nth-child(3) a");

	public VisitTypeListPage(Page parent) {
		super(parent);
		ADD = By.cssSelector("#content a[href=\"visitType.form\"]");
	}

	public void waitForError() {
		waitForElement(ERROR);
	}

	public VisitTypePage addVisitType() {
		clickOn(ADD);
		return new VisitTypePage(this);
	}

	public VisitTypePage goToVisitType(String name) {
		findElement(By.linkText(name)).click();
		return new VisitTypePage(this);
	}

	public List<String> getVisitTypeList() {
		List<String> visitTypeList = new ArrayList<String>();
		driver.navigate().refresh();
		for (WebElement webElement : findElements(VISIT_TYPE_LIST)) {
			visitTypeList.add(webElement.getText());
		}
		return visitTypeList;
	}
//
	public List<String> getRetiredVisitTypeList() {
		List<String> retiredVisitTypeList = new ArrayList<String>();
		for (WebElement webElement : findElements(RETIRED_VISIT_TYPE_LIST)) {
			retiredVisitTypeList.add(webElement.getText());
		}
		return retiredVisitTypeList;
	}

	public VisitTypePage clickAlreadySavedVisitType() {
		clickOn(ALREADY_SAVED_VISIT_TYPE_LINK);
		return new VisitTypePage(this);
	}

	@Override
	public String getPageUrl() {
		return "/admin/visits/visitType.list";
	}
}

