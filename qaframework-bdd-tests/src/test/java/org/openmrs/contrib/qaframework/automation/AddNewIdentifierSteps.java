package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.ManagePatientPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddNewIdentifierSteps extends Steps {
    private AdministrationPage administrationPage;
    private ManagePatientPage managePatientPage;
    private static final String IDENTIFIER = "100397A";
    @Before(RunTest.HOOK.SELENIUM_ADD_NEW_IDENTIFIER)
    public void setUp() {
        initiateWithLogin();
    }

    @After(RunTest.HOOK.SELENIUM_ADD_NEW_IDENTIFIER)
    public void tearDown() {
        quit();
    }

    @Given("a user clicks on system administration link on the home page")
    public void launchAdministrationPage() {
        administrationPage = homePage.goToAdministration();
    }

    @Then("the administration page is loaded")
    public void verifyAdministrationPageIsLoaded() {
        assertTrue(textExists("Administration"));
    }

    @And("a user clicks on the manage Patients link on the administration page")
    public void clickManagePatientsLink() {
        managePatientPage = (ManagePatientPage) administrationPage.clickOnManagePatients().waitForPage();
    }

    @Then("the system loads patient page")
    public void loadManagePatientsPage() {
        assertTrue(textExists("Patient"));
    }

    @When("a user searches for the patient")
    public void searchPatientIdentifierOrPatientName() {
        managePatientPage.searchPatientIdentifierOrPatientName("000");
    }

    @And("user selects returned patient")
    public void userSelectsReturnedPatient() {
        managePatientPage.clickOnReturnedPatient("");
    }

    @And("a user clicks on add new identifier")
    public void ClickOnAddNewIdentifier() {
        managePatientPage.clickOnAddNewIdentifier();
    }

    @And("a user mentions preferred, identifier, identifier type and location")
    public void fillNewIdentifierForm() {
        managePatientPage.selectPreferred();
        managePatientPage.setIdentifier(IDENTIFIER);
        managePatientPage.setIdentifierType();
        managePatientPage.setLocation();
    }

    @Then("the system adds the new identifier")
    public void clickOnSavePatient() {
        managePatientPage.savePatient();
    }

}
