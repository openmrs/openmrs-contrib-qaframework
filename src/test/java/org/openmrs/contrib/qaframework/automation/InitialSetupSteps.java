package org.openmrs.contrib.qaframework.automation;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openmrs.uitestframework.page.InitialSetupPage;
import org.openmrs.uitestframework.test.TestBase;

import static org.junit.Assert.fail;

public class InitialSetupSteps extends TestBase {
	protected InitialSetupPage initialSetupPage;

	public InitialSetupSteps() {
		try {
			startWebDriver();
		} catch (Exception e) {
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
