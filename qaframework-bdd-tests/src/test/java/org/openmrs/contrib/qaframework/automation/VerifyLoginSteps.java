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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.ModulesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class VerifyLoginSteps extends Steps {

    private AdministrationPage administrationPage;
    private ModulesPage modulesPage;
    private  WebElement moduleListing;
    
    @Before(RunTest.HOOK.SELENIUM_DEFAULT_LOGIN)
    public void visitHomePage() {
        initiateWithLogin();
    }
    
    @After(RunTest.HOOK.SELENIUM_DEFAULT_LOGIN)
    public void destroy() {
        quit();
    }

    @And("user verifies modules available on home page after login as super user")
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
        assertFalse(homePage.isConfigureMetadataAppPresent());
        assertFalse(homePage.isDataManagementAppPresent());
        assertFalse(homePage.isSystemAdministrationAppPresent());
        assertFalse(homePage.isCaptureVitalsAppPresent());
        assertFalse(homePage.isFindAPatientAppPresent());
    }

    @And("user verifies modules available on home page after login as doctor")
    public void verifyDoctorModulesAvailableOnHomePage() {
        goToLoginPage().loginAsDoctor();
        assertTrue(homePage.isFindAPatientAppPresent());
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
        assertFalse(homePage.isConfigureMetadataAppPresent());
        assertFalse(homePage.isCaptureVitalsAppPresent());
        assertFalse(homePage.isRegisterPatientCustomizedForRefAppPresent());
        assertFalse(homePage.isDataManagementAppPresent());
        assertFalse(homePage.isSystemAdministrationAppPresent());
    }

    @And("user verifies modules available on home page after login as nurse")
    public void verifyNurseModulesAvailableOnHomePage() {
        goToLoginPage().loginAsNurse();
        assertTrue(homePage.isFindAPatientAppPresent());
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
        assertTrue(homePage.isCaptureVitalsAppPresent());
        assertFalse(homePage.isRegisterPatientCustomizedForRefAppPresent());
        assertFalse(homePage.isDataManagementAppPresent());
        assertFalse(homePage.isSystemAdministrationAppPresent());
        assertFalse(homePage.isConfigureMetadataAppPresent());
    }

    @And("user verifies modules available on home page after login as sysadmin")
    public void verifySysadminModulesAvailableOnHomePage() {
        goToLoginPage().loginAsSysadmin();
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
        assertTrue(homePage.isSystemAdministrationAppPresent());
        assertFalse(homePage.isFindAPatientAppPresent());
        assertFalse(homePage.isActiveVisitsAppPresent());
        assertFalse(homePage.isCaptureVitalsAppPresent());
        assertFalse(homePage.isRegisterPatientCustomizedForRefAppPresent());
        assertFalse(homePage.isDataManagementAppPresent());
        assertFalse(homePage.isConfigureMetadataAppPresent());
    }

    @Then("system goes back to login page")
    public void comeBackToApplicationAfterLogoutTest() throws Exception {
        getLoginPage().go();
        assertPage(getLoginPage());
    }

}
