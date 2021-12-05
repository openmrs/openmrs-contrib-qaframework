/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.legacy;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.PatientGenerator;
import org.openmrs.contrib.qaframework.helper.TestBase;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.helper.TestPatient;
import org.openmrs.contrib.qaframework.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.contrib.qaframework.page.HomePage;
import org.openmrs.contrib.qaframework.page.RegistrationPage;

public class UnidentifiedPatientKeyboardTest extends TestBase {
    
    private RegistrationPage registrationPage;
    private HomePage homePage;
    private ClinicianFacingPatientDashboardPage patientDashboardPage;
    private TestPatient patient;

    @Before
    public void setUp() throws Exception {
        homePage = new HomePage(page);
        registrationPage = new RegistrationPage(page);
        patientDashboardPage = new ClinicianFacingPatientDashboardPage(page);
        assertPage(homePage.waitForPage());
    }

    @Test
    @Category(BuildTests.class)
    public void unidentifiedPatientKeyboardTest() throws InterruptedException {
        homePage.goToRegisterPatientApp();
        patient = PatientGenerator.generateTestPatient();
        registrationPage.enterUnidentifiedPatient(patient);

        assertTrue(registrationPage.getNameInConfirmationPage().contains("--"));
        assertTrue(registrationPage.getGenderInConfirmationPage().contains(patient.gender));

        patientDashboardPage = registrationPage.confirmPatient();
        patientDashboardPage.waitForPage();
        
        patient.uuid = patientDashboardPage.getPatientUuidFromUrl();
        assertPage(patientDashboardPage.waitForPage());	// remember just-registered patient id, so it can be removed.
        assertTrue(driver.getPageSource().contains("UNKNOWN UNKNOWN"));
    }
    
    @After
    public void tearDown() throws Exception {
        TestData.PatientInfo p = new TestData.PatientInfo();
        p.uuid = patient.uuid;
        deletePatient(p);
        waitForPatientDeletion(patient.uuid);
    }
}
