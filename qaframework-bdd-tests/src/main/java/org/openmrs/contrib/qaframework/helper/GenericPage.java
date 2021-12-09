/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.helper;

import org.openqa.selenium.WebDriver;

/**
 * A Page that represents any page, i.e. a page that we don't (yet) know which
 * page it is.
 */
public class GenericPage extends Page {

	public GenericPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getPageUrl() {
		return null;
	}
}
