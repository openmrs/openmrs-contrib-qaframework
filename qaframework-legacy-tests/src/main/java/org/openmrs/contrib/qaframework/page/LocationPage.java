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

@Deprecated
public class LocationPage extends Page {

	private static final By MANAGE_LOCATION = By.linkText("Manage Locations");
	private static final By ADD_LOCATION = By.linkText("Add Location");
	private static final By SAVE_LOCATION = By.name("saveLocation");
	private static final By NAME = By.name("name");
	private static final By TAGS = By.name("tags");
	private static final By REASON_RETIRE = By.name("retireReason");
	public static String NAME_LOCATION;
	private static By DELETE_LOCATION = By.name("action");
	private static By ADDED_LOCATION;
	private static String LOCATION_PATH = "//table[@id='locationTable']/tbody/tr/td/input[@value=\"";
	private static String REST_PATH = "\"]";
	private static By CHECK_LOCATION;

	public LocationPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnManageLocation() {
		clickOn(MANAGE_LOCATION);
	}

	public void clickOnAddLocation() {
		clickOn(ADD_LOCATION);
	}

	public void clickOnSaveLocation() {
		clickOn(SAVE_LOCATION);
	}

	public void enterName(String name) {
		findElement(NAME).sendKeys(name);
		NAME_LOCATION = name;
	}

	public String findLocation() {
		ADDED_LOCATION = By.linkText(NAME_LOCATION);
		return findElement(ADDED_LOCATION).getAttribute("href").split("=")[1];
	}

	public void checkLocation() {
		CHECK_LOCATION = By.xpath(LOCATION_PATH + findLocation() + REST_PATH);
		clickOn(CHECK_LOCATION);
	}

	public void clearName() {
		findElement(NAME).clear();
	}

	public void chooseTags(String tags) {
		findElement(TAGS).getText().contains(tags);
	}

	public void clickOnTags() {
		clickOn(TAGS);
	}

	public void clickOnDelete() {
		clickOn(DELETE_LOCATION);
	}

	public void addedLocation() {
		clickOn(ADDED_LOCATION = By.linkText(NAME_LOCATION));
	}

	public boolean locationPresent() {
		try {
			return findElement(ADDED_LOCATION = By.linkText(NAME_LOCATION)) != null;
		} catch (Exception ex) {
			return false;
		}
	}

	public void enterRetireReason(String retire) {
		setText(REASON_RETIRE, retire);
	}

	@Override
	public String getPageUrl() {
		return "/locations/location.list";
	}
}
