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
import org.openmrs.contrib.qaframework.page.HtmlFormsPage;
import org.openmrs.contrib.qaframework.page.ManageFormsPage;
import org.openmrs.contrib.qaframework.page.ManageHtmlFormsPage;
import org.openqa.selenium.By;

public class FormSteps extends Steps {
	private static final String NAME = "newFormTest1";
	private static final String DESCRIPTION = "description of new form";
	private static final String VERSION = "1.2";
	private ManageFormsPage manageForm;
	private ManageHtmlFormsPage manageHtmlFormsPage;
	private HtmlFormsPage htmlFormsPage;
	
	@Before(RunTest.HOOK.SELENIUM_FORM)
	public void visitDashboard() {
		initiateWithLogin();
		manageForm = new ManageFormsPage(driver);
		htmlFormsPage = new HtmlFormsPage(manageHtmlFormsPage);
	}
	
	@After(RunTest.HOOK.SELENIUM_FORM)
	public void tearDown() {
		quit();
	}
	
	@Given("a user click on manage html forms")
	public void clickOnManageHtmlFormsPage() {
		manageHtmlFormsPage = homePage.goToAdministration().clickOnManageHtmlForms();
	}

	@Then("the system loads manage html forms page")
	public void systemLoadsManageHtmlFormsPage() {
		assertTrue(textExists("Manage HTML Forms"));
	}
	
	@When("a user check the availability of forms")
	public void instantiateFormPresence() throws InterruptedException {
		if (manageHtmlFormsPage.getElementsIfExisting(By.xpath("//*[contains(text(), '" + NAME + "')]")).isEmpty()) {
			manageHtmlFormsPage.clickOnNewHtmlForm();
			htmlFormsPage.createNewFormTest(NAME, DESCRIPTION, VERSION);
		}
		
		homePage.go();
		homePage.goToManageForm();
		if (!manageForm.addPresent()) {
			manageForm.delete();
		}
	}
	
	@Then("a user adds a form in the system")
	public void addFormTest() throws Exception {
		instantiateFormPresence();
		manageForm.add();
		manageForm.addLabel("Eye Report");
		manageForm.addIcon("icon-align-justify");
		manageForm.formIdFromUrl();
		manageForm.save();
		homePage.go();
		assertNotNull("Eye Report", dashboardPage.FORM_EXIST);
	}

	@And("user clicks on edit form")	
	public void editFormTest() throws InterruptedException{
		homePage.goToManageForm();
		manageForm.waitForPage();
		manageForm.editPath();
		manageForm.addLabel("Eye Test");
		manageForm.save();
		homePage.go();
		assertNotNull("Eye Test", dashboardPage.FORM_EXIST);
	}

	@Then("user clicks on delete form")	
	public void deleteFormTest() {
		homePage.goToManageForm();
		manageForm.waitForPage();
		manageForm.deletePath();
		assertNotNull("Add", manageForm.ADD);
	}
}
