/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * <p>
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.ConfigureMetadataPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.ManageLocationAttributeTypesPage;
import org.openmrs.reference.page.ManageLocationTagsPage;
import org.openmrs.reference.page.ManageLocationsPage;

import static org.junit.Assert.assertTrue;

public class LocationsSteps extends Steps {

	private static final String PREFERRED_HANDLER = "Location Field Gen Datatype Handler";
	private ManageLocationAttributeTypesPage attributePage;
	private ManageLocationTagsPage locationTagsPage;
	private ManageLocationsPage locationsPage;
	private ConfigureMetadataPage metadataPage;
	private HomePage homePage;

	@Before(RunTest.HOOK.SELENIUM_LOCATION)
	public void visitHomePage() {
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_LOCATION)
	public void destroy() {
		quit();
	}

	@Given("a user clicks on configure metadata link from home page")
	public void goToConfigureMetadataPage() {
		metadataPage = homePage.goToConfigureMetadata();
	}

	@Then("the system loads configure metadata page")
	public void systemLoadsConfigureMetadataPage() {
		// assertPage(metadataPage.waitForPage());
		assertTrue(textExists("Configure Metadata"));
	}

	@When("a user clicks on manage location link from configure metadata page")
	public void goToManageLocationsPage() {
		locationsPage = metadataPage.goToManageLocations();
	}

	@Then("the system loads manage locations page")
	public void systemLoadsManageLocationsPage() {
		// assertPage(locationsPage.waitForPage());
		assertTrue(textExists("Manage Locations"));
	}

	@And("a user clicks on add new location button")
	public void clickOnAddNewLocationButton() {
		locationsPage.goToAddNewLocation();
	}

	@Then("the system loads add new location form")
	public void sytemLoadsLocationsForm() {
		assertTrue(textExists("Add New Location"));
	}

	@And("a user enters location details into the form")
	public void enterLocationDetails() {
		locationsPage.enterLocationName("Maternity ward");
		locationsPage.enterLocationDescription("Expectant mothers ward");
		locationsPage.enterAddress1("Upper floor");
		locationsPage.enterAddress2("Lower floor");
		locationsPage.enterCity("Gulu");
		locationsPage.enterState("Central");
		locationsPage.enterCountry("Uganda");
		locationsPage.enterPostalCode("25619");
		locationsPage.selectLocationTag();
	}

	@And("a user clicks on save location button")
	public void clickOnSaveLocationButton() {
		locationsPage.saveLocation();
	}

	@Then("the system adds location into the locations table")
	public void systemAddsLocation() {
		assertTrue(textExists("Location Saved"));
	}

	@When("a user clicks on manage location tags link from configure metadata page")
	public void goToManageLocationTagsPage() {
		locationTagsPage = metadataPage.goToManageLocationTagPage();
	}

	@Then("the system loads manage location tags page")
	public void systemLoadsManageLocationTagsPage() {
		assertPage(locationTagsPage.waitForPage());
		assertTrue(textExists("Manage Location Tags"));
	}

	@And("a user clicks on add new location tag button")
	public void goToLocationTagsForm() {
		locationTagsPage.goToAddNewLocationTagForm();
	}

	@Then("the system loads add new location tag form")
	public void systemLoadsLocationTagsForm() {
		assertTrue(textExists("Add New Location Tag"));
	}

	@And("a user enters location tag details into the form")
	public void enterLocationTagDetails() {
		locationTagsPage.enterLocationTagName("Maternity Ward Wing");
		locationTagsPage.enterLocationTagDescription("For expectant mothers");
	}

	@And("a user clicks on save location tag button")
	public void clickOnSaveLocationTagsButton() {
		locationTagsPage.saveLocationTag();
	}

	@Then("the system adds location tag into the location tags table")
	public void systemAddsLocationTag() {
		assertTrue(textExists("Location Tag Saved"));
	}

	@When("a user clicks on manage location attribute types link from configure metadata page")
	public void goToManageLocationAttributeTypesPage() {
		attributePage = metadataPage.goToManageLocationAttributeTypesPage();
	}

	@Then("the system loads manage location attribute types page")
	public void systemLoadsManageLocationAttributeTypesPage() {
		// assertPage(attributePage.waitForPage());
		assertTrue(textExists("Manage Location Attribute Types"));		
	}

	@And("a user clicks on add new location attribute type button")
	public void goToManageLocationAttributeTypeForm() {
		attributePage.goToAddNewLocationAttributeTypeForm();
	}

	@Then("the system loads add new location attribute type form")
	public void systemLoadsManageLocationAttributeTypeForm() {
		assertTrue(textExists("Add New Location Attribute Type"));
	}

	@And("a user enters location attribute type details into the form")
	public void enterLocationAttributeTypeDetails() {
		attributePage.enterLocationAttributeTypeName("Female Ward");
		attributePage
				.enterLocationAttributeTypeDescription("The location is for expectant mothers");
		attributePage.enterMinimumOccurs("1");
		attributePage.enterMaximumOccurs("3");
		attributePage.selectDatatype("Location Datatype");
		attributePage.selectPreferredHandler(PREFERRED_HANDLER);
	}

	@And("a user clicks on save location attribute type button")
	public void clickOnSaveButton() {
		attributePage.clickOnSaveButton();
		attributePage.waitForPageToLoad();
	}

	@Then("the system adds location attribute type into the location attribute types table")
	public void systemAddsLocationAttributeType() {
		assertTrue(textExists("Location Attribute Type Saved"));
		homePage.go();
	}
}
