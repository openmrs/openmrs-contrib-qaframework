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

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.PatientGenerator;
import org.openmrs.contrib.qaframework.helper.ReferenceApplicationTestBase;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.helper.TestPatient;
import org.openmrs.contrib.qaframework.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.contrib.qaframework.page.RegistrationPage;

import io.cucumber.java.After;

@Ignore
public class NamePatientAccentedLetterTest extends ReferenceApplicationTestBase {
	
	private TestPatient patient;
	
	@Before
	public void setUp() throws Exception {
		patient = PatientGenerator.generateTestPatient();
	}
	
	@Test
	@Category(BuildTests.class)
	public void namePatientAccentedLetterTest() throws Exception {
		RegistrationPage registrationPage = homePage.goToRegisterPatientApp();
		patient.givenName = "Mike";
		patient.familyName = "Kłoczkowski";
		patient.gender = "Male";
		registrationPage.enterPatient(patient);
		ClinicianFacingPatientDashboardPage dashboardPage = registrationPage.confirmPatient();
		String name = dashboardPage.getPatientFamilyName();
		assertThat(name, Matchers.is(patient.familyName));
		patient.uuid = dashboardPage.getPatientUuidFromUrl();
	}
	
	@After
	public void tearDown() throws Exception {
		TestData.PatientInfo p = new TestData.PatientInfo();
		p.uuid = patient.uuid;
		deletePatient(p);
		waitForPatientDeletion(patient.uuid);
	}
}
