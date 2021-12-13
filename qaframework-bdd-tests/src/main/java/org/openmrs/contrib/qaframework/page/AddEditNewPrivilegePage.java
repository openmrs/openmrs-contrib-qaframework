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

public class AddEditNewPrivilegePage extends Page{
	protected static final String PAGE_URL = "/adminui/metadata/privileges/privilege.page?action=add&";
	public static final By ENTER_PRIVILEGE_FIELD = By.cssSelector("#privilege-field");
	public static final By ENTER_DESCRIPTION_FIELD = By.cssSelector("#description-field");
	public static final By SAVE_BUTTON = By.cssSelector("#save-button");
	public static final By CANCEL_BUTTON = By.cssSelector("#privilegeForm input.cancel");
	
	public AddEditNewPrivilegePage(Page parent) {
		super(parent);
	}
	
	public AddEditNewPrivilegePage(Page parent, WebElement waitForStaleness) {
		super(parent, waitForStaleness);
	}
	
	public ManagePrivilegesPage enterPrivilegeName(String privilegeName){
		findElement(ENTER_PRIVILEGE_FIELD).clear();
		findElement(ENTER_PRIVILEGE_FIELD).sendKeys(privilegeName);
		return new ManagePrivilegesPage(this);
	}
	
	public ManagePrivilegesPage enterPrivilegeDescription(String privilegeDescription){
		findElement(ENTER_DESCRIPTION_FIELD).clear();
		findElement(ENTER_DESCRIPTION_FIELD).sendKeys(privilegeDescription);
		return new ManagePrivilegesPage(this);
	}
	
	public ManagePrivilegesPage clickSaveButton() {
		clickOn(SAVE_BUTTON);
		return new ManagePrivilegesPage(this);
	}

	@Override
	public String getPageUrl() {
		return PAGE_URL;
	}
}
