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
import org.openqa.selenium.WebDriver;

/**
 * Created by tomasz on 25.05.15.
 */
public class CaptureVitalsPage extends Page {

	private static final By PATIENT_SEARCH = By.id("patient-search");
	private static final By FIRST_FOUND_PATIENT = By.cssSelector("i.icon-vitals");

	public CaptureVitalsPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getPageUrl() {
		return "/coreapps/findpatient/findPatient.page?app=referenceapplication.vitals";
	}

	// public void search(String text) {
	// WebElement searchField = findElement(PATIENT_SEARCH);
	// try {
	// searchField.sendKeys(text);
	// } catch(Exception e) {
	// e.printStackTrace();
	// }
	// clickOn(FIRST_FOUND_PATIENT);
	// }
}
