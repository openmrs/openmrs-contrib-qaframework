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

import org.apache.commons.lang3.RandomStringUtils;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.AdministrationAddEditLocationPage;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.ConfigureMetadataPage;
import org.openmrs.contrib.qaframework.page.ManageLocationAttributeTypesPage;
import org.openmrs.contrib.qaframework.page.ManageLocationTagsPage;
import org.openmrs.contrib.qaframework.page.ManageLocationsOnAdminPage;
import org.openmrs.contrib.qaframework.page.SystemAdministrationPage;

public class LocationManagementSteps extends Steps {

    private ConfigureMetadataPage configureMetadataPage;
    private AdministrationAddEditLocationPage administrationAddEditLocationPage;
    private ManageLocationsOnAdminPage manageLocationsOnAdminPage;
    private ManageLocationTagsPage manageLocationTagsPage;
    private ManageLocationAttributeTypesPage manageLocationAttributeTypesPage;
    private SystemAdministrationPage systemAdministrationPage;
    private AdministrationPage administrationPage;
    private static final String LOCATION_ATTRIBUTE_TYPE_NAME = RandomStringUtils.randomAlphabetic(5);
    private static final String LOCATION_ATTRIBUTE_TYPE_DESCRIPTION = RandomStringUtils.randomAlphabetic(8);
    private static final String MINIMUM_OCCURS = RandomStringUtils.randomNumeric(2);
    private static final String MAXIMUM_OCCURS = RandomStringUtils.randomNumeric(5);
    private static final String DATATYPE_CONFIGURATION = RandomStringUtils.randomAlphabetic(8);
    private static final String LOCATION_HANDLER_CONFIGURATION = RandomStringUtils.randomAlphabetic(8);
    private static final String LOCATION_NAME = RandomStringUtils.randomAlphabetic(5);
    private static final String LOCATION_TAG = RandomStringUtils.randomAlphabetic(5);
    private static final String LOCATION_DESCRIPTION = RandomStringUtils.randomAlphabetic(8);

    @Before(RunTest.HOOK.SELENIUM_LOCATION_MANAGEMENT)
    public void visitDashboard() {
        initiateWithLogin();
    }

    @After(RunTest.HOOK.SELENIUM_LOCATION_MANAGEMENT)
    public void destroy() {
        quit();
    }

    @Given("a user clicks on the configure metadata link from the home page")
    public void userClicksOnConfigureMetadataLink() {
        configureMetadataPage = homePage.goToConfigureMetadata();
    }

    @Then("the system loads configure metadata link dashboard")
    public void systemLoadsMetadataDashboard() {
        assertTrue(textExists("Configure Metadata"));
    }

    @When("a user clicks on the Manage Location Attribute Types")
    public void userClicksOnManageLocationAttributeTypes() {
        manageLocationAttributeTypesPage = configureMetadataPage.goToManageLocationAttributeTypesPage();
    }

    @Then("the system loads the manage location attribute type page")
    public void systemLoadsTheManageLocationAttributeTypePage() {
        assertTrue(textExists("Manage Location Attribute Types"));
    }

    @And("a user clicks on the add new location attribute type")
    public void userClicksOnAddNewLocationAttributeType() {
        manageLocationAttributeTypesPage.goToAddNewLocationAttributeTypeForm();
    }

    @And("a user fills the add new location attribute type form")
    public void userFillsAddNewLocationAttributeTypeForm() {
        manageLocationAttributeTypesPage.enterLocationAttributeTypeName(LOCATION_ATTRIBUTE_TYPE_NAME);
        manageLocationAttributeTypesPage.enterLocationAttributeTypeDescription(LOCATION_ATTRIBUTE_TYPE_DESCRIPTION);
        manageLocationAttributeTypesPage.enterMinimumOccurs(MINIMUM_OCCURS);
        manageLocationAttributeTypesPage.enterMaximumOccurs(MAXIMUM_OCCURS);
        manageLocationAttributeTypesPage.selectDatatype("Location Datatype");
        manageLocationAttributeTypesPage.enterDatatypeConfiguration(DATATYPE_CONFIGURATION);
        manageLocationAttributeTypesPage.selectPreferredHandler("Location Field Gen Datatype Handler");
        manageLocationAttributeTypesPage.enterHandlerConfiguration(LOCATION_HANDLER_CONFIGURATION);
    }

