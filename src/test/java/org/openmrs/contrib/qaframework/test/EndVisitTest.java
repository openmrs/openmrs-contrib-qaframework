/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.test;

import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.contrib.qaframework.page.FindPatientPage;
import org.openmrs.contrib.qaframework.page.PatientVisitsDashboardPage;

public class EndVisitTest extends LocationSensitiveApplicationTestBase {
	
	private TestData.PatientInfo patient;
	
	@Before
	public void setUp() throws Exception {
		patient = createTestPatient();
		new TestData.TestVisit(patient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
	}
	
	@Test
	@Category(BuildTests.class)
	public void endVisitTest() {
		FindPatientPage findPatientPage = homePage.goToFindPatientRecord();
		findPatientPage.enterPatient(patient.identifier);
		findPatientPage.waitForPageToLoad();
		ClinicianFacingPatientDashboardPage clinicianFacingPatientDashboardPage = findPatientPage.clickOnFirstPatient();
		PatientVisitsDashboardPage patientVisitsDashboardPage = clinicianFacingPatientDashboardPage.goToRecentVisits();
		patientVisitsDashboardPage.endVisit();
		patientVisitsDashboardPage.waitForPageToLoad();
		assertNull(patientVisitsDashboardPage.getActiveVisit());
	}
	
	@After
	public void tearDown() throws Exception {
		deletePatient(patient);
	}
}
