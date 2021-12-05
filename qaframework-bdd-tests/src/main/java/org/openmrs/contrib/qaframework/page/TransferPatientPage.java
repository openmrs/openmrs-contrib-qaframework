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

public abstract class TransferPatientPage extends Page {

	private static final String URL = "htmlformentryui/htmlform/enterHtmlFormWithStandardUi.page";
	private static final By SELECT_LOCATION = By.id("w5");
	private static final By SAVE = By.cssSelector(".submitButton");
	private final Page parent;

	public TransferPatientPage(Page parent) {
		super(parent);
		this.parent = parent;
	}

	@Override
	public String getPageUrl() {
		return URL;
	}

	public Page confirm(String location) {
		selectFrom(SELECT_LOCATION, location);
		clickOn(SAVE);
		return parent;
	}
}
