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
import org.openmrs.reference.page.ManageLocationAttributeTypesPage;
import org.openmrs.reference.page.ManageLocationTagsPage;
import org.openmrs.reference.page.ManageLocationsPage;

import static org.junit.Assert.assertTrue;

public class LocationsSteps extends Steps {

	private static final String LOCATION_DESCRIPTION = "The location is for expectant mothers";
	private static final String PREFERRED_HANDLER = "Location Field Gen Datatype Handler";
	private ManageLocationAttributeTypesPage attributePage;
	private ManageLocationTagsPage manageLocationTagsPage;
	private ConfigureMetadataPage metadataPage;
	private ManageLocationsPage manageLocationsPage;

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
		assertTrue(textExists("Configure Metadata"));
	}

	@When("a user clicks on manage location link from configure metadata page")
	public void goToManageLocationsPage() {
		manageLocationsPage = metadataPage.goToManageLocations();
	}

	@Then("the system loads manage locations page")
	public void systemLoadsManageLocationsPage() {
		assertTrue(textExists("Manage Locations"));
	}

	@And("a user clicks on add new location button")
	public void clickOnAddNewLocationButton() {
		manageLocationsPage.goToAddNewLocation();
	}

	@Then("the system loads add new location form")
	public void sytemLoadsLocationsForm() {
		assertTrue(textExists("Add New Location"));
	}

	@And("a user enters location details into the form")
	public void enterLocationDetails() {
		manageLocationsPage.enterLocationName("Bathroom ward");
		manageLocationsPage.enterLocationDescription("Expectant mothers ward");
		manageLocationsPage.enterAddress1("Kineya");
		manageLocationsPage.enterAddress2("Rwakitura");
		manageLocationsPage.enterCity("Kigali");
		manageLocationsPage.enterState("Central");
		manageLocationsPage.enterCountry("Rwanda");
		manageLocationsPage.enterPostalCode("99999");
		manageLocationsPage.selectParentLocation("East Africa");
		manageLocationsPage.selectLocationTag();
	}

	@And("a user clicks on save location button")
	public void clickOnSaveLocationButton() {
		manageLocationsPage.saveLocation();
	}

	@Then("the system adds location into the locations table")
	public void systemAddsLocation() {
		assertTrue(textExists("Location Saved"));
	}
	
	@And("a user clicks on delete location icon and confirms the action")
	public void deleteLocation() {
		manageLocationsPage.purgeLocation("Bathroom");
	}
	
	@Then("the system deletes location from the system")
	public void systemDeletesLocation() {
		assertTrue(textExists("Location Deleted"));
	}

	@When("a user clicks on manage location tags link from configure metadata page")
	public void goToManageLocationTagsPage() {
		manageLocationTagsPage = metadataPage.goToManageLocationTagPage();
	}

	@Then("the system loads manage location tags page")
	public void systemLoadsManageLocationTagsPage() {
		assertTrue(textExists("Manage Location Tags"));
	}

	@And("a user clicks on add new location tag button")
	public void goToLocationTagsForm() {
		manageLocationTagsPage.goToAddNewLocationTagForm();
	}

	@Then("the system loads add new location tag form")
	public void systemLoadsLocationTagsForm() {
		assertTrue(textExists("Add New Location Tag"));
	}

	@And("a user enters location tag details into the form")
	public void enterLocationTagDetails() {
		manageLocationTagsPage.enterLocationTagName("AAlovera");
		manageLocationTagsPage.enterLocationTagDescription("It cures C-19");
	}

	@When("a user edits location tag details in the form")
	public void editLocationTagDetails() {
		manageLocationTagsPage.enterLocationTagName("AAQuality");
		manageLocationTagsPage.enterLocationTagDescription("This is quality");
	}
	
	@And("a user clicks on save location tag button")
	public void clickOnSaveLocationTagsButton() {
		manageLocationTagsPage.saveLocationTag();
	}

	@Then("the system adds location tag into the location tags table")
	public void systemAddsLocationTag() {
		assertTrue(textExists("Location Tag Saved"));
	}

	@And("a user clicks on delete location attribute tag icon")
	public void deleteLocationAttributeTag() {
		manageLocationTagsPage.deleteLocationTag();
	}

	@Then("the system deletes loaction attribute tag from the table")
	public void systemDeletesLocationAttributeTag() {
		assertTrue(textExists("Location Attribute Tag Deleted"));
	}

	@When("a user clicks on manage location attribute types link from configure metadata page")
	public void goToManageLocationAttributeTypesPage() {
		attributePage = metadataPage.goToManageLocationAttributeTypesPage();
	}

	@Then("the system loads manage location attribute types page")
	public void systemLoadsManageLocationAttributeTypesPage() {
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
		attributePage.enterLocationAttributeTypeName("Maternity");
		attributePage.enterLocationAttributeTypeDescription(LOCATION_DESCRIPTION);
		attributePage.enterMinimumOccurs("1");
		attributePage.enterMaximumOccurs("3");
		attributePage.selectDatatype("Location Datatype");
		attributePage.selectPreferredHandler(PREFERRED_HANDLER);
	}
	
	@And("a user clicks on save location attribute type button")
	public void clickOnSaveLocationAttributeTypeButton() {
		attributePage.clickOnSaveButton();
		attributePage.waitForPage();
	}

	@Then("the system adds location attribute type into the location attribute types table")
	public void systemAddsLocationAttributeType() {
		assertTrue(textExists("Location Attribute Type Saved"));
	}

	@When("a user clicks on delete location attribute type icon and confirms action")
	public void deleteLocationAttributeType() {
		attributePage.purgeLocation();
	}

	@Then("the system deletes the location attribute type from the table")
	public void systemDeletesLocationAttributeType() {
		assertTrue(textExists("Location Attribute Deleted"));
	}
}
