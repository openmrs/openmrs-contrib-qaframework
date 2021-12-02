/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.page.HomePage;

public class LoginTest extends ReferenceApplicationTestBase {

    private HomePage homePage;
    
    private void initiateHomePage() {
        homePage = new HomePage(page);
        assertPage(homePage.waitForPage());
    }

    @Test
    @Category(BuildTests.class)
    public void verifyModulesAvailableOnHomePage() {
        initiateHomePage();
        assertTrue(homePage.isFindAPatientAppPresent());
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
        assertTrue(homePage.isCaptureVitalsAppPresent());
        assertTrue(homePage.isRegisterPatientCustomizedForRefAppPresent());
        assertTrue(homePage.isDataManagementAppPresent());
        assertTrue(homePage.isConfigureMetadataAppPresent());
        assertTrue(homePage.isSystemAdministrationAppPresent());
    }

    @Test
    @Category(BuildTests.class)
    public void verifyClerkModulesAvailableOnHomePage() {
        goToLoginPage().loginAsClerk();
        initiateHomePage();
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
        assertTrue(homePage.isRegisterPatientCustomizedForRefAppPresent());
    }

    @Test
    @Category(BuildTests.class)
    public void verifyDoctorModulesAvailableOnHomePage() {
        goToLoginPage().loginAsDoctor();
        initiateHomePage();
        assertTrue(homePage.isFindAPatientAppPresent());
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
    }

    @Test
    @Category(BuildTests.class)
    public void verifyNurseModulesAvailableOnHomePage() {
        goToLoginPage().loginAsNurse();
        initiateHomePage();
        assertTrue(homePage.isFindAPatientAppPresent());
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
        assertTrue(homePage.isCaptureVitalsAppPresent());
    }

    @Test
    @Category(BuildTests.class)
    public void verifySysadminModulesAvailableOnHomePage() {
        goToLoginPage().loginAsSysadmin();
        initiateHomePage();
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
        assertTrue(homePage.isSystemAdministrationAppPresent());
    }
}
