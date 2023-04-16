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

public class ManageLocationsOnAdminPage extends Page {

    private static final By ADD_LOCATION_LINK = By.linkText("Add Location");

    public ManageLocationsOnAdminPage(Page parent) {
        super(parent);
    }

    public AdministrationAddEditLocationPage clickOnAddLocationLink() {
        clickOn(ADD_LOCATION_LINK);
        return new AdministrationAddEditLocationPage(this);
    }

    public void toggleRetired() {
        clickOn(By.cssSelector("#content b a"));
    }

    public void clickOnEditLocationLink(String location) {
        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("location name must be provided");
        }
        clickOn(By.linkText(location));
    }

    @Override
    public String getPageUrl() {
        return "/openmrs/admin/locations/location.list";
    }

}
