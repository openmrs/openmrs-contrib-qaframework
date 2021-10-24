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

public class CreateNewUserPage extends Page {

	private static final By PERSON_GIVEN_NAME = By
			.name("person.names[0].givenName");
	private static final By PERSON_FAMILY_NAME = By
			.name("person.names[0].familyName");
	private static final By PASSWORD = By.name("userFormPassword");
	private static final By CONFIRM = By.name("confirm");
	private static final By SAVE_BUTTON = By.id("saveButton");
	private static final By GENDER_FEMALE = By.id("F");
	private static final By USERNAME = By.name("username");

	public CreateNewUserPage(Page parent) {
		super(parent);
	}

	@Override
	public String getPageUrl() {
		return AddEditUserPage.URL + "?createNewPerson=true";
	}

	public void fillInPersonName(String givenName, String familyName,
			String username, String password) {
		findElement(USERNAME).clear();
		findElement(USERNAME).sendKeys(username);
		findElement(PERSON_GIVEN_NAME).clear();
		findElement(PERSON_GIVEN_NAME).sendKeys(givenName);
		findElement(PERSON_FAMILY_NAME).clear();
		findElement(PERSON_FAMILY_NAME).sendKeys(familyName);
		clickOn(GENDER_FEMALE);
		findElement(PASSWORD).clear();
		findElement(PASSWORD).sendKeys(password);
		findElement(CONFIRM).clear();
		findElement(CONFIRM).sendKeys(password);
		clickOn(SAVE_BUTTON);
	}
}
