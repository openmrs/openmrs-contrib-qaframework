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

import org.apache.commons.lang3.StringUtils;
import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageFormsPage extends Page {

	public static final By ADD = By.linkText("Add");
	private static final By SAVE = By.cssSelector("input[type=\"submit\"]");
	private static final By EXTENSION_FORM_LABEL = By.id("extensionForm.label");
	private static final By EXTENSION_FORM_ICON = By.id("extensionForm.icon");
	private static final By DELETE = By
			.cssSelector("i.icon-remove.delete-action");
	private static String EDIT_FORM = "//i[@onclick=\"location.href='forms/extension.page?formId=";
	private static String REST_PATH = "&extensionId=patientDashboard.overallActions.form.";
	private static String DELETE_FORM = "//i[@onclick=\"location.href='forms/deleteExtension.page?formId=";
	private static By EDIT_FORM_PATH;
	private static By DELETE_FORM_PATH;
	private static String REST_PATH_2 = "'\"]";
	private static By DATAPICKER_YEAR = By.className("ui-datepicker-year");
	private static By DATAPICKER_MONTH = By.className("ui-datepicker-moth");
	private static By DATAPICKER_DAY = By.className("ui-state-default");
	private static By WHERE_EYE_REPORT = By.id("w5");
	private static By WHO_EYE_REPORT = By.name("w3");
	private static By ENTER_FORM = By.cssSelector("input.submitButton");
	private static By CURRENT_DAY = By.className("ui-state-highlight");
	private static By PATIENT = By.className("name");
	public String id;

	public ManageFormsPage(WebDriver driver) {
		super(driver);
	}

	public void add() {
		clickOn(ADD);
	}

	public void addLabel(String lab) {
		driver.findElement(EXTENSION_FORM_LABEL).clear();
		driver.findElement(EXTENSION_FORM_LABEL).sendKeys(lab);
	}

	public void save() throws InterruptedException {
		clickOn(SAVE);
	}

	public void addIcon(String icon) {
		driver.findElement(EXTENSION_FORM_ICON).clear();
		driver.findElement(EXTENSION_FORM_ICON).sendKeys(icon);
	}

	public boolean addPresent() {
		try {
			return driver.findElement(ADD) != null;
		} catch (Exception ex) {
			return false;
		}
	}

	public void delete() {
		clickOn(DELETE);
	}

	public void formIdFromUrl() {
		String url = driver.getCurrentUrl();
		id = StringUtils.substringAfter(url, "formId=");
	}

	public void editPath() {
		EDIT_FORM_PATH = By
				.xpath(EDIT_FORM + id + REST_PATH + id + REST_PATH_2);
		clickOn(EDIT_FORM_PATH);
	}

	public void deletePath() {
		DELETE_FORM_PATH = By.xpath(DELETE_FORM + id + REST_PATH + id
				+ REST_PATH_2);
		clickOn(DELETE_FORM_PATH);
	}

	public void selectYear(String year) {
		selectFrom(DATAPICKER_YEAR, year);
	}

	public void selectMoth(String month) {
		selectFrom(DATAPICKER_MONTH, month);
	}

	public void selectDay(String day) {
		selectFrom(DATAPICKER_DAY, day);
	}

	public void selectWhere(String where) {
		selectFrom(WHERE_EYE_REPORT, where);
	}

	public void selectWho(String who) {
		selectFrom(WHO_EYE_REPORT, who);
	}

	public void enterForm() {
		clickOn(ENTER_FORM);
	}

	public void clickOnCurrentDate() {
		clickOn(CURRENT_DAY);
	}

	@Override
	public String getPageUrl() {
		return "/formentryapp/forms.page";
	}
}
