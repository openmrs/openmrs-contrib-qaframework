/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.AppointmentBlocksPage;
import org.openmrs.contrib.qaframework.page.AppointmentSchedulingPage;
import org.openmrs.contrib.qaframework.page.ManageAppointmentsPage;
import org.openmrs.contrib.qaframework.page.ManageProviderSchedulesPage;
import org.openmrs.contrib.qaframework.page.RequestAppointmentPage;

public class AppointmentSteps extends Steps {

	private AppointmentSchedulingPage appointmentSchedulingPage;
	private AppointmentBlocksPage appointmentBlocksPage;
	private ManageAppointmentsPage manageAppointmentsPage;
	private ManageProviderSchedulesPage manageProviderSchedulesPage;
	private RequestAppointmentPage requestAppointmentPage;
	private TestData.PatientInfo testPatient;
	private static final String FIRST_SERVICE_NAME = "Oncology";
	private static final String SECOND_SERVICE_NAME = "Dermatology";
	private static final String LOCATION = "Laboratory";

	@Before(RunTest.HOOK.SELENIUM_APPOINTMENT)
	public void visitDashboard() {
		initiateWithLogin();
		testPatient = createTestPatient();
		findPatientPage = homePage.goToFindPatientRecord();
		findPatientPage.enterPatient(testPatient.identifier);
		findPatientPage.waitForPageToLoad();
		dashboardPage = findPatientPage.clickOnFirstPatient();
	}

	@After(RunTest.HOOK.SELENIUM_APPOINTMENT)
	public void destroy() {
		deletePatient(testPatient);
		quit();
	}

	@Given("a user clicks on Request Appointment link from the Patient dashboard")
	public void loadRequestAppointmentPage() {
		requestAppointmentPage = (RequestAppointmentPage) dashboardPage.clickOnRequest();
	}

	@When("the system loads Request Appointment page")
	public void systemLoadsRequestAppointmentPage() {
		assertTrue(textExists("Request Appointment"));
	}

	@And("a user fills in the appointment request details")
	public void captureAppointmentRequestDetails() {
		requestAppointmentPage.enterAppointmentType(FIRST_SERVICE_NAME);
		requestAppointmentPage.enterMinimumValue("0");
		requestAppointmentPage.selectMinimumUnits("Day(s)");
		requestAppointmentPage.enterMaximumValue("2");
		requestAppointmentPage.selectMaximumUnits("Day(s)");
		requestAppointmentPage.addAppointmentRequestNote("I need to see a doctor first");
	}

	@And("a user clicks save button")
	public void userClicksOnSaveAppointmentRequest() {
		dashboardPage = requestAppointmentPage.saveRequest();
	}

	@Then("the system adds appointment request into the appointment requests table")
	public void systemSavesAppointmentRequest() {
		assertNotNull(dashboardPage.getAppointmentRequestsList());
	}

	@When("a user clicks on Schedule Appointment link from the Patient dashboard")
	public void loadManageAppointmentsPage() {
		manageAppointmentsPage = (ManageAppointmentsPage) dashboardPage.goToManageAppointments();
	}

	@And("the system loads Manage Appointments page")
	public void systemLoadsManageAppointmentsPage() {
		assertTrue(manageAppointmentsPage.containsText(testPatient.identifier));
	}

	@And("a user clicks on Cancel this Request icon")
	public void userClicksOnDeleteAppointmentRequest() {
		manageAppointmentsPage.deleteRequest() ;
	}

	@Then("the system deletes appointment request from the request table")
	public void systemDeletesAppointmentRequest() {
		assertTrue(manageAppointmentsPage.containsText("No appointment requests"));
	}

	@When("a user clicks on Appointment scheduling app from the home page")
	public void loadAppointmentSchedulingPage() {
		dashboardPage.goToHomePage();
		appointmentSchedulingPage = (AppointmentSchedulingPage ) homePage.goToAppointmentScheduling();
	}

	@Then("the system loads the appointment scheduling page")
	public void systemLoadsAppointmentSchedulingPage() {
		assertTrue(textExists("Appointment Scheduling"));
	}

	@And("a user clicks on Manage Provider Schedules app")
	public void loadManageProviderSchedulesPage() {
		manageProviderSchedulesPage = (ManageProviderSchedulesPage) appointmentSchedulingPage.goToManageProviderSchedules();
	}

	@And("the system loads Manage Provider Schedules page")
	public void systemLoadsManageProviderSchedulesPage() {
		assertTrue(textExists("Manage Appointment Blocks"));
	}

