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

import java.awt.AWTException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hamcrest.Matchers;
import org.junit.After;
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

@Ignore
public class DuplicatePatientRegisterTest extends ReferenceApplicationTestBase {
	
	private TestPatient patient;
	
	@Before
	public void setUp() throws AWTException {
		patient = PatientGenerator.generateTestPatient();
	}
	
	@Test
	@Category(BuildTests.class)
	public void duplicatePatientRegisterTest() throws InterruptedException, ParseException {
		RegistrationPage registrationPage = homePage.goToRegisterPatientApp();
		registrationPage.enterPatient(patient);
		ClinicianFacingPatientDashboardPage dashboardPage = registrationPage.confirmPatient();
		dashboardPage.waitForPage();
		
		patient.uuid = dashboardPage.getPatientUuidFromUrl();
		
		dashboardPage.goToHomePage();
		
		registrationPage = homePage.goToRegisterPatientApp();
		registrationPage.enterPatient(patient);
		
		String name = registrationPage.getSimilarPatientName();
		assertThat(name, Matchers.is(patient.givenName + " " + patient.familyName));
		
		final String OLD_FORMAT = "d.MMM.yyyy";
		final String NEW_FORMAT = "dd.MMM.yyyy";
		
		String oldBirthDate = patient.birthDay + "." + patient.birthMonth.substring(0, 3) + "." + patient.birthYear;
		String newBirthDate;
		
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date date = sdf.parse(oldBirthDate);
		sdf.applyPattern(NEW_FORMAT);
		newBirthDate = sdf.format(date);
		
		String info = registrationPage.getSimilarPatientInfo();
		assertThat(info, Matchers.is(patient.gender + ", " + newBirthDate + ", " + patient.address1 + " " + patient.address2
		        + " " + patient.city + patient.state + patient.country + patient.postalCode));
	}
	
	@After
	public void tearDown() throws Exception {
		TestData.PatientInfo p = new TestData.PatientInfo();
		p.uuid = patient.uuid;
		deletePatient(p);
		waitForPatientDeletion(patient.uuid);
	}
}
