package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;
import org.openmrs.contrib.qaframework.RunTest;

public class VerifyLoginSteps  extends Steps {

    @Before(RunTest.HOOK.SELENIUM_DEFAULT_LOGIN)
    public void visitHomePage() {
        initiateWithLogin();
    }
    
    @After(RunTest.HOOK.SELENIUM_DEFAULT_LOGIN)
    public void destroy() {
        quit();

    }

    @When("user verifies modules available on home page")
    public void verifyModulesAvailableOnHomePage() {
        assertTrue(homePage.isFindAPatientAppPresent());
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
        assertTrue(homePage.isCaptureVitalsAppPresent());
        assertTrue(homePage.isRegisterPatientCustomizedForRefAppPresent());
        assertTrue(homePage.isDataManagementAppPresent());
        assertTrue(homePage.isConfigureMetadataAppPresent());
        assertTrue(homePage.isSystemAdministrationAppPresent());
    }

    @And("user verifies modules available on homepage after login as clerk")
    public void verifyClerkModulesAvailableOnHomePage() {
        goToLoginPage().loginAsClerk();
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
        assertTrue(homePage.isRegisterPatientCustomizedForRefAppPresent());
    }

    @And("user verifies modules available on home page after login as Docter")
    public void verifyDoctorModulesAvailableOnHomePage() {
        goToLoginPage().loginAsDoctor();
        assertTrue(homePage.isFindAPatientAppPresent());
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
    }

    @And("user verifies modules available on home page after login as Nurse")
    public void verifyNurseModulesAvailableOnHomePage() {
        goToLoginPage().loginAsNurse();
        assertTrue(homePage.isFindAPatientAppPresent());
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
        assertTrue(homePage.isCaptureVitalsAppPresent());
    }

    @Then("user verifies modules available on home page after login as sysadmin")
    public void verifySysadminModulesAvailableOnHomePage() {
        goToLoginPage().loginAsSysadmin();
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
        assertTrue(homePage.isSystemAdministrationAppPresent());
    }
    
}
