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

public class StylesGuidePage extends Page {
	
	public static By STYLES_GUIDE_HEADER = By.id("style-guide-header");
	
	public StylesGuidePage(SystemAdministrationPage systemAdministrationPage) {
		super(systemAdministrationPage);
	}
	
	public void pressBack() {
		driver.navigate().back();
	}
	
	@Override
	public String getPageUrl() {
		return "/uicommons/styleGuide.page";
	}
}
