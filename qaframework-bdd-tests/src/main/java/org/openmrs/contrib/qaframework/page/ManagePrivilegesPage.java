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

public class ManagePrivilegesPage extends Page{
	protected static final String PAGE_URL = "adminui/metadata/privileges/managePrivileges.page";
	public static final By ADD_NEW_PRIVILEGE = By.cssSelector("#content > input");
	public static final By EDIT_PRIVILEGE = By.cssSelector("i.icon-pencil.edit-action");
	public static final By DELETE_PRIVILEGE = By.cssSelector("i.icon-trash.delete-action.right");
	public static final By SEARCH_CREATED_PRIVILEGE = By.cssSelector("#list-privileges_filter > label > input[type=text]");
	public static final By CONFIRM_DELETION = By.cssSelector("#purgePrivilegeForm > div > button.confirm.right");
	
	public ManagePrivilegesPage(Page configureMetadataPage) {
		super(configureMetadataPage);
	}
	
	public AddEditNewPrivilegePage goToAddNewPrivilege(){
		clickOn(ADD_NEW_PRIVILEGE);
		return new AddEditNewPrivilegePage(this);
	}
	
	public void confirmPrivilegeDelete(){
		clickOn(CONFIRM_DELETION);
	}
	
	public void goToEditPrivilege(){
		clickOn(EDIT_PRIVILEGE);
	}
	
	public void searchForPrivilege(String searchInput){
		setText(SEARCH_CREATED_PRIVILEGE, searchInput);
	}
	
	public void deletePrivilege(){
		clickOn(DELETE_PRIVILEGE);
	}

	@Override
	public String getPageUrl() {
		return PAGE_URL;
	}
}
