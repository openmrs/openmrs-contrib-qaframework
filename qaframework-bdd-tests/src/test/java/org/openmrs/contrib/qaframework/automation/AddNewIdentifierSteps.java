package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.PatientFormPage;
import org.openmrs.contrib.qaframework.page.PatientPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddNewIdentifierSteps extends Steps {
    private AdministrationPage administrationPage;
    private PatientPage patientPage;
    private PatientFormPage patientFormPage;
    private static final String IDENTIFIER = "1007A1";


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
        patientPage = (PatientPage) administrationPage.clickOnManagePatients().waitForPage();
    }

    @Then("the system loads patient page")
    public void loadManagePatientsPage() {
        assertTrue(textExists("Patient"));
    }

    @When("a user searches for the patient")
    public void searchPatientIdentifierOrPatientName() {
        patientPage.searchPatientIdentifierOrPatientName(firstPatientIdentifier);
    }

    @And("user selects returned patient")
    public void userSelectsReturnedPatient() {
        dashboardPage = findPatientPage.clickOnFirstPatient();
    }

    @And("a user clicks on add new identifier")
    public void ClickOnAddNewIdentifier() {
        patientFormPage.addNewIdentifier();
    }

    @And("a user mentions preferred, identifier, identifier type and location")
    public void fillNewIdentifierForm() {
        patientFormPage.selectPreferred();
        patientFormPage.setIdentifier(IDENTIFIER);
        patientFormPage.setIdentifierType();
        patientFormPage.setLocation();
    }

    @Then("the system adds the new identifier")
    public void clickOnSavePatient() {
        patientFormPage.savePatient();
    }

}
