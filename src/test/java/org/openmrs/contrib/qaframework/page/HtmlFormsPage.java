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

public class HtmlFormsPage extends Page {
	
	private static final By NEW_HTML_FORM = By.cssSelector("a[href='htmlForm.form']");
	
	private static final By NAME = By.name("form.name");
	
	private static By DESCRIPTION = By.name("form.description");
	
	private static By SAVE_FORM = By.cssSelector("input[type='submit']");
	
	private static By VERSION = By.name("form.version");
	
	public HtmlFormsPage(Page page) {
		super(page);
	}
	
	public void createNewFormTest(String name, String description, String version) throws InterruptedException {
		setTextToFieldNoEnter(NAME, name);
		setTextToFieldNoEnter(DESCRIPTION, description);
		setTextToFieldNoEnter(VERSION, version);
		clickOn(SAVE_FORM);
	}
	
	public String getPageUrl() {
		return "/module/htmlformentry/htmlForm.form";
	}
}
