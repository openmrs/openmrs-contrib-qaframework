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

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.ActiveVisitsPage;
import org.openmrs.contrib.qaframework.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.contrib.qaframework.page.VisitNotePage;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class AddDiagnosisToVisitNoteTest extends LocationSensitiveApplicationTestBase {

    private TestData.PatientInfo patient;

    @Before
    public void setup() throws Exception {
        patient = createTestPatient();
        new TestData.TestVisit(patient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
    }

    @Test
    @Category(BuildTests.class)
    public void addDiagnosisToVisitNoteTest() {
        ActiveVisitsPage activeVisitsPage = homePage.goToActiveVisitsSearch();
        activeVisitsPage.search(patient.identifier);

        ClinicianFacingPatientDashboardPage patientDashboardPage = activeVisitsPage.goToPatientDashboardOfLastActiveVisit();
        VisitNotePage visitNotePage = patientDashboardPage.goToVisitNote();
        visitNotePage.enterDiagnosis("Pne");
        visitNotePage.enterSecondaryDiagnosis("Bleed");
        assertEquals("Pneumonia", visitNotePage.primaryDiagnosis());
        assertEquals("Bleeding", visitNotePage.secondaryDiagnosis());

        patientDashboardPage = visitNotePage.save();

        List<String> diagnoses = patientDashboardPage.getDiagnoses();
        assertThat(diagnoses, hasItems("Pneumonia", "Bleeding"));
    }

    @After
    public void tearDown() throws Exception {
        deletePatient(patient);
    }
}
