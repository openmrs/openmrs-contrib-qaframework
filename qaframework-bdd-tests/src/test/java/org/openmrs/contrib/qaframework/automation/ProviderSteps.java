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
import org.openmrs.contrib.qaframework.helper.RestClient;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.ManageProviderPage;
import org.openmrs.contrib.qaframework.page.ProviderPage;

public class ProviderSteps extends Steps {
	
    private TestData.PersonInfo person;
    private String personUuid;
    private String providerUuid;
    private TestData.TestProvider provider;
    private AdministrationPage administrationPage;
    private ManageProviderPage manageProviderPage;
    private ProviderPage providerPage;
	
    @Before(RunTest.HOOK.SELENIUM_PROVIDER)
    public void setUp() {
    	initiateWithLogin();
    	person = TestData.generateRandomPerson();
    	personUuid = TestData.createPerson(this.person);
    	TestData.createPerson(this.person);
    	provider = new TestData.TestProvider(person.uuid, person.uuid);
    	providerUuid = provider.create();
        providerUuid = new TestData.TestProvider(personUuid, personUuid).create();
    }
    
    @After(RunTest.HOOK.SELENIUM_PROVIDER)
    public void tearDown() {
        RestClient.delete("provider/" + providerUuid, true);
        RestClient.delete("person/" + personUuid, true);
	quit();
    }
    
    @Given("a user clicks on the administration app")
    public void launchSystemAdministrationPage() {
    	administrationPage = homePage.goToAdministration(); 	
    }
    
    @Then ("system loads administration page")
    public void systemLoadsAdministrationPage() {
    	assertPage(administrationPage.waitForPage());
    }
    
    @And ("a user clicks on the manage provider link")
    public void launchAdvancedAdministrationPage() {
    	manageProviderPage = administrationPage.clickOnManageProviders();
    }
    
    @Then ("system loads manage provider page")
    public void systemLoadsManageProviderPage() {
    	assertTrue(textExists("Manage Providers"));
    }
    
    @When ("a user clicks on the add provider link")
    public void clickOnAddProviderLink() {
    	providerPage = manageProviderPage.clickOnAddProvider();
    }
    
    @Then ("system loads add provider page")
    public void systemLoadsAddProviderPage() {
    	assertPage(providerPage.waitForPage());
    }
    
    @And ("a user fills the provider form")
    public void fillProviderForm() {
        providerPage.setIdentifier(personUuid);
        providerPage.setPerson(person.getName());
        manageProviderPage = providerPage.clickOnSave();
    }
    
    @When ("a user searches for the created provider")
    public void searchForProvider() {
    	manageProviderPage.setProviderNameOrId(person.getName());
    }
    
    @And ("a user clicks on the provider in the search results")
    public void clickOnProvider() {
    	providerPage = manageProviderPage.clickOnProvider(person.getName());
    }
	
    @Then ("a user deletes provider forever")
    public void deleteProviderForever() {
	providerPage.deleteForever();
    }
	
    @And ("a user edits provider details")
    public void editProviderDetails() {
        providerPage.setIdentifier("uitest-" + person.uuid);
    }
    
    @Then ("a user clicks the save button")
    public void savedEditedProvider() {
    	providerPage.clickOnSave();
    }
	
    @And ("a user sets the reason for retiring a provider")
    public void retireProvider() {
        providerPage.setRetireReason("retire reason");
    }
    
    @Then ("a user retires the provider")
    public void clickOnRetireButton() {
    	providerPage.clickOnRetire();
    }	
}
