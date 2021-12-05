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

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.TestBase;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.helper.TestPatient;
import org.openmrs.contrib.qaframework.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.contrib.qaframework.page.DataManagementPage;
import org.openmrs.contrib.qaframework.page.HomePage;
import org.openmrs.contrib.qaframework.page.RegistrationPage;

public class UsingBackButtonInMergePatientTest extends TestBase {

    private HomePage homePage;
    private TestPatient patient1;
    private TestPatient patient2;
    private RegistrationPage registrationPage;
    private ClinicianFacingPatientDashboardPage patientDashboardPage;
    private DataManagementPage dataManagementPage;
    private String id;
    private String id2;

    @Before
    public void setUp() throws Exception {
        homePage = new HomePage(page);
        assertPage(homePage.waitForPage());
        registrationPage = new RegistrationPage(page);
        patientDashboardPage = new ClinicianFacingPatientDashboardPage(page);
        dataManagementPage = new DataManagementPage(page);
        patient1 = new TestPatient();
        patient2 = new TestPatient();
    }

    @Test
    @Category(BuildTests.class)
    public void usingBackButtonInMergePatientTest() throws Exception {
        homePage.goToRegisterPatientApp().waitForPage();
        patient1.familyName = "Potter";
        patient1.givenName = "John";
        patient1.gender = "Male";
        patient1.estimatedYears = "45";
        patient1.address1 = "address";
        registrationPage.enterMergePatient(patient1);
        id = patientDashboardPage.findPatientId();
        patient1.uuid =  patientDashboardPage.getPatientUuidFromUrl();
        homePage.go();
        
        homePage.goToRegisterPatientApp();
        patient2.familyName = "Smith";
        patient2.givenName = "Jane";
        patient2.gender = "Female";
        patient2.estimatedYears = "25";
        patient2.address1 = "address";
        registrationPage.enterMergePatient(patient2);
        id2 = patientDashboardPage.findPatientId();
        homePage.go();
        
        homePage.goToDataManagement();
        dataManagementPage.goToMergePatient();
        dataManagementPage.enterPatient1(id);
        dataManagementPage.enterPatient2(id2);
        dataManagementPage.searchId(id);
        dataManagementPage.clickOnContinue();
        dataManagementPage.clickOnNo();
        dataManagementPage.enterPatient1(id);
        assertNotNull(dataManagementPage.CONTINUE);
    }

    @After
    public void tearDown() throws Exception {
        homePage.go();
        TestData.PatientInfo p = new TestData.PatientInfo();
        p.uuid = patient1.uuid;
        deletePatient(p);
        waitForPatientDeletion(patient1.uuid);
    }
}