	@And("a user fills in the details of the appointment block")
	public void captureAppointmentBlockDetails() {
		manageProviderSchedulesPage.selectLocation(LOCATION);
		manageProviderSchedulesPage.clickOnNextWeekday();
		manageProviderSchedulesPage.selectLocationBlock(LOCATION);
		manageProviderSchedulesPage.enterMinimumTimeValue("06", "30");
		manageProviderSchedulesPage.clickOnStartTimeButton();
		manageProviderSchedulesPage.enterMaximumTimeValue("09", "30");
		manageProviderSchedulesPage.clickOnEndTimeButton();
		manageProviderSchedulesPage.enterService(FIRST_SERVICE_NAME);
	}

	@And("a user clicks on the save button")
	public void saveAppointmentBlockDetails() {
		manageProviderSchedulesPage.clickOnSave();
	}

	@Then("the system saves the appointment block")
	public void systemSavesAppointmentBlockDetails() {
		assertTrue(manageProviderSchedulesPage.containsText(FIRST_SERVICE_NAME));
		dashboardPage.goToHomePage();
	}

	@And("a user clicks on the Manage Provider Schedules app")
	public void loadAppointmentBlocksPage() {
		appointmentBlocksPage = (AppointmentBlocksPage ) appointmentSchedulingPage.goToAppointmentBlock();
	}

	@And("the system loads Appointment Blocks page")
	public void systemLoadsAppointmentBlocksPage() {
		assertTrue(textExists("Manage Appointment Blocks"));
	}

	@And("a user clicks on an appointment block")
	public void clickOnAppointmentBlock() throws InterruptedException {
		appointmentBlocksPage.selectLocation(LOCATION);
		appointmentBlocksPage.clickOnCurrentDay();
		appointmentBlocksPage.clickOnAppointment();
	}

	@And("a user clicks on Edit appointment block")
	public void loadEditAppointmentBlockSection() {
		appointmentBlocksPage.clickOnEdit();
	}

	@And("the system loads Edit Appointment Block section")
	public void systemLoadsEditAppointmentBlockSection() {
		assertTrue(textExists("Edit Appointment Block"));
	}

	@And("a user edits the Appointment block")
	public void editAppointmentBlock() {
		appointmentBlocksPage.removeAppointment();
		appointmentBlocksPage.enterService(SECOND_SERVICE_NAME);
	}

	@And("a user clicks on Save button")
	public void saveAppointmentBlock() {
		appointmentBlocksPage.clickOnSave();
	}

	@Then("the system saves the edited appointment block")
	public void systemSavesEditedAppointmentBlock() {
		assertTrue(appointmentBlocksPage.containsText(SECOND_SERVICE_NAME));
	}

	@And("a user clicks on Delete appointment block")
	public void deleteAppointmentBlock() throws InterruptedException {
		appointmentBlocksPage.clickOnDelete();
		appointmentBlocksPage.clickOnConfirmDelete();
		appointmentBlocksPage.waitForPage();
	}

	@Then("the system deletes appointment block from the appointment Blocks table")
	public void systemDeletesAppointmentBlock() {
		assertTrue(appointmentBlocksPage.containsText("Manage Appointment Blocks"));
		dashboardPage.goToHomePage();
	}

	@And("a user clicks on Manage Appointments app")
	public void loadFindPatientPage() {
		findPatientPage = appointmentSchedulingPage.goToManageAppointments();
	}

	@And("user searches patient")
	public void searchPatient() {
		findPatientPage.enterPatient(testPatient.identifier);
	}

	@And("user selects first patient")
	public void selectFirstPatient() {
		manageAppointmentsPage = (ManageAppointmentsPage) findPatientPage.clickOnFirstPatientAppointment();
	}

	@And("a user clicks on book appointment icon")
	public void clickOnBookAppointment() {
		manageAppointmentsPage.clickOnBookAppointment();
	}

	@And("a user searches appointment request")
	public void searchAppointmentRequest() {
		manageAppointmentsPage.clickOnViewAllTypes();
		manageAppointmentsPage.clickOnService(FIRST_SERVICE_NAME);
		manageAppointmentsPage.searchAppointment();
	}

	@And("a user clicks on the appointment")
	public void clickOnAppointment() {
		manageAppointmentsPage.clickAppointment();
	}

	@And("a user clicks the Save button")
	public void saveScheduledAppointment() {
		manageAppointmentsPage.saveAppointment();
	}

	@And("user searches the patient")
	public void searchThePatient() {
		findPatientPage.enterPatient(testPatient.getName());
	}

	@Then("the system loads the Manage Appointments page")
	public void systemLoadsTheManageAppointmentsPage() {
		assertTrue(manageAppointmentsPage.containsText("Scheduled"));
	}
}
