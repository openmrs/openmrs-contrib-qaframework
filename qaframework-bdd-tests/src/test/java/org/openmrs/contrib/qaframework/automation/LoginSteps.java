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

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginSteps extends Steps {
	Logger log = Logger.getLogger(LoginSteps.class.getName());

	@After(RunTest.HOOK.SELENIUM)
	public void destroy() {
		quit();
	}

	private void enterUsername(String username) {
		driver.findElement(By.id("username")).sendKeys(username);
	}

	private void enterPassword(String password) {
		driver.findElement(By.id("password")).sendKeys(password);
	}

	private WebElement getLoginButton() {
		return getElement(By.id("loginButton"));
	}

	private WebElement getLogOutLink() {
		return getElement(By.className("logout"));
	}

	@Given("User visits login page")
	public void visitLoginPage() throws Exception {
		log.info(".... Login.......");
		goToLoginPage();
	}

	@When("User enters {string} username")
	public void anyUsername(String username) {
		if ("setupUser".equals(username)) {
			username = testProperties.getUsername();
		}
		enterUsername(username);
	}

	@And("User enters {string} password")
	public void anyPassword(String password) {
		if ("setupPass".equals(password)) {
			password = testProperties.getPassword();
		}
		enterPassword(password);
	}

	@And("User Selects {string} Login Location")
	public void selectLoginLocation(String loginLocation) {
		if ("firstLocation".equals(loginLocation)) {
			driver.findElement(By.cssSelector("#sessionLocation li")).click();
		} else if ("noLocation".equals(loginLocation)) {
			getLoginButton().click();
			assertNotNull(getLoginButton());
		} else if ("setupLocation".equals(loginLocation)) {
			loginPage.clickOn(By.id(testProperties.getLocation()));
		} else {
			loginPage.clickOn(By.id(loginLocation));
		}
	}

	@And("User Logs in")
	public void userLogsIn() {
		getLoginButton().click();
	}

	@Then("System Evaluates Login {string}")
	public void evaluateLogin(String status) {
		if (status.trim().endsWith("true")) {
			assertNotNull(getLogOutLink());
		} else if (status.trim().endsWith("false")) {
			assertNotNull(getLoginButton());
		}
	}
}
