package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.ManagePatientsPage;
import org.openmrs.contrib.qaframework.page.PatientFormPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddNewIdentifierSteps extends Steps{
	
	private AdministrationPage administrationPage;
	private ManagePatientsPage managePatientsPage;
    private PatientFormPage patientFormPage;
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
    
    @Then("the system loads administration page")
    public void loadAdministrationPage() {
    	assertTrue(textExists("Administration"));
    }
   
    @And("a user clicks on the Manage Patients link on the administration page")
    public void clickManagePatientsLink() {
    	managePatientsPage = administrationPage.clickOnManagePatients();
    }
    
    @Then("the system loads patient page")
    public void loadManagePatientsPage() {
    	assertTrue(textExists("Patient"));
    }
    
    @When("a user searches for the patient")
    public void searchPatientIdentifierOrPatientName() {
    	managePatientsPage.searchPatientIdentifierOrPatientName(firstPatientIdentifier);
    }
    
    @And("a user clicks on add new identifier")
    public void ClickOnAddNewIdentifier() {
    	patientFormPage.addNewIdentifier();
    }
    
    @And("a user fills in the new identifier form")
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
