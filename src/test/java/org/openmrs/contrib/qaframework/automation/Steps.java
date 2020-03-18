package org.openmrs.contrib.qaframework.automation;

import org.openmrs.reference.ReferenceApplicationTestBase;
import org.openmrs.uitestframework.page.TestProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Steps extends ReferenceApplicationTestBase {
	protected TestProperties testProperties = TestProperties.instance();

	protected void elementClickOn(By elementBy) {
		driver.findElement(elementBy).click();
	}

	protected WebElement getElement(By elementBy) {
		try {
			return driver.findElement(elementBy);
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	protected void quitBrowser() {
		driver.quit();
		driver = null;
	}
}
