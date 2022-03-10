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
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FindPatientPage extends Page {

	private static final By PATIENT_NAME_SEARCH_RESULT = By.cssSelector("#patient-search-results-table tbody tr:first-child td:nth-child(2)");
	private static final By PATIENT_ID_SEARCH_RESULT = By.cssSelector("#patient-search-results-table tr:first-child td:first-child");
	private static final By PATIENT_SEARCH = By.id("patient-search");

	public FindPatientPage(Page parent) {
		super(parent);
	}

	public void enterPatient(String patient) {
		setTextToFieldNoEnter(PATIENT_SEARCH, patient);
	}

	public ClinicianFacingPatientDashboardPage clickOnFirstPatient() {
		waiter.until(ExpectedConditions.elementToBeClickable(PATIENT_NAME_SEARCH_RESULT));
		clickOn(PATIENT_NAME_SEARCH_RESULT);
		return new ClinicianFacingPatientDashboardPage(this);
	}

	public ManageAppointmentsPage clickOnFirstPatientAppointment() {
		clickOn(PATIENT_NAME_SEARCH_RESULT);
		return new ManageAppointmentsPage(this);
	}

	/**
	 * Finds first record from the result table
	 * 
	 * @return patient id
	 */
	public String getFirstPatientIdentifier() {
		// let's wait for the name to appear as the identifier selector is ambiguous and may select the loading image
		getFirstPatientName();
		return findElement(PATIENT_ID_SEARCH_RESULT).getText();
	}

	public String getFirstPatientName() {
		return findElement(PATIENT_NAME_SEARCH_RESULT).getText();
	}

	public void search(String text) {
		setTextToFieldNoEnter(PATIENT_SEARCH, text);
	}

	@Override
	public String getPageUrl() {
		return "/coreapps/findpatient/findPatient.page";
	}
}
