package org.openmrs.contrib.qaframework.automation;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openmrs.reference.page.HomePage;
import org.openqa.selenium.By;

public class StylesGuideSteps extends Steps {
    @Given("a user logins into the system")
    public void loginForStyles() {
        loginPage.login(testProperties.getUsername(),
                testProperties.getPassword(), testProperties.getLocation());
    }

    @And("user navigates to the systems admin page")
    public void navigateToSystemsPage() {
        elementClickOn(By.id("coreapps-systemadministration-homepageLink-coreapps-systemadministration-homepageLink-extension"));
    }


    @When("user presses the styles guide link")
    public void loadStylesGuide() {
        elementClickOn(By.className("icon-magic"));
    }

    @Then("system should load the styles guide page")
    public void validatePage() {
        Assert.assertNotNull(getElement(By.id("style-guide-header")));
    }

    @And("user clicks back")
    public void back() {
        driver.navigate().back();
    }

    @Then("system should return to the previous page")
    public void validateReturn() {
        Assert.assertNotNull(getElement(By.id("icon-magic")));
    }

}

