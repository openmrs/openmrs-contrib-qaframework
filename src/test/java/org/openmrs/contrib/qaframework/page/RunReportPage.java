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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RunReportPage extends Page {

	private static final By START_DATE_FIELD = By.cssSelector("#userEnteredParamstartDate");
	private static final By END_DATE_FIELD = By.cssSelector("#userEnteredParamendDate");
	private static final By REQUEST_REPORT_BUTTON = By.cssSelector("input[type='submit' i]");
	private static final By EMPTY_FORM = By.cssSelector("#content");

	public RunReportPage(Page page) {
		super(page);
	}

	public void enterStartDate(String STARTDATE) {
		waiter.until(ExpectedConditions.elementToBeClickable(START_DATE_FIELD));
		clickOn(START_DATE_FIELD);
		setTextToFieldNoEnter(START_DATE_FIELD, STARTDATE);
	}

	public void enterEndDate(String ENDDATE) {
		waiter.until(ExpectedConditions.elementToBeClickable(END_DATE_FIELD));
		clickOn(END_DATE_FIELD);
		setTextToFieldNoEnter(END_DATE_FIELD,ENDDATE);
	}

	public ReportHistoryPage clickOnRequestReport() {
		clickOn(REQUEST_REPORT_BUTTON);
		return new ReportHistoryPage(this);
	}

	public void clickOnEmptyForm() {
		clickOn(EMPTY_FORM);
	}

	@Override
	public String getPageUrl() {
		return "reporting/run/runReport";
	}
}
