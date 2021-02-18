package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertNotNull;

public class LoginSteps extends Steps {

    @After("@selenium")
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
            driver.findElement(By.cssSelector("#sessionLocation li"))
                    .click();
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

    @Then("System Evaluates Login {string}")
    public void evaluateLogin(String status) {
        if (status.trim().endsWith("true")) {
            assertNotNull(getLogOutLink());
        } else if (status.trim().endsWith("false")) {
            assertNotNull(getLoginButton());
        }
    }

    @When("Setup user rightly logs in")
    public void setupRightLogin() {
        loginPage.login(testProperties.getUsername(),
                testProperties.getPassword(), testProperties.getLocation());
    }

    @Then("System logs in user")
    public void evaluateLogin() {
        assertNotNull(getLogOutLink());
        assertNotNull(getElement(By.className("homeList")));
    }
}
