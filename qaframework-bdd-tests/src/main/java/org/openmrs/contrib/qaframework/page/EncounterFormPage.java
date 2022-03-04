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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EncounterFormPage extends Page {

	private static final String URL = "/admin/encounters/encounter.form";
	private static final By PATIENT_FIELD= By.id("patientId_id_selection");
	private static final By FIRST_AUTOCOMPLETE_RESULT = By.cssSelector("body ul li a span");
	private static final By ENCOUNTER_DATE_TIME_FIELD= By.xpath("//input[@name='encounterDatetime']");
	private static final By ROLE_SELECT = By.id("roleIds[0]");
	private static final By PROVIDER_NAME_FIELD = By.name("providerName");
	private static final By SAVE_ENCOUNTER_BUTTON = By.id("saveEncounterButton");
	private static final By DELETE_CHECKBOX = By.id("voided");
	private static final By VOID_REASON_FIELD = By.name("voidReason");
	private static final By CLOSE_CALENDER_BUTTON = By.xpath("//button[@class='ui-datepicker-close ui-state-default ui-priority-primary ui-corner-all']");

	public EncounterFormPage(Page page) {
		super(page);
	}

	public void enterPatient(String patient) {
		findElement(PATIENT_FIELD).sendKeys(patient);
		waitForElement(FIRST_AUTOCOMPLETE_RESULT);
		clickOn(FIRST_AUTOCOMPLETE_RESULT);
	}

	public void enterEncounterDateTime(String ENCOUNTER_DATE_TIME) {
		waiter.until(ExpectedConditions.elementToBeClickable(ENCOUNTER_DATE_TIME_FIELD));
		clickOn(ENCOUNTER_DATE_TIME_FIELD);
		setTextToFieldNoEnter(ENCOUNTER_DATE_TIME_FIELD, ENCOUNTER_DATE_TIME);
		clickOn(CLOSE_CALENDER_BUTTON);
	}

	public void pressReturnKey() {
		WebElement dateTimeElement = driver.findElement(By.xpath("//input[@name='encounterDatetime']"));
		dateTimeElement.sendKeys(Keys.RETURN);
	}

	public void selectRole(String role) {
		selectFrom(ROLE_SELECT, role);
	}

	public void enterProviderName(String providerName) {
		findElement(PROVIDER_NAME_FIELD).sendKeys(providerName);
		waitForElement(FIRST_AUTOCOMPLETE_RESULT);
		clickOn(FIRST_AUTOCOMPLETE_RESULT);
	}

	public void clickOnSaveEncounter() {
		clickOn(SAVE_ENCOUNTER_BUTTON);
	}

	public void checkDelete() {
		clickOn(DELETE_CHECKBOX);
	}

	public void enterVoidReason(String voidReason) {
		setTextField(voidReason, VOID_REASON_FIELD);
	}

	private void setTextField(String text, By by) {
		findElement(by).clear();
		findElement(by).sendKeys(text);
	}

	@Override
	public String getPageUrl() {
		return URL;
	}
}
