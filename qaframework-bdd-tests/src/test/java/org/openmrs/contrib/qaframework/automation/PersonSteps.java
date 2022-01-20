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
import static org.junit.Assert.assertTrue;
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
    public void setUp() {
	initiateWithLogin();
	personInfo = TestData.generateRandomPerson();
	personUuid = TestData.createPerson(personInfo);
    }
	
    @After(RunTest.HOOK.SELENIUM_PERSON)
    public void tearDown() {
	RestClient.delete("person/" + personUuid, true);
	quit();
    }
	
    @Given ("a user clicks on the system administration link on the home page")
    public void launchAdministrationPage() {
    	administrationPage = homePage.goToAdministration();
    }
    
    @Then ("the system loads the administration page")
    public void loadAdministrationPage() {
    	assertTrue(textExists("Administration"));
    }
    
    @And ("a user clicks on the manage Persons link on the administration page")
    public void launchManagePersonsPage() {
    	managePersonPage = administrationPage.clickOnManagePersons();
    }
    
    @Then ("the system loads manage person page")
    public void loadManagePersonPage() {
    	assertTrue(textExists("Person"));
    }
    
    @When ("a user clicks on the create person link on the manage persons page")
    public void clickOnCreatePersonLink() {
    	addPersonPage = managePersonPage.createPerson();
    }
    
    @Then ("the system loads the create person page")
    public void loadPersonForm() {
    	assertTrue(textExists("Adding a Person"));
    }

    @And ("a user fills the create person form")
    public void fillPersonForm() {
    	addPersonPage.setPersonName(PERSON_NAME);
    	addPersonPage.setAge(PERSON_AGE);
    	addPersonPage.clickGenderMale();
    	personFormPage = addPersonPage.createPerson();
    	personFormPage.setFamilyNameField(PERSON_FAMILY_NAME);
    }
    
    @Then ("a user saves the person details")
    public void savePersonDetails() {
    	personFormPage.savePerson();
    }
    
    @Then ("a user saves the edited person details")
    public void saveEditedPersonDetails() {
    	personFormPage.savePerson();
    	assertThat(personFormPage.getFamilyName(), is("newFamilyName"));
    }
    
    @When ("a user searches for the person")
    public void searchForPersonToEdit() {
    	managePersonPage.setPersonName(personInfo.givenName);
    }
    
    @And ("a user clicks on the first person found from the search results")
    public void clickOnFirstPerson() {
    	personFormPage = managePersonPage.clickFirstFoundPerson();
    }
    
    @And ("a user edits the person details")
    public void editPersonDetails() {
    	personFormPage.setFamilyNameField("newFamilyName");
    }
    
    @Then ("a user clicks on the delete person forever button")
    public void deletePersonForever() {
    	personFormPage.deletePersonForever();
    }
    
    @And ("a user fills in the reason for retiring person")
    public void setRetireReason() {
    	personFormPage.setRetireReason("retire reason");
    }
    
    @Then ("a user clicks on the retire person button")
    public void retirePerson() {
    	personFormPage.retirePerson();
    }
}
