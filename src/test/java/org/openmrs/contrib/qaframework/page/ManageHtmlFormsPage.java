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

public class ManageHtmlFormsPage extends Page {

	private static final By NEW_HTML_FORM = By
			.cssSelector("a[href='htmlForm.form']");

	public ManageHtmlFormsPage(Page page) {
		super(page);
	}

	public HtmlFormsPage clickOnNewHtmlForm() {
		waiter.until(ExpectedConditions
				.visibilityOfElementLocated(NEW_HTML_FORM));
		findElement(NEW_HTML_FORM).click();
		return new HtmlFormsPage(this);
	}

	@Override
	public String getPageUrl() {
		return "/module/htmlformentry/htmlForms.list";
	}
}
