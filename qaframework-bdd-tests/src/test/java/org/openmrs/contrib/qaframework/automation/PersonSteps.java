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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.commons.lang3.RandomStringUtils;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.RestClient;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.AddPersonPage;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.ManagePersonPage;
import org.openmrs.contrib.qaframework.page.PersonFormPage;

public class PersonSteps extends Steps {
	
    private AdministrationPage administrationPage;
    private ManagePersonPage managePersonPage;
    private PersonFormPage personFormPage;
    private AddPersonPage addPersonPage;
    private String personUuid;
    private TestData.PersonInfo personInfo;
    private final String PERSON_NAME = RandomStringUtils.randomAlphabetic(8);
    private final String PERSON_AGE = RandomStringUtils.randomNumeric(2);
    private final String PERSON_FAMILY_NAME = RandomStringUtils.randomAlphanumeric(8);
	
    @Before(RunTest.HOOK.SELENIUM_PERSON)
    public void setup() {
	initiateWithLogin();
	personInfo = TestData.generateRandomPerson();
	personUuid = TestData.createPerson(personInfo);
    }
	
    @After(RunTest.HOOK.SELENIUM_PERSON)
    public void teardown() {
	RestClient.delete("person/" + personUuid, true);
	quit();
    }
	
    @Given ("User clicks the system administration link on the home page")
    public void launchSystemAdministrationPage() {
    	administrationPage = homePage.goToAdministration();
    }
    
    @When ("User clicks the manage Persons link on the administration page")
    public void launchAdvancedAdministrationPage() {
    	managePersonPage = administrationPage.clickOnManagePersons();
    }
    
    @And ("User clicks the create person link on the manage persons page")
    public void clickOnCreatePersonPage() {
    	addPersonPage = managePersonPage.createPerson();
    	addPersonPage.createPerson();
    }

    @Then ("User fills the create person form")
    public void createPersonForm() {
    	addPersonPage.setPersonName(PERSON_NAME);
    	addPersonPage.setAge(PERSON_AGE);
    	addPersonPage.clickGenderMale();
    	personFormPage = addPersonPage.createPerson();
    	personFormPage.setFamilyNameField(PERSON_FAMILY_NAME);
    	personFormPage.savePerson();
    	personFormPage.deletePersonForever();
    }
    
    @And ("User searches for the person")
    public void searchForPersonToEdit() {
    	managePersonPage.setPersonName(personInfo.givenName);
    }
    
    @And ("User clicks the first person found from the search results")
    public void clickFirstPersonFound() {
    	personFormPage = managePersonPage.clickFirstFoundPerson();
    }
    
    @And ("User edits the person details")
    public void editPersonDetails() {
    	personFormPage.setFamilyNameField("newFamilyName");
    	personFormPage.savePerson();
    	assertThat(personFormPage.getFamilyName(), is("newFamilyName"));
    }
    
    @Then ("User clicks delete person forever button")
    public void deletePersonForever() {
    	personFormPage.deletePersonForever();
    }
    
    @And ("User fills in the reason for retiring person")
    public void setRetireReason() {
    	personFormPage.setRetireReason("retire reason");
    }
    
    @Then ("User clicks retire person button")
    public void retirePerson() {
    	personFormPage.retirePerson();
    }
}