    @Then("the user saves the form")
    public void userSavesForm() {
        manageLocationAttributeTypesPage.clickOnSaveButton();
        assertTrue(textExists("Manage Location Attribute Types"));
    }

    @And("the user clicks on the edit icon")
    public void userClicksOnEditIcon() {
        manageLocationAttributeTypesPage.goToEditLocationAttributeTypeForm();
        assertTrue(textExists("Edit Location Attribute Type"));
    }

    @And("the user fills in the prefered details in the edit location attribute type form")
    public void userFillsInPreferedDetails() {
        manageLocationAttributeTypesPage.enterLocationAttributeTypeDescription("This location attribute type is being edited");
    }

    @Then("the user saves the edit location attribute type form")
    public void saveEditLocationAttributeTypeForm() {
        manageLocationAttributeTypesPage.clickOnSaveButton();
        assertTrue(textExists("Manage Location Attribute Types"));
    }

    @And("the user clicks on the retire icon")
    public void userClicksOnRetireIcon() {
        manageLocationAttributeTypesPage.retireLocation();
    }

    @Then("the system retires the location attribute type")
    public void systemRetiresLocationAttributeType() {
        assertTrue(textExists("Location Attribute Type Retired"));
    }

    @And("the user clicks on the restore location attribute type icon")
    public void userClicksRestoreLocationAttributeTypeIcon() {
        manageLocationAttributeTypesPage.restoreLocationAttributeType();
        assertTrue(textExists("Location Attribute Type restored"));
    }

    @And("the user clicks on the delete forever icon")
    public void userClicksOnDeleteForeverIcon() {
        manageLocationAttributeTypesPage.purgeLocation();
    }

    @Then("the system deletes the location attribute type forever")
    public void systemDeletesLocationAttributeTypeForever() {
        assertTrue(textExists("Location Attribute Type Deleted"));
    }

    @When("a user clicks on the manage location tag")
    public void userClicksOnManageLocationTag() {
        manageLocationTagsPage = configureMetadataPage.goToManageLocationTagPage();
    }

    @Then("the system loads the manage location tag")
    public void systemLoadsManageLocationTagPage() {
        assertTrue(textExists("Manage Location Tags"));
    }

    @And("the user clicks on the add new location tag")
    public void userClicksOnAddNewLocationTag() {
        manageLocationTagsPage.goToAddNewLocationTagForm();
        assertTrue(textExists("Add New Location Tag"));
    }

    @And("a user fills add new location tag form")
    public void userFillsAddNewLocationTagForm() {
        manageLocationTagsPage.enterLocationTagName(LOCATION_TAG);
        manageLocationTagsPage.enterLocationTagDescription("locationTagDescription");
    }

    @Then("a user saves add New location tag form")
    public void userSavesAddNewLocationTagForm() {
        manageLocationTagsPage.saveLocationTag();
        assertTrue(textExists("Location Tag Saved"));
    }

    @And("the user clicks on the edit location tag icon")
    public void userClicksOnEditLocationTagIcon() {
        manageLocationTagsPage.goToEditLocationTagForm();
    }

    @And("the user fills in the prefered details in the edit location tag form")
    public void userFillsPreferedDetailsInEditLocationTagForm() {
        manageLocationTagsPage.enterLocationTagName("editedlocationTagName");
        manageLocationTagsPage.enterLocationTagDescription("editedlocationTagDescription");
    }

    @And("the user clicks on the retire location tag button")
    public void userClicksOnRetireLocationTagButton() {
        manageLocationTagsPage.retireLocation();
    }

