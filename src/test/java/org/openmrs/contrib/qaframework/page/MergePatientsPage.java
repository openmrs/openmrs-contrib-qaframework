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

public class MergePatientsPage extends Page {
	
	private final static By MERGE_PATIENT_ELECTRONIC_RECORD = By.id("coreapps-mergePatientsHomepageLink-app");
	
	private final static By ID_PATIENT_1 = By.id("patient1-text");
	
	private final static By ID_PATIENT_2 = By.id("patient2-text");
	
	private final static By CONTINUE = By.id("confirm-button");
	
	private final static By MERGE_PATIENT = By.id("second-patient");
	
	private final static By NO = By.id("cancel-button");
	
	private final static By SEARCH = By.id("patient-search");
	
	public MergePatientsPage(Page page) {
		super(page);
	}
	
	public void enterPatient1(String patient1) {
		setText(ID_PATIENT_1, patient1);
	}
	
	public void enterPatient2(String patient2) {
		setText(ID_PATIENT_2, patient2);
	}
	
	public PatientVisitsDashboardPage clickOnContinue() {
		waitForElementToBeEnabled(CONTINUE);
		clickOn(CONTINUE);
		return new PatientVisitsDashboardPage(this);
	}
	
	public void clickOnMergePatient() {
		waitForElement(MERGE_PATIENT);
		clickOn(MERGE_PATIENT);
	}
	
	public void clickOnNo() {
		clickOn(NO);
	}
	
	public void waitCont() throws InterruptedException {
		clickOn(CONTINUE);
	}
	
	public void searchId(String id) {
		setTextToFieldNoEnter(SEARCH, id);
	}
	
	public String search() {
		return findElement(SEARCH).getText();
	}
	
	@Override
	public String getPageUrl() {
		return "/datamanagement/mergePatients.page";
	}
}
