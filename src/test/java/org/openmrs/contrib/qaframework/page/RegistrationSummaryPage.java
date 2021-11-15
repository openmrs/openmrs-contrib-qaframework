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

	private static final By EDIT_DEMOGRAPHICS = By.cssSelector("#edit-patient-demographics a");
	private static final By SHOW_CONTACT_INFO = By.id("patient-header-contactInfo");
	private static final By EDIT_CONTACT_INFO = By.id("contact-info-inline-edit");
	private static final By EDIT_RELATIONSHIP_LINK = By.id("relationships-info-edit-link");
	private static final By PATIENT_GIVENNAME = By.cssSelector("#content div span.PersonName-givenName");
	private static final By PATIENT_FAMILYNAME = By.cssSelector(".patient-header .demographics .name .PersonName-familyName");
	private static final By TELEPHONE_NUMBER_TEXT = By.cssSelector("#coreapps-telephoneNumber");

	public RegistrationSummaryPage(Page parent) {
		super(parent);
	}

	public RegistrationEditSectionPage clickOnEditDemographics() {
		clickOn(EDIT_DEMOGRAPHICS);
		return new RegistrationEditSectionPage(this);
	}
	
	public void clickOnShowContact() {
		clickOn(SHOW_CONTACT_INFO);
	}
	
	public RegistrationEditSectionPage clickOnEditContact() {
		clickOn(EDIT_CONTACT_INFO);
		return new RegistrationEditSectionPage(this);
	}
	
	public EditPatientRelationshipPage goToEditPatientRelationship() {
		clickOn(EDIT_RELATIONSHIP_LINK);
		return new EditPatientRelationshipPage(this);
	}
	
	public String getPatientGivenName() {
		return findElement(PATIENT_GIVENNAME).getText();
	}
	
	public String getPatientFamilyName() {
		return findElement(PATIENT_FAMILYNAME).getText();
	}
	
	public String getTelephoneNumber() {
		return findElement(TELEPHONE_NUMBER_TEXT).getText();
	}

	@Override
	public String getPageUrl() {
		return "/registrationapp/registrationSummary.page";
	}
}
