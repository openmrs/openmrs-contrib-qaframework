/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * <p>
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.page;
 
import java.util.List;
 
import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
 
public class OpenConceptLabPage extends Page {
 
    //private static final By SUBSCRIPTION_URL_FIELD = By.id("subscription-url");
    private static final By SETUP_SUBSCRIPTION_BUTTON = By.cssSelector("#body-wrapper > home > div:nth-child(2) > div:nth-child(2) > a");
    private static final By RELEASE_DICTIONARY_URL_FIELD = By.cssSelector("#subscription-url");
    private static final By USER_URL_FIELD = By.id("subscription-token");
    private static final By SAVE_BUTTON = By.className("saveSubscription");
    private static final By CANCEL_BUTTON = By.cssSelector("#body-wrapper > subscription > div:nth-child(1) > form > fieldset > div:nth-child(4) > button:nth-child(2)");
    private static final By EDIT_SUBSCRIPTION_BUTTON = By.cssSelector("#body-wrapper > home > div:nth-child(3) > div:nth-child(1) > a");
    private static final By IMPORT_FROM_SUBSCRIPTION_BUTTON = By.cssSelector("#body-wrapper > home > div:nth-child(3) > div:nth-child(2) > fieldset > div:nth-child(2) > div > p:nth-child(1) > button");
    private static final By CHOOSE_FILE = By.cssSelector("#body-wrapper > home > div:nth-child(3) > div:nth-child(2) > fieldset > div:nth-child(2) > form > p:nth-child(2) > input");
    private static final By IMPORT_FROM_FILE = By.cssSelector("#body-wrapper > home > div:nth-child(3) > div:nth-child(2) > fieldset > div:nth-child(2) > form > p:nth-child(3) > button");
    private static final By IMPORTS_LIST = By.cssSelector("#body-wrapper > home > div:nth-child(3) > div:nth-child(5) > fieldset > table");
    private static final By ENTER_SUBSCRIPTION_TOKEN = By.id("subscription-token");
    
    public OpenConceptLabPage(Page page) {
        super(page);
    }
 
    @Override
    public String getPageUrl() {
        return "/owa/openconceptlab/index.html#/";
    }
 
    public SubscriptionPage clickOnsetupSubscriptionButton() {
        clickOn(SETUP_SUBSCRIPTION_BUTTON);
        return new SubscriptionPage(this); 
    }
 
    public void enterSubscriptionURL(String conceptUrl) {
         findElement(RELEASE_DICTIONARY_URL_FIELD).click();
         findElement(RELEASE_DICTIONARY_URL_FIELD ).clear();
         findElement(RELEASE_DICTIONARY_URL_FIELD ).sendKeys("https://qa-refapp.openmrs.org/openmrs/dictionary/concept.htm?conceptId=119204");
    }
 
    public void enterTokenURL(String tokenUrl) {
        clickOn(ENTER_SUBSCRIPTION_TOKEN);
        findElement(USER_URL_FIELD).clear(); 
        findElement(USER_URL_FIELD).sendKeys(tokenUrl);
    }
 
    public void clickSaveButton() {
        clickOn(SAVE_BUTTON);
    }
 
    public void clickCancelButton() {
        clickOn(CANCEL_BUTTON);
    }
 
    public void goToEditsubscriptionForm() {
        clickOn(EDIT_SUBSCRIPTION_BUTTON);
    }
 
    public void clickOnImportFromSubscriptionButton() {
        clickOn(IMPORT_FROM_SUBSCRIPTION_BUTTON);
    }
 
     public void clickOnChooseFileTextarea() {
        clickOn(CHOOSE_FILE);
    }
 
    public void clickOnImportFromFileButton() {
        clickOn(IMPORT_FROM_FILE);
    }
 
	public List<WebElement> getpreviousImportsList() {
		return findElements(IMPORTS_LIST);   
	}
}
