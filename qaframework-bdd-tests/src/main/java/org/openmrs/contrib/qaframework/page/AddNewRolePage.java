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

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;

public class AddNewRolePage extends Page{

	private static final String PAGE_URL = "/openmrs/admin/users/role.form";
	private static final By ROLE_NAME_FIELD = By.cssSelector("#role");
	private static final By SELECT_OR_UNSELECT_ALL_PRIVILEGES = By.id("toggleSelectionCheckbox");
	private static final By DESCRIPTION_FIELD = By.cssSelector("#content textarea");
	private static final By SAVE_BUTTON = By.cssSelector("#content form input[type=submit]");

	public AddNewRolePage(Page parent) {
		super(parent);
	}
	
	public void addRoleName(String roleName) {
		findElement(ROLE_NAME_FIELD).clear();
		findElement(ROLE_NAME_FIELD).sendKeys(roleName);
	}
	
	public void addDescription(String description) {
		findElement(DESCRIPTION_FIELD).clear();
		findElement(DESCRIPTION_FIELD).sendKeys(description);
	}
	
	public void selectPrivileges() {
		clickOn(SELECT_OR_UNSELECT_ALL_PRIVILEGES);
	}
	
	public void saveRole() {
		clickOn(SAVE_BUTTON);
	}

	@Override
	public String getPageUrl() {
		return PAGE_URL;
	}
}
