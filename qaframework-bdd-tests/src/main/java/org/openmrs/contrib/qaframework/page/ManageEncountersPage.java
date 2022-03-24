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

public class ManageEncountersPage extends Page {

	private static final String URL = "/admin/encounters/index.htm";
	private static final By ADD_ENCOUNTER_LINK = By.linkText("Add Encounter");

	public ManageEncountersPage(Page page) {
		super(page);
	}

	public EncounterFormPage clickOnAddEncounter() {
		clickOn(ADD_ENCOUNTER_LINK);
		return new EncounterFormPage(this);
	}

	@Override
	public String getPageUrl() {
		return URL;
	}
}
