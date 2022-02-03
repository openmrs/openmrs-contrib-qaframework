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
// import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReportHistoryPage extends Page {

	private static final By VIEW_REPORT = By.xpath("/html/body/div[1]/div[3]/div[3]/div/table/tbody/tr/td[2]/div[1]/a");

	public ReportHistoryPage(Page page) {
		super(page);
	}

	public RenderDefaultReportPage clickOnViewLink() {
		clickOn(VIEW_REPORT);
		return new RenderDefaultReportPage(this);
	}

	@Override
	public String getPageUrl() {
		return "/reporting/reports/reportHistoryOpen";
	}
}
