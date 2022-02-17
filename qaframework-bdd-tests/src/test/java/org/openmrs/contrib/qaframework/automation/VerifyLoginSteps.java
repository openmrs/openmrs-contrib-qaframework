/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;

public class VerifyLoginSteps extends Steps {
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

    @And("user verifies modules available on home page after login as doctor")
    public void verifyDoctorModulesAvailableOnHomePage() {
        goToLoginPage().loginAsDoctor();
        assertTrue(homePage.isFindAPatientAppPresent());
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
    }

    @And("user verifies modules available on home page after login as nurse")
    public void verifyNurseModulesAvailableOnHomePage() {
        goToLoginPage().loginAsNurse();
        assertTrue(homePage.isFindAPatientAppPresent());
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
        assertTrue(homePage.isCaptureVitalsAppPresent());
    }

    @And("user verifies modules available on home page after login as sysadmin")
    public void verifySysadminModulesAvailableOnHomePage() {
        goToLoginPage().loginAsSysadmin();
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
        assertTrue(homePage.isSystemAdministrationAppPresent());
    }
    
    @Then("system goes back to login page")
    public void comeBackToApplicationAfterLogoutTest() throws Exception {
        getLoginPage().go();
        assertPage(getLoginPage());
    }
}
