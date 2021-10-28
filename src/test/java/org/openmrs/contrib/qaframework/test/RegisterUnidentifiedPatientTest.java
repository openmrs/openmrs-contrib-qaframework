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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.PatientGenerator;
import org.openmrs.contrib.qaframework.helper.ReferenceApplicationTestBase;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.helper.TestPatient;
import org.openmrs.contrib.qaframework.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.contrib.qaframework.page.RegistrationPage;

/**
 * Test for RA-472
 */
public class RegisterUnidentifiedPatientTest extends ReferenceApplicationTestBase {
	
	private TestPatient patient;
	
	@After
	public void tearDown() throws Exception {
		TestData.PatientInfo p = new TestData.PatientInfo();
		p.uuid = patient.uuid;
		deletePatient(p);
		waitForPatientDeletion(patient.uuid);
	}
	
	@Test
	@Category(BuildTests.class)
	public void registerUnidentifiedPatientTest() throws InterruptedException {
		RegistrationPage registrationPage = homePage.goToRegisterPatientApp();
		patient = PatientGenerator.generateTestPatient();
		registrationPage.enterUnidentifiedPatient(patient);
		
		assertTrue(registrationPage.getGenderInConfirmationPage().contains(patient.gender));
		
		ClinicianFacingPatientDashboardPage dashboardPage = registrationPage.confirmPatient();
		dashboardPage.waitForPage();
		patient.uuid = page.getPatientUuidFromUrl();
		assertThat(dashboardPage.getPatientGivenName(), is("UNKNOWN"));
		assertThat(dashboardPage.getPatientFamilyName(), is("UNKNOWN"));
	}
}
