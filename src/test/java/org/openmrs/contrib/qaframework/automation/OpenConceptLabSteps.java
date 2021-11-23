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
 
import static org.junit.Assert.assertNotNull;
 
import java.util.List;
 
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.ConfigureMetadataPage;
import org.openmrs.contrib.qaframework.page.OpenConceptLabPage;
import org.openmrs.contrib.qaframework.page.OpenConceptLabSuccessPage;
import org.openmrs.contrib.qaframework.page.SubscriptionPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
 
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class OpenConceptLabSteps extends Steps  {
    private By subscriptionUrl = By.id("subscription-url");
    private OpenConceptLabPage openconceptlabpage;
    private ConfigureMetadataPage configuremetadatapage;
    private static final String releasedDictionaryUrl = "https://api.staging.openconceptlab.org/users/openmrs/collections/TD-ecly/65725685/";
    private static final String tokenUrl = "bd022mad6d3df24f5c42ewewa94b53a23edf6eee7r";
    private static final String newDictionaryUrl = "http://api.openconceptlab.com/orgs/CIEL/sources/CIEL/";
    private static final String newTokenUrl = "bd022mad6d3df24f5c42ewewa94b53a23edf6fff7r";
    private OpenConceptLabSuccessPage openConceptLabSuccessPage;
    private OpenConceptLabPage openConceptLabPage;
    private SubscriptionPage subscriptionPage;
    
    @Before(RunTest.HOOK.SELENIUM_OPEN_CONCEPT_LAB)
    public void visitHomePage() {
        initiateWithLogin();    
    }
    
    @After(RunTest.HOOK.SELENIUM_OPEN_CONCEPT_LAB)
    public void destroy(){
        quit();
    }
    
    @Given("a user clicks on configure Metadata link from home page")
    public void launchMetadataDashboard() {
        configuremetadatapage = homePage.goToConfigureMetadata();
    }
 
    @When("a user clicks Manage OCL link")
    public void manageOclPage() {
        openconceptlabpage = configuremetadatapage.goToOpenConceptLabPage();
    }
 
    @Then("System loads Open Concept Lab page")
    public void systemLoadsOpenConceptPage() {
        assertPage(openconceptlabpage.waitForPage());
    }
 
    @When ("a user clicks on Setup subscription button")
    public void loadSubscriptionPage() {
    	subscriptionPage = (SubscriptionPage) openconceptlabpage.clickOnsetupSubscription();
    }
    
    @And("a user enters the URL of a new released dictionary")
    public void enterDictionaryUrl() {
		openconceptlabpage.enterSubscriptionURL(releasedDictionaryUrl);
    }   
    
    @And("a user enters the Token url")
    public void enterTokenUrl(){
        openconceptlabpage.enterTokenURL(tokenUrl);
    }
    
    @And ("a user clicks on the Save Changes button")
    public void saveChanges(){
    	openConceptLabSuccessPage = (OpenConceptLabSuccessPage) subscriptionPage.clickSaveChangesButton();
    }
    
    @And ("the system loads Open Concept Lab Success page")
    public void systemLoadsOpenConceptLabSuccessPage(){
    	assertPage(openConceptLabSuccessPage.waitForPage());  
    }
    
    @And ("a user clicks import from Subscription server button")
    public void LoadsOpenConceptLabPage(){
    	assertPage(openConceptLabSuccessPage.waitForPage());  
    }
    
    @And ("the API should be displayed on the previous imports")
    public void displayAmongPreviousImports(){
        assertNotNull(openConceptLabSuccessPage.getpreviousImportsList());
    }
    
//    @When ("a user clicks edit subscription button")
//    public void loadsOpenConceptLabPage(){
//    	assertPage(openConceptLabPage.waitForPage());  
//    }
//    
//    @And("a user edits the URL of a released dictionary")
//    public void editDictionaryUrl() {
//		openconceptlabpage.enterSubscriptionURL(newDictionaryUrl);
//    }   
//    
//    @And("a user edits the Token url")
//    public void editTokenUrl(){
//        openconceptlabpage.enterTokenURL(newTokenUrl);
//    }
}

