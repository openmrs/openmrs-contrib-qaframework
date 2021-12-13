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

public class AdministrationManageRolesPage extends Page{
	protected static final String PAGE_URL = "/openmrs/admin/users/role.list";
	private static final By ADD_NEW_ROLE = By.cssSelector("#content > a");
	private static final By DELETE_SELECTED_ROLES = By.cssSelector("#content > form > input[type=submit]");
	private static final By CHECKBOX = By.cssSelector("#content > form > table > tbody input[type=checkbox]");
	private static final By EDIT_ROLE = By.cssSelector("#content > form > table > tbody > tr:nth-child(3) > td:nth-child(2) > a");

	public AdministrationManageRolesPage(Page parent) {
		super(parent);
	}
	
	public AddNewRolePage goToaddNewRole(){
		clickOn(ADD_NEW_ROLE);
		return new AddNewRolePage(this);
	}
	
	public void goToEditRole(){
		clickOn(EDIT_ROLE);
	}
	
	public void deleteSelectedRoles(){
		clickOn(DELETE_SELECTED_ROLES);
	}
	
	public void selectRole(){
		clickOn(CHECKBOX);
	}

	@Override
	public String getPageUrl() {
		return PAGE_URL;
	}
	
}