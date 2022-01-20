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

public class OpenConceptLabPage extends Page {
	
	private static final String PAGE_URL = "/owa/openconceptlab/index.html#/";
    private static final By SETUP_SUBSCRIPTION_BUTTON = By.linkText("Setup Subscription");
    public OpenConceptLabPage(Page page) {
        super(page);
    }
    
    @Override
    public String getPageUrl() {
        return PAGE_URL;
    }

    public SubscriptionPage clickOnsetupSubscriptionButton() {
        clickOn(SETUP_SUBSCRIPTION_BUTTON);
        return new SubscriptionPage(this);
    }
}
