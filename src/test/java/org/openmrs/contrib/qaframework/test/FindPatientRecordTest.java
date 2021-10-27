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

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.ReferenceApplicationTestBase;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.FindPatientPage;

public class FindPatientRecordTest extends ReferenceApplicationTestBase {

	private TestData.PatientInfo patient;

	@Before
	public void createPatient() {
		patient = createTestPatient();
	}

	@After
	public void deletePatient() throws Exception {
		deletePatient(patient);
	}

	@Test
	@Category(BuildTests.class)
	public void findPatientRecordTest() {
		FindPatientPage findPatientPage = homePage.goToFindPatientRecord();
		findPatientPage.enterPatient(patient.identifier);
		findPatientPage.waitForPageToLoad();
		assertThat(findPatientPage.getFirstPatientIdentifier(),
				containsString(patient.identifier));
	}
}
