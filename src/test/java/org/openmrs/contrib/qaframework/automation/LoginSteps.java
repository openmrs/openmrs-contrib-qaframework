package org.openmrs.contrib.qaframework.automation;

import cucumber.api.java.en.Given;
import org.junit.Assert;
import org.openmrs.contrib.qaframework.CucumberProperties;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginSteps extends Steps {

	private void enterUsername(String username) {
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).sendKeys(username);
	}

	private void enterPassword(String password) {
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys(password);
	}

	private WebElement getLoginButton() {
		return getElement(By.id("loginButton"));
	}

	@Given("User visits login page")
	public void visitLoginPage() throws Exception {
		startWebDriver();
		goToLoginPage();
	}

	@When("User enters " + CucumberProperties.REGEX_UNDER_DOUBLE_QUOTES_STRING
			+ " username")
	public void anyUsername(String username) {
		if("setupUser".equals(username)) {
			username = testProperties.getUsername();
		}
		enterUsername(username);
	}

	@And("User enters " + CucumberProperties.REGEX_UNDER_DOUBLE_QUOTES_STRING
			+ " password")
	public void anyPassword(String password) {
		if("setupPass".equals(password)) {
			password = testProperties.getPassword();
		}
		enterPassword(password);
	}

	@And("User Selects " + CucumberProperties.REGEX_UNDER_DOUBLE_QUOTES_STRING
			+ " Login Location")
	public void selectLoginLocatuon(String loginLocation) {
		if ("anyLocation".equals(loginLocation)) {
			driver.findElement(By.cssSelector("#sessionLocation li")).click();
		} else if ("noLocation".equals(loginLocation)) {
			getLoginButton().click();
			Assert.assertNotNull(getLoginButton());
		} else if("setupLocation".equals(loginLocation)) {
			elementClickOn(By.id(testProperties.getLocation()));
		} else {
			elementClickOn(By.id(loginLocation));
		}
	}

	@And("User Logs in")
	public void userLogsIn() {
		getLoginButton().click();
	}

	@Then("System Evaluates Login "
			+ CucumberProperties.REGEX_UNDER_DOUBLE_QUOTES_STRING)
	public void evaluateLogin(String status) {
		if (status.trim().endsWith("true")) {
			Assert.assertNull(getLoginButton());
		} else if (status.trim().endsWith("false")) {
			Assert.assertNotNull(getLoginButton());
		}
	}

	@And("System Closes browser")
	public void closeBrowser() {
		quitBrowser();
	}

	@When("setup user rightly logs in")
	public void setupRightLogin() {
		getLoginPage().login(testProperties.getUsername(), testProperties.getPassword(), testProperties.getLocation());
	}

	@Then("System logs in user")
	public void evaluateLogin() {
		Assert.assertNull(getLoginButton());
	}
}
