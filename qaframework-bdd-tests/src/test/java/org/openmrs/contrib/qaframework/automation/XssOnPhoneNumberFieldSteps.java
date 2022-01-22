package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.ActiveVisitsPage;
import org.openmrs.contrib.qaframework.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.contrib.qaframework.page.RegistrationEditSectionPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class  XssOnPhoneNumberFieldSteps  extends Steps {
    
    private TestData.PatientInfo patient;
    private ActiveVisitsPage activeVisitsPage;
    private ClinicianFacingPatientDashboardPage patientDashboardPage;
    private RegistrationEditSectionPage registrationEditSectionPage;

    @Before(RunTest.HOOK.SELENIUM_XSS_VULNERABILITY)
    public void setUp() throws Exception {
        initiateWithLogin();
        patient = createTestPatient();
        new TestData.TestVisit(patient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
    }

    @After(RunTest.HOOK.SELENIUM_XSS_VULNERABILITY)
    public void tearDown() throws Exception {
        deletePatient(patient);
    }  
    
    @Given("User goes to active visit search page")
    public void userGoesToActivVisitSearch() {
        activeVisitsPage = homePage.goToActiveVisitsSearch();
    }

    @When("User searches for patient using patient identifier")
    public void searchForPatientUsingPatientIdentifier() {
        activeVisitsPage.search(patient.identifier);
    }

    @And("User goes to patient dashboard of last Active visit")
    public void systemGoesToPatientDashboardOfLastActiveVisit() {
        patientDashboardPage = activeVisitsPage.goToPatientDashboardOfLastActiveVisit();
    }
    
    @Then("User clicks on show contact link")
    public void clickOnShowContact() {
        patientDashboardPage.clickOnShowContact();
    }

    @And("User clicks on editContact info")
    public void clickOnEditContact() {
        registrationEditSectionPage = patientDashboardPage.clickOnEditContact();
    }

    @And("User clicks on phone Number edit")
    public void userClicksOnPhoneNumberEdit(){
        registrationEditSectionPage.clickOnPhoneNumberEdit();
    }

    @Then("User clears phone number")
    public void userClearsPhoneNumber() {
        registrationEditSectionPage.clearPhoneNumber();
    }

    @And("User enters new phone number with xss check script")
    public void userEntersPhoneNumber() {
        registrationEditSectionPage.enterPhoneNumber("<script>alert(0)</script>");
    }

    @And("User clicks on confirm Edit button")
    public void userClicksOnComfirmEditButton() {
        registrationEditSectionPage.clickOnConfirmEdit();
    }

    @Then("System returns a message validator")
    public void checkXssVulnerabilityValidator() {
        assertTrue(registrationEditSectionPage.getInvalidPhoneNumberNotification().contains(("Must be a valid phone number (with +, -, numbers or parentheses)")));
    }

    @And("User re clears the phone Number input area")
    public void userReClearsPhoneNumber() {
        registrationEditSectionPage.clearPhoneNumber();
    }

    @And("User enters the collect phone number")
    public void userReEntersCorrectPhonrNumber() {
        registrationEditSectionPage.enterPhoneNumber("111111111");
    }

    @And("User clicks on confirm edit button")
    public void userClicksOnConfirmsEditButton() {
        registrationEditSectionPage.clickOnConfirmEdit();
    }

    @Then("User clicks on confirm Patient")
    public void userClicksOnConfirmPatient() throws Exception {
        patientDashboardPage = registrationEditSectionPage.confirmPatient();
    }

    @And("User clicks on show contact")
    public void userClicksOnShowContact() {
        patientDashboardPage.clickOnShowContact();
    }

    @Then("User validate the collect number to be entered into the input area")
    public void systemChecksTheCollectPhoneNumber() {
        assertTrue(patientDashboardPage.getTelephoneNumber().contains("111111111"));
    }
}

