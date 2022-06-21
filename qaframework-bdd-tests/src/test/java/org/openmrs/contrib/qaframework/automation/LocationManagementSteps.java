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

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.AddEditLocationPage;
import org.openmrs.contrib.qaframework.page.ConfigureMetadataPage;
import org.openmrs.contrib.qaframework.page.LocationPage;
import org.openmrs.contrib.qaframework.page.ManageLocationAttributeTypesPage;
import org.openmrs.contrib.qaframework.page.ManageLocationTagsPage;
import org.openmrs.contrib.qaframework.page.ManageLocationsPage;

public class LocationManagementSteps extends Steps {
    
    private ConfigureMetadataPage configureMetadataPage;
    private LocationPage locationPage;
    private ManageLocationsPage manageLocationsPage;
    private AddEditLocationPage addEditLocationPage;
    private ManageLocationTagsPage manageLocationTagsPage;
    private ManageLocationAttributeTypesPage manageLocationAttributeTypesPage;
    
    @Before(RunTest.HOOK.SELENIUM_LOCATION_MANAGEMENT)
    public void visitDashboard() {
	    initiateWithLogin();
    }
	
    @After(RunTest.HOOK.SELENIUM_LOCATION_MANAGEMENT)
    public void destroy() {
        quit();
    }

    @Given("a user clicks on the configure metadata link from the home page")
    public void userClicksTheConfigureMetadataLink() {
        configureMetadataPage = homePage.goToConfigureMetadata();
    }

    @Then ("the system loads configure metadata link dashboard")
    public void systemLoadsMetadataDashboard() {
        assertTrue(textExists("Configure Metadata"));
    }

    @When ("a user clicks on Manage Location Attribute Types")
    public void userClicksOnManageLocationAttributeTypes() {
        manageLocationAttributeTypesPage = configureMetadataPage.goToManageLocationAttributeTypesPage();
    }

    @Then ("the system loads the manage location attribute type page")
    public void theSystemLoadsTheManageLocationAttributeTypePage() {
        assertTrue(textExists("Manage Location Attribute Types"));
    }
    
    @And ("a user clicks on add new location attribute type")
    public void userClicksOnAddNewLocationAttributeType() {
        manageLocationAttributeTypesPage.goToAddNewLocationAttributeTypeForm();
    }

    @And ("a user fills the add new location attribute type form")
    public void userFillsTheAddNewLocationAttributeTypeForm() {
        manageLocationAttributeTypesPage.enterLocationAttributeTypeName("location AttributeType Name");
        manageLocationAttributeTypesPage.enterLocationAttributeTypeDescription("Location AttributeType Description");
        manageLocationAttributeTypesPage.enterMinimumOccurs("1");
        manageLocationAttributeTypesPage.enterMaximumOccurs("4");
        manageLocationAttributeTypesPage.selectDatatype("Location Datatype");
        manageLocationAttributeTypesPage.enterDatatypeConfiguration("datatype Configuration");
        manageLocationAttributeTypesPage.selectPreferredHandler("Location Field Gen Datatype Handler");
        manageLocationAttributeTypesPage.enterHandlerConfiguration("Location Handler Configuration");
    }

    @Then ("the user saves the form")
    public void userSavesTheForm() {
        manageLocationAttributeTypesPage.clickOnSaveButton();
        assertTrue(textExists("Location Attribute Type Saved"));
    }

    @And ("the user clicks on the edit icon")
    public void userClicksOnTheEditIcon() {
        manageLocationAttributeTypesPage.goToEditLocationAttributeTypeForm();
    }

    @And ("the user fills in the prefered details in the edit location attribute type form")
    public void userFillsInPreferedDetails() {
        manageLocationAttributeTypesPage.enterLocationAttributeTypeDescription("locationAttributeTypeDescription");
        manageLocationAttributeTypesPage.enterMaximumOccurs("100");
        manageLocationAttributeTypesPage.enterMinimumOccurs("11");
    }

    @And ("the user clicks on the retire icon")
    public void userClicksOnTheRetireIcon() {
        manageLocationAttributeTypesPage.retireLocation();
    }

    @Then ("the system retires the location attribute type")
    public void systemRetiresTheLocationAttributeType() {
        assertTrue(textExists("Location Attribute Type Retired"));
    }

    @And ("the user clicks the restore location attribute type icon")
    public void userClicksRestoreLocationAttributeTypeIcon () {
        manageLocationAttributeTypesPage.restoreLocationAttributeType();
        assertTrue(textExists("Location Attribute Type restored"));
    }

    @And ("the user clicks on the delete forever icon")
    public void userClicksOnTheDeleteForeverIcon() {
        manageLocationAttributeTypesPage.purgeLocation();
    }

    @Then ("the system deletes the location attribute type forever")
    public void systemDeletesLocationAttributeTypeForever() {
        assertTrue(textExists("Location Attribute Type Deleted"));
    }

