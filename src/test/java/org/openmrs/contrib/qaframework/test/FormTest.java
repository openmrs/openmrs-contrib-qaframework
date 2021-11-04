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

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.TestBase;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.contrib.qaframework.page.HomePage;
import org.openmrs.contrib.qaframework.page.HtmlFormsPage;
import org.openmrs.contrib.qaframework.page.ManageFormsPage;
import org.openmrs.contrib.qaframework.page.ManageHtmlFormsPage;
import org.openqa.selenium.By;

/**
 * Created by nata on 24.06.15.
 */

@Ignore
public class FormTest extends TestBase {
	
	private static String name = "newFormTest1";
	private static String description = "description of new form";
	private static String version = "1.2";
	private HomePage homePage;
	private AdministrationPage administrationPage;
	private ManageFormsPage manageForm;
	private ManageHtmlFormsPage manageHtmlFormsPage;
	private HtmlFormsPage htmlFormsPage;
	
	private ClinicianFacingPatientDashboardPage patientDashboardPage;
	
	@Before
	public void setUp() throws Exception {
		homePage = new HomePage(page);
		assertPage(homePage.waitForPage());
		administrationPage = new AdministrationPage(page);
		manageForm = new ManageFormsPage(driver);
		htmlFormsPage = new HtmlFormsPage(page);
		manageHtmlFormsPage = new ManageHtmlFormsPage(page);
		patientDashboardPage = new ClinicianFacingPatientDashboardPage(page);
	}
	
	@Test
	@Category(BuildTests.class)
	public void initiateFormPresence() throws InterruptedException {
		manageHtmlFormsPage = homePage.goToAdministration().clickOnManageHtmlForms();
		if (manageHtmlFormsPage.getElementsIfExisting(By.xpath("//*[contains(text(), '" + name + "')]")).isEmpty()) {
			manageHtmlFormsPage.clickOnNewHtmlForm();
			htmlFormsPage.createNewFormTest(name, description, version);
		}
		homePage.go();
		homePage.goToManageForm();
		if (!manageForm.addPresent()) {
			manageForm.delete();
		}
	}
	
	@Test
	@Category(BuildTests.class)
	public void addFormTest() throws Exception {
		initiateFormPresence();
		manageForm.add();
		manageForm.addLabel("Eye Report");
		manageForm.addIcon("icon-align-justify");
		manageForm.formIdFromUrl();
		manageForm.save();
		homePage.go();
		assertNotNull("Eye Report", patientDashboardPage.FORM_EXIST);
	}
	
	@Test
	@Category(BuildTests.class)
	public void editFormTest() throws Exception {
		addFormTest();
		homePage.goToManageForm();
		manageForm.waitForPage();
		manageForm.editPath();
		manageForm.addLabel("Eye Test");
		manageForm.save();
		homePage.go();
		assertNotNull("Eye Test", patientDashboardPage.FORM_EXIST);
	}
	
	@Test
	@Category(BuildTests.class)
	public void deleteFormTest() throws Exception {
		addFormTest();
		homePage.goToManageForm();
		manageForm.waitForPage();
		manageForm.deletePath();
		assertNotNull("Add", manageForm.ADD);
	}
	
	@After
	public void tearDown() throws Exception {
		homePage.go();
	}
}
