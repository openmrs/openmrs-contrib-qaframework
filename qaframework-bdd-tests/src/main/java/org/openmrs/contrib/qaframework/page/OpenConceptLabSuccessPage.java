/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
  * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.page;

import java.util.List;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OpenConceptLabSuccessPage extends Page {

    private static final String PAGE_URL = "/owa/openconceptlab/index.html#/";
    private static final By EDIT_SUBSCRIPTION_BUTTON = By.linkText("Edit subscription");
    private static final By IMPORT_FROM_SUBSCRIPTION_BUTTON = By.linkText("Import from subscription server");
    private static final By CHOOSE_FILE = By.linkText("Choose file");
    private static final By IMPORT_FROM_FILE = By.linkText("Import from file");
    private static final By IMPORTS_LIST = By.cssSelector("#body-wrapper home fieldset table tbody");

    public OpenConceptLabSuccessPage(Page page) {
        super(page);
    }

    @Override
    public String getPageUrl() {
        return PAGE_URL;
    }

    public SubscriptionPage clickEditSubscriptionButton() {
        clickOn(EDIT_SUBSCRIPTION_BUTTON);
        return new SubscriptionPage(this);
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
