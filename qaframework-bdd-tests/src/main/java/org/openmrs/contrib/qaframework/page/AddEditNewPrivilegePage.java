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

public class AddEditNewPrivilegePage extends Page{

	private static final String PAGE_URL = "/adminui/metadata/privileges/privilege.page";
	private static final By PRIVILEGE_FIELD = By.cssSelector("#privilege-field");
	private static final By DESCRIPTION_FIELD = By.cssSelector("#description-field");
	private static final By SAVE_BUTTON = By.cssSelector("#save-button");
	
	public AddEditNewPrivilegePage(Page parent) {
		super(parent);
	}
	
	public void enterPrivilegeName(String privilegeName) {
		findElement(PRIVILEGE_FIELD).clear();
		findElement(PRIVILEGE_FIELD).sendKeys(privilegeName);
	}
	
	public void enterPrivilegeDescription(String privilegeDescription) {
		findElement(DESCRIPTION_FIELD).clear();
		findElement(DESCRIPTION_FIELD).sendKeys(privilegeDescription);
	}
	
	public void clickSaveButton() {
		clickOn(SAVE_BUTTON);
	}
	@Override
	public String getPageUrl() {
		return PAGE_URL;
	}
}
