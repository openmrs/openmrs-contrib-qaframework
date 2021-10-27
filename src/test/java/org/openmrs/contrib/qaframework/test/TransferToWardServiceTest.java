/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * <p>
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.ReferenceApplicationTestBase;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.FindPatientPage;
import org.openmrs.contrib.qaframework.page.PatientVisitsDashboardPage;

public class TransferToWardServiceTest extends ReferenceApplicationTestBase {

	private static final String INPATIENT_WARD = "Inpatient Ward";
	private static final String ISOLATION_WARD = "Isolation Ward";
	private TestData.PatientInfo testPatient;

	@Before
	public void createTestData() throws Exception {
		testPatient = createTestPatient();
	}

	@Test
	@Category(BuildTests.class)
	public void transferToWardServiceTest() {
		FindPatientPage findPatientPage = homePage.goToFindPatientRecord();
		findPatientPage.enterPatient(testPatient.identifier);
		findPatientPage.waitForPageToLoad();
		PatientVisitsDashboardPage patientVisitsDashboardPage = findPatientPage
				.clickOnFirstPatient().startVisit();

		patientVisitsDashboardPage.goToAdmitToInpatient().confirm(
				INPATIENT_WARD);
		assertTrue(patientVisitsDashboardPage.containsText("Admission"));

		patientVisitsDashboardPage.goToTransferToWardServicePage().confirm(
				ISOLATION_WARD);
		assertTrue(patientVisitsDashboardPage.containsText("Transfer"));
		patientVisitsDashboardPage.waitForPage();

		assertThat(patientVisitsDashboardPage.getEncountersCount(), is(2));
	}

	@After
	public void deleteTestPatient() {
		deletePatient(testPatient);
	}
}
