package org.openmrs.contrib.qaframework.automation;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openmrs.contrib.qaframework.CucumberProperties;
import org.openmrs.contrib.qaframework.page.AddNewConditionPage;
import org.openmrs.contrib.qaframework.page.ConditionsPage;
import org.openmrs.reference.page.HomePage;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConditionsSteps extends Steps {
    private ConditionsPage conditionsPage;
    private String patientDashboardId;
    private By addNewCondition = By.id("conditionui-addNewCondition");
    private AddNewConditionPage addNewConditionPage;

    @Given("User logs in, searches John and visits first patient dashboard")
    public void visitFirstJohnsDashboard() {
        goToLoginPage().login(testProperties.getUsername(),
                testProperties.getPassword(), testProperties.getLocation());
        homePage = new HomePage(loginPage);
        findPatientPage = homePage.goToFindPatientRecord();
        findPatientPage.enterPatient("John");
        dashboardPage = findPatientPage.clickOnFirstPatient();
        patientDashboardId = getElement(patientHeaderId).getText();
    }

    @And("User clicks on Conditions")
    public void launchManageConditions() {
        elementClickOn(ConditionsPage.MANAGE_CONDITIONS_ENDPOINT);
        conditionsPage = new ConditionsPage(dashboardPage);
        assertEquals(patientDashboardId, getElement(patientHeaderId).getText());
    }

    @Then("System on Manage Conditions Page")
    public void systemLoadsManageConditions() {
        assertNotNull(getElement(addNewCondition));
    }

    @And("User clicks on Return")
    public void clickReturn() {
        conditionsPage.clickReturn();
    }

    @Then("System returns to patient dashboard")
    public void returnToDashboard() {
        assertEquals(patientDashboardId, getElement(patientHeaderId).getText());
    }

    @And("User clicks on Add new condition")
    public void userClicksAddNewCondition() {
        elementClickOn(ConditionsPage.ADD_NEW_CONDITION);
        addNewConditionPage = new AddNewConditionPage(conditionsPage);
    }

    @Then("System on Add New Condition Page")
    public void launchAddNewCondition() {
        assertNotNull(getElement(AddNewConditionPage.SAVE));
    }

    @And("User clicks on cancel")
    public void cancelAddNewCondition() {
        addNewConditionPage.clickCancel();
    }

    @And("User enters Missing condition")
    public void enterMissingCondition() {
        addNewConditionPage.typeInCondition("Missing condition");
    }

    @And("User clicks save")
    public void saveCondition() {
        addNewConditionPage.clickSave();
    }

    @And("User enters " + CucumberProperties.REGEX_UNDER_DOUBLE_QUOTES_STRING
            + " existing condition")
    public void enterExistingCondition(String activity) {
        addNewConditionPage.typeInCondition("Diarrhea");
        if ("true".equals(activity)) {
            addNewConditionPage.clickOnActive();
        } else if ("false".equals(activity)) {
            addNewConditionPage.clickOnInActive();
        }
    }

    @Then("Then System on "
            + CucumberProperties.REGEX_UNDER_DOUBLE_QUOTES_STRING + " Page")
    public void persist(String page) {
        if ("parent".equals(page)) {
            assertNotNull(getElement(addNewCondition));
        } else if ("current".equals(page)) {
            assertNotNull(getElement(AddNewConditionPage.SAVE));
        }
    }

    @And("Quit browser")
    public void quit() {
        quitBrowser();
    }
}
