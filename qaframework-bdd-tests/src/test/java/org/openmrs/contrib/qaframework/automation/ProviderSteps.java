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
    
    @Given("User clicks the system administration link on the home page")
    public void launchSystemAdministrationPage() {
    	administrationPage = homePage.goToAdministration();
    }
    
    @When ("User clicks the manage Provider link on the administration page")
    public void launchAdvancedAdministrationPage() {
    	manageProviderPage = administrationPage.clickOnManageProviders();
    }
    
    @And ("User clicks on add provider link")
    public void clickOnAddProviderLink() {
    	providerPage = manageProviderPage.clickOnAddProvider();
    }
    
    @And ("User fills the provider form")
    public void fillProviderForm() {
        providerPage.setIdentifier(personUuid);
        providerPage.setPerson(person.getName());
        manageProviderPage = providerPage.clickOnSave();
    }
    
    @And ("User searches for the created provider")
    public void searchForProvider() {
    	manageProviderPage.setProviderNameOrId(person.getName());
    	providerPage = manageProviderPage.clickOnProvider(person.getName());
    }
	
    @Then ("User deletes provider forever")
    public void deleteProviderForever() {
	providerPage.deleteForever();
    }
	
    @Then ("User edits provider details")
    public void editProviderDetails() {
        providerPage.setIdentifier("uitest-" + person.uuid);
        providerPage.clickOnSave();
    }
	
    @Then ("User retires a provider")
    public void retireProvider() {
        providerPage.setRetireReason("retire reason");
        providerPage.clickOnRetire();
    }
	
    @Then ("User deletes  a provider")
    public void deleteProvider() {
	providerPage.deleteForever();
    }	
}
