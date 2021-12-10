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
import org.openqa.selenium.WebElement;

public class AddEditNewRolePage extends Page{
	protected static final String PAGE_URL = "adminui/metadata/configureMetadata/roles/manageRoles.page#/edit/";
	public static final By ENTER_NEW_ROLE = By.cssSelector("#manage-roles ui-view form p:nth-child(1) input");
	public static final By ENTER_DESCRIPTION = By.cssSelector("#manage-roles ui-view form p:nth-child(4) input");
	public static final By FULL_PRIVILEGE_LEVEL_CHECKBOX = By.cssSelector("#manage-roles > ui-view form table:nth-child(7) tbody tr:nth-child(1) td:nth-child(1) input");
	public static final By HIGH_PRIVILEGE_LEVEL_CHECKBOX = By.cssSelector("#manage-roles > ui-view > form > table:nth-child(7) > tbody > tr:nth-child(1) > td:nth-child(2) > input");
	public static final By CANCEL_BUTTON = By.cssSelector("#manage-roles ui-view form p:nth-child(14) button.cancel");
	public static final By SAVE_BUTTON = By.cssSelector("#manage-roles ui-view form button.confirm.right");

	public AddEditNewRolePage(Page parent) {
		super(parent);
	}
	
	public AddEditNewRolePage(Page parent, WebElement waitForStaleness) {
		super(parent, waitForStaleness);
	}

	@Override
	public String getPageUrl() {
		return PAGE_URL;
	}
	
	public ManageRolesPage enterRoleName(String roleName){
		findElement(ENTER_NEW_ROLE).clear();
		findElement(ENTER_NEW_ROLE).sendKeys(roleName);
		return new ManageRolesPage(this);
	}
	
	public ManageRolesPage enterRoleDescription(String roleDescription){
		findElement(ENTER_DESCRIPTION).clear();
		findElement(ENTER_DESCRIPTION).sendKeys(roleDescription);
		return new ManageRolesPage(this);
	}
	
	public ManageRolesPage clickOnFullPrivilegeLevelCheckbox(){
		clickOn(FULL_PRIVILEGE_LEVEL_CHECKBOX);
		return new ManageRolesPage(this);
	}
	
	public ManageRolesPage clickOnHighPrivilegeLevelCheckbox(){
		clickOn(HIGH_PRIVILEGE_LEVEL_CHECKBOX);
		return new ManageRolesPage(this);
	}
	
	public ManageRolesPage clickSaveButton() {
		clickOn(SAVE_BUTTON);
		return new ManageRolesPage(this);
	}
	
	public ManageRolesPage clickCancelButton() {
		clickOn(CANCEL_BUTTON);
		return new ManageRolesPage(this);
	}
	
}
