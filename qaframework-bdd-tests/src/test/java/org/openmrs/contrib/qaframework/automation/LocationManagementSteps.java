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

import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openmrs.contrib.qaframework.RunTest;

import org.openmrs.contrib.qaframework.page.ManageLocationAttributeTypesPage;
import org.openmrs.contrib.qaframework.page.LocationPage;
import org.openmrs.contrib.qaframework.page.ManageLocationsPage;
import org.openmrs.contrib.qaframework.page.AddEditLocationPage;
import org.openmrs.contrib.qaframework.page.ManageLocationTagsPage;
import org.openmrs.contrib.qaframework.page.ConfigureMetadataPage;

public class LocationManagementSteps extends Steps {

    private static final String TAG_NAME = "This is a tag name";
    private static final String MAX_VALUE = "20";
    private static final String MIN_VALUE = "1";
    private static final String DESCRIPTION = "This is the description";
    private static final String NAME = "Noah";
    private static final String LOCATION_NAME = "Bugema";
    private static final String ADDRESS_ONE = "Bugema one";
    private static final String ADDRESS_TWO = "Bugema two";
    private static final String CITY = "Kampala";
    private static final String STATE = "Central";
    private static final String COUNTRY = "Uganda";
    
    private ConfigureMetadataPage configureMetadataPage;
    private LocationPage locationPage;
    private ManageLocationsPage manageLocationsPage;
    private AddEditLocationPage addEditLocationPage;
    private ManageLocationTagsPage manageLocationTagsPage;
    private ManageLocationAttributeTypesPage manageLocationAttributeTypesPage;

    @Before(RunTest.HOOK.SELENIUM_LOCATION_MANAGEMENT)
    public void visitHomePage() {
        initiateWithLogin();
    }

    @After(RunTest.HOOK.SELENIUM_LOCATION_MANAGEMENT)
    public void destroy() {
        quit();
    }

    @Given("a user clicks on the configure metadata link from the home page")
    public void clickOnConfigureMetaDataLink(){
        configureMetadataPage = homePage.goToConfigureMetadata();
    }

    @When("the system loads configure metadata link dashboard")
    public void loadsConfigureMetaData(){
        assertTrue(textExists("Configure Metadata"));
    }

    @And("a user clicks on Manage Location Attribute Types")
    public void clickOnManageLocationAttributesTypes() {
        manageLocationAttributeTypesPage = configureMetadataPage.goToManageLocationAttributeTypesPage();
    }

    @And("a user clicks on add new location attribute type")
    public void addNewLocationAttributeType() {
        manageLocationAttributeTypesPage.goToAddNewLocationAttributeTypeForm();
    }

    @And("a user fills the form")
    public void fillsTheForm() {
        manageLocationAttributeTypesPage.enterLocationAttributeTypeName(NAME);
        manageLocationAttributeTypesPage.enterLocationAttributeTypeDescription(DESCRIPTION);
        manageLocationAttributeTypesPage.enterMinimumOccurs(MIN_VALUE);
        manageLocationAttributeTypesPage.enterMaximumOccurs(MAX_VALUE);
        manageLocationAttributeTypesPage.selectDatatype("Provider Datatype");
        manageLocationAttributeTypesPage.enterDatatypeConfiguration("I have entered the data configuration");
        manageLocationAttributeTypesPage.selectPreferredHandler("Provider Field Gen Datatype Handler");
        manageLocationAttributeTypesPage.enterHandlerConfiguration("I have entered the handler configuration");
    }

    @Then("the user saves the form")
    public void userSavesForm() {
        manageLocationAttributeTypesPage.clickOnSaveButton();
        manageLocationAttributeTypesPage.returnToConfigureMetaDataDashboard();
    }

    @Then("the form is saved")
    public void theFormIsSaved() {
        assertTrue(textExists("Noah"));
        manageLocationAttributeTypesPage.returnToConfigureMetaDataDashboard();
    }

    @When("a user clicks on manage location tag")
    public void clicksOnManageLocationTag() {
        manageLocationTagsPage = configureMetadataPage.goToManageLocationTagPage();
    }

    @Then("the user clicks on add new location")
    public void clicksAddNewLocation(){
        manageLocationTagsPage.goToAddNewLocationTagForm();
    }

    @And("a user fills add new location tag form")
    public void fillsAddNewLocationTagForm() {
        manageLocationTagsPage.enterLocationTagName(TAG_NAME);
        manageLocationTagsPage.enterLocationTagDescription("This is the location tag description");
    }

    @And("a user saves add New location tag form")
    public void savesAddNewLocationTagForm(){
        manageLocationTagsPage.saveLocationTag();
    }

    @Then("the New location tag form is saved")
    public void locationTagFormIsSaved() {
        assertTrue(textExists("This is a tag name"));
    }

    @And("the the user clicks on the retire location button")
    public void clickOnRetireLocation() {
        manageLocationTagsPage.retireLocation();
    }

    @And("the user cancels retire location")
    public void cancelRetireLocation() {
        manageLocationTagsPage.restoreLocation();
    }

    @Then("a user deletes location tag")
    public void deleteLocationTag() {
        manageLocationTagsPage.deleteLocationTag();
        manageLocationTagsPage.returnToConfigureMetaDataHomeDashboard();
    }

    @When("a user clicks on manager locations")
    public void clicksManagerLocations(){
        manageLocationsPage = configureMetadataPage.goToManageLocations();
    }

    @Then("the user click on add new location")
    public void clickAddNewLocation(){
        manageLocationsPage.goToAddLocation();
    }

    @And("a user fills add new location form")
    public void fillAddNewLocationForm() {
        manageLocationsPage.enterLocationName(LOCATION_NAME);
        manageLocationsPage.enterLocationDescription("This is the location description");
        manageLocationsPage.enterAddress1(ADDRESS_ONE);
        manageLocationsPage.enterAddress2(ADDRESS_TWO);
        manageLocationsPage.enterCity(CITY);
        manageLocationsPage.enterState(STATE);
        manageLocationsPage.enterCountry(COUNTRY);
        manageLocationsPage.enterPostalCode("5678");
        manageLocationsPage.selectLocationTag();
    }

    @Then("the add new location form is saved")
    public void saveNewLocationForm(){
        manageLocationsPage.saveLocation();
    }
}