    @When ("a user clicks on manage location tag")
    public void userClicksOnManageLocationTag() {
        manageLocationTagsPage = configureMetadataPage.goToManageLocationTagPage();
    }

    @Then ("the system loads the manage location tag")
    public void systemLoadsTheManageLocationTag() {
        assertTrue(textExists("Manage Location Tags"));
    }

    @And ("the user clicks on add new location tag")
    public void userClicksOnAddNewLocationTag() {
        manageLocationTagsPage.goToAddNewLocationTagForm();
        assertTrue(textExists("Add New Location Tag"));
    }

    @And ("a user fills add new location tag form")
    public void userFillsAddNewLocationTagForm() {
        manageLocationTagsPage.enterLocationTagName("locationTagName");
        manageLocationTagsPage.enterLocationTagDescription("locationTagDescription");
    }

    @Then ("a user saves add New location tag form")
    public void userSavesAddNewLocationTagForm() {
        manageLocationTagsPage.saveLocationTag();
        assertTrue(textExists("Location Tag Saved"));
    }

    @And ("the user clicks on the edit location tag icon")
    public void userClicksOnTheEditLocationTagIcon() {
        manageLocationTagsPage.goToEditLocationTagForm();
    }

    @And ("the user fills in the prefered details in the edit location tag form")
    public void userFillsInThePreferedDetailsInTheEditLocationTagForm() {
        manageLocationTagsPage.enterLocationTagName("editedlocationTagName");
        manageLocationTagsPage.enterLocationTagDescription("editedlocationTagDescription");
    }

    @And ("the user clicks on the retire location tag button")
    public void userClicksOnTheRetireLocationTagButton() {
        manageLocationTagsPage.retireLocation();
    }

    @Then ("the system retires the location tag")
    public void systemRetiresTheLocationTag() {
        assertTrue(textExists("Location Tag Retired"));
    }

    @And ("the user clicks the restore location tag icon")
    public void userClicksTheRestoreLocationTagIcon() {
        manageLocationTagsPage.restoreLocation();
        assertTrue(textExists("Location Tag restored"));
    }

    @And ("the user clicks on the delete location tag forever icon")
    public void userClicksOnTheDeleteLocationTagForeverIcon() {
        manageLocationTagsPage.deleteLocationTag();
    }

    @Then ("the system deletes the location tag forever")
    public void systemDeletesTheLocationTagForever() {
        assertTrue(textExists("Location Tag Deleted"));
    }

    @When ("a user clicks on manage location link")
    public void userClicksOnManageLocation() {
        manageLocationsPage = configureMetadataPage.goToManageLocations();
    }

    @Then ("the system loads the manage location page")
    public void systemLoadsTheManageLocationPage() {
        assertTrue(textExists("Manage Locations"));
    }

    @And ("the user clicks on add new location button")
    public void userClicksOnAddNewLocationButton() {
        manageLocationsPage.goToAddNewLocation();
        assertTrue(textExists("Add New Location"));
    }

    @And ("the user fills the form")
    public void userFillsAddNewLocationForm() {
        manageLocationsPage.enterLocationName("locationName");
        manageLocationsPage.enterLocationDescription("locationDescription");
        manageLocationsPage.enterAddress1("address");
        manageLocationsPage.enterAddress2("address");
        manageLocationsPage.enterCity("cityName");
        manageLocationsPage.enterState("stateName");
        manageLocationsPage.enterCountry("countryName");
        manageLocationsPage.enterPostalCode("postalCode");
        manageLocationsPage.selectParentLocation("Pharmacy");
        manageLocationsPage.selectLocationTag();
    }

    @Then ("the user saves the location form")
    public void userSavesAddNewLocationForm() {
        manageLocationsPage.saveLocation();
        assertTrue(textExists("Location Saved"));
    }

    @And ("the user clicks on the edit location icon")
    public void userClicksOnTheEditLocationIcon() {
        manageLocationsPage.goToEditLocationForm();
    }

    @And ("the user fills in the prefered details in the edit location form")
    public void userFillsInThePreferedDetailsInTheEditLocationForm() {

    }

    @And ("the user clicks on the retire location button")
    public void userClicksOnTheRetireLocationIcon() {
        manageLocationsPage.retireLocation("locationName");
    }

    @Then ("the system retires the location")
    public void systemRetiresTheLocation() {
        assertTrue(textExists("Location Retired"));
    }

    @And ("the user clicks the restore location icon")
    public void userClicksTheRestoreLocationIcon() {
        manageLocationsPage.restoreLocation();
        assertTrue(textExists("Location restored"));
    }

    @And ("the user clicks on the delete location forever icon")
    public void userClicksOnTheDeleteLocationForeverIcon() {
        manageLocationsPage.purgeLocation("locationName");
    }

    @Then ("the system deletes the location forever")
    public void systemDeletesTheLocationForever() {
        assertTrue(textExists("Location Deleted"));
    }

}
