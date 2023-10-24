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
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdministrationPage extends Page {

	private static final String URL = "/admin/index.htm";
	private static final By MANAGE_USERS_LINK = By.cssSelector("#content a[href='/openmrs/admin/users/users.list']");
	private static final By MANAGE_ROLES_LINK = By.cssSelector("#legacyui-manageRoles > a");
	private static final By MANAGE_MODULES_LINK = By.cssSelector("#legacyui-manageModules a");
	private final static By MANAGE_VISIT_TYPES_LINK = By.cssSelector("#legacyui-manageVisitTypes a");
	private static final By MANAGE_PROVIDERS_LINK = By.cssSelector("#content a[href='/openmrs/admin/provider/index.htm']");
	private static final By MANAGE_PERSONS_LINK = By.cssSelector("#content a[href='/openmrs/admin/person/index.htm']");
	private static final By MANAGE_HTMLFORMS_PAGE_LINK = By.cssSelector("#content a[href*='/module/htmlformentry/htmlForms.list']");
	private static final By REPORT_ADMINISTRATION_LINK = By.cssSelector("#content a[href*='/module/reporting/reports/manageReports.form']");
	private static final By MANAGE_ENCOUNTERS_LINK = By.linkText("Manage Encounters");
	private static final By MANAGE_LOCATIONS_LINK = By.linkText("Manage Locations");
	private static final By MANAGE_PATIENTS_LINK = By.cssSelector("#content a[href='/openmrs/admin/patients/index.htm']");
	public AdministrationPage(Page page) {
		super(page);
	}

	public ManageUserPage clickOnManageUsers() {
		findElement(MANAGE_USERS_LINK).click();
		return new ManageUserPage(this);
	}

	public AdministrationManageRolesPage goToManageRolesPage() {
		clickOn(MANAGE_ROLES_LINK);
		return new AdministrationManageRolesPage(this);
	}

	public ManageProviderPage clickOnManageProviders() {
		clickOn(MANAGE_PROVIDERS_LINK);
		return new ManageProviderPage(this);
	}

	public VisitTypeListPage goToVisitTypePage() {
		findElement(MANAGE_VISIT_TYPES_LINK).click();
		return new VisitTypeListPage(this);
	}

	public ModulesPage goToManageModulesPage() {
		findElement(MANAGE_MODULES_LINK).click();
		return new ModulesPage(this);
	}

	public ManageHtmlFormsPage clickOnManageHtmlForms() {
		clickOn(MANAGE_HTMLFORMS_PAGE_LINK);
		return new ManageHtmlFormsPage(this);
	}

	public ManagePersonPage clickOnManagePersons() {
		findElement(MANAGE_PERSONS_LINK).click();
		return new ManagePersonPage(this);
	}

	public ManageReportsPage clickOnReportAdministrationLink() {
		waiter.until(ExpectedConditions.visibilityOfElementLocated(REPORT_ADMINISTRATION_LINK));
		clickOn(REPORT_ADMINISTRATION_LINK);
		return new ManageReportsPage(this);
	}

	public ManageEncountersPage clickOnManageEncounters() {
		clickOn(MANAGE_ENCOUNTERS_LINK);
		return new ManageEncountersPage(this);
	}

	public ManageLocationsOnAdminPage clickOnManageLocations() {
		clickOn(MANAGE_LOCATIONS_LINK);
		return new ManageLocationsOnAdminPage(this);
	}

	@Override
	public String getPageUrl() {
		return URL;
	}

	public PatientPage clickOnManagePatients() {
		findElement(MANAGE_PATIENTS_LINK).click();
		return new PatientPage(this);
	}
}
