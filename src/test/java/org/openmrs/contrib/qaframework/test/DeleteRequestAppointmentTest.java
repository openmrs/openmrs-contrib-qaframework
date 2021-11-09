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

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.ActiveVisitsPage;
import org.openmrs.contrib.qaframework.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.contrib.qaframework.page.ManageAppointmentsPage;
import org.openmrs.contrib.qaframework.page.RequestAppointmentPage;

public class DeleteRequestAppointmentTest extends LocationSensitiveApplicationTestBase {
	
	private static final String SERVICE_NAME = "Oncology";
	
	private TestData.PatientInfo patient;
	
	@Before
	public void setUp() throws Exception {
		patient = createTestPatient();
		createTestVisit();
	}
	
	@Test
	@Category(BuildTests.class)
	public void deleteRequestAppointmentTest() {
		ActiveVisitsPage activeVisitsPage = homePage.goToActiveVisitsSearch();
		activeVisitsPage.search(patient.identifier);
		ClinicianFacingPatientDashboardPage patientDashboardPage = activeVisitsPage.goToPatientDashboardOfLastActiveVisit();
		RequestAppointmentPage requestAppointmentPage = patientDashboardPage.clickOnRequest();
		requestAppointmentPage.enterAppointmentType(SERVICE_NAME);
		requestAppointmentPage.enterMinimumValue("0");
		requestAppointmentPage.selectMinimumUnits("Day(s)");
		patientDashboardPage = requestAppointmentPage.saveRequest();
		
		List<String> appointmentRequestsList = patientDashboardPage.getAppointmentRequestsList();
		assertTrue(appointmentRequestsList.get(0).equals(SERVICE_NAME));
		
		ManageAppointmentsPage manageAppointmentsPage = patientDashboardPage.goToManageAppointments();
		manageAppointmentsPage.deleteRequest();
		patientDashboardPage = manageAppointmentsPage.clickCancel();
		manageAppointmentsPage = patientDashboardPage.goToManageAppointments();
		assertTrue(manageAppointmentsPage.containsText("No appointment requests"));
	}
	
	@After
	public void tearDown() throws Exception {
		deletePatient(patient);
	}
	
	private void createTestVisit() {
		new TestData.TestVisit(patient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
	}
}
