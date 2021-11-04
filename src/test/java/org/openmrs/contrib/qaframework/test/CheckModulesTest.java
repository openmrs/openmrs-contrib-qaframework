/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.test;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.ReferenceApplicationTestBase;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.ModulesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Ignore
public class CheckModulesTest extends ReferenceApplicationTestBase {
	
	/**
	 * Check the list of modules to be sure they are all started.
	 */
	@Test
	@Category(BuildTests.class)
	public void checkModulesTest() {
		AdministrationPage administrationPage = homePage.goToAdministration();
		ModulesPage modulesPage = administrationPage.goToManageModulesPage();
		// Get the modulesListing <div>, which contains the table of modules.
		WebElement moduleListing = modulesPage.findElementById("moduleListing");
		// Grab all the <input> elements from the first column of the table.
		List<WebElement> firstColumn = moduleListing.findElements(By.cssSelector("#moduleListing table tbody td"));
		for (WebElement eachModule : firstColumn) {
			// The name attr on the <input> elements should all be "stop" which indicates the module is correctly started.
			// If not, then grab the text from the 3rd column to show which module is not started.
			assertFalse(eachModule.getText().contains("moduleNotStarted"));
		}
	}
}
