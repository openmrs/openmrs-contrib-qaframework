/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.fail;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openmrs.contrib.qaframework.helper.InitialSetupPage;
import org.openmrs.contrib.qaframework.helper.TestBase;

public class InitialSetupSteps extends TestBase {
	
	protected InitialSetupPage initialSetupPage;
	
	public InitialSetupSteps() {
		try {
			startWebDriver();
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	protected void complete() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	protected void initialSetup() {
		initialSetupPage = new InitialSetupPage(driver);
		initialSetupPage.go();
		initialSetupPage.waitForPage();
	}
	
	protected void submitInstallationStep1() {
		initialSetupPage.installationSelectLanguage();
	}
	
	protected void waitForSetupCompletion() {
		initialSetupPage.waitForSetupToComplete();
		String errorMsg = initialSetupPage.getErrorMessage();
		if (StringUtils.isNotBlank(errorMsg)) {
			Assert.fail(errorMsg);
		}
	}
}
