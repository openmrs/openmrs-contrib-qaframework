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

import org.openmrs.contrib.qaframework.RunTest;

public class LocationManagementSteps extends Steps {
    
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
        
    }

    @Then ("the system loads configure metadata link dashboard")
    public void systemLoadsMetadataDashboard() {
        
    }

    @When ("a user clicks on Manage Location Attribute Types")
    public void userClicksOnManageLocationAttributeTypes() {
        
    }

    @Then ("the system loads the manage location attribute type page")
    public void theSystemLoadsTheManageLocationAttributeTypePage() {

    }
    
    @And ("a user clicks on add new location attribute type")
    public void userClicksOnAddNewLocationAttributeType() {
        
    }

    @And ("a user fills the add new location attribute type form")
    public void userFillsTheAddNewLocationAttributeTypeForm() {

    }

    @Then ("the user saves the form")
    public void userSavesTheForm() {

    }

    @And ("the user clicks on the edit icon")
    public void userClicksOnTheEditIcon() {

    }

    @And ("the user fills in the prefered details in the edit location attribute type form")
    public void userFillsInPreferedDetails() {

    }

    @And ("the user clicks on the retire icon")
    public void userClicksOnTheRetireIcon() {

    }

    @And ("the user clicks the confirm button")
    public void userClicksTheConfirmButton() {

    }

    @Then ("the system retires the location attribute type")
    public void systemRetiresTheLocationAttributeType() {

    }

    @And ("the user clicks the restore location attribute type icon")
    public void userClicksRestoreLocationAttributeTypeIcon () {

    }

    @And ("the user clicks on the delete forever icon")
    public void userClicksOnTheDeleteForeverIcon() {

    }

    @Then ("the system deletes the location attribute type forever")
    public void systemDeletesLocationAttributeTypeForever() {

    }

    @When ("a user clicks on manage location tag")
    public void userClicksOnManageLocationTag() {

    }

    @Then ("the system loads the manage location tag")
    public void systemLoadsTheManageLocationTag() {

    }

    @And ("the user clicks on add new location tag")
    public void userClicksOnAddNewLocationTag() {

    }

    @And ("a user fills add new location tag form")
    public void userFillsAddNewLocationTagForm() {

    }

    @Then ("a user saves add New location tag form")
    public void userSavesAddNewLocationTagForm() {

    }

    @And ("the user clicks on the edit location tag icon")
    public void userClicksOnTheEditLocationTagIcon() {

    }

    @And ("the user fills in the prefered details in the edit location tag form")
    public void userFillsInThePreferedDetailsInTheEditLocationTagForm() {

    }

    @And ("the user clicks save")
    public void userClicksSave() {

    }

    @And ("the user clicks on the retire location tag button")
    public void userClicksOnTheRetireLocationTagButton() {

    }

    @Then ("the system retires the location tag")
    public void systemRetireTheLocationTag() {

    }

    @And ("the user clicks the restore location tag icon")
    public void userClicksTheRestoreLocationTagIcon() {

    }

    @And ("the user clicks on the delete location tag forever icon")
    public void userClicksOnTheDeleteLocationTagForeverIcon() {

    }

    @Then ("the system deletes the location tag forever")
    public void systemDeletesTheLocationTagForever() {

    }

    @When ("a user clicks on manage location link")
    public void userClicksOnManageLocation() {

    }

    @Then ("the system loads the manage location page")
    public void systemLoadsTheManageLocationPage() {

    }

    @And ("the user clicks on add new location button")
    public void userClicksOnAddNewLocationButton() {

    }

    @And ("the user fills the form")
    public void userFillsAddNewLocationForm() {

    }

    @Then ("the user saves the location form")
    public void userSavesAddNewLocationForm() {

    }

    @And ("the user clicks on the edit location icon")
    public void userClicksOnTheEditLocationIcon() {

    }

    @And ("the user fills in the prefered details in the edit location form")
    public void userFillsInThePreferedDetailsInTheEditLocationForm() {

    }

    @And ("the user clicks on the retire location button")
    public void userClicksOnTheRetireLocationIcon() {

    }

}
