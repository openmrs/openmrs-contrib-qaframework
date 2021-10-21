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

public class RegistrationSummaryPage extends Page {

	private static final By EDIT_RELATIONSHIP_LINK = By.id("relationships-info-edit-link");

	public RegistrationSummaryPage(Page parent) {
		super(parent);
	}

	public EditPatientRelationshipPage goToEditPatientRelationship() {
		clickOnLast(EDIT_RELATIONSHIP_LINK);
		return new EditPatientRelationshipPage(this);
	}

	@Override
	public String getPageUrl() {
		return "/registrationapp/registrationSummary.page";
	}
}
