package org.openmrs.contrib.qaframework.automation;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openmrs.contrib.qaframework.CucumberProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LoginSteps extends Steps {

    private void enterUsername(String username) {
        driver.findElement(By.id("username")).sendKeys(username);
    }

    private void enterPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    private WebElement getLoginButton() {
        return getElement(By.id("loginButton"));
    }

    @Given("User visits login page")
    public void visitLoginPage() throws Exception {
        goToLoginPage();
    }

    @When("User enters " + CucumberProperties.REGEX_UNDER_DOUBLE_QUOTES_STRING
            + " username")
    public void anyUsername(String username) {
        if ("setupUser".equals(username)) {
            username = testProperties.getUsername();
        }
        enterUsername(username);
    }

    @And("User enters " + CucumberProperties.REGEX_UNDER_DOUBLE_QUOTES_STRING
            + " password")
    public void anyPassword(String password) {
        if ("setupPass".equals(password)) {
            password = testProperties.getPassword();
        }
        enterPassword(password);
    }

    @And("User Selects " + CucumberProperties.REGEX_UNDER_DOUBLE_QUOTES_STRING
            + " Login Location")
    public void selectLoginLocation(String loginLocation) {
        if ("anyLocation".equals(loginLocation)) {
            driver.findElement(By.cssSelector("#sessionLocation li")).click();
        } else if ("noLocation".equals(loginLocation)) {
            getLoginButton().click();
            assertNotNull(getLoginButton());
        } else if ("setupLocation".equals(loginLocation)) {
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
            assertNull(getLoginButton());
        } else if (status.trim().endsWith("false")) {
            assertNotNull(getLoginButton());
        }
        quitBrowser();
    }

    @When("Setup user rightly logs in")
    public void setupRightLogin() {
        loginPage.login(testProperties.getUsername(),
                testProperties.getPassword(), testProperties.getLocation());
    }

    @Then("System logs in user")
    public void evaluateLogin() {
        assertNull(getLoginButton());
        assertNotNull(getElement(By.className("homeList")));
        quitBrowser();
    }
}
