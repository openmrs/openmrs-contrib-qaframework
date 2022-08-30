/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;

public class ManageAppointmentsPage extends Page {

	public static final By DELETE_REQUEST_ICON = By.cssSelector(".delete-item.icon-remove");
	private static final By APPOINTMENT = By.xpath("//table[@id='appointmentTable']/div[2]/div/div/div/div[2]/div[2]/div");
	private static final By SAVE_BUTTON = By.xpath("//div[@id='confirmAppointment']/div[2]/button[2]");
	private static final By CANCEL_BUTTON = By.cssSelector("#searchButtons button");
	private static final By SEARCH_BUTTON = By.cssSelector("#searchButtons button.confirm");
	private static final By NEXT_BUTTON = By.cssSelector("#selectAppointment button.confirm");
	private static final By BOOK_APPOINTMENT_ICON = By.cssSelector("i.icon-calendar:nth-child(1)");
	private static final By CONFIRM_CANCEL_APPOINTMENT_REQUEST_BUTTON  = By.cssSelector("#confirm-cancel-appointment-request div.dialog-content button.button.confirm.right");
	private static final By VIEW_ALL_TYPES_LINK = By.cssSelector("#viewAllAppointmentTypes a");

	public ManageAppointmentsPage(Page page) {
		super(page);
	}

	public void searchAppointment() {
		clickOn(SEARCH_BUTTON);
	}

	public void clickAppointment() {
		clickOn(APPOINTMENT);
	}

	public FindPatientPage saveAppointment() {
		clickOn(NEXT_BUTTON);
		clickOn(SAVE_BUTTON);
		return new FindPatientPage(this);
	}

	public void deleteRequest() {
		waitForElement(DELETE_REQUEST_ICON);
		clickOn(DELETE_REQUEST_ICON);
		clickOn(CONFIRM_CANCEL_APPOINTMENT_REQUEST_BUTTON);
	}

	public String getAppointmentServiceType() {
		final int serviceColumn = 1;
		return findElement(By.cssSelector("div.col" + serviceColumn + " > span")).getText();
	}

	public String getAppointmentStatus() {
		final int statusColumn = 4;
		return findElement(By.cssSelector("div.col" + statusColumn + " > span")).getText();
	}

	public void clickOnBookAppointment() {
		clickOn(BOOK_APPOINTMENT_ICON);
	}

	public void clickOnViewAllTypes() {
		clickOn(VIEW_ALL_TYPES_LINK);
	}

	public void clickOnService(String serviceName) {
		By link = By.xpath("/" + "/div[@id='allAppointmentTypesModal']"+ "/div[@class='dialog-content']" + "//*[text()='"+ serviceName + "']");
		clickOn(link);
	}

	public ClinicianFacingPatientDashboardPage clickCancel() {
		clickOn(CANCEL_BUTTON);
		return new ClinicianFacingPatientDashboardPage(this);
	}

	@Override
	public String getPageUrl() {
		return "/appointmentschedulingui/manageAppointments.page";
	}
}