    @Then("the system retires the location tag")
    public void systemRetiresLocationTag() {
        assertTrue(textExists("Location Tag Retired"));
    }

    @And("the user clicks on the restore location tag icon")
    public void userClicksRestoreLocationTagIcon() {
        manageLocationTagsPage.restoreLocation();
        assertTrue(textExists("Location Tag restored"));
    }

    @And("the user clicks on the delete location tag forever icon")
    public void userClicksOnDeleteLocationTagForeverIcon() {
        manageLocationTagsPage.deleteLocationTag();
    }

    @Then("the system deletes the location tag forever")
    public void systemDeletesLocationTagForever() {
        assertTrue(textExists("Location Tag Deleted"));
    }

    @Given("a user clicks on the System Administration Link from home page")
    public void userClicksOnSystemAdminLink() {
        systemAdministrationPage = homePage.goToSystemAdministrationPage();
    }

    @When("User clicks on the Advanced Administration link from the System Administration Page")
    public void userClicksOnAdvancedAdminLink() {
        administrationPage = systemAdministrationPage.goToAdvancedAdministration();
    }

    @When("a user clicks on the manage location link")
    public void userClicksOnManageLocation() {
        manageLocationsOnAdminPage = administrationPage.clickOnManageLocations();
    }

    @Then("the system loads the manage location page")
    public void systemLoadsManageLocationPage() {
        assertTrue(textExists("Location Management"));
    }

    @And("the user clicks on the add new location button")
    public void userClicksOnAddNewLocationButton() {
        administrationAddEditLocationPage = manageLocationsOnAdminPage.clickOnAddLocationLink();
    }

    @And("the user fills the form")
    public void userFillsAddNewLocationForm() {
        administrationAddEditLocationPage.enterName(LOCATION_NAME);
        administrationAddEditLocationPage.enterDescription(LOCATION_DESCRIPTION);
        administrationAddEditLocationPage.enterAddressOne("Kampala");
        administrationAddEditLocationPage.enterAddressTwo("Nyamitanga");
        administrationAddEditLocationPage.enterStateOrProvince("Central");
        administrationAddEditLocationPage.enterCountry("Uganda");
        administrationAddEditLocationPage.enterPostalCode("00000");
    }

    @Then("the user saves the location form")
    public void userSavesAddNewLocationForm() {
        administrationAddEditLocationPage.saveLocation();
    }

    @And("the user clicks on the edit location icon")
    public void userClicksOnEditLocationIcon() {
        manageLocationsOnAdminPage.clickOnEditLocationLink(LOCATION_NAME);
    }

    @And("the user fills in the prefered details in the edit location form")
    public void userFillsInThePreferedDetailsInTheEditLocationForm() {
        administrationAddEditLocationPage.enterAddressTwo("Mbale");
        administrationAddEditLocationPage.enterStateOrProvince("Eastern");
        administrationAddEditLocationPage.saveLocation();
    }

    @And("the user clicks on the retire location button")
    public void userClicksOnRetireLocationIcon() {
        manageLocationsOnAdminPage.clickOnEditLocationLink(LOCATION_NAME);
        administrationAddEditLocationPage.retireLocation("For testing purposes");
    }

    @Then("the system retires the location")
    public void systemRetiresLocation() {
        assertTrue(textExists("Location retired successfully"));
    }

    @And("the user restores the retired location")
    public void userRestoresLocation() {
        manageLocationsOnAdminPage.toggleRetired();
        manageLocationsOnAdminPage.clickOnEditLocationLink(LOCATION_NAME);
        administrationAddEditLocationPage.unRetireLocation();
    }

    @Then("the system deletes the location forever")
    public void systemDeletesLocationForever() {
        manageLocationsOnAdminPage.clickOnEditLocationLink(LOCATION_NAME);
        administrationAddEditLocationPage.deleteLocationForever();
    }

}
