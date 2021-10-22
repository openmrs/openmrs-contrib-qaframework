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

import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InitialSetupPage extends Page {
	private By rememberCheck = By.name("remember");
	private By continueButton = By.name("continue");
	private By passwordInput = By.cssSelector("input[type=password]");
	private By remoteUrlInput = By.name("remoteUrl");
	private By usernameInput = By.name("username");
	private By errorMessage = By.className("openmrs_error");
	private String databaseConnection = "input[name=database_connection]";
	protected TestProperties testProperties = TestProperties.instance();

	public InitialSetupPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getPageUrl() {
		return "/initialsetup";
	}

	private void pressContinue() {
		clickOn(continueButton);
		waitForPage();
	}

	private void clickOnInstallationType(Type type) {
		clickOn(By.cssSelector("input[value='" + type.name().toLowerCase()
				+ "']"));
	}

	/**
	 * Installation Step 1
	 */
	public void installationSelectLanguage() {
		clickOn(rememberCheck);
		pressContinue();
	}

	/**
	 * Installation Step 2
	 * 
	 * @param type
	 */
	public void selectInstallationType(Type type) {
		clickOnInstallationType(type);
		pressContinue();
	}

	private void setDatabaseConnection(Type type) {
		String dbHost = testProperties.getDbHost();
		if (StringUtils.isNotBlank(dbHost)) {
			setAttributeWithJs(
					databaseConnection,
					"value",
					queryJsForAttribute(databaseConnection, "value").replace(
							"localhost", dbHost));
		}
		if (Type.POSTGRES.equals(type)) {
			setAttributeWithJs(
					databaseConnection,
					"value",
					queryJsForAttribute(databaseConnection, "value").replace(
							"mysql", "postgresql").replace("3306", "5432"));
		}
	}

	/**
	 * Next installation steps after step 2 in right order
	 */
	public void install(Type type) {
		// enter password
		if (type.equals(Type.SIMPLE)) {
			setDatabaseConnection(type);
			setTextToFieldNoEnter(passwordInput, testProperties.getPassword());
			pressContinue();// review
			pressContinue();// review
		} else if (type.equals(Type.ADVANCED) || type.equals(Type.POSTGRES)) {
			advancedInstall(type);
		} else if (type.equals(Type.TESTING)) {
			setTextToFieldNoEnter(remoteUrlInput,
					"http://demo.openmrs.org/openmrs");
			enterUsernameAndPassword();
			pressContinue();

			// step 2 of 3
			setTextToFieldNoEnter(passwordInput, testProperties.getPassword());
			pressContinue();

			// step 3 of 3
			driver.findElements(passwordInput).get(1)
					.sendKeys(testProperties.getPassword());
			pressContinue();

			waitForPageToLoad();

			pressContinue();// review
		}
	}

	private void advancedInstall(Type type) {
		setDatabaseConnection(type);
		setTextToFieldNoEnter(passwordInput, testProperties.getPassword());
		pressContinue();
		// step 2 of 5
		driver.findElements(passwordInput).get(1)
				.sendKeys(testProperties.getPassword());// last password field
		pressContinue();

		// step 3 of 5
		pressContinue();

		// step 4 of 5
		driver.findElements(passwordInput).get(0)
				.sendKeys(testProperties.getPassword());// password
		driver.findElements(passwordInput).get(1)
				.sendKeys(testProperties.getPassword());// confirm password
		pressContinue();

		// step 5 of 5
		pressContinue();

		pressContinue();// review
	}

	public void enterUsernameAndPassword() {
		setTextToFieldNoEnter(usernameInput, testProperties.getUsername());
		setTextToFieldNoEnter(passwordInput, testProperties.getPassword());
	}

	/**
	 * Upgrading steps
	 */
	public void upgrade() {
		pressContinue();
		pressContinue();
	}

	public enum Type {
		SIMPLE, ADVANCED, TESTING, POSTGRES
	}

	public void waitForSetupToComplete() {
		new WebDriverWait(driver, Duration.ofSeconds(720L))
				.until(ExpectedConditions.or(
						ExpectedConditions.urlContains("index.htm"),
						ExpectedConditions.urlContains("home.page")));
	}

	public String getErrorMessage() {
		List<WebElement> error = getElementsIfExisting(errorMessage);
		return error.size() > 0 ? error.get(0).getText() : null;
	}
}
