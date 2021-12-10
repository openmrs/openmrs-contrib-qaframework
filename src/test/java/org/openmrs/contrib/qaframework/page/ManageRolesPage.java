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

public class ManageRolesPage extends Page{
	protected static final String PAGE_URL = "adminui/metadata/configureMetadata/roles/manageRoles.page#/list";
	public static final By ADD_NEW_ROLE = By.cssSelector("#manage-roles > ui-view > button");
	public static final By EDIT_ROLE = By.cssSelector("#list-roles tr:nth-child(1) a:nth-child(1)");
	public static final By DELETE_ROLE = By.cssSelector("#list-roles > tbody > tr:nth-child(1) > td:nth-child(3) > a.right > i");
	public static final By FIRST_ROLE_NAME = By.cssSelector("//tbody/tr[1]/td[1]");
	private static final By CONFIRM_DELETE_ROLE_BUTTON = By.cssSelector("#ngdialog2 > div.ngdialog-content > div.dialog-content > div > button.confirm.right");

	
	public ManageRolesPage(Page configureMetadataPage) {
		super(configureMetadataPage);
	}
	
	public AddEditNewRolePage goToAddNewROle(){
		clickOn(ADD_NEW_ROLE);
		return new AddEditNewRolePage(this);
	}
	
	public void goToEditRole(){
		clickOn(EDIT_ROLE);
	}
	
	public void deleteRole(){
		clickOn(DELETE_ROLE);
	}
	
	public void confirmDeleteCondition() {
		clickOn(CONFIRM_DELETE_ROLE_BUTTON);
	}

	@Override
	public String getPageUrl() {
		return PAGE_URL;
	}
	
}

