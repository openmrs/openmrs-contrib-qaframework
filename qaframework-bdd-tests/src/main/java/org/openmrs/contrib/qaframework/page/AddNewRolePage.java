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

	protected static final String PAGE_URL = "/openmrs/admin/users/role.form";
	private static final By ENTER_ROLE_NAME = By.cssSelector("#role");
	private static final By SELECT_OR_UNSELECT_ALL_PRIVILEGES = By.id("toggleSelectionCheckbox");
	private static final By ENTER_DESCRIPTION = By.cssSelector("#content textarea");
	private static final By SAVE = By.cssSelector("#content > form > input[type=submit]");

	public AddNewRolePage(Page parent) {
		super(parent);
	}
	
	public AdministrationManageRolesPage addRoleName(String roleName){
		findElement(ENTER_ROLE_NAME).clear();
		findElement(ENTER_ROLE_NAME).sendKeys(roleName);
		return new AdministrationManageRolesPage(this);
	}
	
	public AdministrationManageRolesPage addDescription(String description){
		findElement(ENTER_DESCRIPTION).clear();
		findElement(ENTER_DESCRIPTION).sendKeys(description);
		return new AdministrationManageRolesPage(this);
	}
	
	public AdministrationManageRolesPage selectPrivileges(){
		clickOn(SELECT_OR_UNSELECT_ALL_PRIVILEGES);
		return new AdministrationManageRolesPage(this);
	}
	
	public AdministrationManageRolesPage saveRole(){
		clickOn(SAVE);
		return new AdministrationManageRolesPage(this);
	}

	@Override
	public String getPageUrl() {
		return PAGE_URL;
	}
}
