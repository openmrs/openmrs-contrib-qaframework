/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SubscriptionPage extends Page {
   
    private static final String PAGE_URL = "/owa/openconceptlab/index.html#/subscription";
    private static final By RELEASE_DICTIONARY_URL_FIELD = By.id("subscription-url");
    private static final By TOKEN_URL_FIELD = By.id("subscription-token");
    private static final By SAVE_CHANGES_BUTTON = By.cssSelector("button[ng-click='vm.subscribe()']");
    private static final By UNSUBSCRIBE_BUTTON = By.cssSelector("button[ng-click='vm.unSubscribe()']");
    
    public SubscriptionPage(Page page) {
        super(page);
    }
    
    @Override
    public String getPageUrl() {
        return PAGE_URL;
    }
    
    public void enterSubscriptionURL(String conceptUrl) {
        findElement(RELEASE_DICTIONARY_URL_FIELD ).clear();
        findElement(RELEASE_DICTIONARY_URL_FIELD ).sendKeys(conceptUrl);
    }
    
    public void enterToken(String tokenUrl) {
        findElement(TOKEN_URL_FIELD).clear(); 
        findElement(TOKEN_URL_FIELD).sendKeys(tokenUrl);
    }
    
    public OpenConceptLabSuccessPage clickSaveChangesButton() {
        clickOn(SAVE_CHANGES_BUTTON);
        return new OpenConceptLabSuccessPage(this);
    }
    
    public void clickOnUnSubscribeButton() {
    	waiter.until(ExpectedConditions.elementToBeClickable(UNSUBSCRIBE_BUTTON));
        clickOn(UNSUBSCRIBE_BUTTON);
    }
}
